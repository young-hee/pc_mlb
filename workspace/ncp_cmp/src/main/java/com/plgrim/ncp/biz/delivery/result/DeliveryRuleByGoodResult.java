/* Copyright (c) 2015 plgrim, Inc.
 * All right reserved.
 * http://plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 *
 * ------------------------------------------------------------------------
 * @author      jwcale.kim
 * @since       2015. 4. 29       
 */
package com.plgrim.ncp.biz.delivery.result;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractResult;
import com.plgrim.ncp.base.entities.datasource1.com.ComDmstcDlvCstPlc;



/**
 * Instantiates a new delivery claim good result.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class DeliveryRuleByGoodResult extends AbstractResult {

	/**
	 * 
	 */
    private static final long serialVersionUID = 5621488408855620757L;
	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
    
    /**
	 * 상품 번호
	ㅁ. 상품 유형 1자리 || 업체 코드 3자리 || YYMMDD || 5자리 Cycle Sequence	 
	 */
	
	private String godNo;
	
	private List<String> godList;
	
    private ComDmstcDlvCstPlc comDmstcDlvCstPlc;
}
