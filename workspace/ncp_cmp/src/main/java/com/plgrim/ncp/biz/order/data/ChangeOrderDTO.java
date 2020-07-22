package com.plgrim.ncp.biz.order.data;

import com.plgrim.ncp.base.abstracts.AbstractDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ChangeOrderDTO extends AbstractDTO {

	private String sourceOrdNo;
	private String ordNo;
	private String sourceClmNo;
	
}
