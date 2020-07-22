/* Copyright (c) 2013 plgrim, Inc.
 * All right reserved.
 * http://plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 *
 * Revision History
 * Author              Date                         Description
 * ------------------   --------------                  ------------------
 *                       
 */
package com.plgrim.ncp.biz.order.result;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractResult;
import com.plgrim.ncp.base.entities.datasource1.god.GodItm;
import com.plgrim.ncp.base.entities.datasource1.ord.OrdGodFoExtend;



@Data
@EqualsAndHashCode(callSuper = false)
public class MypageOrderFoResult extends AbstractResult {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /*
     * ---------------------------------------------------------------------
     * Instance fields.
     * ---------------------------------------------------------------------
     */

    /** === Entity START ==============================================================  */
	/**
	 * 주문 번호
	 */
	private String mbrNo = "";
	private String cstmrNm = "";
	private String cstmrMobilNo = "";
	private String ordNo = "";
	private String dateStart = "";
	private String dateEnd = "";
	private java.lang.Integer clmCnt;					/* 다중반품 표시를 위한 클레임 건수 */
	private String ordTpCd;       						/* 주문 유형 코드 */
	private String ordDt;               				/* 주문 일시 */
	private String ordDt2;               				/* 주문 일시2 */
	private String ordStatCd; 							/* 주문 상태 코드 */
	private String godNm;     							/* 상품명 */
	private String lstImgUrl;     						/* 상품이미지명 */
	private String ordGodTurn;							/* 주문 상품 순번 */
	private String rcptfrReqstCd; 						/* 영수증 신청 코드 */
	private String rcptfrPrcsCd; 						/* 영수증 처리 코드 */
	private String rcptfrAprvNo; 						/* 영수증 승인번호 */
	private String payMnCd; 							/* 주결제 */
	private String viewType; 							/* 메인페이지 여부 */
	
	
	private String dlvShopId;                          /* 배송 매장 ID */
	private String dlivyDrctDt;                        /* 출고지시일 */
	private String dlivyForcastDt;                     /* 배송매장 배정일+1 */
	
	
	private java.math.BigDecimal payCrncyPayAmt; 		/* 결제 통화 결제 금액 */
	private String pgStoreId; 						    /* 상점id */
	private java.math.BigDecimal payExchgRtCrncySumAmt; /* 결제 환율 통화 합계 금액 */
	private java.math.BigDecimal payExchgRtCrncyAmt; 	/* 클레임 결제 환율 통화 합계 금액 */
	private List<OrdGodFoExtend> ordGodList;       		/* 주문상품 정보  */
	private MypageOrderCancelFoResult orderCancel;      /* 취소 가능여부 */
	
	private String godNo;
	private String ordItmNo;
	private String godSaleSectCd;
	private List<GodItm> godItmList; 					/* 상품 옵션 엔티티 */

	private String payNo;
	private String upperPayNo;
	private String statCd;
	private String ordQty;
	private String dlvPcupspTurn;
	
	private String srchType;
	
	private String resveOrdDlivyPrearngeDate;
	private String payTmlmtDt;
	
	private java.lang.Integer godEvlCnt;									/* 상품리뷰 등록 건 */
	
	private List<String> plgrimshopMallIdList;					/* 통합몰에 노출될 mall id list */
	
    /** === Entity ENT   ==============================================================  */

	private String dealTpCd;
	private String pgSectCd;                        /* PG 구분코드  */
    private String pgAprvNo;                        /* PG 승인번호  */
	private String pgDealNo;                        /* PG 거래 번호 */
	private String pgCrsKey;                        /* PG 교차 키  */

	private String hashValue;                  /* PG 영수증 호출을 위한 HashValue */
	private String currentTime;                  	/* PG 영수증 호출을 위한 currentTime */

	private String rn;
	private String payTpCd;
	private java.math.BigDecimal upperPayAmt; 		/* 상위 결제 통화 결제 금액 */
	private String clmNo;
	
	/* #45596 [개발]주문내역조회화면에서 신청 가능 및 신청화면 UX 개선(결제수단변경용) */
	private java.math.BigDecimal saleSumAmt; 	   	/* 실소가 */
	private java.math.BigDecimal allDcSumAmt;    	/* 전체할인 합계 금액 */
	private java.math.BigDecimal empDcSumAmt;       /* 임직원할인 금액 */
	private java.math.BigDecimal unityPntUseSumAmt; /* 통합 포인트 사용 합계 금액 */
	private java.math.BigDecimal evtPntUseSumAmt;   /* 이벤트 포인트 사용 합계 금액 */
	private java.math.BigDecimal webpntUseSumAmt;   /* 마일리지 사용 합계 금액 */
	private java.math.BigDecimal dlvCstSumAmt;    	/* 배송비 합계 금액 */

	private String talkYn;                          /* 시나리오 채팅 호출 여부(Y 일 경우 톡상담 버튼 미노출)*/


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
