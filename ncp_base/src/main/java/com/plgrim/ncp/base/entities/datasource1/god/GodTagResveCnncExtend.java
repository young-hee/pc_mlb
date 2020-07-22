/**
 * @package : com.plgrim.ncp.base.entities..god
 * @author : jackie(jackie)
 * @date : 20150601
 * @version : 1.0
 * @desc : 
 */

package com.plgrim.ncp.base.entities.datasource1.god;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.apache.ibatis.type.Alias;

/**
 * 상품 태그 예약 연결
 */
@Data
@EqualsAndHashCode(callSuper=false)
@Alias("godTagResveCnncExtend")
public class GodTagResveCnncExtend extends GodTagResveCnnc {
	
	/**
	 * 
	 */
    private static final long serialVersionUID = 7561825779456574150L;

	/**
	 * 등록 일시	 
	 */
	private String regDtStr;

	/**
	 * 수정 일시	 
	 */
	private String udtDtStr;

}
