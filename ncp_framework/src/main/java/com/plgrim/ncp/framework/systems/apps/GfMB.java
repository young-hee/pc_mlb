package com.plgrim.ncp.framework.systems.apps;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Profile;

import com.plgrim.ncp.framework.systems.SiteType;

@Profile(GfMB.PROFILE_NAME)
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.METHOD })
public @interface GfMB {
	String PROFILE_NAME = "ncp_web_mb_dx";

	String SYMBOL = "GFM";

	String MALL_ID = "DXM";
	
	SiteType SITE_TYPE = SiteType.MobileSite;
}
