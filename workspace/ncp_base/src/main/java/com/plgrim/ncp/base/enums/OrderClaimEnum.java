package com.plgrim.ncp.base.enums;

/**
 * 반드시 enum 속성은 상수이기 때문에 대문자로 선언 한다. 예) USER_ID_INPUT_NAME
 * enum를 String값 비교 할때는 if (EnumSample.USER_ID_INPUT_NAME.equals("비교값"))
 */
public enum OrderClaimEnum {	
	ORD("ord"),
	CLM("clm"),
 	KOR("KOR"),
	KRW("KRW"),
	USD("USD"),
	
	/**
	 * 주문상태 코드
	 * @author user
	 *
	 */
	ORD_STAT_ALL_CNCL("ALL_CNCL"),                         // >.전체 취소 :
	ORD_STAT_DEPST_WAIT("DEPST_WAIT"),                     // >.입금 대기 :
	ORD_STAT_DLV_COMPT("DLV_COMPT"),                       // >.배송 완료 :
	ORD_STAT_DLV_PROGRS("DLV_PROGRS"),                     // >.배송중 :
	ORD_STAT_DLV_PRPARE("DLV_PRPARE"),                     // >.배송준비 :
	ORD_STAT_PAY_COMPT("PAY_COMPT"),                       // >.결제 완료 :
	ORD_STAT_PAY_WAIT("PAY_WAIT"),                         // >.결제 대기 :
	ORD_STAT_RESVE_ORD_DEPST_WAIT("RESVE_ORD_DEPST_WAIT"), // >.예약 주문 입금 대기:
	ORD_STAT_RESVE_ORD_PAY_COMPT("RESVE_ORD_PAY_COMPT"),   // >.예약 주문 결제 완료:
	
	/**
	 * 배송 코드
	 * @author user
	 *
	 */
	DLVSP_ORD_DLVSP("ORD_DLVSP"),							// >. 주문 배송지 :
	DLVSP_CLM_PCUPSPP("CLM_PCUPSP"),						// >. 클레임 수거지 :
	DLVSP_CLM_DLVSP("CLM_DLVSP"),							// >. 클레임 배송지 :

	DLV_STAT_DLIVY_COMPT("DLIVY_COMPT"),          			// >. 출고 완료
	DLV_STAT_DLIVY_DRCT("DLIVY_DRCT"),           			// >. 출고지시
	DLV_STAT_DLIVY_DRCT_CNCL("DLIVY_DRCT_CNCL"),      		// >. 출고지시취소
	DLV_STAT_DLV_COMPT("DLV_COMPT"),            			// >. 배송 완료
	DLV_STAT_DLV_WAIT("DLV_WAIT"),             				// >. 배송대기
	DLV_STAT_EXCHG_WRHS_WAIT("EXCHG_WRHS_WAIT"),            // >. 교환입고대기: EXCHG_WRHS_WAIT
	DLV_STAT_LSSGS_RCEPT("LSSGS_RCEPT"),          			// >. 결품접수
	DLV_STAT_SHOP_GOD_PRPARE_COMPT("SHOP_GOD_PRPARE_COMPT"),	// >. 매장 상품 준비 완료
	
	/**
	 * 주문타입 코드
	 * @author user
	 *
	 */
	ORD_TP_CD_GNRL_ORD("GNRL_ORD"),							//>. 일반주문 : 
	ORD_TP_CD_LAG_QTY_ORD("LAG_QTY_ORD"),					//>. 대량주문 : 
	ORD_TP_CD_RESVE_ORD("RESVE_ORD"),						//>. 예약주문 : 
	ORD_TP_CD_SHOP_PKUP_ORD("SHOP_PKUP_ORD"),				//>. 매장 수령 주문 : 

	// 2016.01.26 by cannon
	DLV_ADBUK_TURN_PREFIX_ORD("10000"),						//주문배송지순번
	DLV_ADBUK_TURN_PREFIX_MBR("20000");						//회원배송지

	/**
	 * KCP 승인취소
	 * @author Seed
	 *
	 */
	public enum KcpCancel {
		
		/** 전체취소 : STSC */
		STANDARD_CANCEL_PAY("STSC")
		/** 부분취소 : STPC */
		, STANDARD_PARTCANCEL_PAY("STPC")
		
		/** 전체환불 : STHD */
		, STANDARD_REFUND_PAY("STHD")
		/** 부분환불 : STPD */
		, STANDARD_PARTREFUND_PAY("STPD")
		
		/** 휴대폰 재결제 : STRA */
		, MOBILPON_REPAY("STRA")
		
		/** 에스크로 즉시취소 (배송 전 취소) : STE2 */
		, ESCROW_CANCEL_BEFORE_DLV("STE2")
		
		/** 에스크로 정산보류 : STE3 */
		, ESCROW_DEFER_CALCULATE("STE3")
		/** 에스크로 취소 (배송 후 취소) : STE4 */
		, ESCROW_CANCEL_AFTER_DLV("STE4")
		/** 에스크로 발급계좌해지 (가상계좌) : STE5 */
		, ESCROW_CANCEL_VIRTLBNKACCT("STE5")
		
		/** 에스크로 구매 확인 후 취소 : STE9 */
		, ESCROW_CANCEL_AFTER_CONFIRM("STE9")
		
		/** 에스크로 계좌이체 구매 확인 후 취소 : STE9_A */
		, ESCROW_CANCEL_RLTMBNKACCT_AFTER_CONFIRM("STE9_A")
		/** 에스크로 계좌이체 구매 확인 후 부분취소 : STE9_AP */
		, ESCROW_PARTCANCEL_RLTMBNKACCT_AFTER_CONFIRM("STE9_AP")
		
		/** 에스크로 가상계좌 구매 확인 후 환불 : STE9_V */
		, ESCROW_CANCEL_VIRTLBNKACCT_AFTER_CONFIRM("STE9_V")
		/** 에스크로 가상계좌 구매 확인 후 부분환불 : STE9_VP */
		, ESCROW_PARTCANCEL_VIRTLBNKACCT_AFTER_CONFIRM("STE9_VP")
		;
		
		/**
    	 * KCP I/F - 변경 유형
    	 */
    	private final String modType;
    	
    	private KcpCancel(final String modType) {
    		this.modType = modType;
    	}
    	
    	public String getModType() {
            return modType;
        }
	}
	
    final String text;

    private OrderClaimEnum(final String text) {
        this.text = text;
    }

    /* (non-Javadoc)
     * @see java.lang.Enum#toString()
     */
    @Override
    public String toString() {
        return text;
    }

    
    /**
     * 클레임상태코드
     * 
     * @author seed
     *
     */
    public enum ClmStatEnum {
    	REQST, PAY_WAIT, RCEPT, COMPT, WTHDRAW;
    }
    
    public enum EscrStatCd {
    	/** 에스크로 신청 */
    	ESCR_REQST
    	/** 에스크로 배송등록 */
    	, ESCR_DLV_REG
    	/** 에스크로 구매확정 */
    	, ESCR_PCH_DCSN
    	/** 에스크로 구매거부 */
    	, ESCR_PCH_REJECT
    	/** 에스크로 자동구매확정 */
    	, ESCR_AUTO_PCH_DCSN
    	/** 에스크로 정산 보류 */
    	, ESCR_CAL_RESRVE
    	/** 배송 전 취소 */
    	, DLV_BF_CNCL
    	/** 배송 후 취소 */
    	, DLV_AF_CNCL
    }
    /**
     * 클레임사유코드
     * 
     * @author seed
     *
     */
    public enum ClmRsnEnum {
    	/**
    	 * 사유코드 (자사귀책여부, 취소, 반품, 교환) 
    	 */
    	CSTMR_CHGMIND_CNCL(		false,	true,	false,	false	),
    	CSTMR_CHGMIND(			false,	false,	true,	true	),
    	SIZE_EXCHG(				false,	true,	false,	false	),
    	SHTG_CNCL(				true,	true,	false,	false	),
    	DLV_DELAY_CNCL(			true,	true,	false,	false	),
    	DPLC_ORD_CNCL(			false,	true,	false,	false	),
    	SYS_ERR_CNCL(			true,	true,	false,	false	),
    	OVSITE_DLV(				true,	true,	false,	false	),
    	HDRY_LOST(				true,	true,	false,	false	),
    	OPT_CHG(				false,	false,	false,	false	),
    	GOD_BADN(				true,	true,	true,	true	),
    	GOD_DMG(				true,	false,	false,	false	),
    	GOD_STAN(				true,	false,	false,	false	),
    	ETC_GOD_INFO_GAP(		true,	false,	false,	false	),
    	SIZE_INFO_DFFRNC(		true,	false,	false,	false	),
    	COLOR_INFO_DFFRNC(		false,	false,	false,	true	),
    	WN_DLV(					true,	false,	true,	true	),
    	PRC_GAP(				true,	false,	false,	false	),
    	ETC(					true,	false,	false,	false	),
    	AUTO_SHTG_CNCL(			true,	false,	false,	false	),
    	SHOP_NON_RECPT(			false,	false,	false,	false	),
    	PRC_MOD(				true,	false,	false,	false	),
    	SND_CSTMR(				true,	false,	false,	false	),
    	DEFAULT_CLAIM(			true,	false,	false,	false	),
    	;
    	
    	/**
    	 * 자사 귀책 여부
    	 * 
    	 * true : 판매자귀책 / false : 구매자귀책
    	 */
    	private final boolean mcomRsn;

    	/**
    	 * 취소클레임 사유여부
    	 */
    	private final boolean cancelRsn;
    	
    	/**
    	 * 반품클레임 사유여부
    	 */
    	private final boolean returnRsn;
    	
    	/**
    	 * 교환클레임 사유여부
    	 */
    	private final boolean exchangeRsn;
    	
    	private ClmRsnEnum(final boolean mcomRsn, final boolean cancelRsn, final boolean returnRsn, final boolean exchangeRsn) {
    		this.mcomRsn = mcomRsn;
    		this.cancelRsn = cancelRsn;
    		this.returnRsn = returnRsn;
    		this.exchangeRsn = exchangeRsn;
    	}
    	
        public boolean isMcomRsn() {
            return mcomRsn;
        }
        
        public boolean isCancelRsn() {
            return cancelRsn;
        }
        
        public boolean isReturnRsn() {
            return returnRsn;
        }
        
        public boolean isExchangeRsn() {
            return exchangeRsn;
        }
    }
    
    public enum ClmTpEnum {
    	/** 부분취소 */
    	PART_CNCL
    	/** 전체취소 */
    	, ALL_CNCL
    	/** 일반교환 */
    	, GNRL_EXCHG
    	/** 반품 */
    	, RTGOD
    }
    
    public enum ErpTrnsmisTpEnum {
    	 MILE_USE
    	 , MILE_USE_CNCL
    	 , MILE_ACCML
    	 , MILE_ACCML_CNCL
    	 , SALES
    	 , SALES_CNCL
    	 , ONLNE_RT_REQUST
    	 , ONLNE_RT_CNCL
    	 , ONLNE_RT_COMPT
    	 , SHOP_RTGOD_RT
    }
}
