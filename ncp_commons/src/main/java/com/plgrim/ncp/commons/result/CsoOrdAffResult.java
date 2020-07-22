/* Copyright (c) 2015 plgrim, Inc.
 * All right reserved.
 * http://plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 *
 * ------------------------------------------------------------------------
 * @author      jwcale.kim
 * @since       2015. 6. 15       
 */
package com.plgrim.ncp.commons.result;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractResult;
import com.plgrim.ncp.base.entities.datasource1.cso.CsoOrgztOrdAffInq;
import com.plgrim.ncp.base.entities.datasource1.cso.CsoOrgztOrdAffInqAns;
import com.plgrim.ncp.base.entities.datasource1.cso.CsoOrgztOrdAffInqAtchmnfl;

/**
 * @author ed
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CsoOrdAffResult extends AbstractResult {

	/**
	 * UID
	 */
    private static final long serialVersionUID = 591797712065396263L;
    
    private CsoOrgztOrdAffInq csoOrgztOrdAffInq; // 단체주문 / 제휴문의
    private CsoOrgztOrdAffInqAns csoOrgztOrdAffInqAns; // 단체주문 / 제휴문의 답변
    private CsoOrgztOrdAffInqAtchmnfl csoOrgztOrdAffInqAtchmnfl; // 단체주문 파일
    
    String regDtNm; // 등록일시
    String ansDtNm; // 답변일시
    String orgztOrdAffInqTpCdEng;
}

