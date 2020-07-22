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
package com.plgrim.ncp.cmp.orderfulfilment.bo.delivery.search;

import java.net.URI;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.json.XML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.plgrim.ncp.base.entities.RepositoryHelper;
import com.plgrim.ncp.base.entities.datasource1.com.Com;
import com.plgrim.ncp.biz.delivery.data.DeliveryAffDTO;
import com.plgrim.ncp.biz.delivery.data.DeliverySearchDTO;
import com.plgrim.ncp.biz.delivery.result.DeliveryHistoryResult;
import com.plgrim.ncp.biz.delivery.result.DeliveryInvoiceResult;
import com.plgrim.ncp.biz.delivery.result.DeliveryOrderGoodResult;
import com.plgrim.ncp.biz.delivery.result.DeliveryRedisplayResult;
import com.plgrim.ncp.biz.delivery.service.DeliveryListService;
import com.plgrim.ncp.biz.delivery.service.DeliveryService;
import com.plgrim.ncp.cmp.orderfulfilment.bo.delivery.DeliverySearchBoComponent;
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
public class DeliverySearchBoComponentImpl implements DeliverySearchBoComponent {

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */

	@Autowired
	DeliveryListService deliveryListService;
	
	@Autowired
	DeliveryService deliveryService;

	/*
	 * ---------------------------------------------------------------------
	 * Constructors.
	 * ---------------------------------------------------------------------
	 */

	/** 자사상품 배송조회. */
	@Override
	public List<DeliveryOrderGoodResult> getDeliveryList(SystemPK systemPK, DeliverySearchDTO deliverySearchDTO) throws Exception {
		RepositoryHelper.makePageEntityIndex(deliverySearchDTO, deliverySearchDTO.getPageParam());	// 페이지 설정
		return deliveryListService.getDeliveryList(systemPK,deliverySearchDTO);
	}

	/** 자사상품 배송조회 total count. */
	@Override
	public int getDeliveryListCount(SystemPK systemPK, DeliverySearchDTO deliverySearchDTO) throws Exception {
		return deliveryListService.getDeliveryListCount(systemPK, deliverySearchDTO);
	}

	/** 자사상품 배송조회 excel. */
	@Override
	public List<Map<String, Object>> getDeliveryListExcel(SystemPK systemPK, DeliverySearchDTO deliverySearchDTO) throws Exception {
		return deliveryListService.getDeliveryListExcel(systemPK, deliverySearchDTO);
	}

	@Override
	public List<DeliveryAffDTO> selectFrgnDeliveryList(String ordNo) throws Exception {
		return deliveryListService.selectFrgnDeliveryList(ordNo);
	}

	/** 배송매장 변경이력 조회. */
	@Override
	public List<DeliveryHistoryResult> getDeliveryShopHistoryList(SystemPK systemPk, DeliverySearchDTO deliverySearchDTO) throws Exception {
		return deliveryListService.getDeliveryShopHistoryList(systemPk, deliverySearchDTO);
	}
	
	/** 판매제휴사 목록 */
	public List<Com> getCompanyList(SystemPK systemPK, DeliverySearchDTO deliverySearchDTO) throws Exception {
		return deliveryListService.getCompanyList(systemPK, deliverySearchDTO);
	}
	
	//** 기본 배송 정책 조회. */
    @Override
    public String selectBaseDlvComCd(String mallId) throws Exception {
     	return deliveryService.selectBaseDlvComCd(mallId);
    }
    
	/** 매장 취급브랜드 조회. */	
	public List<DeliveryInvoiceResult> selectShopBrndList(SystemPK systemPK, DeliverySearchDTO deliverySearchDTO) throws Exception {
		return deliveryListService.selectShopBrndList(systemPK,deliverySearchDTO);
	}
	
	/**
	 * #50212 [개발]픽업 재진열 대상 알림(매장 Dashboard) 기능 추가
	 * 	- 픽업 재진열 리스트 조회
	 *
	 * @param dlvShopId 매장매장ID
	 * @return 픽업 재진열 리스트
	 * @throws Exception
	 */
	public List<DeliveryRedisplayResult> getDeliveryRedisplayList(String dlvShopId) throws Exception {
		return this.deliveryListService.getDeliveryRedisplayList(dlvShopId);
	}
	
	/**
	 * 
	 * 우체국 택배 송장번호로 종추적 조회.
	 * 
	 * @param systemPk
	 * @param deliveryOrderGoodDTO
	 * @return
	 * @throws Exception
	 */
	public String getPostTraceDlv(String dmstcWaybilNo) throws Exception {
		return deliveryService.getPostTraceDlv(dmstcWaybilNo);
	}
}
