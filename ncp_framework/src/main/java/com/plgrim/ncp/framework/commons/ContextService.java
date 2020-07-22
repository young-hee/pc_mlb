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
 * @since       2015. 3. 16
 */
package com.plgrim.ncp.framework.commons;

import com.plgrim.ncp.framework.enums.SecurityParam;
import com.plgrim.ncp.framework.systems.UserContextInjector;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.env.Environment;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.support.RequestContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * context(ex: spring context)관련 data를 handling 하기 위한 static helper method를 제공한다.
 *
 *
 * @author tktaeki.kim
 * @since 2015. 2. 25
 */
@Slf4j
public final class ContextService implements ApplicationContextAware {
    private static ApplicationContext context;

	@Autowired(required = false)
	private UserContextInjector userContextInjector;

    private static synchronized void setContext(ApplicationContext ctx) {
        context = ctx;
    }

    /**
     * @see org.springframework.context.ApplicationContextAware#setApplicationContext(org.springframework.context.ApplicationContext)
     */
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		setContext(applicationContext);
	}

	/**
	 * valueType과 매치되는 spring의 appliationContext에 load된 bean을 가져온다.
	 *
	 * @param valueType load된 bean의 call type
	 * @return 없으면 null return
	 */
	public static <T> T getBean(final Class<T> valueType) {
		return context.getBean(valueType);
	}

	/**
	 * Type에 따라서 현재 스프링 빈을 가져 온다.
	 *
	 * <p/>
	 *
	 * ContextService.getBean(SampleComponent.class); <br/>
	 * getContextService.getBean(SampleComponent.class);
	 *
	 * @param <T> the generic type
	 * @param valueType [설명]
	 * @return T [설명]
	 * @since 2015. 3. 16
	 */
	public static <T> T getBean(RequestContext context  ,final Class<T> valueType) {
		return 	context.getWebApplicationContext().getBean(valueType);
	}



	/* 스프링 시큐리티로 로그인된 사용자 세션정보를 얻는다. */
	public static Object getCurrentUserDetail() {
		SecurityContext context = SecurityContextHolder.getContext();
		if (context == null || context.getAuthentication() == null) {
			return null;
		} else {
			return context.getAuthentication().getPrincipal();
		}
	}

	/**
	 * 프런트 (pc, mobile)에서 인증 여부를 판단 한다.
	 * <p/>
	 * <p/>
	 *
	 * @return true:[설명], false:[설명]
	 * @since 2015. 4. 9
	 */
	@SuppressWarnings("unchecked")
	public static boolean hasRole() {
		boolean hasRole = false;
		try {
			if (SecurityContextHolder.getContext() != null && SecurityContextHolder.getContext().getAuthentication() != null
					&& SecurityContextHolder.getContext().getAuthentication().getAuthorities() != null) {
				Collection<GrantedAuthority> authorities = (Collection<GrantedAuthority>) SecurityContextHolder.getContext()
						.getAuthentication().getAuthorities();

				for (GrantedAuthority authority : authorities) {
					hasRole = authority.getAuthority().equals(SecurityParam.DEFAULT_ROLE.toString());
					if (hasRole) {
						break;
					}
				}
			}
		} catch (Exception e) {
			ContextService.log.info("hasRole() error occour :: " + e.getMessage());
		}
		return hasRole;
	}

	/* 현재 request 정보를 가져 온다. */
	public static HttpServletRequest getCurrentRequest() {
		HttpServletRequest request = null;
		try {
			ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
			request = attributes.getRequest();
		}
		catch(IllegalStateException ex){
			// Session destroy 시에는 존재하지 않기 때문에 IllegalStateException이 발생하는게 맞음
		}
		catch (Exception e) {
			ContextService.log.info("getCurrentRequest() Error : {}", e);
		}

		return request;
	}

	/* 현재 response 정보를 가져 온다. */
	public static HttpServletResponse getCurrentResponse() {
		HttpServletResponse response = null;
		try {
			ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
			response = attributes.getResponse();
		} catch (IllegalStateException ex) {
			// Session destroy 시에는 존재하지 않기 때문에 IllegalStateException이 발생하는게 맞음
		} catch (Exception e) {
			ContextService.log.info("getCurrentResponse() Error : {}", e);
		}

		return response;
	}

	public String getMallUrl(HttpServletRequest request) {
		request.getParameter("mall-id");
		return "";
	}

	private static boolean isExistLocalProfile(String[] profiles) {
		for (String profile : profiles) {
			if (profile.equals("local")) {
				return true;
			}
		}
		return false;
	}

    public static boolean isLocalProfile() {
        Environment environment = context.getEnvironment();
        String[] activeProfiles = environment.getActiveProfiles();
        if (isExistLocalProfile(activeProfiles)) {
            return true;
        } else if (activeProfiles != null && activeProfiles.length == 0 && isExistLocalProfile(environment.getDefaultProfiles())) {
            return true;
        }
        return false;
    }

    /* 스프링 시큐리티로 로그인된 BO 사용자 담당 몰 정보를 얻는다. */
	public List<Map<String, String>> getCurrentUserAuthMallList() {
		return userContextInjector.getCurrentUserAuthMallList();
	}
}
