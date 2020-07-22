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



@Data
@EqualsAndHashCode(callSuper = false)
public class CsTemplateSearchDTO extends AbstractDTO {

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */

    
    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    
	private java.lang.Long cnsltTmplatSn;
	private String mallId;
	private String langCd;
	private String cnsltTmplatNm;
	private String useYn;
	private String cnsltTmplatKndCd;
	private String inqTpCd;
	private String inqDetailTpCd;
	private String mtmInqAnsTpCd;
	private String mtmInqAnsDetailTpCd;
	private String transTgtCd;
	private String chrgJobTpCd;
	private String transRequstTpCd;
	private String cnsltTmplatSj;
	private String cnsltTmplatCont;
	private String contHtmlYn;
	/*private String regtrId;
	private java.util.Date regDt;
	private String udterId;
	private java.util.Date udtDt;*/
	private String cmsltTmplatNm;

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
