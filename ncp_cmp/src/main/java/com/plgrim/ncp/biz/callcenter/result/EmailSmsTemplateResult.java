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
public class EmailSmsTemplateResult extends AbstractResult {
	
	// ~ Instance fields. ~~~~~~~~~~~~~~
	
	/**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    
    private java.lang.Long smsEmailTmplatSn;
	private String mallId;
	private String mallNm;	
	private String langCd;
	private String langNm;
	private String smsEmailTmplatNm;
	private String useYn;
	private String smsEmailTmplatKndCd;
	private String smsEmailTmplatKndNm;
	private String smsClfcCd;
	private String smsClfcNm;
	private String smsClfcDetailCd;
	private String smsClfcDetailNm;
	private String emailClfcCd;
	private String emailClfcNm;
	private String emailClfcDetailCd;
	private String emailClfcDetailNm;
	private String smsEmailTmplatSj;
	private String smsEmailTmplatCont;
	private String regtrId;
	private String regtrNm;
	private String regDt;
	private String udterId;
	private String udterNm;
	private String udtDt;
	
	private String smsEmailTp;
	
	// ~ Constructors. ~~~~~~~~~~~~~~~~~
	
	// ~ Getter and Setter. ~~~~~~~~~~~~
	
	
  

}
