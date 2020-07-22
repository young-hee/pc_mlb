/* Copyright (c) 2015 plgrim, Inc.
 * All right reserved.
 * http://plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 *
 * ------------------------------------------------------------------------
 * @author      tktaeki.kim
 * @since       2015. 3. 27       
 */
package com.plgrim.ncp.biz.display.result;

import com.plgrim.ncp.base.abstracts.AbstractResult;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspAbTest;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class DspAbTestResult extends AbstractResult {

    private static final long serialVersionUID = -2752248806043804337L;

    /*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	/** Ab Test */
    DspAbTest dspAbTest;
	
	/** 등록자명 */
	String regtrNm;
	
	/** 수정자 명 */
	String udterNm;
	
	/** Ab Test 진행메뉴 명 */
	String abTestMenuNm;
	
	/** AB Test 진행 화면명 */
	String abTestPageNm;
	
	/** AB Test 진행 상태 */
	String abTestStat;
	
	/** 기간 */
	String abTestDate;

	String dspCtgryNo;

	String dspBrndCtgryNo;

	String dspBrndId;

	String promtSn;

	String strndSn;

	String evtNo;

	int modTurn;

	String resultExistYn;

	String dvcSectCd;

	String progrsPsbYn;
	
	String setExpsrMenmthdNm;
	
	/** AB Test 진행 상태 코드 */
	String abTestStatCd;
	
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
