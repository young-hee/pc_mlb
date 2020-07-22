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
 * 시스템 표준 옵션 값
 */
@Data
@EqualsAndHashCode(callSuper=false)
@Alias("sysStdOptVal")
public class SysStdOptVal extends AbstractEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 표준 옵션 코드	 
	 */
	private String stdOptCd;


	/**
	 * 표준 옵션 값 코드	 
	 */
	private String stdOptValCd;


	/**
	 * 옵션 값 명	 
	 */
	private String optValNm;


	/**
	 * 사용 여부	 
	 */
	private String useYn;


	/**
	 * 정렬 순서	 
	 */
	private java.lang.Integer sortSeq;


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
