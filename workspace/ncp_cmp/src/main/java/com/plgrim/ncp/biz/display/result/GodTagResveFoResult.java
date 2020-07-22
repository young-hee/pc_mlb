package com.plgrim.ncp.biz.display.result;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractResult;
import com.plgrim.ncp.base.entities.datasource1.god.GodLangbyTagResve;
import com.plgrim.ncp.base.entities.datasource1.god.GodTagResve;
@Data
@EqualsAndHashCode(callSuper=false)
public class GodTagResveFoResult extends AbstractResult {

	/**
	 * 
	 */
    private static final long serialVersionUID = 2898288357379780493L;

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
    String godNo;
    GodTagResve godTagResve;
    GodLangbyTagResve godLangbyTagResve;
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
