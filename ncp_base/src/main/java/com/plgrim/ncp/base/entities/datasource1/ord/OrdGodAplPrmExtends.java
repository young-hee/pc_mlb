/**
 * @package : com.plgrim.ncp.base.entities..ord
 * @author : jackie(jackie)
 * @date : 20150807
 * @version : 1.0
 * @desc : 
 */

package com.plgrim.ncp.base.entities.datasource1.ord;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.apache.ibatis.type.Alias;

/**
 * 주문 상품 적용 프로모션 확장
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Alias("ordGodAplPrmExtends")
public class OrdGodAplPrmExtends extends OrdGodAplPrm {
	/**
	 * UUID
	 */
	private static final long serialVersionUID = 3132290673749868515L;

	private String godNo = null;

	private String cpnCrtfcCd = null;

	private boolean isOffLineCoupon = false;

}
