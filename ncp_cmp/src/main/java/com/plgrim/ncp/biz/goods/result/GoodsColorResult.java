package com.plgrim.ncp.biz.goods.result;

import com.plgrim.ncp.base.entities.datasource1.dsp.DspGodPrc;
import com.plgrim.ncp.base.entities.datasource1.god.God;
import com.plgrim.ncp.base.entities.datasource1.god.GodCnncGrpGod;
import com.plgrim.ncp.base.entities.datasource1.god.GodImg;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class GoodsColorResult extends GoodsResult{

	/**
	 * 
	 */
    private static final long serialVersionUID = -9136782567820721118L;

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	private God god;
	
	private DspGodPrc dspGodPrc;
	
	private GodImg godImg;
	
	private String colorGrpNm;

	private GodCnncGrpGod godCnncGrpGod;
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
