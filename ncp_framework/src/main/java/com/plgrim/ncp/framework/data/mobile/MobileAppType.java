package com.plgrim.ncp.framework.data.mobile;

import lombok.Getter;

public enum MobileAppType {
	PLGRIMSHOP("plgrimshop_app"), _8SECONDS("8seconds_app"), BEANPOLE("beanpole_app");

	@Getter
	private String userAgentToken;

	private MobileAppType(String userAgentToken) {
		this.userAgentToken = userAgentToken;
	}
}
