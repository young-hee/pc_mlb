package com.plgrim.ncp.biz.goods.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
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
 * Description	:	상품 검색 Repository
 *
 * @Author 	:	neps
 * @Date   	:	2018. 6. 1.
 * @Version	:	
 *
 */
@Repository
public class GoodsSearchRepository extends AbstractRepository {

	/**
	 * 매장 목록 조회
	 * 
	 * @param shopSearchDTO
	 * @return
	 */
	public List<SysShopExtends> getGoodsShopList(ShopSearchDTO shopSearchDTO) {
		return getSession1().selectList("com.plgrim.ncp.biz.goods.search.getGoodsShopList", shopSearchDTO);
	}	
	
	/**
	 * 해당 매장 조회
	 * 
	 * @param shopSearchDTO(shopId)
	 * @return
	 */
	public SysShopExtends getGoodsShop(ShopSearchDTO shopSearchDTO) {
		return getSession1().selectOne("com.plgrim.ncp.biz.goods.search.getGoodsShop", shopSearchDTO);
	}	
}
