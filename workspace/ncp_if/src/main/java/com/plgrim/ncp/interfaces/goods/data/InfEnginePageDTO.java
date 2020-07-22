package com.plgrim.ncp.interfaces.goods.data;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class InfEnginePageDTO {
	
	/**
	 * EP 대상 몰
	 */
	private String mallId;

	/**
	 * EP 대상 브랜드
	 */
	private String brndId;
}
