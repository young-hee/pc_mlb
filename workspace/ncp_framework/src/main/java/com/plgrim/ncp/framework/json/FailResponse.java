package com.plgrim.ncp.framework.json;

import com.plgrim.ncp.framework.responsecode.ResponseCode;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 비지니스적인 실패를 반환하는 응답
 * 
 * @author narusas
 *
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
public class FailResponse extends JSendResponse {

	public FailResponse() {
		status = JsendStatus.fail;
	}
}

@Getter
@Setter
class FailResponseWithData extends FailResponse {
	private Object data;

	FailResponseWithData(Object data) {
		this.data = data;
	} 
}
