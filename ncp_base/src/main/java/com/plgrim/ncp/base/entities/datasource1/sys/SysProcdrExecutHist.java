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
 * 시스템 프로시저 실행 이력
 */
@Data
@EqualsAndHashCode(callSuper=false)
@Alias("sysProcdrExecutHist")
public class SysProcdrExecutHist extends AbstractEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 프로시저 실행 일련번호	 
	 */
	private java.lang.Long procdrExecutSn;


	/**
	 * 프로시저 명	 
	 */
	private String procdrNm;


	/**
	 * 시작 일시	 
	 */
	private java.util.Date begDt;


	/**
	 * 종료 일시	 
	 */
	private java.util.Date endDt;


	/**
	 * 경과 시간
	 * ㅁ. 초단위 경과 시간	 
	 */
	private java.math.BigDecimal elapseTime;


	/**
	 * 프로시저 실행 상태 코드
	 * ㅁ. 프로시저 실행 상태 : PROCDR_EXECUT_STAT
	 *    >. 실행 : EXECUT
	 *    >. 성공 : SUCCES
	 *    >. 실패 : FAILR	 
	 */
	private String procdrExecutStatCd;


	/**
	 * 메시지 내용	 
	 */
	private String msgCont;


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