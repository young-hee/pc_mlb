package com.plgrim.ncp.biz.promotion.data;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractDTO;

@Data
@EqualsAndHashCode(callSuper = false)
public class SingleEventSearchDTO extends AbstractDTO{

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
		
	/**

		 
		 */
	private String ivPartner; //erp 번호

	private String evtNo;// 이벤트 번호
	
	private String endYn;// 종료이벤트 조회 여부
	
	private String mbrNo;// 회원번호

	private String mbrNm;// 회원성명
	
	private String mbrTpCd;// 회원유형속성

	private String prcSectCd;// 가격 타입
	
	private String orderBy;
	
	private String sprtrTurn;
	
	private String tgtMbrTpCd;

	private String erpCstmrNo;
	
	private String totalEnterHisCount;

	private String eventUrl;
	
	private String loginId;

	private List freeGiftTurn;

	private String setFreeGiftTurn;

	private Long evtPartcptnSn;

	private java.lang.Integer aplTurn;
	
	private String todayCheck;

	private String evtStplatCd;
	
	private List<String> evtStplatAgrCds;
	
	private String nMbrNm;
	
	private String mobileNumber;
	
	private String deviceId;
	
	private String certifyKey;
	
	private String shopId;
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
