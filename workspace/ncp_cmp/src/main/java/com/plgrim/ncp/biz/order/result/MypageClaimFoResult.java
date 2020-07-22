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
package com.plgrim.ncp.biz.order.result;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractResult;
import com.plgrim.ncp.base.entities.datasource1.ord.OrdGodFoExtend;



@Data
@EqualsAndHashCode(callSuper = false)
public class MypageClaimFoResult extends AbstractResult {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /*
     * ---------------------------------------------------------------------
     * Instance fields.
     * ---------------------------------------------------------------------
     */

	private String clmNo;
	private String ordNo;
	private String ordDt;       /* 주문 일시 */
	private String clmDt;       /* 클레임 일시 */
	private String clmStatCd;   /* 클레임진행상태 */
	private String clmTpCd;     /* 클레임상태 */
	private String payNo;       /* 결제번호 */
	private String dealTpCd;    /* 거래유형코드 */
	private java.math.BigDecimal payExchgRtCrncySumAmt;    /* 클레임배송비 */
	private List<OrdGodFoExtend> ordGodList;       // 주문상품 정보
    /** === Entity ENT   ==============================================================  */
    

    
   
    /*
     * ---------------------------------------------------------------------
     * Constructors.
     * ---------------------------------------------------------------------
     */

    /*
     * ---------------------------------------------------------------------
     * public & interface method.
     * ---------------------------------------------------------------------
     */

    /*
     * ---------------------------------------------------------------------
     * private method.
     * ---------------------------------------------------------------------
     */

}
