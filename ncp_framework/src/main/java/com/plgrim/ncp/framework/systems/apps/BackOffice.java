package com.plgrim.ncp.framework.systems.apps;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Profile;

@Profile(BackOffice.PROFILE_NAME)
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.METHOD })
public @interface BackOffice {
	String PROFILE_NAME = "ncp_web_bo";

	String SYMBOL = "BO";
}
