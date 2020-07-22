/* Copyright (c) 2015 plgrim, Inc.
 * All right reserved.
 * http://plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 *
 * ------------------------------------------------------------------------
 * @author      tktaeki.kim
 * @since       2015. 3. 25       
 */
package com.plgrim.ncp.base.mybatis;

import java.lang.reflect.Field;
import java.sql.Statement;
import java.util.List;
import java.util.Properties;

import com.plgrim.ncp.base.abstracts.AbstractEntity;
import com.plgrim.ncp.base.abstracts.AbstractResult;
import com.plgrim.ncp.framework.annotation.MaskingFormat;
import com.plgrim.ncp.framework.utils.ReflectionUtil;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;

import lombok.extern.slf4j.Slf4j;


@Intercepts({@Signature(type=StatementHandler.class, method="query", args={Statement.class, ResultHandler.class})})
@Slf4j
public class MybatisInterceptor implements Interceptor {


	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	private Properties properties;


	/*
	 * ---------------------------------------------------------------------
	 * Constructors.
	 * ---------------------------------------------------------------------
	 */

	/*
	 * ---------------------------------------------------------------------
	 * public & interface method.
	 * ---------------------------------------------------------------------
	 */

	@Override
	public Object intercept(Invocation invocation) throws Throwable {

		Object dbResult = invocation.proceed();

		if (dbResult == null) {
			return dbResult;
		}

		if (dbResult instanceof Object[]) {
			for (Object obj : (Object[]) dbResult) {
				maskObjectFields(obj);
			}
		} else if (dbResult instanceof List<?>) {
			for (Object obj : (List<?>) dbResult) {
				maskObjectFields(obj);
			}
		} else {
			maskObjectFields(dbResult);
		}

		return dbResult;
	}

	@Override
	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	@Override
	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	public Properties getProperties() {
		return this.properties;
	}

	protected static void maskObjectFields(Object dbResult) {
		if (dbResult == null) {
			return;
		}

		Field[] declaredFields = ReflectionUtil.getDeclaredFieldsAll(dbResult.getClass());

		for (Field field : declaredFields) {
			Object valueOfField = ReflectionUtil.getFieldValue(dbResult, field);

			if (valueOfField == null) {
				continue;
			}

			if (AbstractEntity.class.isAssignableFrom(field.getType())) {
				maskObjectFields(valueOfField);
			} else if (List.class.isAssignableFrom(field.getType())) {
				for (Object element : (List<?>) valueOfField) {
					maskObjectFields(element);
				}
			} else {
				mask(dbResult, field, valueOfField);
			}
		}
	}

	protected static void mask(Object dbResult, Field field, Object valueOfField) {
		if (!String.class.isAssignableFrom(field.getType())) {
			return;
		}

		MaskingFormat mf = field.getAnnotation(MaskingFormat.class);
		if (mf == null) {
			return;
		}

		String strValue = String.valueOf(valueOfField);
		String masked = strValue.replaceAll(mf.regexPattern(), mf.replacePattern());

		ReflectionUtil.setFieldValue(dbResult, field, masked);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */

}
