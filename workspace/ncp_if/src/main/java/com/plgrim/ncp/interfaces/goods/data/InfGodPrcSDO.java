package com.plgrim.ncp.interfaces.goods.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.plgrim.ncp.interfaces.abstracts.InterfaceSDO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
@JsonInclude(value = Include.NON_EMPTY)
public class InfGodPrcSDO extends InterfaceSDO{
	private static final long serialVersionUID = 6192525107130982705L;

	/**
	 * 순서
	 * ㅁ. SEQ : 시퀀스	 
	 */
	@JsonProperty("SEQ")
	private String seq;
	
	/**
	 * 브랜드 ID
	 * ㅁ. BRAND : 브랜드코드
	 *    >. 디스커버리 : X
	 *    >. MLB : M
	 *    >. MLB Kids : I	 
	 */
	@JsonProperty("BRAND")
	private String brndId;


	/**
	 * 시즌 코드
	 * ㅁ. SEASON : 시즌 코드
	 * ㅁ. ERP에서 연계 받는 코드	 
	 */
	@JsonProperty("SEASON")
	private String sesonCd;


	/**
	 * 디자인 그룹 번호
	 * ㅁ. PARTCODE : 품번 코드	 
	 */
	@JsonProperty("PARTCODE")
	private String dsgnGrpNo;


	/**
	 * 실소가
	 * ㅁ. PRICE : 판매가(실소가)	 
	 */
	@JsonProperty("PRICE")
	private String csmrPrc;


	/**
	 * 실소가 유형 코드
	 * ㅁ. SALETRTYPE : 판매가유형	 
	 */
	@JsonProperty("SALETRTYPE")
	private String csmrPrcTpCd;


	/**
	 * 등록자 ID
	 * 등록한 관리자 번호	 
	 */
	private String regtrId;


	/**
	 * 등록 일시	 
	 */
	private java.util.Date regDt;


	/**
	 * 수정자 ID
	 * 수정한 관리자 번호	 
	 */
	private String udterId;


	/**
	 * 수정 일시	 
	 */
	private java.util.Date udtDt;

}
