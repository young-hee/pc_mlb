package com.plgrim.ncp.biz.claim.result;

import com.plgrim.ncp.base.abstracts.AbstractResult;
import com.plgrim.ncp.base.entities.datasource1.prm.PrmCpnExtend;
import com.plgrim.ncp.base.entities.datasource1.prm.PrmExtend;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ClmGoodsCouponResult extends AbstractResult {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5326353330656235695L;

	/**
	 * 프로모션
	 */
	private PrmExtend prm;

	/**
	 * 프로모션 쿠폰
	 */
	private PrmCpnExtend prmCpn;

	private boolean selectedCoupon;

	private boolean isAvailble;

	private String mbrCpnNo;

	private String cpnCrtfcCd;
	
	//온오프라인쿠폰 ERP 체크여부
	private boolean isOnoffCheckedErp;
	
	//온오프라인쿠폰 체크여부(I/F)
	private boolean isOnoffCheckedAvailble;

}
