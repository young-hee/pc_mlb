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

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.datasource1.cso.CsoFaqAtchmnfl;
import com.plgrim.ncp.base.entities.datasource1.cso.CsoMtmInqAtchFile;
import com.plgrim.ncp.commons.data.SysCmmnTempletDTO;
import com.plgrim.ncp.commons.data.SysCmmnTempletDataDTO;
import com.plgrim.ncp.commons.data.TransferBoardReqDTO;
import com.plgrim.ncp.commons.data.TransferBoardResDTO;
import com.plgrim.ncp.commons.result.SysCmmnTempletResult;

import lombok.extern.slf4j.Slf4j;

/**
 * 템플릿 Repository.
 *
 * @author peter
 */
@Slf4j
@Repository
public class SysCmmnTempletRepository extends AbstractRepository {
	
	/**
	 * 템플릿 목록 조회.
	 *
	 * @param paramData [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public List<SysCmmnTempletResult> selectSysCmmnTempletList( SysCmmnTempletDTO paramData) throws Exception {
		return getSession1().selectList("com.plgrim.ncp.commons.cmmntemp.selectSysCmmnTempletList", paramData);
	}
	
	/**
	 * 템플릿 목록 조회 개수.
	 *
	 * @param paramData [설명]
	 * @return Long [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public long selectSysCmmnTempletListCount( SysCmmnTempletDTO paramData) throws Exception {
		return getSession1().selectOne("com.plgrim.ncp.commons.cmmntemp.selectSysCmmnTempletListCount", paramData);
    }
	
	/**
	 * 템플릿 목록 조회 엑셀.
	 *
	 * @param paramData [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public List<Map<String, Object>> selectSysCmmnTempletListExcel( SysCmmnTempletDTO paramData) throws Exception {
		return getSession1().selectList("com.plgrim.ncp.commons.cmmntemp.selectSysCmmnTempletListExcel", paramData);
	}
	
	/**
	 * 템플릿 등록.
	 *
	 * @param paramData [설명]
	 * @return int
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public int insertSysTemplet(SysCmmnTempletDTO paramData)throws Exception {
		return getSession1().insert("com.plgrim.ncp.commons.cmmntemp.insertSysTemplet", paramData);
	}
	
	/**
	 * 템플릿 상세 조회.
	 *
	 * @param paramData [설명]
	 * @return Sys cmmn noti result
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public SysCmmnTempletResult selectSysCmmnTempletDetail( SysCmmnTempletDTO paramData) throws Exception {
		return getSession1().selectOne("com.plgrim.ncp.commons.cmmntemp.selectSysCmmnTempletDetail", paramData);
	}	
	
	/**
	 * 템플릿 첨부파일 1개 객체 구하기
	 *
	 * @param paramData [설명]
	 * @return Sys cmmn noti result
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public SysCmmnTempletResult selectSysCmmnTempletFile( SysCmmnTempletDTO paramData) throws Exception {
		return getSession1().selectOne("com.plgrim.ncp.commons.cmmntemp.selectSysCmmnTempletFile", paramData);
	}
	
	
	
	/**
	 * 템플릿 MAX Download 객체 구하기
	 *
	 * @param paramData [설명]
	 * @return Sys cmmn noti result
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public SysCmmnTempletResult selectSysCmmnMaxTempletFile( SysCmmnTempletDTO paramData) throws Exception {
		return getSession1().selectOne("com.plgrim.ncp.commons.cmmntemp.selectSysCmmnMaxTempletFile", paramData);
	}
	
	/**
	 * 템플릿 수정.
	 *
	 * @param paramData [설명]
	 * @return int
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public int updateSysTemplet(SysCmmnTempletDTO paramData) throws Exception {
		return getSession1().update("com.plgrim.ncp.commons.cmmntemp.updateSysTemplet", paramData);
	}	
	
	/**
	 * 템플릿 그리드 수정.
	 *
	 * @param paramData [설명]
	 * @return int
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	/*
	public int updateSysTempletGrid(SysTemplet paramData) throws Exception {
		return getSession1().update("com.plgrim.ncp.commons.cmmntemp.updateSysTempletGrid", paramData);
	}
	*/
	
	/**
	 * 템플릿 삭제
	 * @param paramData
	 * @throws Exception
	 */
	public void deleteTemplet ( SysCmmnTempletDataDTO paramData) throws Exception {
		/*
		paramData.getSysTemplet().setRegtrId(BOSecurityUtil.getLoginId());
		log.info(paramData.toString());
		getSession1().delete("com.plgrim.ncp.commons.cmmntemp.deleteTemplet", paramData) ;
		getSession1().delete("com.plgrim.ncp.commons.cmmntemp.deleteTempletAttach", paramData) ; //관련 첨부파일 삭제
		*/
	}
		
	/**
	 * 템플릿 삭제.
	 *
	 * @param paramData [설명]
	 * @return Int [설명]
	 * @throws Exception the exception
	 * @since 2015. 6. 1
	 */
	public int deleteSysTemplet(SysCmmnTempletDTO paramData) throws Exception {
		return getSession1().delete("com.plgrim.ncp.commons.cmmntemp.deleteSysTemplet", paramData);
	}
	
	/**
	 * 첨부파일 등록.
	 *
	 * @param paramData [설명]
	 * @return int
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	/*
	public int insertSysTempletAtchFile(SysTempletAtchFile paramData)throws Exception {
		return getSession1().insert("com.plgrim.ncp.commons.cmmntemp.insertSysTempletAtchFile", paramData);
	}
	*/
	/**
	 * 템플릿 첨부 파일 삭제.
	 *
	 * @param sysNotiAtchFile the SysNotiAtchFile
	 * @return the SysNotiAtchFile
	 * @throws SQLException the SQL exception
	 */
	/*
	public int deleteSysTempAtchFile(SysTempletAtchFile sysTempletAtchFile) throws Exception {
		//업데이트 후에 데이터를 다시 조회 한다.
		return getSession1().delete("com.plgrim.ncp.commons.cmmntemp.deleteSysTempletAtchFile", sysTempletAtchFile);
	}
	*/
	
	/**
	 * 템플릿 조회 횟수 증가.
	 *
	 * @param paramData the SysCmmnTempletDTO
	 * @return the SysCmmnTempletDTO
	 * @throws SQLException the SQL exception
	 */
	public int updateTempletReadCnt(SysCmmnTempletDTO paramData) throws Exception {
		return getSession1().update("com.plgrim.ncp.commons.cmmntemp.updateTempletReadCnt", paramData);
	}
	
	/**
	 * 템플릿 다운로드 횟수 증가. (Templet관리 용)
	 *
	 * @param paramData the SysCmmnTempletDTO
	 * @return the SysCmmnTempletDTO
	 * @throws SQLException the SQL exception
	 */
	public int updateTempletDownloadCnt(long sn) throws Exception {
		SysCmmnTempletDTO paramData = new SysCmmnTempletDTO();
		//paramData.getSysTemplet().setTempSn(sn);
		return getSession1().update("com.plgrim.ncp.commons.cmmntemp.updateTempletDownloadCnt", paramData);
	}
	
	/**
	 * 템플릿 다운로드 횟수 증가.(MAX DOWNLOAD 용)
	 *
	 * @param paramData the SysCmmnTempletDTO
	 * @return the SysCmmnTempletDTO
	 * @throws SQLException the SQL exception
	 */
	public int updateTempletMaxDownloadCnt(SysCmmnTempletDTO paramData) throws Exception {
		return getSession1().update("com.plgrim.ncp.commons.cmmntemp.updateTempletMaxDownloadCnt", paramData);
	}
	
	
	/**
	 * 1:1문의 첨부파일 1개
	 *
	 * @param paramData [설명]
	 * @return Sys cmmn templet result
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public CsoMtmInqAtchFile selectSysCmmnInquiryFile( SysCmmnTempletDTO paramData) throws Exception {
		return getSession1().selectOne("com.plgrim.ncp.commons.cmmntemp.selectSysCmmnInquiryFile", paramData);
	}
	
	/**
	 * 업무게시판의 첨부파일 1개
	 *
	 * @param paramData [설명]
	 * @return Sys cmmn templet result
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public TransferBoardReqDTO selectSysCmmnTransferBoardReqFile( SysCmmnTempletDTO paramData) throws Exception {
		return getSession1().selectOne("com.plgrim.ncp.commons.cmmntemp.selectSysCmmnTransferBoardReqFile", paramData);
	}
	public TransferBoardResDTO selectSysCmmnTransferBoardResFile( SysCmmnTempletDTO paramData) throws Exception {
		return getSession1().selectOne("com.plgrim.ncp.commons.cmmntemp.selectSysCmmnTransferBoardResFile", paramData);
	}
	public int selectSysCmmnTransferBoardDownRoleCount( SysCmmnTempletDTO paramData) throws Exception {
		int cnt = getSession1().selectOne("com.plgrim.ncp.commons.cmmntemp.selectSysCmmnTransferBoardDownRoleCount", paramData);
		return cnt;
	}
	
	/**
	 * FAQ의 첨부파일 1개
	 *
	 * @param paramData [설명]
	 * @return Sys cmmn templet result
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public CsoFaqAtchmnfl selectSysCmmnFaqFile( SysCmmnTempletDTO paramData) throws Exception {
		return getSession1().selectOne("com.plgrim.ncp.commons.cmmntemp.selectSysCmmnFaqFile", paramData);
	}
	
}





