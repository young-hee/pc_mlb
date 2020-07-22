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
import org.springframework.stereotype.Service;

import com.plgrim.ncp.base.abstracts.AbstractService;
import com.plgrim.ncp.base.entities.datasource1.sys.SysAuthor;
import com.plgrim.ncp.base.entities.datasource1.sys.SysMenu;
import com.plgrim.ncp.commons.data.FormSysAuthGrpDTO;
import com.plgrim.ncp.commons.data.SysAuthDTO;
import com.plgrim.ncp.commons.data.SysAuthGrpDTO;
import com.plgrim.ncp.commons.data.SysAuthMenuDTO;
import com.plgrim.ncp.commons.repository.SysAuthRepository;
import com.plgrim.ncp.commons.result.AuthGrpResult;
import com.plgrim.ncp.commons.result.AuthResult;
import com.plgrim.ncp.commons.result.MenuResult;

/**
 * EditorService Implementation
 * @author Tam
 *
 */
@Service
public class SysAuthGrpService extends AbstractService {

	
	@Autowired
    SysAuthRepository sysAuthRepository; //시스템 권한 DAO

	
	/**
	 * 권한그룹 목록 조회
	 * @param sysAuthGrpDTO
	 * @return
	 */
	public List<AuthGrpResult> selectListAuthGrp( FormSysAuthGrpDTO paramData) throws Exception {
		return sysAuthRepository.selectListAuthGrp(paramData); 
	}
	
	/**
	 * 권한(기능) 목록 조회
	 * @param paramData
	 * @return
	 * @throws Exception
	 */
	public List<AuthResult> selectListAuth( FormSysAuthGrpDTO paramData) throws Exception {
		
		List<AuthResult> authResultList = new ArrayList<AuthResult>();
		
		AuthResult authResult1 =new AuthResult();
		authResult1.setSysAuthor( authGeneratorAct( paramData.getSelAuthorGrpSn() , paramData.getSelMenuSn() ,"C", "등록" ) ) ;
		authResult1.setUseYn("N");
		
		AuthResult authResult2 =new AuthResult();
		authResult2.setSysAuthor( authGeneratorAct( paramData.getSelAuthorGrpSn() , paramData.getSelMenuSn() ,"V", "조회" ) ) ;
		authResult2.setUseYn("N");
		
		AuthResult authResult3 =new AuthResult();
		authResult3.setSysAuthor( authGeneratorAct( paramData.getSelAuthorGrpSn() , paramData.getSelMenuSn() ,"U", "수정" ) ) ;
		authResult3.setUseYn("N");
		
		AuthResult authResult4 =new AuthResult();
		authResult4.setSysAuthor( authGeneratorAct( paramData.getSelAuthorGrpSn() , paramData.getSelMenuSn() ,"D", "삭제" ) ) ;
		authResult4.setUseYn("N");
		
		AuthResult authResult5 =new AuthResult();
		authResult5.setSysAuthor( authGeneratorAct( paramData.getSelAuthorGrpSn() , paramData.getSelMenuSn() ,"E", "엑셀다운로드" ) ) ;
		authResult5.setUseYn("N");
		
		AuthResult authResult6 =new AuthResult();
		authResult6.setSysAuthor( authGeneratorAct( paramData.getSelAuthorGrpSn() , paramData.getSelMenuSn() ,"M", "마스킹해제" ) ) ;
		authResult6.setUseYn("N");
		
		Iterator<AuthResult> iterator = sysAuthRepository.selectListAuth(paramData).iterator();
		//while(iterator.hasNext()) {
		if(iterator.hasNext()) {
			AuthResult authResult = iterator.next();
			
			authResult1.setUseYn( (" "+getStringService().nvl(authResult.getSysAuthor().getAuthorResrcNm(),"")).indexOf("C") > 0?"Y":"N" );
			authResult2.setUseYn((" "+getStringService().nvl(authResult.getSysAuthor().getAuthorResrcNm(),"")).indexOf("V") > 0?"Y":"N" );
			authResult3.setUseYn((" "+getStringService().nvl(authResult.getSysAuthor().getAuthorResrcNm(),"")).indexOf("U") > 0?"Y":"N" );
			authResult4.setUseYn((" "+getStringService().nvl(authResult.getSysAuthor().getAuthorResrcNm(),"")).indexOf("D") > 0?"Y":"N" );
			authResult5.setUseYn((" "+getStringService().nvl(authResult.getSysAuthor().getAuthorResrcNm(),"")).indexOf("E") > 0?"Y":"N" );
			authResult6.setUseYn((" "+getStringService().nvl(authResult.getSysAuthor().getAuthorResrcNm(),"")).indexOf("M") > 0?"Y":"N" );
			
		}
		
		authResultList.add( authResult1 );
		authResultList.add( authResult2 );
		authResultList.add( authResult3 );
		authResultList.add( authResult4 );
		authResultList.add( authResult5 );
		authResultList.add( authResult6 );
		
		return authResultList;
	}
	
	/**
	 * 권한그룹사용 횟수 조회
	 * @param paramData
	 * @return
	 */
	public long selectUseCntAuthGrp ( SysAuthGrpDTO paramData) throws Exception {
		return sysAuthRepository.selectUseCntAuthGrp(paramData);
	}
	
	/**
	 * 권한 메뉴 목록 조회
	 * @param paramData
	 * @return
	 */
	public List<AuthResult> selectListAuthMenu(  FormSysAuthGrpDTO paramData ) throws Exception {
		return sysAuthRepository.selectListAuthMenu(paramData);
	}
	
	/**
	 * 권한(메뉴/기능) 사용 횟수 조회
	 * @param paramData
	 * @return
	 */
	public long selectUseCntAuth ( SysAuthDTO paramData) throws Exception {
		return sysAuthRepository.selectUseCntAuth(paramData);
	}
	
	
	/**
	 * 메뉴목록를 조회한다.
	 * @param paramData
	 * @return
	 * @throws Exception
	 */
	public List<MenuResult> selectListMenu(  FormSysAuthGrpDTO paramData ) throws Exception {
		return sysAuthRepository.selectListMenu(paramData);
	}
	
	
	/**
	 * 메뉴 목록(로그인한 사용자의 권한 그룹별 메뉴 목록)
	 * @param paramData
	 * @return
	 * @throws Exception
	 */
	public List<MenuResult> selectListRoleMenuAll(  SysAuthMenuDTO paramData ) throws Exception {
		return sysAuthRepository.selectListRoleMenuAll(paramData);
	}	
	
	
	/**
	 * 관리자 메뉴 url 및 권한정보를 조회한다.
	 * @param boSiteId
	 * @param adminId
	 * @param adminPwd
	 * @throws Exception
	 */
	public void getAdmAuthUrlAct ( String boSiteId, String adminId, String adminPwd ) throws Exception {
		
		
	}
	
	/**
	 * 관리자 정보를 조회한다.
	 * @param boSiteId
	 * @param adminId
	 * @param adminPwd
	 * @throws Exception
	 */
	public void getSysAdmInfo ( String boSiteId, String adminId, String adminPwd ) throws Exception {
		
	}
	
	/*######################### command #######################################################################*/
	
	/**
	 * 권한그룹 저장 처리
	 * @param paramData
	 */
	public void mergeAuthGrp ( SysAuthGrpDTO paramData) throws Exception {
		sysAuthRepository.mergeAuthGrp(paramData);
	}
	
	/**
	 * 권한그룹 복사
	 * @param paramData
	 */
	public void copyAuthGrp ( SysAuthGrpDTO paramData) throws Exception {
		sysAuthRepository.copyAuthGrp(paramData);
		
		//권한(기능/메뉴) 복사 
		copyAuth(paramData);
	}
	
	/**
	 * 권한(기능/메뉴) 복사
	 * @param paramData
	 */
	public void copyAuth ( SysAuthGrpDTO paramData) throws Exception {
		sysAuthRepository.copyAuth(paramData);
	}
	
	/**
	 * 권한(기능/메뉴)정보 삭제
	 * @param paramData
	 */
	public void deleteAdmAuth ( SysAuthDTO paramData) throws Exception {
		sysAuthRepository.deleteAdmAuth(paramData);
	}
	
	/**
	 * 권한그룹정보 삭제
	 * @param paramData
	 */
	public void deleteAdmAuthGrp ( SysAuthGrpDTO paramData) throws Exception {
		
		if( this.selectUseCntAuthGrp(paramData) == 0){
			
			//권한 삭제
			SysAuthDTO sysAuthDTO = new SysAuthDTO();
			SysAuthor sysAuthor = new SysAuthor();
			sysAuthor.setAuthorGrpSn(paramData.getSysAuthorGrp().getAuthorGrpSn());
			sysAuthDTO.setSysAuthor(sysAuthor);
			sysAuthDTO.setSysMenu( new SysMenu());
			this.deleteAdmAuth(sysAuthDTO);
			
			//권한그룹 삭제
			sysAuthRepository.deleteAdmAuthGrp( paramData);
			
		}
		
	}
	
	/**
	 * 권한 메뉴 등록 처리
	 * @param paramData
	 * @throws Exception
	 */
	public void insertAuth ( SysAuthDTO paramData) throws Exception {
		sysAuthRepository.insertAuth( paramData);
	}
	
	/**
	 * 권한 저장 처리
	 * @param paramData
	 * @throws Exception
	 */
	public void updateAuth  ( SysAuthDTO paramData) throws Exception {
		sysAuthRepository.updateAuth( paramData);
		
		updateAuthButton(paramData);
	}
	
	/**
	 * 첫번째 보여지는 프로그램URL 페이지에 버튼권한정보를 수정한다.
	 * @param paramData
	 * @throws Exception
	 */
	public void updateAuthButton  ( SysAuthDTO paramData) throws Exception {
		sysAuthRepository.updateAuth( paramData);
		/*
		if( (" "+getStringService().nvl(paramData.getSysAuthor().getAuthorResrcNm(),"")).indexOf("M") > 0 ){
			//마스킹해제 권한이 존재하면 첫번째 프로그램 URL를 제외한 나머지 프로그램 URL정보에 M(마스킹해제) 권한을 부여한다.
			
			paramData.getSysAuthor().setAuthorResrcNm("M");
			paramData.getSysAuthor().setAuthorResrcVal("마스킹해제");
		}else{
			//마스킹해제 권한이 없을 경우 첫번째 프로그램 URL를 제외한 나머지 프로그램 URL정보에 M(마스킹해제) 권한을 삭제한다.
			
			paramData.getSysAuthor().setAuthorResrcNm("");
			paramData.getSysAuthor().setAuthorResrcVal("");
		}
		
		sysAuthRepository.updateMaskingAuth( paramData);
		*/
	}
	
	
	/*########################################################################################################################*/
	
	/**
	 * 메뉴 URL 권한(기능)를 생성한다.
	 * @param selAuthorGrpSn
	 * @param selMenuSn
	 * @param resrcNm
	 * @param resrcVal
	 * @return
	 */
	private SysAuthor authGeneratorAct( long selAuthorGrpSn, long selMenuSn, String resrcNm, String resrcVal){
		
		SysAuthor sysAuthor = new SysAuthor();
		sysAuthor.setAuthorGrpSn( selAuthorGrpSn );
		sysAuthor.setMenuSn(selMenuSn);
		sysAuthor.setAuthorResrcNm( resrcNm );
		sysAuthor.setAuthorResrcVal( resrcVal);
		return sysAuthor;
	}
	
	
	
	

}
