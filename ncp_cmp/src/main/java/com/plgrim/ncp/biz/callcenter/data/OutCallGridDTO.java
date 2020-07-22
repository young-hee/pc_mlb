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
import com.plgrim.ncp.base.entities.datasource1.cso.CsoGlan;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class OutCallGridDTO extends AbstractDTO {

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */

	/**
	 * 업체 명	 
	 */
	private String comNm;
	private String comNmKey;
	/**
	 * 담당자 명	 
	 */
	private String chrgerNm;
	private String chrgerNmKey;

	private String cnvrsPhone;

	private int phonTurn;
	
	
	/**
	 * 전환 국가번호	 
	 */
	private String cnvrsNationNo;
	/**
	 * 전환 지역번호	 
	 */
	private String cnvrsAreaNo;
	/**
	 * 전환 국번호	 
	 */
	private String cnvrsTlofNo;
	/**
	 * 전환 국내번호	 
	 */
	private String cnvrsTlofWthnNo;
	/**
	 * 정렬 순서	 
	 */
	private java.lang.Integer sortSeq;
	/**
	 * 사용 여부	 
	 */
	private String useYn;
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
