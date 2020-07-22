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

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractResult;

@Data
@EqualsAndHashCode(callSuper=false)
public class AlarmResult extends AbstractResult {
	
	// ~ Instance fields. ~~~~~~~~~~~~~~
	
	// ~ Constructors. ~~~~~~~~~~~~~~~~~
	
	// ~ Getter and Setter. ~~~~~~~~~~~~
	
	private String promsNo;			   
	private String cnsltSn;             
	private String cnsltDetailTurn;             
	private String chrgerCnfirmNm;
	private String rqesterCnfirmNn;
	private String cnsltNoTurn;         
	private String promsclStatNm;   
	private String promsclDt;            
	private String promsclComptDt;     
	private String promsTgtNm;      
	private String promsTel;
	private String promsRegDt;
	private String transRecrId;
	private String transRqesterId;
		
	private String transRequstDt;
	private String transRqesterNm;
	private String transStatNm;
	private String transTgtNm;
	private String chrgJobTpNm;	
	private String doiNm;
	private String translDelay;
	private String transComptDt;

	private String promsclSn;
	private String promsclMsg;

	
}
