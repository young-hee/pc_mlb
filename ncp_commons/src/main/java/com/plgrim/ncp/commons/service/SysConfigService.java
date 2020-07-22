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
package com.plgrim.ncp.commons.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plgrim.ncp.base.abstracts.AbstractService;
import com.plgrim.ncp.base.entities.datasource1.sys.SysDynmcConfig;
import com.plgrim.ncp.base.entities.datasource1.sys.SysDynmcConfigDetail;
import com.plgrim.ncp.base.repository.sys.SysDynmcConfigRepository;
import com.plgrim.ncp.commons.data.SysConfigDTO;
import com.plgrim.ncp.commons.repository.SysConfigRepository;
import com.plgrim.ncp.commons.result.SysConfigResult;

@Service
public class SysConfigService extends AbstractService {

	@Autowired
    SysConfigRepository sysConfigRepository;
	
	@Autowired
	SysDynmcConfigRepository sysDynmcConfigRepository;
	
	/**
	 * 설정조회 리스트
	 */
	public List<SysConfigResult> selectListSysConfig(SysConfigDTO paramData) throws Exception {
		return sysConfigRepository.selectListSysConfig(paramData);
	}
	
	/**
	 * 설정조회 리스트 총 갯수
	 */
	public long selectListCountSysConfig(SysConfigDTO paramData) throws Exception {
		return sysConfigRepository.selectListCountSysConfig(paramData);
	}
	
	/**
	 * 설정조회
	 */
	public SysConfigResult selectSysConfig(SysConfigDTO paramData) throws Exception {
		return sysConfigRepository.selectSysConfig(paramData);
	}
	
	/**
	 * 설정등록 처리
	 */
	public void insertSysDynmcConfig(SysDynmcConfig paramData) throws Exception {
		sysDynmcConfigRepository.insertSysDynmcConfig(paramData);
	}
	
	/**
	 * 설정상세 등록 처리
	 */
	public int insertSysDynmcConfigDetail(SysDynmcConfigDetail paramData) throws Exception {
		return sysConfigRepository.insertSysDynmcConfigDetail(paramData);
	}
	
	/**
	 * 설정상세 리스트 조회
	 */
	public List<SysConfigResult> selectSysConfigDetailList(SysConfigDTO paramData) throws Exception {
		return sysConfigRepository.selectSysConfigDetailList(paramData);
	}
	
	/**
	 * 설정상세 삭제 처리
	 */
	public int deleteSysDynmcConfigDetail(SysDynmcConfigDetail paramData) throws Exception {
		return sysConfigRepository.deleteSysDynmcConfigDetail(paramData);
	}
	
	/**
	 * 설정 수정 처리
	 */
	public int updateSysDynmcConfig(SysConfigDTO paramData) throws Exception {
		return sysConfigRepository.updateSysDynmcConfig(paramData);
	}
	
	/**
	 * 이전 설정 수정 처리
	 */
	public int updateAgoSysDynmcConfig(SysConfigDTO paramData) throws Exception {
		return sysConfigRepository.updateAgoSysDynmcConfig(paramData);
	}
	
	/**
	 * 이전 설정상세 수정 처리
	 */
	public int updateAgoSysDynmcConfigDetail(SysConfigDTO paramData) throws Exception {
		return sysConfigRepository.updateAgoSysDynmcConfigDetail(paramData);
	}
	
	/**
	 * 이전 설정 삭제 처리
	 */
	public int deleteAgoSysDynmcConfig(SysConfigDTO paramData) throws Exception {
		return sysConfigRepository.deleteAgoSysDynmcConfig(paramData);
	}
	
	/**
	 * 서킷브래이커 스케줄 대상 설정조회 리스트
	 */
	public List<SysConfigResult> selectListSysConfigPop(SysConfigDTO paramData) throws Exception {
		return sysConfigRepository.selectListSysConfigPop(paramData);
	}
	
	/**
	 * 서킷브래이커 스케줄 대상 설정조회 리스트 총 갯수
	 */
	public long selectListCountSysConfigPop(SysConfigDTO paramData) throws Exception {
		return sysConfigRepository.selectListCountSysConfigPop(paramData);
	}


	/**
	 * 설정상세 수정 처리
	 */
	public int updateSysDynmcConfigDetail(SysDynmcConfigDetail paramData) throws Exception {
		return sysConfigRepository.updateSysDynmcConfigDetail(paramData);
	}


	/**
	 * 이전설정 조회하여 등록
	 *
	 * @param paramData the param data
	 * @throws Exception the exception
	 */
	public void insertSysDynmcConfigBySelect(SysConfigDTO paramData) throws Exception {
		sysConfigRepository.insertSysDynmcConfigBySelect(paramData);
	}

	/**
	 * 이전설정상세 조회하여 등록
	 *
	 * @param paramData the param data
	 * @throws Exception the exception
	 */
	public void insertSysDynmcConfigDetailBySelect(SysConfigDTO paramData) throws Exception {
		sysConfigRepository.insertSysDynmcConfigDetailBySelect(paramData);
	}
}
