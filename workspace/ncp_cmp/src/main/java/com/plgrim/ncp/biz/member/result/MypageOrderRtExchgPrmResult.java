package com.plgrim.ncp.biz.member.result;


import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractResult;

@Data
@EqualsAndHashCode(callSuper = false)
public class MypageOrderRtExchgPrmResult extends AbstractResult {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5836627492662374735L;
	
	/** 상품 번호 */
	private String godNo;
	
	/** 프로모션 번호 */
	private String prmNo;
	
	/** 프로모션 세부 유형 */
	private String prmDtlTpCd;

}

