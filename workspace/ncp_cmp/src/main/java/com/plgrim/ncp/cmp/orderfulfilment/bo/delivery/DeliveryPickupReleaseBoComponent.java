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

import com.plgrim.ncp.biz.delivery.data.DeliveryOrderGoodDTO;
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
public interface DeliveryPickupReleaseBoComponent {

	/**
	 * 매장픽업 주문관리 조회.
	 *
	 * <p/>
	 *
	 * @param systemPK [설명]
	 * @param deliverySearchDTO [설명]
	 * @return the delivery list
	 * @throws Exception the exception
	 * @since 2015. 4. 24
	 */
	public List<DeliveryOrderGoodResult> getStorePickupOrderList(SystemPK systemPK, DeliverySearchDTO deliverySearchDTO) throws Exception;

	/**
	 *  매장픽업 주문관리. totla count.
	 *
	 * <p/>
	 *
	 * @param systemPK [설명]
	 * @param deliverySearchDTO [설명]
	 * @return the delivery list count
	 * @throws Exception the exception
	 * @since 2015. 4. 24
	 */
	public int getStorePickupOrderListCount(SystemPK systemPK, DeliverySearchDTO deliverySearchDTO) throws Exception ;

	/**
	 *  매장픽업 주문관리 조회. excel.
	 *
	 * <p/>
	 *
	 * @param systemPK [설명]
	 * @param deliverySearchDTO [설명]
	 * @return the delivery list
	 * @throws Exception the exception
	 * @since 2015. 4. 24
	 */
	public List<Map<String, Object>> getStorePickupOrderListExcel(SystemPK systemPK, DeliverySearchDTO deliverySearchDTO) throws Exception;

	/**
	 * 전체 주문상품 수량.
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param systemPK [설명]
	 * @param deliverySearchDTO [설명]
	 * @return Int [설명]
	 * @throws Exception the exception
	 * @since 2015. 7. 25
	 */
	public int selectOrdGodCount(SystemPK systemPK, DeliverySearchDTO deliverySearchDTO) throws Exception;
	
    /**
     * 매장픽업 준비완료 SMS 발송.
     * 
     * @param systemPk [설명]
     * @param gridList [설명]
     * @return Int [설명]
     * @throws Exception the exception
     * @since 2015. 8. 29
     */
    public int sendPickupReadySms(SystemPK systemPk, List<DeliveryOrderGoodResult> gridList) throws Exception;
    
    /**
     * [고객센터] 픽업 결품 철회
     *
     * <p/>
     *
     * [사용 방법 설명].
     *
     * @param systemPk [설명]
     * @param gridList [설명]
     * @return String
     * @throws Exception the exception
     * @since 2017. 5. 12
     */
    public String modifyShortageCancelForPickup (SystemPK systemPk, List<DeliveryOrderGoodResult> gridList) throws Exception;

    /**
     * 매장전용] #50212 픽업 재진열 대상 알림(매장 Dashboard) 기능 추가
     * 	- '픽업매장 재진열 완료' 수정
     *
     * @param systemPk
     * @param deliveryOrderGoodDTO
     * @return
     * @throws Exception
     */
    String updateRedispCompt(SystemPK systemPk, DeliveryOrderGoodDTO deliveryOrderGoodDTO) throws Exception;
}
