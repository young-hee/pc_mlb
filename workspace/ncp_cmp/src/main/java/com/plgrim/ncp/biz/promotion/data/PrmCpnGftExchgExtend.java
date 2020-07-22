package com.plgrim.ncp.biz.promotion.data;

import com.plgrim.ncp.base.entities.datasource1.prm.PrmCpnGftExchg;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class PrmCpnGftExchgExtend extends PrmCpnGftExchg {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	
	/**
	 * 가능여부 체크
	 *   - 발급가능 : Y
	 *   - 발급불가 : N
	 */
	private String usableCheck;

	/**
	 * 복수개의 쿠폰번호
	 */
	private String exchgbilNoArr;

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
