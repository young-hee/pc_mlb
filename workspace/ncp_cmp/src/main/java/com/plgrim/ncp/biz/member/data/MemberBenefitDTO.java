package com.plgrim.ncp.biz.member.data;

import com.plgrim.ncp.base.abstracts.AbstractDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 회원 혜택 DTO. 
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class MemberBenefitDTO extends AbstractDTO {
	
	private static final long serialVersionUID = 1L;

	/**
	 * ERP 고객번호
	 */
	private String erpCstmrNo;
	
	/**
	 * 혜택 구분 코드	 
	 */
	private String bnefSectCd;
	
	/**
	 * 혜택 상세 구분 코드	 
	 */
	private String bnefDetailSectCd;
	
	/**
	 * 혜택 일련번호	 
	 */
	private java.lang.Long bnefSn;
}
