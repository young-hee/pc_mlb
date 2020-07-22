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
package com.plgrim.ncp.cmp.orderfulfilment.bo.delivery.inspect;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.plexus.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.plgrim.ncp.base.entities.datasource1.cso.CsoCnsltMemo;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsDlivyDrctGod;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsDlivyDrctGodHist;
import com.plgrim.ncp.base.entities.datasource1.ord.Ord;
import com.plgrim.ncp.base.entities.datasource1.ord.OrdGodInv;
import com.plgrim.ncp.base.enums.OrderClaimEnum;
import com.plgrim.ncp.biz.callcenter.service.MemoService;
import com.plgrim.ncp.biz.delivery.data.DeliveryInvoiceDTO;
import com.plgrim.ncp.biz.delivery.data.DeliveryOrderGoodDTO;
import com.plgrim.ncp.biz.delivery.data.DeliverySearchDTO;
import com.plgrim.ncp.biz.delivery.exception.DeliveryStatusException;
import com.plgrim.ncp.biz.delivery.repository.DeliveryCommandRepository;
import com.plgrim.ncp.biz.delivery.result.DeliveryOrderGoodResult;
import com.plgrim.ncp.biz.delivery.service.DeliveryAssignService;
import com.plgrim.ncp.biz.delivery.service.DeliveryAutoAssignService;
import com.plgrim.ncp.biz.delivery.service.DeliveryInfErpService;
import com.plgrim.ncp.biz.delivery.service.DeliveryListService;
import com.plgrim.ncp.biz.delivery.service.DeliveryStatusService;
import com.plgrim.ncp.biz.order.service.OrderBoService;
import com.plgrim.ncp.cmp.orderfulfilment.bo.delivery.DeliveryInspectBoComponent;
import com.plgrim.ncp.cmp.orderfulfilment.bo.delivery.DeliveryReleaseBoComponent;
import com.plgrim.ncp.commons.util.BOSecurityUtil;
import com.plgrim.ncp.framework.commons.JsonService;
import com.plgrim.ncp.framework.commons.StringService;
import com.plgrim.ncp.framework.data.SystemPK;
import com.plgrim.ncp.interfaces.order.adapter.OrderAdapter;
import com.plgrim.ncp.interfaces.order.data.OrderOnlineShopRtListSDO;
import com.plgrim.ncp.interfaces.order.data.OrderOnlineShopRtSDO;
import com.plgrim.ncp.interfaces.util.AdapterHeader;
import com.plgrim.ncp.interfaces.util.InterfaceApiCommon;
import com.plgrim.ncp.interfaces.util.InterfaceConstants;

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
public class DeliveryInspectBoComponentImpl implements DeliveryInspectBoComponent {

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	@Autowired
	DeliveryListService deliveryListService;

	@Autowired
	DeliveryInfErpService deliveryInfErpService;
	
	@Autowired
	DeliveryStatusService deliveryStatusService;
	
	@Autowired
	DeliveryAssignService deliveryAssignService;
	
	@Autowired
	OrderBoService orderBoService;
	
	@Autowired
	DeliveryReleaseBoComponent deliveryReleaseBoComponent;
	
	@Autowired
	DeliveryAutoAssignService deliveryAutoAssignService;
	
	@Autowired
	OrderAdapter orderAdapter;
	
	@Autowired
	InterfaceApiCommon interfaceApiCommon;
	
	@Autowired
	DeliveryCommandRepository deliveryCommandRepository;
	
	@Autowired
    MemoService memoService;

	/** 복품출고 검수 리스트 조회 */
	@Override
	public List<DeliveryOrderGoodResult> getGoodsInspectList(SystemPK systemPK, DeliverySearchDTO deliverySearchDTO) throws Exception {
		return deliveryListService.getGoodsInspectList(systemPK, deliverySearchDTO);
	}

	/** 상품 검수정보 조회. */
	@Override
	public List<DeliveryOrderGoodResult> selectItemCheckList(SystemPK systemPK, DeliverySearchDTO deliverySearchDTO) throws Exception {
		return deliveryListService.selectItemCheckList(systemPK,deliverySearchDTO);
	}

	/** 시리얼번호 중복체크 */
	@Override
	public int isUsedErpGodSnCnt(SystemPK systemPK, DeliverySearchDTO deliverySearchDTO) throws Exception {
		return deliveryListService.isUsedErpGodSnCnt(systemPK, deliverySearchDTO);
	}

	/**
	 * 출고상품 검수
	 */
	@Override
	public String inspectDeliveryGood(SystemPK systemPk, DeliveryOrderGoodDTO dto) throws Exception {
		LgsDlivyDrctGod lgsDdg = new LgsDlivyDrctGod();
		DeliverySearchDTO deliverySearchDTO = new DeliverySearchDTO();
		String status = "";
		String result = "";
		String erpStyleNoTag = "";
		boolean tmallFlag = StringUtils.defaultString(dto.getMallId()).equalsIgnoreCase("TMALL");
		boolean frgnFlag = !StringUtils.defaultString(dto.getLangCd()).equalsIgnoreCase("KOR");

		//1. 주문취소건 체크
		lgsDdg.setDlivyDrctGodNo(dto.getDlivyDrctGodNo());
		status = deliveryStatusService.getDeliveryStatus(lgsDdg);

		if ("DLIVY_DRCT_CNCL".equals(status)) {
			DeliveryStatusException statusException = new DeliveryStatusException(null);
			statusException.setDirectMessage("주문취소건이 존재합니다.\n확인 후 처리하세요.");
			throw statusException;

		} else if ("DLIVY_COMPT".equals(status) || "DLV_COMPT".equals(status)) {
			DeliveryStatusException statusException = new DeliveryStatusException(null);
			statusException.setDirectMessage("이미 출고처리가 완료되었습니다.\n확인 후 처리하세요.");
			throw statusException;

		}

		// 해외의 경우 TAG_SERIAL 조회 안함
		if (tmallFlag || frgnFlag) {
			dto.setErpGodSn("");
		} else {

			//2. ERP 전송 Tag Serial 세팅
			if (StringService.isNotEmpty(dto.getSkuNo())) {
				if (dto.getSkuNo().length() == 11) {
					erpStyleNoTag = dto.getSkuNo() + "  " + dto.getErpGodSn();
				} else if (dto.getSkuNo().length() == 12) {
					erpStyleNoTag = dto.getSkuNo() + " " + dto.getErpGodSn();
				} else {
					erpStyleNoTag = dto.getSkuNo() + dto.getErpGodSn();
				}
			}
        }

        //4. 검수 처리(TAG Serial 등록 + 검수여부 수정)
        boolean success = deliveryStatusService.updateInspectionInfo(dto);

        // 해외의 경우 TAG_SERIAL 조회 안함
        if(!tmallFlag && !frgnFlag && !success) {
            DeliveryStatusException statusException = new DeliveryStatusException(null);
            statusException.setDirectMessage("처리된 [TAG Serial]입니다.\n확인 후 처리하세요.");
            throw statusException;
        }

        return result;
	}

	/** 검수 취소 */
	@Override
	public void resetInspection(SystemPK systemPk, DeliveryOrderGoodDTO dto) throws Exception {
		deliveryStatusService.updateInspectionReset(dto);
	}
	
	/** 입고상품 검수 */
	@Override
	public String retrievalDeliveryGood(SystemPK systemPk, DeliveryOrderGoodDTO dto) throws Exception {
		String result = "";
		String erpStyleNoTag = "";
        boolean tmallFlag = StringUtils.defaultString(dto.getMallId()).equalsIgnoreCase("TMALL");
		boolean frgnFlag = !StringUtils.defaultString(dto.getLangCd()).equalsIgnoreCase("KOR");

        // 해외의 경우 TAG_SERIAL 조회 안함
        if (tmallFlag || frgnFlag) {
            dto.setErpGodSn("");
        } else {
            //1. ERP 전송 Tag Serial 세팅retrievalDeliveryGood
            if (StringService.isNotEmpty(dto.getSkuNo())) {
                if (dto.getSkuNo().length() == 11) {
                    erpStyleNoTag = dto.getSkuNo() + "  " + dto.getErpGodSn();
                } else if (dto.getSkuNo().length() == 12) {
                    erpStyleNoTag = dto.getSkuNo() + " " + dto.getErpGodSn();
                } else {
                    erpStyleNoTag = dto.getSkuNo() + dto.getErpGodSn();
                }
            }
        }

		//3. 검수 처리
		deliveryStatusService.updateRtrvlInspectionInfo(dto);

		return result;
	}	
	
	/**
	 * 출고검수여부 수정
	 */
	@Override
	public void updateAcptYn(SystemPK systemPk, DeliveryOrderGoodDTO dto) throws Exception {
		String status = "";
		String userId = BOSecurityUtil.getLoginId();	//세션ID

		//1. 주문취소건 체크
		LgsDlivyDrctGod lgsDdg = new LgsDlivyDrctGod();
		lgsDdg.setDlivyDrctGodNo(dto.getDlivyDrctGodNo());
		status = deliveryStatusService.getDeliveryStatus(lgsDdg);

		if ("DLIVY_DRCT_CNCL".equals(status)) {
			DeliveryStatusException statusException = new DeliveryStatusException(null);
			statusException.setDirectMessage("주문취소건이 존재합니다.\n확인 후 처리하세요.");
			throw statusException;

		} else if ("DLIVY_COMPT".equals(status) || "DLV_COMPT".equals(status)) {
			DeliveryStatusException statusException = new DeliveryStatusException(null);
			statusException.setDirectMessage("이미 출고처리가 완료되었습니다.\n확인 후 처리하세요.");
			throw statusException;

		}

		//2. 출고검수여부 수정
		deliveryStatusService.updateAcptYn(dto);

		//3. 출고지시 상품 이력 등록
		LgsDlivyDrctGodHist hist = new LgsDlivyDrctGodHist();
		hist.setDlivyDrctGodNo(dto.getDlivyDrctGodNo());
		hist.setRegtrId("REQ_INSPCT");
		hist.setUdterId(userId);
		deliveryStatusService.insertLgsDlivyDrctGodHist(hist);

	}
	
	/** [출고상품검수] ERP매출전송 :: 단일 주문건 처리 */
	@Override
	public void transToErp(SystemPK systemPk, List<DeliveryOrderGoodResult> gridList) throws Exception {
		LgsDlivyDrctGod lgsDdg = new LgsDlivyDrctGod();
		DeliveryInvoiceDTO sdto = new DeliveryInvoiceDTO();
		LgsDlivyDrctGodHist hist = new LgsDlivyDrctGodHist();
		String userId = BOSecurityUtil.getLoginId();	//세션ID

		//1. 대상 필터링 :: '주문상품ERP분배' 테이블 단위의 데이터를 '출고지시상품' 테이블 단위로 필터링
		List<DeliveryOrderGoodDTO> target = deliveryStatusService.filterlingTarget(gridList);

		List<OrderOnlineShopRtListSDO> allDetailList = new ArrayList<>();
		for (DeliveryOrderGoodDTO dto : target) {
			//2. 출고지시상품데이터 조회 및 주문취소여부 확인
			lgsDdg.setDlivyDrctGodNo(dto.getDlivyDrctGodNo());
			LgsDlivyDrctGod info = deliveryStatusService.selectLgsDdgInfo(dto);
			if("SHOP_PKUP".equals(info.getDlivyDrctTpCd())) {
				// INF_ORD_GOD_ERP_DSTB 수량단위 조회후 온라인->매장 RT 받음 처리
				OrderOnlineShopRtListSDO search = new OrderOnlineShopRtListSDO();
				search.setOrdNo(info.getOrdNo());
				search.setOrdDtlNo(info.getDlivyDrctGodNo());
				List<OrderOnlineShopRtListSDO> detailList = deliveryCommandRepository.selectDlivyComptRtDetail(search);
				for(OrderOnlineShopRtListSDO detail : detailList)
				{
					allDetailList.add(detail);
				}
			}
		}
		
		int idx=0;
		for (DeliveryOrderGoodDTO dto : target) {

			//2. 출고지시상품데이터 조회 및 주문취소여부 확인
			lgsDdg.setDlivyDrctGodNo(dto.getDlivyDrctGodNo());
			LgsDlivyDrctGod info = deliveryStatusService.selectLgsDdgInfo(dto);

			//출고건 취소 체크
			if("DLIVY_DRCT_CNCL".equals(info.getDlvStatCd())) {
				DeliveryStatusException statusException = new DeliveryStatusException(null);
				statusException.setDirectMessage("주문취소건이 존재합니다.\n확인 후 처리하세요.");
				throw statusException;
			//출고상태 체크
			} else if("DLIVY_COMPT".equals(info.getDlvStatCd()) || "DLV_COMPT".equals(info.getDlvStatCd())) {
				DeliveryStatusException statusException = new DeliveryStatusException(null);
				statusException.setDirectMessage("이미 출고처리가 완료되었습니다.\n확인 후 처리하세요.");
				throw statusException;
			}
			
			//3. 배송상태 수정
			sdto.setDlivyDrctGodNo(info.getDlivyDrctGodNo());
			sdto.setDlivyDrctQty(info.getDlivyDrctQty().intValue());
			sdto.setUdterId(userId);
			//매장픽업 :: 배송완료 처리
			if("SHOP_PKUP".equals(info.getDlivyDrctTpCd())) {
				
				// 온라인->매장 RT 받음 처리 들어가야함.
				OrderOnlineShopRtSDO orderOnlineRtSendSDO = new OrderOnlineShopRtSDO();
				AdapterHeader adapterHeader = new AdapterHeader();
				adapterHeader.setRequestId(this.interfaceApiCommon.getRequestId());
				adapterHeader.setMallId("DX");
				adapterHeader.setLangCd("KOR");
				adapterHeader.setDvcCd("PC");
				
				orderOnlineRtSendSDO.setOrdList(allDetailList);
            	String resultString = orderAdapter.storeDeliConfirm(orderOnlineRtSendSDO, adapterHeader);
            	OrderOnlineShopRtSDO orderOnlineShopRtSDO = JsonService.unmarshalling(resultString, OrderOnlineShopRtSDO.class);
            	
            	// 전체 RT 성공시에만 업데이트.
            	if(InterfaceConstants.IF_RESULT_CD_SUCESS.equals(orderOnlineShopRtSDO.getResponseCd()))
    			{
    				sdto.setDlivyDrctTpCd(info.getDlivyDrctTpCd());
    				sdto.setDlvStatCd("DLV_COMPT");		//배송완료
    				deliveryStatusService.updateDeliveryStat(sdto);
    				
    				//4. 출고지시 상품 이력 등록
    				hist.setDlivyDrctGodNo(info.getDlivyDrctGodNo());
    				hist.setRegtrId("TRANS_ERP");
    				hist.setUdterId(userId);
    				deliveryStatusService.insertLgsDlivyDrctGodHist(hist);
    				
//    				if(idx == target.size()-1) {
    					//6. 주문상태 수정
    					Ord ord = new Ord();
    					ord.setOrdNo(info.getOrdNo());
    					ord.setUdterId(userId);
    					//매장픽업 :: 배송완료 처리
    					ord.setOrdStatCd("DLV_COMPT");	//배송완료
    					orderBoService.updateOrdStatAboutCompt(ord);

    					// SMS 발송
//    					try {
//    						deliveryStatusService.sendPickupCompleteSms(systemPk, target);
//    					}
//    					catch (Exception e) {
//    						e.printStackTrace();
//    					}
//    				}
    			}else
    			{
    				log.info("storeDeliConfirm FAIL : {} {}",dto.getDlivyDrctGodNo() ,orderOnlineShopRtSDO);
    				DeliveryStatusException statusException = new DeliveryStatusException(null);
    				statusException.setDirectMessage("RT 처리에 실패하였습니다.");
    				throw statusException;
    			}
			//출고완료 처리
			} else {
				sdto.setDlvStatCd("DLIVY_COMPT");	//출고완료
				deliveryStatusService.updateDeliveryStat(sdto);
				
				//4. 출고지시 상품 이력 등록
				hist.setDlivyDrctGodNo(info.getDlivyDrctGodNo());
				hist.setRegtrId("TRANS_ERP");
				hist.setUdterId(userId);
				deliveryStatusService.insertLgsDlivyDrctGodHist(hist);
				
//				if(idx == target.size()-1) {
					//6. 주문상태 수정
					Ord ord = new Ord();
					ord.setOrdNo(info.getOrdNo());
					ord.setUdterId(userId);
					ord.setOrdStatCd("DLV_PROGRS");	//배송중
					orderBoService.updateOrdStatCd(ord);
//				}
			}

			//7. 맞교환건 회수지시 처리
			if("DRT_EXCHG".equals(info.getDlivyDrctTpCd())) {
				//맞교환 출고건 회수정보 수정 :: 회수지시 대기 ->  회수지시
				deliveryStatusService.updateRtrvlInfo4DrctExch(info);
			}
			idx++;
		}
		
	}
	
	/** 결품 */
	@Override
	public void shopShortage(SystemPK systemPk, List<DeliveryOrderGoodResult> gridList) throws Exception {
		LgsDlivyDrctGod lgsDdg = new LgsDlivyDrctGod();
		DeliveryInvoiceDTO sdto = new DeliveryInvoiceDTO();
		LgsDlivyDrctGodHist hist = new LgsDlivyDrctGodHist();
		String userId = BOSecurityUtil.getLoginId();	//세션ID

		//1. 대상 필터링 :: '주문상품ERP분배' 테이블 단위의 데이터를 '출고지시상품' 테이블 단위로 필터링
		List<DeliveryOrderGoodDTO> target = deliveryStatusService.filterlingTarget(gridList);

		List<OrderOnlineShopRtListSDO> allDetailList = new ArrayList<>();
		for (DeliveryOrderGoodDTO dto : target) {
			//2. 출고지시상품데이터 조회 및 주문취소여부 확인
			lgsDdg.setDlivyDrctGodNo(dto.getDlivyDrctGodNo());
			LgsDlivyDrctGod info = deliveryStatusService.selectLgsDdgInfo(dto);
			if("SHOP_PKUP".equals(info.getDlivyDrctTpCd())) {
				// INF_ORD_GOD_ERP_DSTB 수량단위 조회후 온라인->매장 RT 받음 처리
				OrderOnlineShopRtListSDO search = new OrderOnlineShopRtListSDO();
				search.setOrdNo(info.getOrdNo());
				search.setOrdDtlNo(info.getDlivyDrctGodNo());
				List<OrderOnlineShopRtListSDO> detailList = deliveryCommandRepository.selectDlivyComptRtDetail(search);
				for(OrderOnlineShopRtListSDO detail : detailList)
				{
					allDetailList.add(detail);
				}
			}
		}
		

		for (DeliveryOrderGoodDTO dto : target) {

			//2. 출고지시상품데이터 조회 및 주문취소여부 확인
			lgsDdg.setDlivyDrctGodNo(dto.getDlivyDrctGodNo());
			LgsDlivyDrctGod info = deliveryStatusService.selectLgsDdgInfo(dto);

			//출고건 취소 체크
			if("DLIVY_DRCT_CNCL".equals(info.getDlvStatCd())) {
				DeliveryStatusException statusException = new DeliveryStatusException(null);
				statusException.setDirectMessage("주문취소건이 존재합니다.\n확인 후 처리하세요.");
				throw statusException;
			//출고상태 체크
			} else if("DLIVY_COMPT".equals(info.getDlvStatCd()) || "DLV_COMPT".equals(info.getDlvStatCd())) {
				DeliveryStatusException statusException = new DeliveryStatusException(null);
				statusException.setDirectMessage("이미 출고처리가 완료되었습니다.\n확인 후 처리하세요.");
				throw statusException;
			}
			
			//6. 주문상태 수정
			OrdGodInv ordGodInv = new OrdGodInv();
			ordGodInv.setOrdNo(info.getOrdNo());
			ordGodInv.setUdterId(userId);
			ordGodInv.setOrdQty(info.getDlivyDrctQty());
			ordGodInv.setOrdGodTurn(info.getOrdGodTurn());

			
			orderBoService.updateShopShortage(ordGodInv);
			
			// 메모

			CsoCnsltMemo csoCnsltMemo = new CsoCnsltMemo();

			csoCnsltMemo.setOrdNo(info.getOrdNo());
			//csoCnsltMemo.setMbrNo(info);
			csoCnsltMemo.setMemoCont("매장수령 / "+info.getDlvShopNm()+" / "+info.getDlvGodInfo()+" 결품으로 OB콜 필요");
			csoCnsltMemo.setMemoRegtrId(userId);
			csoCnsltMemo.setRegtrId(userId);
			csoCnsltMemo.setUdterId(userId);
			csoCnsltMemo.setMemoTpCd(OrderClaimEnum.ORD.toString());
			csoCnsltMemo.setExpsrYn("N");
			
			memoService.insertCsoCnsltMemo(csoCnsltMemo);

		}
		
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * Constructors.
	 * ---------------------------------------------------------------------
	 */

}
