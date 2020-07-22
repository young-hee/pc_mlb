package com.plgrim.ncp.biz.display.result;

import java.util.List;
import java.util.Map;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractResult;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspCnrSet;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspCnrSetLang;
@Data
@EqualsAndHashCode(callSuper=false)
public class DspCnrSetFoResult extends AbstractResult {
	/**
	 * 
	 */
    private static final long serialVersionUID = 5489690913358055230L;

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
    /** 전시코너세트 기본정보 */
    DspCnrSet dspCnrSet;
    /** 전시코너세트언어 정보 */
    DspCnrSetLang dspCnrSetLang;
    /** 전시코너세트서브 정보 */
    Map<String,List<DspCnrConttFoResult>> dspCnrConttMap;
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
