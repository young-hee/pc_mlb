package com.plgrim.ncp.framework.messages;

import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;

/**
 * 다음의 순서로 locale resolving을 한다.
 * <pre>
 * 1. cookie에 설정된 locale (cookie name은 ncp_base.csv의 ncp_base.locale.cookie.name 값을 사용한다.)
 * 2. request의 accept-language에 설정된 locale
 * 3. default locale (default locale은 ncp_base.csv의 ncp_base.locale.default.locale 값을 사용한다.
 * </pre>
 * @author @author Chulhui Park <charles@plgrim.com>
 *
 */
public class NCPCookieAndRequestLocaleResolver extends CookieLocaleResolver {
	
	@Value("${ncp_base.locale.supported}")
	private String supportedLocale;

	@Value("${ncp_base.locale.default.locale}")
	private String defaultLocale;

	private List<String> supportedLocales = new LinkedList<>();
	
	@PostConstruct
	public void init() {
		String[] locales = supportedLocale.split("\\|");
		for (String val : locales) {
			this.supportedLocales.add(val);
		}

		setDefaultLocale(new Locale(this.defaultLocale));
		Locale.setDefault(getDefaultLocale());
	}

	@Override
	public void setCookieDomain(String cookieDomain) {
		if (!cookieDomain.equals(".")) {
			super.setCookieDomain(cookieDomain);
		}
	}


}
