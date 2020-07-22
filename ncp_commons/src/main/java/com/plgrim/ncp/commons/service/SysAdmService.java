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
 * @since       2015. 5. 22       
 */
package com.plgrim.ncp.commons.service;

import com.plgrim.ncp.base.abstracts.AbstractService;
import com.plgrim.ncp.base.entities.datasource1.sys.*;
import com.plgrim.ncp.commons.data.FormSysAdmDTO;
import com.plgrim.ncp.commons.data.SysCmmnNotiDTO;
import com.plgrim.ncp.commons.repository.SysAdmRepository;
import com.plgrim.ncp.commons.result.AdmResult;
import com.plgrim.ncp.commons.result.AuthResult;
import com.plgrim.ncp.commons.result.DashboardSumResult;
import com.plgrim.ncp.commons.result.SysCmmnNotiResult;
import com.plgrim.ncp.commons.util.BOSecurityUtil;
import com.plgrim.ncp.framework.data.SystemPK;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

/**
 * EditorService Implementation
 * @author Tam
 *
 */
@Service
@Slf4j
public class SysAdmService extends AbstractService {

	
	@Autowired
    SysAdmRepository sysAdmRepository;

	
	/**
	 * 운영자관리 운영자목록 조회
	 * @param paramData
	 * @return
	 * @throws Exception
	 */
	public List<AdmResult> selectListAdm( FormSysAdmDTO paramData) throws Exception {
		
		List<AdmResult> admInfoList = sysAdmRepository.selectListAdm(paramData);
	
		Iterator<AdmResult> iterator = admInfoList.iterator();
		while( iterator.hasNext()){
			this.setAdminInfoDetail(paramData, iterator.next(), 0);
		}
			
		return admInfoList;
	}
	
	/**
	 * 운영관리 운영자 총횟수
	 * @param paramData
	 * @return
	 * @throws Exception
	 */
	public long selectCountAdmList( FormSysAdmDTO paramData) throws Exception {
		return sysAdmRepository.selectCountAdmList(paramData);
	}
	
	/**
	 * 권한그룹 조회( 사용)
	 * @return
	 * @throws Exception
	 */
	public List<SysAuthorGrp>  selectListAuthorGrp() throws Exception {
		
		return sysAdmRepository.selectListAuthorGrp();
	}
	
	/**
	 * 아이디 존재 유/무
	 * @param adminId
	 * @return
	 * @throws Exception
	 */
	public boolean checkAdminId ( String adminId ) throws Exception {
		
		return (sysAdmRepository.selectRowAdminIdCnt( adminId ) > 0)?true:false;
	}
	
	/**
	 * 운영자정보를 조회한다. (PO 비밀번호 분실 처리용)
	 * @param paramData
	 * @return
	 * @throws Exception
	 */
	public AdmResult poSelectRowAdminInfo( FormSysAdmDTO paramData ) throws Exception {

		if( paramData.getSelAdminId() == null){
			paramData.setSelAdminId(paramData.getSysAdmin().getAdminId());
		}

		AdmResult admResult = sysAdmRepository.poSelectRowAdminInfo( paramData.getSelAdminId());

		this.setAdminInfoDetail(paramData, admResult, 1);

		return admResult;

	}


	/**
	 * 운영자정보를 조회한다.
	 * @param paramData
	 * @return
	 * @throws Exception
	 */
	public AdmResult selectRowAdminInfo( FormSysAdmDTO paramData ) throws Exception {
		
		if( paramData.getSelAdminId() == null){
			paramData.setSelAdminId(paramData.getSysAdmin().getAdminId()); 
		}
		
		AdmResult admResult = sysAdmRepository.selectRowAdminInfo( paramData.getSelAdminId());
		
		this.setAdminInfoDetail(paramData, admResult, 1);
		
		return admResult;
		
	}
	
	/**
	 * 운영자정보를 조회한다.
	 * @param paramData
	 * @return
	 * @throws Exception
	 */
	public AdmResult selectRowAdminMaskNInfo( FormSysAdmDTO paramData ) throws Exception {
		
		if( paramData.getSelAdminId() == null){
			paramData.setSelAdminId(paramData.getSysAdmin().getAdminId()); 
		}
		
		AdmResult admResult = sysAdmRepository.selectRowAdminMaskNInfo( paramData.getSelAdminId());
		
		this.setAdminInfoDetail(paramData, admResult, 1);
		
		return admResult;
		
	}
	
	/**
	 * 권한그룹 메뉴목록 조회.
	 * @param paramData
	 * @return
	 * @throws Exception
	 */
	public List<AuthResult> selectListAuthMenu (  FormSysAdmDTO paramData ) throws Exception {
		
		return sysAdmRepository.selectListAuthMenu( paramData.getSysAuthor());
		
	}
	
	/**
	 * 권한그룹 FILE 목록 조회
	 * @param paramData
	 * @return
	 * @throws Exception
	 */
	public List<AuthResult> selectListAuthFile (  FormSysAdmDTO paramData) throws Exception {
		
		return sysAdmRepository.selectListAuthFile( paramData.getSysAuthor());
	}
	
	
	/*#####################################Cmd Function ###################################################################################*/
	
	/**
	 * 운영자 등록
	 * @param paramData
	 * @throws Exception
	 */
	public void insertAdminInfo ( FormSysAdmDTO paramData) throws Exception {
		
	
        // APRV_WAIT, APRV_COMPT, 
		if("APRV_COMPT".equals( paramData.getSysAdmin().getAdminStatCd() )){
			paramData.getSysAdmin().setAprvDt(Calendar.getInstance().getTime()); //승인일시
		}
		
		if("APRV_REJECT".equals( paramData.getSysAdmin().getAdminStatCd() )){
			paramData.getSysAdmin().setRejectDt(Calendar.getInstance().getTime()); //승인일시
		}
         
		paramData.getSysAdmin().setLoginFailrCount(0);//로그인 실패횟수
		
		//비밀번호 암호화 SHA256
		paramData.getSysAdmin().setPw( getIdGenService().generateSHA256(paramData.getSysAdmin().getPw()));  
		
		/*운영자 기본 정보 등록*/
		sysAdmRepository.insertAdminInfo(paramData.getSysAdmin());
		
		this.insertAdminDetail(paramData);
	}
	
	/**
	 * 운영자 수정
	 * @param paramData
	 * @throws Exception
	 */
	public void updateAdminInfo ( FormSysAdmDTO paramData) throws Exception {
		
		// APRV_WAIT, APRV_COMPT,  기타 운영자 상태로 변경필요.
		if("APRV_COMPT".equals( paramData.getSysAdmin().getAdminStatCd() )){
			paramData.getSysAdmin().setAprvDt(Calendar.getInstance().getTime()); //승인일시
		}
		
		if("APRV_REJECT".equals( paramData.getSysAdmin().getAdminStatCd() )){
			paramData.getSysAdmin().setRejectDt(Calendar.getInstance().getTime()); //거부일시
		}
		
		if("LAYOFF".equals( paramData.getSysAdmin().getAdminStatCd() )){
			paramData.getSysAdmin().setLayoffDt(Calendar.getInstance().getTime()); //휴직일시
		}
		
		if("DRMC".equals( paramData.getSysAdmin().getAdminStatCd() )){
			paramData.getSysAdmin().setDrmcDt(Calendar.getInstance().getTime()); //휴면일시
		}
		
		if("GNRL_SECSN".equals( paramData.getSysAdmin().getAdminStatCd() )){
			paramData.getSysAdmin().setSecsnDt(Calendar.getInstance().getTime()); //일반탈퇴일시
		}
		
		if("ENFRC_SECSN".equals( paramData.getSysAdmin().getAdminStatCd() )){
			paramData.getSysAdmin().setSecsnDt(Calendar.getInstance().getTime()); //강제탈퇴일시
		}
		
		/*운영자 기본 정보 수정*/
		sysAdmRepository.updateAdminInfo(paramData.getSysAdmin());
		
		
		this.updateAdminDetail(paramData);
		
	}
	
	
	/*#####################################private Function ###################################################################################*/
	
	/**
	 * taget목록에 src정보가 포함되어 있는지 체크
	 * @param srcList
	 * @param targetList
	 * @return
	 * @throws Exception
	 */
	private boolean isChangeId ( List<?> srcList, List<?> targetList) throws Exception{
		
		if( srcList == null || targetList == null) return true;
		
		if( !getCollectionService().containsAll( targetList, srcList) ){
			log.debug("#################################################");
			log.debug( "실패");
			log.debug("#################################################");
			return true;
		}
		
		//return false;
		return true;
	}
	
	/**
	 * 운영자 등록/수정 처리
	 * @param paramData
	 * @param mode  1: 등록, 2:수정
	 * @throws Exception
	 */
	private void insertAdminDetail ( FormSysAdmDTO paramData) throws Exception {
		
		//CS 시스템 접근 권한이 있는 체크
		boolean isCsBoSite = false;
		
		
		/*권한그룹 등록 (필수 1:1)*/
		SysAdminAuthorGrpMapng sysAdminAuthorGrpMapng = new SysAdminAuthorGrpMapng();
		sysAdminAuthorGrpMapng.setAdminId(paramData.getSysAdmin().getAdminId());
		sysAdminAuthorGrpMapng.setAuthorGrpSn(paramData.getSysAuthorGrp().getAuthorGrpSn());
		sysAdmRepository.insertAuthorMapng(sysAdminAuthorGrpMapng);
		
		
		/*운영자 BO사이트 권한(필수 1:N)*/
		isCsBoSite = insertAuthorBoSite(paramData);

		/* 운영자 담당 몰 등록 */
		insertAdminMall(paramData);

		/*담당브랜드 등록*/
		insertAdminBrnd(paramData);

		/*시스템 담당업무 등록 */
		insertAdminChrgJob(paramData);

		//CS 접근 가능이면
		if(isCsBoSite){
			//고객센터 업무유형 등록(필수)
			insertCcJobTp(paramData);
		}
	}

	private void insertAdminMall(FormSysAdmDTO paramData) throws Exception{
		Iterator<SysAdminMall> smIterator = paramData.getSysMallList().iterator();
		while (smIterator.hasNext()) {
			SysAdminMall sysAdminMall = smIterator.next();
			sysAdminMall.setAdminId(paramData.getSysAdmin().getAdminId());
			sysAdminMall.setRegtrId(BOSecurityUtil.getLoginId());

			sysAdmRepository.insertAdminMall(sysAdminMall);
		}
	}

	/**
	 * //고객센터 업무유형 등록
	 *
	 * @param paramData
	 * @throws Exception
	 */
	private void insertCcJobTp(FormSysAdmDTO paramData) throws Exception {
		Iterator<SysCcJobTp> ccJoibIterator = paramData.getSysCcJobTpList().iterator();
		while (ccJoibIterator.hasNext()) {
			SysCcJobTp iParamData = ccJoibIterator.next();
			iParamData.setAdminId(paramData.getSysAdmin().getAdminId());
			sysAdmRepository.insertCcJobTp(iParamData);
		}
	}

	/**
	 * 시스템 담당업무 등록
	 *
	 * @param paramData
	 * @throws Exception
	 */
	private void insertAdminChrgJob(FormSysAdmDTO paramData) throws Exception {
		if (paramData.getSysAdminChrgJobList() != null) {
			Iterator<SysAdminChrgJob> chrgIterator = paramData.getSysAdminChrgJobList().iterator();
			while (chrgIterator.hasNext()) {
				SysAdminChrgJob iParamData = chrgIterator.next();
				iParamData.setAdminId(paramData.getSysAdmin().getAdminId());
				sysAdmRepository.insertAdminChrgJob(iParamData);
			}
		}
	}

	/**
	 * 담당브랜드 등록
	 *
	 * @param paramData
	 * @throws Exception
	 */
	private void insertAdminBrnd(FormSysAdmDTO paramData) throws Exception {
		if (paramData.getSysAdminBrndList() != null) {
			Iterator<SysAdminBrnd> brndIterator = paramData.getSysAdminBrndList().iterator();
			while (brndIterator.hasNext()) {
				SysAdminBrnd brndData = brndIterator.next();
				brndData.setAdminId(paramData.getSysAdmin().getAdminId());
				sysAdmRepository.insertAdminBrnd(brndData);
			}
		}
	}

	private void updateAdminDetail ( FormSysAdmDTO paramData) throws Exception {
		

		//BO site id 변경시 기존 bositeid 삭제
		SysAdminBoSiteAuthor sysAdminBoSiteAuthor = new SysAdminBoSiteAuthor();
		sysAdminBoSiteAuthor.setAdminId(paramData.getSysAdmin().getAdminId());
		//List<SysAdminBoSiteAuthor>  boSiteList = sysAdmRepository.selectAuthorBoSiteList(sysAdminBoSiteAuthor);
		sysAdmRepository.deleteAuthorBoSite(sysAdminBoSiteAuthor);
		
		/*운영자 BO사이트 권한(필수 1:N)*/
		//CS 시스템 접근 권한이 있는 체크
		boolean isCsBoSite = insertAuthorBoSite(paramData);
		
		/* 권한그룹정보 변경시 기존 권한그룹정보 삭제*/
		SysAdminAuthorGrpMapng sysAuthorGrpMapng = sysAdmRepository.selectRowAuthorMapng( paramData.getSysAdmin().getAdminId() );
		if( sysAuthorGrpMapng.getAuthorGrpSn() !=  paramData.getSysAuthorGrp().getAuthorGrpSn()) {
			/*권한그룹 history 삭제*/
			SysAdminAuthorGrpMapngHis sysAdminAuthorGrpMapngHis = new SysAdminAuthorGrpMapngHis();
			sysAdminAuthorGrpMapngHis.setAdminId(paramData.getSysAdmin().getAdminId());
			sysAdmRepository.deleteAuthorMapngHist(sysAdminAuthorGrpMapngHis);

			/*권한그룹 삭제*/
			SysAdminAuthorGrpMapng sysAdminAuthorGrpMapng = new SysAdminAuthorGrpMapng();
			sysAdminAuthorGrpMapng.setAdminId(paramData.getSysAdmin().getAdminId());
			sysAdmRepository.deleteAuthorMapng(sysAdminAuthorGrpMapng);
			
			/*권한그룹 등록 (필수 1:1)*/
			sysAdminAuthorGrpMapng.setAdminId(paramData.getSysAdmin().getAdminId());
			sysAdminAuthorGrpMapng.setAuthorGrpSn(paramData.getSysAuthorGrp().getAuthorGrpSn());
			sysAdmRepository.insertAuthorMapng(sysAdminAuthorGrpMapng);
		}

		/* 담당몰 변경시 기존 담당 몰 삭제. */
		SysAdminMall sdmParam = new SysAdminMall();
		sdmParam.setAdminId(paramData.getSysAdmin().getAdminId());
		sysAdmRepository.deleteAdminMall(sdmParam);

		/* 운영자 담당 몰 등록 */
		insertAdminMall(paramData);

		/*담당브랜드 변경시 기존 담당브랜드 삭제.*/
		//List<SysBrndExtend> targetBrndList = sysAdmRepository.selectAdminBrndList(paramData.getSysAdmin().getAdminId());
		//등록된 브랜드정보를 삭제한다.	
		SysAdminBrnd brParam = new SysAdminBrnd();
		brParam.setAdminId(paramData.getSysAdmin().getAdminId());
		sysAdmRepository.deleteAdminBrnd(brParam);

		/*담당브랜드 등록*/
		insertAdminBrnd(paramData);

		/*시스템 담당업무 변경시 기존 담당업무 삭제 */
		//List<SysAdminChrgJob>  chrgJobList = sysAdmRepository.selectAdminChrgJobList(paramData.getSysAdmin().getAdminId());
		//등록된 담당업무를 삭제한다.	
		SysAdminChrgJob dParamData = new SysAdminChrgJob();
		dParamData.setAdminId(paramData.getSysAdmin().getAdminId());
		sysAdmRepository.deleteAdminChrgJob(dParamData);
		
		/*시스템 담당업무 등록 */
		insertAdminChrgJob(paramData);


		//CS 시스템 접근 권한이 있는 체크
		//CS 접근 가능이면
		if(isCsBoSite){
			//고객센터 업무유형 변경시 기존 업무유형 삭제
			//List<SysCcJobTp>  ccJobList = sysAdmRepository.selectCcJobTpList(paramData.getSysAdmin().getAdminId());
				
			//등록된 업무유형 삭제한다.	
			SysCcJobTp dParamData1 = new SysCcJobTp();
			dParamData1.setAdminId(paramData.getSysAdmin().getAdminId());
			sysAdmRepository.deleteCcJobTp(dParamData1);
			
			//고객센터 업무유형 등록(필수)
			insertCcJobTp(paramData);
		}

	}

	private boolean insertAuthorBoSite(FormSysAdmDTO paramData) throws Exception {
		boolean isCsBoSite = false;
		Iterator<SysBoSite> boSiteIterator = paramData.getSysBoSiteList().iterator();
		while (boSiteIterator.hasNext()) {
			SysBoSite boSiteData = boSiteIterator.next();
			if ("CS".equals(boSiteData.getBoSiteId())) {
				isCsBoSite = true;
			}

			SysAdminBoSiteAuthor adminBoSiteData = new SysAdminBoSiteAuthor();
			adminBoSiteData.setAdminId(paramData.getSysAdmin().getAdminId());
			adminBoSiteData.setBoSiteId(boSiteData.getBoSiteId());
			sysAdmRepository.insertAuthorBoSite(adminBoSiteData);
		}
		return isCsBoSite;
	}

	/**
	 * 운영정보 상세정보를 조회한다.
	 * @param paramData
	 * @param resultData
	 * @param viewType 
	 * @throws Exception
	 */
	private void setAdminInfoDetail( FormSysAdmDTO paramData, AdmResult resultData, int viewType) throws Exception {
		
		String adminId = resultData.getSysAdmin().getAdminId();
		
		paramData.setSelAdminId( adminId );
		
		boolean isCsBoSite = false; //CS 시스템 접근 권한이 있는 체크
		
		//BO site id 정보를 읽어 문자열로 설정한다.
		List<SysBoSite>  boSiteList =  sysAdmRepository.selectListBoSiteId(paramData);
		resultData.setSysBoSiteList(boSiteList);
		
		String boSiteIds = ""; // Bo 사이트접근 아이디  여러개일경우 콤마(,)로 구분한다.
		Iterator<SysBoSite> iterator1 = boSiteList.iterator();
		while( iterator1.hasNext()){
			SysBoSite boSiteData = iterator1.next();
			
			if( "CS".equals( boSiteData.getBoSiteId())){
				isCsBoSite = true;
			}
			
			if( "".equals(boSiteIds)){
				boSiteIds = boSiteData.getBoSiteId();
			}else{
				boSiteIds += ","+boSiteData.getBoSiteId();
			}
		}
		
		resultData.setBoSiteIds(boSiteIds);
		
		if( viewType == 1){
			
			//담당브랜드  paramData.getSysAdminBrndList()
			resultData.setBrndList( sysAdmRepository.selectAdminBrndList( adminId ) ); 
			
			//시스템 담당업무
			resultData.setChrgJobList( sysAdmRepository.selectAdminChrgJobList( adminId ) );
			
			//CS 접근 가능이면
			if(isCsBoSite){
				//고객센터 업무유형 변경시 기존 업무유형 삭제
				resultData.setCcJobList( sysAdmRepository.selectCcJobTpList( adminId ) );
			}
		}
		    
		/* 운영자:권한그룹 1:1이므로 주석처리
		//권한그룹 정보를 읽어 문자열로 설정한다.

		 List<SysAuthorGrp> authorGrpList = sysAdmRepository.selectListAuthorGrpAdm(paramData);
		resultData.setSysAuthorGrpList(authorGrpList);
		
		String authorGrpNms = ""; //권한그룹명  권한그룹이 여러개일경우 콤마(,)로 구분한다.
		Iterator<SysAuthorGrp> iterator2 = authorGrpList.iterator();
		while( iterator2.hasNext()){
			SysAuthorGrp authorGrpData = iterator2.next();
			
			if( "".equals(authorGrpNms)){
				authorGrpNms = authorGrpData.getAuthorGrpNm();
			}else{
				authorGrpNms += ","+ authorGrpData.getAuthorGrpNm();
			}
		}
		resultData.setAuthorGrpNms(authorGrpNms);
		*/
		
		//휴대폰- 여러필드 분리된 휴대폰번호를 한개 필드로 결합한다.
		
		/*String mobil = "";
		
			if( resultData.getSysAdmin().getMobilAreaNo() != null){
				mobil += resultData.getSysAdmin().getMobilAreaNo();
				if( resultData.getSysAdmin().getMobilTlofNo() != null){
					mobil += "-"+ resultData.getSysAdmin().getMobilTlofNo();
					if( resultData.getSysAdmin().getMobilTlofWthnNo() != null){
						mobil += "-"+ resultData.getSysAdmin().getMobilTlofWthnNo();
					}
				}
			}
		resultData.setMobil(mobil);
		
		String tel = "";
		
			
			if( resultData.getSysAdmin().getTelAreaNo() != null){
				tel += resultData.getSysAdmin().getTelAreaNo();
				if( resultData.getSysAdmin().getTelTlofNo() != null){
					tel += "-"+ resultData.getSysAdmin().getTelTlofNo();
					if( resultData.getSysAdmin().getTelTlofWthnNo() != null){
						tel += "-"+ resultData.getSysAdmin().getTelTlofWthnNo();
					}
				}
			}
		
		resultData.setTel(tel);*/
	}
	
	/**
	 * 비밀번호 초기화
	 * @param adminId
	 * @return
	 * @throws Exception
	 */
	public String updateAdminTempPassword( SysAdmin paramData) throws Exception{
		
		String tempPw = getIdGenService().generateTempPassword();
		
		paramData.setPw( getIdGenService().generateSHA256(  tempPw ));  //SHA256 암호화
		paramData.setPwIntzYn("Y");
		paramData.setPwIntzDt( Calendar.getInstance().getTime() );
		
		sysAdmRepository.updateAdminPassword(paramData);
		
		
		return tempPw;
	}
	
	/**
	 * PO 비밀번호 분실 - 회원 리스트 조회 (존재여부)
	 * @param SysAdmin
	 * @return
	 * @throws Exception
	 */
	public List<SysAdmin> poSelectLostPasswordList( SysAdmin paramData) throws Exception{
		return sysAdmRepository.poSelectLostPasswordList(paramData);
	}
	public List<SysAdmin> poSelectLostInfo( SysAdmin paramData) throws Exception{
		return sysAdmRepository.poSelectLostInfo(paramData);
	}

	/**
	 * PO 비밀번호 분실, 임시비밀번호 발급 (로그인 정보가 없는 사용자 요청임)
	 * @param adminId
	 * @return
	 * @throws Exception
	 */
	public String poUpdateAdminTempPassword( SysAdmin paramData) throws Exception{

		String tempPw = getIdGenService().generateTempPassword();

		paramData.setPw( getIdGenService().generateSHA256(  tempPw ));  //SHA256 암호화
		paramData.setPwIntzYn("Y");
		paramData.setPwIntzDt( Calendar.getInstance().getTime() );

		sysAdmRepository.poUpdateAdminPassword(paramData);

		return tempPw;
	}

	/**
	 * MyInfo 정보 수정처리
	 * @param paramData
	 * @throws Exception
	 */
	public void updateMyInfo( SysAdmin paramData) throws Exception{
		
		if( paramData.getPw() != null && !"".equals(paramData.getPw()  )) {
			paramData.setPw( getIdGenService().generateSHA256(  paramData.getPw() ));  //SHA256 암호화
		}else{
			paramData.setPw(null);
		}
		
		sysAdmRepository.updateMyInfo(paramData);
		
	}
	
	/**
	 * 비밀번호 변경
	 * @param paramData
	 * @return
	 * @throws Exception
	 */
	public String updateAdminChangePassword( SysAdmin paramData) throws Exception{
		
		String tempPw = paramData.getPw();
		
		paramData.setPw( getIdGenService().generateSHA256(  tempPw ));  //SHA256 암호화
		paramData.setPwIntzYn("N");
		paramData.setPwIntzDt( Calendar.getInstance().getTime() );
		
		sysAdmRepository.updateAdminPassword(paramData);
		
		
		return tempPw;
	}
	
	/**
	 * 운영자정보 비밀번호 초기화 날짜 변경
	 * @param paramData
	 * @throws Exception
	 */
	public void updateAdminPwIntzDt( SysAdmin paramData) throws Exception{
		sysAdmRepository.updateAdminPwIntzDt(paramData);
	}
	

	/**
	 * 브랜드 선택 목록 조회
	 * @param paramData
	 * * @return
	 * @throws Exception
	 */
	public List<SysBrndExtend> getComBrndList( SysAdmin paramData ) throws Exception {
		return sysAdmRepository.getComBrndList(paramData);
	}
	public List<SysBrndExtend> getUserComBrndList( FormSysAdmDTO paramData ) throws Exception {
		return sysAdmRepository.getUserComBrndList(paramData);
	}

	/**
	 * PO Dashboard 현황 정보
	 * @param paramData
	 * @return
	 * @throws Exception
	 */
	public DashboardSumResult selectDashboardSumInfo( FormSysAdmDTO paramData ) throws Exception {
		return sysAdmRepository.selectDashboardSumInfo(paramData);
	}

	/**
	 * PO Main Notice list
	 * @param paramData
	 * @return
	 * @throws Exception
	 */
	public List<SysCmmnNotiResult> selectPoMainNoticeList( SystemPK systemPK, SysCmmnNotiDTO paramData ) throws Exception {
		return sysAdmRepository.selectPoMainNoticeList(systemPK, paramData);
	}

	/**
	 * #33927 BO 배송매장 전용 DashBoard 생성
	 * - 배송매장 Notice list
	 *
	 * @return
	 * @throws Exception
	 */
	public List<SysCmmnNotiResult> selectDlvShopMainNoticeList() throws Exception {
		return sysAdmRepository.selectDlvShopMainNoticeList();
	}

	/**
	 * 운영자 담당 몰 조회
	 *
	 * @param paramData
	 * @throws Exception
	 */
	public List<SysAdminMallExtend> selectAdminMall(FormSysAdmDTO paramData) throws Exception{
		return sysAdmRepository.selectAdminMall(paramData);
	}
}
