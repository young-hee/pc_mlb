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
public class MainCounselNotMemberInfoResult extends AbstractResult {
	
	// ~ Instance fields. ~~~~~~~~~~~~~~
	
	// ~ Constructors. ~~~~~~~~~~~~~~~~~
	
	// ~ Getter and Setter. ~~~~~~~~~~~~

	private String mbrNm;
	private String mbrEmail;
	private String telNo;
	private String mobilNo;
	private String mbrSexSectNm;
	private String mbrSexSectCd;
	private String mbrAtrbCd;
	private String mbrStatCd;
	private String mbrId;
	private String joinDt;
	private String mbrNo;
	private String erpCstmrNo;
	private String usableCpnCnt;
	private String cpnCnt;
	private String joinMallId;
	
	//20160503_주동민_sr#18801 [CSO내 제휴사 고객정보 마스킹 처리요청]
	private String orgTelNo;
	private String orgMobilNo;
	private String orgMbrNm;
	private String orgMbrEmail;
	private String mbrTpCd;
	private String reJoinYn;

	/**  회원 등급. */
	private String onlneGrdNm;
}
