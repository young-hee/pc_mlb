package com.plgrim.ncp.framework.systems;

import lombok.Getter;

public enum Language {
	KOREAN("ko"), ENGLISH("en"), CHINESS("cn");

	@Getter
	private String code;

	private Language(String code) {
		this.code = code;
	}
}
