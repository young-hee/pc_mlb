/* Copyright (c) 2015 plgrim, Inc.
 * All right reserved.
 * http://plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 *
 * ------------------------------------------------------------------------
 * @author      dennis.na
 * @since       2015. 5. 7       
 */
package com.plgrim.ncp.commons.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.datasource1.sys.SysCd;
import com.plgrim.ncp.base.entities.datasource1.sys.SysCdLang;
import com.plgrim.ncp.base.entities.datasource1.sys.SysGrpCd;
import com.plgrim.ncp.base.entities.datasource1.sys.SysGrpCdCdCnnc;
import com.plgrim.ncp.base.entities.datasource1.sys.SysGrpCdCdCnncExtend;
import com.plgrim.ncp.base.entities.datasource1.sys.SysGrpCdExtend;
import com.plgrim.ncp.commons.data.FormSysCodeDTO;
import com.plgrim.ncp.commons.result.SysCodeResult;
import com.plgrim.ncp.commons.util.BOSecurityUtil;

/**
 * Sys Code Repository.
 *
 * @author Dennis
 */

@Slf4j
@Repository
public class SysCodeRepository extends AbstractRepository {
	
	/**
	 * Sys Code GrpCd list 조회.
	 * @param FormSysCodeDTO
	 * @return
	 */
	public List<SysCodeResult> selectListCdGrp( FormSysCodeDTO  paramData) throws Exception {
		log.info(paramData.toString());
		return getSession1().selectList("com.plgrim.ncp.commons.code.selectListCdGrp", paramData);
	}
	
	/**
	 * Sys Code Cd list 조회.
	 * @param FormSysCodeDTO
	 * @return
	 */
	public List<SysCodeResult> selectListCdGrpDetail( FormSysCodeDTO  paramData) throws Exception {
		log.info(paramData.toString());
		
		return getSession1().selectList("com.plgrim.ncp.commons.code.selectListCdGrpDetail", paramData);
	}

	/**
	 * 공통그룹코드들을 조회한다.
	 * @param paramData
	 * @return
	 * @throws Exception
	 */
	public List<String> selectGrpCds( FormSysCodeDTO  paramData) throws Exception {
		log.info(paramData.toString());
		
		return getSession1().selectList("com.plgrim.ncp.commons.code.selectGrpCds", paramData);
	}
	
	
	/**
	 * SysCd 코드 사용 유/무
	 * @param paramData
	 * @return
	 * @throws Exception
	 */
	public boolean isUseFromSysCd( String cd) throws Exception {
		
		Map<String, String> paramData = new HashMap();
		paramData.put("cd", cd);
		log.info(paramData.toString());
		
		String checkYn = getSession1().selectOne("com.plgrim.ncp.commons.code.checkYnFromSysCd", paramData);
		
		return ("Y".equals(checkYn)?true:false);
	}
	
	
	/**
	 * 공통그룹코드 정보를 조회한다.
	 * @param grpCd
	 * @return
	 * @throws Exception
	 */
	public SysGrpCdExtend selectRowGrpCd( String grpCd) throws Exception {
		Map<String, String> paramData = new HashMap();
		paramData.put("grpCd", grpCd);
		log.info(paramData.toString());
		
		SysCodeResult sysCodeResult = getSession1().selectOne("com.plgrim.ncp.commons.code.selectRowGrpCd", paramData);
		return sysCodeResult.getSysGrpCd();
	}
	
	/**
	 * 공통그룹 상세코드정보를 조회한다.
	 * @param grpCd
	 * @param cd
	 * @return
	 * @throws Exception
	 */
	public SysGrpCdCdCnncExtend selectRowCdGrpDetail( String grpCd, String cd) throws Exception {
		Map<String, String> paramData = new HashMap();
		paramData.put("grpCd", grpCd);
		paramData.put("cd", cd);
		log.info(paramData.toString());
		
		SysCodeResult sysCodeResult = getSession1().selectOne("com.plgrim.ncp.commons.code.selectRowCdGrpDetail", paramData);
		return sysCodeResult.getSysGrpCdCdCnnc();
	}
	
	
	/*##########################################################################################
	 * CMD
	 ###########################################################################################*/
	
	/**
	 * Sys code 등록/수정 처리 
	 * @param paramData
	 * @throws Exception
	 */
	public void mergeSysCd ( SysCd paramData) throws Exception {
		paramData.setRegtrId(BOSecurityUtil.getLoginId());
		log.info(paramData.toString());
		getSession1().update("com.plgrim.ncp.commons.code.mergeSysCd", paramData);
	}
	
	/**
	 * 다국어 공통코드명 등록/수정 처리
	 * @param paramData
	 * @throws Exception
	 */
	public void mergeSysCdLang ( SysCdLang paramData) throws Exception {
		paramData.setRegtrId(BOSecurityUtil.getLoginId());
		log.info(paramData.toString());
		getSession1().update("com.plgrim.ncp.commons.code.mergeSysCdLang", paramData);
	}
	
	/**
	 * 공통그룹코드 등록
	 * @param paramData
	 * @throws Exception
	 */
	public void insertSysGrpCd ( SysGrpCd paramData) throws Exception {
		paramData.setRegtrId(BOSecurityUtil.getLoginId());
		log.info(paramData.toString());
		getSession1().insert("com.plgrim.ncp.commons.code.insertSysGrpCd", paramData);
	}
	
	/**
	 * 공통그룹코드 수정
	 * @param paramData
	 * @throws Exception
	 */
	public void updateSysGrpCd ( SysGrpCd paramData) throws Exception {
		paramData.setRegtrId(BOSecurityUtil.getLoginId());
		log.info(paramData.toString());
		getSession1().update("com.plgrim.ncp.commons.code.updateSysGrpCd", paramData);
	}
	
	/**
	 * 공통그룹 상세코드 등록
	 * @param paramData
	 * @throws Exception
	 */
	public void insertSysGrpCdCdCnnc ( SysGrpCdCdCnnc paramData) throws Exception {
		paramData.setRegtrId(BOSecurityUtil.getLoginId());
		log.info(paramData.toString());
		getSession1().insert("com.plgrim.ncp.commons.code.insertSysGrpCdCdCnnc", paramData);
	}
	
	/**
	 * 공통그룹 상세코드 수정
	 * @param paramData
	 * @throws Exception
	 */
	public void updateSysGrpCdCdCnnc ( SysGrpCdCdCnnc paramData) throws Exception {
		paramData.setRegtrId(BOSecurityUtil.getLoginId());
		log.info(paramData.toString());
		getSession1().update("com.plgrim.ncp.commons.code.updateSysGrpCdCdCnnc", paramData);
	}
	
	/**
	 * refresh MVIEW 
	 * @throws Exception
	 */
	public void refreshMview() throws Exception {
		Map<String, String> paramData = new HashMap();
		log.info( "refreshMview 실행");
		getSession1().update("com.plgrim.ncp.commons.code.refreshMview", paramData);
		
	}
	
}
