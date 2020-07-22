/**
 * @package : com.plgrim.ncp.base.entities..ord
 * @author : jackie(jackie)
 * @date : 20150707
 * @version : 1.0
 * @desc :
 */

package com.plgrim.ncp.base.entities.datasource1.ord;

import org.apache.ibatis.type.Alias;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 주문 상품
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Alias("ordGodInv")
public class OrdGodInv extends OrdGod {
	
	private String ordTpCd;
	private int realInvQty;
	private int hoffInvQty;
	private int reserveInvQty;
	
}
