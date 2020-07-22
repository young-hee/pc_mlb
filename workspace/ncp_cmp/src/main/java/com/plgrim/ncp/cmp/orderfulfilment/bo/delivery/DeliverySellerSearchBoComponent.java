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

import com.plgrim.ncp.biz.delivery.data.DeliveryInvoiceDTO;
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
public interface DeliverySellerSearchBoComponent {

	/**
	 * 입점업체배송리스트 조회.
	 *
	 * <p/>
	 *
	 * @param systemPK [설명]
	 * @param deliverySearchDTO [설명]
	 * @return the delivery list
	 * @throws Exception the exception
	 * @since 2015. 4. 24
	 */
	public List<DeliveryOrderGoodResult> getPartMallDeliveryList(SystemPK systemPK, DeliverySearchDTO deliverySearchDTO) throws Exception;

	/**
	 *  입점업체배송리스트 조회. totla count.
	 *
	 * <p/>
	 *
	 * @param systemPK [설명]
	 * @param deliverySearchDTO [설명]
	 * @return the delivery list count
	 * @throws Exception the exception
	 * @since 2015. 4. 24
	 */
	public int getPartMallDeliveryListCount(SystemPK systemPK, DeliverySearchDTO deliverySearchDTO) throws Exception ;

	/**
	 *  입점업체배송리스트 조회. excel.
	 *
	 * <p/>
	 *
	 * @param systemPK [설명]
	 * @param deliverySearchDTO [설명]
	 * @return the delivery list
	 * @throws Exception the exception
	 * @since 2015. 4. 24
	 */
	public List<Map<String, Object>> getPartMallDeliveryListExcel(SystemPK systemPK, DeliverySearchDTO deliverySearchDTO) throws Exception;

	/**
	 *  PO 입점업체 결품접수 리스트 조회.
	 *
	 * <p/>
	 *
	 * @param systemPK [설명]
	 * @param deliverySearchDTO [설명]
	 * @return the delivery list
	 * @throws Exception the exception
	 * @since 2015.11. 24
	 */
	public List<DeliveryOrderGoodResult> getPoPartMallOrderGoodLackList(SystemPK systemPK, DeliverySearchDTO deliverySearchDTO) throws Exception;

	/**
	 *  PO 입점업체 결품접수 리스트 조회 total count.
	 *
	 * <p/>
	 *
	 * @param systemPK [설명]
	 * @param deliverySearchDTO [설명]
	 * @return the delivery list
	 * @throws Exception the exception
	 * @since 2015.11. 24
	 */
	public int getPoPartMallOrderGoodLackListCount(SystemPK systemPK, DeliverySearchDTO deliverySearchDTO) throws Exception;

	/**
	 *  PO 입점업체 주문취소 리스트 조회.
	 *
	 * <p/>
	 *
	 * @param systemPK [설명]
	 * @param deliverySearchDTO [설명]
	 * @return the delivery list
	 * @throws Exception the exception
	 * @since 2015.11. 24
	 */
	public List<DeliveryOrderGoodResult> getPoPartMallOrderCancelList(SystemPK systemPK, DeliverySearchDTO deliverySearchDTO) throws Exception;

	/**
	 *  PO 입점업체 주문취소 리스트 조회 total count.
	 *
	 * <p/>
	 *
	 * @param systemPK [설명]
	 * @param deliverySearchDTO [설명]
	 * @return the delivery list
	 * @throws Exception the exception
	 * @since 2015.11. 24
	 */
	public int getPoPartMallOrderCancelListCount(SystemPK systemPK, DeliverySearchDTO deliverySearchDTO) throws Exception;

	/**
	 *  PO 입점업체배송리스트 조회. excel.
	 *
	 * <p/>
	 *
	 * @param systemPK [설명]
	 * @param deliverySearchDTO [설명]
	 * @return the delivery list
	 * @throws Exception the exception
	 * @since 2015. 4. 24
	 */
	public List<Map<String, Object>> getPoPartMallDeliveryListExcel(SystemPK systemPK, DeliverySearchDTO deliverySearchDTO) throws Exception;

	/**
	 *  PO 입점업체 결품접수 조회. excel.
	 *
	 * <p/>
	 *
	 * @param systemPK [설명]
	 * @param deliverySearchDTO [설명]
	 * @return the delivery list
	 * @throws Exception the exception
	 * @since 2015. 4. 24
	 */
	public List<Map<String, Object>> getPoPartMallOrderGoodLackListExcel(SystemPK systemPK, DeliverySearchDTO deliverySearchDTO) throws Exception;

	/**
	 *  PO 입점업체 주문취소 조회. excel.
	 *
	 * <p/>
	 *
	 * @param systemPK [설명]
	 * @param deliverySearchDTO [설명]
	 * @return the delivery list
	 * @throws Exception the exception
	 * @since 2015. 4. 24
	 */
	public List<Map<String, Object>> getPoPartMallOrderCancelListExcel(SystemPK systemPK, DeliverySearchDTO deliverySearchDTO) throws Exception;
	
    /**
     * 입점업체저장(택배사,송장번호,배송상태,배송매장메모).
     *
     * <p/>
     *
     * [사용 방법 설명].
     *
     * @param systemPK [설명]
     * @param listDTO [설명]
     * @param deliveryInvoiceDTO [설명]
     * @return boolean
     * @throws Exception the exception
     * @since 2015. 5. 19
     */
    public boolean updatePartMall(SystemPK systemPK, List<DeliveryOrderGoodResult> listDTO , DeliveryInvoiceDTO deliveryInvoiceDTO)  throws Exception;
    
    /**
     * 입점업체 주문확인
     *
     * <p/>
     *
     * [사용 방법 설명].
     *
     * @param systemPK [설명]
     * @param listDTO [설명]
     * @param deliveryInvoiceDTO [설명]
     * @return boolean
     * @throws Exception the exception
     * @since 2015. 11. 16
     */
    public String updateOrderConfirmPartMall(SystemPK systemPK, List<DeliveryOrderGoodResult> listDTO , DeliveryInvoiceDTO deliveryInvoiceDTO)  throws Exception;
    
    /**
     * 입점업체 운송장번호등록
     *
     * <p/>
     *
     * [사용 방법 설명].
     *
     * @param systemPK [설명]
     * @param listDTO [설명]
     * @param deliveryInvoiceDTO [설명]
     * @return boolean
     * @throws Exception the exception
     * @since 2015. 11. 16
     */
    public boolean updateInvoicePartMall(SystemPK systemPK, List<DeliveryOrderGoodResult> listDTO , DeliveryInvoiceDTO deliveryInvoiceDTO)  throws Exception;
    
    /**
     * 입점업체 결품접수
     *
     * <p/>
     *
     * [사용 방법 설명].
     *
     * @param systemPk [설명]
     * @param gridList [설명]
     * @return int
     * @throws Exception the exception
     * @since 2015. 4. 8
     */
    public String updatePartMallShortage(SystemPK systemPk, List<DeliveryOrderGoodResult> gridList) throws Exception;  
    
    /**
     * 출고완료 처리.
     *
     * <p/>
     *
     * [사용 방법 설명].
     *
     * @param systemPk [설명]
     * @param gridList [설명]
     * @return int
     * @throws Exception the exception
     * @since 2015. 4. 8
     */
    public boolean updateCompleteDelivery(SystemPK systemPK, List<DeliveryOrderGoodResult> listDTO , DeliveryInvoiceDTO deliveryInvoiceDTO)  throws Exception;  
}
