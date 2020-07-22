package com.plgrim.ncp.interfaces.goods.adapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plgrim.ncp.framework.commons.JsonService;
import com.plgrim.ncp.interfaces.abstracts.AbstractAdapter;
import com.plgrim.ncp.interfaces.goods.data.OlapicFTPTransferSDO;
import com.plgrim.ncp.interfaces.util.AdapterHeader;
import com.plgrim.ncp.interfaces.util.InterfaceApiCommon;
import com.plgrim.ncp.interfaces.util.InterfaceConstants;

@Service
public class OlapicAdapter extends AbstractAdapter {
	/*
     * ---------------------------------------------------------------------
     * Instance fields.
     * ---------------------------------------------------------------------
     */

    @Autowired
    private InterfaceApiCommon interfaceApiCommon;

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
     *  FTP 전송요청
     * 
     * @param 
     * @param adapterHeader
     * @return
     */
    public String trans(OlapicFTPTransferSDO sdo, final AdapterHeader adapterHeader) {
    	String inputJson = JsonService.marshalling(sdo);
    	String serverUrl = interfaceApiCommon.getExternalInterfaceServerUrl() + InterfaceConstants.OLAPIC_TRANS;
    	return this.sendInterface(inputJson, adapterHeader, serverUrl); 
    }
}
