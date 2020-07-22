/* Copyright (c) 2015 plgrim, Inc.
 * All right reserved.
 * http://plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 *
 * ------------------------------------------------------------------------
 * @author      ds1.an
 * @since       2015. 3. 16       
 */
package com.plgrim.ncp.biz.display.data;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractDTO;

/**
 * 베스트 전시 시뮬레이션 DTO.
 *
 */

@Data
@EqualsAndHashCode(callSuper = false)
public class DspSaleSimulBoDTO extends AbstractDTO {
	
	/**
	 * 
	 */
    private static final long serialVersionUID = 5373530121050086852L;

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	
	/** 로직선택 */
    private String saleIdexCd;
    
    /** 전시구분 */
	private String dspStatCd;
	
	/** 검색 자사 브랜드 */
	private String[] brndIds;
	
	/** 전시카테고리 */
	private String[] dspCtgryNos;
	
	/** 상품조회 구분 */
	private String godNosGbn;
	
	/** 상품번호 멀티 */
    private String[] godNoArry;
    
    /** 로직 적용 여부 */
    private String logicAppYn;

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
