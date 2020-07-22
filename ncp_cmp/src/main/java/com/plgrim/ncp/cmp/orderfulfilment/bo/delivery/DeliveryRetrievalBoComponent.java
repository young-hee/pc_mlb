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

import com.plgrim.ncp.base.entities.datasource1.lgs.LgsDlvRtrvlDrctHist;
import com.plgrim.ncp.biz.delivery.data.DeliveryInvoiceDTO;
import com.plgrim.ncp.biz.delivery.data.DeliverySearchDTO;
import com.plgrim.ncp.biz.delivery.result.DeliveryClaimGoodResult;
import com.plgrim.ncp.biz.delivery.result.DeliveryInvoiceResult;
import com.plgrim.ncp.biz.delivery.result.DeliveryOrderGoodResult;
import com.plgrim.ncp.biz.delivery.result.ReturnItemListByClaimNoResult;
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
public interface DeliveryRetrievalBoComponent {

	/**
	 * 자사상품 회수리스트 조회.
	 *
	 * <p/>
	 *
	 * @param systemPk [설명]
	 * @param deliverySearchDTO [설명]
	 * @return the retrieval goods list
	 * @throws Exception the exception
	 * @since 2015. 4. 29
	 */
	public  List<DeliveryClaimGoodResult> getRetrievalGoodsList(SystemPK systemPk, DeliverySearchDTO deliverySearchDTO) throws Exception;

	/**
	 * 자사상품 회수리스트 조회 row count.
	 *
	 * <p/>
	 *
	 * @param systemPk [설명]
	 * @param deliverySearchDTO [설명]
	 * @return the retrieval goods list
	 * @throws Exception the exception
	 * @since 2015. 4. 29
	 */
	public  int  getRetrievalGoodsListCount(SystemPK systemPk, DeliverySearchDTO deliverySearchDTO) throws Exception;

	/**
	 *  자사상품 회수리스트 조회 excel.
	 *
	 * @param systemPK [설명]
	 * @param deliverySearchDTO [설명]
	 * @return the delivery list
	 * @throws Exception the exception
	 * @since 2015. 4. 24
	 */
	public List<Map<String, Object>> getRetrievalGoodsListExcel(SystemPK systemPK, DeliverySearchDTO deliverySearchDTO) throws Exception;

	/**
	 *  상품회수처리 조회.
	 *
	 * @param systemPK [설명]
	 * @param deliverySearchDTO [설명]
	 * @return List<DeliveryOrderGoodResult>
	 * @throws Exception the exception
	 * @since 2015. 5. 16
	 */
	public List<DeliveryClaimGoodResult> getCompleteRetrievalList(SystemPK systemPK, DeliverySearchDTO deliverySearchDTO) throws Exception;

	/**
	 * 클레임 운송장정보 상세 조회.
	 *
	 * <p/>
	 *
	 * @param systemPK [설명]
	 * @param deliverySearchDTO [설명]
	 * @return the delivery list
	 * @throws Exception the exception
	 * @since 2015. 12. 09
	 */
	public DeliveryInvoiceResult getClmInvoice(SystemPK systemPK, DeliverySearchDTO deliverySearchDTO) throws Exception;

	/**
	 * 운송장정보 이력 조회.
	 *
	 * <p/>
	 *
	 * @param systemPK [설명]
	 * @param deliverySearchDTO [설명]
	 * @return the delivery list
	 * @throws Exception the exception
	 * @since 2015. 12. 09
	 */
	public List<DeliveryInvoiceResult>getClmInvoiceHistoryList(SystemPK systemPK, DeliverySearchDTO deliverySearchDTO) throws Exception;
	
    /**
     * 회수완료.
     *
     * <p/>
     *
     * [사용 방법 설명].
     *
     * @param systemPK [설명]
     * @param gridList [설명]
     * @return Int [설명]
     * @throws Exception the exception
     * @since 2015. 5. 27
     */
    public int updateCompleteRetrival(SystemPK systemPK, List<DeliveryClaimGoodResult> gridList) throws Exception;
    
    /**
     * 맞교환 회수완료.
     *
     * <p/>
     *
     * [사용 방법 설명].
     *
     * @param systemPK [설명]
     * @param gridList [설명]
     * @return Int [설명]
     * @throws Exception the exception
     * @since 2015. 5. 27
     */
    public int updateDrctExtCompleteRetrival(SystemPK systemPK, List<DeliveryOrderGoodResult> gridList) throws Exception;


    /**
     *  [회수리스트] 회수완료 정보 수정.
     *
     * @param systemPk [설명]
     * @param gridList [설명]
     * @throws Exception the exception
     * @since 2015. 5. 13
     */
    public void updateRtrvlComptInfo(SystemPK systemPk, List<DeliveryClaimGoodResult> gridList) throws Exception;
    
    
    /**
     *  [회수리스트] 회수정보 수정.
     *
     * @param systemPk [설명]
     * @param gridList [설명]
     * @throws Exception the exception
     * @since 2015. 5. 13
     */
    public void updateRtrvlInfo(SystemPK systemPk, List<DeliveryClaimGoodResult> gridList) throws Exception;

    /**
     * 불량상품 정보 수정.
     *
     * <p/>
     *
     * [사용 방법 설명].
     *
     * @param systemPK [설명]
     * @param gridList [설명]
     * @throws Exception the exception
     * @since 2015. 5. 13
     */
    public void updateFaultyGoodsInfo(SystemPK systemPK, List<DeliveryClaimGoodResult> gridList) throws Exception;


    /**
     * 맞교환 품절처리(결품요청).
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
    public int updateShortage(SystemPK systemPk, List<DeliveryOrderGoodResult> gridList) throws Exception;
    
    /**
     * 회수관리 택배사,송장번호,정산구분 배송비 저장.
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
    public boolean updateRetrievalGoods(SystemPK systemPK, List<DeliveryClaimGoodResult> listDTO , DeliveryInvoiceDTO deliveryInvoiceDTO)  throws Exception;

    /**
     * 회수관리 회수상태저장.
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
    public boolean updateRetrievalGoodsStat(SystemPK systemPK, List<DeliveryClaimGoodResult> listDTO , DeliveryInvoiceDTO deliveryInvoiceDTO)  throws Exception;
    
	/**
	 * 품번 시리얼 반품배송클레임상품리스트 조회.
	 *
	 * @param returnItemListByClaimNoResult the return item list by claim no result
	 * @return List<ReturnItemWithWayBillResult>
	 */
    public List<ReturnItemListByClaimNoResult> getReturnItemListBySkuWithSerial(ReturnItemListByClaimNoResult returnItemListByClaimNoResult);
    
}
