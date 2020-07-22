package com.plgrim.ncp.base.entities.datasource1.god;

import java.util.List;
import java.util.Map;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 상품 상세 이미지
 */
/**
 * @author Vito
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class GodAditDetailExtend extends GodAditDetail{
	
	/**
	 * 
	 */
    private static final long serialVersionUID = 3399637603566616951L;

    /*
     * 코드 - depth 0
     */
    private String upperCd;
    /*
     * 코드명 - depth0
     */
    private String upperCdNm;
    /*
     * 코드 - depth1-1 (최하위 공통코드 사이의 grouping 코드)
     */
    private String grpCd;
    /*
     * 코드 - depth1
     */
    private String cd;
    /*
     * 코드명 - depth1
     */
    private String cdNm;

    private List<Map<String, String>> cdList; 
    /*
     * 언어
     */
    private String langCd;
}
