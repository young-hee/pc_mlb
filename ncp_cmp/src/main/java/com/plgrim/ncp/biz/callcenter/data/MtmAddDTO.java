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

import com.plgrim.ncp.base.entities.datasource1.cso.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractDTO;
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
public class MtmAddDTO extends AbstractDTO {
	/**
	 * 
	 */
    private static final long serialVersionUID = 2989475890583474852L;

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
    /**
     * 1:1 상담 번호
     */
    private String mtmInqSn;
    /**
     * 답변 상태 코드
     */
    private String ansStatCd;
    /**
     * 상담 상태 코드
     */
    private String cnsltStatCd;
    /**
     * 상담 이관 등록 여부
     */
    private String transSaveYn;
    /**
     * 약속콜 등록 여부
     */
    private String promiseCallSaveYn;
    private String chrgJobTpCd;
    private String transRequstTpCd;

    private String[] cnsltJobTpCd;

    /**
     * 1:1 문의 답변 내용
     */
    private String ansCont;

    /**
     * 답변 대기 체크 여부
     */
    private String checkAnsWait;

    /**
     * 약속콜 전화번호
     */
    private String csoPromsclTel;

    /**
     * 주문 정보 삭제 여부
     */
    private String orderDeleteYn;

    /**
     *  문의 내용
     */
    private String inqCont;

    /**
     *  회원 ID
     */
    private String mbrId;

    private String inqCstmrNm;
    private String mbrEmail;


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
