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
public class ReturnBankAccountResult extends AbstractResult {
	
	// ~ Instance fields. ~~~~~~~~~~~~~~
	
	// ~ Constructors. ~~~~~~~~~~~~~~~~~
	
	// ~ Getter and Setter. ~~~~~~~~~~~~
	
	
    private static final long serialVersionUID = -1783731681615312020L;

    private String mbrNo;
    private String rfdBnkAcctTurn;
    private String rfdBnkAcctNm;
    private String rfdBnkAcctNo;
    private String rfdBnkAcctBnkCd;
    private String rfdBnkAcctAcnthldrNm;
    private String rprstRfdBnkAcctYn;
    private String regtrId;
    private String regDt;
    private String udterId;
    private String udtDt;
}
