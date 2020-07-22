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
 * [마일리지 최소 단위 오류]
 * 
 * @author sy59.gim
 * @since 2015. 5. 20
 */
public class MemberMileageMinUtilException  extends NCPException {

    /** UID. */
    private static final long serialVersionUID = -2617186536812776736L;

    /**
     * {0}원 미만의 마일리지는 전환이 불가합니다.
     *
     * @param params [설명]
     */
    public MemberMileageMinUtilException(String[] params) {
        init(params);
    }

}
