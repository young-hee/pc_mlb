/**
 * @package : com.plgrim.ncp.base.entities..sys
 * @author : ()
 * @date : 20150730
 * @version : 1.0
 * @desc : 
 */

package com.plgrim.ncp.base.entities.datasource1.sys;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.apache.ibatis.type.Alias;

/**
 * 시스템 매장
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Alias("sysShopExtends")
public class SysShopExtends extends SysShop {
	
	private static final long serialVersionUID = 6062519623491991985L;

	private String brndId;

	private String brndNm;

	private String erpBrndId;
	
	private String pkupShopVisitDate;
	
	private String shopTpCdVal;
	
	/**
	 * 영업 시작 시분 HH:MM
	 */
	private String bsnBegHhmm;
	
	/**
	 * 영업 종료 시분 HH:MM
	 */
	private String bsnEndHhmm;
	
	/**
	 * 재고 수량	 
	 */
	private java.lang.Long invQty;
	
	/**
	 * 수령 가능 기간
	 */
	private String recptPdrgSect;
	
	private String pkupDay;
	
	private String crntDay;

	/**
	 * 픽업매장여부
	 */
	private String pkupShopYn;
	
	private String holdyYn;
	private String shopEndHhmm;
	private String shopBegHhmm;
	
}
