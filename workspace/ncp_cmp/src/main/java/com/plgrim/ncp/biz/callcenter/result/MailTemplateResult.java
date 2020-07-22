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

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractResult;

@Data
@EqualsAndHashCode(callSuper=false)
public class MailTemplateResult extends AbstractResult {
	
	// ~ Instance fields. ~~~~~~~~~~~~~~
	
	/**
	 * 
	 */
    
	private String inqCstmrNm;
	private String cdDscr;	
	private String regDt;
	private String inqCont;	
	private String inqSj;
	private String ansEvlCd;
	private String ansEvlAdminAnsCont;
	private String ansEvlAdminAnsDt;

	List<MailAnsResult> mailAns;
	List<MailImgResult> mailImg; 
	// ~ Constructors. ~~~~~~~~~~~~~~~~~
	
	// ~ Getter and Setter. ~~~~~~~~~~~~
	
	
  

}
