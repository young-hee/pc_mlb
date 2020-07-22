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
 * @since       2015. 10. 27       
 */
package com.plgrim.ncp.biz.delivery.service;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.codehaus.plexus.util.StringUtils;
import org.krysalis.barcode4j.DefaultBarcodeClassResolver;
import org.krysalis.barcode4j.impl.AbstractBarcodeBean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
import org.krysalis.barcode4j.tools.MimeTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;
import com.plgrim.ncp.base.abstracts.AbstractService;
import com.plgrim.ncp.base.entities.datasource1.god.God;
import com.plgrim.ncp.base.entities.datasource1.inf.InfOrdGodErpDstb;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsDlivyDrctGod;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsDlivyDrctGodExtend;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsDlivyDrctGodHist;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsDlv;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsDlvHist;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsDlvsp;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsDlvspHist;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsRtrvlDrctGod;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsRtrvlDrctGodHist;
import com.plgrim.ncp.base.entities.datasource1.ord.Ord;
import com.plgrim.ncp.base.enums.DeliveryEnum;
import com.plgrim.ncp.base.enums.GoodsEnum;
import com.plgrim.ncp.base.enums.OrderEnum;
import com.plgrim.ncp.base.repository.lgs.LgsDlvRepository;
import com.plgrim.ncp.biz.claim.exception.ClaimCompleteFailException;
import com.plgrim.ncp.biz.delivery.data.DeliveryInfErpDTO;
import com.plgrim.ncp.biz.delivery.data.DeliveryInvoiceDTO;
import com.plgrim.ncp.biz.delivery.data.DeliveryOrderGoodDTO;
import com.plgrim.ncp.biz.delivery.data.DeliverySearchDTO;
import com.plgrim.ncp.biz.delivery.data.DlvOrdGodInfoDTO;
import com.plgrim.ncp.biz.delivery.data.LgsDlivyWayBil;
import com.plgrim.ncp.biz.delivery.exception.DeliveryStatusException;
import com.plgrim.ncp.biz.delivery.exception.DeliveryUnavailableStatusException;
import com.plgrim.ncp.biz.delivery.repository.DeliveryCommandRepository;
import com.plgrim.ncp.biz.delivery.repository.DeliverySelectRepository;
import com.plgrim.ncp.biz.delivery.result.DeliveryClaimGoodResult;
import com.plgrim.ncp.biz.delivery.result.DeliveryComptMsgResult;
import com.plgrim.ncp.biz.delivery.result.DeliveryInvoiceResult;
import com.plgrim.ncp.biz.delivery.result.DeliveryOrderGoodResult;
import com.plgrim.ncp.biz.delivery.result.ReturnItemListByClaimNoResult;
import com.plgrim.ncp.biz.delivery.result.ReturnItemWithWayBillResult;
import com.plgrim.ncp.biz.goods.service.GoodsService;
import com.plgrim.ncp.biz.member.data.SecurityUserDetail;
import com.plgrim.ncp.biz.member.service.MemberPersonalInfoCommandService;
import com.plgrim.ncp.biz.order.repository.OrderBoCommandRepository;
import com.plgrim.ncp.biz.order.service.OrderSelectService;
import com.plgrim.ncp.commons.service.MailHtmlReaderService;
import com.plgrim.ncp.commons.util.BODateUtil;
import com.plgrim.ncp.commons.util.BOSecurityUtil;
import com.plgrim.ncp.framework.commons.ContextService;
import com.plgrim.ncp.framework.commons.JsonService;
import com.plgrim.ncp.framework.commons.StringService;
import com.plgrim.ncp.framework.data.SystemPK;
import com.plgrim.ncp.framework.enums.DatabaseType;
import com.plgrim.ncp.interfaces.delivery.adapter.DeliveryAdapter;
import com.plgrim.ncp.interfaces.delivery.data.DlivyDrctSDO;
import com.plgrim.ncp.interfaces.delivery.data.DlivyWmsSDO;
import com.plgrim.ncp.interfaces.email.adapter.EmailAdapter;
import com.plgrim.ncp.interfaces.email.data.EmailHtmlSDO;
import com.plgrim.ncp.interfaces.mpush.adapter.MPushAdapter;
import com.plgrim.ncp.interfaces.mpush.data.MPushAlimTalkSDO;
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
 * @since 2015. 4. 13
 */
@Slf4j
@Service
public class DeliveryStatusService extends AbstractService {

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	@Autowired
	DeliveryCommandRepository deliveryCommandRepository;

	@Autowired
	DeliverySelectRepository deliverySelectRepository;
	
	@Autowired
	DeliveryInfErpService deliveryInfErpService;

	@Autowired
	InterfaceApiCommon interfaceApiCommon;
	
    @Autowired
    LgsDlvRepository lgsDlvRepository;

	private @Value("${delivery.message.sms.pickup.ready}") String pickupSmsMsg;
	
	private @Value("${ncp_web_bo.pickup.barcode.path}") String pickupBarcodePath; 

	@Autowired
	@Qualifier("sessionTemplate1")
	SqlSession sqlSession1;

	@Autowired
	OrderBoCommandRepository orderBoCommandRepository;

	@Autowired
	MemberPersonalInfoCommandService memberPersonalInfoCommandService;
	
	@Autowired
	DeliveryCommandService deliveryCommandService;
	
	@Autowired
	DeliveryAdapter deliveryAdapter;
	
	@Autowired
	MPushAdapter mPushAdapter;
	
	@Autowired
	MailHtmlReaderService mailHtmlReaderService;
	
	@Autowired
	EmailAdapter emailAdapter;
	
	@Autowired
	GoodsService goodsService;
	
	@Autowired
	OrderSelectService orderSelectService;

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
	 * 검수 후 회수완료.
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
	public int updateRetrivalComplete(SystemPK systemPk, List<DeliveryOrderGoodDTO> gridList) throws Exception {
		int idx = 0;
		LgsDlv lgsDlv = new LgsDlv();
//		LgsDlvHist lgsDlvHist = new LgsDlvHist();
		LgsRtrvlDrctGodHist lgsRtrvlDrctGodHist = new LgsRtrvlDrctGodHist();
		DeliveryOrderGoodDTO deliveryOrderGoodDTO = new DeliveryOrderGoodDTO();
		String userId = BOSecurityUtil.getLoginId();	//세션ID
		
		
		for (DeliveryOrderGoodDTO obj : gridList) {
			//1. 회수완료 처리
			if(StringService.isNotEmpty(obj.getParams())) {
				String[] paramArr = obj.getParams().substring(0, obj.getParams().length()-1).split(",");
				for (String param : paramArr) {
					if(!"".equals(param) && null != param) {
						deliveryOrderGoodDTO.setRtrvlDrctGodNo(param);
						deliveryOrderGoodDTO.setRtrvlStatCd("RTRVL_COMPT");	//회수상태 : 회수완료
						deliveryOrderGoodDTO.setUdterId(userId);
						deliveryCommandRepository.updateRetrivalStatus(deliveryOrderGoodDTO);
					}
	            }
			}
			
			//2. 물류 회수지시 상품 이력 등록
			if(StringService.isNotEmpty(obj.getParams())) {
				String[] paramArr = obj.getParams().substring(0, obj.getParams().length()-1).split(",");
				for (String param : paramArr) {
					if(!"".equals(param) && null != param) {
						lgsRtrvlDrctGodHist.setRtrvlDrctGodNo(param);
						lgsRtrvlDrctGodHist.setRegtrId(userId);
						lgsRtrvlDrctGodHist.setUdterId(userId);
						deliveryCommandRepository.insertLgsRtrvlDrctGodHist(lgsRtrvlDrctGodHist);
					}
				}
			}
			
			//3. 택배사정보 수정
			lgsDlv.setOrdNo(obj.getOrdNo());
			lgsDlv.setClmNo(obj.getClmNo());
			lgsDlv.setDlvPcupspTurn(obj.getDlvPcupspTurn());
			lgsDlv.setDlvTurn(obj.getDlvTurn());
			lgsDlv.setDlvComCd(obj.getDlvComCd());
			lgsDlv.setDmstcWaybilNo(obj.getWaybilNo());
			lgsDlv.setUdterId(userId);
			deliveryCommandRepository.updateRtrvlWaybilInfo(lgsDlv);

			/* TODO : 무결성 제약조건으로 인해 잠시 주석처리 : 추후에 주석제거
			//4. 물류배송이력 등록
			lgsDlvHist.setOrdNo(obj.getOrdNo());
			lgsDlvHist.setClmNo(obj.getClmNo());
			lgsDlvHist.setDlvPcupspTurn(obj.getDlvPcupspTurn());
			lgsDlvHist.setDlvTurn(obj.getDlvTurn());
			lgsDlvHist.setRegtrId(userId);
			lgsDlvHist.setUdterId(userId);
			deliveryCommandRepository.insertLgsDlvHist(lgsDlvHist);
			*/
			
			//5. 인터페이스주문상품 수선정보 초기화
			if(StringService.isNotEmpty(obj.getParams2())) {
				String[] paramArr = obj.getParams2().substring(0, obj.getParams2().length()-1).split(",");
				for (String param : paramArr) {
					if(!"".equals(param) && null != param) {
						deliveryOrderGoodDTO.setOrdNo(obj.getOrdNo());
						deliveryOrderGoodDTO.setClmNo(obj.getClmNo());
						deliveryOrderGoodDTO.setDlivyDrctGodNo(obj.getDlivyDrctGodNo());
						deliveryCommandRepository.updateInfErpRepairInfo(deliveryOrderGoodDTO);
					}
				}
			}
			idx++;
		}
		return idx;
	}
	
	
	/**
	 * 맞교환 회수완료.
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
	public int updateCompleteRetrival(SystemPK systemPk, List<DeliveryClaimGoodResult> gridList) throws Exception {
		//log.info("#################################[updateCompleteRetrival] gridList:"+ gridList);
		int idx = 0;
		LgsRtrvlDrctGod lgsRtrvlDrctGod = new LgsRtrvlDrctGod();
		LgsRtrvlDrctGodHist lgsRtrvlDrctGodHist = new LgsRtrvlDrctGodHist();
		DeliveryOrderGoodDTO deliveryOrderGoodDTO = new DeliveryOrderGoodDTO();
		String userId = BOSecurityUtil.getLoginId();	//세션ID
		
		for (DeliveryClaimGoodResult deliveryClaimGoodResult : gridList) {
			//1. 맞교환 회수완료처리
			lgsRtrvlDrctGod.setRtrvlDrctGodNo(deliveryClaimGoodResult.getLgsRdg().getRtrvlDrctGodNo());	//회수지시상품번호
			lgsRtrvlDrctGod.setRtrvlStatCd("RTRVL_COMPT");	//회수상태 : 회수완료
			lgsRtrvlDrctGod.setUdterId(userId);
			deliveryCommandRepository.updateLgsRdg4CompRet(lgsRtrvlDrctGod);
			
			//2. 물류 회수지시 상품 이력 등록
			lgsRtrvlDrctGodHist.setRtrvlDrctGodNo(deliveryClaimGoodResult.getLgsRdg().getRtrvlDrctGodNo());
			lgsRtrvlDrctGodHist.setRegtrId(userId);
			lgsRtrvlDrctGodHist.setUdterId(userId);
			deliveryCommandRepository.insertLgsRtrvlDrctGodHist(lgsRtrvlDrctGodHist);
			
			//3. 인터페이스주문상품 수선정보 초기화
			deliveryOrderGoodDTO.setOrdNo(deliveryClaimGoodResult.getOrd().getOrdNo());
			deliveryOrderGoodDTO.setClmNo(deliveryClaimGoodResult.getClm().getClmNo());
			deliveryOrderGoodDTO.setDlivyDrctGodNo(deliveryClaimGoodResult.getLgsDdg().getDlivyDrctGodNo());
			deliveryCommandRepository.updateInfErpRepairInfo(deliveryOrderGoodDTO);
			
			idx++;
		}
		
		return idx;
	}
	
	
	/**
	 * 맞교환 회수완료.
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
	public int updateDrctExtCompleteRetrival(SystemPK systemPk, List<DeliveryOrderGoodResult> gridList) throws Exception {
		//log.info("#################################[updateCompleteRetrival] gridList:"+ gridList);
		int idx = 0;
		LgsRtrvlDrctGod lgsRtrvlDrctGod = new LgsRtrvlDrctGod();
		LgsRtrvlDrctGodHist lgsRtrvlDrctGodHist = new LgsRtrvlDrctGodHist();
		DeliveryOrderGoodDTO deliveryOrderGoodDTO = new DeliveryOrderGoodDTO();
		String userId = BOSecurityUtil.getLoginId();	//세션ID
		
		for (DeliveryOrderGoodResult deliveryOrderGoodResult : gridList) {
			//1. 맞교환 회수완료처리
			lgsRtrvlDrctGod.setRtrvlDrctGodNo(deliveryOrderGoodResult.getRtrvlDrctGodNo());	//회수지시상품번호
			lgsRtrvlDrctGod.setRtrvlStatCd("RTRVL_COMPT");	//회수상태 : 회수완료
			lgsRtrvlDrctGod.setUdterId(userId);
			deliveryCommandRepository.updateLgsRdg4CompRet(lgsRtrvlDrctGod);
			
			//2. 물류 회수지시 상품 이력 등록
			lgsRtrvlDrctGodHist.setRtrvlDrctGodNo(deliveryOrderGoodResult.getRtrvlDrctGodNo());
			lgsRtrvlDrctGodHist.setRegtrId(userId);
			lgsRtrvlDrctGodHist.setUdterId(userId);
			deliveryCommandRepository.insertLgsRtrvlDrctGodHist(lgsRtrvlDrctGodHist);
			
			//3. 인터페이스주문상품 수선정보 초기화
			deliveryOrderGoodDTO.setOrdNo(deliveryOrderGoodResult.getOrd().getOrdNo());
			deliveryOrderGoodDTO.setClmNo(deliveryOrderGoodResult.getClmNo());
			deliveryOrderGoodDTO.setDlivyDrctGodNo(deliveryOrderGoodResult.getLgsDdg().getDlivyDrctGodNo());
			deliveryCommandRepository.updateInfErpRepairInfo(deliveryOrderGoodDTO);
			
			idx++;
		}
		
		return idx;
	}


	/**
	 *  맞교환 품절처리(결품요청).
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
	public int updateShortage(SystemPK systemPk, List<DeliveryOrderGoodResult> gridList) throws Exception {
		LgsDlivyDrctGod lgsDdg = new LgsDlivyDrctGod();
		String udterId = BOSecurityUtil.getLoginId();	//세션ID
		String status = "";
		int successCnt = 0;

		LgsDlivyDrctGodHist lgsDlivyDrctGodHist = new LgsDlivyDrctGodHist();
		for (DeliveryOrderGoodResult deliveryOrderGoodResult : gridList) {
			lgsDdg.setDlivyDrctGodNo(deliveryOrderGoodResult.getLgsDdg().getDlivyDrctGodNo());
			lgsDdg.setShtgQty(deliveryOrderGoodResult.getLgsDdg().getDlivyDrctQty());
			lgsDdg.setUdterId(udterId);

			//1. 품절건 유효성 체크 ['출고지시' || '배송대기' || '매장준비완료' 만 유효]
			status = deliveryCommandRepository.getDeliveryStatus(lgsDdg);

			//2. 결품접수 처리
			if("DLIVY_DRCT".equals(status) || "DLV_WAIT".equals(status) || "SHOP_PRPARE_COMPT".equals(status)) {
				successCnt += deliveryCommandRepository.updateShortage(lgsDdg);

				lgsDlivyDrctGodHist.setDlivyDrctGodNo(deliveryOrderGoodResult.getLgsDdg().getDlivyDrctGodNo());
				lgsDlivyDrctGodHist.setRegtrId(udterId);
				lgsDlivyDrctGodHist.setUdterId(udterId);
				deliveryCommandRepository.insertLgsDlivyDrctGodHist(lgsDlivyDrctGodHist);//이력저장

			} else {
				throw new DeliveryUnavailableStatusException(null);
			}
		}
		return successCnt;
	}

	/**
	 *  결품 철회.
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
	public int updateShortageCancel(SystemPK systemPk, List<DeliveryOrderGoodResult> gridList) throws Exception {
		LgsDlivyDrctGod lgsDdg = new LgsDlivyDrctGod();
		String udterId = BOSecurityUtil.getLoginId();	//세션ID
		String status = "";
		int successCnt = 0;

		LgsDlivyDrctGodHist lgsDlivyDrctGodHist = new LgsDlivyDrctGodHist();
		for (DeliveryOrderGoodResult deliveryOrderGoodResult : gridList) {
			lgsDdg.setDlivyDrctGodNo(deliveryOrderGoodResult.getLgsDdg().getDlivyDrctGodNo());
			lgsDdg.setUdterId(udterId);

			//1. 품절건 유효성 체크 ['결품 접수' 만 유효]
			status = deliveryCommandRepository.getDeliveryStatus(lgsDdg);

			//2. 결품접수 처리
			if("SHTG_RCEPT".equals(status)) {
				
//				지정희님 요구사항
//				1. 출고지시 상태에서 결품접수->철회시 [출고지시] 
//				2. 준비완료 상태에서 결품접수->철회시 [매장준비완료]
				if(StringService.isNotEmpty(deliveryOrderGoodResult.getLgsDlv().getDmstcWaybilNo()))
				{
					lgsDdg.setDlvStatCd("SHOP_PRPARE_COMPT");
				}else
				{
					lgsDdg.setDlvStatCd("DLIVY_DRCT");
				}
				
				successCnt += deliveryCommandRepository.updateShortageCancel(lgsDdg);

				lgsDlivyDrctGodHist.setDlivyDrctGodNo(deliveryOrderGoodResult.getLgsDdg().getDlivyDrctGodNo());
				lgsDlivyDrctGodHist.setRegtrId(udterId+": 결품 철회");
				lgsDlivyDrctGodHist.setUdterId(udterId+": 결품 철회");
				deliveryCommandRepository.insertLgsDlivyDrctGodHist(lgsDlivyDrctGodHist);//이력저장

			} else {
				throw new DeliveryUnavailableStatusException(null);
			}
		}
		return successCnt;
	}


	/**
	 *  입점업체 결품처리.
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param systemPk [설명]
	 * @param gridList [설명]
	 * @return int
	 * @throws Exception the exception
	 * @since 2015. 12. 17
	 * 
	 */	
	public int updatePartMallShortage(SystemPK systemPk, LgsDlivyDrctGod lgsDdg) throws Exception {
		String status = "";
		int successCnt = 0;

		LgsDlivyDrctGodHist lgsDlivyDrctGodHist = new LgsDlivyDrctGodHist();

		//1. 품절건 유효성 체크 ['출고지시' || '배송대기' || '매장준비완료' 만 유효]
		status = deliveryCommandRepository.getDeliveryStatus(lgsDdg);

		//2. 결품접수 처리
		if("DLIVY_DRCT".equals(status) || "DLV_WAIT".equals(status) || "SHOP_PRPARE_COMPT".equals(status)) {
			successCnt += deliveryCommandRepository.updateShortage(lgsDdg);

			lgsDlivyDrctGodHist.setDlivyDrctGodNo(lgsDdg.getDlivyDrctGodNo());
			lgsDlivyDrctGodHist.setRegtrId(lgsDdg.getUdterId());
			lgsDlivyDrctGodHist.setUdterId(lgsDdg.getUdterId());
			deliveryCommandRepository.insertLgsDlivyDrctGodHist(lgsDlivyDrctGodHist);//이력저장

		} else {
			throw new DeliveryUnavailableStatusException(null);
		}
			
		return successCnt;		
	}
	
    /**
     * 송장번호가 같은 DLV_LGS 조회
     * @param returnItemList
     * @param dmstcWaybilNo
     * @return ReturnItemWithWayBillResult
     */
    private ReturnItemWithWayBillResult getSameWayBillLgsDlv(List<ReturnItemWithWayBillResult> returnItemList, String dmstcWaybilNo) {
        ReturnItemWithWayBillResult sameWayBillLgsDlv = null;
        for (ReturnItemWithWayBillResult returnItem : returnItemList) {
            if ((dmstcWaybilNo == null || "".equals(dmstcWaybilNo)) && (returnItem.getDmstcWaybilNo() == null || "".equals(returnItem.getDmstcWaybilNo()))
                    || dmstcWaybilNo.equals(returnItem.getDmstcWaybilNo())) {
                sameWayBillLgsDlv = returnItem;
                break;
            }
        }
        return sameWayBillLgsDlv;
    }

	/**
	 * [회수 팝업] 회수완료정보 수정.
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param systemPk [설명]
	 * @param gridList [설명]
	 * @return true:[설명], false:[설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 4
	 */
	public void updateRtrvlComptInfo(SystemPK systemPk, List<DeliveryClaimGoodResult> gridList) throws Exception {
		LgsDlv lgsDlv = new LgsDlv();
		LgsDlvHist lgsDlvHist = new LgsDlvHist();
		LgsRtrvlDrctGod lgsRtrvlDrctGod = new LgsRtrvlDrctGod();
		LgsRtrvlDrctGodHist lgsRtrvlDrctGodHist = new LgsRtrvlDrctGodHist();
		LgsDlivyDrctGodHist lgsDlivyDrctGodHist = new LgsDlivyDrctGodHist();
		DeliveryOrderGoodDTO deliveryOrderGoodDTO = new DeliveryOrderGoodDTO();
		DeliveryInvoiceDTO deliveryInvoiceDTO = new DeliveryInvoiceDTO();
		ReturnItemWithWayBillResult sameWayBilLsgDlv = null;
		DeliverySearchDTO deliverySearchDTO = new DeliverySearchDTO();
		
		// [#48750][개발] 불량상품 고객에 대한 교환/반품 Process 개선 요청
		// ResultRtrvl batch 에서 호출시 exception 처리.
		String userId = "";
		try {
			userId = BOSecurityUtil.getLoginId();	//세션ID
		} catch(Exception e) {
			log.info("### ResultRtrvl batch call LoginId null ###");
		}
		if(StringService.isEmpty(userId)) {
			userId = "RESULT_RTRVL_BATCH";
		}
		
		for (DeliveryClaimGoodResult deliveryClaimGoodResult : gridList) {
            //0. 회수지시상품 송장번호 리스트 조회
            deliverySearchDTO.setOrdNo(deliveryClaimGoodResult.getOrd().getOrdNo());
            deliverySearchDTO.setClmNo(deliveryClaimGoodResult.getClm().getClmNo());
            List<ReturnItemWithWayBillResult> returnItemList = deliverySelectRepository.selectReturnProductList(deliverySearchDTO);
            Integer changedDlvturn = null;
            
            log.debug("************************** lgsDlvReturnItemList **************** \n" + returnItemList );
            
            for (ReturnItemWithWayBillResult returnItem : returnItemList) {
                // 회수지시 상품의 송장번호가 변경된 경우
                if (returnItem.getRtrvlDrctGodNo().equals(deliveryClaimGoodResult.getLgsRdg().getRtrvlDrctGodNo()) 
                        && deliveryClaimGoodResult.getLgsDlv().getDmstcWaybilNo() != null && !deliveryClaimGoodResult.getLgsDlv().getDmstcWaybilNo().equals(returnItem.getDmstcWaybilNo())) {
                    sameWayBilLsgDlv = getSameWayBillLgsDlv(returnItemList, deliveryClaimGoodResult.getLgsDlv().getDmstcWaybilNo());
                    if (returnItemList != null && returnItemList.size() > 1 && sameWayBilLsgDlv == null) {
                        // i) 송장번호가 변경되고 신규 송장번호이고 주문번호, 배송수거지순번, 배송순번이 같은 회수상품이 2개 이상인경우 LGS_DLV 에서 Row추가후 배송순번 변경
                        LgsDlv lgsDlvParam = new LgsDlv();
                        lgsDlvParam.setOrdNo(returnItem.getOrdNo());
                        lgsDlvParam.setDlvPcupspTurn(returnItem.getDlvPcupspTurn());
                        lgsDlvParam.setDlvTurn(returnItem.getDlvTurn());
                        LgsDlv lgsDlvCopy = lgsDlvRepository.selectLgsDlv(lgsDlvParam);
                        lgsDlvCopy.setRegtrId(userId);
                        lgsDlvCopy.setUdterId(userId);
                        deliveryCommandRepository.copyLgsDlv(lgsDlvCopy);

                        lgsDlvHist.setOrdNo(deliveryClaimGoodResult.getOrd().getOrdNo());
                        lgsDlvHist.setDlvPcupspTurn(deliveryClaimGoodResult.getLgsDsp().getDlvPcupspTurn());
                        lgsDlvHist.setDlvTurn(lgsDlvCopy.getDlvTurn());
                        lgsDlvHist.setRegtrId(userId);
                        lgsDlvHist.setUdterId(userId);
                        deliveryCommandRepository.insertLgsDlvHist(lgsDlvHist);
                        
                        log.info("############## 송장번호변경으로 인한 LGS_DLV 복사" + lgsDlvCopy);
                        
                        changedDlvturn = lgsDlvCopy.getDlvTurn();
                        
                    }
                    else if (sameWayBilLsgDlv != null) {
                        // ii) 변경된 송장번호가 다른 상품에서 이미 존재하는경우 송장번호에 해당하는 배송순번 변경
                        changedDlvturn = sameWayBilLsgDlv.getDlvTurn();
                    }
                    break;
                }
            }
		    
		    
			if("Y".equals(deliveryClaimGoodResult.getLgsRdg().getGftYn())) //사은품인 경우 회수상태 변경기능 추가.
			{
				//1. 회수지시상품 정보 수정
				lgsRtrvlDrctGod.setRtrvlDrctGodNo(deliveryClaimGoodResult.getLgsRdg().getRtrvlDrctGodNo());
				lgsRtrvlDrctGod.setRtgodcstCalSectCd("");																//반품비정산구분코드
				lgsRtrvlDrctGod.setRtgodcstCalAmt(new BigDecimal("0"));													//반품비정산금액
				lgsRtrvlDrctGod.setErpInvTrnsmisSectCd("");																//ERP재고전송구분코드
				lgsRtrvlDrctGod.setRtrvlDrctGrpDgre(deliveryClaimGoodResult.getLgsRdg().getRtrvlDrctGrpDgre());			//차수
				lgsRtrvlDrctGod.setBadnReqestCont("");																	//불량의뢰내용
				lgsRtrvlDrctGod.setRtrvlGodStatCd(deliveryClaimGoodResult.getLgsRdg().getRtrvlGodStatCd());				//회수상품상태코드

			}
			else {   // 사은품이 아닌 경우
			    //1. 회수지시상품 정보 수정
	            lgsRtrvlDrctGod.setRtrvlDrctGodNo(deliveryClaimGoodResult.getLgsRdg().getRtrvlDrctGodNo());
	            lgsRtrvlDrctGod.setRtgodcstCalSectCd(deliveryClaimGoodResult.getLgsRdg().getRtgodcstCalSectCd());
	            lgsRtrvlDrctGod.setRtgodcstCalAmt(deliveryClaimGoodResult.getLgsRdg().getRtgodcstCalAmt());
	            lgsRtrvlDrctGod.setErpInvTrnsmisSectCd(deliveryClaimGoodResult.getLgsRdg().getErpInvTrnsmisSectCd());
	            lgsRtrvlDrctGod.setRtrvlDrctGrpDgre(deliveryClaimGoodResult.getLgsRdg().getRtrvlDrctGrpDgre());
	            lgsRtrvlDrctGod.setBadnReqestCont(deliveryClaimGoodResult.getLgsRdg().getBadnReqestCont());
	            lgsRtrvlDrctGod.setRtrvlGodStatCd(deliveryClaimGoodResult.getLgsRdg().getRtrvlGodStatCd());
			}
            
            if (changedDlvturn != null) {    // 배송순번이 변경된경우
                lgsRtrvlDrctGod.setDlvTurn(changedDlvturn);
            }
            //ERP재고전송구분 : 센터재고 ==> 회수매장 : 물류센터
            if("CNTR_INV".equals(deliveryClaimGoodResult.getLgsRdg().getErpInvTrnsmisSectCd())) {
            	//mall 분리
            	God tempGod = goodsService.getGoods(deliveryClaimGoodResult.getLgsRdg().getRtrvlDrctGodNo());
            	String rtrvlShopId = DeliveryEnum.DLV_ONLINE_SHOP_DISCOVERY.toString();
            	if("M".equals(tempGod.getBrndId())) {
            		rtrvlShopId = DeliveryEnum.DLV_ONLINE_SHOP_MLB.toString();
            	} else if("I".equals(tempGod.getBrndId())) {
            		rtrvlShopId = DeliveryEnum.DLV_ONLINE_SHOP_MLB_KIDZ.toString();
            	} else if("A".equals(tempGod.getBrndId())) {
            		rtrvlShopId = DeliveryEnum.DLV_ONLINE_SHOP_SA.toString();
            	}
                lgsRtrvlDrctGod.setRtrvlShopId(rtrvlShopId);
                //ERP재고전송구분 : 매장재고 ==> 회수매장 : 출고매장
            } else if("SHOP_INV".equals(deliveryClaimGoodResult.getLgsRdg().getErpInvTrnsmisSectCd())) {
                lgsRtrvlDrctGod.setRtrvlShopId(deliveryClaimGoodResult.getLgsDdg().getDlvShopId());
            }
            lgsRtrvlDrctGod.setRtrvlStatCd("RTRVL_COMPT");
            lgsRtrvlDrctGod.setUdterId(userId);
            deliveryCommandRepository.updateRtrvlComptInfo(lgsRtrvlDrctGod);

            //2. 물류 회수지시 상품 이력 등록
            lgsRtrvlDrctGodHist.setRtrvlDrctGodNo(deliveryClaimGoodResult.getLgsRdg().getRtrvlDrctGodNo());
            lgsRtrvlDrctGodHist.setRegtrId(userId);
            lgsRtrvlDrctGodHist.setUdterId(userId);
            deliveryCommandRepository.insertLgsRtrvlDrctGodHist(lgsRtrvlDrctGodHist);

            //3. 택배사정보 수정
            lgsDlv.setOrdNo(deliveryClaimGoodResult.getOrd().getOrdNo());
            lgsDlv.setDlvPcupspTurn(deliveryClaimGoodResult.getLgsDsp().getDlvPcupspTurn());
            if (changedDlvturn == null) {
                lgsDlv.setDlvTurn(deliveryClaimGoodResult.getLgsDlv().getDlvTurn());
            }
            else {
                lgsDlv.setDlvTurn(changedDlvturn);
            }
            lgsDlv.setDlvComCd(deliveryClaimGoodResult.getLgsDlv().getDlvComCd());
            lgsDlv.setDmstcWaybilNo(deliveryClaimGoodResult.getLgsDlv().getDmstcWaybilNo());
            lgsDlv.setOvseaWaybilNo(deliveryClaimGoodResult.getLgsDlv().getOvseaWaybilNo());
            lgsDlv.setUdterId(userId);
            deliveryCommandRepository.updateRtrvlWaybilInfo(lgsDlv);

            //4. 물류배송이력 등록
            lgsDlvHist.setOrdNo(deliveryClaimGoodResult.getOrd().getOrdNo());
            lgsDlvHist.setDlvPcupspTurn(deliveryClaimGoodResult.getLgsDsp().getDlvPcupspTurn());
            if (changedDlvturn == null) {
                lgsDlvHist.setDlvTurn(deliveryClaimGoodResult.getLgsDlv().getDlvTurn());
            }
            else {
                lgsDlvHist.setDlvTurn(changedDlvturn);
            }
            lgsDlvHist.setRegtrId(userId);
            lgsDlvHist.setUdterId(userId);
            deliveryCommandRepository.insertLgsDlvHist(lgsDlvHist);

            /*
            //5. 고객정보 수정
            ord.setOrdNo(deliveryClaimGoodResult.getOrd().getOrdNo());
            ord.setCstmrNm(deliveryClaimGoodResult.getOrd().getCstmrNm());
            if(StringService.isNotEmpty(deliveryClaimGoodResult.getCstmrMobilNo())) {
                ord.setCstmrMobilNationNo(makePhoneNumber(deliveryClaimGoodResult.getCstmrMobilNo())[0]);
                ord.setCstmrMobilAreaNo(makePhoneNumber(deliveryClaimGoodResult.getCstmrMobilNo())[1]);
                ord.setCstmrMobilTlofNo(makePhoneNumber(deliveryClaimGoodResult.getCstmrMobilNo())[2]);
                ord.setCstmrMobilTlofWthnNo(makePhoneNumber(deliveryClaimGoodResult.getCstmrMobilNo())[3]);
            }
            deliveryCommandRepository.updateCstmrInfo(ord);
            */

            if(!"Y".equals(deliveryClaimGoodResult.getLgsRdg().getGftYn())) //사은품인 경우 회수상태 변경기능 추가.
            {
                //6. 인터페이스주문상품 수선정보 초기화
                deliveryOrderGoodDTO.setOrdNo(deliveryClaimGoodResult.getOrd().getOrdNo());
                deliveryOrderGoodDTO.setClmNo(deliveryClaimGoodResult.getClm().getClmNo());
                deliveryOrderGoodDTO.setDlivyDrctGodNo(deliveryClaimGoodResult.getLgsDdg().getDlivyDrctGodNo());
                deliveryCommandRepository.updateInfErpRepairInfo(deliveryOrderGoodDTO);
            }

            //7. 교환일때 교환입고대기 상태인 출고대상 상품을 배정대기 상태로 변경한다.
            deliveryInvoiceDTO.setOrdNo(deliveryClaimGoodResult.getOrd().getOrdNo());
            deliveryInvoiceDTO.setDlvPcupspTurn(deliveryClaimGoodResult.getLgsDsp().getDlvPcupspTurn().toString());
            if (changedDlvturn == null) {
                deliveryInvoiceDTO.setDlvTurn(deliveryClaimGoodResult.getLgsDlv().getDlvTurn().toString());
            }
            else {
                deliveryInvoiceDTO.setDlvTurn(changedDlvturn.toString());
            }
            List<DeliveryInvoiceResult> deliveryInvoiceResults = deliverySelectRepository.getInvoiceBasic2(deliveryInvoiceDTO);
            if("GNRL_EXCHG".equals(deliveryClaimGoodResult.getClm().getClmTpCd())) {
                for(DeliveryInvoiceResult deliveryInvoiceResult : deliveryInvoiceResults) {
                	// 매출 -+필요 (클레임 배치에서 처리 하기로함.)
                    lgsDlivyDrctGodHist.setDlivyDrctGodNo(deliveryInvoiceResult.getExchgDlivyDrctGodNo());
                    lgsDlivyDrctGodHist.setRegtrId(userId);
                    lgsDlivyDrctGodHist.setUdterId(userId);
                    deliveryCommandRepository.updateLgsDlivyDrctGod(lgsDlivyDrctGodHist);
                    deliveryCommandRepository.insertLgsDlivyDrctGodHist(lgsDlivyDrctGodHist);
                }
            }

            //8. 회수지시 상품없는 물류배송 LGS_DLV 데이터 삭제
            // 회수상품없는 송장정보 수정은 보류함. 
//          LgsRtrvlDrctGod lgsRtrvlDrctGodParam = new LgsRtrvlDrctGod();
//          lgsRtrvlDrctGodParam.setOrdNo(deliveryClaimGoodResult.getOrd().getOrdNo());
//          lgsRtrvlDrctGodParam.setDlvPcupspTurn(deliveryClaimGoodResult.getLgsDsp().getDlvPcupspTurn());
//          lgsRtrvlDrctGodParam.setDlvTurn(deliveryClaimGoodResult.getLgsDlv().getDlvTurn());
//          if (deliverySelectRepository.getCountReturnProductList(lgsRtrvlDrctGodParam) == 0) {
//              LgsDlv lgsDlvForDelete = new LgsDlv();
//              lgsDlvForDelete.setOrdNo(lgsRtrvlDrctGodParam.getOrdNo());
//              lgsDlvForDelete.setDlvPcupspTurn(lgsRtrvlDrctGodParam.getDlvPcupspTurn());
//              lgsDlvForDelete.setDlvTurn(lgsRtrvlDrctGodParam.getDlvTurn());
//              lgsDlvRepository.deleteLgsDlv(lgsDlvForDelete);
//                log.info("############## 회수지시상품없는 LGS_DLV 삭제" + lgsDlvForDelete);
//          }
            
		}
	}


	/**
	 * [회수리스트] 회수정보 수정.
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param systemPk [설명]
	 * @param gridList [설명]
	 * @return true:[설명], false:[설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 4
	 */
	public void updateRtrvlInfo(SystemPK systemPk, List<DeliveryClaimGoodResult> gridList) throws Exception {
//		LgsDlv lgsDlv = new LgsDlv();
//		LgsDlvHist lgsDlvHist = new LgsDlvHist();
		LgsRtrvlDrctGod lgsRtrvlDrctGod = new LgsRtrvlDrctGod();
		LgsRtrvlDrctGodHist lgsRtrvlDrctGodHist = new LgsRtrvlDrctGodHist();
		DeliveryOrderGoodDTO deliveryOrderGoodDTO = new DeliveryOrderGoodDTO();
		Ord ord = new Ord();
		String userId = BOSecurityUtil.getLoginId();	//세션ID
		
		for (DeliveryClaimGoodResult deliveryClaimGoodResult : gridList) {
			//1. 회수지시상품 정보 수정
			lgsRtrvlDrctGod.setRtrvlDrctGodNo(deliveryClaimGoodResult.getLgsRdg().getRtrvlDrctGodNo());
//			lgsRtrvlDrctGod.setRtgodcstCalSectCd(deliveryClaimGoodResult.getLgsRdg().getRtgodcstCalSectCd());
//			lgsRtrvlDrctGod.setRtgodcstCalAmt(deliveryClaimGoodResult.getLgsRdg().getRtgodcstCalAmt());
			lgsRtrvlDrctGod.setErpInvTrnsmisSectCd(deliveryClaimGoodResult.getLgsRdg().getErpInvTrnsmisSectCd());
			lgsRtrvlDrctGod.setRtrvlDrctGrpDgre(deliveryClaimGoodResult.getLgsRdg().getRtrvlDrctGrpDgre());
			lgsRtrvlDrctGod.setBadnReqestCont(deliveryClaimGoodResult.getLgsRdg().getBadnReqestCont());
			lgsRtrvlDrctGod.setRtrvlGodStatCd(deliveryClaimGoodResult.getLgsRdg().getRtrvlGodStatCd());
			lgsRtrvlDrctGod.setUdterId(userId);
			deliveryCommandRepository.updateRtrvlInfo(lgsRtrvlDrctGod);
			
			//2. 물류 회수지시 상품 이력 등록
			lgsRtrvlDrctGodHist.setRtrvlDrctGodNo(deliveryClaimGoodResult.getLgsRdg().getRtrvlDrctGodNo());
			lgsRtrvlDrctGodHist.setRegtrId(userId);
			lgsRtrvlDrctGodHist.setUdterId(userId);
			deliveryCommandRepository.insertLgsRtrvlDrctGodHist(lgsRtrvlDrctGodHist);
			
			/*
			//3. 택배사정보 수정
			lgsDlv.setOrdNo(deliveryClaimGoodResult.getOrd().getOrdNo());
			lgsDlv.setDlvPcupspTurn(deliveryClaimGoodResult.getLgsDsp().getDlvPcupspTurn());
			lgsDlv.setDlvTurn(deliveryClaimGoodResult.getLgsDlv().getDlvTurn());
			lgsDlv.setDlvComCd(deliveryClaimGoodResult.getLgsDlv().getDlvComCd());
			lgsDlv.setDmstcWaybilNo(deliveryClaimGoodResult.getLgsDlv().getDmstcWaybilNo());
			lgsDlv.setOvseaWaybilNo(deliveryClaimGoodResult.getLgsDlv().getOvseaWaybilNo());
			lgsDlv.setUdterId(userId);
			deliveryCommandRepository.updateRtrvlWaybilInfo(lgsDlv);
			
			//4. 물류배송이력 등록
			lgsDlvHist.setOrdNo(deliveryClaimGoodResult.getOrd().getOrdNo());
			lgsDlvHist.setDlvPcupspTurn(deliveryClaimGoodResult.getLgsDsp().getDlvPcupspTurn());
			lgsDlvHist.setDlvTurn(deliveryClaimGoodResult.getLgsDlv().getDlvTurn());
			lgsDlvHist.setRegtrId(userId);
			lgsDlvHist.setUdterId(userId);
			deliveryCommandRepository.insertLgsDlvHist(lgsDlvHist);
			*/
			
			//5. 고객정보 수정
			ord.setOrdNo(deliveryClaimGoodResult.getOrd().getOrdNo());
			ord.setCstmrNm(deliveryClaimGoodResult.getOrd().getCstmrNm());
			if(StringService.isNotEmpty(deliveryClaimGoodResult.getCstmrMobilNo())) {
				ord.setCstmrMobilNationNo(makePhoneNumber(deliveryClaimGoodResult.getCstmrMobilNo())[0]);
				ord.setCstmrMobilAreaNo(makePhoneNumber(deliveryClaimGoodResult.getCstmrMobilNo())[1]);
				ord.setCstmrMobilTlofNo(makePhoneNumber(deliveryClaimGoodResult.getCstmrMobilNo())[2]);
				ord.setCstmrMobilTlofWthnNo(makePhoneNumber(deliveryClaimGoodResult.getCstmrMobilNo())[3]);
			}
			deliveryCommandRepository.updateCstmrInfo(ord);
			
			//6. 인터페이스주문상품 수선정보 초기화
			deliveryOrderGoodDTO.setOrdNo(deliveryClaimGoodResult.getOrd().getOrdNo());
			deliveryOrderGoodDTO.setClmNo(deliveryClaimGoodResult.getClm().getClmNo());
			deliveryOrderGoodDTO.setDlivyDrctGodNo(deliveryClaimGoodResult.getLgsDdg().getDlivyDrctGodNo());
			deliveryCommandRepository.updateInfErpRepairInfo(deliveryOrderGoodDTO);
		}
	}


	/**
	 * 불량상품 정보 수정.
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param systemPk [설명]
	 * @param gridList [설명]
	 * @return true:[설명], false:[설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 4
	 */
	public void updateFaultyGoodsInfo(SystemPK systemPk, List<DeliveryClaimGoodResult> gridList) throws Exception {
		DeliveryOrderGoodDTO deliveryOrderGoodDTO = new DeliveryOrderGoodDTO();
		LgsRtrvlDrctGodHist lgsRtrvlDrctGodHist = new LgsRtrvlDrctGodHist();
		LgsDlvspHist lgsDlvspHist = new LgsDlvspHist();
		Ord ord = new Ord();
		String userId = BOSecurityUtil.getLoginId();	//세션ID

		for (DeliveryClaimGoodResult deliveryClaimGoodResult : gridList) {
			//1. 불량상품 정보 수정
			deliveryOrderGoodDTO.setBadnReqestCont(deliveryClaimGoodResult.getLgsRdg().getBadnReqestCont());
			deliveryOrderGoodDTO.setRtrvlDrctGodNo(deliveryClaimGoodResult.getLgsRdg().getRtrvlDrctGodNo());
			deliveryOrderGoodDTO.setRtrvlDrctGrpDgre(deliveryClaimGoodResult.getLgsRdg().getRtrvlDrctGrpDgre().toString());
			deliveryOrderGoodDTO.setUdterId(userId);
			deliveryCommandRepository.updateFaultyGoodInfo(deliveryOrderGoodDTO);

			//2. 물류 회수지시 상품 이력 등록
			lgsRtrvlDrctGodHist.setRtrvlDrctGodNo(deliveryClaimGoodResult.getLgsRdg().getRtrvlDrctGodNo());
			deliveryCommandRepository.insertLgsRtrvlDrctGodHist(lgsRtrvlDrctGodHist);

			//3. 고객정보 수정
			ord.setOrdNo(deliveryClaimGoodResult.getOrd().getOrdNo());
			ord.setCstmrNm(deliveryClaimGoodResult.getOrd().getCstmrNm());
			if(StringService.isNotEmpty(deliveryClaimGoodResult.getCstmrMobilNo())) {
				ord.setCstmrMobilNationNo(makePhoneNumber(deliveryClaimGoodResult.getCstmrMobilNo())[0]);
				ord.setCstmrMobilAreaNo(makePhoneNumber(deliveryClaimGoodResult.getCstmrMobilNo())[1]);
				ord.setCstmrMobilTlofNo(makePhoneNumber(deliveryClaimGoodResult.getCstmrMobilNo())[2]);
				ord.setCstmrMobilTlofWthnNo(makePhoneNumber(deliveryClaimGoodResult.getCstmrMobilNo())[3]);
			}
			deliveryCommandRepository.updateCstmrInfo(ord);
		}
	}


	/**
	 * 주문의 출고건 확인 [주문 전체취소 체크시 사용].
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param infoList [설명]
	 * @return String [설명]
	 * @throws Exception the exception
	 * @since 2015. 4. 14
	 */
	public String checkDeliveryYnAboutOrder(List<DlvOrdGodInfoDTO> infoList) throws Exception {
		for(DlvOrdGodInfoDTO dlvOrdGodInfoDTO : infoList){
			if("Y".equals(deliverySelectRepository.checkDeliveryYnAboutOrder(dlvOrdGodInfoDTO))){//한 배송지라도 출고완료건이 있으면 Y로 리턴한다.
				return "Y";
			}
		}
		return "N";
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
	 * @since 2015. 7. 14
	 */
	public List<LgsDlivyDrctGodExtend> selectOrdDlivyDrct(DlvOrdGodInfoDTO dlvOrdGodInfoDTO) throws Exception {
		return deliverySelectRepository.selectOrdDlivyDrct(dlvOrdGodInfoDTO);

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
	 * @since 2015. 5. 17
	 */
	public String getDeliveryStatus(LgsDlivyDrctGod lgsDdg) throws Exception {
		return deliveryCommandRepository.getDeliveryStatus(lgsDdg);
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
		return deliveryCommandRepository.getWaybilNo(lgsDdg);
	}


	/**
	 * 출고 검수처리.
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param dto [설명]
	 * @return true:[설명], false:[설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 17
	 */
	public boolean updateInspectionInfo(DeliveryOrderGoodDTO dto) throws Exception {
		InfOrdGodErpDstb infOrdGodErpDstb = new InfOrdGodErpDstb();
		boolean success = false;
		String userId = BOSecurityUtil.getLoginId();	//세션ID

		infOrdGodErpDstb.setOrdNo(dto.getOrdNo());
		infOrdGodErpDstb.setOrdGodTurn(dto.getOrdGodTurn());
		infOrdGodErpDstb.setQtyTurn(dto.getQtyTurn());
		infOrdGodErpDstb.setDlivyAcptYn(dto.getDlivyAcptYn());
		infOrdGodErpDstb.setDlivyDrctGodNo(dto.getDlivyDrctGodNo());
		infOrdGodErpDstb.setErpGodSn(dto.getErpGodSn());

		//TAG Serail 미처리 또는 미등록 건
		if(StringService.isEmpty(dto.getErpGodSn())
				|| StringService.isEmpty(deliveryCommandRepository.selectErpGodSn(infOrdGodErpDstb))) {
			//검수정보 수정
			deliveryCommandRepository.updateInspectionInfo(infOrdGodErpDstb);
			
			//물류출고지시상품이력 등록
			LgsDlivyDrctGodHist lgsDlivyDrctGodHist = new LgsDlivyDrctGodHist();
			lgsDlivyDrctGodHist.setDlivyDrctGodNo(dto.getDlivyDrctGodNo());
			lgsDlivyDrctGodHist.setRegtrId("INSPECTION_"+dto.getSkuNo()+""+StringUtils.defaultString(dto.getErpGodSn(), ""));
			lgsDlivyDrctGodHist.setUdterId(userId+"_"+StringUtils.defaultString(dto.getMallId()));
			deliveryCommandRepository.insertLgsDlivyDrctGodHist(lgsDlivyDrctGodHist);

			success = true;
		}
		return success;
	}

	/**
	 * 검수취소
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param dto [설명]
	 * @return int
	 * @throws Exception the exception
	 * @since 2015. 5. 17
	 */
	public int updateInspectionReset(DeliveryOrderGoodDTO dto) throws Exception {
		return deliveryCommandRepository.updateInspectionReset(dto);
	}


	/**
	 * [단건] 운송장번호 생성.
	 *
	 * <p/>
	 *
	 *
	 * @return String [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 19
	 */
	public String selectWayBilSeq() throws Exception {
		//String wayBilSeq = getIdGenService().generateDBSequence(sqlSession1, "SQ_WAYBIL_NO", DatabaseType.ORACLE)+""; //한진택배 운송장 SEQ
		String wayBilSeq = getIdGenService().generateDBSequence(sqlSession1, "SQ_CJKEX_WAYBIL_NO", DatabaseType.ORACLE)+""; //CJ대한통운 운송장 SEQ
		/************************************************************/
		/* 택배사 변경 수정 영역 : 한진 -> CJ                       */
		/************************************************************/
		//CJ대한통운 운송장 채번
		String wayBilNo = wayBilSeq + ( Long.parseLong(wayBilSeq.substring(2)) % 7 );//운송장 번호
		return wayBilNo;
	}

    /**
     * [단건] 용차운송장번호 생성.
     *
     * <p/>
     *
     *
     * @return String [설명]
     * @throws Exception the exception
     * @since 2015. 5. 19
     */
    public String selectFrgnWayBilSeq() throws Exception {
        String frgnWayBilSeq = getIdGenService().generateDBSequence(sqlSession1, "SQ_TMALL_DLV", DatabaseType.ORACLE)+""; //운송장 SEQ
        DecimalFormat way = new DecimalFormat("000000000");
        String frgnWayBilNo =  "60" + way.format(Integer.valueOf(frgnWayBilSeq)) + (Long.parseLong(frgnWayBilSeq) % 7);
        return frgnWayBilNo;
    }

	/**
	 * 검수시 신규송장 적용대상 필터링.
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param gridList [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 20
	 */
	public List<DeliveryOrderGoodDTO> filterlingTarget(List<DeliveryOrderGoodResult> gridList) throws Exception {
		List<DeliveryOrderGoodResult> targetList = gridList;
		List<DeliveryOrderGoodDTO> retList = new ArrayList<DeliveryOrderGoodDTO>();
		List<DeliveryOrderGoodDTO> tempList = new ArrayList<DeliveryOrderGoodDTO>();

		//출고지시 상품별 선택 수량
		for (DeliveryOrderGoodResult result : targetList) {
			for (DeliveryOrderGoodResult result2 : gridList) {
				if(result.getOrd().getOrdNo().equals(result2.getOrd().getOrdNo())
						&& result.getOrdGod().getOrdGodTurn().equals(result2.getOrdGod().getOrdGodTurn())
						&& result.getLgsDsp().getDlvPcupspTurn().equals(result2.getLgsDsp().getDlvPcupspTurn())
						&& result.getLgsDlv().getDlvTurn().equals(result2.getLgsDlv().getDlvTurn())
						&& result.getLgsDdg().getDlivyDrctGodNo().equals(result2.getLgsDdg().getDlivyDrctGodNo())) {
					result.setNewDlivyDrctQty(result.getNewDlivyDrctQty()+1);	//분리 출고될 출고지시 수량.
				}
			}
		}

		//출고지시 상품별 중복제거
		for (DeliveryOrderGoodResult result : targetList) {
			DeliveryOrderGoodDTO dto = new DeliveryOrderGoodDTO();
			dto.setOrdNo(result.getOrd().getOrdNo());
			dto.setOrdGodTurn(result.getOrdGod().getOrdGodTurn());
			dto.setDlvPcupspTurn(result.getLgsDsp().getDlvPcupspTurn());
			dto.setDlvTurn(result.getLgsDlv().getDlvTurn());
			dto.setDlivyDrctGodNo(result.getLgsDdg().getDlivyDrctGodNo());
			dto.setNewDlivyDrctQty(result.getNewDlivyDrctQty());
			dto.setDlvMnCd(result.getLgsDlv().getDlvMnCd());
			tempList.add(dto);
		}

		HashSet<DeliveryOrderGoodDTO> hs = new HashSet<DeliveryOrderGoodDTO>(tempList);
		Iterator<DeliveryOrderGoodDTO> it = hs.iterator();
		while(it.hasNext()) {
			retList.add(it.next());
		}

		return retList;
	}
	
	
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
	 * @since 2015. 8. 3
	 */
	public List<DeliveryOrderGoodResult> selectSearchWaybillNoList(DeliverySearchDTO deliverySearchDTO) throws Exception {
		List<String> ordNoList = new ArrayList<String>();
		List<String> erpNoList = new ArrayList<String>();
		List<String> searchNoList = new ArrayList<String>();
		List<String> dlivyDrctGrpDgreList = new ArrayList<String>();
		
		//주문번호 다중검색
		if(StringService.isNotEmpty(deliverySearchDTO.getOrdNos())) {
			String[] ordNoArr = deliverySearchDTO.getOrdNos().split("\\r?\\n");
			for (String ordNo : ordNoArr) {
				ordNoList.add(ordNo.toUpperCase().trim());
            }
			deliverySearchDTO.setOrdNoList(ordNoList);
		}
		
		//erp품번 다중검색
		if(StringService.isNotEmpty(deliverySearchDTO.getErpNos())) {
			String[] erpNoArr = deliverySearchDTO.getErpNos().split("\\r?\\n");
			for (String erpNo : erpNoArr) {
				erpNoList.add(erpNo.toUpperCase().trim());
			}
			deliverySearchDTO.setErpNoList(erpNoList);
		}
		
		//다중검색
		if(StringService.isNotEmpty(deliverySearchDTO.getSearchNos())) {
			String[] searchNoArr = deliverySearchDTO.getSearchNos().split("\\r?\\n");
			for (String searchNo : searchNoArr) {
				searchNoList.add(searchNo.toUpperCase().trim());
			}
			deliverySearchDTO.setSearchNoList(searchNoList);
		}
		
		//차수 다중검색
		if(StringService.isNotEmpty(deliverySearchDTO.getDlivyDrctGrpDgres())) {
			String[] dlivyDrctGrpDgreArr = deliverySearchDTO.getDlivyDrctGrpDgres().split("\\,");
			for (String dlivyDrctGrpDgre : dlivyDrctGrpDgreArr) {
				dlivyDrctGrpDgreList.add(dlivyDrctGrpDgre.toUpperCase().trim());
			}
			deliverySearchDTO.setDlivyDrctGrpDgreList(dlivyDrctGrpDgreList);
		}
		
		return deliveryCommandRepository.selectSearchWaybillNoList(deliverySearchDTO);
	}
	
	
	/**
	 * 물류배송 분리대상 수량(박스분리 대삳 수량) 선별.
	 * 주문번호 / 배송수거지순번 / 배송순번 / 배송매장ID / 차수 / DAS 묶음.
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param gridList [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 8. 2
	 */
	public List<DeliveryOrderGoodDTO> filterDivWaybillInfo(List<DeliveryOrderGoodResult> gridList) throws Exception {
		List<DeliveryOrderGoodResult> targetList = gridList;
		List<DeliveryOrderGoodDTO> retList = new ArrayList<DeliveryOrderGoodDTO>();
		List<DeliveryOrderGoodDTO> tempList = new ArrayList<DeliveryOrderGoodDTO>();
		
		//물류배송 분리대상 수량(박스분리 대상 수량) 선별
		for (DeliveryOrderGoodResult result : targetList) {
			for (DeliveryOrderGoodResult result2 : gridList) {
				if(result.getOrd().getOrdNo().equals(result2.getOrd().getOrdNo())
						&& result.getLgsDsp().getDlvPcupspTurn().equals(result2.getLgsDsp().getDlvPcupspTurn())
						&& result.getLgsDlv().getDlvTurn().equals(result2.getLgsDlv().getDlvTurn())	
						&& result.getLgsDdg().getDlvShopId().equals(result2.getLgsDdg().getDlvShopId())) {
					
					result.setNewTargetCnt(result.getNewTargetCnt()+1); //물류배송 분리대상 수량(박스분리 대상 수량)
					result.setParams(StringService.nvl(result.getParams(), "")+result2.getLgsDdg().getDlivyDrctGodNo()+",");
				}
			}
		}
		
		//물류배송 분리대상 중복제거
		for (DeliveryOrderGoodResult result : targetList) {
			DeliveryOrderGoodDTO dto = new DeliveryOrderGoodDTO();
            dto.setMallId(result.getOrd().getMallId());
            dto.setLangCd(result.getOrd().getLangCd());
			dto.setOrdNo(result.getOrd().getOrdNo());
			dto.setDlvPcupspTurn(result.getLgsDsp().getDlvPcupspTurn());
			dto.setDlvTurn(result.getLgsDlv().getDlvTurn());
			dto.setDlvShopId(result.getLgsDdg().getDlvShopId());
			dto.setNewTargetCnt(result.getNewTargetCnt());
			dto.setParams(result.getParams());
			tempList.add(dto);
			
		}
		
		HashSet<DeliveryOrderGoodDTO> hs = new HashSet<DeliveryOrderGoodDTO>(tempList);
		Iterator<DeliveryOrderGoodDTO> it = hs.iterator();
		while(it.hasNext()) {
			retList.add(it.next());
		}
		
		return retList;
	}
	
	
	/**
	 * 회수완료 처리대상 선별.
	 * 주문번호 / 클레임번호 / 배송수거지순번 / 배송순번 / 송장번호 묶음.
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param gridList [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 8. 2
	 */
	public List<DeliveryOrderGoodDTO> filterRtrvlCompleteInfo(List<DeliveryClaimGoodResult> gridList) throws Exception {
		List<DeliveryClaimGoodResult> targetList = gridList;
		List<DeliveryOrderGoodDTO> retList = new ArrayList<DeliveryOrderGoodDTO>();
		List<DeliveryOrderGoodDTO> tempList = new ArrayList<DeliveryOrderGoodDTO>();
		
		for (DeliveryClaimGoodResult result : gridList) {
			for (DeliveryClaimGoodResult result2 : gridList) {
				if(result.getOrd().getOrdNo().equals(result2.getOrd().getOrdNo())
						&& result.getClm().getClmNo().equals(result2.getClm().getClmNo())
						&& result.getLgsDsp().getDlvPcupspTurn().equals(result2.getLgsDsp().getDlvPcupspTurn())
						&& result.getLgsDlv().getDlvTurn().equals(result2.getLgsDlv().getDlvTurn())
						&& result.getLgsDlv().getDlvComCd().equals(result2.getLgsDlv().getDlvComCd())
						&& result.getLgsDlv().getDmstcWaybilNo().equals(result2.getLgsDlv().getDmstcWaybilNo())) {
					
					result.setParams(StringService.nvl(result.getParams(), "")+result2.getLgsRdg().getRtrvlDrctGodNo()+",");
					result.setParams2(StringService.nvl(result.getParams2(), "")+result2.getLgsDdg().getDlivyDrctGodNo()+",");
				}
			}
		}
		
		//회수완료 처리대상 중복제거
		for (DeliveryClaimGoodResult result : targetList) {
			DeliveryOrderGoodDTO dto = new DeliveryOrderGoodDTO();
			dto.setOrdNo(result.getOrd().getOrdNo());
			dto.setClmNo(result.getClm().getClmNo());
			dto.setDlvPcupspTurn(result.getLgsDsp().getDlvPcupspTurn());
			dto.setDlvTurn(result.getLgsDlv().getDlvTurn());
			dto.setWaybilNo(result.getLgsDlv().getDmstcWaybilNo());
			dto.setDlvComCd(result.getLgsDlv().getDlvComCd());
			dto.setParams(result.getParams());
			dto.setParams2(result.getParams2());
			tempList.add(dto);
		}
		
		HashSet<DeliveryOrderGoodDTO> hs = new HashSet<DeliveryOrderGoodDTO>(tempList);
		Iterator<DeliveryOrderGoodDTO> it = hs.iterator();
		while(it.hasNext()) {
			retList.add(it.next());
		}
		
		return retList;
	}
	


	/**
	 * 신규 운송장정보 등록 처리.
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param list [설명]
	 * @param gridList [설명]
	 * @param wayBilNo [설명]
	 * @return void [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 21
	 */
	public void divideWayBilInfo(List<DeliveryOrderGoodDTO> list, List<DeliveryOrderGoodResult> gridList, String wayBilNo, String call_Id) throws Exception {

		/*
		 * 분리건수가 1건이라도 있으면 '물류배송' 신규 데이터 생성
		 * 원 출고지시 수량과 선택 수량이 다른 경우 - 물류출고지시상품 신규 데이터 생성
		 *                                    - 원 출고지시 수량 수정 (출고지시 수량 - 신규 생성 출고지시 수량)
		 * 원 출고지시 수량과 선택 수량이 동일한 경우 - 물류출고지시상품 데이터 수정 :: 배송순번
		 */
		String userId = BOSecurityUtil.getLoginId();	//세션ID

		//물류배송 단위 유효출고 건 수 조회
		DeliveryOrderGoodDTO ckeckDto = new DeliveryOrderGoodDTO();
		ckeckDto.setOrdNo(list.get(0).getOrdNo());
		ckeckDto.setDlvPcupspTurn(list.get(0).getDlvPcupspTurn());
		ckeckDto.setDlvTurn(list.get(0).getDlvTurn());
		int availDlvCnt = deliveryCommandRepository.checkAbleDlvDrctCnt(ckeckDto);

		//물류배송 출고수량 == 선택수량 :: 송장번호만 수정.
		if(availDlvCnt == gridList.size()) {
			LgsDlv lgsDlv = new LgsDlv();
			lgsDlv.setDmstcWaybilNo(wayBilNo);
			lgsDlv.setOrdNo(list.get(0).getOrdNo());
			lgsDlv.setDlvPcupspTurn(list.get(0).getDlvPcupspTurn());
			lgsDlv.setDlvTurn(list.get(0).getDlvTurn());
//			lgsDlv.setDlvComCd("HANJN");
			lgsDlv.setUdterId(userId);
			deliveryCommandRepository.updateRtrvlWaybilInfo(lgsDlv);

			//물류배송 이력 등록
			LgsDlvHist lgsDlvHist = new LgsDlvHist();
			lgsDlvHist.setOrdNo(list.get(0).getOrdNo());
			lgsDlvHist.setDlvPcupspTurn(list.get(0).getDlvPcupspTurn());
			lgsDlvHist.setDlvTurn(list.get(0).getDlvTurn());
			lgsDlvHist.setRegtrId("DIV_WAYBILL");
			lgsDlvHist.setUdterId(userId);
			deliveryCommandRepository.insertLgsDlvHist(lgsDlvHist);

			//물류배송 출고수량 > 선택수량 :: 물류배송 데이터 신규 생성
		} else if(availDlvCnt > gridList.size()) {
			int dlvTurn;	//배송순번

			//배송순번 채번
			Map<String, Object> conditions = Maps.newHashMap();
			conditions.put("ord_no", list.get(0).getOrdNo());
			conditions.put("dlv_pcupsp_turn", list.get(0).getDlvPcupspTurn());
			dlvTurn = getIdGenService().generateDBOrder(sqlSession1, "lgs_dlv", "dlv_turn", conditions,
					DatabaseType.ORACLE);

			//물류배송 데이터 등록
			DeliveryOrderGoodDTO paramDto = new DeliveryOrderGoodDTO();
			paramDto.setOrdNo(list.get(0).getOrdNo());
			paramDto.setDlvPcupspTurn(list.get(0).getDlvPcupspTurn());
			paramDto.setOrgDlvTurn(list.get(0).getDlvTurn());	//원 배송순번
			paramDto.setDlvTurn(dlvTurn);	//신규 배송순번
			paramDto.setDmstcWaybilNo(wayBilNo);
			// 픽업상품 매장준비완료에서 사용
			if(call_Id.equals("SHOP_PRPARE_COMPT")){
				paramDto.setDlvComCd("SHOP_PKUP");
			} else {
				/****************************************/
				/* 택배사 변경 수정 영역 : 한진 -> CJ   */
				/****************************************/

				// 기본배송정책 조회 written by Henry 20170208
				String mallId = orderSelectService.selectOrdMallId(list.get(0).getOrdNo());
				String dlvComCd = deliverySelectRepository.selectBaseDlvComCd(mallId);
				paramDto.setDlvComCd(dlvComCd);
			}
			paramDto.setRegtrId(userId);
			deliveryCommandRepository.insertLgsDlv(paramDto);

			//물류배송 이력 등록
			LgsDlvHist lgsDlvHist = new LgsDlvHist();
			lgsDlvHist.setOrdNo(list.get(0).getOrdNo());
			lgsDlvHist.setDlvPcupspTurn(list.get(0).getDlvPcupspTurn());
			lgsDlvHist.setDlvTurn(dlvTurn);
			lgsDlvHist.setRegtrId("DIV_WAYBILL");
			lgsDlvHist.setUdterId(userId);
			deliveryCommandRepository.insertLgsDlvHist(lgsDlvHist);


			DeliveryOrderGoodDTO deliveryOrderGoodDTO = new DeliveryOrderGoodDTO();
			LgsDlivyDrctGodHist lgsDlivyDrctGodHist = new LgsDlivyDrctGodHist();

			//물류출고지시상품 데이터 분리
			for (DeliveryOrderGoodDTO dto : list) {
				//출고지시수량 조회 (출고지시수량 - 출고지시취소수량)
				int dlvDrctQty = deliveryCommandRepository.selectDlivyDrctQty(dto.getDlivyDrctGodNo());

				//출고지시수량 == 선택수량 :: 물류출고지시상품 수정
				if(dlvDrctQty == dto.getNewDlivyDrctQty()) {
					//1. 물류출고지시상품 데이터 수정 :: 배송순번
					deliveryOrderGoodDTO.setDlivyDrctGodNo(dto.getDlivyDrctGodNo());
					deliveryOrderGoodDTO.setDlvTurn(dlvTurn);
					deliveryOrderGoodDTO.setDlivyDrctQty(0);
					deliveryOrderGoodDTO.setUdterId("DIV_WAYBILL");
					deliveryCommandRepository.updateLgsDdgInfo4DivWayBil(deliveryOrderGoodDTO);

					//2. 물류출고지시상품 이력 생성
					lgsDlivyDrctGodHist.setDlivyDrctGodNo(dto.getDlivyDrctGodNo());
					lgsDlivyDrctGodHist.setRegtrId("DIV_WAYBILL");
					lgsDlivyDrctGodHist.setUdterId(userId);
					deliveryCommandRepository.insertLgsDlivyDrctGodHist(lgsDlivyDrctGodHist);

					//출고지시수량 > 선택수량 :: 물류출고지시상품 신규 생성
				} else if(dlvDrctQty > dto.getNewDlivyDrctQty()) {
					//1. 출고지시상품번호 채번
					String dlivyDrctGodNo = getIdGenService().generateDBNumber(sqlSession1, "sq_lgs_dlivy_drct_god", "RO",
							DatabaseType.ORACLE);

					//2. 물류출고지시상품 신규레코드 생성 :: 신규 출고지시상품번호 리턴
					deliveryOrderGoodDTO.setOrgDlivyDrctGodNo(dto.getDlivyDrctGodNo());
					deliveryOrderGoodDTO.setDlivyDrctGodNo(dlivyDrctGodNo);
					deliveryOrderGoodDTO.setDlvTurn(dlvTurn);
					deliveryOrderGoodDTO.setDlivyDrctQty(dto.getNewDlivyDrctQty());	//분리 출고지시수량
					deliveryOrderGoodDTO.setRegtrId("DIV_WAYBILL");
					deliveryOrderGoodDTO.setUdterId(userId);
					deliveryCommandRepository.insertLgsDdg(deliveryOrderGoodDTO);

					//3. 물류출고지시상품 신규레코드 이력 생성
					lgsDlivyDrctGodHist.setDlivyDrctGodNo(dlivyDrctGodNo);
					lgsDlivyDrctGodHist.setRegtrId("DIV_WAYBILL");
					lgsDlivyDrctGodHist.setUdterId(userId);
					deliveryCommandRepository.insertLgsDlivyDrctGodHist(lgsDlivyDrctGodHist);

					//4. 원 물류출고지시상품 데이터 수정
					deliveryOrderGoodDTO.setDlivyDrctGodNo(dto.getDlivyDrctGodNo());
					deliveryOrderGoodDTO.setDlvTurn(dto.getDlvTurn());
					//출고지시수량(원 출고지시 수량 - 신규 출고지시 수량)
					deliveryOrderGoodDTO.setDlivyDrctQty(dlvDrctQty - dto.getNewDlivyDrctQty());
					deliveryOrderGoodDTO.setUdterId("DIV_WAYBILL");
					deliveryCommandRepository.updateLgsDdgInfo4DivWayBil(deliveryOrderGoodDTO);

					//5. 원 물류출고지시상품 이력 생성
					lgsDlivyDrctGodHist.setDlivyDrctGodNo(dto.getDlivyDrctGodNo());
					lgsDlivyDrctGodHist.setRegtrId("DIV_WAYBILL");
					lgsDlivyDrctGodHist.setUdterId(userId);
					deliveryCommandRepository.insertLgsDlivyDrctGodHist(lgsDlivyDrctGodHist);

					//6. 인터페이스주문상품ERP분배 수정 :: 신규 출고지시상품번호
					for (DeliveryOrderGoodResult result : gridList) {
						if(result.getOrd().getOrdNo().equals(dto.getOrdNo())
								&& result.getOrdGod().getOrdGodTurn().equals(dto.getOrdGodTurn())
								&& result.getLgsDdg().getDlivyDrctGodNo().equals(dto.getDlivyDrctGodNo())) {
							deliveryOrderGoodDTO.setOrdNo(dto.getOrdNo());
							deliveryOrderGoodDTO.setOrdGodTurn(dto.getOrdGodTurn());
							deliveryOrderGoodDTO.setDlivyDrctGodNo(dlivyDrctGodNo);
							deliveryOrderGoodDTO.setQtyTurn(result.getQtyTurn());
							deliveryCommandRepository.updateInfOgsdInfo4DivWayBil(deliveryOrderGoodDTO);
						}
					}

					//출고지시수량 < 선택수량 :: 부분취소등의 사유로 출고지시 수량 변동
				} else if(dlvDrctQty < dto.getNewDlivyDrctQty()) {
					DeliveryStatusException statusException = new DeliveryStatusException(null);
					statusException.setDirectMessage("상품의 출고지시 수량이 변경되었습니다. \n확인 후 처리하세요.");
					throw statusException;
				}
			}
		}
	}


	/**
     * 신규 용차운송장정보 등록 처리.
     *
     * <p/>
     *
     * [사용 방법 설명].
     *
     * @param list [설명]
     * @param gridList [설명]
     * @param wayBilNo [설명]
     * @return void [설명]
     * @throws Exception the exception
     * @since 2015. 5. 21
     */
    public void divideFrgnWayBilInfo(List<DeliveryOrderGoodDTO> list
            , List<DeliveryOrderGoodResult> gridList
            , String wayBilNo) throws Exception {

		/*
		 * 분리건수가 1건이라도 있으면 '물류배송' 신규 데이터 생성
		 * 원 출고지시 수량과 선택 수량이 다른 경우 - 물류출고지시상품 신규 데이터 생성
		 *                                    - 원 출고지시 수량 수정 (출고지시 수량 - 신규 생성 출고지시 수량)
		 * 원 출고지시 수량과 선택 수량이 동일한 경우 - 물류출고지시상품 데이터 수정 :: 배송순번
		 */
        String userId = BOSecurityUtil.getLoginId();	//세션ID

        //물류배송 단위 유효출고 건 수 조회
        DeliveryOrderGoodDTO ckeckDto = new DeliveryOrderGoodDTO();
        ckeckDto.setOrdNo(list.get(0).getOrdNo());
        ckeckDto.setDlvPcupspTurn(list.get(0).getDlvPcupspTurn());
        ckeckDto.setDlvTurn(list.get(0).getDlvTurn());
        int availDlvCnt = deliveryCommandRepository.checkAbleDlvDrctCnt(ckeckDto);

        //물류배송 출고수량 == 선택수량 :: 송장번호만 수정.
        if(availDlvCnt == gridList.size()) {
            LgsDlv lgsDlv = new LgsDlv();
            lgsDlv.setDmstcWaybilNo(wayBilNo);
            lgsDlv.setOrdNo(list.get(0).getOrdNo());
            lgsDlv.setDlvPcupspTurn(list.get(0).getDlvPcupspTurn());
            lgsDlv.setDlvTurn(list.get(0).getDlvTurn());
//			lgsDlv.setDlvComCd("HANJN");
            lgsDlv.setUdterId(userId);
            deliveryCommandRepository.updateRtrvlWaybilInfo(lgsDlv);

            //물류배송 이력 등록
            LgsDlvHist lgsDlvHist = new LgsDlvHist();
            lgsDlvHist.setOrdNo(list.get(0).getOrdNo());
            lgsDlvHist.setDlvPcupspTurn(list.get(0).getDlvPcupspTurn());
            lgsDlvHist.setDlvTurn(list.get(0).getDlvTurn());
            lgsDlvHist.setRegtrId("DIV_WAYBILL");
            lgsDlvHist.setUdterId(userId);
            deliveryCommandRepository.insertLgsDlvHist(lgsDlvHist);

            //물류배송 출고수량 > 선택수량 :: 물류배송 데이터 신규 생성
        } else if(availDlvCnt > gridList.size()) {
            int dlvTurn;	//배송순번

            //배송순번 채번
            Map<String, Object> conditions = Maps.newHashMap();
            conditions.put("ord_no", list.get(0).getOrdNo());
            conditions.put("dlv_pcupsp_turn", list.get(0).getDlvPcupspTurn());
            dlvTurn = getIdGenService().generateDBOrder(sqlSession1, "lgs_dlv", "dlv_turn", conditions,
                    DatabaseType.ORACLE);

            //물류배송 데이터 등록
            DeliveryOrderGoodDTO paramDto = new DeliveryOrderGoodDTO();
            paramDto.setOrdNo(list.get(0).getOrdNo());
            paramDto.setDlvPcupspTurn(list.get(0).getDlvPcupspTurn());
            paramDto.setOrgDlvTurn(list.get(0).getDlvTurn());	//원 배송순번
            paramDto.setDlvTurn(dlvTurn);	//신규 배송순번
            paramDto.setDmstcWaybilNo(wayBilNo);
            paramDto.setDlvComCd("PANTOS");
            paramDto.setRegtrId(userId);
            deliveryCommandRepository.insertLgsDlv(paramDto);

            //물류배송 이력 등록
            LgsDlvHist lgsDlvHist = new LgsDlvHist();
            lgsDlvHist.setOrdNo(list.get(0).getOrdNo());
            lgsDlvHist.setDlvPcupspTurn(list.get(0).getDlvPcupspTurn());
            lgsDlvHist.setDlvTurn(dlvTurn);
            lgsDlvHist.setRegtrId("DIV_WAYBILL");
            lgsDlvHist.setUdterId(userId);
            deliveryCommandRepository.insertLgsDlvHist(lgsDlvHist);


            DeliveryOrderGoodDTO deliveryOrderGoodDTO = new DeliveryOrderGoodDTO();
            LgsDlivyDrctGodHist lgsDlivyDrctGodHist = new LgsDlivyDrctGodHist();

            //물류출고지시상품 데이터 분리
            for (DeliveryOrderGoodDTO dto : list) {
                //출고지시수량 조회 (출고지시수량 - 출고지시취소수량)
                int dlvDrctQty = deliveryCommandRepository.selectDlivyDrctQty(dto.getDlivyDrctGodNo());

                //출고지시수량 == 선택수량 :: 물류출고지시상품 수정
                if(dlvDrctQty == dto.getNewDlivyDrctQty()) {
                    //1. 물류출고지시상품 데이터 수정 :: 배송순번
                    deliveryOrderGoodDTO.setDlivyDrctGodNo(dto.getDlivyDrctGodNo());
                    deliveryOrderGoodDTO.setDlvTurn(dlvTurn);
                    deliveryOrderGoodDTO.setDlivyDrctQty(0);
                    deliveryOrderGoodDTO.setUdterId("DIV_WAYBILL");
                    deliveryCommandRepository.updateLgsDdgInfo4DivWayBil(deliveryOrderGoodDTO);

                    //2. 물류출고지시상품 이력 생성
                    lgsDlivyDrctGodHist.setDlivyDrctGodNo(dto.getDlivyDrctGodNo());
                    lgsDlivyDrctGodHist.setRegtrId("DIV_WAYBILL");
                    lgsDlivyDrctGodHist.setUdterId(userId);
                    deliveryCommandRepository.insertLgsDlivyDrctGodHist(lgsDlivyDrctGodHist);

                    //출고지시수량 > 선택수량 :: 물류출고지시상품 신규 생성
                } else if(dlvDrctQty > dto.getNewDlivyDrctQty()) {
                    //1. 출고지시상품번호 채번
                    String dlivyDrctGodNo = getIdGenService().generateDBNumber(sqlSession1, "sq_lgs_dlivy_drct_god", "RO",
                            DatabaseType.ORACLE);

                    //2. 물류출고지시상품 신규레코드 생성 :: 신규 출고지시상품번호 리턴
                    deliveryOrderGoodDTO.setOrgDlivyDrctGodNo(dto.getDlivyDrctGodNo());
                    deliveryOrderGoodDTO.setDlivyDrctGodNo(dlivyDrctGodNo);
                    deliveryOrderGoodDTO.setDlvTurn(dlvTurn);
                    deliveryOrderGoodDTO.setDlivyDrctQty(dto.getNewDlivyDrctQty());	//분리 출고지시수량
                    deliveryOrderGoodDTO.setRegtrId("DIV_WAYBILL");
                    deliveryOrderGoodDTO.setUdterId(userId);
                    deliveryCommandRepository.insertLgsDdg(deliveryOrderGoodDTO);

                    //3. 물류출고지시상품 신규레코드 이력 생성
                    lgsDlivyDrctGodHist.setDlivyDrctGodNo(dlivyDrctGodNo);
                    lgsDlivyDrctGodHist.setRegtrId("DIV_WAYBILL");
                    lgsDlivyDrctGodHist.setUdterId(userId);
                    deliveryCommandRepository.insertLgsDlivyDrctGodHist(lgsDlivyDrctGodHist);

                    //4. 원 물류출고지시상품 데이터 수정
                    deliveryOrderGoodDTO.setDlivyDrctGodNo(dto.getDlivyDrctGodNo());
                    deliveryOrderGoodDTO.setDlvTurn(dto.getDlvTurn());
                    //출고지시수량(원 출고지시 수량 - 신규 출고지시 수량)
                    deliveryOrderGoodDTO.setDlivyDrctQty(dlvDrctQty - dto.getNewDlivyDrctQty());
                    deliveryOrderGoodDTO.setUdterId("DIV_WAYBILL");
                    deliveryCommandRepository.updateLgsDdgInfo4DivWayBil(deliveryOrderGoodDTO);

                    //5. 원 물류출고지시상품 이력 생성
                    lgsDlivyDrctGodHist.setDlivyDrctGodNo(dto.getDlivyDrctGodNo());
                    lgsDlivyDrctGodHist.setRegtrId("DIV_WAYBILL");
                    lgsDlivyDrctGodHist.setUdterId(userId);
                    deliveryCommandRepository.insertLgsDlivyDrctGodHist(lgsDlivyDrctGodHist);

                    //6. 인터페이스주문상품ERP분배 수정 :: 신규 출고지시상품번호
                    for (DeliveryOrderGoodResult result : gridList) {
                        if(result.getOrd().getOrdNo().equals(dto.getOrdNo())
                                && result.getOrdGod().getOrdGodTurn().equals(dto.getOrdGodTurn())
                                && result.getLgsDdg().getDlivyDrctGodNo().equals(dto.getDlivyDrctGodNo())) {
                            deliveryOrderGoodDTO.setOrdNo(dto.getOrdNo());
                            deliveryOrderGoodDTO.setOrdGodTurn(dto.getOrdGodTurn());
                            deliveryOrderGoodDTO.setDlivyDrctGodNo(dlivyDrctGodNo);
                            deliveryOrderGoodDTO.setQtyTurn(result.getQtyTurn());
                            deliveryCommandRepository.updateInfOgsdInfo4DivWayBil(deliveryOrderGoodDTO);
                        }
                    }

                    //출고지시수량 < 선택수량 :: 부분취소등의 사유로 출고지시 수량 변동
                } else if(dlvDrctQty < dto.getNewDlivyDrctQty()) {
                    DeliveryStatusException statusException = new DeliveryStatusException(null);
                    statusException.setDirectMessage("상품의 출고지시 수량이 변경되었습니다. \n확인 후 처리하세요.");
                    throw statusException;
                }
            }
        }
    }

	/**
	 * 배송상태 수정.
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param dto [설명]
	 * @return Int [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 22
	 */
	public int updateDeliveryStat(DeliveryInvoiceDTO dto) throws Exception {
		return deliveryCommandRepository.updateCompleteDelivery(dto);
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
		return deliveryCommandRepository.selectLgsDdgInfo(dto);
	}


	/**
	 * 물류배송지 이력 등록.
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param lgsDlivyDrctGodHist [설명]
	 * @return Int [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 23
	 */
	public int insertLgsDlivyDrctGodHist(LgsDlivyDrctGodHist lgsDlivyDrctGodHist) throws Exception {
		return deliveryCommandRepository.insertLgsDlivyDrctGodHist(lgsDlivyDrctGodHist);
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
		return deliveryCommandRepository.updateRtrvlInfo4DrctExch(lgsDlivyDrctGod);
	}


	/**
	 * [일반] 배송상태 수정.
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param obj [설명]
	 * @param newDlvStatCd [설명]
	 * @return Delivery order good dto [설명]
	 * @throws Exception the exception
	 * @since 2015. 6. 22
	 */
	public DeliveryOrderGoodDTO updateDeliveryStatus(DeliveryOrderGoodResult obj, String newDlvStatCd) throws Exception {
		DeliveryOrderGoodDTO retObj = new DeliveryOrderGoodDTO();
		String userId = BOSecurityUtil.getLoginId();	//세션ID
		
		//배송상태 체크
		retObj.setDlivyDrctGodNo(obj.getLgsDdg().getDlivyDrctGodNo());
		LgsDlivyDrctGod info = deliveryCommandRepository.selectLgsDdgInfo(retObj);
		
		if ("DLIVY_DRCT_CNCL".equals(info.getDlvStatCd())) {
			/*DeliveryStatusException statusException = new DeliveryStatusException(null);
			statusException.setDirectMessage("주문취소건이 존재합니다.\n확인 후 처리하세요.");
			throw statusException;*/
			throw new ClaimCompleteFailException("DeliveryException.cancel.order.check", null);

		} else if (!obj.getLgsDdg().getDlvStatCd().equals(info.getDlvStatCd())) {
			DeliveryStatusException statusException = new DeliveryStatusException(null);
			statusException.setDirectMessage("배송상태가 변경된 건이 존재합니다.\n확인 후 처리하세요.");
			throw statusException;
		}

		//출고완료
		if("DLIVY_COMPT".equals(newDlvStatCd)) {
			if(!"DLIVY_DRCT".equals(obj.getLgsDdg().getDlvStatCd())) {
				DeliveryStatusException statusException = new DeliveryStatusException(null);
				statusException.setDirectMessage("'출고지시' 상태인 경우만 '출고완료'로 변경이 가능합니다.\n배송상태를 확인하세요.");
				throw statusException;
			}
		//배송완료
		} else if("DLV_COMPT".equals(newDlvStatCd)) {
			if(!"DLIVY_COMPT".equals(obj.getLgsDdg().getDlvStatCd())) {
				DeliveryStatusException statusException = new DeliveryStatusException(null);
				statusException.setDirectMessage("'출고완료' 상태인 경우만 '배송완료'로 변경이 가능합니다.\n배송상태를 확인하세요.");
				throw statusException;
			}
		//배송대기
		} else if("DLV_WAIT".equals(newDlvStatCd)) {
			if(!"DLIVY_DRCT".equals(obj.getLgsDdg().getDlvStatCd())) {
				DeliveryStatusException statusException = new DeliveryStatusException(null);
				statusException.setDirectMessage("'출고지시' 상태인 경우만 '배송대기'로 변경이 가능합니다.\n배송상태를 확인하세요.");
				throw statusException;
			}
		}

		//배송상태 수정
		retObj.setDlvStatCd(newDlvStatCd);
		retObj.setDlivyDrctQty(obj.getLgsDdg().getDlivyDrctQty().intValue());
		retObj.setDlvShopId(obj.getLgsDdg().getDlvShopId());
		retObj.setUdterId(userId);
		deliveryCommandRepository.updateDeliveryStatus(retObj);
		
		//물류 출고지시 상품 이력 등록
		LgsDlivyDrctGodHist lgsDlivyDrctGodHist = new LgsDlivyDrctGodHist();
		lgsDlivyDrctGodHist.setRegtrId(userId);
		lgsDlivyDrctGodHist.setUdterId(userId);
		lgsDlivyDrctGodHist.setDlivyDrctGodNo(obj.getLgsDdg().getDlivyDrctGodNo());
		deliveryCommandRepository.insertLgsDlivyDrctGodHist(lgsDlivyDrctGodHist);
		
		retObj.setCount(1);
		retObj.setOrdNo(obj.getOrd().getOrdNo());
		retObj.setDlvPcupspTurn(obj.getLgsDsp().getDlvPcupspTurn());
		retObj.setDlvTurn(obj.getLgsDlv().getDlvTurn());
		retObj.setGftYn(obj.getLgsDdg().getGftYn());
		return retObj;
	}


	/**
	 * [패키지] 배송상태 수정.
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param list [설명]
	 * @param newDlvStatCd [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 6. 22
	 */
	public List<DeliveryOrderGoodDTO> updateDeliveryStatus4Package(List<DeliveryOrderGoodResult> list, String newDlvStatCd) throws Exception {
		String userId = BOSecurityUtil.getLoginId();	//세션ID
		List<DeliveryOrderGoodDTO> retList = new ArrayList<DeliveryOrderGoodDTO>();

		//패키지 묶음 단일 처리
		List<DeliverySearchDTO> newList = this.filterDuplPackageItem(list);

		for (DeliverySearchDTO param : newList) {
			param.setNewDlvStatCd(newDlvStatCd);
			//패키지 구성상품 조회 :: 구성품별 묶음처리
			List<DeliveryOrderGoodResult> packList = deliveryCommandRepository.selectDeliveryInfoPckage(param);

			for (DeliveryOrderGoodResult packObj : packList) {
				DeliveryOrderGoodDTO retObj = new DeliveryOrderGoodDTO();

				//배송상태 체크
				retObj.setDlivyDrctGodNo(packObj.getLgsDdg().getDlivyDrctGodNo());
				LgsDlivyDrctGod info = deliveryCommandRepository.selectLgsDdgInfo(retObj);
				if ("DLIVY_DRCT_CNCL".equals(info.getDlvStatCd())) {
					/*DeliveryStatusException statusException = new DeliveryStatusException(null);
					statusException.setDirectMessage("주문취소건이 존재합니다.\n확인 후 처리하세요.");
					throw statusException;*/
					throw new ClaimCompleteFailException("DeliveryException.cancel.order.check", null);
				} else if (!packObj.getLgsDdg().getDlvStatCd().equals(info.getDlvStatCd())) {
					DeliveryStatusException statusException = new DeliveryStatusException(null);
					statusException.setDirectMessage("배송상태가 변경된 건이 존재합니다.\n확인 후 처리하세요.");
					throw statusException;
				}

				//출고완료
				if("DLIVY_COMPT".equals(newDlvStatCd)) {
					if(!"DLIVY_DRCT".equals(packObj.getLgsDdg().getDlvStatCd())
							&& !("DLV_WAIT".equals(packObj.getLgsDdg().getDlvStatCd()) && !"B031".equals(packObj.getLgsDdg().getDlvShopId()))) {
						DeliveryStatusException statusException = new DeliveryStatusException(null);
						statusException.setDirectMessage("'출고지시' 상태인 경우만 '출고완료'로 변경이 가능합니다.\n배송상태를 확인하세요.");
						throw statusException;
					}

					//상품유형 :: 상품권 또는 사은품
//					if(!"GFCT".equals(packObj.getOrdGod().getGodTpCd())
//							&& !"Y".equals(packObj.getLgsDdg().getGftYn())) {
//						DeliveryStatusException statusException = new DeliveryStatusException(null);
//						statusException.setDirectMessage("출고완료 변경은 상품권 또는 사은품만 가능합니다.\n일반상품은 검수 화면에서 처리 가능합니다.");
//						throw statusException;
//					}

				} else if("DLV_COMPT".equals(newDlvStatCd)) {
					if(!"DLIVY_COMPT".equals(packObj.getLgsDdg().getDlvStatCd())) {
						DeliveryStatusException statusException = new DeliveryStatusException(null);
						statusException.setDirectMessage("'출고완료' 상태인 경우만 '배송완료'로 변경이 가능합니다.\n배송상태를 확인하세요.");
						throw statusException;
					}

				//배송대기
				} else if("DLV_WAIT".equals(newDlvStatCd)) {
					if(!"DLIVY_DRCT".equals(packObj.getLgsDdg().getDlvStatCd())) {
						DeliveryStatusException statusException = new DeliveryStatusException(null);
						statusException.setDirectMessage("'출고지시' 상태인 경우만 '배송대기'로 변경이 가능합니다.\n배송상태를 확인하세요.");
						throw statusException;
					}
				}

				//배송상태 수정
				retObj.setDlvStatCd(newDlvStatCd);
				retObj.setDlivyDrctQty(packObj.getLgsDdg().getDlivyDrctQty().intValue());
				retObj.setUdterId(userId);
				deliveryCommandRepository.updateDeliveryStatus(retObj);

				//물류 출고지시 상품 이력 등록
				LgsDlivyDrctGodHist lgsDlivyDrctGodHist = new LgsDlivyDrctGodHist();
				lgsDlivyDrctGodHist.setRegtrId(userId);
				lgsDlivyDrctGodHist.setUdterId(userId);
				lgsDlivyDrctGodHist.setDlivyDrctGodNo(packObj.getLgsDdg().getDlivyDrctGodNo());
				deliveryCommandRepository.insertLgsDlivyDrctGodHist(lgsDlivyDrctGodHist);

				retObj.setOrdNo(packObj.getOrd().getOrdNo());
				retObj.setDlvPcupspTurn(packObj.getLgsDsp().getDlvPcupspTurn());
				retObj.setDlvTurn(packObj.getLgsDlv().getDlvTurn());
				retObj.setGftYn(packObj.getLgsDdg().getGftYn());

				/*
				 * 1. 수정일자	: 2016-10-26
				 * 2. 수정자		: 김재성 (jskim27)
				 * 3. 요청 SR NO	: #26204
				 * 4. 수정내용	: 주문건의 적립 포인트 임시삭감 복원 로직 확인 요청
				 *					- '멤버쉽포인트 임시삭감 환원' 배치 수행이전에 '반품접수' 되는 경우를 대비한 방어 기능
				 */
				retObj.setMbrNo(packObj.getOrd().getMbrNo());

				retList.add(retObj);
			}
		}

		return retList;
	}


	/**
	 * [일반] 입점사 재출고 배송상태 수정.
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param obj [설명]
	 * @param newDlvStatCd [설명]
	 * @return Delivery order good dto [설명]
	 * @throws Exception the exception
	 * @since 2015. 11. 24
	 */
	public DeliveryOrderGoodDTO updateDeliveryDrctStatus(DeliveryOrderGoodResult obj, String newDlvStatCd) throws Exception {
		DeliveryOrderGoodDTO retObj = new DeliveryOrderGoodDTO();
		String userId = BOSecurityUtil.getLoginId();	//세션ID

		//배송상태 수정
		retObj.setDlivyDrctGodNo(obj.getLgsDdg().getDlivyDrctGodNo());
		retObj.setDlvStatCd(newDlvStatCd);
		retObj.setDlivyDrctQty(obj.getLgsDdg().getDlivyDrctQty().intValue());
		retObj.setUdterId(userId);
		deliveryCommandRepository.updateDeliveryStatus(retObj);

		//물류 출고지시 상품 이력 등록
		LgsDlivyDrctGodHist lgsDlivyDrctGodHist = new LgsDlivyDrctGodHist();
		lgsDlivyDrctGodHist.setRegtrId(userId);
		lgsDlivyDrctGodHist.setUdterId(userId);
		lgsDlivyDrctGodHist.setDlivyDrctGodNo(obj.getLgsDdg().getDlivyDrctGodNo());
		deliveryCommandRepository.insertLgsDlivyDrctGodHist(lgsDlivyDrctGodHist);

		retObj.setCount(1);
		retObj.setOrdNo(obj.getOrd().getOrdNo());
		retObj.setDlvPcupspTurn(obj.getLgsDsp().getDlvPcupspTurn());
		retObj.setDlvTurn(obj.getDlvTurn());
		return retObj;
	}


	/**
	 * [패키지] 입점사 재출고 배송상태 수정.
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param list [설명]
	 * @param newDlvStatCd [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 11. 24
	 */
	public List<DeliveryOrderGoodDTO> updateDeliveryDrctStatus4Package(List<DeliveryOrderGoodResult> list, String newDlvStatCd) throws Exception {
		String userId = BOSecurityUtil.getLoginId();	//세션ID
		List<DeliveryOrderGoodDTO> retList = new ArrayList<DeliveryOrderGoodDTO>();

		//패키지 묶음 단일 처리
		List<DeliverySearchDTO> newList = this.filterDuplPackageItem(list);

		for (DeliverySearchDTO param : newList) {
			param.setNewDlvStatCd(newDlvStatCd);
			//패키지 구성상품 조회 :: 구성품별 묶음처리
			List<DeliveryOrderGoodResult> packList = deliveryCommandRepository.selectDeliveryInfoPckage(param);

			for (DeliveryOrderGoodResult packObj : packList) {
				DeliveryOrderGoodDTO retObj = new DeliveryOrderGoodDTO();

				//배송상태 수정
				retObj.setDlivyDrctGodNo(packObj.getLgsDdg().getDlivyDrctGodNo());
				retObj.setDlvStatCd(newDlvStatCd);
				retObj.setDlivyDrctQty(packObj.getLgsDdg().getDlivyDrctQty().intValue());
				retObj.setUdterId(userId);
				deliveryCommandRepository.updateDeliveryStatus(retObj);

				//물류 출고지시 상품 이력 등록
				LgsDlivyDrctGodHist lgsDlivyDrctGodHist = new LgsDlivyDrctGodHist();
				lgsDlivyDrctGodHist.setRegtrId(userId);
				lgsDlivyDrctGodHist.setUdterId(userId);
				lgsDlivyDrctGodHist.setDlivyDrctGodNo(packObj.getLgsDdg().getDlivyDrctGodNo());
				deliveryCommandRepository.insertLgsDlivyDrctGodHist(lgsDlivyDrctGodHist);

				retObj.setOrdNo(packObj.getOrd().getOrdNo());
				retObj.setDlvPcupspTurn(packObj.getLgsDsp().getDlvPcupspTurn());
				retObj.setDlvTurn(packObj.getLgsDlv().getDlvTurn());
				retList.add(retObj);
			}
		}

		return retList;
	}


	//배송매장별 물류배송 정보 분리
	/**
	 * [메서드 설명].
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param obj [설명]
	 * @return Int [설명]
	 * @throws Exception the exception
	 * @since 2015. 7. 14
	 */
	public int divideDeliveryInfo(DeliveryOrderGoodResult obj) throws Exception {
		String userId = BOSecurityUtil.getLoginId();	//세션ID
		int dlvTurn = 0;	//배송순번

		//배송순번 채번
		Map<String, Object> conditions = Maps.newHashMap();
		conditions.put("ord_no", obj.getOrd().getOrdNo());
		conditions.put("dlv_pcupsp_turn", obj.getLgsDsp().getDlvPcupspTurn());
		dlvTurn = getIdGenService().generateDBOrder(sqlSession1, "lgs_dlv", "dlv_turn", conditions,
				DatabaseType.ORACLE);

		//물류배송 데이터 등록
		DeliveryOrderGoodDTO paramDto = new DeliveryOrderGoodDTO();
		paramDto.setOrdNo(obj.getOrd().getOrdNo());
		paramDto.setDlvPcupspTurn(obj.getLgsDsp().getDlvPcupspTurn());
		paramDto.setOrgDlvTurn(obj.getLgsDlv().getDlvTurn());	//원 배송순번
		paramDto.setDlvTurn(dlvTurn);	//신규 배송순번
		paramDto.setDmstcWaybilNo(obj.getLgsDlv().getDmstcWaybilNo());
		paramDto.setRegtrId(userId);
		deliveryCommandRepository.insertLgsDlv(paramDto);

		//물류배송 이력 등록
		LgsDlvHist lgsDlvHist = new LgsDlvHist();
		lgsDlvHist.setOrdNo(obj.getOrd().getOrdNo());
		lgsDlvHist.setDlvPcupspTurn(obj.getLgsDsp().getDlvPcupspTurn());
		lgsDlvHist.setDlvTurn(dlvTurn);
		lgsDlvHist.setRegtrId(userId);
		lgsDlvHist.setUdterId("DIV_WAYBILL");
		deliveryCommandRepository.insertLgsDlvHist(lgsDlvHist);

		//물류출고지시상품 데이터 수정 :: 배송순번
		DeliveryOrderGoodDTO deliveryOrderGoodDTO = new DeliveryOrderGoodDTO();
		deliveryOrderGoodDTO.setDlivyDrctGodNo(obj.getLgsDdg().getDlivyDrctGodNo());
		deliveryOrderGoodDTO.setDlvTurn(dlvTurn);
		deliveryOrderGoodDTO.setUdterId("DIV_WAYBILL");
		deliveryCommandRepository.updateLgsDdgInfo4DivWayBil(deliveryOrderGoodDTO);

		//물류출고지시상품 이력 생성
		LgsDlivyDrctGodHist lgsDlivyDrctGodHist = new LgsDlivyDrctGodHist();
		lgsDlivyDrctGodHist.setDlivyDrctGodNo(obj.getLgsDdg().getDlivyDrctGodNo());
		lgsDlivyDrctGodHist.setRegtrId(userId);
		lgsDlivyDrctGodHist.setUdterId("DIV_WAYBILL");
		deliveryCommandRepository.insertLgsDlivyDrctGodHist(lgsDlivyDrctGodHist);

		return dlvTurn;
	}
	
	/**
	 * 물류배송 분리정보 생성.
	 * @param obj [설명]
	 * @param waybilNo [설명]
	 * @return Int [설명]
	 * @throws Exception the exception
	 * @since 2015. 8. 3
	 */
	public int makeDivLgsDlvInfo(DeliveryOrderGoodResult obj, String waybilNo) throws Exception {
		String userId = BOSecurityUtil.getLoginId();	//세션ID
		int dlvTurn = 0;	//배송순번
		
		//배송순번 채번
		Map<String, Object> conditions = Maps.newHashMap();
		conditions.put("ord_no", obj.getOrd().getOrdNo());
		conditions.put("dlv_pcupsp_turn", obj.getLgsDsp().getDlvPcupspTurn());
		dlvTurn = getIdGenService().generateDBOrder(sqlSession1, "lgs_dlv", "dlv_turn", conditions,
				DatabaseType.ORACLE);

		//물류배송 데이터 등록
		DeliveryOrderGoodDTO paramDto = new DeliveryOrderGoodDTO();
		paramDto.setOrdNo(obj.getOrd().getOrdNo());
		paramDto.setDlvPcupspTurn(obj.getLgsDsp().getDlvPcupspTurn());
		paramDto.setOrgDlvTurn(obj.getLgsDlv().getDlvTurn());	//원 배송순번
		paramDto.setDlvTurn(dlvTurn);	//신규 배송순번

		// 픽업주문
		if(obj.getLgsDdg().getDlivyDrctTpCd().equals("SHOP_PKUP")){
			paramDto.setDlvComCd("SHOP_PKUP");
		}else{
			String mallId = orderSelectService.selectOrdMallId(obj.getOrd().getOrdNo());
        	String dlvComCd = deliverySelectRepository.selectBaseDlvComCd(mallId);
			paramDto.setDlvComCd(dlvComCd);
		}
		
		paramDto.setDmstcWaybilNo(waybilNo);
		paramDto.setRegtrId(userId);
		deliveryCommandRepository.insertLgsDlv(paramDto);

        //물류배송 이력 등록
		LgsDlvHist lgsDlvHist = new LgsDlvHist();
		lgsDlvHist.setOrdNo(obj.getOrd().getOrdNo());
		lgsDlvHist.setDlvPcupspTurn(obj.getLgsDsp().getDlvPcupspTurn());
		lgsDlvHist.setDlvTurn(dlvTurn);
		lgsDlvHist.setRegtrId(userId);
		lgsDlvHist.setUdterId("DIV_LGSDLV");
		deliveryCommandRepository.insertLgsDlvHist(lgsDlvHist);
		
		return dlvTurn;
	}
	
	/**
	 * 출고지시상품에 물류배송 분리정보 적용.
	 *
	 * @param obj [설명]
	 * @param dlvTurn [설명]
	 * @throws Exception the exception
	 */
	public void updateDivLgsDdgInfo(DeliveryOrderGoodResult obj, int dlvTurn) throws Exception {
		String userId = BOSecurityUtil.getLoginId();	//세션ID
		
		//물류출고지시상품 데이터 수정 :: 배송순번
		DeliveryOrderGoodDTO deliveryOrderGoodDTO = new DeliveryOrderGoodDTO();
		deliveryOrderGoodDTO.setDlivyDrctGodNo(obj.getLgsDdg().getDlivyDrctGodNo());
		deliveryOrderGoodDTO.setDlvTurn(dlvTurn);
		deliveryOrderGoodDTO.setDlvStatCd(obj.getLgsDdg().getDlvStatCd());
		deliveryOrderGoodDTO.setDlivyDrctDt(obj.getLgsDdg().getDlivyDrctDt());
		deliveryOrderGoodDTO.setDlivyDrctCnclStatCd(obj.getLgsDdg().getDlivyDrctCnclStatCd());
		deliveryOrderGoodDTO.setUdterId("DIV_LGSDLV");
		deliveryCommandRepository.updateLgsDdgInfo4DivWayBil(deliveryOrderGoodDTO);

		//물류출고지시상품 이력 생성
		LgsDlivyDrctGodHist lgsDlivyDrctGodHist = new LgsDlivyDrctGodHist();
		lgsDlivyDrctGodHist.setDlivyDrctGodNo(obj.getLgsDdg().getDlivyDrctGodNo());
		lgsDlivyDrctGodHist.setRegtrId(userId);
		lgsDlivyDrctGodHist.setUdterId("DIV_LGSDLV");
		deliveryCommandRepository.insertLgsDlivyDrctGodHist(lgsDlivyDrctGodHist);
	}

	/**
	 * 박스분리 대상 정보 조회.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dto [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 8. 2
	 */
	public List<DeliveryOrderGoodResult> selectDlvDivideTargetList(DeliverySearchDTO dto) throws Exception {
		return deliveryCommandRepository.selectDlvDivideTargetList(dto);
	}
	
	
	/**
	 * 패키지에 대한 구성품 정보 조회.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dto [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 8. 2
	 */
	public List<DeliveryOrderGoodResult> selectDeliveryInfoPckage(DeliverySearchDTO dto) throws Exception {
		return deliveryCommandRepository.selectDeliveryInfoPckage(dto);
	}

	/**
	 * 전화번호 파라미터 변환.
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param phoneNum [설명]
	 * @return String[] [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 13
	 */
	public String[] makePhoneNumber(String phoneNum) throws Exception {
		String[] totalArr = new String[4];
		totalArr[0] = "82";
		String[] tempNum = phoneNum.split("-");
		for(int i = 0 ; i < tempNum.length; i++){
			totalArr[i + 1] = tempNum[i];
		}
		return totalArr;
	}


	/**
	 * 패키지 묶음 중복건 단일 처리.
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param list [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 6. 10
	 */
	public List<DeliverySearchDTO> filterDuplPackageItem(List<DeliveryOrderGoodResult> list) throws Exception {
		List<DeliverySearchDTO> retList = new ArrayList<DeliverySearchDTO>();
		List<DeliverySearchDTO> tempList = new ArrayList<DeliverySearchDTO>();

		for (DeliveryOrderGoodResult obj : list) {
			DeliverySearchDTO dto = new DeliverySearchDTO();
			dto.setOrdNo(obj.getOrd().getOrdNo());
			dto.setPckageGodTurn(obj.getLgsDdg().getPckageGodTurn());
			tempList.add(dto);
		}

		HashSet<DeliverySearchDTO> hs = new HashSet<DeliverySearchDTO>(tempList);
		Iterator<DeliverySearchDTO> it = hs.iterator();
		while(it.hasNext()) {
			retList.add(it.next());
		}

		return retList;
	}
	
	
	/**
	 * 송장번호 묶음 중복건 단일 처리.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dto [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 6. 10
	 * 	public List<DeliverySearchDTO> filterDuplWaybillNo(List<DeliveryOrderGoodResult> list) throws Exception {
	 * 		List<DeliverySearchDTO> retList = new ArrayList<DeliverySearchDTO>();
	 * 		List<DeliverySearchDTO> tempList = new ArrayList<DeliverySearchDTO>();
	 * 
	 * 		for (DeliveryOrderGoodResult obj : list) {
	 * 			DeliverySearchDTO dto = new DeliverySearchDTO();
	 * 			dto.setOrdNo(obj.getOrd().getOrdNo());
	 * 			dto.setDlvPcupspTurn(obj.getLgsDsp().getDlvPcupspTurn()+"");
	 * 			dto.setDlvTurn(obj.getLgsDlv().getDlvTurn()+"");
	 * 			dto.setDlvShopId(obj.getLgsDdg().getDlvShopId());
	 * 			tempList.add(dto);
	 * 		}
	 * 
	 * 		HashSet<DeliverySearchDTO> hs = new HashSet<DeliverySearchDTO>(tempList);
	 * 		Iterator<DeliverySearchDTO> it = hs.iterator();
	 * 		while(it.hasNext()) {
	 * 			retList.add(it.next());
	 * 		}
	 * 
	 * 		return retList;
	 * 	}
	 */
	
	
	/**
	 *  미입고 TAG Serail 조회.
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param dto [설명]
	 * @return true:[설명], false:[설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 17
	 */
	public String selectErpGodSn(DeliveryOrderGoodDTO dto)  throws Exception {
		InfOrdGodErpDstb infOrdGodErpDstb = new InfOrdGodErpDstb();
		infOrdGodErpDstb.setOrdNo(dto.getOrdNo());
		infOrdGodErpDstb.setClmNo(dto.getClmNo());
		infOrdGodErpDstb.setDlivyDrctGodNo(dto.getDlivyDrctGodNo());
		infOrdGodErpDstb.setErpGodSn(dto.getErpGodSn());
		return deliveryCommandRepository.selectErpGodSn(infOrdGodErpDstb);
	}

	/**
	 * 입고 검수처리.
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param dto [설명]
	 * @return int
	 * @throws Exception the exception
	 * @since 2015. 5. 17
	 */
	public int updateRtrvlInspectionInfo(DeliveryOrderGoodDTO dto) throws Exception {
		//입고검수정보 수정
		InfOrdGodErpDstb infOrdGodErpDstb = new InfOrdGodErpDstb();
		infOrdGodErpDstb.setOrdNo(dto.getOrdNo());
		infOrdGodErpDstb.setOrdGodTurn(dto.getOrdGodTurn());
		infOrdGodErpDstb.setQtyTurn(dto.getQtyTurn());
		infOrdGodErpDstb.setWrhsAcptYn(dto.getWrhsAcptYn());
		infOrdGodErpDstb.setDlivyDrctGodNo(dto.getDlivyDrctGodNo());
		infOrdGodErpDstb.setErpGodSn(dto.getErpGodSn());
		return deliveryCommandRepository.updateRtrvlInspectionInfo(infOrdGodErpDstb);
	}
	
	/**
	 * 물류배송단위 출고대상건 카운트 조회
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dto [설명]
	 * @return Int [설명]
	 * @throws Exception the exception
	 * @since 2015. 8. 2
	 */
	public int selectAbleDlivyeryCnt(DeliveryOrderGoodDTO dto) throws Exception {
		return deliveryCommandRepository.selectAbleDlivyeryCnt(dto);
	}
	
	/**
	 * 매장픽업 준비완료 SMS 발송.
	 * 
	 * @param systemPK
	 * @param gridList
	 * @return
	 * @throws Exception
	 */
	public int sendPickupReadySms(SystemPK systemPK, List<DeliveryOrderGoodResult> gridList) throws Exception {
		int procCnt = 0;
		
		// 톡
		//[디스커버리 공식몰]
		//
		//#{성명} 고객님의 주문상품이 Discovery Expedition #{매장명}에 준비되었습니다.
		//매장 수령일과 매장 휴무일이 겹칠 수 있으니 매장에 미리 연락 후 매장 수령일을 확인하세요.
		//
		//- 주문번호: #{주문번호}
		//- 주문상품: #{상품명}외 #{건수}건
		//- 결제 금액: #{결제금액}원 
		//- 픽업매장: Discovery Expedition #{매장명}
		//- 픽업매장: Discovery Expedition #{매장전화번호}
		//- 픽업매장 영업시간 :  #{시:분~시:분}
		//- 픽업시간: #{년/월/일/시}까지 매장 미방문 시, 주문이 자동 취소 됩니다.
		//
		// 매장지도 보기
		//#{URL}
		//
		//고객님 주문해 주셔서 감사드리며, 언제나 고객만족을 위해 노력하는 디스커버리가 되겠습니다.
		//고객센터(080-820-8802)
		
		// 문자
		//[디스커버리] 
		//
		//#{성명}님의 주문상품이 Discovery Expedition #{매장명}에 준비되었습니다.
		//매장 수령일과 매장 휴무일이 겹칠 수 있으니 매장에 미리 연락 후 매장 수령일을 확인하세요.
		//
		//- 픽업매장: Discovery Expedition #{매장명}}
		//- 픽업매장: Discovery Expedition #{매장전화번호}
		//- 픽업시간: #{년/월/일/시}까지 매장 미방문 시, 주문이 자동 취소 됩니다.
		//- 매장지도 보기 #{URL}
		
		//1. SMS발송대상 중복 제거
		List<DeliverySearchDTO> targetList = filterPickupSmsTarget(gridList);
		
		for (DeliverySearchDTO deliverySearchDTO : targetList) {
			//2. SMS 발송 정보 조회
			DeliveryOrderGoodDTO smsInfo = deliverySelectRepository.selectPickupReadySms(deliverySearchDTO);
	        
			if(smsInfo != null) {
				String mallId = orderSelectService.selectOrdMallId(smsInfo.getOrdNo());
				
				log.debug("sendPickupReadySms SMS EMAIL order {}",smsInfo.toString());
				log.debug("mall id : " + mallId);
				//알림톡 OR SMS
		        AdapterHeader adapterHeader = new AdapterHeader();
		        adapterHeader.setRequestId(this.interfaceApiCommon.getRequestId());
		        //mallId
		        adapterHeader.setMallId(mallId);
		        adapterHeader.setLangCd("KOR");
		        adapterHeader.setDvcCd("PC");
//		        https://dev-m.discovery-expedition.com/common/shop/view.page?shopId=I113
		        String url = getConfigService().getProperty("ncp.url.mb_DX.root.secure")+"/common/shop/view.page?shopId="+smsInfo.getDlvShopId();
		        if("MBM".equals(mallId)) {
		        	url = getConfigService().getProperty("ncp.url.mb_MLB.root.secure")+"/common/shop/view.page?shopId="+smsInfo.getDlvShopId();
				} else if("SAM".equals(mallId)) {
					url = getConfigService().getProperty("ncp.url.mb_SA.root.secure")+"/common/shop/view.page?shopId="+smsInfo.getDlvShopId();
				}

		        ArrayList<String> params = new ArrayList<>();
		        
		        if("MBM".equals(mallId)) {
		        	params.add(0, getConfigService().getProperty("ncp_base.talk.mlb.shop.name"));
		        	params.add(1, smsInfo.getAddrseNm());
		        	params.add(2, smsInfo.getShopNm());
		        	params.add(3, smsInfo.getOrdNo());
		        	params.add(4, smsInfo.getGodNm());
		        	params.add(5, smsInfo.getTotalAmt());
		        	params.add(6, smsInfo.getShopNm());
		        	params.add(7, smsInfo.getShopTel());
		        	params.add(8, smsInfo.getShopTime());
		        	params.add(9, smsInfo.getPickDate());
		        	params.add(10, url);
		        } else if("SAM".equals(mallId)) {
		        	params.add(0, getConfigService().getProperty("ncp_base.talk.sa.shop.name"));
		        	params.add(1, smsInfo.getAddrseNm());
		        	params.add(2, smsInfo.getShopNm());
		        	params.add(3, smsInfo.getOrdNo());
		        	params.add(4, smsInfo.getGodNm());
		        	params.add(5, smsInfo.getTotalAmt());
		        	params.add(6, smsInfo.getShopNm());
		        	params.add(7, smsInfo.getShopTel());
		        	params.add(8, smsInfo.getShopTime());
		        	params.add(9, smsInfo.getPickDate());
		        	params.add(10, url);
		        } else {
		        	params.add(0, smsInfo.getShopNm());
			        params.add(1, smsInfo.getOrdNo());
			        params.add(2, smsInfo.getGodNm());
			        params.add(3, smsInfo.getTotalAmt());
			        params.add(4, smsInfo.getAddrseNm());
			        params.add(5, url);
			        params.add(6, smsInfo.getShopTel());
			        params.add(7, smsInfo.getShopTime());
			        params.add(8, smsInfo.getPickDate());
		        }
		        

				if (StringService.isNotEmpty(smsInfo.getAddrseMobileNo())) {
					MPushAlimTalkSDO mPushAlimTalkSDO = new MPushAlimTalkSDO();
					mPushAlimTalkSDO.setMbrNo(smsInfo.getMbrNo() == null ? "NMBR" : smsInfo.getMbrNo() );
					mPushAlimTalkSDO.setReceiveMobileNo( smsInfo.getAddrseMobileNo());
					//mallId
					mPushAlimTalkSDO.setMallId(mallId);
					mPushAlimTalkSDO.setCallerId(this.getClass().getName() + ".sendPickupReadySms");
					
					//mPushAlimTalkSDO.setMsgKey("DXM_LGS_DLIVY_03");
					if("MBM".equals(mallId)) {
						mPushAlimTalkSDO.setSendDirectFlag(true);
						
						mPushAlimTalkSDO.setMsgKey("MBM_LGS_DLIVY_03");
					} else if("SAM".equals(mallId)) {
						mPushAlimTalkSDO.setSendDirectFlag(true);
						
						mPushAlimTalkSDO.setMsgKey("SAM_LGS_DLIVY_03");
					} else {
						mPushAlimTalkSDO.setMsgKey("DXM_LGS_DLIVY_03");
					}
					
					mPushAlimTalkSDO.setParams(params);
					try{
						mPushAdapter.sendAlimTalk(mPushAlimTalkSDO, adapterHeader);
					}catch(Exception e){
						log.error(e.getMessage(),e);
					}
				}
				
		        AdapterHeader adapterHeaderEmail = new AdapterHeader();
		        adapterHeaderEmail.setRequestId(this.interfaceApiCommon.getRequestId());
		        //mallId
		        adapterHeaderEmail.setMallId(mallId);
		        adapterHeaderEmail.setLangCd("KOR");
		        adapterHeaderEmail.setDvcCd("PC");
		        
		        String mallBodyUrl = getConfigService().getProperty("ncp.url.pc_DX.root.secure");
		        String mallEmail = "DISCOVERY <discovery@fnf.co.kr>";
		        String mallSubject = "[Discovery Expedition] ";
		        if("MBM".equals(mallId)) {
		        	mallBodyUrl = getConfigService().getProperty("ncp.url.pc_MLB.root.secure");
		        	mallEmail = getConfigService().getProperty("ncp_base.mlb.mall.email.address");
		        	mallSubject = getConfigService().getProperty("ncp_base.mlb.mall.email.subject") + " ";
		        } else if("SAM".equals(mallId)) {
		        	mallBodyUrl = getConfigService().getProperty("ncp.url.pc_SA.root.secure");
		        	mallEmail = getConfigService().getProperty("ncp_base.sa.mall.email.address");
		        	mallSubject = getConfigService().getProperty("ncp_base.sa.mall.email.subject") + " ";
		        }
		        log.info("sendPickupReadySms mail send url {}",mallBodyUrl+"/email/order/"+smsInfo.getOrdNo()+"/shopPrpareCompt");
		        
				String mailbody = mailHtmlReaderService.readHTML(mallBodyUrl+"/email/order/"+smsInfo.getOrdNo()+"/shopPrpareCompt", "");
				
				EmailHtmlSDO emailHtmlSDO = new EmailHtmlSDO();
		        emailHtmlSDO.setCallerId(getClass().getName() + ".modifyDlivyEmailProc");
		        emailHtmlSDO.setMallEmail(mallEmail);
		        emailHtmlSDO.setMbrEmail(smsInfo.getCstmrEmail());
		        emailHtmlSDO.setSubject(mallSubject+smsInfo.getCstmrNm()+" 고객님의 주문상품 발송 안내입니다");
		        emailHtmlSDO.setHtmlBody(mailbody);
		        try{
		        	if(!"MBM".equals(mallId) && !"SAM".equals(mallId)) {
		        		log.info(emailAdapter.sendHtmlEmail(emailHtmlSDO, adapterHeader));
		        	}			        
					
					// SMS 발송여부 Update
					deliveryCommandRepository.updatePickupReadySmsYn(deliverySearchDTO);
		        }catch(Exception e){
		        	log.error(e.getMessage(),e);
		        }
				procCnt++;
			}
        }
		return procCnt;
	}	
	
	/**
	 * [물류센터전용] 물류결품처리
	 * <p/>
	 * 물류에서 재고 이동을 위한 처리시 사용.
	 *
	 * @param result [설명]
	 * @throws Exception the exception
	 * @since 2015. 10. 27
	 */
	public void updateDstrbtCenterShortage(DeliveryOrderGoodResult obj) throws Exception {
		String userId = BOSecurityUtil.getLoginId();	//세션ID
		
		//배정대상 출고정보 조회
		LgsDlivyDrctGod param = new LgsDlivyDrctGod();
		param.setOrdNo(obj.getOrd().getOrdNo());
		param.setDlivyDrctGodNo(obj.getLgsDdg().getDlivyDrctGodNo());
		DeliveryOrderGoodResult result = deliveryCommandRepository.selectAssignTargetList(param).get(0);

		//(임시매장이 아닌)배송대기/출고지시/결품접수 외 상태는 재배정처리 불가. 
		String chckStat = result.getLgsDdg().getDlvStatCd();
		if(!"DLIVY_DRCT".equals(chckStat) || (!DeliveryEnum.DLV_ONLINE_SHOP_DISCOVERY.toString().equals(result.getLgsDdg().getDlvShopId()))
				|| (!DeliveryEnum.DLV_ONLINE_SHOP_MLB.toString().equals(result.getLgsDdg().getDlvShopId()))
				|| (!DeliveryEnum.DLV_ONLINE_SHOP_MLB_KIDZ.toString().equals(result.getLgsDdg().getDlvShopId()))
				|| (!DeliveryEnum.DLV_ONLINE_SHOP_SA.toString().equals(result.getLgsDdg().getDlvShopId()))) {
			DeliveryStatusException statusException = new DeliveryStatusException(null);
			statusException.setDirectMessage("결품처리 대상이 아닌 건이 존재합니다.\n배송상태를 확인 후 처리하세요.");
			throw statusException;
		}
		
		//기존 품절처리된 상품중 ERP출고취소가 미처리된 상품은 처리 안함
		if("Y".equals(result.getLgsDdg().getDlivyDrctYn())) {
			DeliveryStatusException statusException = new DeliveryStatusException(null);
			statusException.setDirectMessage("ERP출고취소가 되지 않은 주문건이 존재합니다.\n확인 후 처리하세요.");
			throw statusException;
			
		} else {
			//ERP 인터페이스 출고지시대상 정보 수정
			InfOrdGodErpDstb infOrdGodErpDstb = new InfOrdGodErpDstb();
			infOrdGodErpDstb.setOrdNo(obj.getOrd().getOrdNo());
			infOrdGodErpDstb.setOrdGodTurn(obj.getOrdGod().getOrdGodTurn());
			infOrdGodErpDstb.setDlivyDrctGodNo(obj.getLgsDdg().getDlivyDrctGodNo());
			infOrdGodErpDstb.setDlivyDrctTgtYn("N");
			infOrdGodErpDstb.setDlivyDrctYn("N");
			deliveryCommandRepository.updateInfDeliveryDirectTarget(infOrdGodErpDstb);
			
			//배정정보 수정
			LgsDlivyDrctGod lgsDdg = new LgsDlivyDrctGod();
			lgsDdg.setDlivyDrctGodNo(obj.getLgsDdg().getDlivyDrctGodNo());
			lgsDdg.setDlivyDrctQty(obj.getLgsDdg().getDlivyDrctQty());
			lgsDdg.setDlvShopId("B031");					//배송매장
			lgsDdg.setDlvStatCd("SHTG_RCEPT");			//배송상태
			lgsDdg.setLgsItmYn("Y");							//단품여부
			lgsDdg.setDlivyDrctTgtYn("N");					//출고지시대상여부
			lgsDdg.setUdterId(userId);
			deliveryCommandRepository.updateAssignShopInfo(lgsDdg);
			
			if( DeliveryEnum.DLV_ONLINE_SHOP_DISCOVERY.toString().equals(result.getLgsDdg().getDlvShopId()) 
					|| DeliveryEnum.DLV_ONLINE_SHOP_MLB.toString().equals(result.getLgsDdg().getDlvShopId())
					|| DeliveryEnum.DLV_ONLINE_SHOP_MLB_KIDZ.toString().equals(result.getLgsDdg().getDlvShopId())
					|| DeliveryEnum.DLV_ONLINE_SHOP_SA.toString().equals(result.getLgsDdg().getDlvShopId()))
			{
				//물류 출고지시 상품 이력 등록
				LgsDlivyDrctGodHist lgsDlivyDrctGodHist = new LgsDlivyDrctGodHist();
				lgsDlivyDrctGodHist.setRegtrId("DSTRBT_CENTER_SHORTAGE");
				lgsDlivyDrctGodHist.setUdterId(userId);
				lgsDlivyDrctGodHist.setDlivyDrctGodNo(obj.getLgsDdg().getDlivyDrctGodNo());
				deliveryCommandRepository.insertLgsDlivyDrctGodHist(lgsDlivyDrctGodHist);
			} 
		}
	}
	
	
	/**
	 * 출고검수여부 수정
	 * @param DeliveryOrderGoodDTO
	 * @return int
	 * @throws Exception the exception
	 * @since 2016.7.13
	 */
	public int updateAcptYn(DeliveryOrderGoodDTO deliveryOrderGoodDTO) throws Exception {
		return deliveryCommandRepository.updateAcptYn(deliveryOrderGoodDTO);
	}




	/**
	 * [메서드 설명].
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param systemPK [설명]
	 * @return Adapter header [설명]
	 * @since 2015. 10. 27
	 */
	private AdapterHeader setBOAdapterHeader(SystemPK systemPK){
		AdapterHeader adapterHeader = new AdapterHeader();
		adapterHeader.setRequestId(this.interfaceApiCommon.getRequestId());
		adapterHeader.setMallId(systemPK.getMall());
		adapterHeader.setDvcCd(systemPK.getDevice());
		//mallId
		//adapterHeader.setMallId("DXM");
		adapterHeader.setLangCd("KOR");
		adapterHeader.setDvcCd("PC");
		
		return adapterHeader;
    }
	
	
	/**
	 * 매장픽업 SMS 발송 대상 분류.
	 *
	 * @param gridList [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 8. 29
	 */
	private List<DeliverySearchDTO> filterPickupSmsTarget(List<DeliveryOrderGoodResult> gridList) throws Exception {
		List<DeliveryOrderGoodResult> targetList = gridList;
		List<DeliverySearchDTO> retList = new ArrayList<DeliverySearchDTO>();
		List<DeliverySearchDTO> tempList = new ArrayList<DeliverySearchDTO>();
		
		//회수완료 처리대상 중복제거
		for (DeliveryOrderGoodResult result : targetList) {
			DeliverySearchDTO dto = new DeliverySearchDTO();
			dto.setOrdNo(result.getOrd().getOrdNo());
			tempList.add(dto);
		}
		
		HashSet<DeliverySearchDTO> hs = new HashSet<DeliverySearchDTO>(tempList);
		Iterator<DeliverySearchDTO> it = hs.iterator();
		while(it.hasNext()) {
			retList.add(it.next());
		}
		
		return retList;
	}

	/**
	 * '물류출고지시상품' 데이터 전체가 '배송완료', '출고지시취소' 인 경우에
	 * 	해당 '주문'(ORD테이블)에 대해서 '배송완료' 로 상태 수정
	 *
	 * @param obj [설명]
	 * @param dlvTurn [설명]
	 * @throws Exception the exception
	 * @since 2015. 8. 3
	 */
	public void modifyOrdDlvCompt( String ordNo ) throws Exception {
		
		String userId="";
		boolean isBoSite=false;
		
		try {
			
			HttpServletRequest req = ContextService.getCurrentRequest();
			
			if (req != null) {
		        String host = req.getHeader("host");
		        
		        if(host.indexOf("withus.plgrimshop") > -1 || host.indexOf("bo.plgrimshop") >-1 || host.indexOf("po.plgrimshop") > -1  ) {
		            isBoSite = true;
		        }
		        if(isBoSite) {        
		           userId = BOSecurityUtil.getLoginId();    //세션ID
		        }
			}
		} catch (Exception e) {
			
		}

        DeliveryOrderGoodDTO deliveryOrderGoodDTO = new DeliveryOrderGoodDTO();
        deliveryOrderGoodDTO.setOrdNo( ordNo );
        deliveryOrderGoodDTO.setUdterId( userId );

        deliveryCommandRepository.modifyOrdDlvCompt(deliveryOrderGoodDTO);
	}

	public void modifyRepairOrdDlvCompt( String ordNo ) throws Exception {
        DeliveryOrderGoodDTO deliveryOrderGoodDTO = new DeliveryOrderGoodDTO();
        deliveryOrderGoodDTO.setOrdNo( ordNo );
        deliveryOrderGoodDTO.setUdterId( "REPAIR_BATCH" );

        deliveryCommandRepository.modifyOrdDlvCompt(deliveryOrderGoodDTO);
	}

	/**
	 * ERP 인터페이스 시리얼정보 수정.
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param systemPk [설명]
	 * @param gridList [설명]
	 * @return true:[설명], false:[설명]
	 * @throws Exception the exception
	 * @since 2016. 3.17
	 */
	public void updateErpGodSnInfo(SystemPK systemPk, List<DeliveryOrderGoodResult> gridList) throws Exception {
		InfOrdGodErpDstb infOrdGodErpDstb = new InfOrdGodErpDstb();
		
		for (DeliveryOrderGoodResult obj : gridList) {
			//ERP 인터페이스 시리얼 정보 수정
			infOrdGodErpDstb.setOrdNo(obj.getOrd().getOrdNo());
			infOrdGodErpDstb.setOrdGodTurn(obj.getOrdGod().getOrdGodTurn());
			infOrdGodErpDstb.setQtyTurn(obj.getQtyTurn());
			infOrdGodErpDstb.setErpGodSn(obj.getErpGodSn());
			deliveryCommandRepository.updateErpGodSnInfo(infOrdGodErpDstb);
		}
		
	}
	
	/**
	 * 픽업주문 바코드 생성
	 * @param data
	 */
	private String genPickupBarcode(String data){
		FileOutputStream out = null;
		String barcodeFullPath = "";
		
		if(data == null || data.length() <= 0) return barcodeFullPath;
		
		try{
			Class<?> clazz = new DefaultBarcodeClassResolver().resolveBean("code128");
			AbstractBarcodeBean barcodeBean = (AbstractBarcodeBean)clazz.newInstance();
			
			File pickupBarcodeDir = new File(pickupBarcodePath
												+ BODateUtil.getDefaultYear()
												+ BODateUtil.getDefaultMonth());

			if(!pickupBarcodeDir.isDirectory()){
				pickupBarcodeDir.mkdirs();
			}
			
			barcodeFullPath = pickupBarcodeDir.getPath() + File.separator + data + ".jpg";
			
			out = new FileOutputStream(barcodeFullPath);
			
			BitmapCanvasProvider canvas = new BitmapCanvasProvider(
											out, 
											MimeTypes.expandFormat("jpg"), 
											150, 
											BufferedImage.TYPE_BYTE_BINARY, 
											false, 
											0);
			
			barcodeBean.doQuietZone(true);
			barcodeBean.setBarHeight(10); 
			barcodeBean.setModuleWidth(0.25);
			barcodeBean.setFontSize(2);
			barcodeBean.generateBarcode(canvas, data);

			canvas.finish();
			
		}catch(Exception e){
		}finally{if(out !=null) try{ out.close(); }catch(IOException io){}}
		
		return barcodeFullPath;
	}
	
	
	/**
     * 거절 전 현재상태 조회
     * @param DeliveryOrderGoodDTO
     * @return int
     */
    public int getChkStatus(DeliveryOrderGoodDTO deliveryOrderGoodDTO) throws Exception {
        return deliveryCommandRepository.getChkStatus(deliveryOrderGoodDTO);
    }

	/**
	 * 매장픽업 픽업완료 SMS 발송.
	 *
	 * @param systemPK
	 * @param gridList
	 * @return
	 * @throws Exception
	 */
	public void sendPickupCompleteSms(SystemPK systemPK, List<DeliveryOrderGoodDTO> gridList) throws Exception {
		// SMS개선 by cannon : 2016.07.17

		DeliveryOrderGoodDTO smsInfo = deliverySelectRepository.selectPickupCompeteSms(gridList);

//		if (smsInfo != null) {
//			List<HumusonSDO> humusonSDOList = new ArrayList<>();
//
//			String ordNo = smsInfo.getOrdNo();
//			String godNm = '[' + smsInfo.getBrndNm() + ']' + smsInfo.getGodNm();
//			String tranId = StringService.isNotEmpty(smsInfo.getMbrNo()) ? smsInfo.getMbrNo() : ordNo;
//			String mallId = smsInfo.getMallId();
//			String mobilNo = StringService.nvl(smsInfo.getCstmrMobilNo(), "");
//
//			if ("ADDRSE".equals(smsInfo.getSmsRecptnSectCd())) {
//				mobilNo = StringService.nvl(smsInfo.getAddrseMobileNo(), "");
//			}
//
//			ArrayList<String> params = new ArrayList<>();
//			params.add(0, ordNo);
//			params.add(1, godNm);
//
//			String msgKey = "DXM_LGS_PKUP_02";
//			String callerId = DeliveryStatusService.class.getName() + ".sendPickupCompleteSms";
//
//			//3. SMS 발송 요청
////			try {
////				HumusonSDO humusonSDO = humusonCommonService.getAlimTalkData(tranId, mobilNo, mallId, callerId, msgKey, params);
////				humusonSDOList.add(humusonSDO);
////
////				if ("ORDMAN_ADDRSE".equals(smsInfo.getSmsRecptnSectCd())) {
////					if (!StringService.equalsIgnoreCase(smsInfo.getCstmrMobilNo(), smsInfo.getAddrseMobileNo())) {
////						HumusonSDO parameter2 = new HumusonSDO();
////						BeanUtils.copyProperties(humusonSDO, parameter2);
////						parameter2.setPhoneNumber(smsInfo.getAddrseMobileNo());
////
////						humusonSDOList.add(parameter2);
////					}
////				}
////				AdapterHeader adapterHeader = setBOAdapterHeader(systemPK);
////
////				humusonCommonService.sendAlimTalk(humusonSDOList, adapterHeader);
////			} catch (Exception e) {
////				if (log.isInfoEnabled()) {
////					log.info("> sendPickupCompleteSms SMS Exception : ", e);
////				}
////			}
//		}
	}


	/**
	 * CJ대한통운 주소정제 적용 단위 필터링.
	 * [사용 방법 설명].
	 *
	 * @param list [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2017. 2. 1
	 */
	public List<DeliverySearchDTO> filterDuplAddrItem(List<DeliveryOrderGoodResult> list) throws Exception {
		List<DeliverySearchDTO> retList = new ArrayList<DeliverySearchDTO>();
		List<DeliverySearchDTO> tempList = new ArrayList<DeliverySearchDTO>();

		for (DeliveryOrderGoodResult obj : list) {
			DeliverySearchDTO dto = new DeliverySearchDTO();
			dto.setOrdNo(obj.getOrd().getOrdNo());
			dto.setDlvPcupspTurn(obj.getLgsDsp().getDlvPcupspTurn()+"");
			dto.setDlvMnCd(obj.getLgsDlv().getDlvMnCd());
			
			tempList.add(dto);
		}

		HashSet<DeliverySearchDTO> hs = new HashSet<DeliverySearchDTO>(tempList);
		Iterator<DeliverySearchDTO> it = hs.iterator();
		while(it.hasNext()) {
			retList.add(it.next());
		}

		return retList;
	}
	
	
	
	
	/**
	 * 출고지 상세 정보 조회 .
	 * 
	 * @param dto [설명]
	 * @return LgsDlvsp [설명]
	 * @throws Exception the exception
	 * @since 2017. 2. 2
	 */
    public LgsDlvsp selectLgsDlvspInfo(DeliverySearchDTO dto) throws Exception {
    	return  deliveryCommandRepository.selectLgsDlvspInfo(dto);
    }
	
	/**
	 * [고객센터] 픽업철회
	 * 주문상세팝업 또는 고객센터 화면에서 픽업건에 대한 철회 기능
	 * 철회시 재배정이 가능하도록 임시매장/배정대기 처리
	 * [사용 방법 설명].
	 *
	 * @param retObj [설명]
	 * @throws Exception the exception
	 * @since 2017. 5. 12
	 */
	public boolean modifyShortageCancelForPickup (DeliveryOrderGoodDTO dto, String udterId) throws Exception {
		
		// 1. 유효성 체크
		LgsDlivyDrctGod obj = deliveryCommandRepository.selectLgsDdgInfo(dto);
		
		if ( DeliveryEnum.DlvStatCd.DLIVY_DRCT_CNCL.toString().equals(obj.getDlvStatCd()) ) {
			DeliveryStatusException statusException = new DeliveryStatusException(null);
			statusException.setDirectMessage("주문취소건이 존재합니다.\n확인 후 처리하세요.");
			throw statusException;
		} else if ( !DeliveryEnum.DlvStatCd.SHTG_RCEPT.toString().equals(obj.getDlvStatCd()) ) {
			DeliveryStatusException statusException = new DeliveryStatusException(null);
			statusException.setDirectMessage("결품접수 상태건만 처리 가능합니다.\n확인 후 처리하세요.");
			throw statusException;
		}
		
		// 2. 출고정보 변경
		LgsDlivyDrctGod lgsDdg = new LgsDlivyDrctGod();
		lgsDdg.setDlivyDrctGodNo(obj.getDlivyDrctGodNo());
		lgsDdg.setDlvShopId( DeliveryEnum.B031.toString() ); // 배송매장
		lgsDdg.setDlvStatCd( DeliveryEnum.DlvStatCd.DLV_WAIT.toString() ); // 배송상태
		lgsDdg.setDlivyDrctTgtYn("N");
		lgsDdg.setUdterId(udterId);
		deliveryCommandRepository.updateAssignShopInfo(lgsDdg);
		
		// 3. 물류 출고지시 상품 이력 등록
		LgsDlivyDrctGodHist lgsDlivyDrctGodHist = new LgsDlivyDrctGodHist();
		lgsDlivyDrctGodHist.setRegtrId("CALL_CENTER_PKUP_WITHDRAW");
		lgsDlivyDrctGodHist.setUdterId(udterId);
		lgsDlivyDrctGodHist.setDlivyDrctGodNo(obj.getDlivyDrctGodNo());
		deliveryCommandRepository.insertLgsDlivyDrctGodHist(lgsDlivyDrctGodHist);

		// 4. ERP 예약영수증 변경 대상 조회
		DeliverySearchDTO deliverySearchDTO = new DeliverySearchDTO();
		deliverySearchDTO.setOrdNo(obj.getOrdNo());
		deliverySearchDTO.setDlivyDrctGodNo(obj.getDlivyDrctGodNo());
		List<DeliveryInfErpDTO> rlist = deliveryInfErpService.selectErpResveRcptfrCancelInfoList(deliverySearchDTO);

		for (int i = 0; i < rlist.size(); i++) {
			rlist.get(i).setWerks("B031"); // 매장 셋팅
		}

		// 5. 예약영수증 정보 변경
		deliveryInfErpService.modifyPreSalesDlvShopChangeNew(rlist, "CALL_CENTER_PKUP_WITHDRAW", DeliveryEnum.FC01.toString());
		
		return true;
	}	
	
	/**
	 * [FO] [일반] 배송상태 수정.
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param obj [설명]
	 * @return Delivery order good dto [설명]
	 * @throws Exception the exception
	 * @since 2017. 9. 28
	 */
	public DeliveryOrderGoodDTO updateDeliveryStatusFO(DeliveryOrderGoodResult obj) throws Exception {
		DeliveryOrderGoodDTO retObj = new DeliveryOrderGoodDTO();
		String udtId = "";
		if(ContextService.hasRole()){ // 회원
			SecurityUserDetail userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();
			udtId = userDetail.getMbr().getMbrNo();
		}else{ // 비회원
			udtId = GoodsEnum.RegtrId.GUEST.toString();
		}

		//물류 출고지시 상품 배송상태 수정
		retObj.setDlivyDrctGodNo(obj.getLgsDdg().getDlivyDrctGodNo());
		retObj.setDlvStatCd(OrderEnum.DLV_STAT_DLV_COMPT.toString());
		retObj.setDlivyDrctQty(obj.getLgsDdg().getDlivyDrctQty().intValue());
		retObj.setUdterId(udtId);
		deliveryCommandRepository.updateDeliveryStatus(retObj);

		//물류 출고지시 상품 이력 등록
		LgsDlivyDrctGodHist lgsDlivyDrctGodHist = new LgsDlivyDrctGodHist();
		lgsDlivyDrctGodHist.setRegtrId(udtId);
		lgsDlivyDrctGodHist.setUdterId(udtId);
		lgsDlivyDrctGodHist.setDlivyDrctGodNo(obj.getLgsDdg().getDlivyDrctGodNo());
		deliveryCommandRepository.insertLgsDlivyDrctGodHist(lgsDlivyDrctGodHist);

		retObj.setCount(1);
		retObj.setOrdNo(obj.getOrd().getOrdNo());
		return retObj;
	}


	/**
	 * [FO] [패키지] 배송상태 수정.
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param list [설명]
	 * @param newDlvStatCd [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2017. 9. 28
	 */
	public List<DeliveryOrderGoodDTO> updateDeliveryStatus4PackageFO(List<DeliveryOrderGoodResult> list) throws Exception {
		String udtId = "";
		if(ContextService.hasRole()){ // 회원
			SecurityUserDetail userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();
			udtId = userDetail.getMbr().getMbrNo();
		}else{ // 비회원
			udtId = GoodsEnum.RegtrId.GUEST.toString();
		}
		List<DeliveryOrderGoodDTO> retList = new ArrayList<DeliveryOrderGoodDTO>();

		//패키지 묶음 단일 처리
		List<DeliverySearchDTO> newList = this.filterDuplPackageItem(list);

		for (DeliverySearchDTO param : newList) {
			param.setNewDlvStatCd(OrderEnum.DLV_STAT_DLV_COMPT.toString());
			//패키지 구성상품 조회 :: 구성품별 묶음처리
			List<DeliveryOrderGoodResult> packList = deliveryCommandRepository.selectDeliveryInfoPckage(param);

			for (DeliveryOrderGoodResult packObj : packList) {
				DeliveryOrderGoodDTO retObj = new DeliveryOrderGoodDTO();

				//물류 출고지시 상품 배송상태 수정
				retObj.setDlivyDrctGodNo(packObj.getLgsDdg().getDlivyDrctGodNo());
				retObj.setDlvStatCd(OrderEnum.DLV_STAT_DLV_COMPT.toString());
				retObj.setDlivyDrctQty(packObj.getLgsDdg().getDlivyDrctQty().intValue());
				retObj.setUdterId(udtId);
				deliveryCommandRepository.updateDeliveryStatus(retObj);

				//물류 출고지시 상품 이력 등록
				LgsDlivyDrctGodHist lgsDlivyDrctGodHist = new LgsDlivyDrctGodHist();
				lgsDlivyDrctGodHist.setRegtrId(udtId);
				lgsDlivyDrctGodHist.setUdterId(udtId);
				lgsDlivyDrctGodHist.setDlivyDrctGodNo(packObj.getLgsDdg().getDlivyDrctGodNo());
				deliveryCommandRepository.insertLgsDlivyDrctGodHist(lgsDlivyDrctGodHist);

				retObj.setOrdNo(packObj.getOrd().getOrdNo());

				/*
				 * 1. 수정일자	: 2016-10-26
				 * 2. 수정자		: 김재성 (jskim27)
				 * 3. 요청 SR NO	: #26204
				 * 4. 수정내용	: 주문건의 적립 포인트 임시삭감 복원 로직 확인 요청
				 *					- '멤버쉽포인트 임시삭감 환원' 배치 수행이전에 '반품접수' 되는 경우를 대비한 방어 기능
				 */
				retObj.setMbrNo(packObj.getOrd().getMbrNo());

				retList.add(retObj);
			}
		}
		return retList;
	}

	/**
	 * [FO] 주문완료 문자메시지 발송
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param dmstcWaybilNo [설명]
	 * @return [설명]
	 * @throws Exception the exception
	 * @since 2017. 9. 28
	 */
	public void sendDeliveryComptMsg(SystemPK systemPK, DeliverySearchDTO deliverySearchDTO) throws Exception {
		// 3-4 주문완료 문자메시지 발송
		/*
		 * 주문완료 문자메시지 발송
		 * 	- [PLGRIM] 배송완료/O201505291023054/*** 외 *건/배송이 완료되었습니다/☞배송추적(택배송장추적 url링크)/☞http://앱다운.com
		 */
		List<DeliveryComptMsgResult> listOrd = this.deliverySelectRepository.selectDlvComptMsgFO(deliverySearchDTO);

		for (DeliveryComptMsgResult ord : listOrd) {
			if ((StringService.isEmpty(ord.getSmsRecptnSectCd()) && StringService.isNotEmpty(ord.getMobile()))
					|| ("ADDRSE".equals(ord.getSmsRecptnSectCd()) && StringService.isNotEmpty(ord.getAddrseMobileNo()))
					|| ("ORDMAN_ADDRSE".equals(ord.getSmsRecptnSectCd()) && (StringService.isNotEmpty(ord.getOrdmanMobileNo()) || StringService.isNotEmpty(ord.getAddrseMobileNo())))
					) {
				try {
					String mallId = ord.getMallId();
					String ordNo = ord.getOrdNo();
					String tranId = StringService.isEmpty(ord.getMbrNo()) ? ordNo : ord.getMbrNo();

					// phone, message, callerId
					String godNm = StringService.isNotEmpty(ord.getGodSumry()) ? ord.getGodSumry() : "";
					String brndNm = ord.getBrndNm();

					String msgKey = "";

					ArrayList<String> params = new ArrayList<>();

					/**
					 * [PLGRIM SHOP] 배송완료
					 *
					 * 주문번호 : {0}
					 * 주문상품 : {1}
					 *
					 * ∙ 리뷰 작성하기
					 * http://plgrimshop.com/s/Order
					 */
					//mallId
					msgKey = "DXM_LGS_COMPT_01";
					if("MBM".equals(mallId)) {
						msgKey = "MBM_LGS_COMPT_01";
					} else if("SAM".equals(mallId)) {
						msgKey = "SAM_LGS_COMPT_01";
					}
					
					godNm = "[" + brndNm + "]" + godNm;

					params.add(0, ordNo);
					params.add(1, godNm);

					// 수신구분에 따른 SMS발송
					String mobilNo = "";
					String sendMobileNum = "";
					if (StringService.isEmpty(ord.getSmsRecptnSectCd())) {
						mobilNo = ord.getOrdmanMobileNo();

					} else if ("ADDRSE".equals(ord.getSmsRecptnSectCd())) {
						mobilNo = ord.getAddrseMobileNo();
					} else if ("ORDMAN_ADDRSE".equals(ord.getSmsRecptnSectCd())) {
						mobilNo = ord.getOrdmanMobileNo();

						if (!StringService.equalsIgnoreCase(ord.getOrdmanMobileNo(), ord.getAddrseMobileNo())) {
							mobilNo = ord.getAddrseMobileNo();
							//주문자와 수령자 핸드폰 번호가 같지 않으면 주문자도 SMS 보냄 #49114
							sendMobileNum = ord.getOrdmanMobileNo();
						}
					}
					
//					String callerId = InterfaceAdapterEnum.INTERFACE_ADAPTER_DLIVY_COMPT_GNRL_SMS.toString();
//
//					if(StringService.isNotEmpty(mobilNo) && StringService.isNotEmpty(msgKey)){
//						HumusonSDO humusonSDO = humusonCommonService.getAlimTalkData(tranId, mobilNo, mallId, callerId, msgKey, params);
//
//						AdapterHeader adapterHeader = new AdapterHeader();
//						adapterHeader.setRequestId( UUID.randomUUID().toString().replace("-", "") );
//						adapterHeader.setMallId(mallId);
//						adapterHeader.setDvcCd(systemPK.getDevice());
//						adapterHeader.setLangCd(systemPK.getLang());
//
//						try {
//							humusonCommonService.sendAlimTalk(humusonSDO, adapterHeader);
//							/*
//							 * 개인정보이력 생성-SMS
//							 */
//							if (StringService.isNotEmpty(tranId) && tranId.contains("MB")) {
//								// tranID 가 회원일 경우
//								// 구분, 업무, 업무상세
//								String[][] usefCdInfo = {
//									{
//										MemberPersonalInfoEnum.UsefSectCd.PSNL_INFO_THPR_OFFER_DETL.toString(), // 개인정보 취급위탁 내역
//										MemberPersonalInfoEnum.UsefJobCd.SND_KKO_NTCN_TAK.toString(), // SMS, MMS발송
//										MemberPersonalInfoEnum.UsefJobDetail.STPLAT_APL.toString() // 약관에 따름
//									}
//								};
//								String[] target = { tranId };
//								memberPersonalInfoCommandService.insertMemberPersonalInfo( systemPK
//										, usefCdInfo			    // 개인정보 코드 정보(구분, 업무, 업무상세)
//										, target					// 이용대상 : 회원
//										, null				// 유입 일련번호
//										, null              // 메뉴 일련번호
//										, tranId	                // 로그인 ID
//								);
//							}
//						} catch (Exception e) {
//							StringWriter error = new StringWriter();
//							e.printStackTrace(new PrintWriter(error));
//							log.error("> Failure message : {}", this.getClass().getName() + " : " + error.toString());
//						}
//					}
					
					//주문자와 수령자 핸드폰 번호가 같지 않으면 주문자도 SMS 보냄 #49114
					//회원 개인정보이력 생성 안함
//					if(StringService.isNotEmpty(sendMobileNum) && StringService.isNotEmpty(msgKey)){
//						HumusonSDO humusonSDO = humusonCommonService.getAlimTalkData(tranId, sendMobileNum, mallId, callerId, msgKey, params);
//
//						AdapterHeader adapterHeader = new AdapterHeader();
//						adapterHeader.setRequestId( UUID.randomUUID().toString().replace("-", "") );
//						adapterHeader.setMallId(mallId);
//						adapterHeader.setDvcCd(systemPK.getDevice());
//						adapterHeader.setLangCd(systemPK.getLang());
//
//						try {
//							humusonCommonService.sendAlimTalk(humusonSDO, adapterHeader);
//						} catch (Exception e) {
//							StringWriter error = new StringWriter();
//							e.printStackTrace(new PrintWriter(error));
//							log.error("> Failure message : {}", this.getClass().getName() + " : " + error.toString());
//						}
//					}
				} catch (Exception e) {
					StringWriter error = new StringWriter();
					e.printStackTrace(new PrintWriter(error));
					log.error("> Failure message : {}", this.getClass().getName() + " : " + error.toString());
				}
			}
		}
	}
	
	/**
	 * 출고지시
	 * 
	 * @param systemPK
	 * @param gridList
	 * @return
	 * @throws Exception
	 */
	public String sendDlivyDrct(SystemPK systemPK, List<DeliveryOrderGoodResult> gridList) throws Exception {

		List<DeliveryOrderGoodResult> wmsList = new ArrayList<DeliveryOrderGoodResult>();
		List<DeliveryOrderGoodResult> shopList = new ArrayList<DeliveryOrderGoodResult>();
		List<DeliveryOrderGoodResult> packList = new ArrayList<DeliveryOrderGoodResult>();
		List<DeliveryOrderGoodResult> gftList = new ArrayList<DeliveryOrderGoodResult>();
		String rtrnMsg = "";

		// 배송상태 체크 및 처리대상 선별.
		DeliveryOrderGoodDTO checkDto = new DeliveryOrderGoodDTO();
		for (DeliveryOrderGoodResult obj : gridList) {

			//배송상태 체크 :: 출고지시 대기만 출고지시 가능.
			checkDto.setDlivyDrctGodNo(obj.getLgsDdg().getDlivyDrctGodNo()); // DLIVY_DRCT_GOD_NO : 출고지시상품번호
			LgsDlivyDrctGod info = selectLgsDdgInfo(checkDto); // 출고지시 상품 조회
			
			if((DeliveryEnum.DLV_STAT_DLIVY_DRCT_WAIT.toString().equals(info.getDlvStatCd()) && info.getDlvShopId() != null)
					|| (DeliveryEnum.DLV_STAT_DLIVY_DRCT.toString().equals(info.getDlvStatCd())
					&& DeliveryEnum.DLIVY_DRCT_CNCL_DCSN.toString().equals(info.getDlivyDrctCnclStatCd()))
					)
			{
				// 배장 매송 사은품인 경우 -> '주문확인' 상태가 Y가 아니라면 무시
				if ("Y".equals(info.getGftYn())) {
					DeliverySearchDTO search = new DeliverySearchDTO();
					search.setOrdNo(obj.getOrd().getOrdNo());
					search.setOrdGodTurn(obj.getOrdGod().getOrdGodTurn());
					String ordCnfirmYn = deliveryCommandRepository.selectOrdCnfirmYn(search);
					if (!"Y".equals(ordCnfirmYn)) {
						gftList.add(obj);
						continue;
					}
				}

				//일반
				if(null == obj.getLgsDdg().getPckageGodTurn() || 0 == obj.getLgsDdg().getPckageGodTurn()) {
					//일반상품 추가
					//wms
					if(DeliveryEnum.DLV_ONLINE_SHOP_DISCOVERY.toString().equals(info.getDlvShopId()) 
							|| DeliveryEnum.DLV_ONLINE_SHOP_MLB.toString().equals(info.getDlvShopId())
							|| DeliveryEnum.DLV_ONLINE_SHOP_MLB_KIDZ.toString().equals(info.getDlvShopId())
							|| DeliveryEnum.DLV_ONLINE_SHOP_SA.toString().equals(info.getDlvShopId())) {
						wmsList.add(obj);
					//매장	
					} else {
						shopList.add(obj);
					}
				//패키지	
				} else {
					//미선택 구성품을 위한 별도 처리.
					packList.add(obj);
				}
			}else
			{
				DeliveryStatusException statusException = new DeliveryStatusException(null);
				statusException.setDirectMessage("유효한 상태가 아닙니다. \n확인 후 처리하세요.");
				throw statusException;
			}
        }
		
		//패키지 누락건 대상 적용( 패키지에서는 WMS에서만 제공)
		if(packList.size() > 0) {
			List<DeliverySearchDTO> plist = filterDuplPackageItem(packList);
			for (DeliverySearchDTO pobj : plist) {
				List<DeliveryOrderGoodResult> subList = selectDeliveryInfoPckage(pobj);
				for(DeliveryOrderGoodResult obj : subList) {
					//패키지 구성품 추가
					wmsList.add(obj);
				}
			}
		}
		
		// WMS 출고지시 실패나도 매장은 배송하게 매장먼저 우선순위.
		// 배송매장은 일부 상품만 선택해도 전체 상품 출고지시 변경을 위해 
		// ord_no, dlv_pcupsp_turn, shop_Id 기준으로 중복 제거.
		if(shopList.size()>0)
		{
			rtrnMsg += filterDivWaybillShopInfo(shopList);
		}
		
		// TODO 테스트 코딩 테스트 필요함.
		// WMS 전송. 
		if(wmsList.size()>0)
		{
			if(!"".equals(rtrnMsg))
			{
				rtrnMsg += "\n";
			}
			rtrnMsg += sendWmsData(wmsList, systemPK, false);
		}
		
		if (gftList.size() > 0) {
			rtrnMsg += "\n";
			rtrnMsg += gftList.size()+"건은 매장주문 처리대기로 인해 WMS로 전송되지 않았습니다.";
		}
		
		return rtrnMsg;
	}
	
	/**
	 * 출고지시 취소
	 * 
	 * @param systemPK
	 * @param gridList
	 * @return
	 * @throws Exception
	 */
	public String sendDlivyDrctCancel(SystemPK systemPK, List<DeliveryOrderGoodResult> gridList) throws Exception {
		List<DeliveryOrderGoodResult> wmsList = new ArrayList<DeliveryOrderGoodResult>();
		List<DeliveryOrderGoodResult> packList = new ArrayList<DeliveryOrderGoodResult>();
		String rtrnMsg = "";
		
		// 배송상태 체크 및 처리대상 선별.
		DeliveryOrderGoodDTO checkDto = new DeliveryOrderGoodDTO();
		for (DeliveryOrderGoodResult obj : gridList) {
			
			//배송상태 체크 :: 출고지시만 송장채번 가능.
			checkDto.setDlivyDrctGodNo(obj.getLgsDdg().getDlivyDrctGodNo());
			LgsDlivyDrctGod info = selectLgsDdgInfo(checkDto);
			
			if((DeliveryEnum.DLV_STAT_DLIVY_DRCT.toString().equals(info.getDlvStatCd()) && info.getDlvShopId() != null))
			{
				//일반
				if(null == obj.getLgsDdg().getPckageGodTurn() || 0 == obj.getLgsDdg().getPckageGodTurn()) {
					//일반상품 추가
					//wms
					wmsList.add(obj);
					//패키지	
				} else {
					//미선택 구성품을 위한 별도 처리.
					packList.add(obj);
				}
			}
		}
		
		//패키지 누락건 대상 적용( 패키지에서는 WMS에서만 제공)
		if(packList.size() > 0) {
			List<DeliverySearchDTO> plist = filterDuplPackageItem(packList);
			for (DeliverySearchDTO pobj : plist) {
				List<DeliveryOrderGoodResult> subList = selectDeliveryInfoPckage(pobj);
				for(DeliveryOrderGoodResult obj : subList) {
					//패키지 구성품 추가
					wmsList.add(obj);
				}
			}
		}
		
		// TODO 테스트 코딩 테스트 필요함.
		// WMS 전송. 
		if(wmsList.size()>0)
		{
			rtrnMsg = sendWmsData(gridList, systemPK, true);
		}
		
		return rtrnMsg;
	}
	
	/**
	 * 배송매장 분리대상 
	 * 배송매장은 일부 상품만 선택해도 전체 상품 출고지시 변경을 위해 ord_no, dlv_pcupsp_turn, shop_Id 기준으로 중복 제거. 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param gridList [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 */
	public String filterDivWaybillShopInfo(List<DeliveryOrderGoodResult> targetList) throws Exception {
		List<DeliveryOrderGoodDTO> retList = new ArrayList<DeliveryOrderGoodDTO>();
		List<DeliveryOrderGoodDTO> tempList = new ArrayList<DeliveryOrderGoodDTO>();
		List<DeliveryOrderGoodResult> shopList = new ArrayList<DeliveryOrderGoodResult>();
		int sCnt = 0;
		int fCnt = 0;
		String rtrnMsg = "";
		String userId = BOSecurityUtil.getLoginId();	//세션ID
		
		//물류배송 분리대상 중복제거
		for (DeliveryOrderGoodResult result : targetList) {
			DeliveryOrderGoodDTO dto = new DeliveryOrderGoodDTO();
			dto.setOrdNo(result.getOrd().getOrdNo());
			dto.setDlvPcupspTurn(result.getLgsDsp().getDlvPcupspTurn());
			dto.setDlvTurn(result.getLgsDlv().getDlvTurn());
			dto.setDlvShopId(result.getLgsDdg().getDlvShopId());
			tempList.add(dto);
		}
		
		HashSet<DeliveryOrderGoodDTO> hs = new HashSet<DeliveryOrderGoodDTO>(tempList);
		Iterator<DeliveryOrderGoodDTO> it = hs.iterator();
		while(it.hasNext()) {
			retList.add(it.next());
		}
		
		for(DeliveryOrderGoodDTO obj2 : retList)
		{
			DeliverySearchDTO search = new DeliverySearchDTO();
			search.setOrdNo(obj2.getOrdNo());
			search.setDlvPcupspTurn(obj2.getDlvPcupspTurn()+"");
			search.setDlvTurn(obj2.getDlvTurn()+"");
			search.setDlvShopId(obj2.getDlvShopId());
			List<DeliveryOrderGoodResult> reSelectList = deliveryCommandRepository.selectDlivyDrctTargetList(search);
			for(DeliveryOrderGoodResult obj3 : reSelectList)
			{
				shopList.add(obj3);
			}
		}
		
		log.info("targetList :: "+shopList);
		//물류배송 분리대상(박스분리 대상) 선별.
		List<DeliveryOrderGoodDTO> finalList = filterDivWaybillInfo(shopList);
		log.info("finalList :: "+finalList);
		
		DeliveryInvoiceDTO invoiceDto = new DeliveryInvoiceDTO();
		for(DeliveryOrderGoodDTO fdto : finalList) {

			//물류배송단위 출고대상건 카운트 조회
			int dlvCnt = selectAbleDlivyeryCnt(fdto);
			
			//물류배송단위 출고대상건과 박스 분리대상건 수량 비교
			if(dlvCnt < fdto.getNewTargetCnt()) {
				DeliveryStatusException statusException = new DeliveryStatusException(null);
				statusException.setDirectMessage("배송상태 변경건이 존재합니다. \n확인 후 처리하세요.");
				throw statusException;
				
			} else if(dlvCnt == fdto.getNewTargetCnt()) {
				
				List<String> paramList = new ArrayList<String>();
				if(StringService.isNotEmpty(fdto.getParams())) {
					String[] paramArr = fdto.getParams().substring(0, fdto.getParams().length()-1).split(",");
					for (String dlivyDrctGodNo : paramArr) {
						DeliveryOrderGoodResult param = new DeliveryOrderGoodResult();
						LgsDlivyDrctGod lgsDdg = new LgsDlivyDrctGod();
						lgsDdg.setDlivyDrctGodNo(dlivyDrctGodNo);
						lgsDdg.setDlvStatCd(DeliveryEnum.DLV_STAT_DLIVY_DRCT.toString());
						//출고지시수량 조회 (출고지시수량 - 출고지시취소수량)
						lgsDdg.setDlivyDrctQty(Long.valueOf(deliveryCommandRepository.selectDlivyDrctQty(dlivyDrctGodNo)));
						lgsDdg.setDlivyDrctDt(new Date());
						param.setLgsDdg(lgsDdg);
			            // 출고지시상품에 물류배송 분리정보 적용.
			            updateDivLgsDdgInfo(param, 0);
		            }
				}
				
				// TODO 데이터 변경 어떻게 할지 고민.
	            //TODO 메일 발송? 또는 SMS 발송을 해야할것 같음. 이후 LGS_DLV 발송여부 업데이트 (이부분이 아니라 배치로 빠져야 할것 같음.)
				
                // 주문상태 수정
            	Ord ord = new Ord();
            	ord.setOrdNo(fdto.getOrdNo());
            	ord.setUdterId(userId);
            	ord.setOrdStatCd(DeliveryEnum.OrdStatCd.DLV_PRPARE.toString());    //배송준비
            	orderBoCommandRepository.updateOrdStatCd(ord);
                
				sCnt += fdto.getNewTargetCnt();
			} else {
				//박스분리건 대상 조회 :: 배송매장이 물류센터인 경우 사은품까지 조회(합포장)
				DeliverySearchDTO search = new DeliverySearchDTO();
				search.setOrdNo(fdto.getOrdNo());
				search.setDlvPcupspTurn(fdto.getDlvPcupspTurn()+"");
				search.setDlvTurn(fdto.getDlvTurn()+"");
				search.setDlvShopId(fdto.getDlvShopId());
				
				List<String> paramList = new ArrayList<String>();
				if(StringService.isNotEmpty(fdto.getParams())) {
					String[] paramArr = fdto.getParams().substring(0, fdto.getParams().length()-1).split(",");
					for (String param : paramArr) {
						paramList.add(param);
		            }
					search.setParamList(paramList);
				}
				
				//박스분리 대상 정보 조회
				List<DeliveryOrderGoodResult> divTargetList = selectDlvDivideTargetList(search);
				
				int idx = 0;
				int dlvTurn = 0;	//배송순번
				for (DeliveryOrderGoodResult divObj : divTargetList) {
	                if(idx == 0) {

	    				// TODO 데이터 변경 어떻게 할지 고민.
	                    divObj.setMallId(StringUtils.defaultString(fdto.getMallId()));
						divObj.setLang(StringUtils.defaultString(fdto.getLangCd()));
						divObj.getOrd().setLangCd(StringUtils.defaultString(fdto.getLangCd()));
	                	
	            		//배송순번 채번
	            		Map<String, Object> conditions = Maps.newHashMap();
	            		conditions.put("ord_no", divObj.getOrd().getOrdNo());
	            		conditions.put("dlv_pcupsp_turn", divObj.getLgsDsp().getDlvPcupspTurn());
	            		dlvTurn = getIdGenService().generateDBOrder(sqlSession1, "lgs_dlv", "dlv_turn", conditions,
	            				DatabaseType.ORACLE);

	            		//물류배송 데이터 등록
	            		DeliveryOrderGoodDTO paramDto = new DeliveryOrderGoodDTO();
	            		paramDto.setOrdNo(divObj.getOrd().getOrdNo());
	            		paramDto.setDlvPcupspTurn(divObj.getLgsDsp().getDlvPcupspTurn());
	            		paramDto.setOrgDlvTurn(divObj.getLgsDlv().getDlvTurn());	//원 배송순번
	            		paramDto.setDlvTurn(dlvTurn);	//신규 배송순번

	            		String mallId = orderSelectService.selectOrdMallId(divObj.getOrd().getOrdNo());
	                	String dlvComCd = deliverySelectRepository.selectBaseDlvComCd(mallId);
	        			paramDto.setDlvComCd(dlvComCd);
	            		paramDto.setRegtrId(userId);
	            		deliveryCommandRepository.insertLgsDlv(paramDto);

	                    //물류배송 이력 등록
	            		LgsDlvHist lgsDlvHist = new LgsDlvHist();
	            		lgsDlvHist.setOrdNo(divObj.getOrd().getOrdNo());
	            		lgsDlvHist.setDlvPcupspTurn(divObj.getLgsDsp().getDlvPcupspTurn());
	            		lgsDlvHist.setDlvTurn(dlvTurn);
	            		lgsDlvHist.setRegtrId(userId);
	            		lgsDlvHist.setUdterId("DIV_LGSDLV");
	            		deliveryCommandRepository.insertLgsDlvHist(lgsDlvHist);
	                }
	                
	                divObj.getLgsDdg().setDlvStatCd(DeliveryEnum.DLV_STAT_DLIVY_DRCT.toString());
	                divObj.getLgsDdg().setDlivyDrctQty(Long.valueOf(deliveryCommandRepository.selectDlivyDrctQty(divObj.getLgsDdg().getDlivyDrctGodNo())));
	                divObj.getLgsDdg().setDlivyDrctDt(new Date());
	                // 출고지시상품에 물류배송 분리정보 적용.
	                updateDivLgsDdgInfo(divObj, dlvTurn);
	                // 주문상태 수정
                	Ord ord = new Ord();
                	ord.setOrdNo(divObj.getOrd().getOrdNo());
                	ord.setUdterId(userId);
                	ord.setOrdStatCd(DeliveryEnum.OrdStatCd.DLV_PRPARE.toString());    //배송준비
                	orderBoCommandRepository.updateOrdStatCd(ord);
	                idx++;
	                sCnt++;
	            }
			}
	    }
		if(fCnt < 1) {
			rtrnMsg = "배송매장 "+sCnt+"건이 처리되었습니다.";
		} else {
			rtrnMsg = "배송매장 처리건 중 성공 :: "+sCnt+"건 실패 ::"+fCnt+"건\n실패 건에 대해 배송상태 확인 바랍니다.";
		}
		return rtrnMsg;
	}
	
	/**
	 * wms 데이터 셋팅 및 전송.
	 * 
	 * @param systemPK
	 * @param gridList
	 * @return
	 * @throws Exception
	 */
	public String sendWmsData(List<DeliveryOrderGoodResult> wmsList, SystemPK systemPK, Boolean dlivyDrctCancel) throws Exception {
		int sCnt = 0;
		int fCnt = 0;
		String rtrnMsg = "";
		List<DlivyDrctSDO> paramList = new ArrayList<DlivyDrctSDO>();
		
		for(int i=0; i<wmsList.size(); i++)
		{
			DeliveryOrderGoodDTO retObj = new DeliveryOrderGoodDTO();
			
			//배송상태 체크
			retObj.setDlivyDrctGodNo(wmsList.get(i).getLgsDdg().getDlivyDrctGodNo());
			LgsDlivyDrctGod info = deliveryCommandRepository.selectLgsDdgInfo(retObj);
			
			if ("DLIVY_DRCT_CNCL".equals(info.getDlvStatCd())) {
				DeliveryStatusException statusException = new DeliveryStatusException(null);
				statusException.setDirectMessage("주문취소건이 존재합니다.\n확인 후 처리하세요.");
				throw statusException;
			}
			DeliverySearchDTO deliverySearchDTO = new DeliverySearchDTO();
            // 취소 여부에 따라서 WMS 전송 값 설정.
            if(dlivyDrctCancel)
            {
                deliverySearchDTO.setCancelYn("Y");
            }else {
                deliverySearchDTO.setCancelYn("N");
            }
			deliverySearchDTO.setOrdNo(wmsList.get(i).getOrd().getOrdNo());
			deliverySearchDTO.setDlvPcupspTurn(wmsList.get(i).getLgsDsp().getDlvPcupspTurn()+"");
			deliverySearchDTO.setDlvTurn(wmsList.get(i).getLgsDlv().getDlvTurn()+"");
			deliverySearchDTO.setDlivyDrctGodNo(wmsList.get(i).getLgsDdg().getDlivyDrctGodNo());
			//TODO 주문번호, 출고지시상품번호 기준으로 ERP(WMS) 전송용 데이터 조회 필요
			DlivyDrctSDO dlivyDrctSDO = deliveryCommandRepository.selectWmsDlivyDrctTargetList(deliverySearchDTO);
			
			paramList.add(dlivyDrctSDO);
		}
		DlivyWmsSDO dlivyWmsSDO = new DlivyWmsSDO();
		dlivyWmsSDO.setReleaseList(paramList);
		// AdapterHeader 설정
		AdapterHeader adapterHeader = setBOAdapterHeader(systemPK);
		dlivyWmsSDO.setCallerId(this.getClass().getSimpleName() + this.getClass().getDeclaredMethods()[0].getName());
		String returnData = deliveryAdapter.sendWMSDlivyDrct(dlivyWmsSDO, adapterHeader);
		
		DlivyWmsSDO dlivyWmsSDOReturn = (DlivyWmsSDO) JsonService.unmarshalling(returnData, DlivyWmsSDO.class);
		
		if(InterfaceConstants.IF_RESULT_CD_SUCESS.equals(dlivyWmsSDOReturn.getResponseCd()))
		{
			List<DlivyDrctSDO> returnList = dlivyWmsSDOReturn.getResponseData();
			if(dlivyDrctCancel)
			{
				for(int i=0; i<returnList.size(); i++)
				{
					if("00".equals(returnList.get(i).getResultCd()))
					{
						DeliveryOrderGoodResult param = new DeliveryOrderGoodResult();
						LgsDlivyDrctGod lgsDdg = new LgsDlivyDrctGod();
						lgsDdg.setDlivyDrctGodNo(returnList.get(i).getDlivyDrctGodNo());
						lgsDdg.setDlivyDrctCnclStatCd(DeliveryEnum.DLIVY_DRCT_CNCL_WAIT.toString());
						param.setLgsDdg(lgsDdg);
			            // 출고지시상품에 물류배송 분리정보 적용.
			            updateDivLgsDdgInfo(param, 0);
	                	
			            sCnt ++;
					}else
					{
						log.info("dlivyDrctCancelRow : {} ResultCd : {} ResultMsg : {}",dlivyDrctCancel,returnList.get(i).getResultCd(),returnList.get(i).getResultMsg());
						fCnt ++;
					}
				}
			}else
			{
				for(int i=0; i<returnList.size(); i++)
				{
					if("00".equals(returnList.get(i).getResultCd()))
					{
						DeliveryOrderGoodResult param = new DeliveryOrderGoodResult();
						LgsDlivyDrctGod lgsDdg = new LgsDlivyDrctGod();
						lgsDdg.setDlivyDrctGodNo(returnList.get(i).getDlivyDrctGodNo());
						lgsDdg.setDlvStatCd(DeliveryEnum.DLV_STAT_DLIVY_DRCT.toString());
						lgsDdg.setDlivyDrctDt(new Date());
						param.setLgsDdg(lgsDdg);
			            // 출고지시상품에 물류배송 분리정보 적용.
			            updateDivLgsDdgInfo(param, 0);
			            
	                	Ord ord = new Ord();
	                	ord.setOrdNo(returnList.get(i).getOrdNo());
	                	ord.setUdterId(BOSecurityUtil.getLoginId());
	                	ord.setOrdStatCd(DeliveryEnum.OrdStatCd.DLV_PRPARE.toString());    //배송준비
	                	orderBoCommandRepository.updateOrdStatCd(ord);
	                	
			            sCnt ++;
					}else
					{
						log.info("dlivyDrctCancelRow : {} ResultCd : {} ResultMsg : {}",dlivyDrctCancel,returnList.get(i).getResultCd(),returnList.get(i).getResultMsg());
						fCnt ++;
					}
				}
			}
		}else
		{
			log.info("sendWmsDataBO dlivyDrctCancel : {} ResponseCd : {} ResponseMsg : {}",dlivyDrctCancel,dlivyWmsSDOReturn.getResponseCd(),dlivyWmsSDOReturn.getResponseMsg());
			fCnt += paramList.size();
		}
		
		if(fCnt < 1) {
			rtrnMsg = "WMS "+sCnt+"건이 처리되었습니다.";
		} else {
			rtrnMsg = "WMS "+"처리건 중 성공 :: "+sCnt+"건 실패 ::"+fCnt+"건\n실패 건에 대해 배송상태 확인 및 ERP 상태 확인 바랍니다.";
		}
		
		return rtrnMsg;
	}
	
    /**
     * 품번 시리얼 반품배송클레임상품리스트 조회
     * @param ReturnItemListByClaimNoResult
     * @return List<ReturnItemWithWayBillResult>
     */
    public List<ReturnItemListByClaimNoResult> getReturnItemListBySkuWithSerial(ReturnItemListByClaimNoResult returnItemListByClaimNoResult) {
        return deliverySelectRepository.getReturnItemListBySkuWithSerial(returnItemListByClaimNoResult);
    }
    
}
