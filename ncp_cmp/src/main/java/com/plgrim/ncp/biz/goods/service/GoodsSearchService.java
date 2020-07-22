package com.plgrim.ncp.biz.goods.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plgrim.ncp.base.entities.datasource1.sys.SysShopExtends;
import com.plgrim.ncp.biz.goods.repository.GoodsSearchRepository;
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
 * Description	:	상품 검색 Service
 *
 * @Author 	:	neps
 * @Date   	:	2018. 6. 1.
 * @Version	:	
 *
 */
@Service
public class GoodsSearchService extends GoodsService{
	
	@Autowired
	private GoodsSearchRepository goodsSearchRepository;
	
	/**
	 * 매장 목록 조회
	 * 
	 * @param shopSearchDTO
	 * @return
	 */
	public List<SysShopExtends> getGoodsShopList(ShopSearchDTO shopSearchDTO) {
		return goodsSearchRepository.getGoodsShopList(shopSearchDTO);
	}
	
	/**
	 * 해당 매장 조회
	 * 
	 * @param shopSearchDTO(shopId)
	 * @return
	 */
	public SysShopExtends getGoodsShop(ShopSearchDTO shopSearchDTO) {
		return goodsSearchRepository.getGoodsShop(shopSearchDTO);
	}
	
}
