package com.plgrim.ncp.interfaces.sample.adapter;

import com.plgrim.ncp.framework.commons.JsonService;
import com.plgrim.ncp.framework.exception.AdapterConnectException;
import com.plgrim.ncp.interfaces.sample.data.InterfaceSampleListSDO;
import com.plgrim.ncp.interfaces.sample.data.InterfaceSampleSDO;
import com.plgrim.ncp.interfaces.util.AdapterHeader;
import com.plgrim.ncp.interfaces.util.InterfaceApiCommon;
import com.plgrim.ncp.interfaces.util.InterfaceConstants;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


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
public class InterfaceSampleAdapter {


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
     * Sample interface
     *
     * @param parameter [설명]
     * @param adapterHeader [설명]
     * @return String [설명]
     * @ the exception
     */
    public String sendInterfaceSample(final InterfaceSampleSDO parameter, final AdapterHeader adapterHeader) {
        String serverUrl = interfaceApiCommon.getInternalInterfaceServerUrl() + InterfaceConstants.SEND_SAMPLE;
        String result = "00";

        try {
            String inputJson = JsonService.marshalling(parameter);
            result = this.interfaceApiCommon.requestToServer(inputJson, serverUrl, adapterHeader);
            log.info("> {} Result : {}", this.getClass().getName(), result);
        } catch (Exception e) {
            log.error("> {} Failure Message : {}", this.getClass().getName(), e.getStackTrace());
            // interface server 에 대한 접속오류가 발생하면 명시적인 exception 을 발생시킨다.
            throw new AdapterConnectException(null);
        }

        return result;
    }

    public String sendInterfaceSampleList(final InterfaceSampleListSDO parameterList, final AdapterHeader adapterHeader) {
        String serverUrl = interfaceApiCommon.getInternalInterfaceServerUrl() + InterfaceConstants.SEND_SAMPLE_LIST;
        String result = "00";

        try {
            String inputJson = JsonService.marshalling(parameterList);
            result = this.interfaceApiCommon.requestToServer(inputJson, serverUrl, adapterHeader);
            log.info("> {} Result : {}", this.getClass().getName(), result);
        } catch (Exception e) {
            log.error("> {} Failure Message : {}", this.getClass().getName(), e.getStackTrace());

            // interface server 에 대한 접속오류가 발생하면 명시적인 exception 을 발생시킨다.
            throw new AdapterConnectException(null);
        }

        return result;
    }


}
