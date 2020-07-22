package com.plgrim.ncp.cmp.product.fo;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.plgrim.ncp.biz.goods.data.GoodsContentSearchDTO;
import com.plgrim.ncp.biz.goods.data.GoodsReviewSearchDTO;
import com.plgrim.ncp.biz.goods.result.GoodsContentResult;
import com.plgrim.ncp.biz.goods.result.GoodsRelatedGodResult;
import com.plgrim.ncp.biz.goods.result.GoodsReviewResult;

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
 * Description	:	상품 컨텐츠 Component	
 *
 * @Author 	:	neps
 * @Date   	:	2018. 5. 3.
 * @Version	:	
 *
 */
public interface GoodsContentFOComponent {

	/**
	 * 상품 컨텐츠 조회
	 * 
	 * @param goodsContentSearchDTO
	 * @param response
	 * @param request
	 * @return
	 */
	public GoodsContentResult getGoodsContent(GoodsContentSearchDTO goodsContentSearchDTO, HttpServletResponse response, HttpServletRequest request);
	
	/**
	 * 상품 리뷰 목록 검색
	 * 
	 * @param goodsReviewSearchDTO
	 * @return
	 */
	public GoodsReviewResult searchReviewList(GoodsReviewSearchDTO goodsReviewSearchDTO);

	public long getReviewListCount(GoodsReviewSearchDTO goodsReviewSearchDTO);
	
	public List<GoodsRelatedGodResult> getRelatedGoods(GoodsContentSearchDTO contentSearchDTO);
	
	
}
