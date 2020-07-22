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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import com.plgrim.ncp.base.abstracts.AbstractService;
import com.plgrim.ncp.base.entities.RepositoryHelper;
import com.plgrim.ncp.base.entities.datasource1.sys.SysMenu;
import com.plgrim.ncp.commons.data.FormSysMenuDTO;
import com.plgrim.ncp.commons.data.SysMenuDTO;
import com.plgrim.ncp.commons.repository.SysMenuMgrRepository;
import com.plgrim.ncp.commons.result.MenuResult;
import com.plgrim.ncp.commons.result.SysMenuPopupResult;

/**
 * EditorService Implementation
 * @author Tam
 *
 */
@Service
public class SysMenuService extends AbstractService {

	
	@Autowired
    SysMenuMgrRepository sysMenuMgrRepository;

	
	/**
	 * 메뉴관리 목록 조회
	 * @param systemPK
	 * @param paramData
	 * @return
	 * @throws Exception
	 */
	public List<MenuResult> selectListMenu( FormSysMenuDTO paramData) throws Exception {
		return sysMenuMgrRepository.selectListMenu(paramData);
	}
	
	/**
	 * 메뉴 총 횟수
	 * @param paramData
	 * @return
	 * @throws Exception
	 */
	public long selectCountMenu( FormSysMenuDTO paramData) throws Exception {
		return sysMenuMgrRepository.selectCountMenu(paramData);
	}
	
	/**
	 * 메뉴 팝업 목록 조회
	 * @param systemPK
	 * @param paramData
	 * @return
	 * @throws Exception
	 */
	public Page<SysMenuPopupResult> selectListMenuPopupList( FormSysMenuDTO formSysMenuDTO) throws Exception {
		RepositoryHelper.makePageEntityIndex(formSysMenuDTO, formSysMenuDTO.getPageParam());  // 페이지 설정

		// 목록 건수 조회
		long totalRow = sysMenuMgrRepository.selectListMenuPopupCount(formSysMenuDTO);
		
		// 목록 조회
		List<SysMenuPopupResult> results = new ArrayList<SysMenuPopupResult>();
		if(totalRow > 0){
			results = sysMenuMgrRepository.selectListMenuPopupList(formSysMenuDTO);
		}
		
		return new PageImpl<SysMenuPopupResult>(results, formSysMenuDTO.getPageParam().getPageable(), totalRow);
		
	}
	
	
	
	/**
	 * 메뉴관리 메뉴정보 조회
	 * @param systemPK
	 * @param menuSn
	 * @return
	 * @throws Exception
	 */
	public MenuResult selectRowMenu ( long menuSn ) throws Exception{
		return sysMenuMgrRepository.selectRowMenu(menuSn);
	}
	
	/**
	 * 메뉴관리 메뉴 파일정보를 조회
	 * @param menuSn
	 * @return
	 * @throws Exception
	 */
	public List<MenuResult> selectListFile ( long menuSn ) throws Exception{
		return sysMenuMgrRepository.selectListFile(menuSn);
	}
	
	/**
	 * 멀티메뉴 사용 횟수 조회
	 * @param paramDataList
	 * @return
	 * @throws Exception
	 */
	public long selectUseCntMenuList ( List<SysMenuDTO> paramDataList) throws Exception {
		return sysMenuMgrRepository.selectUseCntMenuList(paramDataList);
	}
	
	/*#####################################Cmd Function ###################################################################################*/
	
	/**
	 * 메뉴정보 수정처리
	 * @param paramData
	 * @throws Exception
	 */
	public void updateMenu ( SysMenuDTO paramData) throws Exception {
		//변경된 메뉴정보를 수정한다.
		sysMenuMgrRepository.updateMenu(paramData);
	}
	
	/**
	 * 메뉴 사용여부, 순서 수정
	 * @param paramData
	 * @throws Exception
	 */
	public void updateMenuUseYn ( SysMenuDTO paramData) throws Exception {
		sysMenuMgrRepository.updateMenuUseYn(paramData);
	}
	
	/**
	 * 파일 메뉴 삭제
	 * @param paramData
	 * @throws Exception
	 */
	//public void deleteFileMenu ( String boSiteId, long upMenuSn , List<Long> menuSnList) throws Exception { 
	public void deleteFileMenu ( String boSiteId, long upMenuSn , List<SysMenuDTO> menuSnList) throws Exception { 
		
		SysMenu deleteData = new  SysMenu();
		
		deleteData.setBoSiteId( boSiteId );
		deleteData.setUpperMenuSn( upMenuSn );
		
		for( int i =0; i < menuSnList.size(); i++ ){
			SysMenuDTO rowData =  menuSnList.get(i);
			deleteData.setMenuSn( rowData.getSysMenu().getMenuSn());
			sysMenuMgrRepository.deleteFileMenu( deleteData);
		}
	}

	/**
	 * 상위메뉴로 File 메뉴 삭제
	 * @param paramData
	 * @throws Exception
	 */
	public void deleteFileUpMenu ( SysMenuDTO paramData) throws Exception { 
		sysMenuMgrRepository.deleteFileUpMenu(paramData);
	}
	
	/**
	 * 메뉴 삭제
	 * @param paramData
	 * @throws Exception
	 */
	public void deleteMenu ( SysMenuDTO paramData) throws Exception { 
		sysMenuMgrRepository.deleteMenu(paramData);
	}
	
	/**
	 *  메뉴정보 등록처리
	 * @param paramData
	 * @throws Exception
	 */
	public long addMenu ( SysMenuDTO paramData) throws Exception {
		//변경된 메뉴정보 등록 처리.
		return sysMenuMgrRepository.insertMenu(paramData);
	}
	
	/**
	 * 메뉴파일 정보를 등록/수정 처리
	 * @param upMenuSn
	 * @param paramList
	 * @throws Exception
	 */
	public void mergeMenuFile( String boSiteId, long upMenuSn, List<SysMenuDTO> paramList) throws Exception {
		
		if( paramList != null){
			
			Iterator<SysMenuDTO> iterator = paramList.iterator();
			while(iterator.hasNext()) {
				SysMenuDTO paramData = iterator.next();
				
				if( isGcudTag(paramData)){
					
					paramData.getSysMenu().setBoSiteId(boSiteId);
					paramData.getSysMenu().setUpperMenuSn(upMenuSn);
					paramData.getSysMenu().setMenuImgUrl("");
					paramData.getSysMenu().setUseYn("Y");
					paramData.getSysMenu().setSortSeq(1);//정렬순서 조회
					paramData.getSysMenu().setMenuTpCd("FILE");
					
					if( paramData.getRadioFirstPgeYn() != null ){
						paramData.getSysMenu().setFirstPgeYn( "1".equals(paramData.getRadioFirstPgeYn())?"Y":"N");
					}else{
						paramData.getSysMenu().setFirstPgeYn(  "1".equals(paramData.getSysMenu().getFirstPgeYn())?"Y":"N");
					}
					
					paramData.getSysMenu().setOutptTpCd("");
					paramData.getSysMenu().setPopupLeftLc(0);
					paramData.getSysMenu().setPopupUpendLc(0);
					//paramData.getSysMenu().setPopupWidth(0);
					paramData.getSysMenu().setPopupHg(0);
					
					sysMenuMgrRepository.mergeMenuFile( paramData);
				
				}
			}
		}
		
	}
	
	
	private boolean isGcudTag(  SysMenuDTO element) {
		String value = (String) element.getGCudTag();
			
		//구버전 호환성을 위해 코딩함. GridUtil.js 배포 변경 확인후 삭제할것
		if(value == null ) {
			value = (String) element.getGridCudTag();
		}
		 
		if (value.equals("U") || value.equals("C")) {
			 return true;
		}
		
		return false;
	}
}
