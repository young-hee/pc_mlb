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

import com.plgrim.ncp.base.abstracts.AbstractResult;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class MtmUserInfoResult extends AbstractResult {
	
	// ~ Instance fields. ~~~~~~~~~~~~~~
	
	// ~ Constructors. ~~~~~~~~~~~~~~~~~
	
	// ~ Getter and Setter. ~~~~~~~~~~~~

	private String inqCstmrNm;
	private String inqMbrNo;

	private String cstmrSmsRectptnYn;
	private String cstmrEmailRectptnYn;

	private String mbrSexSectCd;
	private String mbrSexSectNm;
	private String mbrTpCd;
	private String mbrTpNm;
	private String mbrId;
	private String mbrEmail;
	private String erpCstmrNo;
	private String ordNo;
	private String ordGodTurn;
	private String godNo;
	private String erpGodNo;
	private String godNm;
	private String mobilNo;
	private String inqErpGodNo;
	private String inqGodNo;
	private String mallNm;
	/* #31806  2016.12.06 eunsik10.kim  멤버십 제도 개선관련  */
	private String erpGrdCd;
	
   
}
