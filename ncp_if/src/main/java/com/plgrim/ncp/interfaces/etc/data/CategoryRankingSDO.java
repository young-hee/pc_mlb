package com.plgrim.ncp.interfaces.etc.data;

import org.apache.ibatis.type.Alias;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.plgrim.ncp.base.abstracts.AbstractSDO;
import com.plgrim.ncp.base.enums.DisplayEnum;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@JsonInclude(value=Include.NON_EMPTY)
@Alias("categoryRankingSDO")
public class CategoryRankingSDO extends AbstractSDO {

    private static final long serialVersionUID = 1L;
    
    /**
     * 브랜드코드
     */
    @JsonProperty("BRAND")
    private String brand;
    
    /**
     * 몰 구분
     */
    @JsonProperty("MALLID")
    private String mallId;
    
    /**
     * 요청수량
     */
    @JsonProperty("QTY")
    private String qty;
    
    /**
	 * 매출 일자
	 */
    @JsonProperty("SALEDATE")
	private String saleDate;
    
	/**
	 * ERP 상품 번호 ㅁ. 
	 */
	@JsonProperty("ERP_CODE")
	private String erpGodNo;
	
	/**
	 * 색상 코드 ㅁ. COLOR_CODE : 컬러코드
	 */
	@JsonProperty("COLOR_CODE")
	private String colorCd;
    
	/**
	 * 순번 ㅁ. SEQ : 순번
	 */
	@JsonProperty("SEQ")
	private String seq;
	
	/**
	 * 카테고리 ㅁ. CATEGORY : 카테고리
	 */
	@JsonProperty("CATEGORY")
	private String category;
	
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
	
	/**
	 * 코너 일련번호	 
	 */
	private java.lang.Long cnrSn;
	
	/**
	 * 카테고리 일련번호	 
	 */
	private java.lang.Long cnrSetSn;
	
	/**
	 * 개정 일련번호	 
	 */
	private java.lang.Long revSn;
	
	/**
	 * 컨텐츠 유형 코드
	 */
	private String conttTpCd;
	
	/**
	 * 스티커 노출 여부
	 */
	private String stkExpsrYn;
	
	/**
	 * 정렬 순서 우선 순위
	 */
	private java.lang.Long sortSeq;
	
	/**
	 * 전시 여부
	 */
	private String dspYn;
}