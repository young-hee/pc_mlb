package com.plgrim.ncp.interfaces.crema.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.plgrim.ncp.base.abstracts.AbstractSDO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
@JsonInclude(value=Include.NON_EMPTY)
@JsonIgnoreProperties({"authPrps","mallId","createDt","expireDt","period","expireYn"})
public class CremaAuthSDO extends AbstractSDO {

	private static final long serialVersionUID = -7008925163837657028L;

	private String authPrps;

	@JsonProperty("grant_type")
	private String grantType;

	@JsonProperty("client_id")
	private String clientId;

	private String mallId;

	@JsonProperty("client_secret")
	private String clientSecret;

	@JsonProperty("access_token")
	private String accessToken;

	@JsonProperty("token_type")
	private String tokenType;

	@JsonProperty("expires_in")
	private String expiresIn;

	@JsonProperty("created_at")
	private String createdAt;

	private String createDt;
	private String expireDt;
	private int period;
	private String expireYn;

}