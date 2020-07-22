package com.plgrim.ncp.cloudgateway.httpclientproxy;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
@Component
public class HttpClientProxyClient {
	@Value("${ncp_base.cloudgateway.url}")
	String proxyUrl;

	ObjectMapper mapper = new ObjectMapper();

	CloseableHttpClient client = HttpClients.custom().setConnectionManager(new PoolingHttpClientConnectionManager())
			.build();

	public HttpProxyResponse request(HttpProxyRequest req) {
		try {
			String requestJson = mapper.writeValueAsString(req);
			HttpUriRequest trial = RequestBuilder.post().setUri(proxyUrl + "/httpproxy")
					.setEntity(new StringEntity(requestJson)).build();
			HttpResponse response2 = client.execute(trial);
			HttpEntity entity = response2.getEntity();
			String responseJson = EntityUtils.toString(entity, "UTF-8");
			System.out.println(responseJson);
			return mapper.readValue(responseJson, HttpProxyResponse.class);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public File download(HttpProxyRequest req) {
		try {
			String requestJson = mapper.writeValueAsString(req);
			HttpUriRequest trial = RequestBuilder.post().setUri(proxyUrl + "/httpproxyDownload")
					.setEntity(new StringEntity(requestJson)).build();
			HttpResponse response2 = client.execute(trial);
			HttpEntity entity = response2.getEntity();
			File tmpFile = File.createTempFile("httpproxy", ".tmp");
			StreamUtils.copy(entity.getContent(), new BufferedOutputStream(new FileOutputStream(tmpFile)));
			return tmpFile;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}
}
