package com.plgrim.ncp.biz.promotion.data;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractDTO;
import com.plgrim.ncp.base.entities.datasource1.evt.Evt;
import com.plgrim.ncp.base.entities.datasource1.evt.EvtRelateGod;

/**
 * 연관상품 Bo DTO
 * @author js
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class EvtRelateGodBoDTO extends AbstractDTO  {

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
	 * 이벤트
	 */
	private Evt evt;
	
	/**
	 * 연관상품
	 */
	private EvtRelateGod evtRelateGod;
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
