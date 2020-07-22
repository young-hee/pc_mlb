package com.plgrim.ncp.biz.bi.result;

import org.apache.ibatis.type.Alias;

import com.plgrim.ncp.base.abstracts.AbstractResult;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Alias("biStatisticMvSysCd")
public class BiStatisticMvSysCdResult extends AbstractResult{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7500528792609800312L;

	/**
	 * 코드
	 * 표준 용어 사전에 기초한 약어의 조합을 사용	 
	 */
	private String cd;


	/**
	 * 코드 명
	 * 기본 언어의 코드 명을 입력	 
	 */
	private String cdNm;

	/**
	 *
	 */
	private String asstnCd1;
	
	
	
}
