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

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.plgrim.ncp.base.abstracts.AbstractService;
import com.plgrim.ncp.base.entities.datasource1.sys.SysBoNotiSite;
import com.plgrim.ncp.base.entities.datasource1.sys.SysBoNotiTgt;
import com.plgrim.ncp.base.entities.datasource1.sys.SysFoNotiMall;
import com.plgrim.ncp.base.entities.datasource1.sys.SysNoti;
import com.plgrim.ncp.base.entities.datasource1.sys.SysNotiAtchFile;
import com.plgrim.ncp.base.entities.datasource1.sys.SysPopupNotiAplTgt;
import com.plgrim.ncp.base.entities.datasource1.sys.SysPopupNotiDspCtgryCnnc;
import com.plgrim.ncp.base.repository.sys.SysBoNotiSiteRepository;
import com.plgrim.ncp.base.repository.sys.SysBoNotiTgtRepository;
import com.plgrim.ncp.base.repository.sys.SysFoNotiMallRepository;
import com.plgrim.ncp.base.repository.sys.SysNotiAtchFileRepository;
import com.plgrim.ncp.base.repository.sys.SysPopupNotiDspCtgryCnncRepository;
import com.plgrim.ncp.commons.data.SysCmmnNotiDTO;
import com.plgrim.ncp.commons.data.SysCmmnNotiDataDTO;
import com.plgrim.ncp.commons.data.SysGrpcoDataDTO;
import com.plgrim.ncp.commons.grid.GridCommand;
import com.plgrim.ncp.commons.repository.SysCmmnNotiRepository;
import com.plgrim.ncp.commons.result.SysCmmnNotiResult;
import com.plgrim.ncp.commons.util.BOSecurityUtil;
import com.plgrim.ncp.framework.cloud.aws.S3FileSystem;
import com.plgrim.ncp.framework.commons.IOService;
import com.plgrim.ncp.framework.commons.StringService;
import com.plgrim.ncp.framework.data.FileUploadInfo;
import com.plgrim.ncp.framework.data.FileUploadResult;

import lombok.extern.slf4j.Slf4j;

/**
 * 공지사항 Service
 * @author ed
 *
 */
@Slf4j
@Service
public class SysCmmnNotiService extends AbstractService {

	@Autowired
    SysCmmnNotiRepository sysCmmnNotiRepository; // 공지사항 DAO
	
	@Autowired
	SysBoNotiSiteRepository sysBoNotiSiteRepository; // BO공지사이트 DAO
	
	@Autowired
	SysBoNotiTgtRepository sysBoNotiTgtRepository; // BO공지대상 DAO
	
	@Autowired
	SysFoNotiMallRepository sysFoNotiMallRepository; // FO공지몰 DAO
	
	@Autowired
	SysNotiAtchFileRepository sysNotiAtchFileRepository; // 첨부파일 DAO
	
	@Autowired
	SysPopupNotiDspCtgryCnncRepository sysPopupNotiDspCtgryCnncRepository; // 팝업공지 전시카테고리 DAO
	
	@Autowired
	EditorService editorService;
	
	@Autowired
	private GridCommand gridCommand;
	
	private final String CONFIG_NOTICE_UPLOAD_FILE_PATH = "ncp_base.image.notice.upload.path";
	private final String CONFIG_NOTICE_UPLOAD_HTTP_PATH = "ncp_base.image.notice.http.path";
	
	/**
	 * 공지사항 목록 조회.
	 *
	 * @param paramData [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public List<SysCmmnNotiResult> selectSysCmmnNotiList( SysCmmnNotiDTO paramData) throws Exception {
		return sysCmmnNotiRepository.selectSysCmmnNotiBoList(paramData); 
	}
	
	/**
	 * 공지사항 목록 조회 개수.
	 *
	 * @param paramData [설명]
	 * @return Long [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public long selectSysCmmnNotiListCount( SysCmmnNotiDTO paramData) throws Exception {
		return sysCmmnNotiRepository.selectSysCmmnNotiBoListCount(paramData); 
	}
	
	/**
	 * 공지사항 목록 조회 엑셀.
	 *
	 * @param paramData [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public List<Map<String, Object>> selectSysCmmnNotiListExcel( SysCmmnNotiDTO paramData) throws Exception {
		return sysCmmnNotiRepository.selectSysCmmnNotiBoListExcel(paramData); 
	}
	
	/**
	 * 공지사항 등록.
	 *
	 * @param paramData [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
    public void insertSysCmmnNoti(SysCmmnNotiDTO paramData) throws Exception {
        String loginId = BOSecurityUtil.getLoginId(); // 로그인ID
        Long notiSn = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        String ymd1 = paramData.getPopupBegDt() + " " + (paramData.getPopupBegDtHour().length() > 1 ? paramData.getPopupBegDtHour() : "0" + paramData.getPopupBegDtHour()) +
                ":" + (paramData.getPopupBegDtMinute().length() > 1 ? paramData.getPopupBegDtMinute() : "0" + paramData.getPopupBegDtMinute()) + ":01";
        String ymd2 = paramData.getPopupEndDt() + " " + (paramData.getPopupEndDtHour().length() > 1 ? paramData.getPopupEndDtHour() : "0" + paramData.getPopupEndDtHour()) +
                ":" + (paramData.getPopupEndDtMinute().length() > 1 ? paramData.getPopupEndDtMinute() : "0" + paramData.getPopupEndDtMinute()) + ":59";

        paramData.getSysNoti().setRegtrId(loginId); // 등록자
        paramData.getSysNoti().setUdterId(loginId); // 수정자
        paramData.getSysNoti().setRegAdminId(loginId); // 등록관리자
        //paramData.getSysNoti().setPopupBegDt(getDateService().createDate(paramData.getPopupBegDt(), "yyyy-MM-dd")); // 공지시작일
        //paramData.getSysNoti().setPopupEndDt(getDateService().createDate(paramData.getPopupEndDt(), "yyyy-MM-dd")); // 공지종료일
        paramData.getSysNoti().setPopupBegDt(sdf.parse(paramData.getPopupBegDt())); // 공지시작일
        paramData.getSysNoti().setPopupEndDt(sdf.parse(paramData.getPopupEndDt())); // 공지종료일
        paramData.setPopupBegDt2(ymd1);
        paramData.setPopupEndDt2(ymd2);
        String commitResourcePath = "";
        //이미지 무브를 위한 패스 얻어오기
		/*if(paramData.getBoSiteIds().get(0) !="BO" && ){
			if( paramData.getMallIds().size() > 0){
				 commitResourcePath = this.getNoticeImageUploadPath(paramData.getMallIds().get(0));
			}else{
				String paramMallIds = "BO";
				 commitResourcePath = this.getNoticeImageUploadPath(paramMallIds);
			}
		}else{
			String paramMallIds = "BO";
			 commitResourcePath = this.getNoticeImageUploadPath(paramMallIds);
		}*/
        if (paramData.getBoSiteIds().size() > 0) {
            String paramMallIds = "BO";
            commitResourcePath = this.getNoticeImageUploadPath(paramMallIds);

        } else {
            commitResourcePath = this.getNoticeImageUploadPath(paramData.getMallIds().get(0));
        }

        String pcNotiCont = paramData.getSysNoti().getPcNotiCont();
        String mobileNotiCont = paramData.getSysNoti().getMobileNotiCont();
//  		이미지 무브
        if (StringService.contains(pcNotiCont, "uncommited")) {
            pcNotiCont = editorService.commitContentsImagesFromTemp(pcNotiCont, commitResourcePath);
            paramData.getSysNoti().setPcNotiCont(pcNotiCont);
        }
        if (StringService.contains(mobileNotiCont, "uncommited")) {
            mobileNotiCont = editorService.commitContentsImagesFromTemp(mobileNotiCont, commitResourcePath);
            paramData.getSysNoti().setMobileNotiCont(mobileNotiCont);
        }


        // PC/MOBILE 통합사용일 경우 모바일쪽 초기화
        if ("PC_MOBILE_UNITY_USE".equals(paramData.getSysNoti().getNotiContUseSectCd())) {
            paramData.getSysNoti().setMobileNotiCont("");
        }

        if ("BO_NOTI".equals(paramData.getSysNoti().getNotiSectCd()) || "PO_NOTI".equals(paramData.getSysNoti().getNotiSectCd())) {
            // BO 등록

            paramData.getSysNoti().setNotiTpCd(null); // FO 공지유형 초기화
            paramData.getSysNoti().setUpendFixYn("N"); // 게시상단노출 초기화
            paramData.getSysNoti().setSortSeq(null); // 상단노출순서 초기화

            sysCmmnNotiRepository.insertSysNoti(paramData); // 시스템공지 등록

            notiSn = paramData.getSysNoti().getNotiSn(); // 공지일련번호

            if (paramData.getBoSiteIds() != null) {
                for (String siteId : paramData.getBoSiteIds()) {
                    SysBoNotiSite sysBoNotiSite = new SysBoNotiSite();

                    sysBoNotiSite.setNotiSn(notiSn); // 공지번호
                    sysBoNotiSite.setBoSiteId(siteId); // 사이트ID
                    sysBoNotiSite.setRegtrId(loginId); // 등록자
                    sysBoNotiSite.setUdterId(loginId); // 수정자

                    sysBoNotiSiteRepository.insertSysBoNotiSite(sysBoNotiSite); // BO SITE 등록
                }
            }

            if (paramData.getAdminTpCds() != null) {
                for (String adminTpCd : paramData.getAdminTpCds()) {
                    SysBoNotiTgt sysBoNotiTgt = new SysBoNotiTgt();

                    sysBoNotiTgt.setNotiSn(notiSn); // 공지번호
                    sysBoNotiTgt.setAdminTpCd(adminTpCd); // 관리자유형코드
                    sysBoNotiTgt.setRegtrId(loginId); // 등록자
                    sysBoNotiTgt.setUdterId(loginId); // 수정자

                    sysBoNotiTgtRepository.insertSysBoNotiTgt(sysBoNotiTgt); // BO공지대상 등록
                }
            }
        } else {
            // FO 등록

            if (StringUtils.isEmpty(paramData.getSysNoti().getUpendFixYn())) {
                paramData.getSysNoti().setUpendFixYn("N"); // 체크되어 있지 않으면 N으로 설정
            }

            sysCmmnNotiRepository.insertSysNoti(paramData); // 공지사항 등록

            notiSn = paramData.getSysNoti().getNotiSn(); // 공지일련번호

            if (paramData.getMallIds() != null) {
                for (String mallId : paramData.getMallIds()) {
                    SysFoNotiMall sysFoNotiMall = new SysFoNotiMall();

                    sysFoNotiMall.setNotiSn(notiSn); // 공지번호
                    sysFoNotiMall.setMallId(mallId); // 관리자유형코드
                    sysFoNotiMall.setRegtrId(loginId); // 등록자
                    sysFoNotiMall.setUdterId(loginId); // 수정자

                    sysFoNotiMallRepository.insertSysFoNotiMall(sysFoNotiMall); // FO공지몰 등록
                }
            }
        }






        /*추후 사용 할수 있음*/
		/*// 첨부파일 저장
		if(paramData.getUploadFiles() != null && paramData.getUploadFiles().size() > 0) {
			FileUploadResult fileUploadResult = uploadFiles(paramData.getUploadFiles()); // 첨부파일 업로드
			
			if(fileUploadResult != null && fileUploadResult.getFileCnt() > 0) {
				for(FileUploadInfo item : fileUploadResult.getRows()) {
					SysNotiAtchFile sysNotiAtchFile = new SysNotiAtchFile();
					
					sysNotiAtchFile.setNotiSn(notiSn); // 공지번호
					sysNotiAtchFile.setNotiAtchFileNm(item.getOrgFileName() + "." + item.getExtension()); // 원본파일명
					sysNotiAtchFile.setNotiAtchFileUrl(getConfigService().getProperty(CONFIG_NOTICE_UPLOAD_HTTP_PATH) + "/" + item.getFileName()); // 파일URL
					sysNotiAtchFile.setNotiAtchFileCpcty(new BigDecimal(item.getFileSize())); // 파일크기
					sysNotiAtchFile.setRegtrId(loginId); // 등록자
					sysNotiAtchFile.setUdterId(loginId); // 수정자
					
					sysCmmnNotiRepository.insertSysNotiAtchFile(sysNotiAtchFile); // 첨부파일 저장
				}
			}
		}*/

        if (paramData.getSysNotiAtchFileList() != null && paramData.getSysNotiAtchFileList().size() > 0) {
            for (SysNotiAtchFile item : paramData.getSysNotiAtchFileList()) {
                SysNotiAtchFile sysNotiAtchFile = new SysNotiAtchFile();

                sysNotiAtchFile.setNotiSn(notiSn); // 공지번호
                sysNotiAtchFile.setNotiAtchFileNm(item.getNotiAtchFileNm()); // 원본파일명
                sysNotiAtchFile.setNotiAtchFileUrl(getConfigService().getProperty(CONFIG_NOTICE_UPLOAD_HTTP_PATH) + "/" + item.getNotiAtchFileNm()); // 파일URL
                //sysNotiAtchFile.setNotiAtchFileCpcty(new BigDecimal(item.getFileSize())); // 파일크기
                sysNotiAtchFile.setRegtrId(loginId); // 등록자
                sysNotiAtchFile.setUdterId(loginId); // 수정자

                sysCmmnNotiRepository.insertSysNotiAtchFile(sysNotiAtchFile); // 첨부파일 저장
            }
        }
    }
	
	/**
	 * 공지사항 상세 조회.
	 *
	 * @param paramData [설명]
	 * @return Sys cmmn noti result [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public SysCmmnNotiResult selectSysCmmnNotiDetail( SysCmmnNotiDTO paramData) throws Exception {
		return sysCmmnNotiRepository.selectSysCmmnNotiBoDetail(paramData); 
	}
	
	/**
	 * 공지사항 첨부파일 1개
	 *
	 * @param paramData [설명]
	 * @return Sys cmmn noti result [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public SysCmmnNotiResult selectSysCmmnNotiFile( SysCmmnNotiDTO paramData) throws Exception {
		return sysCmmnNotiRepository.selectSysCmmnNotiBoFile(paramData);
	}


	/**
	 * 공지사항 이전글 다음글 조회.
	 *
	 * @param paramData [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 11. 6
	 */
	public List<SysNoti> selectSysPrevNxtNotice(SysCmmnNotiDTO paramData) throws Exception{
		return sysCmmnNotiRepository.selectSysPrevNxtNotice(paramData);
	}

	/**
	 * 공지사항 수정.
	 *
	 * @param paramData [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
    public void updateSysCmmnNoti(SysCmmnNotiDTO paramData) throws Exception {
        String loginId = BOSecurityUtil.getLoginId(); // 로그인ID
        Long notiSn = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        String ymd1 = paramData.getPopupBegDt() + " " + (paramData.getPopupBegDtHour().length() > 1 ? paramData.getPopupBegDtHour() : "0" + paramData.getPopupBegDtHour()) +
                ":" + (paramData.getPopupBegDtMinute().length() > 1 ? paramData.getPopupBegDtMinute() : "0" + paramData.getPopupBegDtMinute()) + ":01";
        String ymd2 = paramData.getPopupEndDt() + " " + (paramData.getPopupEndDtHour().length() > 1 ? paramData.getPopupEndDtHour() : "0" + paramData.getPopupEndDtHour()) +
                ":" + (paramData.getPopupEndDtMinute().length() > 1 ? paramData.getPopupEndDtMinute() : "0" + paramData.getPopupEndDtMinute()) + ":59";

        paramData.getSysNoti().setUdterId(loginId); // 수정자
        //paramData.getSysNoti().setPopupBegDt(getDateService().createDate(paramData.getPopupBegDt(), "yyyy-MM-dd")); // 공지시작일
        //paramData.getSysNoti().setPopupEndDt(getDateService().createDate(paramData.getPopupEndDt(), "yyyy-MM-dd")); // 공지종료일
        paramData.getSysNoti().setPopupBegDt(sdf.parse(paramData.getPopupBegDt())); // 공지시작일
        paramData.getSysNoti().setPopupEndDt(sdf.parse(paramData.getPopupEndDt())); // 공지종료일
        paramData.setPopupBegDt2(ymd1);
        paramData.setPopupEndDt2(ymd2);
        paramData.setLangCd(paramData.getLangCd()); // 언어구분

        // PC/MOBILE 통합사용일 경우 모바일쪽 초기화
        if ("PC_MOBILE_UNITY_USE".equals(paramData.getSysNoti().getNotiContUseSectCd())) {
            paramData.getSysNoti().setMobileNotiCont("");
        }

        if ("BO_NOTI".equals(paramData.getSysNoti().getNotiSectCd()) || "PO_NOTI".equals(paramData.getSysNoti().getNotiSectCd())) {
            // BO 수정

            sysCmmnNotiRepository.updateSysNoti(paramData); // 공지사항 수정
            sysCmmnNotiRepository.deleteSysBoNotiSiteAll(paramData); // BO공지사이트 삭제

            notiSn = paramData.getSysNoti().getNotiSn(); // 공지일련번호

            if (paramData.getBoSiteIds() != null) {
                for (String siteId : paramData.getBoSiteIds()) {
                    SysBoNotiSite sysBoNotiSite = new SysBoNotiSite();

                    sysBoNotiSite.setNotiSn(notiSn); // 공지번호
                    sysBoNotiSite.setBoSiteId(siteId); // 사이트ID
                    sysBoNotiSite.setRegtrId(loginId); // 등록자
                    sysBoNotiSite.setUdterId(loginId); // 수정자

                    sysBoNotiSiteRepository.insertSysBoNotiSite(sysBoNotiSite); // BO SITE 등록
                }
            }

            sysCmmnNotiRepository.deleteSysBoNotiTgtAll(paramData); // BO공지대상 삭제

            if (paramData.getAdminTpCds() != null) {
                for (String adminTpCd : paramData.getAdminTpCds()) {
                    SysBoNotiTgt sysBoNotiTgt = new SysBoNotiTgt();

                    sysBoNotiTgt.setNotiSn(notiSn); // 공지번호
                    sysBoNotiTgt.setAdminTpCd(adminTpCd); // 관리자유형코드
                    sysBoNotiTgt.setRegtrId(loginId); // 등록자
                    sysBoNotiTgt.setUdterId(loginId); // 수정자

                    sysBoNotiTgtRepository.insertSysBoNotiTgt(sysBoNotiTgt); // BO공지대상 등록
                }
            }
        } else {
            // FO 수정

            if (StringUtils.isEmpty(paramData.getSysNoti().getUpendFixYn())) {
                paramData.getSysNoti().setUpendFixYn("N"); // 체크되어 있지 않으면 N으로 설정
            }

            sysCmmnNotiRepository.updateSysNoti(paramData); // 공지사항 수정
            sysCmmnNotiRepository.deleteSysFoNotiMallAll(paramData); // FO공지몰 삭제

            notiSn = paramData.getSysNoti().getNotiSn(); // 공지일련번호

            if (paramData.getMallIds() != null) {
                for (String mallId : paramData.getMallIds()) {
                    SysFoNotiMall sysFoNotiMall = new SysFoNotiMall();

                    sysFoNotiMall.setNotiSn(notiSn); // 공지번호
                    sysFoNotiMall.setMallId(mallId); // 관리자유형코드
                    sysFoNotiMall.setRegtrId(loginId); // 등록자
                    sysFoNotiMall.setUdterId(loginId); // 수정자

                    sysFoNotiMallRepository.insertSysFoNotiMall(sysFoNotiMall); // FO공지몰 등록
                }
            }
        }

        if (paramData.getDelSysNotiAtchFileList() != null) {
            // 첨부파일 삭제

            for (SysNotiAtchFile file : paramData.getDelSysNotiAtchFileList()) {
                sysNotiAtchFileRepository.deleteSysNotiAtchFile(file); // 첨부파일 삭제
            }
        }

        // 첨부파일 저장
//		if(paramData.getUploadFiles() != null && paramData.getUploadFiles().size() > 0) {
//			FileUploadResult fileUploadResult = uploadFiles(paramData.getUploadFiles()); // 첨부파일 업로드
//
//			if(fileUploadResult != null && fileUploadResult.getFileCnt() > 0) {
//				for(FileUploadInfo item : fileUploadResult.getRows()) {
//					SysNotiAtchFile sysNotiAtchFile = new SysNotiAtchFile();
//
//					sysNotiAtchFile.setNotiSn(notiSn); // 공지번호
//					sysNotiAtchFile.setNotiAtchFileNm(item.getOrgFileName() + "." + item.getExtension()); // 원본파일명
//					sysNotiAtchFile.setNotiAtchFileUrl(getConfigService().getProperty(CONFIG_NOTICE_UPLOAD_HTTP_PATH) + "/" + item.getFileName()); // 파일URL
//					sysNotiAtchFile.setNotiAtchFileCpcty(new BigDecimal(item.getFileSize())); // 파일크기
//					sysNotiAtchFile.setRegtrId(loginId); // 등록자
//					sysNotiAtchFile.setUdterId(loginId); // 수정자
//
//					sysCmmnNotiRepository.insertSysNotiAtchFile(sysNotiAtchFile); // 첨부파일 저장
//				}
//			}
//		}

        if (paramData.getSysNotiAtchFileList() != null && paramData.getSysNotiAtchFileList().size() > 0) {
            for (SysNotiAtchFile item : paramData.getSysNotiAtchFileList()) {
                SysNotiAtchFile sysNotiAtchFile = new SysNotiAtchFile();

                sysNotiAtchFile.setNotiSn(notiSn); // 공지번호
                sysNotiAtchFile.setNotiAtchFileNm(item.getNotiAtchFileNm()); // 원본파일명
                sysNotiAtchFile.setNotiAtchFileUrl(getConfigService().getProperty(CONFIG_NOTICE_UPLOAD_HTTP_PATH) + "/" + item.getNotiAtchFileNm()); // 파일URL
                //sysNotiAtchFile.setNotiAtchFileCpcty(new BigDecimal(item.getFileSize())); // 파일크기
                sysNotiAtchFile.setRegtrId(loginId); // 등록자
                sysNotiAtchFile.setUdterId(loginId); // 수정자

                sysCmmnNotiRepository.insertSysNotiAtchFile(sysNotiAtchFile); // 첨부파일 저장
            }
        }
    }
	
	/**
	 * 공지사항 그리드 수정.
	 *
	 * @param paramData [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public void updateSysNotiGrid( List<SysCmmnNotiDataDTO> paramData) throws Exception {
		String loginId = BOSecurityUtil.getLoginId(); // 로그인ID
		
		if(paramData != null) {
			for(SysCmmnNotiDataDTO item : paramData) {
				item.getSysNoti().setUdterId(loginId);
				sysCmmnNotiRepository.updateSysNotiGrid(item.getSysNoti());
			}
		}
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
		/*
		List<PopupNotiAplTgtDataDTO> tgtList = null; // 공지대상
		List<SysPopupNotiDspCtgryCnncDataDTO> dspList = null; // 전시카테고리
		int i, j;
		String type, cdNm;
		String[] arrNm = new String[]{"","",""};
		
		List<SysCmmnNotiResult> resultList = sysCmmnNotiRepository.selectSysPopupNotiList(paramData); // 데이터로딩 
		
		// 몰, 디바이스, 전시영역, 전시대상 문자열 합침
		if(resultList != null) {
			for(i = 0; i < resultList.size(); i++) {
				tgtList = resultList.get(i).getPopupNotiAplTgt();
				dspList = resultList.get(i).getSysPopupNotiDspCtgryCnnc();
				
				if(tgtList != null) {
					for(j = 0; j < tgtList.size(); j++) {
						type = tgtList.get(j).getPopupNotiTgtTpCd(); // 코드타입
						cdNm = tgtList.get(j).getCdNm(); // 코드명
						System.out.println("============================type : " + type + " cdNm : " + cdNm);
						if("MALL_ID".equals(type)) {
							// 몰일 경우
							arrNm[0] = arrNm[0] + cdNm + ",";
						} else if("DVC".equals(type)) {
							// 디바이스일 경우
							arrNm[1] = arrNm[1] + cdNm + ",";
						} else if("TGT_MBR_ATRB".equals(type)) {
							// 회원 속성일 경우
							arrNm[2] = arrNm[2] + cdNm + ",";
						} else if("TGT_MBR_TP".equals(type)) {
							// 회원 유형일 경우
							arrNm[2] = arrNm[2] + cdNm + ",";
						}
					}
					
					// 끝에 "," 제거
					for(j = 0; j < arrNm.length; j++) {
						if(arrNm[j].length() > 0) {
							// 빈값이 아닐 경우
							arrNm[j] = arrNm[j].substring(0, arrNm[j].length()-1);
						}
						System.out.println("============================arrNm : " + arrNm[j]);
					}
					
					// 저장
					resultList.get(i).getSysPopupNoti().setMall(arrNm[0]);
					resultList.get(i).getSysPopupNoti().setDvc(arrNm[1]);
					resultList.get(i).getSysPopupNoti().setMbr(arrNm[2]);
				}
				
				cdNm = "";
				
				// 전시영역
				if(dspList != null) {
					for(j = 0; j < dspList.size(); j++) {
						cdNm = cdNm + dspList.get(j).getDspCtgryNm() + ",";
					}
					
					String tmp = cdNm.substring(0, cdNm.length()-1); // "," 제거한 문자열
					if(tmp.length() > 20) {
						// 20자 이상시 글 줄임 표시
						tmp = tmp.substring(0, 20) + "...";
					}
					resultList.get(i).getSysPopupNoti().setDsp(tmp);
					System.out.println("============================cdNm : " + resultList.get(i).getSysPopupNoti().getDsp());
				}
			}
		}
		*/
		return sysCmmnNotiRepository.selectSysPopupNotiList(paramData);
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
		return sysCmmnNotiRepository.selectSysPopupNotiListCount(paramData); 
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
		return sysCmmnNotiRepository.selectSysPopupNotiListExcel(paramData); 
	}
	
	/**
	 * 팝업공지 등록.
	 *
	 * @param paramData [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public void insertSysPopupNoti( SysCmmnNotiDTO paramData) throws Exception {
		String loginId = BOSecurityUtil.getLoginId(); // 로그인ID
		Long popupNotiSn = null;
		
		// 형식별 필요하지 않은 데이터 초기화
		if("HTML".equals(paramData.getFormData().getSysPopupNoti().getNotiContSectCd())) {
			// HTML일 경우
			
			paramData.getFormData().getSysPopupNoti().setLinkUrl(""); // 링크URL
			paramData.getFormData().getSysPopupNoti().setLinkOutptTgtCd("NEW_WIN"); // 링크타입
			paramData.getFormData().getSysPopupNoti().setMobilePopupMenmthdCd("GNRL_POPUP"); // 모바일 팝업방식
		} else if("IMG_BANNER".equals(paramData.getFormData().getSysPopupNoti().getNotiContSectCd())) {
			// 이미지배너일 경우
			
			paramData.getFormData().getSysPopupNoti().setNotiCont(""); // 내용
		}
		
		// 이미지맵, 배너일 경우 팝업닫기 기본값 설정
		if(StringUtils.isEmpty(paramData.getFormData().getSysPopupNoti().getTodayStpViewConfigYn())) {
			paramData.getFormData().getSysPopupNoti().setTodayStpViewConfigYn("N"); // 오늘그만보기 
		}
		if(StringUtils.isEmpty(paramData.getFormData().getSysPopupNoti().getClseConfigYn())) {
			paramData.getFormData().getSysPopupNoti().setClseConfigYn("N"); // 닫기 
		}
		
		String dspBegDt = paramData.getFormData().getDspBegDtDay() + " " + paramData.getFormData().getDspBegDtHour() + ":" + paramData.getFormData().getDspBegDtMinute(); // 전시시작일
		String dspEndDt = paramData.getFormData().getDspEndDtDay() + " " + paramData.getFormData().getDspEndDtHour() + ":" + paramData.getFormData().getDspEndDtMinute(); // 전시종료일
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		
		paramData.getFormData().getSysPopupNoti().setRegtrId(loginId); // 등록자
		paramData.getFormData().getSysPopupNoti().setUdterId(loginId); // 수정자
		paramData.getFormData().getSysPopupNoti().setRegAdminId(loginId); // 등록관리자
		//paramData.getFormData().getSysPopupNoti().setDspBegDt(getDateService().createDate(dspBegDt, "yyyy-MM-dd HH:mm")); // 전시시작일
		//paramData.getFormData().getSysPopupNoti().setDspEndDt(getDateService().createDate(dspEndDt, "yyyy-MM-dd HH:mm")); // 전시종료일
		paramData.getFormData().getSysPopupNoti().setDspBegDt(sdf.parse(dspBegDt)); // 전시시작일
		paramData.getFormData().getSysPopupNoti().setDspEndDt(sdf.parse(dspEndDt)); // 전시종료일
		paramData.getFormData().getSysPopupNoti().setDspBegHour(paramData.getFormData().getDspTimeBegHour() + paramData.getFormData().getDspTimeBegMinute()); // 시간반복시작
		paramData.getFormData().getSysPopupNoti().setDspEndHour(paramData.getFormData().getDspTimeEndHour() + paramData.getFormData().getDspTimeEndMinute()); // 시간반복종료
		
		sysCmmnNotiRepository.insertSysPopupNoti(paramData.getFormData().getSysPopupNoti()); // 팝업공지 등록
		
		popupNotiSn = paramData.getFormData().getSysPopupNoti().getPopupNotiSn(); // 팝업공지일련번호
		
		// 공지 적용대상 저장
		// 몰
		if(paramData.getFormData().getMallIds() != null) {
			for(String item : paramData.getFormData().getMallIds()) {
				insertSysPopupNotiAplTgt(popupNotiSn, "MALL_ID", item, null, loginId);
			}
		}
		
		// 디바이스구분
		if(paramData.getFormData().getDvcCds() != null) {
			for(String item : paramData.getFormData().getDvcCds()) {
				insertSysPopupNotiAplTgt(popupNotiSn, "DVC", item, null, loginId);
			}
		}
		
		// 언어 코드
		if(paramData.getFormData().getLangCds() != null) {
			for(String item : paramData.getFormData().getLangCds()) {
				insertSysPopupNotiAplTgt(popupNotiSn, "LANG", item, null, loginId);
			}
		}
		
		// 회원유형
		if(paramData.getFormData().getTgtMbrTps() != null) {
			for(String item : paramData.getFormData().getTgtMbrTps()) {
				insertSysPopupNotiAplTgt(popupNotiSn, "TGT_MBR_TP", item, null, loginId);
			}
		}
		
		// 회원속성
		if(paramData.getFormData().getTgtMbrAtrbs() != null) {
			for(String item : paramData.getFormData().getTgtMbrAtrbs()) {
				// 그룹사가 아닐 경우
				if(!"GRPCO".equals(item)) {
					insertSysPopupNotiAplTgt(popupNotiSn, "TGT_MBR_ATRB", item, null, loginId);
				}
			}
		}
		
		// 그룹사
		if(!StringUtils.isEmpty(paramData.getFormData().getGrpco())) {
			if("GRPCO_ALL".equals(paramData.getFormData().getGrpco())) {
				// 전체일 경우
				insertSysPopupNotiAplTgt(popupNotiSn, "TGT_MBR_ATRB", paramData.getFormData().getGrpco(), null, loginId);
			} else {
				// 개별일 경우
				List<SysGrpcoDataDTO> cList = gridCommand.getCreateList(paramData.getGridList());
				
				if(cList != null) {
					for(SysGrpcoDataDTO item : cList) {
						insertSysPopupNotiAplTgt(popupNotiSn, "TGT_MBR_ATRB", paramData.getFormData().getGrpco(), item.getSysGrpco().getGrpcoId(), loginId);
					}
				}
			}
		}
		
		// 전시카테고리 저장
		// 메인
		if(paramData.getFormData().getMainDsps() != null) {
			for(String item : paramData.getFormData().getMainDsps()) {
				insertSysPopupNotiDspCtgryCnnc(popupNotiSn, item, loginId);
			}
		}
		
		// 카테고리
		if(paramData.getFormData().getCtgryDsps() != null) {
			for(String item : paramData.getFormData().getCtgryDsps()) {
				insertSysPopupNotiDspCtgryCnnc(popupNotiSn, item, loginId);
			}
		}
					
		// 브랜드
		if(paramData.getFormData().getBrndDsps() != null) {
			for(String item : paramData.getFormData().getBrndDsps()) {
				insertSysPopupNotiDspCtgryCnnc(popupNotiSn, item, loginId);
			}
		}
	}
	
	/**
	 * 팝업공지대상 등록.
	 *
	 * @param sn [공지번호]
	 * @param type [공지대상유형]
	 * @param value [공지대상]
	 * @param id [로그인ID]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public void insertSysPopupNotiAplTgt(Long sn, String type, String typeValue, String grpcoId, String id) throws Exception {
		SysPopupNotiAplTgt tmp = new SysPopupNotiAplTgt();
		
		tmp.setPopupNotiSn(sn); // 팝업공지번호
		tmp.setPopupNotiTgtTpCd(type); // 공지대상유형
		tmp.setRegtrId(id); // 등록자
		tmp.setUdterId(id); // 수정자
		
		if("MALL_ID".equals(type)) {
			// 몰일 경우
			tmp.setMallId(typeValue);
		} else if("DVC".equals(type)) {
			// 디바이스일 경우
			tmp.setDvcCd(typeValue);
		} else if("LANG".equals(type)) {
			// 언어일 경우
			tmp.setLangCd(typeValue);
		} else if("TGT_MBR_TP".equals(type)) {
			// 회원유형일 경우
			tmp.setTgtMbrTpCd(typeValue);
		} else {
			// 회원속성일 경우
			tmp.setTgtMbrAtrbCd(typeValue);
			
			// 개별일 경우
			if("GRPCO_IND".equals(typeValue)) {
				tmp.setGrpcoId(grpcoId);
			}
		}
		
		sysCmmnNotiRepository.insertSysPopupNotiAplTgt(tmp); // 저장
	}
	
	/**
	 * 팝업공지카테고리 등록.
	 *
	 * @param sn [공지번호]
	 * @param value [카테고리]
	 * @param id [로그인ID]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public void insertSysPopupNotiDspCtgryCnnc(Long sn, String value, String id) throws Exception {
		SysPopupNotiDspCtgryCnnc tmp = new SysPopupNotiDspCtgryCnnc();
		
		tmp.setPopupNotiSn(sn); // 팝업공지번호
		tmp.setDspCtgryNo(value); // 카테고리번호
		tmp.setRegtrId(id); // 등록자
		tmp.setUdterId(id); // 수정자
		
		sysPopupNotiDspCtgryCnncRepository.insertSysPopupNotiDspCtgryCnnc(tmp);
	}
	
	/**
	 * 팝업공지 상세 조회.
	 *
	 * @param paramData [설명]
	 * @return Sys cmmn noti result [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public SysCmmnNotiResult selectSysPopupNotiDetail( SysCmmnNotiDTO paramData) throws Exception {
		return sysCmmnNotiRepository.selectSysPopupNotiDetail(paramData); 
	}
	
	/**
	 * 팝업공지 수정.
	 *
	 * @param paramData [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public void updateSysPopupNoti( SysCmmnNotiDTO paramData) throws Exception {
		String loginId = BOSecurityUtil.getLoginId(); // 로그인ID
		Long popupNotiSn = paramData.getFormData().getSysPopupNoti().getPopupNotiSn(); // 팝업공지일련번호
		
		// 이미지맵, 배너일 경우 팝업닫기 기본값 설정
		if(StringUtils.isEmpty(paramData.getFormData().getSysPopupNoti().getTodayStpViewConfigYn())) {
			paramData.getFormData().getSysPopupNoti().setTodayStpViewConfigYn("N"); // 오늘그만보기 
		}
		if(StringUtils.isEmpty(paramData.getFormData().getSysPopupNoti().getClseConfigYn())) {
			paramData.getFormData().getSysPopupNoti().setClseConfigYn("N"); // 닫기 
		}
		
		String dspBegDt = paramData.getFormData().getDspBegDtDay() + " " + paramData.getFormData().getDspBegDtHour() + ":" + paramData.getFormData().getDspBegDtMinute(); // 전시시작일
		String dspEndDt = paramData.getFormData().getDspEndDtDay() + " " + paramData.getFormData().getDspEndDtHour() + ":" + paramData.getFormData().getDspEndDtMinute(); // 전시종료일
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		
		paramData.getFormData().getSysPopupNoti().setUdterId(loginId); // 수정자
		//paramData.getFormData().getSysPopupNoti().setDspBegDt(getDateService().createDate(dspBegDt, "yyyy-MM-dd HH:mm")); // 전시시작일
		//paramData.getFormData().getSysPopupNoti().setDspEndDt(getDateService().createDate(dspEndDt, "yyyy-MM-dd HH:mm")); // 전시종료일
		paramData.getFormData().getSysPopupNoti().setDspBegDt(sdf.parse(dspBegDt)); // 전시시작일
		paramData.getFormData().getSysPopupNoti().setDspEndDt(sdf.parse(dspEndDt)); // 전시종료일
		paramData.getFormData().getSysPopupNoti().setDspBegHour(paramData.getFormData().getDspTimeBegHour() + paramData.getFormData().getDspTimeBegMinute()); // 시간반복시작
		paramData.getFormData().getSysPopupNoti().setDspEndHour(paramData.getFormData().getDspTimeEndHour() + paramData.getFormData().getDspTimeEndMinute()); // 시간반복종료
		
		sysCmmnNotiRepository.updateSysPopupNoti(paramData.getFormData()); // 팝업공지 수정
		
		// 공지 적용대상 삭제 후 추가
		sysCmmnNotiRepository.deleteSysPopupNotiAplTgtAll(paramData); // 모든 공지 적용대상 삭제
			
		// 몰
		if(paramData.getFormData().getMallIds() != null) {
			for(String item : paramData.getFormData().getMallIds()) {
				insertSysPopupNotiAplTgt(popupNotiSn, "MALL_ID", item, null, loginId);
			}
		}
		
		// 디바이스구분
		if(paramData.getFormData().getDvcCds() != null) {
			for(String item : paramData.getFormData().getDvcCds()) {
				insertSysPopupNotiAplTgt(popupNotiSn, "DVC", item, null, loginId);
			}
		}
		
		// 언어 코드
		if(paramData.getFormData().getLangCds() != null) {
			for(String item : paramData.getFormData().getLangCds()) {
				insertSysPopupNotiAplTgt(popupNotiSn, "LANG", item, null, loginId);
			}
		}
				
		// 회원유형
		if(paramData.getFormData().getTgtMbrTps() != null) {
			for(String item : paramData.getFormData().getTgtMbrTps()) {
				insertSysPopupNotiAplTgt(popupNotiSn, "TGT_MBR_TP", item, null, loginId);
			}
		}
		
		// 회원속성
		if(paramData.getFormData().getTgtMbrAtrbs() != null) {
			for(String item : paramData.getFormData().getTgtMbrAtrbs()) {
				// 그룹사가 아닐 경우
				if(!"GRPCO".equals(item)) {
					insertSysPopupNotiAplTgt(popupNotiSn, "TGT_MBR_ATRB", item, null, loginId);
				}
			}
		}
		
		// 그룹사
		if(!StringUtils.isEmpty(paramData.getFormData().getGrpco())) {
			if("GRPCO_ALL".equals(paramData.getFormData().getGrpco())) {
				// 전체일 경우
				insertSysPopupNotiAplTgt(popupNotiSn, "TGT_MBR_ATRB", paramData.getFormData().getGrpco(), null, loginId);
			} else {
				// 개별일 경우
				List<SysGrpcoDataDTO> cList = paramData.getGridList();
				
				if(cList != null) {
					for(SysGrpcoDataDTO item : cList) {
						// 기존 데이터 모두 삭제하므로 수정플래그일 경우에도 등록처리
						if("C".equals(item.getGridCudTag()) || "U".equals(item.getGridCudTag())) {
							insertSysPopupNotiAplTgt(popupNotiSn, "TGT_MBR_ATRB", paramData.getFormData().getGrpco(), item.getSysGrpco().getGrpcoId(), loginId);
						}
					}
				}
			}
		}
		
		// 전시카테고리 삭제 후 추가
		sysCmmnNotiRepository.deleteSysPopupNotiDspCtgryCnncAll(paramData); // 모든 공지 적용대상 삭제
		
		// 메인
		if(paramData.getFormData().getMainDsps() != null) {
			for(String item : paramData.getFormData().getMainDsps()) {
				insertSysPopupNotiDspCtgryCnnc(popupNotiSn, item, loginId);
			}
		}
		
		// 카테고리
		if(paramData.getFormData().getCtgryDsps() != null) {
			for(String item : paramData.getFormData().getCtgryDsps()) {
				insertSysPopupNotiDspCtgryCnnc(popupNotiSn, item, loginId);
			}
		}
					
		// 브랜드
		if(paramData.getFormData().getBrndDsps() != null) {
			for(String item : paramData.getFormData().getBrndDsps()) {
				insertSysPopupNotiDspCtgryCnnc(popupNotiSn, item, loginId);
			}
		}
	}
	
	/**
	 *  팝업공지 그룹사 목록 조회.
	 *
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public List<SysCmmnNotiResult> selectSysPopNotiGrpcoList(Long paramData) throws Exception {
		return sysCmmnNotiRepository.selectSysPopNotiGrpcoList(paramData); 
	}
	
	/**
	 * 그룹사 목록 조회.
	 *
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public List<SysCmmnNotiResult> selectSysGrpcoList(SysCmmnNotiDTO paramData) throws Exception {
		return sysCmmnNotiRepository.selectSysGrpcoList(paramData); 
	}
	
	/**
	 * 그룹사 목록 조회 건수.
	 *
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public long selectSysGrpcoListCount(SysCmmnNotiDTO paramData) {
		return sysCmmnNotiRepository.selectSysGrpcoListCount(paramData);
    }

	/**
	 * 공지사항 메인 목록 조회.
	 *
	 * @param paramData [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public List<SysCmmnNotiResult> selectMainSysCmmnNotiList( SysCmmnNotiDTO paramData) throws Exception {
		return sysCmmnNotiRepository.selectMainSysCmmnNotiList(paramData); 
	}
	
	/**
	 * 첨부파일 등록.
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
		FileUploadResult  fileUploadResult = getIoService().fileUploadAutoFileName(files, getConfigService().getProperty(CONFIG_NOTICE_UPLOAD_FILE_PATH), excludeExtensions);
		
		return fileUploadResult;
	}
	
	/**
	 * 공지 사항 이미지 업로드시 Mall에 따른 업로드 경로 가져오기
	 * 
	 * @param mallId
	 * @return
	 */
	public String getNoticeImageUploadPath(String mallId) {
		String webResourcePath = "";
		String s3BuketName = IOService.getBucketName();
		
		if("DXM".equals(mallId)) {
			webResourcePath = s3BuketName + ":" +getConfigService().getProperty("ncp_web_bo.cloud.DXM.bucket.image.folder.path") + "/helpdesk/notice/";
		}else if("MBM".equals(mallId)) {
			webResourcePath = s3BuketName + ":" +getConfigService().getProperty("ncp_web_bo.cloud.MBM.bucket.image.folder.path") + "/helpdesk/notice/";
		}else if("SAM".equals(mallId)) {
			webResourcePath = s3BuketName + ":" +getConfigService().getProperty("ncp_web_bo.cloud.SAM.bucket.image.folder.path") + "/helpdesk/notice/";
		}else if("BO".equals(mallId)){
			webResourcePath = s3BuketName + ":" +getConfigService().getProperty("ncp_web_bo.cloud.DXM.bucket.image.folder.path") + "/helpdesk/notice/";// BO공지 
		}
		
		return webResourcePath;
	}
	public String findFileName(String editorContents, String imagesPathPrefix){
		Matcher mch;
	    StringBuffer sb;
	    sb = new StringBuffer();
		String filename = null;
		final Pattern IMAGE_TEST = Pattern.compile("(<img[^>]+src\\s*\\=\\s*['\"])((\\S+)\\/)(pec\\/temp\\/)(.+?)(\\#uncommited)(['\"])", Pattern.CASE_INSENSITIVE);
		mch =  IMAGE_TEST.matcher(editorContents);
          while (mch.find()) {
              Matcher filenameMch;
              int sp;
              String tempResourcePath;
              String tempResourceFileName;
              StringBuilder commitResourcePathBuilder;
              String commitResourcePath;
              long tempUploadIndex;
              StringBuilder replaceBuilder;

      

              tempResourcePath = IOService.getBucketName() + ":" + mch.group(4) + mch.group(5);
              sp = tempResourcePath.lastIndexOf('/');
              if (sp == -1) {
                  tempResourceFileName = tempResourcePath;
              } else {
                  tempResourceFileName = tempResourcePath.substring(sp + 1);
              }

              filenameMch = IMAGE_TEST.matcher(tempResourceFileName);
              if (filenameMch.find()) {
                  tempResourceFileName = filenameMch.group(1);
                  filename= tempResourceFileName;
                  return filename;
              }
          }
          return filename;
	}
	
}
