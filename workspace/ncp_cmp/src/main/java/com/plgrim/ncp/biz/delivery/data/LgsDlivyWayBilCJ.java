package com.plgrim.ncp.biz.delivery.data;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class LgsDlivyWayBilCJ {
	
	private String cjComId;				// CJ 코드
	private String ordNo;				// 주문번호
	private String dlvShopId;			// 배송매장ID
	private String dmstcWaybilNo;		// 운송장번호              
	private String qty;					// 수량
	
	private String dlvPcupspTurn;		// 배송수거지순번
	private String dlvTurn;				// 배송순번
	private String gdgpStatCd;			// 집하상태

	private String clmNo;				// 클레임 번호

	//01: 일반, 02: 반품
	String rcptDv;						// 접수구분	RCPT_DV     P.K
	
	private String sndZip;			// 송하인/수하인 우편번호         
	private String sndAd1;			// 송하인/수하인 주소1(동이상 주소)
	private String sndAd2;			// 송하인/수하인 주소2(동이하 주소)
	private String sndNam1;			// 송하인/수하인                
	private String sndTel1;			// 송하인/수하인 전화번호
	private String sndTel2;			// 송하인/수하인 전화번호
	private String sndTel3;			// 송하인/수하인 전화번호

	private String cjBoxType;		// 박스타입코드 ( 01: 극소,  02: 소,  03: 중,  04: 대,  05: 특대 )
	private String cjCustomerId;	// CJ 고객 아이디
	private String cjWmsCustomerId;	// CJ 물류 고객 아이디
	
	private String brnd;

	/**
	 * 회원번호
	 */
	private String mbrNo;

	/**
	 * 택배사 코드
	 */
	private String dlvComCd;

	/**
	 * 수정자
	 */
	private String udterId;
	
	private String custUseNo;

}
