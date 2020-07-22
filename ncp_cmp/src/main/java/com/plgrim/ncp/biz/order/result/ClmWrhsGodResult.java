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
package com.plgrim.ncp.biz.order.result;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractResult;

@Data
@EqualsAndHashCode(callSuper=false)
public class ClmWrhsGodResult extends AbstractResult {
	/**
	 * 
	 */
    private static final long serialVersionUID = -4163343765283146774L;
    
    private String dlvPcupspTurn;					//물류배송지순번
    private java.math.BigDecimal realityDlvCst;   	//배송지별 주문배송비
    /* 클레임관리 반품 수정 후 접*/
    private String dlvMnCd;							//회수방법코드

    /*
     * 문의한 주문의 상품리스트 정보
     */
    List<ClmWrhsGodDetailResult> ordGodForRtnClmDetailResultList;    
	
	
}
