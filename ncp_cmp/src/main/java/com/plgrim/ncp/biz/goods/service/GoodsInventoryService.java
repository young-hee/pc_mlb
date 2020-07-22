package com.plgrim.ncp.biz.goods.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.plgrim.ncp.base.entities.RepositoryHelper;
import com.plgrim.ncp.base.entities.datasource1.god.God;
import com.plgrim.ncp.base.entities.datasource1.god.GodItm;
import com.plgrim.ncp.base.entities.datasource1.god.GodShopItmInv;
import com.plgrim.ncp.biz.goods.data.GoodsInventorySearchDTO;
import com.plgrim.ncp.biz.goods.repository.GoodsInventoryRepository;
import com.plgrim.ncp.biz.goods.result.GoodsInventoryResult;
import com.plgrim.ncp.framework.page.PageParam;
import com.plgrim.ncp.framework.utils.Utils;
import com.plgrim.ncp.interfaces.goods.data.GoodsListSDO;
import com.plgrim.ncp.interfaces.goods.data.InfGodItmInvSDO;
import com.plgrim.ncp.interfaces.goods.data.InfGodTargetSDO;
import com.plgrim.ncp.interfaces.util.AdapterHeader;

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
 * Description	:	상품 재고 Service
 *
 * @Author 	:	neps
 * @Date   	:	2018. 6. 8.
 * @Version	:	
 *
 */
@Service
public class GoodsInventoryService extends GoodsService {
	
	@Autowired
	private GoodsInventoryRepository goodsInventoryRepository;
	
	/**
	 * 상품 매장 단품 판매예정수량 조정
	 * 	- 증감/차감 계산
	 * 
	 * @param shopItmInv
	 */
	public int updateGoodsShopItemInventorySalePrearrangementQuantityVariation(GodShopItmInv shopItmInv) {
		return goodsInventoryRepository.updateGoodsShopItemInventorySalePrearrangementQuantityVariation(shopItmInv);
	}

	/**
	 * 상품 단품 판매예정수량 조정 및 상태 변경
	 * 	- 증감/차감 계산
	 * 
	 * @param godImt
	 * @return
	 */
	public int updateGoodsItemSalePrearrangementQuantityVariation(GodItm godItm) {
		return goodsInventoryRepository.updateGoodsItemSalePrearrangementQuantityVariation(godItm);
	}
	
	/**
	 * 상품 매장 단품 재고 조정
	 * 	- 증감/차감 계산 
	 * 
	 * @param shopItmInv
	 * @return
	 */
	public int updateGoodsShopItemInventoryQuantityVariation(GodShopItmInv shopItmInv) {
		return goodsInventoryRepository.updateGoodsShopItemInventoryQuantityVariation(shopItmInv);
	}	
	
	/**
	 * 상품 단품 재고 조정 및 상태 변경
	 * 	- 증감/차감 계산
	 * 
	 * @param godItm
	 * @return
	 */
	public int updateGoodsItemInventoryQuantityVariation(GodItm godItm) {
		return goodsInventoryRepository.updateGoodsItemInventoryQuantityVariation(godItm);
	}
	
	/**
	 * 상품 단품 예약 재고 조정 및 상태 변경
	 * 	- 증감/차감 계산
	 * 
	 * @param godItm
	 * @return
	 */
	public int updateGoodsItemReserveInventoryQuantityVariation(GodItm godItm) {
		return goodsInventoryRepository.updateGoodsItemReserveInventoryQuantityVariation(godItm);
	}	
	
	/**
	 * 상품 매장 단품 재고 수정
	 * 
	 * @param shopItmInv
	 * @return
	 */
	public int updateGoodsShopItemInventoryQuantity(GodShopItmInv shopItmInv) {
		return goodsInventoryRepository.updateGoodsShopItemInventoryQuantity(shopItmInv);
	}	
	
	/**
	 * 상품 단품 재고 수정 및 상태 변경
	 * 
	 * @param godItm
	 * @return
	 */
	public int updateGoodsItemInventoryQuantity(GodItm godItm) {
		return goodsInventoryRepository.updateGoodsItemInventoryQuantity(godItm);
	}	
	
	/**
	 * 상품 단품 상태에 따라 상품 상태 수정
	 * 
	 * @param god
	 * @return
	 */
	public int updateGoodsItemStatusAccordingToGoodsStatus(God god) {		
		String status = goodsInventoryRepository.getGoodsItemFirstProrityStatus(god.getGodNo());
		god.setGodSaleSectCd(status);
		return goodsInventoryRepository.updateGoodsItemStatusAccordingToGoodsStatus(god);
	}
	
	/**
	 * 매장 재고 목록 검색
	 * 
	 * @param inventorySearchDTO
	 * @return
	 */
	public Page<GoodsInventoryResult> searchShopInventoryList(GoodsInventorySearchDTO inventorySearchDTO) {
		try {
			// 페이지 인덱스 셋팅
			PageParam pageParam = getPageService().buildPageParam(inventorySearchDTO.getGPageNo(), inventorySearchDTO.getGPageSize());
			RepositoryHelper.makePageEntityIndex(inventorySearchDTO, pageParam);
			
			// 목록 건수 조회	
			long totalRow = goodsInventoryRepository.getShopInventoryListCount(inventorySearchDTO);		
			
			// 목록 조회
			List<GoodsInventoryResult> results = new ArrayList<GoodsInventoryResult>();			
			
			if(totalRow > 0) {
				results = goodsInventoryRepository.selectShopInventoryList(inventorySearchDTO);
			}
			
			return new PageImpl<GoodsInventoryResult>(results, pageParam.getPageable(), totalRow);
		} catch (Exception e) {
			super.stackTrace(e);
			throw new RuntimeException();			
		}
	}
	
	/**
	 * 실시간 ERP 재고 조회 후 반영
	 * 
	 * @param adapterHeader
	 * @param targetSDO
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void getRealtimeGoodsInventoryAndUpdate(AdapterHeader adapterHeader, InfGodTargetSDO targetSDO) {		
		try {
			targetSDO.setCallerId(this.getClass().getSimpleName() +".getRealtimeGoodsInventoryAndUpdate");
			
			//	ERP 재고 조회
			GoodsListSDO result = goodsAdapter.getRltmGodInvInformation(targetSDO, adapterHeader);
						
			// 상품 매장 단품 재고 업데이트
			String erpGodNo = targetSDO.getDsgnGrpNo() + "-" + targetSDO.getColorCd();					
			if (!Utils.empty(result) && !Utils.empty(result.getStockList())) {
				for (InfGodItmInvSDO stock : result.getStockList()) {					
					stock.setErpGodNo(erpGodNo);
					stock.setUdterId("IF-GOD-07");					
					goodsInventoryRepository.updateSingleGoodsShopItemInventory(stock);
				}
			}
			
			//	상품 단품 재고 반영
			goodsInventoryRepository.updateSingleGoodsItemInventory(erpGodNo);
			
			//	단품 상태에 따른 상품 상태 변경
			goodsInventoryRepository.updateSingleGoodsItemStatusAccordingToGoodsStatus(erpGodNo);				

		} catch (Exception e) {
			super.stackTrace(e);
		}
	}	
	
}
