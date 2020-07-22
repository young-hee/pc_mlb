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
package com.plgrim.ncp.cmp.common.bo.operation.content;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.plgrim.ncp.cmp.common.bo.operation.OperationContentBOComponent;
import com.plgrim.ncp.commons.data.SysCmmnTempletDataDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.plgrim.ncp.base.abstracts.AbstractComponent;
import com.plgrim.ncp.base.entities.datasource1.cso.CsoFaqAtchmnfl;
import com.plgrim.ncp.base.entities.datasource1.cso.CsoMtmInqAtchFile;
import com.plgrim.ncp.commons.data.SysCmmnTempletDTO;
import com.plgrim.ncp.commons.data.TransferBoardReqDTO;
import com.plgrim.ncp.commons.data.TransferBoardResDTO;
import com.plgrim.ncp.commons.result.SysCmmnTempletResult;
import com.plgrim.ncp.commons.service.SysCmmnTempletService;
import com.plgrim.ncp.framework.data.SystemPK;

// @Slf4j
@Component
public class OperationContentBOComponentImpl extends AbstractComponent implements OperationContentBOComponent {

	@Autowired
	SysCmmnTempletService sysCmmnTempletService; // 메뉴얼 Service
	
	/**
	 * 템플릿 목록 조회.
	 *
	 * @param systemPK [설명]
	 * @param paramData [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public List<SysCmmnTempletResult> selectSysCmmnTempletList( SystemPK systemPK, SysCmmnTempletDTO paramData) throws Exception {
		return sysCmmnTempletService.selectSysCmmnTempletList(paramData); 
	}
	
	/**
	 * 템플릿 목록 조회 개수.
	 *
	 * @param systemPK [설명]
	 * @param paramData [설명]
	 * @return Long [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public long selectSysCmmnTempletListCount( SystemPK systemPK, SysCmmnTempletDTO paramData) throws Exception {
		return sysCmmnTempletService.selectSysCmmnTempletListCount(paramData); 
	}
	
	/**
	 * 템플릿 목록 조회 엑셀.
	 *
	 * @param systemPK [설명]
	 * @param paramData [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public List<Map<String, Object>> selectSysCmmnTempletListExcel( SystemPK systemPK, SysCmmnTempletDTO paramData) throws Exception {
		return sysCmmnTempletService.selectSysCmmnTempletListExcel(paramData); 
	}
	
	/**
	 * 템플릿 상세 조회.
	 *
	 * @param systemPK [설명]
	 * @param paramData [설명]
	 * @return Sys cmmn noti result [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public SysCmmnTempletResult selectSysCmmnTempletDetail( SystemPK systemPK, SysCmmnTempletDTO paramData) throws Exception {
		return sysCmmnTempletService.selectSysCmmnTempletDetail(paramData); 
	}
	
	/**
	 * 템플릿 첨부 파일 1개.
	 *
	 * @param systemPK [설명]
	 * @param paramData [설명]
	 * @return Sys cmmn noti result [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public SysCmmnTempletResult selectSysCmmnTempletFile(SysCmmnTempletDTO paramData) throws Exception {
		return sysCmmnTempletService.selectSysCmmnTempletFile(paramData);
	}
	
	/**
	 * 템플릿 MAX Download 객체 구하기
	 *
	 * @param systemPK [설명]
	 * @param paramData [설명]
	 * @return Sys cmmn noti result [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public SysCmmnTempletResult selectSysCmmnMaxTempletFile(SysCmmnTempletDTO paramData) throws Exception {
		return sysCmmnTempletService.selectSysCmmnMaxTempletFile(paramData);
	}
	
	/**
	 * 1:1문의 첨부파일 1개
	 *
	 * @param systemPK [설명]
	 * @param paramData [설명]
	 * @return Sys cmmn noti result [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public CsoMtmInqAtchFile selectSysCmmnInquiryFile(SysCmmnTempletDTO paramData) throws Exception {
		return sysCmmnTempletService.selectSysCmmnInquiryFile(paramData);
	}
	
	/**
	 * 업무게시판 첨부파일 1개
	 *
	 * @param systemPK [설명]
	 * @param paramData [설명]
	 * @return Sys cmmn noti result [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public TransferBoardReqDTO selectSysCmmnTransferBoardReqFile(SysCmmnTempletDTO paramData) throws Exception {
		return sysCmmnTempletService.selectSysCmmnTransferBoardReqFile(paramData);
	}
	public TransferBoardResDTO selectSysCmmnTransferBoardResFile(SysCmmnTempletDTO paramData) throws Exception {
		return sysCmmnTempletService.selectSysCmmnTransferBoardResFile(paramData);
	}
	public int selectSysCmmnTransferBoardDownRoleCount(SysCmmnTempletDTO paramData) throws Exception {
		return sysCmmnTempletService.selectSysCmmnTransferBoardDownRoleCount(paramData);
	}
	
	/**
	 * FAQ 첨부파일 1개
	 *
	 * @param systemPK [설명]
	 * @param paramData [설명]
	 * @return Sys cmmn noti result [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public CsoFaqAtchmnfl selectSysCmmnFaqFile(SysCmmnTempletDTO paramData) throws Exception {
		return sysCmmnTempletService.selectSysCmmnFaqFile(paramData);
	}

	/**
	 * 템플릿 등록.
	 *
	 * @param systemPK [설명]
	 * @param paramData [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public void insertSysCmmnTemplet( SystemPK systemPK, SysCmmnTempletDTO paramData) throws Exception {
		sysCmmnTempletService.insertSysCmmnTemplet(paramData);
	}

	/**
	 * 템플릿 수정.
	 *
	 * @param systemPK [설명]
	 * @param paramData [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public void updateSysCmmnTemplet( SystemPK systemPK, SysCmmnTempletDTO paramData) throws Exception {
		sysCmmnTempletService.updateSysCmmnTemplet(paramData);
	}

	/**
	 * 템플릿 그리드 수정.
	 *
	 * @param systemPK [설명]
	 * @param paramData [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public void updateSysTempletGrid( SystemPK systemPK, List<SysCmmnTempletDataDTO> paramData) throws Exception {
		sysCmmnTempletService.updateSysTempletGrid(paramData);
	}

	/**
	 * 템플릿 일괄삭제
	 * @param paramList
	 * @return
	 * @throws Exception
	 */
	public void deleteTempletList ( List<SysCmmnTempletDataDTO> paramList) throws Exception {

		Iterator<SysCmmnTempletDataDTO> iterator = paramList.iterator();
		while(iterator.hasNext()) {
			SysCmmnTempletDataDTO rowData = iterator.next();

			//템플릿 삭제
			sysCmmnTempletService.deleteTemplet(rowData);
		}
	}

}
