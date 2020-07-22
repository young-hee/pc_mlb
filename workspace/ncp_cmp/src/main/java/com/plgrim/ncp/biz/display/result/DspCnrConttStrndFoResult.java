package com.plgrim.ncp.biz.display.result;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractResult;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspStrnd;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspStrndLang;
import com.plgrim.ncp.base.entities.datasource1.sys.SysBrnd;
import com.plgrim.ncp.base.entities.datasource1.sys.SysBrndLang;
@Data
@EqualsAndHashCode(callSuper=false)
public class DspCnrConttStrndFoResult extends AbstractResult {

	/**
	 * 
	 */
    private static final long serialVersionUID = -5628596093150956570L;

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
    DspStrnd dspStrnd;
    DspStrndLang dspStrndLang;
    SysBrnd sysBrnd;
    SysBrndLang sysBrndLang;
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
