package com.plgrim.ncp.biz.display.data;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractDTO;
import com.plgrim.ncp.biz.display.result.DspCtgryFoResult;

@Data
@EqualsAndHashCode(callSuper = false)
public class GnbResultFoDTO extends AbstractDTO {/**
	 * 
	 */
    private static final long serialVersionUID = -1388465928970701850L;

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
    List<DspCtgryFoResult> dspCtgryList;
    List<DspCtgryFoResult> outLetCtgryList;
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
