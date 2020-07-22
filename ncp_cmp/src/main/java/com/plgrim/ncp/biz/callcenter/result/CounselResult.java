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
public class CounselResult extends AbstractResult {
	
	// ~ Instance fields. ~~~~~~~~~~~~~~
	
	// ~ Constructors. ~~~~~~~~~~~~~~~~~
	
	// ~ Getter and Setter. ~~~~~~~~~~~~
	
	
    private static final long serialVersionUID = 1L;

    private String cnsltSnTurn;

    private String cnsltSn;         
    
    private String cstmrnm;        
    
    private String cnsltJobTpCd;

    private String cnsltJobTpNm;
    
    private String cnsltKndCd;

    private String cnsltKndNm;

    private String rceptCallSectCd;

    private String rceptCallSectNm;

    private String inqTpCd;

    private String inqTpNm;

    private String inqDetailCd;

    private String inqDetailNm;

    private String maxTurn;         
    
    private String cnsltStatCd;

    private String cnsltStatNm;

    private String cnsltDetailStatCd;

    private String cnsltLangCd;

    private String cnsltLangNm;
    
    private String cstmrClmCd;

    private String cstmrClmNm;
    
    private String regtrId;     

    private String regDt;
    
    private String udterId;         
    
    private String udtDt;           
    
    private String ordGodSectCd;

    private String ordGodSectNm;
    
    private String godNo;           
    
    private String ordNo;           
    
    private String ordGodTurn;      
    
    private String cnsltDetailTurn; 
    
    private String transTpCd;

    private String transTpNm;
    
    private String transComId;      
    
    private String transRecrId;     
    
    private String transRqesterId;  
    
    private String transUdterId;    
    
    private String transUdterDt;    
    
    private String promsclConfigYn; 
    
    private String promsclDt;
    
    private String cnsltReqstMbrNo;
    
    private String mbrNo;
    
    private String mbrStatCd;

    private String mbrStatNm;
    
    private String mbrTpCd;

    private String mbrTpNm;
    
    private String mbrAtrbCd;

    private String mbrAtrbNm;
    
    private String mbrNm;
    
    private String mbrId;
    
    private String godNm;
    
    private String cnsltDt;
    
    private String inqDetailTpCd;

    private String inqDetailTpNm;
    
    private String transStatCd;

    private String transTgtCd;

    private String cnsltTgtCd;

    private String cnsltTgtNm;
    
    private String promsclStat;

    private String transRequstTpCd;

    private String cnsltDetailCont;
    
    private String ansCont;
    
    private String ansCnfirmYn;
    
    private String ansStatCd;

    private String ansStatNm;

    private String regtrNm;

    private String cnsltOrdGodTurn;

    private String cnsltDetailStatNm;

    private String transTgtNm;

    private String transRequstTpNm;

    private String transStatNm;

    private String cd;

    private String cdNm;
    
    private String adminId;
    
    private String adminNm;
    
    private String deptNm;
    
    private String chrgJobTpCd;

    private String langCd;

    private String langNm;

    private String mallId;

    private String mallNm;

    private String dvcCd;

    private String dvcNm;

    private String cnsltPrcsComptDt;

    private String chrgJobTpNm;

    private String transComNm;

    private String totalNm;

    private String promsclStatDt;

    private String partmalSectCd;

    private String updateHistory;

    private String ansEvlCd;
    private String ansEvlCdNm;
    private String ansEvlCont;
    private String ansEvlAdminAns;

    private String chatTpCdNm; // 시나리오 채팅 구분

    /**  회원 등급. */
    private String onlneGrdNm;
}
