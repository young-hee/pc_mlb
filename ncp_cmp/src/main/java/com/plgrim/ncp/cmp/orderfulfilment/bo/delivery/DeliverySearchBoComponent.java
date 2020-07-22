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

import org.json.JSONObject;

import com.plgrim.ncp.base.entities.datasource1.com.Com;
import com.plgrim.ncp.biz.delivery.data.DeliveryAffDTO;
import com.plgrim.ncp.biz.delivery.data.DeliverySearchDTO;
import com.plgrim.ncp.biz.delivery.result.DeliveryHistoryResult;
import com.plgrim.ncp.biz.delivery.result.DeliveryInvoiceResult;
import com.plgrim.ncp.biz.delivery.result.DeliveryOrderGoodResult;
import com.plgrim.ncp.biz.delivery.result.DeliveryRedisplayResult;
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
public interface DeliverySearchBoComponent {

	/**
	 * 자사상품 배송조회.
	 *
	 * <p/>
	 *
	 * @param systemPK [설명]
	 * @param deliverySearchDTO [설명]
	 * @return the delivery list
	 * @throws Exception the exception
	 * @since 2015. 4. 24
	 */
	public List<DeliveryOrderGoodResult> getDeliveryList(SystemPK systemPK, DeliverySearchDTO deliverySearchDTO) throws Exception;

	/**
	 * 자사상품 배송조회 totla count.
	 *
	 * <p/>
	 *
	 * @param systemPK [설명]
	 * @param deliverySearchDTO [설명]
	 * @return the delivery list count
	 * @throws Exception the exception
	 * @since 2015. 4. 24
	 */
	public int getDeliveryListCount(SystemPK systemPK, DeliverySearchDTO deliverySearchDTO) throws Exception ;

	/**
	 * 자사상품 배송조회 excel.
	 *
	 * <p/>
	 *
	 * @param systemPK [설명]
	 * @param deliverySearchDTO [설명]
	 * @return the delivery list
	 * @throws Exception the exception
	 * @since 2015. 4. 24
	 */
	public List<Map<String, Object>> getDeliveryListExcel(SystemPK systemPK, DeliverySearchDTO deliverySearchDTO) throws Exception;

	/**
	 * 개별상품 해외 배송 현황
	 *
	 * Select frgn delivery list.
	 *
	 * @param ordNo the ord no
	 * @return the list
	 * @throws Exception the exception
	 */
	public List<DeliveryAffDTO> selectFrgnDeliveryList(String ordNo) throws Exception;

	/**
	 * [메서드 설명].
	 * 배송매장 변경이력 팝업.
	 *
	 * <p/>
	 *
	 * @param systemPk [설명]
	 * @param deliverySearchDTO [설명]
	 * @return the delivery shop history list
	 * @throws Exception the exception
	 * @since 2015. 4. 2
	 */
	public List<DeliveryHistoryResult> getDeliveryShopHistoryList(SystemPK systemPk, DeliverySearchDTO deliverySearchDTO) throws Exception;

	/**
	 * 판매제휴사 목록
	 *
	 * <p/>
	 *
	 * @param systemPK [설명]
	 * @param deliverySearchDTO [설명]
	 * @return the delivery list
	 * @throws Exception the exception
	 * @since 2015. 4. 24
	 */
	public List<Com> getCompanyList(SystemPK systemPK, DeliverySearchDTO deliverySearchDTO) throws Exception;
	
    /**
     * 기본 배송 정책 조회.
     * @Description : 기본 배송 정책 조회.
     * @Author
     * @param
     * @return
     */
    public String selectBaseDlvComCd(String mallId) throws Exception;
    
	/**
	 * 매장 취급브랜드 조회.
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param systemPK [설명]
	 * @param deliverySearchDTO the delivery search dto
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 8. 18
	 */
	public List<DeliveryInvoiceResult> selectShopBrndList(SystemPK systemPK, DeliverySearchDTO deliverySearchDTO) throws Exception;
	
	/**
	 * #50212 [개발]픽업 재진열 대상 알림(매장 Dashboard) 기능 추가
	 * 	- 픽업 재진열 리스트 조회
	 *
	 * @param dlvShopId 매장매장ID
	 * @return 픽업 재진열 리스트
	 * @throws Exception
	 */
	List<DeliveryRedisplayResult> getDeliveryRedisplayList(String dlvShopId) throws Exception;
	
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
