package com.plgrim.ncp.biz.member.data;

import lombok.Data;

@Data
public class BizNice {

	String reqInfo;
	
	String retUrl;
	
	String actionUrl;
	
	/*
	 * 인증구분
	 * pcc : 본인인증
	 * ipin : IPIN 인증
	 */
	String certificationDiv;

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