package com.plgrim.ncp.biz.goods.result;

import java.util.List;

import org.apache.ibatis.type.Alias;

import com.plgrim.ncp.base.entities.datasource1.com.ComDmstcDlvCstPlc;
import com.plgrim.ncp.base.entities.datasource1.god.GodColor;
import com.plgrim.ncp.base.entities.datasource1.god.GodItmExtend;
import com.plgrim.ncp.base.entities.datasource1.god.GodLndr;
import com.plgrim.ncp.base.entities.datasource1.god.GodLndrDscr;
import com.plgrim.ncp.base.entities.datasource1.god.GodLndrImg;
import com.plgrim.ncp.base.entities.datasource1.inf.InfGodItmExtend;
import com.plgrim.ncp.base.entities.datasource1.sys.SysBrnd;
import com.plgrim.ncp.base.entities.datasource1.sys.SysBrndPrdlst;

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
 * Description	:	상품 ERP RESULT
 *
 * @Author 	:	neps
 * @Date   	:	2018. 5. 3.
 * @Version	:	
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Alias("goodsErpResult")
public class GoodsErpResult extends GoodsResult {
	private static final long serialVersionUID = -1894484279615507115L;
	
	/**
	 * 사이즈 조견표 PC
	 */
	private String sizeLktb;
	
	/**
	 * 사이즈 조견표(영문)
	 */
	private String sizeLktbEng;
	
	/**
	 * 사이즈 조견표(중문)
	 */
	private String sizeLktbChi;

	/**
	 * 사이즈 조견표 MOBILE
	 */
	private String mobileSizeLktb;
	
	/**
	 * 사이즈 조견표(영문)
	 */
	private String mobileSizeLktbEng;	
	
	/**
	 * 사이즈 조견표(중문)
	 */
	private String mobileSizeLktbChi;	
	
	/** 인터페이스 상품 단품 확장 엔티티 */
	private InfGodItmExtend infGodItmEx;
	
	/** 상품 단품 확장 엔티티 리스트*/
	private List<GodItmExtend> itmList;
	
	/** 브랜드 엔티티 */
	private SysBrnd sysBrnd;
	
	/** 브랜드 품목 엔티티 */
	private SysBrndPrdlst brndPrdlst;
	
	/** 상품 컬러 엔티티 */
	private GodColor godColor;

	/** 세탁코드 엔티티 */
	private GodLndr godLndr;
	
	/** 상품 세탁 설명 엔티티 리스트 */
	private List<GodLndrDscr> godLndrDscrList;
	
	/** 세탁코드 이미지 엔티티 리스트*/
	private List<GodLndrImg> godLndrImgList;
	
	/** 업체 국내 배송비 정책 엔티티 리스트. */
	private List<ComDmstcDlvCstPlc> comDmstcDlvCstPlcList;	
	
}
