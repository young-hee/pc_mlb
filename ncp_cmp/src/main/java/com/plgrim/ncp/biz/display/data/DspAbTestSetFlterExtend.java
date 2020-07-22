/* Copyright (c) 2015 plgrim, Inc.
 * All right reserved.
 * http://plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 *
 * ------------------------------------------------------------------------
 * @author      shsunhee.kim
 * @since       2015. 5. 7       
 */
package com.plgrim.ncp.biz.display.data;

import org.apache.ibatis.type.Alias;

import com.plgrim.ncp.base.entities.datasource1.dsp.DspAbTestSetFlter;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Alias("dspAbTestSetFlterExtend")
public class DspAbTestSetFlterExtend extends DspAbTestSetFlter {

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	/**
	 * 
	 */
	private static final long serialVersionUID = 1350961687776881360L;
	
	String inOrdBegDt;
	
	String inOrdEndDt;
	
	String flterCdNm;
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
