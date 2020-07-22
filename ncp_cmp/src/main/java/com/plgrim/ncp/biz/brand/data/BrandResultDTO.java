/* Copyright (c) 2015 plgrim, Inc.
 * All right reserved.
 * http://plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 *
 * ------------------------------------------------------------------------
 * @author      jm6417.lee
 * @since       2016. 4. 12       
 */
package com.plgrim.ncp.biz.brand.data;

import com.plgrim.ncp.base.entities.datasource1.sys.SysBrnd;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class BrandResultDTO extends SysBrnd {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */

	/**
	 * 브랜드 알파벳 별 카운트
	 */
	private int brndGrpCnt;
	
	/**
	 * 자사 브랜드 여부
	 */
	private String ownBrndYN;
	
	/**
	 * 알파벳 첫 문자
	 */
	private String firstBrndChar;
	
	/**
	 * 자사 브랜드 카운트
	 */
	private int ownBrndCnt;
	
	/**
	 * 총 카운트
	 */
	private int totCnt;

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
