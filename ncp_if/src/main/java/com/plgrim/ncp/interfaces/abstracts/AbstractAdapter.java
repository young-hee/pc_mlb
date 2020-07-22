package com.plgrim.ncp.interfaces.abstracts;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.plgrim.ncp.framework.commons.JsonService;
import com.plgrim.ncp.framework.exception.AdapterConnectException;
import com.plgrim.ncp.interfaces.util.AdapterHeader;
import com.plgrim.ncp.interfaces.util.InterfaceApiCommon;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractAdapter {
	
	// ~ Instance fields. ~~~~~~~~~~~~~~
	@Autowired
	protected ApplicationContext applicationContext;
	
	@Autowired
    private InterfaceApiCommon interfaceApiCommon;
	
	// ~ Constructors. ~~~~~~~~~~~~~~~~~
	// ~ Implementation Method. ~~~~~~~~
	// ~ Public Methods. ~~~~~~~~~~~~~~~
	
	public String sendInterface(Object obj, AdapterHeader adapterHeader, String serverUrl) {
		String inputJson = JsonService.marshalling(obj);
		return sendInterface(inputJson, adapterHeader, serverUrl);
	}
	
	public String sendInterface(final String inputJson, AdapterHeader adapterHeader, String serverUrl) {
        String result = "00";

        try {
            result = this.interfaceApiCommon.requestToServer(inputJson, serverUrl, adapterHeader);
        } catch (Exception e) {
            log.error("> {} Failure Message : {}", this.getClass().getName(), e.getStackTrace());
            // interface server 에 대한 접속오류가 발생하면 명시적인 exception 을 발생시킨다.
            throw new AdapterConnectException(null);
        }

        return result;
    }
	
	public <T extends InterfaceBaseSDO> T sendInterface(Object obj, AdapterHeader adapterHeader, String serverUrl, Class<T> clazz) {
		String inputJson = JsonService.marshalling(obj);
        return this.sendInterface(inputJson, adapterHeader, serverUrl, clazz);		
    }
	
	public <T extends InterfaceBaseSDO> T sendInterface(final String inputJson, AdapterHeader adapterHeader, String serverUrl, Class<T> clazz) {
		String result = sendInterface(inputJson, adapterHeader, serverUrl);
        return JsonService.unmarshalling(result, clazz);		
    }
	
	// ~ Private Methods. ~~~~~~~~~~~~~~
	// ~ Getter and Setter. ~~~~~~~~~~~~
}
