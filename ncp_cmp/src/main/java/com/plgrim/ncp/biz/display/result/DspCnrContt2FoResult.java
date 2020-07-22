package com.plgrim.ncp.biz.display.result;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractResult;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspCnrContt;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspCnrConttLang;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspCnrSet;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspCnrSetLang;
@Data
@EqualsAndHashCode(callSuper=false)
public class DspCnrContt2FoResult extends AbstractResult {
	/**
	 * 
	 */
    private static final long serialVersionUID = -7376158934458615283L;

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
    /** 전시코너세트 */
    DspCnrSet dspCnrSet;
    /** 전시코너세트 언어정보 */
    DspCnrSetLang dspCnrSetLang;
    /** 전시코너컨텐츠 */
    DspCnrContt dspCnrContt;
    /** 전시코너컨텐츠언어정보 */
    DspCnrConttLang dspCnrConttLang;
    /** 전시코너 스티커 코드정보 */
    String stkCdNm;
    
    /** 컨텐츠가 상품이면 해당 상품정보를 연결한다. */
    DspCnrConttGodFoResult dspCnrGod;
    /** 컨텐츠가 기획전이면 해당 기획정보를 연결한다. */
    DspCnrConttPromtFoResult dspCnrPromt;
    /** 컨텐츠가 S TRND 이면 해당 S TRND를 연결한다. */
    DspCnrConttStrndFoResult dspCnrStrnd;
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
