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
 * @since       2015. 11. 25       
 */
package com.plgrim.ncp.biz.delivery.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.codehaus.plexus.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plgrim.ncp.base.abstracts.AbstractService;
import com.plgrim.ncp.base.entities.datasource1.god.GodItm;
import com.plgrim.ncp.base.entities.datasource1.inf.InfOrdGodErpDstb;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsDlivyDrctGod;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsDlivyDrctGodHist;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsDlv;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsDlvHist;
import com.plgrim.ncp.base.enums.DeliveryEnum;
import com.plgrim.ncp.biz.delivery.data.DeliveryInfErpDTO;
import com.plgrim.ncp.biz.delivery.data.DeliveryOrderGoodDTO;
import com.plgrim.ncp.biz.delivery.data.DeliverySearchDTO;
import com.plgrim.ncp.biz.delivery.exception.DeliveryStatusException;
import com.plgrim.ncp.biz.delivery.repository.DeliveryCommandRepository;
import com.plgrim.ncp.biz.delivery.result.DeliveryOrderGoodResult;
import com.plgrim.ncp.commons.util.BOSecurityUtil;
import com.plgrim.ncp.framework.commons.StringService;
import com.plgrim.ncp.framework.data.SystemPK;
import com.plgrim.ncp.interfaces.util.InterfaceApiCommon;

import lombok.extern.slf4j.Slf4j;

/**
 * [클래스 설명]
 * <p/>
 * <p/>
 * <p/>
 * <ul>
 * <li> [기능1]
 * <li> [기능2]
 * </ul>.
 * @author 
 * @since 2015. 4. 13
 */

/**
 * @author shinkun
 */
@Slf4j
@Service
public class DeliveryAssignService extends AbstractService {

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	@Autowired
	DeliveryCommandRepository deliveryCommandRepository;

	@Autowired
	InterfaceApiCommon interfaceApiCommon;

	@Autowired
	DeliveryInfErpService deliveryInfErpService;

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
	 * [일반] 재배정.
	 * <p/>
	 * <p/>
	 * <p/>
	 * [사용 방법 설명].
	 *
	 * @param result [설명]
	 * @param targetShop [설명]
	 * @param caller [설명]
	 * @return Delivery order good dto [설명]
	 * @throws Exception the exception
	 * @since 2015. 11. 6
	 */
	public DeliveryOrderGoodDTO reAssignDeliShop4Single(DeliveryOrderGoodResult result, String targetShop, String caller) throws Exception {
		DeliveryOrderGoodDTO retDTO = new DeliveryOrderGoodDTO();
		//LgsAutoAsgnExtend shopInfo = new LgsAutoAsgnExtend();
		String[] excptBfShop = { DeliveryEnum.DLV_ONLINE_SHOP_DISCOVERY.toString(), "B031", "A345", "A500", "B04E", "A351", "A505", "A506" }; 	// 배정이력 예외 이전 매장
		String[] excptShop = { DeliveryEnum.DLV_ONLINE_SHOP_DISCOVERY.toString(), "A345", "A500", "B04E", "A351", "A505", "A506" }; 			// 배정이력 예외 매장
		String userId = BOSecurityUtil.getLoginId(); 		// 세션ID

		// 배정대상 출고정보 조회
		LgsDlivyDrctGod lgsDdg = new LgsDlivyDrctGod();
		lgsDdg.setOrdNo(result.getOrd().getOrdNo());
		lgsDdg.setDlivyDrctGodNo(result.getLgsDdg().getDlivyDrctGodNo());
		DeliveryOrderGoodResult obj = deliveryCommandRepository.selectAssignTargetList(lgsDdg).get(0);

		// 기존 품절처리된 상품중 ERP출고취소가 미처리된 상품은 재배정 처리 안함
		if ( "Y".equals(obj.getLgsDdg().getDlivyDrctYn()) ) {
			DeliveryStatusException statusException = new DeliveryStatusException(null);
			statusException.setDirectMessage("ERP출고취소가 되지 않은 주문건이 존재합니다.\n확인 후 처리하세요.");
			log.info("ERP출고취소가 되지 않은 주문건이 존재합니다.\n확인 후 처리하세요.");
			throw statusException;

		} else {
			// 출고매장 지정
			String dlvLcId = ""; 		// 물류저장위치
			String dlvShopId = DeliveryEnum.DLV_ONLINE_SHOP_DISCOVERY.toString(); 	// 물류센터
			if (targetShop != null && !"".equals(targetShop)) {
				dlvShopId = targetShop;
			} else {
				//mall 분리
				String brndId = StringUtils.defaultString(obj.getOrdGod().getBrndId(), "X");
				if("M".equals(brndId)) {
					dlvShopId = DeliveryEnum.DLV_ONLINE_SHOP_MLB.toString();
				} else if("I".equals(brndId)) {
					dlvShopId = DeliveryEnum.DLV_ONLINE_SHOP_MLB_KIDZ.toString();
				} else if("A".equals(brndId)) {
					dlvShopId = DeliveryEnum.DLV_ONLINE_SHOP_SA.toString();
				}
			}
			String dlvStatCd = DeliveryEnum.DLV_STAT_DLIVY_DRCT.toString();	// 배송상태 : 출고지시
			String dlvTgtYn = "Y"; 			// 출고지시대상여부
			boolean shopStckChck = true; 	// 매장재고체크여부

			// 한정재고 체크
			GodItm godItm = new GodItm();
			if ( "Y".equals(obj.getLmttInvYn()) ) {
				retDTO.setItmNo(obj.getOrdGod().getItmNo()); // 단품번호
				retDTO.setDlivyDrctQty(obj.getLgsDdg().getDlivyDrctQty().intValue()); // 출고지시수량
				retDTO.setDlvShopId(dlvShopId); // 신규배송매장

				// 한정재고 수량 조회
				godItm = deliveryCommandRepository.selectLmttInvQty4ItemStock(retDTO);

				// 제휴주문
				if ( DeliveryEnum.OrdTpCd.AFF_ORD.toString().equals(obj.getOrd().getOrdTpCd()) ) {
					// 제휴업체 한정재고 체크
					if ( godItm.getAffComLmttInvQty() < 0 ) {
						shopStckChck = false;
					}

				// 제휴외주문
				} else {
					// 온라인 한정재고 체크
					if ( godItm.getOnlneLmttInvQty() < 0 ) {
						shopStckChck = false;
					}
				}
			}

			// 주문유형 : 매장픽업주문
			if ( DeliveryEnum.OrdTpCd.SHOP_PKUP_ORD.toString().equals(obj.getOrd().getOrdTpCd()) ) {
				dlvShopId = DeliveryEnum.B031.toString();
				dlvStatCd = DeliveryEnum.DLV_STAT_SHTG_RCEPT.toString(); // 배송상태 : 결품접수
				dlvTgtYn = "N";

			} else {
				retDTO.setItmNo(obj.getOrdGod().getItmNo()); // 단품번호
				retDTO.setDlivyDrctQty(obj.getLgsDdg().getDlivyDrctQty().intValue()); // 출고지시수량
				retDTO.setDlvShopId(dlvShopId); // 신규배송매장
				retDTO.setDlivyDrctTpCd(obj.getLgsDdg().getDlivyDrctTpCd());	//출고유형
				retDTO.setBrndId(obj.getOrdGod().getBrndId());

				// 한정재고 + 수량초과
				if ( "Y".equals(obj.getLmttInvYn()) && !shopStckChck ) {
					dlvShopId = DeliveryEnum.B031.toString(); 		// 임시매장
					dlvStatCd = DeliveryEnum.DLV_STAT_SHTG_RCEPT.toString(); 	// 결품접수
					dlvTgtYn = "N";

				} else {
					// 재고있는 매장 재고 조회(CDC 재고 조회)
					//shopInfo = deliveryCommandRepository.getDeliShopInfoForItemStock(retDTO);

					// [배송조회]/[출고관리] '결품접수' 기능
					if ( "DELIVERY".equals(caller) ) {
						/*
						// 재고가 있는 매장이 없는 경우
						if ( shopInfo == null || StringService.isEmpty(shopInfo.getDlvShopId()) ) {
							dlvShopId = DeliveryEnum.B031.toString(); 					// 임시매장
							dlvStatCd = DeliveryEnum.DLV_STAT_SHTG_RCEPT.toString(); 	// 결품접수
							dlvTgtYn = "N";

						} else {
							// 배송매장이 물류센터가 아닐 경우
							if ( !DeliveryEnum.DLV_ONLINE_SHOP_DISCOVERY.toString().equals(shopInfo.getDlvShopId()) ) {

								dlvShopId = DeliveryEnum.B031.toString(); 				// 임시매장
								dlvStatCd = DeliveryEnum.DLV_STAT_DLV_WAIT.toString(); 	// 배송대기
								dlvTgtYn = "N";

								// 물류센터 미배정건에 대해 배송매장 배정을 위한 처리
								
								LgsAutoAsgnExtend lgsAutoAsgnExtend = new LgsAutoAsgnExtend();
								lgsAutoAsgnExtend.setOrdNo(obj.getOrd().getOrdNo());
								lgsAutoAsgnExtend.setDlivyDrctGodNo(obj.getLgsDdg().getDlivyDrctGodNo());
								throw new DeliveryAutoAssignException(lgsAutoAsgnExtend);
								 
							// 배송매장이 물류센터인 경우
							} else {
								// 안전재고(T몰재고)를 체크하여 출고 로케이션(FC08/FC01) 확인.
								dlvLcId = StringService.nvl(deliveryCommandRepository.selectDlvLcId4ItemStock(retDTO), "");
							}
						}
						*/
					// 기타 재배정 기능
					} else {
						
						//배송매장 희망배정 동시처리건 체크
						if( "TARGET".equals(caller) ) {
							if ( !DeliveryEnum.DLV_STAT_DLV_WAIT.toString().equals(obj.getLgsDdg().getDlvStatCd()) 
									&& !DeliveryEnum.B031.toString().equals(obj.getLgsDdg().getDlvShopId()) ) {
								DeliveryStatusException statusException = new DeliveryStatusException(null);
								statusException.setDirectMessage("재배정 대상이 아닌 건이 존재합니다.\n배송상태를 확인 후 처리하세요.");
								throw statusException;
							}
						}
						/*
						if ( shopInfo != null && targetShop.equals(shopInfo.getDlvShopId()) ) {
							if ( !DeliveryEnum.CDC.toString().equals(dlvShopId) ) {
								dlvTgtYn = "N";
							} else {
								// 안전재고(T몰재고)를 체크하여 출고 로케이션(FC08/FC01) 확인.
								dlvLcId = StringService.nvl(deliveryCommandRepository.selectDlvLcId4ItemStock(retDTO), "");
							}
						} else {
							DeliveryStatusException statusException = new DeliveryStatusException(null);
							statusException.setDirectMessage("매장재고 부족. 미배정.");
							log.info("매장재고 부족. 미배정.");
							throw statusException;
						}
						*/
					}
				}
			}

			// ERP 인터페이스 출고지시대상 정보 수정
			InfOrdGodErpDstb infOrdGodErpDstb = new InfOrdGodErpDstb();
			infOrdGodErpDstb.setOrdNo(obj.getOrd().getOrdNo());
			infOrdGodErpDstb.setOrdGodTurn(obj.getOrdGod().getOrdGodTurn());
			infOrdGodErpDstb.setDlivyDrctGodNo(obj.getLgsDdg().getDlivyDrctGodNo());
			infOrdGodErpDstb.setDlivyDrctTgtYn(dlvTgtYn);
			infOrdGodErpDstb.setDlivyDrctYn("N");
			deliveryCommandRepository.updateInfDeliveryDirectTarget(infOrdGodErpDstb);

			// 배정정보 수정
			// 물류센터 배정상태가 아닌 경우
			if ( dlvLcId == null || "".equals(dlvLcId) ) {
				dlvLcId = DeliveryEnum.FC01.toString();
			}
			lgsDdg.setDlvShopDlivyLcId(dlvLcId); // 배송매장출고위치ID
			lgsDdg.setDlivyDrctGodNo(obj.getLgsDdg().getDlivyDrctGodNo());
			lgsDdg.setDlivyDrctQty(obj.getLgsDdg().getDlivyDrctQty());
			lgsDdg.setDlvShopId(dlvShopId); 	// 배송매장
			lgsDdg.setDlvStatCd(dlvStatCd); 	// 배송상태
			lgsDdg.setLgsItmYn("Y"); 			// 단품여부
			lgsDdg.setDlivyDrctTgtYn(dlvTgtYn); // 출고지시대상여부
			lgsDdg.setUdterId(userId);
			if ( "TARGET".equals(caller) ) {
				lgsDdg.setAsgnSectCd("HOPE_ASGN");		// 배정구분코드 : 배송매장 희망배정
			}
			else if ( "DELIVERY".equals(caller) ) {
				lgsDdg.setAsgnSectCd("ENFRC_ASGN");		// 배정구분코드 : 강제배정
			}
			else {
				lgsDdg.setAsgnSectCd("CC_ENFRC_ASGN");	// 배정구분코드 : 고객센터 강제배정
			}
			lgsDdg.setAsgnCount(1);
			deliveryCommandRepository.updateAssignShopInfo(lgsDdg);

			// 물류 출고지시 상품 이력 등록
			LgsDlivyDrctGodHist lgsDlivyDrctGodHist = new LgsDlivyDrctGodHist();
			lgsDlivyDrctGodHist.setRegtrId("REASSIGN_SHOP_SINGLE_" + caller);
			lgsDlivyDrctGodHist.setUdterId(userId);
			lgsDlivyDrctGodHist.setDlivyDrctGodNo(obj.getLgsDdg().getDlivyDrctGodNo());
			deliveryCommandRepository.insertLgsDlivyDrctGodHist(lgsDlivyDrctGodHist);

			log.info("Single Assign Info : {} :: {} :: {} :: {} :: {} :: {} :: {} "
					, obj.getOrd().getOrdNo(), obj.getLgsDdg().getDlivyDrctGodNo(), dlvShopId, dlvStatCd
					, dlvLcId, obj.getLgsDdg().getDlivyDrctQty(), caller);

			// 배정정보 세팅
			retDTO.setDlivyDrctGodNo(obj.getLgsDdg().getDlivyDrctGodNo());
			retDTO.setInvAplYn(obj.getLgsDdg().getInvAplYn()); // 재고적용여부
			retDTO.setDlvShopId(dlvShopId);
			retDTO.setDlvStatCd(dlvStatCd);
			retDTO.setDlvShopDlivyLcId(dlvLcId);
			retDTO.setLmttInvYn(obj.getLmttInvYn());
			retDTO.setDmstcWaybilNo(obj.getLgsDlv().getDmstcWaybilNo());
			retDTO.setOrdNo(obj.getOrd().getOrdNo());
			retDTO.setOrdStatCd(obj.getOrd().getOrdStatCd());
			retDTO.setDlvPcupspTurn(obj.getLgsDsp().getDlvPcupspTurn());
			retDTO.setDlvTurn(obj.getLgsDlv().getDlvTurn());
			if ( shopStckChck ) {
				retDTO.setShopStckChckYn("Y");
			}
			else {
				retDTO.setShopStckChckYn("N");
			}
			retDTO.setCount(1); // 처리건수
		}
		
		return retDTO;
	}

	/**
	 * [패키지] 재배정.
	 * <p/>
	 * <p/>
	 * <p/>
	 * [사용 방법 설명].
	 *
	 * @param obj [설명]
	 * @param targetShop [설명]
	 * @param caller [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 11. 25
	 */
	public List<DeliveryOrderGoodResult> reAssignDeliShop4Package(DeliverySearchDTO obj, String targetShop, String caller)
	        throws Exception {
		List<DeliveryOrderGoodResult> retList = new ArrayList<DeliveryOrderGoodResult>(); // 처리결과
		                                                                                  // 리스트
		DeliveryOrderGoodDTO deliveryOrderGoodDTO = new DeliveryOrderGoodDTO();
		List<DeliverySearchDTO> paramList = new ArrayList<DeliverySearchDTO>();
		/*
		LgsAutoAsgnExtend shopInfo = new LgsAutoAsgnExtend();
		List<LgsAutoAsgnExtend> lgsAutoAsgnExtendList = new ArrayList<LgsAutoAsgnExtend>();
		*/
		String[] excptBfShop = { DeliveryEnum.DLV_ONLINE_SHOP_DISCOVERY.toString(), "B031", "A345", "A500", "B04E", "A351", "A505", "A506" };	// 배정이력 예외 이전 매장
		String[] excptShop = { DeliveryEnum.DLV_ONLINE_SHOP_DISCOVERY.toString(), "A345", "A500", "B04E", "A351", "A505", "A506" }; 			// 배정이력 예외 매장
		String userId = BOSecurityUtil.getLoginId(); 		// 세션ID

		String dlvLcId = ""; 		// 물류저장위치
		String dlvShopId = DeliveryEnum.DLV_ONLINE_SHOP_DISCOVERY.toString();	// 배송매장 : 물류센터
		if ( targetShop != null && !"".equals(targetShop) ) {
			dlvShopId = targetShop;
		} else {
			//mall 분리
			String brndId = StringUtils.defaultString(obj.getBrndId(), "X");
			if("M".equals(brndId)) {
				dlvShopId = DeliveryEnum.DLV_ONLINE_SHOP_MLB.toString();
			} else if("I".equals(brndId)) {
				dlvShopId = DeliveryEnum.DLV_ONLINE_SHOP_MLB_KIDZ.toString();
			} else if("A".equals(brndId)) {
				dlvShopId = DeliveryEnum.DLV_ONLINE_SHOP_SA.toString();
			}

		}
		String dlvStatCd = DeliveryEnum.DLV_STAT_DLIVY_DRCT.toString();// 배송상태 : 출고지시
		String dlvTgtYn = "Y"; 			// 출고지시대상여부

		List<GodItm> lmtGodItmList = new ArrayList<GodItm>();

		// 세트 구성품 조회 :: 미선택 구성품을 위한 별도 처리.
		List<DeliveryOrderGoodResult> packList = deliveryCommandRepository.selectReAssignDeliveryInfoPckage(obj);

		// 재고있는 매장 조회
		int idx = 0;
		for ( DeliveryOrderGoodResult packObj : packList ) {
			boolean shopStckChck = true; // 매장재고체크여부

			DeliverySearchDTO dsDto = new DeliverySearchDTO();
			dsDto.setItmNo(packObj.getOrdGod().getItmNo());
			dsDto.setDlivyDrctQty(packObj.getLgsDdg().getDlivyDrctQty().intValue());
			paramList.add(dsDto);

			// 물류센터 미배정건에 대해 배송매장 배정을 위한 파라미터 세팅
			/*
			LgsAutoAsgnExtend lgsAutoAsgnExtend = new LgsAutoAsgnExtend();
			lgsAutoAsgnExtend.setOrdNo(packObj.getOrd().getOrdNo());
			lgsAutoAsgnExtend.setDlivyDrctGodNo(packObj.getLgsDdg().getDlivyDrctGodNo());
			lgsAutoAsgnExtendList.add(lgsAutoAsgnExtend);
			 */
			if ( idx == packList.size() - 1 ) {
				deliveryOrderGoodDTO.setParamList(paramList);
				deliveryOrderGoodDTO.setDlvShopId(dlvShopId);

				// 한정재고
				if ( "Y".equals(packObj.getLmttInvYn()) ) {
					// 아이템별 한정재고 수량 조회
					lmtGodItmList = deliveryCommandRepository.selectLmttInvQty4ItemsStock(deliveryOrderGoodDTO);

					for ( GodItm lmtGodItm : lmtGodItmList ) {
						// 제휴주문
						if ( DeliveryEnum.OrdTpCd.AFF_ORD.toString().equals(packObj.getOrd().getOrdTpCd()) ) {
							// 제휴업체 한정재고 체크
							if ( lmtGodItm.getAffComLmttInvQty() < 0 ) {
								shopStckChck = false;
							}

						// 제휴외주문
						} else {
							// 온라인 한정재고 체크
							if ( lmtGodItm.getOnlneLmttInvQty() < 0 ) {
								shopStckChck = false;
							}
						}
					}
				}

				// 재고보유 매장조회
				deliveryOrderGoodDTO.setItmCnt(packList.size());
				deliveryOrderGoodDTO.setDlivyDrctTpCd(packObj.getLgsDdg().getDlivyDrctTpCd());	//출고유형
				deliveryOrderGoodDTO.setBrndId(packObj.getOrdGod().getBrndId());
				//shopInfo = deliveryCommandRepository.getDeliShopInfoForPackageStock(deliveryOrderGoodDTO);

				// 매장픽업주문
				if ( DeliveryEnum.OrdTpCd.SHOP_PKUP_ORD.toString().equals(packObj.getOrd().getOrdTpCd()) ) {
					dlvShopId = DeliveryEnum.B031.toString();
					dlvStatCd = DeliveryEnum.DLV_STAT_SHTG_RCEPT.toString(); 		// 배송상태 : 결품접수
					dlvTgtYn = "N";

				} else {
					// 한정재고 + 수량초과
					if ("Y".equals(packObj.getLmttInvYn()) && !shopStckChck) {
						dlvShopId = DeliveryEnum.B031.toString(); 					// 임시매장
						dlvStatCd = DeliveryEnum.DLV_STAT_SHTG_RCEPT.toString(); 	// 결품접수
						dlvTgtYn = "N";

					} else {
						// [배송조회]/[출고관리] '결품접수' 기능
						if ( "DELIVERY".equals(caller) ) {
							// 재고가 있는 매장이 없는 경우
							/*							
							if ( shopInfo == null || StringService.isEmpty(shopInfo.getDlvShopId()) ) {
								dlvShopId = DeliveryEnum.B031.toString(); 					// 임시매장
								dlvStatCd = DeliveryEnum.DLV_STAT_SHTG_RCEPT.toString(); 	// 결품접수
								dlvTgtYn = "N";

							} else {
								// 배송매장이 물류센터가 아닐 경우
								if ( !DeliveryEnum.CDC.toString().equals(shopInfo.getDlvShopId()) ) {
									dlvShopId = DeliveryEnum.B031.toString(); 				// 임시매장
									dlvStatCd = DeliveryEnum.DLV_STAT_DLV_WAIT.toString(); 	// 배송대기
									dlvTgtYn = "N";

									// 물류센터 미배정건에 대해 배송매장 배정을 위한 처리
									throw new DeliveryAutoAssignException(lgsAutoAsgnExtendList);
								} else {
									dlvShopId = DeliveryEnum.CDC.toString();					//물류센터
                                    dlvStatCd = DeliveryEnum.DLV_STAT_DLIVY_DRCT.toString();	//출고지시
                                    dlvTgtYn = "Y";
								}
							}
							*/
						// 기타 재배정 기능
						} else {
							
							//배송매장 희망배정 동시처리건 체크
							if( "TARGET".equals(caller) ) {
								if ( !DeliveryEnum.DLV_STAT_DLV_WAIT.toString().equals(packObj.getLgsDdg().getDlvStatCd()) 
										&& !DeliveryEnum.B031.toString().equals(packObj.getLgsDdg().getDlvShopId()) ) {
									DeliveryStatusException statusException = new DeliveryStatusException(null);
									statusException.setDirectMessage("재배정 대상이 아닌 건이 존재합니다.\n배송상태를 확인 후 처리하세요.");
									throw statusException;
								}
							}
							/*
							if ( shopInfo != null && targetShop.equals(shopInfo.getDlvShopId()) ) {
								if ( !DeliveryEnum.CDC.toString().equals(dlvShopId) ) {
									dlvTgtYn = "N";
								}
							} else {
								DeliveryStatusException statusException = new DeliveryStatusException(null);
								statusException.setDirectMessage("매장재고 부족. 미배정.");
								throw statusException;
							}
							*/
						}
					}
				}
			}
			idx++;
		}

		for ( DeliveryOrderGoodResult packObj2 : packList ) {

			// 안전재고(T몰재고)를 체크하여 출고 로케이션(FC08/FC01) 확인.
			if ( DeliveryEnum.DLV_ONLINE_SHOP_DISCOVERY.toString().equals(dlvShopId) 
					|| DeliveryEnum.DLV_ONLINE_SHOP_MLB.toString().equals(dlvShopId)
					|| DeliveryEnum.DLV_ONLINE_SHOP_MLB_KIDZ.toString().equals(dlvShopId)
					|| DeliveryEnum.DLV_ONLINE_SHOP_SA.toString().equals(dlvShopId)) {
				DeliveryOrderGoodDTO deliveryOrderGoodDTO2 = new DeliveryOrderGoodDTO();
				deliveryOrderGoodDTO2.setItmNo(packObj2.getOrdGod().getItmNo());
				deliveryOrderGoodDTO2.setDlivyDrctQty(packObj2.getLgsDdg().getDlivyDrctQty().intValue());
				deliveryOrderGoodDTO2.setDlvShopId(dlvShopId);

				// 안전재고(T몰재고)를 체크하여 출고 로케이션(FC08/FC01) 확인.
				dlvLcId = StringService.nvl(deliveryCommandRepository.selectDlvLcId4ItemStock(deliveryOrderGoodDTO2), "");
			}

			// ERP 인터페이스 출고지시대상 정보 수정
			InfOrdGodErpDstb infOrdGodErpDstb = new InfOrdGodErpDstb();
			infOrdGodErpDstb.setOrdNo(packObj2.getOrd().getOrdNo());
			infOrdGodErpDstb.setOrdGodTurn(packObj2.getOrdGod().getOrdGodTurn());
			infOrdGodErpDstb.setDlivyDrctGodNo(packObj2.getLgsDdg().getDlivyDrctGodNo());
			infOrdGodErpDstb.setDlivyDrctTgtYn(dlvTgtYn);
			infOrdGodErpDstb.setDlivyDrctYn("N");
			deliveryCommandRepository.updateInfDeliveryDirectTarget(infOrdGodErpDstb);
			
			/*
			 * 1. 수정일자	: 2017-08-07
			 * 2. 수정자	: 신영규 (shinkun)
			 * 3. SR NO	    : #48051
			 * 4. 수정 내용	: 1차 물류 출고지시 HUB 처리 완료된 건에 대해 
			 *                재차 물류 출고지시 처리가 필요한 경우 출고지시가 가능하도록 HUB 전송 FLAG 초기화
			 */
			// 출고매장이 물류인 경우 출고지시 HUB 전송여부 초기화
			/*
			if( DeliveryEnum.CDC.toString().equals( shopInfo.getDlvShopId() ) ) {
				InfOrdGodErpDstbFlg dstbFlg = new InfOrdGodErpDstbFlg();
				dstbFlg.setOrdNo(packObj2.getOrd().getOrdNo());
				dstbFlg.setOrdGodTurn(packObj2.getOrdGod().getOrdGodTurn());
				dstbFlg.setDstbFlgYn("N");
				dstbFlg.setUdterId("CDC_REASSIGN_"  + caller);
				dstbFlg.setDstbFlgCd(DeliveryEnum.DstbFlg.DLIVY_REQEST_HUB.toString());
				deliveryCommandHubRepository.modifyOrdGodErpDstbFlg4GodTotal(dstbFlg);
			}
			*/
			// 배정정보 수정
			LgsDlivyDrctGod lgsDdg = new LgsDlivyDrctGod();
			// 물류센터 배정상태가 아닌 경우
			if ( dlvLcId == null || "".equals(dlvLcId) ) {
				dlvLcId = DeliveryEnum.FC01.toString();
			}
			lgsDdg.setDlvShopDlivyLcId(dlvLcId); // 배송매장출고위치ID
			lgsDdg.setDlivyDrctGodNo(packObj2.getLgsDdg().getDlivyDrctGodNo());
			lgsDdg.setDlivyDrctQty(packObj2.getLgsDdg().getDlivyDrctQty());
			lgsDdg.setDlvShopId(dlvShopId); // 배송매장
			lgsDdg.setDlvStatCd(dlvStatCd); // 배송상태
			lgsDdg.setLgsItmYn("Y"); // 단품여부
			lgsDdg.setDlivyDrctTgtYn(dlvTgtYn); // 출고지시대상여부
			lgsDdg.setUdterId(userId);

			if ( "TARGET".equals(caller) ) {
				lgsDdg.setAsgnSectCd("HOPE_ASGN"); 		// 배정구분코드 : 배송매장 희망배정
			} else if ( "DELIVERY".equals(caller) ) {
				lgsDdg.setAsgnSectCd("ENFRC_ASGN");		// 배정구분코드 : 강제배정
			} else {
				lgsDdg.setAsgnSectCd("CC_ENFRC_ASGN");	// 배정구분코드 : 고객센터 강제배정
			}
			lgsDdg.setAsgnCount(1);

			deliveryCommandRepository.updateAssignShopInfo(lgsDdg);

			// 물류 출고지시 상품 이력 등록
			LgsDlivyDrctGodHist lgsDlivyDrctGodHist = new LgsDlivyDrctGodHist();
			lgsDlivyDrctGodHist.setRegtrId("REASSIGN_SHOP_PACKAGE_" + caller);
			lgsDlivyDrctGodHist.setUdterId(userId);
			lgsDlivyDrctGodHist.setDlivyDrctGodNo(packObj2.getLgsDdg().getDlivyDrctGodNo());
			deliveryCommandRepository.insertLgsDlivyDrctGodHist(lgsDlivyDrctGodHist);

			log.info("Package Assign Info : {} :: {} :: {} :: {} :: {} :: {} :: {} "
					, packObj2.getOrd().getOrdNo(), packObj2.getLgsDdg().getDlivyDrctGodNo(), dlvShopId, dlvStatCd
					, dlvLcId, packObj2.getLgsDdg().getDlivyDrctQty(), caller);

			packObj2.setDlvShopDlivyLcId(dlvLcId);
			packObj2.setNewDlvShopId(dlvShopId);
			packObj2.setOrdNo(packObj2.getOrd().getOrdNo());
			packObj2.setDmstcWaybilNo(packObj2.getLgsDlv().getDmstcWaybilNo());
			packObj2.setDlvPcupspTurn(packObj2.getLgsDsp().getDlvPcupspTurn());
			packObj2.setDlvTurn(packObj2.getLgsDlv().getDlvTurn());

			retList.add(packObj2);
		}

		return retList;
	}

	/**
	 * [상품 사은품] 재배정.
	 * <p/>
	 * <p/>
	 * <p/>
	 * 합포장 상품사은품에 대해 모상품과 동기화.
	 *
	 * @param obj [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 6. 15
	 */
	public List<DeliveryOrderGoodDTO> reAssignDeliShop4Gift(DeliveryOrderGoodDTO obj) throws Exception {
		List<DeliveryOrderGoodDTO> retList = new ArrayList<DeliveryOrderGoodDTO>();
		String userId = BOSecurityUtil.getLoginId(); // 세션ID

		// 모상품에 걸려있는 합포장 사은품 조회
		List<DeliveryOrderGoodResult> gftList = deliveryCommandRepository.selectAssignGiftList(obj);

		for (DeliveryOrderGoodResult gftObj : gftList) {
			// 배정정보 수정
			LgsDlivyDrctGod lgsDdg = new LgsDlivyDrctGod();
			lgsDdg.setDlivyDrctGodNo(gftObj.getLgsDdg().getDlivyDrctGodNo());
			lgsDdg.setDlivyDrctQty(gftObj.getLgsDdg().getDlivyDrctQty());
			lgsDdg.setDlvShopId(obj.getDlvShopId()); // 배송매장 :: 모상품 배송매장
			lgsDdg.setDlvStatCd(obj.getDlvStatCd()); // 배송상태 :: 모상품 배송상태
			lgsDdg.setLgsItmYn("Y"); // 단품여부
			lgsDdg.setDlivyDrctTgtYn("N"); // 출고지시대상여부
			lgsDdg.setUdterId(userId);
			deliveryCommandRepository.updateAssignShopInfo(lgsDdg);

			// 물류 출고지시 상품 이력 등록
			LgsDlivyDrctGodHist lgsDlivyDrctGodHist = new LgsDlivyDrctGodHist();
			lgsDlivyDrctGodHist.setRegtrId("REASSIGN_SHOP");
			lgsDlivyDrctGodHist.setUdterId(userId);
			lgsDlivyDrctGodHist.setDlivyDrctGodNo(gftObj.getLgsDdg().getDlivyDrctGodNo());
			deliveryCommandRepository.insertLgsDlivyDrctGodHist(lgsDlivyDrctGodHist);

			// 반환데이터 세팅
			DeliveryOrderGoodDTO retDTO = new DeliveryOrderGoodDTO();
			retDTO.setDlivyDrctGodNo(gftObj.getLgsDdg().getDlivyDrctGodNo());
			retDTO.setItmNo(gftObj.getOrdGod().getItmNo());
			retDTO.setDlvStatCd(gftObj.getLgsDdg().getDlvStatCd());
			retDTO.setDlvShopId(gftObj.getLgsDdg().getDlvShopId());
			retDTO.setDlivyDrctQty(gftObj.getLgsDdg().getDlivyDrctQty().intValue());
			retDTO.setNewDlvShopId(obj.getDlvShopId());
			retList.add(retDTO);
		}

		return retList;
	}

	/**
	 * [패키지 상품 사은품] 재배정.
	 * <p/>
	 * <p/>
	 * <p/>
	 * 합포장 상품사은품에 대해 모상품과 동기화.
	 *
	 * @param list [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 6. 15
	 */
	public List<DeliveryOrderGoodDTO> reAssignDeliShop4PckgGift(List<DeliveryOrderGoodResult> list) throws Exception {
		List<DeliveryOrderGoodDTO> retList = new ArrayList<DeliveryOrderGoodDTO>();
		DeliveryOrderGoodDTO obj = new DeliveryOrderGoodDTO();
		String userId = BOSecurityUtil.getLoginId(); // 세션ID

		// 패키지 묶음 단일 처리
		List<DeliverySearchDTO> newList = this.filterDuplPackageItem(list);

		for (DeliverySearchDTO param : newList) {
			obj.setOrdNo(param.getOrdNo());
			obj.setOrdGodTurn(param.getPckageGodTurn());

			// 모상품에 걸려있는 합포장 사은품 조회
			List<DeliveryOrderGoodResult> gftList = deliveryCommandRepository.selectAssignGiftList(obj);

			for (DeliveryOrderGoodResult gftObj : gftList) {
				// 배정정보 수정
				LgsDlivyDrctGod lgsDdg = new LgsDlivyDrctGod();
				lgsDdg.setDlivyDrctGodNo(gftObj.getLgsDdg().getDlivyDrctGodNo());
				lgsDdg.setDlivyDrctQty(gftObj.getLgsDdg().getDlivyDrctQty());
				lgsDdg.setDlvShopId(obj.getDlvShopId()); // 배송매장 :: 모상품 배송매장
				lgsDdg.setDlvStatCd(obj.getDlvStatCd()); // 배송상태 :: 모상품 배송상태
				lgsDdg.setLgsItmYn("Y"); // 단품여부
				lgsDdg.setDlivyDrctTgtYn("N"); // 출고지시대상여부
				lgsDdg.setUdterId(userId);
				deliveryCommandRepository.updateAssignShopInfo(lgsDdg);

				// 물류 출고지시 상품 이력 등록
				LgsDlivyDrctGodHist lgsDlivyDrctGodHist = new LgsDlivyDrctGodHist();
				lgsDlivyDrctGodHist.setRegtrId("REASSIGN_SHOP");
				lgsDlivyDrctGodHist.setUdterId(userId);
				lgsDlivyDrctGodHist.setDlivyDrctGodNo(gftObj.getLgsDdg().getDlivyDrctGodNo());
				deliveryCommandRepository.insertLgsDlivyDrctGodHist(lgsDlivyDrctGodHist);

				// 반환데이터 세팅
				DeliveryOrderGoodDTO retDTO = new DeliveryOrderGoodDTO();
				retDTO.setDlivyDrctGodNo(gftObj.getLgsDdg().getDlivyDrctGodNo());
				retDTO.setItmNo(gftObj.getOrdGod().getItmNo());
				retDTO.setDlvStatCd(gftObj.getLgsDdg().getDlvStatCd());
				retDTO.setDlvShopId(gftObj.getLgsDdg().getDlvShopId());
				retDTO.setDlivyDrctQty(gftObj.getLgsDdg().getDlivyDrctQty().intValue());
				retDTO.setNewDlvShopId(obj.getDlvShopId());
				retList.add(retDTO);
			}
		}

		return retList;
	}

	/**
	 * 패키지 묶음 중복건 단일 처리.
	 * <p/>
	 * <p/>
	 * <p/>
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
		while (it.hasNext()) {
			retList.add(it.next());
		}

		return retList;
	}

	/**
	 * 가상배송 출고처지정 처리.
	 * <p/>
	 * <p/>
	 * <p/>
	 * [사용 방법 설명].
	 *
	 * @param ordNo [설명]
	 * @throws Exception the exception
	 * @since 2015. 4. 21
	 */
	public void assignDlvShop4Vitual(String ordNo) throws Exception {
		String userId = BOSecurityUtil.getLoginId(); // 세션ID
		String dlvShopId = "B04A"; // 배송매장 : 8seconds 이외 브랜드
		String dlvStatCd = "DLV_COMPT"; // 배송상태 : 배송완료
		String dlvTgtYn = "N"; // 출고지시대상여부
		String invAplYn = "N"; // 재고적용여부

		// 가상배송 처리 대상조회
		LgsDlivyDrctGod lgsDdg = new LgsDlivyDrctGod();
		lgsDdg.setOrdNo(ordNo);
		List<DeliveryOrderGoodResult> list = deliveryCommandRepository.selectAssignTargetList(lgsDdg);

		for (DeliveryOrderGoodResult obj : list) {
			// 8seconds 브랜드
			if ("MCOM".equals(obj.getOrdGod().getPartmalSectCd()) && ("SABR".equals(obj.getOrdGod().getBrndGrpId())
			        || "8M".equals(obj.getOrdGod().getBrndGrpId()) || "8W".equals(obj.getOrdGod().getBrndGrpId()))) {
				dlvShopId = "B04B";
			}

			// ERP 인터페이스 출고지시대상 정보 수정
			InfOrdGodErpDstb infOrdGodErpDstb = new InfOrdGodErpDstb();
			infOrdGodErpDstb.setOrdNo(obj.getOrd().getOrdNo());
			infOrdGodErpDstb.setOrdGodTurn(obj.getOrdGod().getOrdGodTurn());
			infOrdGodErpDstb.setDlivyDrctGodNo(obj.getLgsDdg().getDlivyDrctGodNo());
			infOrdGodErpDstb.setDlivyDrctTgtYn(dlvTgtYn);
			infOrdGodErpDstb.setDlivyDrctYn("N");
			deliveryCommandRepository.updateInfDeliveryDirectTarget(infOrdGodErpDstb);

			// 출고지시상품 배정정보 수정
			lgsDdg.setDlvShopId(dlvShopId); // 배송매장번호
			lgsDdg.setDlvStatCd(dlvStatCd); // 배송상태코드
			lgsDdg.setDlivyDrctQty(obj.getLgsDdg().getDlivyDrctQty()); // 출고지시수량
			lgsDdg.setDlivyDrctTpCd("ORD"); // 출고지시유형코드 : 주문
			lgsDdg.setLgsItmYn("Y"); // 단품여부
			lgsDdg.setDlivyDrctTgtYn(dlvTgtYn); // 출고지시대상여부
			lgsDdg.setDlivyComptQty(obj.getLgsDdg().getDlivyDrctQty()); // 출고완료수량
			lgsDdg.setInvAplYn(invAplYn); // 재고적용여부
			lgsDdg.setUdterId(userId);
			lgsDdg.setDlivyDrctGodNo(obj.getLgsDdg().getDlivyDrctGodNo()); // 출고지시상품번호
			deliveryCommandRepository.updateAssignShopInfo(lgsDdg);

			// 출고지시상품 이력 등록
			LgsDlivyDrctGodHist lgsDdgHist = new LgsDlivyDrctGodHist();
			lgsDdgHist.setRegtrId(userId);
			lgsDdgHist.setUdterId(userId);
			lgsDdgHist.setDlivyDrctGodNo(obj.getLgsDdg().getDlivyDrctGodNo());
			deliveryCommandRepository.insertLgsDlivyDrctGodHist(lgsDdgHist);

		}
	}

	/**
	 * 패키지 구성 상품 정보 조회.
	 * <p/>
	 * <p/>
	 * <p/>
	 * [사용 방법 설명].
	 *
	 * @param systemPk [설명]
	 * @param deliverySearchDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 9
	 */
	public List<DeliveryOrderGoodResult> selectPckageCompositInfoList(SystemPK systemPk, DeliverySearchDTO deliverySearchDTO)
	        throws Exception {
		return deliveryCommandRepository.selectPckageCompositInfoList(deliverySearchDTO);
	}

	/**
	 * 물류 출고지시 상품 이력 등록.
	 * <p/>
	 * <p/>
	 * <p/>
	 * [사용 방법 설명].
	 *
	 * @param deliveryOrderGoodResult [설명]
	 * @return Int [설명]
	 * @throws Exception the exception
	 * @since 2015. 4. 14
	 */
	public int insertLgsDlivyDrctGodHist(DeliveryOrderGoodResult deliveryOrderGoodResult) throws Exception {
		LgsDlivyDrctGodHist lgsDlivyDrctGodHist = new LgsDlivyDrctGodHist();

		lgsDlivyDrctGodHist.setRegtrId("ORDER");
		lgsDlivyDrctGodHist.setUdterId("SYSTEM");
		lgsDlivyDrctGodHist.setDlivyDrctGodNo(deliveryOrderGoodResult.getLgsDdg().getDlivyDrctGodNo());
		return deliveryCommandRepository.insertLgsDlivyDrctGodHist(lgsDlivyDrctGodHist);
	}

	/**
	 * ERP 인터페이스 출고지시대상 정보 수정.
	 * <p/>
	 * <p/>
	 * <p/>
	 * [사용 방법 설명].
	 *
	 * @param deliveryOrderGoodResult [설명]
	 * @throws Exception the exception
	 * @since 2015. 4. 15
	 */
	public void updateInfDeliveryDirectTarget(DeliveryOrderGoodResult deliveryOrderGoodResult) throws Exception {
		InfOrdGodErpDstb infOrdGodErpDstb = new InfOrdGodErpDstb();

		infOrdGodErpDstb.setOrdNo(deliveryOrderGoodResult.getOrd().getOrdNo());
		infOrdGodErpDstb.setOrdGodTurn(deliveryOrderGoodResult.getOrdGod().getOrdGodTurn());
		infOrdGodErpDstb.setDlivyDrctGodNo(deliveryOrderGoodResult.getLgsDdg().getDlivyDrctGodNo());
		infOrdGodErpDstb.setDlivyDrctTgtYn(deliveryOrderGoodResult.getLgsDdg().getDlivyDrctTgtYn());
		deliveryCommandRepository.updateInfDeliveryDirectTarget(infOrdGodErpDstb);
	}

	/**
	 * 출고검수 물류출고상품 단/복품 유형 초기화
	 * <p/>
	 * <p/>
	 * <p/>
	 * [사용 방법 설명].
	 *
	 * @param lgsDlivyDrctGod [설명]
	 * @throws Exception the exception
	 * @since 2015. 4. 16
	 */
	public void updateLgsItemYnInit(LgsDlivyDrctGod lgsDlivyDrctGod) throws Exception {
		deliveryCommandRepository.updateLgsItemYnInit(lgsDlivyDrctGod);
	}

	/**
	 * 물류출고지시상품 단/복품 수정.
	 * <p/>
	 * <p/>
	 * <p/>
	 * [사용 방법 설명].
	 *
	 * @param ordNo [설명]
	 * @throws Exception the exception
	 * @since 2015. 4. 16
	 */
	public void updateLgsItmYn(String ordNo) throws Exception {
		LgsDlivyDrctGod lgsDlivyDrctGod = new LgsDlivyDrctGod();
		lgsDlivyDrctGod.setOrdNo(ordNo);
		deliveryCommandRepository.updateLgsItmYn(lgsDlivyDrctGod);
	}

	/**
	 * 물류출고지시상품 단/복품 수정.
	 * <p/>
	 * <p/>
	 * <p/>
	 * [사용 방법 설명].
	 *
	 * @param lgsDlivyDrctGod [설명]
	 * @throws Exception the exception
	 * @since 2015. 4. 16
	 */
	public void updateLgsItmYn(LgsDlivyDrctGod lgsDlivyDrctGod) throws Exception {
		deliveryCommandRepository.updateLgsItmYn(lgsDlivyDrctGod);
	}

	/**
	 * 입점사 주문상품 SMS발송.
	 * <p/>
	 * <p/>
	 * <p/>
	 * 입점상품별 주문건수를 해당 담당자에게 알림SMS 전송.
	 *
	 * 현재 사용 안함
	 *
	 * @param ordNo [설명]
	 * @throws Exception the exception
	 * @since 2015. 4. 16
	 */
	public void sendOrderSmsToPartner(String ordNo) throws Exception {
//		List<HumusonSDO> parameterList = new ArrayList<>();
		String smsMsg = "";
		String result = "";
		int nonExistcnt = 0;
		int wrongCnt = 0;
		int successCnt = 0;

		// 입점사 주문상품 SMS발송 정보 조회
		List<DeliveryOrderGoodResult> list = deliveryCommandRepository.selectOrdGodListForSms(ordNo);

		if (log.isDebugEnabled()) {
			log.debug("주문 입점사 상품 SMS 발송건 ");
		}
		if (list.size() > 0) {
			for (DeliveryOrderGoodResult deliveryOrderGoodResult : list) {
				// 번호가 잘못 등록된 경우
				if ("N".equals(deliveryOrderGoodResult.getMobilNoCheck())) {
					wrongCnt++;

					// 등록된 번호 없는 경우
				}
				else if ("NN".equals(deliveryOrderGoodResult.getMobilNoCheck())) {
					nonExistcnt++;

					// 정상
				}
				else {
					/**
					 * 현재 사용 안함
					 */
					smsMsg = "[신규주문] " + deliveryOrderGoodResult.getBrndNm() + " " + deliveryOrderGoodResult.getToday() + " 주문 "
					        + deliveryOrderGoodResult.getOrdGodCnt() + "건 발생. 확인 후 발송처리 부탁드립니다.";
//
//					HumusonSDO humusonSDO = humusonCommonService.getSmsData(ordNo, deliveryOrderGoodResult.getChrgerMobilNo(), "DXM",
//							null, DeliveryAssignService.class.getName()+".sendOrderSmsToPartner", smsMsg, null, null);
//
//					parameterList.add(humusonSDO);

					successCnt++;
				}
			}

//			if (parameterList != null && parameterList.size() > 0) {
//				AdapterHeader adapterHeader = new AdapterHeader();
//				adapterHeader.setRequestId(interfaceApiCommon.getRequestId());
//				adapterHeader.setMallId("DXM");
//				adapterHeader.setLangCd("KOR");
//				adapterHeader.setDvcCd("PC");
//
//				result = humusonCommonService.sendSmsMms(parameterList, adapterHeader);
//			}
		}

		if ("SUCCESS".equals(result)) {
			if (log.isDebugEnabled()) {
				log.debug("[주문번호 " + ordNo + " 입점업체 당담자 SMS발송 성공");
			}
		}
	}

	/**
	 * 운송장정보 초기화.
	 * <p/>
	 * <p/>
	 * <p/>
	 * [사용 방법 설명].
	 *
	 * @param retObj [설명]
	 * @throws Exception the exception
	 * @since 2015. 8. 28
	 */
	public void initWayBillNo(DeliveryOrderGoodDTO retObj) throws Exception {
		String userId = BOSecurityUtil.getLoginId(); // 세션ID

		LgsDlv lgsDlv = new LgsDlv();
		lgsDlv.setOrdNo(retObj.getOrdNo());
		lgsDlv.setDlvPcupspTurn(retObj.getDlvPcupspTurn());
		lgsDlv.setDlvTurn(retObj.getDlvTurn());
		lgsDlv.setDmstcWaybilNo(retObj.getDmstcWaybilNo());
		deliveryCommandRepository.updateInitWayBillInfo(lgsDlv);

		// 물류배송 이력 등록
		LgsDlvHist lgsDlvHist = new LgsDlvHist();
		lgsDlvHist.setOrdNo(retObj.getOrdNo());
		lgsDlvHist.setDlvPcupspTurn(retObj.getDlvPcupspTurn());
		lgsDlvHist.setDlvTurn(retObj.getDlvTurn());
		lgsDlvHist.setRegtrId(userId);
		lgsDlvHist.setUdterId("INIT_WAYBILL");
		deliveryCommandRepository.insertLgsDlvHist(lgsDlvHist);
	}
	
	/**
	 * 운송장정보 초기화. (BO 외 실행)
	 * <p/>
	 * <p/>
	 * <p/>
	 * [사용 방법 설명]. BOSecurityUtil 사용안함
	 *
	 * @param retObj [설명]
	 * @throws Exception the exception
	 * @since 2016. 6. 3
	 */
	public void initWayBillNoBt(DeliveryOrderGoodDTO retObj, String caller) throws Exception {
		LgsDlv lgsDlv = new LgsDlv();
		lgsDlv.setOrdNo(retObj.getOrdNo());
		lgsDlv.setDlvPcupspTurn(retObj.getDlvPcupspTurn());
		lgsDlv.setDlvTurn(retObj.getDlvTurn());
		lgsDlv.setDmstcWaybilNo(retObj.getDmstcWaybilNo());
		deliveryCommandRepository.updateInitWayBillInfo(lgsDlv);

		// 물류배송 이력 등록
		LgsDlvHist lgsDlvHist = new LgsDlvHist();
		lgsDlvHist.setOrdNo(retObj.getOrdNo());
		lgsDlvHist.setDlvPcupspTurn(retObj.getDlvPcupspTurn());
		lgsDlvHist.setDlvTurn(retObj.getDlvTurn());
		lgsDlvHist.setRegtrId(caller); // caller
		lgsDlvHist.setUdterId("INIT_WAYBILL");
		deliveryCommandRepository.insertLgsDlvHist(lgsDlvHist);
	}

	/**
	 * 운송장정보 초기화.
	 * <p/>
	 * <p/>
	 * <p/>
	 * [사용 방법 설명].
	 *
	 * @param retObj [설명]
	 * @throws Exception the exception
	 * @since 2015. 8. 28
	 */
	public void initWayBillNoForList(DeliveryOrderGoodResult retObj) throws Exception {
		String userId = BOSecurityUtil.getLoginId(); // 세션ID
		LgsDlv lgsDlv = new LgsDlv();
		lgsDlv.setOrdNo(retObj.getOrd().getOrdNo());
		lgsDlv.setDlvPcupspTurn(retObj.getLgsDsp().getDlvPcupspTurn());
		lgsDlv.setDlvTurn(retObj.getLgsDlv().getDlvTurn());
		lgsDlv.setDmstcWaybilNo(retObj.getLgsDlv().getDmstcWaybilNo());
		deliveryCommandRepository.updateInitWayBillInfo(lgsDlv);

		// 물류배송 이력 등록
		LgsDlvHist lgsDlvHist = new LgsDlvHist();
		lgsDlvHist.setOrdNo(retObj.getOrdNo());
		lgsDlvHist.setDlvPcupspTurn(retObj.getDlvPcupspTurn());
		lgsDlvHist.setDlvTurn(retObj.getDlvTurn());
		lgsDlvHist.setRegtrId(userId);
		lgsDlvHist.setUdterId("INIT_WAYBILL");
		deliveryCommandRepository.insertLgsDlvHist(lgsDlvHist);
	}

	/**
	 * <p/>
	 * <p/>
	 * [사용 방법 설명].
	 *
	 * @param obj [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 10. 30
	 */
	public DeliveryOrderGoodResult selectReAssignDeliveryInfo(DeliverySearchDTO obj) throws Exception {
		return deliveryCommandRepository.selectReAssignDeliveryInfo(obj);
	}

	/**
	 * [패키지] 재배정대상정보 조회
	 * <p/>
	 * .
	 *
	 * @param obj [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 10. 29
	 */
	public List<DeliveryOrderGoodResult> selectReAssignDeliveryInfoPckage(DeliverySearchDTO obj) throws Exception {
		return deliveryCommandRepository.selectReAssignDeliveryInfoPckage(obj);
	}

	/**
	 * [일반] 입점상품출고 배정.
	 * <p/>
	 * <p/>
	 * <p/>
	 * [사용 방법 설명].
	 *
	 * @param result [설명]
	 * @param newDlvShopId [설명]
	 * @return Delivery order good dto [설명]
	 * @throws Exception the exception
	 * @since 2015. 6. 10
	 */
	public DeliveryOrderGoodDTO assignDeliShop4BasicPart(DeliveryOrderGoodResult result, String newDlvShopId) throws Exception {

		DeliveryOrderGoodDTO retDTO = new DeliveryOrderGoodDTO();
		retDTO.setCount(0); // 처리건수
		String userId = BOSecurityUtil.getLoginId(); // 세션ID
		boolean shopStckChck = true; // 매장재고체크여부

		// 배정대상 출고정보 조회
		LgsDlivyDrctGod lgsDdg2 = new LgsDlivyDrctGod();
		lgsDdg2.setOrdNo(result.getOrd().getOrdNo());
		lgsDdg2.setDlivyDrctGodNo(result.getLgsDdg().getDlivyDrctGodNo());
		DeliveryOrderGoodResult obj = deliveryCommandRepository.selectPartMallAssignTargetList(lgsDdg2).get(0);

		// 기존 품절처리된 상품중 ERP출고취소가 미처리된 상품은 재배정 처리 안함
		if ("Y".equals(obj.getLgsDdg().getDlivyDrctYn())) {
			DeliveryStatusException statusException = new DeliveryStatusException(null);
			statusException.setDirectMessage("ERP출고취소가 되지 않은 주문건이 존재합니다.\n확인 후 처리하세요.");
			throw statusException;

		}
		else {

			// 출고매장 지정
			String dlvShopId = newDlvShopId;
			String dlvStatCd = DeliveryEnum.DLV_STAT_DLIVY_DRCT.toString(); // 배송상태 : 출고지시
			String dlivyDrctTgtYn = "N"; // 출고지시대상여부[매장출고]

			retDTO.setItmNo(obj.getOrdGod().getItmNo()); // 단품번호
			retDTO.setDlivyDrctQty(obj.getLgsDdg().getDlivyDrctQty().intValue()); // 출고지시수량
			retDTO.setDlvShopId(dlvShopId); // 신규배송매장

			// ERP 인터페이스 출고지시대상 정보 수정
			InfOrdGodErpDstb infOrdGodErpDstb = new InfOrdGodErpDstb();
			infOrdGodErpDstb.setOrdNo(obj.getOrd().getOrdNo());
			infOrdGodErpDstb.setOrdGodTurn(obj.getOrdGod().getOrdGodTurn());
			infOrdGodErpDstb.setDlivyDrctGodNo(obj.getLgsDdg().getDlivyDrctGodNo());
			infOrdGodErpDstb.setDlivyDrctTgtYn(dlivyDrctTgtYn);
			infOrdGodErpDstb.setDlivyDrctYn("N");
			deliveryCommandRepository.updateInfDeliveryDirectTarget(infOrdGodErpDstb);

			// 물류 배송매장 주문확인 컬럼 업데이트 처리 필요.
			LgsDlv lgsDlv = new LgsDlv();
			lgsDlv.setOrdNo(result.getOrd().getOrdNo());
			lgsDlv.setDlvPcupspTurn(result.getLgsDdg().getDlvPcupspTurn());
			lgsDlv.setDlvTurn(result.getLgsDdg().getDlvTurn());
			lgsDlv.setOrdCnfirmYn("Y");
			deliveryCommandRepository.updateRtrvlWaybilInfo(lgsDlv);
			
			LgsDlvHist lgsDlvHist = new LgsDlvHist();
			lgsDlvHist.setOrdNo(result.getOrd().getOrdNo());
			lgsDlvHist.setDlvPcupspTurn(result.getLgsDdg().getDlvPcupspTurn());
			lgsDlvHist.setDlvTurn(result.getLgsDdg().getDlvTurn());
			deliveryCommandRepository.insertLgsDlvHist(lgsDlvHist);
			
			// 배정정보 수정
			LgsDlivyDrctGod lgsDdg = new LgsDlivyDrctGod();
			lgsDdg.setDlivyDrctGodNo(obj.getLgsDdg().getDlivyDrctGodNo());
			lgsDdg.setDlivyDrctQty(obj.getLgsDdg().getDlivyDrctQty());
			lgsDdg.setDlvShopId(dlvShopId); // 배송매장
			lgsDdg.setDlvStatCd(dlvStatCd); // 배송상태
			lgsDdg.setLgsItmYn("Y"); // 단품여부
			lgsDdg.setDlivyDrctTgtYn(dlivyDrctTgtYn); // 출고지시대상여부
			lgsDdg.setOrdNo(obj.getOrd().getOrdNo()); // 주문번호
			lgsDdg.setOrdGodTurn(obj.getOrdGod().getOrdGodTurn()); // 상품순번

			lgsDdg.setUdterId(userId);
			deliveryCommandRepository.updateAssignShopInfo(lgsDdg);

			// 물류 출고지시 상품 이력 등록
			LgsDlivyDrctGodHist lgsDlivyDrctGodHist = new LgsDlivyDrctGodHist();
			lgsDlivyDrctGodHist.setRegtrId("ASSIGN_TARGET_SHOP");
			lgsDlivyDrctGodHist.setUdterId(userId);
			lgsDlivyDrctGodHist.setDlivyDrctGodNo(obj.getLgsDdg().getDlivyDrctGodNo());
			deliveryCommandRepository.insertLgsDlivyDrctGodHist(lgsDlivyDrctGodHist);

			retDTO.setOrdNo(obj.getOrd().getOrdNo());
			retDTO.setOrdStatCd(obj.getOrd().getOrdStatCd());
			retDTO.setOrdGodTurn(obj.getOrdGod().getOrdGodTurn());
			retDTO.setDlivyDrctGodNo(obj.getLgsDdg().getDlivyDrctGodNo());
			retDTO.setInvAplYn(obj.getLgsDdg().getInvAplYn()); // 재고적용여부
			retDTO.setDlvShopId(dlvShopId);
			retDTO.setDlvStatCd(dlvStatCd);
			retDTO.setLmttInvYn(obj.getLmttInvYn());
			retDTO.setDlvPcupspTurn(obj.getLgsDsp().getDlvPcupspTurn());
			retDTO.setDlvTurn(obj.getLgsDlv().getDlvTurn());
			retDTO.setDmstcWaybilNo(obj.getLgsDlv().getDmstcWaybilNo());
			if (shopStckChck) {
				retDTO.setShopStckChckYn("Y");
			}
			else {
				retDTO.setShopStckChckYn("N");
			}
			retDTO.setCount(1); // 처리건수
		}
		return retDTO;
	}

	/**
	 * [패키지] 입점상품출고 배정.
	 * <p/>
	 * <p/>
	 * <p/>
	 * [사용 방법 설명].
	 *
	 * @param packList [설명]
	 * @param newDlvShopId [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 6. 10
	 */
	public List<DeliveryOrderGoodResult> assignDeliShop4PckagePart(List<DeliveryOrderGoodResult> packList, String newDlvShopId)
	        throws Exception {

		List<DeliveryOrderGoodResult> retList = new ArrayList<DeliveryOrderGoodResult>(); // 처리결과
		                                                                                  // 리스트
		String userId = BOSecurityUtil.getLoginId(); // 세션ID

		String dlvShopId = newDlvShopId; // 배송매장
		String dlvStatCd = DeliveryEnum.DLV_STAT_DLIVY_DRCT.toString(); // 배송상태 : 출고지시
		String dlivyDrctTgtYn = "N"; // 출고지시대상여부 :: 매장출고

		// 패키지 묶음 단일 처리
		List<DeliverySearchDTO> newList = this.filterDuplPackageItem(packList);

		for (DeliverySearchDTO param : newList) {
			// 패키지 구성상품 조회 :: 구성품별 묶음처리
			List<DeliveryOrderGoodResult> list = deliveryCommandRepository.selectReAssignDeliveryInfoPckage(param);

			for (DeliveryOrderGoodResult packObj2 : list) {

				// ERP 인터페이스 출고지시대상 정보 수정
				InfOrdGodErpDstb infOrdGodErpDstb = new InfOrdGodErpDstb();
				infOrdGodErpDstb.setOrdNo(packObj2.getOrd().getOrdNo());
				infOrdGodErpDstb.setOrdGodTurn(packObj2.getOrdGod().getOrdGodTurn());
				infOrdGodErpDstb.setDlivyDrctGodNo(packObj2.getLgsDdg().getDlivyDrctGodNo());
				infOrdGodErpDstb.setDlivyDrctTgtYn(dlivyDrctTgtYn);
				infOrdGodErpDstb.setDlivyDrctYn("N");
				deliveryCommandRepository.updateInfDeliveryDirectTarget(infOrdGodErpDstb);

				// 물류 배송매장 주문확인 컬럼 업데이트 처리 필요.
				LgsDlv lgsDlv = new LgsDlv();
				lgsDlv.setOrdNo(packObj2.getOrd().getOrdNo());
				lgsDlv.setDlvPcupspTurn(packObj2.getLgsDdg().getDlvPcupspTurn());
				lgsDlv.setDlvTurn(packObj2.getLgsDdg().getDlvTurn());
				lgsDlv.setOrdCnfirmYn("Y");
				deliveryCommandRepository.updateRtrvlWaybilInfo(lgsDlv);
				
				LgsDlvHist lgsDlvHist = new LgsDlvHist();
				lgsDlvHist.setOrdNo(packObj2.getOrd().getOrdNo());
				lgsDlvHist.setDlvPcupspTurn(packObj2.getLgsDdg().getDlvPcupspTurn());
				lgsDlvHist.setDlvTurn(packObj2.getLgsDdg().getDlvTurn());
				deliveryCommandRepository.insertLgsDlvHist(lgsDlvHist);
				
				// 배정정보 수정
				LgsDlivyDrctGod lgsDdg = new LgsDlivyDrctGod();
				lgsDdg.setDlivyDrctGodNo(packObj2.getLgsDdg().getDlivyDrctGodNo());
				lgsDdg.setDlivyDrctQty(packObj2.getLgsDdg().getDlivyDrctQty());
				lgsDdg.setDlvShopId(dlvShopId); // 배송매장
				lgsDdg.setDlvStatCd(dlvStatCd); // 배송상태
				lgsDdg.setLgsItmYn("Y"); // 단품여부
				lgsDdg.setDlivyDrctTgtYn(dlivyDrctTgtYn); // 출고지시대상여부
				lgsDdg.setUdterId(userId);
				lgsDdg.setOrdNo(packObj2.getOrd().getOrdNo()); // 주문번호
				lgsDdg.setOrdGodTurn(packObj2.getOrdGod().getOrdGodTurn()); // 상품순번
				deliveryCommandRepository.updateAssignShopInfo(lgsDdg);

				// 물류 출고지시 상품 이력 등록
				LgsDlivyDrctGodHist lgsDlivyDrctGodHist = new LgsDlivyDrctGodHist();
				lgsDlivyDrctGodHist.setRegtrId("ASSIGN_TARGET_SHOP");
				lgsDlivyDrctGodHist.setUdterId(userId);
				lgsDlivyDrctGodHist.setDlivyDrctGodNo(packObj2.getLgsDdg().getDlivyDrctGodNo());
				deliveryCommandRepository.insertLgsDlivyDrctGodHist(lgsDlivyDrctGodHist);

				// 한정재고 체크
				DeliveryOrderGoodDTO chkDTO = new DeliveryOrderGoodDTO();
				chkDTO.setItmNo(packObj2.getOrdGod().getItmNo()); // 단품번호
				chkDTO.setDlivyDrctQty(packObj2.getLgsDdg().getDlivyDrctQty().intValue()); // 출고지시수량

				GodItm godItm = new GodItm();
				boolean shopStckChck = false;
				if ("Y".equals(packObj2.getLmttInvYn())) {

					// 한정재고 수량 조회
					godItm = deliveryCommandRepository.selectLmttInvQty4ItemStock(chkDTO);

					// 온라인 한정재고 체크
					if (godItm.getOnlneLmttInvQty() < 0) {
						shopStckChck = false;
					}
				}

				packObj2.setNewDlvShopId(dlvShopId);
				packObj2.setLmttInvYn(packObj2.getLmttInvYn());
				packObj2.setOrdNo(packObj2.getOrd().getOrdNo());
				packObj2.setDlvPcupspTurn(packObj2.getLgsDsp().getDlvPcupspTurn());
				packObj2.setDlvTurn(packObj2.getLgsDlv().getDlvTurn());
				packObj2.setDmstcWaybilNo(packObj2.getLgsDlv().getDmstcWaybilNo());
				if (shopStckChck) {
					packObj2.setShopStckChckYn("Y");
				}
				else {
					packObj2.setShopStckChckYn("N");
				}

				retList.add(packObj2);
			}
		}

		return retList;
	}

	/**
	 * 24시간 초과 또는 출고 거절시 진행단계 변경 - 배송매장 자동배정 관련 추가
	 * <p/>
	 * <p/>
	 * <p/>
	 * [사용 방법 설명].
	 *
	 * @param retObj [설명]
	 * @throws Exception the exception
	 * @since 2016. 5. 2
	 */
	public void updateDlvStatData(DeliveryOrderGoodDTO dlivyDrctInfo) throws Exception {
		int vriablRsultcnt = 0;
		int drctRsultcnt = 0;
		String rejectAdminId = "";
		String regtrId = "";
		String udterId = "";
		String ori_dlv_shop = dlivyDrctInfo.getDlvShopId(); // 매장 정보		

		// 자동배치의 경우 userid가 없음
		if (dlivyDrctInfo.getRejectAdminId() == null || dlivyDrctInfo.getRejectAdminId().equals("")) {
			rejectAdminId = "sysadmin";
			regtrId = "AUTO_ASGN_REJECT_BATCH";
			udterId = "AUTO_ASGN_REJECT_BATCH";
		}
		else {
			rejectAdminId = dlivyDrctInfo.getRejectAdminId();
			regtrId = dlivyDrctInfo.getRegtrId();
			udterId = "ASGN_REJECT (" + dlivyDrctInfo.getRegtrId() + ")";
		}
		dlivyDrctInfo.setRegtrId(regtrId);
		dlivyDrctInfo.setUdterId(udterId);
		
		log.info("Ord_no :: " + dlivyDrctInfo.getOrdNo() + "|" + "DlivyDrctGodNo :: " + dlivyDrctInfo.getDlivyDrctGodNo()  + "|" + "RejectResnCd :: " + dlivyDrctInfo.getRejectResnCd() + "|" + "DlivyDrctQty :: " + dlivyDrctInfo.getDlivyDrctQty());

		dlivyDrctInfo.setRejectQty(dlivyDrctInfo.getDlivyDrctQty()); // 출고지시 수량 : (출고지시 수량 - 출고지시 취소 수량)

		vriablRsultcnt = 0; // 물류 자동 배정 운영 변수 중 거부 수량 수정

		dlivyDrctInfo.setRejectCnt(dlivyDrctInfo.getRejectCnt() + 1);
		dlivyDrctInfo.setDlvShopId("B031");

		// 1차 거절
		if (dlivyDrctInfo.getRejectCnt() == 1) {
			dlivyDrctInfo.setDlvStatCd("DLV_WAIT"); // 배정대기
			// 2차 거절 이상
		}
		else if (dlivyDrctInfo.getRejectCnt() > 1) {
			dlivyDrctInfo.setDlvStatCd("SHTG_RCEPT"); // 결품접수
		}

		drctRsultcnt = deliveryCommandRepository.updateDlvDrctGodCnt(dlivyDrctInfo); // 매장 및 배송상태 변경

		// 상태변경 성공
		if (vriablRsultcnt > 0 && drctRsultcnt > 0) {

			// 물류 출고지시 상품 이력 등록
			LgsDlivyDrctGodHist lgsDlivyDrctGodHist = new LgsDlivyDrctGodHist();
			lgsDlivyDrctGodHist.setRegtrId(regtrId);
			lgsDlivyDrctGodHist.setUdterId(udterId);
			lgsDlivyDrctGodHist.setDlivyDrctGodNo(dlivyDrctInfo.getDlivyDrctGodNo());
			deliveryCommandRepository.insertLgsDlivyDrctGodHist(lgsDlivyDrctGodHist);

			// ERP 예약영수증 변경 대상 조회
			DeliverySearchDTO deliverySearchDTO = new DeliverySearchDTO();
			deliverySearchDTO.setOrdNo(dlivyDrctInfo.getOrdNo());
			deliverySearchDTO.setDlivyDrctGodNo(dlivyDrctInfo.getDlivyDrctGodNo());
			List<DeliveryInfErpDTO> rlist = deliveryInfErpService.selectErpResveRcptfrCancelInfoList(deliverySearchDTO);

			for (int i = 0; i < rlist.size(); i++) {
				rlist.get(i).setWerks("B031"); // 매장 셋팅
			}

			deliveryInfErpService.modifyPreSalesDlvShopChangeNew(rlist, regtrId, "");
		}
	}

	/**
	 * 시스템 시간 조회.
	 *
	 * @return the hash map
	 * @throws Exception the exception
	 */
	public HashMap<String, String> selectSystemCurrentTimeInfo() throws Exception {
		return deliveryCommandRepository.selectSystemCurrentTimeInfo();
	}

	/***********************************************
	 * 배송외 파트 작업 영역[S]
	 *************************************************************/

	/***********************************************
	 * 배송외 파트 작업 영역[E]
	 *************************************************************/

	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */

}
