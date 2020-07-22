package com.plgrim.ncp.commons.taglib;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
class CodeBean implements InitializingBean{
	static String urlRootPc;
	static String urlSRootPc;
	static String urlRootMb;
	static String urlSRootMb;
	static String urlImage;
	static String currentDevice;

	@Value("${ncp.url.pc.root}")
	String lUrlRootPc;
	@Value("${ncp.url.pc.root.secure}")
	String lUrlSRootPc;
	@Value("${ncp.url.mb.root}")
	String lUrlRootMb;
	@Value("${ncp.url.mb.root.secure}")
	String lUrlSRootMb;
	@Value("${ncp.image.url}")
	String lUrlImage;

	@Value("${ncp.server.target}")
	String lCurrentDevice;

	@Override
    public void afterPropertiesSet() throws Exception {
		urlRootPc = lUrlRootPc;
		urlSRootPc = lUrlSRootPc;
		urlRootMb = lUrlRootMb;
		urlSRootMb = lUrlSRootMb;
		urlImage = lUrlImage;
		currentDevice = lCurrentDevice;
    }

	public static String getUrlRootPc() {
		return urlRootPc;
	}
	public static String getUrlSRootPc() {
		return urlSRootPc;
	}
	public static String getUrlRootMb() {
		return urlRootMb;
	}
	public static String getUrlSRootMb() {
		return urlSRootMb;
	}

}
