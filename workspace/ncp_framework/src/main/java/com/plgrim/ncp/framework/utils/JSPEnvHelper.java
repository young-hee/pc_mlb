package com.plgrim.ncp.framework.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspContext;

import org.springframework.core.env.Environment;

import com.plgrim.ncp.framework.cloud.HealthCheckBean;
import com.plgrim.ncp.framework.commons.ContextService;
import com.plgrim.ncp.framework.commons.LocaleService;

public class JSPEnvHelper {
	private static JSPEnvHelper INSTANCE;
	
	private boolean isInitialized = false;
	private HealthCheckBean healthCheckBean;
	private Environment env;
	private String version;
	private boolean defaultJsDebugMode = false;
	
	private static synchronized void init() {
		JSPEnvHelper helper = new JSPEnvHelper();
		helper.healthCheckBean = ContextService.getBean(HealthCheckBean.class);
		helper.version = helper.healthCheckBean.getVersion();
		helper.env = ContextService.getBean(Environment.class);
		// local profile에서는 jsDebugMode를 true, dev/stg/prd에서는 jsDebugMode를 false로 한다.
		if (ContextService.isLocalProfile()) {
			helper.defaultJsDebugMode = true;
		}
		
		if (helper.env != null) {
			helper.isInitialized = true;
		}
		
		INSTANCE = helper;
	}
	
	public static final void setDomainVariables(HttpServletRequest request, JspContext pageContext) {
		if (INSTANCE == null || !INSTANCE.isInitialized) {
			init();
		}
		pageContext.setAttribute("_version", INSTANCE.version);
//		pageContext.setAttribute("_category_version", );
		

		// jsDebugMode가 false일 때 cookie에 jsDebugMode가 true이면 다시 활성화한다.
		boolean jsDebugMode = INSTANCE.defaultJsDebugMode;
		if (!jsDebugMode) {
			String value = CookieUtil.getCookieValue(request, "jsDebugMode");
			if (value != null && value.equals("true")) {
				jsDebugMode = true;
			}
		}
		pageContext.setAttribute("_jsDebugMode", jsDebugMode);
		
		Object userDetail = ContextService.getCurrentUserDetail();
		if (userDetail == null || (userDetail instanceof String)) {
			pageContext.setAttribute("_user.mbr.mbrNo", "");
			pageContext.setAttribute("_user.mbr.mbrId", "");
			pageContext.setAttribute("_user.mbr.mbrNm", "");
			pageContext.setAttribute("_user.mbr.mbrSexSectCd", "");
			pageContext.setAttribute("_user.mbr.mbrEmail", "");
		} else {
			pageContext.setAttribute("_user", userDetail);
		}
		
		pageContext.setAttribute("_env", INSTANCE.env);
		pageContext.setAttribute("_locale", LocaleService.getCurrentLocale());
		// helper attrbiute from env
		pageContext.setAttribute("_resourceURL", INSTANCE.env.getProperty("ncp.web.resource.url"));
		pageContext.setAttribute("_image", INSTANCE.env.getProperty("ncp.image.url"));
	}
}
