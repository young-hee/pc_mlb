/* Copyright (c) 2015 plgrim, Inc.
 * All right reserved.
 * http://plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 *
 * ------------------------------------------------------------------------
 * @author      yoon.eun
 * @since       2015. 11. 6
 */
package com.plgrim.ncp.commons.repository;

import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.datasource1.sys.SysBoSite;
import com.plgrim.ncp.base.entities.datasource1.sys.SysMall;
import com.plgrim.ncp.base.entities.datasource1.sys.SysNoti;
import com.plgrim.ncp.base.entities.datasource1.sys.SysNotiAtchFile;
import com.plgrim.ncp.base.entities.datasource1.sys.SysPopupNoti;
import com.plgrim.ncp.base.entities.datasource1.sys.SysPopupNotiAplTgt;
import com.plgrim.ncp.commons.data.SysCmmnNotiDTO;
import com.plgrim.ncp.commons.data.SysPopupNotiDataDTO;
import com.plgrim.ncp.commons.result.SysCmmnNotiResult;

/**
 * 공지사항 Repository.
 *
 * @author ed
 */
@Slf4j
@Repository
public class SysCmmnNotiRepository extends AbstractRepository {
	
	/**
	 * 시스템공지 목록 조회.
	 *
	 * @param paramData [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public List<SysCmmnNotiResult> selectSysCmmnNotiBoList( SysCmmnNotiDTO paramData) throws Exception {
		return getSession1().selectList("com.plgrim.ncp.commons.cmmnnoti.selectSysCmmnNotiBoList", paramData);
	}
	
	/**
	 * 시스템공지 목록 조회 개수.
	 *
	 * @param paramData [설명]
	 * @return Long [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public long selectSysCmmnNotiBoListCount( SysCmmnNotiDTO paramData) throws Exception {
		return getSession1().selectOne("com.plgrim.ncp.commons.cmmnnoti.selectSysCmmnNotiBoListCount", paramData);
    }
	
	/**
	 * 시스템공지 목록 조회 엑셀.
	 *
	 * @param paramData [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public List<Map<String, Object>> selectSysCmmnNotiBoListExcel( SysCmmnNotiDTO paramData) throws Exception {
		return getSession1().selectList("com.plgrim.ncp.commons.cmmnnoti.selectSysCmmnNotiBoListExcel", paramData);
	}
	
	/**
	 * 시스템공지 등록.
	 *
	 * @param paramData [설명]
	 * @return int
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public int insertSysNoti(SysCmmnNotiDTO paramData)throws Exception {
		return getSession1().insert("com.plgrim.ncp.commons.cmmnnoti.insertSysNoti", paramData);
	}
	
	/**
	 * 시스템공지 상세 조회.
	 *
	 * @param paramData [설명]
	 * @return Sys cmmn noti result
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public SysCmmnNotiResult selectSysCmmnNotiBoDetail( SysCmmnNotiDTO paramData) throws Exception {
		return getSession1().selectOne("com.plgrim.ncp.commons.cmmnnoti.selectSysCmmnNotiBoDetail", paramData);
	}
	
	/**
	 * 시스템공지 첨부파일 1개.
	 *
	 * @param paramData [설명]
	 * @return Sys cmmn noti result
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public SysCmmnNotiResult selectSysCmmnNotiBoFile( SysCmmnNotiDTO paramData) throws Exception {
		return getSession1().selectOne("com.plgrim.ncp.commons.cmmnnoti.selectSysCmmnNotiBoFile", paramData);
	}

	/**
	 * 공지사항 이전글 다음글 조회.
	 *
	 * @param paramData [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 11. 6
	 */
	public List<SysNoti> selectSysPrevNxtNotice(SysCmmnNotiDTO paramData) throws Exception {
		return getSession1().selectList("com.plgrim.ncp.commons.cmmnnoti.selectSysPrevNxtNotice", paramData);
	}

	/**
	 * 시스템공지 수정.
	 *
	 * @param paramData [설명]
	 * @return int
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public int updateSysNoti(SysCmmnNotiDTO paramData) throws Exception {
		return getSession1().update("com.plgrim.ncp.commons.cmmnnoti.updateSysNoti", paramData);
	}
	
	/**
	 * 시스템공지 그리드 수정.
	 *
	 * @param paramData [설명]
	 * @return int
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public int updateSysNotiGrid(SysNoti paramData) throws Exception {
		return getSession1().update("com.plgrim.ncp.commons.cmmnnoti.updateSysNotiGrid", paramData);
	}
	
	/**
	 * BO공지대상 삭제.
	 *
	 * @param paramData [설명]
	 * @return Int [설명]
	 * @throws Exception the exception
	 * @since 2015. 6. 1
	 */
	public int deleteSysBoNotiTgtAll(SysCmmnNotiDTO paramData) throws Exception {
		return getSession1().delete("com.plgrim.ncp.commons.cmmnnoti.deleteSysBoNotiTgtAll", paramData);
	}
	
	/**
	 * BO공지사이트 삭제.
	 *
	 * @param paramData [설명]
	 * @return Int [설명]
	 * @throws Exception the exception
	 * @since 2015. 6. 1
	 */
	public int deleteSysBoNotiSiteAll(SysCmmnNotiDTO paramData) throws Exception {
		return getSession1().delete("com.plgrim.ncp.commons.cmmnnoti.deleteSysBoNotiSiteAll", paramData);
	}
	
	/**
	 * FO공지몰 삭제.
	 *
	 * @param paramData [설명]
	 * @return Int [설명]
	 * @throws Exception the exception
	 * @since 2015. 6. 1
	 */
	public int deleteSysFoNotiMallAll(SysCmmnNotiDTO paramData) throws Exception {
		return getSession1().delete("com.plgrim.ncp.commons.cmmnnoti.deleteSysFoNotiMallAll", paramData);
	}
	
	/**
	 * 몰 목록 조회.
	 *
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public List<SysMall> selectSysMallList() throws Exception {
		return getSession1().selectList("com.plgrim.ncp.commons.cmmnnoti.selectSysMallList");
	}
	
	/**
	 * BO 사이트 목록 조회.
	 *
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public List<SysBoSite> selectSysBoSiteList() throws Exception {
		return getSession1().selectList("com.plgrim.ncp.commons.cmmnnoti.selectSysBoSiteList");
	}
	
	/**
	 * 전시영역 목록 조회.
	 *
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public List<SysCmmnNotiResult> selectDspCtgryList() throws Exception {
		return getSession1().selectList("com.plgrim.ncp.commons.cmmnnoti.selectDspCtgryList");
	}
	
	/**
	 * 팝업공지 목록 조회.
	 *
	 * @param paramData [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public List<SysCmmnNotiResult> selectSysPopupNotiList( SysCmmnNotiDTO paramData) throws Exception {
		return getSession1().selectList("com.plgrim.ncp.commons.cmmnnoti.selectSysPopupNotiList", paramData);
	}
	
	/**
	 * 팝업공지 목록 조회 개수.
	 *
	 * @param paramData [설명]
	 * @return Long [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public long selectSysPopupNotiListCount( SysCmmnNotiDTO paramData) throws Exception {
		return getSession1().selectOne("com.plgrim.ncp.commons.cmmnnoti.selectSysPopupNotiListCount", paramData);
    }
	
	/**
	 * 팝업공지 목록 조회 엑셀.
	 *
	 * @param paramData [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public List<Map<String, Object>> selectSysPopupNotiListExcel( SysCmmnNotiDTO paramData) throws Exception {
		return getSession1().selectList("com.plgrim.ncp.commons.cmmnnoti.selectSysPopupNotiListExcel", paramData);
	}
	
	/**
	 * 첨부파일 등록.
	 *
	 * @param paramData [설명]
	 * @return int
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public int insertSysNotiAtchFile(SysNotiAtchFile paramData)throws Exception {
		return getSession1().insert("com.plgrim.ncp.commons.cmmnnoti.insertSysNotiAtchFile", paramData);
	}
	
	/**
	 * 팝업공지 등록.
	 *
	 * @param paramData [설명]
	 * @return int
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public int insertSysPopupNoti(SysPopupNoti paramData)throws Exception {
		return getSession1().insert("com.plgrim.ncp.commons.cmmnnoti.insertSysPopupNoti", paramData);
	}
	
	/**
	 * 팝업공지 적용대상 등록.
	 *
	 * @param paramData [설명]
	 * @return int
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public int insertSysPopupNotiAplTgt(SysPopupNotiAplTgt paramData)throws Exception {
		return getSession1().insert("com.plgrim.ncp.commons.cmmnnoti.insertSysPopupNotiAplTgt", paramData);
	}
	
	/**
	 * 팝업공지 상세 조회.
	 *
	 * @param paramData [설명]
	 * @return Sys cmmn noti result
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public SysCmmnNotiResult selectSysPopupNotiDetail( SysCmmnNotiDTO paramData) throws Exception {
		return getSession1().selectOne("com.plgrim.ncp.commons.cmmnnoti.selectSysPopupNotiDetail", paramData);
	}
	
	/**
	 * 팝업공지대상 삭제.
	 *
	 * @param paramData [설명]
	 * @return Int [설명]
	 * @throws Exception the exception
	 * @since 2015. 6. 1
	 */
	public int deleteSysPopupNotiAplTgtAll(SysCmmnNotiDTO paramData) throws Exception {
		return getSession1().delete("com.plgrim.ncp.commons.cmmnnoti.deleteSysPopupNotiAplTgtAll", paramData);
	}
	
	/**
	 * 팝업공지카테고리 삭제.
	 *
	 * @param paramData [설명]
	 * @return Int [설명]
	 * @throws Exception the exception
	 * @since 2015. 6. 1
	 */
	public int deleteSysPopupNotiDspCtgryCnncAll(SysCmmnNotiDTO paramData) throws Exception {
		return getSession1().delete("com.plgrim.ncp.commons.cmmnnoti.deleteSysPopupNotiDspCtgryCnncAll", paramData);
	}
	
	/**
	 * 팝업공지 수정.
	 *
	 * @param paramData [설명]
	 * @return int
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public int updateSysPopupNoti(SysPopupNotiDataDTO paramData) throws Exception {
		return getSession1().update("com.plgrim.ncp.commons.cmmnnoti.updateSysPopupNoti", paramData);
	}
	
	/**
	 * 팝업공지 그룹사 목록 조회.
	 *
	 * @param paramData [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public List<SysCmmnNotiResult> selectSysPopNotiGrpcoList(Long paramData) throws Exception {
		return getSession1().selectList("com.plgrim.ncp.commons.cmmnnoti.selectSysPopNotiGrpcoList", paramData);
	}
	
	/**
	 * 그룹사 목록 조회.
	 *
	 * @param paramData [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public List<SysCmmnNotiResult> selectSysGrpcoList(SysCmmnNotiDTO paramData) throws Exception {
		return getSession1().selectList("com.plgrim.ncp.commons.cmmnnoti.selectSysGrpcoList", paramData);
	}
	
	/**
	 * 그룹사 목록 조회 건수.
	 *
	 * @param paramData [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public long selectSysGrpcoListCount(SysCmmnNotiDTO paramData) {
		return getSession1().selectOne("com.plgrim.ncp.commons.cmmnnoti.selectSysGrpcoListCount", paramData);
    }
	
	/**
	 * 시스템공지 메인 목록 조회.
	 *
	 * @param paramData [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public List<SysCmmnNotiResult> selectMainSysCmmnNotiList( SysCmmnNotiDTO paramData) throws Exception {
		return getSession1().selectList("com.plgrim.ncp.commons.cmmnnoti.selectMainSysCmmnNotiList", paramData);
	}
}





