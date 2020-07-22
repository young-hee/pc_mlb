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
package com.plgrim.ncp.base.enums.member;

/**
 * 
 * [ERP 연동 관련 Enum]
 * 
 * <p>
 * 
 * <ul>
 *   <li> [기능1]
 *   <li> [기능2]
 * </ul>.
 *
 * @author sy59.gim
 * @since 2015. 6. 17
 */
public enum MemberErpEnum {
	
	// CONSTANTS
	YES("Y")
	, NO("N")
	, CONVERT_RESERVE_RATE("10")		// 마일리지 1원 전환시 포인트 비율
	, RESERVE_MIN_UNIT("1")
	, MILEAGE_MIN_UNIT("10")
	, CB_TYPE_10("10"), CB_TYPE_10_KOR("적립")
	, CB_TYPE_11("11"), CB_TYPE_11_KOR("사용")
	, CB_TYPE_12("12"), CB_TYPE_12_KOR("차감")
	, CB_TYPE_20("20"), CB_TYPE_20_KOR("조정")
	, CB_TYPE_21("21"), CB_TYPE_21_KOR("통합")
	, CB_TYPE_22("22"), CB_TYPE_22_KOR("삭제")
	, CB_TYPE_23("23"), CB_TYPE_23_KOR("휴면")
	, CB_TYPE_24("24"), CB_TYPE_24_KOR("이관")
	, CB_TYPE_30("30"), CB_TYPE_30_KOR("이벤트")
	, CB_TYPE_40("40"), CB_TYPE_40_KOR("전환")
	, CB_TYPE_50("50"), CB_TYPE_50_KOR("수선")
	, CB_TYPE_60("60"), CB_TYPE_60_KOR("마일리지전환")
	, CB_TYPE_90("90"), CB_TYPE_90_KOR("초기조정")
	, CB_TYPE_25("25"), CB_TYPE_25_KOR("스크랩")
	, CB_TYPE_26("26"), CB_TYPE_26_KOR("패션피아 상품권")
	, CB_TYPE_27("27"), CB_TYPE_27_KOR("신규회원가입")
	, CB_TYPE_28("28"), CB_TYPE_28_KOR("재구매")
	, CB_TYPE_29("29"), CB_TYPE_29_KOR("매장오픈")
	, CB_TYPE_31("31"), CB_TYPE_31_KOR("고객생일")
	, CB_TYPE_32("32"), CB_TYPE_32_KOR("감사의달")
	
	/**
	 * 2016.03.11 정재민 - BO에 "유형"문구 노출을 위해 추가
	 * 주문,교환출고에 대한 적립/포인트사용 -> "주문"
	 * 취소,반품,교환반품에 대한 적립취소/포인트사용취소 -> "주문취소"
	 */
	, CB_SECTION_CODE_ORDER_KOR("주문")
	, CB_SECTION_CODE_CANCEL_KOR("주문취소(반품)")
	
	/**
	 * ERP return
	 */
	, NEW("C")     //신규
	, SWITCH("M")  //전환
	, UPDATE("U")  //수정
	, SOLAR("S") //양력
	, LUNAR("L") //음력
	
	;

	/**
	 * 포인트 유형명 (ERP CODE).
	 *
	 * @author sy59.gim
	 * @since 2015. 6. 17
	 */
	public enum ReserveCpTypeNm{
		  CB_TYPE_10, CB_TYPE_10_KOR, CB_TYPE_10_ENG, CB_TYPE_10_CHI
		, CB_TYPE_11, CB_TYPE_11_KOR, CB_TYPE_11_ENG, CB_TYPE_11_CHI
		, CB_TYPE_12, CB_TYPE_12_KOR, CB_TYPE_12_ENG, CB_TYPE_12_CHI
		, CB_TYPE_20, CB_TYPE_20_KOR, CB_TYPE_20_ENG, CB_TYPE_20_CHI
		, CB_TYPE_21, CB_TYPE_21_KOR, CB_TYPE_21_ENG, CB_TYPE_21_CHI
		, CB_TYPE_22, CB_TYPE_22_KOR, CB_TYPE_22_ENG, CB_TYPE_22_CHI
		, CB_TYPE_23, CB_TYPE_23_KOR, CB_TYPE_23_ENG, CB_TYPE_23_CHI
		, CB_TYPE_24, CB_TYPE_24_KOR, CB_TYPE_24_ENG, CB_TYPE_24_CHI
		, CB_TYPE_30, CB_TYPE_30_KOR, CB_TYPE_30_ENG, CB_TYPE_30_CHI
		, CB_TYPE_40, CB_TYPE_40_KOR, CB_TYPE_40_ENG, CB_TYPE_40_CHI
		, CB_TYPE_50, CB_TYPE_50_KOR, CB_TYPE_50_ENG, CB_TYPE_50_CHI
		, CB_TYPE_60, CB_TYPE_60_KOR, CB_TYPE_60_ENG, CB_TYPE_60_CHI
		, CB_TYPE_90, CB_TYPE_90_KOR, CB_TYPE_90_ENG, CB_TYPE_90_CHI
		, CB_TYPE_25, CB_TYPE_25_KOR, CB_TYPE_25_ENG, CB_TYPE_25_CHI
		, CB_TYPE_26, CB_TYPE_26_KOR, CB_TYPE_26_ENG, CB_TYPE_26_CHI
		, CB_TYPE_27, CB_TYPE_27_KOR, CB_TYPE_27_ENG, CB_TYPE_27_CHI
		, CB_TYPE_28, CB_TYPE_28_KOR, CB_TYPE_28_ENG, CB_TYPE_28_CHI
		, CB_TYPE_29, CB_TYPE_29_KOR, CB_TYPE_29_ENG, CB_TYPE_29_CHI
		, CB_TYPE_31, CB_TYPE_31_KOR, CB_TYPE_31_ENG, CB_TYPE_31_CHI
		, CB_TYPE_32, CB_TYPE_32_KOR, CB_TYPE_32_ENG, CB_TYPE_32_CHI	    
	}
	
	/**
	 * BO 포인트 조정 구분(ERP CODE).
	 *
	 * @author sy59.gim
	 * @since 2015. 6. 17
	 */
	public enum BoReserveCpType{
		  CB_TYPE_20	    // 조정
		//, CB_TYPE_26        // 패션피아 상품권
		//, CB_TYPE_27        // 신규 회원 가입
		  ,CB_TYPE_30       //이벤트
	}
	
	/**
	 * ERP_CODE - 포인트 조정 구분.
	 *
	 * @author sy59.gim
	 * @since 2015. 6. 17
	 */
	public enum ReserveOrderType{
		  OR	        // -
		, RE	        // +
	}
	
	/**
	 * ERP_CODE - 포인트 통화키.
	 *
	 * @author sy59.gim
	 * @since 2015. 6. 17
	 */
	public enum ReserveWaers{
		KRW
	}

	/**
	 * ERP_CODE - 매장 코드 (B04A > A345로 변경 20150623).
	 *
	 * @author sy59.gim
	 * @since 2015. 6. 17
	 */
	public enum ErpWerks{
//		B04A
		A345
	}
	
	/**
	 * ERP_CODE -.
	 *
	 * @author sy59.gim
	 * @since 2015. 6. 17
	 */
	public enum ErpR3nameR{
		ERP
	}
	
	/**
	 * fsp고객 생성 입력.
	 *
	 * @author sy59.gim
	 * @since 2015. 6. 17
	 */
	public enum ErpIsBp{
		ZSBP0107
	}

	/**
	 * 성공 코드.
	 *
	 * @author sy59.gim
	 * @since 2015. 6. 17
	 */
	public enum ErpSuccess{
		S
		, SUCCESS
	}
	
	/**
	 * 포인트 유형.
	 *
	 * @author sy59.gim
	 * @since 2015. 6. 17
	 */
	public enum MemberPntTp{
		EVT_PNT				// 이벤트 포인트
		, MBSH_PNT				// 멤버쉽 포인트
		, WEBPNT				// P포인트

	}
	
	/**
	 * 포인트 유입 유형
	 * 
	 * GNRL : 일반
	 * FIRST_LOGIN : 첫로그인
	 */
	public enum MemberPointInflowType {
		GNRL, FIRST_LOGIN
	}
	
	/**
	 * 포인트 적용 사유.
	 *
	 * @author sy59.gim
	 * @since 2015. 6. 17
	 */
	public enum MemberPntAplResn{
		  ADMIN_ACCML	        // 관리자 적립
		, ADMIN_DDCT	        // 관리자 차감
		, ORD_ACCML	            // 주문으로 인한 적립 
		, ORD_CNCL_ACCML	    // 주문취소로 인한 적립 
		, ORD_DDCT	            // 주문으로 인한 차감 	     
	}

	/**
	 * 값
	 */
	private final String value;

	/**
	 * 생성자
	 *
	 * @param value [설명]
	 */
	private MemberErpEnum(final String value) {
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
