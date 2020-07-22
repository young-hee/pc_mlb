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
public class MtmListSearchDTO extends AbstractDTO {

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */


	/**
	 * 
	 */
    private static final long serialVersionUID = -4425784568552289540L;

    //단일검색
    private String ordNo;	//주문번호
    private String mbrId;	//회원ID
    
    private String inqTpCd;			//문의유형
    private String inqDetailCd;		//문의 상세 유형 
    private String cnsltStatCd;		//처리상태
    private String ansStatCd;		//답변상태
    private String cnsltChrgerId;	//상담사id
    //private String cnsltChrgerNm;	// 상담사 이름
    private String ansAdminId;      // 답변자 이름*/
    private String inqSj;	//문의 제목
    private String searchDtType;		//날짜 검색 타입
    private String searchDtStart;		//날짜 검색 시작일
    private String searchDtEnd;		//날짜 검색 종료일
    private String dvcCd;					//경로
    private String ansAfterDt;			//답변경과일시
    
    private String ansEvlCd;		//만족도
    private String ansEvlAdminAns;		//만족도 추가답변 여부
    private String mtmInqSn;				//문의번호
    
    private String langCd;
    private String mallId;
    
    private String ansDelayWorkTm;		//답변경과시간(업무시간기준)
    private String inqDelayWorkTm;		//문의경과시간(업무시간기준)
//    DVC_CD

    private String ansDscnttTpCd;				//불만유형

    /**  회원 등급. */
    private String onlneGrdCd;
    

}
