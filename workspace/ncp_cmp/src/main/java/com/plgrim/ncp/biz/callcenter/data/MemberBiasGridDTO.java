/* Copyright (c) 2015 plgrim, Inc.
 * All right reserved.
 * http://plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 *
 * ------------------------------------------------------------------------
 * @author      shhenry.choi
 * @since       2015. 3. 18       
 */
package com.plgrim.ncp.biz.callcenter.data;

import com.plgrim.ncp.base.abstracts.AbstractDTO;
import com.plgrim.ncp.base.entities.datasource1.cso.CsoGlan;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class MemberBiasGridDTO extends AbstractDTO {

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */

	private java.lang.Long memoSn;
	
	private String memoRegtrId;
	
	private String memoTpCd;
	
	private String cstmrTpCd;
	
	private String expsrYn;
	
	private String memoCont;
	
	private String ordNo;
	
	private String clmNo;
	
	private String mbrNo;
	
	private String regtrId;
	
	private java.util.Date regDt;
	
	private String udterId;
	
	private java.util.Date udtDt;



	
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
