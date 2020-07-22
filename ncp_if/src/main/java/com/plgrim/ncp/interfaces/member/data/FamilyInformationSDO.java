package com.plgrim.ncp.interfaces.member.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.plgrim.ncp.base.abstracts.AbstractSDO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
public class FamilyInformationSDO extends AbstractSDO {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 자녀 이름
	 */
	@JsonProperty("FAMILY_NAME")
	private String familyName;
	/**
	 * 자녀 생년월일
	 */
	@JsonProperty("FAMILY_BIRTH")
	private String familyBirth;
}