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

import com.plgrim.ncp.base.abstracts.AbstractEntity;

/**
 * 시스템 그룹 코드 코드 연결
 */
@Data
@EqualsAndHashCode(callSuper=false)
@Alias("sysGrpCdCdCnncExtend")
public class SysGrpCdCdCnncExtend extends SysGrpCdCdCnnc{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 코드명
	 */
	private String cdNm;

	 /**
	 * 영어 코드명
	 */
	private String engCdNm;
	/**
	 * 중국 코드명
	 */
	private String chiCdNm;
	
	/**
	 * 등록자명
	 */
	private String regtrNm;
	
	/**
	 * 수정자명
	 */
	private String udterNm;
	
	/**
	 * 코드 설명
	 */
	private String cdDscr;

}
