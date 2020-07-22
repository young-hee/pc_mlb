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

import com.plgrim.ncp.base.entities.datasource1.sys.SysAdmin;
import com.plgrim.ncp.base.entities.datasource1.sys.SysAdminLoginLog;
import com.plgrim.ncp.base.entities.datasource1.sys.SysAdminUseLog;
import com.plgrim.ncp.base.entities.datasource1.sys.SysMenu;
import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractResult;
import com.plgrim.ncp.base.entities.datasource1.sys.SysAdmin;
import com.plgrim.ncp.base.entities.datasource1.sys.SysAdminLoginLog;
import com.plgrim.ncp.base.entities.datasource1.sys.SysAdminUseLog;
import com.plgrim.ncp.base.entities.datasource1.sys.SysMenu;

/**
 * 운영자 로그 
 * @author Tam
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AdmLogResult extends AbstractResult {

	/**
	 * UID
	 */
    private static final long serialVersionUID = 591797712065396263L;
    
    //운영자 기본정보
    SysAdmin sysAdmin;
    
    SysAdminLoginLog sysAdminLoginLog; // 관리자 로그인 로그
    
    String comNm; //소속명
    
    SysAdminUseLog sysAdminUseLog; //관리자 사용 로그
    
    SysMenu sysMenu; //메뉴정보
    
    String naMenuNm; //메뉴경로
    
    String upperMenuNm; //상위 메뉴명
    
    long menuLogCnt ; //메뉴 로그 수
    
    String menuViewNm ="보기"; // 목록화면에 표시할 항목
    
    
}

