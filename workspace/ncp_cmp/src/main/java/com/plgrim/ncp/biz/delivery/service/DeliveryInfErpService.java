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
 * @since       2015. 11. 9       
 */
package com.plgrim.ncp.biz.delivery.service;

import com.plgrim.ncp.base.abstracts.AbstractService;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsDlivyDrctGod;
import com.plgrim.ncp.base.enums.DeliveryEnum;
import com.plgrim.ncp.biz.delivery.data.DeliveryInfErpDTO;
import com.plgrim.ncp.biz.delivery.data.DeliverySearchDTO;
import com.plgrim.ncp.biz.delivery.repository.DeliveryCommandRepository;
import com.plgrim.ncp.biz.delivery.result.DeliveryOrderGoodResult;
import com.plgrim.ncp.commons.util.BOSecurityUtil;
import com.plgrim.ncp.framework.systems.SystemContext;
import com.plgrim.ncp.interfaces.util.AdapterHeader;
import com.plgrim.ncp.interfaces.util.InterfaceApiCommon;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
 * @author jwcale.kim
 * @since 2015. 4. 25
 */

/** The Constant log. */
@Slf4j
@Service
public class DeliveryInfErpService extends AbstractService {

	/** The delivery command repository. */
	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	@Autowired
	DeliveryCommandRepository deliveryCommandRepository;
	
	/** The interface api common. */
	@Autowired
	InterfaceApiCommon interfaceApiCommon;

	@Autowired
	SystemContext systemContext;
	
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
	 * CDC출고지시건 정보 조회.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param deliverySearchDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 4. 25
	 */
	public List<DeliveryInfErpDTO> selectDlivyDrctInfoList(DeliverySearchDTO deliverySearchDTO) throws Exception {
		return deliveryCommandRepository.selectDlivyDrctInfoList(deliverySearchDTO);
	}
	
	
	/**
	 * 예약영수증 취소건 정보 조회.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param deliverySearchDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 4. 25
	 */
	public List<DeliveryInfErpDTO> selectErpResveRcptfrCancelInfoList(DeliverySearchDTO deliverySearchDTO) throws Exception {
		return deliveryCommandRepository.selectErpResveRcptfrCancelInfoList(deliverySearchDTO);
	}
	
	
	/**
	 * 예약영수증 생성건 정보 조회.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param deliverySearchDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 4. 25
	 */
	public List<DeliveryInfErpDTO> selectErpResveRcptfrCreateInfoList(DeliverySearchDTO deliverySearchDTO) throws Exception {
		return deliveryCommandRepository.selectErpResveRcptfrCreateInfoList(deliverySearchDTO);
	}
	
	
	/**
	 * CDC 출고의뢰 취소.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param list [설명]
	 * @return Delivery inf erp dto [설명]
	 * @throws Exception the exception
	 * @since 2015. 4. 27
	 */
	public DeliveryInfErpDTO modifyPlantOrderCancel(List<DeliveryInfErpDTO> list) throws Exception {
		DeliveryInfErpDTO resultDTO = new DeliveryInfErpDTO();
//		LgsDlivySDO lgsDlivySDO = new LgsDlivySDO();
//		List<LgsDlivySDO> parameters = new ArrayList<LgsDlivySDO>();
//
//		//check issue 파라미터 세팅
//		for (DeliveryInfErpDTO deliveryInfErpDTO : list) {
//			LgsDlivySDO paramSdo = new LgsDlivySDO();
//			paramSdo.setVbeln(deliveryInfErpDTO.getVbeln());
//			paramSdo.setFpiaOr(deliveryInfErpDTO.getFpiaOr());
//			paramSdo.setSeqNo(deliveryInfErpDTO.getSeqNo());
//			paramSdo.setWadatIst(deliveryInfErpDTO.getWadatIst());
//			paramSdo.setMatnr(deliveryInfErpDTO.getMatnr());
//			paramSdo.setMenge(deliveryInfErpDTO.getMenge());
//			paramSdo.setMeins(deliveryInfErpDTO.getMeins());
//			paramSdo.setCallerId(InterfaceAdapterEnum.RFC_NM_YRSD_FP_CHECK_ISSUE.toString() + "_modifyPlantOrderCancel");
//			parameters.add(paramSdo);
//		}
//
//		//check issue
//		LgsDlivySDO resultCheck = lgsDlivyAdapter.getCheckIssueList(parameters, getHeader());
//		resultDTO.setResult(resultCheck.getResult());
//		resultDTO.setRfcType("CHECK_ISSUE");
//		log.info("result CHECK_ISSUE :: "+resultDTO);
//
//		// 출고확정건이 없을 경우 해당건 CancelPlantOrder
//		if("Y".equals(resultCheck.getResult())) {
//			for (DeliveryInfErpDTO deliveryInfErpDTO : list) {
//				//Interface 파라미터 세팅
//				lgsDlivySDO.setVbeln(deliveryInfErpDTO.getVbeln());
//				lgsDlivySDO.setFpiaOr(deliveryInfErpDTO.getFpiaOr());
//				lgsDlivySDO.setSeqNo(deliveryInfErpDTO.getSeqNo());
//				lgsDlivySDO.setWadatIst(deliveryInfErpDTO.getWadatIst());
//				lgsDlivySDO.setMatnr(deliveryInfErpDTO.getMatnr());
//				lgsDlivySDO.setMenge(deliveryInfErpDTO.getMenge());
//				lgsDlivySDO.setMeins(deliveryInfErpDTO.getMeins());
//				lgsDlivySDO.setSto(deliveryInfErpDTO.getSto());
//				lgsDlivySDO.setCallerId( DeliveryInfErpService.class.getSimpleName() +"_modifyPlantOrderCancel");
//
//				//출고의뢰 취소
//				LgsDlivySDO result = lgsDlivyAdapter.modifyCancelPlantOrder(lgsDlivySDO, getHeader());
//				resultDTO.setResult(result.getResult());
//				resultDTO.setRfcType("CANCEL_PLANT_ORDER");
//				log.info("result CANCEL_PLANT_ORDER :: "+resultDTO);
//
//				if(StringService.isEmpty(result.getResult())) {
//					//인터페이스주문상품 출고지시 취소
//					deliveryCommandRepository.updateInfErpDlivyDrctCancel(deliveryInfErpDTO);
//
//					//물류출고지시상품 출고지시 취소
//					int scount = deliveryCommandRepository.updateLgsDlivyDrctCancel(deliveryInfErpDTO);
//					if(scount == 1) {
//						LgsDlivyDrctGodHist lgsDlivyDrctGodHist = new LgsDlivyDrctGodHist();
//						lgsDlivyDrctGodHist.setRegtrId("CANCEL_PLANT_ORDER");
//						lgsDlivyDrctGodHist.setUdterId("CANCEL_PLANT_ORDER");	//FO 조세형 차장 수정.
//						lgsDlivyDrctGodHist.setDlivyDrctGodNo(deliveryInfErpDTO.getDlivyDrctGodNo());
//						deliveryCommandRepository.insertLgsDlivyDrctGodHist(lgsDlivyDrctGodHist);
//					}
//
//				} else if("E".equals(result.getResult())) {
//					DeliveryInterfaceException ifException = new DeliveryInterfaceException(null);
//					ifException.setDirectMessage("ERP납품문서 삭제요청 중 오류가 발생하였습니다.");
//					throw ifException;
//
//				}
//			}
//		}
		return resultDTO;
	}
	
	
	/**
	 * [클레임용] CDC 출고의뢰 취소.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param list [설명]
	 * @throws Exception the exception
	 * @since 2015. 4. 27
	 */
	public void modifyPlantOrderCancelForClm(List<DeliveryInfErpDTO> list) throws Exception {
//		LgsDlivySDO lgsDlivySDO = new LgsDlivySDO();
//		ObjectMapper om1 = new ObjectMapper();
//
//		// 출고확정건이 없을 경우 해당건 CancelPlantOrder
//		for (DeliveryInfErpDTO deliveryInfErpDTO : list) {
//			//Interface 파라미터 세팅
//			lgsDlivySDO.setVbeln(deliveryInfErpDTO.getVbeln());
//			lgsDlivySDO.setFpiaOr(deliveryInfErpDTO.getFpiaOr());
//			lgsDlivySDO.setSeqNo(deliveryInfErpDTO.getSeqNo());
//			lgsDlivySDO.setWadatIst(deliveryInfErpDTO.getWadatIst());
//			lgsDlivySDO.setMatnr(deliveryInfErpDTO.getMatnr());
//			lgsDlivySDO.setMenge(deliveryInfErpDTO.getMenge());
//			lgsDlivySDO.setMeins(deliveryInfErpDTO.getMeins());
//			lgsDlivySDO.setSto(deliveryInfErpDTO.getSto());
//			lgsDlivySDO.setCallerId( DeliveryInfErpService.class.getSimpleName() +"_modifyPlantOrderCancelForClm");
//
//			String werks = deliveryInfErpDTO.getWerks();
//
//			//출고의뢰 취소
//			LgsDlivySDO result = lgsDlivyAdapter.modifyCancelPlantOrder(lgsDlivySDO, getHeader());
//
//			if(StringService.isEmpty(result.getResult())) {
//				//인터페이스주문상품 출고지시 취소
//				deliveryCommandRepository.updateInfErpDlivyDrctCancel(deliveryInfErpDTO);
//
//				//물류출고지시상품 출고지시 취소
//				int scount = deliveryCommandRepository.updateLgsDlivyDrctCancel(deliveryInfErpDTO);
//				if(scount == 1) {
//					LgsDlivyDrctGodHist lgsDlivyDrctGodHist = new LgsDlivyDrctGodHist();
//					lgsDlivyDrctGodHist.setRegtrId("CANCEL_PLANT_ORDER");
//					lgsDlivyDrctGodHist.setUdterId("CANCEL_PLANT_ORDER");	//FO 조세형 차장 수정.
//					lgsDlivyDrctGodHist.setDlivyDrctGodNo(deliveryInfErpDTO.getDlivyDrctGodNo());
//					deliveryCommandRepository.insertLgsDlivyDrctGodHist(lgsDlivyDrctGodHist);
//				}
//
//			} else if("E".equals(result.getResult())) {
//				DeliveryInterfaceException ifException = new DeliveryInterfaceException(null);
//				ifException.setDirectMessage("ERP납품문서 삭제요청 중 오류가 발생하였습니다.");
//				throw ifException;
//			}
//		}
	}
	
	
	
	
	/**
	 * 클레임 픽업매장 주문 취소시 출고지시 로직.
	 *
	 * @param list [설명]
	 * @throws Exception the exception
	 * @since 2015. 11. 9
	 */
	public void modifyPickUpPlantOrderCancel(List<DeliveryInfErpDTO> list) throws Exception {
//		LgsDlivySDO lgsDlivySDO = new LgsDlivySDO();
//		List<LgsDlivySDO> parameters = new ArrayList<LgsDlivySDO>();
//		LgsDlivySDO paramSdo = new LgsDlivySDO();
//
//
//		//check to 파라미터 세팅
//		for (DeliveryInfErpDTO deliveryInfErpDTO : list) {//1개수량에 1개로우인 리스트
//			if("Y".equals(deliveryInfErpDTO.getIntrlckTgtYn())) {
//				paramSdo = new LgsDlivySDO();
//				paramSdo.setVbeln(deliveryInfErpDTO.getVbeln());
//				paramSdo.setFpiaOr(deliveryInfErpDTO.getFpiaOr());
//				paramSdo.setSeqNo(deliveryInfErpDTO.getSeqNo());
//				paramSdo.setWadatIst(deliveryInfErpDTO.getWadatIst());
//				paramSdo.setMatnr(deliveryInfErpDTO.getMatnr());
//				paramSdo.setMenge(deliveryInfErpDTO.getMenge());
//				paramSdo.setMeins(deliveryInfErpDTO.getMeins());
//				paramSdo.setSto(deliveryInfErpDTO.getSto());
//				paramSdo.setCallerId(InterfaceAdapterEnum.RFC_NM_YRSD_FP_CHECK_TO.toString());
//				parameters.add(paramSdo);
//
//				if(DeliveryEnum.DLV_ONLINE_SHOP_DISCOVERY.toString().equals(deliveryInfErpDTO.getDlivyShopId())
//						&& "Y".equals(deliveryInfErpDTO.getResveRcptfrCreatYn())){	//출고매장이 X30004 (CDC : 물류센터)
//
//					//YRSD_FP_CHECK_TO 실행하여 출고여부 확인
//					LgsDlivySDO resultCheck = lgsDlivyAdapter.getCheckTo(parameters, getHeader());
//
//					if(!"Y".equals(resultCheck.getResult())){//출고되었다면
//
//						//YRSD_FP_ORDER_STATUS 실행하여 매장입고 여부 확인
//						paramSdo.setCallerId(InterfaceAdapterEnum.RFC_NM_YRSD_FP_ORDER_STATUS.toString());
//						resultCheck = lgsDlivyAdapter.checkOrderStatus(paramSdo, getHeader());
//
//						if("Y".equals(resultCheck.getResult())){
//
//							//입고되었다면 ZD5SDC_FP_CANC_STO 실행하여 매장 출고 취소
//							paramSdo.setCallerId(InterfaceAdapterEnum.RFC_NM_ZD5SDC_FP_CANC_STO.toString());
//							lgsDlivyAdapter.modifyCancelSTO(paramSdo, getHeader());
//
//						}else{
//
//							//미입고 되었다면 ZD5SDC_FP_STOTAG_CHANGE 실행하여 픽업매장에서 입고전이면 관련 물류출고 STO(11번) 헤더정보를 변경함
//							paramSdo.setCallerId(InterfaceAdapterEnum.RFC_NM_ZD5SDC_FP_STOTAG_CHANGE.toString());
//							lgsDlivyAdapter.modifyStoTagChange(paramSdo, getHeader());
//
//						}
//					}else{
//
//						//미출고
//						//YRSD_FP_CANCEL_PLANT_ORDER 실행
//						paramSdo.setCallerId(InterfaceAdapterEnum.RFC_NM_YRSD_FP_CANCEL_PLANT_ORDER.toString() + "_modifyPickUpPlantOrderCancel");
//						LgsDlivySDO result = lgsDlivyAdapter.modifyCancelPlantOrder(paramSdo, getHeader());
//
//					}
//				}
//
//				parameters = new ArrayList<LgsDlivySDO>();
//
//			}
//
//
//		}
		
	}
	
	
	
	
	/**
	 * 클레임 픽업매장 주문 취소시 check to  체크.
	 *
	 * @param list [설명]
	 * @return String [설명]
	 * @throws Exception the exception
	 * @since 2015. 11. 9
	 */
	public String checkToForClm(List<DeliveryInfErpDTO> list) throws Exception {
//		LgsDlivySDO lgsDlivySDO = new LgsDlivySDO();
//		List<LgsDlivySDO> parameters = new ArrayList<LgsDlivySDO>();
//		LgsDlivySDO paramSdo = new LgsDlivySDO();
//		//Adapter Header 세팅
//
//		String result ="Y";
//
//		//check to 파라미터 세팅
//		for (DeliveryInfErpDTO deliveryInfErpDTO : list) {//1개수량에 1개로우인 리스트
//			if("Y".equals(deliveryInfErpDTO.getIntrlckTgtYn())) {
//				paramSdo = new LgsDlivySDO();
//				paramSdo.setVbeln(deliveryInfErpDTO.getVbeln());
//				paramSdo.setFpiaOr(deliveryInfErpDTO.getFpiaOr());
//				paramSdo.setSeqNo(deliveryInfErpDTO.getSeqNo());
//				paramSdo.setWadatIst(deliveryInfErpDTO.getWadatIst());
//				paramSdo.setMatnr(deliveryInfErpDTO.getMatnr());
//				paramSdo.setMenge(deliveryInfErpDTO.getMenge());
//				paramSdo.setMeins(deliveryInfErpDTO.getMeins());
//				paramSdo.setSto(deliveryInfErpDTO.getSto());
//				paramSdo.setCallerId(InterfaceAdapterEnum.RFC_NM_YRSD_FP_CHECK_TO.toString());
//				parameters.add(paramSdo);
//
//				if(DeliveryEnum.DLV_ONLINE_SHOP_DISCOVERY.toString().equals(deliveryInfErpDTO.getDlivyShopId())){	//출고매장이 X30004 (CDC : 물류센터)
//
//					//YRSD_FP_CHECK_TO 실행하여 출고여부 확인
//					LgsDlivySDO resultCheck = lgsDlivyAdapter.getCheckTo(parameters, getHeader());
//
//					if("N".equals(resultCheck.getResult())){//출고되었다면
//						result = resultCheck.getResult();
//					}
//
//				}
//			}
//		}
//		return result;
		return "N";
	}
	
	
	
	/**
	 * 예약영수증 취소.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param list [설명]
	 * @throws Exception the exception
	 * @since 2015. 4. 28
	 */
	public void modifyPreSalesCancel(List<DeliveryInfErpDTO> list) throws Exception {
//		List<SalesSDO> parameters = new ArrayList<SalesSDO>();
//		SpecialDCSDO specialDC = new SpecialDCSDO();
//
//		//예약영수증 취소 파라미터세팅
//		for (DeliveryInfErpDTO deliveryInfErpDTO : list) {
//			/*
//			 * 예약영수증생성 리스트 조회 시 'ERP 연동 대상여부' 값을 조회하지 않으므로
//			 * 아래 분기에서 제외
//			 */
//			//if("Y".equals(deliveryInfErpDTO.getIntrlckTgtYn()) && "Y".equals(deliveryInfErpDTO.getErpResveRcptfrCreatYn())){
//			SalesSDO paramSdo = new SalesSDO();
//			paramSdo.setVbeln(deliveryInfErpDTO.getVbeln());
//			paramSdo.setFpiaOr(deliveryInfErpDTO.getFpiaOr());
//			paramSdo.setSeqNo(deliveryInfErpDTO.getSeqNo());
//			paramSdo.setWadatIst(deliveryInfErpDTO.getWadatIst());
//			paramSdo.setMatnr(deliveryInfErpDTO.getMatnr());
//			paramSdo.setMenge(deliveryInfErpDTO.getMenge());
//			paramSdo.setMeins(deliveryInfErpDTO.getMeins());
//			paramSdo.setCbAmt(deliveryInfErpDTO.getCbAmt());
//			paramSdo.setNamt(deliveryInfErpDTO.getNamt());
//			paramSdo.setEmpNo(deliveryInfErpDTO.getEmpNo());
//			paramSdo.setCallerId(InterfaceAdapterEnum.RFC_NM_YRSD_FP_PRE_SALES_CANCEL.toString());
//
//			parameters.add(paramSdo);
//			//}
//		}
//
//		if(parameters != null && parameters.size() > 0) {
//			//예약영수증 취소
//			SalesSDO result = salesAdapter.modifyPreSalesCancel(parameters, getHeader());
//
//			//예약영수증취소 성공여부에 따른 처리
//			if("Y".equals(result.getResult())) {
//				int idx = 0;
//				String bfData = "";
//				for (DeliveryInfErpDTO deliveryInfErpDTO : list) {
//					//인터페이스주문상품 예약영수증 취소
//					deliveryCommandRepository.updateInfErpResveRcptfrCancel(deliveryInfErpDTO);
//
//					//신규 출고지시상품번호에 대한 처리
//					if("".equals(bfData) || !bfData.equals(deliveryInfErpDTO.getDlivyDrctGodNo())) {
//						//물류출고지시상품 예약영수증 취소
//						deliveryCommandRepository.updateLgsResveRcptfrCancel(deliveryInfErpDTO);
//
//						//물류출고지시상품 이력 등록
//						LgsDlivyDrctGodHist lgsDlivyDrctGodHist = new LgsDlivyDrctGodHist();
//						lgsDlivyDrctGodHist.setRegtrId("CANCEL_PRESALES");
//						lgsDlivyDrctGodHist.setUdterId("CANCEL_PRESALES");
//						lgsDlivyDrctGodHist.setDlivyDrctGodNo(deliveryInfErpDTO.getDlivyDrctGodNo());
//						deliveryCommandRepository.insertLgsDlivyDrctGodHist(lgsDlivyDrctGodHist);
//
//						bfData = deliveryInfErpDTO.getDlivyDrctGodNo();
//					}
//
//					//마지막 출고지시상품번호에 대한 처리
//					if(idx == list.size()-1) {
//						//물류출고지시상품 예약영수증 취소
//						deliveryCommandRepository.updateLgsResveRcptfrCancel(deliveryInfErpDTO);
//
//						//물류출고지시상품 이력 등록
//						LgsDlivyDrctGodHist lgsDlivyDrctGodHist = new LgsDlivyDrctGodHist();
//						lgsDlivyDrctGodHist.setRegtrId("CANCEL_PRESALES");
//						lgsDlivyDrctGodHist.setUdterId("CANCEL_PRESALES");
//						lgsDlivyDrctGodHist.setDlivyDrctGodNo(deliveryInfErpDTO.getDlivyDrctGodNo());
//						deliveryCommandRepository.insertLgsDlivyDrctGodHist(lgsDlivyDrctGodHist);
//					}
//					idx++;
//				}
//			} else if("E".equals(result.getResult())) {
//				DeliveryInterfaceException ifException = new DeliveryInterfaceException(null);
//				ifException.setDirectMessage("ERP예약영수증 취소요청 중 오류가 발생하였습니다.");
//				throw ifException;
//			}
//		}
	}

	/**
	 * 클레임 예약영수증 취소.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param list [설명]
	 * @param clmNo [설명]
	 * @throws Exception the exception
	 * @since 2015. 4. 28
	 */
	public void modifyPreSalesCancelForClm(List<DeliveryInfErpDTO> list,String clmNo) throws Exception {
		
//		ObjectMapper om = new ObjectMapper();
//		List<SalesSDO> parameters = new ArrayList<SalesSDO>();
//		SpecialDCSDO specialDC = new SpecialDCSDO();
//
//		boolean preSaleCancel = false;
//
//		//예약영수증 취소 파라미터세팅
//		for (DeliveryInfErpDTO deliveryInfErpDTO : list) {
//			if("Y".equals(deliveryInfErpDTO.getIntrlckTgtYn()) && "Y".equals(deliveryInfErpDTO.getResveRcptfrCreatYn())){
//				preSaleCancel = true;
//				SalesSDO paramSdo = new SalesSDO();
//				paramSdo.setVbeln(deliveryInfErpDTO.getVbeln());
//				paramSdo.setFpiaOr(deliveryInfErpDTO.getFpiaOr());
//				paramSdo.setSeqNo(deliveryInfErpDTO.getSeqNo());
//				paramSdo.setWadatIst(deliveryInfErpDTO.getWadatIst());
//				paramSdo.setMatnr(deliveryInfErpDTO.getMatnr());
//				paramSdo.setMenge(deliveryInfErpDTO.getMenge());
//				paramSdo.setMeins(deliveryInfErpDTO.getMeins());
//				paramSdo.setCbAmt(deliveryInfErpDTO.getCbAmt());
//                paramSdo.setNamt(deliveryInfErpDTO.getNamt());
//				paramSdo.setEmpNo(deliveryInfErpDTO.getEmpNo());
//				paramSdo.setCallerId(InterfaceAdapterEnum.RFC_NM_YRSD_FP_PRE_SALES_CANCEL.toString());
//
//				parameters.add(paramSdo);
//			}
//		}
//
//
//
//		if(preSaleCancel){
//			//예약영수증 취소
//			SalesSDO result = salesAdapter.modifyPreSalesCancel(parameters, getHeader());
//			log.info("예약영수증 취소 결과 : "+om.writeValueAsString(result));
//			//취소영수증을 ord_god_erp 에 등록해준다.
//			DeliveryInfErpDTO deliveryInfErpSearchDTO = new DeliveryInfErpDTO();
//			for(SalesSDO sdo : result.getResultSalesSDO()){
//
//				//DB CODE
//				if("S".equals(sdo.getResult())){
//					sdo.setResult("Y");
//				}
//
//
//				if(sdo.getResult()==null||"".equals(sdo.getResult())){
//					sdo.setResult("E");
//				}
//				deliveryInfErpSearchDTO = new DeliveryInfErpDTO();
//				deliveryInfErpSearchDTO.setErpResveCnclRcptfrNo(sdo.getMessage());//성공이면 취소영수증이오고 실패면 실패 메세지가 온다
//				deliveryInfErpSearchDTO.setClmNo(clmNo);
//				deliveryInfErpSearchDTO.setOrdNo(sdo.getFpiaOr());//주문번호
//				deliveryInfErpSearchDTO.setQtyTurn(sdo.getSeqNo());
//				deliveryInfErpSearchDTO.setErpResveRcptfrCnclYn(sdo.getResult());
//				deliveryCommandRepository.updateInfErpResveRcptfrCancelForClm(deliveryInfErpSearchDTO);
//			}
//
//			//예약영수증취소 성공여부에 따른 처리
//			if("Y".equals(result.getResult())) {
//				int idx = 0;
//				String bfData = "";
//				for (DeliveryInfErpDTO deliveryInfErpDTO : list) {
//					//인터페이스주문상품 예약영수증 취소
//					//deliveryCommandRepository.updateInfErpResveRcptfrCancel(deliveryInfErpDTO);
//
//					//신규 출고지시상품번호에 대한 처리
//					if("".equals(bfData) || !bfData.equals(deliveryInfErpDTO.getDlivyDrctGodNo())) {
//						//물류출고지시상품 예약영수증 취소
//						deliveryCommandRepository.updateLgsResveRcptfrCancel(deliveryInfErpDTO);
//
//						//물류출고지시상품 이력 등록
//						LgsDlivyDrctGodHist lgsDlivyDrctGodHist = new LgsDlivyDrctGodHist();
//						lgsDlivyDrctGodHist.setRegtrId("CANCEL_PRESALES");
//						lgsDlivyDrctGodHist.setUdterId("CANCEL_PRESALES");
//						lgsDlivyDrctGodHist.setDlivyDrctGodNo(deliveryInfErpDTO.getDlivyDrctGodNo());
//						deliveryCommandRepository.insertLgsDlivyDrctGodHist(lgsDlivyDrctGodHist);
//
//						bfData = deliveryInfErpDTO.getDlivyDrctGodNo();
//					}
//
//					//마지막 출고지시상품번호에 대한 처리
//					if(idx == list.size()-1) {
//						//물류출고지시상품 예약영수증 취소
//						deliveryCommandRepository.updateLgsResveRcptfrCancel(deliveryInfErpDTO);
//
//						//물류출고지시상품 이력 등록
//						LgsDlivyDrctGodHist lgsDlivyDrctGodHist = new LgsDlivyDrctGodHist();
//						lgsDlivyDrctGodHist.setRegtrId("CANCEL_PRESALES");
//						lgsDlivyDrctGodHist.setUdterId("CANCEL_PRESALES");
//						lgsDlivyDrctGodHist.setDlivyDrctGodNo(deliveryInfErpDTO.getDlivyDrctGodNo());
//						deliveryCommandRepository.insertLgsDlivyDrctGodHist(lgsDlivyDrctGodHist);
//					}
//					idx++;
//				}
//			} else if("E".equals(result.getResult())) {
//				DeliveryInterfaceException ifException = new DeliveryInterfaceException(null);
//				ifException.setDirectMessage("ERP예약영수증 취소요청 중 오류가 발생하였습니다.");
//				throw ifException;
//			}
//		}

	}
	
	
	/**
	 * ERP 예약영수증 배송지 변경.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param list [설명]
	 * @throws Exception the exception
	 * @since 2015. 7. 21
	 */
	public void modifyPreSalesDlvShopChange(List<DeliveryInfErpDTO> list) throws Exception {
		DeliveryInfErpDTO resultDTO = new DeliveryInfErpDTO();
		//Adapter Header 세팅
		AdapterHeader adapterHeader = new AdapterHeader();
		adapterHeader.setRequestId(this.interfaceApiCommon.getRequestId());
		adapterHeader.setMallId("PLGRIM");
		adapterHeader.setLangCd("KOR");
		adapterHeader.setDvcCd("PC");
		
		//예약영수증 취소 파라미터세팅
//		for (DeliveryInfErpDTO deliveryInfErpDTO : list) {
//			if("Y".equals(deliveryInfErpDTO.getIntrlckTgtYn())) {
//				LgsDlivySDO paramSdo = new LgsDlivySDO();
//				paramSdo.setFpiaOr(deliveryInfErpDTO.getFpiaOr());	//주문번호
//				paramSdo.setSeqNo(deliveryInfErpDTO.getSeqNo());	//수량순번
//				paramSdo.setWerks(deliveryInfErpDTO.getWerks());	//배송매장
//				if(DeliveryEnum.DLV_ONLINE_SHOP_DISCOVERY.toString().equals(deliveryInfErpDTO.getWerks())) {
//					paramSdo.setLgort("FC08");
//				} else {
//					paramSdo.setLgort("FC01");
//				}
//				paramSdo.setCallerId(InterfaceAdapterEnum.RFC_NM_ZD5SDC_FP_DELIVERY_CHANGE.toString());
//
//
//				LgsDlivySDO result = lgsDlivyAdapter.modifyDeliveryChange(paramSdo, adapterHeader);
//
//				//E:에러
//				if("E".equals(result.getResult())) {
//					log.info("[modifyPreSalesDlvShopChange] [주문번호 :: "+ deliveryInfErpDTO.getFpiaOr() +"] ERP 예약영수증 배송지 변경 중 오류가 발생하였습니다.");
//
//				//N:재고부족
//				} else if("N".equals(result.getResult())) {
//					if(DeliveryEnum.DLV_ONLINE_SHOP_DISCOVERY.toString().equals(deliveryInfErpDTO.getWerks())) {
//						paramSdo.setLgort("FC01");
//						LgsDlivySDO rslt = lgsDlivyAdapter.modifyDeliveryChange(paramSdo, adapterHeader);
//
//						if("E".equals(rslt.getResult()) || "N".equals(rslt.getResult())) {
//							log.info("[modifyPreSalesDlvShopChange] [주문번호 :: "+ deliveryInfErpDTO.getFpiaOr() +"] ERP 예약영수증 배송지 변경 중 오류가 발생하였습니다.");
//						} else {
//							//물류출고지시상품 이력 등록
//							LgsDlivyDrctGodHist lgsDlivyDrctGodHist = new LgsDlivyDrctGodHist();
//							lgsDlivyDrctGodHist.setRegtrId("CHANGE_PRESALES");
//							lgsDlivyDrctGodHist.setUdterId("CHANGE_PRESALES");
//							lgsDlivyDrctGodHist.setDlivyDrctGodNo(deliveryInfErpDTO.getDlivyDrctGodNo());
//							deliveryCommandRepository.insertLgsDlivyDrctGodHist(lgsDlivyDrctGodHist);
//						}
//
//					} else {
//						log.info("[modifyPreSalesDlvShopChange] [주문번호 :: "+ deliveryInfErpDTO.getFpiaOr() +"] ERP 예약영수증 배송지 변경 중 오류가 발생하였습니다.");
//					}
//				//S:정상
//				} else {
//					//물류출고지시상품 이력 등록
//					LgsDlivyDrctGodHist lgsDlivyDrctGodHist = new LgsDlivyDrctGodHist();
//					lgsDlivyDrctGodHist.setRegtrId("CHANGE_PRESALES");
//					lgsDlivyDrctGodHist.setUdterId("CHANGE_PRESALES");
//					lgsDlivyDrctGodHist.setDlivyDrctGodNo(deliveryInfErpDTO.getDlivyDrctGodNo());
//					deliveryCommandRepository.insertLgsDlivyDrctGodHist(lgsDlivyDrctGodHist);
//
//				}
//			//S/Y:정상
//			} else {
//				//물류출고지시상품 이력 등록
//				LgsDlivyDrctGodHist lgsDlivyDrctGodHist = new LgsDlivyDrctGodHist();
//				lgsDlivyDrctGodHist.setRegtrId("CHANGE_PRESALES");
//				lgsDlivyDrctGodHist.setUdterId("CHANGE_PRESALES");
//				lgsDlivyDrctGodHist.setDlivyDrctGodNo(deliveryInfErpDTO.getDlivyDrctGodNo());
//				deliveryCommandRepository.insertLgsDlivyDrctGodHist(lgsDlivyDrctGodHist);
//
//			}
//		}
	}
	
	
	/**
	 * TAG Serial Number_조회.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dto [설명]
	 * @return LgsDlivySDO [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 18
	 */
//	public LgsDlivySDO checkTagSerialTarget(DeliveryOrderGoodDTO dto) throws Exception {
//		String msg = "";
//
//		// Adapter Header 를 세팅한다.
//		AdapterHeader adapterHeader = new AdapterHeader();
//		adapterHeader.setRequestId(this.interfaceApiCommon.getRequestId());
//		adapterHeader.setMallId("DXM");
//		adapterHeader.setLangCd("KOR");
//		adapterHeader.setDvcCd("PC");
//
//		//파라미터 세팅
//		LgsDlivySDO parameter = new LgsDlivySDO();
//		parameter.setMatnr(dto.getErpStyleNoTag());
//
//		return lgsDlivyAdapter.getTagInfo(parameter, adapterHeader);
//	}
	
	
	/**
	 * 예약영수증 생성.
	 * 예약영수증 생성은 화면에서 실시간으로 처리 안하고 배치에서 처리
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param list [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 8
	 */
	public void modifyPreSalesCreation(List<DeliveryInfErpDTO> list) throws Exception {

	    modifyPreSalesCreation(list,false,BOSecurityUtil.getLoginId());
	    
	}
	
	
	/**
	 * [메서드 설명].
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param list [설명]
	 * @param flag [설명]
	 * @param id [설명]
	 * @throws Exception the exception
	 * @since 2015. 11. 9
	 */
	public void modifyPreSalesCreation(List<DeliveryInfErpDTO> list,boolean flag,String id) throws Exception {
	    
//		List<SalesSDO> parameters = new ArrayList<SalesSDO>();
//        SpecialDCSDO specialDC = new SpecialDCSDO();
//        InfOrdGodErpDstb infOrdGodErpDstb = new InfOrdGodErpDstb();
//        LgsRtrvlDrctGodHist lgsRtrvlDrctGodHist = new LgsRtrvlDrctGodHist();
//        String userId = id;    //세션ID
//
//        //Adapter Header 세팅
//        AdapterHeader adapterHeader = new AdapterHeader();
//        adapterHeader.setRequestId(this.interfaceApiCommon.getRequestId());
//        adapterHeader.setMallId("PLGRIM");
//        adapterHeader.setLangCd("KOR");
//        adapterHeader.setDvcCd("PC");
//
//        //예약영수증 생성 파라미터세팅
//        int idx=0;
//        for (DeliveryInfErpDTO deliveryInfErpDTO : list) {
//			/*
//			 * 'ERP 연동 대상여부'(deliveryInfErpDTO.getIntrlckTgtYn()) 값을 조회하지 않으므로
//			 * 아래 분기에서 제외
//			 */
//	        //if("Y".equals(deliveryInfErpDTO.getIntrlckTgtYn())) {
//			SalesSDO paramSdo = new SalesSDO();
//
//			paramSdo.setVbeln(deliveryInfErpDTO.getVbeln());
//			paramSdo.setFpiaOr(deliveryInfErpDTO.getFpiaOr());
//			paramSdo.setSeqNo(deliveryInfErpDTO.getSeqNo());
//			paramSdo.setWadatIst(deliveryInfErpDTO.getWadatIst());
//			paramSdo.setMatnr(deliveryInfErpDTO.getMatnr());
//			paramSdo.setMenge(deliveryInfErpDTO.getMenge());
//			paramSdo.setMeins(deliveryInfErpDTO.getMeins());
//			paramSdo.setEindt(deliveryInfErpDTO.getEindt());
//
//			paramSdo.setReswk(deliveryInfErpDTO.getReswk());
//			paramSdo.setReslo(deliveryInfErpDTO.getReslo());
//			paramSdo.setWerks(deliveryInfErpDTO.getWerks());
//			paramSdo.setLgort(deliveryInfErpDTO.getLgort());
//			paramSdo.setDmbtr(deliveryInfErpDTO.getDmbtr());
//			paramSdo.setVbetr(deliveryInfErpDTO.getVbetr());
//			paramSdo.setCbAmt(deliveryInfErpDTO.getCbAmt());
//            paramSdo.setNamt(deliveryInfErpDTO.getNamt());
//			paramSdo.setMallText(deliveryInfErpDTO.getMalltext());
//			paramSdo.setEmpNo(deliveryInfErpDTO.getEmpNo());
//			paramSdo.setSto(deliveryInfErpDTO.getSsto());
//			paramSdo.setDocNo(deliveryInfErpDTO.getDocno());
//			paramSdo.setRctNo(deliveryInfErpDTO.getRctno());
//
//			paramSdo.setZlsch(deliveryInfErpDTO.getZlsch());
//			paramSdo.setPwerks(deliveryInfErpDTO.getPwerks());
//			paramSdo.setPlgort(deliveryInfErpDTO.getPlgort());
//
//			SalesSDO result = salesAdapter.modifyPreSales(paramSdo, adapterHeader);
//
//			if ( StringService.equalsIgnoreCase( result.getResult(), "Y") ) {
//				//ERP IF 예약영수증 생성 상태 변경
//				deliveryInfErpDTO.setErpResveRcptfrNo(result.getErpResveRcptfrNo());
//
//				if ( flag ) {
//					deliveryCommandRepository.pkupModifyPreSalesYnInf(deliveryInfErpDTO);
//				}
//				else {
//					deliveryCommandRepository.modifyPreSalesYnInf(deliveryInfErpDTO);
//				}
//
//
//				if(idx == list.size()-1) {
//					/*
//					 * 출고지시 예약영수증 생성 상태 변경
//					 * 	- DLV_SHOP_DLIVY_LC_ID(저장위치(FC08 또는 FC01)) 정보는
//					 * 	  이후 '배정' 배치에서 재생성하므로 별도 수정 없음
//					 */
//					deliveryCommandRepository.modifyPreSalesYnLgs(deliveryInfErpDTO);
//
//					lgsRtrvlDrctGodHist.setDlivyDrctGodNo(deliveryInfErpDTO.getDlivyDrctGodNo());
//					lgsRtrvlDrctGodHist.setRegtrId(userId);
//					lgsRtrvlDrctGodHist.setUdterId(userId);
//					deliveryCommandRepository.insertLgsRtrvlDrctGodHist(lgsRtrvlDrctGodHist);
//				}
//			}
//
//			idx++;
//	        //}
//        }
	}

	
	
	
	
	
	
	
	
	
	
	/**
	 * ERP 예약영수증 배송지 변경..
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param list [설명]
	 * @param caller [설명]
	 * @throws Exception the exception
	 * @since 2015. 11. 9
	 */
	public void modifyPreSalesDlvShopChangeNew(List<DeliveryInfErpDTO> list, String caller) throws Exception {
		//Adapter Header 세팅
		AdapterHeader adapterHeader = new AdapterHeader();
		adapterHeader.setRequestId(this.interfaceApiCommon.getRequestId());
		adapterHeader.setMallId("PLGRIM");
		adapterHeader.setLangCd("KOR");
		adapterHeader.setDvcCd("PC");
		
		//예약영수증 취소 파라미터세팅
//		for (DeliveryInfErpDTO deliveryInfErpDTO : list) {
//			LgsDlivySDO paramSdo = new LgsDlivySDO();
//			paramSdo.setFpiaOr(deliveryInfErpDTO.getFpiaOr());	//주문번호
//			paramSdo.setSeqNo(deliveryInfErpDTO.getSeqNo());	//수량순번
//			paramSdo.setWerks(deliveryInfErpDTO.getWerks());	//배송매장
//			if(DeliveryEnum.DLV_ONLINE_SHOP_DISCOVERY.toString().equals(deliveryInfErpDTO.getWerks())) {
//				paramSdo.setLgort("FC08");
//			} else {
//				paramSdo.setLgort("FC01");
//			}
//			paramSdo.setCallerId(InterfaceAdapterEnum.RFC_NM_ZD5SDC_FP_DELIVERY_CHANGE.toString());
//
//
//			LgsDlivySDO result = lgsDlivyAdapter.modifyDeliveryChange(paramSdo, adapterHeader);
//			log.info("change result :: " + result);
//
//			//FAIL:인터페이스서버 오류
//			if("FAIL".equals(result.getResult())) {
//				log.info("[modifyPreSalesDlvShopChange] [주문번호 :: "+ deliveryInfErpDTO.getFpiaOr() +"] 인터페이스서버 오류로 ERP 예약영수증 배송지 변경 중 오류가 발생하였습니다.");
//				DeliveryStatusException statusException = new DeliveryStatusException(null);
//				statusException.setDirectMessage("[modifyPreSalesDlvShopChange] [주문번호_수량순번 :: "+ deliveryInfErpDTO.getFpiaOr() +"_"+deliveryInfErpDTO.getSeqNo()+"] ERP 예약영수증 배송지 변경 중 오류가 발생하였습니다.");
//				throw statusException;
//
//			//E:처리오류
//			} else if("E".equals(result.getResult())) {
//				log.info("[modifyPreSalesDlvShopChange] [주문번호 :: "+ deliveryInfErpDTO.getFpiaOr() +"] ERP 예약영수증 배송지 변경 중 오류가 발생하였습니다.");
//				DeliveryStatusException statusException = new DeliveryStatusException(null);
//				statusException.setDirectMessage("[modifyPreSalesDlvShopChange] [주문번호_수량순번 :: "+ deliveryInfErpDTO.getFpiaOr() +"_"+deliveryInfErpDTO.getSeqNo()+"] ERP 예약영수증 배송지 변경 중 오류가 발생하였습니다.");
//				throw statusException;
//
//			//N:재고부족
//			} else if("N".equals(result.getResult())) {
//				if(DeliveryEnum.DLV_ONLINE_SHOP_DISCOVERY.toString().equals(deliveryInfErpDTO.getWerks())) {
//					paramSdo.setLgort("FC01");
//					LgsDlivySDO rslt = lgsDlivyAdapter.modifyDeliveryChange(paramSdo, adapterHeader);
//
//					if("E".equals(rslt.getResult()) || "N".equals(rslt.getResult())) {
//						log.info("[modifyPreSalesDlvShopChange] [주문번호 :: "+ deliveryInfErpDTO.getFpiaOr() +"] ERP 예약영수증 배송지 변경 중 오류가 발생하였습니다.");
//						DeliveryStatusException statusException = new DeliveryStatusException(null);
//						statusException.setDirectMessage("[modifyPreSalesDlvShopChange] [주문번호_수량순번 :: "+ deliveryInfErpDTO.getFpiaOr() +"_"+deliveryInfErpDTO.getSeqNo()+"] ERP 예약영수증 배송지 변경 중 오류가 발생하였습니다.");
//						throw statusException;
//					} else {
//						//물류출고지시상품 이력 등록
//						LgsDlivyDrctGodHist lgsDlivyDrctGodHist = new LgsDlivyDrctGodHist();
//						lgsDlivyDrctGodHist.setRegtrId("CHANGE_PRESALES_"+caller);
//						lgsDlivyDrctGodHist.setUdterId("CHANGE_PRESALES_"+caller);
//						lgsDlivyDrctGodHist.setDlivyDrctGodNo(deliveryInfErpDTO.getDlivyDrctGodNo());
//						deliveryCommandRepository.insertLgsDlivyDrctGodHist(lgsDlivyDrctGodHist);
//					}
//
//				} else {
//					log.info("[modifyPreSalesDlvShopChange] [주문번호 :: "+ deliveryInfErpDTO.getFpiaOr() +"] ERP 예약영수증 배송지 변경 중 오류가 발생하였습니다.");
//					DeliveryStatusException statusException = new DeliveryStatusException(null);
//					statusException.setDirectMessage("[modifyPreSalesDlvShopChange] [주문번호_수량순번 :: "+ deliveryInfErpDTO.getFpiaOr() +"_"+deliveryInfErpDTO.getSeqNo()+"] ERP 예약영수증 배송지 변경 중 오류가 발생하였습니다.");
//					throw statusException;
//				}
//			//Y:정상
//			} else {
//				//물류출고지시상품 이력 등록
//				LgsDlivyDrctGodHist lgsDlivyDrctGodHist = new LgsDlivyDrctGodHist();
//				lgsDlivyDrctGodHist.setRegtrId("CHANGE_PRESALES_"+caller);
//				lgsDlivyDrctGodHist.setUdterId("CHANGE_PRESALES_"+caller);
//				lgsDlivyDrctGodHist.setDlivyDrctGodNo(deliveryInfErpDTO.getDlivyDrctGodNo());
//				deliveryCommandRepository.insertLgsDlivyDrctGodHist(lgsDlivyDrctGodHist);
//
//			}
//		}
	}


	/**
	 * ERP 예약영수증 배송지 변경..
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param list [설명]
	 * @param caller [설명]
	 * @param dlvLcId [설명]
	 * @throws Exception the exception
	 * @since 2015. 12. 29
	 */
	public void modifyPreSalesDlvShopChangeNew(List<DeliveryInfErpDTO> list, String caller, String dlvLcId) throws Exception {
		//Adapter Header 세팅
		AdapterHeader adapterHeader = new AdapterHeader();
		adapterHeader.setRequestId(this.interfaceApiCommon.getRequestId());
		adapterHeader.setMallId("PLGRIM");
		adapterHeader.setLangCd("KOR");
		adapterHeader.setDvcCd("PC");

		//예약영수증 취소 파라미터세팅
//		for ( DeliveryInfErpDTO deliveryInfErpDTO : list ) {
//			LgsDlivySDO paramSdo = new LgsDlivySDO();
//			paramSdo.setFpiaOr(deliveryInfErpDTO.getFpiaOr());	//주문번호
//			paramSdo.setSeqNo(deliveryInfErpDTO.getSeqNo());	//수량순번
//			paramSdo.setWerks(deliveryInfErpDTO.getWerks());	//배송매장
//			if( DeliveryEnum.CDC.toString().equals(deliveryInfErpDTO.getWerks()) && DeliveryEnum.FC08.toString().equals(dlvLcId) ) {
//				paramSdo.setLgort( DeliveryEnum.FC08.toString() );
//			} else {
//				paramSdo.setLgort( DeliveryEnum.FC01.toString() );
//			}
//			paramSdo.setCallerId(InterfaceAdapterEnum.RFC_NM_ZD5SDC_FP_DELIVERY_CHANGE.toString());
//
//
//			LgsDlivySDO result = lgsDlivyAdapter.modifyDeliveryChange(paramSdo, adapterHeader);
//			log.info("PreSales Change Result : {} :: {} :: {} :: {} :: {} :: {} :: {} "
//					, deliveryInfErpDTO.getFpiaOr(), deliveryInfErpDTO.getSeqNo(), deliveryInfErpDTO.getWerks(), paramSdo.getLgort()
//					, result.getResult(), result.getMessage(), caller);
//
//			//FAIL:인터페이스서버 오류
//			if( "FAIL".equals(result.getResult()) ) {
//				log.info("[modifyPreSalesDlvShopChange] [주문번호 :: "+ deliveryInfErpDTO.getFpiaOr() +"] 인터페이스서버 오류로 ERP 예약영수증 배송지 변경 중 오류가 발생하였습니다.");
//				DeliveryStatusException statusException = new DeliveryStatusException(null);
//				statusException.setDirectMessage("[modifyPreSalesDlvShopChange] [주문번호_수량순번 :: "+ deliveryInfErpDTO.getFpiaOr() +"_"+deliveryInfErpDTO.getSeqNo()+"] ERP 예약영수증 배송지 변경 중 오류가 발생하였습니다.");
//				throw statusException;
//
//			//E:에러
//			} else if( "E".equals(result.getResult()) ) {
//				log.info("[modifyPreSalesDlvShopChange] [주문번호 :: "+ deliveryInfErpDTO.getFpiaOr() +"] ERP 예약영수증 배송지 변경 중 오류가 발생하였습니다.");
//				DeliveryStatusException statusException = new DeliveryStatusException(null);
//				statusException.setDirectMessage("[modifyPreSalesDlvShopChange] [주문번호_수량순번 :: "+ deliveryInfErpDTO.getFpiaOr() +"_"+deliveryInfErpDTO.getSeqNo()+"] ERP 예약영수증 배송지 변경 중 오류가 발생하였습니다.");
//				throw statusException;
//
//			//N:재고부족
//			} else if( "N".equals(result.getResult()) ) {
//				if( DeliveryEnum.CDC.toString().equals(deliveryInfErpDTO.getWerks()) && DeliveryEnum.FC08.toString().equals(dlvLcId) ) {
//					paramSdo.setLgort( DeliveryEnum.FC01.toString() );
//					LgsDlivySDO rslt = lgsDlivyAdapter.modifyDeliveryChange(paramSdo, adapterHeader);
//					log.info("PreSales Change Result 2nd : {} :: {} :: {} :: {} :: {} :: {} :: {} "
//							, deliveryInfErpDTO.getFpiaOr(), deliveryInfErpDTO.getSeqNo(), deliveryInfErpDTO.getWerks(), paramSdo.getLgort()
//							, result.getResult(), result.getMessage(), caller);
//
//					if( "E".equals(rslt.getResult()) || "N".equals(rslt.getResult()) ) {
//						log.info("[modifyPreSalesDlvShopChange] [주문번호 :: "+ deliveryInfErpDTO.getFpiaOr() +"] ERP 예약영수증 배송지 변경 중 오류가 발생하였습니다.");
//						DeliveryStatusException statusException = new DeliveryStatusException(null);
//						statusException.setDirectMessage("[modifyPreSalesDlvShopChange] [주문번호_수량순번 :: "+ deliveryInfErpDTO.getFpiaOr() +"_"+deliveryInfErpDTO.getSeqNo()+"] ERP 예약영수증 배송지 변경 중 오류가 발생하였습니다.");
//						throw statusException;
//					} else {
//						//물류출고지시 정보 수정
//						LgsDlivyDrctGod lgsDdg = new LgsDlivyDrctGod();
//						lgsDdg.setDlivyDrctGodNo(deliveryInfErpDTO.getDlivyDrctGodNo());
//						lgsDdg.setDlvShopDlivyLcId(paramSdo.getLgort());
//						lgsDdg.setUdterId("DELIVERY_CHANGE_FC01");
//						deliveryCommandRepository.updateDlvShopDlivyLcId(lgsDdg);
//
//						//물류출고지시상품 이력 등록
//						LgsDlivyDrctGodHist lgsDlivyDrctGodHist = new LgsDlivyDrctGodHist();
//						lgsDlivyDrctGodHist.setRegtrId("CHANGE_PRESALES_"+caller);
//						lgsDlivyDrctGodHist.setUdterId("CHANGE_PRESALES_"+caller);
//						lgsDlivyDrctGodHist.setDlivyDrctGodNo(deliveryInfErpDTO.getDlivyDrctGodNo());
//						deliveryCommandRepository.insertLgsDlivyDrctGodHist(lgsDlivyDrctGodHist);
//					}
//
//				} else {
//					log.info("[modifyPreSalesDlvShopChange] [주문번호 :: "+ deliveryInfErpDTO.getFpiaOr() +"] ERP 예약영수증 배송지 변경 중 오류가 발생하였습니다.");
//					DeliveryStatusException statusException = new DeliveryStatusException(null);
//					statusException.setDirectMessage("[modifyPreSalesDlvShopChange] [주문번호_수량순번 :: "+ deliveryInfErpDTO.getFpiaOr() +"_"+deliveryInfErpDTO.getSeqNo()+"] ERP 예약영수증 배송지 변경 중 오류가 발생하였습니다.");
//					throw statusException;
//				}
//			//Y:정상
//			} else {
//				//물류출고지시상품 이력 등록
//				LgsDlivyDrctGodHist lgsDlivyDrctGodHist = new LgsDlivyDrctGodHist();
//				lgsDlivyDrctGodHist.setRegtrId("CHANGE_PRESALES_"+caller);
//				lgsDlivyDrctGodHist.setUdterId("CHANGE_PRESALES_"+caller);
//				lgsDlivyDrctGodHist.setDlivyDrctGodNo(deliveryInfErpDTO.getDlivyDrctGodNo());
//				deliveryCommandRepository.insertLgsDlivyDrctGodHist(lgsDlivyDrctGodHist);
//
//			}
//		}
	}
	
	
	/**
	 * CDC 출고의뢰 취소.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param list [설명]
	 * @param caller [설명]
	 * @return Delivery inf erp dto [설명]
	 * @throws Exception the exception
	 * @since 2015. 11. 9
	 */
	public DeliveryInfErpDTO modifyPlantOrderCancelNew(List<DeliveryInfErpDTO> list, String caller) throws Exception {
		DeliveryInfErpDTO resultDTO = new DeliveryInfErpDTO();
//		LgsDlivySDO lgsDlivySDO = new LgsDlivySDO();
//		List<LgsDlivySDO> parameters = new ArrayList<LgsDlivySDO>();
//		String param = "";
//
//		//check issue 파라미터 세팅
//		for (DeliveryInfErpDTO deliveryInfErpDTO : list) {
//			LgsDlivySDO paramSdo = new LgsDlivySDO();
//			paramSdo.setVbeln(deliveryInfErpDTO.getVbeln());
//			paramSdo.setFpiaOr(deliveryInfErpDTO.getFpiaOr());
//			paramSdo.setSeqNo(deliveryInfErpDTO.getSeqNo());
//			paramSdo.setWadatIst(deliveryInfErpDTO.getWadatIst());
//			paramSdo.setMatnr(deliveryInfErpDTO.getMatnr());
//			paramSdo.setMenge(deliveryInfErpDTO.getMenge());
//			paramSdo.setMeins(deliveryInfErpDTO.getMeins());
//			paramSdo.setCallerId(InterfaceAdapterEnum.RFC_NM_YRSD_FP_CHECK_ISSUE.toString() + "_modifyPlantOrderCancelNew");
//			parameters.add(paramSdo);
//
//			if("" == param) {
//				param += deliveryInfErpDTO.getFpiaOr()+":"+deliveryInfErpDTO.getSeqNo();
//			} else {
//				param += " / "+deliveryInfErpDTO.getFpiaOr()+":"+deliveryInfErpDTO.getSeqNo();
//			}
//		}
//		log.info("modifyPlantOrderCancelNew caller :: "+caller);
//		log.info("modifyPlantOrderCancelNew param :: "+param);
//		log.info("modifyPlantOrderCancelNew user :: "+BOSecurityUtil.getLoginId());
//
//		//check issue
//		//GI확정 - result :: S/O
//		//TO확정 - result :: E/Y
//		//TO생성 - result :: E/Y
//		//TO미생성 - result :: E/Y
//		LgsDlivySDO resultCheck = lgsDlivyAdapter.getCheckIssueList(parameters, getHeader());
//		resultDTO.setResult(resultCheck.getResult());
//		resultDTO.setRfcType("CHECK_ISSUE");
//
//		// 출고확정건이 없을 경우 해당건 CancelPlantOrder
//		if("Y".equals(resultCheck.getResult())) {
//			for (DeliveryInfErpDTO deliveryInfErpDTO : list) {
//				//Interface 파라미터 세팅
//				lgsDlivySDO.setVbeln(deliveryInfErpDTO.getVbeln());
//				lgsDlivySDO.setFpiaOr(deliveryInfErpDTO.getFpiaOr());
//				lgsDlivySDO.setSeqNo(deliveryInfErpDTO.getSeqNo());
//				lgsDlivySDO.setWadatIst(deliveryInfErpDTO.getWadatIst());
//				lgsDlivySDO.setMatnr(deliveryInfErpDTO.getMatnr());
//				lgsDlivySDO.setMenge(deliveryInfErpDTO.getMenge());
//				lgsDlivySDO.setMeins(deliveryInfErpDTO.getMeins());
//				lgsDlivySDO.setSto(deliveryInfErpDTO.getSto());
//				lgsDlivySDO.setCallerId( DeliveryInfErpService.class.getSimpleName() +"_modifyPlantOrderCancelNew");
//
//				//출고의뢰 취소
//				//TO확정 - result :: 4
//				//TO생성 - result :: 4
//				//출고의뢰취소 - result :: ""
//				LgsDlivySDO result = lgsDlivyAdapter.modifyCancelPlantOrder(lgsDlivySDO, getHeader());
//				resultDTO.setResult(result.getResult());
//				resultDTO.setRfcType("CANCEL_PLANT_ORDER");
//
//				if(StringService.isEmpty(result.getResult())) {
//					//인터페이스주문상품 출고지시 취소
//					deliveryCommandRepository.updateInfErpDlivyDrctCancel(deliveryInfErpDTO);
//
//					//물류출고지시상품 출고지시 취소
//					int scount = deliveryCommandRepository.updateLgsDlivyDrctCancel(deliveryInfErpDTO);
//					if(scount == 1) {
//						LgsDlivyDrctGodHist lgsDlivyDrctGodHist = new LgsDlivyDrctGodHist();
//						lgsDlivyDrctGodHist.setRegtrId("CANCEL_PLANT_ORDER_"+caller);
//						lgsDlivyDrctGodHist.setUdterId("CANCEL_PLANT_ORDER_"+caller);	//FO 조세형 차장 수정.
//						lgsDlivyDrctGodHist.setDlivyDrctGodNo(deliveryInfErpDTO.getDlivyDrctGodNo());
//						deliveryCommandRepository.insertLgsDlivyDrctGodHist(lgsDlivyDrctGodHist);
//					}
//
//				} else if("E".equals(result.getResult())) {
//					log.info("CANCEL_PLANT_ORDER 오류 ORD_NO :: "+deliveryInfErpDTO.getFpiaOr()+"  QTY_TURN :: "+deliveryInfErpDTO.getSeqNo() );
//					DeliveryInterfaceException ifException = new DeliveryInterfaceException(null);
//					ifException.setDirectMessage("ERP납품문서 삭제요청 중 오류가 발생하였습니다.");
//					throw ifException;
//
//				} else if("4".equals(result.getResult())) {
//					DeliveryInterfaceException ifException = new DeliveryInterfaceException(null);
//					ifException.setDirectMessage("TO생성 대상입니다..");
//					throw ifException;
//
//				} else {
//					log.info("CANCEL_PLANT_ORDER 오류 ORD_NO :: "+deliveryInfErpDTO.getFpiaOr()+"  QTY_TURN :: "+deliveryInfErpDTO.getSeqNo() );
//					DeliveryInterfaceException ifException = new DeliveryInterfaceException(null);
//					ifException.setDirectMessage("ERP납품문서 삭제요청 중 오류가 발생하였습니다.");
//					throw ifException;
//
//				}
//			}
//		} else {
//			DeliveryInterfaceException ifException = new DeliveryInterfaceException(null);
//			ifException.setDirectMessage("GI확정 주문입니다.");
//			throw ifException;
//		}
		return resultDTO;
	}
	
	
	/**
	 * ERP의 주문상태정보 확인.
	 *
	 * @param salesSDO the sales sdo
	 * @return the sales sdo
	 * @throws Exception the exception
	 */
//	public SalesSDO checkOrderStatus(SalesSDO salesSDO) throws Exception {
//		return salesAdapter.getOrderStatus(salesSDO, getHeader());
//	}
	
	
	/**
	 * 재배정시 발생하는 Exception에 대한 출고지시 정보 보정 처리.
	 *
	 * @param obj the obj
	 * @throws Exception the exception
	 */
	public void procReAssignException(DeliveryOrderGoodResult obj) throws Exception {
		LgsDlivyDrctGod lgsDlivyDrctGod = new LgsDlivyDrctGod();
		DeliverySearchDTO deliverySearchDTO = new DeliverySearchDTO();
//		SalesSDO salesSDO = new SalesSDO();
		String userId = BOSecurityUtil.getLoginId();    //세션ID
		
		deliverySearchDTO.setOrdNo(obj.getOrd().getOrdNo());
		deliverySearchDTO.setDlivyDrctGodNo(obj.getLgsDdg().getDlivyDrctGodNo());
		List<DeliveryInfErpDTO> rlist = this.selectErpResveRcptfrCancelInfoList(deliverySearchDTO);
		
//		for (DeliveryInfErpDTO dto : rlist) {
//			//주문상태 조회
////			salesSDO.setFpiaOr(dto.getFpiaOr());
////			salesSDO.setSeqNo(dto.getSeqNo());
////			SalesSDO result = checkOrderStatus(salesSDO);
//
//			// 정상호출
//        	if ( StringService.equalsIgnoreCase( result.getResult(), "S" ) ) {
//        		//STO번호 및 납품번호 없는 경우
//        		if ( StringService.isEmpty( result.getDocNoA() )
//        				|| StringService.isEmpty( result.getDocNoB() ) ) {
//
//        			//출고지시정보 수정
//        			lgsDlivyDrctGod.setDlivyDrctGodNo(obj.getLgsDdg().getDlivyDrctGodNo());
//        			lgsDlivyDrctGod.setDlivyDrctTgtYn("Y");
//        			lgsDlivyDrctGod.setDlivyDrctYn("N");
//        			lgsDlivyDrctGod.setDlvShopId(obj.getLgsDdg().getDlvShopId());
//        			lgsDlivyDrctGod.setUdterId(userId);
//        			deliveryCommandRepository.updateDlvDrctInfo(lgsDlivyDrctGod);
//
//
//        			//물류 출고지시 상품 이력 등록
//        			LgsDlivyDrctGodHist lgsDlivyDrctGodHist = new LgsDlivyDrctGodHist();
//        			lgsDlivyDrctGodHist.setRegtrId("PROC_ASSIGN_EXCEPTION");
//        			lgsDlivyDrctGodHist.setUdterId(userId);
//        			lgsDlivyDrctGodHist.setDlivyDrctGodNo(obj.getLgsDdg().getDlivyDrctGodNo());
//        			deliveryCommandRepository.insertLgsDlivyDrctGodHist(lgsDlivyDrctGodHist);
//
//
//        			//ERP 인터페이스 출고지시대상 정보 수정
//        			InfOrdGodErpDstb infOrdGodErpDstb = new InfOrdGodErpDstb();
//        			infOrdGodErpDstb.setOrdNo(obj.getOrd().getOrdNo());
//        			infOrdGodErpDstb.setOrdGodTurn(obj.getOrdGod().getOrdGodTurn());
//        			infOrdGodErpDstb.setDlivyDrctGodNo(obj.getLgsDdg().getDlivyDrctGodNo());
//        			infOrdGodErpDstb.setDlivyDrctTgtYn("Y");
//        			infOrdGodErpDstb.setDlivyDrctYn("N");
//        			deliveryCommandRepository.updateInfDeliveryDirectTarget(infOrdGodErpDstb);
//        		}
//        	}
//		}
	}

	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
	
	
	
	/**
	 * [메서드 설명].
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param list [설명]
	 * @return String [설명]
	 * @throws Exception the exception
	 * @since 2015. 11. 9
	 */
	public String checkIssueListForClm(List<DeliveryInfErpDTO> list) throws Exception {
//		LgsDlivySDO lgsDlivySDO = new LgsDlivySDO();
//		List<LgsDlivySDO> parameters = new ArrayList<LgsDlivySDO>();
//
//		//Adapter Header 세팅
//
//		//check issue 파라미터 세팅
//		for (DeliveryInfErpDTO deliveryInfErpDTO : list) {
//			if("Y".equals(deliveryInfErpDTO.getIntrlckTgtYn())) {
//				LgsDlivySDO paramSdo = new LgsDlivySDO();
//				paramSdo.setVbeln(deliveryInfErpDTO.getVbeln());
//				paramSdo.setFpiaOr(deliveryInfErpDTO.getFpiaOr());
//				paramSdo.setSeqNo(deliveryInfErpDTO.getSeqNo());
//				paramSdo.setWadatIst(deliveryInfErpDTO.getWadatIst());
//				paramSdo.setMatnr(deliveryInfErpDTO.getMatnr());
//				paramSdo.setMenge(deliveryInfErpDTO.getMenge());
//				paramSdo.setMeins(deliveryInfErpDTO.getMeins());
//				paramSdo.setCallerId(InterfaceAdapterEnum.RFC_NM_YRSD_FP_CHECK_ISSUE.toString() + "_checkIssueListForClm" );
//				parameters.add(paramSdo);
//			}
//		}
//
//		if(parameters != null && parameters.size() > 0) {
//			//check issue
//			ObjectMapper om = new ObjectMapper();
//			log.info("parameters :: "+om.writeValueAsString(parameters));
//			LgsDlivySDO resultCheck = lgsDlivyAdapter.getCheckIssueList(parameters, getHeader());
//
//			// 출고확정건이 없을 경우 해당건 CancelPlantOrder
//			return resultCheck.getResult();
//		} else {
//			return "";
//		}

		return "";
	}

	
	/**
	 * Gets the header.
	 *
	 * @return the header
	 */
	/*private AdapterHeader getHeader(){
		//Adapter Header 세팅
		AdapterHeader adapterHeader = new AdapterHeader();
		adapterHeader.setRequestId(this.interfaceApiCommon.getRequestId());
		adapterHeader.setMallId("DXM");
		adapterHeader.setLangCd("KOR");
		adapterHeader.setDvcCd("PC");
		return adapterHeader;
	}*/
}
