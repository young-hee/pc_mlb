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
import com.plgrim.ncp.base.entities.datasource1.dsp.DspCnr;

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
 * @since 2015. 3. 13
 */

@Data
@EqualsAndHashCode(callSuper = false)
public class DspCnrBoDTO extends AbstractDTO {
	
	

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	String pageNo;
	
	/** The dsp cnr. */
	DspCnr dspCnr;

	/** The ar cnr sn. */
	String[] arCnrSn;
	
	/** The sc cnr sn. */
	String scCnrSn;
	
	List<String> scUseYn;
	
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
	public String[] getArCnrSn() {
		
		if(scCnrSn==null||"".equals(scCnrSn)) {
			this.arCnrSn = null;
		}
		else {
			this.arCnrSn = scCnrSn.split("\r\n");			
		}
		return this.arCnrSn;
	}
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */

}
