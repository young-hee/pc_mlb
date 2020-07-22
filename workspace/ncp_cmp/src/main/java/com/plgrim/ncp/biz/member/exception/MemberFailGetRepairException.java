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
package com.plgrim.ncp.biz.member.exception;

import com.plgrim.ncp.framework.exception.NCPException;

/**
 * 수선내역 조회 실패
 * 
 * @author sy59.gim
 * @since 2015. 6. 1
 */
public class MemberFailGetRepairException  extends NCPException {

    /** UID. */
    private static final long serialVersionUID = -2617186536812776736L;

	/**
	 * 수선내역 조회 연동을 실패하였습니다.
	 *
	 * @param params [설명]
	 */
	public MemberFailGetRepairException(String[] params) {
		init(params);
	}

}
