package com.plgrim.ncp.base.enums;

/**
 * 회원혜택 Enum 
 */
public enum MemberBenefitEnum {

	// CONSTANTS
	YES("Y"), NO("N");

	/**
	 * 회원혜택 구분 코드
	 */
	public enum BnefSectCd {
        LOGIN					// 로그인 
        ,NEW_MBR_JOIN			// 신규 회원 가입
        ,PHOTO_GOD_REVW		// 포토 상품 리뷰
        ,TEXT_GOD_REVW			// 텍스트 상품 리뷰
        ,SNS_CNRS				// SNS공유
        ,ONLNE_GRD_MOD			// 온라인 등급 변경
        ,EMP_BNEF				// 임직원 혜택
        ,GRDBY_BRTHDY_BNEF	// 등급별 생일 혜택
        ,MBR_BEG_PCH_BNEF		// 회원 첫구매 혜택
        ,BSK_CPN_BNEF			// 장바구니 쿠폰 혜택
        ,RELIS_DRMC_MBR_BNEF	// 휴면 해제 회원 혜택
	}
	
	/**
	 * 회원혜택 지급 유형 코드
	 */
	public enum BnefPymntTpCd {
		ONLNE_CPN		//온라인쿠폰
		,ONOFLNE_CPN	//온오프라인쿠폰
		,WEBPNT		    //P포인트		
		,MBSH_PNT		//멤버쉽 포인트
	}
	
	
	/**
	 * 회원혜택 디바이스 코드
	 */
	public enum DvcCd {
		PC				//PC
		,MOBILE_WEB		//모바일 웹
		,MOBILE_APP		//모바일 앱
	}
	
	/**
	 * 회원혜택 언어 코드
	 */
	public enum LangCd {
		KOR			//한국어
		,CHI		//중국어
		,ENG		//영어
	}
	
	/**
	 * 회원혜택 모바일 어플리케이션 구분 코드
	 */
	public enum MobileAppSectCd {
		PLGRIMSHOP_APP			//PLGRIM 앱
		,BEANPOLE_APP		//빈폴 앱
		,EIGHT_SECONDS_APP	//8S 앱
		,TORYBURCH_APP      //토리버치 앱
	}
	
    /**
     * 회원혜택 상태코드
     * 
     * APRV_WAIT(승인대기), APRV(승인), STPGE(중지);
     */
    public enum MemberBenefitStat {
        APRV_WAIT
        ,APRV
        ,STPGE;
    }     
	
	/**
	 * 회원혜택 적용 대상 유형 코드
	 */
	public enum BnefTgtTpCd {
		 MALL_ID           //몰 ID
		,LANG              //언어
		,DVC               //디바이스
		,MOBILE_APP_SECT   //모바일 앱 구분
		,TGT_MBR_TP        //대상 회원 유형
		,TGT_MBR_ATRB      //대상 회원 속성
		,GRPCO_ID          //그룹사 ID
		,AFF_COM_ID        //제휴 업체 ID
		,CHNNL_RECPTN_AGR_SECT //채널 수신 동의 구분
	}
	
	/**
	 * 채널 수신 동의 구분 코드
	 */
	public enum ChnnlRecptnAgrSectCd {
		 SMS_RECPTN_AGR     //SMS 수신 동의
		,EMAIL_RECPTN_AGR   //이메일 수신 동의
		,DM_RECPTN_AGR      //DM 수신 동의
		,TM_RECPTN_AGR      //TM 수신 동의
	}
	
	/**
	 * 포인트 적용 사유 코드
	 */ 
	public enum PntAplResnCd {
		ADMIN_ACCML     //관리자 적립
	}
	
	/**
	 * 혜택 상세 구분 코드 BNEF_DETAIL_SECT_CD 
	 */
	public enum BnefDetailSectCd {
		MOBILE_APP_DWLD_BNEF    //모바일 앱 다운로드 혜택
		,NEW_MBR_JOIN   		//신규 회원 가입
		,MARKT_RECPTN_AGR      	//마케팅 수신 동의
		,NEW_UNITY_MBR_JOIN     //신규 멤버십 회원 가입
		,MBR_JOIN_RECOMENDE_BNEF //회원가입 피추천인 혜택
		,MBR_JOIN_RECOMENDR_BNEF //회원가입 추천인 혜택
        ,MBR_SIZE_REG
	}
	
	/**
	 * 값
	 */
	private final String value;

	/**
	 * Instantiates a new enum.
	 */
	private MemberBenefitEnum(final String value) {
		this.value = value;
	}

	/**
	 * toString
	 */
	@Override
	public String toString() {
		return value;
	}
}
