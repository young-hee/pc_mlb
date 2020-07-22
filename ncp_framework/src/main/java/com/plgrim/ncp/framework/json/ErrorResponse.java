package com.plgrim.ncp.framework.json;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

/**
 * 시스템적인 실패를 반환하는 응답
 * 
 * @author narusas
 *
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
@Slf4j
public class ErrorResponse extends JSendResponse {
	String message;

	public ErrorResponse(String message) {
		status = JsendStatus.error;
		this.message = message;

	} 
}