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
package com.plgrim.ncp.biz.claim.result;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractResult;

@Data
@EqualsAndHashCode(callSuper=false)
public class rtnClmWrhsGodResult extends AbstractResult {
	
	/**
	 * 
	 */
    private static final long serialVersionUID = 3350141842832443273L;
	
    private String mbrNo                ;
    private String ordGodTurn           ;
    private String ordNo                ;
    private String pkerpNo              ;
    private String groupId              ;
    private String regDt                ;
    private String brndGrpId            ;
    private String brndgrpnm            ;    
    private String erpGodNo             ;
    private String godNo                ;
    private String godNm                ;
    private String itmNo                ;
    private String godTpCd              ;
    private String godtpnm              ;
    private String rtlPrc               ;
    private String payExchgRtCrncyAmt   ;
    private String dlivyDrctqty         ;
    private String dlivydrctcnclqty     ;
    private String rtrvldrctqty         ;
    private String clmQty               ;    
    private String wrkQty               ;    
    
}
