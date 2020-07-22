package com.plgrim.ncp.framework.cloud;

import java.util.HashMap;
import java.util.Map;

import org.apache.cxf.feature.LoggingFeature;
import org.apache.cxf.jaxrs.client.JAXRSClientFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;

import lombok.extern.slf4j.Slf4j;

/**
 * cxf client factory helper for internal/external api interface
 * 
 * @author Chulhui Park <charles@plgrim.com>
 */
@Component
@Slf4j
public class ApiClientFactory {
	@Value("${ncp_base.interface.api.key}")
	private String apiKey;
	
	@Value("${ncp_base.interface.internal.cxf.url}")
	private String internalApiUrl;
	
	@Value("${ncp_base.interface.external.cxf.url}")
	private String externalApiUrl;
	
	@Autowired
	private JacksonJsonProvider jsonProvider;
	
	private Map<Class<?>, JAXRSClientFactoryBean> cfbMap = new HashMap<>();
	
    private <T> JAXRSClientFactoryBean createJaxRsClientFactory(Class<T> cls) {
    	Map<String, String> map = new HashMap<>();
        String headerApiKey = "APIKEY "+apiKey;
        map.put("Authorization", headerApiKey);
        JAXRSClientFactoryBean bean = new JAXRSClientFactoryBean();
        bean.setHeaders(map);
        bean.setProvider(jsonProvider);
        
        bean.setThreadSafe(false);
        bean.setSecondsToKeepState(30*1000);

        bean.setServiceClass(cls);
        LoggingFeature feature = new LoggingFeature();
        bean.getFeatures().add(feature);
        
        return bean;
    }
    
    public <T> T create(String apiUrl, Class<T> cls, Object... varValues) {
    	JAXRSClientFactoryBean factory = cfbMap.get(cls);
    	if (factory == null) {
    		factory = createJaxRsClientFactory(cls);
    		cfbMap.put(cls, factory);
    	}

    	String pkg = cls.getPackage().getName();
    	if (!pkg.startsWith("com.plgrim.ncp")) {
    		throw new IllegalArgumentException("cls["+cls+"] is not suppored package.");
    	}
    	
    	if (apiUrl == null || apiUrl.length() == 0) {
    		if (pkg.indexOf(".internal.") > 0) {
        		apiUrl = internalApiUrl;
        	} else if (pkg.indexOf(".external.") > 0) {
        		apiUrl = externalApiUrl;
        	} else {
        		throw new IllegalArgumentException("can't decide url of internal/external apiServer from package["+pkg+"]");
        	}
    	}
    	factory.setAddress(apiUrl);
    	
        T created = factory.create(cls, varValues);
        log.info("cxf webclient creted:{}", created);
        return created;    	
    }
    
	/**
	 * package prefix : com.plgrim.ncp...internal. 일 경우  internal api server와 통신하는 cxf client 생성
	 * package prefix : com.plgrim.ncp...external. 일 경우  external api server와 통신하는 cxf client 생성
	 * 이 외의 경우 IllegalArgumentException 발생
	 * 
	 * @param cls 생성할 cxf client interface
	 * @param varValues
	 * @return cxf client
	 */
    public <T> T create(Class<T> cls, Object... varValues) {
    	return create(null, cls, varValues);
    }

	/**
	 * internal api server와 통신하는 cxf client 생성<br>
	 * * pcakge에 .internal. 또는 .external. 이 없을 경우 사용
	 * 
	 * @param cls 생성할 cxf client interface
	 * @param varValues
	 * @return cxf client
	 */
    public <T> T createForInternalApi(Class<T> cls, Object... varValues) {
    	return create(internalApiUrl, cls, varValues);  	
    }

	/**
	 * external api server와 통신하는 cxf client 생성<br>
	 * * pcakge에 .internal. 또는 .external. 이 없을 경우 사용
	 * 
	 * @param cls 생성할 cxf client interface
	 * @param varValues
	 * @return cxf client
	 */
    public <T> T createForExternalApi(Class<T> cls, Object... varValues) {
    	return create(externalApiUrl, cls, varValues);
    }
}
