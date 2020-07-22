package com.plgrim.ncp.biz.display.data;

import java.util.List;

import com.plgrim.ncp.base.entities.datasource1.dsp.DspCnrContt;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class DspCnrConttExtDTO extends DspCnrContt {

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	private static final long serialVersionUID = 1L;
	List<DspCnrConttExt> erList;

	/** 임시저장미리보기 리비전 */
	private Long prvwRevSnPge;
	
	/** A/B테스트 변경 리비전 */
	private Long abTestModRevSnPge;
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
