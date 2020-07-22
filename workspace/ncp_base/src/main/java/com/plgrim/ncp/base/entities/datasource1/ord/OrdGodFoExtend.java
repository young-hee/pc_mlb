/**
 * @package : com.plgrim.ncp.base.entities..ord
 * @author : ()
 * @date : 20150417
 * @version : 1.0
 * @desc : 
 */

package com.plgrim.ncp.base.entities.datasource1.ord;


import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.type.Alias;

import java.util.List;



/**
 * 주문 상품
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Alias("ordGodFoExtend")
public class OrdGodFoExtend extends OrdGod{
	private static final long serialVersionUID = -338074828482249789L;
	
	private java.lang.Integer ordGodTurn; /*주문상품번호*/
	private String godTpCd;	/*상품유형코드*/
	private String qtyTurn;	/*상품유형코드*/
	private String clmNo;	/* 클레임번호*/
	private String clmDt;	/* 클레임일시*/
	private String clmTpCd;	/*클레임유형코드*/
	private String dlvStatCd;	/*배송상태코드*/
	private String brndNm;		/*브랜드명*/
	private java.lang.Integer dlvPcupspTurn; /*  배송 수거지 순번*/
	private String dmstcWaybilNo;      /*국내 운송장 번호	*/
	private String ovseaWaybilNo; /*해외 운송장 번호	*/
	private String dlvPcupspSectCd; /*배송구분	*/
	private String dlvComCd;     /*배송업체코드*/
	private String cdDscr;		/*배송업체URL*/
	private String clmQty; /* 클레임수량	*/
	private String clmYn; /* 클레임수량	*/
	private String clmStatCd; /* 클레임상태코드 */
	private java.lang.Long ordQty;	/*주문상품수량 */
	private java.lang.Long realOrdQty;	/*주문상품수량 - 클레임수량 */
	private java.math.BigDecimal payAmt; /*결제 환율 통화 합계 금액 -  클레임 결제 환율 통화 합계 금액= 실결제*/
	private java.lang.Integer dlvTurn; /* 배송순번 */
	private String prmDtlTpCd; /* 프로모션세부 유형코드 */
	private String prmTpCd; /* 프로모션세부 유형코드 */
	private String resveOrdDlivyPrearngeDate;	/*배송예정일*/ 
	private String payExchgRtCrncySumAmt;	/*결제 환율 통화 합계 금액*/ 

	private java.lang.Integer godEvlCnt;									/* 상품리뷰 등록 건 */

	private String dlivyDrctTpCd;	/* 출고지시 유형 코드 */

	private String pckageGodTpCd;    /* 패키지형 상품 유형 */
	
	private List<OrdGodFoExtend> godOptList; // 세트,패키지 상품 옵션 리스트

	
	//ncp 3차
	private String hblNo;	/* HBL 번호 */
	private String dlvCstCpnUseYn;	/* 배송비쿠폰사용여부 */
	private String gdgpStatCd;	/* 집하상태코드 */
	private String cdPantosDscr;		/*PantosURL*/

	private String ordTpCd; /* 주문 유형 코드 */

	private String dlvShopId; /* 배송 매장 ID */
	private String dlivyDrctDt; /* 출고지시일 */
	
	//품목명:uxui개선(table에 없는 필드)
	private String prdlstNm;
	
	private String resveOrdPayClosDate; /* 예약주문 결제 마감일시 */
	private java.math.BigDecimal remainAmt; /*예약주문 결제 미결제금액*/
	
	
	private String dspYn; /*상품 전시 여부 #34878 [개발]주문리스트, 상세 세트 상품의 리뷰 작성 후에도 리뷰 작성 버튼 활성화 오류 로 추가*/

	private String dlvComTrnsmisYn; /* 택배사 전송 여부 */
	private String dlvComTrnsmisTgtYn; /* 택배사 전송 대상 여부 */

	private String freeRepairPsbYn; /* 무료수선 가능 여부 */
	private java.lang.Long susunOrdQty;	/* 수선수량 */
	private String upGodNo; /* 상위상품번호 */

	private String gftTpCd;	// 사은품 유형
	// 사은품 프로모션 이미지 및 대체 텍스트
	private String gftRprstImgPcUrl;
	private String gftRprstImgPcAltrtvCont;
	private String gftRprstImgMobileUrl;
	private String gftRprstImgMobileAltrtvCont;
	
	/**
	 * 주문 상품 서비스 상세(선물포장)
	 */
	private List<OrdGodSvcDetailCnncExtend> ordGodSvcDetailCnncList;
	
	/**
	 * 추천성별코드
	 */
	private String recomendSexCd;
	
	/**
	 * 리뷰작성 가능기간 여부
	 */
	private String reviewApplyTermYn;
	
	// 픽업 준비완료시 쓰는 값들.
	private String shopAddr;
	private String shopTel;
	private String wkdyHhmm;
	private String shopImgUrl;
	private String pickDate;
	private String shopNm;
	
	private String mainClmStatCd; /* 클레임상태코드 */
	private java.math.BigDecimal clmPayExchgRtCrncySumAmt;	/* 결제 환율 통화 합계 금액(클레임) */
	private java.math.BigDecimal clmRtlPrcSumAmt;			/* 정소가(클레임) */
	private java.math.BigDecimal clmSaleSumAmt;			/* 실소가(클레임) */
	private java.math.BigDecimal clmWebDcSumAmt;			/* 웹할인금액(클레임) */
	private java.math.BigDecimal clmDlvCstSumAmt;			/* 배송비 합계 금액(클레임) */
	private java.math.BigDecimal clmGodCpnDcSumAmt;		/* 상품 쿠폰 할인 합계 금액(클레임) */
	private java.math.BigDecimal clmBskCpnDcSumAmt;		/* 장바구니 쿠폰 할인 합계 금액(클레임) */
	private java.math.BigDecimal clmBundleDcSumAmt;		/* 묶음할인 합계 금액(클레임) */
	private java.math.BigDecimal clmCrsDcSumAmt;			/* 교차할인 합계 금액(클레임) */
	private java.math.BigDecimal clmUnityPntUseSumAmt;		/* 통합 포인트 사용 합계 금액(클레임) */
	private java.math.BigDecimal clmWebpntUseSumAmt;		/* 마일리지 사용 합계 금액(클레임) */
}
