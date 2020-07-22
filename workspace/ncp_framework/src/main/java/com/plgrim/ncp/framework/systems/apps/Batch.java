package com.plgrim.ncp.framework.systems.apps;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Profile;

@Profile(Batch.PROFILE_NAME)
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.METHOD })
public @interface Batch {
	String PROFILE_NAME = "ncp_batch_server";
	
	String SYMBOL = "BT";
}
