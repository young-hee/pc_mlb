/* Copyright (c) 2015 plgrim, Inc.
 * All right reserved.
 * http://plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 *
 * ------------------------------------------------------------------------
 * @author      
 * @since       2015. 3. 30       
 */
package com.plgrim.ncp.biz.delivery.data;

import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractDTO;

/**
 * [운송장 dto]
 * 
 * <p>
 * 
 * <ul>
 *   <li> [기능1]
 *   <li> [기능2]
 * </ul>.
 *
 * @author stdev10.kwon
 * @since 2015. 3. 30
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class DeliveryInvoiceDTO extends AbstractDTO {

	/**
	 * 
	 */
    private static final long serialVersionUID = -3030308300128453744L;

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	/** 주문번호. */
	private String  ordNo;
	
	/** 클레임번호. */
	private String  clmNo;

	/** 상품코드. */
	private String  godNo;
	
	/** 상품명. */
	private String godNm;
	
	/** 수량. */
	private String ordQty;
	
	/** 판매가. */
	private String csmrPrc;
	
	/** 택배사. */
	private String dlvComCd;
	
	/** 택배사명. */
	private String dlvComNm;
	
	/** 신규 택배사. */
	private String newDlvComCd;
	
	/** 운송장번호. */
	private String dmstcWaybilNo;
	
	/** 신규운송장번호. */
	private String newDmstcWaybilNo;
	
	/** 최종수정일시. */
	private String udtDt;
	
	/** 배송 순번. */
	private String dlvTurn;
	
	/** 배송수거지 순번. */
	private String dlvPcupspTurn;
	
	/** 수정자아이디. */
	private String udterId;
	
	/** 등록자아이디. */
	private String regtrId;
	
	/** 배송상태. */
	private String dlvStatCd;

	/** 신규배송상태. */
	private String newDlvStatCd;
	
	/** 출고지시상품번호. */
	private String dlivyDrctGodNo;
	
	/** 출고지시수량. */
	private int dlivyDrctQty;
	
	/** 배송매장메모. */
	private String dlivyDrctGodMemoCont;
	
	/** 정산구분. */
	private String rtgodcstCalSectCd;
	
	/** 배송비. */
	private String rtgodcstCalAmt;
	
	/** 회수지시상품번호. */
	private String rtrvlDrctGodNo;
	
	/** 출고유형코드. */
	private String dlivyDrctTpCd;
	
	/** 패키지형상품 순번 */
	private int pckageGodTurn;
	
	private String rtrvlStatCd;
	private String newRtrvlStatCd;
	private String rfdDelayCd;		//환불지연사유코드
	private String rfdDelayResnDscr;//기타사유

	private Date dlivyPrearngeDt;	//출고예정일시

	/** 환불처리지연요청 */

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
