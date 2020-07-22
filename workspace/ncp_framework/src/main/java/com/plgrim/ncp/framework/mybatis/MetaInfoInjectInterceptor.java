package com.plgrim.ncp.framework.mybatis;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;

import com.plgrim.ncp.framework.systems.ExecutionContext;
import com.plgrim.ncp.framework.systems.RequestInfo;
import com.google.common.collect.Sets;

import lombok.extern.slf4j.Slf4j;

@Intercepts({ @Signature(type = Executor.class, method = "update", args = { MappedStatement.class, Object.class }) })
@Slf4j
public class MetaInfoInjectInterceptor implements Interceptor {
	Set<Class> enabledClasses = Sets.newConcurrentHashSet();
	Set<Class> disabledClasses = Sets.newConcurrentHashSet();

	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		Object[] args = invocation.getArgs();

		if (ArrayUtils.isNotEmpty(args)) {
			inject(args);
		}
		return invocation.proceed();
	}

	private void inject(Object[] args) {
		try {
			RequestInfo info = ExecutionContext.currentRequestInfo();
			if (info == null || info.getUserSessionId() == null) {
				return;
			}
			for (Object arg : args) {
				if (hasProperty(arg.getClass())) {
					PropertyUtils.setProperty(arg, "userTrackingId", info.getUserSessionId());
					return;
				}
			}
		} catch (Exception e) {
			log.warn("Fail to inject userTrackingId", e);
			// 주입이 실패했다고 실행이 원래 비니지스 로직이 멈추면 않됨
		}
	}

	private boolean hasProperty(Class<? extends Object> clazz) {
		try {
			if (enabledClasses.contains(clazz)) {
				return true;
			}
			if (disabledClasses.contains(clazz)) {
				return false;
			}
			Method method = clazz.getMethod("setUserTrackingId", String.class);
			if (method != null) {
				enabledClasses.add(clazz);
				return true;
			}
			disabledClasses.add(clazz);
			return false;
		} catch (NoSuchMethodException e) {
			disabledClasses.add(clazz);
			return false;
		} catch (SecurityException e) {
			disabledClasses.add(clazz);
			return false;
		}
	}

	@Override
	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	@Override
	public void setProperties(Properties properties) {

	}

}
