package com.plgrim.ncp.framework.systems.apps;

import org.springframework.context.annotation.Profile;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Profile(Tutorial.PROFILE_NAME)
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.METHOD })
public @interface Tutorial {
	String PROFILE_NAME = "ncp_web_tutorial";

	String SYMBOL = "TUTORIAL";
}
