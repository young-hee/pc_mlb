package com.plgrim.ncp.biz.claim.data;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.type.Alias;

@Data
@EqualsAndHashCode(callSuper = false)
@Alias("smartHelperDTO")
public class SmartHelperDTO {

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */

	/**
	 고객번호
	 */
	private String mbrNo;

	/**
	 * 주문 번호
	 */
	private String ordNo;

	/**
	 * 주문 상품 순번
	 */
	private String ordGodTurn;

	/**
	 * 배송 수거지 순번
	 */
	private String dlvPcupspTurn;

	/**
	 * 클레임 유형 코드
	 */
	private String clmTpCd;

	private String startOrdDt;

	private String prmTpCd;

	private String talkYn;
	/*
	 * ---------------------------------------------------------------------
	 * Constructors.
	 * ---------------------------------------------------------------------
	 */

	/*
	 * ---------------------------------------------------------------------
	 * public & interface method.
	 * ---------------------------------------------------------------------
	 */

	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */

}
