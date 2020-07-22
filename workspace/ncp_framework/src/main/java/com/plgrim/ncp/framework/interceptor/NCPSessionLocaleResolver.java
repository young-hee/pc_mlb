package com.plgrim.ncp.framework.interceptor;

import java.util.Enumeration;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.i18n.SessionLocaleResolver;

public class NCPSessionLocaleResolver extends SessionLocaleResolver {

	private List<String> supportLocales;

	public void setSupportLocale(List<String> supportLocales) {
		this.supportLocales = supportLocales;
	}

	public List<String> getSupportLocale() {
		return supportLocales;
	}

	@Override
	protected Locale determineDefaultLocale(HttpServletRequest request) {
		if (supportLocales != null) {
			Enumeration<Locale> rLocale = request.getLocales();
			Locale rl;
			while(rLocale.hasMoreElements()){
				rl = rLocale.nextElement();
				for (String lc : supportLocales) {
					if (rl.toLanguageTag().startsWith(lc)) {
						return rl;
					}
				}
			}
		}
		return getDefaultLocale();
	}

}
