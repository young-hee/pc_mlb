package com.plgrim.ncp.biz.goods.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.datasource1.god.God;
import com.plgrim.ncp.base.entities.datasource1.god.GodItm;
import com.plgrim.ncp.base.entities.datasource1.god.GodShopItmInv;
import com.plgrim.ncp.biz.goods.data.GoodsInventorySearchDTO;
import com.plgrim.ncp.biz.goods.result.GoodsInventoryResult;
import com.plgrim.ncp.interfaces.goods.data.InfGodItmInvSDO;

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
 * Description	:	상품 재고 Repository
 *
 * @Author 	:	neps
 * @Date   	:	2018. 6. 8.
 * @Version	:	
 *
 */
@Repository
public class GoodsInventoryRepository extends AbstractRepository{
	
	/**
	 * 상품 매장 단품 판매 예정 재고 조정
	 * 	- 증감/차감 계산
	 * 
	 * @param shopItmInv
	 * @return
	 */
	public int updateGoodsShopItemInventorySalePrearrangementQuantityVariation(GodShopItmInv shopItmInv) {
		return getSession1().update("com.plgrim.ncp.biz.goods.inventory.updateGoodsShopItemInventorySalePrearrangementQuantityVariation", shopItmInv);
	}
	
	/**
	 * 상품 단품 판매예정수량 조정 및 상태 변경
	 * 	- 증감/차감 계산
	 * 
	 * @param godItm
	 * @return
	 */
	public int updateGoodsItemSalePrearrangementQuantityVariation(GodItm godItm) {
		return getSession1().update("com.plgrim.ncp.biz.goods.inventory.updateGoodsItemSalePrearrangementQuantityVariation", godItm);
	}
	
	/**
	 * 상품 매장 단품 재고 조정
	 * 	- 증감/차감 계산
	 * 
	 * @param shopItmInv
	 * @return
	 */
	public int updateGoodsShopItemInventoryQuantityVariation(GodShopItmInv shopItmInv) {
		return getSession1().update("com.plgrim.ncp.biz.goods.inventory.updateGoodsShopItemInventoryQuantityVariation", shopItmInv);
	}	
	
	/**
	 * 상품 단품 재고 조정 및 상태 변경
	 * 	- 증감/차감 계산
	 * 
	 * @param godItm
	 * @return
	 */
	public int updateGoodsItemInventoryQuantityVariation(GodItm godItm) {
		return getSession1().update("com.plgrim.ncp.biz.goods.inventory.updateGoodsItemInventoryQuantityVariation", godItm);
	}

	/**
	 * 상품 단품 예약 재고 조정 및 상태 변경
	 * 	- 증감/차감 계산
	 * 
	 * @param godItm
	 * @return
	 */
	public int updateGoodsItemReserveInventoryQuantityVariation(GodItm godItm) {
		return getSession1().update("com.plgrim.ncp.biz.goods.inventory.updateGoodsItemReserveInventoryQuantityVariation", godItm);
	}
	
	/**
	 * 상품 매장 단품 재고 수정
	 * 
	 * @param shopItmInv
	 * @return
	 */
	public int updateGoodsShopItemInventoryQuantity(GodShopItmInv shopItmInv) {
		return getSession1().update("com.plgrim.ncp.biz.goods.inventory.updateGoodsShopItemInventoryQuantity", shopItmInv);
	}		
	
	/**
	 * 상품 단품 재고 수정 및 상태 변경
	 * 
	 * @param godItm
	 * @return
	 */
	public int updateGoodsItemInventoryQuantity(GodItm godItm) {
		return getSession1().update("com.plgrim.ncp.biz.goods.inventory.updateGoodsItemInventoryQuantity", godItm);
	}
	
	/**
	 * 상품 단품의 우선수위가 가장 높은 상태 가져오기
	 * 
	 * @param godNo
	 * @return
	 */
	public String getGoodsItemFirstProrityStatus(String godNo) {
		return getSession1().selectOne("com.plgrim.ncp.biz.goods.inventory.getGoodsItemFirstProrityStatus", godNo);
	}	
	
	/**
	 * 상품 단품 상태에 따라 상품 상태 수정
	 * 
	 * @param god
	 * @return
	 */
	public int updateGoodsItemStatusAccordingToGoodsStatus(God god) {
		return getSession1().update("com.plgrim.ncp.biz.goods.inventory.updateGoodsItemStatusAccordingToGoodsStatus", god);
	}
	
	/**
	 * 매장 재고 목록 수
	 * 
	 * @param inventorySearchDTO
	 * @return
	 */
	public long getShopInventoryListCount(GoodsInventorySearchDTO inventorySearchDTO) {
		return getSession1().selectOne("com.plgrim.ncp.biz.goods.inventory.getShopInventoryListCount", inventorySearchDTO);
	}
	
	/**
	 * 매장 재고 목록 조회
	 * 
	 * @param inventorySearchDTO
	 * @return
	 */
	public List<GoodsInventoryResult> selectShopInventoryList(GoodsInventorySearchDTO inventorySearchDTO) {
		return getSession1().selectList("com.plgrim.ncp.biz.goods.inventory.selectShopInventoryList", inventorySearchDTO);
	}

	/**
	 * 상품 매장 단품 재고 변경
	 * 	- 매장 단품 1건 변경
	 * 
	 * @param itmInv
	 * @return
	 * @throws Exception
	 */
	public int updateSingleGoodsShopItemInventory(InfGodItmInvSDO infGodItmInvSDO) throws Exception {
		return getSqlSession1().update("com.plgrim.ncp.biz.goods.inventory.updateSingleGoodsShopItemInventory", infGodItmInvSDO);
	}
	

	/**
	 * 상품 단품 재고 반영
	 * 	- 단품 1건 반영
	 * 
	 * @param erpGodNo
	 * @return
	 * @throws Exception
	 */
	public int updateSingleGoodsItemInventory(String erpGodNo) throws Exception {
		return getSqlSession1().update("com.plgrim.ncp.biz.goods.inventory.updateSingleGoodsItemInventory", erpGodNo);
	}
	
	/**
	 * 단품 상태에 따른 상품 상태 변경
	 * 	- 상품 1건 반영
	 * 
	 * @param erpGodNo
	 * @return
	 * @throws Exception
	 */
	public int updateSingleGoodsItemStatusAccordingToGoodsStatus(String erpGodNo) throws Exception {
		return getSqlSession1().update("com.plgrim.ncp.biz.goods.inventory.updateSingleGoodsItemStatusAccordingToGoodsStatus", erpGodNo);
	}
	
}
