package com.plgrim.ncp.biz.delivery.data;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class PsnlInfoCJ {
	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */	
	/*
	 * 개인정보보호 대상 회원정보
	 */
	private String psnlInfoUsefSn; 			// 개인정보이용 일련번호
	private String psnlInfoUsefMbdTurn; 	// 개인정보이용 주체 순번	
	private String mbrNo;				// 회원번호
	private String langCd;				// 언어코드              
	private String mallId;				// MALL ID                
	private String dvcCd;				// 디바이스 코드                
	private String ordNo;				// 주문번호
	private String partMalSectCd;	// 입점구분, 자사(MCOM), 입점(PARTMAL)
	private String inflowSn;			// 유입일련번호
	
	private String cjComId;			// 기본 택배사(한진택배) ID
	

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
