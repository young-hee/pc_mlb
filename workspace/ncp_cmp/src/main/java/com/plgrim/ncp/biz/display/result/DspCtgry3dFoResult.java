package com.plgrim.ncp.biz.display.result;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractResult;

@Data
@EqualsAndHashCode(callSuper=false)
public class DspCtgry3dFoResult extends AbstractResult {

	/**
	 * 
	 */
    private static final long serialVersionUID = -7482937759778573899L;

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
    /** 전시카테고리3DPTH 기본정보 */
    DspCtgryFoResult dspCtgryInfo;
    /** 하부 전시카테고리(Leaf) 목록 */
    List<DspCtgryFoResult> subDsp4dCtgryList;
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
