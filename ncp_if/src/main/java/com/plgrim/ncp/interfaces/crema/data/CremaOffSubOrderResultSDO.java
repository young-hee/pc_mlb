package com.plgrim.ncp.interfaces.crema.data;

import java.util.List;

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
@JsonIgnoreProperties({"mallId"})
public class CremaOffSubOrderResultSDO extends AbstractSDO {

	private static final long serialVersionUID = 8352401981468776119L;

	@JsonProperty("RESPONSE_CD")
	private String responseCd;

	@JsonProperty("RESPONSE_MSG")
	private String responseMsg;

	@JsonProperty("RESPONSE_DATA")
	private List<CremaOffSubOrderSDO> responseData;

}