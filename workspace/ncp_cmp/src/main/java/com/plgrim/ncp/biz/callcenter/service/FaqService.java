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
import com.plgrim.ncp.base.entities.datasource1.cso.CsoFaqAtchmnfl;
import com.plgrim.ncp.base.entities.datasource1.sys.SysNotiAtchFile;
import com.plgrim.ncp.biz.callcenter.data.FaqDTO;
import com.plgrim.ncp.biz.callcenter.data.FaqGridDTO;
import com.plgrim.ncp.biz.callcenter.repository.FaqRepository;
import com.plgrim.ncp.commons.service.EditorService;
import com.plgrim.ncp.commons.util.BOSecurityUtil;
import com.plgrim.ncp.framework.commons.IOService;
import com.plgrim.ncp.framework.commons.StringService;
import com.plgrim.ncp.framework.data.FileUploadInfo;
import com.plgrim.ncp.framework.data.FileUploadResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

/**
 * CodeViewService Implementation
 * @author Park
 *
 */
@Service
@Slf4j
public class FaqService extends AbstractService {

	/**
	 * 공통코드 조회 Repository
	 */
	@Autowired
	private FaqRepository faqRepository;

	@Autowired
	EditorService editorService;
	
	public void updateFaqGrid(List<FaqGridDTO> updateList) {
		Iterator<FaqGridDTO> iterator = updateList.iterator();
		while(iterator.hasNext()) {
			faqRepository.updateFaqGrid(iterator.next());
		}
	}

	public void insertFaq(FaqDTO faqDTO,List<MultipartFile> files) throws Exception {
		String loginId = BOSecurityUtil.getLoginId();
		faqDTO.setInqireCount(0);
		faqDTO.setSortSeq(1);
		faqDTO.setRegtrId(BOSecurityUtil.getLoginId());
		faqDTO.setUdterId(BOSecurityUtil.getLoginId());
		String commitResourcePath = this.getFaqImageUploadPath(faqDTO.getMallId());// 나중에 여러몰이 나올시 수정필요
		String faqCont=faqDTO.getFaqCont();
		
		if(StringService.contains(faqCont, "uncommited")) {
			faqCont = editorService.commitContentsImagesFromTemp(faqCont, commitResourcePath);
			faqDTO.setFaqCont(faqCont);
		}
		faqRepository.insertFaqWithGenSn(faqDTO);
		
		Long faqSn = faqDTO.getFaqSn();
		
		if(faqDTO.getCsoFaqAtchmnfl() != null && faqDTO.getCsoFaqAtchmnfl().size()  > 0){
			for(CsoFaqAtchmnfl item : faqDTO.getCsoFaqAtchmnfl()){
				CsoFaqAtchmnfl csoFaqAtchmnfl = new CsoFaqAtchmnfl();
				csoFaqAtchmnfl.setFaqSn(faqSn);
				csoFaqAtchmnfl.setFaqAtchFileNm(item.getFaqAtchFileNm());
				csoFaqAtchmnfl.setFaqAtchFileUrl(getConfigService().getProperty("ncp_web_bo.image.cs.faq.http.path")  + item.getFaqAtchFileNm());
				csoFaqAtchmnfl.setRegtrId(loginId);
				csoFaqAtchmnfl.setUdterId(loginId);
				
				faqRepository.insertFaqAtchmnflWithGenTurn(csoFaqAtchmnfl);
			}
			
		}

	}

	public void insertFaqAtchmnfl(FaqDTO faqDTO, List<MultipartFile> files) throws Exception {

		// 전체 업로드 확장자 체크를 한다.
		// excludeExtensions 포함된 데이터는 업로드 할 수 없다.
		String[] excludeExtensions = { "jsp", "php", "exe", "bat"};

		//확장자를 체크한다. 불가 확장자이며 NotSupportedUploadFileException 발생시킨다.
		IOService.availableUploadExcludeExtension(files, excludeExtensions);


			/* 시스템 파일명을 자동으로 부여하여 업로드한다.
				FileUploadResult  fileUploadResult = getIoService().fileUploadAutoFileName(files, "c:/upload/", excludeExtensions);
			*/


		// 중복된 파일이 존재하면 오버라이트한다.
		// 업로드 경로는  properties : 각시스템/local.properties 에서 설정한다.
		// ex ) ncp_base.upload.path=C:\\upload
		String timeStamp = new SimpleDateFormat("yy/MM/dd").format(Calendar.getInstance().getTime());
		String path = timeStamp + "/";
		//FileUploadResult fileUploadResult = getIoService().getUniqueFileName(files, getConfigService().getProperty("ncp_web_bo.image.cs.faq.upload.path") + path, excludeExtensions);
		FileUploadResult fileUploadResult = getIoService().fileUploadAutoFileName(files, getConfigService().getProperty("ncp_web_bo.image.cs.faq.upload.path") + path, excludeExtensions);


		if( fileUploadResult.getFileCnt() > 0){

			List<FileUploadInfo> rows = fileUploadResult.getRows();

			for (FileUploadInfo fileInfo : rows) {

				CsoFaqAtchmnfl csoFaqAtchmnfl = new CsoFaqAtchmnfl();
				csoFaqAtchmnfl.setFaqSn(faqDTO.getFaqSn());

				csoFaqAtchmnfl.setFaqAtchFileNm(fileInfo.getOrgFileName() + "." + fileInfo.getExtension());
				csoFaqAtchmnfl.setFaqAtchFileUrl(getConfigService().getProperty("ncp_web_bo.image.cs.faq.http.path") + path + fileInfo.getFileName());
				csoFaqAtchmnfl.setFaqAtchFileCpcty(new BigDecimal(fileInfo.getFileSize()));

				csoFaqAtchmnfl.setRegtrId(BOSecurityUtil.getLoginId());
				csoFaqAtchmnfl.setUdterId(BOSecurityUtil.getLoginId());

				faqRepository.insertFaqAtchmnflWithGenTurn(csoFaqAtchmnfl);


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

	public void updateFaq(FaqDTO faqDTO) {
		faqDTO.setUdterId(BOSecurityUtil.getLoginId());

		faqRepository.updateFaq(faqDTO);
	}

	public void deleteFaqGrid(List<FaqGridDTO> deleteList) {
		Iterator<FaqGridDTO> iterator = deleteList.iterator();
		while(iterator.hasNext()) {
			faqRepository.deleteFaqGrid(iterator.next());
		}
	}

	public void deleteFaqAtchmnfl(FaqDTO faqDTO) {
		faqRepository.deleteFaqAtchmnfl(faqDTO);
	}
	/**
	 * FAQ 이미지 업로드시 Mall에 따른 업로드 경로 가져오기
	 * 
	 * @param mallId
	 * @return
	 */
	public String getFaqImageUploadPath(String mallId) {
		String webResourcePath = "";
		String s3BuketName = IOService.getBucketName();
		
		if("DXM".equals(mallId)) {
			webResourcePath = s3BuketName + ":" +getConfigService().getProperty("ncp_web_bo.cloud.DXM.bucket.image.folder.path") + "/helpdesk/FAQ/";
		}
		
		return webResourcePath;
	}
}
