package com.plgrim.ncp.interfaces.goods.data;

import org.apache.ibatis.type.Alias;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.plgrim.ncp.base.abstracts.AbstractSDO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Alias("goodsImageUrlSDO")
public class GoodsImageUrlSDO extends AbstractSDO {
	private static final long serialVersionUID = -3816862468283367913L;

	/**
	 * 브랜드 코드
	 * X : 디스커버리, M:MLB, I:MLB Kids
	 */
	@JsonProperty("BRAND")
	private String brand;

	/**
	 * 시즌 코드 
	 */
	@JsonProperty("SEASON")
	private String season;
	
	/**
	 * 품번 코드 
	 */
	@JsonProperty("PARTCODE")
	private String partcode;
	
	/**
	 * 칼라 코드	 
	 */
	@JsonProperty("COLOR")
	private String color;	
	
	/**
	 * 순번
	 */
	@JsonProperty("SEQ")
	private String seq;	
	
	/**
	 * 디폴트 플래그
	 * 1: 디폴트 ,  0: 기타(이미지가 상품별로 한개뿐일 경우는 0으로)
	 */
	@JsonProperty("DEFAULT_IMAGE")
	private String defaultImage;
	
	/**
	 * 이미지 링크
	 */
	@JsonProperty("URL")
	private String url;
}
