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

import java.util.List;

import com.plgrim.ncp.base.abstracts.AbstractResult;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class CounselDetailTransferResult extends AbstractResult {
	
	// ~ Instance fields. ~~~~~~~~~~~~~~
	
	// ~ Constructors. ~~~~~~~~~~~~~~~~~
	
	// ~ Getter and Setter. ~~~~~~~~~~~~

	private String cnsltSn;
	private String cnsltDetailTurn;
	private String transRequstTurn;
	private String transRequstCont;
	private String transRqesterNm;
	private String transRequstTpCd;
	private String transRequstTpNm;
	private String transStatCd;
	private String transStatNm;
	private String transRegtrNm;
	private String transTgtCd;
	private String transTgtNm;
	private String chrgJobTpCd;
	private String chrgJobTpNm;
	private String transRecrId;
	private String transRecrNm;
	private String transComId;
	private String transComNm;
	private String regDt;
	
	List<CounselTransferBoardDetailAnsResult> counselTransferBoardDetailAnsResult;
	
   
}
