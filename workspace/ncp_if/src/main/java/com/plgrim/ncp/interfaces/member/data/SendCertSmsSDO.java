package com.plgrim.ncp.interfaces.member.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.plgrim.ncp.base.abstracts.AbstractSDO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
@JsonInclude(value=Include.NON_EMPTY)
public class SendCertSmsSDO extends AbstractSDO {
	
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("REQ_SEQ")
	private String REQ_SEQ;
	@JsonProperty("RES_SEQ")
	private String RES_SEQ;
	private String sAuthNo;
	private String birthday;
	private String gender;
	private String mbrNm;
	private String mobileCo;
	private String mobileNumber;
	private String frgnrYn;
}