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

import java.util.List;

import com.plgrim.ncp.base.abstracts.AbstractDTO;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspCnrConttDspTgt;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class DspCnrConttDspTgtBoDTO extends AbstractDTO {

	/**
	 * 
	 */
    private static final long serialVersionUID = 7342842921787798528L;

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
    Long scRevSn;

	Long scCnrSn;
	
	Long scCnrSetSn;
	
	List<Integer> scConttTurn;
	
	List<String> arLangCd;
	
	List<String> arDvcCd;
	
	List<String> arTgtMbrAtrbCd;
	
	List<String> arTgtMbrTpCd;
	
	String tgtGrpco;
	
	DspCnrConttDspTgt dspCnrConttDspTgt;
	
	List<DspCnrConttDspTgtBoDTO> grpCoList;
	
	String scConttTpCd;

	/** The sc dsp ctgry no. 카테고리 번호*/
	String scDspCtgryNo;

	String scDspBrndId;

	/** The sc promt sn. 기획전 번호*/
	String scPromtSn;

	/** The sc evt no. 이벤트 번호 */
	String scEvtNo;

	/** The sc strend sn. S-Trend 번호 */
	String scStrndSn;

	String scTmplatSn;


	/**** A/B 테스트 변경차수 저장을 위한 변수 */
	String scAbTestSn;

	String scAbTestSetTurn;

	String scAbTestModTurn;

	String scModResnCont;

	/** ab test 변경 end 날짜  */
	String abTestEndDt;
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
