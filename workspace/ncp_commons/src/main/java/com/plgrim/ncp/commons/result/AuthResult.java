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
import com.plgrim.ncp.base.entities.datasource1.sys.SysAuthor;
import com.plgrim.ncp.base.entities.datasource1.sys.SysAuthorGrp;
import com.plgrim.ncp.base.entities.datasource1.sys.SysMenu;
import com.plgrim.ncp.base.entities.datasource1.sys.SysMenuExtend;

/**
 * @author Tam
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AuthResult extends AbstractResult {

	/**
	 * UID
	 */
    private static final long serialVersionUID = 591797712065396263L;
    
    String useYn ="Y"; //권한(기능)정보가 있을 경우 사용으로 설정.
    
    
    java.math.BigInteger menuDepth; //메뉴 단계
    String upperMenuNm; //상위 메뉴명
    String naMenuNm; // 메뉴명 네비
    
    SysMenu sysMenu;
    
    SysAuthor sysAuthor;//권한정보
    
    SysAuthorGrp sysAuthorGrp; //권한그룹정보
    
    String authorPath; //권한경로 ( 운영관리 운영권한 팝업창에서 사용 )
    
    String authorActNm ; //권한기능명 ( 운영관리 운영권한 팝업창에서 사용 )
    
}

