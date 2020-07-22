package com.plgrim.ncp.biz.member.data;

import java.io.Serializable;

import lombok.Data;

@Data
public class Ipin implements Serializable {

	 /**
	 * 
	 */
    private static final long serialVersionUID = 3622430197450172480L;
	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	String reqnum;
	String vdiscrno;
	String name;
	String result;
	String age;
	String sex;
	String ip;
	String authinfo; 
	String birth;		
	String fgn;		
	String discrhash;
	String civersion;
	String ciscrhash;
	String birthday;
	String certGb;
	String age_yn;
	
	String reqIpinInfo;
	String reqIpinNum;
	String retIpinUrl;
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
