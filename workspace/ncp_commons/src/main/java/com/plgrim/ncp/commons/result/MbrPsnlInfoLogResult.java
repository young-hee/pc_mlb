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
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrPsnlInfoUsef;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrPsnlInfoUsefMbd;

/**
 * @author ed
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class MbrPsnlInfoLogResult extends AbstractResult {

	/**
	 * UID
	 */
    private static final long serialVersionUID = 591797712065396263L;
    
    private MbrPsnlInfoUsef mbrPsnlInfoUsef; // 회원 개인정보 이용
    private MbrPsnlInfoUsefMbd mbrPsnlInfoUsefMbd; // 회원 개인정보 이용 주체
    
    String mbrId; // 회원ID
    String usefDtNm; // 제공일시
}

