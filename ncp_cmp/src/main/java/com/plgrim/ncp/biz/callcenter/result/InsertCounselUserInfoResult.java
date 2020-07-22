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
public class InsertCounselUserInfoResult extends AbstractResult {

	// ~ Instance fields. ~~~~~~~~~~~~~~

	// ~ Constructors. ~~~~~~~~~~~~~~~~~

	// ~ Getter and Setter. ~~~~~~~~~~~~

	private String mbrNm;
	private String mbrSexSectCd;
	private String mbrSexSectNm;
	private String mbrTpCd;
	private String mbrTpNm;
	private String mbrId;
	private String mbrNo;
	private String erpCstmrNo;
	private String mallNm;
	
	private String mbrEmail;
	private String mobilNationNo;
	private String mobilAreaNo;
	private String mobilTlofNo;
	private String mobilTlofWthnNo;
	private String telNationNo;
	private String telAreaNo;
	private String telTlofNo;
	private String telTlofWthnNo;

	private String ordNo;
	private String godNo;
	private String godNm;
	private String erpGodNo;
	private String ordGodTurn;
	private String partmalSectCd;

	private String joinMallId;
	private String joinMallNm;
	/* #31806  2016.12.06 eunsik10.kim  멤버십 제도 개선관련  */
	private String erpGrdCd;

}
