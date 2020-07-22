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

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractResult;
import com.plgrim.ncp.base.entities.datasource1.sys.SysBoSite;
import com.plgrim.ncp.base.entities.datasource1.sys.SysMenu;

/**
 * @author Tam
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class MenuResult extends AbstractResult {

	/**
	 * UID
	 */
    private static final long serialVersionUID = 591797712065396263L;
    
    
    java.math.BigInteger menuDepth; //메뉴 단계
    String upperMenuNm; //상위 메뉴명
    String naMenuNm; // 메뉴명 네비
    
    SysMenu sysMenu;
    
    SysBoSite sysBoSite;
    
    List<SysMenu> sysFileList;//메뉴 종속 FILE 유형 정보
    
    
    String regtrNm; //등록자명
    String udterNm; //수정자명
    
    String  radioFirstPgeYn; //라디오 첫페이지 선택여부 1 or 0
    
    String assignYn;
}

