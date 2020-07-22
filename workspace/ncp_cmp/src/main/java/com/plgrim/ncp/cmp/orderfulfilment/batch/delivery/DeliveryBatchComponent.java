/* Copyright (c) 2015 plgrim, Inc.
 * All right reserved.
 * http://plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 *
 * ------------------------------------------------------------------------
 * @author      jwcale.kim
 * @since       2015. 8. 18
 */
package com.plgrim.ncp.cmp.orderfulfilment.batch.delivery;

import org.json.JSONObject;

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
public interface DeliveryBatchComponent {

	/**
	 * 
	 * 우체국 택배 송장번호로 종추적 조회.
	 * 
	 * @param systemPk
	 * @param deliveryOrderGoodDTO
	 * @return
	 * @throws Exception
	 */
	public String getPostTraceDlv(String dmstcWaybilNo) throws Exception;
	
}
