package com.plgrim.ncp.commons.data.order;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.plgrim.ncp.commons.data.AbstractOrderDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@JsonInclude(value=Include.ALWAYS)
@EqualsAndHashCode(callSuper = false)
public class KcpMobileDTO  extends AbstractOrderDTO {

	private static final long serialVersionUID = 4701813949999671480L;
	
	String request_URI;
	String payUrl;
	String traceNo;
	String paymentMethod;
	String code;
	String Message;
	String approvalKey;
	String hashData;


}
