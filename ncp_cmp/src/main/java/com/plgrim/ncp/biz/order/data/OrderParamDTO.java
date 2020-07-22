package com.plgrim.ncp.biz.order.data;

import com.plgrim.ncp.base.abstracts.AbstractDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class OrderParamDTO extends AbstractDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7903260877032998751L;
	
	
	String ordNo;
	
	String clmNo;
	
	String requestId;
	
	String brandCode;
	
	String realMileYn;
	
	String quantityType;
	
	String assignDlvShopId;
	
	String dlivyDrctGodNo;
	
	String unAssignedOrderYn;
	
}
