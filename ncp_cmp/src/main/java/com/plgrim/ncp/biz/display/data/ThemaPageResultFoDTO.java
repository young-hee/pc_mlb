package com.plgrim.ncp.biz.display.data;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractDTO;
import com.plgrim.ncp.biz.display.result.DspCnrFoResult;
import com.plgrim.ncp.biz.display.result.DspCtgryFoResult;
@Data
@EqualsAndHashCode(callSuper = false)
public class ThemaPageResultFoDTO extends AbstractDTO {
	/**
	 * 
	 */
    private static final long serialVersionUID = 5929293134502224272L;

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	/** 전시카테고리 정보 */
	DspCtgryFoResult dspCtgryResult;
	/** 하부 코너목록 */
	List<DspCnrFoResult> dspCnrResultList;
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
