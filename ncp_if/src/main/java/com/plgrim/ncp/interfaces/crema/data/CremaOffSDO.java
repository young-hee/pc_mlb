package com.plgrim.ncp.interfaces.crema.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.plgrim.ncp.base.abstracts.AbstractSDO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
@JsonInclude(value=Include.NON_EMPTY)
public class CremaOffSDO extends AbstractSDO {

	private static final long serialVersionUID = -7008925163837657028L;

	@JsonProperty("APP_ID")
	private String appId;

	@JsonProperty("SECRET")
	private String secret;
	
	@JsonProperty("BRAND")
	private String brand;
	
	@JsonProperty("SALEDATE")
	private String saledate;

}