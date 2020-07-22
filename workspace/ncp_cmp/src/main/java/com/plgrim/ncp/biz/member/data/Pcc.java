package com.plgrim.ncp.biz.member.data;

import java.io.Serializable;

import lombok.Data;

@Data
public class Pcc implements Serializable{

	/**
	 * 
	 */
    private static final long serialVersionUID = 6840190560882141204L;

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	String retInfo		= "";																// 결과정보

	String name			= "";                                                               //성명
	String sex			= "";																//성별
	String birYMD		= "";																//생년월일
	String fgnGbn		= "";																//내외국인 구분값

	String di			= "";																//DI
	String ci			= "";																//CI
	String civersion    = "";                                                               //CI Version

	String reqNum		= "";                                                               // 본인확인 요청번호
	String result		= "";                                                               // 본인확인결과 (Y/N)
	String certDate		= "";                                                               // 검증시간
	String certGb		= "";                                                               // 인증수단
	String cellNo		= "";																// 핸드폰 번호
	String cellCorp		= "";																// 이동통신사
	String addVar		= "";
	
	String reqPccInfo;
	String reqPccNum;
	String retPccUrl;
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
