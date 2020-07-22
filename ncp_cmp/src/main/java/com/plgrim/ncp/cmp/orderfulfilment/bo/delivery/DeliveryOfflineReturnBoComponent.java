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
package com.plgrim.ncp.cmp.orderfulfilment.bo.delivery;

import java.util.List;

import com.plgrim.ncp.biz.delivery.data.DeliverySearchDTO;
import com.plgrim.ncp.biz.delivery.result.DeliveryOrderGoodResult;
import com.plgrim.ncp.framework.data.SystemPK;

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
 * @since 2015. 3. 30
 */
public interface DeliveryOfflineReturnBoComponent {

    /**
	 *  온라인 주문 반품 리스트 조회.
	 *
	 * @param systemPK [설명]
	 * @param deliverySearchDTO [설명]
	 * @return DeliveryOrderGoodResult list
	 * @throws Exception the exception
	 * @since 2016. 7. 27
	 */
	public List<DeliveryOrderGoodResult> getOfflineReturnList(SystemPK systemPK, DeliverySearchDTO deliverySearchDTO) throws Exception;
	
	/**
	 *  시리얼번호 리스트.
	 *
	 * @param deliverySearchDTO [설명]
	 * @return String list
	 * @throws Exception the exception
	 * @since 2016. 8. 3
	 */
	public List<String> getOrdTurnErpGodSnList(DeliverySearchDTO deliverySearchDTO) throws Exception;
}
