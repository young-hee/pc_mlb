package com.plgrim.ncp.biz.bi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import com.plgrim.ncp.base.abstracts.AbstractService;
import com.plgrim.ncp.base.entities.RepositoryHelper;
import com.plgrim.ncp.base.entities.datasource1.god.GodItm;
import com.plgrim.ncp.biz.bi.data.BiStatisticMbrDTO;
import com.plgrim.ncp.biz.bi.data.BiStatisticOnlineInventoryDTO;
import com.plgrim.ncp.biz.bi.repository.BiStatisticRepository;
import com.plgrim.ncp.biz.bi.result.BiStatisticGodQtyShopResult;
import com.plgrim.ncp.biz.bi.result.BiStatisticMbrResult;
import com.plgrim.ncp.biz.bi.result.BiStatisticMvSysCdResult;
import com.plgrim.ncp.biz.bi.result.BiStatisticOnlineInventoryResult;
import com.plgrim.ncp.biz.goods.data.GoodsSearchDTO;
import com.plgrim.ncp.framework.page.PageParam;

import lombok.extern.slf4j.Slf4j;

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
 * Description	:	BO 회원 가상 그룹 통계 Service
 *
 * @Author 	:	muba
 * @Date   	:	2018. 7. 17.
 * @Version	:	
 *
 */
@Slf4j
@Service
public class BiStatisticService extends AbstractService{

	@Autowired
	BiStatisticRepository biStatisticRepository;
	
	/**
	 * 마케팅 회원그룹 목록 조회
	 * 
	 * @param biStatisticMbrDTO
	 * @return
	 */
	public Page<BiStatisticMbrResult> getMbrMarketingGridList(BiStatisticMbrDTO biStatisticMbrDTO) throws Exception{
		try {
			// 페이지 인덱스 셋팅
			PageParam pageParam = getPageService().buildPageParam(biStatisticMbrDTO.getGPageNo(), biStatisticMbrDTO.getGPageSize());
			RepositoryHelper.makePageEntityIndex(biStatisticMbrDTO, pageParam);
			
			// 목록 건수 조회	
			long totalRow = biStatisticRepository.getMbrMarketingListCount(biStatisticMbrDTO);
			
			// 목록 조회
			List<BiStatisticMbrResult> results = new ArrayList<BiStatisticMbrResult>();
			
			if(totalRow > 0) {
				results = biStatisticRepository.getMbrMarketingList(biStatisticMbrDTO);	
			}
			
			return new PageImpl<BiStatisticMbrResult>(results, pageParam.getPageable(), totalRow);
		} catch (Exception e) {
			log.info("Exception : ", e.getMessage());
			throw new RuntimeException();
		}
	}	
	
	/**
	 * 마케팅 회원그룹 목록 엑셀 다운로드
	 * 
	 * @param biStatisticMbrDTO
	 * @return
	 */
	public List<Map<String, Object>> getMbrMarketingListExcel(BiStatisticMbrDTO biStatisticMbrDTO) throws Exception{
		return biStatisticRepository.getMbrMarketingListExcel(biStatisticMbrDTO);
	}	
	
	/**
	 * 장바구니 회원그룹 목록 조회
	 * 
	 * @param biStatisticMbrDTO
	 * @return
	 */
	public Page<BiStatisticMbrResult> getMbrBasketGridList(BiStatisticMbrDTO biStatisticMbrDTO) throws Exception{
		try {
			// 페이지 인덱스 셋팅
			PageParam pageParam = getPageService().buildPageParam(biStatisticMbrDTO.getGPageNo(), biStatisticMbrDTO.getGPageSize());
			RepositoryHelper.makePageEntityIndex(biStatisticMbrDTO, pageParam);
			
			// 목록 건수 조회	
			long totalRow = biStatisticRepository.getMbrBasketListCount(biStatisticMbrDTO);
			
			// 목록 조회
			List<BiStatisticMbrResult> results = new ArrayList<BiStatisticMbrResult>();
			
			if(totalRow > 0) {
				results = biStatisticRepository.getMbrBasketList(biStatisticMbrDTO);
			}
			
			return new PageImpl<BiStatisticMbrResult>(results, pageParam.getPageable(), totalRow);
		} catch (Exception e) {
			log.info("Exception : ", e.getMessage());
			throw new RuntimeException();
		}
	}	
	
	/**
	 * 장바구니 회원그룹 목록 엑셀 다운로드
	 * 
	 * @param biStatisticMbrDTO
	 * @return
	 */
	public List<Map<String, Object>> getMbrBasketListExcel(BiStatisticMbrDTO biStatisticMbrDTO) throws Exception{
		return biStatisticRepository.getMbrBasketListExcel(biStatisticMbrDTO);
	}	
	
	/**
	 * 주문 회원그룹 목록 조회
	 * 
	 * @param biStatisticMbrDTO
	 * @return
	 */
	public Page<BiStatisticMbrResult> getMbrOrderGridList(BiStatisticMbrDTO biStatisticMbrDTO) throws Exception{
		try {
			// 페이지 인덱스 셋팅
			PageParam pageParam = getPageService().buildPageParam(biStatisticMbrDTO.getGPageNo(), biStatisticMbrDTO.getGPageSize());
			RepositoryHelper.makePageEntityIndex(biStatisticMbrDTO, pageParam);
			
			// 목록 건수 조회	
			long totalRow = biStatisticRepository.getMbrOrderListCount(biStatisticMbrDTO);
			
			// 목록 조회
			List<BiStatisticMbrResult> results = new ArrayList<BiStatisticMbrResult>();
			
			if(totalRow > 0) {
				results = biStatisticRepository.getMbrOrderList(biStatisticMbrDTO);
			}
			
			return new PageImpl<BiStatisticMbrResult>(results, pageParam.getPageable(), totalRow);
		} catch (Exception e) {
			log.info("Exception : ", e.getMessage());
			throw new RuntimeException();
		}
	}	
	
	/**
	 * 주문 회원그룹 목록 엑셀 다운로드
	 * 
	 * @param biStatisticMbrDTO
	 * @return
	 */
	public List<Map<String, Object>> getMbrOrderListExcel(BiStatisticMbrDTO biStatisticMbrDTO) throws Exception{
		return biStatisticRepository.getMbrOrderListExcel(biStatisticMbrDTO);
	}
	
	/**
	 * 시즌구분 - 코드
	 * 
	 * @param 
	 * @return
	 */
	public List<BiStatisticMvSysCdResult> getSeasonGrpCd() throws Exception{
		return biStatisticRepository.getSeasonGrpCd();
	}	

	/**
	 * 시즌구분 - 연도
	 * 
	 * @param 
	 * @return
	 */
	public List<BiStatisticMvSysCdResult> getMnfcturYear() throws Exception{
		return biStatisticRepository.getMnfcturYear();
	}	
	
	/**
	 * 재고 현황 목록 조회
	 * 
	 * @param biStatisticOnlineInventoryDTO
	 * @return
	 */
	public Page<BiStatisticOnlineInventoryResult> getOnlineInventoryGridList(BiStatisticOnlineInventoryDTO biStatisticOnlineInventoryDTO) throws Exception{
		try {
			// 페이지 인덱스 셋팅
			PageParam pageParam = getPageService().buildPageParam(biStatisticOnlineInventoryDTO.getGPageNo(), biStatisticOnlineInventoryDTO.getGPageSize());
			RepositoryHelper.makePageEntityIndex(biStatisticOnlineInventoryDTO, pageParam);
			
			// 목록 건수 조회	
			long totalRow = biStatisticRepository.getOnlineInventoryListCount(biStatisticOnlineInventoryDTO);
			
			// 목록 조회
			List<BiStatisticOnlineInventoryResult> results = new ArrayList<BiStatisticOnlineInventoryResult>();
			
			if(totalRow > 0) {
				results = biStatisticRepository.getOnlineInventoryList(biStatisticOnlineInventoryDTO);
			}
			
			return new PageImpl<BiStatisticOnlineInventoryResult>(results, pageParam.getPageable(), totalRow);
		} catch (Exception e) {
			log.info("Exception : ", e.getMessage());
			throw new RuntimeException();
		}
	}	
	
	/**
	 * 재고 현황 목록 엑셀 다운로드
	 * 
	 * @param biStatisticOnlineInventoryDTO
	 * @return
	 */
	public List<Map<String, Object>> getOnlineInventoryListExcel(BiStatisticOnlineInventoryDTO biStatisticOnlineInventoryDTO) throws Exception{
		return biStatisticRepository.getOnlineInventoryListExcel(biStatisticOnlineInventoryDTO);
	}	

	/**
	 * 온라인 재고 현황 - 해당 상품 단품 조회
	 * 
	 * @param godNo
	 * @return
	 */
	public List<GodItm> getGodItmList(String godNo){
		return biStatisticRepository.getGodItmList(godNo);
	}
	
	/**
	 * 매장별 재고 현황  목록 조회
	 * 
	 * @param goodsSearchDTO
	 * @return
	 */
	public Page<BiStatisticGodQtyShopResult> getGodQtyShopGridList(GoodsSearchDTO goodsSearchDTO) throws Exception{
		try {
			// 페이지 인덱스 셋팅
			PageParam pageParam = getPageService().buildPageParam(goodsSearchDTO.getGPageNo(), goodsSearchDTO.getGPageSize());
			RepositoryHelper.makePageEntityIndex(goodsSearchDTO, pageParam);
			
			// 목록 건수 조회	
			long totalRow = biStatisticRepository.getGodQtyShopListCount(goodsSearchDTO);
			
			// 목록 조회
			List<BiStatisticGodQtyShopResult> results = new ArrayList<BiStatisticGodQtyShopResult>();
			
			if(totalRow > 0) {
				results = biStatisticRepository.getGodQtyShopList(goodsSearchDTO);
			}
			
			return new PageImpl<BiStatisticGodQtyShopResult>(results, pageParam.getPageable(), totalRow);
		} catch (Exception e) {
			log.info("Exception : ", e.getMessage());
			throw new RuntimeException();
		}
	}	
	
	/**
	 * 매장별 재고 현황  목록 엑셀 다운로드
	 * 
	 * @param goodsSearchDTO
	 * @return
	 */
	public List<Map<String, Object>> getGodQtyShopListExcel(GoodsSearchDTO goodsSearchDTO) throws Exception{
		return biStatisticRepository.getGodQtyShopListExcel(goodsSearchDTO);
	}		
	
	/**
	 * 재고 batch 마지막 실행 시간
	 * 
	 * @param 
	 * @return
	 */
	public String getInvBatchEndDt(){
		return biStatisticRepository.getInvBatchEndDt();
	}
	
}
