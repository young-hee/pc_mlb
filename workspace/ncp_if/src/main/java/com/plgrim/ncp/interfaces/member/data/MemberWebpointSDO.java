package com.plgrim.ncp.interfaces.member.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.plgrim.ncp.interfaces.abstracts.InterfaceSDO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
@JsonInclude(value=Include.NON_EMPTY)
public class MemberWebpointSDO extends InterfaceSDO {

	private static final long serialVersionUID = 1L;
	
	@JsonProperty("WEBPNT_AMT")
	private String webpntAmt;
	
	@JsonProperty("MALL_ID")
	private String mallId;
	
	@JsonProperty("MBR_ID")
	private String mbrId;
	
	private String mbrNo;
	
	/**
	 * 웹포인트 일련번호	 
	 */
	private java.lang.Long webpntSn;
}
