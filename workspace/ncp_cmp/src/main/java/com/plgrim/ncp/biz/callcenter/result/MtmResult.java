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

import com.plgrim.ncp.base.abstracts.AbstractResult;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 1:1 문의 게시판 리스트 조회 dto
 *
 * @author 
 * @since 2015. 3. 25
 */

@Data
@EqualsAndHashCode(callSuper = false)
public class MtmResult extends AbstractResult {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    private String mtmInqSn;
    private String inqTpCd;
    private String inqMbrNo;
    private String inqCstmrNm;
    private String inqSj;
    private String inqCont;
    private String inqDt;
    private String ansStatCd;
    private String inqireCount;
    private String prcsComptDt;
    private String deleteYn;
    private String cstmrEmail;
    private String cstmrtelAreaNo;
    private String cstmrtelTlofNo;
    private String cstmrtelTlofWthnNo;
    private String cstmrtelNationNo;
    private String cstmrmobilAreaNo;
    private String cstmrmobilTlofNo;
    private String cstmrmobilTlofWthnNo;
    private String cstmrmobilNationNo;
    private String cstmrSmsRecptnYn;
    private String cstmrEmailRecptnYn;
    private String langCd;
    private String mallId;
    private String dvcCd;
    private String regtrId;
    private String regDt;
    private String udterId;
    private String udtDt;
    
    private String ansEvlCd;
    private String ansEvlCont;
    private String ansEvlWritngDt;
    private String ansEvlAdminAnsCont;

	private String ansDscnttTpCd;
	private String ansDscnttDetailCont;
    

}
