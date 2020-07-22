package com.plgrim.ncp.biz.order.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.util.StringUtils;
import com.plgrim.ncp.base.abstracts.AbstractService;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrIssuCpn;
import com.plgrim.ncp.base.entities.datasource1.ord.OrdGodAplPrmExtends;
import com.plgrim.ncp.base.enums.OrderClaimEnum;
import com.plgrim.ncp.base.enums.OrderEnum;
import com.plgrim.ncp.biz.order.data.OrderParamDTO;
import com.plgrim.ncp.biz.order.exception.OrderInterfaceFailException;
import com.plgrim.ncp.biz.order.repository.OrderSelectRepository;
import com.plgrim.ncp.commons.data.order.InfOrderSalesDTO;
import com.plgrim.ncp.commons.data.order.OrderResultDTO;
import com.plgrim.ncp.framework.commons.ContextService;
import com.plgrim.ncp.framework.commons.StringService;
import com.plgrim.ncp.framework.data.SystemPK;
import com.plgrim.ncp.interfaces.abstracts.InterfaceSDO;
import com.plgrim.ncp.interfaces.order.adapter.OrderAdapter;
import com.plgrim.ncp.interfaces.order.data.OrderPaymentInfoListSDO;
import com.plgrim.ncp.interfaces.order.data.OrderPaymentInfoSDO;
import com.plgrim.ncp.interfaces.order.data.OrderUseTempMileageListSDO;
import com.plgrim.ncp.interfaces.order.data.OrderUseTempMileageSDO;
import com.plgrim.ncp.interfaces.promotion.adapter.PromotionAdapter;
import com.plgrim.ncp.interfaces.promotion.data.OnOffCouponUseSDO;
import com.plgrim.ncp.interfaces.util.AdapterHeader;
import com.plgrim.ncp.interfaces.util.InterfaceApiCommon;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class OrderInterfaceService extends AbstractService {


	@Autowired
	OrderSelectRepository orderSelectRepository;

    @Autowired
	private OrderAdapter orderAdapter;
    
    @Autowired
    private InterfaceApiCommon interfaceApiCommon;
    
    @Autowired
	private PromotionAdapter promotionAdapter;

    /**
     * 매출처리
     * 
     * @param dto
     * @return
     */
	public List<OrderPaymentInfoListSDO> sendSalesData(OrderParamDTO dto) {
		
		
		
		AdapterHeader adapterHeader = new AdapterHeader();
		if(dto.getMallId()!=null&&!"".equals(dto.getMallId())){
			adapterHeader.setRequestId(interfaceApiCommon.getRequestId());
			adapterHeader.setMallId(dto.getMallId());
			adapterHeader.setLangCd("KOR");
			adapterHeader.setDvcCd("PC");
		}else{
			SystemPK systemPK = getIdGenService().getAutoGeneratorSystemPK(ContextService.getCurrentRequest());
			adapterHeader.setRequestId(interfaceApiCommon.getRequestId());
			adapterHeader.setMallId(systemPK.getMall());
			adapterHeader.setLangCd("KOR");
			adapterHeader.setDvcCd(systemPK.getDevice());
		}
		
		
		return this.sendSalesData(dto, adapterHeader);
	}
	
    /**
     * 매출처리
     * 
     * @param dto
     * @return
     */
	public List<OrderPaymentInfoListSDO> sendSalesData(OrderParamDTO dto, AdapterHeader adapterHeader) {
		
		List<InfOrderSalesDTO> salesList = orderSelectRepository.selectOrderSalesList(dto);
		
		if(salesList == null || salesList.isEmpty()){	// 결과가 없을 경우 빈 리스트 리턴처리
			return new ArrayList<OrderPaymentInfoListSDO>();
		}
		
		OrderPaymentInfoSDO sdo = new OrderPaymentInfoSDO();
		List<OrderPaymentInfoListSDO> ordList = new ArrayList<OrderPaymentInfoListSDO>();
		
		for(InfOrderSalesDTO item:salesList){
			OrderPaymentInfoListSDO ordGodSDO = new OrderPaymentInfoListSDO();

			ordGodSDO.setOrdNo(item.getOrdNo());
			ordGodSDO.setOrdDtlNo(item.getQtyTurn().toString());
			
			if(StringService.isEmpty(dto.getClmNo())){
				ordGodSDO.setSaleDate(item.getPayDt());
			}else{
				ordGodSDO.setSaleDate(item.getClmComptDt());
			}
			if(ordGodSDO.getSaleDate()==null){
				ordGodSDO.setSaleDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
			}
			
			if(StringService.isNotEmpty(item.getClmTpCd())){
				ordGodSDO.setSaleType("2");//반품
			}else{
				ordGodSDO.setSaleType("1");//판매
			}
			/**
			 * (X : 디스커버리, M:MLB, I:MLB Kids)
			 *     /**
		     * 매장코드 (MLB : 510 , MLB KIDS : 50002, DX : 30004 (VIP의 경우 RT처리) )
		     */
			if(StringUtils.isNullOrEmpty(dto.getBrandCode())){
				if(StringUtils.isNullOrEmpty(item.getBrand())) {
					ordGodSDO.setBrand("X");
					ordGodSDO.setShopCode("30004");
				} else {
					ordGodSDO.setBrand(item.getBrand());
					if("M".equals(item.getBrand())) {
						ordGodSDO.setShopCode("510");
					} else if("I".equals(item.getBrand())) {
						ordGodSDO.setShopCode("50002");
					} else if("A".equals(item.getBrand())) {
						ordGodSDO.setShopCode("30003");
					} else {
						ordGodSDO.setShopCode("30004");
					}
				}				
			}else{
				throw new OrderInterfaceFailException("ord.error.interface.param.error"); 
			}
			ordGodSDO.setSeason(item.getSeasonCd());
			ordGodSDO.setPartCode(item.getPartCode());
			ordGodSDO.setColor(item.getColorCd());
			ordGodSDO.setSiz(item.getSizeCd());
			ordGodSDO.setSaleTrType(item.getSaleTrType());
			
			ordGodSDO.setTagPrice(item.getRtlPrc().toString());
			ordGodSDO.setSellPrice(item.getSaleUntPrc().toString());
			
			ordGodSDO.setQty("1");
			
			ordGodSDO.setActualAmt(item.getPayExchgRtCrncyUntPrc().toString());
			
			if(OrderEnum.CREDT_CARD_PAY.toString().equals(item.getPayMainMnCd())){
				ordGodSDO.setCardAmt(item.getPayExchgRtCrncyUntPrc().toString());
				ordGodSDO.setCashAmt("0");
			}else{
				ordGodSDO.setCardAmt("0");
				ordGodSDO.setCashAmt(item.getPayExchgRtCrncyUntPrc().toString());
			}
			
			BigDecimal saleDisAmt = BigDecimal.ZERO.add(item.getRtlPrc()).subtract(item.getSaleUntPrc());
			ordGodSDO.setSaleDisAmt(saleDisAmt.toString());
			
			ordGodSDO.setUsePoint(item.getUnityPntUseUntPrc().toString());
			
			ordGodSDO.setIssueNo(item.getIssueNo());
			
			ordGodSDO.setIssueNoAmt(item.getGodCpnDcUntPrc().add(item.getBskCpnDcUntPrc()).toString());

			//기타할인 금액 = 쿠폰할인금액 + 웹포인트사용액 + 이벤트할인금액(묶음할인 + 교차할인)
			BigDecimal etcDisAmt = BigDecimal.ZERO
												.add(item.getGodCpnDcUntPrc())
												.add(item.getBskCpnDcUntPrc())
												.add(item.getWebpntUseUntPrc())
												.add(item.getBundleDcUntPrc())
												.add(item.getCrsDcUntPrc());						
			//대량 주문일 경우 ETCDIS_AMT에 세일할인 포함
			if("LAG_QTY_ORD".equals(item.getOrdTpCd())){
				etcDisAmt = etcDisAmt.add(saleDisAmt);	
			}
			
			ordGodSDO.setEtcDisAmt(etcDisAmt.toString());
			
			ordGodSDO.setCid(item.getErpMbrSeqNo());
			
			ordGodSDO.setGetPoint(item.getUnityPntAccmlUntPrc().toString());
			
			ordGodSDO.setRegDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			
			ordGodSDO.setRealMileYn(item.getRealMileYn());
			
			ordGodSDO.setEmpNo(item.getErpEmpMbrNo());
			
			if(StringService.isEmpty(dto.getClmNo())){
				if(item.getUnityPntUseUntPrc().intValue()>0){
					if("VIRTL_BNK_ACCT_PAY".equals(item.getPayMainMnCd())){
						ordGodSDO.setTmprMileDdctYn("Y");
					}else if("SHOP_PKUP_ORD".equals(item.getOrdTpCd())){ // 픽업주문
						ordGodSDO.setTmprMileDdctYn("N");
					}else if("Y".equals(item.getTmprMileDdctYn())){
						ordGodSDO.setTmprMileDdctYn("Y");
					}else{
						ordGodSDO.setTmprMileDdctYn("N");
					}
				}else{
					ordGodSDO.setTmprMileDdctYn("N");
				}
			}else{
				ordGodSDO.setTmprMileDdctYn("N");
			}
			
			/**
		     * 구매량 타입 (B: VIP 대량구매, S:일반구매)
		     */
			if(StringUtils.isNullOrEmpty(item.getQuantityType())){
				ordGodSDO.setQuantityType("S");
			}else{
				ordGodSDO.setQuantityType(item.getQuantityType());
			}

			/**
			 * 클레임유형
			 */
			if(OrderClaimEnum.ClmTpEnum.ALL_CNCL.toString().equals(item.getClmTpCd())
					|| OrderClaimEnum.ClmTpEnum.PART_CNCL.toString().equals(item.getClmTpCd())) {
				ordGodSDO.setClmType("CANCEL");
			} else if(OrderClaimEnum.ClmTpEnum.RTGOD.toString().equals(item.getClmTpCd()) ||
					OrderClaimEnum.ClmTpEnum.GNRL_EXCHG.toString().equals(item.getClmTpCd())) {
				ordGodSDO.setClmType("RETURN");
			}
			
			/**
			 * RT처리를 위한 배송(수령)매장 코드
			 */
			if(StringService.isEmpty(dto.getAssignDlvShopId())) {
				
				if("30004".equals(item.getErpShopId()) == false && "510".equals(item.getErpShopId()) == false 
						&& "50002".equals(item.getErpShopId()) == false && "30003".equals(item.getErpShopId()) == false && "CANCEL".equals(ordGodSDO.getClmType())) { // 매장배송의 매출취소인경우
					ordGodSDO.setDeliShopcode(item.getErpShopId());
				} else if ("SHOP_RTGOD".equals(item.getRtrvlDrctTpCd())) {	// 매장반품일 경우
					ordGodSDO.setDeliShopcode(item.getErpShopId());
				} else if("SHOP_PKUP".equals(item.getDlivyDrctTpCd()) // 픽업 주문시
						&& StringUtils.isNullOrEmpty(ordGodSDO.getClmType()))
				{
					ordGodSDO.setDeliShopcode(item.getErpShopId());
				}
				
			} else {
				// 배정배치 수령매장코드.
				ordGodSDO.setDeliShopcode(dto.getAssignDlvShopId());
			}

			ordGodSDO.setPayType(item.getPayMainMnCd());
			
			ordList.add(ordGodSDO);
		}
		sdo.setOrdList(ordList);
		
		if(StringService.isEmpty(dto.getAssignDlvShopId()))
		{
			try{
				String result = orderAdapter.orderPaymentInfo(sdo,adapterHeader);
				OrderResultDTO resultDTO = OrderResultDTO.fromJSON(result, OrderResultDTO.class);
				
				log.debug("########################interface connect result msg ##################################");
				log.debug("#######################################################################################");
				log.debug("#######################################################################################");
				log.debug("#######################################################################################");
				
				log.debug("interface result msg {}", resultDTO.toJSON());
				
				log.debug("#######################################################################################");
				log.debug("#######################################################################################");
				log.debug("#######################################################################################");
				log.debug("########################interface connect result msg ##################################");
				
				if(!"200".equals(resultDTO.getRESPONSE_CD())){
					throw new OrderInterfaceFailException("ord.error.interface.error");
				}
				
			}catch(Exception e){
				log.error(e.getMessage(),e);
				throw new OrderInterfaceFailException("ord.error.interface.error"); 
			}
		}
		
		return ordList;
	}
    
	/**
	 * 마일리지 임시처리
	 * 
	 * @param dto
	 * @return
	 */
	public List<OrderUseTempMileageListSDO> sendMileageTempData(OrderParamDTO dto) {
		
		SystemPK systemPK = getIdGenService().getAutoGeneratorSystemPK(ContextService.getCurrentRequest());
		
		AdapterHeader adapterHeader = new AdapterHeader();
		
		adapterHeader.setRequestId(interfaceApiCommon.getRequestId());
		adapterHeader.setMallId(systemPK.getMall());
		adapterHeader.setLangCd("KOR");
		adapterHeader.setDvcCd(systemPK.getDevice());
		
		return this.sendMileageTempData(dto, adapterHeader);
	}
	
	/**
	 * 마일리지 임시처리
	 * 
	 * @param dto
	 * @return
	 */
	public List<OrderUseTempMileageListSDO> sendMileageTempData(OrderParamDTO dto, AdapterHeader adapterHeader) {
		
		List<InfOrderSalesDTO> salesList = orderSelectRepository.selectMileageTempDataList(dto);
		
		if(salesList.isEmpty()){
			if(dto.getUnAssignedOrderYn()!=null&&"Y".equals(dto.getUnAssignedOrderYn())){
				return null;
			}else{
				throw new OrderInterfaceFailException("ord.error.interface.not.result");
			}
		}
		
		OrderUseTempMileageSDO sdo = new OrderUseTempMileageSDO();
		List<OrderUseTempMileageListSDO> ordList = new ArrayList<OrderUseTempMileageListSDO>();
		
		for(InfOrderSalesDTO item:salesList){
			OrderUseTempMileageListSDO ordGodSDO = new OrderUseTempMileageListSDO();

			ordGodSDO.setOrdNo(item.getOrdNo());
			ordGodSDO.setOrdDtlNo(item.getQtyTurn().toString());
			
			
			/**
			 * (X : 디스커버리, M:MLB, I:MLB Kids)
			 *     /**
		     * 매장코드 (MLB : 510 , MLB KIDS : 50002, DX : 30004 (VIP의 경우 RT처리) )
		     */
			if(StringUtils.isNullOrEmpty(dto.getBrandCode())){
				if(StringUtils.isNullOrEmpty(item.getBrand())) {
					ordGodSDO.setBrand("X");
				} else {
					ordGodSDO.setBrand(item.getBrand());
				}				
			}else{
				throw new OrderInterfaceFailException("ord.error.interface.param.error"); 
			}
			
			ordGodSDO.setCid(item.getErpMbrSeqNo());
			
			ordGodSDO.setTempMileAmt(item.getUnityPntUseUntPrc().toString());
			
			if(StringService.isNotEmpty(dto.getClmNo())){
				ordGodSDO.setTempSectCd("00"); // 00차감 01증가 반대로
			} else {
				ordGodSDO.setTempSectCd("01"); // 00차감 01증가 반대로
			}
			/**
		     * 임시처리 유형 (00:사용마일리지, 01:적립마일리지)
		     */
			ordGodSDO.setTempType("00");
			
			ordList.add(ordGodSDO);
		}
		sdo.setOrdList(ordList);
		
		if(StringService.isEmpty(dto.getAssignDlvShopId()))
		{
			try{
				
				
				
				String result = orderAdapter.useTempMemberMileage(sdo,adapterHeader);
				OrderResultDTO resultDTO = OrderResultDTO.fromJSON(result, OrderResultDTO.class);
				
				log.debug("########################interface connect result msg useTempMemberMileage ##################################");
				log.debug("#######################################################################################");
				log.debug("#######################################################################################");
				log.debug("#######################################################################################");

				
				log.debug("interface result msg useTempMemberMileage {}", resultDTO.toJSON());
				
				log.debug("#######################################################################################");
				log.debug("#######################################################################################");
				log.debug("#######################################################################################");
				log.debug("########################interface connect result msg useTempMemberMileage ##################################");
				
				if(!"200".equals(resultDTO.getRESPONSE_CD())){
					throw new OrderInterfaceFailException("ord.error.interface.error");
				}
				
			}catch(Exception e){
				log.error(e.getMessage(),e);
				throw new OrderInterfaceFailException("ord.error.interface.error"); 
			}
		}
		
		return ordList;
	}
	
	
	public void useOnOffCpn(List<String> cpnList,String cid,String ordNo) {
		SystemPK systemPK = getIdGenService().getAutoGeneratorSystemPK(ContextService.getCurrentRequest());
		AdapterHeader adapterHeader = new AdapterHeader();
		adapterHeader.setRequestId(interfaceApiCommon.getRequestId());
		adapterHeader.setMallId(systemPK.getMall());
		adapterHeader.setLangCd("KOR");
		adapterHeader.setDvcCd(systemPK.getDevice());
		
		for(String cpnStr:cpnList){
			
			MbrIssuCpn mbrIssuCpn = new MbrIssuCpn();
			mbrIssuCpn.setOrdNo(ordNo);
			mbrIssuCpn.setCpnCrtfcCd(cpnStr);
			
			OrdGodAplPrmExtends target = orderSelectRepository.selectAppliedyOnOffCouponAmount(mbrIssuCpn); 
			
			String shopCode = "";			
			if("DXM".equals(target.getMallId())){
				shopCode = "30004";
			}else if("MBM".equals(target.getMallId())){
				shopCode = "510";
			}else if("SAM".equals(target.getMallId())){
				shopCode = "30003";
			}else{
				shopCode = "30004";
			}
			
			OnOffCouponUseSDO sdo = new OnOffCouponUseSDO();
	    	sdo.setCid(cid);
	    	sdo.setIssueNo(cpnStr);
	    	sdo.setOrdNo(ordNo);
	    	sdo.setShopcode(shopCode);
	    	sdo.setSpdAmount(String.valueOf(target.getAplAmt()));
			
			InterfaceSDO result = promotionAdapter.useOnOffCoupon(sdo, adapterHeader);
			if(!"200".equals(result.getResponseCd())){
				log.error(result.getResponseData());
				throw new OrderInterfaceFailException("ord.error.interface.error");
			}
		}
	}
	
	public void restoreOnOffCpn(List<String> cpnList,String cid,String ordNo) {
		SystemPK systemPK = getIdGenService().getAutoGeneratorSystemPK(ContextService.getCurrentRequest());
		AdapterHeader adapterHeader = new AdapterHeader();
		adapterHeader.setRequestId(interfaceApiCommon.getRequestId());
		adapterHeader.setMallId(systemPK.getMall());
		adapterHeader.setLangCd("KOR");
		adapterHeader.setDvcCd(systemPK.getDevice());
		
		for(String cpnStr:cpnList){
			
			MbrIssuCpn mbrIssuCpn = new MbrIssuCpn();
			mbrIssuCpn.setOrdNo(ordNo);
			mbrIssuCpn.setCpnCrtfcCd(cpnStr);
									
			OrdGodAplPrmExtends target = orderSelectRepository.selectAppliedyOnOffCouponAmount(mbrIssuCpn); 
			
			String shopCode = "";			
			if("DXM".equals(target.getMallId())){
				shopCode = "30004";
			}else if("MBM".equals(target.getMallId())){
				shopCode = "510";
			}else if("SAM".equals(target.getMallId())){
				shopCode = "30003";
			}else{
				shopCode = "30004";
			}
			
			OnOffCouponUseSDO sdo = new OnOffCouponUseSDO();
	    	sdo.setCid(cid);
	    	sdo.setIssueNo(cpnStr);
	    	sdo.setOrdNo(ordNo);
	    	sdo.setClmNo(ordNo);
	    	sdo.setShopcode(shopCode);
	    	sdo.setSpdAmount(String.valueOf(target.getAplAmt()));			
									
			InterfaceSDO result = promotionAdapter.restoreOnOffCoupon(sdo, adapterHeader);			

			if(!"200".equals(result.getResponseCd())){
				log.error(result.getResponseData());
				throw new OrderInterfaceFailException("ord.error.interface.error");
			}
		}
	}
}
