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

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

/**
 * Locale 관련 유틸 서비스
 * 
 * <p>
 * 
 *
 * @author tktaeki.kim
 * @since 2015. 2. 25
 */
@Slf4j
public class LocaleService {

    private static LocaleResolver localeResolver;

	private static synchronized void init() {
		localeResolver = ContextService.getBean(LocaleResolver.class);
	}

	/**
	 * 시스템 정책에 따라서 현재 로케일 정보를 리턴 한다.
	 * 
	 * <p/>
	 * 
	 * getLocaleService().getCurrentLocale(request);
	 *
	 * @param request
	 *            HttpServletRequest
	 * @return Locale 시스템 정책에 맞는 로케일 오브젝트 리턴
	 * @since 2015. 3. 16
	 */
	public static Locale getCurrentLocale(HttpServletRequest request) {
		if(localeResolver == null) {
			init();
		}

        return localeResolver.resolveLocale(request);
	}

	/* 쓰레드 로컬에 저장된 현재 HttpServletRequest 객체를 얻는다. */
	public static Locale getCurrentLocale() {
		if(localeResolver == null) {
			init();
		}

		ServletRequestAttributes srAttr = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		if (srAttr == null) {
			log.warn("ServletRequestAttributes is null. using default locale");
			return Locale.getDefault();
		}
		
        return localeResolver.resolveLocale(srAttr.getRequest());
	}

	/**
	 * 시스템 정책에 따라서 현재 로케일 enum 정보를 리턴 한다.
	 * 
	 * <p/>
	 * 
	 * getLocaleService().getCurrentLang(request);
	 *
	 * @param request
	 *            HttpServletRequest
	 * @return Lang 시스템 정책에 맞는 enum 오브젝트 리턴
	 * @since 2015. 3. 16
	 */
	public static String getCurrentLang(HttpServletRequest request) {

		Locale locale = getCurrentLocale(request);
		String localeLang = locale.getISO3Language().toUpperCase();
		final String chinaLang = "CHI";

		// 중문일 경우만 변환 한다.
		if (localeLang.equals(chinaLang) || localeLang.equals("ZHO")) {
			localeLang = chinaLang;
		}

		return localeLang;
	}

}
