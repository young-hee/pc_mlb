package com.plgrim.ncp.framework.systems.apps;

import com.plgrim.ncp.framework.systems.SiteType;
import org.springframework.context.annotation.Profile;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Profile(NcpSAMB.PROFILE_NAME)
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.METHOD })
public @interface NcpSAMB {
	String PROFILE_NAME = "ncp_web_mb_sa";

	String SYMBOL = "SAM";

	String MALL_ID = "SAM";
	
	SiteType SITE_TYPE = SiteType.MobileSite;
}
