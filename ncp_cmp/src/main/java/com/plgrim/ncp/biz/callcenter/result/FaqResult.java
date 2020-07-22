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

import com.plgrim.ncp.base.entities.datasource1.cso.CsoFaqAtchmnfl;
import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractResult;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper=false)
public class FaqResult extends AbstractResult {
	
	// ~ Instance fields. ~~~~~~~~~~~~~~
	
	// ~ Constructors. ~~~~~~~~~~~~~~~~~
	
	// ~ Getter and Setter. ~~~~~~~~~~~~
	
	/**
	 * UID
	 */
    private static final long serialVersionUID = 1L;

	private String faqSn;
	private String mallId;
	private String mallNm;
	private String langCd;
	private String langNm;
	private String faqSectCd;
	private String faqSectNm;
	private String faqDetailSectCd;
	private String faqDetailSectNm;			// 상세 구분 코드네임
	private String faqTurn;
	private String faqSj;
	private String faqCont;
	private String sortSeq;
	private String inqireCount;
	private String dspYn;
	private String mainExpsrYn;
	private String regtrId;
	private String regtrNm;
	private String regDt;
	private String udterId;
	private String udterNm;
	private String udtDt;

	private List<CsoFaqAtchmnfl> csoFaqAtchmnfl;
	
	
}
