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
 * @since       2015. 10. 30       
 */
package com.plgrim.ncp.biz.delivery.repository;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.datasource1.god.GodItm;
import com.plgrim.ncp.base.entities.datasource1.inf.InfOrdGodErpDstb;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsDlivyDrctGod;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsDlivyDrctGodHist;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsDlv;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsDlvHist;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsDlvsp;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsDlvspHist;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsRtrvlDrctGod;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsRtrvlDrctGodHist;
import com.plgrim.ncp.base.entities.datasource1.ord.Ord;
import com.plgrim.ncp.biz.delivery.data.DeliveryInfErpDTO;
import com.plgrim.ncp.biz.delivery.data.DeliveryInvoiceDTO;
import com.plgrim.ncp.biz.delivery.data.DeliveryOrderGoodDTO;
import com.plgrim.ncp.biz.delivery.data.DeliverySearchDTO;
import com.plgrim.ncp.biz.delivery.result.DeliveryOrderGoodResult;
import com.plgrim.ncp.commons.data.order.KcpCommonReceiveDTO;
import com.plgrim.ncp.interfaces.delivery.data.DlivyDrctSDO;
import com.plgrim.ncp.interfaces.order.data.OrderOnlineShopRtListSDO;

import lombok.extern.slf4j.Slf4j;

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
@Slf4j
@Repository
public class DeliveryCommandRepository extends AbstractRepository {

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
	public List<DeliveryInfErpDTO> selectDlivyDrctInfoList(DeliverySearchDTO deliverySearchDTO)  throws Exception {
		return getSession1().selectList("com.plgrim.ncp.biz.delivery.command.selectDlivyDrctInfoList", deliverySearchDTO);
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
	public List<DeliveryInfErpDTO> selectErpResveRcptfrCancelInfoList(DeliverySearchDTO deliverySearchDTO)  throws Exception {
		return getSession1().selectList("com.plgrim.ncp.biz.delivery.command.selectErpResveRcptfrCancelInfoList", deliverySearchDTO);
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
	public List<DeliveryInfErpDTO> selectErpResveRcptfrCreateInfoList(DeliverySearchDTO deliverySearchDTO)  throws Exception {
		return getSession1().selectList("com.plgrim.ncp.biz.delivery.command.selectErpResveRcptfrCreateInfoList", deliverySearchDTO);
	}
	
	
	/**
	 * [맞교환] 회수완료 처리.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param lgsRdg [설명]
	 * @return Int [설명]
	 * @throws Exception the exception
	 * @since 2015. 4. 8
	 */
	public int updateLgsRdg4CompRet(LgsRtrvlDrctGod lgsRdg) throws Exception {
		return getSession1().update("com.plgrim.ncp.biz.delivery.command.updateLgsRdg4CompRet", lgsRdg);
	}
	
	
	/**
	 * 배송상태 조회.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param lgsDdg [설명]
	 * @return String [설명]
	 * @throws Exception the exception
	 * @since 2015. 4. 7
	 */
	public String getDeliveryStatus(LgsDlivyDrctGod lgsDdg) throws Exception {
		return (String)getSession1().selectOne("com.plgrim.ncp.biz.delivery.command.getDeliveryStatus",lgsDdg);
	}
	
	
	/**
	 * 운송장번호 조회.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param lgsDdg [설명]
	 * @return String [설명]
	 * @throws Exception the exception
	 * @since 2015. 7. 14
	 */
	public String getWaybilNo(LgsDlivyDrctGod lgsDdg) throws Exception {
		return (String)getSession1().selectOne("com.plgrim.ncp.biz.delivery.command.getWaybilNo",lgsDdg);
	}
	
	/**
	 * (맞교환) 결품접수 처리.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param lgsDdg [설명]
	 * @return Int [설명]
	 * @throws Exception the exception
	 * @since 2015. 4. 7
	 */
	public int updateShortage(LgsDlivyDrctGod lgsDdg) throws Exception {
		return getSession1().update("com.plgrim.ncp.biz.delivery.command.updateShortage", lgsDdg);
	}
	
	/**
	 * 결품 철회.
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param lgsDdg [설명]
	 * @return Int [설명]
	 * @throws Exception the exception
	 * @since 2015. 4. 7
	 */
	public int updateShortageCancel(LgsDlivyDrctGod lgsDdg) throws Exception {
		return getSession1().update("com.plgrim.ncp.biz.delivery.command.updateShortageCancel", lgsDdg);
	}


	/**
	 * 배정대상 출고정보 조회.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param lgsDdg [설명]
	 * @return List<DeliveryOrderGoodResult> [설명]
	 * @throws Exception the exception
	 * @since 2015. 4. 13
	 */
	public List<DeliveryOrderGoodResult> selectAssignTargetList(LgsDlivyDrctGod lgsDdg) throws Exception {
		return getSession1().selectList("com.plgrim.ncp.biz.delivery.command.selectAssignTargetList", lgsDdg);
	}
	
	
	/**
	 * 입점업체 배정대상 출고정보 조회.
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param lgsDdg [설명]
	 * @return List<DeliveryOrderGoodResult> [설명]
	 * @throws Exception the exception
	 * @since 2015. 4. 13
	 */
	public List<DeliveryOrderGoodResult> selectPartMallAssignTargetList(LgsDlivyDrctGod lgsDdg) throws Exception {
		return getSession1().selectList("com.plgrim.ncp.biz.delivery.command.selectPartMallAssignTargetList", lgsDdg);
	}


	/**
	 * 배정대상(합포장 상품 사은품) 출고정보 조회.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param deliveryOrderGoodDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 6. 15
	 */
	public List<DeliveryOrderGoodResult> selectAssignGiftList(DeliveryOrderGoodDTO deliveryOrderGoodDTO) throws Exception {
		return getSession1().selectList("com.plgrim.ncp.biz.delivery.command.selectAssignGiftList", deliveryOrderGoodDTO);
	}
	
	
	/**
	 * 맞교환 배송매장 조회.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param deliveryOrderGoodDTO [설명]
	 * @return String [설명]
	 * @throws Exception the exception
	 * @since 2015. 4. 13
	 * 	public String getExchangeDeliShopSn(LgsRtrvlDrctGod lgsRdg) throws Exception {
	 * 		log.info("#################################[DeliveryCommandRepository] getExchangeDeliShopSn :"+ lgsRdg);
	 * 		return (String)getSession1().selectOne("com.plgrim.ncp.biz.delivery.command.getExchangeDeliShopSn",lgsRdg);
	 * 	}
	public String getExchangeDeliShopSn(LgsRtrvlDrctGod lgsRdg) throws Exception {
		return (String)getSession1().selectOne("com.plgrim.ncp.biz.delivery.command.getExchangeDeliShopSn",lgsRdg);
	}
	 */
	
	
	/**
	 * 재고있는 배송매장 조회(일반).
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param deliveryOrderGoodDTO [설명]
	 * @return String [설명]
	 * @throws Exception the exception
	 * @since 2015. 4. 14
	 */
	public String getDeliShopIdForItemStock(DeliveryOrderGoodDTO deliveryOrderGoodDTO) throws Exception {
		return (String)getSession1().selectOne("com.plgrim.ncp.biz.delivery.command.getDeliShopIdForItemStock",deliveryOrderGoodDTO);
	}
/*	
	public LgsAutoAsgnExtend getDeliShopInfoForItemStock(DeliveryOrderGoodDTO deliveryOrderGoodDTO) throws Exception {
		return (LgsAutoAsgnExtend)getSession1().selectOne("com.plgrim.ncp.biz.delivery.command.getDeliShopInfoForItemStock",deliveryOrderGoodDTO);
	}
*/
	
	/**
	 * 재고있는 배송매장 조회(패키지).
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param deliveryOrderGoodDTO [설명]
	 * @return String [설명]
	 * @throws Exception the exception
	 * @since 2015. 4. 14
	 */
	public String getDeliShopIdForPackageStock(DeliveryOrderGoodDTO deliveryOrderGoodDTO) throws Exception {
		return (String)getSession1().selectOne("com.plgrim.ncp.biz.delivery.command.getDeliShopIdForPackageStock",deliveryOrderGoodDTO);
	}
/*	
	public LgsAutoAsgnExtend getDeliShopInfoForPackageStock(DeliveryOrderGoodDTO deliveryOrderGoodDTO) throws Exception {
		return (LgsAutoAsgnExtend)getSession1().selectOne("com.plgrim.ncp.biz.delivery.command.getDeliShopInfoForPackageStock",deliveryOrderGoodDTO);
	}
*/
	
	/**
	 * [일반] 한정재고 수량 조회.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param deliveryOrderGoodDTO [설명]
	 * @return God itm [설명]
	 * @throws Exception the exception
	 * @since 2015. 7. 11
	 */
	public GodItm selectLmttInvQty4ItemStock(DeliveryOrderGoodDTO deliveryOrderGoodDTO) throws Exception {
		return (GodItm)getSession1().selectOne("com.plgrim.ncp.biz.delivery.command.selectLmttInvQty4ItemStock",deliveryOrderGoodDTO);
	}
	
	
	/**
	 * [패키지] 한정재고 수량 조회.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param deliveryOrderGoodDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 7. 11
	 */
	public List<GodItm> selectLmttInvQty4ItemsStock(DeliveryOrderGoodDTO deliveryOrderGoodDTO) throws Exception {
		return getSession1().selectList("com.plgrim.ncp.biz.delivery.command.selectLmttInvQty4ItemsStock",deliveryOrderGoodDTO);
	}
	
	
	/**
	 * 배정정보 수정.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param lgsDdg [설명]
	 * @return Int [설명]
	 * @throws Exception the exception
	 * @since 2015. 4. 14
	 */
	public int updateAssignShopInfo(LgsDlivyDrctGod lgsDdg) throws Exception {
		return getSession1().update("com.plgrim.ncp.biz.delivery.command.updateAssignShopInfo", lgsDdg);
	}
	
	
	/**
	 * 물류 출고지시 상품 이력 등록.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param lgsDlivyDrctGodHist [설명]
	 * @return Int [설명]
	 * @throws Exception the exception
	 * @since 2015. 4. 14
	 */
	public int insertLgsDlivyDrctGodHist(LgsDlivyDrctGodHist lgsDlivyDrctGodHist) throws Exception {
		return getSession1().insert("com.plgrim.ncp.biz.delivery.command.insertLgsDlivyDrctGodHist", lgsDlivyDrctGodHist);
	}

	/**
	 * ERP 인터페이스 출고지시대상 정보 수정.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param infOrdGodErpDstb [설명]
	 * @return Int [설명]
	 * @throws Exception the exception
	 * @since 2015. 4. 14
	 */
	public int updateInfDeliveryDirectTarget(InfOrdGodErpDstb infOrdGodErpDstb) throws Exception {
		return getSession1().update("com.plgrim.ncp.biz.delivery.command.updateInfDeliveryDirectTarget", infOrdGodErpDstb);
	}
	
	
	/**
	 * ERP 인터페이스 시리얼정보 수정.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param infOrdGodErpDstb [설명]
	 * @return Int [설명]
	 * @throws Exception the exception
	 * @since 2016. 3. 17
	 */
	public int updateErpGodSnInfo(InfOrdGodErpDstb infOrdGodErpDstb) throws Exception {
		return getSession1().update("com.plgrim.ncp.biz.delivery.command.updateErpGodSnInfo", infOrdGodErpDstb);
	}
	
	
	/**
	 * 출고검수 물류출고상품 단/복품 유형 초기화.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param lgsDlivyDrctGod [설명]
	 * @return Int [설명]
	 * @throws Exception the exception
	 * @since 2015. 4. 14
	 */
	public int updateLgsItemYnInit(LgsDlivyDrctGod lgsDlivyDrctGod) throws Exception {
		return getSession1().update("com.plgrim.ncp.biz.delivery.command.updateLgsItemYnInit", lgsDlivyDrctGod);
	}
	
	
	/**
	 * 물류출고상품 단/복품 유형 수정.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param lgsDlivyDrctGod [설명]
	 * @return Int [설명]
	 * @throws Exception the exception
	 * @since 2015. 4. 14
	 */
	public int updateLgsItmYn(LgsDlivyDrctGod lgsDlivyDrctGod) throws Exception {
		return getSession1().update("com.plgrim.ncp.biz.delivery.command.updateLgsItmYn", lgsDlivyDrctGod);
	}
	
	
	/**
	 * 입점사 주문상품 SMS발송 정보 조회.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param ordNo [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 4. 16
	 */
	public List<DeliveryOrderGoodResult> selectOrdGodListForSms(String ordNo) throws Exception {
		return getSession1().selectList("com.plgrim.ncp.biz.delivery.command.selectOrdGodListForSms", ordNo);
	}
	
	
	/**
	 * 재배정대상정보 조회.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param deliverySearchDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 4. 27
	public List<DeliveryOrderGoodResult> selectReAssignDeliveryInfo(DeliverySearchDTO deliverySearchDTO) throws Exception {
		return getSession1().selectList("com.plgrim.ncp.biz.delivery.command.selectReAssignDeliveryInfo", deliverySearchDTO);
	}
	 */
	
	
	/**
	 * 박스분리 대상 정보 조회
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param deliverySearchDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 4. 27
	 */
	public List<DeliveryOrderGoodResult> selectDlvDivideTargetList(DeliverySearchDTO deliverySearchDTO) throws Exception {
		return getSession1().selectList("com.plgrim.ncp.biz.delivery.command.selectDlvDivideTargetList", deliverySearchDTO);
	}
	
	
	/**
	 * 패키지 구성품 정보 조회 .
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param deliverySearchDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 4. 27
	 */
	public List<DeliveryOrderGoodResult> selectDeliveryInfoPckage(DeliverySearchDTO deliverySearchDTO) throws Exception {
		return getSession1().selectList("com.plgrim.ncp.biz.delivery.command.selectDeliveryInfoPckage", deliverySearchDTO);
	}
	
	
	/**
	 * 재배정대상정보 조회
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param deliverySearchDTO [설명]
	 * @return Delivery order good result [설명]
	 * @throws Exception the exception
	 * @since 2015. 10. 30
	 */
	public DeliveryOrderGoodResult selectReAssignDeliveryInfo(DeliverySearchDTO deliverySearchDTO) throws Exception {
		return (DeliveryOrderGoodResult)getSession1().selectOne("com.plgrim.ncp.biz.delivery.command.selectReAssignDeliveryInfo", deliverySearchDTO);
	}
	
	
	/**
	 * 재배정대상정보 조회(패키지) .
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param deliverySearchDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 4. 27
	 */
	public List<DeliveryOrderGoodResult> selectReAssignDeliveryInfoPckage(DeliverySearchDTO deliverySearchDTO) throws Exception {
		return getSession1().selectList("com.plgrim.ncp.biz.delivery.command.selectReAssignDeliveryInfoPckage", deliverySearchDTO);
	}
	
	
	/**
	 * 패키지 구성 상품 정보 조회.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param deliverySearchDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 4. 27
	 */
	public List<DeliveryOrderGoodResult> selectPckageCompositInfoList(DeliverySearchDTO deliverySearchDTO) throws Exception {
		return getSession1().selectList("com.plgrim.ncp.biz.delivery.command.selectPckageCompositInfoList", deliverySearchDTO);
	}
	
	
	/**
	 * 패키지 구성 상품 정보 조회.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param deliveryInfErpDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 4. 27
	 * 	public List<DeliveryOrderGoodDTO> selectPckageLgsddgList(DeliveryOrderGoodDTO deliveryOrderGoodDTO) throws Exception {
	 * 		return getSession1().selectList("com.plgrim.ncp.biz.delivery.command.selectPckageLgsddgList", deliveryOrderGoodDTO);
	 * 	}
	 */
	
	
	/**
	 * 재배정정보 수정.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param lgsDdg [설명]
	 * @return Int [설명]
	 * @throws Exception the exception
	 * @since 2015. 4. 27
	public int updateReAssignShopInfo(LgsDlivyDrctGod lgsDdg) throws Exception {
		return getSession1().update("com.plgrim.ncp.biz.delivery.command.updateReAssignShopInfo", lgsDdg);
	}
	 */
	
	
	/**
	 * 인터페이스주문상품 출고지시 취소.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param deliveryInfErpDTO [설명]
	 * @return Int [설명]
	 * @throws Exception the exception
	 * @since 2015. 4. 27
	 */
	public int updateInfErpDlivyDrctCancel(DeliveryInfErpDTO deliveryInfErpDTO) throws Exception {
		return getSession1().update("com.plgrim.ncp.biz.delivery.command.updateInfErpDlivyDrctCancel", deliveryInfErpDTO);
	}
	
	
	/**
	 * 물류출고지시상품 출고지시 취소.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param deliveryInfErpDTO [설명]
	 * @return Int [설명]
	 * @throws Exception the exception
	 * @since 2015. 4. 27
	 */
	public int updateLgsDlivyDrctCancel(DeliveryInfErpDTO deliveryInfErpDTO) throws Exception {
		return getSession1().update("com.plgrim.ncp.biz.delivery.command.updateLgsDlivyDrctCancel", deliveryInfErpDTO);
	}
	
	
	/**
	 * 인터페이스주문상품 예약영수증 취소.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param deliveryInfErpDTO [설명]
	 * @return Int [설명]
	 * @throws Exception the exception
	 * @since 2015. 4. 27
	 */
	public int updateInfErpResveRcptfrCancel(DeliveryInfErpDTO deliveryInfErpDTO) throws Exception {
		return getSession1().update("com.plgrim.ncp.biz.delivery.command.updateInfErpResveRcptfrCancel", deliveryInfErpDTO);
	}
	
	
	/**
	 * 물류출고지시상품 예약영수증 취소.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param deliveryInfErpDTO [설명]
	 * @return Int [설명]
	 * @throws Exception the exception
	 * @since 2015. 4. 27
	 */
	public int updateLgsResveRcptfrCancel(DeliveryInfErpDTO deliveryInfErpDTO) throws Exception {
		return getSession1().update("com.plgrim.ncp.biz.delivery.command.updateLgsResveRcptfrCancel", deliveryInfErpDTO);
	}
	
	
	/**
	 * 물류 회수지시 상품 이력 등록.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param lgsRtrvlDrctGodHist [설명]
	 * @return Int [설명]
	 * @throws Exception the exception
	 * @since 2015. 4. 14
	 */
	public int insertLgsRtrvlDrctGodHist(LgsRtrvlDrctGodHist lgsRtrvlDrctGodHist) throws Exception {
		return getSession1().insert("com.plgrim.ncp.biz.delivery.command.insertLgsRtrvlDrctGodHist", lgsRtrvlDrctGodHist);
	}
	
	
	/**
	 * 불량상품 정보 수정
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param deliveryOrderGoodDTO [설명]
	 * @return Int [설명]
	 * @throws Exception the exception
	 * @since 2015. 4. 27
	 */
	public int updateFaultyGoodInfo(DeliveryOrderGoodDTO deliveryOrderGoodDTO) throws Exception {
		return getSession1().update("com.plgrim.ncp.biz.delivery.command.updateFaultyGoodInfo", deliveryOrderGoodDTO);
	}
	
	
	/**
	 * 불량상품 배송회원 정보 수정
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param deliveryOrderGoodDTO [설명]
	 * @return Int [설명]
	 * @throws Exception the exception
	 * @since 2015. 4. 27
	 */
	public int updateLgsDsp4FaultyGood(DeliveryOrderGoodDTO deliveryOrderGoodDTO) throws Exception {
		return getSession1().update("com.plgrim.ncp.biz.delivery.command.updateLgsDsp4FaultyGood", deliveryOrderGoodDTO);
	}
	
	
	/**
	 * [회수리스트] 회수정보 수정
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param lgsRtrvlDrctGod [설명]
	 * @return Int [설명]
	 * @throws Exception the exception
	 * @since 2015. 4. 27
	 */
	public int updateRtrvlInfo(LgsRtrvlDrctGod lgsRtrvlDrctGod) throws Exception {
		return getSession1().update("com.plgrim.ncp.biz.delivery.command.updateRtrvlInfo", lgsRtrvlDrctGod);
	}
	
	
	/**
	 * [회수리스트] 회수완료정보 수정
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param lgsRtrvlDrctGod [설명]
	 * @return Int [설명]
	 * @throws Exception the exception
	 * @since 2015. 4. 27
	 */
	public int updateRtrvlComptInfo(LgsRtrvlDrctGod lgsRtrvlDrctGod) throws Exception {
		return getSession1().update("com.plgrim.ncp.biz.delivery.command.updateRtrvlComptInfo", lgsRtrvlDrctGod);
	}
	
	
	/**
	 * 고객정보 수정
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param ord [설명]
	 * @return Int [설명]
	 * @throws Exception the exception
	 * @since 2015. 4. 27
	 */
	public int updateCstmrInfo(Ord ord) throws Exception {
		return getSession1().update("com.plgrim.ncp.biz.delivery.command.updateCstmrInfo", ord);
	}
	
	
	/**
	 * [회수리스트] 송장번호 수정
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param lgsDlv [설명]
	 * @return Int [설명]
	 * @throws Exception the exception
	 * @since 2015. 4. 27
	 */
	public int updateRtrvlWaybilInfo(LgsDlv lgsDlv) throws Exception {
		return getSession1().update("com.plgrim.ncp.biz.delivery.command.updateRtrvlWaybilInfo", lgsDlv);
	}
	
	
	/**
	 * [재배정] 운송장정보 초기화
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param lgsDlv [설명]
	 * @return Int [설명]
	 * @throws Exception the exception
	 * @since 2015. 8. 27
	 */
	public int updateInitWayBillInfo(LgsDlv lgsDlv) throws Exception {
		return getSession1().update("com.plgrim.ncp.biz.delivery.command.updateInitWayBillInfo", lgsDlv);
	}
	
	
	/**
	 * 인터페이스주문상품 수선정보 초기화.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param deliveryOrderGoodDTO [설명]
	 * @return Int [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 4
	 */
	public int updateInfErpRepairInfo(DeliveryOrderGoodDTO deliveryOrderGoodDTO) throws Exception {
		return getSession1().update("com.plgrim.ncp.biz.delivery.command.updateInfErpRepairInfo", deliveryOrderGoodDTO);
	}
	
	
	/**
	 * 물류배송 이력 등록
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param lgsDlvHist [설명]
	 * @return Int [설명]
	 * @throws Exception the exception
	 * @since 2015. 4. 27
	 */
	public int insertLgsDlvHist(LgsDlvHist lgsDlvHist) throws Exception {
		return getSession1().update("com.plgrim.ncp.biz.delivery.command.insertLgsDlvHist", lgsDlvHist);
	}
	
	
	/**
	 * 물류배송지 이력 등록
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param lgsDlvspHist [설명]
	 * @return Int [설명]
	 * @throws Exception the exception
	 * @since 2015. 4. 27
	 */
	public int insertLgsDlvspHist(LgsDlvspHist lgsDlvspHist) throws Exception {
		return getSession1().update("com.plgrim.ncp.biz.delivery.command.insertLgsDlvspHist", lgsDlvspHist);
	}
	
	
	/**
	 * 배송상태 체크.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param deliveryOrderGoodDTO [설명]
	 * @return Int [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 8
	 */
	public int checkAbleAssignStat(DeliveryOrderGoodDTO deliveryOrderGoodDTO) throws Exception {
		return getSession1().selectOne("com.plgrim.ncp.biz.delivery.command.checkAbleAssignStat", deliveryOrderGoodDTO);
	}
	
	
	/**
	 * 운송장 수정.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param deliveryInvoiceDTO [설명]
	 * @return Int [설명]
	 * @throws Exception the exception
	 * @since 2015. 4. 27
	 */
	public int updateInvoice(DeliveryInvoiceDTO deliveryInvoiceDTO) throws Exception {
		return getSession1().update("com.plgrim.ncp.biz.delivery.command.updateInvoice", deliveryInvoiceDTO);
	}
	
	
	/**
	 * 운송장 이력등록.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param deliveryInvoiceDTO [설명]
	 * @return Int [설명]
	 * @throws Exception the exception
	 * @since 2015. 4. 27
	 */
	public int insertInvoiceHistory(DeliveryInvoiceDTO deliveryInvoiceDTO) throws Exception {
		return getSession1().update("com.plgrim.ncp.biz.delivery.command.insertInvoiceHistory", deliveryInvoiceDTO);
	}
	
	
	/**
	 * TAG Serial 조회.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param infOrdGodErpDstb [설명]
	 * @return String [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 17
	 */
	public String selectErpGodSn(InfOrdGodErpDstb infOrdGodErpDstb) throws Exception {
		return (String)getSession1().selectOne("com.plgrim.ncp.biz.delivery.command.selectErpGodSn",infOrdGodErpDstb);
	}
	
	
	/**
	 * 검수정보 수정.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param infOrdGodErpDstb [설명]
	 * @return Int [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 17
	 */
	public int updateInspectionInfo(InfOrdGodErpDstb infOrdGodErpDstb) throws Exception {
		return getSession1().update("com.plgrim.ncp.biz.delivery.command.updateInspectionInfo", infOrdGodErpDstb);
	}
	
	
	/**
	 * 검수 취소.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param deliveryOrderGoodDTO [설명]
	 * @return Int [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 17
	 */
	public int updateInspectionReset(DeliveryOrderGoodDTO deliveryOrderGoodDTO) throws Exception {
		return getSession1().update("com.plgrim.ncp.biz.delivery.command.updateInspectionReset", deliveryOrderGoodDTO);
	}
	
	
	/**
	 * 유효 출고지시 건 수 (검수시)
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dto [설명]
	 * @return Int [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 20
	 */
	public int checkAbleDlvDrctCnt(DeliveryOrderGoodDTO dto) throws Exception {
		return getSession1().selectOne("com.plgrim.ncp.biz.delivery.command.checkAbleDlvDrctCnt", dto);
	}
	
	
	/**
	 * 물류배송단위 출고대상건 카운트 조회.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dto [설명]
	 * @return Int [설명]
	 * @throws Exception the exception
	 * @since 2015. 8. 2
	 */
	public int selectAbleDlivyeryCnt(DeliveryOrderGoodDTO dto) throws Exception {
		return getSession1().selectOne("com.plgrim.ncp.biz.delivery.command.selectAbleDlivyeryCnt", dto);
	}
	
	/**
	 * 물류배송단위 출고대상건 카운트 조회.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dto [설명]
	 * @return Int [설명]
	 * @throws Exception the exception
	 * @since 2015. 8. 2
	 */
	public int selectAssignAbleDlivyeryCnt(DeliveryOrderGoodDTO dto) throws Exception {
		return getSession1().selectOne("com.plgrim.ncp.biz.delivery.command.selectAssignAbleDlivyeryCnt", dto);
	}
	
	
	/**
	 * 출고지시 수량 조회.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dlivyDrctGodNo [설명]
	 * @return Int [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 20
	 */
	public int selectDlivyDrctQty(String dlivyDrctGodNo) throws Exception {
		return getSession1().selectOne("com.plgrim.ncp.biz.delivery.command.selectDlivyDrctQty", dlivyDrctGodNo);
	}

	
	/**
	 * 원 물류출고지시상품 데이터 수정
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param deliveryOrderGoodDTO [설명]
	 * @return Int [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 17
	 */
	public int updateLgsDdgInfo4DivWayBil(DeliveryOrderGoodDTO deliveryOrderGoodDTO) throws Exception {
		return getSession1().update("com.plgrim.ncp.biz.delivery.command.updateLgsDdgInfo4DivWayBil", deliveryOrderGoodDTO);
	}
	
	
	/**
	 * 인터페이스주문상품ERP분배 데이터 수정
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param deliveryOrderGoodDTO [설명]
	 * @return Int [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 17
	 */
	public int updateInfOgsdInfo4DivWayBil(DeliveryOrderGoodDTO deliveryOrderGoodDTO) throws Exception {
		return getSession1().update("com.plgrim.ncp.biz.delivery.command.updateInfOgsdInfo4DivWayBil", deliveryOrderGoodDTO);
	}
	
	
	/**
	 * 물류배송 등록
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param deliveryOrderGoodDTO [설명]
	 * @return Int [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 17
	 */
	public int insertLgsDlv(DeliveryOrderGoodDTO deliveryOrderGoodDTO) throws Exception {
		return getSession1().insert("com.plgrim.ncp.biz.delivery.command.insertLgsDlv", deliveryOrderGoodDTO);
	}
	
	
	/**
	 * 물류출고지시상품 등록
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param deliveryOrderGoodDTO [설명]
	 * @return Int [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 17
	 */
	public int insertLgsDdg(DeliveryOrderGoodDTO deliveryOrderGoodDTO) throws Exception {
		return getSession1().insert("com.plgrim.ncp.biz.delivery.command.insertLgsDdg", deliveryOrderGoodDTO);
	}
	
	/**
	 * 송장생성 배송 목록 조회
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param deliverySearchDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 3. 30
	public List<Map<String, Object>>  getDrctGoodsInvoiceCreateList(DeliverySearchDTO deliverySearchDTO) throws Exception {
		return getSession1().selectList("com.plgrim.ncp.biz.delivery.select.getDrctGoodsInvoiceCreateList", deliverySearchDTO);
	}	
	 */
	
	/**
	 * 송장번호 전체생성 대상 조회.
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
	public List<DeliveryOrderGoodResult> selectSearchWaybillNoList(DeliverySearchDTO deliverySearchDTO) throws Exception {
		return getSession1().selectList("com.plgrim.ncp.biz.delivery.select.selectSearchWaybillNoList", deliverySearchDTO);
	}
	
	
	/**
	 * 배송상태 수정.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param deliveryInvoiceDTO [설명]
	 * @return Int [설명]
	 * @throws Exception the exception
	 * @since 2015. 6. 22
	 */
	public int updateCompleteDelivery(DeliveryInvoiceDTO deliveryInvoiceDTO) throws Exception {
		return getSession1().update("com.plgrim.ncp.biz.delivery.command.updateDeliveryStat", deliveryInvoiceDTO);
	}
	
	
	/**
	 * 배송상태 수정.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param deliveryOrderGoodDTO [설명]
	 * @return Int [설명]
	 * @throws Exception the exception
	 * @since 2015. 6. 22
	 */
	public int updateDeliveryStatus(DeliveryOrderGoodDTO deliveryOrderGoodDTO) throws Exception {
		return getSession1().update("com.plgrim.ncp.biz.delivery.command.updateDeliveryStatus", deliveryOrderGoodDTO);
	}
	
	
	/**
	 * 출고지시상품 상세 정보 조회.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dto [설명]
	 * @return Lgs dlivy drct god [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 22
	 */
	public LgsDlivyDrctGod selectLgsDdgInfo(DeliveryOrderGoodDTO dto) throws Exception {
		return getSession1().selectOne("com.plgrim.ncp.biz.delivery.command.selectLgsDdgInfo", dto);
	}
	
	
	/**
	 * 맞교환 출고건 회수정보 수정.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param lgsDlivyDrctGod [설명]
	 * @return Int [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 30
	 */
	public int updateRtrvlInfo4DrctExch(LgsDlivyDrctGod lgsDlivyDrctGod) throws Exception {
		return getSession1().insert("com.plgrim.ncp.biz.delivery.command.updateRtrvlInfo4DrctExch", lgsDlivyDrctGod);
	}
	
	
	/**
	 * 맞교환 출고건 유효성 체크.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param deliveryInvoiceDTO [설명]
	 * @return DeliveryOrderGoodDTO [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 23
	 * 	public DeliveryOrderGoodDTO selectDrctExchInfo(LgsDlivyDrctGod lgsDlivyDrctGod) throws Exception {
	 * 		return getSession1().selectOne("com.plgrim.ncp.biz.delivery.command.selectDrctExchInfo", lgsDlivyDrctGod);
	 * 	}
	 */
	
	
	/**
	 * 물류회수지시삳품 등록
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param deliveryOrderGoodDTO [설명]
	 * @return Int [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 17
	public int insertLgsRdg(DeliveryOrderGoodDTO deliveryOrderGoodDTO) throws Exception {
		return getSession1().insert("com.plgrim.ncp.biz.delivery.command.insertLgsRdg", deliveryOrderGoodDTO);
	}
	 */
	
	
	/**
	 * 송장생성 배송 목록 count
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param deliverySearchDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 3. 30
	public int  getDrctGoodsInvoiceCreateListCount(DeliverySearchDTO deliverySearchDTO) throws Exception {
		return getSession1().selectOne("com.plgrim.ncp.biz.delivery.select.getDrctGoodsInvoiceCreateListCount", deliverySearchDTO);
	}	
	 */
	
	
	/**
	 * 매장배송메모수정.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param deliveryInvoiceDTO [설명]
	 * @return Int [설명]
	 * @throws Exception the exception
	 * @since 2015. 4. 27
	 */
	public int updateDeliveryShopMemo(DeliveryInvoiceDTO deliveryInvoiceDTO) throws Exception {
		return getSession1().update("com.plgrim.ncp.biz.delivery.command.updateDeliveryShopMemo", deliveryInvoiceDTO);
	}
	
	
	/**
	 * 회수관리 정산구분 배송비 저장
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param deliveryInvoiceDTO [설명]
	 * @return Int [설명]
	 * @throws Exception the exception
	 * @since 2015. 4. 27
	 */
	public int updateRtrvlSimple(DeliveryInvoiceDTO deliveryInvoiceDTO) throws Exception {
		return getSession1().update("com.plgrim.ncp.biz.delivery.command.updateRtrvlSimple", deliveryInvoiceDTO);
	}
	
	
	/**
	 * 회수상태 수정.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param deliveryOrderGoodDTO [설명]
	 * @return Int [설명]
	 * @throws Exception the exception
	 * @since 2015. 4. 27
	 */
	public int updateRetrivalStatus(DeliveryOrderGoodDTO deliveryOrderGoodDTO) throws Exception {
		return getSession1().update("com.plgrim.ncp.biz.delivery.command.updateRetrivalStatus", deliveryOrderGoodDTO);
	}
	
	
	/**
	 * 회수상태수정.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param deliveryInvoiceDTO [설명]
	 * @return Int [설명]
	 * @throws Exception the exception
	 * @since 2015. 4. 27
	 */
	public int updateRtrvlStat(DeliveryInvoiceDTO deliveryInvoiceDTO) throws Exception {
		return getSession1().update("com.plgrim.ncp.biz.delivery.command.updateRtrvlStat", deliveryInvoiceDTO);
	}
	
	
	/**
	 * 물류 출고지시 상품 이력 등록(패키지단위).
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param lgsDlivyDrctGodHist [설명]
	 * @return Int [설명]
	 * @throws Exception the exception
	 * @since 2015. 4. 14
	 */
	public int insertLgsDlivyDrctGodHistPackList(LgsDlivyDrctGodHist lgsDlivyDrctGodHist) throws Exception {
		return getSession1().insert("com.plgrim.ncp.biz.delivery.command.insertLgsDlivyDrctGodHistPackList", lgsDlivyDrctGodHist);
	}
	
	
	/**
	 * 배송상태수정(패키지단위).
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param deliveryInvoiceDTO [설명]
	 * @return Int [설명]
	 * @throws Exception the exception
	 * @since 2015. 4. 27
	 */
	public int updateCompleteDeliveryPackList(DeliveryInvoiceDTO deliveryInvoiceDTO) throws Exception {
		return getSession1().update("com.plgrim.ncp.biz.delivery.command.updateDeliveryStatPackList", deliveryInvoiceDTO);
	}
  
	
	/**
	 * 입고 검수정보 수정.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param infOrdGodErpDstb [설명]
	 * @return Int [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 17
	 */
	public int updateRtrvlInspectionInfo(InfOrdGodErpDstb infOrdGodErpDstb) throws Exception {
		return getSession1().update("com.plgrim.ncp.biz.delivery.command.updateRtrvlInspectionInfo", infOrdGodErpDstb);
	}

	/**
	 * [메서드 설명].
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param deliveryInfErpDTO [설명]
	 * @return Int [설명]
	 * @throws Exception the exception
	 * @since 2015. 10. 30
	 */
	public int updateInfErpResveRcptfrCancelForClm(DeliveryInfErpDTO deliveryInfErpDTO) throws Exception {
		return getSession1().update("com.plgrim.ncp.biz.delivery.command.updateInfErpResveRcptfrCancelForClm", deliveryInfErpDTO);
	}

	
	/**
	 *  ERP IF 예약영수증 생성 정보 수정
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param deliveryInfErpDTO [설명]
	 * @return Int [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 17
	 */
	public int modifyPreSalesYnInf(DeliveryInfErpDTO deliveryInfErpDTO) throws Exception {
		return getSession1().update("com.plgrim.ncp.biz.delivery.command.modifyPreSalesYnInf", deliveryInfErpDTO);
	}
	
   /**
    * [메서드 설명].
    * 
    * <p/>
    * 
    * [사용 방법 설명].
    *
    * @param deliveryInfErpDTO [설명]
    * @return Int [설명]
    * @throws Exception the exception
    * @since 2015. 10. 30
    */
   public int pkupModifyPreSalesYnInf(DeliveryInfErpDTO deliveryInfErpDTO) throws Exception {
        return getSession1().update("com.plgrim.ncp.biz.delivery.command.pkupModifyPreSalesYnInf", deliveryInfErpDTO);
    }
	
	/**
	 * 출고지시 예약영수증 생성정보 수정
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param deliveryInfErpDTO [설명]
	 * @return Int [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 17
	 */
	public int modifyPreSalesYnLgs(DeliveryInfErpDTO deliveryInfErpDTO) throws Exception {
		return getSession1().update("com.plgrim.ncp.biz.delivery.command.modifyPreSalesYnLgs", deliveryInfErpDTO);
	}


	/**
	 * 클레임완료상태수정.
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param deliveryInvoiceDTO [설명]
	 * @return Int [설명]
	 * @throws Exception the exception
	 * @since 2015. 11. 30
	 */
	public int updateClmStatCd(DeliveryInvoiceDTO deliveryInvoiceDTO) throws Exception {
		return getSession1().update("com.plgrim.ncp.biz.delivery.command.updateClmStatCd", deliveryInvoiceDTO);
	}


	/**
	 * 교환시 접수된 데이터 중 회수완료 대상의 교환입고대기인 상품을 배정대기 로 수정
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param lgsDlivyDrctGodHist [설명]
	 * @return Int [설명]
	 * @throws Exception the exception
	 * @since 2015. 11. 30
	 */
	public int updateLgsDlivyDrctGod(LgsDlivyDrctGodHist lgsDlivyDrctGodHist) throws Exception{
		return getSession1().update("com.plgrim.ncp.biz.delivery.command.updateLgsDlivyDrctGod", lgsDlivyDrctGodHist);
	}
	
	/**
	 * 출고지시 테이블 복사.
	 *
	 * @param lgsDlv the lgs dlv
	 */
    public void copyLgsDlv(LgsDlv lgsDlv) {
        getSession1().insert("com.plgrim.ncp.biz.delivery.command.copyLgsDlv", lgsDlv);
    }


	/**
	 *  물류센터 매장출고위치 확인.
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param deliveryOrderGoodDTO [설명]
	 * @return String [설명]
	 * @throws Exception the exception
	 * @since 2015. 12. 16
	 */
	public String selectDlvLcId4ItemStock(DeliveryOrderGoodDTO deliveryOrderGoodDTO) throws Exception {
		return (String)getSession1().selectOne("com.plgrim.ncp.biz.delivery.command.selectDlvLcId4ItemStock",deliveryOrderGoodDTO);
	}


	/**
	 * 배송매장출고위치ID 수정
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param lgsDdg [설명]
	 * @return Int [설명]
	 * @throws Exception the exception
	 * @since 2015. 12. 29
	 */
	public int updateDlvShopDlivyLcId(LgsDlivyDrctGod lgsDdg) throws Exception {
		return getSession1().update("com.plgrim.ncp.biz.delivery.command.updateDlvShopDlivyLcId", lgsDdg);
	}
	
	
	/**
	 * '물류출고지시상품' 데이터 전체가 '배송완료', '출고지시취소' 인 경우에
	 * 	해당 '주문'(ORD테이블)에 대해서 '배송완료' 로 상태 수정
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param deliveryOrderGoodDTO [설명]
	 * @throws Exception the exception
	 * @since 2015. 12. 29
	 */
	public void modifyOrdDlvCompt( DeliveryOrderGoodDTO deliveryOrderGoodDTO ) throws Exception {
		this.getSession1().update("com.plgrim.ncp.biz.delivery.command.modifyOrdDlvCompt", deliveryOrderGoodDTO);
	}
	
	
	/**
	 * 출고지시 정보 수정.
	 *
	 * @param lgsDlivyDrctGod the lgs dlivy drct god
	 * @return the int
	 * @throws Exception the exception
	 */
	public int updateDlvDrctInfo(LgsDlivyDrctGod lgsDlivyDrctGod) throws Exception {
		return getSession1().update("com.plgrim.ncp.biz.delivery.command.updateDlvDrctInfo", lgsDlivyDrctGod);
	}
	
	

	/**
	 * 카페 24 회원 물류 배송 수정.
	 *
	 * @param lgsDlv the lgs dlv
	 */
	public void updateOrgLgsDlv(LgsDlv lgsDlv) {
		getSession1().update("com.plgrim.ncp.biz.delivery.command.updateOrgLgsDlv", lgsDlv);
	}
	
	/**
	 * 24시간 초과 또는 출고 거절시 진행단계 변경 - 배송매장 자동배정 관련 추가
	 * <p/>
	 * <p/>
	 * <p/>
	 * [사용 방법 설명].
	 *
	 * @param dlivyDrctInfo the dlivy drct info
	 * @return the int
	 * @throws Exception the exception
	 * @since 2016. 5. 2
	 */
	public int updateDlvDrctGodCnt(DeliveryOrderGoodDTO dlivyDrctInfo) throws Exception {
		return getSession1().update("com.plgrim.ncp.biz.delivery.command.updateDlvDrctGodCnt", dlivyDrctInfo);
	}
	
	
	/**
	 * 시스템 운영시간 조회.
	 *
	 * @return the hash map
	 * @throws Exception the exception
	 */
	public HashMap<String, String> selectSystemCurrentTimeInfo() throws Exception {
		return getSession1().selectOne("com.plgrim.ncp.biz.delivery.command.selectSystemCurrentTimeInfo");
	}
	
	
	/**
	 * 픽업매장 준비완료 SMS 발송 여부 수정
	 * <p/>
	 * <p/>
	 * <p/>
	 * [사용 방법 설명].
	 *
	 * @param DeliverySearchDTO
	 * @return the int
	 * @throws Exception the exception
	 * @since 2016. 07. 04
	 */
	public int updatePickupReadySmsYn(DeliverySearchDTO deliverySearchDTO) throws Exception {
		return getSession1().update("com.plgrim.ncp.biz.delivery.command.updatePickupReadySmsYn", deliverySearchDTO);
	}
	
	
	/**
	 *  현재 상태 체크
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param deliveryOrderGoodDTO [설명]
	 * @return String [설명]
	 * @throws Exception the exception
	 * @since 2016. 07. 01
	 */
	public int getChkStatus(DeliveryOrderGoodDTO deliveryOrderGoodDTO) throws Exception {
		return (int)getSession1().selectOne("com.plgrim.ncp.biz.delivery.command.getChkStatus",deliveryOrderGoodDTO);
	}


	/**
	 * 출고검수여부 수정
	 * <p/>
	 * <p/>
	 * <p/>
	 * [사용 방법 설명].
	 *
	 * @param DeliveryOrderGoodDTO
	 * @return the int
	 * @throws Exception the exception
	 * @since 2016. 07. 13
	 */
	public int updateAcptYn(DeliveryOrderGoodDTO deliveryOrderGoodDTO) throws Exception {
		return getSession1().update("com.plgrim.ncp.biz.delivery.command.updateAcptYn", deliveryOrderGoodDTO);
	}

	/**
	 * 단품출고검수 대상 선정.
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param deliveryOrderGoodDTO [설명]
	 * @return Int [설명]
	 * @throws Exception the exception
	 * @since 2016. 8. 28
	 */
	public int updateUnitInspectionTargetInfo(DeliveryOrderGoodDTO deliveryOrderGoodDTO) throws Exception {
		return getSession1().update("com.plgrim.ncp.biz.delivery.command.updateUnitInspectionTargetInfo", deliveryOrderGoodDTO);
	}

	/**
	 * 픽업매장 후 보충(물류센터 -> 매장) 출고지시 처리
	 * 	- 매장 재고 후보충 성공 여부 및 문서번호, 오류 메시지 등 저장
	 *
	 * @param DeliveryInfErpDTO
	 * @return
	 * @throws Exception
	 */
	public int updateShopInvAfSupleYn(DeliveryInfErpDTO deliveryInfErpDTO) throws Exception {
		return getSession1().update("com.plgrim.ncp.biz.delivery.command.updateShopInvAfSupleYn", deliveryInfErpDTO);
	}
	
	/**
	 * (맞교환) 결품접수 처리.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param lgsDdg [설명]
	 * @return Int [설명]
	 * @throws Exception the exception
	 * @since 2015. 4. 7
	 */
	public int updateOrgLgsDlv(DeliveryOrderGoodDTO dlivyDrctInfo) throws Exception {
		return getSession1().update("com.plgrim.ncp.biz.delivery.command.updateShortage", dlivyDrctInfo);
	}
	
	/**
	 * 출고지 상세 정보 조회.
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dto [설명]
	 * @return LgsDlvsp [설명]
	 * @throws Exception the exception
	 * @since 2017. 2. 1
	 */
	public LgsDlvsp selectLgsDlvspInfo(DeliverySearchDTO dto) throws Exception {
		return getSession1().selectOne("com.plgrim.ncp.biz.delivery.command.selectLgsDlvspInfo", dto);
	}

	/**
	 * [매장전용] #50212 픽업 재진열 대상 알림(매장 Dashboard) 기능 추가
	 * 	- '픽업매장 재진열 완료' 수정
	 *
	 * @param deliveryOrderGoodDTO
	 * @return
	 * @throws Exception
	 */
	public int updateRedispCompt(DeliveryOrderGoodDTO deliveryOrderGoodDTO) throws Exception {
		return getSession1().update("com.plgrim.ncp.biz.delivery.command.updateRedispCompt", deliveryOrderGoodDTO);
	}

	/**
	 * 같은 주문에 같은 배송매장 전체 출고지시를 위해 조회
	 *
	 * @param deliveryOrderGoodDTO
	 * @return
	 * @throws Exception
	 */
	public List<DeliveryOrderGoodResult> selectDlivyDrctTargetList(DeliverySearchDTO search) throws Exception {
		return getSession1().selectList("com.plgrim.ncp.biz.delivery.command.selectDlivyDrctTargetList", search);
	}
	
	/**
	 * WMS 출고지시를 위해 조회
	 *
	 * @param deliveryOrderGoodDTO
	 * @return
	 * @throws Exception
	 */
	public DlivyDrctSDO selectWmsDlivyDrctTargetList(DeliverySearchDTO search) throws Exception {
		return getSession1().selectOne("com.plgrim.ncp.biz.delivery.command.selectWmsDlivyDrctTargetList", search);
	}
	
	/**
	 * @param deliveryOrderGoodDTO
	 * @return
	 * @throws Exception
	 */
	public int updateAssignDlivyDrctGod(DeliveryOrderGoodDTO deliveryOrderGoodDTO) throws Exception {
		return getSession1().update("com.plgrim.ncp.biz.delivery.command.updateAssignDlivyDrctGod", deliveryOrderGoodDTO);
	}
	
	/**
	 * 출고완료시 RT 처리를 위해 상세 조회
	 * 
	 * @param search
	 * @return
	 * @throws Exception
	 */
	public List<OrderOnlineShopRtListSDO> selectDlivyComptRtDetail(OrderOnlineShopRtListSDO search) throws Exception {
		return getSession1().selectList("com.plgrim.ncp.biz.delivery.command.selectDlivyComptRtDetail", search);
	}
	
	/**
	 * '에스크로 구매 확정|취소' 수정
	 * @return
	 */
	public void modifyEscrPchDcsn(KcpCommonReceiveDTO kcpCommonReceiveDTO) {		
		getSession1().update("com.plgrim.ncp.biz.delivery.command.modifyEscrPchDcsn", kcpCommonReceiveDTO);
	}

	/**
	 * 매장배송 주문상품에 사은품이 걸린 주문건의 경우 매장의 '주문확인여부' 조회
	 * @param ordNo
	 * @return
	 */
	public String selectOrdCnfirmYn(DeliverySearchDTO search) {
		return getSession1().selectOne("com.plgrim.ncp.biz.delivery.command.selectOrdCnfirmYn", search);
	}
	
}
