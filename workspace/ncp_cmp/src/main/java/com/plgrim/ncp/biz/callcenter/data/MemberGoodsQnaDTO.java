/* Copyright (c) 2015 plgrim, Inc.
 * All right reserved.
 * http://plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 *
 * ------------------------------------------------------------------------
 * @author      shhenry.choi
 * @since       2015. 3. 18       
 */
package com.plgrim.ncp.biz.callcenter.data;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractDTO;
import com.plgrim.ncp.base.entities.datasource1.cso.CsoCnslt;
import com.plgrim.ncp.base.entities.datasource1.cso.CsoCnsltDetail;
import com.plgrim.ncp.base.entities.datasource1.cso.CsoCnsltDetailJobTp;
import com.plgrim.ncp.base.entities.datasource1.cso.CsoCnsltTrans;
import com.plgrim.ncp.base.entities.datasource1.cso.CsoCnsltTransHist;
import com.plgrim.ncp.base.entities.datasource1.cso.CsoCnsltTransOrdGod;
import com.plgrim.ncp.base.entities.datasource1.cso.CsoGodInq;
import com.plgrim.ncp.base.entities.datasource1.cso.CsoGodInqAns;
import com.plgrim.ncp.base.entities.datasource1.cso.CsoJobRequst;
import com.plgrim.ncp.base.entities.datasource1.cso.CsoJobRequstOrdGod;
import com.plgrim.ncp.base.entities.datasource1.cso.CsoPromscl;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class MemberGoodsQnaDTO extends AbstractDTO {

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	
	
	
	private long godInqSn = 0;
    private long godAnsTurn = 0;
    //private String ansSj; 안씀
    private String ansCont;
    private String detailAnsStatCd;
    private String ansDt;
    private String ansAdminId;
    private String regtrId;
    private String udterId;
    private String regDt;
    private String udtDt;
    
    private String inqTpCd;
    private String[] cnsltJobTpCd;
    
    private String csoPromsclTel;
    
    private String transSaveYn;
    private String promiseCallSaveYn;

    //답변 대기 상태
    private String checkAnsWait;
    
    CsoGodInq csoGodInq;
    CsoGodInqAns csoGodInqAns;
    CsoCnslt csoCnslt;
    CsoCnsltDetail csoCnsltDetail;
    CsoCnsltDetailJobTp csoCnsltDetailJobTp;
    
    CsoCnsltTrans csoCnsltTrans;
    CsoCnsltTransOrdGod csoCnsltTransOrdGod;
    CsoCnsltTransHist csoCnsltTransHist;
    CsoJobRequst csoJobRequst;
    CsoJobRequstOrdGod csoJobRequstOrdGod;
    
    CsoPromscl csoPromscl;
    private String csoPromsclDate;

    private List<MemberGoodsQnaGridDTO> listCsoGodInq;
    
    
    
    
	
	
	/*
	 * ---------------------------------------------------------------------
	 * Constructors.
	 * ---------------------------------------------------------------------
	 */
	/*
	 * ---------------------------------------------------------------------
	 * public & interface method.
	 * ---------------------------------------------------------------------
	 */
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
    
    
    

}
