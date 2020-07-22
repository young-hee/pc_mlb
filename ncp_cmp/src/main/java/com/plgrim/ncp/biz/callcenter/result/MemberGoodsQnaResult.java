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
public class MemberGoodsQnaResult extends AbstractResult {
	
	// ~ Instance fields. ~~~~~~~~~~~~~~
	
	// ~ Constructors. ~~~~~~~~~~~~~~~~~
	
	// ~ Getter and Setter. ~~~~~~~~~~~~
	
	
    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

	private String godInqSn;
    private String dsgnGrpNo;
    private String godNo;
    private String erpGodNo;
    private String inqTpCd;
    private String mbrNo;
    private String inqCstmrNm;
    private String inqCont;
    private String inqDt;
    private String secretsntncYn;
    private String ansStatCd;
    private String inqireCount;
    private String prcsComptDt;
    private String deleteYn;
    private String cstmrEmail;
    private String cstmrEmailRecptnYn;
    private String langCd;
    private String mallId;
    private String dvcCd;
    private String stplatIemAgrYn;
    private String stplatCd;
    private String regtrId;
    private String regDt;
    private String udterId;
    private String udtDt;
    private String detailAnsStatCd;
    private String ansDt;
    private String ansRegtrId;
    private String godNm;
    private String brndId;
    private String cnsltStatCd;
    private String cnsltPrcsComptDt;

    private String brndNm;
    private String godNnm;
    private String inqTpNm;
    private String ansStatNm;
    private String ansDelay;
    private String cnsltChrgerNm;
    private String cnsltStatNm;
    private String updateHistory;
    private String dvcNm;
    private String langNm;
    private String mallNm;
    private String partmalSectCd; 			// 입점,자사 구분 코드
    private String partmalComNm;			// 입점사 명
    private String comId;							// 업체ID
    private String elapseTime;					// 경과시간
    private String ansEvlCd;
    private String ansEvlCdNm;
    private String ansEvlCont;
    private String ansEvlAdminAns;
    private String ansEvlAdminAnsCont;
    
    private String ansDelayWorkTm;
    private String inqDelayWorkTm;
    private String inqDelay;

}
