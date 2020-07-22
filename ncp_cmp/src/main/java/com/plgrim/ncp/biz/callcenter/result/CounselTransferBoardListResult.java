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
 * Henry				2015.04.09         
 */
package com.plgrim.ncp.biz.callcenter.result;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractResult;

@Data
@EqualsAndHashCode(callSuper=false)
public class CounselTransferBoardListResult extends AbstractResult {
	/**
	 * 
	 */
    private static final long serialVersionUID = 9113553783478319970L;
	
	// ~ Instance fields. ~~~~~~~~~~~~~~
	
	// ~ Constructors. ~~~~~~~~~~~~~~~~~
	
	// ~ Getter and Setter. ~~~~~~~~~~~~
    
    
    private String cnsltSn;
    private String transRequstDt;
    private String transStatNm;
    private String transRequstTpNm;
    private String doiNm;
    private String deptNm;
    private String transRecrNm;
    private String ordNo;
    private String godNo;
    private String partmalSectCd;
    private String requstCont;
    private String transDelay;
    private String transComptDt;
    private String ansCont;
    private String transRqesterNm;
    private String cnsltReqstMbrNm;
    private String cnsltDetailTurn;
    private String langNm;
    private String mallNm;
	
    private String requstSn;
    private String transStatCd;
    private String chrgJobTpCd;
    private String transRequstTpCd;
    private String regAdminId;
    private String regAdminNm;
    private String requstRceptAdminId;
    private String requstRceptAdminNm;
    private String requstSj;

    private String transRequstTurn;
    private String regtrId;
    private String regDt;
    private String udterId;
    private String udtDt;
    private String jobRequstAnsTurn;
    private String ansSj;
    private String ansDt;
    private String ansAdminId;
    private String ansAdminNm;
    private String requstOrdGodTurn;
    private String godNm;
    private String ordGodSectCd;
    private String ordGodTurn;
    private String requstAtchmnflTurn;
    private String requstAtchFileNm;
    private String requstAtchFileUrl;
    private String requstAtchFileCpcty;
    private String requstAnsAtchmnflTurn;
    private String requstAnsAtchFileNm;
    private String requstAnsAtchFileUrl;
    private String requstAnsAtchFileCpcty;
	
}
