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
package com.plgrim.ncp.biz.order.data;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractResult;
import com.plgrim.ncp.base.entities.datasource1.ord.OrdGod;

@Data
@EqualsAndHashCode(callSuper = false)
public class MypageOrderFoEntity extends AbstractResult {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /*
     * ---------------------------------------------------------------------
     * Instance fields.
     * ---------------------------------------------------------------------
     */

    /** === Entity START ==============================================================  */
    private String ordGodTurn;					    // 주문 상품 순번
    private String ordGodTurnDtl;					// 주문 상품 순번
    private String ordDtlNo;					    // 주문 번호
    private String godNm;					    	// 상품명
    private String ordQty;					    	// 주문수량(클레임 포함안함)
    private String itmNm;					    	// 단품명
    private String godTpCd;					    	// 상품유형코드
    private List<OrdGod> ordGodList;       			// 주문상품 정보(세트용)
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
