package com.plgrim.ncp.biz.display.result;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractResult;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspCnrSet;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspCnrTpGrp;
@Data
@EqualsAndHashCode(callSuper=false)
public class DspCnrTpGrpResult extends AbstractResult {
	/**
	 * 
	 */
    private static final long serialVersionUID = -8943441439342728120L;

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
    DspCnrSet dspCnrSet;
    DspCnrTpGrp dspCnrTpGrp;
    List<DspCnrConttFoResult> dspCnrCottList;
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
