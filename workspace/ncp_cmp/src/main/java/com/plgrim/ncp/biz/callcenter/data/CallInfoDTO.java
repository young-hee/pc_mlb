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

import java.util.List;


/**
 * 인터페이스 메일
 */
@Data
@EqualsAndHashCode(callSuper=false)
@Alias("callInfoDTO")
public class CallInfoDTO {

	private static final long serialVersionUID = 1L;

	@JsonProperty("CallInfo")
	private CallInfo CallInfo;
	
	@JsonProperty("SendDateTime")
	private String SendDateTime;
	
	@JsonProperty("ResponseResultFlag")
	private String ResponseResultFlag;

	@JsonProperty("ResponseResultMessage")
	private String ResponseResultMessage;

}
