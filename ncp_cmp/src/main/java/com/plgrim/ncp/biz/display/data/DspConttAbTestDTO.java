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
import com.plgrim.ncp.base.entities.datasource1.dsp.DspAbTest;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspAbTestRev;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspRev;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspRevCpst;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class DspConttAbTestDTO extends AbstractDTO {

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1350961687776881360L;

	/** The sc dsp ctgry no. 카테고리 번호*/
	String scDspCtgryNo;
	
	/** The sc promt sn. 기획전 번호*/
	String scPromtSn;
	
	/** The sc evt no. 이벤트 번호 */
	String scEvtNo;
	
	/** The sc strend sn. S-Trend 번호 */
	String scStrndSn;
	
	String scDspBrndId;
	
	String scAbTestSn;
	
	//전시기간
	String inBegDt;
	
	String inEndDt;
	
	Long scRevSn;
	
	Integer scRevCpstTurn;
	
	String scDvcSectCd;
	
	DspAbTest dspAbTest;
	
	List<DspAbTestRev> dspAbTestRevList;
	
	List<DspRev> dspRevList;
	
	List<DspRevCpst> dspRevCpstList;
	
	List<DspAbTestExtDTO> abTestExtList;
	
	List<DspAbTestSetExtend> dspAbTestSetList;
	
	List<DspAbTestSetFlterExtend> dspAbTestSetFlterList;
	
	String flterGrpList;
	
	String strCountBegDt;
	String strCountEndDt;
	
	String strAmtBegDt;
	String strAmtEndDt;
	
	String curSetExpsrMenmthdCd;
	
	
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
