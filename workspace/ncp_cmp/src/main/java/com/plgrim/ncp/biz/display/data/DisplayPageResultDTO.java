package com.plgrim.ncp.biz.display.data;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractDTO;
import com.plgrim.ncp.biz.display.result.DspCnrFoResult;
import com.plgrim.ncp.biz.display.result.DspCtgryFoResult;
@Data
@EqualsAndHashCode(callSuper = false)
public class DisplayPageResultDTO extends AbstractDTO {/**
	 * 
	 */
    private static final long serialVersionUID = 9207553296433131270L;

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
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
