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

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.datasource1.sys.SysAdminLoginLog;
import com.plgrim.ncp.base.entities.datasource1.sys.SysAdminUseLog;
import com.plgrim.ncp.commons.data.FormSysAdmlogDTO;
import com.plgrim.ncp.commons.result.AdmLogResult;
import com.plgrim.ncp.commons.result.AdmMsgLogResult;
import com.plgrim.ncp.commons.util.BOSecurityUtil;

/**
 * 운영 로그 관리 Repository
 * @author tam
 *
 */
@Slf4j
@Repository
public class SysAdmlogRepository extends AbstractRepository {

	
	/*######################### select #######################################################################*/
	
	/**
	 * 운영자로그인로그 목록조회
	 * @param paramData
	 * @return
	 * @throws Exception
	 */
	public List<AdmLogResult> selectListLoginLog( FormSysAdmlogDTO paramData) throws Exception {
		log.info(paramData.toString());
		paramData.setDbMarkingYn(BOSecurityUtil.checkMarkingFromAccessUrl());
		return getSession1().selectList("com.plgrim.ncp.commons.admlog.selectListLoginLog", paramData);
	}

	
	/**
	 * 운영자로그인로그 총로그수
	 * @param paramData
	 * @return
	 * @throws Exception
	 */
	public long selectCountLoginLog( FormSysAdmlogDTO paramData) throws Exception {
		log.debug(paramData.toString());
		return getSession1().selectOne("com.plgrim.ncp.commons.admlog.selectCountLoginLog", paramData);
	}
	
	
	
	/**
	 * 운영자메뉴내역 로그조회
	 * @param paramData
	 * @return
	 * @throws Exception
	 */
	public List<AdmLogResult> selectListMenuLog( FormSysAdmlogDTO paramData) throws Exception {
		log.debug(paramData.toString());
		return getSession1().selectList("com.plgrim.ncp.commons.admlog.selectListMenuLog", paramData);
	}
	
	/**
	 * 운영자메뉴내역 총로그수
	 * @param paramData
	 * @return
	 * @throws Exception
	 */
	public long selectCountMenuLog( FormSysAdmlogDTO paramData) throws Exception {
		log.debug(paramData.toString());
		return getSession1().selectOne("com.plgrim.ncp.commons.admlog.selectCountMenuLog", paramData);
	}
	
	/**
	 * 운영자메뉴->File이용 내역
	 * @param paramData
	 * @return
	 * @throws Exception
	 */
	public List<AdmLogResult> selectListFileLog( FormSysAdmlogDTO paramData) throws Exception {
		log.debug(paramData.toString());
		paramData.setDbMarkingYn(BOSecurityUtil.checkMarkingFromAccessUrl());
		return getSession1().selectList("com.plgrim.ncp.commons.admlog.selectListFileLog", paramData);
	}
	
	/**
	 * 운영자메뉴File이용 총로그수
	 * @param paramData
	 * @return
	 * @throws Exception
	 */
	public long selectCountFileLog( FormSysAdmlogDTO paramData) throws Exception {
		log.debug(paramData.toString());
		return getSession1().selectOne("com.plgrim.ncp.commons.admlog.selectCountFileLog", paramData);
	}
	
	
	/**
	 * 메세지 로그 목록를 조회한다.
	 * @param paramData
	 * @return
	 * @throws Exception
	 */
	public List<AdmMsgLogResult> selectListMsgLog( FormSysAdmlogDTO paramData) throws Exception {
		log.debug(paramData.toString());
		paramData.setDbMarkingYn(BOSecurityUtil.checkMarkingFromAccessUrl());
		return getSession1().selectList("com.plgrim.ncp.commons.admlog.selectListMsgLog", paramData); 
	}
	
	/**
	 * 메세지로그 총 카운터 수
	 * @param paramData
	 * @return
	 * @throws Exception
	 */
	public long selectCountMsgLog( FormSysAdmlogDTO paramData) throws Exception {
		log.debug(paramData.toString());
		return getSession1().selectOne("com.plgrim.ncp.commons.admlog.selectCountMsgLog", paramData);
	}
	
	/*######################### command #######################################################################*/
	
	/**
	 * 운영자 로그인로그 등록
	 * @param paramData
	 * @throws Exception
	 */
	public void insertLoginLog ( SysAdminLoginLog paramData) throws Exception {
		log.debug(paramData.toString());
		getSession1().insert("com.plgrim.ncp.commons.admlog.insertLoginLog", paramData);
	}
	
	/**
	 * 운영자 메뉴사용로그 등록
	 * @param paramData
	 * @throws Exception
	 */
	public void insertMenuUseLog ( SysAdminUseLog paramData) throws Exception {
		log.debug(paramData.toString());
		getSession1().insert("com.plgrim.ncp.commons.admlog.insertMenuUseLog", paramData);
	}
	
}





