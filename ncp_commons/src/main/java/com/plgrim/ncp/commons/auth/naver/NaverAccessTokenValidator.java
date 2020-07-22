package com.plgrim.ncp.commons.auth.naver;

import com.plgrim.ncp.framework.data.SystemPK;

public interface NaverAccessTokenValidator {
	public NaverValidationResult validate(SystemPK systemPK, String userAccessToken);
}

