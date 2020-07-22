package com.plgrim.ncp.commons.auth.facebook;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URLEncoder;

import org.apache.http.client.ClientProtocolException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.plgrim.ncp.cloudgateway.httpclientproxy.HttpClientProxyClient;
import com.plgrim.ncp.cloudgateway.httpclientproxy.HttpProxyRequest;
import com.plgrim.ncp.cloudgateway.httpclientproxy.HttpProxyResponse;

import lombok.extern.slf4j.Slf4j;

@Component
@Profile({ "dev", "stg", "stg2","prod" })
@Slf4j
public class CloudGatewayFacebookAccessTokenValidator extends DirectFacebookAccessTokenValidator {
	@Autowired
	private HttpClientProxyClient client;

	@Override
	protected String fetch(String userAccessToken) throws URISyntaxException, IOException, ClientProtocolException {
		log.info("Facebook에 토큰 검증을 요청하기 위해  CloudGateway 에 접속합니다. ");
		HttpProxyRequest req = new HttpProxyRequest();
		req.setMethod("get");
		req.setUrl("https://graph.facebook.com/debug_token");
		req.addParameter("input_token", userAccessToken);
		req.addParameter("access_token", URLEncoder.encode(appAccessToken, "UTF-8"));

		HttpProxyResponse res = client.request(req);
		String json = res.getBody();
		log.info("CloudGateway httpproxy response: {}", json);
		return json;
	}

}
