package com.plgrim.ncp.biz.display.result;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractResult;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspPromt;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspPromtLang;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspPromtSprtr;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspPromtSprtrLang;


@Data
@EqualsAndHashCode(callSuper=false)
public class DspPlanSprtrFoResult extends AbstractResult{

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	/** 전시 기획전 엔티티 */
	DspPromt dspPromt;
	
	/** 전시 기획전 언어 엔티티 */
	DspPromtLang dspPromtLang;
	
	/** 전시 기획전 구분자정보*/
	private DspPromtSprtr dspPromtSprtr;
	
	/** 전시 기획전 구분자 언어 정보*/
	private DspPromtSprtrLang dspPromtSprtrLang;
	
	private List<DspPlanGodFoResult> godList;
	
	private List<List<DspPlanGodFoResult>> godSetList;
	
	private long totalRow;
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
