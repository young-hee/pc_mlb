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
 * @since       2015. 6. 5       
 */
package com.plgrim.ncp.commons.result;

import com.plgrim.ncp.base.abstracts.AbstractResult;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author ed
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysCmmnTempletResult extends AbstractResult {

	/**
	 * UID
	 */
    private static final long serialVersionUID = 591797712065396263L;
/*    
    private SysTemplet sysTemplet; // 시스템공지
    List<SysTempletAtchFile> sysTempletAtchFileList; // 첨부파일
*/    
    private String regtrIdNm; // 등록자명
    private String regDtNm; // 등록일
    private String udterIdNm; // 수정자명
    private String udtDtNm; // 수정일
    
    private String downYn; // 다운로드 여부
}

