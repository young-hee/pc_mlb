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
package com.plgrim.ncp.cmp.orderfulfilment.batch.delivery.search;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.plgrim.ncp.biz.delivery.service.DeliveryService;
import com.plgrim.ncp.cmp.orderfulfilment.batch.delivery.DeliveryBatchComponent;


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
 */
@Transactional(value = "transactionManager")
@Component
public class DeliveryBatchComponentImpl implements DeliveryBatchComponent {
	
	@Autowired
	DeliveryService deliveryService;

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
