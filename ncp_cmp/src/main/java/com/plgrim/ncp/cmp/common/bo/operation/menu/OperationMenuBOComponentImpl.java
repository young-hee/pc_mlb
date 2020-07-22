/* Copyright (c) 2015 plgrim, Inc.
 * All right reserved.
 * http://plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 *
 * ------------------------------------------------------------------------
 * @author      tktaeki.kim
 * @since       2015. 3. 27       
 */
package com.plgrim.ncp.cmp.common.bo.operation.menu;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.plgrim.ncp.cmp.common.bo.operation.OperationMenuBOComponent;
import com.plgrim.ncp.commons.grid.GridCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.plgrim.ncp.base.abstracts.AbstractComponent;
import com.plgrim.ncp.commons.data.FormSysMenuDTO;
import com.plgrim.ncp.commons.data.SysMenuDTO;
import com.plgrim.ncp.commons.result.MenuResult;
import com.plgrim.ncp.commons.result.SysMenuPopupResult;
import com.plgrim.ncp.commons.service.SysMenuService;
import com.plgrim.ncp.framework.data.SystemPK;

// @Slf4j
@Component
public class OperationMenuBOComponentImpl extends AbstractComponent implements OperationMenuBOComponent {

	
	@Autowired
	SysMenuService sysMenuService;

	@Autowired
	private GridCommand gridCommand;
	
	/**
	 * 메뉴관리 목록 조회
	 * @param systemPK
	 * @param paramData
	 * @return
	 * @throws Exception
	 */
	public List<MenuResult> selectListMenu( FormSysMenuDTO paramData) throws Exception {
		return sysMenuService.selectListMenu(paramData);
	}
	
	/**
	 * 메뉴 총 횟수 조회
	 * @param paramData
	 * @return
	 * @throws Exception
	 */
	public long selectCountMenu( FormSysMenuDTO paramData) throws Exception {
		return sysMenuService.selectCountMenu(paramData);
	}
	
    public Page<SysMenuPopupResult> selectListMenuPopupList(SystemPK systemPK,
    		FormSysMenuDTO formSysMenuDTO) throws Exception {
		
		return sysMenuService.selectListMenuPopupList(formSysMenuDTO);
    }
    
	/**
	 * 메뉴관리 목록 excel download 
	 * @param systemPK
	 * @param paramData
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, String>> selectListMenuExcel( FormSysMenuDTO paramData) throws Exception {
		
		List<Map<String, String>> menuResultList = new ArrayList();
		
		Iterator<MenuResult> iterator = selectListMenu(paramData).iterator();
		
		int excelNo = 1;
		while(iterator.hasNext()) {
			MenuResult menuResult = iterator.next();
			
			LinkedHashMap<String,String> dataMap = new LinkedHashMap<String,String>();
			
			dataMap.put("excelNo", String.valueOf(excelNo));
			dataMap.put("boSiteId", menuResult.getSysMenu().getBoSiteId() );
			dataMap.put("naMenuNm",  menuResult.getNaMenuNm() );
			dataMap.put("menuSn",  String.valueOf(menuResult.getSysMenu().getMenuSn()));
			dataMap.put("menuNm",  menuResult.getSysMenu().getMenuNm() );
			dataMap.put("useYn",  "Y".equals(menuResult.getSysMenu().getUseYn())?"사용":"미사용"  );
			dataMap.put("regtrNm",  menuResult.getRegtrNm() );
			dataMap.put("regDt",   getDateService().parseString( menuResult.getSysMenu().getRegDt() , "yyyy-MM-dd")  );
			dataMap.put("udterNm",  menuResult.getUdterNm() );
			dataMap.put("udtDt",   getDateService().parseString( menuResult.getSysMenu().getUdtDt() , "yyyy-MM-dd")  );
			
			menuResultList.add(dataMap);
			excelNo++;
		}
		
		return menuResultList;
		
	}
	
	/**
	 * 메뉴관리 메뉴정보 조회
	 * @param systemPK
	 * @param menuSn
	 * @return
	 * @throws Exception
	 */
	public MenuResult selectRowMenu ( long menuSn ) throws Exception{
		
		MenuResult rowMenu = sysMenuService.selectRowMenu(menuSn);
		
		return rowMenu;
	}
	
	/**
	 * 메뉴관리 메뉴파일목록 조회
	 * @param menuSn
	 * @return
	 * @throws Exception
	 */
	public List<MenuResult> selectMenuFileList ( long menuSn ) throws Exception{
		
		return sysMenuService.selectListFile( menuSn );
	}
	
	
	/**
	 * 멀티메뉴 사용 횟수 조회
	 * @param paramDataList
	 * @return
	 * @throws Exception
	 */
	public long selectUseCntMenuList ( List<SysMenuDTO> paramDataList) throws Exception {
		return sysMenuService.selectUseCntMenuList( paramDataList );
	}


	/**
	 * 메뉴정보 사용/미사용 일괄 저장처리
	 * @param paramData
	 * @throws Exception
	 */
	public void updateMenuList ( List<SysMenuDTO> paramList) throws Exception {

		Iterator<SysMenuDTO> iterator = paramList.iterator();
		while(iterator.hasNext()) {
			//메뉴 사용여부, 순서 수정
			sysMenuService.updateMenuUseYn(iterator.next());
		}
	}

	/**
	 * 메뉴 일괄삭제
	 * @param paramList
	 * @return
	 * @throws Exception
	 */
	public long deleteMenuList ( List<SysMenuDTO> paramList) throws Exception {

		long useCnt = sysMenuService.selectUseCntMenuList(paramList);

		if( useCnt > 0 ) return useCnt;

		Iterator<SysMenuDTO> iterator = paramList.iterator();
		while(iterator.hasNext()) {
			SysMenuDTO rowData = iterator.next();

			//파일 메뉴 삭제
			sysMenuService.deleteFileUpMenu(rowData);

			//메뉴 삭제
			sysMenuService.deleteMenu(rowData);
		}

		return useCnt;

	}

	/**
	 * 메뉴정보 수정처리
	 * @param paramData
	 * @throws Exception
	 */
	public void updateMenu ( SysMenuDTO paramData) throws Exception {

		if(  !"POPUP".equals(paramData.getSysMenu().getOutptTpCd() )){
			paramData.getSysMenu().setPopupLeftLc(0);
			paramData.getSysMenu().setPopupUpendLc(0);
			//paramData.getSysMenu().setPopupWidth(BigDecimal.ZERO);
			paramData.getSysMenu().setPopupHg(0);
		}

		/*if( paramData.getDeleteFileList() != null){
			//삭제된 File URL 삭제
			sysMenuService.deleteFileMenu( paramData.getSysMenu().getBoSiteId(), paramData.getSysMenu().getMenuSn(), paramData.getDeleteFileList());
		}*/

		sysMenuService.updateMenu(paramData);

		List<SysMenuDTO>  insertFileList = gridCommand.getCreateList(  paramData.getSysFileList() );
		if( insertFileList != null){
			sysMenuService.mergeMenuFile( paramData.getSysMenu().getBoSiteId(), paramData.getSysMenu().getMenuSn(), insertFileList);
		}

		List<SysMenuDTO>  updateFileList = gridCommand.getUpdateList(  paramData.getSysFileList() );
		if( updateFileList != null){
			sysMenuService.mergeMenuFile( paramData.getSysMenu().getBoSiteId(), paramData.getSysMenu().getMenuSn(), updateFileList);
		}

		List<SysMenuDTO>  deleteList = gridCommand.getDeleteList(  paramData.getSysFileList() );

		if( deleteList != null){
			sysMenuService.deleteFileMenu( paramData.getSysMenu().getBoSiteId(), paramData.getSysMenu().getMenuSn(), deleteList );
		}

	}


	/**
	 * 메뉴정보 등록처리
	 * @param paramData
	 */
	public void addMenu ( SysMenuDTO paramData) throws Exception {


		if( paramData.getSysMenu().getUpperMenuSn() != null  && paramData.getSysMenu().getUpperMenuSn() > 0){

			MenuResult upperMenuResult = sysMenuService.selectRowMenu(paramData.getSysMenu().getUpperMenuSn());
			if( upperMenuResult != null ){
				paramData.getSysMenu().setBoSiteId( upperMenuResult.getSysMenu().getBoSiteId() );
			}
		}

		paramData.getSysMenu().setMenuImgUrl("");
		paramData.getSysMenu().setMenuUrl("");
		//정렬순서 조회
		paramData.getSysMenu().setSortSeq(1);
		paramData.getSysMenu().setMenuTpCd("MENU");
		paramData.getSysMenu().setFirstPgeYn("N");

		if(  !"POPUP".equals(paramData.getSysMenu().getOutptTpCd() )){
			paramData.getSysMenu().setPopupLeftLc(0);
			paramData.getSysMenu().setPopupUpendLc(0);
			//paramData.getSysMenu().setPopupWidth(BigDecimal.ZERO);
			paramData.getSysMenu().setPopupHg(0);
		}

		long menuSn = sysMenuService.addMenu(paramData);

		if( paramData.getSysFileList()  != null && menuSn > 0 ){
			sysMenuService.mergeMenuFile( paramData.getSysMenu().getBoSiteId(), menuSn, paramData.getSysFileList());
		}

	}

}
