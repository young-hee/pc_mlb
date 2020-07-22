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

@Data
@EqualsAndHashCode(callSuper=false)
public class MtmInqInfoResult extends AbstractResult {
	
	// ~ Instance fields. ~~~~~~~~~~~~~~
	
	// ~ Constructors. ~~~~~~~~~~~~~~~~~
	
	// ~ Getter and Setter. ~~~~~~~~~~~~

	private String mtmInqSn;
	private String inqDt;
	private String inqTpCd;
	private String inqTpNm;
	private String inqDetailTpCd;
	private String inqSj;
	private String inqCont;
	private String ansStatCd;
	private String ansStatNm;
	private String cnsltStatCd;
	private String cnsltStatNm;
	private String inqCstmrNm;
	private String cstmrSmsRecptnYn;
	private String cstmrEmailRecptYn;
	private String cstmrEmail;
	private String cstmrmobilNationNo;
	private String cstmrmobilAreaNo;
	private String cstmrmobilTlofNo;
	private String cstmrmobilTlofWthnNo;
	private String mallId;
	
	private String ansDscnttTpCd;
	private String ansDscnttTpCdNm;
	private String ansDscnttDetailCont;
	
	private String ansEvlCd;
	private String ansEvlCdNm;
	private String ansEvlCont;
	private String ansEvlAdminAnsCont;

	private String ansCont;
	private String langCd;
	private String ansDt;
}
