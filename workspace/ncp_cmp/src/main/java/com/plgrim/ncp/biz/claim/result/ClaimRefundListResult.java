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
package com.plgrim.ncp.biz.claim.result;

import com.plgrim.ncp.base.abstracts.AbstractResult;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class ClaimRefundListResult extends AbstractResult {
	/**
	 * 
	 */
    private static final long serialVersionUID = 1L;
	
    private String clmNo;
    private String payNo;
    private String rfdTurn;
    private String ordNo;
    private String partmalSectCdNm;
    private String mallId;
    private String langCd;
    private String rfdRequstDt;
    private String clmTpCd;
    private String clmTpCdNm;
    private String clmStatCdNm;
    private String rfdStatCdNm;
    private String rfdTpCdNm;
    private String rfdAcnthldr;
    private String rfdBnkCd;
    private String rfdBnkAcctNo;
    private String rfdUdtDt;
    private String rfdErrCont;
    private String stdrCrncyRfdAmt;
    private String rfdAmt;
    private String rfdRegter;
    private String rfdComptDt;
    private String ord_dt;
    private String ordCustNm;
    private String refTel;
    private String memo_cont;
    private String acnthldrNm;
    
    /** [#48750][개발] 불량상품 고객에 대한 교환/반품 Process 개선 요청 **/
	// 클레임 선진행 구분 코드
	private String clmPreprogrsSectCd;

}
