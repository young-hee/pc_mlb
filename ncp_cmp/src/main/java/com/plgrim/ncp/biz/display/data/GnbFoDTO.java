package com.plgrim.ncp.biz.display.data;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractDTO;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspCtgry;
import com.plgrim.ncp.base.entities.datasource1.sys.SysBrnd;
import com.plgrim.ncp.biz.display.result.DspCtgryFoResult;
@Data
@EqualsAndHashCode(callSuper = false)
public class GnbFoDTO extends AbstractDTO {

	/**
	 * 
	 */
    private static final long serialVersionUID = 3875338809945823705L;

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
    /** 1Dpth Display Category 정보 */
    List<DspCtgryFoResult> dspCtgry1DeptList;
    /** OUTLET Category 정보 */
    List<DspCtgry> outletCtgry1DeptList;
    /** BRAND LIST */
    List<SysBrnd> brandList;
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
