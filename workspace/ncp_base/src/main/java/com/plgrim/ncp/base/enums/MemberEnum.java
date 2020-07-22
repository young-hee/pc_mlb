/* Copyright (c) 2015 plgrim, Inc.
 * All right reserved.
 * http://plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 *
 * ------------------------------------------------------------------------
 * @author      sy59.gim
 * @since       2015. 6. 17       
 */

package com.plgrim.ncp.base.enums;

/**
 * [회원 Enum].
 *
 * @author sy59.gim
 * @since 2015. 6. 17
 */
public enum MemberEnum {
	
	// CONSTANTS
	YES("Y"), NO("N"),X("X")
	, KOR("KOR")
	, CHI("CHI")
	, ENG("ENG")
	, ONLINE_CLUB_LOGIN_CNT("5")	// 온라인 클럽 로그인 횟수 
	, ONLINE_CLUB_GOD_EVL_CNT("1")	// 
	, ONLINE_CLUB_SNS_CNT("1")
    , MALL("DXM")
    , CLNT_CD_A("A") // CLNT_CD_A : 플그림 통합고객 및 온라인고객
    ,SYSTEM_BO_ID("BO"),SYSTEM_DX_ID("DXM") // BO,PLGRIM SHOP구분을 위한 구분
    /* 080 수신거부 */
    , SMS_REJECT_DSCR("080 마케팅무료수신거부로 인한 SMS 수신거부") // 이력처리용 문구
	, PAY_SUM_AMT("1000000") // 온라인(PLGRIM SHOP) 회원 VIP 등급 산정 기준 구매 금액
	, PAY_SUM_CNT("3") // 온라인(PLGRIM SHOP) 회원 VIP 등급 산정 기준 구매 건수
	;
	
	/**
	 * [클래스 설명].
	 *
	 * @author sy59.gim
	 * @since 2015. 6. 17
	 */
	public enum ApplcnMemberSectCd {
		MBR				//회원
		,NMBR			//비회원
		,NMBR_EMP		//비회원 임직원
		
	}
	
	/**
	 * 회원상태.
	 *
	 * @author sy59.gim
	 * @since 2015. 6. 17
	 */
	public enum MemberStatCd{
		ACT					//정상
		,STOP				//정지
		,DRMNCY		//휴면
		,SECSN			//탈퇴
		,BLCLST_A		//블랙리스트 A
		,BLCLST_B		//블랙리스트 B
	}
	
	/**
	 * 회원유형.
	 *
	 * @author sy59.gim
	 * @since 2015. 6. 17
	 */
	public enum MemberTpCd{
		NMBR                      //비회원              
		,ONLINE_MBR               //온라인 회원         
		,UNITY_MBR                //통합 회원           
		     
	}
	
	/**
	 * 회원속성.
	 *
	 * @author sy59.gim
	 * @since 2015. 6. 17
	 */
	public enum MemberAtrbCd{
		GNRL_MBR		//일반 회원
		,EMP				//임직원
	}
	
	/**
	 * 통합회원인증구분코드
	 * 
	 * @author sy59.gim
	 * @since 2015. 6. 17
	 */
	public enum UnityMbrCrtfcSectCd{
		SLF_CRTFC	//휴대폰 실명인증
		,IPIN_CRTFC		//IPIN 실명인증
		,NO_CRTFC				//비인증
	}
	
	/**
	 * 회원 인증 : 회원 인증 유형 코드
	 * 
	 * @author sy59.gim
	 * @since 2015. 6. 17
	 */
	public enum MemberSecsnSectCd{
		ADMIN_SECSN	         	// 관리자 탈퇴
		, MBR_SECSN	        	// 회원 탈퇴
		, DRMNCY_SECSN	        // 휴면 탈퇴
		, ERP_SECSN				// 오프라인 매장에서 온라인 탈퇴
	}
	
	/**
	 * 회원 인증 : 회원 인증 유형 코드
	 * 
	 * @author sy59.gim
	 * @since 2015. 6. 17
	 */
	public enum MemberCrtfcTpCd{
		B2E_EMP_CRTFC	         	// B to E 인증
		, EMAIL_CRTFC	        // 이메일 인증
		, EMP_CRTFC	           	// 사원 인증
		, IPIN_CRTFC	    // IPIN 실명 인증
		, SLF_CRTFC			// 휴대전화 실명 인증
	}
	
	/**
	 * 임시 비밀번호 발송 방법
	 * 
	 * @author sy59.gim
	 * @since 2015. 6. 17
	 */
	public enum MemberPwdSendMethod{
		SMS	
		, EMAIL
	}
	
	/**
	 * ID 연계 유형 코드
	 * 
	 * @author user
	 *
	 */
	public enum MemberIdCntcTpCd{
		FACEBUK
		, NAVER
		, NAVER_MBM
		, NAVER_SAM
	}

	/**
	 * SSO 그룹 코드
	 ㅁ. SSO 그룹 : SSO_GRP
	 >. FNF 그룹 : FNF_GRP
	 >. 토리버치 그룹 : TRY_GRP
	 */
	public enum MemberSsoGrpCd{
		FNF_GRP	         	// FNF 그룹
	}

	/**
	 * 홍보 마케팅 이용동의
	 */
	public enum MbrStplatMarktAgr{
		MARKT_PSNL_INFO_COLCT_USEF_AGR	         	// 홍보 마케팅 이용동의
	}
	
	/**
	 * 약관코드
	 * ONLNE_SITE_USEF_STPLAT : 온라인사이트 이용약관
	 * PSNL_INFO_PRTC_USEF_AGR : 개인정보 보호를 위한 이용자 동의사항
	 * PSNL_INFO_COLCT_USEF_AGR : 개인정보 수집 이용에 대한 동의
	 * PSNL_INFO_THPR_OFFER_DETL : 개인정보 제 3자 제공 안내 (선택)
	 * PSNL_INFO_COLCT_AGR : 개인정보수집동의
	 * PSNL_INFO_TRTMNT_CNSGN_AGR : 개인정보의 취급위탁 동의
	 * MBSH_STPLAT : 멤버십 약관
	 * NMBR_PSNL_INFO_COLCT_USEF_AGR : 비회원 개인정보 수집 이용 동의 
	 * EMAIL_WONTICE_COLCT_REJECT : 이메일 무단 수집 거부
	 * MARKT_PSNL_INFO_COLCT_USEF_AGR : 홍보 마케팅 목적 개인정보 수집 및 이용 동의 (선택)
	 * PSNL_INFO_TRTMNT_POLCY : 개인정보 취급 방침
	 * PSNL_INFO_USEF_ARG_SK : 개인정보 이용 동의 SK
	 * PSNL_INFO_USEF_ARG_KT : 개인정보 이용 동의 KT
	 * PSNL_INFO_USEF_ARG_LG : 개인정보 이용 동의 LG
	 * ORG_IDNTFC_INFO_PRCS_AGR_SK : 고유식별정보 처리 동의 SK
	 * ORG_IDNTFC_INFO_PRCS_AGR_KT : 고유식별정보 처리 동의 KT
	 * ORG_IDNTFC_INFO_PRCS_AGR_LG : 고유식별정보 처리 동의 LG
	 * SLF_CRTFC_SVC_USEF_STPLAT_SK : 본인인증 서비스 이용약관 SK
	 * SLF_CRTFC_SVC_USEF_STPLAT_KT : 본인인증 서비스 이용약관 KT
	 * SLF_CRTFC_SVC_USEF_STPLAT_LG : 본인인증 서비스 이용약관 LG
	 * MCC_USEF_STPLAT_AGR_SK : 통신사 이용약관 동의 SK
	 * MCC_USEF_STPLAT_AGR_KT : 통신사 이용약관 동의 KT
	 * MCC_USEF_STPLAT_AGR_LG : 통신사 이용약관 동의 LG
	 * PSNL_INFO_THPR_OFFER_AGR_KT : 개인정보 제3자 제공동의 KT
	 * PSNL_INFO_THPR_OFFER_AGR_LG : 개인정보 제3자 제공동의 LG
	 */
	public enum StplatCd{
		ONLNE_SITE_USEF_STPLAT,
		PSNL_INFO_PRTC_USEF_AGR,
		PSNL_INFO_COLCT_USEF_AGR,
		PSNL_INFO_THPR_OFFER_DETL,
		PSNL_INFO_COLCT_AGR,
		PSNL_INFO_TRTMNT_CNSGN_AGR,
		MBSH_STPLAT,
		NMBR_PSNL_INFO_COLCT_USEF_AGR,
		EMAIL_WONTICE_COLCT_REJECT,
		MARKT_PSNL_INFO_COLCT_USEF_AGR,
		PSNL_INFO_TRTMNT_POLCY,
		NMBR_PSNL_INFO_COLCT_USEF_AGR_ORD,
		PSNL_INFO_USEF_ARG_SK,
		PSNL_INFO_USEF_ARG_KT,
		PSNL_INFO_USEF_ARG_LG,
		ORG_IDNTFC_INFO_PRCS_AGR_SK,
		ORG_IDNTFC_INFO_PRCS_AGR_KT,
		ORG_IDNTFC_INFO_PRCS_AGR_LG,
		SLF_CRTFC_SVC_USEF_STPLAT_SK,
		SLF_CRTFC_SVC_USEF_STPLAT_KT,
		SLF_CRTFC_SVC_USEF_STPLAT_LG,
		MCC_USEF_STPLAT_AGR_SK,
		MCC_USEF_STPLAT_AGR_KT,
		MCC_USEF_STPLAT_AGR_LG,
		PSNL_INFO_THPR_OFFER_AGR_KT,
		PSNL_INFO_THPR_OFFER_AGR_LG
	}
	
	public enum SmsRcvRejectCd{
        
        /*클라이언트 코드
         CLNT_CD
         시나리오 A : A
         시나리오 B : B
         */
        CLNT_CD_A, 
        CLNT_CD_B,
        
        /*소스 구분 코드
        ㅁ. SOURCE_GB
        ㅁ. 수신 거부 신청 시스템
           >. ARS 기기 : A
           >. POS 입력 : P
           >. 수신거부사이트 : I
           >. 반영실패 : F
           >. 당일 접수 : N */
        SOURC_SECT_CD_A,
        SOURC_SECT_CD_P,
        SOURC_SECT_CD_I,
        SOURC_SECT_CD_F,
        SOURC_SECT_CD_N,
        
        /*ㅁ. 작업 상태를 작성
           >. 작업 전: N
           >. 작업 중: X
           >. 작업 완료: Y
           >. 오류 : E */
        
        OPERT_N,
        OPERT_X,
        OPERT_Y,
        OPERT_E
    }



	/**
	 * 정보성 알림 수신방법 코드
	 * ㅁ. 정보성 알림 수신방법 코드 : INFOR_NTCN_RECPTN_TP_CD
	 >.  카카오톡 : NTCN_TAK
	 >.  문자 : CHAR
	 * >.
	 */
    public enum MemberInforRecptnCd{
		NTCN_TAK,
		CHAR
	}
    
    /**
     * ㅁ. 회원 사이즈 분류 : MBR_SIZE_CLFC
     *  >. 전체상품 기본사이즈 : ALL_BASE_SIZE
     *  >. 여성상품 기본사이즈 : WOMEN_BASE_SIZE
     *  >. 남성상품 기본사이즈 : MEN_BASE_SIZE
     *  >. 키즈상품 기본사이즈 : KIDS_BASE_SIZE
     *  >. 기본사이즈 미지정 : BASE_SIZE_UNDS
     */
    public enum MbrSizeClfc {
    	ALL_BASE_SIZE,
    	WOMEN_BASE_SIZE,
    	MEN_BASE_SIZE,
    	KIDS_BASE_SIZE,
    	BASE_SIZE_UNDS
    }
    
	/**
	 * 값
	 */
	private final String value;

	/**
	 * Instantiates a new member enum.
	 *
	 * @param value [설명]
	 */
	private MemberEnum(final String value) {
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
