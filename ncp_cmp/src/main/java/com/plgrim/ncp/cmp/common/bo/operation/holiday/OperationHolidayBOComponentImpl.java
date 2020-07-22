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
 * @since       2015. 5. 29       
 */
package com.plgrim.ncp.cmp.common.bo.operation.holiday;

import java.util.List;
import java.util.Map;

import com.plgrim.ncp.cmp.common.bo.operation.OperationHolidayBOComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.plgrim.ncp.base.abstracts.AbstractComponent;
import com.plgrim.ncp.commons.data.SysHldyDTO;
import com.plgrim.ncp.commons.result.SysHldyResult;
import com.plgrim.ncp.commons.service.SysHldyService;
import com.plgrim.ncp.framework.data.SystemPK;

// @Slf4j
@Component
public class OperationHolidayBOComponentImpl extends AbstractComponent implements OperationHolidayBOComponent {

	@Autowired
	SysHldyService sysHldyService; // 휴일관리 Service
	
	/**
	 * 휴일 목록 조회.
	 *
	 * @param systemPK [설명]
	 * @param paramData [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public List<SysHldyResult> selectSysHldyList( SystemPK systemPK, SysHldyDTO paramData) throws Exception {
		return sysHldyService.selectSysHldyList(paramData); 
	}
	
	/**
	 * 휴일 목록 조회 개수.
	 *
	 * @param systemPK [설명]
	 * @param paramData [설명]
	 * @return Long [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public long selectSysHldyListCount( SystemPK systemPK, SysHldyDTO paramData) throws Exception {
		return sysHldyService.selectSysHldyListCount(paramData); 
	}
	
	/**
	 * 휴일 목록 조회 엑셀.
	 *
	 * @param systemPK [설명]
	 * @param paramData [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public List<Map<String, Object>> selectSysHldyListExcel( SystemPK systemPK, SysHldyDTO paramData) throws Exception {
		return sysHldyService.selectSysHldyListExcel(paramData); 
	}
	
	/**
	 * 휴일 상세 조회.
	 *
	 * @param systemPK [설명]
	 * @param paramData [설명]
	 * @return Sys cmmn noti result [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public SysHldyResult selectSysHldyDetail( SystemPK systemPK, SysHldyDTO paramData) throws Exception {
		return sysHldyService.selectSysHldyDetail(paramData); 
	}

	/**
	 * 휴일 등록.
	 *
	 * @param systemPK [설명]
	 * @param paramData [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public void insertSysHldy( SystemPK systemPK, SysHldyDTO paramData) throws Exception {
		sysHldyService.insertSysHldy(paramData);
	}

	/**
	 * 휴일 수정.
	 *
	 * @param systemPK [설명]
	 * @param paramData [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public void updateSysHldy( SystemPK systemPK, SysHldyDTO paramData) throws Exception {
		sysHldyService.updateSysHldy(paramData);
	}

	/**
	 * 휴일 삭제.
	 *
	 * @param systemPK [설명]
	 * @param paramData [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public void deleteSysHldy( SystemPK systemPK, SysHldyDTO paramData) throws Exception {
		sysHldyService.deleteSysHldy(paramData);
	}

}
