package com.plgrim.ncp.cmp.product.bo;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import com.plgrim.ncp.biz.goods.data.GoodsSearchDTO;
import com.plgrim.ncp.biz.goods.result.GoodsErpResult;

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
 * Description	:	상품 ERP 정보 Component
 *
 * @Author 	:	neps
 * @Date   	:	2018. 5. 3.
 * @Version	:	
 *
 */
public interface GoodsErpBOComponent {

	/**
	 * ERP 상품 정보 목록 검색
	 * 
	 * @param goodsSearchDTO
	 * @return
	 */
	public Page<GoodsErpResult> searchErpGoodsGridList(GoodsSearchDTO goodsSearchDTO);
	
	/**
	 * ERP 상품 정보 조회
	 * 
	 * @param goodsSearchDTO
	 * @return
	 */
	public GoodsErpResult getErpGoodsData(GoodsSearchDTO goodsSearchDTO);
	
	/**
	 * ERP 상품 선택항목 엑셀다운로드 - 파일생성
	 * 
	 * @param goodsSearchDTO
	 * @return
	 */
	public List<Map<String, String>> getErpGoodsUploadExcelTemplate(GoodsSearchDTO goodsSearchDTO);
	
}
