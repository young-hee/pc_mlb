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
package com.plgrim.ncp.commons.repository;


import java.util.List;

import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.datasource1.sys.SysDynmcConfigDetail;
import com.plgrim.ncp.commons.data.SysConfigDTO;
import com.plgrim.ncp.commons.result.SysConfigResult;

@Repository
public class SysConfigRepository extends AbstractRepository {

	/**
	 * 설정조회 리스트
	 */
	public List<SysConfigResult> selectListSysConfig(SysConfigDTO paramData) throws Exception {
		return getSession1().selectList("com.plgrim.ncp.commons.sysconfig.selectListSysConfig", paramData);
	}
	
	/**
	 * 설정조회 리스트 총 갯수
	 */
	public long selectListCountSysConfig(SysConfigDTO paramData) throws Exception {
		return getSession1().selectOne("com.plgrim.ncp.commons.sysconfig.selectListCountSysConfig", paramData);
	}
	
	/**
	 * 설정조회
	 */
	public SysConfigResult selectSysConfig(SysConfigDTO paramData) throws Exception {
		return getSession1().selectOne("com.plgrim.ncp.commons.sysconfig.selectSysConfig", paramData);
	}
	
	/**
	 * 설정상세 등록 처리
	 */
	public int insertSysDynmcConfigDetail(SysDynmcConfigDetail paramData) throws Exception { 
		return getSession1().update("com.plgrim.ncp.commons.sysconfig.insertSysDynmcConfigDetail", paramData);
	}
	
	/**
	 * 설정상세 리스트 조회
	 */
	public List<SysConfigResult> selectSysConfigDetailList(SysConfigDTO paramData) throws Exception {
		return getSession1().selectList("com.plgrim.ncp.commons.sysconfig.selectSysConfigDetailList", paramData);
	}
	
	/**
	 * 설정상세 삭제 처리
	 */
	public int deleteSysDynmcConfigDetail(SysDynmcConfigDetail paramData) throws Exception { 
		return getSession1().delete("com.plgrim.ncp.commons.sysconfig.deleteSysDynmcConfigDetail", paramData);
	}
	
	/**
	 * 설정 수정 처리
	 */
	public int updateSysDynmcConfig(SysConfigDTO paramData) throws Exception { 
		return getSession1().update("com.plgrim.ncp.commons.sysconfig.updateSysDynmcConfig", paramData);
	}
	
	/**
	 * 이전 설정 수정 처리
	 */
	public int updateAgoSysDynmcConfig(SysConfigDTO paramData) throws Exception { 
		return getSession1().update("com.plgrim.ncp.commons.sysconfig.updateAgoSysDynmcConfig", paramData);
	}
	
	/**
	 * 이전 설정상세 수정 처리
	 */
	public int updateAgoSysDynmcConfigDetail(SysConfigDTO paramData) throws Exception { 
		return getSession1().update("com.plgrim.ncp.commons.sysconfig.updateAgoSysDynmcConfigDetail", paramData);
	}
	
	/**
	 * 이전 설정 삭제 처리
	 */
	public int deleteAgoSysDynmcConfig(SysConfigDTO paramData) throws Exception { 
		return getSession1().delete("com.plgrim.ncp.commons.sysconfig.deleteAgoSysDynmcConfig", paramData);
	}
	
	/**
	 * 서킷브래이커 스케줄 대상 설정조회 리스트
	 */
	public List<SysConfigResult> selectListSysConfigPop(SysConfigDTO paramData) throws Exception {
		return getSession1().selectList("com.plgrim.ncp.commons.sysconfig.selectListSysConfigPop", paramData);
	}
	
	/**
	 * 서킷브래이커 스케줄 대상 설정조회 리스트 총 갯수
	 */
	public long selectListCountSysConfigPop(SysConfigDTO paramData) throws Exception {
		return getSession1().selectOne("com.plgrim.ncp.commons.sysconfig.selectListCountSysConfigPop", paramData);
	}

	/**
	 * 설정상세 수정 처리
	 */
	public int updateSysDynmcConfigDetail(SysDynmcConfigDetail paramData) throws Exception {
		return getSession1().update("com.plgrim.ncp.commons.sysconfig.updateSysDynmcConfigDetail", paramData);
	}

	/**
	 * 이전설정 조회하여 등록
	 *
	 * @param paramData the param data
	 * @throws Exception the exception
	 */
	public void insertSysDynmcConfigBySelect(SysConfigDTO paramData) throws Exception {
		getSession1().insert("com.plgrim.ncp.commons.sysconfig.insertSysDynmcConfigBySelect", paramData);
	}

	/**
	 * 이전설정상세 조회하여 등록
	 *
	 * @param paramData the param data
	 * @throws Exception the exception
	 */
	public void insertSysDynmcConfigDetailBySelect(SysConfigDTO paramData) throws Exception {
		getSession1().insert("com.plgrim.ncp.commons.sysconfig.insertSysDynmcConfigDetailBySelect", paramData);
	}

}
