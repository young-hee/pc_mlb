package com.plgrim.ncp.framework.systems.apps;

import com.plgrim.ncp.framework.systems.SiteType;
import org.springframework.context.annotation.Profile;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Profile(NcpMLBPC.PROFILE_NAME)
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.METHOD })
public @interface NcpMLBPC {
	String PROFILE_NAME = "ncp_web_pc_mlb";

	String SYMBOL = "MLBP";

	String MALL_ID = "MBM";
	
	SiteType SITE_TYPE = SiteType.PcSite;
}
