package com.plgrim.ncp.cmp.product.fo;

import com.plgrim.ncp.base.entities.datasource1.god.GodReWhsgNtcn;
import com.plgrim.ncp.biz.goods.data.GoodsSearchDTO;
import com.plgrim.ncp.biz.goods.result.GoodsInfoResult;

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
 * Description	:	상품 기본 정보 Component
 *
 * @Author 	:	neps
 * @Date   	:	2018. 5. 3.
 * @Version	:	
 *
 */
public interface GoodsFOComponent {
	
	/**
	 * 상품 상세정보 조회
	 * 
	 * @param goodsSearchDTO
	 * @return
	 */
	public GoodsInfoResult getGoods(GoodsSearchDTO goodsSearchDTO);

	/**
	 * 상품 할인쿠폰 회원발급
	 * 
	 * @param prmNo
	 * @return
	 */
	public String addCouponDownMemberIssue(String prmNo) throws Exception;
	
	/**
	 * 상품 상세 URL 조회
	 * 
	 * @param godNo
	 * @param encode
	 * @return
	 */
	public String getViewGoodsUrl(String godNo, boolean encode);
	
	/**
	 * 상품 상세 redirect URL 조회
	 * 
	 * @param goodsSearchDTO
	 * @return
	 */
	public GoodsInfoResult getRedirectGoodsUrl(GoodsSearchDTO goodsSearchDTO);
	
	/**
	 * 재입고 알림 저장
	 * 
	 * @param 
	 * @return
	 */
	public String addGoodsInform(GodReWhsgNtcn godReWhsgNtcn) throws Exception;
	
}
