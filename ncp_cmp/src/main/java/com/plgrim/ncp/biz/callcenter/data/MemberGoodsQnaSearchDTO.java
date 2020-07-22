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

import com.plgrim.ncp.base.abstracts.AbstractDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class MemberGoodsQnaSearchDTO extends AbstractDTO {

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	
	
	private String godInqSn;
	private String ansStatCd; 							//답변상태 
	private String langCd;
	private String mallId; 								// 몰ID
	private String dvcCd;								// 경로
	private String regtrId;								// 상담사 ID
	private java.util.Date regDt;
	private String udterId;
	private java.util.Date udtDt;
	private String searchDateType;						// 기간 검색 유형
	private String searchStartDate;						// 기간 검색 시작 일시
	private String searchEndDate;						// 기간 검색 종료 일시
	private String inqTpCd;								// 문의 유형
	private String inqDetailTpCd;						// 문의 상세 유형
	private String cnsltStatCd;							// 처리 상태
	private String regtrNm;								// 상담사 이름
	private String elapseDay;							// 경과 일시
	private String godNm;								// 상품명
	private String partmalSectCd; 						// 자사,입점사 구분 코드
	private String comId;								// 입점업체 ID

	private String selectGodNoType;
	private String inputGodNo;
	private String inqCstmrId;

	private String mbrNo;
	private String mobilNo;
	private String mbrEmail;

	private String dinitFlag;							//데쉬보드FLAG
	private String dcomId;								//업체코드
	
	//20160503_주동민_sr#18801 [CSO내 제휴사 고객정보 마스킹 처리요청]
	private String orgMobilNo;
	private String orgTelNo;
	private String orgMbrNm;
	private String orgMbrEmail;
	private String mbrTpCd;
	
	private String ansEvlCd;		//만족도
    private String ansEvlAdminAns;		//만족도 추가답변 여부
    
    private String ansDelayWorkTm;		//답변경과시간(업무시간기준)
    private String inqDelayWorkTm;		//문의경과시간(업무시간기준)

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
