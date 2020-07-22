/**
 * @package : com.plgrim.ncp.base.entities..com
 * @author : asha
 * @date : 20151124
 * @version : 1.0
 * @desc : 
 */

package com.plgrim.ncp.base.entities.datasource1.com;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.apache.ibatis.type.Alias;

/**
 * 업체 국내 배송비 정책 - 확장 
 */
@Data
@EqualsAndHashCode(callSuper=false)
@Alias("comQdlvGudTxtExtend")
public class ComQdlvGudTxtExtend extends ComQdlvGudTxt{
	/**ComQdlvGudTxtExtend
	 * 
	 */
	private static final long serialVersionUID = -6704469461203443739L;
	
	/**
	 * 디바이스종류
	 */
	private String device;
	
	/**
	 * 안내문구 내용
	 */
	private String gudTxtCont;
	
	/**
	 * 퀵배송비 부과 구분 코드 
	 * 무료 : FREE
	 * 유료 : PCHRG
	 * 조건부무료 : COND_FREE
	 */
	private String qdlvCstLevySectCd;
	
	/**
	 * 퀵배송비 면제 기준 금액
	 */
	private int qdlvCstExmStdrAmt;
	
	/**
	 * 퀵배송비
	 */
	private int qdlvCst;
	
	/**
	 * 퀵배송 토요일 종료시간
	 */
	private String satQdlvEndTime;
	
	/**
	 * 퀵배송 주문 가능 시간
	 */
	private String ordAbleTime;
	
	/**
	 * 퀵배송 배송예정 시간
	 */
	private String qdlvExpTime;

}
