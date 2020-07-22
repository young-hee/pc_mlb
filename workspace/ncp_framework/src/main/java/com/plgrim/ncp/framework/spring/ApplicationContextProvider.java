package com.plgrim.ncp.framework.spring;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import lombok.Getter;

@Component
public class ApplicationContextProvider implements ApplicationContextAware {

	@Getter
	static ApplicationContext context;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		ApplicationContextProvider.context = applicationContext;
	}

}
