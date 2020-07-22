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
package com.plgrim.ncp.cmp.orderfulfilment.bo.delivery.offlinereturn;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.plgrim.ncp.biz.delivery.data.DeliverySearchDTO;
import com.plgrim.ncp.biz.delivery.result.DeliveryOrderGoodResult;
import com.plgrim.ncp.biz.delivery.service.DeliveryListService;
import com.plgrim.ncp.cmp.orderfulfilment.bo.delivery.DeliveryOfflineReturnBoComponent;
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
public class DeliveryOfflineReturnBoComponentImpl implements DeliveryOfflineReturnBoComponent {

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	@Autowired
	DeliveryListService deliveryListService;

	/** 온라인 주문 반품 리스트 조회 */
	@Override
	public List<DeliveryOrderGoodResult> getOfflineReturnList(SystemPK systemPK, DeliverySearchDTO deliverySearchDTO) throws Exception {
		return deliveryListService.getOfflineReturnList(systemPK, deliverySearchDTO);
	}
	
	/** 시리얼번호 리스트 */
	public List<String> getOrdTurnErpGodSnList(DeliverySearchDTO deliverySearchDTO) throws Exception {
		return deliveryListService.getOrdTurnErpGodSnList(deliverySearchDTO);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * Constructors.
	 * ---------------------------------------------------------------------
	 */

}
