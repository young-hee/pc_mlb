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
 * @since       2015. 4. 28       
 */
package com.plgrim.ncp.biz.vendor.result;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractResult;
import com.plgrim.ncp.base.entities.datasource1.sys.SysAdmin;
import com.plgrim.ncp.base.entities.datasource1.sys.SysAdminChrgJob;

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
public class SysAdminListResult extends AbstractResult {

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	
    /**
	 * 
	 */
    private static final long serialVersionUID = 2097822591560688187L;

	/**
     * 운영자 Entity
     */
    private SysAdmin sysAdmin;
    
    /**
     * sysAdminChrgJob
     */
    private SysAdminChrgJob sysAdminChrgJob; 
    
    /**
     * mobilNo
     */
    private String mobilNo;
    
    /**
     * telNo
     */
    private String telNo;
    
    /**
     * chrgJobDscr
     */
    private String chrgJobDscr;
    
    /**
     * viewRegDt
     */
    private String viewRegDt;
  

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

	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */

}
