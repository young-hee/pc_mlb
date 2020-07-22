package com.plgrim.ncp.base.entities.datasource1.god;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.entities.datasource1.dsp.DspCtgry;

@Data
@EqualsAndHashCode(callSuper = false)
public class GodDspCtgryExtend extends DspCtgry{
	private static final long serialVersionUID = -1936885533382899685L;

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	/**
	 * 상품번호
	 */
	private String godNo;
	
	/**
	 * 전시카테고리경로
	 */
	private String dspCtgryPath;
	
	/**
	 * 전시상품 순번
	 */
	private String godSortSeq;	
	
	/**
	 * 카테고리구분코드
	 */
	private String ctgrySectCd;

	/**
	 * 전시여부
	 */
	private String dspYn;

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
