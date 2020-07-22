package com.plgrim.ncp.framework.systems;

import org.springframework.core.env.Environment;

import lombok.Getter;

public enum Stage {
	LOCAL("L", "local"), DEV("D", "dev"), STAGING("S", "stg"),STAGING2("S2", "stg2"), PRODUCTION("P", "prod");

	@Getter
	private String symbol;

	@Getter
	private String springProfileName;

	private Stage(String symbol, String springProfileName) {
		this.symbol = symbol;
		this.springProfileName = springProfileName;
	}

	public static Stage decideStage(Environment env) {
		for (Stage stage : values()) {
			if (env.acceptsProfiles(stage.getSpringProfileName())) {
				return stage;
			}
		}
		// 기본값으로 Local을 사용한다.
		return LOCAL;
	}
}
