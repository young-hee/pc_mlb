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
 * 시스템 그룹 코드 코드 연결
 */
@Data
@EqualsAndHashCode(callSuper=false)
@Alias("sysGrpCdCdCnnc")
public class SysGrpCdCdCnnc extends AbstractEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 그룹 코드
	 * 표준 용어 사전에 기초한 약어의 조합을 사용	 
	 */
	private String grpCd;


	/**
	 * 코드
	 * 표준 용어 사전에 기초한 약어의 조합을 사용	 
	 */
	private String cd;


	/**
	 * 사용 여부	 
	 */
	private String useYn;


	/**
	 * 정렬 순서	 
	 */
	private java.lang.Integer sortSeq;


	/**
	 * 보조 코드 1	 
	 */
	private String asstnCd1;


	/**
	 * 보조 코드 2	 
	 */
	private String asstnCd2;


	/**
	 * 코드 설명
	 * 기본 언어의 코드 설명을 입력	 
	 */
	private String cdDscr;


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
