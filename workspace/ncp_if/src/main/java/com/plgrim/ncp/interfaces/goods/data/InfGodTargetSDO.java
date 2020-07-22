package com.plgrim.ncp.interfaces.goods.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.plgrim.ncp.interfaces.abstracts.InterfaceSDO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
@JsonInclude(value = Include.NON_EMPTY)
public class InfGodTargetSDO extends InterfaceSDO{
	private static final long serialVersionUID = -3078213173831157161L;
	
	/*******************************************************************************
     * Request parameter
     *******************************************************************************/	

	/**
	 * 시작 시퀀스 
	 */
	@JsonProperty("START_SEQ")
	private String startSeq;
	
	/**
	 * 종료 시퀀스
	 */
	@JsonProperty("END_SEQ")
	private String endSeq;

	/**
	 * 브랜드코드 ㅁ.X : Discovery Expedition 
	 */
	@JsonProperty("BRAND")
	private String brand;
	
	/**
	 * 매장코드 ㅁ.30004 : Discovery Expedition 온라인매장
	 */
	@JsonProperty("SHOPCODE")
	private String shopCode;
	
	/**
	 * 시즌코드 ㅁ.18S : 2018년도 SS
	 */
	@JsonProperty("SEASON")
	private String season;
	
	/**
	 * 가격 / 재고연동구분코드
	 */
	@JsonProperty("FLAG")
	private String flag;
	
	/**
	 * 품번코드
	 */
	@JsonProperty("PARTCODE")
	private String dsgnGrpNo;
	
	/**
	 * 색상 코드	 
	 */
	@JsonProperty("COLOR")
	private String colorCd;
	
	/**
	 * 사이즈 코드
	 */
	@JsonProperty("SIZ")
	private String optCdVal;
	
	/*******************************************************************************
     * Response parameter
     *******************************************************************************/
	
	/**
	 * 상품연동 Target Row count
	 */
	@JsonProperty("PRODUCT")
	private String product;
	
	/**
	 * 가격 연동 Target Row count
	 */
	@JsonProperty("PRICE")
	private String price;
	
	/**
	 * 재고 연동 Target Row count
	 */
	@JsonProperty("QTY")
	private String qty;
	
	/**
	 * 소재연동 Target Row count
	 */
	@JsonProperty("MATERIAL")
	private String material;
	
	/**
	 * 사이즈조견표 연동 Target Row count
	 */
	@JsonProperty("SIZE_REF")
	private String sizeRef;
	
	/**
	 * 사이즈조견표 POM연동 Target Row count
	 */
	@JsonProperty("SIZE_REF_POM")
	private String sizeRefPom;	
}
