package com.plgrim.ncp.biz.display.result;

import com.plgrim.ncp.base.abstracts.AbstractResult;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspCnr;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspPromt;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspPromtLang;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspTmplat;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class DspPlanTmplatFoResult  extends AbstractResult{
	private static final long serialVersionUID = -8069759853878355906L;

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	/** 전시 기획전 엔티티 */
	private DspPromt dspPromt;
	
	/** 전시 기획전 언어 엔티티 */
	private DspPromtLang dspPromtLang;
	
	/** 전시 템플릿 정보*/
	private DspTmplat dspTmplat;
		
	/** 전시 코너 정보*/
	private DspCnr dspCnr;
	
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
