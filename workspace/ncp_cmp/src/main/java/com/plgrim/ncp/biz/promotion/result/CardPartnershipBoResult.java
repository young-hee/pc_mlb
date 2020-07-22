package com.plgrim.ncp.biz.promotion.result;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractResult;
import com.plgrim.ncp.base.entities.datasource1.prm.Prm;

/**
 * 카드 제휴사 Bo Result
 * @author js
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CardPartnershipBoResult extends AbstractResult {
	/**
	 * UID
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */

	/**
	 * 프로모션
	 */
	Prm prm;

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
