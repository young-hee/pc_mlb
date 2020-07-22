package com.plgrim.ncp.cmp.bi.bo;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

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
 * Description	:	BO 회원 가상 그룹 통계 Component
 *
 * @Author 	:	muba
 * @Date   	:	2018. 7. 17.
 * @Version	:	
 *
 */
public interface BiStatisticBOComponent {

	/**
	 * 마케팅 회원그룹 목록 조회
	 * 
	 * @param biStatisticMbrDTO
	 * @return
	 */
	public Page<BiStatisticMbrResult> getMbrMarketingGridList(BiStatisticMbrDTO biStatisticMbrDTO) throws Exception;
	
    /**
     * 마케팅 회원그룹 목록 엑셀 다운로드
     *
     * @param paramData
     * @return
     * @throws Exception
     */
    public List<Map<String, Object>> getMbrMarketingListExcel(BiStatisticMbrDTO biStatisticMbrDTO) throws Exception;	

    /**
     * 장바구니 회원그룹 목록 조회
     * 
     * @param biStatisticMbrDTO
     * @return
     */
    public Page<BiStatisticMbrResult> getMbrBasketGridList(BiStatisticMbrDTO biStatisticMbrDTO) throws Exception;
    
    /**
     * 장바구니 회원그룹 목록 엑셀 다운로드
     *
     * @param paramData
     * @return
     * @throws Exception
     */
    public List<Map<String, Object>> getMbrBasketListExcel(BiStatisticMbrDTO biStatisticMbrDTO) throws Exception;	

    /**
     * 주문 회원그룹 목록 조회
     * 
     * @param biStatisticMbrDTO
     * @return
     */
    public Page<BiStatisticMbrResult> getMbrOrderGridList(BiStatisticMbrDTO biStatisticMbrDTO) throws Exception;
    
    /**
     * 주문 회원그룹 목록 엑셀 다운로드
     *
     * @param paramData
     * @return
     * @throws Exception
     */
    public List<Map<String, Object>> getMbrOrderListExcel(BiStatisticMbrDTO biStatisticMbrDTO) throws Exception;	

	/**
	 * 시즌구분 - 코드
	 * 
	 * @param 
	 * @return
	 */
    public List<BiStatisticMvSysCdResult> getSeasonGrpCd() throws Exception;	

	/**
	 * 시즌구분 - 연도
	 * 
	 * @param 
	 * @return
	 */
    public List<BiStatisticMvSysCdResult> getMnfcturYear() throws Exception;	
    
    /**
	 * 재고 현황 목록 조회
	 * 
	 * @param biStatisticOnlineInventoryDTO
     * @return
     */
    public Page<BiStatisticOnlineInventoryResult> getOnlineInventoryGridList(BiStatisticOnlineInventoryDTO biStatisticOnlineInventoryDTO) throws Exception;
    
    /**
	 * 재고 현황 목록 엑셀 다운로드
	 * 
	 * @param biStatisticOnlineInventoryDTO
     * @return
     * @throws Exception
     */
    public List<Map<String, Object>> getOnlineInventoryListExcel(BiStatisticOnlineInventoryDTO biStatisticOnlineInventoryDTO) throws Exception;	  
    
	/**
	 * 온라인 재고 현황 - 해당 상품 단품 조회
	 * 
	 * @param godNo
	 * @return
	 */
	public List<GodItm> getGodItmList(String godNo) throws Exception;
	
    /**
	 * 매장별 재고 현황 목록 조회
	 * 
	 * @param goodsSearchDTO
     * @return
     */
    public Page<BiStatisticGodQtyShopResult> getGodQtyShopGridList(GoodsSearchDTO goodsSearchDTO) throws Exception;
    
    /**
	 * 매장별 재고 현황  목록 엑셀 다운로드
	 * 
	 * @param goodsSearchDTO
     * @return
     * @throws Exception
     */
    public List<Map<String, Object>> getGodQtyShopListExcel(GoodsSearchDTO goodsSearchDTO) throws Exception;	  
    
	/**
	 * 재고 batch 마지막 실행 시간
	 * 
	 * @param 
	 * @return
	 */
	public String getInvBatchEndDt() throws Exception;
    
    
}
