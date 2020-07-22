package com.plgrim.ncp.biz.goods.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.datasource1.god.GodDspCtgryExtend;

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
 * Description	:	상품 카테고리 Repository
 *
 * @Author 	:	neps
 * @Date   	:	2018. 5. 3.
 * @Version	:	
 *
 */
@Repository
public class GoodsCategoryRepository extends AbstractRepository{

	
	/**
	 * 전시 카테고리 조회
	 * 
	 * @param godNo
	 * @return
	 */
	public List<GodDspCtgryExtend> selectDisplayCategoryList(String godNo) {
		return getSession1().selectList("com.plgrim.ncp.biz.goods.category.selectDisplayCategoryList", godNo);
	}
	
	/**
	 * 상품 전시카테고리 연결 상품 삭제
	 * 
	 * @param godNo
	 */
	public void deleteDisplayCategoryConnectionGoods(String godNo) {
		getSession1().update("com.plgrim.ncp.biz.goods.category.deleteDisplayCategoryConnectionGoods", godNo);	
	}

}
