/**
 * @package : com.plgrim.ncp.base.entities..sys
 * @author : jackie(jackie)
 * @date : 20150814
 * @version : 1.0
 * @desc : 
 */

package com.plgrim.ncp.base.entities.datasource1.sys;

import com.plgrim.ncp.base.abstracts.AbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.type.Alias;

/**
 * 시스템 코드
 */
@Data
@EqualsAndHashCode(callSuper=false)
@Alias("sysExcpCdExtend")
public class SysExcpCdExtend extends SysExcpCd{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 그룹 코드명
	 */
	private String grpCdNm;

}
