package com.plgrim.ncp.base.enums;

public enum OlapicEnum {
	DISPLAY_TYPE_MAIN("main")
	, DISPLAY_TYPE_GOODS("god")
	, DISPLAY_TYPE_ORDER("ord")
	, DISPLAY_TYPE_VIEW("view");
	
	private final String value;

	private OlapicEnum(final String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return value;
	}
}
