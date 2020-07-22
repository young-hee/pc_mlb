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

import java.util.List;


@Data
@EqualsAndHashCode(callSuper = false)
public class CsoCnsltClHistDTO extends AbstractDTO {

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 상담 관리자 ID
	 */
	private String cnsltAdminId;
	/**
	 * 상담 일시
	 */
	private java.util.Date cnsltDt;
	/**
	 * 콜 유형 코드
	 ㅁ. 콜 유형 : CL_TP
	 >. 인바운드 : IB
	 >. 아웃바운드 : OB

	 */
	private String clTpCd;
	/**
	 * 고객전화 국가번호
	 ㅁ. 상담 고객의 전화번호를 의미 함
	 >. 주로 SMS를 통한 회신의 용도로 관리 됨.
	 */
	private String cstmrtelNationNo;
	/**
	 * 고객전화 지역번호
	 ㅁ. 상담 고객의 전화번호를 의미 함
	 >. 주로 SMS를 통한 회신의 용도로 관리 됨.
	 */
	private String cstmrtelAreaNo;
	/**
	 * 고객전화 국번호
	 ㅁ. 상담 고객의 전화번호를 의미 함
	 >. 주로 SMS를 통한 회신의 용도로 관리 됨.
	 */
	private String cstmrtelTlofNo;
	/**
	 * 고객전화 국내번호
	 ㅁ. 상담 고객의 전화번호를 의미 함
	 >. 주로 SMS를 통한 회신의 용도로 관리 됨.
	 */
	private String cstmrtelTlofWthnNo;
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
