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
package com.plgrim.ncp.cmp.orderfulfilment.bo.delivery.sellersearch;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.plgrim.ncp.base.entities.RepositoryHelper;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsDlivyDrctGod;
import com.plgrim.ncp.base.entities.datasource1.ord.Ord;
import com.plgrim.ncp.base.enums.DeliveryEnum;
import com.plgrim.ncp.biz.delivery.data.DeliveryInvoiceDTO;
import com.plgrim.ncp.biz.delivery.data.DeliveryOrderGoodDTO;
import com.plgrim.ncp.biz.delivery.data.DeliverySearchDTO;
import com.plgrim.ncp.biz.delivery.exception.DeliveryStatusException;
import com.plgrim.ncp.biz.delivery.repository.DeliveryCommandRepository;
import com.plgrim.ncp.biz.delivery.result.DeliveryOrderGoodResult;
import com.plgrim.ncp.biz.delivery.service.DeliveryAssignService;
import com.plgrim.ncp.biz.delivery.service.DeliveryCommandService;
import com.plgrim.ncp.biz.delivery.service.DeliveryListService;
import com.plgrim.ncp.biz.delivery.service.DeliveryStatusService;
import com.plgrim.ncp.biz.order.service.OrderBoService;
import com.plgrim.ncp.cmp.orderfulfilment.bo.delivery.DeliverySellerSearchBoComponent;
import com.plgrim.ncp.commons.util.BOSecurityUtil;
import com.plgrim.ncp.framework.commons.StringService;
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
public class DeliverySellerSearchBoComponentImpl implements DeliverySellerSearchBoComponent {

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	@Autowired
	DeliveryListService deliveryListService;

	@Autowired
	DeliveryCommandService deliveryCommandService;
	
	@Autowired
	DeliveryAssignService deliveryAssignService;
	
	@Autowired
	OrderBoService orderBoService;
	
	@Autowired
	DeliveryStatusService deliveryStatusService;
	
	@Autowired
	DeliveryCommandRepository deliveryCommandRepository;

	/** 입점업체배송리스트 조회. */
	@Override
	public List<DeliveryOrderGoodResult> getPartMallDeliveryList(SystemPK systemPK, DeliverySearchDTO deliverySearchDTO) throws Exception {
		RepositoryHelper.makePageEntityIndex(deliverySearchDTO, deliverySearchDTO.getPageParam());	// 페이지 설정
		return deliveryListService.getPartMallDeliveryList(systemPK, deliverySearchDTO);
	}

	/** 입점업체배송리스트 조회 total count. */
	@Override
	public int getPartMallDeliveryListCount(SystemPK systemPK, DeliverySearchDTO deliverySearchDTO) throws Exception {
		return deliveryListService.getPartMallDeliveryListCount(systemPK,deliverySearchDTO);
	}

	/** 입점업체배송리스트 조회 excel. */
	@Override
	public List<Map<String, Object>> getPartMallDeliveryListExcel(SystemPK systemPK, DeliverySearchDTO deliverySearchDTO) throws Exception {
		return deliveryListService.getPartMallDeliveryListExcel(systemPK, deliverySearchDTO);
	}

	/** PO 입점업체 결품접수 리스트 조회. */
	@Override
	public List<DeliveryOrderGoodResult> getPoPartMallOrderGoodLackList(SystemPK systemPK, DeliverySearchDTO deliverySearchDTO) throws Exception {
		RepositoryHelper.makePageEntityIndex(deliverySearchDTO, deliverySearchDTO.getPageParam());	// 페이지 설정
		return deliveryListService.getPoPartMallOrderGoodLackList(systemPK, deliverySearchDTO);
	}

	/** PO 입점업체 결품접수 리스트 조회 total count. */
	@Override
	public int getPoPartMallOrderGoodLackListCount(SystemPK systemPK, DeliverySearchDTO deliverySearchDTO) throws Exception {
		return deliveryListService.getPoPartMallOrderGoodLackListCount(systemPK,deliverySearchDTO);
	}

	/** PO 입점업체 결품접수 리스트 조회 excel. */
	@Override
	public List<Map<String, Object>> getPoPartMallOrderGoodLackListExcel(SystemPK systemPK, DeliverySearchDTO deliverySearchDTO) throws Exception {
		return deliveryListService.getPoPartMallOrderGoodLackListExcel(systemPK, deliverySearchDTO);
	}

	/** PO 입점업체 주문취소 리스트 조회. */
	@Override
	public List<DeliveryOrderGoodResult> getPoPartMallOrderCancelList(SystemPK systemPK, DeliverySearchDTO deliverySearchDTO) throws Exception {
		RepositoryHelper.makePageEntityIndex(deliverySearchDTO, deliverySearchDTO.getPageParam());	// 페이지 설정
		return deliveryListService.getPoPartMallOrderCancelList(systemPK, deliverySearchDTO);
	}

	/** PO 입점업체 주문취소 리스트 조회 total count. */
	@Override
	public int getPoPartMallOrderCancelListCount(SystemPK systemPK, DeliverySearchDTO deliverySearchDTO) throws Exception {
		return deliveryListService.getPoPartMallOrderCancelListCount(systemPK,deliverySearchDTO);
	}

	/** PO 입점업체배송리스트 조회 excel. */
	@Override
	public List<Map<String, Object>> getPoPartMallDeliveryListExcel(SystemPK systemPK, DeliverySearchDTO deliverySearchDTO) throws Exception {
		return deliveryListService.getPoPartMallDeliveryListExcel(systemPK, deliverySearchDTO);
	}

	/** PO 입점업체 주문취소 조회 excel. */
	@Override
	public List<Map<String, Object>> getPoPartMallOrderCancelListExcel(SystemPK systemPK, DeliverySearchDTO deliverySearchDTO) throws Exception {
		return deliveryListService.getPoPartMallOrderCancelListExcel(systemPK, deliverySearchDTO);
	}

	/** 입점업체저장(택배사,송장번호,배송상태,배송매장메모). */
	@Override
	public boolean updatePartMall(SystemPK systemPK, List<DeliveryOrderGoodResult> listDTO, DeliveryInvoiceDTO deliveryInvoiceDTO) throws Exception {

//		deliveryCommandService.updateCompleteDelivery(systemPK, listDTO, deliveryInvoiceDTO);//배송상태변경
//		deliveryCommandService.updateInoviceCompare(systemPK, listDTO, deliveryInvoiceDTO);//운송장택배사정보 수정
		deliveryCommandService.updateDeliveryShopMemo(systemPK, listDTO, deliveryInvoiceDTO);//배송메모수정
		return true;
	}
	
	/** 배송매장 주문확인 */
	@Override
	public String updateOrderConfirmPartMall(SystemPK systemPK,
											 List<DeliveryOrderGoodResult> paramList,
											 DeliveryInvoiceDTO deliveryInvoiceDTO) throws Exception {

		List<DeliveryOrderGoodResult> shopList = new ArrayList<DeliveryOrderGoodResult>();
		List<DeliveryOrderGoodResult> packList = new ArrayList<DeliveryOrderGoodResult>();
		String message = "";
		boolean pckgYn = false;
		int procCnt = 0;	//처리건수	
		
		if (paramList != null && paramList.size() > 0) {
			for (DeliveryOrderGoodResult dto : paramList) {
				if (!DeliveryEnum.DLV_STAT_DLIVY_DRCT.toString().equals(dto.getLgsDdg().getDlvStatCd())) {
					DeliveryStatusException statusException = new DeliveryStatusException(null);
					statusException.setDirectMessage("출고지시 상태에서만 주문확인할 수 있습니다.");
					throw statusException;
				}
				
				//패키지 상품
				if(null != dto.getLgsDdg().getPckageGodTurn() && 0 != dto.getLgsDdg().getPckageGodTurn()) {
					packList.add(dto);

					//일반 상품
				} else {
					DeliverySearchDTO search = new DeliverySearchDTO();
					search.setOrdNo(dto.getOrd().getOrdNo());
					search.setDlvPcupspTurn(dto.getLgsDdg().getDlvPcupspTurn()+"");
					search.setDlvTurn(dto.getLgsDdg().getDlvTurn()+"");
					search.setDlvShopId(dto.getLgsDdg().getDlvShopId());
					List<DeliveryOrderGoodResult> reSelectList = deliveryCommandRepository.selectDlivyDrctTargetList(search);
					for(DeliveryOrderGoodResult obj3 : reSelectList)
					{
						shopList.add(obj3);
						//배송상태 변경

					}
				}
			}
			
			log.info("targetList :: "+shopList);
			// 중복 제거.
			List<DeliveryOrderGoodResult> finalList = new ArrayList<DeliveryOrderGoodResult>();
			HashSet<DeliveryOrderGoodResult> hs = new HashSet<DeliveryOrderGoodResult>(shopList);
			Iterator<DeliveryOrderGoodResult> it = hs.iterator();
			while(it.hasNext()) {
				finalList.add(it.next());
			}
			log.info("finalList :: "+finalList);
			for (DeliveryOrderGoodResult dto : finalList)
			{
				//배송매장 주문확인 컬럼 업데이트.
				DeliveryOrderGoodDTO retObj = deliveryAssignService.assignDeliShop4BasicPart(dto, "");
				procCnt += retObj.getCount();	//처리건수
			}
		}

		if(packList.size() > 0) {
			pckgYn = true;
			//배송매장 주문확인 컬럼 업데이트.
			deliveryAssignService.assignDeliShop4PckagePart(packList, "");
		}

		if(procCnt > 0) {
			if(pckgYn) {
				message = "패키지의 모든 구성품 포함 총 "+procCnt+"건이 처리되었습니다.";
			} else {
				message = "총 "+procCnt+"건이 처리되었습니다.";
			}
		} else {
			message = "처리된 건이 없습니다.";
		}

		return message;
	}

	/** 배송매장 운송장등록 */
	@Override
	public boolean updateInvoicePartMall(SystemPK systemPK,
										 List<DeliveryOrderGoodResult> listDTO,
										 DeliveryInvoiceDTO deliveryInvoiceDTO) throws Exception {

		String userId = BOSecurityUtil.getLoginId();	//세션ID

		if (listDTO != null && listDTO.size() > 0) {
			for (DeliveryOrderGoodResult dto : listDTO) {
				if ( (dto.getLgsDlv().getDlvComCd() == null || "".equals(dto.getLgsDlv().getDlvComCd())) ||
						(dto.getLgsDlv().getDmstcWaybilNo() == null || "".equals(dto.getLgsDlv().getDmstcWaybilNo())) ) {
					DeliveryStatusException statusException = new DeliveryStatusException(null);
					statusException.setDirectMessage("택배사와 송장번호를 입력해 주세요.");
					throw statusException;
				}

				if (!DeliveryEnum.DLV_STAT_DLIVY_DRCT.toString().equals(dto.getLgsDdg().getDlvStatCd())) {
					DeliveryStatusException statusException = new DeliveryStatusException(null);
					statusException.setDirectMessage("출고지시 상태에서만 사용할 수 있습니다.");
					throw statusException;
				}

				dto.getLgsDdg().setDlvStatCd(DeliveryEnum.DLV_STAT_DLIVY_COMPT.toString());//출고완료

				Ord ord = new Ord();
				ord.setOrdNo(dto.getOrd().getOrdNo());
				ord.setUdterId(userId);
				ord.setOrdStatCd(DeliveryEnum.OrdStatCd.DLV_PROGRS.toString());	//배송중
				orderBoService.updateOrdStatCd(ord);
            }
		}

		deliveryCommandService.updateInoviceCompare(systemPK, listDTO, deliveryInvoiceDTO);//운송장택배사정보 수정

		return true;
	}
	
	/** 출고완료. */
	@Override
	public boolean updateCompleteDelivery(SystemPK systemPK,
			List<DeliveryOrderGoodResult> listDTO,
			DeliveryInvoiceDTO deliveryInvoiceDTO) throws Exception {
		
		String userId = BOSecurityUtil.getLoginId();	//세션ID
		
		if (listDTO != null && listDTO.size() > 0) {
			for (DeliveryOrderGoodResult dto : listDTO) {
				
				//출고지시상품데이터 조회 및 주문취소여부 확인
				DeliveryOrderGoodDTO param = new DeliveryOrderGoodDTO();
				param.setDlivyDrctGodNo(dto.getLgsDdg().getDlivyDrctGodNo());
				LgsDlivyDrctGod info = deliveryStatusService.selectLgsDdgInfo(param);
				
				if (!DeliveryEnum.DLV_STAT_DLIVY_DRCT.toString().equals(dto.getLgsDdg().getDlvStatCd())) {
					DeliveryStatusException statusException = new DeliveryStatusException(null);
					statusException.setDirectMessage("출고지시 상태에서만 사용할 수 있습니다.");
					throw statusException;
				}
				
				//출고건 취소 체크
				if(DeliveryEnum.DLV_STAT_DLIVY_DRCT_CNCL.toString().equals(info.getDlvStatCd())) {
					DeliveryStatusException statusException = new DeliveryStatusException(null);
					statusException.setDirectMessage("주문취소건이 존재합니다.\n확인 후 처리하세요.");
					throw statusException;
					
					//출고상태 체크
				} else if(DeliveryEnum.DLV_STAT_DLIVY_COMPT.toString().equals(info.getDlvStatCd()) || "DLV_COMPT".equals(info.getDlvStatCd())) {
					DeliveryStatusException statusException = new DeliveryStatusException(null);
					statusException.setDirectMessage("이미 출고처리가 완료되었습니다.\n확인 후 처리하세요.");
					throw statusException;
					
					//출고건 부분취소 체크
				} else if( dto.getLgsDdg().getDlivyDrctQty() != info.getDlivyDrctQty() ) {
					DeliveryStatusException statusException = new DeliveryStatusException(null);
					statusException.setDirectMessage("부분취소건이 존재합니다.\n수량 확인 후 다시 처리하세요.");
					throw statusException;
				}
				
				dto.getLgsDdg().setDlvStatCd(DeliveryEnum.DLV_STAT_DLIVY_COMPT.toString());//출고완료
				
				Ord ord = new Ord();
				ord.setOrdNo(dto.getOrd().getOrdNo());
				ord.setUdterId(userId);
				ord.setOrdStatCd(DeliveryEnum.OrdStatCd.DLV_PROGRS.toString());	//배송중
				orderBoService.updateOrdStatCd(ord);
			}
		}
		
		deliveryCommandService.updateCompleteDelivery(systemPK, listDTO, deliveryInvoiceDTO);//배송상태변경
//		deliveryCommandService.updateInoviceCompare(systemPK, listDTO, deliveryInvoiceDTO);//운송장택배사정보 수정
//		deliveryCommandService.updateDeliveryShopMemo(systemPK, listDTO, deliveryInvoiceDTO);//배송메모수정
		
		return true;
	}
	
	/** 입점업체 결품접수 */
	@Override
	public String updatePartMallShortage(SystemPK systemPk, List<DeliveryOrderGoodResult> gridList) throws Exception {
		List<DeliveryOrderGoodResult> packList = new ArrayList<DeliveryOrderGoodResult>();
		LgsDlivyDrctGod lgsDdg = new LgsDlivyDrctGod();
		String udterId = BOSecurityUtil.getLoginId();	//세션ID
		String message = "";
		boolean pckgYn = false;
		int procCnt = 0;	//처리건수
		
		for (DeliveryOrderGoodResult dto : gridList) {
			//패키지 상품
			if(null != dto.getLgsDdg().getPckageGodTurn() && 0 != dto.getLgsDdg().getPckageGodTurn()) {
				packList.add(dto);
			//일반 상품		
			} else {
				lgsDdg.setDlivyDrctGodNo(dto.getLgsDdg().getDlivyDrctGodNo());
				lgsDdg.setShtgQty(dto.getLgsDdg().getDlivyDrctQty());
				lgsDdg.setUdterId(udterId);
				
				procCnt += deliveryStatusService.updatePartMallShortage(systemPk, lgsDdg);
			}
		}
		
		if(packList.size() > 0) {
			pckgYn = true;
			List<DeliverySearchDTO> plist = deliveryAssignService.filterDuplPackageItem(packList);
						
			for (DeliverySearchDTO dto : plist) {
				//패키지 구성상품 조회 :: 구성품별 묶음처리
				List<DeliveryOrderGoodResult> packList2 = deliveryStatusService.selectDeliveryInfoPckage(dto);
				
				for (DeliveryOrderGoodResult packObj : packList2) {
					lgsDdg.setDlivyDrctGodNo(packObj.getLgsDdg().getDlivyDrctGodNo());
					lgsDdg.setShtgQty((long) packObj.getLgsDdg().getDlivyDrctQty());
					lgsDdg.setUdterId(udterId);
					
					procCnt += deliveryStatusService.updatePartMallShortage(systemPk, lgsDdg);					
				}
			}
		}
		
		if(procCnt > 0) {
			if(pckgYn) {
				message = "패키지의 모든 구성품 포함 총 "+procCnt+"건이 처리되었습니다.";
			} else {
				message = "총 "+procCnt+"건이 처리되었습니다.";
			}
		} else {
			message = "처리된 건이 없습니다.";
		}
		
		return message;
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * Constructors.
	 * ---------------------------------------------------------------------
	 */

}
