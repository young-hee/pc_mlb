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

import com.plgrim.ncp.base.abstracts.AbstractDTO;
import com.plgrim.ncp.base.entities.datasource1.inf.InfOrdGodErpDstb;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * [클래스 설명]
 * 
 * <p>
 * 
 * <ul>
 *   <li> [기능1]
 *   <li> [기능2]
 * </ul>.
 *
 * @author 
 * @since 2015. 3. 30
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class DeliverySearchDTO extends AbstractDTO {

    private static final long serialVersionUID = -3030308300128453744L;

	/** 주문번호 리스트 */
	private List<String> ordNoList;
	
	/** 상품번호 리스트 */
	private List<String> godNoList;
	
	/** 제휴번호 리스트 */
	private List<String> affComOrdNos;
	
	/** 운송장번호 리스트 */
	private List<String> wayBilNoList;
	
	/** 물류출고지시상품번호 리스트 */
	private List<String> dlivyDrctGodNoList;
	
	/** 클레임번호 리스트 */
	private List<String> clmNoList;
	
	/** 회수지시유형 리스트 */
	private List<String> rtrvlDrctTpCdList;
	
	/** 브랜드Id 리스트 */
	private List<String> brndIdList;
	
	/** 파라미터 리스트 */
	private List<String> paramList;
	
	/** 주문상품순번 리스트 */
	private List<DlvOrdGodInfoDTO> clmDlvOrdGodInfoList;
	
	private List<InfOrdGodErpDstb> infOrdGodErpDstbs;
	
	/** 주문번호 */
	private String ordNos;
	
	private String ordType;
	
	/** 상품번호 */
	private String godNos;
	
	/** 운송장번호 */
	private String wayBilNos;
	
	/** 클레임번호 */
	private String clmNos;
	
	/** 회수지시유형 */
	private String rtrvlDrctTpCds;
	
	/** 브랜드Id */
	private String brndIds;	
	
	/** 브랜드 그룹 Id SR #21177 김병철*/
	private String upperBrndId;
	
	/** 파라미터 */
	private String params;
	
	/** 주문번호 */
	private String ordNo;
	
	/** 상품번호 */
	private String godNo;
	
	/** 운송장번호 */
	private String wayBilNo;
	
	/** 클레임번호 */
	private String clmNo;
	
	/** 회수지시유형 */
	private String rtrvlDrctTpCd;
	
	/** 파라미터 */
	private String param;
	
	/** 단품번호 */
	private String itmNo;
	
	/** 조회일자 구분 (ord:주문일자, dlv:출고지시일자) */
	private String srchDtTp;
	
	/** 조회 시작일 */
	private String srchStDt;
	
	/** 조회 종료일 */
	private String srchEnDt;
	
	/** 주문유형 */
	private String ordTpCd;
	
	/** 주문유형(다건) */
	private String ordTpCds;
	
	/** 주문유형 리스트 */
	private List<String> ordTpCdList;
	
	/** 배송매장 */
	private String dlvShopId;
	
	/** 배송상태코드 */
	private String dlvStatCd;
	
	/**2015.9.30, Harris, 예약영수증 생성여부 */
	private String erpResveRcptfrCreatYn;
	
	/** 배송수단코드 */
	private String dlvMnCd;
	
	/** 판매제휴사ID */
	private String saleAffComId;
	
	/** 브랜드ID */
	private String brndId;
	
	/** 브랜드그룹ID */
	private String brndGrpId;
	
	/** 상품유형코드 */
	private String godTpCd;
	
	/** 고객명 */
	private String cstmrNm;
	
	/** 수취인명 */
	private String addrseNm;
	
	/** 출고지시상품번호 */
	private String dlivyDrctGodNo;
	
	/** 회수지시상품번호 */
	private String rtrvlDrctGodNo;

	/** 물류단품여부 */
	private String lgsItmYn;
	
	/** 지연일수 */
	private String delayTerm;
	
	/** 출고수량 */
	private int dlivyDrctQty;
	
	/** 판매예정수량 */
	private int SalePrearngeQty;
	
	/** 재고반영여부 */
	private String invAplYn;
	
	/** 원배송매장 */
	private String orgDlvShopId;
	
	/** 회수완료여부 */
	private String rtrvlCompYn;
	
	/** 회수배송매장 */
	private String rtrvlShopId;
	
	/** 클레임사유 */
	private String clmResnCd;
	
	/** 회수상태코드 */
	private String rtrvlStatCd;
	
	/** 파라미터유형 */
	private String paramTp;
	
	/** 패키지형상품순번 */
	private int pckageGodTurn;
	
	/** 에러여부 */
	private String errorYn;

	/** 전송여부 */
	private String transYn;
	
	/** 국내/해외 */
	private String internalYn;
	
	/** 에약여부 */
	private String reservationYn;
	
	/** ERP확정여부 */
	private String erpCnfirmYn;
	
	/** 회수상품처리완료여부 */
	private String rtrvlGodPrcsComptYn;

	/** ERP품번 */
	private String erpNos;
	
	/** ERP품번 리스트 */
	private List<String> erpNoList;
	
	/** 반품상태 */
	private String clmStatCd;
	
	/** 업체 유형 코드 */
	private String comTpCd;
	
	/** 배송구분 코드 */
	private String dlvDrctTpCd;
	
	/** 배송 순번 */
	private String dlvTurn;
	
	/** 배송수거지 순번 */
	private String dlvPcupspTurn;
	
	/** 주문 유형 */
	private String drctTpCds;
	
	/** 주문유형 리스트 */
	private List<String> drctTpCdList;
	
	
	/** 배송상태. */
	private String dlvStatCds;
	
	/** 배송상태 리스트 */
	private List<String> dlvStatCdList;
	
	/** 수정자아이디. */
	private String udterId;
	
	/** 등록자아이디. */
	private String regtrId;
	
	/** 택배사. */
	private String dlvComCd;
	
	/** 판매제휴사명. */
	private String affComId;
	
	/** ERP상품 시리얼번호. */
	private String erpGodSn;
	
	/** ERP상품번호. */
	private String erpGodNo;
	
	/** SKU 번호. */
	private String skuNo;

	/** ERP품번,온라인품번 검색구분. */
	private String nosGubun;
	
	/** ERP품번,온라인품번 검색어. */
	private String searchNos;
	
	/** 검색조건 리스트 */
	private List<String> searchNoList;
	
	/** 입점 구분 코드 (자사 : MCOM,입점 : PARTMAL) */
	private String  partmalSectCd;
	
	/** 피킹차수 */
	private String  dlivyDrctGrpDgres;
	
	/** 피킹차수 리스트 */
	private List<String> dlivyDrctGrpDgreList;
	
	/** 피킹차수 */
	private String  dlivyDrctGrpDgre;
	
	/** 사용자그룹차수 */
	private String  dlivyDrctUserGrpDgre;
	
	/** 사은품 단독배송여부 */
	private String  gftViewYn;
	
	/** 업체 */
	private String  comId;
	
	/** 주문상품순번 */
	private int ordGodTurn;
	
	/** 요약유형 */
	private String summaryTp;	//dlv:출고수량 / wb:송장수량 / itm:아이템수량
	private String affVrscComId;
	private String callType;
	private int clmWrhsGodTurn;
	private String target;

	/** 세션매장ID */
	private String sysShopId;
	
	/** 세션업체ID */
	private String sysComId;
	
	/** 브랜드 조회 구분 */
	private String sysBrndSearchType;
	
	/** 브랜드 조회 텍스트 */
	private String sysBrndSearchText;

	private String mcom;

	private String aff;

	private String langCd;

	private String ordStatCd;

	/** 처리지연사유 */
	private String rfdDelayCd;

	/** 변경될 배송상태. */
	private String newDlvStatCd;

	/** 선물포장 */
	private String svcAplTpCd;

	/** 단품출고검수 선택대상여부 */
	private String itmDlivyAcptTgtYn;


	/*
	 * ---------------------------------------------------------------------
	 * 대쉬보드 검색조건
	 * ---------------------------------------------------------------------
	 */
	/** 대쉬보드 A:신규, B:전체 */
	private String initDFlag;
	
	/*
	 * ---------------------------------------------------------------------
	 * 배송매장 자동검색 관련 검색조건
	 * ---------------------------------------------------------------------
	 */
	/** 배정 유형 */
	private String assignType;
	/** 배정 유형 리스트 */
	private List<String> assignTypeList;
	
	/*
	 * ---------------------------------------------------------------------
	 * 배송지연 상품관리 남은시간 검색조건
	 * ---------------------------------------------------------------------
	 */
	private int remainTime;

	/** 주문 상품 수량 순번 */
	private java.lang.Integer qtyTurn;
	/** 관리자 구분 */
	private String adminTpCd;
	/** 로그인한 매장 ID */
	private String regtrShopId;
	/** 범한진행단계 */
	private String statusCode;


	/**
	 * 배송매장 대쉬보드로부터 링크 여부
	 */
	private boolean isFromDashboard;

	/** 포장 조회 순서 */
	private String paramPackingTp;
	
	/** 송장 유무 */
	private String dmstcWaybilNoYn;
	
	/** 
	 * [#48750][개발] 불량상품 고객에 대한 교환/반품 Process 개선 요청
	 * 클레임 선진행 구분 코드(상품회수관리) 
	 */
	private String clmPreprogrsSectCd;
	private String[] clmPreprogrsSectCdArr;

	/** 회원 번호 */
	private String mbrNo;

	/** 핸드폰 번호 */
	private String cstmrMobilNo;
	
	/** 지연시간 */
	private String delayTermTime;
	
	/** 출고지시 취소 여부 */
	private String cancelYn;
	
	private String vip;
	
	private String nonVip;
	
	private List<String> cstrnoList;
}
