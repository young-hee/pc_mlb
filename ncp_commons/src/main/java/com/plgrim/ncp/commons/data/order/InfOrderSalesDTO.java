package com.plgrim.ncp.commons.data.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.plgrim.ncp.base.entities.datasource1.inf.InfOrdGodErpDstb;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 매출 전용 DTO
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class InfOrderSalesDTO extends InfOrdGodErpDstb {
	
	private String saleTrType;
	private String sizeCd;
	private String colorCd;
	private String partCode;
	private String seasonCd;
	private String brand;
	
	private String payMainMnCd;
	
	private String erpGodNo;
	private String erpMbrSeqNo;
	private String erpEmpMbrNo;
	
	private String erpShopId;
	private String clmTpCd;
	private String issueNo;
	private String rtrvlDrctTpCd;
	private String realMileYn;
	
	// 가상계좌결제 또는 주문 매장배송건만 포함한 주문의경우 Y
	private String tmprMileDdctYn;

	private String payDt;
	private String clmComptDt;
	
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String dlivyDrctTpCd;
	
	private String quantityType;
}
