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
public enum EMailEnum {
	
	JOIN_ONLINE_MEMBER("M61"),
	JOIN_MEMBERSHIP_MEMBER("M62"),
	JOIN_EMP_MEMBER("M64"),
	B2E_MEMBER("M65"),
	SECESSION_MEMBER("M66"),
	RECEIVE_AGR("M67"),
	SEARCH_PWD("M68"),
	UPDATE_MEMBER("M70"),
	MARKET_AGR("M71"), //국문온라인광구수신여부
	
	
	//메일템플릿 타이틀
	M71_MAIL_TITLE("온라인 광고 수신동의 여부 안내"), // 이력처리용 문구,
	

	/** EN 글로벌 AUTOTYPE Enum */
	// 회원
	ENG_MEMBER_CRTFC_EMAIL("E21"),						// 이메일 인증(회원가입 완료)
	//ENG_RE_MEMBER_CRTFC_EMAIL("E02"),					// 이메일 재인증
	ENG_SEARCH_PWD("E22"),								// 임시비밀번호 발급
	ENG_UPDATE_MEMBER("E23"),							// 회원정보수정 완료
	ENG_RECEIVE_AGR("E24"),								// E-mail 수신동의
	ENG_SECESSION_DORMANCY_MEMBER_INFO("E25"),			// 휴면회원 탈퇴 안내
	ENG_SECESSION_MEMBER("E26"),						// 회원탈퇴완료
	// CS
	ENG_CSO_MTM_INQ_ANS("E08"),							// 1:1 상담 답변
	ENG_CSO_GOD_INQ_ANS("E09"),							// 상품 Q&A 답변
	// 상품
	ENG_RE_STOCK_INFO("E10"),							// 상품 재입고 알림
	// 주문
	ENG_ORD_INFO("E11"),								// 주문완료
	// 배송
	ENG_DELIVERY_INFO("E12"),                            // 상품발송안내
	ENG_DEALAY_DELIVERY_INFO("E13"),                     // 발송지연 안내
	ENG_DELIVERY_COMPT_INFO("E14"),                      // 배송완료
	// 클레임
	ENG_CLM_CANCEL_INFO("E15"),                          // 주문취소완료
	ENG_CLM_RETURN_REQUEST_INFO("E16"),                  // 반품신청
	ENG_CLM_RETURN_COMPT_INFO("E17"),                    // 반품완료

	/** CN 글로벌 AUTOTYPE Enum */
	// 회원
	CHI_MEMBER_CRTFC_EMAIL("Z21"),						// 이메일 인증(회원가입 완료)
//	CHI_RE_MEMBER_CRTFC_EMAIL("Z02"),					// 이메일 재인증
	CHI_SEARCH_PWD("Z22"),								// 임시비밀번호 발급
	CHI_UPDATE_MEMBER("Z23"),							// 회원정보수정 완료
	CHI_RECEIVE_AGR("Z24"),								// E-mail 수신동의
	CHI_SECESSION_DORMANCY_MEMBER_INFO("Z25"),			// 휴면회원 탈퇴 안내
	CHI_SECESSION_MEMBER("Z26"),						// 회원탈퇴완료
	// CS
	CHI_CSO_MTM_INQ_ANS("Z08"),							// 1:1 상담 답변
	CHI_CSO_GOD_INQ_ANS("Z09"),							// 상품 Q&A 답변
	// 상품
	CHI_RE_STOCK_INFO("Z10"),							// 상품 재입고 알림
	// 주문
	CHI_ORD_INFO("Z11"),								// 주문완료
	// 배송
	CHI_DELIVERY_INFO("Z12"),							// 상품발송안내
	CHI_DEALAY_DELIVERY_INFO("Z13"),					// 발송지연 안내
	CHI_DELIVERY_COMPT_INFO("Z14"),						// 배송완료
	// 클레임
	CHI_CLM_CANCEL_INFO("Z15"),							// 주문취소완료
	CHI_CLM_RETURN_REQUEST_INFO("Z16"),					// 반품신청
	CHI_CLM_RETURN_COMPT_INFO("Z17"),					// 반품완료
	
	//20161219 추가	
	KOR_SECESSION_DORMANCY_MEMBER_INFO_EXPIRED("M74"),			// 한 휴면회원 자동 탈퇴 처리     
	ENG_SECESSION_DORMANCY_MEMBER_INFO_EXPIRED("E27"),			// 영 휴면회원 자동 탈퇴 처리     
	CHI_SECESSION_DORMANCY_MEMBER_INFO_EXPIRED("Z27")			// 중 휴면회원 자동 탈퇴 처리     
	;
	
	
	
	/**
	 * 값
	 */
	private final String value;

	/**
	 * Instantiates a new member enum.
	 *
	 * @param value [설명]
	 */
	private EMailEnum(final String value) {
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

