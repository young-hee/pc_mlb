/* Copyright (c) 2015 plgrim, Inc.
 * All right reserved.
 * http://plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 *
 * ------------------------------------------------------------------------
 * @author      
 * @since       2015. 4. 6
 */
package com.plgrim.ncp.cmp.orderfulfilment.bo.delivery.retrieval;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.plgrim.ncp.base.entities.RepositoryHelper;
import com.plgrim.ncp.base.entities.datasource1.god.GodShopItmInvExtend;
import com.plgrim.ncp.base.entities.datasource1.inf.InfOrdGodErpDstb;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsDlvRtrvlDrctHist;
import com.plgrim.ncp.biz.delivery.data.DeliveryInvoiceDTO;
import com.plgrim.ncp.biz.delivery.data.DeliverySearchDTO;
import com.plgrim.ncp.biz.delivery.result.DeliveryClaimGoodResult;
import com.plgrim.ncp.biz.delivery.result.DeliveryInvoiceResult;
import com.plgrim.ncp.biz.delivery.result.DeliveryOrderGoodResult;
import com.plgrim.ncp.biz.delivery.result.ReturnItemListByClaimNoResult;
import com.plgrim.ncp.biz.delivery.service.DeliveryCommandService;
import com.plgrim.ncp.biz.delivery.service.DeliveryListService;
import com.plgrim.ncp.biz.delivery.service.DeliveryStatusService;
import com.plgrim.ncp.cmp.orderfulfilment.bo.delivery.DeliveryRetrievalBoComponent;
import com.plgrim.ncp.commons.util.BOSecurityUtil;
import com.plgrim.ncp.framework.data.SystemPK;

import lombok.extern.slf4j.Slf4j;


/**
 * [클래스 설명]
 *
 * <p>
 *
 * <ul>
 *   <li> [기능1]
 *   <li> [기능2]
 * </ul>.
 *
 * @author 
 * @since 2015. 4. 6
 */
@Slf4j
@Transactional(value = "transactionManager")
@Component
public class DeliveryRetrievalBoComponentImpl implements DeliveryRetrievalBoComponent {

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	@Autowired
	DeliveryListService deliveryListService;

	@Autowired
	DeliveryStatusService deliveryStatusService;
	
	@Autowired
	DeliveryCommandService deliveryCommandService;

	/** 자사상품 회수리스트 조회. */
	@Override
	public  List<DeliveryClaimGoodResult> getRetrievalGoodsList(SystemPK systemPk, DeliverySearchDTO deliverySearchDTO) throws Exception {
		RepositoryHelper.makePageEntityIndex(deliverySearchDTO, deliverySearchDTO.getPageParam());	// 페이지 설정
		return deliveryListService.getRetrievalGoodsList(systemPk, deliverySearchDTO);
	}

	/** 자사상품 회수리스트 조회 total count. */
	@Override
	public  int getRetrievalGoodsListCount(SystemPK systemPk, DeliverySearchDTO deliverySearchDTO) throws Exception {
		return deliveryListService.getRetrievalGoodsListCount(systemPk, deliverySearchDTO);
	}

	/** 자사상품 회수리스트 조회 excel. */
	@Override
	public List<Map<String, Object>> getRetrievalGoodsListExcel(SystemPK systemPK, DeliverySearchDTO deliverySearchDTO) throws Exception {
		return deliveryListService.getRetrievalGoodsListExcel(systemPK,deliverySearchDTO);
	}

	/**상품회수처리 조회 */
	@Override
	public List<DeliveryClaimGoodResult> getCompleteRetrievalList(SystemPK systemPK, DeliverySearchDTO deliverySearchDTO) throws Exception {
		return deliveryListService.getCompleteRetrievalList(systemPK, deliverySearchDTO);
	}

	/** 클레임 운송장 상세조회 */
	@Override
	public DeliveryInvoiceResult getClmInvoice(SystemPK systemPK, DeliverySearchDTO deliverySearchDTO) throws Exception {
		return deliveryListService.getClmInvoice(systemPK, deliverySearchDTO);
	}

	/** 클레임 운송장 이력 목록 */
	@Override
	public List<DeliveryInvoiceResult> getClmInvoiceHistoryList(SystemPK systemPK, DeliverySearchDTO deliverySearchDTO) throws Exception {
		return deliveryListService.getClmInvoiceHistoryList(systemPK, deliverySearchDTO);
	}
	
	/** 회수완료 */
	@Override
	public int updateCompleteRetrival(SystemPK systemPK, List<DeliveryClaimGoodResult> gridList) throws Exception {
		return deliveryStatusService.updateCompleteRetrival(systemPK, gridList);
	}
	
	/**
	 * [맞교환] 회수완료
	 */
	@Override
	public int updateDrctExtCompleteRetrival(SystemPK systemPK, List<DeliveryOrderGoodResult> gridList) throws Exception {
		return deliveryStatusService.updateDrctExtCompleteRetrival(systemPK, gridList);
	}

	/**
	 * 맞교환 품절처리(결품요청)
	 */
	@Override
	public int updateShortage(SystemPK systemPk, List<DeliveryOrderGoodResult> gridList) throws Exception {
		return deliveryStatusService.updateShortage(systemPk, gridList);
	}
	
	/**
	 * [회수리스트] 회수정보 수정
	 */
	@Override
	public void updateRtrvlInfo(SystemPK systemPk, List<DeliveryClaimGoodResult> gridList) throws Exception {
		deliveryStatusService.updateRtrvlInfo(systemPk, gridList);
	}

	/**
	 * [회수리스트] 회수완료 정보 수정
	 */
	@Override
	public void updateRtrvlComptInfo(SystemPK systemPk, List<DeliveryClaimGoodResult> gridList) throws Exception {
		deliveryStatusService.updateRtrvlComptInfo(systemPk, gridList);
	}

	/**
	 * 불량상품 정보 수정
	 */
	@Override
	public void updateFaultyGoodsInfo(SystemPK systemPK, List<DeliveryClaimGoodResult> gridList) throws Exception {
		deliveryStatusService.updateFaultyGoodsInfo(systemPK, gridList);
	}
	
	/** 회수관리 택배사,송장번호,정산구분 배송비 저장.. */
	@Override
	public boolean updateRetrievalGoods(SystemPK systemPK,
			List<DeliveryClaimGoodResult> listDTO,
			DeliveryInvoiceDTO deliveryInvoiceDTO) throws Exception {

		deliveryCommandService.updateRtrvlSimple(systemPK, listDTO, deliveryInvoiceDTO);

		return true;
	}
	
	/**회수관리 회수상태저장.. */
	@Override
	public boolean updateRetrievalGoodsStat(SystemPK systemPK,
			List<DeliveryClaimGoodResult> listDTO,
			DeliveryInvoiceDTO deliveryInvoiceDTO) throws Exception {
		GodShopItmInvExtend godShopItmInvExtendHist = new GodShopItmInvExtend();
		List<DeliveryClaimGoodResult> returnRtrvlList = deliveryCommandService.updateRtrvlStat(systemPK, listDTO, deliveryInvoiceDTO);
		String clmTpCd = "";
		for(DeliveryClaimGoodResult returnRtrvl : returnRtrvlList){
			godShopItmInvExtendHist.setGodNo(returnRtrvl.getClmwg().getGodNo());					// 상품 번호
			godShopItmInvExtendHist.setItmNo(returnRtrvl.getClmwg().getItmNo());					// 단품 번호
			godShopItmInvExtendHist.setShopId("");				// 매장 ID
			if("GNRL_EXCHG".equals(returnRtrvl.getClm().getClmTpCd())) {
				if("EXCHG_DLIVY".equals(returnRtrvl.getParams())) {
					godShopItmInvExtendHist.setSalePrearngeQtyInt(Integer.parseInt(returnRtrvl.getParams2()));	// 총 가용 재고 증감 수량
				} else {
					godShopItmInvExtendHist.setSalePrearngeQtyInt(returnRtrvl.getLgsRdg().getRtrvlDrctQty().intValue());	// 총 가용 재고 증감 수량
				}
				clmTpCd = returnRtrvl.getParams();
				// TODO	상품재작업필요 : goodsHistoryService.insertGodItmInvHistByClaim(godShopItmInvExtendHist, returnRtrvl.getOrd().getOrdNo(), returnRtrvl.getClm().getClmNo(), returnRtrvl.getOrd().getMbrNo(), clmTpCd, "Y");
			} else {
				godShopItmInvExtendHist.setSalePrearngeQtyInt(returnRtrvl.getLgsRdg().getRtrvlDrctQty().intValue() * -1);	// 총 가용 재고 증감 수량
				// TODO	상품재작업필요 : goodsHistoryService.insertGodItmInvHistByClaim(godShopItmInvExtendHist, returnRtrvl.getOrd().getOrdNo(), returnRtrvl.getClm().getClmNo(), returnRtrvl.getOrd().getMbrNo(), returnRtrvl.getClm().getClmTpCd(), "Y");
			}
		}
		return true;
	}
	
	@Override
	public List<ReturnItemListByClaimNoResult> getReturnItemListBySkuWithSerial(ReturnItemListByClaimNoResult returnItemListByClaimNoResult) {
		return deliveryStatusService.getReturnItemListBySkuWithSerial(returnItemListByClaimNoResult);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * Constructors.
	 * ---------------------------------------------------------------------
	 */

}
