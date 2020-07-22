/**
 * @package : com.plgrim.ncp.base.entities..inf
 * @author : jackie(jackie)
 * @date : 20150804
 * @version : 1.0
 * @desc : 
 */

package com.plgrim.ncp.biz.callcenter.data;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.apache.ibatis.type.Alias;

import com.plgrim.ncp.base.abstracts.AbstractEntity;

/**
 * 인터페이스 호출 BACK LIST
 */
@Data
@EqualsAndHashCode(callSuper=false)
@Alias("infCallBackList")
public class InfCallBackDTO extends AbstractEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * REGTIME	 
	 */
	private java.util.Date regtime;
	/**
	 * 호출BACKID	 
	 */
	private String callbackid;
	/**
	 * CUSTOMERTELNUMBER	 
	 */
	private String customertelnumber;
	/**
	 * CUSTOMERCALLBACKTELNUMBER	 
	 */
	private String customercalbacktelnumber;
	/**
	 * AGENTID	 
	 */
	private java.math.BigInteger agentid;
	/**
	 * 호출BACKSTATUSCODE	 
	 */
	private java.math.BigInteger callbackstatuscode;
	/**
	 * 호출BACKSTATUSNANE	 
	 */
	private String callbackstatusname;
	/**
	 * RESPONSERESULTFLAG	 
	 */
	private String responseresultflag;
	/**
	 * RESPONSERESULTMESSAGE	 
	 */
	private String responseresultmessage;

}
