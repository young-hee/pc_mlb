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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.datasource1.sys.SysMenu;
import com.plgrim.ncp.commons.data.FormSysMenuDTO;
import com.plgrim.ncp.commons.data.SysAuthGrpDTO;
import com.plgrim.ncp.commons.data.SysMenuDTO;
import com.plgrim.ncp.commons.result.MenuResult;
import com.plgrim.ncp.commons.result.SysMenuPopupResult;
import com.plgrim.ncp.commons.util.BOSecurityUtil;
import com.plgrim.ncp.framework.enums.DatabaseType;

/**
 * 권한그룹 Repository
 * @author tam
 *
 */
@Slf4j
@Repository
public class SysMenuMgrRepository extends AbstractRepository {

	
	/*######################### select #######################################################################*/
	
	/**
	 * 메뉴관리 목록 조회
	 * @param systemPK
	 * @param paramData
	 * @return
	 * @throws Exception
	 */
	public List<MenuResult> selectListMenu( FormSysMenuDTO paramData) throws Exception {
		log.info(paramData.toString());
		return getSession1().selectList("com.plgrim.ncp.commons.menu.selectListMenu", paramData);
	}
	
	/**
	 * 메뉴 총 횟수
	 * @param paramData
	 * @return
	 * @throws Exception
	 */
	public long selectCountMenu( FormSysMenuDTO paramData) throws Exception {
		log.info(paramData.toString());
		return getSession1().selectOne("com.plgrim.ncp.commons.menu.selectCountMenu", paramData);
	}
	

	/**
	 * 메뉴 팝업 목록 조회
	 * @param searchField
	 * @param searchText
	 * @return
	 * @throws Exception
	 */
	public List<SysMenuPopupResult> selectListMenuPopupList(FormSysMenuDTO formSysMenuDTO) throws Exception {
		
//		Map<String, String> paramData = new HashMap<String, String>();
//		paramData.put("searchField", formSysMenuDTO.getSearchField());
//		paramData.put("searchText", formSysMenuDTO.getSearchText());
		
		log.info("selectListMenu : " + formSysMenuDTO.toString());
		
		return getSession1().selectList("com.plgrim.ncp.commons.menu.selectListMenuPopupList", formSysMenuDTO);
	}
	

	public long selectListMenuPopupCount(FormSysMenuDTO formSysMenuDTO) {
		log.info(formSysMenuDTO.toString());

		return getSession1().selectOne("com.plgrim.ncp.commons.menu.selectListMenuPopupCount", formSysMenuDTO);
    }
	
	/**
	 * 메뉴관리 메뉴정보 조회
	 * @param systemPK
	 * @param menuSn
	 * @return
	 * @throws Exception
	 */
	public MenuResult selectRowMenu ( long menuSn ) throws Exception{
		Map<String,Object> paramData = new HashMap<String,Object>();
		paramData.put("menuSn", menuSn);
		log.info(paramData.toString());
		return getSession1().selectOne("com.plgrim.ncp.commons.menu.selectRowMenu", paramData);
	}
	
	
	/**
	 * 메뉴관리 메뉴하위 파일정보 목록을 조회한다.
	 * @param menuSn
	 * @return
	 * @throws Exception
	 */
	public List<MenuResult> selectListFile( long menuSn) throws Exception {
		
		Map<String,Object> paramData = new HashMap<String,Object>();
		paramData.put("menuSn", menuSn);
		log.info(paramData.toString());
		return getSession1().selectList("com.plgrim.ncp.commons.menu.selectListFile", paramData);
	}
	
	/**
	 * 메뉴 사용 획수 조회
	 * @param paramData
	 * @return
	 * @throws Exception
	 */
	public long selectUseCntMenu ( SysMenuDTO paramData) throws Exception {
		log.info(paramData.toString());
		return getSession1().selectOne("com.plgrim.ncp.commons.menu.selectUseCntMenu", paramData);
	}
	
	/**
	 * 멀티메뉴 사용 횟수 조회
	 * @param paramDataList
	 * @return
	 * @throws Exception
	 */
	public long selectUseCntMenuList ( List<SysMenuDTO> paramDataList) throws Exception {
		
		Map<String,Object> paramData = new HashMap<String,Object>();
		paramData.put("listMenu", paramDataList);
		log.info(paramData.toString());
		return getSession1().selectOne("com.plgrim.ncp.commons.menu.selectUseCntMenuList", paramData);
	}
	
	
	/*######################### command #######################################################################*/
	
	/**
	 * 메뉴정보 등록처리
	 * @param paramData
	 * @return new menu_sn
	 * @throws Exception
	 */
	public long insertMenu ( SysMenuDTO paramData) throws Exception { 
		
		paramData.getSysMenu().setMenuSn(getIdGenService().generateDBSequence(getSession1(), "SQ_SYS_MENU", DatabaseType.ORACLE).longValue());
		paramData.setRegtrId(BOSecurityUtil.getLoginId());
		log.info(paramData.toString());
		getSession1().insert("com.plgrim.ncp.commons.menu.insertMenu", paramData) ;
		return paramData.getSysMenu().getMenuSn();
	}
	
	/**
	 * 메뉴 삭제
	 * @param paramData
	 * @throws Exception
	 */
	public void deleteMenu ( SysMenuDTO paramData) throws Exception { 
		paramData.setRegtrId(BOSecurityUtil.getLoginId());
		log.info(paramData.toString());
		getSession1().delete("com.plgrim.ncp.commons.menu.deleteMenu", paramData) ;
	}
	
	/**
	 * 파일 메뉴 삭제
	 * @param paramData
	 * @throws Exception
	 */
	public void deleteFileMenu ( SysMenu paramData) throws Exception { 
		paramData.setRegtrId(BOSecurityUtil.getLoginId());
		log.info(paramData.toString());
		getSession1().delete("com.plgrim.ncp.commons.menu.deleteFileMenu", paramData) ;
	}
	
	/**
	 * 상위메뉴코드로 FIle Url 정보 삭제
	 * @param paramData
	 * @throws Exception
	 */
	public void deleteFileUpMenu ( SysMenuDTO paramData) throws Exception {
		paramData.setRegtrId(BOSecurityUtil.getLoginId());
		log.info(paramData.toString());
		getSession1().delete("com.plgrim.ncp.commons.menu.deleteFileUpMenu", paramData) ;
	}
	
	/**
	 * 메뉴정보 메뉴정보 수정
	 * @param paramData
	 * @throws Exception
	 */
	public void updateMenu ( SysMenuDTO paramData) throws Exception { 
		paramData.setRegtrId(BOSecurityUtil.getLoginId());
		log.info(paramData.toString());
		getSession1().update("com.plgrim.ncp.commons.menu.updateMenu", paramData) ;
	}
	
	/**
	 * 메뉴 사용/미사용, 순서 수정
	 * @param paramData
	 * @throws Exception
	 */
	public void updateMenuUseYn ( SysMenuDTO paramData) throws Exception { 
		paramData.setRegtrId(BOSecurityUtil.getLoginId());
		log.info(paramData.toString());
		getSession1().update("com.plgrim.ncp.commons.menu.updateMenuUseYn", paramData) ;
	}
	
	
	/**
	 * 메뉴관리 메뉴 FILE(URL) 정보를 등록/수정 처리
	 * @param paramData
	 * @throws Exception
	 */
	public void mergeMenuFile ( SysMenuDTO paramData) throws Exception {
		paramData.setRegtrId(BOSecurityUtil.getLoginId());
		log.info(paramData.toString());
		getSession1().update("com.plgrim.ncp.commons.menu.mergeMenuFile", paramData) ;
	}
}





