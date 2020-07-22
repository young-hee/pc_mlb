/**
 * @author : Generator(Generator)
 * @date : 2018-05-23
 * @version : 1.0
 * @desc :  generated by gradle
 */

package com.plgrim.ncp.base.entities.datasource1.sys;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.apache.ibatis.type.Alias;

import com.plgrim.ncp.base.abstracts.AbstractEntity;

/**
 * 시스템 매장 이벤트
 */
@Data
@EqualsAndHashCode(callSuper=false)
@Alias("sysShopEvt")
public class SysShopEvt extends AbstractEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 매장 이벤트 일련번호	 
	 */
	private java.lang.Long shopEvtSn;


	/**
	 * 매장 ID	 
	 */
	private String shopId;


	/**
	 * 이벤트 명	 
	 */
	private String evtNm;


	/**
	 * 이벤트 시작 일자	 
	 */
	private String evtBegDate;


	/**
	 * 이벤트 종료 일자	 
	 */
	private String evtEndDate;


	/**
	 * 이벤트 내용	 
	 */
	private String evtCont;


	/**
	 * 사용 여부	 
	 */
	private String useYn;


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
