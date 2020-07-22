package com.plgrim.ncp.cmp.product.fo;

import java.util.List;

import com.plgrim.ncp.base.entities.datasource1.sys.SysShopExtends;
import com.plgrim.ncp.biz.vendor.data.ShopSearchDTO;

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
 * Description	:	상품 검색 Component
 *
 * @Author 	:	neps
 * @Date   	:	2018. 5. 3.
 * @Version	:	
 *
 */
public interface GoodsSearchFOComponent {
	
	/**
	 * 매장 목록 조회
	 * 
	 * @param shopSearchDTO
	 * @return
	 */
	public List<SysShopExtends> getGoodsShopList(ShopSearchDTO shopSearchDTO);

	/**
	 * 해당 목록 조회
	 * 
	 * @param shopSearchDTO(shopId)
	 * @return
	 */
	public SysShopExtends getGoodsShop(ShopSearchDTO shopSearchDTO);

}
