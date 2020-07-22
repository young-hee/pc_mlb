package com.plgrim.ncp.biz.display.result;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractResult;
@Data
@EqualsAndHashCode(callSuper=false)
public class DspCtgry1dFoResult extends AbstractResult {
	/**
	 * 
	 */
    private static final long serialVersionUID = 6473817802680180296L;

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
    /** 대카테고리 기본 정보 */
    DspCtgryFoResult dspCtgryInfo;
    /** 전시카테고리 2DPTH 목록 */
    List<DspCtgry2dFoResult> subDspCtgryList;
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
