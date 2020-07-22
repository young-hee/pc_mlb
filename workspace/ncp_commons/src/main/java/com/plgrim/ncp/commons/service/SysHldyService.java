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
 * @since       2015. 6. 10       
 */
package com.plgrim.ncp.commons.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plgrim.ncp.base.abstracts.AbstractService;
import com.plgrim.ncp.commons.data.SysHldyDTO;
import com.plgrim.ncp.commons.repository.SysHldyRepository;
import com.plgrim.ncp.commons.result.SysHldyResult;
import com.plgrim.ncp.commons.util.BOSecurityUtil;

/**
 * 휴일관리 Service
 * @author ed
 *
 */
@Service
public class SysHldyService extends AbstractService {

	@Autowired
    SysHldyRepository sysHldyRepository; // 휴일관리 DAO
	
	/**
	 * 휴일 목록 조회.
	 *
	 * @param paramData [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public List<SysHldyResult> selectSysHldyList( SysHldyDTO paramData) throws Exception {
		return sysHldyRepository.selectSysHldyList(paramData); 
	}
	
	/**
	 * 휴일 목록 조회 개수.
	 *
	 * @param paramData [설명]
	 * @return Long [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public long selectSysHldyListCount( SysHldyDTO paramData) throws Exception {
		return sysHldyRepository.selectSysHldyListCount(paramData); 
	}
	
	/**
	 * 휴일 목록 조회 엑셀.
	 *
	 * @param paramData [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public List<Map<String, Object>> selectSysHldyListExcel( SysHldyDTO paramData) throws Exception {
		return sysHldyRepository.selectSysHldyListExcel(paramData); 
	}
	
	/**
	 * 휴일 등록.
	 *
	 * @param paramData [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public void insertSysHldy( SysHldyDTO paramData) throws Exception {
		String loginId = BOSecurityUtil.getLoginId(); // 로그인ID
		
		paramData.getFormData().getSysCldr().setUdterId(loginId); // 수정자
		paramData.getFormData().getSysCldr().setSysDate(paramData.getFormData().getSysCldr().getSysDate().replaceAll("-", "")); // - 제거
		
		sysHldyRepository.insertSysHldy(paramData.getFormData().getSysCldr()); // 등록
	}
	
	/**
	 * 휴일 상세 조회.
	 *
	 * @param paramData [설명]
	 * @return Sys cmmn noti result [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public SysHldyResult selectSysHldyDetail( SysHldyDTO paramData) throws Exception {
		return sysHldyRepository.selectSysHldyDetail(paramData); 
	}
	
	/**
	 * 휴일 수정.
	 *
	 * @param paramData [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public void updateSysHldy( SysHldyDTO paramData) throws Exception {
		String loginId = BOSecurityUtil.getLoginId(); // 로그인ID
		 
		paramData.getFormData().getSysCldr().setUdterId(loginId); // 수정자
		paramData.getFormData().getSysCldr().setSysDate(paramData.getFormData().getSysCldr().getSysDate().replaceAll("-", "")); // - 제거
		
		sysHldyRepository.deleteSysHldy(paramData.getFormData()); // 기존데이터 삭제
		
		sysHldyRepository.insertSysHldy(paramData.getFormData().getSysCldr()); // 등록
	}
	
	/**
	 * 휴일 삭제.
	 *
	 * @param paramData [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public void deleteSysHldy( SysHldyDTO paramData) throws Exception {
		String loginId = BOSecurityUtil.getLoginId(); // 로그인ID
		 
		paramData.getFormData().getSysCldr().setUdterId(loginId); // 수정자
		
		sysHldyRepository.deleteSysHldy(paramData.getFormData()); // 삭제
	}
}
