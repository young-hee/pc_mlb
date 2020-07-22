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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.datasource1.sys.*;
import com.plgrim.ncp.commons.data.FormSysAdmDTO;
import com.plgrim.ncp.commons.data.SysCmmnNotiDTO;
import com.plgrim.ncp.commons.result.AdmResult;
import com.plgrim.ncp.commons.result.AuthResult;
import com.plgrim.ncp.commons.result.DashboardSumResult;
import com.plgrim.ncp.commons.result.SysCmmnNotiResult;
import com.plgrim.ncp.commons.util.BOSecurityUtil;
import com.plgrim.ncp.framework.commons.StringService;
import com.plgrim.ncp.framework.data.SystemPK;

import lombok.extern.slf4j.Slf4j;

/**
 * 운영관리 Repository
 * @author tam
 *
 */
@Slf4j
@Repository
public class SysAdmRepository extends AbstractRepository {

	
	/*######################### select #######################################################################*/
	
	/**
	 * 운영관리 운영자 목록 조회
	 * @param paramData
	 * @return
	 * @throws Exception
	 */
	public List<AdmResult> selectListAdm( FormSysAdmDTO paramData) throws Exception {
		log.info(paramData.toString());
		
		paramData.setDbMarkingYn( BOSecurityUtil.checkMarkingFromAccessUrl());
		return getSession1().selectList("com.plgrim.ncp.commons.adm.selectListAdm", paramData);
	}

	/**
	 * 운영관리 운영자 BO site id 목록 조회
	 * @param paramData
	 * @return
	 * @throws Exception
	 */
	public List<SysBoSite> selectListBoSiteId( FormSysAdmDTO paramData) throws Exception {
		log.info(paramData.toString());
		return getSession1().selectList("com.plgrim.ncp.commons.adm.selectListBoSiteId", paramData);
	}
	
	/**
	 * 운영관리 운영자 권한그룹 목록 조회
	 * @param paramData
	 * @return
	 * @throws Exception
	 */
	public List<SysAuthorGrp> selectListAuthorGrpAdm( FormSysAdmDTO paramData) throws Exception {
		log.info(paramData.toString());
		return getSession1().selectList("com.plgrim.ncp.commons.adm.selectListAuthorGrpAdm", paramData);
	}
	
	/**
	 * 전체 권한그룹 목록 조회
	 * @return
	 * @throws Exception
	 */
	public List<SysAuthorGrp> selectListAuthorGrp() throws Exception {
		return getSession1().selectList("com.plgrim.ncp.commons.adm.selectListAuthorGrp");
	}
	
	
	/**
	 * 운영관리 운영자 총 횟수
	 * @param paramData
	 * @return
	 * @throws Exception
	 */
	public long selectCountAdmList( FormSysAdmDTO paramData) throws Exception {
		log.info(paramData.toString());
		return getSession1().selectOne("com.plgrim.ncp.commons.adm.selectCountAdmList", paramData);
	}
	
	/**
	 * 운영자ID 사용 카운터
	 * @param adminId
	 * @return
	 * @throws Exception
	 */
	public long selectRowAdminIdCnt( String adminId) throws Exception {
		Map<String,String> paramData = new HashMap<String,String>();
		paramData.put("adminId", adminId);
		
		log.info(paramData.toString());
		return getSession1().selectOne("com.plgrim.ncp.commons.adm.selectRowAdminIdCnt", paramData);
	}
	
	/**
	 * 운영자정보 조회 (PO 비밀번호 분실 처리용)
	 * @param adminId
	 * @return
	 * @throws Exception
	 */
	public AdmResult poSelectRowAdminInfo( String adminId) throws Exception {
		Map<String,String> paramData = new HashMap<String,String>();
		paramData.put("adminId", adminId);
		paramData.put("dbMarkingYn","N");

		log.info(paramData.toString());
		return getSession1().selectOne("com.plgrim.ncp.commons.adm.selectRowAdminInfo", paramData);
	}

	/**
	 * 운영자정보 조회
	 * @param adminId
	 * @return
	 * @throws Exception
	 */
	public AdmResult selectRowAdminInfo( String adminId) throws Exception {
		Map<String,String> paramData = new HashMap<String,String>();
		paramData.put("adminId", adminId);
		paramData.put("dbMarkingYn",BOSecurityUtil.checkMarkingFromAccessUrl());
		
		log.info(paramData.toString());
		return getSession1().selectOne("com.plgrim.ncp.commons.adm.selectRowAdminInfo", paramData);
	}
	
	/**
	 * 운영자정보 Masking X 조회
	 * @param adminId
	 * @return
	 * @throws Exception
	 */
	public AdmResult selectRowAdminMaskNInfo( String adminId) throws Exception {
		Map<String,String> paramData = new HashMap<String,String>();
		paramData.put("adminId", adminId);
		paramData.put("dbMarkingYn","N");
		
		log.info(paramData.toString());
		return getSession1().selectOne("com.plgrim.ncp.commons.adm.selectRowAdminInfo", paramData);
	}
	
	/**
	 * site접근권한 목록 조회
	 * @param paramData
	 * @return
	 * @throws Exception
	 */
	public List<SysAdminBoSiteAuthor> selectAuthorBoSiteList( SysAdminBoSiteAuthor paramData) throws Exception {
		log.info(paramData.toString());
		return getSession1().selectList("com.plgrim.ncp.commons.adm.selectAuthorBoSiteList", paramData);
	}
	
	/**
	 * 운영자 권한그룹 조회
	 * @param adminId
	 * @return
	 * @throws Exception
	 */
	public SysAdminAuthorGrpMapng selectRowAuthorMapng( String adminId) throws Exception {
		Map<String,String> paramData = new HashMap<String,String>();
		paramData.put("adminId", adminId);
		log.info(paramData.toString());
		return getSession1().selectOne("com.plgrim.ncp.commons.adm.selectRowAuthorMapng", paramData);
	}
	
	/**
	 * 운영자 브랜드 목록 조회
	 * @param adminId
	 * @return
	 * @throws Exception
	 */
	public List<SysBrndExtend> selectAdminBrndList( String adminId) throws Exception {
		Map<String,String> paramData = new HashMap<String,String>();
		paramData.put("adminId", adminId);
		log.info(paramData.toString());
		return getSession1().selectList("com.plgrim.ncp.commons.adm.selectAdminBrndList", paramData);
	}
	
	/**
	 * 운영자 담당업무 목록 조회
	 * @param adminId
	 * @return
	 * @throws Exception
	 */
	public List<SysAdminChrgJob> selectAdminChrgJobList( String adminId) throws Exception {
		Map<String,String> paramData = new HashMap<String,String>();
		paramData.put("adminId", adminId);
		log.info(paramData.toString());
		return getSession1().selectList("com.plgrim.ncp.commons.adm.selectAdminChrgJobList", paramData);
	}
	
	/**
	 * 고객센터 업무유형 목록 조회
	 * @param adminId
	 * @return
	 * @throws Exception
	 */
	public List<SysCcJobTp> selectCcJobTpList( String adminId) throws Exception {
		Map<String,String> paramData = new HashMap<String,String>();
		paramData.put("adminId", adminId);
		log.info(paramData.toString());
		return getSession1().selectList("com.plgrim.ncp.commons.adm.selectCcJobTpList", paramData);
	}
	
	
	/**
	 * 권한그룹 메뉴 목록를 조회.
	 * @param paramData
	 * @return
	 * @throws Exception
	 */
	public List<AuthResult> selectListAuthMenu (  SysAuthor paramData ) throws Exception {
		
		log.info(paramData.toString());
		return getSession1().selectList("com.plgrim.ncp.commons.adm.selectListAuthMenu", paramData); 
	}
	
	/**
	 * 권한그룹 FILE 목록 조회
	 * @param paramData
	 * @return
	 * @throws Exception
	 */
	public List<AuthResult> selectListAuthFile (  SysAuthor paramData ) throws Exception {
		
		log.info(paramData.toString());
		return getSession1().selectList("com.plgrim.ncp.commons.adm.selectListAuthFile", paramData);
	}

	/*######################### command #######################################################################*/
	
	/**
	 * 운영자 등록
	 * @param paramData
	 * @throws Exception
	 */
	public void insertAdminInfo ( SysAdmin paramData) throws Exception {
		paramData.setRegtrId(BOSecurityUtil.getLoginId());
		log.info(paramData.toString());
		getSession1().insert( "com.plgrim.ncp.commons.adm.insertAdminInfo", paramData);
	}
	
	/**
	 * 운영자정보 수정
	 * @param paramData
	 * @throws Exception
	 */
	public void updateAdminInfo ( SysAdmin paramData) throws Exception {
		paramData.setRegtrId(BOSecurityUtil.getLoginId());
		log.info(paramData.toString());
		getSession1().insert( "com.plgrim.ncp.commons.adm.updateAdminInfo", paramData);
	}
	
	/**
	 * site 접근권한 등록
	 * @param paramData
	 * @throws Exception
	 */
	public void insertAuthorBoSite ( SysAdminBoSiteAuthor paramData) throws Exception {
		paramData.setRegtrId(BOSecurityUtil.getLoginId());
		log.info(paramData.toString());
		getSession1().insert( "com.plgrim.ncp.commons.adm.insertAuthorBoSite", paramData);
	}
	
	/**
	 * site 접근권한 삭제
	 * @param paramData
	 * @throws Exception
	 */
	public void deleteAuthorBoSite ( SysAdminBoSiteAuthor paramData) throws Exception {
		paramData.setRegtrId(BOSecurityUtil.getLoginId());
		log.info(paramData.toString());
		getSession1().insert( "com.plgrim.ncp.commons.adm.deleteAuthorBoSite", paramData);
	}
	
	/**
	 * 운영자 권한그룹 등록
	 * @param paramData
	 * @throws Exception
	 */
	public void insertAuthorMapng ( SysAdminAuthorGrpMapng paramData) throws Exception {
		paramData.setRegtrId(BOSecurityUtil.getLoginId());
		log.info(paramData.toString());
		getSession1().insert("com.plgrim.ncp.commons.adm.insertAuthorMapng", paramData);
		getSession1().insert("com.plgrim.ncp.commons.adm.insertAuthorMapngHist", paramData); 
	}
	
	/**
	 * 운영자 권한그룹 등록이력 등록
	 * @param paramData
	 * @throws Exception
	 */
	public void insertAuthorMapngHist ( SysAdminAuthorGrpMapng paramData) throws Exception {
		paramData.setRegtrId(BOSecurityUtil.getLoginId());
		getSession1().insert("com.plgrim.ncp.commons.adm.insertAuthorMapngHist", paramData); 
	}
	
	/**
	 * 운영자 권한그룹 삭제
	 * @param paramData
	 * @throws Exception
	 */
	public void deleteAuthorMapng ( SysAdminAuthorGrpMapng paramData) throws Exception {
		paramData.setRegtrId(BOSecurityUtil.getLoginId());
		log.info(paramData.toString());
		getSession1().delete("com.plgrim.ncp.commons.adm.deleteAuthorMapng", paramData);
	}
	
	/**
	 * 운영자 권한그룹 history 삭제
	 * @param paramData
	 * @throws Exception
	 */
	public void deleteAuthorMapngHist ( SysAdminAuthorGrpMapngHis paramData) throws Exception {
		paramData.setRegtrId(BOSecurityUtil.getLoginId());
		log.info(paramData.toString());
		getSession1().delete("com.plgrim.ncp.commons.adm.deleteAuthorMapngHist", paramData);
	}
	
	/**
	 * 운영자 브랜드 등록
	 * @param paramData
	 * @throws Exception
	 */
	public void insertAdminBrnd ( SysAdminBrnd paramData) throws Exception {
		paramData.setRegtrId(BOSecurityUtil.getLoginId());
		log.info(paramData.toString());
		getSession1().insert("com.plgrim.ncp.commons.adm.insertAdminBrnd", paramData);
	}
	
	/**
	 * 운영자 브랜드 삭제
	 * @param paramData
	 * @throws Exception
	 */
	public void deleteAdminBrnd ( SysAdminBrnd paramData) throws Exception {
		paramData.setRegtrId(BOSecurityUtil.getLoginId());
		log.info(paramData.toString());
		getSession1().insert("com.plgrim.ncp.commons.adm.deleteAdminBrnd", paramData);
	}
	
	/**
	 * 운영자 담당업무 등록
	 * @param paramData
	 * @throws Exception
	 */
	public void insertAdminChrgJob ( SysAdminChrgJob paramData) throws Exception {
		paramData.setRegtrId(BOSecurityUtil.getLoginId());
		log.info(paramData.toString());
		getSession1().insert("com.plgrim.ncp.commons.adm.insertAdminChrgJob", paramData);
	}
	
	/**
	 * 운영자 담당업무 삭제
	 * @param paramData
	 * @throws Exception
	 */
	public void deleteAdminChrgJob ( SysAdminChrgJob paramData) throws Exception {
		paramData.setRegtrId(BOSecurityUtil.getLoginId());
		log.info(paramData.toString());
		getSession1().insert("com.plgrim.ncp.commons.adm.deleteAdminChrgJob", paramData);
	}
	
	
	/**
	 * 고객센터 업무유형 등록
	 * @param paramData
	 * @throws Exception
	 */
	public void insertCcJobTp ( SysCcJobTp paramData) throws Exception {
		paramData.setRegtrId(BOSecurityUtil.getLoginId());
		log.info(paramData.toString());
		getSession1().insert("com.plgrim.ncp.commons.adm.insertCcJobTp", paramData);
	}
	
	/**
	 * 고객센터 업무유형 삭제
	 * @param paramData
	 * @throws Exception
	 */
	public void deleteCcJobTp ( SysCcJobTp paramData) throws Exception {
		paramData.setRegtrId(BOSecurityUtil.getLoginId());
		log.info(paramData.toString());
		getSession1().delete("com.plgrim.ncp.commons.adm.deleteCcJobTp", paramData);
	}
	
	/**
	 * 운영자 비밀번호 초기화
	 * @param paramData
	 * @throws Exception
	 */
	public void updateAdminPassword ( SysAdmin paramData) throws Exception {
		
		paramData.setRegtrId(BOSecurityUtil.getLoginId());
		log.info(paramData.toString());
		getSession1().update("com.plgrim.ncp.commons.adm.updateAdminPassword", paramData);
	}
	
	/**
	 * PO 비밀번호 분실 - 회원 리스트 조회 (존재여부)
	 * @param paramData
	 * @throws Exception
	 */
	public List<SysAdmin> poSelectLostPasswordList ( SysAdmin paramData) throws Exception {
		return getSession1().selectList("com.plgrim.ncp.commons.adm.poSelectLostPasswordList", paramData);
	}
	public List<SysAdmin> poSelectLostInfo ( SysAdmin paramData) throws Exception {
		return getSession1().selectList("com.plgrim.ncp.commons.adm.poSelectLostInfo", paramData);
	}

	/**
	 * PO 비밀번호 분실, 임시비밀번호 발급 (로그인 정보가 없는 사용자 요청임)
	 * @param paramData
	 * @throws Exception
	 */
	public void poUpdateAdminPassword ( SysAdmin paramData) throws Exception {

		paramData.setRegtrId("system");
		paramData.setUdterId("system");
		log.info(paramData.toString());
		getSession1().update("com.plgrim.ncp.commons.adm.updateAdminPassword", paramData); //기존 쿼리 그대로 이용
	}

	/**
	 * 로그인된 운영자정보 수정
	 * @param paramData
	 * @throws Exception
	 */
	public void updateMyInfo ( SysAdmin paramData) throws Exception {
		if(StringService.isEmpty(paramData.getRegtrId())){
			paramData.setRegtrId(BOSecurityUtil.getLoginId());
			log.info(paramData.toString());
		}

		getSession1().update("com.plgrim.ncp.commons.adm.updateMyInfo", paramData);
	}
	
	/**
	 * 운영자정보 비밀번호 초기화 날짜 변경
	 * @param paramData
	 * @throws Exception
	 */
	public void updateAdminPwIntzDt ( SysAdmin paramData) throws Exception {
		
		paramData.setRegtrId(BOSecurityUtil.getLoginId());
		log.info(paramData.toString());
		getSession1().update("com.plgrim.ncp.commons.adm.updateAdminPwIntzDt", paramData);
	}
	
	/**
	 * 브랜드 선택 목록 조회
	 * @param paramData
	 * * @return
	 * @throws Exception
	 */
	public List<SysBrndExtend> getComBrndList ( SysAdmin paramData ) throws Exception {
		log.info(paramData.toString());
		return getSession1().selectList("com.plgrim.ncp.commons.adm.getComBrndList", paramData); //해당 업체 all 브랜드
	}
	public List<SysBrndExtend> getUserComBrndList ( FormSysAdmDTO paramData ) throws Exception {
		log.info(paramData.toString());
		return getSession1().selectList("com.plgrim.ncp.commons.adm.getUserComBrndList", paramData); //해당 사용자의 선택 업체 브랜드
	}

	/**
	 * PO Dashboard 현황 정보
	 * @param paramData
	 * @return
	 * @throws Exception
	 */
	public DashboardSumResult selectDashboardSumInfo( FormSysAdmDTO paramData ) throws Exception {
		log.info(paramData.toString());
		return getSession1().selectOne("com.plgrim.ncp.commons.adm.selectDashboardSumInfo", paramData);
	}

	/**
	 * PO Main Notice list
	 * @param paramData
	 * @return
	 * @throws Exception
	 */
	public List<SysCmmnNotiResult> selectPoMainNoticeList( SystemPK systemPK, SysCmmnNotiDTO paramData ) throws Exception {
		log.info(paramData.toString());
		return getSession1().selectList("com.plgrim.ncp.commons.cmmnnoti.selectPoDashboardNoticeList", paramData);
	}

	/**
	 * * #33927 BO 배송매장 전용 DashBoard 생성
	 * - 배송매장 Notice list
	 *
	 * @param paramData
	 * @return
	 * @throws Exception
	 */
	public List<SysCmmnNotiResult> selectDlvShopMainNoticeList() throws Exception {
		return getSession1().selectList("com.plgrim.ncp.commons.cmmnnoti.selectDlvShopMainNoticeList");
	}

	/**
	 * 운영자 담당 몰 등록
	 *
	 * @param paramData
	 */
	public void insertAdminMall(SysAdminMall paramData) throws Exception {
		log.info(paramData.toString());
		getSession1().insert("com.plgrim.ncp.commons.adm.insertAdminMall", paramData);
	}

	/**
	 * 운영자 브랜드 삭제
	 *
	 * @param paramData
	 * @throws Exception
	 */
	public void deleteAdminMall(SysAdminMall paramData) throws Exception {
		paramData.setRegtrId(BOSecurityUtil.getLoginId());
		log.info(paramData.toString());
		getSession1().delete("com.plgrim.ncp.commons.adm.deleteAdminMall", paramData);
	}

	/**
	 * 운영자 담당 몰 조회
	 *
	 * @param paramData
	 * @throws Exception
	 */
	public List<SysAdminMallExtend> selectAdminMall(FormSysAdmDTO paramData) throws Exception {
		log.info(paramData.toString());
		return getSession1().selectList("com.plgrim.ncp.commons.adm.selectAdminMall", paramData);
	}

}





