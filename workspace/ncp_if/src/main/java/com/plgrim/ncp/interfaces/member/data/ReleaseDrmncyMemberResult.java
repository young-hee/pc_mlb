/* Copyright (c) 2015 plgrim, Inc.
 * All right reserved.
 * http://plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 *
 * ------------------------------------------------------------------------
 * @author      sy59.gim
 * @since       2015. 6. 22       
 */
package com.plgrim.ncp.interfaces.member.data;

import com.plgrim.ncp.base.abstracts.AbstractSDO;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 휴면 회원 해제 결과
 * 
 * @author Id
 * @since 2018. 12. 27
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class ReleaseDrmncyMemberResult extends AbstractSDO {

	/** The Constant serialVersionUID. */
    private static final long serialVersionUID = 266556246105397073L;
    
	/**
	 * ERP 고객번호
	 * ㅁ. ERP에서 관리되는 고객 번호	 
	 */
	private String erpCstmrNo;

    /**
     * 처리결과코드
     */
    private String resultCd;

}
