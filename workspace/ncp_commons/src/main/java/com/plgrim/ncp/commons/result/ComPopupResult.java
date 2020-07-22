/* Copyright (c) 2013 plgrim, Inc.
 * All right reserved.
 * http://plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 *
 * Revision History
 * Author              Date                         Description
 * ------------------   --------------                  ------------------
 * 
 */
package com.plgrim.ncp.commons.result;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractResult;
import com.plgrim.ncp.base.entities.datasource1.com.Com;
import com.plgrim.ncp.base.entities.datasource1.sys.SysBrnd;
import com.plgrim.ncp.base.entities.datasource1.sys.SysShop;

/**
 * @author Tam
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ComPopupResult extends AbstractResult {

	/**
	 * UID
	 */
    private static final long serialVersionUID = 591797712065396263L;
    
    
    /**
     * 매장정보
     */
    SysShop sysShop;
    
    /**
     * 업체정보
     */
    Com	com;
    
    //주소
    String addr;
    
    /**
     * 브랜드 정보
     */
    SysBrnd sysBrnd;
    
    /**
     * 브랜드 경로
     */
    String naBrndNm;
    
    /**
     * 브랜드 Level
     */
    String brndLevel;
}

