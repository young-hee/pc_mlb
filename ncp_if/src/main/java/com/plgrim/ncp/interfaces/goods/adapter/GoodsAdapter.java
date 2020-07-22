package com.plgrim.ncp.interfaces.goods.adapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plgrim.ncp.framework.commons.JsonService;
import com.plgrim.ncp.interfaces.abstracts.AbstractAdapter;
import com.plgrim.ncp.interfaces.goods.data.GoodsListSDO;
import com.plgrim.ncp.interfaces.goods.data.InfFacebookRequestSDO;
import com.plgrim.ncp.interfaces.goods.data.InfGodTargetSDO;
import com.plgrim.ncp.interfaces.goods.data.MonolabsSDO;
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
 * Description	:	상품 내부용 Interface Adapter
 *
 * @Author 	:	neps
 * @Date   	:	2018. 6. 28.
 * @Version	:	
 *
 */
@Service
public class GoodsAdapter extends AbstractAdapter{
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
     * 상품 I/F 대상 카운터 개수 가져오기
     * 	- PRICE(ERP 할인가) / QTY(재고)
     * 
     * @param infGodTargetSDO
     * @param adapterHeader
     * @return
     */
    public InfGodTargetSDO getGoodsTargetCount(final InfGodTargetSDO infGodTargetSDO, final AdapterHeader adapterHeader) {
    	String serverUrl = interfaceApiCommon.getInternalInterfaceServerUrl() + InterfaceConstants.GOODS_TARGET_COUNT;	
    	return this.sendInterface(infGodTargetSDO, adapterHeader, serverUrl, InfGodTargetSDO.class);
    }
     
    /**
     * 상품 ERP 할인가 가져오기
     * 
     * @param infGodTargetSDO
     * @param adapterHeader
     * @return
     */
    public GoodsListSDO getGoodsPrcInformation(final InfGodTargetSDO infGodTargetSDO, final AdapterHeader adapterHeader) {
        String serverUrl = interfaceApiCommon.getInternalInterfaceServerUrl() + InterfaceConstants.GOODS_PRC_INFORMATION;
        return this.sendInterface(infGodTargetSDO, adapterHeader, serverUrl, GoodsListSDO.class);
    }
    
    /**
     * 상품 ERP 재고 가져오기
     * 
     * @param infGodTargetSDO
     * @param adapterHeader
     * @return
     */
    public GoodsListSDO getGoodsItmInvInformation(final InfGodTargetSDO infGodTargetSDO, final AdapterHeader adapterHeader) {
        String serverUrl = interfaceApiCommon.getInternalInterfaceServerUrl() + InterfaceConstants.GOODS_ITMINV_INFORMATION;
        return this.sendInterface(infGodTargetSDO, adapterHeader, serverUrl, GoodsListSDO.class);
    }

    /**
     * 상품 ERP 실시간 재고 가져오기
     * 
     * @param goodsListSDO
     * @param adapterHeader
     * @return
     */
    public GoodsListSDO getRltmGodInvInformation(final InfGodTargetSDO infGodTargetSDO, final AdapterHeader adapterHeader) {
        String serverUrl = interfaceApiCommon.getInternalInterfaceServerUrl() + InterfaceConstants.RLTM_GODINVL_INFORMATION;
        return this.sendInterface(infGodTargetSDO, adapterHeader, serverUrl, GoodsListSDO.class);
    }
    
    /**
     * 상품 이미지링크 송신
     * 
     * @param goodsListSDO
     * @param adapterHeader
     * @return
     */
    public GoodsListSDO sendGoodsImageUrl(final InfGodTargetSDO infGodTargetSDO, final AdapterHeader adapterHeader) {
        String serverUrl = interfaceApiCommon.getInternalInterfaceServerUrl() + InterfaceConstants.SEND_GOODS_IMAGE_URL;
        return this.sendInterface(infGodTargetSDO, adapterHeader, serverUrl, GoodsListSDO.class);
    }
    
    /**
     * 페이스북 상품 연동
     * - 상품등록
     * 
     * @param 
     * @param 
     * @return
     */
    public InfFacebookRequestSDO sendNewGoodsToFacebook(InfFacebookRequestSDO sdo, final AdapterHeader adapterHeader) {
    	String inputJson = JsonService.marshalling(sdo);
    	String serverUrl = interfaceApiCommon.getExternalInterfaceServerUrl() + InterfaceConstants.FACEBOOK_SEND_GOD;
    	return this.sendInterface(inputJson, adapterHeader, serverUrl, InfFacebookRequestSDO.class);
    }

    /**
     * 페이스북 상품 연동
     * - 상품수정 전송
     * 
     * @param 
     * @param 
     * @return
     */
    public InfFacebookRequestSDO sendUpdateGoodsToFacebook(InfFacebookRequestSDO sdo, final AdapterHeader adapterHeader) {
    	String inputJson = JsonService.marshalling(sdo);
    	String serverUrl = interfaceApiCommon.getExternalInterfaceServerUrl() + InterfaceConstants.FACEBOOK_UPDATE_GOD;
    	return this.sendInterface(inputJson, adapterHeader, serverUrl, InfFacebookRequestSDO.class);
    }
    
    
    public MonolabsSDO sendLicense(MonolabsSDO sdo, final AdapterHeader adapterHeader) {
    	String inputJson = JsonService.marshalling(sdo);
    	String serverUrl = interfaceApiCommon.getExternalInterfaceServerUrl() + InterfaceConstants.MLB_MONOLABS_LICENSE;
    	return this.sendInterface(inputJson, adapterHeader, serverUrl, MonolabsSDO.class);
    }
}
