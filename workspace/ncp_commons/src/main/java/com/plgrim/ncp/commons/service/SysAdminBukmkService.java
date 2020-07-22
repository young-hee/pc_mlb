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
 * @since       2015. 6. 29       
 */
package com.plgrim.ncp.commons.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plgrim.ncp.base.abstracts.AbstractService;
import com.plgrim.ncp.base.entities.datasource1.sys.SysAdminBukmk;
import com.plgrim.ncp.base.repository.sys.SysAdminBukmkRepository;
import com.plgrim.ncp.commons.repository.SysAdminBukmkMgrRepository;
import com.plgrim.ncp.commons.result.SysAdminBukmkResult;
import com.plgrim.ncp.commons.util.BOSecurityUtil;

/**
 * 즐겨찾기 Service
 * @author ed
 *
 */
@Service
public class SysAdminBukmkService extends AbstractService {

	@Autowired
	SysAdminBukmkRepository sysAdminBukmkRepository;
	
	@Autowired
	SysAdminBukmkMgrRepository sysAdminBukmkMgrRepository;  
	
	/**
	 * 즐겨찾기 등록 처리.
	 *
	 * @param paramData [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public void insertSysAdminBukmk(SysAdminBukmk paramData) throws Exception {
		String loginId = BOSecurityUtil.getLoginId(); // 로그인ID
		
		if(paramData != null) {
			paramData.setAdminId(loginId);
			paramData.setUseYn("Y");
			paramData.setRegtrId(loginId); // 등록자
			paramData.setUdterId(loginId); // 수정자
		}
		sysAdminBukmkRepository.insertSysAdminBukmk(paramData);
	}
	
	/**
	 * 사용자별 즐겨찾기 목록.
	 *
	 * @param adminId [설명]
	 * @return List [설명]
	 * @since 2015. 5. 29
	 */
	public List<SysAdminBukmkResult> selectAdminBookmarkList(String adminId, String boSiteId) {
	    return sysAdminBukmkMgrRepository.selectAdminBookmarkList(adminId, boSiteId);
    }
	
	/**
	 * 즐겨찾기 삭제 처리.
	 *
	 * @param paramData [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public void deleteSysAdminBukmk(SysAdminBukmk paramData) throws Exception {
		String loginId = BOSecurityUtil.getLoginId(); // 로그인ID
		
		if(paramData != null) {
			paramData.setAdminId(loginId); // 관리자 ID
		}
		
		sysAdminBukmkRepository.deleteSysAdminBukmk(paramData);
	}
}
