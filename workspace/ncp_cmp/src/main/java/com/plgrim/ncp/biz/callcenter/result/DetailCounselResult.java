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
public class DetailCounselResult extends AbstractResult {
	
	// ~ Instance fields. ~~~~~~~~~~~~~~
	
	// ~ Constructors. ~~~~~~~~~~~~~~~~~
	
	// ~ Getter and Setter. ~~~~~~~~~~~~

	private String cnsltSn;
	private String cnsltDetailTurn;
	private String inqTpCd;
	private String inqTpNm;
	private String inqDetailTpCd;
	private String inqDetailTpNm;
	private String cnsltStatCd;
	private String cnsltStatNm;
	private String cnsltDetailStatCd;
	private String cnsltDetailStatNm;
	private String cstmrClmCd;
	private String smsSndCount;
	private String emailSndCount;
	private String regDt;
	private String regtrId;
	private String regtrNm;
	private String udtDt;
	private String udterId;
	private String udterNm;
	private String cnsltTmplatSn;
	private String tmplateInqTpCd;
	private String tmplateInqTpNm;
	private String tmplateInqDetailTpCd;
	private String tmplateInqDetailTpNm;
	private String cnsltKndCd;
	private String cnsltKndNm;
	private String cnsltDetailCont;
	private String promsclSn;
	private String promsclDt;
	private String promsclStatCd;
	private String promsclStatNm;
	private String promsclMemoCont;
	private String promsclNationNo;
	private String promsclAreaNo;
	private String promsclTlofNo;
	private String promsclTlofWthnNo;
	private String promsclRegtrNm;
	private String jobcdNm;
	private String transTgtCd;
	private String transTgtNm;
	private String ansCont;
	private int inqAnsTurn;

	private String langCd;

	private String chatInqSn;// 채팅번호 추가
	//CsoCnsltTrans csoCnsltTrans = new CsoCnsltTrans();
	CounselDetailTransferResult counselDetailTransferResult;
	CounselDetailPromsclResult counselDetailPromsclResult;

}