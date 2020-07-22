package com.plgrim.ncp.commons.data.order;

import com.plgrim.ncp.commons.data.AbstractOrderDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = false)
public class OrderResultDTO  extends AbstractOrderDTO {

	private String RESPONSE_CD;
	
}
