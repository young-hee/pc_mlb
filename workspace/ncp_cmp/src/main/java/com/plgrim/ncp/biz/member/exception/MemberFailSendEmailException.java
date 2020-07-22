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
 * 임시비밀번호 발송 실패
 * 
 * @author sy59.gim
 * @since 2015. 5. 20
 */
public class MemberFailSendEmailException  extends NCPException {

    /** UID. */
    private static final long serialVersionUID = -2617186536812776736L;

    /**
     * 메일 발송에 실패 하였습니다.
     *
     * @param params [설명]
     */
    public MemberFailSendEmailException(String[] params) {
        init(params);
    }
}
