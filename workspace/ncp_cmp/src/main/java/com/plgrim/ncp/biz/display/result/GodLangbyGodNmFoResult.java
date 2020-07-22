package com.plgrim.ncp.biz.display.result;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractResult;
import com.plgrim.ncp.base.entities.datasource1.god.GodLangbyGodNm;
import com.plgrim.ncp.base.entities.datasource1.god.GodLangbyTagResve;
@Data
@EqualsAndHashCode(callSuper=false)
public class GodLangbyGodNmFoResult extends AbstractResult {
	/**
	 * 
	 */
    private static final long serialVersionUID = 2044181581717613096L;

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
    /** 상품 언어별 상품명 */
    GodLangbyGodNm godLangbyGodNm;
    /** 상품 언언별 예약 태그 목록 */
    List<GodLangbyTagResve> godLangbyTagResveList;
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
