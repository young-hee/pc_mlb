package com.plgrim.ncp.biz.claim.data;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractDTO;

@Data
@EqualsAndHashCode(callSuper = false)
public class ClmPaySumAmtSearchDTO extends AbstractDTO {
	
	private String ordNo;
	
	private String clmTpCd;
	
	private String clmStatCd;
	
	private String dlvPcupspTurn;

	/**
	 * 국내 배송비 정책 일련번호 : 2015-12-07 추가 [AshA]
	 */
	private java.lang.Long dmstcDlvCstPlcSn;
	/**
	 * 해외 배송 국내 배송비 정책 일련번호 : 2015-12-07 추가 [AshA]
	 */
	private java.lang.Long ovseaDlvDmstcDlvCstPlcSn;
	/**
	 * 해외 배송비 정책 일련번호 : 2015-12-07 추가 [AshA]
	 */
	private java.lang.Long ovseaDlvCstPlcSn;
}
