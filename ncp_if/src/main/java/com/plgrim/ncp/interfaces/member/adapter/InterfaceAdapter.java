package com.plgrim.ncp.interfaces.member.adapter;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plgrim.ncp.framework.commons.JsonService;
import com.plgrim.ncp.framework.exception.AdapterConnectException;
import com.plgrim.ncp.interfaces.member.data.SendCertSmsSDO;
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
public class InterfaceAdapter {


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
	 * 통합회원정보 조회
	 * 
	 * @param inputJson
	 * @param adapterHeader
	 * @param serverUrl
	 * @return
	 */
    public String sendInterface(final String inputJson, AdapterHeader adapterHeader, String serverUrl) {
        String result = "00";

        try {
            log.info("> inputJson : {}", inputJson);

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("result", this.interfaceApiCommon.requestToServer(inputJson, serverUrl, adapterHeader));

            result = jsonObject.getString("result");
            log.info("> {} Result : {}", this.getClass().getName(), result);
        } catch (Exception e) {
            log.error("> {} Failure Message : {}", this.getClass().getName(), e.getStackTrace());

            // interface server 에 대한 접속오류가 발생하면 명시적인 exception 을 발생시킨다.
            throw new AdapterConnectException(null);
        }

        return result;
    }

    public String sendInterfaceList(final String inputJson, AdapterHeader adapterHeader, String serverUrl) {
        String result = "00";

        try {
            log.info("> inputJson : {}", inputJson);

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("result", this.interfaceApiCommon.requestToServer(inputJson, serverUrl, adapterHeader));

            result = jsonObject.getString("result");
            log.info("> {} Result : {}", this.getClass().getName(), result);
        } catch (Exception e) {
            log.error("> {} Failure Message : {}", this.getClass().getName(), e.getStackTrace());

            // interface server 에 대한 접속오류가 발생하면 명시적인 exception 을 발생시킨다.
            throw new AdapterConnectException(null);
        }

        return result;
    }
    
    @SuppressWarnings("unchecked")
    public Map<String, String> sendCertSms(SendCertSmsSDO sendCertSmsSDO, AdapterHeader adapterHeader) {
    	String serverUrl = interfaceApiCommon.getExternalInterfaceServerUrl() + InterfaceConstants.SEND_CERT_SMS;
    	Map<String, String> result = new HashMap<>();

        try {
        	String res = this.interfaceApiCommon.requestToServer(JsonService.marshalling(sendCertSmsSDO), serverUrl, adapterHeader);
        	log.info("======res : " + res);
        	if(res != null && !"".equals(res)){
        		result = JsonService.unmarshalling(res, HashMap.class);
        	}
        } catch (Exception e) {
            log.error("> {} Failure Message : {}", this.getClass().getName(), e.getStackTrace());

            // interface server 에 대한 접속오류가 발생하면 명시적인 exception 을 발생시킨다.
            throw new AdapterConnectException(null);
        }

        return result;
    }

    @SuppressWarnings("unchecked")
    public Map<String, String> sendCertSmsCheck(SendCertSmsSDO sendCertSmsSDO, AdapterHeader adapterHeader) {
    	String serverUrl = interfaceApiCommon.getExternalInterfaceServerUrl() + InterfaceConstants.SEND_CERT_SMS_CHECK;
    	Map<String, String> result = new HashMap<>();

        try {
        	String res = this.interfaceApiCommon.requestToServer(JsonService.marshalling(sendCertSmsSDO), serverUrl, adapterHeader);
        	log.info("======res : " + res);
        	if(res != null && !"".equals(res)){
        		result = JsonService.unmarshalling(res, HashMap.class);
        	}
        } catch (Exception e) {
            log.error("> {} Failure Message : {}", this.getClass().getName(), e.getStackTrace());

            // interface server 에 대한 접속오류가 발생하면 명시적인 exception 을 발생시킨다.
            throw new AdapterConnectException(null);
        }

        return result;
    }
    
}
