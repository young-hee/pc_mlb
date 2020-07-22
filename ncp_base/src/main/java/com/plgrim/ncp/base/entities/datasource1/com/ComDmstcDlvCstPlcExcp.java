/**
 * @author : Generator(Generator)
 * @date : 2018-05-29
 * @version : 1.0
 * @desc :  generated by gradle
 */

package com.plgrim.ncp.base.entities.datasource1.com;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.apache.ibatis.type.Alias;

import com.plgrim.ncp.base.abstracts.AbstractEntity;

/**
 * 업체 국내 배송비 정책 예외
 */
@Data
@EqualsAndHashCode(callSuper=false)
@Alias("comDmstcDlvCstPlcExcp")
public class ComDmstcDlvCstPlcExcp extends AbstractEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 국내 배송비 정책 일련번호	 
	 */
	private java.lang.Long dmstcDlvCstPlcSn;


	/**
	 * 예외 순번	 
	 */
	private java.lang.Integer excpTurn;


	/**
	 * 예외 우편번호	 
	 */
	private String excpPostNo;


	/**
	 * 예외 기본주소	 
	 */
	private String excpBaseAddr;


	/**
	 * 예외 국내 배송비	 
	 */
	private java.math.BigDecimal excpDmstcDlvCst;


	/**
	 * 등록자 ID
	 * 등록한 관리자 번호	 
	 */
	private String regtrId;


	/**
	 * 등록 일시	 
	 */
	private java.util.Date regDt;


	/**
	 * 수정자 ID
	 * 수정한 관리자 번호	 
	 */
	private String udterId;


	/**
	 * 수정 일시	 
	 */
	private java.util.Date udtDt;

	
}
