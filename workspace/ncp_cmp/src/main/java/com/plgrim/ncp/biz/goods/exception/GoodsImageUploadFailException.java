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
 * @since       2015. 7. 9       
 */
package com.plgrim.ncp.biz.goods.exception;

import com.plgrim.ncp.framework.exception.NCPException;

public class GoodsImageUploadFailException extends NCPException {

	/**
	 * UID
	 */
    private static final long serialVersionUID = 175306522755964247L;


    /**
     * 상품이미지 업로드 오류
     *
     * @param params [설명]
     */
    public GoodsImageUploadFailException(String[] params) {
		init(params);
	}

}
