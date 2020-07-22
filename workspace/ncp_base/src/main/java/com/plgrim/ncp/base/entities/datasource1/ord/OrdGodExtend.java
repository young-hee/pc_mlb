package com.plgrim.ncp.base.entities.datasource1.ord;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class OrdGodExtend extends OrdGod {

	/**
	 *
	 */
	private static final long serialVersionUID = -338074828482249789L;

	private List<OrdCpstGodCnnc> ordCpstGodCnncList;// 주문 구성 상품 연결

	private List<OrdGodExtend> ordGodList;

	private List<OrdGodAplPrmExtend> gifts;

	private List<OrdGodAplPrmExtend> ordGifts;

	private String comNm;// 업체명

	private String dmstcWaybilNo;// 운송장번호

	private String dlvStatNm;

	private String dlvStatCd;

	private String shtgDcsnYn;

	private String shopNm;

	private String url;

	private java.lang.Integer pkGodTurn;

	private java.math.BigDecimal saleUntPrc;
	/**
	 * 주문 수량
	 */
	private java.lang.Long totOrdQty;

	private java.math.BigDecimal stdrCrncySumAmt;

	private java.math.BigDecimal unityPntUseSumAmt;

	private java.math.BigDecimal evtPntUseSumAmt;

	private java.math.BigDecimal webPntUseSumAmt;

	private java.math.BigDecimal godDcSumAmt;

	private java.math.BigDecimal godCpnDcSumAmt;

	private java.lang.Integer dlivyDrctQty;

	private java.lang.Integer dlivyDrctCnclQty;

	private java.lang.Integer rtrvlDrctQty;

	private java.math.BigDecimal godEtcDcSumAmt;

	private String itmNo;

	private String dlivyDrctGodNo;

	private String safeInvUseYn;

	private String lmttInvYn;

	private String invManageYn;

	private String resveSaleGodYn;

	private String errMsg;

	private long totUsefulInvQty;

	private long salePrearngeQty;

	private long safeInvQty;

	private long affComLmttInvQty;

	private long onlneLmttInvQty;

	private long resveInvQty;

	/**
	 * 제휴 주문 순번	 
	 */
	private java.lang.Integer affOrdTurn;

	/**
	 * 제휴 단품 주문번호	 
	 */
	private String affItmOrdNo;
	
	private String affComNm;
	   
	/**
	 * 제휴 주문 일련번호	 
	 */
	private java.lang.Long affOrdSn;
	/**
	 * 주문 상품 순번 - original
	 * 주문 - 교환접수시 사용
	 */
	private java.lang.Integer newOrdGodTurn;

	/**
	 * 주문 상품 순번 - original
	 * 주문 - 교환접수시 사용
	 */
	private java.lang.Integer newDlvPcupspTurn;

	/**
	 * 패키지 상품 순번 - original
	 * 주문 - 교환접수시 사용
	 */
	private java.lang.Integer pckageGodTurn;

	/**
	 * 상품 승인 구분 코드
ㅁ. 상품 승인 구분 : GOD_APRV_SECT
   >. 승인 대기 : APRV_WAIT
   >. 승인 거부 : APRV_REJ
   >. 승인 완료 : APRV_COMPT	 
	 */
	private String godAprvSectCd;
	
	private String dlvPcupspSectCd;
	
	private String itmStatCd;
	
	private String ordTpCd;
	
	private String ordGiftYn = "N";
	
	private java.lang.Integer dlvTurn;
	
	private String godTpNm;
	
	private String erpGodSn;
	
	private String ordDcNm;
	
	private String prmNo;
	
	private String clmTpNm;
	
	private String clmStatCd; //K2 clm상태값
	
	private String clmTpCd; //K2 클레임타입 
	
	private String  dlivyComptDt;
	
	
	/**
	 * 배송매장 ID	 
	 */
	private String dlvShopId;

	private String comId; //업체 코드
	
	/**
	 * 브랜드 명
	 */
	private String brndNm;
	/**
	 * 전시 카테고리번호
	 */
	private String dspCtgryNo;
	/**
	 * 전시 카테고리명
	 */
	private String dspCtgryNm;	
	/**
	 * 예약상품 배송예정일
	 */	
	private String resveOrdDlivyPrearngeDate;


	/**
	 * 고객상담 판매가 ( 판매가 + 통화 판매가)
	 */
	private String saleAmtEx;

	/**
	 * 고객상담 쿠폰할인가 (쿠폰할인 + 통화 쿠폰할인가)
	 */
	private String godCpnDcSumAmtEx;

	/**
	 * 고객상담 기타할인가 (기타할인 + 통화 기타할인가)
	 */
	private String godEtcDcSumAmtEx;

	/**
	 * 주결제금액 (주결제금액 + 통화 주결제금액)
	 */
	private String stdrCrncyAmtEx;

	/**
	 * 언어코드
	 */
	private String langCd;

	//선물포장여부
	private String svcAplTpCd;
	private String svcAplTpNm;

	/**
	 * 상품 썸네일이미지 Url
	 */
	private String godImgUrl;

	//검수요청여부
	private String dlivyAcptYn;


	/**
	 * 무료수선관련 추가 2017.04.26
	 */
	private String freeRepairPsbYn;
	private String repairDrctQty;
	private String repairClmTpCd;
	private String repairClmStatCd;
	
	/** 무료 반품 교환 쿠폰 추가 **/
	private String rtGodCpnYn;
	private String exchgCpnYn;
	
	/** 포장서비스 **/
	private String svcOffNm;

	/**
	 * 시나리오 채팅
	 * 클레임 게이트 페이지를 위한 추가
	 */
	private String startOrdDt;
	private String prmTpCd;
	
	// 픽업 준비완료시 쓰는 값들.
	private String shopAddr;
	private String shopTel;
	private String wkdyHhmm;
	private String shopImgUrl;
	private String pickDate;
	
	private String prmDtlTpCd;
	
	private String strCstmrPchDcsnDt;
    
}
