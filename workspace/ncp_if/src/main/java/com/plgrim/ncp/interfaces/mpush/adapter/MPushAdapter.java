package com.plgrim.ncp.interfaces.mpush.adapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.plgrim.ncp.framework.commons.ConfigService;
import com.plgrim.ncp.framework.commons.StringService;
import com.plgrim.ncp.framework.data.SystemPK;
import com.plgrim.ncp.interfaces.abstracts.AbstractAdapter;
import com.plgrim.ncp.interfaces.mpush.data.MPushAlimTalkSDO;
import com.plgrim.ncp.interfaces.mpush.data.MPushSmsMmsSDO;
import com.plgrim.ncp.interfaces.util.AdapterHeader;
import com.plgrim.ncp.interfaces.util.InterfaceApiCommon;
import com.plgrim.ncp.interfaces.util.InterfaceConstants;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MPushAdapter extends AbstractAdapter {

    /*
     * ---------------------------------------------------------------------
     * Instance fields.
     * ---------------------------------------------------------------------
     */
    @Autowired
    private InterfaceApiCommon interfaceApiCommon;
    
    @Value("${ncp_base.interface.internal.server.url}")
    private String internalInterfaceServerUrl;
    
    @Autowired
    private ConfigService configService;
    
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
     * ALIMTALK을 전송하는 메서드이다.
     * <p>
     * 성공시 SUCCESS 반환
     *
     * @param mPushAlimTalkSDO [설명]
     * @param systemPK    [설명]
     * @return String [설명]
     * @ the exception
     */
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRES_NEW)
    public String sendAlimTalk(final MPushAlimTalkSDO mPushAlimTalkSDO, final SystemPK systemPK) {
        return this.sendAlimTalk(mPushAlimTalkSDO, getAdapterHeader(systemPK));
    }


    /**
     * ALIMTALK을 전송하는 메서드이다.
     * <p>
     * 성공시 SUCCESS 반환
     *
     * @param mPushAlimTalkSDO [설명]
     * @param adapterHeader    [설명]
     * @return String [설명]
     * @ the exception
     */
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRES_NEW)
    public String sendAlimTalk(final MPushAlimTalkSDO mPushAlimTalkSDO, final AdapterHeader adapterHeader) {
        String serverUrl = this.interfaceApiCommon.getInternalInterfaceServerUrl() + InterfaceConstants.SEND_ALIMTALK;

        String result = "";
        
        if (StringService.isEmpty(mPushAlimTalkSDO.getCallerId())
                || StringService.isEmpty(mPushAlimTalkSDO.getMallId())
                || StringService.isEmpty(mPushAlimTalkSDO.getMbrNo())
                || StringService.isEmpty(mPushAlimTalkSDO.getReceiveMobileNo())) {
        	result = "PARAMETER_ERROR";
        }

    	// 운영
    	if(internalInterfaceServerUrl != null && internalInterfaceServerUrl.indexOf("internal-prd") > -1) {
    		return this.sendInterface(mPushAlimTalkSDO, adapterHeader, serverUrl);
    	}
    	// 운영 이 아니면
    	else {
    		String mobileWhiteList = configService.getProperty("ncp_base.white.list.mobile");
    		if(mobileWhiteList != null && !"".equals(mobileWhiteList)) {
    			String[] mobileWhiteListArr = mobileWhiteList.split("/");
    			for(String mobile : mobileWhiteListArr) {
    				if(mPushAlimTalkSDO.getReceiveMobileNo().equals(mobile)) {
    					return this.sendInterface(mPushAlimTalkSDO, adapterHeader, serverUrl);
    				}
    			}
    		}
    	}
        
    	return result;
//        return this.sendInterface(mPushAlimTalkSDO, adapterHeader, serverUrl);
    }


    /**
     * SMS, MMS 를 전송하는 메서드이다.
     * <p>
     * 성공시 SUCCESS 반환
     *
     * @param mPushSmsMmsSDO [설명]
     * @param systemPK  [설명]
     * @return String [설명]
     * @ the exception
     */
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRES_NEW)
    public String sendSmsMms(final MPushSmsMmsSDO mPushSmsMmsSDO, final SystemPK systemPK) {
        return this.sendSmsMms(mPushSmsMmsSDO, getAdapterHeader(systemPK));
    }

    /**
     * SMS, MMS 를 전송하는 메서드이다.
     * <p>
     * 성공시 SUCCESS 반환
     *
     * @param mPushSmsMmsSDO [설명]
     * @param adapterHeader  [설명]
     * @return String [설명]
     * @ the exception
     */
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRES_NEW)
    public String sendSmsMms(final MPushSmsMmsSDO mPushSmsMmsSDO, final AdapterHeader adapterHeader) {
    	String serverUrl = interfaceApiCommon.getInternalInterfaceServerUrl() + InterfaceConstants.SEND_SMS_MMS;
    	
    	String result = "";
    	
        if (StringService.isEmpty(mPushSmsMmsSDO.getCallerId())
                || StringService.isEmpty(mPushSmsMmsSDO.getMallId())
                || StringService.isEmpty(mPushSmsMmsSDO.getMbrNo())
                || StringService.isEmpty(mPushSmsMmsSDO.getReceiveMobileNo())) {
            return "PARAMETER_ERROR";
        }

    	// 운영
    	if(internalInterfaceServerUrl != null && internalInterfaceServerUrl.indexOf("internal-prd") > -1) {
    		return this.sendInterface(mPushSmsMmsSDO, adapterHeader, serverUrl);
    	}
    	// 운영 이 아니면
    	else {
    		String mobileWhiteList = configService.getProperty("ncp_base.white.list.mobile");
    		if(mobileWhiteList != null && !"".equals(mobileWhiteList)) {
    			String[] mobileWhiteListArr = mobileWhiteList.split("/");
    			for(String mobile : mobileWhiteListArr) {
    				if(mPushSmsMmsSDO.getReceiveMobileNo().equals(mobile)) {
    					return this.sendInterface(mPushSmsMmsSDO, adapterHeader, serverUrl);
    				}
    			}
    		}
    	}
        
    	return result;
//        return this.sendInterface(mPushSmsMmsSDO, adapterHeader, serverUrl);
    }


    private AdapterHeader getAdapterHeader(SystemPK systemPK) {
        AdapterHeader adapterHeader = new AdapterHeader();
        adapterHeader.setRequestId(this.interfaceApiCommon.getRequestId());
        adapterHeader.setMallId(systemPK.getMall());
        adapterHeader.setLangCd(systemPK.getLang());
        adapterHeader.setDvcCd(systemPK.getDevice());
        return adapterHeader;
    }

}
