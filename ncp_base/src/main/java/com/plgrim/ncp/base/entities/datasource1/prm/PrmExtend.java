/**
 * @package : com.plgrim.ncp.base.entities..prm
 * @author : yoon(yoon)
 * @date : 20151029
 * @version : 1.0
 * @desc : 
 */

package com.plgrim.ncp.base.entities.datasource1.prm;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.type.Alias;

/**
 * 프로모션
 */
@Data
@EqualsAndHashCode(callSuper=false)
@Alias("prmExtend")
public class PrmExtend extends Prm{
	private java.math.BigDecimal maxDcPsbAmtEx; // 최대 할인 가능 환율 적용 금액
	private java.math.BigDecimal dcAmtEx; // 할인 환율 적용 금액
	private java.math.BigDecimal saleUntPrcEx;	// 판매 환율 적용 단가
	private java.math.BigDecimal ordGftAplCndAmtEx; // 주문 사은품 적용 조건 환율 적용 금액
}
