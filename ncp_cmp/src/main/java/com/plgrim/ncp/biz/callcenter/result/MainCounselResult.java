/* Copyright (c) 2015 plgrim, Inc.
 * All right reserved.
 * http://plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 *
 * Revision History
 * Author              Date                         Description
 * ------------------   --------------                  ------------------
 * beyondj2ee			2015.02.09         
 */
package com.plgrim.ncp.biz.callcenter.result;

import com.plgrim.ncp.base.abstracts.AbstractResult;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper=false)
public class MainCounselResult extends AbstractResult {
	
	// ~ Instance fields. ~~~~~~~~~~~~~~
	
	// ~ Constructors. ~~~~~~~~~~~~~~~~~
	
	// ~ Getter and Setter. ~~~~~~~~~~~~
	
	/**
	 * 
	 */
    private static final long serialVersionUID = 1L;

	private String cnsltSn;
	private String cstmrnm;
	private String cnsltDetailTurn;
	private String inqTpCd;
	private String inqTpNm;
	private String inqDetailTpCd;
	private String inqDetailTpNm;
	private String cnsltReqstMbrNo;
	private String cnsltReqstMbrNm;
	private String cnsltStatCd;
	private String cnsltStatNm;
	private String regtrId;
	private String regtrNm;
	private String regDt;
	private String udterId;
	private String udterNm;
	private String udtDt;
	private String cnsltDetailStatCd;
	private String cnsltDetailStatNm;
	private String cnsltDetailCont;
	private String godNm;
	private String godNo;
	private String ordNo;
	
	private String cnsltDt;
	private String clTpCd;
	private String clTpNm;
	private String telnumber;
	
	private String ibSum;
	private String obSum;

	private String waitSum;
	private String comptSum;

	private String cnsltWaitSum;
	private String cnsltComptSum;
	private String transferWaitSum;
	private String tramsferComptSum;

	private String inqCd;
	private String godInqSn;
	private String mtmInqSn;

}
