package com.plgrim.ncp.base.entities.datasource1.inf;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.apache.ibatis.type.Alias;

/**
 * 상품 핏 조견표
 */
@Data
@EqualsAndHashCode(callSuper=false)
@Alias("infGodFitLktbExtend")
public class InfGodFitLktbExtend extends InfGodFitLktb {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 623940408578859425L;

	/**
	 * FIT 구분 코드 명
	 * ㅁ. FIT_COATPANTS : FIT 구분	 
	 */
	private String fitSectCdNm;
	
	/**
	 * 상품 번호
	 * ㅁ. 상품 유형 1자리 || 업체 코드 3자리 || YYMMDD || 5자리 Cycle Sequence	 
	 */
	private String godNo;
	
	/**
	 * FIT 조견표 HTML	 
	 */
	private String fitLktbHtml;


	/**
	 * 모바일 FIT 조견표 HTML	 
	 */
	private String mobileFitLktbHtml;

	
}
