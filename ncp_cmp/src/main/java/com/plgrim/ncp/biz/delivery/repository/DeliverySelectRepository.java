/* Copyright (c) 2015 plgrim, Inc.
 * All right reserved.
 * http://plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 *
 * ------------------------------------------------------------------------
 * @author      th86.yang
 * @since       2015. 8. 29       
 */
package com.plgrim.ncp.biz.delivery.repository;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.datasource1.clm.ClmWrhsGodExtend;
import com.plgrim.ncp.base.entities.datasource1.com.Com;
import com.plgrim.ncp.base.entities.datasource1.com.ComDmstcDlvCstPlc;
import com.plgrim.ncp.base.entities.datasource1.com.ComDmstcDlvCstPlcExtend;
import com.plgrim.ncp.base.entities.datasource1.inf.InfOrdGodErpDstb;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsDlivyDrctGod;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsDlivyDrctGodExtend;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsDlv;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsDlvFoExtend;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsDlvRtrvlDrctHist;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsRtrvlDrctGod;
import com.plgrim.ncp.base.entities.datasource1.ord.OrdGodExtends;
import com.plgrim.ncp.base.entities.datasource1.sys.SysShop;
import com.plgrim.ncp.base.entities.datasource1.sys.SysShopExtends;
import com.plgrim.ncp.biz.claim.data.LgsDlvExtendForGlobalCancel;
import com.plgrim.ncp.biz.delivery.data.DeliveryAffDTO;
import com.plgrim.ncp.biz.delivery.data.DeliveryInvoiceDTO;
import com.plgrim.ncp.biz.delivery.data.DeliveryOrderGoodDTO;
import com.plgrim.ncp.biz.delivery.data.DeliveryRuleByGoodDTO;
import com.plgrim.ncp.biz.delivery.data.DeliverySearchDTO;
import com.plgrim.ncp.biz.delivery.data.DlvOrdGodInfoDTO;
import com.plgrim.ncp.biz.delivery.data.OrdExt;
import com.plgrim.ncp.biz.delivery.result.DeliveryArticleResult;
import com.plgrim.ncp.biz.delivery.result.DeliveryClaimGoodResult;
import com.plgrim.ncp.biz.delivery.result.DeliveryComptMsgResult;
import com.plgrim.ncp.biz.delivery.result.DeliveryHistoryResult;
import com.plgrim.ncp.biz.delivery.result.DeliveryInvoiceResult;
import com.plgrim.ncp.biz.delivery.result.DeliveryOrderGoodResult;
import com.plgrim.ncp.biz.delivery.result.DeliveryRedisplayResult;
import com.plgrim.ncp.biz.delivery.result.DeliveryRuleByGoodResult;
import com.plgrim.ncp.biz.delivery.result.ReturnItemListByClaimNoResult;
import com.plgrim.ncp.biz.delivery.result.ReturnItemWithWayBillResult;
import com.plgrim.ncp.biz.vendor.result.VendorBrndListResult;

// TODO: Auto-generated Javadoc
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
 * @since 2015. 4. 13
 */

/** The Constant log. */
@Repository
public class DeliverySelectRepository extends AbstractRepository {

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */

	/*
	 * ---------------------------------------------------------------------
	 * Constructors.
	 * ---------------------------------------------------------------------
	 */

	/*
	 * ---------------------------------------------------------------------
	 * public & interface method.
	 * ---------------------------------------------------------------------
	 */
	/**
	 * 자사상품 배송조회
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param deliverySearchDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 3. 30
	 */
	public List<DeliveryOrderGoodResult> getDeliveryList(DeliverySearchDTO deliverySearchDTO) throws Exception {
		return getSession1().selectList("com.plgrim.ncp.biz.delivery.select.getDeliveryList", deliverySearchDTO);
	}

	/**
	 * 배송지연리스트 조회
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param deliverySearchDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 3. 30
	 */
	public List<DeliveryOrderGoodResult> getDelayDeliveryList(DeliverySearchDTO deliverySearchDTO) throws Exception {
		return getSession1().selectList("com.plgrim.ncp.biz.delivery.select.getDelayDeliveryList", deliverySearchDTO);
	}


	/**
	 * 배송매장 변경 이력 조회.
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param deliverySearchDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 4. 2
	 */
	public List<DeliveryHistoryResult> getDeliveryShopHistoryList(DeliverySearchDTO deliverySearchDTO) throws Exception {
		return getSession1().selectList("com.plgrim.ncp.biz.delivery.select.getDeliveryShopHistoryList", deliverySearchDTO);
	}


	/**
	 * [메서드 설명].
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param deliveryOrderGoodDTO [설명]
	 * @return List [설명]
	 * @since 2015. 4. 2
	 */
	public List<DeliveryOrderGoodResult> getDeliveryListForPlantCancel(DeliveryOrderGoodDTO deliveryOrderGoodDTO) {
		List<DeliveryOrderGoodResult> list = null;

		return list;
	}

	/**
	 * 자사상품 회수리스트 조회.
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param deliverySearchDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 4. 29
	 */
	public List<DeliveryClaimGoodResult> getRetrievalGoodsList(DeliverySearchDTO deliverySearchDTO) throws Exception {
		return getSession1().selectList("com.plgrim.ncp.biz.delivery.select.getRetrievalGoodsList", deliverySearchDTO);
	}

	/**
	 * 상품출고배정 조회.
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param deliverySearchDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 4. 29
	 */
	public List<DeliveryOrderGoodResult> getSelfAssignDeliveryList(DeliverySearchDTO deliverySearchDTO) throws Exception {
		return getSession1().selectList("com.plgrim.ncp.biz.delivery.select.getSelfAssignDeliveryList", deliverySearchDTO);
	}

	/**
	 * 주문의 출고건 확인 [주문 전체취소 체크시 사용].
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param dlvOrdGodInfoDTO [설명]
	 * @return String [설명]
	 * @throws Exception the exception
	 * @since 2015. 4. 14
	 */
	public String checkDeliveryYnAboutOrder(DlvOrdGodInfoDTO dlvOrdGodInfoDTO) throws Exception {
		return (String)getSession1().selectOne("com.plgrim.ncp.biz.delivery.select.checkDeliveryYnAboutOrder", dlvOrdGodInfoDTO);
	}

	/**
	 * [메서드 설명].
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dlvOrdGodInfoDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 8. 18
	 */
	public List<LgsDlivyDrctGodExtend> selectOrdDlivyDrct(DlvOrdGodInfoDTO dlvOrdGodInfoDTO) throws Exception {
		return getSession1().selectList("com.plgrim.ncp.biz.delivery.select.selectOrdDlivyDrct", dlvOrdGodInfoDTO);

	}

	/**
	 * 자사상품 배송조회(total count)
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param deliverySearchDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 3. 30
	 */
	public int getDeliveryListCount(DeliverySearchDTO deliverySearchDTO) throws Exception {
		return getSession1().selectOne("com.plgrim.ncp.biz.delivery.select.getDeliveryListCount", deliverySearchDTO);
	}



	/**
	 * 자사상품 배송조회 excel
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param deliverySearchDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 3. 30
	 */
	public List<Map<String, Object>>  getDeliveryListExcel(DeliverySearchDTO deliverySearchDTO) throws Exception {
		return getSession1().selectList("com.plgrim.ncp.biz.delivery.select.getDeliveryListExcel", deliverySearchDTO);
	}



	/**
	 * 자사상품 출고관리조회
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param deliverySearchDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 3. 30
	 */
	public List<DeliveryOrderGoodResult> getDrctGoodsList(DeliverySearchDTO deliverySearchDTO) throws Exception {
		return getSession1().selectList("com.plgrim.ncp.biz.delivery.select.getDrctGoodsList", deliverySearchDTO);
	}

	/**
	 * 자사상품 출고관리조회(total count)
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param deliverySearchDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 3. 30
	 */
	public int getDrctGoodsListCount(DeliverySearchDTO deliverySearchDTO) throws Exception {
		return getSession1().selectOne("com.plgrim.ncp.biz.delivery.select.getDrctGoodsListCount", deliverySearchDTO);
	}


	/**
	 * 자사상품 출고관리조회 excel
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param deliverySearchDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 3. 30
	 */
	public List<Map<String, Object>>  getDrctGoodsListExcel(DeliverySearchDTO deliverySearchDTO) throws Exception {
		return getSession1().selectList("com.plgrim.ncp.biz.delivery.select.getDrctGoodsListExcel", deliverySearchDTO);
	}


	/**
	 * 샘플데이터
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 3. 30
	 */
	public List<Map<String, Object>>  getSampleData() throws Exception {
		return getSession1().selectList("com.plgrim.ncp.biz.delivery.select.getSampleData");
	}


	/**
	 * 매장픽업 주문관리 조회
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param deliverySearchDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 3. 30
	 */
	public List<DeliveryOrderGoodResult> getStorePickupOrderList(DeliverySearchDTO deliverySearchDTO) throws Exception {
		return getSession1().selectList("com.plgrim.ncp.biz.delivery.select.getStorePickupOrderList", deliverySearchDTO);
	}

	/**
	 *  매장픽업 주문관리 조회(total count)
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param deliverySearchDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 3. 30
	 */
	public int getStorePickupOrderListCount(DeliverySearchDTO deliverySearchDTO) throws Exception {
		return getSession1().selectOne("com.plgrim.ncp.biz.delivery.select.getStorePickupOrderListCount", deliverySearchDTO);
	}
	
	
	/**
	 *  주문상품 수량 조회(total count)
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param deliverySearchDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 3. 30
	 */
	public int selectOrdGodCount(DeliverySearchDTO deliverySearchDTO) throws Exception {
		return getSession1().selectOne("com.plgrim.ncp.biz.delivery.select.selectOrdGodCount", deliverySearchDTO);
	}


	/**
	 * 매장픽업 주문관리 조회 excel
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param deliverySearchDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 3. 30
	 */
	public List<Map<String, Object>>  getStorePickupOrderListExcel(DeliverySearchDTO deliverySearchDTO) throws Exception {
		return getSession1().selectList("com.plgrim.ncp.biz.delivery.select.getStorePickupOrderListExcel", deliverySearchDTO);
	}


	/**
	 *  배송지연 상품관리 조회(total count)
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param deliverySearchDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 3. 30
	 */
	public int getDelayDeliveryListCount(DeliverySearchDTO deliverySearchDTO) throws Exception {
		return getSession1().selectOne("com.plgrim.ncp.biz.delivery.select.getDelayDeliveryListCount", deliverySearchDTO);
	}

	/**
	 * 배송지연 상품관리 조회 excel
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param deliverySearchDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 3. 30
	 */
	public List<Map<String, Object>>  getDelayDeliveryListExcel(DeliverySearchDTO deliverySearchDTO) throws Exception {
		return getSession1().selectList("com.plgrim.ncp.biz.delivery.select.getDelayDeliveryListExcel", deliverySearchDTO);
	}


	/**
	 * 입점업체배송리스트 조회
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param deliverySearchDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 3. 30
	 */
	public List<DeliveryOrderGoodResult> getPartMallDeliveryList(DeliverySearchDTO deliverySearchDTO) throws Exception {
		return getSession1().selectList("com.plgrim.ncp.biz.delivery.select.getPartMallDeliveryList", deliverySearchDTO);
	}


	/**
	 *  입점업체배송리스트 조회(total count)
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param deliverySearchDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 3. 30
	 */
	public int getPartMallDeliveryListCount(DeliverySearchDTO deliverySearchDTO) throws Exception {
		return getSession1().selectOne("com.plgrim.ncp.biz.delivery.select.getPartMallDeliveryListCount", deliverySearchDTO);
	}


	/**
	 * 입점업체배송리스트 조회 excel
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param deliverySearchDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 3. 30
	 */
	public List<Map<String, Object>>  getPartMallDeliveryListExcel(DeliverySearchDTO deliverySearchDTO) throws Exception {
		return getSession1().selectList("com.plgrim.ncp.biz.delivery.select.getPartMallDeliveryListExcel", deliverySearchDTO);
	}

	/**
	 * PO 입점업체배송리스트 조회 excel
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param deliverySearchDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 3. 30
	 */
	public List<Map<String, Object>>  getPoPartMallDeliveryListExcel(DeliverySearchDTO deliverySearchDTO) throws Exception {
		return getSession1().selectList("com.plgrim.ncp.biz.delivery.select.getPoPartMallDeliveryListExcel", deliverySearchDTO);
	}

	/**
	 * PO 입점업체 주문취소조회 excel
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param deliverySearchDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 3. 30
	 */
	public List<Map<String, Object>>  getPoPartMallOrderGoodLackListExcel(DeliverySearchDTO deliverySearchDTO) throws Exception {
		return getSession1().selectList("com.plgrim.ncp.biz.delivery.select.getPoPartMallOrderGoodLackListExcel", deliverySearchDTO);
	}

	/**
	 * PO 입점업체 주문취소조회 excel
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param deliverySearchDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 3. 30
	 */
	public List<Map<String, Object>>  getPoPartMallOrderCancelListExcel(DeliverySearchDTO deliverySearchDTO) throws Exception {
		return getSession1().selectList("com.plgrim.ncp.biz.delivery.select.getPoPartMallOrderCancelListExcel", deliverySearchDTO);
	}

	/**
	 * 입점업체 결품접수 리스트 조회
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param deliverySearchDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 3. 30
	 */
	public List<DeliveryOrderGoodResult> getPoPartMallOrderGoodLackList(DeliverySearchDTO deliverySearchDTO) throws Exception {
		return getSession1().selectList("com.plgrim.ncp.biz.delivery.select.getPoPartMallOrderGoodLackList", deliverySearchDTO);
	}

	/**
	 *  입점업체 결품접수 리스트 조회(total count)
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param deliverySearchDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 3. 30
	 */
	public int getPoPartMallOrderGoodLackListCount(DeliverySearchDTO deliverySearchDTO) throws Exception {
		return getSession1().selectOne("com.plgrim.ncp.biz.delivery.select.getPoPartMallOrderGoodLackListCount", deliverySearchDTO);
	}

	/**
	 * PO 입점업체 주문취소 리스트 조회
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param deliverySearchDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 3. 30
	 */
	public List<DeliveryOrderGoodResult> getPoPartMallOrderCancelList(DeliverySearchDTO deliverySearchDTO) throws Exception {
		return getSession1().selectList("com.plgrim.ncp.biz.delivery.select.getPoPartMallDeliveryList", deliverySearchDTO);
	}

	/**
	 *  PO 입점업체 주문취소 리스트 조회(total count)
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param deliverySearchDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 3. 30
	 */
	public int getPoPartMallOrderCancelListCount(DeliverySearchDTO deliverySearchDTO) throws Exception {
		return getSession1().selectOne("com.plgrim.ncp.biz.delivery.select.getPoPartMallDeliveryListCount", deliverySearchDTO);
	}

	/**
	 *  판매체휴사 조회
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param deliverySearchDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 3. 30
	 */
	public List<Com> getCompanyList(DeliverySearchDTO deliverySearchDTO) throws Exception {
		return getSession1().selectList("com.plgrim.ncp.biz.delivery.select.getCompanyList", deliverySearchDTO);
	}


	/**
	 *  운송장 상세 조회
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param deliverySearchDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 3. 30
	 */
	public DeliveryInvoiceResult getInvoice(DeliverySearchDTO deliverySearchDTO) throws Exception {
		return getSession1().selectOne("com.plgrim.ncp.biz.delivery.select.getInvoice", deliverySearchDTO);
	}


	/**
	 * 운송장이력 목록 조회
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param deliverySearchDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 3. 30
	 */
	public List<DeliveryInvoiceResult> getInvoiceHistoryList(DeliverySearchDTO deliverySearchDTO) throws Exception {
		return getSession1().selectList("com.plgrim.ncp.biz.delivery.select.getInvoiceHistoryList", deliverySearchDTO);
	}


	/**
	 * 시리얼번호 중복체크.
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param deliverySearchDTO [설명]
	 * @return Int [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 28
	 */
	public int isUsedErpGodSnCnt(DeliverySearchDTO deliverySearchDTO) throws Exception {
		return getSession1().selectOne("com.plgrim.ncp.biz.delivery.select.selectUsedErpGodSnCnt", deliverySearchDTO);
	}


	/**
	 * 단품출고 검수 대상 조회.
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param deliverySearchDTO [설명]
	 * @return Delivery order good dto [설명]
	 * @throws Exception the exception
	 * @since 2015. 6. 16
	 */
	public DeliveryOrderGoodDTO selectGoodInspectUnitTargetInfo(DeliverySearchDTO deliverySearchDTO) throws Exception {
		return getSession1().selectOne("com.plgrim.ncp.biz.delivery.select.selectGoodInspectUnitTargetInfo", deliverySearchDTO);
	}

	/**
	 * 복품출고 검수 리스트 조회
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param deliverySearchDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 3. 30
	 */
	public List<DeliveryOrderGoodResult> getGoodsInspectList(DeliverySearchDTO deliverySearchDTO) throws Exception {
		return getSession1().selectList("com.plgrim.ncp.biz.delivery.select.getGoodsInspectList", deliverySearchDTO);
	}

	/**
	 *  운송장 기본정보 조회
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param deliveryInvoiceDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 3. 30
	 */
	public DeliveryInvoiceResult getInvoiceBasic(DeliveryInvoiceDTO deliveryInvoiceDTO) throws Exception {
		return getSession1().selectOne("com.plgrim.ncp.biz.delivery.select.getInvoiceBasic", deliveryInvoiceDTO);
	}



	/**
	 *  운송장 기본정보 조회(클레임용)
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param deliveryInvoiceDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 3. 30
	 */
	public List<DeliveryInvoiceResult> getInvoiceBasic2(DeliveryInvoiceDTO deliveryInvoiceDTO) throws Exception {
		return getSession1().selectList("com.plgrim.ncp.biz.delivery.select.getInvoiceBasic2", deliveryInvoiceDTO);
	}

	/**
	 * 상품회수처리 조회
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param deliverySearchDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 3. 30
	 */
	public List<DeliveryClaimGoodResult> getCompleteRetrievalList(DeliverySearchDTO deliverySearchDTO) throws Exception {
		return getSession1().selectList("com.plgrim.ncp.biz.delivery.select.getCompleteRetrievalList", deliverySearchDTO);
	}

	/**
	 * 자사상품 회수리스트 조회 row count.
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param deliverySearchDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 4. 29
	 */
	public int getRetrievalGoodsListCount(DeliverySearchDTO deliverySearchDTO) throws Exception {
		return getSession1().selectOne("com.plgrim.ncp.biz.delivery.select.getRetrievalGoodsListCount", deliverySearchDTO);
	}

	/**
	 * 사은품 출고관리조회
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param deliverySearchDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 3. 30
	 */
	public List<DeliveryOrderGoodResult> getGiftDrctGoodsList(DeliverySearchDTO deliverySearchDTO) throws Exception {
		return getSession1().selectList("com.plgrim.ncp.biz.delivery.select.getGiftDrctGoodsList", deliverySearchDTO);
	}

	/**
	 * 사은품 출고관리조회 row count.
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param deliverySearchDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 4. 29
	 */
	public int getGiftDrctGoodsListCount(DeliverySearchDTO deliverySearchDTO) throws Exception {
		return getSession1().selectOne("com.plgrim.ncp.biz.delivery.select.getGiftDrctGoodsListCount", deliverySearchDTO);
	}


	/**
	 * 사은품 출고관리조회 excel
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param deliverySearchDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 3. 30
	 */
	public List<Map<String, Object>> getGiftDrctGoodsListExcel(DeliverySearchDTO deliverySearchDTO) throws Exception {
		return getSession1().selectList("com.plgrim.ncp.biz.delivery.select.getGiftDrctGoodsListExcel", deliverySearchDTO);
	}


	/**
	 * 사은품 Picking List
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param deliverySearchDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 3. 30
	 */
	public List<DeliveryOrderGoodResult> getGiftDrctGoodsPickingList(DeliverySearchDTO deliverySearchDTO) throws Exception {
		return getSession1().selectList("com.plgrim.ncp.biz.delivery.select.getGiftDrctGoodsPickingList", deliverySearchDTO);
	}

	/**
	 * 자사상품 회수리스트 조회 excel.
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param deliverySearchDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 4. 6
	 */
	public List<Map<String, Object>> getRetrievalGoodsListExcel(DeliverySearchDTO deliverySearchDTO) throws Exception {
		return getSession1().selectList("com.plgrim.ncp.biz.delivery.select.getRetrievalGoodsListExcel", deliverySearchDTO);
	}

	/**
	 * 상품출고배정 조회 rowcount.
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param deliverySearchDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 4. 29
	 */
	public int getSelfAssignDeliveryListCount(DeliverySearchDTO deliverySearchDTO) throws Exception {
		return getSession1().selectOne("com.plgrim.ncp.biz.delivery.select.getSelfAssignDeliveryListCount", deliverySearchDTO);
	}
	
	
	/**
	 * 브랜드 리스트 조회.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param deliverySearchDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 8. 18
	 */
	public List<DeliveryInvoiceResult> selectShopBrndList(DeliverySearchDTO deliverySearchDTO) throws Exception {
		return getSession1().selectList("com.plgrim.ncp.biz.delivery.select.selectShopBrndList", deliverySearchDTO);
	}
	
	
	/**
	 * 매장픽업 준비완료 SMS 발송 정보 조회
	 * [사용 방법 설명].
	 *
	 * @param deliverySearchDTO [설명]
	 * @return Delivery order good dto [설명]
	 * @throws Exception the exception
	 * @since 2015. 8. 29
	 */
	public DeliveryOrderGoodDTO selectPickupReadySms(DeliverySearchDTO deliverySearchDTO) throws Exception {
		return getSession1().selectOne("com.plgrim.ncp.biz.delivery.select.selectPickupReadySms", deliverySearchDTO);
	}
	
	/**
	 * Select frgn delivery list.
	 *
	 * @param ordNo the ord no
	 * @return the list
	 */
	public List<DeliveryAffDTO> selectFrgnDeliveryList(String ordNo){
		return getSession1().selectList("com.plgrim.ncp.biz.delivery.select.selectFrgnDeliveryList", ordNo);
	}
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */

	/**
	 * <pre>
	 * 상품 리스트를 받아 배송정책을 조회
	 * </pre>.
	 *
	 * @param deliveryRuleByGoodDTO the delivery rule by good dto
	 * @return the list
	 * @throws Exception the exception
	 * @since 2015. 11. 10.
	 */
	public List<DeliveryRuleByGoodResult> selectDeliveryRuleByGood(DeliveryRuleByGoodDTO deliveryRuleByGoodDTO) throws Exception{
		return getSession1().selectList("com.plgrim.ncp.biz.delivery.select.selectDeliveryRuleByGood", deliveryRuleByGoodDTO);
	}

	/**
	 * <pre>
	 * 상품에 해당하는 해당 업체의 배송정책을 조회
	 * </pre>.
	 *
	 * @param deliveryRuleByGoodDTO the delivery rule by good dto
	 * @return List<ComDmstcDlvCstPlcExtend>
	 * @throws Exception the exception
	 * @since 2015. 11. 11.
	 */
	public List<ComDmstcDlvCstPlcExtend> selectDeliveryRuleByGodNoList(DeliveryRuleByGoodDTO deliveryRuleByGoodDTO) throws Exception{
		return getSession1().selectList("com.plgrim.ncp.biz.delivery.select.selectDeliveryRuleByGodNoList", deliveryRuleByGoodDTO);
	}

	public List<ComDmstcDlvCstPlcExtend> selectDeliveryRuleByGodNoListOvseaDmst(DeliveryRuleByGoodDTO deliveryRuleByGoodDTO) {
		return getSession1().selectList("com.plgrim.ncp.biz.delivery.select.selectDeliveryRuleByGodNoListOvseaDmst", deliveryRuleByGoodDTO);
	}



	/**
	 * <pre>
	 * 클레임 대상 원 주문 상품 목록 조회
	 * </pre>.
	 *
	 * @param deliveryRuleByGoodDTO the delivery rule by good dto
	 * @return List<OrdGodExtends>
	 * @throws Exception the exception
	 * @since 2015. 11. 12.
	 */
	public List<OrdGodExtends> selectOriOrdGodListByClm(DeliveryRuleByGoodDTO deliveryRuleByGoodDTO) throws Exception{
		return getSession1().selectList("com.plgrim.ncp.biz.delivery.select.selectOriOrdGodListByClm", deliveryRuleByGoodDTO);
	}
	/*교환용별도쿼리 교환후 반품인경우 원주문 배송정책을 가져오는 쿼리 */
	public List<OrdGodExtends> selectOriOrdGodListByClmEx(DeliveryRuleByGoodDTO deliveryRuleByGoodDTO) throws Exception{
		return getSession1().selectList("com.plgrim.ncp.biz.delivery.select.selectOriOrdGodListByClmEx", deliveryRuleByGoodDTO);
	}
	public List<OrdGodExtends> selectOriOrdGodListByClmAfter(DeliveryRuleByGoodDTO deliveryRuleByGoodDTO) throws Exception{
		return getSession1().selectList("com.plgrim.ncp.biz.delivery.select.selectOriOrdGodListByClmAfter", deliveryRuleByGoodDTO);
	}

	/**
	 * <pre>
	 * 원 주문에 해당하는 기존 클레임 입고 상품 목록 조회
	 * </pre>.
	 *
	 * @param deliveryRuleByGoodDTO the delivery rule by good dto
	 * @return List<ClmWrhsGodExtend>
	 * @throws Exception the exception
	 * @since 2015. 11. 13.
	 */
	public List<ClmWrhsGodExtend> selectClmWrhsGodByOrdNo(DeliveryRuleByGoodDTO deliveryRuleByGoodDTO) throws Exception{
		return getSession1().selectList("com.plgrim.ncp.biz.delivery.select.selectClmWrhsGodByOrdNo", deliveryRuleByGoodDTO);
	}

	/**
	 * <pre>
	 * 원 주문에 해당하는 물류 배송정보 목록 조회
	 * </pre>.
	 *
	 * @param deliveryRuleByGoodDTO the delivery rule by good dto
	 * @return List<LgsDlvFoExtend>
	 * @throws Exception the exception
	 * @since 2015. 11. 13.
	 */
	public List<LgsDlvFoExtend> selectLgsDlvByOrdNo(DeliveryRuleByGoodDTO deliveryRuleByGoodDTO) throws Exception{
		return getSession1().selectList("com.plgrim.ncp.biz.delivery.select.selectLgsDlvByOrdNo", deliveryRuleByGoodDTO);
	}

	public List<LgsDlvFoExtend> selectLgsDlvOvseaByOrdNo(DeliveryRuleByGoodDTO deliveryRuleByGoodDTO) {
		return getSession1().selectList("com.plgrim.ncp.biz.delivery.select.selectLgsDlvOvseaByOrdNo", deliveryRuleByGoodDTO);
	}

	/**
	 * <pre>
	 * 주문 + 배송정책 번호에 해당하는 물류 배송 금액 합계 조회
	 * </pre>.
	 *
	 * @param deliveryRuleByGoodDTO the delivery rule by good dto
	 * @return List<LgsDlv>
	 * @throws Exception the exception
	 * @since 2015. 11. 20.
	 */
	public List<LgsDlv> selectLgsDlvCstGroupByDmstcDlvCstPlcSn(DeliveryRuleByGoodDTO deliveryRuleByGoodDTO) throws Exception{
		return getSession1().selectList("com.plgrim.ncp.biz.delivery.select.selectLgsDlvCstGroupByDmstcDlvCstPlcSn", deliveryRuleByGoodDTO);
	}

	public List<LgsDlv> selectLgsDlvCstGroupByOvseaDlvDmstcDlvCstPlcSn(DeliveryRuleByGoodDTO deliveryRuleByGoodDTO) throws Exception{
		return getSession1().selectList("com.plgrim.ncp.biz.delivery.select.selectLgsDlvCstGroupByOvseaDlvDmstcDlvCstPlcSn", deliveryRuleByGoodDTO);
	}

	public List<LgsDlv> selectLgsDlvCstGroupByOvseaDlvCstPlcSn(DeliveryRuleByGoodDTO deliveryRuleByGoodDTO) throws Exception{
		return getSession1().selectList("com.plgrim.ncp.biz.delivery.select.selectLgsDlvCstGroupByOvseaDlvCstPlcSn", deliveryRuleByGoodDTO);
	}

	/**
	 * <pre>
	 * 주문 상품에 해당하는 배송정책을 조회
	 * </pre>.
	 *
	 * @param deliveryRuleByGoodDTO the delivery rule by good dto
	 * @return List<ComDmstcDlvCstPlc>
	 * @throws Exception the exception
	 * @since 2015. 11. 17.
	 */
	public List<ComDmstcDlvCstPlc> selectDeliveryRuleByOrdGodList(DeliveryRuleByGoodDTO deliveryRuleByGoodDTO) throws Exception{
		return getSession1().selectList("com.plgrim.ncp.biz.delivery.select.selectDeliveryRuleByOrdGodList", deliveryRuleByGoodDTO);
	}

	/**
	 * <pre>
	 * 반품 요청 상품이 교환된 상품인 경우 최초의 주문 상품 순번 조회
	 * </pre>.
	 *
	 * @param deliveryRuleByGoodDTO the delivery rule by good dto
	 * @return List<OrdClmGodCnncExtend>
	 * @throws Exception the exception
	 * @since 2015. 11. 17.
	 */
	public ClmWrhsGodExtend selectExchgOriOrdGod(DeliveryRuleByGoodDTO deliveryRuleByGoodDTO) throws Exception{
		return getSession1().selectOne("com.plgrim.ncp.biz.delivery.select.selectExchgOriOrdGod", deliveryRuleByGoodDTO);
	}

	/**
	 * <pre>
	 * 부분취소로 인해 주문 배송비를 부과했는지 확인
	 * </pre>.
	 *
	 * @param deliveryRuleByGoodDTO the delivery rule by good dto
	 * @return String
	 * @throws Exception the exception
	 * @since 2015. 11. 18.
	 */
	public String beforePayOrdDlvAmtByPartCnclYN(DeliveryRuleByGoodDTO deliveryRuleByGoodDTO) throws Exception{
		return getSession1().selectOne("com.plgrim.ncp.biz.delivery.select.beforePayOrdDlvAmtByPartCnclYN", deliveryRuleByGoodDTO);
	}

	/**
	 * <pre>
	 * 고객 귀책 사유의 반품으로 주문 배송비를 부과했는지 확인 - 실제 배송비가 반품 배송비보다 크면 주문 배송비를 지급 한 것으로 판단
	 * </pre>.
	 *
	 * @param deliveryRuleByGoodDTO the delivery rule by good dto
	 * @return String
	 * @throws Exception the exception
	 * @since 2015. 11. 18.
	 */
	public String beforePayOrdDlvAmtByRtGodYN(DeliveryRuleByGoodDTO deliveryRuleByGoodDTO) throws Exception{
		return getSession1().selectOne("com.plgrim.ncp.biz.delivery.select.beforePayOrdDlvAmtByRtGodYN", deliveryRuleByGoodDTO);
	}

	/**
	 * <pre>
	 * 원 주문 상품에 연결된 교환 상품이 있고 해당 교환 상품이 고객 귀책으로 반품으로 접수되어 배송비가 있는 경우 실제 배송비가 반품 배송비보다 크면 주문 배송비를 지급 한 것으로 판단
	 * </pre>.
	 *
	 * @param deliveryRuleByGoodDTO the delivery rule by good dto
	 * @return String
	 * @throws Exception the exception
	 * @since 2015. 11. 18.
	 */
	public String beforePayOrdDlvAmtByExchgToRtGodYN(DeliveryRuleByGoodDTO deliveryRuleByGoodDTO) throws Exception{
		return getSession1().selectOne("com.plgrim.ncp.biz.delivery.select.beforePayOrdDlvAmtByExchgToRtGodYN", deliveryRuleByGoodDTO);
	}

	/**
	 * <pre>
	 * 반품 가능 주문 상품 수량 체크 : 주문 상품 수량 - 반품 수량
	 * </pre>.
	 *
	 * @param deliveryRuleByGoodDTO the delivery rule by good dto
	 * @return List<LgsDlivyDrctGod>
	 * @throws Exception the exception
	 * @since 2015. 11. 20.
	 */
	public List<LgsDlivyDrctGod> selectAvaillableRtOrdQtyList(DeliveryRuleByGoodDTO deliveryRuleByGoodDTO) throws Exception{
		return getSession1().selectList("com.plgrim.ncp.biz.delivery.select.selectAvaillableRtOrdQtyList", deliveryRuleByGoodDTO);
	}

	/**
	* <pre>
	* 반품 가능 주문 상품 수량 체크 : 주문 상품 수량 - 반품 수량
	* </pre>
	*
	* @param deliveryRuleByGoodDTO
	* @return List<LgsDlivyDrctGod>
	* @throws Exception
	* @since 2015. 11. 20.
	*/
	public List<LgsDlivyDrctGod> selectAvaillableRtOrdQtyListForGlobal(DeliveryRuleByGoodDTO deliveryRuleByGoodDTO) throws Exception{
		return getSession1().selectList("com.plgrim.ncp.biz.delivery.select.selectAvaillableRtOrdQtyListForGlobal", deliveryRuleByGoodDTO);
	}



	/**
	 *  킆레임 운송장 상세 조회
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param deliverySearchDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 3. 30
	 */
	public DeliveryInvoiceResult getClmInvoice(DeliverySearchDTO deliverySearchDTO) throws Exception {
		return getSession1().selectOne("com.plgrim.ncp.biz.delivery.select.getClmInvoice", deliverySearchDTO);
	}


	/**
	 * 클레임 운송장이력 목록 조회
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param deliverySearchDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 3. 30
	 */
	public List<DeliveryInvoiceResult> getClmInvoiceHistoryList(DeliverySearchDTO deliverySearchDTO) throws Exception {
		return getSession1().selectList("com.plgrim.ncp.biz.delivery.select.getClmInvoiceHistoryList", deliverySearchDTO);
	}
	
	/**
	 * 반품배송클레임상품리스트 조회.
	 *
	 * @param deliverySearchDTO the delivery search dto
	 * @return List<ReturnItemWithWayBillResult>
	 */
    public List<ReturnItemWithWayBillResult> selectReturnProductList(DeliverySearchDTO deliverySearchDTO) {
        return getSession1().selectList("com.plgrim.ncp.biz.delivery.select.selectReturnProductList", deliverySearchDTO);
    }
    
    /**
     * 회수상품갯수 조회.
     *
     * @param lgsRtrvlDrctGod the lgs rtrvl drct god
     * @return 상품수조회
     */
    public int getCountReturnProductList(LgsRtrvlDrctGod lgsRtrvlDrctGod) {
        return getSession1().selectOne("com.plgrim.ncp.biz.delivery.select.getCountReturnProductList", lgsRtrvlDrctGod);
    }
    
    /**
     * 배정가능 배송매장 조회.
     *
     * @param sysShop the sys shop
     * @return the list
     */
    public List<VendorBrndListResult> selectAssignDlvShop(SysShop sysShop) {
		return getSession1().selectList("com.plgrim.ncp.biz.delivery.select.selectAssignDlvShop", sysShop);
	}
    
    /**
     * 상품 검수정보 조회
     *
     * @param deliverySearchDTO the delivery search dto
     * @return the list
     * @throws Exception the exception
     */
    public List<DeliveryOrderGoodResult> selectItemCheckList(DeliverySearchDTO deliverySearchDTO) throws Exception {
		return getSession1().selectList("com.plgrim.ncp.biz.delivery.select.selectItemCheckList", deliverySearchDTO);
	}

	public LgsDlvExtendForGlobalCancel selectLgsDlvForOvsea(LgsDlv lgsDlvOvsea) {
		return getSession1().selectOne("com.plgrim.ncp.biz.delivery.select.selectLgsDlvForOvsea", lgsDlvOvsea);
	}


	public List<InfOrdGodErpDstb> selectInfOrdGodErpDstbByNoClm(String ordNo) {
		return getSession1().selectList("com.plgrim.ncp.biz.delivery.select.selectInfOrdGodErpDstbByNoClm", ordNo);
	}

	public List<LgsDlivyDrctGod> selectLgsDlivyDrctGod(String ordNo) {
		return getSession1().selectList("com.plgrim.ncp.biz.delivery.select.selectLgsDlivyDrctGod", ordNo);
	}

	/**
	 * 주문번호'를 이용해서 해당 주문에 미배정 상품 정보 조회
	 *
	 * @param dgsDlivyDrctGodExtend the delivery search dto
	 * @return the list
	 * @throws Exception the exception
	 */
	public List<LgsDlivyDrctGodExtend> selectNotAssignGood(LgsDlivyDrctGodExtend dgsDlivyDrctGodExtend) {
		return getSession1().selectList("com.plgrim.ncp.biz.delivery.select.selectNotAssignGood", dgsDlivyDrctGodExtend);
	}

	/**
	 * '주문번호', '단품번호' 를 이용해서 해당 상품의 배정가능 매장정보조회
	 *
	 * @param deliverySearchDTO the delivery search dto
	 * @return the list
	 * @throws Exception the exception
	 */
/*	
	public List<LgsAutoAsgnExtend> selectDlvShop4Assigned(LgsDlivyDrctGodExtend lgsDlivyDrctGodExtend) {
		return getSession1().selectList("com.plgrim.ncp.biz.delivery.select.selectDlvShop4Assigned", lgsDlivyDrctGodExtend);
	}
*/
	/**
	 * '브랜드그룹'으로 그룹핑
	 * @param lgsDlivyDrctGodExtend
	 * @return 단품번호 문자열, 예) IT201604051472983@IT201604141477875, IT201505070976012
	 */
	public List<String> selectItmNosByBrndGrp(LgsDlivyDrctGodExtend lgsDlivyDrctGodExtend) {
		return getSession1().selectList("com.plgrim.ncp.biz.delivery.select.selectItmNosByBrndGrp", lgsDlivyDrctGodExtend);
	}

	/**
	 * 특정 주문의 미배정 상품정보에 대해서 '브랜드그룹'으로 그룹핑
	 * @param ordNo 주문번호
	 * @return 물류출고지시번호 문자열
	 * 	예) RO201606274834826@RO201606274834827
	 *		RO201606274834828@RO201606274834831@RO201606274834832@RO201606274834833
	 * 		RO201606274834829@RO201606274834830
	 */
	public List<String> selectDlivyDrctGodNoByBrndGrp(String ordNo) {
		return getSession1().selectList("com.plgrim.ncp.biz.delivery.select.selectDlivyDrctGodNoByBrndGrp", ordNo);
	}

	/**
	 * 매장픽업 픽업완료 SMS 발송 정보 조회
	 *
	 * @param listDeliveryOrderGoodDTO
	 * @return
	 * @throws Exception
	 */
	public DeliveryOrderGoodDTO selectPickupCompeteSms(List<DeliveryOrderGoodDTO> deliverySearchDTO) throws Exception {
		// SMS개선 by cannon : 2016.07.17
		return getSession1().selectOne("com.plgrim.ncp.biz.delivery.select.selectPickupCompeteSms", deliverySearchDTO);
	}

	/**
	 * 단품출고 검수 대상 조회.
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param deliverySearchDTO [설명]
	 * @return Delivery order good dto [설명]
	 * @throws Exception the exception
	 * @since 2015. 7. 27
	 */
	public List<DeliveryOrderGoodResult> getOfflineReturnList(DeliverySearchDTO deliverySearchDTO) throws Exception {
		return getSession1().selectList("com.plgrim.ncp.biz.delivery.select.selectOfflineReturnList", deliverySearchDTO);
	}

	/**
	 * 시리얼번호 리스트
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param deliverySearchDTO [설명]
	 * @return List String [설명]
	 * @throws Exception the exception
	 * @since 2015. 8. 3
	 */
	public List<String> getOrdTurnErpGodSnList(DeliverySearchDTO deliverySearchDTO) throws Exception {
		return getSession1().selectList("com.plgrim.ncp.biz.delivery.select.selectOrdTurnErpGodSnList", deliverySearchDTO);
	}

	/**
	 *  매장별 취급 브랜드 리스트
	 *
	 * @param String [설명]
	 * @return String list
	 * @throws Exception the exception
	 * @since 2016. 8. 12
	 */
	public List<SysShopExtends> getBrndList(String shopId) throws Exception {
		return getSession1().selectList("com.plgrim.ncp.biz.delivery.select.selectBrndList", shopId);
	}
	
	/**
	 * 국내 기본 배송 정책 택배사 조회
	 *
	 * @return String
	 * @throws Exception the exception
	 * @since 2017. 02. 08
	 */
	public String selectBaseDlvComCd(String mallId) throws Exception{
		return getSession1().selectOne("com.plgrim.ncp.biz.delivery.select.selectBaseDlvComCd", mallId);
	}

	/**
	 * #50212 [개발]픽업 재진열 대상 알림(매장 Dashboard) 기능 추가
	 * 	- 픽업 재진열 리스트 조회
	 *
	 * @param dlvShopId 매장매장ID
	 * @return 픽업 재진열 리스트
	 * @throws Exception
	 */
	public List<DeliveryRedisplayResult> getDeliveryRedisplayList(String dlvShopId) throws Exception {
		return getSession1().selectList("com.plgrim.ncp.biz.delivery.select.getDeliveryRedisplayList", dlvShopId);
	}

	/**
	 * [FO] 배송조회
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param deliverySearchDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2017. 9. 28
	 */
	public List<DeliveryOrderGoodResult> getDeliveryListFO(DeliverySearchDTO deliverySearchDTO) throws Exception {
		return getSession1().selectList("com.plgrim.ncp.biz.delivery.select.getDeliveryListFO", deliverySearchDTO);
	}

	/**
	 * [FO] 배송완료 메시지
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param deliverySearchDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2017. 9. 28
	 */
	public List<DeliveryComptMsgResult> selectDlvComptMsgFO(DeliverySearchDTO deliverySearchDTO) throws Exception {
		return getSession1().selectList("com.plgrim.ncp.biz.delivery.select.selectDlvComptMsgFO", deliverySearchDTO);
	}
	
    /**
     * 품번 시리얼 반품배송클레임상품리스트 조회.
     *
     * @param returnItemListByClaimNoResult the return item list by claim no result
     * @return List<ReturnItemWithWayBillResult>
     */
    public List<ReturnItemListByClaimNoResult> getReturnItemListBySkuWithSerial(ReturnItemListByClaimNoResult returnItemListByClaimNoResult) {
        return getSession1().selectList("com.plgrim.ncp.biz.delivery.select.getReturnItemListBySkuWithSerial", returnItemListByClaimNoResult);
    }
    
	/**
	 * 주문메일/문자 발송 대상 주문 조회 
	 *
	 * @return List<OrdExt> [설명]
	 */
	public List<OrdExt> selectOrd4DlvEmail() {		
		return getSession1().selectList("com.plgrim.ncp.biz.delivery.select.selectOrd4DlvEmail");
	}	
	
	/**
	 * 매장픽업 주문단위 조회
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param deliverySearchDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 3. 30
	 */
	public List<DeliveryOrderGoodResult> getStorePickupOrderDetail(Map<String, List<String>> map) throws Exception {
		return getSession1().selectList("com.plgrim.ncp.biz.delivery.select.getStorePickupOrderDetail", map);
	}
	
	/**
	 * 매장배송 주문단위 조회
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param deliverySearchDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 3. 30
	 */
	public List<DeliveryOrderGoodResult> getStoreOrderDetail(Map<String, List<String>> map) throws Exception {
		return getSession1().selectList("com.plgrim.ncp.biz.delivery.select.getStoreOrderDetail", map);
	}
	
	public String getVipList() {
		String plcCd = "VIP_LIST";
		return getSession1().selectOne("com.plgrim.ncp.biz.delivery.select.getVipList",plcCd);
	}
}
