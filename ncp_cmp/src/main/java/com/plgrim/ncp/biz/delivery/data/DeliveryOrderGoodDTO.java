/* Copyright (c) 2015 plgrim, Inc.
 * All right reserved.
 * http://plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 *
 * ------------------------------------------------------------------------
 * @author      tktaeki.kim
 * @since       2015. 4. 1       
 */
package com.plgrim.ncp.biz.delivery.data;

import com.plgrim.ncp.base.abstracts.AbstractDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
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
 * @since 2015. 4. 1
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class DeliveryOrderGoodDTO extends AbstractDTO {

	/**
	 * 
	 */
    private static final long serialVersionUID = -7302530416648300557L;

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	
    /**##############  주문[S]  ##############**/
	private String cstmrNm;			// 고객명.
	private String ordNo;			// 주문번호.
	private String ordDt;			// 주문일시.
	private String ordStatCd;		// 주문상태.
	private String ordTpCd;			// 주문유형.
	private String saleAffComId;	// 판매제휴사ID.
	private String mbrNo;			// 회원번호.
	private String cstmrMobilNo;	// 주문자휴대전화번호.
	private String addrseMobileNo;	// 수령자휴대전화번호. 
	private String smsRecptnSectCd;	// SMS수신구분코드
	private String cstmrEmail;		// 고객 이메일
	/**##############  주문[E]  ##############**/


	/**##############  주문상품[S]  ##############**/
	private int ordGodTurn;	// 주문상품순번.
	private String godNo;	// 상품번호.
	private String itmNo;	// 단품번호.
	private String erpGodNo;// ERP상품번호.
	private String skuNo;	// ERP단품번호.
	private String brndId;	// 브랜드ID.
	private String godTpCd;	// 상품유형.
	private String partmalSectCd;	// 입점구분.
	private String godNm;	// 상품명.
	/**##############  주문상품[E]  ##############**/
	
	
	/**##############  물류배송지[S]  ##############**/
	private int dlvPcupspTurn;	// 배송 수거지 순번. 
	private String addrseNm;	// 수취인명. 
	private String addrseMobileNationNo;	// 수취인 휴대전화 국가번호.
	private String addrseMobileAreaNo;		// 수취인 휴대전화 지역번호. 
	private String addrseMobileTlofNo;		// 수취인 휴대전화 국번호. 
	private String addrseMobileTlofWthnNo;	// 수취인 휴대전화 국내번호.
	private String addrseMobilNo;			// 수취인 휴대전화번호.
	private String addrseAddr;				// 수취인주소.
	/**##############  물류배송지[E]  ##############**/
	
	
	/**##############  물류배송[S]  ##############**/
	private int dlvTurn;			// 배송 순번. 
	private String dmstcWaybilNo;	// 국내운송장번호. 
	private String ovseaWaybilNo;	// 해외운송장번호. 
	private String dlvComTrnsmisTgtYn;	// 배송업체전송대상여부. 
	private String dlvComCd;		//	배송업체코드		
	private String dlvMnCd;			//	배송방법코드
	/**##############  물류배송[E]  ##############**/
	
	
	/**##############  물류출고지시상품[S]  ##############**/
	private String dlivyDrctGodNo;		// 출고지시상품번호. 
	private int dlivyDrctQty;			// 출고지시수량. 
	private int newDlivyDrctQty;		// 출고지시수량. 
	private String dlvShopId;			// 배송매장ID. 
	private String dlvStatCd;			// 배송상태. 
	private String newDlvStatCd;		// 배송상태. 
	private String rtrvlDrctGrpDgre;	// 그룹차수. 
	private String invAplYn;			// 재고적용여부. 
	private int pckageGodTurn;			// 패키지형상품순번. 
	private String GftYn;				// 사은품여부.
	private String dlvShopDlivyLcId;	//배송매장출고위치ID
	private String dlivyDrctTpCd;		// 출고지시유형코드
	//TODO 
	private String dlivyDrctCnclBoRceptYn;		// BO취소여부.
	/**##############  물류출고지시상품[E]  ##############**/
	
	
	/**##############  물류회수지시상품[S]  ##############**/
	private String clmNo;	// 클레임번호. 
	private String clmWrhsGodTurn;	// 클레임입고상품순번.
	private String rtrvlDrctGodNo;	// 회수지시상품번호. 
	private String badnReqestCont;	// 불량의뢰내용. 
	private String hdcTrnsmisTgtYn;	// 택배사회수지시 대상여부. 
	private String rtrvlStatCd;		// 회수상태. 
	private String itmDlivyAcptTgtYn;		// 단품출고검수 선택대상여부.
	/**##############  물류회수지시상품[E]  ##############**/
	
	
	/**##############  기타[S]  ##############**/
	private String delayTerm;	// 지연일수. 
    private String regtrId;		// 등록자ID. 
    private String udterId;		// 수정자ID. 
	private List<DeliverySearchDTO> paramList;	// 물류출고지시상품 리스트.
	private int count;
	private int qtyTurn;			// 수량순번
	private String erpGodSn;		// TAG Serail(ERP삳품일련번호).
	private String erpStyleNoTag;	// ERP TAG Serail 체크번호
	private String dlivyAcptYn;		// 출고검수여부 
	private int orgDlvTurn;				// 원 배송순번
	private String orgDlivyDrctGodNo;	// 원 출고지시상품번호
	private String hdryComRtrvlDrctPsbYn;	// 택배사회수지시가능여부
	private String waybilNo;		// 택배송장번호
	private String newDlvShopId;	// 배송매장ID.
	private String wrhsAcptYn;//입고검수여부
	private int itmCnt;	//아이템 수
	private int dlvCnt;	//일반상품 출고수량
	private int gftCnt;	//사은품 출고수량
	private int wbCnt;	//송장 수
	private String lmttInvYn;			//한정재고여부
	private String shopStckChckYn;		//매장재고체크여부
	private String aplUnitCd;			//사은품적용단위
	private int newTargetCnt;			//신규채번대상수량
	private int dlivyDrctGrpDgre;		//차수
	private int dlivyDrctUserGrpDgre;	//DAS
	private String shopNm;			
	private String shopTel;			
	private String smsMsg;			
	private String params;			
	private String params2;			
	private List<String> paramsList;
    private String mallId;
    private String langCd;
    private String assignType;
    /**##############  기타[E]  ##############**/
    
    
    /**##############  배송매장 자동배정[S]  ##############**/
    private Date dlivyDrctDt;        // 출고지시시간
    private String asgnSectCd;		   // 배정구분코드
    private int    asgnCnt;		       // 배정횟수
    private int    rejectCnt;		   // 거절횟수
    private int    remndrCapaScore;    // 잔여CAPA
    private int    dlivyCnt;           // 출고건수
    private int    cnclCnt;            // 거부건수
    private String rejectAdminId;      // 거절관리자ID
    private String rejectResnCd;       // 거절사유코드
    private int    asgnQty;            // 배정 수량
    private int    dlivyQty;           // 출고 수량
    private int    cnclQty;            // 취소 수량
    private int    rejectQty;          // 거부 수량
    /**##############  배송매장 자동배정[E]  ##############**/
    
    private String brndNm;				//브랜드명(beaker는 최상위, 이외는 2레벨) - SMS개선 by cannon : 2016.07.17
    
    private int shtgQty;

    private String dlivyDrctCnclStatCd;		// WMS 취소 상태
    private String totalAmt;		// 결제금액
    
    private String shopTime;		// 영업시간
    private String pickDate;		// 매장 휴일과 영업시간을 제외한 시간.
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
