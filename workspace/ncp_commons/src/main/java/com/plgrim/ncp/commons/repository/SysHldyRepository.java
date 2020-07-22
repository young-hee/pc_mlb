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
 * @since       2015. 6. 3       
 */
package com.plgrim.ncp.commons.repository;

import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.datasource1.sys.SysCldr;
import com.plgrim.ncp.commons.data.SysHldyDTO;
import com.plgrim.ncp.commons.data.SysHldyDataDTO;
import com.plgrim.ncp.commons.result.SysHldyResult;

/**
 * 휴일관리 Repository
 * @author ed
 *
 */
@Slf4j
@Repository
public class SysHldyRepository extends AbstractRepository {
	
	/**
	 * 휴일 목록 조회.
	 *
	 * @param paramData [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public List<SysHldyResult> selectSysHldyList( SysHldyDTO paramData) throws Exception {
		return getSession1().selectList("com.plgrim.ncp.commons.hldy.selectSysHldyList", paramData);
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
		return getSession1().selectOne("com.plgrim.ncp.commons.hldy.selectSysHldyListCount", paramData);
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
		return getSession1().selectList("com.plgrim.ncp.commons.hldy.selectSysHldyListExcel", paramData);
	}
	
	/**
	 * 휴일 상세 조회.
	 *
	 * @param paramData [설명]
	 * @return Sys cmmn noti result
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public SysHldyResult selectSysHldyDetail( SysHldyDTO paramData) throws Exception {
		return getSession1().selectOne("com.plgrim.ncp.commons.hldy.selectSysHldyDetail", paramData);
	}
	
	/**
	 * 휴일 등록.
	 *
	 * @param paramData [설명]
	 * @return int
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public int insertSysHldy(SysCldr paramData) throws Exception {
		return getSession1().update("com.plgrim.ncp.commons.hldy.insertSysHldy", paramData);
	}
	
	/**
	 * 휴일 삭제.
	 *
	 * @param paramData [설명]
	 * @return Int [설명]
	 * @throws Exception the exception
	 * @since 2015. 6. 1
	 */
	public int deleteSysHldy(SysHldyDataDTO paramData) throws Exception {
		return getSession1().delete("com.plgrim.ncp.commons.hldy.deleteSysHldy", paramData);
	}
}





