/**
 * @package : com.plgrim.ncp.base.entities..lgs
 * @author : jackie(jackie)
 * @date : 20150526
 * @version : 1.0
 * @desc : 
 */

package com.plgrim.ncp.biz.claim.data;


import lombok.Data;
import lombok.EqualsAndHashCode;

import org.apache.ibatis.type.Alias;

import com.plgrim.ncp.base.entities.datasource1.lgs.LgsDlv;

/**
 * 물류 배송
 */
@Data
@EqualsAndHashCode(callSuper=false)
@Alias("lgsDlvExtendForClm")
public class LgsDlvExtendForGlobalCancel extends LgsDlv{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6756139506616552289L;

	/**
	 * 잔여결제환율적용금액
	 */
	private java.math.BigDecimal remainPayExchgRtCrncyAmt;

}
