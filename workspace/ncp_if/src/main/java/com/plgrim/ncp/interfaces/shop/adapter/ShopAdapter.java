package com.plgrim.ncp.interfaces.shop.adapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plgrim.ncp.interfaces.abstracts.AbstractAdapter;
import com.plgrim.ncp.interfaces.util.AdapterHeader;
import com.plgrim.ncp.interfaces.util.InterfaceApiCommon;
import com.plgrim.ncp.interfaces.util.InterfaceConstants;

/** Copyright (c) 2018 plgrim, Inc.
 * All right reserved.
 * http://plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 *
 * ------------------------------------------------------------------------
 *
 * Description	:	매장정보  내부용 Interface Adapter
 *
 * @Author 	:	muba
 * @Date   	:	2018. 7. 27.
 * @Version	:	
 *
 */
@Service
public class ShopAdapter extends AbstractAdapter{
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
     *  I/F 매장정보 가져오기 	
     * 
     * @param 
     * @param adapterHeader
     * @return
     */
    public String getShopList(final AdapterHeader adapterHeader) {
    	String serverUrl = interfaceApiCommon.getInternalInterfaceServerUrl() + InterfaceConstants.IF_SST_01;	
    	return this.sendInterface("", adapterHeader, serverUrl); 
    }
    
}
