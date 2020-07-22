package com.plgrim.ncp.biz.display.data;

import java.util.List;
import java.util.Map;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractDTO;
import com.plgrim.ncp.biz.display.result.DspCtgryFoResult;
import com.plgrim.ncp.biz.display.result.DspCtgryGodFoResult;

@Data
@EqualsAndHashCode(callSuper = false)
public class DspCtgryResultFoDTO extends AbstractDTO {

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	private static final long serialVersionUID = -6240523548959887928L;

	/** 전시카테고리 정보 */
	DspCtgryFoResult dspCtgryResult;
	/** 전시 카테고리 2DPTH 정보*/
	DspCtgryFoResult dspCtgryResult2d;
	/** 전시카테고리 로케이션 정보 */
	List<DspCtgryFoResult> dspCtgryLocationList;
	/** 전시카테고리 목록 정보 */
	List<DspCtgryFoResult> dspCtgryResultList;
	/** 전시카테고리 NEW 목록 정보 */
	List<DspCtgryFoResult> dspCtgryResultNewList;

	int dspCtgryGodTotCount;
	/** 전시카테고리 연결 상품 목록 */
	List<DspCtgryGodFoResult> dspCtgryGodList;
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
