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
 * beyondj2ee           2015.02.09
 */
package com.plgrim.ncp.biz.order.result;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractResult;
import com.plgrim.ncp.base.entities.datasource1.god.GodExtend;

@Data
@EqualsAndHashCode(callSuper=false)
public class ClmWrhsGodDetailResult extends AbstractResult {
	/**
     *
     */
    private static final long serialVersionUID = -1281738958841378907L;

    private String ordTpCd;                          	//주문유형코드
    private String ordTpNm;                          	//주문유형명
    private String ordStatCd;                          	//주문상태코드
    private String ordStatNm;                          	//주문상태명
    private String ordNo;                           	//주문 번호
    private String mbrNo;                           	//회원 번호
    private String regDt;                           	//접수일자
    private java.lang.Integer ordGodTurn;           	//주문 상품 순번
    private java.lang.Integer ordGodTurnAnce;       	//주문 구성 상품 연결 상품순번    
    private java.lang.Integer ordCpstGodTurn;       	//주문 구성 상품 순번
    private String pckageGodTpCd;                      	//주문 구성 상품 유형
    private java.lang.Integer sortSeq;       			//주문 정렬 순서
    private String brndGrpId;                       	//브랜드 그룹 ID
    private String brndGrpNm;                          	//브랜드그룹명
    private String godNo;                           	//상품 번호
    private String erpGodNo;                         	//ERP 상품 번호
    private String oldSkuNo;                         	//SKU 번호 - 교환 되어질
    private String partmalSectCd;                      	//입점구분코드
	private java.lang.Long dmstcDlvCstPlcSn;           //국내배송비정책일련번호
    private String saleShopCd;                         	//판매매장코드
    private String godNm;                           	//상품 명
    private String itmNo;                           	//단품 번호
    private String itmNm;                           	//단품 명
    private String godTpCd;                         	//상품 유형 코드
    private String godTpNm;                     		//상품 유형 명 
    private String dlvShopId;                         	//배송매장 ID
    private String dlvStatCd;                         	//배송상태 코드
    private String dlvStatNm;                     		//배송상태 명 
    private String lgsItmYn;                     		//물류단품여부 
    private String prdlstCd;                     		//품목코드
    private String prdlstNm;                     		//품목명
    private String dlivyDrctGodNo;                     //출고지시상품번호
    private java.lang.Integer pckageGodTurn;       		//패키지상품순번
    private String dlvTurn;                     		//배송순번
    private java.math.BigDecimal payExchgRtCrncyAmt;	//결제 환율 통화 금액
    private java.math.BigDecimal godDcSumAmt;       	//할인금액
    private java.math.BigDecimal godCpnDcSumAmt;    	//쿠폰할인금액
    private java.math.BigDecimal godEtcDcSumAmt;    	//기타할인금액
    private java.math.BigDecimal stdrCrncySumAmt;   	//주결제금액
    private java.math.BigDecimal unityPntUseSumAmt; 	//통합포인트사용금액
    private java.math.BigDecimal evtPntUseSumAmt;   	//이벤트포인트사용금액
    private java.math.BigDecimal rtlPrc;   				//정소가
    private java.math.BigDecimal saleAmt;   			//실소가
    private java.lang.Long dlivyDrctQty;            	//출고지시수량
    private java.lang.Long dlivyDrctCnclQty;        	//출고지시취소수량
    private java.lang.Long rtrvlDrctQty;            	//회수지시수량
    private java.lang.Long rtrvlDrctWthdrawQty;        	//회수지시취소 수량
    private java.lang.Long wrkQty;                  	//반품가능수량
      
    /* 클레임관리 반품 수정후 접수 사용 S */
    private java.lang.Long clmQty;                     //현재클레임접수수량
    private String clmResnCd;                          //클레임사유코드
//    private String dlvMnC   ;                          //회수방법코드

    /* Fo 사용 S */
    private String lstImgUrl;                          //상품이미지
    private String colorNm;                           	//색상 명
    private String foBrndNm;                           	//브랜드명
    private String foSaleamt;                          //Fo실소가
    /* Fo 사용 E */
    
    private String prmDtlTpCd;//프로모션 상세타입 코드
    private String ordGodGftTurn;//주문상품사은품순번
    
    private String dualOrd;//상품 사은품 정렬을 위한 가상컬럼
    
    /*
     * 교환시 변경가능한 옵션리스트 정보
     */
    List<GodExtend> godExtendChangeList;  
}