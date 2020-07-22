package com.plgrim.ncp.commons.auth.naver;

import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.http.client.ClientProtocolException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.plgrim.ncp.framework.data.SystemPK;

import lombok.extern.slf4j.Slf4j;

@Component
@Profile({ "dev", "stg", "stg2", "prod" })
@Slf4j
public class CloudGatewayNaverAccessTokenValidator extends DirectNaverAccessTokenValidator {

	/**
	 * Interface Server 에서는
	 * NaverUserService에 대해 구현체가 없어 DI 불가
	 */
	@Autowired(required = false)
	private NaverUserService naverUserService;
	
	@Override
	protected String fetch(SystemPK systemPK, String userAccessToken) throws URISyntaxException, IOException, ClientProtocolException {
		
		log.info("Naver에 토큰 검증을 요청하기 위해 외부 인터페이스로 호출합니다. userAccessToken add req.setMethod(\"get\"): {}", userAccessToken);
		
		String json = "";
		try {
			json = naverUserService.naverAccessTokenValidator(systemPK, userAccessToken);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		
		log.info("CloudGateway httpproxy response: {}", json);
		
		return json;		
		
	}

}



