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
package com.plgrim.ncp.biz.claim.result;

import com.plgrim.ncp.base.abstracts.AbstractResult;
import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractDTO;

/**
 * 주문상세 클레임 result
 *
 * @author shhenry.choi
 * @since 2015. 3. 18
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class OrdDetailClmInfoResult extends AbstractResult {
	/**
	 * 
	 */
    private static final long serialVersionUID = -8425614231273166291L;

    /**
     * 클레임 번호
     */
    private String clmNo;
    
    /**
     * 클레임 상태
     */
    private String clmStatNm;
    
    /**
     * 클레임 유형
     */
    private String clmTpNm;
    
    /**
     * 클레임 코드
     */
    private String clmTpCd;    
    
    /**
     * 클레임 사유
     */
    private String clmResnNm;
    
    /**
     * 회수상태
     */
    private String rtrvl;
    
    /**
     * 국내회수송장
     */
    private String dmstcWaybilNo;
    
    /**
     * 해외회수송장
     */
    private String ovseaWaybilNo;
    
    /**
     * 편의점택배
     */
    private String cvnstorHdryAprvNo;
    
    /**
     * 상품명
     */
    private String clmGodNm;
    
    /**
     * 클레임 수량
     */
    private String clmQty;
    
    /**
     * 철회수량
     */
    private String clmWthdrawQty;
    
    /**
     * 신청일시
     */
    private String clmDt;
    
    /**
     * 환불여부
     */
    private String rfdStatNm;
    
    /**
     * 신청자
     */
    private String regNm;
    
    /**
     * 접수자
     */
    private String rceptNm;
    
    /**
     * 국내배송 송장추적URL
     */
    private String dmstcWaybilTraceUrl;
    
    /** 무료 반품 교환 쿠폰 */
    private String rtGodCpnYn;
    private String exchgCpnYn;
    
    /** 클레임 선진행 구분 코드 */
    private String clmPreprogrsSectCd;
    /** 수거상태 */
    private String gdgpStatCd;
    
    private int dlvPcupspTurn;
    private int dlvTurn;
    private String ordNo;
    private String dlvComCd;
    // 재수거 가능 여부
    private String rePickupFlag;
    // 재수거 이력 화면 노출 여부
    private String rePickupHistFlag;
    
    /**
     * 환불오류내용
     */
    private String rfdErrCont;
}
