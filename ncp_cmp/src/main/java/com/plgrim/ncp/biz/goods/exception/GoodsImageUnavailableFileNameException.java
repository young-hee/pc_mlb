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
 * @since       2015. 7. 15       
 */
package com.plgrim.ncp.biz.goods.exception;

import com.plgrim.ncp.framework.exception.NCPException;

public class GoodsImageUnavailableFileNameException extends NCPException{

	/**
	 * UID
	 */
    private static final long serialVersionUID = 6562857437014559428L;

    /**
     * 상품 이미지 업로드 파일명 룰 불일치 오류
     *
     * @param params [설명]
     */
    public GoodsImageUnavailableFileNameException(String[] params) {
		init(params);
	}
}
