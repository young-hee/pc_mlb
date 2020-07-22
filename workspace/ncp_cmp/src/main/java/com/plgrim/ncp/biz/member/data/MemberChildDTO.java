package com.plgrim.ncp.biz.member.data;


import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class MemberChildDTO implements Serializable {

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields...
	 * ---------------------------------------------------------------------
	 */
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2160644988915724312L;

	private String childrenName;
	private String childrenBirthYear;
	private String childrenBirthMonth;
	private String childrenBirthDay;
	/*
	 * ---------------------------------------------------------------------
	 * Constructors.
	 * ---------------------------------------------------------------------
	 */

	/*
	 * ---------------------------------------------------------------------
	 * public & interface method.
	 * ---------------------------------------------------------------------
	 */

	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */

	
}
