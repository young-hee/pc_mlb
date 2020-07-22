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
public class CsTemplateResult extends AbstractResult {
	
	// ~ Instance fields. ~~~~~~~~~~~~~~
	
	/**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    
	private java.lang.Long cnsltTmplatSn;
	private String mallId;
	private String mallNm;	
	private String langCd;
	private String langNm;	
	private String cnsltTmplatNm;
	private String useYn;
	private String cnsltTmplatKndCd;
	private String cnsltTmplatKndNm;
	private String inqTpCd;
	private String inqTpNm;
	private String inqDetailTpCd;
	private String inqDetailTpNm;
	private String mtmInqAnsTpCd;
	private String mtmInqAnsTpNm;
	private String mtmInqAnsDetailTpCd;
	private String mtmInqAnsDetailTpNm;
	private String transTgtCd;
	private String transTgtNm;
	private String chrgJobTpCd;
	private String chrgJobTpNm;
	private String transRequstTpCd;
	private String transRequstTpNm;
	private String cnsltTmplatSj;
	private String cnsltTmplatCont;
	private String contHtmlYn;
	private String regtrId;
	private String regtrNm;
	private java.util.Date regDt;
	private String udterId;
	private String udterNm;
	private java.util.Date udtDt;
	
	private String cnsltTmplatAnsTp;
	
	// ~ Constructors. ~~~~~~~~~~~~~~~~~
	
	// ~ Getter and Setter. ~~~~~~~~~~~~
	
	
  

}
