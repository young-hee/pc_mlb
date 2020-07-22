package com.plgrim.ncp.biz.display.result;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractResult;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspCtgry;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspCtgryLang;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspTmplat;

@Data
@EqualsAndHashCode(callSuper=false)
public class DspCtgryFoResult extends AbstractResult {

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	private static final long serialVersionUID = -4012441265850877117L;
	
	/** 전시카테고리 엔티티 */
	DspCtgry dspCtgry;
	/** 전시카테고리 적용 템플릿 엔티티 */
	DspTmplat dspTmplat;
	/** 전시카테고리 언어 엔티티 */
	DspCtgryLang dspCtgryLang;

	/** 전시카테고리 전시 상품 목록 */
	List<DspCtgryGodFoResult> dspCtgryGodList;
	
	/** 브랜드 전시카테고리 상위 전시카테고리 정보 */
	String brandUpperDspCtgryNo;
	
	/** Location Information */
	String locationUrl;
	String locationName;
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
