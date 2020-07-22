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
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;


@Data
@EqualsAndHashCode(callSuper = false)
public class CallbackDTO extends AbstractDTO {

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;


	//그리드 DTO 리스트
	private List<CallbackGridDTO> listCallback;


	private String clbcId;
	private java.util.Date  clbcRegDt;
	private java.util.Date  clbcDstbDt;
	private String clbcPhon;
	private String clbcRecptnPhon;
	private String clbcPrcsStatCd;
	private String clbcPrcsStatNm;
	private java.util.Date  clbcComptDt;
	private String cnsltAdminId;
	private String regtrId;
	private String regDt;
	private String udterId;
	private String udtDt;
	
	
	

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
