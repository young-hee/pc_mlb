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
public class InfGodItmInvSDO extends InterfaceSDO{
	private static final long serialVersionUID = 7768965021417213152L;


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
	 * 매장 ID
	 * ㅁ. SHOPCODE : 매장 코드	 
	 */
	@JsonProperty("SHOPCODE")
	private String shopId;


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
	 * ERP 상품 번호
	 * ㅁ. ERP_CODE : 제품코드	 
	 */
	private String erpGodNo;


	/**
	 * 색상 코드
	 * ㅁ. COLOR : 컬러코드	 
	 */
	@JsonProperty("COLOR")
	private String colorCd;


	/**
	 * 옵션 값 코드
	 * ㅁ. SIZE : 사이즈	 
	 */
	@JsonProperty("SIZ")
	private String optValCd;


	/**
	 * 재고 수량
	 * ㅁ. QTY : 수량	 
	 */
	@JsonProperty("QTY")
	private String invQty;


	/**
	 * 본사 물류 재고 수량
	 * ㅁ. 본사 물류 재고 수량 : ERP_QTY	 
	 */
	@JsonProperty("ERP_QTY")
	private String hoffLgsInvQty;


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
