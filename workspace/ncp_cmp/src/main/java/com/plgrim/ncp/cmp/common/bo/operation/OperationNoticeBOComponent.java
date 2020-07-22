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
package com.plgrim.ncp.cmp.common.bo.operation;

import java.util.List;
import java.util.Map;

import com.plgrim.ncp.base.entities.datasource1.sys.SysNoti;
import com.plgrim.ncp.commons.data.SysCmmnNotiDTO;
import com.plgrim.ncp.commons.data.SysCmmnNotiDataDTO;
import com.plgrim.ncp.commons.result.SysCmmnNotiResult;
import com.plgrim.ncp.framework.data.SystemPK;


public interface OperationNoticeBOComponent {

	/**
	 * 공지사항 목록 조회.
	 *
	 * @param systemPK [설명]
	 * @param paramData [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public List<SysCmmnNotiResult> selectSysCmmnNotiList( SystemPK systemPK, SysCmmnNotiDTO paramData) throws Exception ;

	/**
	 * 공지사항 목록 조회 개수.
	 *
	 * @param systemPK [설명]
	 * @param paramData [설명]
	 * @return Long [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public long selectSysCmmnNotiListCount( SystemPK systemPK, SysCmmnNotiDTO paramData) throws Exception ;

	/**
	 * 공지사항 목록 조회 엑셀.
	 *
	 * @param systemPK [설명]
	 * @param paramData [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public List<Map<String, Object>> selectSysCmmnNotiListExcel( SystemPK systemPK, SysCmmnNotiDTO paramData) throws Exception ;

	/**
	 * 공지사항 상세 조회.
	 *
	 * @param systemPK [설명]
	 * @param paramData [설명]
	 * @return Sys cmmn noti result [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public SysCmmnNotiResult selectSysCmmnNotiDetail( SystemPK systemPK, SysCmmnNotiDTO paramData) throws Exception ;

	/**
	 * 공지사항 첨부파일 1개
	 *
	 * @param paramData [설명]
	 * @return Sys cmmn noti result [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public SysCmmnNotiResult selectSysCmmnNotiFile(SysCmmnNotiDTO paramData) throws Exception ;

	/**
	 * 공지사항 이전글 다음글 조회.
	 *
	 * @param paramData [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 11. 6
	 */
	public List<SysNoti> selectSysPrevNxtNotice(SysCmmnNotiDTO paramData) throws Exception;

	/**
	 * 팝업공지 목록 조회.
	 *
	 * @param systemPK [설명]
	 * @param paramData [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public List<SysCmmnNotiResult> selectSysPopupNotiList( SystemPK systemPK, SysCmmnNotiDTO paramData) throws Exception ;

	/**
	 * 팝업공지 목록 조회 개수.
	 *
	 * @param systemPK [설명]
	 * @param paramData [설명]
	 * @return Long [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public long selectSysPopupNotiListCount( SystemPK systemPK, SysCmmnNotiDTO paramData) throws Exception ;

	/**
	 * 팝업공지 목록 조회 엑셀.
	 *
	 * @param systemPK [설명]
	 * @param paramData [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public List<Map<String, Object>> selectSysPopupNotiListExcel( SystemPK systemPK, SysCmmnNotiDTO paramData) throws Exception ;

	/**
	 * 팝업공지 상세 조회.
	 *
	 * @param systemPK [설명]
	 * @param paramData [설명]
	 * @return Sys cmmn noti result [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public SysCmmnNotiResult selectSysPopupNotiDetail( SystemPK systemPK, SysCmmnNotiDTO paramData) throws Exception ;

	/**
	 * 팝업공지 그룹사 목록 조회.
	 *
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public List<SysCmmnNotiResult> selectSysPopNotiGrpcoList(SystemPK systemPK, Long paramData) throws Exception ;

	/**
	 * 그룹사 목록 조회.
	 *
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public List<SysCmmnNotiResult> selectSysGrpcoList(SystemPK systemPK, SysCmmnNotiDTO paramData) throws Exception ;

	/**
	 * 그룹사 목록 조회 건수.
	 *
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public long selectSysGrpcoListCount(SystemPK systemPK, SysCmmnNotiDTO paramData);

	/**
	 * 공지사항 메인 목록 조회.
	 *
	 * @param systemPK [설명]
	 * @param paramData [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public List<SysCmmnNotiResult> selectMainSysCmmnNotiList( SystemPK systemPK, SysCmmnNotiDTO paramData) throws Exception ;

	/**
	 * 공지사항 등록.
	 *
	 * @param systemPK [설명]
	 * @param paramData [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public void insertSysCmmnNoti( SystemPK systemPK, SysCmmnNotiDTO paramData) throws Exception ;

	/**
	 * 공지사항 수정.
	 *
	 * @param systemPK [설명]
	 * @param paramData [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public void updateSysCmmnNoti( SystemPK systemPK, SysCmmnNotiDTO paramData) throws Exception ;

	/**
	 * 공지사항 그리드 수정.
	 *
	 * @param systemPK [설명]
	 * @param paramData [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public void updateSysNotiGrid( SystemPK systemPK, List<SysCmmnNotiDataDTO> paramData) throws Exception ;

	/**
	 * 팝업공지 등록.
	 *
	 * @param systemPK [설명]
	 * @param paramData [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public void insertSysPopupNoti( SystemPK systemPK, SysCmmnNotiDTO paramData) throws Exception ;

	/**
	 * 팝업공지 수정.
	 *
	 * @param systemPK [설명]
	 * @param paramData [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public void updateSysPopupNoti( SystemPK systemPK, SysCmmnNotiDTO paramData) throws Exception ;

}