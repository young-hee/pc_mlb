package com.plgrim.ncp.cmp.orderfulfilment.bo.order;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Maps;
import com.plgrim.ncp.base.abstracts.AbstractComponent;
import com.plgrim.ncp.base.entities.datasource1.cso.CsoCnsltMemo;
import com.plgrim.ncp.base.entities.datasource1.god.GodExtend;
import com.plgrim.ncp.base.entities.datasource1.god.GodItm;
import com.plgrim.ncp.base.entities.datasource1.god.GodShopItmInvExtend;
import com.plgrim.ncp.base.entities.datasource1.inf.InfAffOrd;
import com.plgrim.ncp.base.entities.datasource1.inf.InfAffOrdErr;
import com.plgrim.ncp.base.entities.datasource1.inf.InfOrdGodErpDstb;
import com.plgrim.ncp.base.entities.datasource1.inf.InfTmprAffOrd;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsDlivyDrctGod;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsDlivyDrctGodExtend;
import com.plgrim.ncp.base.entities.datasource1.ord.Ord;
import com.plgrim.ncp.base.entities.datasource1.ord.OrdExtend;
import com.plgrim.ncp.base.entities.datasource1.ord.OrdGodExtend;
import com.plgrim.ncp.base.entities.datasource1.pay.Pay;
import com.plgrim.ncp.base.entities.datasource1.pay.PayExtend;
import com.plgrim.ncp.base.enums.DeliveryEnum;
import com.plgrim.ncp.base.enums.GoodsEnum;
import com.plgrim.ncp.base.enums.OrderClaimEnum;
import com.plgrim.ncp.biz.callcenter.service.MemoService;
import com.plgrim.ncp.biz.delivery.data.DeliveryInfErpDTO;
import com.plgrim.ncp.biz.delivery.data.DeliverySearchDTO;
import com.plgrim.ncp.biz.delivery.data.DlvOrdGodInfoDTO;
import com.plgrim.ncp.biz.delivery.data.LgsDlvspBoDTO;
import com.plgrim.ncp.biz.delivery.service.DeliveryAssignService;
import com.plgrim.ncp.biz.delivery.service.DeliveryInfErpExternalService;
import com.plgrim.ncp.biz.delivery.service.DeliveryInfErpService;
import com.plgrim.ncp.biz.delivery.service.DeliveryService;
import com.plgrim.ncp.biz.delivery.service.DeliveryStatusService;
import com.plgrim.ncp.biz.interfaces.data.InfOrdGodErpDstbBoDTO;
import com.plgrim.ncp.biz.interfaces.service.InfOrdGodErpDstbService;
import com.plgrim.ncp.biz.order.data.OrderBoDTO;
import com.plgrim.ncp.biz.order.data.OrderParamDTO;
import com.plgrim.ncp.biz.order.service.OrderBoAffService;
import com.plgrim.ncp.biz.order.service.OrderBoSelectService;
import com.plgrim.ncp.biz.order.service.OrderBoService;
import com.plgrim.ncp.biz.order.service.OrderBulkService;
import com.plgrim.ncp.biz.order.service.OrderCommandService;
import com.plgrim.ncp.biz.order.service.OrderInterfaceService;
import com.plgrim.ncp.biz.order.service.OrderPhoneService;
import com.plgrim.ncp.biz.order.service.OrderSelectService;
import com.plgrim.ncp.biz.pay.data.PayBoDTO;
import com.plgrim.ncp.biz.pay.service.PayBoService;
import com.plgrim.ncp.commons.util.BOSecurityUtil;
import com.plgrim.ncp.framework.commons.StringService;
import com.plgrim.ncp.framework.data.SystemPK;
import com.plgrim.ncp.interfaces.util.InterfaceApiCommon;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Transactional(value = "transactionManager")
@Component
public class OrderBoCommandComponentImpl extends AbstractComponent implements OrderBoCommandComponent{
 
	@Autowired
	OrderBulkService orderBulkService;

	@Autowired
	OrderPhoneService orderPhoneService;

	@Autowired
	DeliveryService deliveryService;

	@Autowired
	InfOrdGodErpDstbService infOrdGodErpDstbService;

	@Autowired
	PayBoService payBoService;

	@Autowired
	OrderBoService orderBoService;

	@Autowired
	OrderBoAffService orderBoAffService;

	/** 메모 서비스. */
	@Autowired
	MemoService memoService;

	@Autowired
	DeliveryAssignService deliveryAssignService;

	@Autowired
	DeliveryStatusService deliveryStatusService;

	@Autowired
	OrderBoSelectService orderBoSelectService;

	@Autowired
	InterfaceApiCommon interfaceApiCommon;

	@Autowired
	DeliveryInfErpService deliveryInfErpService;

	@Autowired
	DeliveryInfErpExternalService deliveryInfErpExternalService;
	
	@Autowired
	OrderSelectService orderSelectService;

	@Autowired
	OrderCommandService orderCommandService;
	
	@Autowired
	OrderInterfaceService orderInterfaceService;
	
    @Override
	public void bulkOrder(OrderBoDTO orderDTO) throws Exception {

		orderBulkService.insertOrder(orderDTO);

		addProcessor(orderDTO);
	}

    @Override
	public void phoneOrder(OrderBoDTO orderDTO) throws Exception {

		orderPhoneService.insertOrder(orderDTO);

		orderDTO.setGodSelect(false);
		addProcessor(orderDTO);
	}

    @Override
	public void affOrder(OrdExtend extend) throws Exception {
		
		OrderBoDTO dto = orderBoAffService.getOrderBoDTO(extend);
		
		// 2016.01.20 by Cannon
		// 제휴사 가상배송완료 여부 체크
		if("Y".equals(extend.getVirtlDlvComptYn())){
			dto.setAffOrdVirtlDlvComptYn("Y");
		}else{
			dto.setAffOrdVirtlDlvComptYn("N");
		}
		orderBoAffService.insertOrder(dto);

		addProcessor(dto);

		InfAffOrd infAffOrd = new InfAffOrd();

		for (OrdGodExtend ordGodExtend : dto.getOrdGodList()) {
	    
			infAffOrd.setAffGodOrdNo(dto.getOrd().getAffComOrdNo());
			infAffOrd.setOrdNo(dto.getOrdNo());
			infAffOrd.setAffOrdSn(ordGodExtend.getAffOrdSn()); 
			infAffOrd.setOrdGodTurn(ordGodExtend.getOrdGodTurn());
			infAffOrd.setAffOrdTurn(ordGodExtend.getAffOrdTurn());
			infAffOrd.setAffItmOrdNo(ordGodExtend.getAffItmOrdNo());	
			infAffOrd.setGodNo(ordGodExtend.getGodNo());
			infAffOrd.setItmNo(ordGodExtend.getItmNo());
			infAffOrd.setUdterId(dto.getRegtrId());
			infAffOrd.setAffOrdStatCd("ORD_COMPT");
			infAffOrd.setAffComNm(ordGodExtend.getAffComNm());
			infAffOrd.setAffVrscComId(dto.getAffVrscComId());
			updateAffOrd(infAffOrd);
        }
		

	}
    
    @Override
	public void insertInfAffOrdErr(InfAffOrdErr infAffOrdErr) throws Exception {

		orderBoAffService.insertInfAffOrdErr(infAffOrdErr);
	}
    
    @Override
	public void insertInfAffOrdErr(InfAffOrdErr infAffOrdErr,InfAffOrd infAffOrd) throws Exception {

		orderBoAffService.insertInfAffOrdErr(infAffOrdErr, infAffOrd);
	}

    @Override
	public void updateAffOrd(InfAffOrd infAffOrd) throws Exception {

		orderBoAffService.updateAffOrd(infAffOrd);
	}
	
    @Override
	public void deleteAffOrd(InfAffOrd infAffOrd) throws Exception {

		orderBoAffService.deleteAffOrd(infAffOrd);
	}

    @Override
	public List<OrdExtend> selectAffOrd(OrderBoDTO orderDTO) throws Exception {

		return orderBoAffService.selectAffOrd(orderDTO);

	}

	private void addProcessor(OrderBoDTO orderDTO) throws Exception {

		String ordNo = orderDTO.getOrdNo();
		String regtrId = orderDTO.getRegtrId();
		String mbrNo = orderDTO.getMbrNo();
        String mallId = StringUtils.defaultString(orderDTO.getMallId(), "DXM");

		if (StringService.isNotEmpty(orderDTO.getMemoCont())) {
			CsoCnsltMemo csoCnsltMemo = new CsoCnsltMemo();

			csoCnsltMemo.setOrdNo(ordNo);
			csoCnsltMemo.setMbrNo(mbrNo);
			csoCnsltMemo.setMemoCont(orderDTO.getMemoCont());
			csoCnsltMemo.setMemoRegtrId(regtrId);
			csoCnsltMemo.setRegtrId(regtrId);
			csoCnsltMemo.setUdterId(regtrId);
			insertCsoCnsltMemo(csoCnsltMemo);
		}

		LgsDlvspBoDTO lgsDlvspBoDTO = new LgsDlvspBoDTO();

		lgsDlvspBoDTO.setLgsDlvspList(orderDTO.getLgsDlvspList());
        lgsDlvspBoDTO.setMallId(mallId);

		deliveryService.lgsDlvspProcessor(lgsDlvspBoDTO, ordNo, regtrId);

		orderDTO.setLgsDlvspList(lgsDlvspBoDTO.getLgsDlvspList());

		if (orderDTO.getOrd().getOrdTpCd().equals(OrderClaimEnum.ORD_TP_CD_LAG_QTY_ORD.toString())) {
			orderBulkService.ordGodProcessor(orderDTO, regtrId);
		}

		List<InfOrdGodErpDstb> infOrdGodErpDstbs = orderDTO.getInfOrdGodErpDstbList();

		lgsDlvspBoDTO.setLgsDlvspList(orderDTO.getLgsDlvspList());
        lgsDlvspBoDTO.setMallId(mallId);
		// 물류 배송지 등록

		deliveryService.lgsDlvProcessor(lgsDlvspBoDTO, regtrId, infOrdGodErpDstbs, true);

		InfOrdGodErpDstbBoDTO infOrdGodErpDstbBoDTO = new InfOrdGodErpDstbBoDTO();

		infOrdGodErpDstbBoDTO.setInfOrdGodErpDstbList(infOrdGodErpDstbs);
		infOrdGodErpDstbService.infOrdGodErpDstbProcessor(infOrdGodErpDstbBoDTO, ordNo);

		PayBoDTO payBoDTO = new PayBoDTO();
		payBoDTO.setPayList(orderDTO.getPayList());
        payBoDTO.setMallId(mallId);
        
        if(orderDTO.getKcpDTO()!=null){
        	payBoDTO.setMbr(orderDTO.getMbr());
        	payBoService.payKcpProcessor(payBoDTO, orderDTO.getKcpDTO(), orderDTO.getOrd().getPayExchgRtCrncySumAmt().longValue(), ordNo, regtrId);
        }else{
        	payBoService.payProcessor(payBoDTO, ordNo, regtrId);
        }
	}

	@Override
	public void updatelagQtyOrdDcsn(OrderBoDTO orderDTO) throws Exception {

	    Pay pay = null;
		for (String ordNo : orderDTO.getOrdExtend().getOrdNos()) {
			orderDTO.getOrdExtend().setOrdNo(ordNo);
			orderBoService.updatelagQtyOrdDcsn(orderDTO.getOrdExtend());
			
			pay = payBoService.getOrdPayInfo(ordNo);
			
			/**
			 * 결제완료인 경우 결제상태 업데이트 하지 않음
			 * 2015.11.18
			 */
			if (pay != null && !"PAY_COMPT".equals(pay.getDealTpCd())) {
	            PayExtend payExtend = new PayExtend();
	            payExtend.setOrdNo(orderDTO.getOrdExtend().getOrdNo());
	            payExtend.setDealTpCd("PREDLIVY_DEPST_WAIT");
	            payBoService.confirmDepositPay(payExtend);
			}
			
		}

	}

	@Override
	public void confirmDeposit(OrderBoDTO orderDTO) throws Exception {

		for (String ordNo : orderDTO.getOrdExtend().getOrdNos()) {
			orderDTO.getOrdExtend().setOrdNo(ordNo);
            orderBoService.confirmDeposit(orderDTO.getOrdExtend());
		
			PayExtend payExtend = new PayExtend();
			payExtend.setOrdNo(orderDTO.getOrdExtend().getOrdNo());
			payExtend.setDealTpCd("PAY_COMPT");
			payBoService.confirmDepositPay(payExtend);
			
			
			
			OrderParamDTO salesDTO = new OrderParamDTO();
			salesDTO.setOrdNo(orderDTO.getOrdExtend().getOrdNo());
			salesDTO.setMallId(orderSelectService.selectOrdMallId(orderDTO.getOrdExtend().getOrdNo()));
			orderInterfaceService.sendSalesData(salesDTO);
		}


	}

	@Override
	public void insertCsoCnsltMemo(CsoCnsltMemo csoCnsltMemo) throws Exception {

		csoCnsltMemo.setMemoTpCd(OrderClaimEnum.ORD.toString());
		csoCnsltMemo.setExpsrYn("N");

		memoService.insertCsoCnsltMemo(csoCnsltMemo);
	}

	@Override
	public HashMap<String, Object> insertInfTmprAffOrdTemp(List<InfTmprAffOrd> infTmprAffOrds, String loginId) throws Exception {
		
		return orderBoAffService.insertInfTmprAffOrdTemp(infTmprAffOrds, loginId);

	}

	@Override
	public void virtlDlvCompt(Ord ord) throws Exception {

		orderBoService.virtlDlvCompt(ord);

	}
	
	@Override
	public void virtlDlvCompt4PayWait(Ord ord) throws Exception {

		orderBoService.virtlDlvCompt4PayWait(ord);

	}

	@Override
	public HashMap<String, Object> optionChange(OrderBoDTO orderDTO, SystemPK systemPk) throws Exception {

		HashMap<String, Object> map = Maps.newHashMap();
		map.put("RT", "F");

		DlvOrdGodInfoDTO dlvOrdGodInfoDTO = new DlvOrdGodInfoDTO();
		dlvOrdGodInfoDTO.setOrdNo(orderDTO.getOrdNo());
		dlvOrdGodInfoDTO.setOrdGodTurn(String.valueOf(orderDTO.getOrdGodTurn()));
		dlvOrdGodInfoDTO.setAdminId("validation");
		List<LgsDlivyDrctGodExtend> lgsDlivyDrctGods =  deliveryStatusService.selectOrdDlivyDrct(dlvOrdGodInfoDTO);

		for (LgsDlivyDrctGodExtend lgsDlivyDrctGod : lgsDlivyDrctGods) {
			String cd = lgsDlivyDrctGod.getDlvStatCd();
			String dm = lgsDlivyDrctGod.getDmstcWaybilNo();

			if (!(cd.equals("DLIVY_DRCT") || cd.equals("SHTG_RCEPT") || cd.equals("DLV_WAIT")) || StringService.isNotEmpty(dm)) {
				map.put("MSG", "배송중인 상품이 존재 하여 옵션 변경이 불가합니다.");
				return map;
			}
			
		}

		OrdGodExtend ordGodExtend = new OrdGodExtend();
		ordGodExtend.setGodNo(orderDTO.getGodNo());
		ordGodExtend.setItmNo(orderDTO.getItmNo());

		GodExtend godExtend = orderBoSelectService.selectBoOrdGodItmHist(ordGodExtend);

		//재고관리 여부
		String invManageYn = godExtend.getInvManageYn();
		String resveSaleGodYn = godExtend.getResveSaleGodYn();
		String itmNo = orderDTO.getItmNo();
		if (invManageYn != null && invManageYn.equals("Y")) {

			if ("RESVE_ORD".equals(orderDTO.getOrdTp()) && resveSaleGodYn != null && resveSaleGodYn.equals("Y")) {

				if (godExtend.getResveInvQty() < orderDTO.getQty()) {

					map.put("MSG", "옵션 변경할 재고가 부족합니다.");
					return map;
				}

			}//한정 재고가 Y 인경우
			else if (godExtend.getLmttInvYn() != null && godExtend.getLmttInvYn().equals("Y")) {

				if (orderDTO.getOrdTp() != null && orderDTO.getOrdTp().equals("AFF_ORD")) {

					//재휴사 한정 재고 수량
					if (godExtend.getAffComLmttInvQty() < orderDTO.getQty()) {

						map.put("MSG", "옵션 변경할 재고가 부족합니다.");
						return map;
					}

				}
				else {
					//온라인 한정 재고 수량
					if (godExtend.getOnlneLmttInvQty() < orderDTO.getQty()) {

						map.put("MSG", "옵션 변경할 재고가 부족합니다.");
						return map;
					}


				}
			}
			else {

				long safeInvQty = 0;

				if (godExtend.getSafeInvUseYn().equals("Y")) {

					safeInvQty = godExtend.getSafeInvQty();
				}
				// 실재고 = 총 가용 재고 수량 - 판매 예정 수 - IF(안전 재고 사용여부=Y,안전 재고 수,0)

				long qty = godExtend.getTotUsefulInvQty() - godExtend.getSalePrearngeQty() - safeInvQty;

				if (qty < orderDTO.getQty()) {

					map.put("MSG", "옵션 변경할 재고가 부족합니다.");
					return map;
				}

			}



			if (godExtend.getPartmalSectCd().equals(GoodsEnum.GoodsPartmal.MCOM.toString())) {

				for (LgsDlivyDrctGod lgsDlivyDrctGod : orderDTO.getLgsDlivyDrctGods()) {


					String shopId = lgsDlivyDrctGod.getDlvShopId();
					long dlivyDrctQty = lgsDlivyDrctGod.getDlivyDrctQty();

					GodShopItmInvExtend shopItmInv = new GodShopItmInvExtend();
					shopItmInv.setItmNo(itmNo);
					shopItmInv.setShopId(shopId);

					//임시 매장 또는 센터이고 값이 없으면 패스
					if (!(shopId == null || shopId.trim() == "" || "B031".equals(shopId) || DeliveryEnum.DLV_ONLINE_SHOP_DISCOVERY.toString().equals(shopId)
							|| DeliveryEnum.DLV_ONLINE_SHOP_MLB.toString().equals(shopId)
							|| DeliveryEnum.DLV_ONLINE_SHOP_MLB_KIDZ.toString().equals(shopId)
							|| DeliveryEnum.DLV_ONLINE_SHOP_SA.toString().equals(shopId))) {

						long safeInvQty = 0;

						if (godExtend.getSafeInvUseYn().equals("Y")) {

							safeInvQty = godExtend.getSafeInvQty();
						}
						// 실재고 = 총 가용 재고 수량 - 판매 예정 수 - IF(안전 재고 사용여부=Y,안전 재고 수,0)

						long qty = shopItmInv.getInvQty() - shopItmInv.getSalePrearngeQty() - safeInvQty;

						if (qty < dlivyDrctQty) {

							map.put("MSG", "옵션 변경할 " + shopId + " 매장 재고가 부족합니다.");
							return map;
						}

					}

				}
			}
		}


		GodItm godItm = new GodItm();

		godItm.setGodNo(orderDTO.getGodNo());
		godItm.setItmNo(orderDTO.getItmNo());
		long qty = orderDTO.getQty();
		godItm.setSalePrearngeQty(qty);
		String regtrId = "0000";

		if ("BO".equals(orderDTO.getRegtrId())) {
			regtrId = BOSecurityUtil.getLoginId();
		}

		godItm.setUdterId(regtrId);

		if ("RESVE_ORD".equals(orderDTO.getOrdTp()) && resveSaleGodYn != null && resveSaleGodYn.equals("Y")) {

			godItm.setResveInvQty(-qty);
		}
		else if (godExtend.getLmttInvYn() != null && godExtend.getLmttInvYn().equals("Y")) {

			if (orderDTO.getOrdTp() != null && orderDTO.getOrdTp().equals("AFF_ORD")) {

				godItm.setAffComLmttInvQty(-qty);
			}
			else {
				godItm.setOnlneLmttInvQty(-qty);

			}

		}

		orderBoService.updateGoodsItmOrd(godItm);

		if (godExtend.getErpInvIntrlckYn() == null && godExtend.getErpInvIntrlckYn().equals("N")) {

			ordGodExtend.setItmNo(orderDTO.getOriItmNo());

			GodExtend ex = orderBoSelectService.selectBoOrdGodItmHist(ordGodExtend);
			godItm.setResveInvQty(null);
			godItm.setAffComLmttInvQty(null);
			godItm.setOnlneLmttInvQty(null);
			if ("RESVE_ORD".equals(orderDTO.getOrdTp()) && resveSaleGodYn != null && resveSaleGodYn.equals("Y")) {

				godItm.setResveInvQty(+qty);
			}
			else if (ex.getLmttInvYn() != null && ex.getLmttInvYn().equals("Y")) {
				if (orderDTO.getOrdTp() != null && orderDTO.getOrdTp().equals("AFF_ORD")) {

					godItm.setAffComLmttInvQty(+qty);

				}
				else {
					godItm.setOnlneLmttInvQty(+qty);
				}

			}

			godItm.setItmNo(orderDTO.getOriItmNo());
			godItm.setSalePrearngeQty(-qty);
			orderBoService.updateGoodsItmOrd(godItm);
		}


		GodShopItmInvExtend godShopItmInvExtend = new GodShopItmInvExtend();

		if (godExtend.getPartmalSectCd().equals(GoodsEnum.GoodsPartmal.MCOM.toString())
				&& !("RESVE_ORD".equals(orderDTO.getOrdTp()) && resveSaleGodYn != null && resveSaleGodYn.equals("Y"))) {

			for (LgsDlivyDrctGod lgsDlivyDrctGod : orderDTO.getLgsDlivyDrctGods()) {
				String shopId = lgsDlivyDrctGod.getDlvShopId();

				godShopItmInvExtend.setItmNo(orderDTO.getItmNo());
				godShopItmInvExtend.setGodNo(orderDTO.getGodNo());
				godShopItmInvExtend.setShopId(shopId);
				godShopItmInvExtend.setSalePrearngeQty(lgsDlivyDrctGod.getDlivyDrctQty());
				godShopItmInvExtend.setUdterId(regtrId);
				orderBoService.updateGoodsShopItmInvOrd(godShopItmInvExtend);

				if (godExtend.getErpInvIntrlckYn() == null && godExtend.getErpInvIntrlckYn().equals("N")) {

					godShopItmInvExtend.setItmNo(orderDTO.getOriItmNo());
					godShopItmInvExtend.setSalePrearngeQty(-lgsDlivyDrctGod.getDlivyDrctQty());
					orderBoService.updateGoodsShopItmInvOrd(godShopItmInvExtend);
				}

			}

		}

		orderDTO.setRegtrId(regtrId);
		orderBoService.optionChange(orderDTO, godExtend);

		DeliverySearchDTO deliverySearchDTO = new DeliverySearchDTO();
		for (LgsDlivyDrctGod lgsDlivyDrctGod : orderDTO.getLgsDlivyDrctGods()) {

			deliverySearchDTO.setOrdNo(orderDTO.getOrdNo());
			deliverySearchDTO.setDlivyDrctGodNo(lgsDlivyDrctGod.getDlivyDrctGodNo());
			List<DeliveryInfErpDTO> slist = deliveryInfErpService.selectErpResveRcptfrCreateInfoList(deliverySearchDTO);

			if (slist.size() > 0) { //4. ERP 예약영수증 생성
				deliveryInfErpService.modifyPreSalesCreation(slist,true,BOSecurityUtil.getLoginId());
			}
		}
		
		
		map.put("RT", "S");

		return map;

	}
}
