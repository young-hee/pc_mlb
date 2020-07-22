package com.plgrim.ncp.cmp.product.fo;

import com.plgrim.ncp.biz.goods.data.GoodsInventoryDTO;
import com.plgrim.ncp.biz.goods.data.GoodsInventorySearchDTO;
import com.plgrim.ncp.biz.goods.data.GoodsOptionDTO;
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
public interface GoodsInventoryFOComponent {

	/**
	 * 상품 옵션 변경
	 * 
	 * @param goodsOptionDTO
	 * @return
	 */
	public GoodsOptionResult getChangeOption(GoodsOptionDTO goodsOptionDTO);

	/**
	 * 상품 판매예정 재고 수정
	 * 	- 증감/차감 계산
	 * 
	 * @param goodsInventoryDTO
	 * @see only the following parameters aer used :
	 * 	shopId, godNo, itmNo, syncType, syncReqType, salePrearngeQty
	 */
	public void updateGoodsSalePrearrangementInventoryVariation(GoodsInventoryDTO goodsInventoryDTO);
	
	/**
	 * 상품 재고 수정
	 * 	- 증감/차감 계산
	 * 
	 * @param goodsInventoryDTO
	 * @see only the following parameters aer used :
	 * 	shopId, godNo, itmNo, syncType, syncReqType, invQty
	 */
	public void updateGoodsInventoryVariation(GoodsInventoryDTO goodsInventoryDTO);
	
	/**
	 * 상품 예약 재고 수정
	 * 	- 증감/차감 계산
	 * 
	 * @param goodsInventoryDTO
	 * @see only the following parameters aer used :
	 * 	godNo, itmNo, syncType, syncReqType, resveInvQty
	 */
	public void updateGoodsReserveInventoryVariation(GoodsInventoryDTO goodsInventoryDTO);
	
	/**
	 * 상품 재고 수정
	 * 	- 수량 업데이트
	 * 
	 * @param goodsInventoryDTO
	 * @see only the following parameters aer used :
	 * 	shopId, godNo, itmNo, syncType, syncReqType, invQty, lc1InvQty
	 */
	public void updateGoodsInventory(GoodsInventoryDTO goodsInventoryDTO);
	
	/**
	 * ERP 재고 조회 후 반영
	 * 
	 * @param inventoryDTO
	 */
	public void updateGoodsErpInventory(GoodsInventorySearchDTO inventoryDTO);
	
}
