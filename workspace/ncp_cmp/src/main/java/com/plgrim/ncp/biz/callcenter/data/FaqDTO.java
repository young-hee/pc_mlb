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
 * @since       2015. 3. 17       
 */
package com.plgrim.ncp.biz.callcenter.data;

import com.plgrim.ncp.base.entities.datasource1.cso.CsoFaqAtchmnfl;
import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractDTO;
import com.plgrim.ncp.base.entities.datasource1.sample.Dept;
import com.plgrim.ncp.base.entities.datasource1.sample.Emp;
import com.plgrim.ncp.base.entities.datasource1.sys.SysNotiAtchFile;

import java.util.ArrayList;
import java.util.List;

/**
 * 샘플 DTO.
 *
 * @author shhenry.choi
 * @since 2015. 3. 17
 */

@Data
@EqualsAndHashCode(callSuper = false)
public class FaqDTO extends AbstractDTO {

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */

	static final long serialVersionUID = 4403992496214821087L;


	//그리드 DTO 리스트
	private List<FaqGridDTO> listFaq;

	private java.lang.Long faqSn;
	private String mallId;
	private String langCd;
	private String faqSectCd;
	private String faqDetailSectCd;
	private String faqSj;
	private String faqCont;
	private java.lang.Integer sortSeq;
	private java.lang.Integer inqireCount;
	private String dspYn;
	private String mainExpsrYn;
	private String regtrId;
	private java.util.Date regDt;
	private String udterId;
	private java.util.Date udtDt;

	private java.lang.Integer faqAtchmnflTurn;
	
	List<CsoFaqAtchmnfl> csoFaqAtchmnfl = new ArrayList<CsoFaqAtchmnfl>();

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
