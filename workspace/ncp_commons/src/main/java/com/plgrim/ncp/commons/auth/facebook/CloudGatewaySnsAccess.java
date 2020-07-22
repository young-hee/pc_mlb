package com.plgrim.ncp.commons.auth.facebook;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URLEncoder;

import org.apache.http.client.ClientProtocolException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.plgrim.ncp.cloudgateway.httpclientproxy.HttpClientProxyClient;
import com.plgrim.ncp.cloudgateway.httpclientproxy.HttpProxyRequest;
import com.plgrim.ncp.cloudgateway.httpclientproxy.HttpProxyResponse;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class CloudGatewaySnsAccess {
	@Autowired
	private HttpClientProxyClient client;
	
	public String getCloudGatewaySnsAccess(String tokenRequestUrl) throws URISyntaxException, IOException, ClientProtocolException {
		HttpProxyRequest req = new HttpProxyRequest();
		req.setMethod("get");
		req.setUrl(tokenRequestUrl);
		log.info("getCloudGatewaySnsAccess 호출로 CloudGateway 에 접속:{} ", tokenRequestUrl);
		
		HttpProxyResponse res = client.request(req);
		String json = res.getBody();
		log.info("getCloudGatewaySnsAccess response: {}", json);
		
		return json;
	}

}
