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
package com.plgrim.ncp.biz.display.data;

import java.util.List;

import com.plgrim.ncp.base.abstracts.AbstractDTO;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspRev;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * [클래스 설명]
 * 
 * <p>
 * 
 * <ul>
 *   <li> [기능1]
 *   <li> [기능2]
 * </ul>.
 *
 * @author shsunhee.kim
 * @since 2017. 7. 18
 */

@Data
@EqualsAndHashCode(callSuper = false)
public class DspRevBoDTO extends AbstractDTO {
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	
	/** The dsp rev. */
	DspRev dspRev;

	/** The ar rev sn. */
	String[] arRevSn;
	
	/** The sc rev sn. */
	String scRevSn;
	
	List<String> scUseYn;
	

	
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
	public String[] getArRevSn() {
		
		if(scRevSn==null||"".equals(scRevSn)) {
			this.arRevSn = null;
		}
		else {
			this.arRevSn = scRevSn.split("\r\n");			
		}
		return this.arRevSn;
	}
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */

}
