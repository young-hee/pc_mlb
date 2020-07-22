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

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractDTO;



@Data
@EqualsAndHashCode(callSuper = false)
public class EmailSmsTemplateDTO extends AbstractDTO {

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
	private List<EmailSmsTemplateGridDTO> listEmailSmsTemplate;
	
	
	private java.lang.Long smsEmailTmplatSn;
	private String mallId;
	private String langCd;
	private String smsEmailTmplatNm;
	private String useYn;
	private String smsEmailTmplatKndCd;
	private String smsClfcCd;
	private String smsClfcDetailCd;
	private String emailClfcCd;
	private String emailClfcDetailCd;
	private String smsEmailTmplatSj;
	private String smsEmailTmplatCont;
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
