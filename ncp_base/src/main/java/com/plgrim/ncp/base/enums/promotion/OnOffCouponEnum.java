package com.plgrim.ncp.base.enums.promotion;

public enum OnOffCouponEnum {
	
	/**
	 * 가입축하 쿠폰
	 */
	DXM_TRAVELER_30000("JOIN_30000")
	,DXM_TRAVELER_50000("JOIN_50000")
	,DXM_TRAVELER_100000("JOIN_100000")
	,JOIN_10("JOIN_10")
	,JOIN_5000("JOIN_5000")
	,JOIN_10000("JOIN_10000")
	,JOIN_5("JOIN_5")
	
	/**
	 * 승급 쿠폰
	 */
	,DXM_ADVENTURER_10DIS("GRADE_10")
	,DXM_EXPLORER_15DIS("GRADE_15")
	,DXM_DISCOVERER_20DIS("GRADE_20")
	
	/**
	 * 생일 쿠폰
	 */
	,DXM_TRAVELER_5BIRTH("BIRTH_5")
	,DXM_ADVENTURER_10BIRTH("BIRTH_10")
	,DXM_EXPLORER_15BIRTH("BIRTH_15")
	,DXM_DISCOVERER_20BIRTH("BIRTH_20")
	
	/**
	 * 자녀생일15일전(ERP)
	 */
	,CHILD_BIRTH_10("CHILD_BIRTH_10")
	;
		
	private final String erpCpnId;
	
	private OnOffCouponEnum(final String erpCpnId) {
		this.erpCpnId = erpCpnId;
	}
	
	public String getErpCpnId() {
		return erpCpnId;
	}
}
