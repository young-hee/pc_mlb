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
 * Henry				2015.04.09         
 */
package com.plgrim.ncp.biz.callcenter.result;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractResult;

@Data
@EqualsAndHashCode(callSuper=false)
public class CounselTransferBoardAdminListResult extends AbstractResult {
	/**
	 * 
	 */
    private static final long serialVersionUID = 9113553783478319970L;
	
	// ~ Instance fields. ~~~~~~~~~~~~~~
	
	// ~ Constructors. ~~~~~~~~~~~~~~~~~
	
	// ~ Getter and Setter. ~~~~~~~~~~~~
    
    private String adminId;							/* 담당자 ID */
    private String adminNm;							/* 담당자 이름 */
    private String deptNm;							/* 부서 이름 */
    private String chrgJobTpCd;						/* 업무운영코드 */
    private String chrgJobTpNm;                     /* 업무운영이름 */
    private String adminHp;                         /* 운영자 hp */
	
}
