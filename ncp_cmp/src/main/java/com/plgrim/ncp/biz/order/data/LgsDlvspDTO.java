package com.plgrim.ncp.biz.order.data;

import java.math.BigDecimal;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractDTO;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsDlv;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsDlvsp;

@Data
@EqualsAndHashCode(callSuper = false)
public class LgsDlvspDTO extends AbstractDTO {
	/**
	 * UUID
	 */
	private static final long serialVersionUID = -4026047283266598749L;

	private LgsDlvsp lgsDlvsp;

	private List<LgsDlv> lgsDlvList;

	private List<OrdGodDTO> ordGodDTOList;

	private List<CouponDTO> couponDTOList;

	private String defaultDelv;

	//배송지목록에 추가 체크박스 체크여부
	private String addMbrDlvspCheck;

	private int dlvAdbukTurn;

	private String adbukNm;

	private BigDecimal deliveryFee;
	private BigDecimal deliveryFeeEx; // 배송비 환율 금액 2016.02.27

	private String deliveryCouponNo = null;

	private String deliveryCouponNm = null;

	private String deliveryCouponAmt = null;

	private String cstmrNm = null;

	private String cstmrEmail = null;

	private String cstmrMobilNo = null;

	private String cstmrMobilNationNo = null;

	private String cstmrMobilAreaNo = null;

	private String cstmrMobilTlofNo = null;

	private String cstmrMobilTlofWthnNo = null;

	private String cstmrTelNo = null;

	private String cstmrTelNationNo = null;

	private String cstmrTelAreaNo = null;

	private String cstmrTelTlofNo = null;

	private String cstmrTelTlofWthnNo = null;

	private String dlvMnCd = null;
}
