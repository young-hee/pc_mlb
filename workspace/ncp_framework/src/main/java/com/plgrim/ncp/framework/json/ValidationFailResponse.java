package com.plgrim.ncp.framework.json;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.plgrim.ncp.framework.responsecode.FailedValidation;
import com.plgrim.ncp.framework.responsecode.ResponseCode;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 실패중 사용자의 입력값 검증 과정의 실패에 대한 응답.
 * 
 * data 로 검증에 실패한 필드명 + [ 에러 종류 목록] 가진다.
 * 
 * 
 * @author narusas
 *
 */

@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
public class ValidationFailResponse extends JSendResponse {  
	String message;
	String code;
	Map<String, Set<String>> data = new HashMap<>();

	public ValidationFailResponse(ResponseCode errorCode, List<FailedValidation> failedValidations) {
		status = JsendStatus.fail;
		message = errorCode.toRawMessage();
		code = errorCode.getCode();

		data = consolidateViolations(failedValidations);
	}

	/**
	 * 하나의 필드에 대해 여러개의 검증 실패가 발생할수 있다. 필드명을 Key 로 검증실패 종류를 목록형태의 Value 로 수집한다
	 */
	public static  Map<String, Set<String>> consolidateViolations(List<FailedValidation> failedValidations) {
		Map<String, Set<String>> data = new HashMap<>();
		for (FailedValidation failedValidation : failedValidations) {
			Set<String> errorTypes = data.get(failedValidation.getField());
			if (errorTypes == null) {
				errorTypes = new HashSet<>();
				data.put(failedValidation.getField(), errorTypes);
			}
			errorTypes.add(failedValidation.getErrorType());
		}
		return data;
	}
}