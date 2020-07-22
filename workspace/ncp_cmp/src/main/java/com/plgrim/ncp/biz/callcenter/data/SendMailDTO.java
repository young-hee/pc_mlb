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

import java.util.Date;

import com.plgrim.ncp.base.abstracts.AbstractDTO;
import com.plgrim.ncp.base.entities.datasource1.cso.CsoCnslt;
import com.plgrim.ncp.base.entities.datasource1.cso.CsoCnsltDetail;
import com.plgrim.ncp.base.entities.datasource1.cso.CsoCnsltDetailJobTp;
import com.plgrim.ncp.base.entities.datasource1.cso.CsoCnsltTrans;
import com.plgrim.ncp.base.entities.datasource1.cso.CsoCnsltTransHist;
import com.plgrim.ncp.base.entities.datasource1.cso.CsoCnsltTransOrdGod;
import com.plgrim.ncp.base.entities.datasource1.cso.CsoJobRequst;
import com.plgrim.ncp.base.entities.datasource1.cso.CsoJobRequstOrdGod;
import com.plgrim.ncp.base.entities.datasource1.cso.CsoMtmInq;
import com.plgrim.ncp.base.entities.datasource1.cso.CsoMtmInqAns;
import com.plgrim.ncp.base.entities.datasource1.cso.CsoPromscl;

/**
 * 1:1 문의 게시판 리스트 조회 dto
 *
 * @author 
 * @since 2015. 3. 25
 */

@Data
@EqualsAndHashCode(callSuper = false)
public class SendMailDTO extends AbstractDTO {

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
    /**
     * 받는 사람
     */
    private String name;
    /**
     * 받는 사람 email
     */
    private String email;
    /**
     * 제목
     */
    private String title;
    /**
     * 내용
     */
    private String ansCont;
    
    private String tag1;
    /**
     * 업무구분
     */
    private String gubun;
    
    /**
     *  회원 ID
     */
    private String mbrNo;
    private String mbrId;

    private String inqCstmrNm;
    private String mbrEmail;
    private long orgztInqSn;
    private String mtmInqSn;
    private String godInqSn;
    private String ansConfirmYN;
    private String mallId;
    private String langCd;

    CsoMtmInq csoMtmInq;
    CsoMtmInqAns csoMtmInqAns;
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
    

}
