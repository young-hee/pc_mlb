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

import com.plgrim.ncp.base.abstracts.AbstractDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class MemberBiasDTO extends AbstractDTO {

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
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

	private String memberBiasMbrNo;
	
	//그리드 DTO 리스트
	private List<MemberBiasGridDTO> listMemberBias;



	
	
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
