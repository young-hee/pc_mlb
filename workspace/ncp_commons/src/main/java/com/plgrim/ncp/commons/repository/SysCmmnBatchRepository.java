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
import com.plgrim.ncp.base.entities.datasource1.sys.SysChckHist;
import com.plgrim.ncp.commons.result.SysBatchResult;

@Repository
public class SysCmmnBatchRepository extends AbstractRepository {

	/**
	 * 배치 리스트 조회
	 */
	public List<SysBatchResult> selectListSysBatch(SysBatchResult paramData) throws Exception {
		return getSession1().selectList("com.plgrim.ncp.commons.sysbatch.selectListSysBatch", paramData);
	}
	
	/**
	 * 시스템 점검 이력 리스트 조회
	 */
	public List<SysChckHist> getSysChckHistList(SysChckHist sysChckHist) throws Exception {
		return getSession1().selectList("com.plgrim.ncp.commons.sysbatch.selectListSysChckHist", sysChckHist);
	}
	
	/**
	 * merge 사이트 속도 측정
	 */
	/*
	public int mergeElasticsearchSpeed(SysBatchSpeed sysBatchSpeed) throws Exception {
		return getSession1().update("com.plgrim.ncp.commons.sysbatch.mergeElasticsearchSpeed", sysBatchSpeed);
	}
	*/
}
