/* Copyright (c) 2013 plgrim, Inc.
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
 * 
 */
package com.plgrim.ncp.commons.result;

import java.util.Date;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.plgrim.ncp.base.abstracts.AbstractResult;

@Data
@EqualsAndHashCode(callSuper = false)
public class SysBatchResult extends AbstractResult {
    private static final long serialVersionUID = 591797712065396263L;
    
    private String batchClassId;
	private String batchNm;
	private String batchGrpNm;
	private String batchExecutCyclCont;
	private String batchBegUrl;
	private String batchEndUrl;
	private String batchRebegUrl;
	private String indExecutUrl;
	private String batchDscr;
	private String batchPrmtrCont;
	private String indExecutPrmtrCont;
	private Date batchBegDt;
	private Date batchEndDt;
	private String batchStatCd;
	private String regtrId;
	private Date regDt;
	private String udterId;
	private Date udtDt;
	private String batchExecutStatCd;
	private long batchExecutExpectTime;
	private long batchExecutTimeTerm;
}

