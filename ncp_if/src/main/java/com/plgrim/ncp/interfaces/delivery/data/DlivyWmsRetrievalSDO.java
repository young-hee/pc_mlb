package com.plgrim.ncp.interfaces.delivery.data;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.plgrim.ncp.interfaces.abstracts.InterfaceBaseSDO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@JsonInclude(value=Include.NON_EMPTY)
public class DlivyWmsRetrievalSDO extends InterfaceBaseSDO{
	
	private static final long serialVersionUID = 5836627492662374731L;
	
	private String callerId;
	
	private String inputJson;
	
	@JsonProperty("RESPONSE_DATA")
	private List<DlivyRetrievalSDO> responseData;
	
	@JsonProperty("RETRIEVAL_LIST")
	private List<DlivyRetrievalSDO> retrievalList;
}
