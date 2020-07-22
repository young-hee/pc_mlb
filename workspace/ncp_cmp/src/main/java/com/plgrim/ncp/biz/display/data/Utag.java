/* Copyright (c) 2015 plgrim, Inc.
 * All right reserved.
 * http://plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 */
package com.plgrim.ncp.biz.display.data;

import com.plgrim.ncp.base.abstracts.AbstractDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * AB테스트 트랙킹을 위한 태그 정의 및 생성 오브젝트
 *
 * @author 배영규
 * @since 2016. 7. 21
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Utag extends AbstractDTO {

	private static final long serialVersionUID = -3089761268709726120L;
	
	/* 구분자 */
	public static final String FIELD_SEPARATOR = ":";
	public static final String FIELD_DELIMITER = "$";
	
	/* 유형 */
	public static final String TYPE_IMPRESSION = "im";
	public static final String TYPE_CLICK = "cl";
	public static final String TYPE_CART = "ca";
	public static final String TYPE_ORDER = "or";

	/* 필드명 */
	public static final String FIELD_LAB_ID = "lid";
	public static final String FIELD_METHOD_ID = "mid";
	public static final String FIELD_IMPRESSION_ID = "imid";
	public static final String FIELD_DISPLAY_POSITION = "dpos";
	public static final String FIELD_REFERRER_PRODUCT = "ref_prd";
	public static final String FIELD_REFERRER_SEARCH = "ref_sch";
	public static final String FIELD_REFERRER_EVENT = "ref_evt";
	public static final String FIELD_REFERRER_NAVIGATION = "ref_nav";
	public static final String FIELD_REFERRER_CATEGORY = "ref_cat";
	public static final String FIELD_REFERRER_BRAND = "ref_brn";
	public static final String FIELD_ATTR_PRODUCTS = "attr_prds";
	
	public static final int IMPRESSION_LOGGING_NONE = 0;
	public static final int IMPRESSION_LOGGING_AUTO = 1;

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
    
    /* utag 유형: im(상품노출), cl(클릭), ca(장바구니담기), or(주문완료) */
    private String type;
    
    /* AB테스트 실험 ID */
    private String labId;
    
    /* AB테스트 메소드 ID */
    private String methodId;
    
    private int impressionLoggingType  = IMPRESSION_LOGGING_NONE;

    /* 노출 ID */
    private String impressionId;
    
    /* 노출 위치 */
    private int displayPosition = -1;
    
    /* 최초 유입 referrer 정보 */
    private Map<String, Object> referrers = new HashMap<String, Object>();
    
    /* 속성 정보 */
    private Map<String, Object> attributes = new HashMap<String, Object>();
    
	/*
	 * ---------------------------------------------------------------------
	 * Constructors.
	 * ---------------------------------------------------------------------
	 */

	/*
	 * ---------------------------------------------------------------------
	 * public & interface method.
	 * ---------------------------------------------------------------------
	 */
    
    /**
     * 최초 유입 referrer 정보를 태그에 추가한다.
     * 
     * @param refName
     * @param refValue
     */
    public void addReferrer(String refName, Object refValue) {
    	referrers.put(refName, refValue);
    }
    
    /**
     * 속성 정보를 태그에 추가한다.
     * 
     * @param attrName
     * @param attrValue
     */
    public void addAttribute(String attrName, Object attrValue) {
    	attributes.put(attrName, attrValue);
    }
    
    /**
     * 생성된 태그 스트링을 리턴한다.
     * 
     * @return
     */
    public String getTagString() {
    	StringBuilder sbTag = new StringBuilder();
    	
    	for (Map.Entry<String, Object> entry : referrers.entrySet()) {
    		if (sbTag.length() > 0) {
    			sbTag.append(FIELD_DELIMITER);
    		}
    		sbTag.append(entry.getKey()).append(FIELD_SEPARATOR).append(entry.getValue());
    	}
    	
    	if (StringUtils.isNotBlank(labId)) {
    		if (sbTag.length() > 0) {
    			sbTag.append(FIELD_DELIMITER);
    		}
    		sbTag.append(FIELD_LAB_ID).append(FIELD_SEPARATOR).append(labId);
    	}
    	
    	if (StringUtils.isNotBlank(methodId)) {
    		if (sbTag.length() > 0) {
    			sbTag.append(FIELD_DELIMITER);
    		}
    		sbTag.append(FIELD_METHOD_ID).append(FIELD_SEPARATOR).append(methodId);
    	}
    	
    	if (impressionLoggingType == IMPRESSION_LOGGING_AUTO) {
    		impressionId = UUID.randomUUID().toString();
    	}

    	if (StringUtils.isNotBlank(impressionId)) {
    		if (sbTag.length() > 0) {
    			sbTag.append(FIELD_DELIMITER);
    		}
    		sbTag.append(FIELD_IMPRESSION_ID).append(FIELD_SEPARATOR).append(impressionId);
    	}
    	
    	if (displayPosition >= 0) {
    		if (sbTag.length() > 0) {
    			sbTag.append(FIELD_DELIMITER);
    		}
    		sbTag.append(FIELD_DISPLAY_POSITION).append(FIELD_SEPARATOR).append(displayPosition);
    	}

    	for (Map.Entry<String, Object> entry : attributes.entrySet()) {
    		if (sbTag.length() > 0) {
    			sbTag.append(FIELD_DELIMITER);
    		}
    		sbTag.append(entry.getKey()).append(FIELD_SEPARATOR).append(entry.getValue());
    	}
    	
    	return sbTag.toString();
    }

	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */

}
