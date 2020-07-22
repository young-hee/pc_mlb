package com.plgrim.ncp.commons.auth.facebook;

public interface FacebookAccessTokenValidator {
	public ValidationResult validate(String userAccessToken);
}
