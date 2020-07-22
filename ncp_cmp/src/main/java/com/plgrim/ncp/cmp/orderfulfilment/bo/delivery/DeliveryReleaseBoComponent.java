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

import org.springframework.data.domain.Page;

import com.plgrim.ncp.base.entities.datasource1.sys.SysShopExtends;
import com.plgrim.ncp.biz.delivery.data.DeliveryInvoiceDTO;
import com.plgrim.ncp.biz.delivery.data.DeliveryOrderGoodDTO;
import com.plgrim.ncp.biz.delivery.data.DeliverySearchDTO;
import com.plgrim.ncp.biz.delivery.result.DeliveryInvoiceResult;
import com.plgrim.ncp.biz.delivery.result.DeliveryOrderGoodResult;
import com.plgrim.ncp.biz.vendor.data.VendorBrndSearchDTO;
import com.plgrim.ncp.biz.vendor.result.VendorBrndListResult;
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
public interface DeliveryReleaseBoComponent {

	/**
	 * 자사상품 출고관리 조회.
	 *
	 * <p/>
	 *
	 * @param systemPK [설명]
	 * @param deliverySearchDTO [설명]
	 * @return the drct goods list
	 * @throws Exception the exception
	 * @since 2015. 7. 16
	 */
	public Page<DeliveryOrderGoodResult> getDrctGoodsList(SystemPK systemPK, DeliverySearchDTO deliverySearchDTO) throws Exception;

	/**
	 *  자사상품 출고관리 조회. excel.
	 *
	 * <p/>
	 *
	 * @param systemPK [설명]
	 * @param deliverySearchDTO [설명]
	 * @return the delivery list
	 * @throws Exception the exception
	 * @since 2015. 4. 24
	 */
	public List<Map<String, Object>> getDrctGoodsListExcel(SystemPK systemPK, DeliverySearchDTO deliverySearchDTO) throws Exception;

	/**
	 * 상품출고배정 조회.
	 *
	 * <p/>
	 *
	 * @param systemPk [설명]
	 * @param deliverySearchDTO [설명]
	 * @return the self assign delivery list
	 * @throws Exception the exception
	 * @since 2015. 5. 6
	 */
	public  List<DeliveryOrderGoodResult> getSelfAssignDeliveryList(SystemPK systemPk, DeliverySearchDTO deliverySearchDTO) throws Exception;

	/**
	 * 상품출고배정 조회 rowcount.
	 *
	 * @param systemPk [설명]
	 * @param deliverySearchDTO [설명]
	 * @return the trans to erp list
	 * @throws Exception the exception
	 * @since 2015. 5. 11
	 */
	public  int getSelfAssignDeliveryListCount(SystemPK systemPk, DeliverySearchDTO deliverySearchDTO) throws Exception;

	/**
	 *  매장별 취급 브랜드 리스트
	 *
	 * @param String [설명]
	 * @return String list
	 * @throws Exception the exception
	 * @since 2016. 8. 12
	 */
	public List<SysShopExtends> getBrndList(String shopId) throws Exception;

    /**
     * 배정가능 배송매장 조회.
     *
     * @param systemPK the system pk
     * @param vendorBrndSearchDTO the vendor brnd search dto
     * @return the list
     * @throws Exception the exception
     */
    public List<VendorBrndListResult> selectAssignDlvShop(SystemPK systemPK, VendorBrndSearchDTO vendorBrndSearchDTO) throws Exception;

	/**
	 * 운송장정보 상세 조회.
	 *
	 * <p/>
	 *
	 * @param systemPK [설명]
	 * @param deliverySearchDTO [설명]
	 * @return the delivery list
	 * @throws Exception the exception
	 * @since 2015. 4. 24
	 */
	public DeliveryInvoiceResult getInvoice(SystemPK systemPK, DeliverySearchDTO deliverySearchDTO) throws Exception;

	/**
	 * 운송장정보 이력 조회.
	 *
	 * <p/>
	 *
	 * @param systemPK [설명]
	 * @param deliverySearchDTO [설명]
	 * @return the delivery list
	 * @throws Exception the exception
	 * @since 2015. 4. 24
	 */
	public List<DeliveryInvoiceResult>getInvoiceHistoryList(SystemPK systemPK, DeliverySearchDTO deliverySearchDTO) throws Exception;

	/**
	 *  사은품 출고관리조회.
	 *
	 * @param systemPK [설명]
	 * @param deliverySearchDTO [설명]
	 * @return List<DeliveryOrderGoodResult>
	 * @throws Exception the exception
	 * @since 2015. 5. 16
	 */
	public List<DeliveryOrderGoodResult> getGiftDrctGoodsList(SystemPK systemPK, DeliverySearchDTO deliverySearchDTO) throws Exception;

	/**
	 *  사은품 출고관리조회 rowcount.
	 *
	 * @param systemPK [설명]
	 * @param deliverySearchDTO [설명]
	 * @return int
	 * @throws Exception the exception
	 * @since 2015. 5. 16
	 */
	public int getGiftDrctGoodsListCount(SystemPK systemPK, DeliverySearchDTO deliverySearchDTO) throws Exception;

	/**
	 *  사은품 출고관리조회 excel.
	 *
	 * @param systemPK [설명]
	 * @param deliverySearchDTO [설명]
	 * @return int
	 * @throws Exception the exception
	 * @since 2015. 5. 16
	 */
	public List<Map<String, Object>> getGiftDrctGoodsListExcel(SystemPK systemPK, DeliverySearchDTO deliverySearchDTO) throws Exception;

	/**
	 *  사은품 Picking List.
	 *
	 * @param systemPK [설명]
	 * @param deliverySearchDTO [설명]
	 * @return List<DeliveryOrderGoodResult>
	 * @throws Exception the exception
	 * @since 2015. 5. 16
	 */
	public List<DeliveryOrderGoodResult> getGiftDrctGoodsPickingList(SystemPK systemPK, DeliverySearchDTO deliverySearchDTO) throws Exception;

    /**
     * 결품처리(재배정).
     * 
     * <p/>
     * 
     * [사용 방법 설명].
     *
     * @param systemPk [설명]
     * @param obj1 [설명]
     * @param obj2 [설명]
     * @throws Exception the exception
     * @since 2015. 11. 5
     */
    public void shortageReassign(SystemPK systemPk, DeliveryOrderGoodResult obj1, DeliverySearchDTO obj2) throws Exception;
    
    /**
     * [물류센터전용] 물류결품처리..
     * 
     * <p/>
     * 
     * [사용 방법 설명].
     *
     * @param systemPk [설명]
     * @param obj1 [설명]
     * @param obj2 [설명]
     * @throws Exception the exception
     * @since 2015. 11. 4
     */
    public void dstrbtCenterShortage(SystemPK systemPk, DeliveryOrderGoodResult obj1, DeliveryOrderGoodResult obj2) throws Exception;
    
    /**
     * 상품출고지 배정(재배정).
     * 
     * <p/>
     * 
     * [사용 방법 설명].
     *
     * @param systemPk [설명]
     * @param obj1 [설명]
     * @param obj2 [설명]
     * @param targetShop [설명]
     * @throws Exception the exception
     * @since 2015. 11. 10
     */
    public void assignDleliveryShop(SystemPK systemPk, DeliveryOrderGoodResult obj1, DeliverySearchDTO obj2, String targetShop) throws Exception;
    
    /**
     * 배송지연 상품관리(재배정) / 결품조회(재배정) .
     * 
     * <p/>
     * 
     * [사용 방법 설명].
     *
     * @param systemPk [설명]
     * @param obj1 [설명]
     * @param obj2 [설명]
     * @param targetShop [설명]
     * @param caller [설명]
     * @throws Exception the exception
     * @since 2015. 11. 11
     */
    public void reAssignDeliveryShop(SystemPK systemPk, DeliveryOrderGoodResult obj1, DeliverySearchDTO obj2, String targetShop, String caller) throws Exception;
    
    /**
     * 운송장수정.
     *
     * <p/>
     *
     * [사용 방법 설명].
     *
     * @param systemPK [설명]
     * @param deliveryInvoiceDTO [설명]
     * @return Int [설명]
     * @throws Exception the exception
     * @since 2015. 4. 6
     */
    public int updateInvoice(SystemPK systemPK, DeliveryInvoiceDTO deliveryInvoiceDTO ) throws Exception;
    
    /**
     * [메서드 설명].
     * 주문 상세 기능 [배송정보에 노출되는 상품 단위 배송완료]
     * 
     * <p/>
     * 
     * [사용 방법 설명].
     *
     * @param systemPk [설명]
     * @param dto [설명]
     * @throws Exception the exception
     * @since 2015. 6. 22
     */
    public void drctGodCompt(SystemPK systemPk, DeliveryOrderGoodDTO dto) throws Exception;
    
    /**
     * 신규 운송장출력.
     *
     * <p/>
     *
     * [사용 방법 설명].
     *
     * @param systemPk [설명]
     * @param gridList [설명]
     * @return String [설명]
     * @throws Exception the exception
     * @since 2015. 5. 19
     */
    public String makeNewWayBill(SystemPK systemPk, List<DeliveryOrderGoodResult> gridList, String call_Id) throws Exception;

    /**
     * 신규 용차운송장출력.
     *
     * <p/>
     *
     * [사용 방법 설명].
     *
     * @param systemPk [설명]
     * @param gridList [설명]
     * @return String [설명]
     * @throws Exception the exception
     * @since 2015. 5. 19
     */
    public String makeNewFrgnWayBill(SystemPK systemPk, List<DeliveryOrderGoodResult> gridList) throws Exception;

    /**
     * 운송장번호 일괄 생성.
     *
     * <p/>
     *
     * [사용 방법 설명].
     *
     * @param systemPk [설명]
     * @param deliverySearchDTO [설명]
     * @return boolean
     * @throws Exception the exception
     * @since 2015. 5. 19
     */
    public int updateSearchWaybillNo(SystemPK systemPk, DeliverySearchDTO deliverySearchDTO) throws Exception;

    /**
     * 운송장번호 개별 생성.
     *
     * <p/>
     *
     * [사용 방법 설명].
     *
     * @param systemPK [설명]
     * @param gridList [설명]
     * @return boolean
     * @throws Exception the exception
     * @since 2015. 5. 19
     */
    public int updateSelectedWaybillNo(SystemPK systemPK,  List<DeliveryOrderGoodResult> gridList, String callId) throws Exception;

    /**
     * 용차 운송장번호 일괄 생성.
     *
     * <p/>
     *
     * [사용 방법 설명].
     *
     * @param systemPK [설명]
     * @param deliverySearchDTO [설명]
     * @return boolean
     * @throws Exception the exception
     * @since 2015. 5. 19
     */
    public int updateSearchFrgnWaybillNo(SystemPK systemPK,  DeliverySearchDTO deliverySearchDTO) throws Exception;

    /**
     * 용차 운송장번호 개별 생성.
     *
     * <p/>
     *
     * [사용 방법 설명].
     *
     * @param systemPK [설명]
     * @param gridList [설명]
     * @return boolean
     * @throws Exception the exception
     * @since 2015. 5. 19
     */
    public int updateSelectedFrgnWaybillNo(SystemPK systemPK,  List<DeliveryOrderGoodResult> gridList) throws Exception;
    
    /**
     * 배송상태변경.
     *
     * <p/>
     *
     * [사용 방법 설명].
     *
     * @param systemPK [설명]
     * @param listDTO [설명]
     * @param newDlvStatCd [설명]
     * @return true:[설명], false:[설명]
     * @throws Exception the exception
     * @since 2015. 6. 22
     */
    public String updateDeliveryStatus(SystemPK systemPK, List<DeliveryOrderGoodResult> listDTO , String newDlvStatCd)  throws Exception;
    
    /**
     * 결품 철회
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
    public int updateShortageCancel(SystemPK systemPk, List<DeliveryOrderGoodResult> gridList) throws Exception;
    
    /**
     * 택배사,송장번호,배송메모 저장.
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
    public boolean updateDeliveryInfo(SystemPK systemPK, List<DeliveryOrderGoodResult> listDTO , DeliveryInvoiceDTO deliveryInvoiceDTO)  throws Exception;

    /**
     *  사은품 송장,배송메모 저장
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
    public boolean updateDeliveryGift(SystemPK systemPK, List<DeliveryOrderGoodResult> listDTO , DeliveryInvoiceDTO deliveryInvoiceDTO)  throws Exception;

    /**
     * ERP 인터페이스 시리얼정보 수정.
     *
     * @param systemPk the system pk
     * @param gridList the grid list
     * @throws Exception the exception
     */
    public void updateErpGodSnInfo(SystemPK systemPk, List<DeliveryOrderGoodResult> gridList) throws Exception;
    
    /**
     * [매장전용] 배정 거부 반영 
     * 
     * <p/>
     * 
     * [사용 방법 설명].
     *
     * @param systemPk [설명]
     * @param dlivyDrctInfo [설명]
     * @throws Exception the exception
     * @since 2016. 5. 20
     */
    public void updateDlvStatData(SystemPK systemPk, DeliveryOrderGoodDTO dlivyDrctInfo) throws Exception;
    
    /**
     * 거부 전 주문 현재 상태 체크
     *
     * [사용 방법 설명].
     *
     * @param gridList [설명]
     * @return Int [설명]
     * @throws Exception the exception
     * @since 2016. 7. 1
     */
    public int getChkStatus(DeliveryOrderGoodDTO deliveryOrderGoodDTO) throws Exception;
    
	/**
	 * 배송지연 상품관리(재배정) / 결품조회(재배정).
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param systemPk [설명]
	 * @param gridList [설명]
	 * @param targetShop [설명]
	 * @param caller [설명]
	 * @return String [설명]
	 * @throws Exception the exception
	 * @since 2015. 11. 11
	 */
	public String reAssignDeliveryShop(SystemPK systemPk, List<DeliveryOrderGoodResult> gridList, String targetShop, String caller) throws Exception;

	/**
	 * 결품접수(재배정).
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param systemPk [설명]
	 * @param gridList [설명]
	 * @return String [설명]
	 * @throws Exception the exception
	 * @since 2015. 11. 5
	 */
	public String shortageReassign(SystemPK systemPk, List<DeliveryOrderGoodResult> gridList) throws Exception;
	
	/**
	 * [물류센터전용] 물류결품처리.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param systemPk [설명]
	 * @param gridList [설명]
	 * @return String [설명]
	 * @throws Exception the exception
	 * @since 2015. 11. 4
	 */
	public String dstrbtCenterShortage(SystemPK systemPk, List<DeliveryOrderGoodResult> gridList) throws Exception;
	
	/**
	 * 출고지시.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param systemPk [설명]
	 * @param gridList [설명]
	 * @return String [설명]
	 * @throws Exception the exception
	 * @since 2015. 11. 4
	 */
	public String sendDlivyDrct(SystemPK systemPk, List<DeliveryOrderGoodResult> gridList) throws Exception;
	
	/**
	 * 출고지시 취소.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param systemPk [설명]
	 * @param gridList [설명]
	 * @return String [설명]
	 * @throws Exception the exception
	 * @since 2015. 11. 4
	 */
	public String sendDlivyDrctCancel(SystemPK systemPk, List<DeliveryOrderGoodResult> gridList) throws Exception;
	
	/**
	 * 상품출고지 배정(재배정).
	 * 배송매장에서 임시매장/배정대기 건에 대해 self 배정.
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param systemPk [설명]
	 * @param gridList [설명]
	 * @param targetShop [설명]
	 * @return String [설명]
	 * @throws Exception the exception
	 * @since 2015. 11. 10
	 */
	public String assignDleliveryShop(SystemPK systemPk, List<DeliveryOrderGoodResult> gridList, String targetShop) throws Exception;
	
	/** 
	 * [매장전용] 배정 거부 반영
	 * 
	 * @param systemPk the system pk
     * @param gridList the grid list
     * @throws Exception the exception
	 */
    public String setAsgnReject(SystemPK systemPk, List<DeliveryOrderGoodResult> gridList) throws Exception;
    
    /**
     * 배송매장 자동배정
     * <p/>
     * <p/>
     * <p/>
     * [사용 방법 설명].
     *
     * @param lgsAutoAsgnExtend 주문번호, 물류출고지상품번호 리스트, 배정구분코드 등
     * @param batchYn 프로그램구분 (true:배치/false:배치외)
     * @throws Exception the exception
     * @since 2016. 5. 2
     */
    //void setAssignDlvShop( LgsAutoAsgnExtend lgsAutoAsgnExtend, boolean batchYn ) throws Exception ;
    
    
    /**
     * 대량주문 배송완료  
     * 
     * @param systemPk
     * @param ordNos
     * @throws Exception
     */
    public void updateLgsQtyOrdDrctGodCompt(SystemPK systemPk, List<String> ordNos) throws Exception;
}
