package com.plgrim.ncp.interfaces.crema.adapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plgrim.ncp.interfaces.abstracts.AbstractAdapter;
import com.plgrim.ncp.interfaces.crema.data.CremaSDO;
import com.plgrim.ncp.interfaces.crema.data.CremaUserDTO;
import com.plgrim.ncp.interfaces.util.AdapterHeader;
import com.plgrim.ncp.interfaces.util.InterfaceApiCommon;
import com.plgrim.ncp.interfaces.util.InterfaceConstants;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CremaAdapter extends AbstractAdapter {

	@Autowired
	private InterfaceApiCommon interfaceApiCommon;

	/** 카테고리 동기화 */
	public String sendCremaCategory(final CremaSDO parameter,  final AdapterHeader adapterHeader) 
			throws Exception {
		final String serverUrl = this.interfaceApiCommon.getExternalInterfaceServerUrl() + InterfaceConstants.CREMA_SEND_CATEGORY;
        return this.sendInterface(parameter, adapterHeader, serverUrl);
	}

	/** 상품 동기화 */
	public String sendCremaProduct(final CremaSDO parameter,  final AdapterHeader adapterHeader) 
			throws Exception {
		final String serverUrl = this.interfaceApiCommon.getExternalInterfaceServerUrl() + InterfaceConstants.CREMA_SEND_PRODUCT;
        return this.sendInterface(parameter, adapterHeader, serverUrl);
	}

	/** 주문 동기화 */
	public String sendCremaOrder(final CremaSDO parameter,  final AdapterHeader adapterHeader) 
			throws Exception {
		final String serverUrl = this.interfaceApiCommon.getExternalInterfaceServerUrl() + InterfaceConstants.CREMA_SEND_ORDER;
        return this.sendInterface(parameter, adapterHeader, serverUrl);
	}

	/** 회원 동기화 */
	public String sendCremaUser(final CremaSDO parameter,  final AdapterHeader adapterHeader) 
			throws Exception {
		final String serverUrl = this.interfaceApiCommon.getExternalInterfaceServerUrl() + InterfaceConstants.CREMA_SEND_USER;
        return this.sendInterface(parameter, adapterHeader, serverUrl);
	}

	/** 장바구니 동기화 */
	public String sendCremaCartItem(final CremaSDO parameter,  final AdapterHeader adapterHeader) 
			throws Exception {
		final String serverUrl = this.interfaceApiCommon.getExternalInterfaceServerUrl() + InterfaceConstants.CREMA_SEND_CARTITEM;
        return this.sendInterface(parameter, adapterHeader, serverUrl);
	}

	/** 오프라인 주문 동기화 */
	public String sendCremaOfflineOrder(final CremaSDO parameter,  final AdapterHeader adapterHeader) 
			throws Exception {
		final String serverUrl = this.interfaceApiCommon.getExternalInterfaceServerUrl() + InterfaceConstants.CREMA_SEND_OFF_ORDER;
		return this.sendInterface(parameter, adapterHeader, serverUrl);
	}

	/** 회원 Update Callback */
	public String updateCremaUser(final CremaUserDTO parameter,  final AdapterHeader adapterHeader) 
			throws Exception {
		final String serverUrl = this.interfaceApiCommon.getExternalInterfaceServerUrl() + InterfaceConstants.CREMA_UPDATE_USER;
        return this.sendInterface(parameter, adapterHeader, serverUrl);
	}

	/** 수신여부 Update Callback */
	public String updateRecptnAgr(final CremaUserDTO parameter,  final AdapterHeader adapterHeader) 
			throws Exception {
		final String serverUrl = this.interfaceApiCommon.getExternalInterfaceServerUrl() + InterfaceConstants.CREMA_UPDATE_RECPTN_AGR;
        return this.sendInterface(parameter, adapterHeader, serverUrl);
	}

}