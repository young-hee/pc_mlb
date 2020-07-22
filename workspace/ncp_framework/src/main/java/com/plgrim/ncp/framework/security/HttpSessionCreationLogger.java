package com.plgrim.ncp.framework.security;

import org.springframework.context.ApplicationListener;
import org.springframework.security.web.session.HttpSessionCreatedEvent;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class HttpSessionCreationLogger implements ApplicationListener<HttpSessionCreatedEvent> {

	@Override
	public void onApplicationEvent(HttpSessionCreatedEvent event) {
//		log.info("New Session is created. SessionId: {}", event.getSession().getId());		
	}

}
