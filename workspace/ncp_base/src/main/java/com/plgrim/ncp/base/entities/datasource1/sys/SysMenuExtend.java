/**
 * @package : com.plgrim.ncp.base.entities..sys
 * @author : jackie(jackie)
 * @date : 20150417
 * @version : 1.0
 * @desc : 
 */

package com.plgrim.ncp.base.entities.datasource1.sys;

import java.math.BigInteger;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.apache.ibatis.type.Alias;

/**
 * 시스템 메뉴
 */
@Data
@EqualsAndHashCode(callSuper=false)
@Alias("SysMenuExtend")
public class SysMenuExtend extends SysMenu{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	 /**
     * dhtmlx 용 id
     */
    private BigInteger id;
    
    /**
     * 상위메뉴코드
     */
    private BigInteger upperKey;
    
	/**
	 * 메뉴 Depth
	 */
	private BigInteger lvl;
	
	/**
	 * 하위 메뉴 갯수
	 */
	private BigInteger childCount;
	
	/**
	 * 하위 메뉴 리스트
	 */
	private List<SysMenuExtend> rows;

}
