/**
 * @package : com.plgrim.ncp.base.entities..lgs
 * @author : jackie(jackie)
 * @date : 20150526
 * @version : 1.0
 * @desc : 
 */

package com.plgrim.ncp.base.entities.datasource1.lgs;

import com.plgrim.ncp.base.entities.datasource1.ord.OrdGodFoExtend;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.type.Alias;

import java.util.List;

/**
 * 물류 배송
 */
@Data
@EqualsAndHashCode(callSuper=false)
@Alias("lgsDlvFoExtend")
public class LgsDlvFoExtend extends LgsDlv{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	/**
	 * 배송 수거지 순번	 
	 */
	private java.lang.Integer dlvPcupspTurnOld; /* 픽업배송 전환시 배송지 합칠때 이전 배송지 원배송 수거지 순번 */

	private List<OrdGodFoExtend> ordGodList;

	/**
	 * 업체 명
	 */
	private String comNm;
	
	/**
	 * 계산 전 실제 배송비	 
	 */
	private java.math.BigDecimal oriRealityDlvCst;

	
	private java.math.BigDecimal befCnclDlvCst;
	




	private java.math.BigDecimal stdrCrncyAmtEx;
	/**
	 * 결제 환율 통화 금액
사이트의 기준 통화를 기준으로 기준 통화금액에 환율을 적용한 금액
	 */
	private java.math.BigDecimal payExchgRtCrncyAmtEx;
	/**
	 * 실제 배송비
	 */
	private java.math.BigDecimal realityDlvCstEx;
	/**
	 * 정책 배송비
	 */
	private java.math.BigDecimal plcDlvCstEx;
	/**
	 * 변경된 정책 배송비
	 */
	private java.math.BigDecimal chgPlcDlvCst;
	/**
	 * 취소 배송비
	 */
	private java.math.BigDecimal cnclDlvCstEx;
	/**
	 * 반품 배송비
	 */
	private java.math.BigDecimal rtgodDlvCstEx;
	/**
	 * 교환 배송비
	 */
	private java.math.BigDecimal exchgDlvCstEx;
	/**
	 * 배송비 쿠폰 할인 금액
	 */
	private java.math.BigDecimal dlvCstCpnDcAmtEx;


	/**
	 * 주문 초도 배송비 금액
	 */
	private java.math.BigDecimal ordDlvCst;
	
	/**
	 * 반품 수거비 금액
	 */
	private java.math.BigDecimal rtnDlvCst;
}
