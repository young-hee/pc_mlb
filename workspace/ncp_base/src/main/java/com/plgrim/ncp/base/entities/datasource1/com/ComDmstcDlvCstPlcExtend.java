/**
 * @package : com.plgrim.ncp.base.entities..com
 * @author : asha
 * @date : 20151124
 * @version : 1.0
 * @desc : 
 */

package com.plgrim.ncp.base.entities.datasource1.com;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.apache.ibatis.type.Alias;

/**
 * 업체 국내 배송비 정책 - 확장
 */
@Data
@EqualsAndHashCode(callSuper=false)
@Alias("comDmstcDlvCstPlcExtend")
public class ComDmstcDlvCstPlcExtend extends ComDmstcDlvCstPlc{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6704469461203443739L;
	
	/**
	 * 업체 명
	 */
	private String comNm;

}
