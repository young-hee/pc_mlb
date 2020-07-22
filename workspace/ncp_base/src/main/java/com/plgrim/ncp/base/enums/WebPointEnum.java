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
 * [웹포인트 Enum].
 *
 * @author nana
 * @since 2016. 2. 1
 */
public enum WebPointEnum {

	// CONSTANTS
	YES("Y"), NO("N"),WEBPNT("WEBPNT")
	;


	/**
	 * 웹포인트 사유코드
	 */
	public enum WebPntResnCd {
		PCH		//구매
		,BNEF_PYMNT				//혜택 지급
		,EVT			//이벤트
		,ADMIN_MDAT		//관리자조정

	}

	/**
	 * 웹포인트 상세 사유코드 - 구매
	 */
	public enum WebPntPchDtlResnCd{
							//구매적립이 없다 TODO bynana.
		 WEBPNT_USE			//포인트사용
        ,WEBPNT_ACCML        // 포인트적립
		,PCH_CANCL_ACCML	//사용취소로 인한 적립
		,PCH_CANCL_DDCT		//사용취소로 인한 차감
	}

	/**
	 * 웹포인트 상세 사유코드 - 이벤트
	 */
	public enum WebPntEvtDtlResnCd{
		GOD_REV				//상품리뷰
		,SNS_REPLY_WRITNG	//SNS댓글작성
		,PROMT_EVT_SNS_CNRS	//기획전/이벤트 SNS공유
		,ATEND_CHK			//출석체크
		,ALL_DRWT     		//전체추첨
		,ALL_PRIZE    		//전체당첨
		,PCH_DRWT     		//구매추첨
		,PCH_PRIZE    		//구매당첨
		,TM_CPN       		//타임쿠폰
		,STMP          		//스탬프
		,MBR_JOIN 			//회원가입
		,EMP_CRTFC_FIRST_PCH	//임직원 인증 첫 구매
		,LOGIN				//로그인
	}

	/**
	 * 웹포인트 상세 사유코드 - 관리자조정
	 */
	public enum WebPntAdminDtlResnCd{
		ADMIN_ADIT	//관리자 추가
		,ADMIN_DDCT	//관리자 차감
	}

	/**
	 * 웹포인트 상태코드
	 */
	public enum WebPntStatCd{
		ACCML_PREARNGE	//적립예정
		,ACCML_DCSN		//적립확정
		,ACCML_CNCL		//적립취소
		,USE			//사용
		,USE_CNCL		//사용취소
		,EXTSH			//소멸
		,ACCML_DDCT		//적립차감
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
	private WebPointEnum(final String value) {
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
