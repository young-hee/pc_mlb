package com.plgrim.ncp.biz.display.data;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class DspCtgryDTO implements Serializable {

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	private static final long serialVersionUID = -3322855233470757931L;
	String prcSectCd;
	/* 몰 아이디 */
	String mallId;

	/* 언어코드 */
	String lang;

	/* 접속 채널 */
	String channel;

	/* 접속 디바이스 */
	String device;
	
	String tgtMbrTpCd;
	
	String absMbrAtrbCd;
}
