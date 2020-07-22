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
public interface DeliveryInspectBoComponent {

	/**
	 *  복품출고 검수 리스트 조회.
	 *
	 * @param systemPK [설명]
	 * @param deliverySearchDTO [설명]
	 * @return the goods inspect list
	 * @throws Exception the exception
	 * @since 2015. 5. 16
	 */
	public List<DeliveryOrderGoodResult> getGoodsInspectList(SystemPK systemPK, DeliverySearchDTO deliverySearchDTO) throws Exception;

    /**
     * 상품 검수정보 조회.
     *
     * @param systemPK the system pk
     * @param deliverySearchDTO the delivery search dto
     * @return the list
     * @throws Exception the exception
     */
    public List<DeliveryOrderGoodResult> selectItemCheckList(SystemPK systemPK, DeliverySearchDTO deliverySearchDTO) throws Exception;

	/**
	 * 시리얼번호 중복체크.
	 *
	 * <p/>
	 *
	 * @param systemPK [설명]
	 * @param deliverySearchDTO [설명]
	 * @return the used erp god sn cnt
	 * @throws Exception the exception
	 * @since 2015. 5. 28
	 */
	public int isUsedErpGodSnCnt(SystemPK systemPK, DeliverySearchDTO deliverySearchDTO) throws Exception;

    /**
     * 출고상품 검수.
     *
     * <p/>
     *
     * [사용 방법 설명].
     *
     * @param systemPk [설명]
     * @param dto [설명]
     * @return String [설명]
     * @throws Exception the exception
     * @since 2015. 5. 18
     */
    public String inspectDeliveryGood(SystemPK systemPk, DeliveryOrderGoodDTO dto) throws Exception;

    /**
     * 검수 취소.
     *
     * <p/>
     *
     * [사용 방법 설명].
     *
     * @param systemPk [설명]
     * @param dto [설명]
     * @throws Exception the exception
     * @since 2015. 6. 2
     */
    public void resetInspection(SystemPK systemPk, DeliveryOrderGoodDTO dto) throws Exception;
    
    /**
     * 상품입고검수.
     * 
     * <p/>
     * 
     * [사용 방법 설명].
     *
     * @param systemPk [설명]
     * @param dto [설명]
     * @return String [설명]
     * @throws Exception the exception
     * @since 2015. 6. 22
     */
    public String retrievalDeliveryGood(SystemPK systemPk, DeliveryOrderGoodDTO dto) throws Exception;
    
    /**
     * 출고검수여부 수정
     *
     * @param SystemPK [설명]
     * @param DeliveryOrderGoodDTO [설명]
     * @return Int [설명]
     * @throws Exception the exception
     * @since 2016. 7. 13
     */
    public void updateAcptYn(SystemPK systemPk, DeliveryOrderGoodDTO dto) throws Exception;
    
    /**
     * ERP매출전송.
     *
     * <p/>
     *
     * [사용 방법 설명].
     *
     * @param systemPk [설명]
     * @param gridList [설명]
     * @throws Exception the exception
     * @since 2015. 5. 26
     */
    public void transToErp(SystemPK systemPk, List<DeliveryOrderGoodResult> gridList) throws Exception;
    
    /**
     * 매장픽업 결품 처리
     *
     * <p/>
     *
     * [사용 방법 설명].
     *
     * @param systemPk [설명]
     * @param gridList [설명]
     * @throws Exception the exception
     * @since 2015. 5. 26
     */
    public void shopShortage(SystemPK systemPk, List<DeliveryOrderGoodResult> gridList) throws Exception;
    

}
