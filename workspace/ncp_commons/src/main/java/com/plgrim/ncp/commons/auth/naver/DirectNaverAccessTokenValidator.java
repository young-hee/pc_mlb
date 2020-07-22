package com.plgrim.ncp.commons.auth.naver;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
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
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.plgrim.ncp.framework.data.SystemPK;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
@Component
@Profile({"default", "local"})
@SuppressWarnings("rawtypes")
public class DirectNaverAccessTokenValidator implements NaverAccessTokenValidator {

	private HttpClient client;

	private ObjectMapper objectMapper;

	public DirectNaverAccessTokenValidator() {
		client = HttpClients.custom().setConnectionManager(new PoolingHttpClientConnectionManager()).build();
		objectMapper = new ObjectMapper();
	}

	/**
	 *   
	 */
	public NaverValidationResult validate(SystemPK systemPK, String userAccessToken) {
		try {
			
			//String userAccessTokenConcat = "Bearer " + userAccessToken + "=";
			String userAccessTokenConcat = "Bearer " + userAccessToken;	
			log.info("Naver fetch userAccessToken 이 요청되었습니다. userAccessTokenConcat: {}", userAccessTokenConcat);
			
			String json = fetch(systemPK, userAccessTokenConcat);
			Map response = (Map) objectMapper.readValue(json, HashMap.class);
			Map data = validate(json, response);
			log.info("Naver fetch userAccessToken 이 요청되었습니다. data: {}", data);
			String user_id = (String) data.get("id");
			String email = (String) data.get("email");

			NaverValidationResult naverValidationResult = new NaverValidationResult();
			naverValidationResult.setUserId(user_id);
			naverValidationResult.setEmail(email);
			return naverValidationResult;
		} catch (URISyntaxException e) {
			log.info("Naver login validate URISyntaxException" + e);
			throw new IllegalArgumentException(e);
		} catch (ClientProtocolException e) {
			log.info("Naver login validate ClientProtocolException" + e);
			throw new IllegalArgumentException(e);
		} catch (IOException e) {
			log.info("Naver login validate IOException" + e);
			throw new IllegalArgumentException(e);
		}
	}

	@SuppressWarnings("unchecked")
	private Map validate(String json, Map response) {
		
		log.info("Naver에서 보내온 응답을 검증합니다 json: {} " , json);
		ObjectMapper mapper = new ObjectMapper();
		HashMap<String, Object> hmap;
		Map<String, Object> data;
		try {
			hmap = mapper.readValue(json, HashMap.class);
		}
		catch (IOException e) {
			throw new IllegalArgumentException("Naver 의 응답이 JSON 응답을 포함하고 있지 않습니다. json: " + json);
		}
		
		log.info("파싱된 Naver 응답: {} " , hmap);
		if (!"00".equals(hmap.get("resultcode"))) {
			/*값이 024 올때 문제가 있음 
			Integer code = (Integer) hmap.get("resultcode");
			*/
			Integer code = Integer.parseInt(String.valueOf(hmap.get("resultcode")));
			String type = (String) hmap.get("resultcode");
			String message = (String) hmap.get("message");
			throw new NaverLoginException(code, type, message);
		}
		data = (Map<String, Object>)hmap.get("response");
		log.info("Naver 응답 data: {}", data);
		return data;
	}

	protected String fetch(SystemPK systemPK, String userAccessToken) throws URISyntaxException, IOException, ClientProtocolException {
		String name = "Authorization";
		HttpUriRequest login = RequestBuilder.get().setUri(new URI("https://openapi.naver.com/v1/nid/me"))
				.addHeader(name, userAccessToken).build();		
			
		HttpResponse response2 = client.execute(login);
		HttpEntity entity = response2.getEntity();
		String json = EntityUtils.toString(entity, "UTF-8");
		return json;
	}

}

