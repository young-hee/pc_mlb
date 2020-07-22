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

import com.plgrim.ncp.base.abstracts.AbstractResult;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 1:1 문의 게시판 리스트 조회 dto
 *
 * @author 
 * @since 2015. 3. 25
 */

@Data
@EqualsAndHashCode(callSuper = false)
public class MtmDetailResult extends AbstractResult {

    /**
	 * 
	 */
    private static final long serialVersionUID = -5140666679508991640L;
    
    
	private String inqCstmrNm;			//문의자 이름
	private String mbrSexSectCd;			//문의 유형
    private String mbrTpCd;					//문의자 회원종류
    private String mbrId;					//문의자 id
    private String mbrNo;					//문의자 회원번호
    private String erpCstmrNo;				//erp고객번호
    private String ordNo;					//주문번호    
    private String mtmInqSn;				//문의번호
    private String mtmRegDt;				//문의일시
    private String cstmrSmsRecptnYn;		//sms 수신여부
    private String cstmrmobilTlofWthnNo;	//문의자 전화번호
    private String cstmrEmailRecptnYn;		//email 수신여부
    private String cstmrEmail;
    private String inqTpCd;				//문의유형
    private String cnsltStatCd;				//처리상태
    private String inqSj;					//제목
    private String inqCont;				//문의내용
    private String ansStatCd;		//답변상태코드
    List<InqOrdGodResult> inqOrdGod;	//문의한 주문의 상품리스트 정보
    

}
