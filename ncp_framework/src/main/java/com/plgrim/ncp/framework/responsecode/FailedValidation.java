package com.plgrim.ncp.framework.responsecode;

import java.io.Serializable;

import lombok.Data;

/**
 * 실패한 검증에 대한 DTO 클래스
 * 
 * @author narusas
 *
 */
@Data
public class FailedValidation implements Serializable { 

	private static final long serialVersionUID = -6176853450902230783L;

	String field;
	String errorType;

	public FailedValidation(String errorType, String field) {
		super();
		this.errorType = errorType;
		this.field = field; 
	}
}
