package com.plgrim.ncp.framework.systems.apps;

import com.plgrim.ncp.framework.systems.SiteType;
import org.springframework.context.annotation.Profile;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Profile(NcpSAPC.PROFILE_NAME)
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.METHOD })
public @interface NcpSAPC {
	String PROFILE_NAME = "ncp_web_pc_sa";

	String SYMBOL = "SAP";

	String MALL_ID = "SAM";
	
	SiteType SITE_TYPE = SiteType.PcSite;
}
