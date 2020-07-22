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
 * @since       2015. 7. 23       
 */
package com.plgrim.ncp.biz.goods.exception;

import com.plgrim.ncp.framework.exception.NCPException;

public class GoodsImageUnavailableFileSizeException extends NCPException{

	/**
	 * UID
	 */
    private static final long serialVersionUID = -2693009869883295681L;

    /**
     * 상품 이미지 업로드 크기 불일치 오류
     *
     * @param params [설명]
     */
    public GoodsImageUnavailableFileSizeException(String[] params) {
		init(params);
	}

}
