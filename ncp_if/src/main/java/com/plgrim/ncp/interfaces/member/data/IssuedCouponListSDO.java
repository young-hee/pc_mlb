package com.plgrim.ncp.interfaces.member.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.plgrim.ncp.base.abstracts.AbstractSDO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
public class IssuedCouponListSDO extends AbstractSDO {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 발급 쿠폰 유형
	 */
	@JsonProperty("ISSUED_COUPON_TYPE")
	private String issuedCouponType;
	/**
	 * 발급 쿠폰 발행번호
	 */
	@JsonProperty("ISSUED_COUPON_NO")
	private String issuedCouponNo;
}