package com.plgrim.ncp.cloudgateway.httpclientproxy;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Data;

@RestController
@Data
public class HttpClientProxyController implements InitializingBean {
	ObjectMapper mapper = new ObjectMapper();

	private static final int DEFAULT_SOCKET_TIMEOUT = 10000;
	private static final int DEFAULT_CONNECT_TIMEOUT = 2000;
	private static final int DEFAULT_REQUEST_TIMEOUT = 2000;

	private int socketTimeout = DEFAULT_SOCKET_TIMEOUT;
	private int connectionTimeout = DEFAULT_CONNECT_TIMEOUT;
	private int requestTimeout = DEFAULT_REQUEST_TIMEOUT;

	private CloseableHttpClient client;

	@RequestMapping(value = "/httpproxyDownload", produces = "application/json")
	public void httpproxyDownload(@RequestBody String text, HttpServletResponse res)
			throws JsonParseException, JsonMappingException, IOException {
		HttpProxyRequest req = mapper.readValue(text, HttpProxyRequest.class);
		HttpUriRequest trialRequest = buildRequest(req);
		CloseableHttpResponse response = client.execute(trialRequest);
		if (response.getStatusLine().getStatusCode() != 200) {
			res.setStatus(response.getStatusLine().getStatusCode());
			return;
		}
		
		HttpEntity entity = response.getEntity();
		if (entity.getContentLength()>0) {
			res.setHeader("Content-Length", String.valueOf(entity.getContentLength()));
		}
		
		StreamUtils.copy(entity.getContent(), res.getOutputStream());
	}

	@RequestMapping(value = "/httpproxy", produces = "application/json")
	HttpProxyResponse httpproxy(@RequestBody String text) throws JsonParseException, JsonMappingException, IOException {
		HttpProxyRequest req = mapper.readValue(text, HttpProxyRequest.class);
		HttpUriRequest trialRequest = buildRequest(req);
		CloseableHttpResponse response = client.execute(trialRequest);
		HttpProxyResponse proxyResponse = buildResponse(req, response);
		return proxyResponse;
	}

	private HttpProxyResponse buildResponse(HttpProxyRequest req, CloseableHttpResponse response) throws IOException {
		HttpProxyResponse proxyResponse = new HttpProxyResponse();

		setupStatus(response, proxyResponse);
		setupHeaders(response, proxyResponse);
		setupBody(req, response, proxyResponse);
		return proxyResponse;
	}

	private void setupBody(HttpProxyRequest req, CloseableHttpResponse response, HttpProxyResponse proxyResponse)
			throws IOException {
		HttpEntity entity = response.getEntity();
		String body = EntityUtils.toString(entity,
				req.getResponseEncoding() == null ? "UTF-8" : req.getResponseEncoding());
		proxyResponse.setBody(body);
	}

	private void setupStatus(CloseableHttpResponse response, HttpProxyResponse proxyResponse) {
		proxyResponse.setStatus(response.getStatusLine().getStatusCode());
	}

	private void setupHeaders(CloseableHttpResponse response, HttpProxyResponse proxyResponse) {
		Header[] headers = response.getAllHeaders();
		if (headers != null) {
			for (Header header : headers) {
				proxyResponse.addHeader(header.getName(), header.getValue());
			}
		}
	}

	private HttpUriRequest buildRequest(HttpProxyRequest req) throws UnsupportedEncodingException {
		RequestBuilder trial = null;
		switch (req.getMethod()) {
		case "post":
			trial = RequestBuilder.post();
			break;
		case "get":
			trial = RequestBuilder.get();
		}
		trial = setupConfig(req, trial);

		trial = trial.setUri(req.getUrl());

		if (req.getHeaders() != null) {
			for (NameValuePair pair : req.getHeaders()) {
				trial = trial.addHeader(pair.getName(), pair.getValue());
			}
		}
		if (req.getParams() != null) {
			for (NameValuePair pair : req.getParams()) {
				trial = trial.addParameter(pair.getName(), pair.getValue());
			}
		}
		if (req.getBody() != null) {
			trial = trial.setEntity(new StringEntity(req.getBody()));
		}
		HttpUriRequest trialRequest = trial.build();
		return trialRequest;
	}

	private RequestBuilder setupConfig(HttpProxyRequest req, RequestBuilder trial) {
		int targetSocketTimeout = req.getSocketTimeout() == null ? socketTimeout : req.getSocketTimeout();
		RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(targetSocketTimeout)
				.setConnectTimeout(connectionTimeout).setConnectionRequestTimeout(requestTimeout).build();

		trial = trial.setConfig(requestConfig);
		return trial;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		client = HttpClients.custom().setConnectionManager(new PoolingHttpClientConnectionManager()).build();
	}
}
