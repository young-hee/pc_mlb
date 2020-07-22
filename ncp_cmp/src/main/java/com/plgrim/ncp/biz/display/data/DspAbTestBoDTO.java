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
public class DspAbTestBoDTO extends AbstractDTO {
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	String pageNo;
	
	/** The dsp AbTest. */
	DspRev dspAbTest;

	/** The ar AbTest sn. */
	String[] arAbTestSn;
	
	/** The sc AbTest sn. */
	String scAbTestSn;

	/** 검색 진행화면명 */
	String[] arAbTestView;
	
	/** 검색 진행화면명 */
	String scAbTestView;

	/** 검색 진행상태 */
	String scAbTestStat;
	
	/** 검색 진행메뉴 */
	String scAbTestMenu;

	/** 검색 AB 테스트명*/
	String scAbTestNm;

	String scStartDate;

	String scEndDate;

	String scDvcSectCd;
	
	String scSetExpsrMenmthdCd;
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
	public String[] getArAbTestSn() {
		
		if(scAbTestSn==null||"".equals(scAbTestSn)) {
			this.arAbTestSn = null;
		}
		else {
			this.arAbTestSn = scAbTestSn.split("\r\n");			
		}
		return this.arAbTestSn;
	}
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */

}
