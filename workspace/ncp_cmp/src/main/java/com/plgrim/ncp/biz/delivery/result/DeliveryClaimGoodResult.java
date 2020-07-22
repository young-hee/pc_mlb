/* Copyright (c) 2015 plgrim, Inc.
 * All right reserved.
 * http://plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 *
 * ------------------------------------------------------------------------
 * @author      jwcale.kim
 * @since       2015. 4. 29       
 */
package com.plgrim.ncp.biz.delivery.result;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractResult;
import com.plgrim.ncp.base.entities.datasource1.clm.Clm;
import com.plgrim.ncp.base.entities.datasource1.clm.ClmWrhsGod;
import com.plgrim.ncp.base.entities.datasource1.god.GodItm;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsDlivyDrctGod;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsDlv;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsDlvsp;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsRtrvlDrctGod;
import com.plgrim.ncp.base.entities.datasource1.ord.Ord;
import com.plgrim.ncp.base.entities.datasource1.ord.OrdClmGodCnnc;



/**
 * Instantiates a new delivery claim good result.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class DeliveryClaimGoodResult extends AbstractResult {

	/**
	 * 
	 */
    private static final long serialVersionUID = 7890001938648481332L;
	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	/** 주문 엔터티. */
    private Ord ord;
	/** 클레임 엔터티. */
    private Clm clm;
    /** 클레임입고상품 엔터티. */
    private ClmWrhsGod clmwg;
	/** 물류배송지 엔터티. */
    private LgsDlvsp lgsDsp;
	/** 물류배솓 상품 엔터티. */
    private LgsDlv lgsDlv;
    /** 물류출고지시상품 엔터티. */
    private LgsRtrvlDrctGod lgsRdg;
	/** 물류출고지시상품 엔터티. */
    private LgsDlivyDrctGod lgsDdg;
    /** 상품정보 */
    private GodItm godItm;
    /** 주문클레임상품연결 */
    private OrdClmGodCnnc ordClmGodCnnc;
    
    private String claimMemo;		//클레임메모
    private String addrseNm;		//반품자명
    private String addrseAddr;		//회수지주소
    private String addrsePostNo;	//회수지우편번호
    private String addrseTelNo;		//회수자전화번호
    private String addrseMobilNo;	//회수자휴대전화번호
    private String clmStatNm;		//회수상태 
    private String rtrvlDrctTpNm;	//회수지시유형
    private String affComNm;	//업체명
    private String rtrvlShopNm;		//회수매장명
    private String clmResnNm;		//클레임사유
    private String rtrvlStatNm;		//회수상태
    private String rtrvlGodStatNm;	//회수상품상태 
    private String rtrvlComptYn;	//회수완료여부
    private int qtyTurn;			//수량순번
    private String repairNo;		//수선번호
    private String repairComptYn;	//수선완료여부
    private String repairComptCd;	//수선완료코드
    private String repairCont;		//수선내용
    private String options;			//옵션
    private String imageView;		//이미지보기
    private String scanSkuNo;		//스캔품번
    private String dlvShopNm;		//배송매장
    private String cstmrMobilNo;	//고객휴대전화번호
    private String comNm;	//업체명
	private String gftGubun;//사은품구분(S:자체배송,H:합배송,N:사은품아님)
	private String jobGBNm;//편의점택배수거상태명
	private String godTpNm;//상품유형명
	private String erpInvTrnsmisSectNm;//ERP 재고 전송 구분 코드명
	private String wrhsAcptYn;//입고검수여부
	private String erpGodSn;//ERP 상품 일련번호
	private String affConfirm;//제휴사확인
	private String params;
	private String params2;
	private String callType;		//호출유형 
	private String erpGodSnView;	//시리얼번호

	private String cstmrNm;			//주문자명
	private String rtrvlDrctDt;		//회수지시일자
	private String rtrvlComptDt;	//회수완료일자
	private String rfdDelayCd;		//환불지연사유코드
	private String rfdDelayNm;		//환불지연사유코드명
	private String rfdDelayResnDscr;//기타사유
	private String rfdDelayDt;		//환불지연 등록 일자
	private String newRtrvlStatCd;
	private String dmstcWaybilNoView;//송장이력
	private String cdDscr; // 택배사송장조회주소
	private String partMallDlvTrace; //입점사택배사송장추적
	private String gdgpStatCd; //한진API상태
	private String dlvDmstcWaybilNo; //출고송장번호

	private String giftRtrvlTgtYn;
	
	private String clmPreprogrsSectCd; 	// 클레임 선진행 구분 코드(상품회수관리)
	private String dlvComCd; 			// 택배사
	
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
