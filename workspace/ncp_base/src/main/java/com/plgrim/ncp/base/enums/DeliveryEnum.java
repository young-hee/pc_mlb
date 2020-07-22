package com.plgrim.ncp.base.enums;

public enum DeliveryEnum {
	
	YES("Y"),NO("N"),
	
	/* 배송 기본 정보 */
	B031("B031"),	//임시매장
	FC01("FC01"), FC08("FC08"),	//물류 재고 저장위치 
	PKUP_GRP_DGRE("9999"), 		//물류 STO 요청 픽업 차수번호 
	GFT_GRP_DGRE("7777"), 		//사은품 출고 차수번호 (단독출고 사은품 적용) 
	APPN_DLV_COM_CD("CJKEX"),	//WMS택배업체 : CJ 대한통운.
	
	/* 배송상태 */
	DLV_STAT_DLV_WAIT("DLV_WAIT"),     				//배송상태 : 배정대기
	DLV_STAT_EXCHG_WRHS_WAIT("EXCHG_WRHS_WAIT"),    //배송상태 : 교환입고대기
	DLV_STAT_DLIVY_DRCT("DLIVY_DRCT"),     			//배송상태 : 출고지시
	DLV_STAT_DLIVY_DRCT_WAIT("DLIVY_DRCT_WAIT"),    //배송상태 : 출고지시대기
	DLV_STAT_DLIVY_DRCT_CNCL("DLIVY_DRCT_CNCL"),    //배송상태 : 출고지시취소
	DLV_STAT_DLIVY_COMPT("DLIVY_COMPT"),     		//배송상태 : 출고완료
	DLV_STAT_DLV_COMPT("DLV_COMPT"),     			//배송상태 : 배송완료
	DLV_STAT_SHOP_PRPARE_COMPT("SHOP_PRPARE_COMPT"),//배송상태 : 매장준비완료
	DLV_STAT_SHTG_RCEPT("SHTG_RCEPT"),     			//배송상태 : 결품접수
	
	RTRVL_STAT_RTRVL_DRCT("RTRVL_DRCT"),     		//회수상태 : 회수지시
	
	DLV_ONLINE_SHOP_DISCOVERY("X30004"),     		//온라인 매장 코드 : 디스커버리 X = X30004
	DLV_ONLINE_SHOP_MLB("M510"),     				//온라인 매장 코드 : M = 510
	DLV_ONLINE_SHOP_MLB_KIDZ("I50002"),     		//온라인 매장 코드 : I = 50002
	DLV_ONLINE_SHOP_SA("A30003"),					//온라인 매장 코드 : A = A30003
	
	DLIVY_DRCT_CNCL_WAIT("DLIVY_DRCT_CNCL_WAIT"),     			// 출고지시 취소 대기
	DLIVY_DRCT_CNCL_DCSN("DLIVY_DRCT_CNCL_DCSN"),     			// 출고지시 취소 확정
	;
	
	
	//배송상태 코드
	public enum DlvStatCd {
		DLV_WAIT, 			//배정대기
		EXCHG_WRHS_WAIT,	//교환입고대기
		DLIVY_DRCT,			//출고지시
		DLIVY_DRCT_CNCL,	//출고지시취소
		DLIVY_COMPT,		//출고완료
		DLV_COMPT,			//배송완료
		SHOP_PRPARE_COMPT,	//매장 준비 완료
		SHTG_RCEPT,			//결품접수
	}	
	
	
	//출고유형 코드
	public enum DlvTpCd {
		ORD, 			//주문
		EXCHG,			//교환
		DRT_EXCHG,		//맞교환
		SHOP_PKUP, 		// 매장픽업
		REPAIR, 		// 수선
		QDLV 			// 퀵배송
	}	
	
	
	//주문상태 코드
	public enum OrdStatCd {
		PAY_WAIT, 				//결제 대기
		DEPST_WAIT,				//입금 대기
		RESVE_ORD_DEPST_WAIT,	//예약 주문 입금 대기
		PAY_COMPT,				//결제 완료
		RESVE_ORD_PAY_COMPT,	//예약 주문 결제 완료
		ALL_CNCL,				//전체 취소
		DLV_PRPARE,				//배송준비
		DLV_PROGRS,				//배송중
		DLV_COMPT				//배송완료
	}
	
	
	//주문유형 코드
	public enum OrdTpCd {
		GNRL_ORD, 			//일반주문
		LAG_QTY_ORD,		//대량주문
		TEL_ORD,			//전화주문
		AFF_ORD,			//제휴업체주문
		RESVE_ORD,			//예약주문
		SHOP_PKUP_ORD,		//매장픽업주문
		GFCT_ORD			//상품권주문
	}
	
	
	//주문상품유형 코드
	public enum GodTpCd {
		GNRL_GOD, 			//일반상품
		PCHS_GFT,			//사입사은품
		CNVRS_GFT,			//전환사은품
		SET_GOD,			//세트상품
		PCKAGE_GOD,			//패키지상품
		GFCT				//상품권
	}
	
	
	//판매매장 코드
	public enum SaleShopCd {
		A345, 	//온라인통합몰국내
		A500,	//온라인통합몰해외
		B04E,	//온라인제휴몰
		A351,	//온라인통합몰단체
		A505,	//온라인토리버치
		A506	//온라인톰브라운
	}
	
	
	//입점구분 코드
	public enum PartmalSectCd {
		MCOM, 	//자사
		PARTMAL	//입점사
	}
	
	
	//사은품유형 코드
	public enum GiftTpCd {
		ORD, 	//주문사은품
		GOD		//상품사은품
	}
	
	
	//시스템 처리 구분
	public enum ProcType4Hist {
		ASSIGN_SHOP_BATCH, 		//배정배치
		ENFRC_ASSIGN_SHOP, 		//강제배정
		AUTO_ASSIGN_SHOP_BATCH,	//배정배치(배송매장 배정)
		QUICK_ASSIGN_BATCH 		//배정배치(퀵배송 배정)
	}
	

	//[SPLIT] HUB 분배플래그 코드
	public enum DstbFlg {
		RESVE_RCPTFR_HUB, 		//예약영수증HUB전달
		DLIVY_DRCT_CNCL_HUB,	//출고지시취소HUB전달
		DLIVY_REQEST_HUB,		//출고의뢰 HUB전달
		SALE_RCPTFR_HUB,		//판매영수증 HUB전달
		RESVE_RCPT_DLIVY_REQEST_HUB	//예약영수 출고의뢰 HUB전달
	}


	//[SPLIT] 예약영수증 구분 코드
	public enum ResveRcptfrSectCd {
		RESVE, 		//생성
		CNCL		//취소
	}


	//[SPLIT] 예약영수증 ERP처리 에러코드
	public enum ErpRetailErrorCd {
		A001, 		//[세트] POS DATA exist. ([세트] 이미 생성된 예약영수증)
		A002,		//[일반] POS DATA exist. ([일반] 이미 생성된 예약영수증)
		A003,		//No avalilable Stock.   (가용재고 부족)
		A004,		//결재정보 없음.   		 (결재정보 없음)
		A005,		//No receipt.   		 (영수증 없음)
		A006,		//A006_판매취소_There is No data yrsdt0027!! (취소할 예약영수증 없음)
		A009,		//Material 출고보류!!(327142W22B,00,0000) GI is suspended
		A010, 		//상품코드가 없습니다	 (ERP에 없는 품번)
		A011		//리스팅 오류 MARC		 (품번이 매장으로 확장이 안됨. 브랜드 영업에 리스팅처리 요청)
	}

	/**
	 * #50212 [개발]픽업 재진열 대상 알림(매장 Dashboard) 기능 추가
	 * 	- 픽업 재진열 코드
	 */
	public enum PkupRedispCd {
		REDISP_UTGT, 	// 재진열 비대상
	 	REDISP_TGT, 	// 재진열 대상
	 	REDISP_COMPT	// 재진열 완료
	}

	final String text;

    private DeliveryEnum(final String text) {
        this.text = text;
    }

    /* (non-Javadoc)
     * @see java.lang.Enum#toString()
     */
    @Override
    public String toString() {
        return text;
    }
    
    
}
