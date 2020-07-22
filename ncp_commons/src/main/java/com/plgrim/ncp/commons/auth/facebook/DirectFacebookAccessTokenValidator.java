package com.plgrim.ncp.commons.auth.facebook;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
@Component
@Profile({"default","local"})
@SuppressWarnings("rawtypes")
public class DirectFacebookAccessTokenValidator implements FacebookAccessTokenValidator {
	@Value("${ncp_base.facebook.login.appid}")
	String appId;

	@Value("${ncp_base.facebook.login.appAccessToken}")
	String appAccessToken;

	private HttpClient client;

	private ObjectMapper objectMapper;

	public DirectFacebookAccessTokenValidator() {
		client = HttpClients.custom().setConnectionManager(new PoolingHttpClientConnectionManager()).build();
		objectMapper = new ObjectMapper();
	}

	/**
	 *   
	 */
	public ValidationResult validate(String userAccessToken) {
		try {
			log.info("Facebook validate userAccessToken 이 요청되었습니다. userAccessToken: {}", userAccessToken);
			String json = fetch(userAccessToken);
			Map response = (Map) objectMapper.readValue(json, HashMap.class);
			Map data = validate(json, response);
			String user_id = (String) data.get("user_id");
			return new ValidationResult(user_id);
		} catch (URISyntaxException e) {
			throw new IllegalArgumentException(e);
		} catch (ClientProtocolException e) {
			throw new IllegalArgumentException(e);
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		}
	}

	private Map validate(String json, Map response) {
		if (response.containsKey("error")) {
			Map error = (Map) response.get("error");
			Integer code = (Integer) error.get("code");
			String type = (String) error.get("type");
			String message = (String) error.get("message");
			throw new FacebookLoginException(code, type, message);
		}
		Map data = (Map) response.get("data");
		if (data == null) {
			throw new IllegalArgumentException("facebook 의 응답이 data를 포함하고 있지 않습니다. json: " + json);
		}
		String app_id = (String) data.get("app_id");
		if (appId.equals(app_id) == false) {
			throw new IllegalArgumentException("PLGRIMSHOP 을 위한 userAccessToken이 아닙니다.  json: " + json);
		}

		Boolean isValid = (Boolean) data.get("is_valid");
		if (Boolean.TRUE.equals(isValid) == false) {
			throw new BadCredentialsException("유효하지 않은 userAccessToken 입니다. json: " + json);
		}

		return data;
	}

	protected String fetch(String userAccessToken) throws URISyntaxException, IOException, ClientProtocolException {
		HttpUriRequest login = RequestBuilder.get().setUri(new URI("https://graph.facebook.com/debug_token"))
				.addParameter("input_token", userAccessToken)
				.addParameter("access_token", URLEncoder.encode(appAccessToken, "UTF-8")).build();
		HttpResponse response2 = client.execute(login);
		HttpEntity entity = response2.getEntity();
		String json = EntityUtils.toString(entity, "UTF-8");
		return json;
	}

}
