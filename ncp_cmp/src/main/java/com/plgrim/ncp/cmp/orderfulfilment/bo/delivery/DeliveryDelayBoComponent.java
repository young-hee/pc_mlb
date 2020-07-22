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
import java.util.Map;

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
public interface DeliveryDelayBoComponent {

	/**
	 * [메서드 설명].
	 * 배송지연 상품관리 조회.
	 *
	 * <p/>
	 *
	 * @param systemPk [설명]
	 * @param deliverySearchDTO [설명]
	 * @return the delay delivery list
	 * @throws Exception the exception
	 * @since 2015. 3. 30
	 */
	public List<DeliveryOrderGoodResult> getDelayDeliveryList(SystemPK systemPk, DeliverySearchDTO deliverySearchDTO) throws Exception;

	/**
	 * 배송지연 상품관리 조회 total count
	 *
	 * <p/>
	 *
	 * @param systemPk [설명]
	 * @param deliverySearchDTO [설명]
	 * @return the delay delivery list
	 * @throws Exception the exception
	 * @since 2015. 3. 30
	 */
	public int getDelayDeliveryListCount(SystemPK systemPk, DeliverySearchDTO deliverySearchDTO) throws Exception;

	/**
	 *  배송지연 상품관리 조회. excel.
	 *
	 * <p/>
	 *
	 * @param systemPK [설명]
	 * @param deliverySearchDTO [설명]
	 * @return the delivery list
	 * @throws Exception the exception
	 * @since 2015. 4. 24
	 */
	public List<Map<String, Object>> getDelayDeliveryListExcel(SystemPK systemPK, DeliverySearchDTO deliverySearchDTO) throws Exception;


}
