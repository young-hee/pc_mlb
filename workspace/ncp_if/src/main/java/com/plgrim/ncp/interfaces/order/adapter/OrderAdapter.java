package com.plgrim.ncp.interfaces.order.adapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plgrim.ncp.interfaces.abstracts.AbstractAdapter;
import com.plgrim.ncp.interfaces.order.data.OrderFormerlyPurchaseCfmInfoSDO;
import com.plgrim.ncp.interfaces.order.data.OrderFormerlyPurchaseSDO;
import com.plgrim.ncp.interfaces.order.data.OrderOfflinePurchaseSDO;
import com.plgrim.ncp.interfaces.order.data.OrderOnlineShopRtSDO;
import com.plgrim.ncp.interfaces.order.data.OrderPaymentInfoSDO;
import com.plgrim.ncp.interfaces.order.data.OrderSendErpInvStockSDO;
import com.plgrim.ncp.interfaces.order.data.OrderUseTempMileageSDO;
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
 * <li> [기능1]
 * <li> [기능2]
 * </ul>.
 *
 * @author tktaeki.kim
 */
@Service
@Slf4j
public class OrderAdapter  extends AbstractAdapter {
	
	@Autowired
	private InterfaceApiCommon interfaceApiCommon;
	
	/**
	 * 온라인 RT 처리. (매장 -> 온라인 재고 이동)
	 * 
	 * @param inputJson
	 * @param adapterHeader
	 * @param serverUrl
	 * @return
	 */
	public String onlineRTSend(final OrderOnlineShopRtSDO orderOnlineRtSendSDO,  final AdapterHeader adapterHeader) 
			throws Exception {
		
		log.info("{}", this.getClass().getName() + ".onlineRTSend");

		final String serverUrl = this.interfaceApiCommon.getInternalInterfaceServerUrl() + InterfaceConstants.ORDER_ONLINE_RT_SEND;
        return this.sendInterface(orderOnlineRtSendSDO, adapterHeader, serverUrl);
	}
	
	/**
	 * 온라인 RT 취소처리. (매장 -> 온라인 재고 이동 취소 )
	 * 
	 * @param inputJson
	 * @param adapterHeader
	 * @param serverUrl
	 * @return
	 */
	public String cancelSendRT(final OrderOnlineShopRtSDO orderOnlineRtSendSDO,  final AdapterHeader adapterHeader) 
			throws Exception {
		
		log.info("{}", this.getClass().getName() + ".cancelSendRT");

		final String serverUrl = this.interfaceApiCommon.getInternalInterfaceServerUrl() + InterfaceConstants.ORDER_ONLINE_RT_CANCLE;
        return this.sendInterface(orderOnlineRtSendSDO, adapterHeader, serverUrl);
	}
	
	/**
	 * 매장 픽업/출고 확정처리.
	 * 
	 * @param inputJson
	 * @param adapterHeader
	 * @param serverUrl
	 * @return
	 */
	public String storeDeliConfirm(final OrderOnlineShopRtSDO orderOnlineRtSendSDO,  final AdapterHeader adapterHeader) 
			throws Exception {
		
		log.info("{}", this.getClass().getName() + ".storeDeliConfirm");

		final String serverUrl = this.interfaceApiCommon.getInternalInterfaceServerUrl() + InterfaceConstants.ORDER_ONLINE_RT_RECEIVE;
        return this.sendInterface(orderOnlineRtSendSDO, adapterHeader, serverUrl);
	}
	
	/**
	 * 매장반품 RT 처리. (온라인 -> 매장 재고 이동 즉시 확정)
	 * 
	 * @param inputJson
	 * @param adapterHeader
	 * @param serverUrl
	 * @return
	 */
	public String shopReturnRT(final OrderOnlineShopRtSDO orderOnlineRtSendSDO,  final AdapterHeader adapterHeader) 
			throws Exception {
		
		log.info("{}", this.getClass().getName() + ".shopReturnRT");

		final String serverUrl = this.interfaceApiCommon.getInternalInterfaceServerUrl() + InterfaceConstants.ORDER_SHOP_RETURN_RT_SEND;
        return this.sendInterface(orderOnlineRtSendSDO, adapterHeader, serverUrl);
	}
	
	/**
	 * 온라인 매출 송신. (온라인결제정보 전송)
	 * 
	 * @param inputJson
	 * @param adapterHeader
	 * @param serverUrl
	 * @return
	 */
	public String orderPaymentInfo(final OrderPaymentInfoSDO orderPaymentInfoSDO,  final AdapterHeader adapterHeader) 
			throws Exception {
		
		log.info("{}", this.getClass().getName() + ".orderPaymentInfo");

		final String serverUrl = this.interfaceApiCommon.getInternalInterfaceServerUrl() + InterfaceConstants.ORDER_PAYMEMT_INFO_SEND;
        return this.sendInterface(orderPaymentInfoSDO, adapterHeader, serverUrl);
	}
	
	/**
	 * 주문 마일리지 임시처리. (임시 마일리지 사용/적립 처리)
	 * 
	 * @param inputJson
	 * @param adapterHeader
	 * @param serverUrl
	 * @return
	 */
	public String useTempMemberMileage(final OrderUseTempMileageSDO orderUseTempMileageSDO,  final AdapterHeader adapterHeader) 
			throws Exception {
		
		log.info("{}", this.getClass().getName() + ".useTempMemberMileage");

		final String serverUrl = this.interfaceApiCommon.getInternalInterfaceServerUrl() + InterfaceConstants.ORDER_USE_TEMP_MEMBER_MILEAGE;
        return this.sendInterface(orderUseTempMileageSDO, adapterHeader, serverUrl);
	}
	
	/**
	 * 오프라인 구매내역 조회. (오프라인 구매내역을 기간별로 조회)
	 * 
	 * @param inputJson
	 * @param adapterHeader
	 * @param serverUrl
	 * @return
	 */
	public String offlinePurchaseList(final OrderOfflinePurchaseSDO orderOfflinePurchaseSDO,  final AdapterHeader adapterHeader) 
			throws Exception {
		
		log.info("{}", this.getClass().getName() + ".offlinePurchaseList");

		final String serverUrl = this.interfaceApiCommon.getInternalInterfaceServerUrl() + InterfaceConstants.ORDER_OFFlINT_PURCHASE_LIST;
        return this.sendInterface(orderOfflinePurchaseSDO, adapterHeader, serverUrl);
	}
	
	/**
	 * 이전주문 구매내역 조회. (이전주문 내역을 기간별로 조회)
	 * 
	 * @param inputJson
	 * @param adapterHeader
	 * @param serverUrl
	 * @return
	 */
	public String formerlyPurchaseList(final OrderFormerlyPurchaseSDO orderFormerlyPurchaseSDO,  final AdapterHeader adapterHeader) 
			throws Exception {
		
		log.info("{}", this.getClass().getName() + ".formerlyPurchaseList");

		final String serverUrl = this.interfaceApiCommon.getInternalInterfaceServerUrl() + InterfaceConstants.ORDER_FORMERLY_PURCHASE_LIST;
        return this.sendInterface(orderFormerlyPurchaseSDO, adapterHeader, serverUrl);
	}
	
	/**
	 * 본사재고 주문 수량 송신. (본사재고 사용량을 송신)
	 * 
	 * @param inputJson
	 * @param adapterHeader
	 * @param serverUrl
	 * @return
	 */
	public String sendOrderErpInvStock(final OrderSendErpInvStockSDO orderSendErpInvStockSDO,  final AdapterHeader adapterHeader) 
			throws Exception {
		
		log.info("{}", this.getClass().getName() + ".sendOrderErpInvStock");

		final String serverUrl = this.interfaceApiCommon.getInternalInterfaceServerUrl() + InterfaceConstants.ORDER_SEND_ERP_INV_STOCK;
        return this.sendInterface(orderSendErpInvStockSDO, adapterHeader, serverUrl);
	}
	
	/**
	 * 이전주문 구매확정여부 조회. (회원의 이전주문 구매확정여부 조회를 ERP 시스템에 요청한다.)
	 * 
	 * @param inputJson
	 * @param adapterHeader
	 * @param serverUrl
	 * @return
	 */
	public String formerlyPurchaseConfirmInfo(final OrderFormerlyPurchaseCfmInfoSDO orderFormerlyPurchaseCfmInfoSDO,  final AdapterHeader adapterHeader) 
			throws Exception {
		
		log.info("{}", this.getClass().getName() + ".formerlyPurchaseConfirmInfo");

		final String serverUrl = this.interfaceApiCommon.getInternalInterfaceServerUrl() + InterfaceConstants.ORDER_FORMERLY_PURCHASE_CONFIRM_INFO;
        return this.sendInterface(orderFormerlyPurchaseCfmInfoSDO, adapterHeader, serverUrl);
	}
}
