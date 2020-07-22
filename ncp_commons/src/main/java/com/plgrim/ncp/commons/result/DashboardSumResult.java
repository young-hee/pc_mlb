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

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractResult;

/**
 * @author Peter
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class DashboardSumResult extends AbstractResult {

	/**
	 * UID
	 */
    private static final long serialVersionUID = 591797712065396263L;
        
    int beforeNewOrderNewCnt;		//발송 전 / 신규주문 / 신규
    int beforeNewOrderAllCnt;		//발송 전 / 신규주문 / 전체    
    int beforeDelevDelayNewCnt;		//발송 전 / 배송지연 / 신규
    int beforeDelevDelayAllCnt;		//발송 전 / 배송지연 / 전체    
    int beforeEmptyNewCnt;			//발송 전 / 결품현황 / 신규
    int beforeEmptyAllCnt;			//발송 전 / 결품현황 / 전체    
    int beforeOrderCancelNewCnt;	//발송 전 / 주문취소 / 신규
    int beforeOrderCancelAllCnt;	//발송 전 / 주문취소 / 전체
    int afterDelevIngNewCnt;		//발송 후 / 배송중 / 신규
    int afterDelevIngAllCnt;		//발송 후 / 배송중 / 전체    
    int afterReturnNewCnt;			//발송 후 / 반품처리 / 신규
    int afterReturnAllCnt;			//발송 후 / 반품처리 / 전체    
    int afterExchgProcNewCnt;		//발송 후 / 교환처리 / 신규
    int afterExchgProcAllCnt;		//발송 후 / 교환처리 / 전체 
    int approveIngCnt;				//승인요청상품
    int emptyProdCnt;				//품절상품
    int salesProdCnt;				//판매중상품
    int prodQnaNewCnt;				//상품q&a(신규)
    int noSalesProdCnt;				//미판매중상품
    int prodQnaNoprocCnt;			//상품q&a(미처리)
}

