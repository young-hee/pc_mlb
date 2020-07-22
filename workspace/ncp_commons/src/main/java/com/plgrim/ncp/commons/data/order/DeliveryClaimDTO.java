package com.plgrim.ncp.commons.data.order;

import com.plgrim.ncp.commons.data.AbstractOrderDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class DeliveryClaimDTO extends AbstractOrderDTO{

	private String clmNo;
	
}
