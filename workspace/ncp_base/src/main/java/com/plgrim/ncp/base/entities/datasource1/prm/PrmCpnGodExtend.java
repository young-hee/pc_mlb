package com.plgrim.ncp.base.entities.datasource1.prm;

import org.apache.ibatis.type.Alias;

import com.plgrim.ncp.base.entities.datasource1.prm.Prm;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Alias("prmCpnGodExtend")
public class PrmCpnGodExtend extends Prm {
	private static final long serialVersionUID = -6053971758247750163L;
	
	/**
	 * 다운로드 쿠폰 상품 상세 노출 여부
	 * ㅁ. 다운로드 쿠폰을 상품 상세에서 노출 할지 선택 하는 구분
	 *    >. Y : 노출, N : 노출안함	 
	 */
	private String dwldCpnGodDetailExpsrYn;
	
	/**
	 * 쿠폰 발급 방법 코드
	 * ㅁ. 쿠폰 발급 방법 : CPN_ISSU_MTHD
	 *    >. 즉시 할인 : IMDTL_DC
	 *    >. 다운로드 : DWLD
	 *    >. 계정 발급 : ACNT_ISSU
	 *    >. 인증 코드 : CRTFC_CD	 
	 */
	private String cpnIssuMthdCd;	
	
	/**
	 * 최종 할인 율
	 * 할인 유형별 차감되는 할인 율	 
	 */
	private java.math.BigDecimal lastDcRt;	

	/**
	 * 최종 할인 금액
	 * 할인 유형별 차감되는 할인 금액	 
	 */
	private java.math.BigDecimal lastDcAmt;	
	
	/**
	 * 최종 할인이 적용된 금액
	 * 할인 유형별 차감되는 할인 금액	 
	 */
	private java.math.BigDecimal lastDcAplAmt;	
}
