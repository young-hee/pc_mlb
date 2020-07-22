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
 * @since       2015. 7. 16       
 */
package com.plgrim.ncp.biz.goods.exception;

import com.plgrim.ncp.framework.exception.NCPException;

public class GoodsNotExistException extends NCPException{

	/**
	 * UID
	 */
    private static final long serialVersionUID = 6948883035441490090L;

    /**
     * 등록되지 않은 상품 오류
     *
     * @param params [설명]
     */
    public GoodsNotExistException(String[] params) {
		init(params);
	}

}
