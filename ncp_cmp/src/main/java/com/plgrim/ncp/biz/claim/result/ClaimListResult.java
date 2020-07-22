/* Copyright (c) 2015 plgrim, Inc.
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
 * beyondj2ee			2015.02.09
 */
package com.plgrim.ncp.biz.claim.result;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractResult;

@Data
@EqualsAndHashCode(callSuper=false)
public class ClaimListResult extends AbstractResult {/**
	 *
	 */
    private static final long serialVersionUID = -7866470360927493155L;

    //클레임 번호
	private String clmNo;

	//주문번호
	private String ordNo;

	//자사/제휴사 코드
	private String partmalSectCd;

	//자사/제휴사 이름
	private String partmalSectCdNm;

	//몰
	private String mallId;

	//언어
	private String langCd;

	//신청일시
	private java.util.Date clmDt;

	//클레임구분
	private String clmTpCd;

	//클레임구분 이름
	private String clmTpCdNm;

	//클레임상태
	private String clmStatCd;

	//클레임상태 이름
	private String clmStatCdNm;

	//클레임사유
	private String clmResnCd;

	//클레임사유 이름
	private String clmResnCdNm;
	
	//클레임사유 이름
	private String clmResnCont;

	//환불상태
	private String rfdStatCd;

	//환불에러사유
	private String rfdErrCont;

	//환불상태 이름
	private String rfdStatCdNm;

	//회수구분
	private String dlvMnCd;

	//회수구분 이름
	private String rtrvlStatCdNm;

	//판매제휴사
	private String saleAffComId;

	//판매제휴사
	private String saleAffComIdNm;

	//상품코드
	private String erpGodNo;

	private String godNo;

	//상품명
	private String godNm;

	// 옵션명
	private String itmNm;

	// 업체명
	private String comNm;

	//상품요약
	private String godSumry;

	//브렌드
	private String brndId;

	//주 결제금액 결제방법
	private String payNmCd;

	//주 결제금액 결제방법 이름
	private String payNmCdNm;

	//주 결제금액
	private String paySumAmt;

	//멤버쉽포인트결제
	private String payExchgRtCrncySumAmt;

	//이벤트포인트결제액
	private String evtPntUseSumAmt;

	//적립 멤버쉽 포인트
	private String unityPntAccmlAmt;

	//적립웹포인트
	private String webpntAccmlAmt;

	//구매자 이름
	private String cstmrNm;

	//구매자 연락처
	private String cstmrMobile;

	//수취인 이름
	private String addrseNm;

	//수취인연락처
	private String addrseMobile;

	//CS접수자
	private String receptAdminId;

	//CS접수자 이름
	private String csAdmin;

	//CS접수일시
	private java.util.Date rceptDt;

	//완료일시
	private java.util.Date comptDt;

	//클레임 쿠폰 발급정보 예 > 클레임 할인쿠폰 외 3개
	private String clmCpnInfo;

	//쿠폰 발급자
	private String issuAdminId;

	//제휴사주문번호
	private String saleAffComOrdNo;


	private String dlivyDlayDay;
	private String dlivyDrctTpNm;
	private java.util.Date ordDt;
	private java.util.Date dlivyDrctDt;
	private java.util.Date shtgDt;
	private String dlvShopNm;
	private String godSaleSectNm;
	private String usefulInvQty;
	private String dlivyDrctQty;
	private String shtgQty;
	private String mbrNm;
	private String mallNm;
	private String ordTpNm;
	private String affComNm;
	private String skuNo;
	private String godTpNm;
	private String dlvStatNm;
	private String itmStatNm;
	private int ordGodTurn;
	private String delayTerm;
	private String elapsedTime;
	private String remainTime;

	private String affGodOrdNo;
	private String affItmOrdNo;

	private String giftRtrvlTgtYn;


	private String lstImgUrl;                          //상품이미지
    private String colorNm;                           	//색상 명
    private String foBrndNm;                           	//브랜드명
    private String brndGrpId;
    private String repairComptCd;
    private String repairComptCdNm;						//수선완료코드명
    private Integer ordClmQty;
    private String setGodNo;
    private String pckageGodTpCd;
    private String dlvSectCd; //상품전달방법
    /* 매장접수일 경우 접수 매장 정보*/
    private String shopNm;
    private String shopTelAreaNo;
    private String shopTelTlofNo;
    private String shopTelTlofWthnNo;

    private String rdmstcWaybilNo;
    private String clmRceptMthdCd;

    private String dlvComTrnsmisYn;
    private String dlvStatCd;
    private String ddlvComUrl;
    private String rdlvComUrl;
    private String rgdgpStatCd;
    private String dgdgpStatCd;
    private String rgdgpStatStr;
    private String dgdgpStatStr;

    /**무료수선관련 추가(이진형)**/
	private int qtyTurn;
	private String repairNo;
	private String langNm;
	private String pkupShopId;
	private String pkupShopNm;
	private String clmRceptMthdCdNm;
	private String rtrvlStatCd;
	private String rtrvlStatNm;
	private String rdlvComCd;
	private String rdlvComNm;
	private String ddmstcWaybilNo;
	private String dlvSectNm;
	private String ddlvComCd;
	private String ddlvComNm;
	private String dlvComptDt;
	private String drawDt;
	private String stdrCrncyUntPrc;
	private String rceptAdminId;
	private String udtDt;
	private String udterId;
	private String regtrNm;
	private String regtrId;
	private String mbrNo;

	/*2017.05.02 이진형 추가*/
	private int clmWrhsGodTurn;
	private int dlvPcupspTurn;
	private int dlivyQty;
	private int dlvPcupspTurnDlivy;
	private String dlivyDrctGodNo;
	private int rtrvlDrctQty;
	private String ordTpCd;

	/*2017.05.17 이진형 추가*/
	private String retRepairStatNm;
	private String pkupShopTelNo;
	private String repairRceptChrgerNm;
	private String repairRceptChrgerPhon;
	private String boRepairStatNm;
	private String boRepairStatCd;

	/**무료수선관련 '17 6/13 오픈 후 추가**/
	private String mbrId;

	/**무료수선관련 '17 6/27 오픈 후 추가**/
	private String dlvMemo;
	private String dlvComTrnsmisTgtYn;
	private String addrseBaseAddr;
	private String addrseDetailAddr;
	
	/** [#48750][개발] 불량상품 고객에 대한 교환/반품 Process 개선 요청 **/
	// 클레임 선진행 구분 코드
	private String clmPreprogrsSectCd;
	// ERP입고확인일
	private java.util.Date erpCnfirmDt;
	// 수거상태
	private String gdgpStatCd;
	// 송장 번호
	private String dmstcWaybilNo;

	/**  회원 등급. */
	private String onlneGrdNm;
	// 배송방법
	private String dlvMnNm;
}
