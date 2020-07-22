package com.plgrim.ncp.base.entities.datasource1.com;

import org.apache.ibatis.type.Alias;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
@Alias("comOvseaDlvZoneNationExtend")
public class ComOvseaDlvZoneNationExtend extends ComOvseaDlvZoneNation {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1426759146976332321L;

	/**
	 * 언어코드
	 */
	private String langCd;

	/**
	 * 배송 국가 코드명
	 */
	private String dlvNationNm;

	/**
	 * 국가 전화번호
	 */
	private String asstnCd1;

}
