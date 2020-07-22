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
 * @since       2015. 4. 8
 */
package com.plgrim.ncp.biz.delivery.service;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.net.URI;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.common.collect.Maps;
import com.plgrim.ncp.base.abstracts.AbstractService;
import com.plgrim.ncp.base.entities.datasource1.clm.ClmWrhsGodExtend;
import com.plgrim.ncp.base.entities.datasource1.god.God;
import com.plgrim.ncp.base.entities.datasource1.god.GodShopItmInvExtend;
import com.plgrim.ncp.base.entities.datasource1.inf.InfOrdGodErpDstb;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsDlivyDrctGod;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsDlivyDrctGodHist;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsDlv;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsDlvHist;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsDlvsp;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsDlvspExtend;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsDlvspHist;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsRtrvlDrctGodExtend;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsRtrvlDrctGodHist;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrDlvsp;
import com.plgrim.ncp.base.entities.datasource1.ord.Ord;
import com.plgrim.ncp.base.entities.datasource1.ord.OrdGodExtend;
import com.plgrim.ncp.base.enums.DeliveryEnum;
import com.plgrim.ncp.base.enums.GoodsEnum;
import com.plgrim.ncp.base.enums.GoodsEnum.GoodsType;
import com.plgrim.ncp.base.enums.OrderClaimEnum;
import com.plgrim.ncp.base.repository.lgs.LgsDlivyDrctGodHistRepository;
import com.plgrim.ncp.base.repository.lgs.LgsDlivyDrctGodRepository;
import com.plgrim.ncp.base.repository.lgs.LgsDlvHistRepository;
import com.plgrim.ncp.base.repository.lgs.LgsDlvRepository;
import com.plgrim.ncp.base.repository.lgs.LgsDlvspHistRepository;
import com.plgrim.ncp.base.repository.lgs.LgsDlvspRepository;
import com.plgrim.ncp.base.repository.mbr.MbrDlvspRepository;
import com.plgrim.ncp.base.repository.ord.OrdRepository;
import com.plgrim.ncp.biz.claim.service.ClaimService;
import com.plgrim.ncp.biz.delivery.data.DeliveryFinishFoDTO;
import com.plgrim.ncp.biz.delivery.data.DeliveryOrderGoodDTO;
import com.plgrim.ncp.biz.delivery.data.DeliveryPayClaimDTO;
import com.plgrim.ncp.biz.delivery.data.DeliverySearchDTO;
import com.plgrim.ncp.biz.delivery.data.DlvOrdGodInfoDTO;
import com.plgrim.ncp.biz.delivery.data.LgsDlvspBoDTO;
import com.plgrim.ncp.biz.delivery.data.LgsDlvspPkupDTO;
import com.plgrim.ncp.biz.delivery.repository.DeliveryCommandRepository;
import com.plgrim.ncp.biz.delivery.repository.DeliveryExternalRepository;
import com.plgrim.ncp.biz.delivery.repository.DeliverySelectRepository;
import com.plgrim.ncp.biz.goods.service.GoodsService;
import com.plgrim.ncp.biz.order.service.OrderBoSelectService;
import com.plgrim.ncp.cmp.orderfulfilment.fo.order.exception.OrderCompleteFailException;
import com.plgrim.ncp.commons.data.SysShopDTO;
import com.plgrim.ncp.commons.repository.SysShopMgrRepository;
import com.plgrim.ncp.commons.result.SysShopResult;
import com.plgrim.ncp.commons.taglib.Functions;
import com.plgrim.ncp.framework.commons.StringService;
import com.plgrim.ncp.framework.enums.DatabaseType;
import com.plgrim.ncp.interfaces.util.InterfaceApiCommon;

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
 * @since 2015. 4. 2
 */
@Slf4j
@Service
public class DeliveryService extends AbstractService {

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	@Autowired
	SysShopMgrRepository sysShopMgrRepository;// 매장 목록 조회.
	
	@Autowired
	LgsDlvspRepository lgsDlvspRepository;// 물류배송지

	@Autowired
	LgsDlvspHistRepository lgsDlvspHistRepository;// 물류배송지 이력

	@Autowired
	LgsDlvRepository lgsDlvRepository;// 물류배송

	@Autowired
	LgsDlvHistRepository lgsDlvHistRepository;// 물류배송 이력

	@Autowired
	LgsDlivyDrctGodRepository lgsDlivyDrctGodRepository;// 물류 출고지시 상품

	@Autowired
	LgsDlivyDrctGodHistRepository lgsDlivyDrctGodHistRepository;// 물류/ 출고지시상품이력

	@Autowired
	DeliveryExternalRepository deliveryExternalRepository;
	@Autowired
	DeliveryCommandRepository deliveryCommandRepository;

	@Autowired
	DeliverySelectRepository deliverySelectRepository;

	@Autowired
	MbrDlvspRepository mbrDlvspRepository;

	@Autowired
	InterfaceApiCommon interfaceApiCommon;
	
	@Autowired
    OrdRepository ordRepository;	
	
	@Autowired
	OrderBoSelectService orderBoSelectService;
	
	@Autowired
	ClaimService claimService;

	@Autowired
	GoodsService goodsService;
	
	@Autowired
	@Qualifier("sessionTemplate1")
	SqlSession sqlSession1;

	// 배송 추적 URL
	private @Value("${ncp_base.epost.trace.url}") String traceUrl;
	
	// 배송 추적 regkey
	private @Value("${ncp_base.epost.trace.regkey}") String traceRegkey;
	
	/*
	 * ---------------------------------------------------------------------
	 * KEN주문등록
	 * ---------------------------------------------------------------------
	 */

	public void lgsDlvspProcessor(LgsDlvspBoDTO lgsDlvspBoDTO, String ordNo, String regtrId) throws Exception {

		Map<String, Object> conditions = Maps.newHashMap();

		for (LgsDlvspExtend lgsDlvspEntity : lgsDlvspBoDTO.getLgsDlvspList()) {

			conditions.put("ord_no", ordNo);
			int dlvPcupspTurn = getIdGenService().generateDBOrder(sqlSession1, "lgs_dlvsp", "dlv_pcupsp_turn", conditions,
					DatabaseType.ORACLE);

			if (lgsDlvspEntity.isMbrCheck()) {
				MbrDlvsp mbrDlvsp = new MbrDlvsp();
				mbrDlvsp.setMbrNo(lgsDlvspEntity.getMbrNo());
				mbrDlvsp.setDlvAdbukTurn(lgsDlvspEntity.getDlvAdbukTurn());
				mbrDlvsp = mbrDlvspRepository.selectMbrDlvsp(mbrDlvsp);
				Functions.copyProperties(mbrDlvsp, lgsDlvspEntity);
				if(lgsDlvspEntity.getAddrseNm().length() >=7){
					lgsDlvspEntity.setAddrseNm(lgsDlvspEntity.getAddrseNm().substring(0, 6));	
				}
				
			}
			
			lgsDlvspEntity.setDlvPcupspSectCd(OrderClaimEnum.DLVSP_ORD_DLVSP.toString());
			lgsDlvspEntity.setDlvPcupspTurn(dlvPcupspTurn);
			lgsDlvspEntity.setAddrseNationCd("KR");
			lgsDlvspEntity.setOrdNo(ordNo);
			lgsDlvspEntity.setRegtrId(regtrId);
			lgsDlvspEntity.setUdterId(regtrId);

			// 물류 배송지
			lgsDlvspRepository.insertLgsDlvsp(lgsDlvspEntity);

			LgsDlvspHist lgsDlvspHist = new LgsDlvspHist();

			BeanUtils.copyProperties(lgsDlvspEntity, lgsDlvspHist);

			lgsDlvspHist.setHistDt(new Date());

			// 물류 배송지 이력
			lgsDlvspHistRepository.insertLgsDlvspHist(lgsDlvspHist);

		}

	}

	public void lgsDlvProcessor(LgsDlvspBoDTO lgsDlvspBoDTO, String regtrId, List<InfOrdGodErpDstb> infOrdGodErpDstbs, boolean flag)
			throws Exception {

		for (LgsDlvspExtend lgsDlvspEntity : lgsDlvspBoDTO.getLgsDlvspList()) {

			Map<Long, List<OrdGodExtend>> lgsDlvMap = Maps.newHashMap();

			for (OrdGodExtend ordGodEntity : lgsDlvspEntity.getOrdGodList()) {

				if("Y".equals(ordGodEntity.getOrdGiftYn())){
					
					if (lgsDlvMap.containsKey(0L)) {

						List<OrdGodExtend> list = lgsDlvMap.get(0L);
						list.add(ordGodEntity);

					}
					else {

						List<OrdGodExtend> list = new ArrayList<OrdGodExtend>();

						list.add(ordGodEntity);
						lgsDlvMap.put(0L, list);

					}					
				}
				else if (lgsDlvMap.containsKey(ordGodEntity.getDmstcDlvCstPlcSn())) {

					List<OrdGodExtend> list = lgsDlvMap.get(ordGodEntity.getDmstcDlvCstPlcSn());
					list.add(ordGodEntity);

				}
				else {

					List<OrdGodExtend> list = new ArrayList<OrdGodExtend>();

					list.add(ordGodEntity);
					lgsDlvMap.put(ordGodEntity.getDmstcDlvCstPlcSn(), list);

				}

			}

			Iterator<Long> it = lgsDlvMap.keySet().iterator();

			while (it.hasNext()) {
				Long key = it.next();

				List<OrdGodExtend> list = lgsDlvMap.get(key);
				LgsDlv lgsDlv = new LgsDlv();
				Map<String, Object> conditions = Maps.newHashMap();
				conditions.put("ord_no", lgsDlvspEntity.getOrdNo());
				conditions.put("dlv_pcupsp_turn", lgsDlvspEntity.getDlvPcupspTurn());
				int dlvTurn = getIdGenService().generateDBOrder(sqlSession1, "lgs_dlv", "dlv_turn", conditions, DatabaseType.ORACLE);
				Functions.variAbleSetN(lgsDlv);

				lgsDlv.setDlvTurn(dlvTurn);
				lgsDlv.setDlvPcupspTurn(lgsDlvspEntity.getDlvPcupspTurn());
				lgsDlv.setOrdNo(lgsDlvspEntity.getOrdNo());
				lgsDlv.setRegtrId(regtrId);
				lgsDlv.setUdterId(regtrId);
				lgsDlv.setCrncyCd("KRW");
				lgsDlv.setStdrCrncyAmt(new BigDecimal("0"));
				lgsDlv.setPayExchgRtCrncyAmt(new BigDecimal("0"));
				lgsDlv.setRealityDlvCst(new BigDecimal("0"));
				lgsDlv.setDlivyWaybilNoErpTrnsmisCd(GoodsEnum.NO.toString());
				lgsDlv.setWaybilNoErpTrnsmisCd(GoodsEnum.NO.toString());
				lgsDlv.setDlvCstBndMbdCd("CSTMR");

				// 2015-12-21 배송 수단 코드 물류배송지 -> 물류배송으로 이동 [AshA]
				log.debug("lgsDlvspEntity.getDlvMnCd() ===> " + lgsDlvspEntity.getDlvMnCd());
				
				lgsDlv.setDlvMnCd("APPN_HDRY");
				lgsDlv.setDlvComCd(DeliveryEnum.APPN_DLV_COM_CD.toString());
				
				if(key == 0){
					long sn = list.get(0).getDmstcDlvCstPlcSn();
					lgsDlv.setDmstcDlvCstPlcSn(sn);	
				}else{
					lgsDlv.setDmstcDlvCstPlcSn(key);	
				}
				
				// 물류배송
				lgsDlvRepository.insertLgsDlv(lgsDlv);

				LgsDlvHist lgsDlvHist = new LgsDlvHist();

				BeanUtils.copyProperties(lgsDlv, lgsDlvHist);

				lgsDlvHist.setHistDt(new Date());

				lgsDlvHistRepository.insertLgsDlvHist(lgsDlvHist);
				conditions.put("dlv_turn", dlvTurn);

				if (flag) {
					lgsDlivyDrctGodProcessor(list, conditions, regtrId, infOrdGodErpDstbs, lgsDlvspEntity.getShopId());
				}
			}

		}

	}

	public void lgsDlivyDrctGodProcessor(List<OrdGodExtend> list, Map<String, Object> map, String regtrId,
			List<InfOrdGodErpDstb> infOrdGodErpDstbs, String shopId) throws Exception {

		for (OrdGodExtend godEntity : list) {

			if (!(godEntity.getGodTpCd().equals(GoodsType.SET_GOD.toString()) || godEntity.getGodTpCd().equals(
					GoodsType.PCKAGE_GOD.toString()))) {

				lgsDlivyDrctGod(map, regtrId, infOrdGodErpDstbs, godEntity, null, shopId);

			}

			if (godEntity.getOrdGodList() != null) {
				for (OrdGodExtend ordGodExtend : godEntity.getOrdGodList()) {

					int ordGodTurn = godEntity.getOrdGodTurn();
					lgsDlivyDrctGod(map, regtrId, infOrdGodErpDstbs, ordGodExtend, ordGodTurn, shopId);

				}
			}

		}

	}

	public void lgsDlivyDrctGod(Map<String, Object> map, String regtrId, List<InfOrdGodErpDstb> infOrdGodErpDstbs, OrdGodExtend godEntity,
			Integer pckageGodTurn, String shopId) throws Exception, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		LgsDlivyDrctGod lgsDlivyDrctGod = new LgsDlivyDrctGod();
		LgsDlivyDrctGodHist lgsDlivyDrctGodHist = new LgsDlivyDrctGodHist();

		String dlivyDrctGodNo = getIdGenService().generateDBNumber(sqlSession1, "sq_lgs_dlivy_drct_god", "RO", DatabaseType.ORACLE);

		lgsDlivyDrctGod.setOrdNo((String) map.get("ord_no"));
		lgsDlivyDrctGod.setDlvPcupspTurn((Integer) map.get("dlv_pcupsp_turn"));
		lgsDlivyDrctGod.setDlvTurn((Integer) map.get("dlv_turn"));

		int ordGodTurn = godEntity.getOrdGodTurn();

		long qty = godEntity.getOrdQty();
		lgsDlivyDrctGod.setOrdGodTurn(ordGodTurn);
		lgsDlivyDrctGod.setPckageGodTurn(pckageGodTurn);

		lgsDlivyDrctGod.setDlivyDrctGodNo(dlivyDrctGodNo);
		lgsDlivyDrctGod.setDlivyDrctQty(qty);
		Functions.variAbleSetN(lgsDlivyDrctGod);
		if (godEntity.getGodTpCd().equals(GoodsType.PCHS_GFT.toString()) || godEntity.getGodTpCd().equals(GoodsType.CNVRS_GFT.toString())) {
			lgsDlivyDrctGod.setGftYn("Y");
		}
		else {
			lgsDlivyDrctGod.setGftYn("N");
		}

		if (godEntity.getPartmalSectCd().equals(GoodsEnum.GoodsPartmal.MCOM.toString()) && shopId == null) {

			lgsDlivyDrctGod.setDlvShopId("X30004");
		}
		else {

			lgsDlivyDrctGod.setDlvShopId(shopId);
		}

		lgsDlivyDrctGod.setDlivyDrctTpCd("ORD");
		lgsDlivyDrctGod.setDlvStatCd(DeliveryEnum.DLV_STAT_DLIVY_DRCT_WAIT.toString());
		lgsDlivyDrctGod.setRegtrId(regtrId);
		lgsDlivyDrctGod.setUdterId(regtrId);
		lgsDlivyDrctGodRepository.insertLgsDlivyDrctGod(lgsDlivyDrctGod);
		BeanUtils.copyProperties(lgsDlivyDrctGod, lgsDlivyDrctGodHist);

		Map<String, Object> conditions = Maps.newHashMap();
		conditions.put("dlivy_drct_god_no", dlivyDrctGodNo);

		int histTurn = getIdGenService().generateDBOrder(sqlSession1, "lgs_dlivy_drct_god_hist", "hist_turn", conditions,
				DatabaseType.ORACLE);
		lgsDlivyDrctGodHist.setHistTurn(histTurn);
		lgsDlivyDrctGodHistRepository.insertLgsDlivyDrctGodHist(lgsDlivyDrctGodHist);

		int count = 1;
		for (InfOrdGodErpDstb infOrdGodErpDstb : infOrdGodErpDstbs) {

			if (infOrdGodErpDstb.getOrdGodTurn() == ordGodTurn && count <= qty) {

				if (StringService.isEmpty(infOrdGodErpDstb.getDlivyDrctGodNo())) {
					infOrdGodErpDstb.setDlivyDrctGodNo(dlivyDrctGodNo);
					count++;
				}

			}

		}
	}


	////////////////////////////////////클레임 관련 method Start//////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * 클레임 수거지 등록
	 * 반품/교환 시 물류배송지, 물류배송 등록.
	 * 반품교환시 수거지가 같은경우 수거지를 한 곳으로 하기위한 로직 때문에 물류배송지 등록모듈을 같이 사용할 수 없음.
	 * @param lgsDlvspBoDTO
	 * @param ordNo
	 * @param clmNo
	 * @param dlvComTrnsmisTgtYn  	//택배사수거지시여부 : Y / N
	 * @param isMbrCheck			//통합회원,온라인회원 - true, 비회원 - false
	 * @param role					//FO/BO 구분
	 * @param regtrId
	 * @throws Exception
	 */
	public void lgsDlvspProcessorForRtrvl(LgsDlvspBoDTO lgsDlvspBoDTO
			                                , String ordNo
			                                , String clmNo
			                                , String dlvComTrnsmisTgtYn
			                                , Boolean isMbrCheck
			                                , String role
			                                , String regtrId
                                            , String ordTp
                                            , String callTp
                                            , String adminTpCd
                                            , String regtrShopId
			                                ) throws Exception {

		Map<String, Object> conditions = Maps.newHashMap();

		int index = 0;//취소배송비, 반품배송비, 교환배송비 저장용
		int dlvAdbukTurnBefore = 0;//배송지 중복체크용(같은 배송지인 경우 1번만 수행)
		int dlvAdbukTurnNow = 0;//배송지 중복체크용(같은 배송지인 경우 1번만 수행)
		int dlvPcupspTurn = 0;//배송지 중복체크용(같은 배송지인 경우 1번만 수행)

		String dlvspChgYn 			= null;	//주문배송지 변경여부
		
		for (LgsDlvspExtend lgsDlvspEntity : lgsDlvspBoDTO.getLgsDlvspList()) {

			dlvspChgYn = lgsDlvspEntity.getDlvspChgYn();
			
			//클레임수거지 인 경우
			if(StringService.equalsIgnoreCase(lgsDlvspEntity.getDlvPcupspSectCd(), "CLM_PCUPSP")) {
				//반품시 수거지가 같은경우 수거지를 한 곳으로 하기위한 로직
				if(index == 0){
					//화면에서 입력받은 배송지순번 추출
					dlvAdbukTurnBefore = lgsDlvspEntity.getDlvAdbukTurn();
				} else {
					dlvAdbukTurnNow = lgsDlvspEntity.getDlvAdbukTurn();
				}

				//물류배송지가 같으면 배송지순번을 다음 배송지로 복사
				if(dlvAdbukTurnBefore == dlvAdbukTurnNow) {

					lgsDlvspEntity.setDlvPcupspTurn(dlvPcupspTurn);

					//추가 반품배송지 합치는 기능 - 물류배송등록 시 필요
					lgsDlvspEntity.setOrdNo(ordNo);
					lgsDlvspEntity.setClmNo(clmNo);

				} else {

					/* 뮬류배송지 start ***********************************************************************************************/

					conditions.put("ord_no", ordNo);
					dlvPcupspTurn = getIdGenService().generateDBOrder(sqlSession1, "lgs_dlvsp", "dlv_pcupsp_turn", conditions, DatabaseType.ORACLE);

					// 물류배송지 모두 insert 후 물류배송 insert 시 화면에서 생성된 물류배송DTO(LgsDlvspTmpList) 에 물류배송지 순번을 설정해준다.
					for (LgsDlvspExtend lgsDlvspTmp : lgsDlvspBoDTO.getLgsDlvspTmpList()) {
						// 반품상품정보의 원배송지순번과 반품배송지정보의 원배송지순번이 같은 경우 물류배송지순번 Set.
						if(StringService.equalsIgnoreCase(lgsDlvspEntity.getDlvPcupspSectCd(), "CLM_PCUPSP")) {
							if (lgsDlvspEntity.getDlvAdbukTurn().intValue() == lgsDlvspTmp.getDlvAdbukTurn().intValue()) {
								lgsDlvspTmp.setDlvPcupspTurn(dlvPcupspTurn);

							}
						}
					}

					//BackOffice
					if(StringService.equalsIgnoreCase(role, "B")) {
						//샵마스터
						if(StringService.equalsIgnoreCase(adminTpCd, "SHOP_MASTER")) {
				    		SysShopDTO paramData = new SysShopDTO();
				    		paramData.getSearch().setShopId(regtrShopId); // 매장ID
				    		SysShopResult result = sysShopMgrRepository.selectSysShopDetail(paramData);

				    		lgsDlvspEntity.setAddrseNm(                 result.getSysShop().getRprstivNm()              );
				    		lgsDlvspEntity.setAddrseNationCd(           "KR"                                            );
				    		lgsDlvspEntity.setAddrSectCd(               "RD_ADDR"                                       );
				    		lgsDlvspEntity.setAddrsePostNo(             result.getSysShop().getPostNo()                 );
				    		lgsDlvspEntity.setAddrseBaseAddr(           result.getSysShop().getBaseAddr()               );
				    		lgsDlvspEntity.setAddrseDetailAddr(         result.getSysShop().getDetailAddr()             );
				    		lgsDlvspEntity.setAddrseMobilNationNo(      result.getSysShop().getRprstivMobilNationNo()   );
				    		lgsDlvspEntity.setAddrseMobilAreaNo(        result.getSysShop().getRprstivMobilAreaNo()     );
				    		lgsDlvspEntity.setAddrseMobilTlofNo(        result.getSysShop().getRprstivMobilTlofNo()     );
				    		lgsDlvspEntity.setAddrseMobilTlofWthnNo(    result.getSysShop().getRprstivMobilTlofWthnNo() );
				    		lgsDlvspEntity.setAddrseTelNationNo(        result.getSysShop().getShopTelNationNo()        );
				    		lgsDlvspEntity.setAddrseTelAreaNo(          result.getSysShop().getShopTelAreaNo()          );
				    		lgsDlvspEntity.setAddrseTelTlofNo(          result.getSysShop().getShopTelTlofNo()          );
				    		lgsDlvspEntity.setAddrseTelTlofWthnNo(      result.getSysShop().getShopTelTlofWthnNo()      );

						} else {

							if(StringService.equalsIgnoreCase(callTp, "DLV")) {
								//매장픽업주문의 경우 주문당시 생성된 배송지정보를 넣는다.
								LgsDlvspExtend lgsDlvsp = new LgsDlvspExtend();
								lgsDlvsp.setOrdNo(ordNo);
								lgsDlvsp.setDlvPcupspTurn(lgsDlvspEntity.getDlvAdbukTurn()- Integer.parseInt(OrderClaimEnum.DLV_ADBUK_TURN_PREFIX_ORD.toString()));	// 2016.01.26 by cannon
								lgsDlvsp.setMaskingYn("N");
								List<LgsDlvspExtend> lgsDlvspExtendList = deliveryExternalRepository.selectDlvspListForOrd(lgsDlvsp);

								lgsDlvspEntity.setAddrseNm(             lgsDlvspExtendList.get(0).getAddrseNm()                   );
								lgsDlvspEntity.setPkupShopId(           lgsDlvspExtendList.get(0).getPkupShopId()                 );	// 매장픽업주문의 경우 추가 - 픽업 매장 ID
								lgsDlvspEntity.setPkupShopBrndId(       lgsDlvspExtendList.get(0).getPkupShopBrndId()             );	// 매장픽업주문의 경우 추가 - 픽업 매장 브랜드 ID
								lgsDlvspEntity.setPkupShopVisitDate(    lgsDlvspExtendList.get(0).getPkupShopVisitDate()          );	// 매장픽업주문의 경우 추가 - 픽업 매장 방문 일자
								lgsDlvspEntity.setAddrSectCd(           lgsDlvspExtendList.get(0).getAddrSectCd()                 );
								lgsDlvspEntity.setAddrseNationCd(       lgsDlvspExtendList.get(0).getAddrseNationCd()             );
								lgsDlvspEntity.setAddrsePostNo(         lgsDlvspExtendList.get(0).getAddrsePostNo()               );
								lgsDlvspEntity.setAddrseBaseAddr(       lgsDlvspExtendList.get(0).getAddrseBaseAddr()             );
								lgsDlvspEntity.setAddrseDetailAddr(     lgsDlvspExtendList.get(0).getAddrseDetailAddr()           );
								lgsDlvspEntity.setAddrseMobilNationNo(  lgsDlvspExtendList.get(0).getAddrseMobilNationNo()        );
								lgsDlvspEntity.setAddrseMobilAreaNo(    lgsDlvspExtendList.get(0).getAddrseMobilAreaNo()          );
								lgsDlvspEntity.setAddrseMobilTlofNo(    lgsDlvspExtendList.get(0).getAddrseMobilTlofNo()          );
								lgsDlvspEntity.setAddrseMobilTlofWthnNo(lgsDlvspExtendList.get(0).getAddrseMobilTlofWthnNo()      );
								lgsDlvspEntity.setAddrseTelNationNo(    lgsDlvspExtendList.get(0).getAddrseTelNationNo()          );
								lgsDlvspEntity.setAddrseTelAreaNo(      lgsDlvspExtendList.get(0).getAddrseTelAreaNo()            );
								lgsDlvspEntity.setAddrseTelTlofNo(      lgsDlvspExtendList.get(0).getAddrseTelTlofNo()            );
								lgsDlvspEntity.setAddrseTelTlofWthnNo(  lgsDlvspExtendList.get(0).getAddrseTelTlofWthnNo()        );

							} else {
								
								if("Y".equals(dlvspChgYn)){
									// 배송지 수정인 경우 화면 데이터 그대로 접수
								} else {
									//비회원수정배송지	: 1 ~ 
									//주문배송지	: 10000 ~
									//회원배송지 	: 20000 ~
									if(lgsDlvspEntity.getDlvAdbukTurn() > Integer.parseInt(OrderClaimEnum.DLV_ADBUK_TURN_PREFIX_MBR.toString())){
										MbrDlvsp mbrDlvsp = new MbrDlvsp();
										mbrDlvsp.setMbrNo(lgsDlvspEntity.getMbrNo());
										mbrDlvsp.setDlvAdbukTurn(lgsDlvspEntity.getDlvAdbukTurn() - Integer.parseInt(OrderClaimEnum.DLV_ADBUK_TURN_PREFIX_MBR.toString()));									
										mbrDlvsp = mbrDlvspRepository.selectMbrDlvsp(mbrDlvsp);
										Functions.copyProperties(mbrDlvsp, lgsDlvspEntity);
	
										lgsDlvspEntity.setAddrSectCd(           mbrDlvsp.getDlvAddrSectCd()			);
										lgsDlvspEntity.setAddrseNationCd(       mbrDlvsp.getNationCd()             	);
									}else{
										LgsDlvspExtend lgsDlvsp = new LgsDlvspExtend();
										lgsDlvsp.setOrdNo(ordNo);
										lgsDlvsp.setDlvPcupspTurn(lgsDlvspEntity.getDlvAdbukTurn() - Integer.parseInt(OrderClaimEnum.DLV_ADBUK_TURN_PREFIX_ORD.toString()));
										lgsDlvsp.setMaskingYn("N");
										List<LgsDlvspExtend> lgsDlvspExtendList = deliveryExternalRepository.selectDlvspListForOrd(lgsDlvsp);
	
										lgsDlvspEntity.setAddrseNm(             lgsDlvspExtendList.get(0).getAddrseNm()                   );
	
										lgsDlvspEntity.setAddrSectCd(           lgsDlvspExtendList.get(0).getAddrSectCd()                 );
										lgsDlvspEntity.setAddrseNationCd(       lgsDlvspExtendList.get(0).getAddrseNationCd()             );
										lgsDlvspEntity.setAddrsePostNo(         lgsDlvspExtendList.get(0).getAddrsePostNo()               );
										lgsDlvspEntity.setAddrseBaseAddr(       lgsDlvspExtendList.get(0).getAddrseBaseAddr()             );
										lgsDlvspEntity.setAddrseDetailAddr(     lgsDlvspExtendList.get(0).getAddrseDetailAddr()           );
										lgsDlvspEntity.setAddrseMobilNationNo(  lgsDlvspExtendList.get(0).getAddrseMobilNationNo()        );
										lgsDlvspEntity.setAddrseMobilAreaNo(    lgsDlvspExtendList.get(0).getAddrseMobilAreaNo()          );
										lgsDlvspEntity.setAddrseMobilTlofNo(    lgsDlvspExtendList.get(0).getAddrseMobilTlofNo()          );
										lgsDlvspEntity.setAddrseMobilTlofWthnNo(lgsDlvspExtendList.get(0).getAddrseMobilTlofWthnNo()      );
										lgsDlvspEntity.setAddrseTelNationNo(    lgsDlvspExtendList.get(0).getAddrseTelNationNo()          );
										lgsDlvspEntity.setAddrseTelAreaNo(      lgsDlvspExtendList.get(0).getAddrseTelAreaNo()            );
										lgsDlvspEntity.setAddrseTelTlofNo(      lgsDlvspExtendList.get(0).getAddrseTelTlofNo()            );
										lgsDlvspEntity.setAddrseTelTlofWthnNo(  lgsDlvspExtendList.get(0).getAddrseTelTlofWthnNo()        );
	
									}
								}
							
							}
						}
					} else {

						//FrontOffice
						if(StringService.equalsIgnoreCase(dlvspChgYn, "Y")
								&&(lgsDlvspEntity!=null&&lgsDlvspEntity.getAddrseBaseAddr()!=null&&!"".equals(lgsDlvspEntity.getAddrseBaseAddr().trim()))
								) {
							log.debug("################################################## 배송지가 변경여부Y 수거지 있음");
							

						} else {
							if(StringService.equalsIgnoreCase(dlvspChgYn, "Y")){
								log.info("################################################## 배송지가 변경여부Y 이나 수거지없음");
								log.info(lgsDlvspBoDTO.toString());
							}

							//일반주문의 반퓸인 경우
							//FO 배송지정보
							LgsDlvspExtend lgsDlvsp = new LgsDlvspExtend();
							lgsDlvsp.setOrdNo(ordNo);
							lgsDlvsp.setDlvPcupspTurn(lgsDlvspEntity.getDlvAdbukTurn());
							List<LgsDlvspExtend> lgsDlvspExtendList = deliveryExternalRepository.selectDlvspListForOrd(lgsDlvsp);
							
							if(lgsDlvspExtendList.isEmpty()){
								throw new OrderCompleteFailException("ord.error.empty_lgs_dlvsp", null);
							}
	
							lgsDlvspEntity.setAddrseNm(             lgsDlvspExtendList.get(0).getAddrseNm()                   );
							lgsDlvspEntity.setAddrSectCd(           lgsDlvspExtendList.get(0).getAddrSectCd()                 );
							lgsDlvspEntity.setAddrseNationCd(       lgsDlvspExtendList.get(0).getAddrseNationCd()             );
							lgsDlvspEntity.setAddrsePostNo(         lgsDlvspExtendList.get(0).getAddrsePostNo()               );
							lgsDlvspEntity.setAddrseBaseAddr(       lgsDlvspExtendList.get(0).getAddrseBaseAddr()             );
							lgsDlvspEntity.setAddrseDetailAddr(     lgsDlvspExtendList.get(0).getAddrseDetailAddr()           );
							lgsDlvspEntity.setAddrseMobilNationNo(  lgsDlvspExtendList.get(0).getAddrseMobilNationNo()        );
							lgsDlvspEntity.setAddrseMobilAreaNo(    lgsDlvspExtendList.get(0).getAddrseMobilAreaNo()          );
							lgsDlvspEntity.setAddrseMobilTlofNo(    lgsDlvspExtendList.get(0).getAddrseMobilTlofNo()          );
							lgsDlvspEntity.setAddrseMobilTlofWthnNo(lgsDlvspExtendList.get(0).getAddrseMobilTlofWthnNo()      );
							lgsDlvspEntity.setAddrseTelNationNo(    lgsDlvspExtendList.get(0).getAddrseTelNationNo()          );
							lgsDlvspEntity.setAddrseTelAreaNo(      lgsDlvspExtendList.get(0).getAddrseTelAreaNo()            );
							lgsDlvspEntity.setAddrseTelTlofNo(      lgsDlvspExtendList.get(0).getAddrseTelTlofNo()            );
							lgsDlvspEntity.setAddrseTelTlofWthnNo(  lgsDlvspExtendList.get(0).getAddrseTelTlofWthnNo()        );
						}
					}

					lgsDlvspEntity.setDlvPcupspTurn(dlvPcupspTurn);
					lgsDlvspEntity.setOrdNo(ordNo);
					lgsDlvspEntity.setClmNo(clmNo);
					lgsDlvspEntity.setRegtrId(regtrId);
					lgsDlvspEntity.setUdterId(regtrId);

					if(StringService.equalsIgnoreCase(callTp, "DLV") || StringService.equalsIgnoreCase(callTp, "OFF_SHOP")) {
						lgsDlvspEntity.setDlvSectCd("PKUP_DLV");	//픽업배송
					} else {
						lgsDlvspEntity.setDlvSectCd("GNRL_DLV");	//일반배송
					}
					
					if (StringService.isEmpty(lgsDlvspEntity.getAddrseNationCd())) {
						lgsDlvspEntity.setAddrseNationCd("KR");
					}

					// 물류 배송지
					lgsDlvspRepository.insertLgsDlvsp(lgsDlvspEntity);

					LgsDlvspHist lgsDlvspHist = new LgsDlvspHist();
					BeanUtils.copyProperties(lgsDlvspEntity, lgsDlvspHist);
					lgsDlvspHist.setHistDt(new Date());

					// 물류 배송지 이력
					lgsDlvspHistRepository.insertLgsDlvspHist(lgsDlvspHist);

					/* 뮬류배송지 end **************************************************************************************************/

					index++;
				}
			}
		}

		/* 뮬류배송 start  *************************************************************************************************/
		
		Ord ord = new Ord();
    	ord.setOrdNo(ordNo);
    	ord = ordRepository.selectOrd(ord);

    	/*
		물류배송 insert 로직
		- 물류상품의 국내 배송비 정책 일련번호 별로 배송순번을 채번하고 있음.
		- 화면에서는 배송지별 취소배송비, 반품배송비, 교환배송비를 보여줘야 함.

		공통로직
		- 업체별(국내 배송비 정책 일련번호) 취소배송비, 반품배송비, 교환배송비 를 구하는 로직
		- 배송지별 취소배송비, 반품배송비, 교환배송비 를 구하는 로직 <<< 업체별 금액을 sum 하여 보여주면 될 것 같음.
		*/
    	
		for (LgsDlvspExtend lgsDlvspTmp2 : lgsDlvspBoDTO.getLgsDlvspTmpList())
		{
			if(StringService.equalsIgnoreCase(lgsDlvspTmp2.getDlvPcupspSectCd(), "CLM_PCUPSP"))
			{
				//화면에서 넘겨받은 물류배송 리스트 만큼 생성
				for (LgsDlv lgsDlvTmp : lgsDlvspTmp2.getLgsDlvList()) {
					LgsDlv lgsDlv = new LgsDlv();

					conditions.put("dlv_pcupsp_turn", lgsDlvspTmp2.getDlvPcupspTurn());
					int dlvTurn = getIdGenService().generateDBOrder(sqlSession1, "lgs_dlv", "dlv_turn", conditions, DatabaseType.ORACLE);

					Functions.variAbleSetN(lgsDlv);
					lgsDlv.setOrdNo(ordNo);															//주문번호
					lgsDlv.setDlvPcupspTurn(lgsDlvspTmp2.getDlvPcupspTurn());						//물류배송지순번
					lgsDlv.setDlvTurn(dlvTurn);														//물류배송순번
					lgsDlv.setClmNo(clmNo);															//클레임번호

					lgsDlv.setDmstcDlvCstPlcSn(lgsDlvTmp.getDmstcDlvCstPlcSn());    				//국내 배송비 정책 일련번호
					lgsDlv.setOvseaDlvDmstcDlvCstPlcSn(lgsDlvTmp.getOvseaDlvDmstcDlvCstPlcSn());    //해외 배송 국내 배송비 정책 일련번호
					lgsDlv.setOvseaDlvCstPlcSn(lgsDlvTmp.getOvseaDlvCstPlcSn());    				//해외 배송비 정책 일련번호

					lgsDlv.setRegtrId(regtrId);
					lgsDlv.setUdterId(regtrId);
					lgsDlv.setCrncyCd(lgsDlvTmp.getCrncyCd());										//통화 코드

					lgsDlv.setStdrCrncyAmt(lgsDlvTmp.getStdrCrncyAmt());    						//기준 통화 금액
					lgsDlv.setPayExchgRtCrncyAmt(lgsDlvTmp.getPayExchgRtCrncyAmt());				//결제 환율 통화 금액

					lgsDlv.setRealityDlvCst(lgsDlvTmp.getRealityDlvCst());    						//실제 배송비
					lgsDlv.setPlcDlvCst(lgsDlvTmp.getPlcDlvCst());    								//정책 배송비

					lgsDlv.setCnclDlvCst(lgsDlvTmp.getCnclDlvCst());    							//취소 배송비
					
					lgsDlv.setRtgodDlvCst(lgsDlvTmp.getRtgodDlvCst());    						    //반품 배송비
					lgsDlv.setExchgDlvCst(lgsDlvTmp.getExchgDlvCst());    							//교환 배송비

					lgsDlv.setWaybilNoErpTrnsmisCd(GoodsEnum.NO.toString());
					lgsDlv.setDlvCstBndMbdCd(lgsDlvTmp.getDlvCstBndMbdCd());    					//배송비 부담 주체 코드
					
					if(StringService.equalsIgnoreCase(callTp, "DLV") || StringService.equalsIgnoreCase(callTp, "OFF_SHOP")) {
						lgsDlv.setDlvMnCd("SHOP_PKUP");												//배송수단코드
					} else {
						lgsDlv.setDlvMnCd(lgsDlvspTmp2.getDlvMnCd());								//배송수단코드 - 수정후 교환접수에서 배송수단코드가 등록이 않되는 현상 때문에 변경
					}
					
					// 2018/11/01 교환시 기존 LGS_DLV의 택배사를 따라가기 때문에 CJ로 변경.
					lgsDlv.setDlvComCd(DeliveryEnum.APPN_DLV_COM_CD.toString());									//배송업체코드

					//택배사수거지시제외 인 경우 배송업체전송대상여부를 N 으로 설정. 화면에서 Y/N 으로 받음.
                    lgsDlv.setDlvComTrnsmisTgtYn(dlvComTrnsmisTgtYn);
                    lgsDlv.setDlvComTrnsmisYn("N");
                    lgsDlv.setDlivyWaybilNoErpTrnsmisCd("N");
                    
                    //물류배송
					lgsDlvRepository.insertLgsDlv(lgsDlv);

					LgsDlvHist lgsDlvHist = new LgsDlvHist();
					BeanUtils.copyProperties(lgsDlv, lgsDlvHist);
					lgsDlvHist.setHistDt(new Date());
					//물류배송이력
					lgsDlvHistRepository.insertLgsDlvHist(lgsDlvHist);

				}
			}
		}

		/* 뮬류배송 end ***************************************************************************************************/
	}

	/**
	 * 클레임 배송지 등록
	 * 교환 시 물류배송지, 물류배송 등록.
	 * 반품시 수거지가 같은경우 수거지를 한 곳으로 하기위한 로직 때문에 물류배송지 등록모듈을 같이 사용할 수 없음.
	 * @param lgsDlvspBoDTO
	 * @param ordNo
	 * @param clmNo
	 * @param dlvComTrnsmisTgtYn  //택배사수거지시여부 : Y / N
	 * @param regtrId
	 * @throws Exception
	 */
	public void lgsDlvspProcessorForDlivy(LgsDlvspBoDTO lgsDlvspBoDTO
			                                , String ordNo
			                                , String clmNo
			                                , String dlvComTrnsmisTgtYn
			                                , Boolean isMbrCheck
			                                , String role
			                                , String regtrId
                                            , String ordTp
                                            , String callTp
			                                ) throws Exception {

		Map<String, Object> conditions = Maps.newHashMap();

		int dlvPcupspTurn = 0;//배송지 중복체크용(같은 배송지인 경우 1번만 수행)
		String dlvspChgYn 			= null;	//주문배송지 변경여부

		for (LgsDlvspExtend lgsDlvspEntity : lgsDlvspBoDTO.getLgsDlvspList()) {
		    
			/*
			 * 배송지변경 여부
			 */
			dlvspChgYn = lgsDlvspEntity.getDlvspChgYn();

			//배송지 인 경우
			if(StringService.equalsIgnoreCase(lgsDlvspEntity.getDlvPcupspSectCd(), "CLM_DLVSP")) {
				
				/* 뮬류배송지 start ***********************************************************************************************/

				conditions.put("ord_no", ordNo);
				dlvPcupspTurn = getIdGenService().generateDBOrder(sqlSession1, "lgs_dlvsp", "dlv_pcupsp_turn", conditions, DatabaseType.ORACLE);

				// 물류배송지 모두 insert 후 물류배송 insert 시 화면에서 생성된 물류배송DTO 에 물류배송지 순번을 설정해준다.
				for (LgsDlvspExtend lgsDlvspTmp : lgsDlvspBoDTO.getLgsDlvspTmpList()) {
					//ncp2 - 반품상품정보의 원배송지순번과 반품배송지정보의 원배송지순번이 같은 경우 물류배송지순번 Set.
					if(StringService.equalsIgnoreCase(lgsDlvspEntity.getDlvPcupspSectCd(), "CLM_DLVSP")) {
						if (lgsDlvspEntity.getDlvAdbukTurn().intValue() == lgsDlvspTmp.getDlvAdbukTurn().intValue()) {
							lgsDlvspTmp.setDlvPcupspTurn(dlvPcupspTurn);

						}
					}
				}

				if(StringService.equalsIgnoreCase(role, "B")) {

					if(StringService.equalsIgnoreCase(callTp, "DLV") || StringService.equalsIgnoreCase(callTp, "OFF_SHOP")) {
						
						//매장픽업주문의 경우 주문당시 생성된 배송지정보를 넣는다.
						LgsDlvspExtend lgsDlvsp = new LgsDlvspExtend();
						lgsDlvsp.setOrdNo(ordNo);
						lgsDlvsp.setDlvPcupspTurn(lgsDlvspEntity.getDlvAdbukTurn()- Integer.parseInt(OrderClaimEnum.DLV_ADBUK_TURN_PREFIX_ORD.toString()));	// 2016.01.26 by cannon
						lgsDlvsp.setMaskingYn("N");
						List<LgsDlvspExtend> lgsDlvspExtendList = deliveryExternalRepository.selectDlvspListForOrd(lgsDlvsp);

						lgsDlvspEntity.setAddrseNm(             lgsDlvspExtendList.get(0).getAddrseNm()                   );
						lgsDlvspEntity.setPkupShopId(           lgsDlvspExtendList.get(0).getPkupShopId()                 );	// 매장픽업주문의 경우 추가 - 픽업 매장 ID
						lgsDlvspEntity.setPkupShopBrndId(       lgsDlvspExtendList.get(0).getPkupShopBrndId()             );	// 매장픽업주문의 경우 추가 - 픽업 매장 브랜드 ID
						lgsDlvspEntity.setPkupShopVisitDate(    lgsDlvspExtendList.get(0).getPkupShopVisitDate()          );	// 매장픽업주문의 경우 추가 - 픽업 매장 방문 일자
						lgsDlvspEntity.setAddrSectCd(           lgsDlvspExtendList.get(0).getAddrSectCd()                 );
						lgsDlvspEntity.setAddrseNationCd(       lgsDlvspExtendList.get(0).getAddrseNationCd()             );
						lgsDlvspEntity.setAddrsePostNo(         lgsDlvspExtendList.get(0).getAddrsePostNo()               );
						lgsDlvspEntity.setAddrseBaseAddr(       lgsDlvspExtendList.get(0).getAddrseBaseAddr()             );
						lgsDlvspEntity.setAddrseDetailAddr(     lgsDlvspExtendList.get(0).getAddrseDetailAddr()           );
						lgsDlvspEntity.setAddrseMobilNationNo(  lgsDlvspExtendList.get(0).getAddrseMobilNationNo()        );
						lgsDlvspEntity.setAddrseMobilAreaNo(    lgsDlvspExtendList.get(0).getAddrseMobilAreaNo()          );
						lgsDlvspEntity.setAddrseMobilTlofNo(    lgsDlvspExtendList.get(0).getAddrseMobilTlofNo()          );
						lgsDlvspEntity.setAddrseMobilTlofWthnNo(lgsDlvspExtendList.get(0).getAddrseMobilTlofWthnNo()      );
						lgsDlvspEntity.setAddrseTelNationNo(    lgsDlvspExtendList.get(0).getAddrseTelNationNo()          );
						lgsDlvspEntity.setAddrseTelAreaNo(      lgsDlvspExtendList.get(0).getAddrseTelAreaNo()            );
						lgsDlvspEntity.setAddrseTelTlofNo(      lgsDlvspExtendList.get(0).getAddrseTelTlofNo()            );
						lgsDlvspEntity.setAddrseTelTlofWthnNo(  lgsDlvspExtendList.get(0).getAddrseTelTlofWthnNo()        );

					} else {

						if("Y".equals(dlvspChgYn)){
							// 배송지 수정인 경우 화면 데이터 그대로 접수
						} else {
							//비회원수정배송지	: 1 ~ 
							//주문배송지	: 10000 ~
							//회원배송지 	: 20000 ~
							if(lgsDlvspEntity.getDlvAdbukTurn() > Integer.parseInt(OrderClaimEnum.DLV_ADBUK_TURN_PREFIX_MBR.toString())){
								MbrDlvsp mbrDlvsp = new MbrDlvsp();
								mbrDlvsp.setMbrNo(lgsDlvspEntity.getMbrNo());
								mbrDlvsp.setDlvAdbukTurn(lgsDlvspEntity.getDlvAdbukTurn() - Integer.parseInt(OrderClaimEnum.DLV_ADBUK_TURN_PREFIX_MBR.toString()));									
								mbrDlvsp = mbrDlvspRepository.selectMbrDlvsp(mbrDlvsp);
								Functions.copyProperties(mbrDlvsp, lgsDlvspEntity);
	
								lgsDlvspEntity.setAddrSectCd(           mbrDlvsp.getDlvAddrSectCd()			);
								lgsDlvspEntity.setAddrseNationCd(       mbrDlvsp.getNationCd()             	);
							}else{
								LgsDlvspExtend lgsDlvsp = new LgsDlvspExtend();
								lgsDlvsp.setOrdNo(ordNo);
								lgsDlvsp.setDlvPcupspTurn(lgsDlvspEntity.getDlvAdbukTurn() - Integer.parseInt(OrderClaimEnum.DLV_ADBUK_TURN_PREFIX_ORD.toString()));
								lgsDlvsp.setMaskingYn("N");
								List<LgsDlvspExtend> lgsDlvspExtendList = deliveryExternalRepository.selectDlvspListForOrd(lgsDlvsp);
	
								lgsDlvspEntity.setAddrseNm(             lgsDlvspExtendList.get(0).getAddrseNm()                   );
								lgsDlvspEntity.setAddrSectCd(           lgsDlvspExtendList.get(0).getAddrSectCd()                 );
								lgsDlvspEntity.setAddrseNationCd(       lgsDlvspExtendList.get(0).getAddrseNationCd()             );
								lgsDlvspEntity.setAddrsePostNo(         lgsDlvspExtendList.get(0).getAddrsePostNo()               );
								lgsDlvspEntity.setAddrseBaseAddr(       lgsDlvspExtendList.get(0).getAddrseBaseAddr()             );
								lgsDlvspEntity.setAddrseDetailAddr(     lgsDlvspExtendList.get(0).getAddrseDetailAddr()           );
								lgsDlvspEntity.setAddrseMobilNationNo(  lgsDlvspExtendList.get(0).getAddrseMobilNationNo()        );
								lgsDlvspEntity.setAddrseMobilAreaNo(    lgsDlvspExtendList.get(0).getAddrseMobilAreaNo()          );
								lgsDlvspEntity.setAddrseMobilTlofNo(    lgsDlvspExtendList.get(0).getAddrseMobilTlofNo()          );
								lgsDlvspEntity.setAddrseMobilTlofWthnNo(lgsDlvspExtendList.get(0).getAddrseMobilTlofWthnNo()      );
								lgsDlvspEntity.setAddrseTelNationNo(    lgsDlvspExtendList.get(0).getAddrseTelNationNo()          );
								lgsDlvspEntity.setAddrseTelAreaNo(      lgsDlvspExtendList.get(0).getAddrseTelAreaNo()            );
								lgsDlvspEntity.setAddrseTelTlofNo(      lgsDlvspExtendList.get(0).getAddrseTelTlofNo()            );
								lgsDlvspEntity.setAddrseTelTlofWthnNo(  lgsDlvspExtendList.get(0).getAddrseTelTlofWthnNo()        );
							}
						}
						
					}
				} else {
					
					if(StringService.equalsIgnoreCase(dlvspChgYn, "Y")) {
						
					} else {
						//일반주문의 반퓸인 경우
						//FO 배송지정보
						LgsDlvspExtend lgsDlvsp = new LgsDlvspExtend();
						lgsDlvsp.setOrdNo(ordNo);
						lgsDlvsp.setDlvPcupspTurn(lgsDlvspEntity.getDlvAdbukTurn());
						List<LgsDlvspExtend> lgsDlvspExtendList = deliveryExternalRepository.selectDlvspListForOrd(lgsDlvsp);
	
						lgsDlvspEntity.setAddrseNm(             lgsDlvspExtendList.get(0).getAddrseNm()                   );
						lgsDlvspEntity.setAddrSectCd(           lgsDlvspExtendList.get(0).getAddrSectCd()                 );
						lgsDlvspEntity.setAddrseNationCd(       lgsDlvspExtendList.get(0).getAddrseNationCd()             );
						lgsDlvspEntity.setAddrsePostNo(         lgsDlvspExtendList.get(0).getAddrsePostNo()               );
						lgsDlvspEntity.setAddrseBaseAddr(       lgsDlvspExtendList.get(0).getAddrseBaseAddr()             );
						lgsDlvspEntity.setAddrseDetailAddr(     lgsDlvspExtendList.get(0).getAddrseDetailAddr()           );
						lgsDlvspEntity.setAddrseMobilNationNo(  lgsDlvspExtendList.get(0).getAddrseMobilNationNo()        );
						lgsDlvspEntity.setAddrseMobilAreaNo(    lgsDlvspExtendList.get(0).getAddrseMobilAreaNo()          );
						lgsDlvspEntity.setAddrseMobilTlofNo(    lgsDlvspExtendList.get(0).getAddrseMobilTlofNo()          );
						lgsDlvspEntity.setAddrseMobilTlofWthnNo(lgsDlvspExtendList.get(0).getAddrseMobilTlofWthnNo()      );
						lgsDlvspEntity.setAddrseTelNationNo(    lgsDlvspExtendList.get(0).getAddrseTelNationNo()          );
						lgsDlvspEntity.setAddrseTelAreaNo(      lgsDlvspExtendList.get(0).getAddrseTelAreaNo()            );
						lgsDlvspEntity.setAddrseTelTlofNo(      lgsDlvspExtendList.get(0).getAddrseTelTlofNo()            );
						lgsDlvspEntity.setAddrseTelTlofWthnNo(  lgsDlvspExtendList.get(0).getAddrseTelTlofWthnNo()        );
					}
				}

				lgsDlvspEntity.setDlvPcupspTurn(dlvPcupspTurn);
				lgsDlvspEntity.setOrdNo(ordNo);
				lgsDlvspEntity.setClmNo(clmNo);
				lgsDlvspEntity.setRegtrId(regtrId);
				lgsDlvspEntity.setUdterId(regtrId);

				//픽업매장교환시
				if(StringService.equalsIgnoreCase(callTp, "DLV") || StringService.equalsIgnoreCase(callTp, "OFF_SHOP")) {
					lgsDlvspEntity.setDlvSectCd("PKUP_DLV");		//배송 구분 코드 - 픽업 배송 : PKUP_DLV
				} else {
					lgsDlvspEntity.setDlvSectCd("GNRL_DLV");		//배송 구분 코드 - 일반 배송 : GNRL_DLV
				}

				if (StringService.isEmpty(lgsDlvspEntity.getAddrseNationCd())) {
					lgsDlvspEntity.setAddrseNationCd("KR");
				}
				
				// 물류 배송지
				lgsDlvspRepository.insertLgsDlvsp(lgsDlvspEntity);

				LgsDlvspHist lgsDlvspHist = new LgsDlvspHist();

				BeanUtils.copyProperties(lgsDlvspEntity, lgsDlvspHist);

				lgsDlvspHist.setHistDt(new Date());

				// 물류 배송지 이력
				lgsDlvspHistRepository.insertLgsDlvspHist(lgsDlvspHist);

				/* 뮬류배송지 end **************************************************************************************************/

			}

		}

		/*
		물류배송 insert 로직
		- 물류상품의 국내 배송비 정책 일련번호 별로 배송순번을 채번하고 있음.
		- 화면에서는 배송지별 취소배송비, 반품배송비, 교환배송비를 보여줘야 함.

		공통로직
		- 업체별(국내 배송비 정책 일련번호) 취소배송비, 반품배송비, 교환배송비 를 구하는 로직
		- 배송지별 취소배송비, 반품배송비, 교환배송비 를 구하는 로직 <<< 업체별 금액을 sum 하여 보여주면 될 것 같음.
		*/

		for (LgsDlvspExtend lgsDlvspTmp2 : lgsDlvspBoDTO.getLgsDlvspTmpList())
		{
			if(StringService.equalsIgnoreCase(lgsDlvspTmp2.getDlvPcupspSectCd(), "CLM_DLVSP"))
			{

				//화면에서 넘겨받은 물류배송 리스트 만큼 생성
				for (LgsDlv lgsDlvTmp : lgsDlvspTmp2.getLgsDlvList()) {
					LgsDlv lgsDlv = new LgsDlv();

					conditions.put("dlv_pcupsp_turn", lgsDlvspTmp2.getDlvPcupspTurn());
					int dlvTurn = getIdGenService().generateDBOrder(sqlSession1, "lgs_dlv", "dlv_turn", conditions, DatabaseType.ORACLE);

					// 교환 출고상품 데이터 생성시 배송순번이 필요함.
					lgsDlvTmp.setOrdNo(ordNo);
					lgsDlvTmp.setDlvPcupspTurn(lgsDlvspTmp2.getDlvPcupspTurn());
					lgsDlvTmp.setClmNo(clmNo);
					lgsDlvTmp.setDlvTurn(dlvTurn);

					Functions.variAbleSetN(lgsDlv);
					lgsDlv.setOrdNo(ordNo);															//주문번호
					lgsDlv.setDlvPcupspTurn(lgsDlvspTmp2.getDlvPcupspTurn());						//물류배송지순번
					lgsDlv.setDlvTurn(dlvTurn);														//물류배송순번
					lgsDlv.setClmNo(clmNo);															//클레임번호

					lgsDlv.setDmstcDlvCstPlcSn(lgsDlvTmp.getDmstcDlvCstPlcSn());    				//국내 배송비 정책 일련번호
					lgsDlv.setOvseaDlvDmstcDlvCstPlcSn(lgsDlvTmp.getOvseaDlvDmstcDlvCstPlcSn());    //해외 배송 국내 배송비 정책 일련번호
					lgsDlv.setOvseaDlvCstPlcSn(lgsDlvTmp.getOvseaDlvCstPlcSn());    				//해외 배송비 정책 일련번호

					lgsDlv.setRegtrId(regtrId);
					lgsDlv.setUdterId(regtrId);
					lgsDlv.setCrncyCd(lgsDlvTmp.getCrncyCd());										//통화 코드

					lgsDlv.setStdrCrncyAmt(lgsDlvTmp.getStdrCrncyAmt());    						//기준 통화 금액
					lgsDlv.setPayExchgRtCrncyAmt(lgsDlvTmp.getPayExchgRtCrncyAmt());				//결제 환율 통화 금액

					lgsDlv.setRealityDlvCst(lgsDlvTmp.getRealityDlvCst());    						//실제 배송비
					lgsDlv.setPlcDlvCst(lgsDlvTmp.getPlcDlvCst());    								//정책 배송비

					lgsDlv.setCnclDlvCst(lgsDlvTmp.getCnclDlvCst());    							//취소 배송비
					lgsDlv.setRtgodDlvCst(lgsDlvTmp.getRtgodDlvCst());    							//반품 배송비
					lgsDlv.setExchgDlvCst(lgsDlvTmp.getExchgDlvCst());    							//교환 배송비

					lgsDlv.setWaybilNoErpTrnsmisCd(GoodsEnum.NO.toString());
					lgsDlv.setDlvCstBndMbdCd(lgsDlvTmp.getDlvCstBndMbdCd());    					//배송비 부담 주체 코드

					if(StringService.equalsIgnoreCase(callTp, "DLV") || StringService.equalsIgnoreCase(callTp, "OFF_SHOP")) {
						lgsDlv.setDlvMnCd("SHOP_PKUP");												//배송수단코드
					} else {
						lgsDlv.setDlvMnCd(lgsDlvspTmp2.getDlvMnCd());								//배송수단코드 - 수정후 교환접수에서 배송수단코드가 등록이 않되는 현상 때문에 변경
					}

					// 2018/11/01 교환시 기존 LGS_DLV의 택배사를 따라가기 때문에 CJ로 변경.
					lgsDlv.setDlvComCd(DeliveryEnum.APPN_DLV_COM_CD.toString());									//배송업체코드

					/*
					 * 교환출고
					 * 	- 배송업체전송대상여부(DLV_COM_TRNSMIS_TGT_YN) - 'Y'
					 * 	- 배송업체전송여부(DLV_COM_TRNSMIS_YN) - 'N'
					 */
					lgsDlv.setDlvComTrnsmisTgtYn("Y");
					lgsDlv.setDlvComTrnsmisYn("N");
					lgsDlv.setDlivyWaybilNoErpTrnsmisCd("N");
					//물류배송
					lgsDlvRepository.insertLgsDlv(lgsDlv);

					LgsDlvHist lgsDlvHist = new LgsDlvHist();
					BeanUtils.copyProperties(lgsDlv, lgsDlvHist);
					lgsDlvHist.setHistDt(new Date());
					//물류배송이력
					lgsDlvHistRepository.insertLgsDlvHist(lgsDlvHist);
				}
			}
		}
	}

	/**
	 *
	 * 클레임 반품/교환 시 물류회수지시상품 등록.
	 * @param lgsDlvspBoDTO
	 * @param ordNo
	 * @param clmNo
	 * @param regtrId
	 * @throws Exception
	 */
	public void lgsRtrvlDrctGodProcessor(LgsDlvspBoDTO lgsDlvspBoDTO
			, String clmTpCd				//클레임유형코드
			, String virtlRtgodYn			//가상반품여부
			, String dlvComTrnsmisTgtYn		//택배사수거지시여부
			, String repairRtgodYn       	//수선반품여부
			, String cstmrNm         		//고객명
			, String cstmrMobilNo     		//전화번호
			, String rtrvlDrctGrpDgre 		//차수
			, String badnReqestCont 		//의뢰내용
            , String role					//FO/BO 구분
            , String ordTp					//주문유형
            , String callTp					//호출유형
            , String regtrShopId			//로그인된 매장ID
            , String dlivyDrctTpCd			//회수지시유형 != 클레임유형코드 이므로 따로 세팅
            , String adminTpCd				//운영자 유형 코드
			) throws Exception {

		//물류배송지별 회수방법
		String dlvMnCd = "";

		//물류배송지 정보 추출
		for (LgsDlvspExtend lgsDlvspExtend : lgsDlvspBoDTO.getLgsDlvspList())
		{

			dlvMnCd = lgsDlvspExtend.getDlvMnCd();

			//클레임수거지 인 경우
			if(StringService.equalsIgnoreCase(lgsDlvspExtend.getDlvPcupspSectCd(), "CLM_PCUPSP"))
			{

				//클레임입고상품 정보 추출
				for (ClmWrhsGodExtend clmWrhsGodEntity : lgsDlvspExtend.getClmWrhsGodList())
				{
					/* 물류회수지시상품순번 */
					String rtrvlDrctGodNo = getIdGenService().generateDBNumber(sqlSession1, "sq_lgs_rtrvl_drct_god", "R", DatabaseType.ORACLE);

					/**
					 * 상품 유형 코드
	            	ㅁ. 상품구분 : GOD_TP
	               	>. 일반상품 : GNRL_GOD
	               	>. 세트상품 : SET_GOD
	               	>. 사은품 : GFT
	               	>. 패키지 상품 : PCKAGE_GOD
	               	>. 상품권 : GFCT
					 */
					String godTp = clmWrhsGodEntity.getGodTpCd();

					// 회수지시상품 저장시에 배송순번을 조회하기 위해 국내배송정책일련번호 를 설정하기 위해 DTO 변경
					LgsRtrvlDrctGodExtend lgsRtrvlDrctGod = new LgsRtrvlDrctGodExtend();

					lgsRtrvlDrctGod.setRtrvlDrctGodNo(rtrvlDrctGodNo);                          /* 회수지시 상품 번호       */
					lgsRtrvlDrctGod.setOrdNo(clmWrhsGodEntity.getOrdNo());						/* 주문 번호OD || YYYYMMDD || 7자리 Cycle Sequence */
					lgsRtrvlDrctGod.setClmNo(clmWrhsGodEntity.getClmNo());						/* 클레임 번호CL || YYYYMMDD || 7자리 Cycle Sequence */
					lgsRtrvlDrctGod.setDlivyDrctGodNo(clmWrhsGodEntity.getDlivyDrctGodNo());	/* 출고지시 상품 번호		*/
					lgsRtrvlDrctGod.setClmWrhsGodTurn(clmWrhsGodEntity.getClmWrhsGodTurn());    /* 클레임 입고 상품 순번    */
					lgsRtrvlDrctGod.setDlvPcupspTurn(clmWrhsGodEntity.getDlvPcupspTurn());      /* 배송 수거지 순번         */

					lgsRtrvlDrctGod.setDmstcDlvCstPlcSn(clmWrhsGodEntity.getDmstcDlvCstPlcSn());/* 국내배송정책 일련번호 - ncp2 */

					lgsRtrvlDrctGod.setRtrvlDrctTgtYn("N");                                     /* 회수지시 대상 여부       */
					lgsRtrvlDrctGod.setRtrvlDrctYn("N");                                        /* 회수지시 여부            */
					
					if((StringService.equalsIgnoreCase(callTp, "DLV") && StringService.equalsIgnoreCase(clmTpCd, "RTGOD")) || (StringService.equalsIgnoreCase(callTp, "OFF_SHOP") && StringService.equalsIgnoreCase(clmTpCd, "RTGOD"))) {
						//매장반품
						lgsRtrvlDrctGod.setRtrvlDrctTpCd("SHOP_RTGOD");                			/* 회수지시 유형 코드 회수지시 유형 코드ㅁ. 회수지시유형 : RTRVL_DRCT_SECT   >. 반품 : RTGOD   >. 교환 : EXCHG   >. 맞교환 : DRT_EXCHG   >. 매장 반품 : SHOP_RTGOD */
					} else {
						lgsRtrvlDrctGod.setRtrvlDrctTpCd(dlivyDrctTpCd);               			/* 회수지시 유형 코드 회수지시 유형 코드ㅁ. 회수지시유형 : RTRVL_DRCT_SECT   >. 반품 : RTGOD   >. 교환 : EXCHG   >. 맞교환 : DRT_EXCHG   >. 매장 반품 : SHOP_RTGOD */
					}
					
					//클레임입고상품 등록시 생성한 수량-물류출고지시상품 기준
					lgsRtrvlDrctGod.setRtrvlDrctQty(clmWrhsGodEntity.getRtrvlDrctQty());        /* 회수지시 수량            */
					
					lgsRtrvlDrctGod.setRtrvlGodPrcsComptYn("N");								/* 회수 상품 처리 완료 여부 */
					lgsRtrvlDrctGod.setWrhsComptDt(null);										/* 입고 완료 일시           */
					lgsRtrvlDrctGod.setRtrvlComptDt(null);										/* 회수 완료 일시           */
					
					lgsRtrvlDrctGod.setRtrvlStatCd("RTRVL_DRCT_WAIT");                      	/* 회수 상태 코드ㅁ. 회수 상태 : RTRVL_STAT   >. 회수지시 대기 : RTRVL_DRCT_WAIT   >. 회수지시 : RTRVL_DRCT   >. 입고 완료 : WRHS_COMPT   >. 회수 완료 : RTRVL_COMPT   >. 회수 철회 : RTRVL_WTHDRAW */						

					lgsRtrvlDrctGod.setHdryComTrnsmisYn("N");                                   /* 택배사 전송 여부         */
					lgsRtrvlDrctGod.setErpTrnsmisYn("N");                                       /* ERP 전송 여부            */
					lgsRtrvlDrctGod.setErpCnfirmYn("N");                                        /* ERP 확인 여부            */
					lgsRtrvlDrctGod.setRtgodcstCalAmt(new BigDecimal("0"));						/* 반품비 정산 금액         */
					lgsRtrvlDrctGod.setRegtrId(clmWrhsGodEntity.getRegtrId());                  /* 등록자 ID                */

					lgsRtrvlDrctGod.setHdryComTrnsmisTgtYn("Y");
					if("N".equals(dlvComTrnsmisTgtYn)){ // 고객발송으로 온 반품은 택배사 전송안함 기존 컬럼이 배송전송타켓으로 되있어서 같은걸로 처리
						lgsRtrvlDrctGod.setHdryComTrnsmisTgtYn("N");
						lgsRtrvlDrctGod.setRtrvlStatCd("RTRVL_DRCT");                      	/* 회수 상태 코드ㅁ. 회수 상태 : RTRVL_STAT   >. 회수지시 대기 : RTRVL_DRCT_WAIT   >. 회수지시 : RTRVL_DRCT   >. 입고 완료 : WRHS_COMPT   >. 회수 완료 : RTRVL_COMPT   >. 회수 철회 : RTRVL_WTHDRAW */
					}
					lgsRtrvlDrctGod.setErpInvTrnsmisSectCd("CNTR_INV");					/* ERP 재고 전송 구분 : 센터 재고 : CNTR_INV */
					
					//mall 분리
					God tempGod = goodsService.getGoods(clmWrhsGodEntity.getGodNo());
					String rtrvlShopId = DeliveryEnum.DLV_ONLINE_SHOP_DISCOVERY.toString();
					if("M".equals(tempGod.getBrndId())) {
						rtrvlShopId = DeliveryEnum.DLV_ONLINE_SHOP_MLB.toString();
					} else if("I".equals(tempGod.getBrndId())) {
						rtrvlShopId = DeliveryEnum.DLV_ONLINE_SHOP_MLB_KIDZ.toString();
					} else if("A".equals(tempGod.getBrndId())) {
						rtrvlShopId = DeliveryEnum.DLV_ONLINE_SHOP_SA.toString();
					}
					lgsRtrvlDrctGod.setRtrvlShopId(rtrvlShopId);    							/* 회수매장코드 */
					
					//매장 반품인 경우 회수 완료
					if(StringService.equalsIgnoreCase(callTp, "OFF_SHOP") && StringService.equalsIgnoreCase(clmTpCd, "RTGOD")) {
						
						lgsRtrvlDrctGod.setRtrvlStatCd("RTRVL_COMPT");                        	/* 회수 상태 코드ㅁ. 회수 상태 : RTRVL_STAT   >. 회수지시 대기 : RTRVL_DRCT_WAIT   >. 회수지시 : RTRVL_DRCT   >. 입고 완료 : WRHS_COMPT   >. 회수 완료 : RTRVL_COMPT   >. 회수 철회 : RTRVL_WTHDRAW */
						lgsRtrvlDrctGod.setRtrvlComptDt(new Date());							/* 회수 완료 일시           */
						lgsRtrvlDrctGod.setRtrvlGodStatCd("NORM_WRHS");
						
						lgsRtrvlDrctGod.setHdryComTrnsmisTgtYn("N");
						lgsRtrvlDrctGod.setErpInvTrnsmisSectCd("SHOP_INV");					/* ERP 재고 전송 구분 : 매장 재고 : SHOP_INV */
						
						lgsRtrvlDrctGod.setRtrvlShopId(clmWrhsGodEntity.getDlvShopId());    /* 회수매장코드 > 배송매장ID */
					}
						
					//가상반품여부
//					if(StringService.equalsIgnoreCase(virtlRtgodYn, "Y")){
//
//						lgsRtrvlDrctGod.setHdryComTrnsmisTgtYn("N");                            /* 택배사 전송 대상 여부    */
//						lgsRtrvlDrctGod.setRtrvlStatCd("RTRVL_COMPT");                        	/* 회수 상태 코드ㅁ. 회수 상태 : RTRVL_STAT   >. 회수지시 대기 : RTRVL_DRCT_WAIT   >. 회수지시 : RTRVL_DRCT   >. 입고 완료 : WRHS_COMPT   >. 회수 완료 : RTRVL_COMPT   >. 회수 철회 : RTRVL_WTHDRAW */
//						lgsRtrvlDrctGod.setRtrvlComptDt(new Date());							/* 회수 완료 일시           */
//						lgsRtrvlDrctGod.setRtrvlGodStatCd("NORM_WRHS");							/* 회수 상품 상태 코드ㅁ. 회수상품상태 : RTRVL_GOD_STAT   >. 정상입고 : NORM_WRHS   >. 불량판정 : BADN_JDGMNT---삭제   >. 수선의뢰 : REPAIR_REQEST */
//
//					}

				
					/* 회수지시상품 배송순번 조회 시 필요함. */
					lgsRtrvlDrctGod.setLang("KOR");
					/* 회수지시상품 배송순번 조회 시 필요함. */

					LgsRtrvlDrctGodHist lgsRtrvlDrctGodHist = new LgsRtrvlDrctGodHist();

					if (!(godTp.equals(GoodsType.SET_GOD.toString()) || godTp.equals(GoodsType.PCKAGE_GOD.toString())))
					{
						//일반상품 또는 구성품
						deliveryExternalRepository.insertLgsRtrvlDrctGodForClm(lgsRtrvlDrctGod);

						BeanUtils.copyProperties(lgsRtrvlDrctGod, lgsRtrvlDrctGodHist);
						lgsRtrvlDrctGodHist.setUdterId(clmWrhsGodEntity.getRegtrId());
						//deliveryExternalRepository.insertLgsDlvHist(lgsRtrvlDrctGodHist);
						deliveryCommandRepository.insertLgsRtrvlDrctGodHist(lgsRtrvlDrctGodHist);
					}
					else
					{
						//패키지, 세트, 사은품, 상품권 인 경우
						//일반상품 또는 구성품
						deliveryExternalRepository.insertLgsRtrvlDrctGodForClm(lgsRtrvlDrctGod);

						BeanUtils.copyProperties(lgsRtrvlDrctGod, lgsRtrvlDrctGodHist);
						lgsRtrvlDrctGodHist.setUdterId(clmWrhsGodEntity.getRegtrId());
						//deliveryExternalRepository.insertLgsRtrvlDrctGodHistForClm(lgsRtrvlDrctGodHist);
						deliveryCommandRepository.insertLgsRtrvlDrctGodHist(lgsRtrvlDrctGodHist);

					}
				}//클레임입고상품 정보 추출
			}//수거지 정보 추출
		}//물류배송지 정보 추출
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
	 * 물류 배송지 update 후 물류 배송지 이력 테이블 insert
	 * @param deliveryPayClaimDTO
	 */
	public void updateClmDlvspCancelPay(DeliveryPayClaimDTO deliveryPayClaimDTO){
		deliveryExternalRepository.updateClmDlvspCancelPay(deliveryPayClaimDTO);
	}
	public void insertClmDlvspCancelPayHist(DeliveryPayClaimDTO deliveryPayClaimDTO){
		deliveryExternalRepository.insertClmDlvspCancelPayHist(deliveryPayClaimDTO);
	}

	/**
	 * 물류 배송 테이블 update 후 물류 배송 이력 테이블 insert
	 * @param deliveryPayClaimDTO
	 */
	public void updateClmDlvCancelPay(DeliveryPayClaimDTO deliveryPayClaimDTO){
		deliveryExternalRepository.updateClmDlvCancelPay(deliveryPayClaimDTO);
	}
	public void insertClmDlvCancelPayForGlobal(DeliveryPayClaimDTO deliveryPayClaimDTO){
		deliveryExternalRepository.insertClmDlvCancelPayForGlobal(deliveryPayClaimDTO);
	}
	public void insertClmDlvCancelPayHist(DeliveryPayClaimDTO deliveryPayClaimDTO){
		deliveryExternalRepository.insertClmDlvCancelPayHist(deliveryPayClaimDTO);
	}

	/**
	 * 물류 출고지시 상품 테이블 update <br/>
	 * 출고지시 취소 상태로 변경, 수량도 수정 전체취소
	 * @param deliveryPayClaimDTO
	 */
	public void updateClmDlvDrctGodCancel(DeliveryPayClaimDTO deliveryPayClaimDTO){
		List<LgsDlivyDrctGod> drctList =  deliveryExternalRepository.selectDlvDrctGodByOrdNo(deliveryPayClaimDTO);
		for(LgsDlivyDrctGod dto : drctList){
			deliveryPayClaimDTO.setDlivyDrctGodNo(dto.getDlivyDrctGodNo());
			deliveryExternalRepository.updateClmDlvDrctGodCancel(deliveryPayClaimDTO);	
		}
		
	}

	/**
	 * 재고복원 위해 select 하는 쿼리
	 * @param dlvOrdGodInfoDTO
	 * @return
	 */
	public List<GodShopItmInvExtend> selectOrdGodShopItmQty(DlvOrdGodInfoDTO dlvOrdGodInfoDTO) {
		return deliveryExternalRepository.selectOrdGodShopItmQty(dlvOrdGodInfoDTO); 
	}

	/**
	 * 물류 출고지시 상품 테이블 update <br/>
	 * 출고지시 취소 상태로 변경, 수량도 수정 부분취소
	 * @param deliveryPayClaimDTO
	 */
	public void updateClmDlvDrctGodUnitCancel(DeliverySearchDTO deliverySearchDTO) {
		for(DlvOrdGodInfoDTO dto : deliverySearchDTO.getClmDlvOrdGodInfoList()){
			deliveryExternalRepository.updateClmDlvDrctGodUnitCancel(dto);
		}
	}

	/**
	 * 주문번호와 배송지 순번으로 배송지와 픽업매장정보를 가져온다
	 * 클레임 접수 화면 배송지 별 상품 리스트에서 사용
	 * @param lgsDlvspExtend
	 * @return
	 */
	public LgsDlvspPkupDTO selectLgsDlvspPkup(LgsDlvspExtend lgsDlvsp){
		return deliveryExternalRepository.selectLgsDlvspPkup(lgsDlvsp);
	}

	/**
	 * 배송지 별로 남아있는 잔여 출고 수량을 리턴한다.
	 * @param deliveryPayClaimDTO
	 * @return deliveryPayClaimDTO.dlvPcupspTurn, deliveryPayClaimDTO.count
	 */
	public List<DeliveryOrderGoodDTO> selectDrctGodRemainQty(
			DeliveryPayClaimDTO deliveryPayClaimDTO) {
		return deliveryExternalRepository.selectDrctGodRemainQty(deliveryPayClaimDTO);
	}



	/**
	 * 주문 - 교환접수 물류출고지시상품 등록
	 * @param lgsDlivyDrctGod
	 */
	public void insertLgsDlivyDrctGodForClm(LgsDlivyDrctGod lgsDlivyDrctGod) throws Exception {
		lgsDlivyDrctGodRepository.insertLgsDlivyDrctGod(lgsDlivyDrctGod);
	}

	/**
	 * 주문 - 교환접수 물류출고지시상품 이력 등록
	 * @param lgsDlivyDrctGodHist
	 */
	public void insertLgsDlivyDrctGodHistForClm(LgsDlivyDrctGodHist lgsDlivyDrctGodHist) throws Exception {
		lgsDlivyDrctGodHistRepository.insertLgsDlivyDrctGodHist(lgsDlivyDrctGodHist);
	}


	/**
	 * 클레임 번호로 물류배송 테이블을 조회하여 엔티티 리스트로 리턴한다. (배송비 환불금액 추출위해서 작성)
	 * @param clmNo
	 * @return
	 */
	public List<LgsDlv> selectLgsDlvByClmNo(String clmNo) {
	    return deliveryExternalRepository.selectLgsDlvByClmNo(clmNo);
    }

	public int selectDlvTurn(DlvOrdGodInfoDTO dto) {
		return deliveryExternalRepository.selectDlvTurn(dto);
	}



	/**
	 * 주문 번호로 물류배송 테이블을 조회하여 엔티티 리스트로 리턴한다. (배송비 환불금액 추출위해서 작성)
	 * @param ordNo
	 * @return
	 */
	public List<LgsDlv> selectLgsDlvByOrdNo(String ordNo) {
		return deliveryExternalRepository.selectLgsDlvByOrdNo(ordNo);
	}

	////////////////////////////////////클레임 관련 method End//////////////////////////////////////////////////////////////////////////////////////////









	////////////////////////////////////Fo 마이페이지 관련  method Start //////////////////////////////////////////////////////////////////////////////////////////
	public List<DeliveryFinishFoDTO> selectDlivycomptTarget(DeliveryFinishFoDTO deliveryFinishFoDTO) {
		return deliveryExternalRepository.selectDlivycomptTarget(deliveryFinishFoDTO);
	}
	public void updateFoLgsDlivyDrctGod(LgsDlivyDrctGod lgsDlivyDrctGod) {
		deliveryExternalRepository.updateFoLgsDlivyDrctGod(lgsDlivyDrctGod);
	}
	public void insertFoLgsDlivyDrctGodHist(LgsDlivyDrctGodHist lgsDlivyDrctGodHist) {
		deliveryExternalRepository.insertFoLgsDlivyDrctGodHist(lgsDlivyDrctGodHist);
	}
	public void updateFolgsDlivyDrctGod(LgsDlivyDrctGod lgsDlivyDrctGod) {
		deliveryExternalRepository.updateFolgsDlivyDrctGod(lgsDlivyDrctGod);
	}
	public void updateDeliveryLocationChange(LgsDlvsp lgsDlvsp) {
		deliveryExternalRepository.updateDeliveryLocationChange(lgsDlvsp);
	}
	public void insertFoLgsDlvspHist(LgsDlvspHist lgsDlvspHist) throws Exception {
		deliveryExternalRepository.insertFoLgsDlvspHist(lgsDlvspHist);
	}
	public void insertFoLgsDlvHist(LgsDlvHist lgsDlvHist) throws Exception {
		deliveryExternalRepository.insertFoLgsDlvHist(lgsDlvHist);
	}
	////////////////////////////////////Fo 마이페이지 관련 method End//////////////////////////////////////////////////////////////////////////////////////////

	public int checkMinusDlvCnt(LgsDlvspExtend dto) {
	    return deliveryExternalRepository.checkMinusDlvCnt(dto);
    }

	public int checkItmDlvCnt(LgsDlvspExtend dto) {
		return deliveryExternalRepository.checkItmDlvCnt(dto);
    }

	public void insertLgsDlvForCnclClm(LgsDlv lgsDlv) {
		deliveryExternalRepository.insertLgsDlvForCnclClm(lgsDlv);	    
    }

	public void insertLgsDlvForCnclClmHist(LgsDlv lgsDlv) {
		deliveryExternalRepository.insertLgsDlvForCnclClmHist(lgsDlv);
    }

	public LgsDlivyDrctGod selectLgsDlivyDrctGod(LgsDlivyDrctGod lgsDlivyDrctGod) throws Exception{
		return lgsDlivyDrctGodRepository.selectLgsDlivyDrctGod(lgsDlivyDrctGod);
	}


	public void updateOrgLgsDlv(LgsDlv lgsDlv){
		deliveryCommandRepository.updateOrgLgsDlv(lgsDlv);
	}

	public void insertLgsDlivyDrctGod(LgsDlivyDrctGodHist lgsDlivyDrctGodHist) throws Exception{

		Map<String, Object> conditions = Maps.newHashMap();
		conditions.put("dlivy_drct_god_no", lgsDlivyDrctGodHist.getDlivyDrctGodNo());

		int histTurn = getIdGenService().generateDBOrder(sqlSession1, "lgs_dlivy_drct_god_hist", "hist_turn", conditions, DatabaseType.ORACLE);

		lgsDlivyDrctGodHist.setHistTurn(histTurn);
		lgsDlivyDrctGodHistRepository.insertLgsDlivyDrctGodHist(lgsDlivyDrctGodHist);
	}
	
	public String selectBaseDlvComCd(String mallId) throws Exception {
		return deliverySelectRepository.selectBaseDlvComCd(mallId);
	}
	
	/**
	 * 
	 * 우체국 택배 송장번호로 종추적 조회.
	 * 
	 * @param systemPk
	 * @param deliveryOrderGoodDTO
	 * @return
	 * @throws Exception
	 */
	public String getPostTraceDlv(String dmstcWaybilNo) throws Exception {
        String url = traceUrl + "?regkey=" + traceRegkey + "&target=trace&query="+dmstcWaybilNo;
		JSONObject resultJson = new JSONObject();
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));
	    
		URI uri = URI.create(url);
		String response = restTemplate.getForObject(uri, String.class);
		resultJson = XML.toJSONObject(response.toString());
		
		return response;
	}

	/**
	 * 재 출고지시 대상 조회 (출고지시 이후 배송지 변경시)
	 * 
	 * @param dlvsp
	 * @return
	 */
	public List<DeliverySearchDTO> selectReDlvDrctTargetList(LgsDlvsp dlvsp) {
		return deliveryExternalRepository.selectReDlvDrctTargetList(dlvsp);
	}
}

