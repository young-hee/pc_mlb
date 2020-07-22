/* Copyright (c) 2013 plgrim, Inc.
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
 * 
 */
package com.plgrim.ncp.commons.result;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractResult;

/**
 * @author Park
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CodeViewResult extends AbstractResult {

	/**
	 * UID
	 */
    private static final long serialVersionUID = 591797712065396263L;

	/**
	 * 코드
	 */
	private String cd;
	
	/**
	 * 상위코드
	 */
	private String upperCd;

	/**
	 * 언어
	 */
	private String lang;
	
	/**
	 * 코드명
	 */
	private String cdNm;
	
	/**
	 * 설명
	 */
	private String cdDscr;
	
	/**
	 * 보조코드1
	 */
	private String asstnCd1;
	
	/**
	 * 보조코드2
	 */
	private String asstnCd2;
	
	/**
	 * 그룹 코드 여부. 그룹코드이면 false, 그룹코드가 아니면 true
	 */
	private boolean isLeaf;
	/**
	 * 사용 여부
	 */
	private boolean isUse;
	/**
	 * 정산사용 여부
	 */
	private boolean isCalUseYn;	
	
	/** SR[22287] 상품상세 페이지 내 상품 추가정보 노출.
	 * 상위코드명
	 */
	private String upperCdNm;
}
