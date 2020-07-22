package com.plgrim.ncp.framework.systems;

import lombok.Getter;

/**
 * 시스템 관리를 Swim Lane 격리 방식으로 하고 있기 때문에 각 Lane을 구분할 필요가 있다
 * 
 * @author narusas
 *
 */
public enum Lane {
	LANE1(1), LANE2(2);

	@Getter
	private int no;

	private Lane(int no) {
		this.no = no;
	}

	public static Lane decideLane(Stage stage, WasInstance wasInstance) {
		if (stage == Stage.LOCAL || stage == Stage.STAGING) {
			return Lane.LANE1;
		}
		if (stage == Stage.STAGING2) {
			return Lane.LANE2;
		}

		// 운영에서는 M1,M3이 LANE1 M2,M4가 LANE2임
		if (wasInstance.getNo() % 2 == 1) {
			return Lane.LANE2;
		} else {
			return Lane.LANE1;
		}
	}
}
