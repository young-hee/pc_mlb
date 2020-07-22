package com.plgrim.ncp.commons.auth.facebook;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

public class FacebookAccessTokenValidatorTest {

	@Test
	public void test() throws ClientProtocolException, IOException, URISyntaxException {
		String appAccessToken = URLEncoder.encode("1085874064807240|3yN7AF0q0YPoJ8FmIexTfFXMlFg", "UTF-8");
		
		System.out.println(appAccessToken);
		String userAccessToken = "EAAPbmMHnVUgBAGqbHomDW42sOBJC7GbxXNBNd3VUGn0TtBltqZAh5J7zS3rLFc6N8WKckAjKpZAHZAt1o8nFOBZAUcCfpfQgnC6aBwMG6wlZByWIWRUGelDHPQQcEfRMU6jD9Fv9myTlgInSc34A13zvJfEHZCWZA8p5xfZBD6rGf9e8xfm8rZAwU";

		CloseableHttpClient httpclient = HttpClients.createDefault();

		HttpUriRequest login = RequestBuilder.get().setUri(new URI("https://graph.facebook.com/debug_token"))
				.addParameter("input_token", userAccessToken).addParameter("access_token", appAccessToken).build();
		CloseableHttpResponse response2 = httpclient.execute(login);

		HttpEntity entity = response2.getEntity();
		System.out.println("Login form get: " + response2.getStatusLine());
		ObjectMapper om = new ObjectMapper();
		HashMap map = om.readValue(entity.getContent(), HashMap.class);
		System.out.println(map);
		Map data = (Map) map.get("data");
		System.out.println(data);

		String app_id = (String) data.get("app_id");
		String user_id = (String) data.get("user_id");
		Boolean isValid = (Boolean) data.get("is_valid");
		Map error = (Map) data.get("error");
		Integer errorCode = (Integer) error.get("code");
		String errorMessage = (String) error.get("message");
		ValidationResult result = new ValidationResult(user_id);
		System.out.println(result);
		// FacebookAccessTokenValidator validator = new
		// FacebookAccessTokenValidator();
		// validator.setWebAccessToken(appAccessToken);
		// ValidationResult result = validator.validate(userAccessToken);
	}

}
// input_token={token-to-inspect}&access_token={app-token-or-admin-token}