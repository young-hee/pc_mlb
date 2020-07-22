package com.plgrim.ncp.biz.order.result;

import com.plgrim.ncp.base.abstracts.AbstractResult;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsDlvspExtend;
import com.plgrim.ncp.base.entities.datasource1.ord.OrdGodAplPrmExtend;
import com.plgrim.ncp.base.entities.datasource1.pay.Pay;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class OrderBoResult extends AbstractResult {

	/**
	 *
	 */
	private static final long serialVersionUID = -5064752112864163329L;

	private List<LgsDlvspExtend> lgsDlvspList;// 물류배송지

	private List<Pay> payList;// 결제

	private List<OrdGodAplPrmExtend> ordGodAplPrmList;
	/* 자사/제휴사 */
	private String mcomOrAff;
	private String pgAprvNo;
	private String mcom;
	private String payNo;
	private String aff;
    private String telNo;
    private Date payDt;
	/* 몰 아이디 */
	private String mallNm;

	/* 언어명 */
	private String langNm;

	private String clmYn;

	private String godTp;

	private String virtlDlvComptYn;

	private String addrseMoNo;
	private String addrseNm;
	private String excutRemNm;
	/**
	 * 예약 주문 출고 예정 일자	 
	 */
	private String resveOrdDlivyPrearngeDate;
	

	/* 제휴사 주문번호 */
	private String affComOrdNo;

	/* 주문 시작일 */
	private String startOrdDt;

	/* 주문 종료일 */
	private String endOrdDt;

	/* 주문구분 */
	private String ordTp;

	private String ordTpCd;

	private String payStat;
	/* 주문 상태 */
	private String ordStat;

	private String ordStatCd;
	/* 판매제휴사 */
	private String saleAff;

	/* 광고채널 */
	private String advrtsCh;

	/* 결제수단 */
	private String payMn;

	/* 글로벌을위한 결제수단 */
	private String payMnGlo;

	/* 주문국가  */
	private String ordNation;

	/* 배송지국가 */
	private String rcNation;

	private String pgSectCd;
	
	private String payAmt;

	private String remAmt;

	private String clmNo;

	/* 회원유형 */
	private String mbrTp;

	private String mbrTpCd;
	/* 회원속성 */
	private String mbrAtrb;

	/* 구매자ID */
	private String pchId;

	/* 구매자 이름 */
	private String pchNm;

	/* 구매자 연락처 전화번호 */
	private String pchTelNo;

	/* 구매자 연락처 휴대전화번호 */
	private String pchMoNo;

	/* 수취인이름 */
	private String rcverNm;

	/* 수취인이름 */
	private String rcverTelNo;

	/* 수취인이름 */
	private String rcverMoNo;
	/* email */
	private String email;

	/* 주문일시 */
	private Date ordDt;



	/* 주문금액 */
	private java.math.BigDecimal ordAmt;
	
	/* 주문 환율금액 */
	private java.math.BigDecimal ordAmtEx;

	/* 환율기준금액 */
	private String exchgInfo;
	
	private java.math.BigDecimal webDcSumAmt;
	/* 할인금액 */
	private java.math.BigDecimal dcAmt;

	/* 글로벌 할인금액 */
	private java.math.BigDecimal dcAmtEx;

	private java.math.BigDecimal cpnSumAmt;

	private java.math.BigDecimal etcDcSumAmt;
	/* 주 결제금액 */
	private java.math.BigDecimal mainPayAmt;
	private java.math.BigDecimal payCrncyPayAmt;

	/* 멤버쉽포인트결제액 */
	private java.math.BigDecimal memPntAmt;

	/* 이벤트포인트결제액 */
	private java.math.BigDecimal evtPntAmt;

	/* 적립웹 포인트 결제액 */
	private java.math.BigDecimal webPntAmt;

	/* 적립 멤버쉽 포인트 */
	private java.math.BigDecimal savMemPnt;

	/* 적립웹 포인트 */
	private java.math.BigDecimal savWebPnt;

	/* 실소가 합계 금액 */
	private java.math.BigDecimal saleSumAmt;

	/* 글로벌 실소가 합계 금액 */
	private java.math.BigDecimal saleSumAmtEx;

	private java.math.BigDecimal dlvCstSumAmt;

	private java.math.BigDecimal dlvCstSumAmtEx;

	private java.math.BigDecimal stdrCrncySumAmt;

	private java.math.BigDecimal payExchgRtCrncyAmt;

	private java.math.BigDecimal stdrCrncyAmt;

	private java.math.BigDecimal rtlPrcSumAmt;

	private java.math.BigDecimal ordSumAmt;

	private String dlvCom;

	/* 디바이스채널 */
	private String dvcNm;

	/* 판매제휴사 */
	private String saleAffComNm;

	private String rcptfrReqstPrcs;



	private String brndGrpId;

	private String brndGrpNm;

	private String brndNm;


	private String godTpNm;


	/*
	 * 가상계좌번호, 은행명
	 */
	private String bnkAcctNo;
	private String bnkNm;

	private int ordQty;



	private java.lang.Integer dlivyDrctCnclQty;

	private java.lang.Integer rtrvlDrctQty;

	private java.lang.Integer dlivyDrctQty;

	private String lagQtyOrdDcsnYn;

	/**
	 * 장바구니 쿠폰 할인 합계 금액
	 */
	private java.math.BigDecimal bskCpnDcSumAmt;

	private java.math.BigDecimal saleAmt;

	/**
	 * 배송비 쿠폰 할인 합계 금액
	 */
	private java.math.BigDecimal dlvCstCpnDcSumAmt;

	private String cstmrEmail;

	private String pchNmEmail;

	private String mbrNo;

	/**
	 * 제휴 주문 일련번호
	 */
	private java.lang.Long affOrdSn;

	/**
	 * 제휴 주문 순번
	 */
	private java.lang.Integer affOrdTurn;

	/**
	 * 제휴 주문 일자
	 */
	private java.util.Date affOrdDt;

	/**
	 * 제휴사 명
	 */
	private String affComNm;

	/**
	 * 제휴사 ID
	 */
	private String affComId;

	/**
	 * 제휴 상품 주문번호
	 */
	private String affGodOrdNo;

	/**
	 * 제휴 단품 주문번호
	 */
	private String affItmOrdNo;

	/**
	 * 고객 명
	 */
	private String cstmrNm;

	/**
	 * 휴대전화 국가번호
	 */
	private String mobilNationNo;

	/**
	 * 휴대전화 지역번호
	 */
	private String mobilAreaNo;

	/**
	 * 휴대전화 국번호
	 */
	private String mobilTlofNo;

	/**
	 * 휴대전화 국내번호
	 */
	private String mobilTlofWthnNo;

	/**
	 * 전화 국가번호
	 */
	private String telNationNo;

	/**
	 * 전화 지역번호
	 */
	private String telAreaNo;

	/**
	 * 전화 국번호
	 */
	private String telTlofNo;

	/**
	 * 전화 국내번호
	 */
	private String telTlofWthnNo;

	/**
	 * 고객 우편번호 ㅁ. 우편번호 적용 적용 관리 함
	 */
	private String cstmrPostNo;

	/**
	 * 고객 기본주소 ㅁ. 우편 번호로 확인 되는 주소로 기본주소의 의미를 가짐.
	 */
	private String cstmrBaseAddr;

	/**
	 * 고객 상세주소 ㅁ. 회원의 개별적 지번, 건물의 층수 등을 의미 함.
	 */
	private String cstmrDetailAddr;

	/**
	 * ERP 상품 번호
	 */
	private String erpGodNo;

	/**
	 * 단품 명
	 */
	private String itmNm;

	/**
	 * 수량
	 */
	private java.lang.Long qty;

	/**
	 * 배송 메모
	 */
	private String dlvMemo;

	/**
	 * 상품 메모
	 */
	private String godMemo;

	private String dealTpCd;
	/**
	 * 몰 ID
	 */
	private String mallId;

	/**
	 * 주문번호
	 */
	private String ordNo;

	/**
	 * 주문 상품 순번
	 */
	private java.lang.Integer ordGodTurn;

	/**
	 * 패키지형 주문 상품 순번
	 */
	private java.lang.Integer pckageshapeOrdGodTurn;

	/**
	 * 단품 번호 IT || YYYYMMDD || 7자리 Cycle Sequence
	 */
	private String itmNo;

	/**
	 * 업체 ID
	 */
	private String comId;

	/**
	 * 브랜드 ID
	 */
	private String brndId;

	/**
	 * 상품 유형 코드 ㅁ. 상품구분 : GOD_TP >. 일반상품 : GNRL_GOD >. 사은품 : GFT >. 세트상품 :
	 * SET_GOD >. 패키지 상품 : PCKAGE_GOD >. 상품권 : GFCT >. 마일리지 상품 : MILE_GOD
	 */
	private String godTpCd;

	/**
	 * 상품 번호 상품 유형 1자리 || 업체 코드 3자리 || YYMMDD || 5자리 Cycle Sequence
	 */
	private String godNo;

	/**
	 * 상품 명
	 */
	private String godNm;

	/**
	 * 정소가
	 */
	private java.math.BigDecimal rtlPrc;

	/**
	 * 실소가 ㅁ. 입력시 >. <상품 통화별 가격.기준 통화 금액> 입력
	 *
	 * ㅁ. 수정시 >. <상품 통화별 가격.기준 통화 금액>과 <상품 통화별 가격.환율 통화 금액>을 사용하여 환율을 구하여 >. <상품
	 * 통화별 가격.통화별 실소가>가 수정 되어야 한다.
	 */
	private java.math.BigDecimal csmrPrc;

	/**
	 * 제휴사 수수료 율
	 */
	private java.math.BigDecimal affComFeeRt;

	/**
	 * 품절 여부
	 */
	private String sldoutYn;

	/**
	 * 제휴 주문 상태 코드
	 */
	private String affOrdStatCd;

	/**
	 * 제휴 주문 상태 내용
	 */
	private String affOrdStatCont;

	/**
	 * 제휴 대행사 ID
	 */
	private String affVrscComId;

	/**
	 * 제휴 대행사 수수료 율
	 */
	private java.math.BigDecimal affVrscComFeeRt;

	/**
	 * 패키지형 상품 번호
	 */
	private String pckageshapeGodNo;

	/**
	 * 패키지형 상품 단품 명
	 */
	private String pckageshapeGodItmNm;

	/**
	 * 판매 매장 번호
	 */
	private String saleShopNo;

	/**
	 * 제휴 수수료 행사 일련번호
	 */
	private java.lang.Long affFeeEventSn;

	/**
	 * 재주문 여부
	 */
	private String reordYn;

	/**
	 * 플그림 판매가
	 */
	private java.math.BigDecimal plgrimSalePrc;

	/**
	 * 제휴사 판매가
	 */
	private java.math.BigDecimal affComSalePrc;

	/**
	 * 제휴사 상품 순번
	 */
	private java.lang.Integer affComGodTurn;

	/**
	 * 제휴사 매장 명
	 */
	private String affComShopNm;

	/**
	 * 고객 ID ㅁ. 회원 가입시 부여되는 고유한 관리 번호 >. MB || YYYYMMDD || 7자리 Cycle
	 */
	private String cstmrId;

	/**
	 * 배송 국가 코드
	 */
	private String dlvNationCd;

	/**
	 * 배송비
	 */
	private java.math.BigDecimal dlvCst;

	/**
	 * CAFE24 주문 여부
	 */
	private String cafe24OrdYn;

	/**
	 * CAFE24 배송비
	 */
	private java.math.BigDecimal cafe24DlvCst;

	/**
	 * 환율
	 */
	private java.math.BigDecimal exchgRt;

	/**
	 * CAFE24 상품 쿠폰 할인 금액
	 */
	private java.math.BigDecimal cafe24GodCpnDcAmt;

	/**
	 * CAFE24 주문 쿠폰 할인 금액
	 */
	private java.math.BigDecimal cafe24OrdCpnDcAmt;

	/**
	 * CAFE24 해외 주문 상품 쿠폰 할인 금액
	 */
	private java.math.BigDecimal cafe24OvseaOrdGodCpnDcAm;

	/**
	 * CAFE24 해외 주문 쿠폰 할인 금액
	 */
	private java.math.BigDecimal cafe24OvseaOrdCpnDcAmt;

	/**
	 * CAFE24 상품 쿠폰 번호
	 */
	private String cafe24GodCpnNo;

	/**
	 * CAFE24 주문 쿠폰 번호
	 */
	private String cafe24OrdCpnNo;

	/**
	 * 업체 배송비
	 */
	private java.math.BigDecimal comDlvCst;

	/**
	 * FASTBOX 배송비
	 */
	private java.math.BigDecimal fastboxDlvCst;

	/**
	 * PG 유형 코드
	 */
	private String pgTpCd;

	/**
	 * PG 상점 ID
	 */
	private String pgStoreId;

	/**
	 * 등록자 ID 등록한 관리자 번호
	 */
	private String regtrId;

	/**
	 * 등록 일시
	 */
	private java.util.Date regDt;

	/**
	 * 수정자 ID 수정한 관리자 번호
	 */
	private String udterId;

	/**
	 * 수정 일시
	 */
	private java.util.Date udtDt;

	/**
	 * 대행사 수수료 행사 일련번호
	 */
	private java.lang.Long vrscComFeeEventSn;

	private String affOrdStatCdNm;

	private String godTpCdNm;

	/**
	 * 프로모션 번호 ㅁ. PR || YYYYMMDD || 7자리 Cycle Sequence
	 */
	private String prmNo;

	/**
	 * 프로모션 유형 코드 ㅁ. 프로모션 유형 : PRM_TP >. 쿠폰 : CPN >. 상품 할인 : GOD_DC >. 주문 할인 :
	 * ORD_DC >. 사은품 : GFT >. 멤버쉽 포인트 : MBSH_PNT >. 카드사 제휴 : CRCOM_AFF
	 */
	private String prmTpCd;

	/**
	 * 프로모션 세부 유형 코드 ㅁ. 프로모션 세부 유형 : PRM_DTL_TP >. 쿠폰 : CPN >>. 상품쿠폰 : GOD_CPN
	 * >>. 장바구니쿠폰 : BSK_CPN >>. 배송비쿠폰 : DLV_CPN >. 상품 할인 : GOD_DC >>. 상품 할인 :
	 * GOD_DC >>. Single 특판 : SIGNL_SPSL >>. B2E 특판 : B2E_SPSL >. 주문 할인 : ORD_DC
	 * >>. 묶음 할인 : BUNDLE_DC >>. 교차 할인 : CRS_DC >. 사은품 : GFT >>. 상품 사은품 :
	 * GOD_GFT >>. 주문 사은품 : ORD_GFT >. 멤버쉽 포인트 : MBSH_PNT >>. 추가 적립 : ADIT_SAV
	 * >. 카드사 제휴 : CRCOM_AFF >>. 청구 할인 : RQEST_DC >>. 무이자 할부 : NINT_INSTM
	 *
	 * ㅁ. 교차 할인의 경우 프로모션 적용 상품을 사용 하지 않고 프로모션 교차 할인 적용 상품을 사용 하여 작업
	 */
	private String prmDtlTpCd;

	/**
	 * 프로모션 상태 코드 ㅁ. 프로모션 상태 : PRM_STAT >. 승인대기 : APRV_WAIT >. 승인 : APRV >. 취소 :
	 * CNCL
	 */
	private String prmStatCd;

	/**
	 * 프로모션 명 관리자가 진행하고자 하는 프로모션의 명칭
	 */
	private String prmNm;

	/**
	 * 상품 사은품 구분 코드 ㅁ. 상품 사은품 구분 : GOD_GFT_SECT >. 1+1 : 1_1 >. 1+N : 1_N >. N+1
	 * : N_1
	 */
	private String godGftSectCd;

	/**
	 * 주문 사은품 구매 기준 코드 ㅁ. 주문 사은품 구매 기준 : ORD_GFT_PCH_STDR >. 금액 기준 : AMT_STDR >.
	 * 수량 기준 : QTY_STDR
	 */
	private String ordGftPchStdrCd;

	/**
	 * 주문 사은품 적용 조건 수량 ㅁ. N+1 일때만 사용
	 */
	private java.lang.Long ordGftAplCndQty;

	/**
	 * 주문 사은품 적용 조건 금액 사은품프로모션유형이 주문사은품 일때 입력된 금액 이상일경우
	 */
	private java.math.BigDecimal ordGftAplCndAmt;

	/**
	 * 주문 사은품 설명
	 */
	private String ordGftDscr;

	/**
	 * 주문 사은품 주의사항 내용
	 */
	private String ordGftAtndmatterCont;

	/**
	 * 사은품 선택 가능 수량
	 */
	private java.lang.Long gftChoisePsbQty;

	/**
	 * 제휴 업체 적용 코드 ㅁ. 제휴 업체 적용 : AFF_COM_APL >. 제휴 업체 미적용 : AFF_COM_NAPL >. 제휴
	 * 업체 적용 : AFF_COM_APL
	 */
	private String affComAplCd;

	/**
	 * 사은품 상품번호
	 */
	private String gftGodNo;

	private long cpnCount;

	private String erpCstmrNo;

	private String memPnt;

	private int count;

	private String payTpNm;

	private String clmTpNm;

	private String shtgDcsnYn;
	
	private String dlvStat;
	/**
	 * 제휴 주문 오류 내용	 
	 */
	private String affOrdErrCont;
	/**
	 * 연계 구분 코드
ㅁ. 연계 구분 코드 : CNTC_SECT
   >. EC모니터: ECMNTR
   >. 직접 연계 : DIRT_CNTC
   >. 엑셀업로드 : EXCEL_UPLOAD
	 
	 */
	private String cntcSectCd;
	private String unityPntUseSumAmt;

	/**
	 * 오류 구분 코드
	 */
	private String errSectCd;
	private String unityPntAccmlSumAmt;
	private String payExchgRtCrncySumAmt;
	private String webPntUseSumAmt;
	/**
	 * 회원 총 주문건수
	 */
	private String pchCount;
	/**
	 * 회원 총 주문금액
	 */
	private String pchAmt;
	/**
	 * 유입 일련번호	 
	 */
	private java.lang.Long inflowSn;
	
	/**
	 * 포장선물 여부
	 */
	private String svcAplTpNm;

	/**
	 *  통화코드
	 */
	private String crncyCd;

	/**
	 * 환율 적용 시작 일시
	 */
	private Date exchgRtAplBegDt;

	private String stdrCrncyPayAmtEx;

	/**
	 * 주문 언어코드
	 */
	private String langCd;
	
	/**
	 * 대분류 브랜드명
	 */
	private String upperGrpBrndNm;
	
	/**
	 * 상품중량
	 */
	private java.math.BigDecimal godWt;
	
	/**
	 * 배송국가명
	 */
	private String dlvNationNm;

	/**
	 * 국내배송비
	 */
	private java.math.BigDecimal dlvCstAmt;

	/**
	 * 해외배송비
	 */
	private java.math.BigDecimal ovseaDlvCstAmt;
	
	private String shopId;
	
	private String dlvMnCd;
	
	private long allDcSumAmt;
	private long godDcSumAmt;
	private long bundleDcSumAmt;
	private long crsDcSumAmt;
	private long godCpnDcSumAmt;
	
	// 에스크로 주문상태
	private String escrStatus;

	/** 고객구매확정여부 */
	private String cstmrPchDcsnYn;
	
	/** 고객구매확정일시 */
	private Date cstmrPchDcsnDt;
	
	private String recentClaim;
	
	private java.math.BigDecimal mileAccmlSumAmt;
}
