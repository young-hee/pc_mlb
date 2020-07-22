package com.plgrim.ncp.cmp.product.bo;

import org.springframework.data.domain.Page;

import com.plgrim.ncp.biz.goods.data.GoodsInventorySearchDTO;
import com.plgrim.ncp.biz.goods.data.GoodsOptionDTO;
import com.plgrim.ncp.biz.goods.result.GoodsInventoryResult;
import com.plgrim.ncp.biz.goods.result.GoodsOptionResult;

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
 * Description	:	상품 재고 Component
 *
 * @Author 	:	neps
 * @Date   	:	2018. 6. 3.
 * @Version	:	
 *
 */
public interface GoodsInventoryBOComponent {

	/**
	 * 상품 옵션 변경
	 * 
	 * @param goodsOptionDTO
	 * @return
	 */
	public GoodsOptionResult getChangeOption(GoodsOptionDTO goodsOptionDTO);

	
	/**
	 * 매장 재고 목록 검색
	 * 
	 * @param inventorySearchDTO
	 * @return
	 */
	public Page<GoodsInventoryResult> searchShopInventoryList(GoodsInventorySearchDTO inventorySearchDTO);
	
}
