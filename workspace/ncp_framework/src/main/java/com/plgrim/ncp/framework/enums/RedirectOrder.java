package com.plgrim.ncp.framework.enums;

public enum RedirectOrder {

	/*
	* 리다이렉트 유형을 정의 한다.
	*
	* JSON : 리턴값을 JSON으로 한다.
	* TARGET_PARAM : 파라미터로 넘겨온 URL
	* SESSION_URL : Session에 저장되어 있는 URL
	* HEADER_REFERER : 헤더 REFERER에 있는 URL
	* DEFAULT : 기본 URL
	* */
	JSON, TARGET_PARAM, SESSION_URL,SESSION_REFERER_URL, HEADER_REFERER, DEFAULT;

}
