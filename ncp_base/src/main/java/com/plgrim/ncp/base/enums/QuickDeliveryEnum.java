package com.plgrim.ncp.base.enums;

public enum QuickDeliveryEnum { 
	
	YES("Y"),NO("N");
	
	//	자사 업체 코드
	public static final String MCOM_COM_ID = "M00";	
	public static final String MCOM_COM_NM = "플그림";
	
   /**
	 * 운영 일 구분 코드
	 * 
	 * 평일 : WKDY
	 * 토요일 : SAT
	 * 일요일 : SUN
	 */
	public enum OperDaySectCd{
		WKDY, SAT, SUN, DSP_ALL
	}
	
	/**
	 * 노출 화면 구분 코드
	 * 
	 * 상품/장바구니 : GOD_BSK
	 * 주문완료 : ORD_COMPT
	 */
	public enum ExpsrScrinSect{
		GOD_BSK, ORD_COMPT
	}
	
	/**
	 * 운영 시간 구분 코드
	 * 
	 * 운영 시간 : OPER_TIME
	 * 미운영 시간 : NONOPER_TIME
	 */
	public enum OperTimeSect{
		OPER_TIME, NONOPER_TIME
	}
	
	
	private final String text;

	private QuickDeliveryEnum(final String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return text;
	}
}
