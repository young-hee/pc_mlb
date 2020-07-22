/* Copyright (c) 2015 plgrim, Inc.
 * All right reserved.
 * http://plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 *
 * ------------------------------------------------------------------------
 * @author      
 * @since       2015. 4. 6
 */
package com.plgrim.ncp.cmp.orderfulfilment.bo.delivery.pickuprelease;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.plgrim.ncp.base.entities.RepositoryHelper;
import com.plgrim.ncp.biz.delivery.data.DeliveryOrderGoodDTO;
import com.plgrim.ncp.biz.delivery.data.DeliverySearchDTO;
import com.plgrim.ncp.biz.delivery.result.DeliveryOrderGoodResult;
import com.plgrim.ncp.biz.delivery.service.DeliveryCommandService;
import com.plgrim.ncp.biz.delivery.service.DeliveryListService;
import com.plgrim.ncp.biz.delivery.service.DeliveryStatusService;
import com.plgrim.ncp.cmp.orderfulfilment.bo.delivery.DeliveryPickupReleaseBoComponent;
import com.plgrim.ncp.cmp.orderfulfilment.bo.delivery.DeliveryReleaseBoComponent;
import com.plgrim.ncp.commons.util.BOSecurityUtil;
import com.plgrim.ncp.framework.data.SystemPK;

import lombok.extern.slf4j.Slf4j;


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
 * @since 2015. 4. 6
 */
@Slf4j
@Transactional(value = "transactionManager")
@Component
public class DeliveryPickupReleaseBoComponentImpl implements DeliveryPickupReleaseBoComponent {

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	@Autowired
	DeliveryListService deliveryListService;

	@Autowired
	DeliveryStatusService deliveryStatusService;
	
	@Autowired
	DeliveryCommandService deliveryCommandService;
	
	@Autowired
	DeliveryReleaseBoComponent deliveryReleaseBoComponent;

	/** 매장픽업 주문관리 조회. */
	@Override
	public List<DeliveryOrderGoodResult> getStorePickupOrderList(SystemPK systemPK, DeliverySearchDTO deliverySearchDTO) throws Exception {
		RepositoryHelper.makePageEntityIndex(deliverySearchDTO, deliverySearchDTO.getPageParam());	// 페이지 설정
		return deliveryListService.getStorePickupOrderList(systemPK, deliverySearchDTO);
	}

	/** 매장픽업 주문관리 total count. */
	@Override
	public int getStorePickupOrderListCount(SystemPK systemPK, DeliverySearchDTO deliverySearchDTO) throws Exception {
		return deliveryListService.getStorePickupOrderListCount(systemPK,deliverySearchDTO);
	}

	/** 매장픽업 주문관리 조회 excel. */
	@Override
	public List<Map<String, Object>> getStorePickupOrderListExcel(SystemPK systemPK, DeliverySearchDTO deliverySearchDTO) throws Exception {
		return deliveryListService.getStorePickupOrderListExcel(systemPK, deliverySearchDTO);
	}

	/** 전체 주문상품 수량. */
	public int selectOrdGodCount(SystemPK systemPK, DeliverySearchDTO deliverySearchDTO) throws Exception {
		return deliveryListService.selectOrdGodCount(systemPK,deliverySearchDTO);
	}
	
	/** 픽업매장 SMS 발송 */
	@Override
	public int sendPickupReadySms(SystemPK systemPk, List<DeliveryOrderGoodResult> gridList) throws Exception {
		deliveryReleaseBoComponent.updateDeliveryStatus(systemPk,  gridList, "SHOP_PRPARE_COMPT");
		return deliveryStatusService.sendPickupReadySms(systemPk,gridList);
	}
	
	/*
	 * [고객센터] 픽업철회
	 * 주문상세팝업 또는 고객센터 화면에서 픽업건에 대한 철회 기능
	 * 철회시 재배정이 가능하도록 임시매장/배정대기 처리
	 *
	 */
	@Override
	public String modifyShortageCancelForPickup (SystemPK systemPk, List<DeliveryOrderGoodResult> gridList) throws Exception {
		String udterId = BOSecurityUtil.getLoginId();	//세션ID
		String rtrnMsg = "";
		int sCnt = 0;
		int fCnt = 0;

		DeliveryOrderGoodDTO dto = new DeliveryOrderGoodDTO();
		for (DeliveryOrderGoodResult deliveryOrderGoodResult : gridList) {
			dto.setDlivyDrctGodNo(deliveryOrderGoodResult.getLgsDdg().getDlivyDrctGodNo());

			try {
				// [고객센터] 픽업철회
				deliveryStatusService.modifyShortageCancelForPickup(dto, udterId);
				sCnt++;
			} catch(Exception e) {
				fCnt++;
			}
		}


		if(fCnt < 1) {
			rtrnMsg = sCnt+"건이 처리되었습니다.";
		} else {
			rtrnMsg = "성공 :: "+sCnt+"건   실패 ::"+fCnt+"건\n실패건에 대해 배송상태 확인바랍니다.";
		}

		return rtrnMsg;
	}

	/**
	 * 매장전용] #50212 픽업 재진열 대상 알림(매장 Dashboard) 기능 추가
	 * - '픽업매장 재진열 완료' 수정
	 *
	 * @param systemPk
	 * @param deliveryOrderGoodDTO
	 * @return
	 * @throws Exception
	 */
	@Override
	public String updateRedispCompt(SystemPK systemPk, DeliveryOrderGoodDTO deliveryOrderGoodDTO) throws Exception {
		try {
			this.deliveryCommandService.updateRedispCompt(systemPk, deliveryOrderGoodDTO);
		}
		catch(Exception e){
			StringWriter error = new StringWriter();
			e.printStackTrace(new PrintWriter(error));
			log.error("> Failure message : {}", this.getClass().getName() + " : " + error.toString());

			return "재진열 완료 처리 시 오류가 발생했습니다.";
		}
		return "재진열 완료 처리되었습니다.";
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * Constructors.
	 * ---------------------------------------------------------------------
	 */

}
