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
public class InfGodSizeLktbPomSDO  extends InterfaceSDO {
	private static final long serialVersionUID = -562850250038821814L;

	/**
	 * 순서 ㅁ. SEQ : 시퀀스
	 */
	private java.lang.Integer seq;
	
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
	 * POM 코드
	 * ㅁ. POM : 폼 코드	 
	 */
	@JsonProperty("POM")
	private String pomCd;


	/**
	 * POM 명
	 * ㅁ. NAME : 폼 이름	 
	 */
	@JsonProperty("NAME")
	private String pomNm;

	/**
	 * 영어 POM 명	 
	 */
	@JsonProperty("NAME_ENG")
	private String engPomNm;	
	
	/**
	 * 중국어 POM 명	 
	 */
	@JsonProperty("NAME_CHI")
	private String chiPomNm;

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
