/**
 * @package : com.plgrim.ncp.base.entities..prm
 * @author : emily(emily)
 * @date : 20150814
 * @version : 1.0
 * @desc :
 */

package com.plgrim.ncp.base.entities.datasource1.prm;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.type.Alias;

/**
 * 프로모션 쿠폰
 */
@Data
@EqualsAndHashCode(callSuper=false)
@Alias("prmCpnExtend")
public class PrmCpnExtend extends PrmCpn{
	private java.math.BigDecimal cpnUseMinPchAmtEx; // 쿠폰 사용 최소 구매 환율 적용 금액
	private java.math.BigDecimal cpnMaxDcPsbAmtEx; // 쿠폰 최대 할인 가능 환율 적용 금액


	private java.math.BigDecimal cpnAplAmt; //네이버EP쿠폰 적용가
	private java.math.BigDecimal lastSalePrc; //최종판매가
	private java.math.BigDecimal CPN_DC_AMT; //쿠폰사용시 할인되는 액
	private String godNo;
	private String brndId;
	private String brndTopId;
}
