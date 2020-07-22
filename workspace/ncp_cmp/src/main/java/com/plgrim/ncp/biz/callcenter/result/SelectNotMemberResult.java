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
public class SelectNotMemberResult extends AbstractResult {/**
	 * 
	 */
    private static final long serialVersionUID = 1L;
	
	// ~ Instance fields. ~~~~~~~~~~~~~~
	
	// ~ Constructors. ~~~~~~~~~~~~~~~~~
	
	// ~ Getter and Setter. ~~~~~~~~~~~~
	
	
    private String mbrNm;
    private String mbrEmail;
    private String mobilNationNo;
    private String mobilAreaNo;
    private String mobilTlofNo;
    private String mobilTlofWithNo;
    private String otelNationNo;
    private String otelAreaNo;
    private String otelTlofNo;
    private String otelTlofWithNo;
    private String cnsltSn;
    private String cnsltPrcsComptDt;
    private String regDt;
    
    private String tel;


    private String nmbrCstmrNm;
    private String nmbrEmail;
    private String nmbrMobile;
    private String langNm;
    private String mallNm;


}
