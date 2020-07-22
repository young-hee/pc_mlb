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
public class InfGodSizeLktbSDO extends InterfaceSDO {
	private static final long serialVersionUID = -6041914855567246757L;

	/**
	 * 순서 ㅁ. SEQ : 시퀀스
	 */
	private java.lang.Integer seq;
	
	/**
	 * 브랜드 ID ㅁ. BRAND : 브랜드코드 >. 디스커버리 : X >. MLB : M >. MLB Kids : I
	 */
	@JsonProperty("BRAND")
	private String brndId;

	/**
	 * 시즌 코드 ㅁ. SEASON : 시즌 코드 ㅁ. ERP에서 연계 받는 코드
	 */
	@JsonProperty("SEASON")
	private String sesonCd;

	/**
	 * 디자인 그룹 번호 ㅁ. PARTCODE : 품번 코드
	 */
	@JsonProperty("PARTCODE")
	private String dsgnGrpNo;

	/**
	 * POM 코드 ㅁ. POM : 폼 코드
	 */
	@JsonProperty("POM")
	private String pomCd;

	/**
	 * 옵션 값 코드 ㅁ. SIZE : 사이즈
	 */
	@JsonProperty("SIZE")
	private String optValCd;

	/**
	 * 옵션 값 명 ㅁ. GRADATION : 값
	 */
	@JsonProperty("GRADATION")
	private String optValNm;

	/**
	 * 등록자 ID 등록한 관리자 번호
	 */
	private String regtrId;

	/**
	 * 등록 일시
	 */
	private java.util.Date regDt;

	/**
	 * 수정자 ID 수정한 관리자 번호
	 */
	private String udterId;

	/**
	 * 수정 일시
	 */
	private java.util.Date udtDt;

}
