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
 * 시스템 관리자 인증
 */
@Data
@EqualsAndHashCode(callSuper=false)
@Alias("sysAdminCrtfc")
public class SysAdminCrtfc extends AbstractEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 관리자 ID	 
	 */
	private String adminId;


	/**
	 * 최초 인증 여부	 
	 */
	private String firstCrtfcYn;


	/**
	 * 최초 인증 일시	 
	 */
	private java.util.Date firstCrtfcDt;


	/**
	 * 최종 인증 번호
	 * ㅁ. PO 로그인시 휴대전화 인증을 위한 인증 번호로 저장된 인증번호와 입력된 인증번호가 같아야만 로그인이 가능
	 * 성공시 최종 인증 성공 일시, 최종 인증 성공여부, 누적 인증 실패 횟수 업데이트
	 * 실패시 최종 인증 실패 일시, 누적 인증 실패 횟수 업데이트
	 * 
	 * ㅁ. 4자리 난수번호	 
	 */
	private String lastCrtfcNo;


	/**
	 * 최종 인증 번호 발급 일시	 
	 */
	private java.util.Date lastCrtfcNoIssuDt;


	/**
	 * 최종 인증 성공 일시	 
	 */
	private java.util.Date lastCrtfcSuccesDt;


	/**
	 * 최종 인증 성공 여부	 
	 */
	private String lastCrtfcSuccesYn;


	/**
	 * 최종 인증 실패 일시	 
	 */
	private java.util.Date lastCrtfcFailrDt;


	/**
	 * 누적 인증 실패 횟수	 
	 */
	private java.lang.Integer acmtlCrtfcFailrCount;


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
