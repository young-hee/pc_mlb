/* Copyright (c) 2015 plgrim, Inc.
 * All right reserved.
 * http://plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 *
 * ------------------------------------------------------------------------
 * @author      th86.yang
 * @since       2015. 7. 24       
 */
package com.plgrim.ncp.biz.goods.exception;

import com.plgrim.ncp.framework.exception.NCPException;

public class GoodsDuplicateException extends NCPException{

	/**
	 * UID
	 */
    private static final long serialVersionUID = 2585271289657068883L;

    /**
     * 중복 상품 오류
     *
     * @param params [설명]
     */
    public GoodsDuplicateException(String[] params) {
		init(params);
	}
	
	
}
