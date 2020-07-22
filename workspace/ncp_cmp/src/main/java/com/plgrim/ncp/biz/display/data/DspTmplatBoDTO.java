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
 * @since       2015. 4. 10       
 */
package com.plgrim.ncp.biz.display.data;

import java.util.List;

import com.plgrim.ncp.base.abstracts.AbstractDTO;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspTmplat;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class DspTmplatBoDTO extends AbstractDTO {
	private static final long serialVersionUID = 926118914549973419L;

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	/** The page no. */
	String pageNo;
	
	/** The dsp tmplat. */
	DspTmplat dspTmplat;
	
	/** The ar tmplat sn. */
	String[] arTmplatSn;
	
	/** The sc tmplat sn. */
	String scTmplatSn;
	
	/** The sc use yn. */
	List<String> scUseYn;
	
	/** The sc ctgry tp cd. */
	String scCtgryTpCd;
	
	String scRevSn;

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
	public String[] getArTmplatSn() {
		
		if(scTmplatSn==null||"".equals(scTmplatSn)) {
			this.arTmplatSn = null;
		}
		else {
			this.arTmplatSn = scTmplatSn.split("\r\n");			
		}
		return this.arTmplatSn;
	}
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */

}
