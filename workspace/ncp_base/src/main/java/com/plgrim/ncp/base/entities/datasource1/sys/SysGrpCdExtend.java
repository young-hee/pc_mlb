/**
 * @package : com.plgrim.ncp.base.entities..sys
 * @author : jackie(jackie)
 * @date : 20150417
 * @version : 1.0
 * @desc : 
 */

package com.plgrim.ncp.base.entities.datasource1.sys;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.apache.ibatis.type.Alias;

/**
 * 시스템 그룹 코드
 */
@Data
@EqualsAndHashCode(callSuper=false)
@Alias("sysGrpCdExtend")
public class SysGrpCdExtend extends SysGrpCd{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 그룹코드명
	 */
	private String grpNm;
	/**
	 * 상위그룹코드명
	 */
	private String upperGrpNm;
	/**
	 * 영어 그룹코드명
	 */
	private String engGrpNm;
	/**
	 * 중국 그룹코드명
	 */
	private String chiGrpNm;
	
	/**
	 * 등록자명
	 */
	private String regtrNm;
	
	/**
	 * 수정자명
	 */
	private String udterNm;
	
}
