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
public class DetailMemberGoodsQnaResult extends AbstractResult {
	
	// ~ Instance fields. ~~~~~~~~~~~~~~
	
	// ~ Constructors. ~~~~~~~~~~~~~~~~~
	
	// ~ Getter and Setter. ~~~~~~~~~~~~
	
	
    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    
    private String godInqSn;
    private String godNo;
    private String mbrNo;
    private String inqCstmrNm;
    private String cstmrEmail;
    private String mbrTpCd;
    private String mbrTpNm;
    private String mbrSexSectCd;
    private String mbrId;
    private String mobilAreaNo;
    private String mobilTlofNo;
    private String mobilTlofWthnNo;
    private String godNm;
    private String erpGodNo;
    private String csmrPrc;
    private String secretsntncYn;
    
    private String inqDt;
    private String inqTpCd;
    private String inqTpNm;
    private String ansStatNm;
    private String ansStatCd;
    private String inqCont;

    private String cnsltStatCd;
    private String cnsltStatNm;

    private String langCd;
    private String mallId;
    private String joinMallNm;
    private String mallNm;
    
	private String ansEvlCd;
	private String ansEvlCdNm;
	private String ansEvlCont;
	private String ansEvlAdminAnsCont;

}
