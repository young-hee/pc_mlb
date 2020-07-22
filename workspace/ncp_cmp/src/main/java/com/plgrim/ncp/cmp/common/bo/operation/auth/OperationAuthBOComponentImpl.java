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
package com.plgrim.ncp.cmp.common.bo.operation.auth;

import java.util.*;

import com.plgrim.ncp.cmp.common.bo.operation.OperationAuthBOComponent;
import com.plgrim.ncp.commons.data.*;
import com.plgrim.ncp.commons.result.AuthGrpResult;
import com.plgrim.ncp.commons.result.AuthResult;
import com.plgrim.ncp.commons.result.MenuResult;
import com.plgrim.ncp.framework.data.SystemPK;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.plgrim.ncp.base.abstracts.AbstractComponent;
import com.plgrim.ncp.base.entities.datasource1.sys.SysAuthor;
import com.plgrim.ncp.commons.service.SysAuthGrpService;

@Slf4j
@Transactional(value = "transactionManager")
@Component
public class OperationAuthBOComponentImpl extends AbstractComponent implements OperationAuthBOComponent {

	
	@Autowired
	SysAuthGrpService sysAuthGrpService;
	
	
	/**
	 * 권한그룹 저장 처리
	 * @param paramData
	 */
	public void mergeAuthGrp (  SysAuthGrpDTO paramData) throws Exception {
		sysAuthGrpService.mergeAuthGrp(paramData);
	}
	
	/**
	 * 일괄 권한그룹 저장처리
	 */
	public void updateAuthGrp ( List<SysAuthGrpDTO> paramList) throws Exception{
		Iterator<SysAuthGrpDTO> iterator = paramList.iterator();
		while(iterator.hasNext()) {
			sysAuthGrpService.mergeAuthGrp( iterator.next());
		}
	}
	/**
	 * 권한그룹 복사
	 * @param paramData
	 */
	public void copyAuthGrp (  SysAuthGrpDTO paramData) throws Exception {
		sysAuthGrpService.copyAuthGrp(paramData);
	}
	
	
	/**
	 * 권한(기능/메뉴)정보 삭제
	 * @param paramData
	 */
	public void updateAuth (  long authorGrpSn, long menuSn, List<SysAuthDTO> paramList) throws Exception {
		
		
		Iterator<SysAuthDTO> iterator = paramList.iterator();
		
		SysAuthDTO paramDTO = new SysAuthDTO();
		
		SysAuthor sysAuthor = new SysAuthor();
		sysAuthor.setAuthorGrpSn(authorGrpSn);
		sysAuthor.setMenuSn(menuSn);
		
		String authorResrcNm ="";
		String authorResrcVal ="";
		
		while(iterator.hasNext()){
			
			SysAuthDTO sysAuthDTO = iterator.next();
			
			if( "Y".equals(sysAuthDTO.getUseYn()) ){
				
				if( !"".equals(authorResrcNm)){
					authorResrcNm += ",";
					authorResrcVal += ",";
				}
				authorResrcNm += sysAuthDTO.getSysAuthor().getAuthorResrcNm();
				authorResrcVal += sysAuthDTO.getSysAuthor().getAuthorResrcVal();
			}
			
		}
		
		sysAuthor.setAuthorResrcNm(authorResrcNm);
		sysAuthor.setAuthorResrcVal(authorResrcVal);
		
		paramDTO.setSysAuthor( sysAuthor );
		
		sysAuthGrpService.updateAuth(paramDTO);
	}
	
	public void updateAuthButton (  long authorGrpSn, long menuSn, List<SysAuthDTO> paramList) throws Exception {
		
		
		Iterator<SysAuthDTO> iterator = paramList.iterator();
		
		SysAuthDTO paramDTO = new SysAuthDTO();
		
		SysAuthor sysAuthor = new SysAuthor();
		sysAuthor.setAuthorGrpSn(authorGrpSn);
		sysAuthor.setMenuSn(menuSn);
		
		String authorResrcNm ="";
		String authorResrcVal ="";
		
		while(iterator.hasNext()){
			
			SysAuthDTO sysAuthDTO = iterator.next();
			
			//if( "Y".equals(sysAuthDTO.getUseYn()) ){
				
			if( !"".equals(authorResrcNm)){
				authorResrcVal += ",";
			}
			authorResrcNm += sysAuthDTO.getSysAuthor().getAuthorResrcNm();
			authorResrcVal += sysAuthDTO.getSysAuthor().getAuthorResrcVal();
			//}
			
		}
		
		sysAuthor.setAuthorResrcNm(authorResrcNm);
		sysAuthor.setAuthorResrcVal(authorResrcVal);
		
		paramDTO.setSysAuthor( sysAuthor );
		sysAuthGrpService.updateAuthButton(paramDTO);
	}
	
	/**
	 * 권한그룹정보 삭제
	 * @param paramData
	 */
	public void deleteAdmAuthGrp (  List<SysAuthGrpDTO> paramList) throws Exception {
		
		Iterator<SysAuthGrpDTO> iterator = paramList.iterator();
		while(iterator.hasNext()) {
			sysAuthGrpService.deleteAdmAuthGrp(iterator.next());
		}
		
	}
	
	/**
	 * 권한메뉴정보 삭제
	 */
	public void deleteAdmAuthMenu ( List<SysAuthDTO> paramList) throws Exception {
		
		Iterator<SysAuthDTO> iterator = paramList.iterator();
		while(iterator.hasNext()) {
			sysAuthGrpService.deleteAdmAuth(iterator.next());
		}
		
	}
	
	/**
	 * 권한 메뉴정보를 등록한다.
	 * @param authorGrpSn
	 * @param paramList
	 * @throws Exception
	 */
	public void updateAuthMenu ( long selAuthorGrpSn, List<SysMenuDTO> paramList) throws Exception {
		
		
		//메뉴정보가 등록되어 있는지 확인 필요!
		SysAuthDTO sysAuthDTO = new SysAuthDTO();
		
		Iterator<SysMenuDTO> iterator = paramList.iterator();
		
		while(iterator.hasNext()){
			
			sysAuthDTO.setSelAuthorGrpSn(selAuthorGrpSn);
			SysMenuDTO sysMenuDTO = iterator.next();
			sysAuthDTO.setSelMenuSn(sysMenuDTO.getSysMenu().getMenuSn());
			
			if( sysAuthGrpService.selectUseCntAuth(sysAuthDTO) == 0) {
				////메뉴권한정보 등록
				String menuUrl = sysMenuDTO.getSysMenu().getMenuUrl().toUpperCase();
				
				SysAuthor sysAuthor = new SysAuthor();
				sysAuthor.setAuthorGrpSn(selAuthorGrpSn);
				sysAuthor.setMenuSn(sysMenuDTO.getSysMenu().getMenuSn());
				sysAuthor.setAuthorResrcNm("CVUDEM");
				sysAuthor.setAuthorResrcVal("등록,조회,수정,삭제, 엑셀다운로드,마스킹해제");
				
				if( menuUrl.indexOf("EXCEL",menuUrl.length() - 5 ) != -1 || menuUrl.indexOf(".JSON",menuUrl.length() - 5 ) != -1 ){
					//처리 URL 는 버튼권한을 사용안함.
					sysAuthor.setAuthorResrcNm("");
					sysAuthor.setAuthorResrcVal("");
				}
					
				sysAuthDTO.setSysAuthor(sysAuthor);
				
				sysAuthGrpService.insertAuth(sysAuthDTO);
			}
		}
		
		
	}

	/**
	 * 권한그룹 목록 조회
	 * @param sysAuthGrpDTO
	 * @return
	 */
	public List<AuthGrpResult> selectListAuthGrp(SystemPK systemPK, FormSysAuthGrpDTO paramData) throws Exception {
		return sysAuthGrpService.selectListAuthGrp(paramData);
	}

	/**
	 * 권한그룹목록 excel 다운로드
	 */
	public List<Map<String, String>> selectListAuthGrpDownload(SystemPK systemPK, FormSysAuthGrpDTO paramData) throws Exception{


		List<Map<String, String>> authResultList = new ArrayList();

		Iterator<AuthGrpResult> iterator = sysAuthGrpService.selectListAuthGrp(paramData).iterator();

		int authGrpNo = 1;
		while(iterator.hasNext()) {
			AuthGrpResult authGrpResult = iterator.next();

			LinkedHashMap<String,String> dataMap = new LinkedHashMap<String,String>();

			dataMap.put("authGrpNo", String.valueOf(authGrpNo));
			dataMap.put("authorGrpSn",  String.valueOf(authGrpResult.getSysAuthorGrp().getAuthorGrpSn()));
			dataMap.put("authorGrpNm",  authGrpResult.getSysAuthorGrp().getAuthorGrpNm() );
			dataMap.put("authorGrpDscr",  authGrpResult.getSysAuthorGrp().getAuthorGrpDscr() );
			dataMap.put("useYn",  "Y".equals(authGrpResult.getSysAuthorGrp().getUseYn())?"사용":"미사용"  );
			dataMap.put("regtrId",  authGrpResult.getSysAuthorGrp().getRegtrId()  );
			dataMap.put("regDt",   getDateService().parseString( authGrpResult.getSysAuthorGrp().getRegDt() , "yyyy-MM-dd")  );

			authResultList.add(dataMap);
			authGrpNo++;
		}

		return authResultList;
	}

	/**
	 * 권한그룹사용 횟수 조회
	 * @param paramData
	 * @return
	 */
	public long selectUseCntAuthGrp ( SystemPK systemPK, SysAuthGrpDTO paramData) throws Exception {
		return sysAuthGrpService.selectUseCntAuthGrp(paramData);
	}

	/**
	 * 권한그룹 사용 유/무 ( true/false)
	 * @param systemPK
	 * @param paramData
	 * @return
	 * @throws Exception
	 */
	public boolean isAuthGrpUse ( SystemPK systemPK, SysAuthGrpDTO paramData) throws Exception {
		return (this.selectUseCntAuthGrp(systemPK, paramData)) > 0?true:false;
	}

	/**
	 * 권한 메뉴 목록 조회
	 * @param paramData
	 * @return
	 */
	public List<AuthResult> selectListAuthMenu(SystemPK systemPK, FormSysAuthGrpDTO paramData) throws Exception {
		return sysAuthGrpService.selectListAuthMenu(paramData);
	}

	/**
	 * 권한(메뉴/기능) 사용 횟수 조회
	 * @param paramData
	 * @return
	 */
	public long selectUseCntAuth ( SystemPK systemPK, SysAuthDTO paramData) throws Exception {
		return sysAuthGrpService.selectUseCntAuth(paramData);
	}

	/**
	 * 권한 기능 목록 조회
	 * @param paramData
	 * @return
	 * @throws Exception
	 */
	public List<AuthResult> selectListAuth( SystemPK systemPK, FormSysAuthGrpDTO paramData) throws Exception {
		return sysAuthGrpService.selectListAuth(paramData);
	}

	/**
	 * 메뉴 목록
	 * @param systemPK
	 * @param paramData
	 * @return
	 * @throws Exception
	 */
	public List<MenuResult> selectListMenu(SystemPK systemPK, FormSysAuthGrpDTO paramData) throws Exception {
		return sysAuthGrpService.selectListMenu(paramData);
	}

	/**
	 * 메뉴 목록(로그인한 사용자의 권한 그룹별 메뉴 목록)
	 * @param systemPK
	 * @param paramData
	 * @return
	 * @throws Exception
	 */
	public List<MenuResult> selectListRoleMenuAll( SystemPK systemPK, SysAuthMenuDTO paramData) throws Exception {
		return sysAuthGrpService.selectListRoleMenuAll(paramData);
	}


}
