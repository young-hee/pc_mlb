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
import com.plgrim.ncp.commons.data.FormSysAuthGrpDTO;
import com.plgrim.ncp.commons.data.SysAuthDTO;
import com.plgrim.ncp.commons.data.SysAuthGrpDTO;
import com.plgrim.ncp.commons.data.SysAuthMenuDTO;
import com.plgrim.ncp.commons.result.AuthGrpResult;
import com.plgrim.ncp.commons.result.AuthResult;
import com.plgrim.ncp.commons.result.MenuResult;
import com.plgrim.ncp.commons.util.BOSecurityUtil;
import com.plgrim.ncp.framework.enums.DatabaseType;

import lombok.extern.slf4j.Slf4j;

/**
 * 권한그룹 Repository
 * @author tam
 *
 */
@Slf4j
@Repository
public class SysAuthRepository extends AbstractRepository {

	/**
	 * 권한그룹 목록 조회
	 * @param sysAuthGrpDTO
	 * @return
	 */
	public List<AuthGrpResult> selectListAuthGrp( FormSysAuthGrpDTO paramData) throws Exception {
		log.info(paramData.toString());
		
		return getSession1().selectList("com.plgrim.ncp.commons.auth.selectListAuthGrp", paramData);
	}
	
	/**
	 * 권한 메뉴 목록 조회
	 * @param paramData
	 * @return
	 */
	public List<AuthResult> selectListAuthMenu( FormSysAuthGrpDTO paramData) throws Exception {
		return getSession1().selectList("com.plgrim.ncp.commons.auth.selectListAuthMenu", paramData);
	}
	
	/**
	 * 권한(기능) 목록 조회
	 * @param paramData
	 * @return
	 */
	public List<AuthResult> selectListAuth( FormSysAuthGrpDTO paramData) throws Exception {
		return getSession1().selectList("com.plgrim.ncp.commons.auth.selectListAuth", paramData);
	}
	
	/**
	 * 권한그룹사용 횟수 조회
	 * @param paramData
	 * @return
	 */
	public long selectUseCntAuthGrp ( SysAuthGrpDTO paramData) throws Exception {
		return getSession1().selectOne("com.plgrim.ncp.commons.auth.selectUseCntAuthGrp", paramData);
	}
	
	/**
	 * 권한(메뉴/기능) 사용 횟수 조회
	 * @param paramData
	 * @return
	 */
	public long selectUseCntAuth ( SysAuthDTO paramData) throws Exception {
		return getSession1().selectOne("com.plgrim.ncp.commons.auth.selectUseCntAuth", paramData);
	}
	
	/**
	 * 권한 메뉴 목록 조회
	 * @param paramData
	 * @return
	 */
	public List<MenuResult> selectListMenu( FormSysAuthGrpDTO paramData) throws Exception {
		return getSession1().selectList("com.plgrim.ncp.commons.auth.selectListMenu", paramData);
	}
	
	/**
	 * 메뉴 목록(로그인한 사용자의 권한 그룹별 메뉴 목록)
	 * @param paramData
	 * @return
	 */
	public List<MenuResult> selectListRoleMenuAll( SysAuthMenuDTO paramData) throws Exception {
		return getSession1().selectList("com.plgrim.ncp.commons.auth.selectListRoleMenuAll", paramData);
	}
	
	/*######################### command #######################################################################*/
	/**
	 * 권한그룹 저장 처리
	 * @param paramData
	 */
	public void mergeAuthGrp ( SysAuthGrpDTO paramData) throws Exception {
		paramData.setRegtrId(BOSecurityUtil.getLoginId());
		
		log.info(paramData.toString());
		
		getSession1().update("com.plgrim.ncp.commons.auth.mergeAuthGrp", paramData) ;
		
		// 권한변경이력을 남기기 위해 추가되는 부분 kenny 
		
		String sqlId = "com.plgrim.ncp.commons.auth.mergeAuthGrpHistMerge";
		
		// AuthorGrpSn이 null 이거나 0이면 새로 생성되는 권한이므로 단순 insert트를 실행
		if(paramData.getSysAuthorGrp().getAuthorGrpSn()==null||paramData.getSysAuthorGrp().getAuthorGrpSn()==0){
			sqlId = "com.plgrim.ncp.commons.auth.mergeAuthGrpHistInsert";
		}
		
		getSession1().insert(sqlId, paramData);
	}
	
	/**
	 * 권한그룹 복사
	 * @param paramData
	 */
	public void copyAuthGrp ( SysAuthGrpDTO paramData) throws Exception {
		
		paramData.setNewAuthorGrpSn(getIdGenService().generateDBSequence(getSession1(), "SQ_SYSAUTHOR_GRP", DatabaseType.ORACLE).longValue());
		paramData.setRegtrId(BOSecurityUtil.getLoginId());
		
		//권한그룹 복사
		getSession1().insert( "com.plgrim.ncp.commons.auth.copyAuthGrp", paramData);
		getSession1().insert( "com.plgrim.ncp.commons.auth.copyAuthGrpHist", paramData);
	}
	
	/**
	 * 권한(기능/메뉴) 복사
	 * @param paramData
	 */
	public void copyAuth ( SysAuthGrpDTO paramData) throws Exception {
		paramData.setRegtrId(BOSecurityUtil.getLoginId());
		getSession1().insert( "com.plgrim.ncp.commons.auth.copyAuth", paramData);
	}
	
	
	/**
	 * 권한(기능/메뉴)정보 삭제
	 * @param paramData
	 */
	public void deleteAdmAuth ( SysAuthDTO paramData) throws Exception {
		paramData.setRegtrId(BOSecurityUtil.getLoginId());
		getSession1().delete( "com.plgrim.ncp.commons.auth.deleteAdmAuth", paramData);
		paramData.getSysAuthor().setAuthorResrcNm("###권한그룹 메뉴권한삭제###");
		if(paramData.getSysMenu() !=null&& paramData.getSysMenu().getMenuSn()!=null){
			paramData.getSysAuthor().setMenuSn(paramData.getSysMenu().getMenuSn());
		}
//		getSession1().insert( "com.plgrim.ncp.commons.auth.insertAuthHist", paramData);
	}
	
	/**
	 * 권한그룹정보 삭제
	 * @param paramData
	 */
	public void deleteAdmAuthGrp ( SysAuthGrpDTO paramData) throws Exception {
		getSession1().delete( "com.plgrim.ncp.commons.auth.deleteAdmAuthGrp", paramData);
		paramData.setRegtrId(BOSecurityUtil.getLoginId());
		paramData.getSysAuthorGrp().setAuthorGrpDscr("###권한그룹 삭제###");
		paramData.setNewAuthorGrpSn(paramData.getSysAuthorGrp().getAuthorGrpSn());
//		getSession1().insert( "com.plgrim.ncp.commons.auth.mergeAuthGrpHistInsert", paramData);
	}
	
	
	/**
	 * 권한 메뉴 등록 처리
	 * @param paramData
	 * @throws Exception
	 */
	public void insertAuth ( SysAuthDTO paramData) throws Exception {
		paramData.setRegtrId(BOSecurityUtil.getLoginId());
		getSession1().insert( "com.plgrim.ncp.commons.auth.insertAuth", paramData);
		getSession1().insert( "com.plgrim.ncp.commons.auth.insertAuthHist", paramData);
	}
	
	
	/**
	 * 버튼 권한 수정 처리
	 * @param paramData
	 * @throws Exception
	 */
	public void updateAuth ( SysAuthDTO paramData) throws Exception {
		
		log.info("updateAuth="+paramData.toString());
		
		paramData.setRegtrId(BOSecurityUtil.getLoginId());
		getSession1().update( "com.plgrim.ncp.commons.auth.updateAuth", paramData);
		getSession1().insert( "com.plgrim.ncp.commons.auth.insertAuthHist", paramData);
	}
	
	/**
	 * 마스킹 권한 처리
	 * @param paramData
	 * @throws Exception
	 */
	public void updateMaskingAuth ( SysAuthDTO paramData) throws Exception {
		
		log.info("updateMaskingAuth="+paramData.toString());
		
		paramData.setRegtrId(BOSecurityUtil.getLoginId());
		getSession1().update( "com.plgrim.ncp.commons.auth.updateMaskingAuth", paramData);
		getSession1().insert( "com.plgrim.ncp.commons.auth.insertAuthHist", paramData);
	}
	
	
}





