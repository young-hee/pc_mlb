/**
 * @package : com.plgrim.ncp.base.entities..inf
 * @author : ()
 * @date : 20150804
 * @version : 1.0
 * @desc : 
 */

package com.plgrim.ncp.base.entities.datasource1.inf;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.apache.ibatis.type.Alias;

import java.math.BigDecimal;

/**
 * 인터페이스 주문 상품 ERP 분배
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Alias("infOrdGodErpDstbExtends")
public class InfOrdGodErpDstbExtends extends InfOrdGodErpDstb {
	/**
	 * UUID
	 */
	private static final long serialVersionUID = -8226112464733225576L;

	private String giftYn;

	private String zlsch;

	private String empNo;

	private String eindt;

	private String pickupShopId;

	private String ilmoCardNo;

	private String payMnCd;

	private String sumPayAmt;

    private BigDecimal namt;
}
