package com.plgrim.ncp.biz.display.data;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractDTO;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspCtgry;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspCtgryLang;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspPromt;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspPromtLang;
import com.plgrim.ncp.biz.display.result.DspCnrFoResult;
import com.plgrim.ncp.biz.display.result.DspCtgryFoResult;
import com.plgrim.ncp.biz.display.result.DspPlanFoResult;

@Data
@EqualsAndHashCode(callSuper = false)
public class DspPlanResultFoDTO extends AbstractDTO{

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	private static final long serialVersionUID = 1L;
	
	/** 전시 기획전 엔티티 */
	DspPromt dspPromt;
	/** 전시 기획전 언어 엔티티 */
	DspPromtLang dspPromtLang;
	
	/** 전시 기획전 리스트 */
	List<DspPlanFoResult> dspPlanList;
	
	/** 전시 기획전 카테고리별 카운트 리스트 */
	List<DspPlanFoResult> dspPlanCtgryCntList;
	
	/** 전시 기획전 브랜드별 카운트 리스트 */
	List<DspPlanFoResult> dspPlanBrndCntList;
	
	/** 전시 카테고리 엔티티 */
	DspCtgry dspCtgry;
	/** 전시 카테고리 언어 엔티티 */
	DspCtgryLang dspCtgryLang;
	
	private List<DspCnrFoResult> cornerList;
	
	private DspCtgryFoResult ctgryInfo;
	
	private long total;
	
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
