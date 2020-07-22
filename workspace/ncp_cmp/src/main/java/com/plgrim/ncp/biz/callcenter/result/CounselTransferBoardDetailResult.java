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
public class CounselTransferBoardDetailResult extends AbstractResult {
	/**
	 * 
	 */
    private static final long serialVersionUID = 9113553783478319970L;
	
	// ~ Instance fields. ~~~~~~~~~~~~~~
	
	// ~ Constructors. ~~~~~~~~~~~~~~~~~
	
	// ~ Getter and Setter. ~~~~~~~~~~~~
    
    private String requstSn;
    private String transStatCd;
    private String transStatNm;
    private String chrgJobTpCd;
    private String chrgJobTpNm;
    private String transRequstTpCd;
    private String transRequstTpNm;
    private String regAdminId;
    private String regAdminNm;
    private String requstRceptAdminId;
    private String requstRceptAdminNm;
    private String requstSj;
    private String requstCont;
    private String cnsltSn;
    private String cnsltDetailTurn;
    private String transRequstTurn;
    private String regtrId;
    private String regDt;
    private String udterId;
    private String udtDt;
    private String jobRequstAnsTurn;
    private String ansSj;
    private String ansCont;
    private String ansDt;
    private String ansAdminId;
    private String requstAtchmnflTurn;
    private String requstAtchFileNm;
    private String requstAtchFileUrl;
    private String requstAtchFileCpcty;
    private String requstOrdGodTurn;
    private String godNm;
    private String ordGodSectCd;
    private String godNo;
    private String ordNo;
    private List<CounselTransferBoardOrdGodResult> ordGod;
    private String ordGodTurn;
    private String ansRegtrId;
    private String ansRegtrNm;
    private String ansRegDt;
    private String ansUdterId;
    private String ansUdtDt;
    private String mallId;
    private String mallNm;
    private String langCd;
    private String langNm;
    private List<CounselTransferBoardAtchmnflResult> atchmnfl;
    private List<CounselTransferBoardAnsAtchmnflResult> ansAtchmnfl;
	
}
