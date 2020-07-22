package com.plgrim.ncp.biz.delivery.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
@JsonInclude(value=Include.NON_EMPTY)
public class LgsDlivyWayBil {

	@JsonProperty("ORD_NO")
	private String ordNo;				// 주문번호
	@JsonProperty("DMSTC_WAYBIL_NO")
	private String dmstcWaybilNo;		// 운송장번호              
	private String dlvPcupspTurn;		// 배송수거지순번
	private String dlvTurn;				// 배송순번
	private String dlivyDrctGodNo;				// 배송순번

	@JsonProperty("CUST_USE_NO")
	private String custUseNo;				// 주문번호
	/**
	 * 회원번호
	 */
	private String mbrNo;
	private String cstmrNm;
	private String mobileNo;
	private String cstmrEmail;

	/**
	 * 수정자
	 */
	private String udterId;

	private String flagParam;
}
