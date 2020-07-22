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
package com.plgrim.ncp.commons.service;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.plgrim.ncp.base.abstracts.AbstractService;
import com.plgrim.ncp.base.entities.datasource1.cso.CsoFaqAtchmnfl;
import com.plgrim.ncp.base.entities.datasource1.cso.CsoMtmInqAtchFile;
import com.plgrim.ncp.commons.data.SysCmmnTempletDTO;
import com.plgrim.ncp.commons.data.SysCmmnTempletDataDTO;
import com.plgrim.ncp.commons.data.TransferBoardReqDTO;
import com.plgrim.ncp.commons.data.TransferBoardResDTO;
import com.plgrim.ncp.commons.grid.GridCommand;
import com.plgrim.ncp.commons.repository.SysCmmnTempletRepository;
import com.plgrim.ncp.commons.result.SysCmmnTempletResult;
import com.plgrim.ncp.commons.util.BOSecurityUtil;
import com.plgrim.ncp.framework.data.FileUploadResult;

/**
 * 템플릿 Service
 * @author ed
 *
 */
@Service
public class SysCmmnTempletService extends AbstractService {

	@Autowired
    SysCmmnTempletRepository sysCmmnTempletRepository; // 템플릿 DAO
		
	@Autowired
	private GridCommand gridCommand;
	
	private final String CONFIG_TEMPLET_UPLOAD_FILE_PATH = "ncp_base.image.templet.upload.path";
	private final String CONFIG_TEMPLET_UPLOAD_HTTP_PATH = "ncp_base.image.templet.http.path";
	
	/**
	 * 템플릿 목록 조회.
	 *
	 * @param paramData [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public List<SysCmmnTempletResult> selectSysCmmnTempletList( SysCmmnTempletDTO paramData) throws Exception {
		return sysCmmnTempletRepository.selectSysCmmnTempletList(paramData); 
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
		return sysCmmnTempletRepository.selectSysCmmnTempletListCount(paramData); 
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
		return sysCmmnTempletRepository.selectSysCmmnTempletListExcel(paramData); 
	}
	
	/**
	 * 템플릿 등록.
	 *
	 * @param paramData [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public void insertSysCmmnTemplet( SysCmmnTempletDTO paramData) throws Exception {
		String loginId = BOSecurityUtil.getLoginId(); // 로그인ID
		Long tempSn = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		//paramData.getSysTemplet().setRegtrId(loginId); // 등록자
		//paramData.getSysTemplet().setUdterId(loginId); // 수정자
		
		sysCmmnTempletRepository.insertSysTemplet(paramData); // 템플릿 등록
		
		//tempSn = paramData.getSysTemplet().getTempSn(); // 템플릿 일련번호
		
		// 첨부파일 저장
		if(paramData.getUploadFiles() != null && paramData.getUploadFiles().size() > 0) {
			FileUploadResult fileUploadResult = uploadFiles(paramData.getUploadFiles()); // 첨부파일 업로드
			/*
			if(fileUploadResult != null && fileUploadResult.getFileCnt() > 0) {
				for(FileUploadInfo item : fileUploadResult.getRows()) {
					SysTempletAtchFile sysTempletAtchFile = new SysTempletAtchFile();
					
					sysTempletAtchFile.setTempSn(tempSn); // 공지번호
					sysTempletAtchFile.setTempAtchFileNm(item.getOrgFileName() + "." + item.getExtension()); // 원본파일명
					sysTempletAtchFile.setTempAtchFileUrl(getConfigService().getProperty(CONFIG_TEMPLET_UPLOAD_HTTP_PATH) + "/" + item.getFileName()); // 파일URL
					sysTempletAtchFile.setTempAtchFileCpcty(new BigDecimal(item.getFileSize())); // 파일크기
					sysTempletAtchFile.setRegtrId(loginId); // 등록자
					sysTempletAtchFile.setUdterId(loginId); // 수정자					
					sysCmmnTempletRepository.insertSysTempletAtchFile(sysTempletAtchFile); // 첨부파일 저장
				}
			}
			*/
		}
	}
	
	/**
	 * 템플릿 상세 조회.
	 *
	 * @param paramData [설명]
	 * @return Sys cmmn templet result [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public SysCmmnTempletResult selectSysCmmnTempletDetail( SysCmmnTempletDTO paramData) throws Exception {
		
		sysCmmnTempletRepository.updateTempletReadCnt(paramData);
		
		return sysCmmnTempletRepository.selectSysCmmnTempletDetail(paramData); 
	}
	
	/**
	 * 템플릿 첨부파일 1개 객체 구하기
	 *
	 * @param paramData [설명]
	 * @return Sys cmmn templet result [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public SysCmmnTempletResult selectSysCmmnTempletFile( SysCmmnTempletDTO paramData) throws Exception {
		sysCmmnTempletRepository.updateTempletDownloadCnt(paramData.getSearch().getTempSn());
		return sysCmmnTempletRepository.selectSysCmmnTempletFile(paramData); 
	}
	
	
	
	/**
	 * 템플릿 MAX Download 객체 구하기
	 *
	 * @param paramData [설명]
	 * @return Sys cmmn noti result [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public SysCmmnTempletResult selectSysCmmnMaxTempletFile( SysCmmnTempletDTO paramData) throws Exception {
		sysCmmnTempletRepository.updateTempletMaxDownloadCnt(paramData);
		return sysCmmnTempletRepository.selectSysCmmnMaxTempletFile(paramData); 
	}
	
	/**
	 * 템플릿 수정.
	 *
	 * @param paramData [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public void updateSysCmmnTemplet( SysCmmnTempletDTO paramData) throws Exception {
		String loginId = BOSecurityUtil.getLoginId(); // 로그인ID
		Long tempSn = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		//paramData.getSysTemplet().setUdterId(loginId); // 수정자
						
		sysCmmnTempletRepository.updateSysTemplet(paramData); // 템플릿 수정
		/*
		if(paramData.getDelSysTempletAtchFileList() != null) {
			// 첨부파일 삭제			
			for(SysTempletAtchFile file : paramData.getDelSysTempletAtchFileList()) { 
				sysCmmnTempletRepository.deleteSysTempAtchFile(file); // 첨부파일 삭제
			}
		}
		*/
		//tempSn = paramData.getSysTemplet().getTempSn(); // 템플릿 일련번호
		
		// 첨부파일 저장
		if(paramData.getUploadFiles() != null && paramData.getUploadFiles().size() > 0) {
			FileUploadResult fileUploadResult = uploadFiles(paramData.getUploadFiles()); // 첨부파일 업로드
			/*
			if(fileUploadResult != null && fileUploadResult.getFileCnt() > 0) {
				for(FileUploadInfo item : fileUploadResult.getRows()) {
					SysTempletAtchFile sysTempletAtchFile = new SysTempletAtchFile();
					
					sysTempletAtchFile.setTempSn(tempSn); // 템플릿번호
					sysTempletAtchFile.setTempAtchFileNm(item.getOrgFileName() + "." + item.getExtension()); // 원본파일명
					sysTempletAtchFile.setTempAtchFileUrl(getConfigService().getProperty(CONFIG_TEMPLET_UPLOAD_HTTP_PATH) + "/" + item.getFileName()); // 파일URL
					sysTempletAtchFile.setTempAtchFileCpcty(new BigDecimal(item.getFileSize())); // 파일크기
					sysTempletAtchFile.setRegtrId(loginId); // 등록자
					sysTempletAtchFile.setUdterId(loginId); // 수정자					
					sysCmmnTempletRepository.insertSysTempletAtchFile(sysTempletAtchFile); // 첨부파일 저장
				}
			}
			*/
		}
	}
	
	/**
	 * 템플릿 그리드 수정.
	 *
	 * @param paramData [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public void updateSysTempletGrid( List<SysCmmnTempletDataDTO> paramData) throws Exception {
		String loginId = BOSecurityUtil.getLoginId(); // 로그인ID
		/*
		if(paramData != null) {
			for(SysCmmnTempletDataDTO item : paramData) {
				item.getSysTemplet().setUdterId(loginId);
				sysCmmnTempletRepository.updateSysTempletGrid(item.getSysTemplet());
			}
		}
		*/
	}
	
	/**
	 * 템플릿 삭제
	 * @param paramData
	 * @throws Exception
	 */
	public void deleteTemplet ( SysCmmnTempletDataDTO paramData) throws Exception { 
		sysCmmnTempletRepository.deleteTemplet(paramData);
	}
	
	/**
	 * 템플릿 첨부파일 등록.
	 *
	 * @param paramData [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public FileUploadResult uploadFiles(List<MultipartFile> files) throws Exception {
		// 전체 업로드 확장자 체크를 한다.
		// excludeExtensions 포함된 데이터는 업로드 할 수 없다.
		String[] excludeExtensions = { "jsp", "php", "exe", "bat"};
		
		// 시스템 파일명을 자동으로 부여하여 업로드한다.
		FileUploadResult  fileUploadResult = getIoService().fileUploadAutoFileName(files, getConfigService().getProperty(CONFIG_TEMPLET_UPLOAD_FILE_PATH), excludeExtensions);
		
		return fileUploadResult;
	}
	
	/**
	 * 1:1문의 첨부파일 1개
	 *
	 * @param paramData [설명]
	 * @return Sys cmmn templet result [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public CsoMtmInqAtchFile selectSysCmmnInquiryFile( SysCmmnTempletDTO paramData) throws Exception {
		return sysCmmnTempletRepository.selectSysCmmnInquiryFile(paramData); 
	}
	
	/**
	 * 업무게시판의 첨부파일 1개
	 *
	 * @param paramData [설명]
	 * @return Sys cmmn templet result [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public TransferBoardReqDTO selectSysCmmnTransferBoardReqFile( SysCmmnTempletDTO paramData) throws Exception {
		return sysCmmnTempletRepository.selectSysCmmnTransferBoardReqFile(paramData); 
	}
	public TransferBoardResDTO selectSysCmmnTransferBoardResFile( SysCmmnTempletDTO paramData) throws Exception {
		return sysCmmnTempletRepository.selectSysCmmnTransferBoardResFile(paramData); 
	}
	public int selectSysCmmnTransferBoardDownRoleCount( SysCmmnTempletDTO paramData) throws Exception {
		return sysCmmnTempletRepository.selectSysCmmnTransferBoardDownRoleCount(paramData);
	}
	
	/**
	 * FAQ의 첨부파일 1개
	 *
	 * @param paramData [설명]
	 * @return Sys cmmn templet result [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public CsoFaqAtchmnfl selectSysCmmnFaqFile( SysCmmnTempletDTO paramData) throws Exception {
		return sysCmmnTempletRepository.selectSysCmmnFaqFile(paramData); 
	}
	
}
