package com.plgrim.ncp.interfaces.member.data;

import com.plgrim.ncp.base.abstracts.AbstractSDO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
public class ChildrenInformationSDO extends AbstractSDO {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 자녀 이름
	 */
	private String childrenName;
	/**
	 * 자녀 생년월일
	 */
	private String childrenBirth;
}