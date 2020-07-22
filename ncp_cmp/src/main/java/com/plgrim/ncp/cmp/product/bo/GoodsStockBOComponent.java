package com.plgrim.ncp.cmp.product.bo;

import java.util.List;

import com.plgrim.ncp.base.entities.datasource1.god.GodExtend;
import com.plgrim.ncp.base.entities.datasource1.ord.OrdGodExtends;

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
 * @Date   	:	2018. 5. 3.
 * @Version	:	
 *
 */
public interface GoodsStockBOComponent {

    /**
	 * 상품 단품 재고 이력 등록 - 주문에서 사용
	 *
	 * @param ordGodExtends
	 * @param shopId
	 */
	@Deprecated
	public void insertGodItmInvHistByOrder(OrdGodExtends ordGodExtends, String shopId);
	
	/**
	 * 해당상품번호, 해당매장의 단품별 판매가능수량과 히스토리정보 목록 조회
	 * 
	 * @param systemPK
	 * @param godNo
	 * @param shopId
	 */	
	@Deprecated
	public List<GodExtend> selectGodItmShopStockHistList(String godNo, String shopId);
}
