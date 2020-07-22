package com.plgrim.ncp.biz.display.result;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractResult;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspCnrContt;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspCnrConttLang;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspCnrTpGrp;
@Data
@EqualsAndHashCode(callSuper=false)
public class DspCnrConttFoResult extends AbstractResult {
	/**
	 * 
	 */
    private static final long serialVersionUID = 2243028136846611520L;

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
    /** 전시코너유형그룹 정보 */
    DspCnrTpGrp dspCnrTpGrp;
    /** 전시코너컨텐츠 정보 */
    DspCnrContt dspCnrContt;
    /** 전시코너컨텐츠언어 정보 */
    DspCnrConttLang dspCnrConttLang;
    /** 컨텐츠가 상품이면 해당 상품정보를 연결한다. */
    DspCnrConttGodFoResult dspCnrGod;
    /** 컨텐츠가 기획전이면 해당 기획정보를 연결한다. */
    DspCnrConttPromtFoResult dspCnrPromt;
    /** 컨텐츠가 S TRND 이면 해당 S TRND를 연결한다. */
    DspCnrConttStrndFoResult dspCnrStrnd;
    /** 전시상품 스티커 코드정보 */
    String stkCdNm;
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
