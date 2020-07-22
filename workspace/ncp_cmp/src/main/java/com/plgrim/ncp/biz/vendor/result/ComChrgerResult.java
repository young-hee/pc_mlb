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
 * @since       2015. 4. 27       
 */
package com.plgrim.ncp.biz.vendor.result;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractResult;
import com.plgrim.ncp.base.entities.datasource1.com.ComChrger;

/**
 * [클래스 설명]
 * 
 * <p>
 * 
 * <ul>
 *   <li> [기능1]
 *   <li> [기능2]
 * </ul>.
 *
 * @author tktaeki.kim
 * @since 2015. 4. 14
 */

/**
 * Instantiates a new vendor list result.
 */

/**
 * Instantiates a new vendor list result.
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class ComChrgerResult extends AbstractResult {

	/**
	 * 
	 */

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 4123265560683943621L;

    /**
     * comChrger
     */
    private ComChrger comChrger;
    
    /* 담당자 핸드폰번호(종합) */
    private String mobilTotalNumber;
    
    /* 담당자 전화번호(종합) */
    private String telTotalNumber;
    
    /* 브랜드명 */
    private String brndNm;

    private String enChrgerNm;

    private String cnChrgerNm;

}
