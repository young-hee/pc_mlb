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
import com.plgrim.ncp.base.entities.datasource1.sys.SysCldr;

/**
 * @author ed
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysHldyResult extends AbstractResult {

	/**
	 * UID
	 */
    private static final long serialVersionUID = 591797712065396263L;
    
    private SysCldr sysCldr; // 달력
    private String sysDateNm; // 시스템일자
    private String regtrIdNm; // 등록자명
    private String regDtNm; // 등록일
    private String udterIdNm; // 수정자명
    private String udtDtNm; // 수정일
}

