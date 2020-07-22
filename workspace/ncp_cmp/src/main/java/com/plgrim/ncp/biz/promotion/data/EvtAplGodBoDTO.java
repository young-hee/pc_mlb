package com.plgrim.ncp.biz.promotion.data;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractDTO;
import com.plgrim.ncp.base.entities.datasource1.evt.Evt;
import com.plgrim.ncp.base.entities.datasource1.evt.EvtAplGod;

/**
 * 이벤트 적용상품 Bo DTO
 * @author js
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class EvtAplGodBoDTO extends AbstractDTO  {

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
	 * 이벤트 적용상품
	 */
	private EvtAplGod evtAplGod;
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
