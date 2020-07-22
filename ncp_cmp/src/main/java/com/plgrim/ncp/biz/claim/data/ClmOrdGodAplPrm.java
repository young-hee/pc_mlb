/* Copyright (c) 2015 plgrim, Inc.
 * All right reserved.
 * http://plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 *
 * ------------------------------------------------------------------------
 * @author      shhenry.choi
 * @since       2015. 3. 17
 */
package com.plgrim.ncp.biz.claim.data;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractDTO;


/**
 * 클레임 주문사은품 DTO
 *
 * @author shhenry.choi
 * @since 2015. 3. 18
 */

@Data
@EqualsAndHashCode(callSuper = false)
public class ClmOrdGodAplPrm extends AbstractDTO {

    /*
     * ---------------------------------------------------------------------
     * Instance fields.
     * ---------------------------------------------------------------------
     */

    static final long serialVersionUID = 4403992496214821087L;
    
    /**
     * 주문사은품 적용기준
     *  금액 기준 : AMT_STDR
   	 *	수량 기준 : QTY_STDR
     */
    private String ordGftPchStdrCd;
    
    /**
     * 적용수량
     */
    private String ordGftAplCndQty;
    
    /**
     * 적용금액
     */
    private String ordGftAplCndAmt;
    
    /**
     * 상품이름
     */
    private String godNm;
    
    /**
     * 옵션이름
     */
    private String itmNm;
    
    /**
     * 적용수량
     */
    private String aplQty;
    
    /**
     * 사은품의 주문상품순번
     */
    private String ordGodTurn;

    
    private String ordNo;


    
        
    /**
     * 주문클레임상품연결 유형
     * WRHS_GOD_CNNC - 회수지시상품연결 
     */    
    private String godCnncTpCd;

	/**
	 * 상품 유형 코드
	 */
	private String godTpCd;
	
    /**
     * 출고지시상품번호
     * 물류회수지시상품 저장시 사용
     * 주문 - 교환접수시 사용
     */    
    private String dlivyDrctGodNo;

	/**
	 * 배송 순번	 
     * 물류회수지시상품 저장시
	 */
	private java.lang.Integer dlvTurn;

	/**
	 * 배송 수거지 순번	
	 */
	private java.lang.Integer dlvPcupspTurn;
	
	/**
	 * 국내 배송비 정책 일련번호
	 */
	private java.lang.Long dmstcDlvCstPlcSn;
	
	/**
	 * 배송매장 ID	 
	 */
	private String dlvShopId;

	/**
	 * 상품 번호
	 */
	private String godNo;

	/**
	 * 단품 번호
	 */
	private String itmNo;
    
    
    
    
}
