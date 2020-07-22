package com.plgrim.ncp.biz.bi.repository;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.datasource1.god.GodItm;
import com.plgrim.ncp.biz.bi.data.BiStatisticMbrDTO;
import com.plgrim.ncp.biz.bi.data.BiStatisticOnlineInventoryDTO;
import com.plgrim.ncp.biz.bi.result.BiStatisticGodQtyShopResult;
import com.plgrim.ncp.biz.bi.result.BiStatisticMbrResult;
import com.plgrim.ncp.biz.bi.result.BiStatisticMvSysCdResult;
import com.plgrim.ncp.biz.bi.result.BiStatisticOnlineInventoryResult;
import com.plgrim.ncp.biz.goods.data.GoodsSearchDTO;

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
 * Description	:	BO 회원 가상 그룹 통계 Repository
 *
 * @Author 	:	muba
 * @Date   	:	2018. 7. 17.
 * @Version	:	
 *
 */
@Repository
public class BiStatisticRepository extends AbstractRepository{

	/**
	 * 마케팅 회원그룹 목록 수
	 * 
	 * @param biStatisticMbrDTO
	 * @return
	 */
	public long getMbrMarketingListCount(BiStatisticMbrDTO biStatisticMbrDTO){
		return getSession1().selectOne("com.plgrim.ncp.biz.bi.getMbrMarketingListCount", biStatisticMbrDTO);
	}
	
	/**
	 * 마케팅 회원그룹 목록 조회
	 * 
	 * @param biStatisticMbrDTO
	 * @return
	 */
	public List<BiStatisticMbrResult> getMbrMarketingList(BiStatisticMbrDTO biStatisticMbrDTO){
		return getSession1().selectList("com.plgrim.ncp.biz.bi.getMbrMarketingList", biStatisticMbrDTO);
	}
	
	/**
	 * 마케팅 회원그룹 목록 엑셀 다운로드
	 * 
	 * @param biStatisticMbrDTO
	 * @return
	 */
	public List<Map<String, Object>> getMbrMarketingListExcel(BiStatisticMbrDTO biStatisticMbrDTO){
		return getSession1().selectList("com.plgrim.ncp.biz.bi.getMbrMarketingListExcel", biStatisticMbrDTO);
	}
	
	/**
	 * 장바구니 회원그룹 목록 수
	 * 
	 * @param biStatisticMbrDTO
	 * @return
	 */
	public long getMbrBasketListCount(BiStatisticMbrDTO biStatisticMbrDTO){
		return getSession1().selectOne("com.plgrim.ncp.biz.bi.getMbrBasketListCount", biStatisticMbrDTO);
	}
	
	/**
	 * 장바구니 회원그룹 목록 조회
	 * 
	 * @param biStatisticMbrDTO
	 * @return
	 */
	public List<BiStatisticMbrResult> getMbrBasketList(BiStatisticMbrDTO biStatisticMbrDTO){
		return getSession1().selectList("com.plgrim.ncp.biz.bi.getMbrBasketList", biStatisticMbrDTO);
	}
	
	/**
	 * 장바구니 회원그룹 목록 엑셀 다운로드
	 * 
	 * @param biStatisticMbrDTO
	 * @return
	 */
	public List<Map<String, Object>> getMbrBasketListExcel(BiStatisticMbrDTO biStatisticMbrDTO){
		return getSession1().selectList("com.plgrim.ncp.biz.bi.getMbrBasketListExcel", biStatisticMbrDTO);
	}
	
	/**
	 * 주문 회원그룹 목록 수
	 * 
	 * @param biStatisticMbrDTO
	 * @return
	 */
	public long getMbrOrderListCount(BiStatisticMbrDTO biStatisticMbrDTO){
		return getSession1().selectOne("com.plgrim.ncp.biz.bi.getMbrOrderListCount", biStatisticMbrDTO);
	}	
	
	/**
	 * 주문 회원그룹 목록 조회
	 * 
	 * @param biStatisticMbrDTO
	 * @return
	 */
	public List<BiStatisticMbrResult> getMbrOrderList(BiStatisticMbrDTO biStatisticMbrDTO){
		return getSession1().selectList("com.plgrim.ncp.biz.bi.getMbrOrderList", biStatisticMbrDTO);
	}
	
	/**
	 * 주문 회원그룹 목록 엑셀 다운로드
	 * 
	 * @param biStatisticMbrDTO
	 * @return
	 */
	public List<Map<String, Object>> getMbrOrderListExcel(BiStatisticMbrDTO biStatisticMbrDTO){
		return getSession1().selectList("com.plgrim.ncp.biz.bi.getMbrOrderListExcel", biStatisticMbrDTO);
	}
	
	/**
	 * 시즌구분 - 코드
	 * 
	 * @param 
	 * @return
	 */
	public List<BiStatisticMvSysCdResult> getSeasonGrpCd(){
		return getSession1().selectList("com.plgrim.ncp.biz.bi.getSeasonGrpCd");
	}
	
	/**
	 * 시즌구분 - 연도
	 * 
	 * @param 
	 * @return
	 */
	public List<BiStatisticMvSysCdResult> getMnfcturYear(){
		return getSession1().selectList("com.plgrim.ncp.biz.bi.getMnfcturYear");
	}
	
	/**
	 * 온라인 재고 현황 목록 목록 수
	 * 
	 * @param biStatisticOnlineInventoryDTO
	 * @return
	 */
	public long getOnlineInventoryListCount(BiStatisticOnlineInventoryDTO biStatisticOnlineInventoryDTO){
		return getSession1().selectOne("com.plgrim.ncp.biz.bi.getOnlineInventoryListCount", biStatisticOnlineInventoryDTO);
	}	
	
	/**
	 * 온라인 재고 현황 목록 목록 조회
	 * 
	 * @param biStatisticOnlineInventoryDTO
	 * @return
	 */
	public List<BiStatisticOnlineInventoryResult> getOnlineInventoryList(BiStatisticOnlineInventoryDTO biStatisticOnlineInventoryDTO){
		return getSession1().selectList("com.plgrim.ncp.biz.bi.getOnlineInventoryList", biStatisticOnlineInventoryDTO);
	}
	
	/**
	 * 온라인 재고 현황 목록 엑셀 다운로드
	 * 
	 * @param biStatisticOnlineInventoryDTO
	 * @return
	 */
	public List<Map<String, Object>> getOnlineInventoryListExcel(BiStatisticOnlineInventoryDTO biStatisticOnlineInventoryDTO){
		return getSession1().selectList("com.plgrim.ncp.biz.bi.getOnlineInventoryListExcel", biStatisticOnlineInventoryDTO);
	}	

	/**
	 * 온라인 재고 현황 - 해당 상품 단품 조회
	 * 
	 * @param godNo
	 * @return
	 */
	public List<GodItm> getGodItmList(String godNo){
		return getSession1().selectList("com.plgrim.ncp.biz.bi.getGodItmList", godNo);
	}	
	
	/**
	 * 매장별 재고현황 목록 수
	 * 
	 * @param godItm
	 * @return
	 */
	public long getGodQtyShopListCount(GoodsSearchDTO goodsSearchDTO){
		return getSession1().selectOne("com.plgrim.ncp.biz.bi.getGodQtyShopListCount", goodsSearchDTO);
	}	
	
	/**
	 * 매장별 재고현황
	 * 
	 * @param godItm
	 * @return
	 */
	public List<BiStatisticGodQtyShopResult> getGodQtyShopList(GoodsSearchDTO goodsSearchDTO){
		return getSession1().selectList("com.plgrim.ncp.biz.bi.getGodQtyShopList", goodsSearchDTO);
	}
	
	/**
	 * 매장별 재고현황 엑셀 다운로드
	 * 
	 * @param godItm
	 * @return
	 */
	public List<Map<String, Object>> getGodQtyShopListExcel(GoodsSearchDTO goodsSearchDTO){
		return getSession1().selectList("com.plgrim.ncp.biz.bi.getGodQtyShopListExcel", goodsSearchDTO);
	}		
	
	/**
	 * 재고 batch 마지막 실행 시간
	 * 
	 * @param 
	 * @return
	 */
	public String getInvBatchEndDt(){
		return getSession1().selectOne("com.plgrim.ncp.biz.bi.getInvBatchEndDt");
	}		
	
}
