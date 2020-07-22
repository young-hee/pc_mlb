package com.plgrim.ncp.biz.display.result;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractResult;
@Data
@EqualsAndHashCode(callSuper=false)
public class DspCtgry2dFoResult extends AbstractResult {
	/**
	 * 
	 */
    private static final long serialVersionUID = 3983731195364216517L;

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
    /** 전시카테고리 2DPTH 기본정보 */
    DspCtgryFoResult dspCtgryInfo;
    /** 하부 전시카테고리 3DPTH 목록 */
    List<DspCtgry3dFoResult> subDsp3dCtgryList;
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
