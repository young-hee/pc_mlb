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
package com.plgrim.ncp.cmp.orderfulfilment.bo.delivery.release;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.codehaus.plexus.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.plgrim.ncp.base.entities.RepositoryHelper;
import com.plgrim.ncp.base.entities.datasource1.god.GodItm;
import com.plgrim.ncp.base.entities.datasource1.god.GodShopItmInv;
import com.plgrim.ncp.base.entities.datasource1.god.GodShopItmInvExtend;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsDlivyDrctGod;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsDlivyDrctGodExtend;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsDlivyDrctGodHist;
import com.plgrim.ncp.base.entities.datasource1.ord.Ord;
import com.plgrim.ncp.base.entities.datasource1.sys.SysShopExtends;
import com.plgrim.ncp.base.enums.DeliveryEnum;
import com.plgrim.ncp.base.enums.GoodsEnum;
import com.plgrim.ncp.biz.delivery.data.DeliveryInfErpDTO;
import com.plgrim.ncp.biz.delivery.data.DeliveryInvoiceDTO;
import com.plgrim.ncp.biz.delivery.data.DeliveryOrderGoodDTO;
import com.plgrim.ncp.biz.delivery.data.DeliverySearchDTO;
import com.plgrim.ncp.biz.delivery.data.DlvOrdGodInfoDTO;
import com.plgrim.ncp.biz.delivery.data.LgsDlivyWayBil;
import com.plgrim.ncp.biz.delivery.exception.DeliveryAutoAssignException;
import com.plgrim.ncp.biz.delivery.exception.DeliveryStatusException;
import com.plgrim.ncp.biz.delivery.repository.DeliveryCommandRepository;
import com.plgrim.ncp.biz.delivery.repository.DeliverySelectRepository;
import com.plgrim.ncp.biz.delivery.result.DeliveryInvoiceResult;
import com.plgrim.ncp.biz.delivery.result.DeliveryOrderGoodResult;
import com.plgrim.ncp.biz.delivery.service.DeliveryAssignService;
import com.plgrim.ncp.biz.delivery.service.DeliveryAutoAssignService;
import com.plgrim.ncp.biz.delivery.service.DeliveryCommandService;
import com.plgrim.ncp.biz.delivery.service.DeliveryInfErpService;
import com.plgrim.ncp.biz.delivery.service.DeliveryListService;
import com.plgrim.ncp.biz.delivery.service.DeliveryStatusService;
import com.plgrim.ncp.biz.goods.data.GoodsDTO;
import com.plgrim.ncp.biz.order.repository.OrderBoCommandRepository;
import com.plgrim.ncp.biz.order.service.OrderBoService;
import com.plgrim.ncp.biz.vendor.data.VendorBrndSearchDTO;
import com.plgrim.ncp.biz.vendor.result.VendorBrndListResult;
import com.plgrim.ncp.cmp.orderfulfilment.bo.delivery.DeliveryReleaseBoComponent;
import com.plgrim.ncp.cmp.product.fo.GoodsInventoryFOComponent;
import com.plgrim.ncp.commons.util.BOSecurityUtil;
import com.plgrim.ncp.framework.commons.JsonService;
import com.plgrim.ncp.framework.commons.StringService;
import com.plgrim.ncp.framework.data.SystemPK;
import com.plgrim.ncp.interfaces.order.adapter.OrderAdapter;
import com.plgrim.ncp.interfaces.order.data.OrderOnlineShopRtListSDO;
import com.plgrim.ncp.interfaces.order.data.OrderOnlineShopRtResultSDO;
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
public class DeliveryReleaseBoComponentImpl implements DeliveryReleaseBoComponent {

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	@Autowired
	DeliveryListService deliveryListService;

	@Autowired
	DeliveryAssignService deliveryAssignService;
	
	@Autowired
	DeliveryInfErpService deliveryInfErpService;
	
	@Autowired
	OrderBoService orderBoService;
	
	@Autowired
	DeliveryStatusService deliveryStatusService;
	
	@Autowired
	DeliveryCommandService deliveryCommandService;
	
	@Autowired
	DeliveryAutoAssignService deliveryAutoAssignService;
	
	@Autowired
	OrderBoCommandRepository orderBoCommandRepository;
	
	@Autowired
	InterfaceApiCommon interfaceApiCommon;
	
	@Autowired
	OrderAdapter orderAdapter;
	
	@Autowired
	DeliveryCommandRepository deliveryCommandRepository;
	
	@Autowired
	GoodsInventoryFOComponent goodsInventoryFOComponent;
	
	@Autowired
	DeliverySelectRepository deliverySelectRepository;
	
	/** 자사상품 출고관리 조회. */
	@Override
	public Page<DeliveryOrderGoodResult> getDrctGoodsList(SystemPK systemPK, DeliverySearchDTO deliverySearchDTO) throws Exception {
		return deliveryListService.getDrctGoodsList(systemPK,deliverySearchDTO);
	}

	/** 자사상품 출고관리조회 excel. */
	@Override
	public List<Map<String, Object>> getDrctGoodsListExcel(SystemPK systemPK, DeliverySearchDTO deliverySearchDTO) throws Exception {
		return deliveryListService.getDrctGoodsListExcel(systemPK, deliverySearchDTO);
	}

	/** 상품출고배정 조회. */
	@Override
	public  List<DeliveryOrderGoodResult> getSelfAssignDeliveryList(SystemPK systemPk, DeliverySearchDTO deliverySearchDTO) throws Exception {
		RepositoryHelper.makePageEntityIndex(deliverySearchDTO, deliverySearchDTO.getPageParam());	// 페이지 설정
		return deliveryListService.getSelfAssignDeliveryList(systemPk, deliverySearchDTO);
	}

	/** 상품출고배정 조회 rowcount. */
	@Override
	public int getSelfAssignDeliveryListCount(SystemPK systemPK, DeliverySearchDTO deliverySearchDTO) throws Exception {
		return deliveryListService.getSelfAssignDeliveryListCount(systemPK,deliverySearchDTO);
	}

	/** 매장별 취급 브랜드 리스트 */
	@Override
	public List<SysShopExtends> getBrndList(String shopId) throws Exception {
		return deliveryListService.getBrndList(shopId);
	}

	/** 배정가능 배송매장 조회 */
	@Override
	public List<VendorBrndListResult> selectAssignDlvShop(SystemPK systemPK, VendorBrndSearchDTO vendorBrndSearchDTO) throws Exception {
		return deliveryListService.selectAssignDlvShop(vendorBrndSearchDTO);
	}

	/** 운송장 상세조회 */
	@Override
	public DeliveryInvoiceResult getInvoice(SystemPK systemPK, DeliverySearchDTO deliverySearchDTO) throws Exception {
		return deliveryListService.getInvoice(systemPK, deliverySearchDTO);
	}

	/** 운송장 이력 목록 */
	@Override
	public List<DeliveryInvoiceResult> getInvoiceHistoryList(SystemPK systemPK, DeliverySearchDTO deliverySearchDTO) throws Exception {
		return deliveryListService.getInvoiceHistoryList(systemPK, deliverySearchDTO);
	}

	/** 사은품 출고관리조회. */
	@Override
	public List<DeliveryOrderGoodResult> getGiftDrctGoodsList(SystemPK systemPk, DeliverySearchDTO deliverySearchDTO) throws Exception {
		RepositoryHelper.makePageEntityIndex(deliverySearchDTO, deliverySearchDTO.getPageParam());	// 페이지 설정
		return deliveryListService.getGiftDrctGoodsList(systemPk, deliverySearchDTO);
	}

	/** 사은품 출고관리조회 total count. */
	@Override
	public  int getGiftDrctGoodsListCount(SystemPK systemPk, DeliverySearchDTO deliverySearchDTO) throws Exception {
		return deliveryListService.getGiftDrctGoodsListCount(systemPk, deliverySearchDTO);
	}

	/** 사은품 출고관리조회 excel. */
	@Override
	public List<Map<String, Object>> getGiftDrctGoodsListExcel(SystemPK systemPK, DeliverySearchDTO deliverySearchDTO) throws Exception {
		return deliveryListService.getGiftDrctGoodsListExcel(systemPK,deliverySearchDTO);
	}

	/** 사은품 Picking List. */
	@Override
	public List<DeliveryOrderGoodResult> getGiftDrctGoodsPickingList(SystemPK systemPK, DeliverySearchDTO deliverySearchDTO) throws Exception {
		return deliveryListService.getGiftDrctGoodsPickingList(systemPK,deliverySearchDTO);
	}

	/**
	 * 결품접수(재배정)
	 */
	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRES_NEW)
	public void shortageReassign(SystemPK systemPk, DeliveryOrderGoodResult obj1, DeliverySearchDTO obj2) throws Exception {
		//일반
		if (obj1 != null) {
			DeliverySearchDTO dto = new DeliverySearchDTO();
			dto.setOrdNo(obj1.getOrd().getOrdNo());
			dto.setDlivyDrctGodNo(obj1.getLgsDdg().getDlivyDrctGodNo());
			DeliveryOrderGoodResult obj = deliveryAssignService.selectReAssignDeliveryInfo(dto);

			this.updateReassignSingle(systemPk, obj, null, "DELIVERY");
		}

		//세트	
		if (obj2 != null) {
			this.updateReassignPackage(systemPk, obj2, null, "DELIVERY");
		}
	}
	
	/**
	 * [일반] 재배정.
	 * <p/>
	 * <p/>
	 * <p/>
	 * [사용 방법 설명].
	 *
	 * @param systemPk   [설명]
	 * @param obj        [설명]
	 * @param targetShop [설명]
	 * @param caller     [설명]
	 * @throws Exception the exception
	 * @since 2015. 11. 9
	 */
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRES_NEW)
	private synchronized void updateReassignSingle(SystemPK systemPk, DeliveryOrderGoodResult obj, String targetShop, String caller) throws Exception {
		DeliverySearchDTO deliverySearchDTO = new DeliverySearchDTO();
		GodItm godItm = new GodItm();

		//1. 배송상태 체크
		String chckStat = obj.getLgsDdg().getDlvStatCd();
		if ("DLIVY_DRCT_CNCL".equals(chckStat)) {
			DeliveryStatusException statusException = new DeliveryStatusException(null);
			statusException.setDirectMessage("주문취소건이 존재합니다.\n확인 후 처리하세요.");
			throw statusException;
		}

		//[배송조회]/[출고관리] '결품접수' 기능
		if ("DELIVERY".equals(caller)) {
			//배송상태 체크
			if (!"DLIVY_DRCT".equals(chckStat) && !"SHTG_RCEPT".equals(chckStat)) {
				DeliveryStatusException statusException = new DeliveryStatusException(null);
				statusException.setDirectMessage("재배정 대상이 아닌 건이 존재합니다.\n배송상태를 확인 후 처리하세요.");
				throw statusException;
			}

		//[상품출고지 배정]  '배정' 기능
		} else if ("TARGET".equals(caller)) {
			//배송상태 체크
			if (!"DLV_WAIT".equals(chckStat) && !"B031".equals(obj.getLgsDdg().getDlvShopId())) {
				DeliveryStatusException statusException = new DeliveryStatusException(null);
				statusException.setDirectMessage("재배정 대상이 아닌 건이 존재합니다.\n배송상태를 확인 후 처리하세요.");
				throw statusException;
			}

		//[배송지연리스트]  '배정' 기능
		} else if ("DELAY".equals(caller)) {
			//배송상태체크
			if (!"DLIVY_DRCT".equals(chckStat) && !"SHTG_RCEPT".equals(chckStat) && !"DLV_WAIT".equals(chckStat)) {
				DeliveryStatusException statusException = new DeliveryStatusException(null);
				statusException.setDirectMessage("재배정 대상이 아닌 건이 존재합니다.\n배송상태를 확인 후 처리하세요.");
				throw statusException;
			}

		//[결품조회]  '배정' 기능
		} else if ("SHORTAGE".equals(caller)) {
			if (!"SHTG_RCEPT".equals(chckStat) && !"B031".equals(obj.getLgsDdg().getDlvShopId())) {
				DeliveryStatusException statusException = new DeliveryStatusException(null);
				statusException.setDirectMessage("재배정 대상이 아닌 건이 존재합니다.\n배송상태를 확인 후 처리하세요.");
				throw statusException;
			}
		}

		//일반상품
		if ("N".equals(obj.getLgsDdg().getGftYn())) {
			//2. 물류센터 ERP출고지시건 출고지시 취소
			if (DeliveryEnum.DLV_ONLINE_SHOP_DISCOVERY.toString().equals(obj.getLgsDdg().getDlvShopId()) 
					|| DeliveryEnum.DLV_ONLINE_SHOP_MLB.toString().equals(obj.getLgsDdg().getDlvShopId())
					|| DeliveryEnum.DLV_ONLINE_SHOP_MLB_KIDZ.toString().equals(obj.getLgsDdg().getDlvShopId())
					|| DeliveryEnum.DLV_ONLINE_SHOP_SA.toString().equals(obj.getLgsDdg().getDlvShopId())) {
				if("Y".equals(obj.getLgsDdg().getDlivyDrctYn())) {
					//2-1. ERP CDC 출고지시 정보 조회
					deliverySearchDTO.setOrdNo(obj.getOrd().getOrdNo());
					deliverySearchDTO.setDlivyDrctGodNo(obj.getLgsDdg().getDlivyDrctGodNo());
					List<DeliveryInfErpDTO> dlist = deliveryInfErpService.selectDlivyDrctInfoList(deliverySearchDTO);

					if (dlist.size() > 0) {
						//2-2. ERP CDC 출고지시건 취소 처리
						deliveryInfErpService.modifyPlantOrderCancelNew(dlist, caller);
					}
				}				
			}

			//3. 재배정
			DeliveryOrderGoodDTO retObj = deliveryAssignService.reAssignDeliShop4Single(obj, targetShop, caller);

			if (retObj.getCount() == 1) {
				if (!obj.getLgsDdg().getDlvShopId().equals(retObj.getDlvShopId())) {

					//4. 배송매장 재고조정 :: 매장변경시 판매예정수량 조정
					if ("Y".equals(retObj.getInvAplYn())) {
						//4-1. 신규배정 배송매장 판매예정수량 (+) 처리
//						GodShopItmInvExtend godShopItmInvExtend = new GodShopItmInvExtend();
//						godShopItmInvExtend.setShopId(retObj.getDlvShopId());						//신규배정 배송매장
//						godShopItmInvExtend.setSalePrearngeQtyInt(retObj.getDlivyDrctQty());	//판매예정수량
//						goodsOptStockCommandService.updateGoodsShopItmInvStatusUpdate(systemPk, godShopItmInvExtend, false);

						//한정재고 + 수량수용
						if ("Y".equals(retObj.getLmttInvYn()) && "Y".equals(retObj.getShopStckChckYn())) {
							//제휴주문
//							if("AFF_ORD".equals(obj.getOrd().getOrdTpCd())) {
//								godItm.setItmNo(retObj.getItmNo());
//								godItm.setAffComLmttInvQty(Long.parseLong(retObj.getDlivyDrctQty()*-1+""));
//								goodsOptStockCommandService.updateGoodsItmLmttInv(systemPk, godItm);
//								
//							}
						}
					}

					//5. ERP 예약영수증 변경 대상 조회
					deliverySearchDTO.setOrdNo(obj.getOrd().getOrdNo());
					deliverySearchDTO.setDlivyDrctGodNo(obj.getLgsDdg().getDlivyDrctGodNo());
					List<DeliveryInfErpDTO> rlist = deliveryInfErpService.selectErpResveRcptfrCancelInfoList(deliverySearchDTO);

					if (rlist.size() > 0) {
						//6. ERP 예약영수증 변경 처리
						deliveryInfErpService.modifyPreSalesDlvShopChangeNew(rlist, caller, retObj.getDlvShopDlivyLcId());
					}
				} else {
					if (obj.getLgsDdg().getDlvStatCd().equals(retObj.getDlvStatCd())) {
						if ("SHORTAGE".equals(caller) || "TARGET".equals(caller) || "DELAY".equals(caller)) {
							DeliveryStatusException statusException = new DeliveryStatusException(null);
							statusException.setDirectMessage("미배정 처리.");
							throw statusException;
						}
					}
				}

				DeliveryOrderGoodDTO dto = new DeliveryOrderGoodDTO();
				if (DeliveryEnum.DLV_ONLINE_SHOP_DISCOVERY.toString().equals(retObj.getDlvShopId()) 
						|| DeliveryEnum.DLV_ONLINE_SHOP_MLB.toString().equals(retObj.getDlvShopId())
						|| DeliveryEnum.DLV_ONLINE_SHOP_MLB_KIDZ.toString().equals(retObj.getDlvShopId())
						|| DeliveryEnum.DLV_ONLINE_SHOP_SA.toString().equals(retObj.getDlvShopId()) ) {
					//8. 합포장 상품사은품 모상품과 동일하게 배정처리
					dto.setOrdNo(obj.getOrd().getOrdNo());
					dto.setOrdGodTurn(obj.getOrdGod().getOrdGodTurn());
					List<DeliveryOrderGoodDTO> gftRetList = deliveryAssignService.reAssignDeliShop4Gift(dto);

					if (gftRetList.size() > 0) {
						//10. 사은품 재고 조정
						for (DeliveryOrderGoodDTO gftRetObj : gftRetList) {
							if (!gftRetObj.getNewDlvShopId().equals(gftRetObj.getDlvShopId())) {
								//원 배송매장 판매예정수량 (-) 처리
//								godShopItmInvExtend.setItmNo(gftRetObj.getItmNo());			//상품단품번호
//								godShopItmInvExtend.setShopId(gftRetObj.getDlvShopId());	//원 배송매장
//								godShopItmInvExtend.setSalePrearngeQtyInt(gftRetObj.getDlivyDrctQty()*-1);	//판매예정수량
//								goodsOptStockCommandService.updateGoodsShopItmInvStatusUpdate(systemPk, godShopItmInvExtend, false);

								//신규배정 배송매장 판매예정수량 (+) 처리
//								godShopItmInvExtend.setShopId(gftRetObj.getNewDlvShopId());				//신규배정 배송매장
//								godShopItmInvExtend.setSalePrearngeQtyInt(gftRetObj.getDlivyDrctQty());	//판매예정수량
//								goodsOptStockCommandService.updateGoodsShopItmInvStatusUpdate(systemPk, godShopItmInvExtend, false);
							}
						}
					}
				}

				//11. 송장정보 초기화 :: 재배정된 건에 대해 기존에 채번한 송장번호가 존재한 경우 초기화.
				if (null != retObj.getDmstcWaybilNo() && !"".equals(retObj.getDmstcWaybilNo())) {
					deliveryAssignService.initWayBillNo(retObj);
				}

				//12. 주문상태 수정
				if ("DLIVY_DRCT".equals(retObj.getDlvStatCd())) {
					Ord ord = new Ord();
					ord.setOrdNo(retObj.getOrdNo());
					ord.setUdterId(BOSecurityUtil.getLoginId());
					ord.setOrdStatCd("DLV_PRPARE");    //배송준비
					orderBoService.updateOrdStatAboutCompt(ord);
				}
			}

		//사은품
		} else {
			if ("DELIVERY".equals(caller)) {
				//결품처리
				List<DeliveryOrderGoodResult> gridList = new ArrayList<DeliveryOrderGoodResult>();
				gridList.add(obj);
				deliveryStatusService.updateShortage(systemPk, gridList);
			}
		}
	}
	
	/**
	 * [패키지] 재배정.
	 * <p/>
	 * <p/>
	 * <p/>
	 * [사용 방법 설명].
	 *
	 * @param systemPk   [설명]
	 * @param obj        [설명]
	 * @param targetShop [설명]
	 * @param caller     [설명]
	 * @throws Exception the exception
	 * @since 2015. 11. 9
	 */
	private synchronized void updateReassignPackage(SystemPK systemPk, DeliverySearchDTO obj, String targetShop, String caller) throws Exception {
		DeliverySearchDTO deliverySearchDTO = new DeliverySearchDTO();
//		GodShopItmInvExtend godShopItmInvExtend = new GodShopItmInvExtend();
		GodItm godItm = new GodItm();

		//1. 세트 구성품 조회 :: 미선택 구성품을 위한 별도 처리.
		List<DeliveryOrderGoodResult> packList = deliveryAssignService.selectReAssignDeliveryInfoPckage(obj);

		for (DeliveryOrderGoodResult packObj : packList) {
			//2. 배송상태 체크
			String chckStat = packObj.getLgsDdg().getDlvStatCd();    //배정대상 출고정보 조회
			//[배송조회]/[출고관리] '결품접수' 기능
			if ("DELIVERY".equals(caller)) {
				//배송상태 체크
				if (!"DLIVY_DRCT".equals(chckStat) && !"SHTG_RCEPT".equals(chckStat)) {
					DeliveryStatusException statusException = new DeliveryStatusException(null);
					statusException.setDirectMessage("재배정 대상이 아닌 건이 존재합니다.\n배송상태를 확인 후 처리하세요.");
					throw statusException;
				}

			//[상품출고지 배정]  '배정' 기능
			} else if ("TARGET".equals(caller)) {
				//배송상태 체크
				if (!"DLV_WAIT".equals(chckStat) && !"B031".equals(packObj.getLgsDdg().getDlvShopId())) {
					DeliveryStatusException statusException = new DeliveryStatusException(null);
					statusException.setDirectMessage("재배정 대상이 아닌 건이 존재합니다.\n배송상태를 확인 후 처리하세요.");
					throw statusException;
				}

			//[배송지연리스트]  '배정' 기능
			} else if ("DELAY".equals(caller)) {
				//배송상태체크
				if (!"DLIVY_DRCT".equals(chckStat) && !"SHTG_RCEPT".equals(chckStat) && !"DLV_WAIT".equals(chckStat)) {
					DeliveryStatusException statusException = new DeliveryStatusException(null);
					statusException.setDirectMessage("재배정 대상이 아닌 건이 존재합니다.\n배송상태를 확인 후 처리하세요.");
					throw statusException;
				}

			//[결품조회]  '배정' 기능
			} else if ("SHORTAGE".equals(caller)) {
				if (!"SHTG_RCEPT".equals(chckStat) && !"B031".equals(packObj.getLgsDdg().getDlvShopId())) {
					DeliveryStatusException statusException = new DeliveryStatusException(null);
					statusException.setDirectMessage("재배정 대상이 아닌 건이 존재합니다.\n배송상태를 확인 후 처리하세요.");
					throw statusException;
				}
			}

			//3. 물류센터 ERP출고지시건 출고지시 취소
			if (DeliveryEnum.DLV_ONLINE_SHOP_DISCOVERY.toString().equals(packObj.getLgsDdg().getDlvShopId()) 
					|| DeliveryEnum.DLV_ONLINE_SHOP_MLB.toString().equals(packObj.getLgsDdg().getDlvShopId())
					|| DeliveryEnum.DLV_ONLINE_SHOP_MLB_KIDZ.toString().equals(packObj.getLgsDdg().getDlvShopId())
					|| DeliveryEnum.DLV_ONLINE_SHOP_SA.toString().equals(packObj.getLgsDdg().getDlvShopId())) {
				if("Y".equals(packObj.getLgsDdg().getDlivyDrctYn())) {
					//3-1. ERP CDC 출고지시 정보 조회
					deliverySearchDTO.setOrdNo(packObj.getOrd().getOrdNo());
					deliverySearchDTO.setDlivyDrctGodNo(packObj.getLgsDdg().getDlivyDrctGodNo());
					List<DeliveryInfErpDTO> dlist = deliveryInfErpService.selectDlivyDrctInfoList(deliverySearchDTO);

					if (dlist.size() > 0) {
						//3-2. ERP CDC 출고지시건 취소 처리
						deliveryInfErpService.modifyPlantOrderCancelNew(dlist, caller);
					}
				}				
			}
		}

		//4. 패키지 배정
		List<DeliveryOrderGoodResult> retList = deliveryAssignService.reAssignDeliShop4Package(obj, targetShop, caller);

		String orgDlvShoId = "";
		String newDlvShopId = "";
		for (DeliveryOrderGoodResult retObj : retList) {
			orgDlvShoId = retObj.getLgsDdg().getDlvShopId();
			newDlvShopId = retObj.getNewDlvShopId();

			//5. 배송매장 재고조정 :: 매장변경시 판매예정수량/한정재고 조정
			if (!newDlvShopId.equals(orgDlvShoId)) {
				if ("Y".equals(retObj.getLgsDdg().getInvAplYn())) {
					//원 배송매장 판매예정수량 (-) 처리
//					godShopItmInvExtend.setItmNo(retObj.getOrdGod().getItmNo());	//상품단품번호
//					godShopItmInvExtend.setShopId(orgDlvShoId);							//원 배송매장
//					godShopItmInvExtend.setSalePrearngeQtyInt(retObj.getLgsDdg().getDlivyDrctQty().intValue()*-1);	//판매예정수량
//					goodsOptStockCommandService.updateGoodsShopItmInvStatusUpdate(systemPk, godShopItmInvExtend, false);

					//신규배정 배송매장 판매예정수량 (+) 처리
//					godShopItmInvExtend.setShopId(retObj.getNewDlvShopId());		//신규배정 배송매장
//					godShopItmInvExtend.setSalePrearngeQtyInt(retObj.getLgsDdg().getDlivyDrctQty().intValue());	//판매예정수량
//					goodsOptStockCommandService.updateGoodsShopItmInvStatusUpdate(systemPk, godShopItmInvExtend, false);

					//한정재고 + 수량수용
					if ("Y".equals(retObj.getLmttInvYn()) && "Y".equals(retObj.getShopStckChckYn())) {
						//제휴주문
						if ("AFF_ORD".equals(retObj.getOrd().getOrdTpCd())) {
//							godItm.setItmNo(retObj.getOrdGod().getItmNo());
//							godItm.setAffComLmttInvQty(retObj.getLgsDdg().getDlivyDrctQty()*-1);
//							goodsOptStockCommandService.updateGoodsItmLmttInv(systemPk, godItm);

						}
					}
				}

				//6. ERP 예약영수증 변경 대상 조회
				deliverySearchDTO.setOrdNo(retObj.getOrd().getOrdNo());
				deliverySearchDTO.setDlivyDrctGodNo(retObj.getLgsDdg().getDlivyDrctGodNo());
				List<DeliveryInfErpDTO> rlist = deliveryInfErpService.selectErpResveRcptfrCancelInfoList(deliverySearchDTO);

				if (rlist.size() > 0) {
					//7. ERP 예약영수증 변경 처리
					deliveryInfErpService.modifyPreSalesDlvShopChangeNew(rlist, caller, retObj.getDlvShopDlivyLcId());
				}
			}

			//9. 합포장 상품사은품 모상품과 동일하게 배정처리
			DeliveryOrderGoodDTO dto = new DeliveryOrderGoodDTO();
			dto.setOrdNo(retObj.getOrd().getOrdNo());
			dto.setOrdGodTurn(retObj.getOrdGod().getOrdGodTurn());
			List<DeliveryOrderGoodDTO> gftRetList = deliveryAssignService.reAssignDeliShop4Gift(dto);

			if (gftRetList.size() > 0) {
				//10. 사은품 재고 조정
				for (DeliveryOrderGoodDTO gftRetObj : gftRetList) {
					if (!gftRetObj.getNewDlvShopId().equals(gftRetObj.getDlvShopId())) {
						//원 배송매장 판매예정수량 (-) 처리
//						godShopItmInvExtend.setItmNo(gftRetObj.getItmNo());			//상품단품번호
//						godShopItmInvExtend.setShopId(gftRetObj.getDlvShopId());	//원 배송매장
//						godShopItmInvExtend.setSalePrearngeQtyInt(gftRetObj.getDlivyDrctQty()*-1);	//판매예정수량
//						goodsOptStockCommandService.updateGoodsShopItmInvStatusUpdate(systemPk, godShopItmInvExtend, false);

						//신규배정 배송매장 판매예정수량 (+) 처리
//						godShopItmInvExtend.setShopId(gftRetObj.getNewDlvShopId());				//신규배정 배송매장
//						godShopItmInvExtend.setSalePrearngeQtyInt(gftRetObj.getDlivyDrctQty());	//판매예정수량
//						goodsOptStockCommandService.updateGoodsShopItmInvStatusUpdate(systemPk, godShopItmInvExtend, false);
					}
				}
			}

			//11. 송장정보 초기화 :: 재배정된 건에 대해 기존에 채번한 송장번호가 존재한 경우 초기화.
			if (null != retObj.getDmstcWaybilNo() && !"".equals(retObj.getDmstcWaybilNo())) {
				deliveryAssignService.initWayBillNoForList(retObj);
			}

			//12. 주문상태 수정
			if ("DLIVY_DRCT".equals(retObj.getLgsDdg().getDlvStatCd())) {
				Ord ord = new Ord();
				ord.setOrdNo(retObj.getOrdNo());
				ord.setUdterId(BOSecurityUtil.getLoginId());
				ord.setOrdStatCd("DLV_PRPARE");    //배송준비
				orderBoService.updateOrdStatAboutCompt(ord);
			}
		}
	}
	
	/**
	 * [물류센터전용] 물류결품처리
	 */
	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRES_NEW)
	public void dstrbtCenterShortage(SystemPK systemPk, DeliveryOrderGoodResult obj1, DeliveryOrderGoodResult obj2) throws Exception {
		//일반
		if (obj1 != null) {
			DeliverySearchDTO dto = new DeliverySearchDTO();
			dto.setOrdNo(obj1.getOrd().getOrdNo());
			dto.setDlivyDrctGodNo(obj1.getLgsDdg().getDlivyDrctGodNo());
			DeliveryOrderGoodResult obj = deliveryAssignService.selectReAssignDeliveryInfo(dto);

			this.updateDstrbtCenterShortageProc(systemPk, obj);
		}
	}
	
	/**
	 * 물류결품처리.
	 * <p/>
	 * <p/>
	 * <p/>
	 * [사용 방법 설명].
	 *
	 * @param systemPk [설명]
	 * @param obj      [설명]
	 * @throws Exception the exception
	 * @since 2015. 10. 29
	 */
	private synchronized void updateDstrbtCenterShortageProc(SystemPK systemPk, DeliveryOrderGoodResult obj) throws Exception {
		String userId = BOSecurityUtil.getLoginId();	//세션ID

		//1. 출고상품 상태 확인
		if ("DLIVY_DRCT_CNCL".equals(obj.getLgsDdg().getDlvStatCd())) {
			DeliveryStatusException statusException = new DeliveryStatusException(null);
			statusException.setDirectMessage("주문취소건이 존재합니다.\n확인 후 처리하세요.");
			throw statusException;
		} else if (!"DLIVY_DRCT".equals(obj.getLgsDdg().getDlvStatCd())) {
			DeliveryStatusException statusException = new DeliveryStatusException(null);
			statusException.setDirectMessage("배송상태가 변경된 건이 존재합니다.\n확인 후 처리하세요.");
			throw statusException;
		}

		//배정정보 수정
		LgsDlivyDrctGod lgsDdg = new LgsDlivyDrctGod();
		lgsDdg.setDlivyDrctGodNo(obj.getLgsDdg().getDlivyDrctGodNo());
		lgsDdg.setDlivyDrctQty(obj.getLgsDdg().getDlivyDrctQty());
		lgsDdg.setDlvStatCd("SHTG_RCEPT");			//배송상태
		lgsDdg.setLgsItmYn("Y");							//단품여부
		lgsDdg.setDlivyDrctTgtYn("N");					//출고지시대상여부
		lgsDdg.setUdterId(userId);
		deliveryCommandRepository.updateAssignShopInfo(lgsDdg);
		
		//물류 출고지시 상품 이력 등록
		LgsDlivyDrctGodHist lgsDlivyDrctGodHist = new LgsDlivyDrctGodHist();
		lgsDlivyDrctGodHist.setRegtrId("SHOP_SHORTAGE");
		lgsDlivyDrctGodHist.setUdterId(userId);
		lgsDlivyDrctGodHist.setDlivyDrctGodNo(obj.getLgsDdg().getDlivyDrctGodNo());
		deliveryCommandRepository.insertLgsDlivyDrctGodHist(lgsDlivyDrctGodHist);
	}
	
	/**
	 * 상품출고지 배정(재배정)
	 */
	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRES_NEW)
	public void assignDleliveryShop(SystemPK systemPk, DeliveryOrderGoodResult obj1, DeliverySearchDTO obj2, String targetShop) throws Exception {
		//일반
		if (obj1 != null) {
			DeliverySearchDTO dto = new DeliverySearchDTO();
			dto.setOrdNo(obj1.getOrd().getOrdNo());
			dto.setDlivyDrctGodNo(obj1.getLgsDdg().getDlivyDrctGodNo());
			DeliveryOrderGoodResult obj = deliveryAssignService.selectReAssignDeliveryInfo(dto);

			this.updateReassignSingle(systemPk, obj, targetShop, "TARGET");
		}

		//세트	
		if (obj2 != null) {
			this.updateReassignPackage(systemPk, obj2, targetShop, "TARGET");
		}
	}
	
	/**
	 * 배송지연 상품관리(재배정) / 결품조회(재배정))
	 */
	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRES_NEW)
	public void reAssignDeliveryShop(SystemPK systemPk, DeliveryOrderGoodResult obj1, DeliverySearchDTO obj2, String targetShop, String caller) throws Exception {
		//일반
		if (obj1 != null) {
			DeliverySearchDTO dto = new DeliverySearchDTO();
			dto.setOrdNo(obj1.getOrd().getOrdNo());
			dto.setDlivyDrctGodNo(obj1.getLgsDdg().getDlivyDrctGodNo());
			DeliveryOrderGoodResult obj = deliveryAssignService.selectReAssignDeliveryInfo(dto);

			this.updateReassignSingle(systemPk, obj, targetShop, caller);
		}

		//세트	
		if (obj2 != null) {
			this.updateReassignPackage(systemPk, obj2, targetShop, caller);
		}
	}
	
	/** 운송장 수정 */
	@Override
	public int updateInvoice(SystemPK systemPK, DeliveryInvoiceDTO deliveryInvoiceDTO) throws Exception {
		return deliveryCommandService.updateInvoice(systemPK, deliveryInvoiceDTO);
	}
	
	@Override
	public void drctGodCompt(SystemPK systemPk, DeliveryOrderGoodDTO dto) throws Exception {
		LgsDlivyDrctGod lgsDdg = new LgsDlivyDrctGod();
		DeliveryInvoiceDTO sdto = new DeliveryInvoiceDTO();
		LgsDlivyDrctGodHist hist = new LgsDlivyDrctGodHist();

		String userId = BOSecurityUtil.getLoginId();    //세션ID

		//2. 출고지시상품데이터 조회 및 주문취소여부 확인
		lgsDdg.setDlivyDrctGodNo(dto.getDlivyDrctGodNo());
		LgsDlivyDrctGod info = deliveryStatusService.selectLgsDdgInfo(dto);

		if ("DLIVY_DRCT_CNCL".equals(info.getDlvStatCd())) {
			DeliveryStatusException statusException = new DeliveryStatusException(null);
			statusException.setDirectMessage("주문취소건이 존재합니다.\n확인 후 처리하세요.");
			throw statusException;
		}

		//3. 배송상태 수정
		sdto.setDlivyDrctGodNo(info.getDlivyDrctGodNo());
		sdto.setDlivyDrctQty(info.getDlivyDrctQty().intValue());
		sdto.setUdterId(userId);
		sdto.setDlvStatCd("DLV_COMPT");
		deliveryStatusService.updateDeliveryStat(sdto);

		//4. 출고지시 상품 이력 등록
		hist.setDlivyDrctGodNo(info.getDlivyDrctGodNo());
		hist.setRegtrId("TRANS_ERP");
		hist.setUdterId(userId);
		deliveryStatusService.insertLgsDlivyDrctGodHist(hist);

		Ord ord = new Ord();
		ord.setOrdNo(info.getOrdNo());
		ord.setUdterId(userId);
		ord.setOrdStatCd("DLV_COMPT");  //배송완료
		orderBoService.updateOrdStatAboutCompt(ord);
	}
	
	/** 신규 운송장출력 */
	@Override
	public String makeNewWayBill(SystemPK systemPk, List<DeliveryOrderGoodResult> gridList, String call_Id) throws Exception {

		String wayBilNo;
		//1. 운송장번호 채번
		wayBilNo = deliveryStatusService.selectWayBilSeq();

		//2. 대상 필터링
		List<DeliveryOrderGoodDTO> target = deliveryStatusService.filterlingTarget(gridList);

		//3. 운송장정보 등록 처리
		deliveryStatusService.divideWayBilInfo(target, gridList, wayBilNo, call_Id);

		return wayBilNo;
	}

    /** 신규 용차 운송장출력 */
    @Override
    public String makeNewFrgnWayBill(SystemPK systemPk, List<DeliveryOrderGoodResult> gridList) throws Exception {

        //1. 운송장번호 채번
        String wayBilNo = deliveryStatusService.selectFrgnWayBilSeq();

        //2. 대상 필터링
        List<DeliveryOrderGoodDTO> target = deliveryStatusService.filterlingTarget(gridList);

        //3. 용차 운송장정보 등록 처리
        deliveryStatusService.divideFrgnWayBilInfo(target, gridList, wayBilNo);

        return wayBilNo;
    }
    
    /**
     * 용차번호 일괄생성
     * 검색조건에 해당하는 대상의 용차운송장을 생성한다.
     * 송장생성시 주문번호, 배송수거지순번, 배송순번, 배송매장ID 기준으로 데이터 분리 필요.
     */
    public int updateSearchFrgnWaybillNo(SystemPK systemPK, DeliverySearchDTO deliverySearchDTO) throws Exception {

        int procCnt = 0;	//처리건수

        //1. 처리대상 조회
        List<DeliveryOrderGoodResult> list = deliveryStatusService.selectSearchWaybillNoList(deliverySearchDTO);

        //2. 운송장 생성 ( TMALL 의 경우 용차번호 생성 )
        procCnt = this.updateSelectedFrgnWaybillNo(systemPK, list); // 용차번호 배정 ( 차수별 용차시퀀스 배번, 검수 과정에서 분기하여 출고하기로 함 )

        return procCnt;
    }

	/**
     * 용차 운송장번호 개별생성
     * 송장생성시 주문번호, 배송수거지순번, 배송순번, 배송상태, 배송매장ID, 차수, DAS 기준으로 데이터 분리.
     */
    public int updateSelectedFrgnWaybillNo(SystemPK systemPK, List<DeliveryOrderGoodResult> gridList) throws Exception {
        List<DeliveryOrderGoodResult> targetList = new ArrayList<DeliveryOrderGoodResult>();
        List<DeliveryOrderGoodResult> packList = new ArrayList<DeliveryOrderGoodResult>();
        String userId = BOSecurityUtil.getLoginId();	//세션ID
        int procCnt = 0; //처리건수

        // 배송상태 체크 및 처리대상 선별.
        DeliveryOrderGoodDTO checkDto = new DeliveryOrderGoodDTO();
        for (DeliveryOrderGoodResult obj : gridList) {
            //배송상태 체크 :: 출고지시만 송장채번 가능.
            checkDto.setDlivyDrctGodNo(obj.getLgsDdg().getDlivyDrctGodNo());
            LgsDlivyDrctGod info = deliveryStatusService.selectLgsDdgInfo(checkDto);

            if ("DLIVY_DRCT".equals(info.getDlvStatCd())) {
                //일반상품 추가
                if(null == obj.getLgsDdg().getPckageGodTurn() || 0 == obj.getLgsDdg().getPckageGodTurn()) {
                    targetList.add(obj);
                //패키지 ( 현재(2015.11.12)는 패키지 상품 도입하지 않고 있슴
                } else {
                    packList.add(obj);  // 패키지 상품 순번 0 이외 상품들 추가
                }
            }
        }

        // 미선택 구성품 리스트에 추가
        if(packList.size() > 0) {
            List<DeliverySearchDTO> plist = deliveryStatusService.filterDuplPackageItem(packList);
            for (DeliverySearchDTO pobj : plist) {
                List<DeliveryOrderGoodResult> subList = deliveryStatusService.selectDeliveryInfoPckage(pobj);
                for(DeliveryOrderGoodResult obj : subList) {
                    targetList.add(obj);
                }
            }
        }


        // 물류배송 분리대상(박스분리 대상) 선별.
        List<DeliveryOrderGoodDTO> finalList = deliveryStatusService.filterDivWaybillInfo(targetList);

        DeliveryInvoiceDTO invoiceDto = new DeliveryInvoiceDTO();

        for(DeliveryOrderGoodDTO fdto : finalList) {

            //물류배송단위 출고대상건 카운트 조회
            int dlvCnt = deliveryStatusService.selectAbleDlivyeryCnt(fdto);

            //물류배송단위 출고대상건과 박스 분리대상건 수량 비교
            if(dlvCnt < fdto.getNewTargetCnt()) {
                DeliveryStatusException statusException = new DeliveryStatusException(null);
                statusException.setDirectMessage("배송상태 변경건이 존재합니다. \n확인 후 처리하세요.");
                throw statusException;
            } else if(dlvCnt == fdto.getNewTargetCnt()) {
                // 용차송장번호 채번
                String wayBilNo = deliveryStatusService.selectFrgnWayBilSeq();

                // 송장정보 등록
                invoiceDto.setNewDmstcWaybilNo(wayBilNo);
                invoiceDto.setNewDlvComCd("PANTOS");
                invoiceDto.setUdterId(userId);
                invoiceDto.setOrdNo(fdto.getOrdNo());
                invoiceDto.setDlvTurn(fdto.getDlvTurn()+"");
                invoiceDto.setDlvPcupspTurn(fdto.getDlvPcupspTurn()+"");
                deliveryCommandService.updateInvoice(systemPK, invoiceDto);

                procCnt += fdto.getNewTargetCnt();

            } else {
                //dlvCnt > fdto.getNewTargetCnt()
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
                List<DeliveryOrderGoodResult> divTargetList = deliveryStatusService.selectDlvDivideTargetList(search);

                int idx = 0;
                int dlvTurn = 0;	//배송순번
                for (DeliveryOrderGoodResult divObj : divTargetList) {
                    if(idx == 0) {
                        // 운송장번호 채번
                        String wayBilNo = deliveryStatusService.selectFrgnWayBilSeq();

                        // 물류배송 분리정보 생성(운송장번호 등록)
                        dlvTurn = deliveryStatusService.makeDivLgsDlvInfo(divObj, wayBilNo);
                    }

                    // 출고지시상품에 물류배송 분리정보 적용.
                    deliveryStatusService.updateDivLgsDdgInfo(divObj, dlvTurn);
                    idx++;
                    procCnt++;
                }
            }
        }
        return procCnt;
    }

	/**
	 * 운송장번호 개별생성
	 * 송장생성시 주문번호, 배송수거지순번, 배송순번, 배송상태, 배송매장ID, 차수, DAS 기준으로 데이터 분리.
	 */
	public int updateSelectedWaybillNo(SystemPK systemPK, List<DeliveryOrderGoodResult> gridList, String callId) throws Exception {
		List<DeliveryOrderGoodResult> targetList = new ArrayList<DeliveryOrderGoodResult>();
		List<DeliveryOrderGoodResult> packList = new ArrayList<DeliveryOrderGoodResult>();
		String userId = BOSecurityUtil.getLoginId();	//세션ID
		int procCnt = 0; //처리건수
		
		// 배송상태 체크 및 처리대상 선별.
		DeliveryOrderGoodDTO checkDto = new DeliveryOrderGoodDTO();
		for (DeliveryOrderGoodResult obj : gridList) {
            obj.getOrd().getAffComId();
			//배송상태 체크 :: 출고지시만 송장채번 가능.
			checkDto.setDlivyDrctGodNo(obj.getLgsDdg().getDlivyDrctGodNo());
			LgsDlivyDrctGod info = deliveryStatusService.selectLgsDdgInfo(checkDto);
			
			if ("DLIVY_DRCT_CNCL".equals(info.getDlvStatCd())) {
//				DeliveryStatusException statusException = new DeliveryStatusException(null);
//				statusException.setDirectMessage("주문취소건이 존재합니다.\n확인 후 처리하세요.");
//				throw statusException;
			} else if (!"DLIVY_DRCT".equals(info.getDlvStatCd()) 
					&& !("DLV_WAIT".equals(info.getDlvStatCd()) && !"B031".equals(info.getDlvShopId()))) {
//				DeliveryStatusException statusException = new DeliveryStatusException(null);
//				statusException.setDirectMessage("'출고지시' 상태만 처리가능합니다.\n배송상태를 확인 후 처리하세요.");
//				throw statusException;
				 
			} else {
				//일반
				if(null == obj.getLgsDdg().getPckageGodTurn() || 0 == obj.getLgsDdg().getPckageGodTurn()) {
					//일반상품 추가
					targetList.add(obj);
					
				//패키지	
				} else {
					//미선택 구성품을 위한 별도 처리.
					packList.add(obj);
				}
			}
        }
		
		//패키지 누락건 대상 적용
		if(packList.size() > 0) {
			List<DeliverySearchDTO> plist = deliveryStatusService.filterDuplPackageItem(packList);
			
			for (DeliverySearchDTO pobj : plist) {
				List<DeliveryOrderGoodResult> subList = deliveryStatusService.selectDeliveryInfoPckage(pobj);
				
				for(DeliveryOrderGoodResult obj : subList) {
					//패키지 구성품 추가
					targetList.add(obj);
				}
			}
		}
		
		log.info("targetList :: "+targetList);
		//물류배송 분리대상(박스분리 대상) 선별.
		List<DeliveryOrderGoodDTO> finalList = deliveryStatusService.filterDivWaybillInfo(targetList);
		log.info("finalList :: "+finalList);
		
		DeliveryInvoiceDTO invoiceDto = new DeliveryInvoiceDTO();
		for(DeliveryOrderGoodDTO fdto : finalList) {

			//물류배송단위 출고대상건 카운트 조회
			int dlvCnt = deliveryStatusService.selectAbleDlivyeryCnt(fdto);
			
			//물류배송단위 출고대상건과 박스 분리대상건 수량 비교
			if(dlvCnt < fdto.getNewTargetCnt()) {
				DeliveryStatusException statusException = new DeliveryStatusException(null);
				statusException.setDirectMessage("배송상태 변경건이 존재합니다. \n확인 후 처리하세요.");
				throw statusException;
				
			} else if(dlvCnt == fdto.getNewTargetCnt()) {
				// 운송장번호 채번
//                String wayBilNo;
//                if ( !StringUtils.defaultString(fdto.getLangCd(),"KOR").equalsIgnoreCase("KOR")
//						&& StringUtils.defaultString(fdto.getDlvShopId()).equalsIgnoreCase(DeliveryEnum.DLV_ONLINE_SHOP_DISCOVERY.toString())) {
//                    wayBilNo = deliveryStatusService.selectFrgnWayBilSeq();
//                } else {
//                    wayBilNo = deliveryStatusService.selectWayBilSeq();
//                }

				// 송장정보 등록
//				invoiceDto.setNewDmstcWaybilNo(wayBilNo);
				// 픽업매장
				if(callId.equals("SHOP_PRPARE_COMPT")){
					invoiceDto.setNewDlvComCd( "" );
                } else{
					DeliveryEnum.APPN_DLV_COM_CD.toString();
				}
				invoiceDto.setUdterId(userId);
				invoiceDto.setOrdNo(fdto.getOrdNo());
				invoiceDto.setDlvTurn(fdto.getDlvTurn()+"");
				invoiceDto.setDlvPcupspTurn(fdto.getDlvPcupspTurn()+"");
				deliveryCommandService.updateInvoice(systemPK, invoiceDto);
				
				procCnt += fdto.getNewTargetCnt();
			} else {
				//dlvCnt > fdto.getNewTargetCnt()
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
				List<DeliveryOrderGoodResult> divTargetList = deliveryStatusService.selectDlvDivideTargetList(search);
				
				int idx = 0;
				int dlvTurn = 0;	//배송순번
				for (DeliveryOrderGoodResult divObj : divTargetList) {
	                if(idx == 0) {
	                	// 운송장번호 채번
//	                	String wayBilNo;

	                	//픽업 매장 송장 채번 안하기로함
//                        if ( !StringUtils.defaultString(divObj.getOrd().getLangCd(),"KOR").equalsIgnoreCase("KOR")
//								&& StringUtils.defaultString(divObj.getLgsDdg().getDlvShopId()).equalsIgnoreCase(DeliveryEnum.DLV_ONLINE_SHOP_DISCOVERY.toString())) {
//                            wayBilNo = deliveryStatusService.selectFrgnWayBilSeq();
//                        } else {
//                            wayBilNo = deliveryStatusService.selectWayBilSeq();
//                        }

	                	// 물류배송 분리정보 생성(운송장번호 등록)
                        divObj.setMallId(StringUtils.defaultString(fdto.getMallId()));
						divObj.setLang(StringUtils.defaultString(fdto.getLangCd()));
						divObj.getOrd().setLangCd(StringUtils.defaultString(fdto.getLangCd()));
	                	dlvTurn = deliveryStatusService.makeDivLgsDlvInfo(divObj, null);
	                }
	                
	                // 출고지시상품에 물류배송 분리정보 적용.
	                deliveryStatusService.updateDivLgsDdgInfo(divObj, dlvTurn);
	                idx++;
	                procCnt++;
                }
			}
        }
		
		return procCnt;
	}
	
	/**
	 * 운송장번호 일괄생성
	 * 검색조건에 해당하는 대상의 운송장을 생성한다.
	 * 송장생성시 주문번호, 배송수거지순번, 배송순번, 배송매장ID 기준으로 데이터 분리 필요.
	 */
	public int updateSearchWaybillNo(SystemPK systemPK, DeliverySearchDTO deliverySearchDTO) throws Exception {
		
		int procCnt = 0;	//처리건수
		
		//1. 처리대상 조회
		List<DeliveryOrderGoodResult> list = deliveryStatusService.selectSearchWaybillNoList(deliverySearchDTO);
		
		//2. 운송장 생성
		procCnt = this.updateSelectedWaybillNo(systemPK, list, "");
		
		return procCnt;
	}
	
	/** 배송상태 변경. */
	@Override
	public String updateDeliveryStatus(SystemPK systemPK,  List<DeliveryOrderGoodResult> listDTO, String newDlvStatCd) throws Exception {
		List<DeliveryOrderGoodResult> packList = new ArrayList<DeliveryOrderGoodResult>();
		String userId = BOSecurityUtil.getLoginId();	//세션ID
		String message = "";
		boolean pckgYn = false;
		int procCnt = 0;	//처리건수
				
		//픽업 매장준비완료건 주문단위로 전체 조회.
		if("SHOP_PRPARE_COMPT".equals(newDlvStatCd)) {
			List<DeliveryOrderGoodResult> retList = new ArrayList<DeliveryOrderGoodResult>();
			List<DeliveryOrderGoodResult> tempList = new ArrayList<DeliveryOrderGoodResult>();
			
			//물류배송 분리대상 중복제거
			for (DeliveryOrderGoodResult dto1: listDTO) {
				DeliveryOrderGoodResult dto = new DeliveryOrderGoodResult();
				dto.setOrdNo(dto1.getOrd().getOrdNo());
				tempList.add(dto);
			}
			
			HashSet<DeliveryOrderGoodResult> hs = new HashSet<DeliveryOrderGoodResult>(tempList);
			Iterator<DeliveryOrderGoodResult> it = hs.iterator();
			while(it.hasNext()) {
				retList.add(it.next());
			}
			
			Map<String, List<String>> map = new HashMap<String, List<String>> ();
			List<String> orderList = new ArrayList<String>();
			for(DeliveryOrderGoodResult dto : retList)
			{
				orderList.add(dto.getOrdNo());
			}
			map.put("orderList", orderList);
			listDTO = deliverySelectRepository.getStorePickupOrderDetail(map);
		}
		
		List<OrderOnlineShopRtListSDO> allDetailList = new ArrayList<>();
		if("DLIVY_COMPT".equals(newDlvStatCd)) {
			List<DeliveryOrderGoodResult> retList = new ArrayList<DeliveryOrderGoodResult>();
			List<DeliveryOrderGoodResult> tempList = new ArrayList<DeliveryOrderGoodResult>();
			
			//물류배송 분리대상 중복제거
			for (DeliveryOrderGoodResult dto1: listDTO) {
				DeliveryOrderGoodResult dto = new DeliveryOrderGoodResult();
				dto.setOrdNo(dto1.getOrd().getOrdNo());
				dto.setDlvShopId(dto1.getLgsDdg().getDlvShopId());
				tempList.add(dto);
			}
			
			HashSet<DeliveryOrderGoodResult> hs = new HashSet<DeliveryOrderGoodResult>(tempList);
			Iterator<DeliveryOrderGoodResult> it = hs.iterator();
			while(it.hasNext()) {
				retList.add(it.next());
			}
			
			Map<String, List<String>> map = new HashMap<String, List<String>> ();
			List<String> orderList = new ArrayList<String>();
			for(DeliveryOrderGoodResult dto : retList)
			{
				orderList.add(dto.getOrdNo()+"@@"+dto.getDlvShopId());
			}
			map.put("orderList", orderList);
			List<DeliveryOrderGoodResult> checkListDTO = deliverySelectRepository.getStoreOrderDetail(map);
			//TODO 물류 하나라도 결품시에 출고 방지.
			for(DeliveryOrderGoodResult dto : checkListDTO)
			{
				if("SHTG_RCEPT".equals(dto.getLgsDdg().getDlvStatCd()))
				{
					log.info("DLIVY_COMPT FAIL : {} ",dto);
					DeliveryStatusException statusException = new DeliveryStatusException(null);
					statusException.setDirectMessage("선택된 주문에 결품인 상태가 존재합니다\n주문 취소 후 처리바랍니다.");
					throw statusException;
				}
			}

			for (DeliveryOrderGoodResult dto : listDTO) {
				// INF_ORD_GOD_ERP_DSTB 수량단위 조회후 온라인->매장 RT 받음 처리
				OrderOnlineShopRtListSDO search = new OrderOnlineShopRtListSDO();
				search.setOrdNo(dto.getOrd().getOrdNo());
				search.setOrdDtlNo(dto.getLgsDdg().getDlivyDrctGodNo());
				List<OrderOnlineShopRtListSDO> detailList = deliveryCommandRepository.selectDlivyComptRtDetail(search);
				for(OrderOnlineShopRtListSDO detail : detailList)
				{
					allDetailList.add(detail);
				}
			}
		}
		
		for (DeliveryOrderGoodResult dto : listDTO) {
			//패키지 상품
			if(null != dto.getLgsDdg().getPckageGodTurn() && 0 != dto.getLgsDdg().getPckageGodTurn()) {
				packList.add(dto);
				
			//일반 상품
			} else {
				
				if("DLIVY_COMPT".equals(newDlvStatCd)) {
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
                		List<OrderOnlineShopRtResultSDO> rtList = orderOnlineShopRtSDO.getResponseData();
                    	for(OrderOnlineShopRtResultSDO rtDto : rtList)
                    	{
                    		log.info("BoDlivyComptRt ResultCd : {} ResultMsg : {} OrdNo : {} OrdDtlNo : {}",rtDto.getResultCd(),rtDto.getResultMsg(),rtDto.getOrdNo(),rtDto.getOrdDtlNo() );
                    	}
    					//배송상태 변경
    					DeliveryOrderGoodDTO retObj = deliveryStatusService.updateDeliveryStatus(dto, newDlvStatCd);
    					procCnt += retObj.getCount();	//처리건수
    					
                    	Ord ord = new Ord();
                    	ord.setOrdNo(retObj.getOrdNo());
                    	ord.setUdterId(userId);
                    	ord.setOrdStatCd(DeliveryEnum.OrdStatCd.DLV_PROGRS.toString());    //배송중
                    	orderBoCommandRepository.updateOrdStatCd(ord);
                    	
                    	//TODO 물류 매장 알림
        			}else
        			{
        				log.info("storeDeliConfirm FAIL : {} {}",dto.getDlivyDrctGodNo() ,orderOnlineShopRtSDO);
        				DeliveryStatusException statusException = new DeliveryStatusException(null);
        				statusException.setDirectMessage("RT 확정 처리에 실패하였습니다.");
        				throw statusException;
        			}
				}else
				{
					//배송상태 변경
					DeliveryOrderGoodDTO retObj = deliveryStatusService.updateDeliveryStatus(dto, newDlvStatCd);
					procCnt += retObj.getCount();	//처리건수
					
					//'배송완료'시 주문상태 수정
					if("DLV_COMPT".equals(retObj.getDlvStatCd())) {
						Ord ord = new Ord();
						ord.setOrdNo(retObj.getOrdNo());
						ord.setUdterId(userId);
						ord.setOrdStatCd("DLV_COMPT");	//배송완료
						orderBoService.updateOrdStatAboutCompt(ord);

					}
				}
			}
		}

		// 패키지는 물류센터에서만 판매
		if(packList.size() > 0) {
			pckgYn = true;
			List<DeliveryOrderGoodDTO> retList = deliveryStatusService.updateDeliveryStatus4Package(packList, newDlvStatCd);
		
			for (DeliveryOrderGoodDTO retObj : retList) {
				//'배송완료'시 주문상태 수정
				if("DLV_COMPT".equals(retObj.getDlvStatCd())) {
					Ord ord = new Ord();
					ord.setOrdNo(retObj.getOrdNo());
					ord.setUdterId(userId);
					ord.setOrdStatCd("DLV_COMPT");	//배송완료
					orderBoService.updateOrdStatAboutCompt(ord);
				}
				procCnt++;
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
	
	/**
	 * 결품 철회
	 */
	@Override
	public int updateShortageCancel(SystemPK systemPk, List<DeliveryOrderGoodResult> gridList) throws Exception {
		return deliveryStatusService.updateShortageCancel(systemPk, gridList);
	}
	
	/** 택배사,송장번호,배송메모 저장. */
	@Override
	public boolean updateDeliveryInfo(SystemPK systemPK, List<DeliveryOrderGoodResult> listDTO , DeliveryInvoiceDTO deliveryInvoiceDTO)  throws Exception {
//		deliveryCommandService.updateInoviceCompare(systemPK, listDTO, deliveryInvoiceDTO);//운송장택배사정보 수정
		deliveryCommandService.updateDeliveryShopMemo(systemPK, listDTO, deliveryInvoiceDTO);//배송메모수정

		return true;
	}

	/**  사은품 송장,배송메모 저장. */
	@Override
	public boolean updateDeliveryGift(SystemPK systemPK, List<DeliveryOrderGoodResult> listDTO , DeliveryInvoiceDTO deliveryInvoiceDTO)  throws Exception {
		deliveryCommandService.updateInoviceCompare(systemPK, listDTO, deliveryInvoiceDTO);//운송장택배사정보 수정
		deliveryCommandService.updateDeliveryShopMemo(systemPK, listDTO, deliveryInvoiceDTO);//배송메모수정

		return true;
	}
	
	/**
	 * ERP 인터페이스 시리얼정보 수정
	 */
	@Override
	public void updateErpGodSnInfo(SystemPK systemPk, List<DeliveryOrderGoodResult> gridList) throws Exception {
		deliveryStatusService.updateErpGodSnInfo(systemPk, gridList);
	}
	
	/**
	 * [매장전용] 배정 거부 반영 
	 */
	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRES_NEW)
	public void updateDlvStatData(SystemPK systemPk, DeliveryOrderGoodDTO dlivyDrctInfo) throws Exception {
		// 송장정보 초기화 :: 기존에 채번한 송장번호가 존재한 경우 초기화.
		if (null != dlivyDrctInfo.getDmstcWaybilNo() && !"".equals(dlivyDrctInfo.getDmstcWaybilNo())) {
			deliveryAssignService.initWayBillNo(dlivyDrctInfo);
		}
		
		/*
		 * 2017.01.25  
		 * [SR 34188 + SR 35462]  - by shinkun  
		 * '상품불량' 또는 '단체출고' 사유로 배정거절시 매장별 단품 재고정보 변경 및 변경 이력 등록
		 */
		if( "GOD_BADN".equals( dlivyDrctInfo.getRejectResnCd()) || "ORGZT_DLIVY".equals( dlivyDrctInfo.getRejectResnCd()) ) {
			GodShopItmInv godShopItmInv = new GodShopItmInv();
			godShopItmInv.setItmNo(dlivyDrctInfo.getItmNo());
			godShopItmInv.setShopId(dlivyDrctInfo.getDlvShopId());
			// TODO	상품재작업필요 : GodShopItmInv shopItemInfo = goodsOptStockSelectService.selectGodShopItmInv(godShopItmInv);
			GodShopItmInv shopItemInfo = null;
			
			// 웹판매여부가 사용중인 경우 미사용 처리 후 이력 등록
			if( String.valueOf(GoodsEnum.YES).equals(shopItemInfo.getWebSalePsbYn()) ) {
				GodShopItmInvExtend godShopItmInvExtend = new GodShopItmInvExtend();
				godShopItmInvExtend.setItmNo(dlivyDrctInfo.getItmNo());
				godShopItmInvExtend.setShopId(dlivyDrctInfo.getDlvShopId());
				godShopItmInvExtend.setGodNo(dlivyDrctInfo.getGodNo());
				//godShopItmInvExtend.setSafeInvQtyInt(0);
				godShopItmInvExtend.setWebSalePsbYn(String.valueOf(GoodsEnum.NO));
				// 안전재고 정율적용여부 N : 예외상품으로 등록한 안전재고 정량 업데이트 
				godShopItmInvExtend.setSafeInvRtUseYn(String.valueOf(GoodsEnum.YES));
				godShopItmInvExtend.setSafeInvRtUseYnEx(String.valueOf(GoodsEnum.YES));
				godShopItmInvExtend.setUdterId(BOSecurityUtil.getLoginId());
				godShopItmInvExtend.setRegtrId(BOSecurityUtil.getLoginId());
				godShopItmInvExtend.setChannel("DLV");
				
				// 매장별 단품의 안전재고 정보를 반영 
				//goodsOptStockCommandService.updateGoodsShopItmInvSafeInv(godShopItmInvExtend);
				
				// 상품재고배분변경이력 등록처리
				GoodsDTO goods = new GoodsDTO();
				/*
				goods.getStock().setUdterId(BOSecurityUtil.getLoginId());
				goods.getStock().setRegtrId(BOSecurityUtil.getLoginId());
				goods.getStock().setShopId(dlivyDrctInfo.getDlvShopId());
				goods.getGodItm().setItmNo(dlivyDrctInfo.getItmNo());
				goods.getStock().setWebSalePsbYn(String.valueOf(GoodsEnum.NO));
				goods.getStock().setWebSalePsbYnBf(shopItemInfo.getWebSalePsbYn());
				goodsHistoryService.insertInfGodInfoModiemHist(systemPk, goods);
				*/
				// 매장별 단품 재고정보 변경이력 등록
				if ( "GOD_BADN".equals( dlivyDrctInfo.getRejectResnCd()) ) {
					godShopItmInvExtend.setModResnDetailCont("상품불량");
				} else {
					godShopItmInvExtend.setModResnDetailCont("단체판매");
				}
				// TODO	상품재작업필요 : goodsOptStockCommandService.insertGodShopItmInvHist(godShopItmInvExtend, "DLV");
				
				// 단품 ERP재고 동기화
				GodItm godItm = new GodItm();
				godItm.setItmNo(dlivyDrctInfo.getItmNo());
			}
		}
				
		//배정 거부 처리
		deliveryAssignService.updateDlvStatData(dlivyDrctInfo);
	}
	
	/**
	 * 거부 전 주문 현재 상태 체크
	 */
	@Override
	public int getChkStatus(DeliveryOrderGoodDTO deliveryOrderGoodDTO) throws Exception{
		return deliveryStatusService.getChkStatus(deliveryOrderGoodDTO);
	}
	
	/** 배송지연 상품관리(재배정) / 결품조회(재배정) */
	@Override
	public String reAssignDeliveryShop(SystemPK systemPk, List<DeliveryOrderGoodResult> gridList, String targetShop, String caller) throws Exception {
		List<DeliveryOrderGoodResult> packList = new ArrayList<DeliveryOrderGoodResult>();
		String rtrnMsg = "";
		boolean existPack = false;
		int sCnt = 0;
		int fCnt = 0;
		
		for (DeliveryOrderGoodResult obj : gridList) {
			//일반
			if(null == obj.getLgsDdg().getPckageGodTurn() || 0 == obj.getLgsDdg().getPckageGodTurn()) {
				try{
					reAssignDeliveryShop(systemPk, obj, null, targetShop, caller);
					sCnt++;
				} catch(Exception e) {
					fCnt++;
					
					try{
						//물류센터 출고지시건에 대한 재배정 보완처리
						if(DeliveryEnum.DLV_ONLINE_SHOP_DISCOVERY.toString().equals(obj.getLgsDdg().getDlvShopId())
								|| DeliveryEnum.DLV_ONLINE_SHOP_MLB.toString().equals(obj.getLgsDdg().getDlvShopId())
								|| DeliveryEnum.DLV_ONLINE_SHOP_MLB_KIDZ.toString().equals(obj.getLgsDdg().getDlvShopId())
								|| DeliveryEnum.DLV_ONLINE_SHOP_SA.toString().equals(obj.getLgsDdg().getDlvShopId())) {
							//ERP출고지시 취소건에 대한 검증 및 보정처리
							deliveryInfErpService.procReAssignException(obj);
						}
					} catch(Exception e1) {
						log.info("출고지시확인 : "+obj.getOrd().getOrdNo()+" / "+obj.getOrdGod().getSkuNo()+" / "+obj.getLgsDdg().getDlivyDrctGodNo());
					}
				}
				
			//패키지
			} else {
				packList.add(obj);
			}
		}
		
		//패키지 출고처지정 처리
		if(packList.size() > 0) {
			existPack = true;
			
			List<DeliverySearchDTO> plist = deliveryAssignService.filterDuplPackageItem(packList);
			for (DeliverySearchDTO dto : plist) {
				try{
					reAssignDeliveryShop(systemPk, null, dto, targetShop, caller);
					sCnt++;
				} catch(Exception e) {
					fCnt++;
					
					//1. 세트 구성품 조회 :: 미선택 구성품을 위한 별도 처리.
					List<DeliveryOrderGoodResult> pList = deliveryAssignService.selectReAssignDeliveryInfoPckage(dto);
					
					try{
						for (DeliveryOrderGoodResult obj : pList) {
							//물류센터 출고지시건에 대한 재배정 보완처리
							if(DeliveryEnum.DLV_ONLINE_SHOP_DISCOVERY.toString().equals(obj.getLgsDdg().getDlvShopId())
									|| DeliveryEnum.DLV_ONLINE_SHOP_MLB.toString().equals(obj.getLgsDdg().getDlvShopId())
									|| DeliveryEnum.DLV_ONLINE_SHOP_MLB_KIDZ.toString().equals(obj.getLgsDdg().getDlvShopId())
									|| DeliveryEnum.DLV_ONLINE_SHOP_SA.toString().equals(obj.getLgsDdg().getDlvShopId()) ) {
								//ERP출고지시 취소건에 대한 검증 및 보정처리
								deliveryInfErpService.procReAssignException(obj);
							}
						}
					} catch(Exception e1) {
						log.info("출고지시확인 : "+pList.get(0).getOrd().getOrdNo());
					}
				}
			}
		}
		
		if(existPack) {
			if(fCnt < 1) {
				rtrnMsg = "세트 포함 "+sCnt+"건이 처리되었습니다.";
			} else {
				rtrnMsg = "세트 포함  처리건 중 성공 :: "+sCnt+"건   실패 ::"+fCnt+"건\n실패건에 대해 배송상태 확인 및 품번의 재고수량 확인 바랍니다.";
			}
		} else {
			if(fCnt < 1) {
				rtrnMsg = sCnt+"건이 처리되었습니다.";
			} else {
				rtrnMsg = "처리건 중 성공 :: "+sCnt+"건   실패 ::"+fCnt+"건\n실패 건에 대해 배송상태 확인 및 품번의 재고수량 확인 바랍니다.";
			}
		}
		
		return rtrnMsg;
	}
	
	/** 결품접수(재배정) */
	@Override
	public String shortageReassign(SystemPK systemPk, List<DeliveryOrderGoodResult> gridList) throws Exception {
		List<DeliveryOrderGoodResult> packList = new ArrayList<DeliveryOrderGoodResult>();
		//List<LgsAutoAsgnExtend> reAsgnList = new ArrayList<LgsAutoAsgnExtend>();	//배송매장 강제배정 처리건
		HashMap<String, String> comptOrd = new HashMap<String, String>();	//처리완료건
		//LgsAutoAsgnExtend reAsgnInfo; //배송매장 처리건
		String rtrnMsg = "";
		boolean existPack = false;
		int sCnt = 0;
		int fCnt = 0;
		
		for (DeliveryOrderGoodResult obj : gridList) {
			//일반
			if(null == obj.getLgsDdg().getPckageGodTurn() || 0 == obj.getLgsDdg().getPckageGodTurn()) {
				try{
					shortageReassign(systemPk, obj, null);
					sCnt++;
				} catch(Exception e) {
					//배송매장 배정을 위한 재처리
					if ( e instanceof DeliveryAutoAssignException ) {
						//LgsAutoAsgnExtend targetLgsAutoAsgn = ( (DeliveryAutoAssignException)e ).getLgsAutoAsgnExtend();
						//reAsgnList.add( targetLgsAutoAsgn );
					}
				}
				
				//패키지
			} else {
				packList.add(obj);
			}
		}
		
		//패키지 출고처지정 처리
		if(packList.size() > 0) {
			existPack = true;
			
			List<DeliverySearchDTO> plist = deliveryAssignService.filterDuplPackageItem(packList);
			for (DeliverySearchDTO dto : plist) {
				try{
					shortageReassign(systemPk, null, dto);
					sCnt++;
				} catch(Exception e) {
					//배송매장 배정을 위한 재처리
					if ( e instanceof DeliveryAutoAssignException ) {
						/*
						List<LgsAutoAsgnExtend> targetLgsAutoAsgnList = ( (DeliveryAutoAssignException)e ).getLgsAutoAsgnExtendList();
						for (LgsAutoAsgnExtend lgsAutoAsgnExtend : targetLgsAutoAsgnList) {
							reAsgnList.add( lgsAutoAsgnExtend );
						}
						*/
					}
				}
			}
		}
		
		/*
		for (LgsAutoAsgnExtend obj : reAsgnList) {
			//기 처리건 체크
			if(!comptOrd.containsKey(obj.getDlivyDrctGodNo())) {
				List<String> dlivyDrctDocNos = new ArrayList<String>();
				
				//기준 처리대상 등록
				comptOrd.put(obj.getDlivyDrctGodNo(), obj.getOrdNo());
				dlivyDrctDocNos.add(obj.getDlivyDrctGodNo());
				
				for (LgsAutoAsgnExtend obj2 : reAsgnList) {
					if(obj2.getOrdNo().equals(obj.getOrdNo())) {
						if(!obj2.getDlivyDrctGodNo().equals(obj.getDlivyDrctGodNo())) {
							
							//(동일주문건에 대한) 처리대상 등록
							comptOrd.put(obj2.getDlivyDrctGodNo(), obj2.getOrdNo());
							dlivyDrctDocNos.add(obj2.getDlivyDrctGodNo());
						}
					}
				}
				
				reAsgnInfo = new LgsAutoAsgnExtend();
				reAsgnInfo.setOrdNo(obj.getOrdNo());
				reAsgnInfo.setListDlivyDrctGodNo(dlivyDrctDocNos);
				
				try{
					//배송매장 배정처리
					reAsgnInfo.setAsgnSectCd( "ENFRC_ASGN" );
					this.setAssignDlvShop( reAsgnInfo, false );
					sCnt += dlivyDrctDocNos.size();
					
				} catch(Exception e) {
					fCnt += dlivyDrctDocNos.size();
					
					
					try{
						//물류센터 출고지시건에 대한 재배정 실패시 보완처리
						for(String dlivyDrctGodNo : reAsgnInfo.getListDlivyDrctGodNo()) {
							for (DeliveryOrderGoodResult obj3 : gridList) {
								if(obj3.getLgsDdg().getDlivyDrctGodNo().equals(dlivyDrctGodNo)) {
									if(DeliveryEnum.DLV_ONLINE_SHOP_DISCOVERY.toString().equals(obj3.getLgsDdg().getDlvShopId())) {
										//ERP출고지시 취소건에 대한 검증 및 보정처리
										deliveryInfErpService.procReAssignException(obj3);
									}
								}
							}
						}
						
					} catch(Exception e1) {
						log.info("출고지시확인 : "+obj3.getOrd().getOrdNo()+" / "+obj3.getOrdGod().getSkuNo()+" / "+obj3.getLgsDdg().getDlivyDrctGodNo());
					}
					
					
				}
			}
		}
		*/
		if(existPack) {
			if(fCnt < 1) {
				rtrnMsg = "세트 포함 "+sCnt+"건이 처리되었습니다.";
			} else {
				rtrnMsg = "세트 포함  처리건 중 성공 :: "+sCnt+"건   실패 ::"+fCnt+"건\n실패건에 대해 배송상태 확인 및 ERP 출고상태 확인 바랍니다.";
			}
		} else {
			if(fCnt < 1) {
				rtrnMsg = sCnt+"건이 처리되었습니다.";
			} else {
				rtrnMsg = "처리건 중 성공 :: "+sCnt+"건   실패 ::"+fCnt+"건\n실패 건에 대해 배송상태 확인 및 ERP 출고상태 확인 바랍니다.";
			}
		}
		
		return rtrnMsg;
	}
	
	/** 결품처리 */
	@Override
	public String dstrbtCenterShortage(SystemPK systemPk, List<DeliveryOrderGoodResult> gridList) throws Exception {
		String rtrnMsg = "";
		int sCnt = 0;
		int fCnt = 0;

		for (DeliveryOrderGoodResult obj : gridList) {
			try{
				dstrbtCenterShortage(systemPk, obj, null);
				sCnt++;
			} catch(Exception e) {
				fCnt++;
			}
		}
		
		if(fCnt < 1) {
			rtrnMsg = sCnt+"건이 처리되었습니다.";
		} else {
			rtrnMsg = "처리건 중 성공 :: "+sCnt+"건   실패 ::"+fCnt+"건\n실패 건에 대해 배송상태 확인 및 ERP 출고상태 확인 바랍니다.";
		}
		return rtrnMsg;
	}
	
	/** 출고지시 */
	@Override
	public String sendDlivyDrct(SystemPK systemPk, List<DeliveryOrderGoodResult> gridList) throws Exception {
		String rtrnMsg = deliveryStatusService.sendDlivyDrct(systemPk, gridList);
		return rtrnMsg;
	}
	
	/** 출고지시 취소 */
	@Override
	public String sendDlivyDrctCancel(SystemPK systemPk, List<DeliveryOrderGoodResult> gridList) throws Exception {
		String rtrnMsg = deliveryStatusService.sendDlivyDrctCancel(systemPk, gridList);
		return rtrnMsg;
	}
	
	/** 상품출고지 배정(재배정) */
	@Override
	public String assignDleliveryShop(SystemPK systemPk, List<DeliveryOrderGoodResult> gridList, String targetShop) throws Exception {
		List<DeliveryOrderGoodResult> packList = new ArrayList<DeliveryOrderGoodResult>();
		String rtrnMsg = "";
		boolean existPack = false;
		int sCnt = 0;
		int fCnt = 0;
		
		//희망배정 유효시간 체
		HashMap<String, String> sysTime = deliveryAssignService.selectSystemCurrentTimeInfo();
		if("N".equals(sysTime.get("ASGN_TIME_YN"))) {
			return "현재는 희망배정 가능시간이 아닙니다.\n희망배정이 가능한 시간을 확인 하십시오.\n희망배정시간 : 20:00 ~ 익일 10:00";
		}
		
		for (DeliveryOrderGoodResult obj : gridList) {
			//일반
			if(null == obj.getLgsDdg().getPckageGodTurn() || 0 == obj.getLgsDdg().getPckageGodTurn()) {
				try{
					assignDleliveryShop(systemPk, obj, null, targetShop);
					sCnt++;
				} catch(Exception e) {
					fCnt++;
				}
				
			//패키지
			} else {
				packList.add(obj);
			}
		}
		
		//패키지 출고처지정 처리
		if(packList.size() > 0) {
			existPack = true;
			
			List<DeliverySearchDTO> plist = deliveryAssignService.filterDuplPackageItem(packList);
			for (DeliverySearchDTO dto : plist) {
				try{
					assignDleliveryShop(systemPk, null, dto, targetShop);
					sCnt++;
				} catch(Exception e) {
					fCnt++;
				}
			}
		}
		
		if(existPack) {
			if(fCnt < 1) {
				rtrnMsg = "세트 포함 "+sCnt+"건이 처리되었습니다.";
			} else {
				rtrnMsg = "세트 포함  처리건 중 성공 :: "+sCnt+"건   실패 ::"+fCnt+"건\n실패건에 대해 배송상태 확인 및 품번의 재고수량 확인 바랍니다.";
			}
		} else {
			if(fCnt < 1) {
				rtrnMsg = sCnt+"건이 처리되었습니다.";
			} else {
				rtrnMsg = "처리건 중 성공 :: "+sCnt+"건   실패 ::"+fCnt+"건\n실패 건에 대해 배송상태 확인 및 품번의 재고수량 확인 바랍니다.";
			}
		}
		
		return rtrnMsg;
	}
	
	/** 
	 * [매장전용] 배정 거부 반영 
	 */
	@Override
	public String setAsgnReject(SystemPK systemPk, List<DeliveryOrderGoodResult> gridList) throws Exception {
		String rtrnMsg = "";
		int sCnt = 0;
		int fCnt = 0;
		int chk_Status = 0;
		String userId = BOSecurityUtil.getLoginId();	//세션ID

		for (DeliveryOrderGoodResult obj : gridList) {
			try{					
				DeliveryOrderGoodDTO dlivyDrctInfo = new DeliveryOrderGoodDTO();
				
				dlivyDrctInfo.setDlivyDrctGodNo(obj.getLgsDdg().getDlivyDrctGodNo());
				dlivyDrctInfo.setOrdNo(obj.getOrd().getOrdNo());	
				if(obj.getLgsDdg().getPckageGodTurn() != null){
					dlivyDrctInfo.setPckageGodTurn(obj.getLgsDdg().getPckageGodTurn());
				}
				
				// 거부 전 주문 현재 상태 체크
				chk_Status = getChkStatus(dlivyDrctInfo);
				
				// 정상데이터
				if(chk_Status == 0){
					
					dlivyDrctInfo.setDlvShopId(obj.getLgsDdg().getDlvShopId());
					dlivyDrctInfo.setRejectResnCd(obj.getRejectResnCd());
					dlivyDrctInfo.setRejectCnt(obj.getRejectCount());
					dlivyDrctInfo.setDlivyDrctQty(obj.getLgsDdg().getDlivyDrctQty().intValue());
					dlivyDrctInfo.setDmstcWaybilNo(obj.getLgsDlv().getDmstcWaybilNo());
					dlivyDrctInfo.setDlvPcupspTurn(obj.getLgsDsp().getDlvPcupspTurn());
					dlivyDrctInfo.setDlvTurn(obj.getLgsDlv().getDlvTurn());
					dlivyDrctInfo.setRejectAdminId(userId);
					dlivyDrctInfo.setRegtrId(userId);

					dlivyDrctInfo.setGodNo(obj.getOrdGod().getGodNo());
					dlivyDrctInfo.setItmNo(obj.getOrdGod().getItmNo());
					
					updateDlvStatData(systemPk, dlivyDrctInfo);
					sCnt++;
					
				// 출고지시가 아닌 데이터 (셋트는 전체 데이터 조회)
				}else{
					fCnt++;
				}
			} catch(Exception e) {
				log.error("", e);
				fCnt++;
			}
		}
		
		if(fCnt < 1) {
			rtrnMsg = sCnt+"건이 처리되었습니다.";
		} else {
			rtrnMsg = "처리건 중 성공 :: "+sCnt+"건   실패 ::"+fCnt+"건\n실패 건에 대해 배송상태 확인 및 ERP 출고상태 확인 바랍니다.";
		}
			
		return rtrnMsg;
	}

	@Override
	public void updateLgsQtyOrdDrctGodCompt(SystemPK systemPk, List<String> ordNos) throws Exception {
		
		for(String ordNo : ordNos){									
			DlvOrdGodInfoDTO dlvOrdGodInfoDTO = new DlvOrdGodInfoDTO();
			dlvOrdGodInfoDTO.setOrdNo(ordNo);

			List<LgsDlivyDrctGodExtend> lgsDlivyDrctGods =  deliveryStatusService.selectOrdDlivyDrct(dlvOrdGodInfoDTO);
			
			for(LgsDlivyDrctGodExtend lgsDlivyDrct : lgsDlivyDrctGods){				
				DeliveryOrderGoodDTO dto = new DeliveryOrderGoodDTO();
				dto.setDlivyDrctGodNo(lgsDlivyDrct.getDlivyDrctGodNo());
				drctGodCompt(systemPk, dto);
			}
		}
	}
}
