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

import com.plgrim.ncp.base.entities.datasource1.cso.CsoFaqAtchmnfl;
import com.plgrim.ncp.base.entities.datasource1.cso.CsoMtmInqAtchFile;
import com.plgrim.ncp.commons.data.*;
import com.plgrim.ncp.commons.result.SysCmmnTempletResult;
import com.plgrim.ncp.framework.data.SystemPK;


public interface OperationContentBOComponent {

	/**
	 * 템플릿 목록 조회.
	 *
	 * @param systemPK [설명]
	 * @param paramData [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public List<SysCmmnTempletResult> selectSysCmmnTempletList( SystemPK systemPK, SysCmmnTempletDTO paramData) throws Exception ;
	
	/**
	 * 템플릿 목록 조회 개수.
	 *
	 * @param systemPK [설명]
	 * @param paramData [설명]
	 * @return Long [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public long selectSysCmmnTempletListCount( SystemPK systemPK, SysCmmnTempletDTO paramData) throws Exception ;
	
	/**
	 * 템플릿 목록 조회 엑셀.
	 *
	 * @param systemPK [설명]
	 * @param paramData [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public List<Map<String, Object>> selectSysCmmnTempletListExcel( SystemPK systemPK, SysCmmnTempletDTO paramData) throws Exception ;
	
	/**
	 * 템플릿 상세 조회.
	 *
	 * @param systemPK [설명]
	 * @param paramData [설명]
	 * @return Sys cmmn templet result [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public SysCmmnTempletResult selectSysCmmnTempletDetail( SystemPK systemPK, SysCmmnTempletDTO paramData) throws Exception ;
	
	/**
	 * 템플릿 첨부파일 1개
	 *
	 * @param paramData [설명]
	 * @return Sys cmmn templet result [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public SysCmmnTempletResult selectSysCmmnTempletFile(SysCmmnTempletDTO paramData) throws Exception ;
	
	/**
	 * 템플릿 MAX Download 객체 구하기
	 *
	 * @param paramData [설명]
	 * @return Sys cmmn templet result [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public SysCmmnTempletResult selectSysCmmnMaxTempletFile(SysCmmnTempletDTO paramData) throws Exception ;
	
	/**
	 * 1:1문의 첨부파일 1개
	 *
	 * @param paramData [설명]
	 * @return Sys cmmn templet result [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public CsoMtmInqAtchFile selectSysCmmnInquiryFile(SysCmmnTempletDTO paramData) throws Exception ;
	
	/**
	 * 업무게시판의 첨부파일 1개
	 *
	 * @param paramData [설명]
	 * @return Sys cmmn templet result [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public TransferBoardReqDTO selectSysCmmnTransferBoardReqFile(SysCmmnTempletDTO paramData) throws Exception ;
	public TransferBoardResDTO selectSysCmmnTransferBoardResFile(SysCmmnTempletDTO paramData) throws Exception ;
	public int selectSysCmmnTransferBoardDownRoleCount(SysCmmnTempletDTO paramData) throws Exception ;
	
	/**
	 * FAQ의 첨부파일 1개
	 *
	 * @param paramData [설명]
	 * @return Sys cmmn templet result [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public CsoFaqAtchmnfl selectSysCmmnFaqFile(SysCmmnTempletDTO paramData) throws Exception ;


	/**
	 * 템플릿 등록.
	 *
	 * @param systemPK [설명]
	 * @param paramData [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public void insertSysCmmnTemplet( SystemPK systemPK, SysCmmnTempletDTO paramData) throws Exception ;

	/**
	 * 템플릿 수정.
	 *
	 * @param systemPK [설명]
	 * @param paramData [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public void updateSysCmmnTemplet( SystemPK systemPK, SysCmmnTempletDTO paramData) throws Exception ;

	/**
	 * 템플릿 그리드 수정.
	 *
	 * @param systemPK [설명]
	 * @param paramData [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public void updateSysTempletGrid( SystemPK systemPK, List<SysCmmnTempletDataDTO> paramData) throws Exception ;

	/**
	 * 템플릿 일괄삭제
	 * @param paramList
	 * @return
	 * @throws Exception
	 */
	public void deleteTempletList ( List<SysCmmnTempletDataDTO> paramList) throws Exception;

}