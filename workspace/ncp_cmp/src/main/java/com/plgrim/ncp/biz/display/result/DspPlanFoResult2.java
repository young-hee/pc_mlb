package com.plgrim.ncp.biz.display.result;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractResult;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspPromt;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspPromtLang;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspTmplat;
@Data
@EqualsAndHashCode(callSuper=false)
public class DspPlanFoResult2 extends AbstractResult {
	/**
	 * 
	 */
    private static final long serialVersionUID = 7233618245399498925L;

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
