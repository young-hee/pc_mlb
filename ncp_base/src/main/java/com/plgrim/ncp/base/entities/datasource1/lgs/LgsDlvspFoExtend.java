package com.plgrim.ncp.base.entities.datasource1.lgs;

import com.plgrim.ncp.base.entities.datasource1.clm.ClmWrhsGodExtend;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.type.Alias;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@Alias("lgsDlvspFoExtend")
public class LgsDlvspFoExtend extends LgsDlvsp {


	/**
	 * 
	 */
	private static final long serialVersionUID = -4027197063254111725L;
	
	private String ordNo; // 주문번호
	private String mbrNo; // 회원번호
	private String type;  // 조건별 타입(픽업주문일때 배송지 팝업 구분용)
	private String ordTpCd;  // 주문유형(픽업주문용)
	
	private String dlvChangeYn;      		/* 배송지 변경 가능 여부 */

	private java.lang.Integer dlvAdbukTurn;  //  회원배송지 순번
	private java.math.BigDecimal realityDlvCst; // 실제 배송비	 
	private String dlvComCd;
	private String cdDscr;
	private String dlvStatCd;
	
	/**
	 * 국내 운송장 번호	 
	 */
	private String dmstcWaybilNo;
	/**
	 * 국내 운송장 번호 등록 일시	 
	 */
	private java.util.Date dmstcWaybilNoRegDt;
	/**
	 * 해외 운송장 번호	 
	 */
	private String ovseaWaybilNo;
	/**
	 * 해외 운송장 번호 등록 일시	 
	 */
	private java.util.Date ovseaWaybilNoRegDt;
	
	/**
	 * 편의점택배 승인번호 
	 */
	private String cvnstorHdryAprvNo;

	
	private List<LgsDlvFoExtend> lgsDlvFoExtendList; 
	
	
	private List<ClmWrhsGodExtend> clmWrhsGodList;
	
	/**
	 * ncp 3
	 * 수취인 국가 코드 명
	 */
	private String addrseNationNm;

	/**
	 * ncp 3
	 * 언어코드
	 */
	private String lang;

	private String placeDiffYN;

	/** 출고지시유형코드 */
	private String dlivyDrctTpCd;

	/** 픽업매장명 */
	private String pkupShopNm;

	/** 픽업매장전화번호 */
	private String pkupShopTelNo; 
	
	/** 회수ERP재고전송구분코드 */
	private String rtrvlErpInvTrnsmisSectCd;
	
	/** 회수지시유형코드 */
	private String rtrvlDrctTpCd;
	
}
