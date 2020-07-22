/* Copyright (c) 2015 plgrim, Inc.
 * All right reserved.
 * http://plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 *
 * ------------------------------------------------------------------------
 * @author      jwcale.kim
 * @since       2015. 7. 20       
 */
package com.plgrim.ncp.commons.result;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractResult;
import com.plgrim.ncp.base.entities.datasource1.god.God;
import com.plgrim.ncp.base.entities.datasource1.god.GodGodEvl;
import com.plgrim.ncp.base.entities.datasource1.god.GodGodEvlAtchFile;

/**
 * @author ed
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class GodEvlResult extends AbstractResult {

	/**
	 * UID
	 */
    private static final long serialVersionUID = 591797712065396263L;
    
    private GodGodEvl godGodEvl; // 상품평
    private God god; // 상품
    private List<GodGodEvlAtchFile> godGodEvlAtchFile; // 상품평 첨부파일
    
    private Integer no; // 일련번호
    private String avrgScore; // 총평균점수
    private String dlvEvlScoreNm; // 배송평가점수
    private String qltyEvlScoreNm; // 품질평가점수
    private String pkgEvlScoreNm; // 포장평가점수
    private String godEvlTp; // 상품평구분
    private String snsYn; // SNS여부
    private String mbrNm; // 회원명
    private String mbrId; // 회원ID
    private String regDtNm; // 등록일
    private String udtDtNm; // 수정일
    private String udterIdNm; // 수정자명
    private String mbrTpCd; // 회원유형
    private String mbrStatCd;   //회원상태
    private String godEvlTpNm;	//상품평구분명
    private String ntceYnNm;	//게시여부 명
    private Integer accmlWebpntAmt; //적립한 P포인트 금액
    private String webpntRtrvlDt;   //P포인트 회수일시
    private String ordNo;           //주문번호             20160526_주동민_sr#20343 [상품평 관리 화면 조회값 및 컬럼값 추가 요청]
    private String bstGodEvlCndcyYn;//베스트상품평후보여부 20160526_주동민_sr#20343 [상품평 관리 화면 조회값 및 컬럼값 추가 요청]
    private Integer bstGodEvlCnt;   //베스트상품평수       20160526_주동민_sr#20343 [상품평 관리 화면 조회값 및 컬럼값 추가 요청]
    
	/**
	 * 상품평 내용 전체 - Excel 출력용
	 */
	private String godEvlContAll;    
}

