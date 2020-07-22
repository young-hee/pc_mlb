package com.plgrim.ncp.biz.display.result;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractResult;
import com.plgrim.ncp.base.entities.datasource1.god.GodIcon;
import com.plgrim.ncp.base.entities.datasource1.god.GodIconCnnc;
@Data
@EqualsAndHashCode(callSuper=false)
public class GodIconFoResult extends AbstractResult {
	/**
	 * 
	 */
    private static final long serialVersionUID = 7393628529147815373L;

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
    /** 상품 연결 상품아이콘 정보 */
    GodIconCnnc godIconCnnc;
    /** 상품 아이콘 정보 */
    GodIcon godIcon;
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
