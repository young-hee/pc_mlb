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
 * @since       2015. 5. 20       
 */
package com.plgrim.ncp.biz.member.exception;

import com.plgrim.ncp.framework.exception.NCPException;

/**
 * 회원정보 ERP 연동 Excption
 *
 * @author sy59.gim
 * @since 2015. 5. 20
 */
public class MemberFailERPIFException  extends NCPException {

	/** UID. */
    private static final long serialVersionUID = -2617186536812776736L;

	/**
	 * 회원정보 ERP 연동을 실패하였습니다.
	 *
	 * @param params [설명]
	 */
	public MemberFailERPIFException(String[] params) {
		init(params);
	}
}