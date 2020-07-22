package com.plgrim.ncp.interfaces.member.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.plgrim.ncp.base.abstracts.AbstractSDO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
public class MileageHistInofSDO extends AbstractSDO {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 마일리지 생성/사용 일자
	 */
	@JsonProperty("MILEAGE_DATE")
	private String mileageDate;
	/**
	 * 마일리지
	 */
	@JsonProperty("MILEAGE_NM")
	private String mileageNm;
	/**
	 * 몰 CODE
	 */
	@JsonProperty("BRAND_NAME")
	private String brandName;
	/**
	 * 주문번호
	 */
	@JsonProperty("ORD_NO")
	private String ordNo;
	/**
	 * 마일리지 사용 금액
	 */
	@JsonProperty("MILEAGE_USE_POINT")
	private String mileageUsePoint;
	/**
	 * 마일리지 적립/복원 금액
	 */
	@JsonProperty("MILEAGE_RESTORE_POINT")
	private String mileageRestorePoint;
}