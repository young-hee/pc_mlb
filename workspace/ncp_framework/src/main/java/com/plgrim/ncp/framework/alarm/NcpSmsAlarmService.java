package com.plgrim.ncp.framework.alarm;

import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.plgrim.ncp.framework.commons.JsonService;
import com.google.common.collect.ImmutableMap;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@Component
@Profile({ "prod" })
public class NcpSmsAlarmService implements AlarmService {
	@Value("${ncp_base.interface.server.url}")
	String ifServerUrl;

	// security key monitoring SMS
	private @Value("${ncp_if_server.authorization.security.key.sms}") String smsSecurityKey;

	CloseableHttpClient client = HttpClients.custom().setConnectionManager(new PoolingHttpClientConnectionManager()).build();
	ExecutorService executor = Executors.newSingleThreadExecutor();

	@Override
	public void alarm(final String message) {
		executor.equals(new Runnable() {

			@Override
			public void run() {
				try {
					log.warn(message);
					Map<String, String> params = ImmutableMap.<String, String>of("group", "MBR_MNTRNG_SMS", "subject", "[PLGRIM CB 모니터링]",
							"message", message);
					String request = JsonService.marshalling(params);
					log.debug("Request: {}", request);
					//@formatter:off
					HttpUriRequest trial = RequestBuilder
							.post()
							.setUri(ifServerUrl + "/smsmms/sendMonitoringSmsMms")
							.setHeader("Content-Type", "application/json")
							.setHeader("tokenKey", "d5d7d90067e770b28184b67c4a05a7cb")
							
							.setEntity(new StringEntity(request))
					.build();
					//@formatter:on
					HttpResponse response2 = client.execute(trial);
					HttpEntity entity = response2.getEntity();
					String responseJson = EntityUtils.toString(entity, "UTF-8");
					log.debug("Response: {}", responseJson);
				} catch (Exception e) {
					log.warn("", e);
				}
			}
		});

	}

}
