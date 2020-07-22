package com.plgrim.ncp.interfaces.email.adapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.plgrim.ncp.framework.commons.ConfigService;
import com.plgrim.ncp.framework.commons.JsonService;
import com.plgrim.ncp.framework.exception.AdapterConnectException;
import com.plgrim.ncp.interfaces.abstracts.AbstractAdapter;
import com.plgrim.ncp.interfaces.email.data.EmailHtmlSDO;
import com.plgrim.ncp.interfaces.email.data.EmailTemplateSDO;
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
 * @author Yoon
 */
@Service
@Slf4j
public class EmailAdapter extends AbstractAdapter {


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
     * AWS SES SEND HTML Email
     *
     * @param emailHtmlSDO
     * @param adapterHeader
     * @return
     */
    public String sendHtmlEmail(final EmailHtmlSDO emailHtmlSDO, final AdapterHeader adapterHeader) {
        String serverUrl = interfaceApiCommon.getExternalInterfaceServerUrl() + InterfaceConstants.SEND_HTML_EMAIL;
        String result = "FAIL";

        try {
        	// 운영
        	if(internalInterfaceServerUrl != null && internalInterfaceServerUrl.indexOf("internal-prd") > -1) {
        		result = this.interfaceApiCommon.requestToServer(JsonService.marshalling(emailHtmlSDO), serverUrl, adapterHeader);
        	}
        	// 운영 이 아니면
        	else {
        		String emaiWhiteList = configService.getProperty("ncp_base.white.list.email");
        		if(emaiWhiteList != null && !"".equals(emaiWhiteList)) {
        			String[] emaiWhiteListArr = emaiWhiteList.split("/");
        			for(String email : emaiWhiteListArr) {
        				if(emailHtmlSDO.getMbrEmail().equals(email)) {
        					result = this.interfaceApiCommon.requestToServer(JsonService.marshalling(emailHtmlSDO), serverUrl, adapterHeader);
        					break;
        				}
        			}
        		}
        	}

//        	result = this.interfaceApiCommon.requestToServer(JsonService.marshalling(emailHtmlSDO), serverUrl, adapterHeader);
        } catch (Exception e) {
            log.error("> {} Failure Message : {}", this.getClass().getName(), e.getStackTrace());

            // interface server 에 대한 접속오류가 발생하면 명시적인 exception 을 발생시킨다.
            throw new AdapterConnectException(null);
        }

        return result;
    }

    /**
     * AWS SES SEND Template Email
     *
     * @param emailTemplateSDO
     * @param adapterHeader
     * @return
     */
    public String sendTemplateEmail(final EmailTemplateSDO emailTemplateSDO, final AdapterHeader adapterHeader) {
        String serverUrl = interfaceApiCommon.getExternalInterfaceServerUrl() + InterfaceConstants.SEND_TEMPLATE_EMAIL;
        String result = "FAIL";

        try {
        	// 운영
        	if(internalInterfaceServerUrl != null && internalInterfaceServerUrl.indexOf("internal-prd") > -1) {
        		result = this.interfaceApiCommon.requestToServer(JsonService.marshalling(emailTemplateSDO), serverUrl, adapterHeader);
        	}
        	// 운영 이 아니면
        	else {
        		String emaiWhiteList = configService.getProperty("ncp_base.white.list.email");
        		if(emaiWhiteList != null && !"".equals(emaiWhiteList)) {
        			String[] emaiWhiteListArr = emaiWhiteList.split("/");
        			for(String email : emaiWhiteListArr) {
        				if(emailTemplateSDO.getMbrEmail().equals(email)) {
        					result = this.interfaceApiCommon.requestToServer(JsonService.marshalling(emailTemplateSDO), serverUrl, adapterHeader);
        					break;
        				}
        			}
        		}
        	}

//            result = this.interfaceApiCommon.requestToServer(JsonService.marshalling(emailTemplateSDO), serverUrl, adapterHeader);
        } catch (Exception e) {
            log.error("> {} Failure Message : {}", this.getClass().getName(), e.getStackTrace());

            // interface server 에 대한 접속오류가 발생하면 명시적인 exception 을 발생시킨다.
            throw new AdapterConnectException(null);
        }

        return result;
    }


}
