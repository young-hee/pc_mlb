/**
 * @package : com.plgrim.ncp.base.entities..ord
 * @author : jackie(jackie)
 * @date : 20150707
 * @version : 1.0
 * @desc :
 */

package com.plgrim.ncp.base.entities.datasource1.ord;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.apache.ibatis.type.Alias;

/**
 * 주문 상품
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Alias("ordGodExtends")
public class OrdGodExtends extends OrdGod {
	/**
	 * UUID
	 */
	private static final long serialVersionUID = 1L;

	private String invManageYn;

	private String lmttInvYn;

	private String basicPackYn;

	private String pckageGodTpCd; // '17 8/30 이진형 MERGE

	private int totUsefulInvQty;

	private boolean isGift;

	/**
	 * 원 주문 수량
	 */
	private java.lang.Long oriOrdQty;

	/**
	 * 배송 수거지 순번
	 */
	private java.lang.Integer dlvPcupspTurn;

	/**
	 * 배송 순번
	 */
	private java.lang.Integer dlvTurn;

	/**
	 * 클레임 적용 여부
	 */
	private boolean isClmApply = false;

}
