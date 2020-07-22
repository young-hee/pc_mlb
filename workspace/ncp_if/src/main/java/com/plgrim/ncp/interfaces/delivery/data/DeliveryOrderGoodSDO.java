/* Copyright (c) 2015 plgrim, Inc.
 * All right reserved.
 * http://plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 *
 * ------------------------------------------------------------------------
 * @author      tktaeki.kim
 * @since       2015. 4. 1       
 */
package com.plgrim.ncp.interfaces.delivery.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.plgrim.ncp.base.abstracts.AbstractDTO;
import com.plgrim.ncp.base.abstracts.AbstractSDO;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

/**
 * [클래스 설명]
 * 
 * <p>
 * 
 * <ul>
 *   <li> [기능1]
 *   <li> [기능2]
 * </ul>.
 *
 * @author 
 */
@Data
@EqualsAndHashCode(callSuper = false)
@JsonInclude(value=Include.NON_EMPTY)
public class DeliveryOrderGoodSDO extends AbstractSDO {

    private static final long serialVersionUID = -7302530416648300557L;

	private String ordNo;			// 주문번호
    private int dlvPcupspTurn;	// 배송 수거지 순번. 
	private int dlvTurn;			// 배송 순번. 
	private int orgDlvTurn;			// 이전 순번. 
	private int dlivyComptQty;			// 출고수량. 
	private String dlivyDrctGodNo;	// 출고지시상품번호. 
	private String dlvStatCd;			// 배송상태.
	private String dmstcWaybilNo;	// 국내운송장번호. 
	private String dlivyDrctCnclStatCd;	// 물류 출고지시 상품. 출고지시 취소 상태 코드. 
	private String regtrId;			// 등록자. 
	private String udterId;			// 수정자. 

}
