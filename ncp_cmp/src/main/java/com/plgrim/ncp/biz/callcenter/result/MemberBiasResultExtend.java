/* Copyright (c) 2015 plgrim, Inc.
 * All right reserved.
 * http://plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 *
 * Revision History
 * Author              Date                         Description
 * ------------------   --------------                  ------------------
 * beyondj2ee			2015.02.09         
 */
package com.plgrim.ncp.biz.callcenter.result;

import com.plgrim.ncp.base.abstracts.AbstractDTO;
import com.plgrim.ncp.base.abstracts.AbstractResult;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper=false)
public class MemberBiasResultExtend extends AbstractDTO {
	
	// ~ Instance fields. ~~~~~~~~~~~~~~
	
	// ~ Constructors. ~~~~~~~~~~~~~~~~~
	
	// ~ Getter and Setter. ~~~~~~~~~~~~

	/**
	 * 
	 */
	private static final long serialVersionUID = 7685023484579368835L;
	private java.lang.Long memoSn;	
	private java.util.Date regDt;
	private String udtDt;
	private String mbrId;
	private String mbrNm;
	private String cstmrTpCd;
	private String cstmrTpNm;
	private String memoCont;
	private String expsrYn;
	private String udterNm;	
	private String udterId;
	private String mallNm;
	private String gridCudTag;	
	
	
	//[
	//{"gridCudTag":"U","memoSn":"393831","":null,"cstmrTpCd":"HARD_HEAR","expsrYn":"Y"
	//,"cstmrTpNm":"난청","memoCont":"잘안들려요 1111","udtDt":"2016-06-14 17:14:51"}
	//]
}
