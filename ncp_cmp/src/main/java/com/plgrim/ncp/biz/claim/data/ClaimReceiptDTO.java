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
import com.plgrim.ncp.base.entities.datasource1.sample.Dept;
import com.plgrim.ncp.base.entities.datasource1.sample.Emp;

/**
 * 클레임 조회 DTO.
 *
 * @author shhenry.choi
 * @since 2015. 3. 18
 */

@Data
@EqualsAndHashCode(callSuper = false)
public class ClaimReceiptDTO extends AbstractDTO {

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */

	static final long serialVersionUID = 4403992496214821087L;

    private String clmNo;                                   /* 클레임번호                    */
    private Long clmWrhsGodTurn;                            /* 클레임 입고 상품 순번         */
    private String ordNo;                                   /* 주문번호                      */
    private String adminId;                                 
    private String godCnncTpCd;                             /* 상품 연결 유형 코드           */
    private String clmStatCd;                               /* 클레임 상태 코드              */
    private String clmTpCd;                                 /* 클레임 유형 코드              */
    private String clmResnCd;                               /* 클레임 사유 코드              */
    private String clmResnCont;                             /* 클레임 사유 내용              */
    private List<ClmDlvOrdGodInfoDTO> ordCancelGodInfo;     
    private String dlvPcupspTurn;                           /* 배송지순번                    */
    private String ordGodTurn;                              /* 주문상품순번                  */
    private String qty;                                     /* 취소수량                      */
    private String clmGodResnCd;                            /* 클레임 상품 사유 코드         */
    private String pgTrnsmisTgtYn;							/* PG 연동여부 			*/
    private List<String> aplPrmTurn;                        /* 주문클레임 적용 프로모션 순번 */
    private String pkupClmYn;	/*픽업클레임 여부*/
    private String rfdBnkCd;
    private String rfdAcnthldrNm;
    private String rfdBnkAcctNo;
    
    private java.math.BigDecimal realityDlvCst;  /* 부분취소 배송비 합계 */
    private java.math.BigDecimal realityDlvCstEx;  /* 부분취소 배송비 환율적용 합계 */
    private String udterId;

    private int qtyTurn;    /* 수선 ERP 취소 내용 */
    private java.math.BigDecimal dlvCstDlvAmt;  /* 쿠폰사용금액 */
    private String dlvCstCpnNo; /*쿠폰번호(프로모션번호)*/
    private String userTrackingId;    /*트레킹아이디*/
}
