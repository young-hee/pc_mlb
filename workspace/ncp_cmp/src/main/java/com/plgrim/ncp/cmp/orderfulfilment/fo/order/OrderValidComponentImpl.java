package com.plgrim.ncp.cmp.orderfulfilment.fo.order;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.SortedMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.plgrim.ncp.base.abstracts.AbstractComponent;
import com.plgrim.ncp.base.entities.datasource1.ord.Ord;
import com.plgrim.ncp.base.entities.datasource1.ord.OrdGod;
import com.plgrim.ncp.base.entities.datasource1.ord.OrdGodExtends;
import com.plgrim.ncp.biz.cart.data.CartSearchDTO;
import com.plgrim.ncp.biz.cart.result.CartCpstGodResult;
import com.plgrim.ncp.biz.cart.result.CartResult;
import com.plgrim.ncp.biz.cart.service.CartSelectService;
import com.plgrim.ncp.biz.order.data.LgsDlvspDTO;
import com.plgrim.ncp.biz.order.data.OrdGodDTO;
import com.plgrim.ncp.biz.order.data.OrderDTO;
import com.plgrim.ncp.biz.order.service.OrderCommandService;
import com.plgrim.ncp.cmp.orderfulfilment.fo.order.exception.OrderCompleteFailException;
import com.plgrim.ncp.cmp.orderfulfilment.fo.order.exception.OrderValidFailException;
import com.plgrim.ncp.framework.data.SystemPK;
import com.plgrim.ncp.framework.responsecode.common.CommonResponseCode;
import com.google.common.collect.TreeBasedTable;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Transactional(value = "transactionManager")
@Component
public class OrderValidComponentImpl extends AbstractComponent implements OrderValidComponent { 

	@Autowired
	CartSelectService cartSelectService;

	@Autowired
	OrderCommandComponent orderCommandComponent;

	@Autowired
	OrderCommandService orderCommandService;

	public void checkOrderValid(OrderDTO orderDTO
							, CartSearchDTO cartSearchDTO
							, List<CartResult> cartResultList
							, SystemPK systemPK
							, Locale locale) throws Exception {

		log.info("VaildOrder_Start. orderDTO: {}", orderDTO);
		log.info(CommonResponseCode.OD_00009_주문서_검증_시작.toMessage() , orderDTO);

		try{
			if( cartSearchDTO == null || cartResultList == null || cartResultList.size() == 0 ) {
				throw new OrderValidFailException("주문 상품의 상태가 유효하지 않습니다.");
			}

			//. 중복주문체크
			if(orderDTO.getMbr().getMbrNo() != null){
				try {
					cartSelectService.selectBskForUpdate(orderDTO.getMbr().getMbrNo());
				} catch(Exception e) {
					throw new OrderValidFailException("진행중인 주문이 있습니다. 다시 주문해 주십시오.");
				}
			}

			//. 주문유형, 매장ID
			Ord ord	= this.makeOrd(orderDTO, cartResultList);
			orderDTO.setOrd(ord);

			String ordTpCd		= ord.getOrdTpCd();
			String pickupShopId	= "";
			if ("SHOP_PKUP_ORD".equals(ordTpCd)) {
				pickupShopId = cartResultList.get(0).getShopId();
			}

			//. 주문상품정보 세팅
			TreeBasedTable<Integer, String, String> table = this.makeOrderTable(orderDTO, cartResultList, locale);
			SortedMap<Integer, Map<String, String>> sortMap = table.rowMap();

			//. 주문상품생성
			List<OrdGodExtends> ordGodExtendsList = new ArrayList<OrdGodExtends>();
			for (Map.Entry<Integer, Map<String, String>> tbl : sortMap.entrySet()) {
				Map<String, String> column = tbl.getValue();
				OrdGodExtends ordGodExtends = this.makeOrdGod(orderDTO, column);
				ordGodExtendsList.add(ordGodExtends);
			}

			/* 재고 동기화 작업이 필요할 경우 추가할것
			//. 재고동기화
			for (OrdGodExtends ordGodExtends : orderCommandService.getListOrdGodSortedByGodnoItmno(ordGodExtendsList)) {
				if ("Y".equals(ordGodExtends.getInvManageYn())) { // 재고관리여부
					if ("MCOM".equals(ordGodExtends.getPartmalSectCd())) {
						if (!"SET_GOD".equals(ordGodExtends.getGodTpCd())
								&& !"PCKAGE_GOD".equals(ordGodExtends.getGodTpCd())
						        && !"RESVE_ORD".equals(ordTpCd)) {
							List<GodItmExtend> itemList = new ArrayList<GodItmExtend>();
							GodItmExtend itm = new GodItmExtend();
							itm.setSkuNo(ordGodExtends.getSkuNo());
							itemList.add(itm);

							goodsOptStockSelectService.interfaceGodItmInvListTransaction(systemPK, ordGodExtends.getErpGodNo(), ordGodExtends.getBrndId(), itemList);
						}
					// 입점 업체인 경우
					} else {
						if (!"SET_GOD".equals(ordGodExtends.getGodTpCd())
								&& !"PCKAGE_GOD".equals(ordGodExtends.getGodTpCd())
						        && !"RESVE_ORD".equals(ordTpCd)) {
							goodsOptStockCommandService.updateGodItmStatusByPartmall((OrdGod)ordGodExtends);
						}
					}
				}
			}
			 */
			
			//. 상품상태체크
			try{

				//실 재고 체크하는 부분에 따라서 체크 로직 수정 할 것
				//orderCommandComponent.checkOrdGodQtyStatus(cartSearchDTO, locale); //재고체크

				for (OrdGodExtends ordGodExtends : orderCommandService.getListOrdGodSortedByGodnoItmno(ordGodExtendsList)) {
					ordGodExtends.setRegtrId(ord.getRegtrId());
					ordGodExtends.setRegDt(ord.getRegDt());
					ordGodExtends.setUdterId(ord.getRegtrId());
					ordGodExtends.setUdtDt(ord.getRegDt());

					orderCommandComponent.checkOrdGodStatus(ordTpCd, ordGodExtends, pickupShopId, false, locale); //상태체크
				}
			}catch(OrderCompleteFailException fail){
				if(fail.getDirectMessage()==null){
					throw new OrderValidFailException("주문 상품의 상태가 유효하지 않습니다. 다시 주문해 주십시오.");
				}else{
					throw new OrderValidFailException(fail.getDirectMessage());
				}
			}catch(Exception error) {
				throw new OrderValidFailException("주문 상품의 상태가 유효하지 않습니다. 다시 주문해 주십시오.");
			}

			log.info("VaildOrder_Complete");
			log.info(CommonResponseCode.OD_00010_주문서_검증_성공.toMessage());
			
		}catch(OrderValidFailException fail){
			log.warn("VaildOrder_Fail : {} / {} / {}", fail.getDirectMessage(), fail.getMessage(), fail.getCustomKey());
			log.warn(CommonResponseCode.OD_40031_주문서_검증_실패.toMessage()+" : {} / {} / {}", fail.getDirectMessage(), fail.getMessage(), fail.getCustomKey());
			throw fail;

		}catch(Exception error){
			log.error("VaildOrder_Fail : {}", error);
			log.error(CommonResponseCode.OD_40031_주문서_검증_실패.toMessage()+" : {}", error);
			throw new OrderValidFailException("주문 상품의 상태가 유효하지 않습니다. 다시 주문해 주십시오.");
		}

	}

	private Ord makeOrd(OrderDTO orderDTO, List<CartResult> cartResultList){

		Ord ord = orderDTO.getOrd();

		String godTpCd				= cartResultList.get(0).getGod().getGodTpCd();
		String chkResveSaleGodYn	= cartResultList.get(0).getGod().getResveSaleGodYn();
		String chkDlvSectCd			= cartResultList.get(0).getBskGod().getDlvSectCd();

		ord.setOrdTpCd("GNRL_ORD");
		if("Y".equals(chkResveSaleGodYn)){
			ord.setOrdTpCd("RESVE_ORD");
		}
		if("PKUP_DLV".equals(chkDlvSectCd)){
			ord.setOrdTpCd("SHOP_PKUP_ORD");
		}
		if("GFCT".equals(godTpCd)){
			ord.setOrdTpCd("GFCT_ORD");
		}

		ord.setMallId(orderDTO.getMallId());

		ord.setRegtrId(orderDTO.getRegtrId());
		ord.setRegDt(orderDTO.getRegDt());
		ord.setUdterId(orderDTO.getRegtrId());
		ord.setUdtDt(orderDTO.getRegDt());

		return ord;

	}

	private TreeBasedTable<Integer, String, String> makeOrderTable(OrderDTO orderDTO, List<CartResult> cartResultList, Locale locale)
	        throws Exception {

		TreeBasedTable<Integer, String, String> table = TreeBasedTable.create();
		{
			int godTurn = 0;
			List<String> existsSessionList = new ArrayList<String>();

			for (LgsDlvspDTO lgsDlvspDTO : orderDTO.getLgsDlvspDTOList()) {
				String parentsGodTurnStr = "";

//				List<CouponDTO> couponDTOList = CollectionService.emptyListIfNull(lgsDlvspDTO.getCouponDTOList());
				List<OrdGodDTO> ordGodDTOList = lgsDlvspDTO.getOrdGodDTOList();

				for (OrdGodDTO ordGodDTO : ordGodDTOList) {
					String delvBskNo = ordGodDTO.getBskNo();
					int delvBskGodTurn = ordGodDTO.getGodTurn();

					OrdGod ordGod = ordGodDTO.getOrdGod();
					int ordQty = (int) (long) ordGod.getOrdQty();

					if (ordQty > 0) {
						//. 주문서에서 넘어온 상품이 세션에 존재하는지
						boolean isExistsSession = false;

						godTurn++;
						for (CartResult cart : cartResultList) {
							String bskNo = cart.getBskGod().getBskNo();
							int bskGodTurn = cart.getBskGod().getGodTurn();
							if (delvBskNo.equals(bskNo) && delvBskGodTurn == bskGodTurn) {

								isExistsSession = true;

								String packageYn = cart.getBskGod().getPckageGodYn();
								String additionalPack = "";
								if ("Y".equals(packageYn) && bskGodTurn != cart.getParentGodTurn()) {
									additionalPack = "Y";
								}


								table.put(godTurn, "godTurn", String.valueOf(godTurn));
								table.put(godTurn, "godNm", cart.getGod().getGodNm());
								table.put(godTurn, "godNo", cart.getBskGod().getGodNo());
								table.put(godTurn, "itmNo", cart.getBskGod().getItmNo());
								table.put(godTurn, "skuNo", this.avoidNull(cart.getGodItm().getSkuNo(), ""));
								table.put(godTurn, "erpGodNo", this.avoidNull(cart.getGod().getErpGodNo(), ""));
								table.put(godTurn, "brndId", cart.getGod().getBrndId());
								table.put(godTurn, "invManageYn", this.avoidNull(cart.getGod().getInvManageYn(), ""));
								table.put(godTurn, "lmttInvYn", this.avoidNull(cart.getGod().getLmttInvYn(), ""));
								table.put(godTurn, "partmalSectCd", cart.getGod().getPartmalSectCd());
								table.put(godTurn, "godTpCd", ordGod.getGodTpCd());
								table.put(godTurn, "ordQty", String.valueOf(ordQty));

								table.put(godTurn, "packageYn", packageYn); // 세트상품
								table.put(godTurn, "parentsGodTurn", "Y".equals(additionalPack) ? parentsGodTurnStr : "");
								table.put(godTurn, "basicPack", ""); // 기본구성상품
								table.put(godTurn, "additionalPack", additionalPack); // 추가구성상품
								table.put(godTurn, "pckageGodTpCd", "Y".equals(additionalPack) ? "ADIT_CPST_GOD" : "");

								// 패키지형 상품의 기본구성상품
								if ("Y".equals(packageYn) && !"Y".equals(additionalPack)) {
									List<CartCpstGodResult> cpstGodList = cartSelectService.selectBskCpstGodCnnc(cart.getBskGod());

									parentsGodTurnStr = String.valueOf(godTurn);
									int parentsGodTurn = godTurn;
									for (CartCpstGodResult cpst : cpstGodList) {
										godTurn++;

										long cpstQty = cpst.getBskCpstGodCnnc().getCpstGodQty();


										table.put(godTurn, "godTurn", String.valueOf(godTurn));
										table.put(godTurn, "godNm", cpst.getGod().getGodNm());
										table.put(godTurn, "godNo", cpst.getGodNo());
										table.put(godTurn, "itmNo", cpst.getItmNo());
										table.put(godTurn, "skuNo", cpst.getSkuNo());
										table.put(godTurn, "erpGodNo", cpst.getGod().getErpGodNo());
										table.put(godTurn, "brndId", cpst.getGod().getBrndId());
										table.put(godTurn, "invManageYn", table.get(godTurn - 1, "invManageYn"));
										table.put(godTurn, "lmttInvYn", table.get(godTurn - 1, "lmttInvYn"));
										table.put(godTurn, "partmalSectCd", table.get(godTurn - 1, "partmalSectCd"));
										table.put(godTurn, "ordQty", String.valueOf(ordQty * cpstQty));

										table.put(godTurn, "packageYn", "Y"); // 세트상품
										table.put(godTurn, "parentsGodTurn", String.valueOf(parentsGodTurn));
										table.put(godTurn, "basicPack", "Y");	// 기본구성상품
										table.put(godTurn, "additionalPack", ""); // 추가구성상품
										table.put(godTurn, "pckageGodTpCd", cpst.getBskCpstGodCnnc().getPckageGodTpCd());

									}
								}

								if( !existsSessionList.contains(bskNo+":"+bskGodTurn) ) {
									existsSessionList.add(bskNo+":"+bskGodTurn);
								}

								break;
							}
						}

						if(!isExistsSession){
							throw new OrderValidFailException("주문 상품이 변경되었습니다. 다시 주문해 주십시오.");
						}
					}
				}
			}

			if( existsSessionList.size() != cartResultList.size() ) {
				throw new OrderValidFailException("주문 상품이 변경되었습니다. 다시 주문해 주십시오.");
			}
		}

		return table;
	}

	private OrdGodExtends makeOrdGod(OrderDTO orderDTO, Map<String, String> column) throws Exception {

		OrdGodExtends ordGodExtends = new OrdGodExtends();
		ordGodExtends.setOrdGodTurn(Integer.parseInt(column.get("godTurn")));
		ordGodExtends.setGodNo(column.get("godNo"));
		ordGodExtends.setItmNo(column.get("itmNo"));
		ordGodExtends.setGodNm(column.get("godNm"));
		ordGodExtends.setErpGodNo(column.get("erpGodNo"));
		ordGodExtends.setSkuNo(column.get("skuNo"));
		ordGodExtends.setBrndId(column.get("brndId"));
		ordGodExtends.setPartmalSectCd(column.get("partmalSectCd"));
		ordGodExtends.setGodTpCd(column.get("godTpCd"));
		ordGodExtends.setOrdQty(Long.parseLong(column.get("ordQty")));

		ordGodExtends.setMallId(orderDTO.getMallId());

		ordGodExtends.setInvManageYn(column.get("invManageYn"));
		ordGodExtends.setLmttInvYn(column.get("lmttInvYn"));
		ordGodExtends.setBasicPackYn(column.get("basicPack"));

		ordGodExtends.setRegtrId(orderDTO.getRegtrId());
		ordGodExtends.setRegDt(orderDTO.getRegDt());
		ordGodExtends.setUdterId(orderDTO.getRegtrId());
		ordGodExtends.setUdtDt(orderDTO.getRegDt());

		ordGodExtends.setPckageGodTpCd(column.get("pckageGodTpCd")); // '17 9.04 추가

		return ordGodExtends;
	}



	private String avoidNull(final String value, final String replacer) {
		if (value == null || value.trim().length() == 0) {
			return replacer;
		}
		else {
			return value;
		}
	}
}