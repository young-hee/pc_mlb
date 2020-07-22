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
package com.plgrim.ncp.biz.callcenter.result;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractDTO;
import com.plgrim.ncp.base.abstracts.AbstractResult;
import com.plgrim.ncp.base.entities.datasource1.sample.Dept;
import com.plgrim.ncp.base.entities.datasource1.sample.Emp;

/**
 * 1:1 문의 게시판 리스트 조회 dto
 *
 * @author 
 * @since 2015. 3. 25
 */

@Data
@EqualsAndHashCode(callSuper = false)
public class MtmListResult extends AbstractResult {
	/**
	 * 
	 */
    private static final long serialVersionUID = 1L;


    private String mtmInqSn;
    private String mtmInqAnsTurn;
    private String mtmInqSnTurn;
    private String inqTpCdNm;
    private String inqSj;
    private String inqMbrNo;
    private String mbrId;
    private String mbrNm;
    private String mbrNo;
    private String ordNo;
    private String godNo;
    private String inqDt;
    private String ansStatCd;
    private String ansStatCdNm;
    private String ansDt;
    private String ansAdminNm;
    private String cnsltStatCd;
    private String cnsltStatNm;
    private String cnsltDetailStatCdNm;
    private String prcsComptDt;
    private String udterId;
    private String udterNm;
    private String udtDt;
    private String dvcCdNm;
    private String langCdNm;
    private String mallNm;
    private String ansDelayDate ;
    private String ansDelayHour;
    private String ansDelayMin;
    private String partmalSectCd;
    private String ansReqDt;
    private String updateHistory;
    private String ansEvlCd;
    private String ansEvlCdNm;
    private String ansEvlCont;
    private String ansEvlAdminAns;

    private String ansReqWorkDt;
    private String inqReqDt;
    private String inqReqWorkDt;
    
    private String ansDscnttTpCd;
    private String ansDscnttTpCdNm;
    /**  회원 등급. */
    private String onlneGrdNm;
    /*상품문의*/
    private String erpGodNo;
    private String inqGodNo;
}
