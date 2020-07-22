package com.plgrim.ncp.biz.callcenter.data;

/**
 * @package : com.plgrim.ncp.base.entities..inf
 * @author : jackie(jackie)
 * @date : 20150526
 * @version : 1.0
 * @desc : 
 */


import lombok.Data;
import lombok.EqualsAndHashCode;

import org.apache.ibatis.type.Alias;

import com.fasterxml.jackson.annotation.JsonProperty;



/**
 * 인터페이스 메일
 */
@Data
@EqualsAndHashCode(callSuper=false)
@Alias("callbackList")
public class CallbackList{

	private static final long serialVersionUID = 1L;

	@JsonProperty("RegTime")
	private String RegTime;

	@JsonProperty("CallbackId")
	private String CallbackId;
	
	@JsonProperty("CustomerTelNumber")
	private String CustomerTelNumber;
	
	@JsonProperty("CustomerCallbackTelNumber")
	private String CustomerCallbackTelNumber;
	
	@JsonProperty("AgentID")
	private String AgentID;
	
	@JsonProperty("CallbackStatusCode")
	private String CallbackStatusCode;
	
	@JsonProperty("CallbackStatusName")
	private String CallbackStatusName;
	
	@JsonProperty("ResponseResultFlag")
	private String ResponseResultFlag;
	
	@JsonProperty("ResponseResultMessage")
	private String ResponseResultMessage;

}
