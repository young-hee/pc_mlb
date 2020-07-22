package com.plgrim.ncp.biz.callcenter.data;

/**
 * @package : com.plgrim.ncp.base.entities..inf
 * @author : jackie(jackie)
 * @date : 20150526
 * @version : 1.0
 * @desc : 
 */


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.type.Alias;


/**
 * 인터페이스 메일
 */
@Data
@EqualsAndHashCode(callSuper=false)
@Alias("callbackList")
public class CallInfo {

	private static final long serialVersionUID = 1L;

	@JsonProperty("TenantId")
	private String TenantId;

	@JsonProperty("TenantName")
	private String TenantName;
	
	@JsonProperty("EnterCallCnt")
	private String EnterCallCnt;
	
	@JsonProperty("AnsweredCallCnt")
	private String AnsweredCallCnt;
	
	@JsonProperty("CompletedCallCnt")
	private String CompletedCallCnt;
	
	@JsonProperty("AbandonedCallCnt")
	private String AbandonedCallCnt;
	
	@JsonProperty("WaitingCallCnt")
	private String WaitingCallCnt;
	
	@JsonProperty("AnsweredCallPercentage")
	private String AnsweredCallPercentage;
	
	@JsonProperty("OutboundCnt")
	private String OutboundCnt;

	@JsonProperty("OutboundConnectCnt")
	private String OutboundConnectCnt;

	@JsonProperty("CallbackReceiveCnt")
	private String CallbackReceiveCnt;

	@JsonProperty("CallbackComplateCnt")
	private String CallbackComplateCnt;

}
