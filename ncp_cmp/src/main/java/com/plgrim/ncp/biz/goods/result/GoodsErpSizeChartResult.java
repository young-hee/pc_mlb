package com.plgrim.ncp.biz.goods.result;

import org.apache.ibatis.type.Alias;

import lombok.Data;
import lombok.EqualsAndHashCode;

/** Copyright (c) 2018 plgrim, Inc.
 * All right reserved.
 * http://plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 *
 * ------------------------------------------------------------------------
 *
 * Description	:	ERP 사이즈 조견표 RESULT
 *
 * @Author 	:	neps
 * @Date   	:	2018. 6. 8.
 * @Version	:	
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Alias("goodsErpSizeChartResult")
public class GoodsErpSizeChartResult extends GoodsResult{
	private static final long serialVersionUID = 4551278407549943808L;
	
	/**
	 * 사이즈 조견표 PC
	 */
	private String sizeLktb;

	/**
	 * 사이즈 조견표 PC(영문)
	 */
	private String sizeLktbEng;
	
	/**
	 * 사이즈 조견표 PC(중문)
	 */
	private String sizeLktbChi;	
	
	/**
	 * 사이즈 조견표 MOBILE
	 */
	private String mobileSizeLktb;

	/**
	 * 사이즈 조견표 MOBILE(영문)
	 */
	private String mobileSizeLktbEng;	
	
	/**
	 * 사이즈 조견표 MOBILE(중문)
	 */
	private String mobileSizeLktbChi;	
	
}
