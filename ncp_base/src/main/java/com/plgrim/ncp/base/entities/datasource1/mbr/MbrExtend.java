/**
 * @package : com.plgrim.ncp.bo.order.base.entities.order.mbr
 * @author : mason(김원섭)
 * @date : 20150721
 * @version : 1.0
 * @desc : 
 */

package com.plgrim.ncp.base.entities.datasource1.mbr;

import com.plgrim.ncp.base.abstracts.AbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.type.Alias;

/**
 * 회원
 */
@Data
@EqualsAndHashCode(callSuper=false)
@Alias("mbrExtend")
public class MbrExtend extends Mbr{
	/**
	 * 국가명
	 * */
	private String nationNm;
}
