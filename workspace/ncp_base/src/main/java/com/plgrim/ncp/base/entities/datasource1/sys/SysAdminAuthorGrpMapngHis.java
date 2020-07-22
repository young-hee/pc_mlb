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
 * 시스템 관리자 권한 그룹 매핑 이력
 */
@Data
@EqualsAndHashCode(callSuper=false)
@Alias("sysAdminAuthorGrpMapngHis")
public class SysAdminAuthorGrpMapngHis extends AbstractEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 관리자 ID	 
	 */
	private String adminId;


	/**
	 * 권한 그룹 일련번호	 
	 */
	private java.lang.Long authorGrpSn;


	/**
	 * 권한 그룹 매핑 이력 일시	 
	 */
	private String authorGrpMapngHistDt;


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