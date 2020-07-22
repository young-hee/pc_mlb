package com.plgrim.ncp.biz.member.result;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractResult;

@Data
@EqualsAndHashCode(callSuper=false)
public class ZipcodeResult extends AbstractResult{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3970344158574088931L;

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	private String zipcode;
	
	private String ordno;
	
	private String addrDoro;
	
	private String addrJibun;
	
	private String totalCount; //우체국 우편번호[MB]
	
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
