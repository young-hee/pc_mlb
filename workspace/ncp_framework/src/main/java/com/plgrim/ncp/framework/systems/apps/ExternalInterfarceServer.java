package com.plgrim.ncp.framework.systems.apps;

import org.springframework.context.annotation.Profile;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Profile(ExternalInterfarceServer.PROFILE_NAME)
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.METHOD })
public @interface ExternalInterfarceServer {
	String PROFILE_NAME = "ncp_if_external_server";

	String SYMBOL = "EX_IF";

}
