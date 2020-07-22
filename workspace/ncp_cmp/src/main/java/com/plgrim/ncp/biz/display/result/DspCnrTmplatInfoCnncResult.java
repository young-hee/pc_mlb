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
 * @since       2015. 4. 8       
 */
package com.plgrim.ncp.biz.display.result;


import com.plgrim.ncp.base.abstracts.AbstractResult;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspCnr;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspCnrTmplatInfoCnnc;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 템플릿 연결 코너 정보 Result Class
 * 
 * <p>
 * 
 * <ul>
 *   <li> 
 *   <li> 
 * </ul>.
 *
 * @author shsunhee.kim
 * @since 2015. 3. 19
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class DspCnrTmplatInfoCnncResult extends AbstractResult {
    private static final long serialVersionUID = -7090185550383122493L;

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
    /** The dsp cnr tmplat info. */
	DspCnrTmplatInfoCnnc dspCnrTmplatInfoCnnc;
	
	Integer cnt;

	DspCnr dspCnr;
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
