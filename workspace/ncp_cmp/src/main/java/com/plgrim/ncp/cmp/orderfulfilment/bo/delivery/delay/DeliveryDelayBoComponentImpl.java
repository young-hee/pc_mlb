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
package com.plgrim.ncp.cmp.orderfulfilment.bo.delivery.delay;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.plgrim.ncp.base.entities.RepositoryHelper;
import com.plgrim.ncp.biz.delivery.data.DeliverySearchDTO;
import com.plgrim.ncp.biz.delivery.result.DeliveryOrderGoodResult;
import com.plgrim.ncp.biz.delivery.service.DeliveryListService;
import com.plgrim.ncp.cmp.orderfulfilment.bo.delivery.DeliveryDelayBoComponent;
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
public class DeliveryDelayBoComponentImpl implements DeliveryDelayBoComponent {

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	@Autowired
	DeliveryListService deliveryListService;

	/** 배송지연리스트 조회. */
	@Override
	public List<DeliveryOrderGoodResult> getDelayDeliveryList(SystemPK systemPk, DeliverySearchDTO deliverySearchDTO) throws Exception {
		RepositoryHelper.makePageEntityIndex(deliverySearchDTO, deliverySearchDTO.getPageParam());	// 페이지 설정
		return deliveryListService.getDelayDeliveryList(systemPk, deliverySearchDTO);
	}

	/** 배송지연 상품관리 total count. */
	@Override
	public int getDelayDeliveryListCount(SystemPK systemPK, DeliverySearchDTO deliverySearchDTO) throws Exception {
		return deliveryListService.getDelayDeliveryListCount(systemPK,deliverySearchDTO);
	}

	/** 배송지연 상품관리 조회 excel. */
	@Override
	public List<Map<String, Object>> getDelayDeliveryListExcel(SystemPK systemPK, DeliverySearchDTO deliverySearchDTO) throws Exception {
		return deliveryListService.getDelayDeliveryListExcel(systemPK, deliverySearchDTO);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Constructors.
	 * ---------------------------------------------------------------------
	 */

}
