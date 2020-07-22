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
 * @since       2015. 5. 13       
 */
package com.plgrim.ncp.base.enums.member;

/**
 * [개인정보 Enum]
 * 
 * <p>
 * 
 * <ul>
 *   <li> [기능1]
 *   <li> [기능2]
 * </ul>.
 *
 * @author sy59.gim
 * @since 2015. 5. 13
 */
public enum MemberPersonalInfoEnum {
	
	// CONSTANTS
	// == 개인정보 제3자 제공 업체 START =======================================
	  CSTMR_MANAGE("COLOMBO_KOR")							// 고객관리
	// == 개인정보 제3자 제공 업체 END   =======================================
	
	// == 개인정보 취급위탁 업체 START =========================================
	, CSTMR_CNSL_REPAIR("FSP_COM|METANETMCC|TRANS_COSMOS")	// 고객상담,수선		- (주)에프에스피(FSP), ㈜메타넷엠씨씨, (주)트랜스코스모스코리아
	, RLNM_CRTFC_IPIN("NCI")									// 실명인증,I-pin발급	- NICE신용평가정보㈜
	, DM_SND("BILLPOST")										// DM발송				- (주)빌포스트
	, LGIST_SRVC("CJKEX")										// 물류서비스			- CJ대한통운 
	, SYS_MANAGE("SAMSUNG_SDS")								// 시스템구축및유지보수	- 삼성SDS
	, CSTMR_SALE_SVC("PLGRIM_FSHN_SHOP")						// 제품판매및응대서비스	- 플그림패션부분대리점(위탁매장)
	, SND_SMS("LG_CNS")										// SMS,MMS발송			- LG CNS
	, SND_KKO_NTCN_TAK("LG_CNS")								// 알림톡 발송 			- LG CNS
	, SND_EMAIL("FNF")											// EMAIL발송			- ㈜F&F
	, MOBILE_CNP_SND("M12")									// 모바일쿠폰발송		- 엠트웰브
	// == 개인정보 취급위탁 업체 END   =========================================

	// == 개인정보 변경 이력 DB 컬럼 START =====================================
	, MBR_NM("mbrNm")															// 회원 명
	, MBR_POST_NO("mbrPostNo")													// 회원 우편번호
	, MBR_ADDR("mbrBaseAddr|mbrDetailAddr")										// 회원 주소
	, MOBIL_NO("mobilNationNo|mobilAreaNo|mobilTlofNo|mobilTlofWthnNo")			// 휴대전화번호
	, MBR_BRTHDY_SLRCLD_YN("mbrBrthdySlrcldYn")									// 회원 생년월일 양력 여부
	, MBR_BRTHDY("mbrBrthdy")													// 회원 생년월일
	, MBR_SEX_SECT_CD("mbrSexSectCd")											// 회원 성별 구분 코드
	, MBR_EMAIL("mbrEmail")														// 회원 이메일
	, JOIN_DT("joinDt")															// 가입 일시
	, PSITN_GRPCO_NM("psitnGrpcoNm")											// 소속 그룹사 명
	, PSITN_DEPT_NM("psitnDeptNm")												// 소속 부서 명
	, UNITY_MBR_CRTFC_SECT_CD("unityMbrCrtfcSectCd")							// 통합 회원 인증 구분 코드
	, EMP_MAIL_CRTFC("empMailCrtfc")											// 임직원 메일 인증(임직원 회원)
	, RETIRE("retire")															// 퇴사(일반 회원)
	, MBR_PW("mbrPw")															// 회원 비밀번호
	, MBR_ID("mbrId")															// 회원 ID
	, SMS_RECPTN_AGR_YN("smsRecptnAgrYn")										// SMS 수신 동의 여부
	, EMAIL_RECPTN_AGR_YN("emailRecptnAgrYn")									// 이메일 수신 동의 여부
	, TM_RECPTN_AGR_YN("tmRecptnAgrYn")												// TM 수신 동의 여부
	, DM_RECPTN_AGR_YN("dmRecptnAgrYn")											// DM 수신 동의 여부
	, PHON_NO("telNationNo|telAreaNo|telTlofNo|telTlofWthnNo")					// 유선전화번호
	, MRRG_YN("mrrgYn")															// 결혼여부
	, MRRG_DATE("mrrgDate")														// 결혼 일자(결혼기념일)
	, CPR_NM("cprNm")															// 법인명
	, BMAN_REG_NO("bmanRegNo")													// 사업자등록번호
	, CPR_RPRSTIV_NM("cprRprstivNm")											// 법인 대표자명
	, CPRTEL_NO("cprtelNationNo|cprtelAreaNo|cprtelTlofNo|cprtelTlofWthnNo")	// 법인유선전화번호
	, ERP_GRD_MOD("erpGrdCd")													// ERP 등급 변경
	, INFOR_NTCN_RECPTN_TP_CD("inforNtcnRecptnTpCd")							// 정보성 알림 수신 유형 코드

	, SIGNL_EPID("signlEpid")													// 싱글EPID(임직원인증키)
	
	, MBR_STAT_CD("mbrStatCd")													// 회원 상태 코드
					
	, MBR_MEMO_CONT("mbrMemoCont")												// 회원성향(회원메모)
	 				
	, DLVSP_ADDR("baseAddr|detailAddr")									// 배송지 주소
	, DLVSP_ADDRSE("addrseNm")												// 배송지 수취인
	, DLVSP_TEL_NO("telNationNo|telAreaNo|telTlofNo|telTlofWthnNo")				// 배송지 전화 번호
	, DLVSP_MOBIL_NO("mobilNationNo|mobilAreaNo|mobilTlofNo|mobilTlofWthnNo")	// 배송지 휴대전화 번호
	 				
	, RFD_BNK_ACCT_NO("rfdBnkAcctNo")											// 환불 계좌 번호
	, RFD_BNK_ACCT_BNK_CD("rfdBnkAcctBnkCd")									// 환불 계좌 은행 코드
	, RFD_BNK_ACCT_ACNTHLDR_NM("rfdBnkAcctAcnthldrNm");							// 환불 계좌 예금주 명
	// == 개인정보 변경 이력 DB 컬럼 END   =====================================
	
	/**
	 * 이용 구분 코드
	 * upper_cd :  PSNL_INFO_USE_TP
	 *   
	 * @author sy59.gim
	 * @since 2015. 5. 13
	 *
	 */
	public enum UsefSectCd{
		  PSNL_INFO_STTUS	    		 // 개인정보이용현황 
		, PSNL_INFO_THPR_OFFER_DETL	     // 개인정보 제3자 제공 내역 
		, PSNL_INFO_TRTMNT_CNSGN_DETL	 // 개인정보 취급위탁 내역 
	}
	
	/**
	 * 이용 업무 코드
	 * upper_cd : PSNL_INFO_STTUS, PSNL_INFO_THPR_OFFER_DETL, PSNL_INFO_TRTMNT_CNSGN_DETL
	 *
	 * @author sy59.gim
	 * @since 2015. 5. 13
	 */
	public enum UsefJobCd{
		  MTM_INQ_REG	     			// 일대일문의등록
		, DLVSP_INFO_REG	     		// 배송정보등록
		, DLVSP_INFO_UDT	     		// 배송정보수정
		, PAY_INFO	     				// 결제정보
		, INQIRE	     				// 조회
		, DELETE	     				// 삭제
		, SAVE	     					// 저장
		, CSTMR_MANAGE					// 고객관리
		, SND_EMAIL					// 이메일 전송
		, CSTMR_CNSL_REPAIR	     		// 고객상담,수선
		, RLNM_CRTFC_IPIN	     		// 실명인증,I-pin발급
		, DM_SND	     				// DM발송
		, LGIST_SRVC	     			// 물류서비스
		, SYS_MANAGE	     			// 시스템구축및유지보수
		, CSTMR_SALE_SVC	     		// 제품판매및응대서비스
		, SND_SMS	     				// SMS,MMS발송
		, MOBILE_CNP_SND	     		// 모바일쿠폰발송
		,SND_KKO_NTCN_TAK				//카카오 알림톡
	}
	
	/**
	 * BO 사이트 ID
	 *
	 * @author sy59.gim
	 * @since 2015. 5. 13
	 */
	public enum BoSiteCd{
		  BO 
		, CS 
		, PO 
	}
	
	/**
	 * 이용 업무 상세 코드
	 * upper_cd : PSNL_INFO_STTUS.
	 *
	 * @author sy59.gim
	 * @since 2015. 5. 13
	 */
	public enum UsefJobDetail{
		  CLM_DETAIL 		// INQIRE  -  클레임상세
		, COM_DETAIL 		// INQIRE  -  업체상세
		, GOD_EVL_DETAIL 	// INQIRE  -  상품평상세
		, MBR_DETAIL 		// INQIRE  -  회원상세
		, MBR_ID 			// INQIRE  -  회원ID
		, MOBIL_NO 			// INQIRE  -  휴대전화번호
		, MTM_INQ_DETAIL 	// INQIRE  -  일대일문의상세
		, ORD_DETAIL 		// INQIRE  -  주문상세
		, MBR_SECSN 		// DELETE  -  회원탈퇴
		, EMAIL 			// MTM_INQ_REG  -  이메일
		, EMAIL_RCV_YN 		// MTM_INQ_REG  -  E-mail수신동의여부
		, SMS_RCV_YN 		// MTM_INQ_REG  -  SMS수신여부
		, ENG_NM 			// DLVSP_INFO_UDT  -  영문이름
		, RCVER_NM 			// DLVSP_INFO_UDT  -  이름(받는사람)
		, ACNTHLDR 			// PAY_INFO  -  예금주
		, BNK_ACCT_NO 		// PAY_INFO  -  계좌번호
		, BNK_NM 			// PAY_INFO  -  은행명
		, EXCEL_DOWN 		// SAVE  -  엑셀다운로드
		, LIST
		
		, MBR_INFO_ALL // 회원정보전체
		
		, STPLAT_APL		// 약관에따름
		, CLM_RCEPT			// 클레임접수
		, MTM_INQ_ANS		// 일대일문의답변
		, SYS_MANAGE		// 시스템구축및유지보수	SYS_MANAGE
		, SND_SMS 			// SMS,MMS발송	SND_SMS
		, CBLE_PHON_NO 		// 유선전화번호	CSTMR_CNSL_REPAIR>DLVSP_INFO_UDT>CBLE_PHON_NO
		, DLVSP_INFO_UDT 	// 배송정보수정	CSTMR_CNSL_REPAIR>DLVSP_INFO_UDT
		, CSTMR_CNSL_REPAIR // 고객상담,수선	CSTMR_CNSL_REPAIR
		, CSTMR_SALE_SVC 	// 제품판매및응대서비스	CSTMR_SALE_SVC
		, DM_SND 			// DM발송	DM_SND
		, ADDR 				// 주소	CSTMR_CNSL_REPAIR>DLVSP_INFO_UDT>ADDR
		, MOBILE_CNP_SND 	// 모바일쿠폰발송	MOBILE_CNP_SND
		, RLNM_CRTFC_IPIN 	// 실명인증,I-pin발급	RLNM_CRTFC_IPIN
		, LGIST_SRVC 		// 물류서비스	LGIST_SRVC
		, CLM_CANCEL_PAY	// 클레임으로 결제취소나 환불된경우 사용 
	}

	/**
	 * 개인정보 이용 주체 코드
	 * upper_cd :  PSNL_INFO_USEF_MBD.
	 *
	 * @author sy59.gim
	 * @since 2015. 5. 13
	 */
	public enum PsnlInfoUsefMbdCd{
		  CSTMR	  			 // 고객 
		, ADMIN	    		 // 어드민 
		, PSNL_INFO_COM	     // 개인정보 업체 
	}

	/**
	 * 개인정보 이용 업체
	 * upper_cd :  PSNL_INFO_USEF_COM.
	 *
	 * @author sy59.gim
	 * @since 2015. 5. 13
	 */
	public enum PsnlInfoUsefComCd{
		  NETPATHY	     	// 넷퍼씨
		, SAMSUNG_SDS	    // 삼성SDS 
		, SCI	     		// 서울신용평가정보㈜
		, SUREM	     		// 슈어엠
		, SIMPLEXI	     	// 심플렉스인터넷㈜	- 사용처 확인 필요
		, M12	     		// 엠트웰브
		, POSTOFC_EMS	    // 우체국EMS
		, PLGRIM_FSHN_SHOP	// 플그림  대리점, 위탁매장
		, METANETMCC	    // ㈜메타넷엠씨씨
		, BILLPOST	     	// ㈜빌포스트
		, FSP_COM	     	// ㈜에프에스피(FSP)
		, TRANS_COSMOS	    // ㈜트랜스코스모스코리아
		, COLOMBO_KOR	    // 콜롬보코리아 (COLOMBO VIA DELLA SPIGA)
		, FASTBOX	     	// 패스트박스(주)	- 사용처 확인 필요
		, HANJN	     		// 한진택배
		, HUMUSON           //휴머스온
		, CJKEX              //CJKEX
	}
	
	/**
	 * 개인정보 변경 이력 구분코드 - 회원.
	 *
	 * @author sy59.gim
	 * @since 2015. 5. 13
	 */
	public enum MbrPsnlInfoUdtSectCd {
		   MBR_NM						// 회원 명
		 , MBR_POST_NO					// 회원 우편번호
		 , MBR_ADDR						// 회원 주소
		 , MBR_ADDR_DETAIL				// 회원 상세 주소
		 , MBR_ADDR_SECT_CD				// 주소구분코드
		 , AUD_CD						// 행정동 코드
		 , MOBIL_NO						// 휴대전화번호
		 , MBR_BRTHDY_SLRCLD_YN			// 회원 생년월일 양력 여부
		 , MBR_BRTHDY					// 회원 생년월일
		 , MBR_SEX_SECT_CD				// 회원 성별 구분 코드
		 , MBR_EMAIL					// 회원 이메일
		 , JOIN_DT						// 가입 일시
		 , PSITN_GRPCO_NM				// 소속 그룹사 명
		 , PSITN_DEPT_NM				// 소속 부서 명
		 , UNITY_MBR_CRTFC_SECT_CD		// 통합 회원 인증 구분 코드
		 , EMP_MAIL_CRTFC				// 임직원 메일 인증(임직원 회원)
		 , RETIRE 						// 퇴사(일반 회원)
		 , MBR_ID						// 회원 ID
		 , MBR_PW						// 회원 비밀번호
		 , SMS_RECPTN_AGR_YN			// SMS 수신 동의 여부
		 , EMAIL_RECPTN_AGR_YN			// 이메일 수신 동의 여부
		 , SIGNL_EPID					// 싱글EPID(임직원인증키)
		 , PHON_NO						// 유선전화번호
		 , MRRG_YN						// 결혼여부
		 , MRRG_DATE					// 결혼 일자(결혼기념일)
		 , CPR_NM						// 법인명
		 , BMAN_REG_NO					// 사업자등록번호
		 , CPR_RPRSTIV_NM				// 법인 대표자명
		 , CPRTEL_NO					// 법인유선전화번호
		 , MBR_MEMO_CONT				// 회원성향(회원메모)
		 , MBR_STAT_CD					// 회원 상태 코드
		 , TM_RECPTN_AGR_YN				// TM 수신 동의 여부
		 , DM_RECPTN_AGR_YN				// DM 수신 동의 여부
		 , MBR_SIZE_MOD					// 회원 나의 사이즈
		 , ERP_GRD_MOD					// ERP 등급 변경
		 , INFOR_NTCN_RECPTN_TP_CD      // 정보성 알림 수신 유형 코드
	}
	
	/**
	 * 개인정보 변경 이력 구분코드 - 회원 배송지.
	 *
	 * @author sy59.gim
	 * @since 2015. 5. 13
	 */
	public enum MbrDlvspPsnlInfoUdtSectCd {
		   DLVSP_ADDR					// 배송지 주소
		 , DLVSP_ADDRSE					// 배송지 수취인
		 , DLVSP_TEL_NO					// 배송지 전화 번호
		 , DLVSP_MOBIL_NO				// 배송지 휴대전화 번호
	}
	
	/**
	 * 개인정보 변경 이력 구분코드 - 회원 환불 계좌.
	 *
	 * @author sy59.gim
	 * @since 2015. 5. 13
	 */
	public enum MbrRfdBnkAcctPsnlInfoUdtSectCd {
		   RFD_BNK_ACCT_NO					// 환불 계좌 번호
		 , RFD_BNK_ACCT_BNK_CD				// 환불 계좌 은행 코드
		 , RFD_BNK_ACCT_ACNTHLDR_NM			// 환불 계좌 예금주 명
	}
	
	/**
	 * 회원정보 수정매장/몰 코드
	  ㅁ. 회원 개인정보 변경 이력. 변경 위치 구분 코드(MBR_PSNL_INFO_MOD_HIST. MOD_LC_SECT_CD)
   
	 * 코드 추가
	   ㅁ. 변경 위치 구분 : MOD_LC_SECT
	   >. PLGRIM SHOP 온라인 몰 : DXM_ONLNE_MALL
	   >. CC : 고객센터
	   >. OFLNE_BRND_SHOP : 오프라인 브랜드 매장
	   >. ETC : 기타

	 */
	public enum MemberModLcSectCd{
		ONLNE_MALL
		,CC
		,OFLNE_BRND_SHOP
		,ETC
	}

	/**
	 * 값
	 */
	private final String value;

	/**
	 * Instantiates a new member personal info enum.
	 *
	 * @param value [설명]
	 */
	MemberPersonalInfoEnum(final String value) {
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
