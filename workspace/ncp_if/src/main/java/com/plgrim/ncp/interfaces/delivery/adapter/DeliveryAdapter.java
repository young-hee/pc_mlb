
/* Copyright (c) 2015 plgrim, Inc.
 * All right reserved.
 * http://plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 *
 * ------------------------------------------------------------------------
 * @author      js279.kim
 * @since       2015. 5. 21       
 */
package com.plgrim.ncp.interfaces.delivery.adapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plgrim.ncp.interfaces.abstracts.AbstractAdapter;
import com.plgrim.ncp.interfaces.delivery.data.DlivyCjSDO;
import com.plgrim.ncp.interfaces.delivery.data.DlivyWmsSDO;
import com.plgrim.ncp.interfaces.util.AdapterHeader;
import com.plgrim.ncp.interfaces.util.InterfaceApiCommon;
import com.plgrim.ncp.interfaces.util.InterfaceConstants;

import lombok.extern.slf4j.Slf4j;


/**
 * WMS와 연동한다.
 * 
 * <p>
 * 
 * <ul>
 *   <li> [기능1]
 *   <li> [기능2]
 * </ul>.
 *
 */
@Service
@Slf4j
public class DeliveryAdapter extends AbstractAdapter { 

	@Autowired
	private InterfaceApiCommon interfaceApiCommon;

	/**
	 * WMS 출고지시
	 * @param parameter
	 * @param adapterHeader
	 * @return
	 * @throws Exception
	 */
	public String sendWMSDlivyDrct(final DlivyWmsSDO parameter,  final AdapterHeader adapterHeader) 
			throws Exception {
		final String serverUrl = this.interfaceApiCommon.getInternalInterfaceServerUrl() + InterfaceConstants.DELIVERY_SEND_WMS_DLIVY_DRCT;
        return this.sendInterface(parameter, adapterHeader, serverUrl);
	}
	
	/**
	 * WMS 입고 예정 전송
	 * @param parameter
	 * @param adapterHeader
	 * @return
	 * @throws Exception
	 */
	public String sendWMSRetrieval(final AdapterHeader adapterHeader) 
			throws Exception {
		final String serverUrl = this.interfaceApiCommon.getInternalInterfaceServerUrl() + InterfaceConstants.DELIVERY_SEND_WMS_RETRIEVAL;
		return this.sendInterface("", adapterHeader, serverUrl);
	}
	public String sendWMSRetrievalWithdraw(final AdapterHeader adapterHeader,final String inputJson) 
			throws Exception {
		final String serverUrl = this.interfaceApiCommon.getInternalInterfaceServerUrl() + InterfaceConstants.DELIVERY_SEND_WMS_RETRIEVAL_WITHDRAW;
		return this.sendInterface(inputJson, adapterHeader, serverUrl);
	}
	
	/**
	 * 우체국 회수 요청
	 * @param parameter
	 * @param adapterHeader
	 * @return
	 * @throws Exception
	 */
	public String sendEpostRetrieval(final AdapterHeader adapterHeader) 
			throws Exception {
		final String serverUrl = this.interfaceApiCommon.getExternalInterfaceServerUrl() + InterfaceConstants.DELIVERY_SEND_EPOST_RETRIEVAL;
		return this.sendInterface("", adapterHeader, serverUrl);
	}

	/**
	 * CJ 연동
	 * @param parameter
	 * @param adapterHeader
	 * @return
	 * @throws Exception
	 */
	public String sendCJList(final DlivyCjSDO parameter,  final AdapterHeader adapterHeader) 
			throws Exception {
		final String serverUrl = this.interfaceApiCommon.getInternalInterfaceServerUrl() + InterfaceConstants.DELIVERY_SEND_CJ_LIST;
        return this.sendInterface(parameter, adapterHeader, serverUrl);
	}
	
	/**
	 * CJ 연동
	 * @param parameter
	 * @param adapterHeader
	 * @return
	 * @throws Exception
	 */
	public String sendRdcCJList(final DlivyCjSDO parameter,  final AdapterHeader adapterHeader) 
			throws Exception {
		final String serverUrl = this.interfaceApiCommon.getInternalInterfaceServerUrl() + InterfaceConstants.DELIVERY_SEND_CJ_RDC_LIST;
		return this.sendInterface(parameter, adapterHeader, serverUrl);
	}
	
	/**
	 * CJ 배송완료 수신.
	 * @param parameter
	 * @param adapterHeader
	 * @return
	 * @throws Exception
	 */
	public String getCjDlvStatus(final DlivyCjSDO parameter,  final AdapterHeader adapterHeader) 
			throws Exception {
		final String serverUrl = this.interfaceApiCommon.getInternalInterfaceServerUrl() + InterfaceConstants.DELIVERY_GET_CJ_DLV_STATUS;
		return this.sendInterface(parameter, adapterHeader, serverUrl);
	}
	
	/**
	 * CJ 회수송장 수신.
	 * @param parameter
	 * @param adapterHeader
	 * @return
	 * @throws Exception
	 */
	public String getCjRetrievalDmstcWaybilNo(final DlivyCjSDO parameter,  final AdapterHeader adapterHeader) 
			throws Exception {
		final String serverUrl = this.interfaceApiCommon.getInternalInterfaceServerUrl() + InterfaceConstants.DELIVERY_GET_CJ_RDC_DMSTC_WAYBIL_NO;
		return this.sendInterface(parameter, adapterHeader, serverUrl);
	}
}
