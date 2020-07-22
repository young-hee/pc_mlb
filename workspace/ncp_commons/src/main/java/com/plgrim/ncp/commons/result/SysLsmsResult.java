/* Copyright (c) 2015 plgrim, Inc.
 * All right reserved.
 * http://plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 *
 * ------------------------------------------------------------------------
 * @author      jwcale.kim
 * @since       2015. 6. 15       
 */
package com.plgrim.ncp.commons.result;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractResult;
import com.plgrim.ncp.base.entities.datasource1.sys.SysSmsEmailTxt;

/**
 * @author ed
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysLsmsResult extends AbstractResult {

	/**
	 * UID
	 */
    private static final long serialVersionUID = 591797712065396263L;
    
    private SysSmsEmailTxt sysSmsEmailTxt; // SMS문구
    private String name; // 이름
	private String tranPhone; // 수신번호
	private String regtrIdNm; // 등록자
	private String tranId; // 수신번호
}

