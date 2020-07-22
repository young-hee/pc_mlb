package com.plgrim.ncp.biz.order.data;

import com.plgrim.ncp.base.abstracts.AbstractDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class NaverPayApproveDTO extends AbstractDTO {

	private static final long serialVersionUID = -1229254201978497971L;
	
	private String resultCode;
	
	private String paymentId;
	
	private Long tempTotalPayment;
	

}
