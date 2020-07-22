/**
 * @package : com.plgrim.ncp.base.entities..god
 * @author : jackie(jackie)
 * @date : 20150420
 * @version : 1.0
 * @desc : 
 */

package com.plgrim.ncp.base.entities.datasource1.god;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.apache.ibatis.type.Alias;

/**
 * 상품 표준 카테고리
 */
@Data
@EqualsAndHashCode(callSuper=false)
@Alias("godStdCtgryExtend")
public class GodStdCtgryExtend extends GodStdCtgry {
	
	/**
	 * 
	 */
    private static final long serialVersionUID = 9051356147191357507L;
	
    private String level;
    
    /**
	 * 품목 코드명	 
	 */
	private String prdlstNm;
	
	/**
	 *	표준카테고리 Path 	 
	 */
	private String stdCtgryPath;

	/**
	 * 몰 ID
	 */
	private String mallId;

	/**
	 * 품목 코드
	 */
	private String prdlstCd;
}
