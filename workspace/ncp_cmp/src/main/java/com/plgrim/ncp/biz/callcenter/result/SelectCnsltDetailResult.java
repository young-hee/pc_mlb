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
public class SelectCnsltDetailResult extends AbstractResult {
	
	// ~ Instance fields. ~~~~~~~~~~~~~~
	
	// ~ Constructors. ~~~~~~~~~~~~~~~~~
	
	// ~ Getter and Setter. ~~~~~~~~~~~~
	
	private String godInqSn;
	private String ansStatCd;
	private String ansStatNm;
	private String cnsltSn;
	private String cnsltDetailTurn;
	private String cnsltKndCd;
	private String cnsltKndNm;
	private String cnsltTgtCd;
	private String cnsltTgtNm;
	private String inqTpCd;
	private String inqTpNm;
	private String inqDetailTpCd;
	private String inqDetailTpNm;
	private String ccdRegtrId;
	private String ccdRegtrNm;
	private String ccdRegDt;
	private String cnsltDetailStatCd;
	private String cnsltDetailStatNm;
	private String cstmrClmCd;
	private String cstmrClmNm;
	private String cnsltDetailCont;
	private String transTgtCd;
	private String transTgtNm;
	private String transRqesterId;
	private String transRqesterNm;
	private String chrgJobTpCd;
	private String chrgJobtpNm;
	private String transRequstTpCd;
	private String transRequstTpNm;
	private String transStatCd;
	private String transStatNm;
	private String cctRegtrId;
	private String cctRegtrNm;
	private String cctRegDt;
	private String transRequstCont;
	private String promsTgtNm;
	private String promsclDt;
	private String promsclStatCd;
	private String promsclStatNm;
	private String promsclMemoCont;
	private String smsSndCount;
	private String emailSndCount;

}
