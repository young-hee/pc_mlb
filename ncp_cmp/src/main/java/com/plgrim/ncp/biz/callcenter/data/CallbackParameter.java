package com.plgrim.ncp.biz.callcenter.data;

/**
 * @package : com.plgrim.ncp.base.entities..inf
 * @author : jackie(jackie)
 * @date : 20150526
 * @version : 1.0
 * @desc : 
 */



import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.apache.ibatis.type.Alias;

import com.fasterxml.jackson.annotation.JsonProperty;



/**
 * 인터페이스 메일
 */
@Data
@EqualsAndHashCode(callSuper=false)
@Alias("callbackParameter")
public class CallbackParameter{

	private static final long serialVersionUID = 1L;

	@JsonProperty("callbackList")
	private List<CallbackList> CallbackList;
	
	@JsonProperty("ResponseResultFlag")
	private Boolean ResponseResultFlag;
	
	@JsonProperty("ResponseResultMessage")
	private String ResponseResultMessage;

}
