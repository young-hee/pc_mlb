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
@Alias("rankingSDO")
public class RankingSDO extends AbstractSDO {

    private static final long serialVersionUID = 1L;
    
    /**
     * 브랜드코드
     */
    private String brand;
    
    /**
     * 몰 구분
     */
    @JsonProperty("BRAND")
    private String mallId;
    
    /**
     * 몰 구분
     */
    @JsonSetter("BRAND")
	public void setMallId(String brand){
    	if("M".equals(brand)){
    		this.mallId = DisplayEnum.MBM_MALL.toString();
    	}else if("A".equals(brand)){
    		this.mallId = DisplayEnum.SAM_MALL.toString();
    	}else {
    		this.mallId = DisplayEnum.DXM_MALL.toString();
    	}
    }
    
    /**
     * 요청수량
     */
    private String qty;
    
    /**
	 * 매출 일자
	 */
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
	 * 국가코드 ㅁ. COUNTRY : 국가
	 */
	@JsonProperty("COUNTRY")
	private String country;
	
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
}