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

@Data
@EqualsAndHashCode(callSuper=false)
public class CsoCnsltDetailJobTpResult extends AbstractResult {
	
	// ~ Instance fields. ~~~~~~~~~~~~~~
	
	// ~ Constructors. ~~~~~~~~~~~~~~~~~
	
	// ~ Getter and Setter. ~~~~~~~~~~~~

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 상담 일련번호
	 */
	private java.lang.Long cnsltSn;
	/**
	 * 상담 상세 순번
	 ㅁ. 상담상세 이력에 대한 순번으로 상담 번호별 순번을 발급하도록 한다.
	 >. "고객 상담 상세 이력.상담 상세 순번"의 MAX값 + 1로 등록 되도록 한다.
	 >. 숫자로 최대 5자리 " 99999" 로 관리 한다.
	 (ORACLE 기준 Format)
	 */
	private java.lang.Integer cnsltDetailTurn;
	/**
	 * 상담 업무 유형 코드
	 ㅁ. 상담 업무 유형 : CNSL_JOB_TP
	 >.  인바운드 : IB
	 >.  아웃바운드 : OB
	 >.  분배 : DSTB
	 >.  문의답변 : INQ_ANS
	 >.  이관 : TRANS
	 >.  기타 : ETC

	 */
	private String cnsltJobTpCd;
	private String cnsltJobTpNm;

	/**
	 * 등록자 ID
	 */
	private String regtrId;
	/**
	 * 등록 일시
	 */
	private java.util.Date regDt;
	/**
	 * 수정자 ID
	 수정한 관리자 번호
	 */
	private String udterId;
	/**
	 * 수정 일시
	 */
	private java.util.Date udtDt;

}
