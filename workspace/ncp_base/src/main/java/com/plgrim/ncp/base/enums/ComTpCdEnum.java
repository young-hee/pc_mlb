package com.plgrim.ncp.base.enums;

public enum ComTpCdEnum {
	
	// CONSTANTS
	AFF_SELR("제휴사"),
	PARTMAL_COM("입점업체"),
	DLV_COM("배송업체"),
	AFF_AD("광고제휴사"),
	POTOGRF_COM("촬영업체"),
	MCOM("자사"),
	AFF_AGNC("제휴대행사"),
	GOD_PACKING_COM("상품포장업체");

	
	/**
	 * 업체 유형
	 */


	private final String value;

	private ComTpCdEnum(final String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return value;
	}
}
