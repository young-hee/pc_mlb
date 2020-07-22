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
package com.plgrim.ncp.biz.callcenter.service;

import com.plgrim.ncp.base.abstracts.AbstractService;
import com.plgrim.ncp.base.entities.datasource1.cso.*;
import com.plgrim.ncp.base.enums.CounselTransferBoardEnum;
import com.plgrim.ncp.base.enums.CsoJobRequstEnum;
import com.plgrim.ncp.biz.callcenter.data.CounselTransferBoardDTO;
import com.plgrim.ncp.biz.callcenter.repository.*;
import com.plgrim.ncp.commons.util.BOSecurityUtil;
import com.plgrim.ncp.framework.commons.IOService;
import com.plgrim.ncp.framework.data.FileUploadInfo;
import com.plgrim.ncp.framework.data.FileUploadResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

/**
 * CodeViewService Implementation
 * @author Park
 *
 */
@Service
@Slf4j
public class CounselTransferBoardService extends AbstractService {
	
	@Autowired
	private CsoJobRequstEntityRepository csoJobRequstEntityRepository;
	
	@Autowired
	private CsoJobRequstAnsEntityRepository csoJobRequstAnsEntityRepository;
	
	@Autowired
	private CsoJobRequstAtchmnflEntityRepository csoJobRequstAtchmnflEntityRepository;
	
	@Autowired
	private CsoJobRequstOrdGodEntityRepository csoJobRequstOrdGodEntityRepository;
	
	@Autowired
	private CsoJobRequstAnsAtchmnflEntityRepository csoJobRequstAnsAtchmnflEntityRepository;

	@Autowired
	private CounselTransferBoardRepository counselTransferBoardRepository;
	
	public void insertCounselTransferBoard(CounselTransferBoardDTO counselTransferBoardDTO) throws Exception{

		CsoJobRequst csoJobRequst = counselTransferBoardDTO.getCsoJobRequst();
		//csoJobRequst.setTransStatCd(CounselTransferBoardEnum.transStat.TRANS_WAIT.toString());
		csoJobRequst.setRegAdminId(BOSecurityUtil.getLoginId());
		csoJobRequst.setRegtrId(BOSecurityUtil.getLoginId());
		csoJobRequst.setUdterId(BOSecurityUtil.getLoginId());
		csoJobRequst.setMallId(counselTransferBoardDTO.getCsoJobRequst().getMallId());
		csoJobRequst.setLangCd(counselTransferBoardDTO.getCsoJobRequst().getLangCd());

		//csoJobRequstEntityRepository.insertCsoJobRequstWithGenSn(csoJobRequst);

		counselTransferBoardRepository.insertCounselTransferBoardWithGenSn(csoJobRequst);
	}
	
	public void insertCounselTransferBoardOrdGod(CounselTransferBoardDTO counselTransferBoardDTO) throws Exception{
		CsoJobRequst csoJboRequst = counselTransferBoardDTO.getCsoJobRequst();
		CsoJobRequstOrdGod csoJobRequstOrdGod = counselTransferBoardDTO.getCsoJobRequstOrdGod();

		if(	!"".equals(csoJobRequstOrdGod.getOrdNo()) && csoJobRequstOrdGod.getOrdNo() != null ||
			!"".equals(csoJobRequstOrdGod.getGodNo()) && csoJobRequstOrdGod.getGodNo() != null){
			
			String ordNo = csoJobRequstOrdGod.getOrdNo();
			String godNo = csoJobRequstOrdGod.getGodNo();
			String godNm = csoJobRequstOrdGod.getGodNm();
			
			if(!"".equals(csoJobRequstOrdGod.getOrdNo()) && csoJobRequstOrdGod.getOrdNo() != null){
				
				csoJobRequstOrdGod.setRequstSn(csoJboRequst.getRequstSn());
				csoJobRequstOrdGod.setOrdNo(csoJobRequstOrdGod.getOrdNo());
				csoJobRequstOrdGod.setGodNo(null);
				csoJobRequstOrdGod.setOrdGodSectCd(CounselTransferBoardEnum.ordGodSect.ORD.toString());

				counselTransferBoardRepository.insertCsoJobRequstOrdGodWithGenTurn(csoJobRequstOrdGod);
				
				//csoJobRequstOrdGodEntityRepository.insertCsoJobRequstOrdGodWithGenTurn(csoJobRequstOrdGod);
				
			}
			
			if(!"".equals(godNo) && godNo != null){
				
				csoJobRequstOrdGod.setRequstSn(csoJboRequst.getRequstSn());
				csoJobRequstOrdGod.setOrdNo(null);
				csoJobRequstOrdGod.setOrdGodSectCd(CounselTransferBoardEnum.ordGodSect.GOD.toString());
				csoJobRequstOrdGod.setRegtrId(BOSecurityUtil.getLoginId());
				csoJobRequstOrdGod.setUdterId(BOSecurityUtil.getLoginId());
				
				String[] godNoArr = godNo.split(",");
				String[] godNmArr = godNm.split(",");
				for(int i = 0; i < godNoArr.length; i++){
					csoJobRequstOrdGod.setGodNo(godNoArr[i]);
					csoJobRequstOrdGod.setGodNm(godNmArr[i]);

					counselTransferBoardRepository.insertCounselTransferOrdGodWithGenTurn(csoJobRequstOrdGod);
					//csoJobRequstOrdGodEntityRepository.insertCsoJobRequstOrdGodWithGenTurn(counselTransferBoardDTO.getCsoJobRequstOrdGod());
				}
				
			}
			
			
		}
		
		
	}
	
	
	public void insertCounselTransferBoardAtchmnfl(CounselTransferBoardDTO counselTransferBoardDTO, List<MultipartFile> files) throws Exception{
		
		CsoJobRequst csoJobRequst = counselTransferBoardDTO.getCsoJobRequst();
		//CsoJobRequstAtchmnfl csoJobRequstAtchmnfl = counselTransferBoardDTO.getCsoJobRequstAtchmnfl();
		
		
		
		
		log.info("- text parameter ----------");
		
		// 전체 업로드 확장자 체크를 한다.
		// excludeExtensions 포함된 데이터는 업로드 할 수 없다.
		String[] excludeExtensions = { "jsp", "php", "exe", "bat"};
		
		//확장자를 체크한다. 불가 확장자이며 NotSupportedUploadFileException 발생시킨다.
		IOService.availableUploadExcludeExtension(files, excludeExtensions);
		
		/* 시스템 파일명을 자동으로 부여하여 업로드한다.
			FileUploadResult  fileUploadResult = getIoService().fileUploadAutoFileName(files, "c:/upload/", excludeExtensions);
		*/
		// 중복된 파일이 존재하면 오버라이트한다.
		String timeStamp = new SimpleDateFormat("yy/MM/dd").format(Calendar.getInstance().getTime());
		String path = timeStamp + "/";
		FileUploadResult fileUploadResult = getIoService().fileUploadAutoFileName(files, getConfigService().getProperty("ncp_web_bo.image.cs.jobrequst.upload.path") + path, excludeExtensions);
		//FileUploadResult  fileUploadResult = IOService.fileUploadOverWrite(files, "c:/upload/", excludeExtensions);
		
		
		if( fileUploadResult.getFileCnt() > 0){
			
 			List<FileUploadInfo> rows = fileUploadResult.getRows();
			
			for (FileUploadInfo fileInfo : rows) {
				
				CsoJobRequstAtchmnfl csoJobRequstAtchmnfl = new CsoJobRequstAtchmnfl();
				
				csoJobRequstAtchmnfl.setRequstSn(csoJobRequst.getRequstSn());
				
				log.info("- sn----------");
				log.info(csoJobRequst.getRequstSn().toString());

				csoJobRequstAtchmnfl.setRequstAtchFileNm(fileInfo.getOrgFileName() + "." + fileInfo.getExtension());
				csoJobRequstAtchmnfl.setRequstAtchFileUrl(getConfigService().getProperty("ncp_web_bo.image.cs.jobrequst.http.path") + path + fileInfo.getFileName());
				csoJobRequstAtchmnfl.setRequstAtchFileCpcty(new BigDecimal(fileInfo.getFileSize()));
				
				csoJobRequstAtchmnflEntityRepository.insertCsoJobRequstAtchmnflWithGenTurn(csoJobRequstAtchmnfl); 
				
				log.info("########## Start "+fileInfo.getOrgFileName() +" 파일 정보 #############################");
				// 확장자
				log.info("- 파일 확장자----------");
				log.info(fileInfo.getExtension());
				// 파일 사이즈
				log.info("- 파일사이즈----------");
				log.info("" + fileInfo.getFileSize());

				log.info("= 파일 저장 경로 =============================");
				log.info( fileInfo.getFilePath() );
				
				log.info("= 저장 파일명 =============================");
				log.info( fileInfo.getFileName() );
				log.info("########## END "+fileInfo.getOrgFileName() +" 파일 정보 #############################");
				
			}
		}
		
		
	
	}
	
	public void insertCounselTransferBoardAns(CounselTransferBoardDTO counselTransferBoardDTO) throws Exception{
		
		CsoJobRequstAns csoJobRequstAns = counselTransferBoardDTO.getCsoJobRequstAns();
		csoJobRequstAns.setRequstSn(counselTransferBoardDTO.getCsoJobRequst().getRequstSn());
		csoJobRequstAns.setRegtrId(BOSecurityUtil.getLoginId());
		csoJobRequstAns.setUdterId(BOSecurityUtil.getLoginId());
		csoJobRequstAns.setAnsAdminId(BOSecurityUtil.getLoginId());
		
		counselTransferBoardRepository.insertCsoJobRequstAnsWithGenTurn(csoJobRequstAns);
		
	}

	public void updateCounselTransferBoardAns(CounselTransferBoardDTO counselTransferBoardDTO) throws Exception {

		CsoJobRequstAns csoJobRequstAns = counselTransferBoardDTO.getCsoJobRequstAns();


		csoJobRequstAns.setRequstSn(counselTransferBoardDTO.getCsoJobRequst().getRequstSn());
		csoJobRequstAns.setUdterId(BOSecurityUtil.getLoginId());
		csoJobRequstAns.setAnsAdminId(BOSecurityUtil.getLoginId());

		counselTransferBoardRepository.updateCounseTransferBoardAns(csoJobRequstAns);
	}
	
	public void insertCounselTransferBoardAnsAtch(CounselTransferBoardDTO counselTransferBoardDTO, List<MultipartFile> files) throws Exception{
		
		CsoJobRequstAns csoJobRequstAns = counselTransferBoardDTO.getCsoJobRequstAns();
		
		log.info("- text parameter ----------");
		
		// 전체 업로드 확장자 체크를 한다.
		// excludeExtensions 포함된 데이터는 업로드 할 수 없다.
		String[] excludeExtensions = { "jsp", "php", "exe", "bat"};
		
		//확장자를 체크한다. 불가 확장자이며 NotSupportedUploadFileException 발생시킨다.
		IOService.availableUploadExcludeExtension(files, excludeExtensions);
		
		/* 시스템 파일명을 자동으로 부여하여 업로드한다.
			FileUploadResult  fileUploadResult = getIoService().fileUploadAutoFileName(files, "c:/upload/", excludeExtensions);
		*/
		// 중복된 파일이 존재하면 오버라이트한다.
		String timeStamp = new SimpleDateFormat("yy/MM/dd").format(Calendar.getInstance().getTime());
		String path = timeStamp + "/";
		FileUploadResult fileUploadResult = getIoService().fileUploadAutoFileName(files, getConfigService().getProperty("ncp_web_bo.image.cs.jobrequst.upload.path") + path, excludeExtensions);


		if(fileUploadResult.getFileCnt() > 0){
			
			List<FileUploadInfo> rows = fileUploadResult.getRows();
			
			for (FileUploadInfo fileInfo : rows) {
				
				
				CsoJobRequstAnsAtchmnfl csoJobRequstAnsAtchmnfl = new CsoJobRequstAnsAtchmnfl(); 
				
				csoJobRequstAnsAtchmnfl.setRequstSn(csoJobRequstAns.getRequstSn());
				csoJobRequstAnsAtchmnfl.setJobRequstAnsTurn(csoJobRequstAns.getJobRequstAnsTurn());
				
				log.info("- sn----------");
				log.info(csoJobRequstAns.getRequstSn().toString());

				csoJobRequstAnsAtchmnfl.setRequstAnsAtchFileNm(fileInfo.getOrgFileName() + "." + fileInfo.getExtension());
				csoJobRequstAnsAtchmnfl.setRequstAnsAtchFileUrl(getConfigService().getProperty("ncp_web_bo.image.cs.jobrequst.http.path") + path + fileInfo.getFileName());
				csoJobRequstAnsAtchmnfl.setRequstAnsAtchFileCpcty(new BigDecimal(fileInfo.getFileSize()));

				counselTransferBoardRepository.insertCounselTransferBoardAnsAtchmnflWithGenTurn(csoJobRequstAnsAtchmnfl);

			}
		}
	}

	public void updateCounselTransferBoard(CsoJobRequst csoJobRequst) throws Exception{
	    
		// 업무 이관 수정
		//csoJobRequstEntityRepository.updateCsoJobRequst(csoJobRequst);
		csoJobRequst.setUdterId(BOSecurityUtil.getLoginId());
		counselTransferBoardRepository.updateCounselTransferBoard(csoJobRequst);
	    
    }

}
