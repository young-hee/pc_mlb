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
 * 시스템 관리자 즐겨찾기
 */
@Data
@EqualsAndHashCode(callSuper=false)
@Alias("sysAdminBukmk")
public class SysAdminBukmk extends AbstractEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 관리자 ID	 
	 */
	private String adminId;


	/**
	 * 메뉴 일련번호	 
	 */
	private java.lang.Long menuSn;


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
