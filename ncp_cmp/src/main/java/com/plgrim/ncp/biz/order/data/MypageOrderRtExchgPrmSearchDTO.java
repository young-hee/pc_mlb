package com.plgrim.ncp.biz.order.data;


import com.plgrim.ncp.base.abstracts.AbstractDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class MypageOrderRtExchgPrmSearchDTO extends AbstractDTO {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5836627492662374733L;

	/** 주문 번호 */
	private String ordNo;
	
	/** 회원 번호 */
	private String mbrNo;
	
}
