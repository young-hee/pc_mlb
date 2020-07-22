package com.plgrim.ncp.commons;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;


import com.plgrim.ncp.commons.service.EditorService;
import com.plgrim.ncp.commons.result.CKEditorUploadResult;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.plgrim.ncp.base.abstracts.AbstractComponent;
import com.plgrim.ncp.commons.data.SysEmailDTO;
import com.plgrim.ncp.commons.data.SysEmailDataDTO;
import com.plgrim.ncp.commons.data.SysLsmsDTO;
import com.plgrim.ncp.commons.data.SysLsmsDataDTO;
import com.plgrim.ncp.commons.repository.SysShopMgrRepository;
import com.plgrim.ncp.commons.service.SysEmailService;
import com.plgrim.ncp.commons.service.SysLsmsService;
import com.plgrim.ncp.framework.commons.StringService;
import com.plgrim.ncp.framework.data.FileUploadResult;
import com.plgrim.ncp.framework.data.SystemPK;

@Slf4j
@Transactional(value = "transactionManager")
@Component
public class CommonCommandComponentImpl extends AbstractComponent implements CommonCommandComponent {

	
	/*@Autowired(required=false)
	ServletContext servletContext;*/

    @Autowired
    EditorService editorService;

    @Autowired
    SysLsmsService sysLsmsService;

    @Autowired
    SysEmailService sysEmailService;

    @Autowired 
    SysShopMgrRepository sysShopMgrRepository;

    /**
     * ckeditor temp image upload  (임시 이미지 S3 저장)
     *
     * @param files
     * @return
     * @throws Exception
     */
    public CKEditorUploadResult editorImageUpload(MultipartFile files) throws IOException {

        return editorService.uploadTempImageToS3(files);

    }

    /**
     * ckeditor Contents image upload  (S3 temp 이미지 클라우드 프론트에 해당하는 위치로 이동)
     *
     * @param files
     * @return
     * @throws Exception
     */
    public String commitContentsImagesFromTemp(String editorContents, String imagesPathPrefix) throws IOException {
        return editorService.commitContentsImagesFromTemp(editorContents, imagesPathPrefix);
    }


    /**
     * 이미지 업로드 ( uploadImageType 서버레파지토리에 저장)
     *
     * @param uploadImageType
     * @param files
     * @return
     * @throws Exception
     */
    public FileUploadResult imageUpload(String uploadImageType, List<MultipartFile> files) throws Exception {

        FileUploadResult fileUploadResult = editorService.imageUpload(getUploadImagePath(uploadImageType), files);
        fileUploadResult.setHttpPath(getHttpImagePath(uploadImageType));
        return fileUploadResult;
    }

    /**
     * 임시 이미지 업로드 경로 ( uploadImageType : properties 설정한 유형)
     *
     * @param uploadImageType
     * @param files
     * @return
     * @throws Exception
     */
    public FileUploadResult tempImageUpload(String uploadImageType, List<MultipartFile> files) throws Exception {

        FileUploadResult fileUploadResult = editorService.imageUpload(getTempUploadImagePath(uploadImageType), files);
        fileUploadResult.setHttpPath(getTempHttpImagePath(uploadImageType));
        return fileUploadResult;
    }

    /**
     * 웹접근 upload 이미지 경로를 얻는다.
     *
     * @param uploadImageType
     * @return
     * @throws Exception
     */
    public String getHttpImagePath(String uploadImageType) throws Exception {

        return getConfigService().getProperty("ncp_base.image." + uploadImageType + ".http.path");

    }

    /**
     * 임시 이미지업로드 경로
     *
     * @param uploadImageType
     * @return
     * @throws Exception
     */
    public String getTempHttpImagePath(String uploadImageType) throws Exception {
        return getConfigService().getProperty("ncp_base.image." + uploadImageType + ".http.temp.path") +"/"+ getDateService().getStringCurrentToday();
    }

    /**
     * 임시 이미지파일을 운영 이미지경로로 이동시킨다.
     *
     * @param uploadImageType
     * @param tempFileNameList
     * @throws Exception
     */
    public boolean moveTempImage(String uploadImageType, List<String> tempFileNameList, String vendorFilePath) throws Exception {

        boolean isSuccess = false;
        int moveFileCnt = 0;
        
        Iterator<String> iterator = tempFileNameList.iterator();
        while (iterator.hasNext()) {
            String fileName = getStringService().replace(iterator.next(), "..", "");

            if (!"".equals(fileName)) {
                String tempFilePath = getTempUploadImagePath(uploadImageType) + fileName;
                log.info(" tempFilePath => {} ", tempFilePath);
                String moveFilepath = "";
                if(uploadImageType.equals("vendor")){                         	
                	moveFilepath = vendorFilePath + fileName;
                }else{
                	moveFilepath = getUploadImagePath(uploadImageType) + fileName;
                }                

                log.info(" moveFilepath => {} " + moveFilepath);
                editorService.moveTempImage(tempFilePath, moveFilepath);
            }

            moveFileCnt++;
        }

        log.info(tempFileNameList.size() + "==" + moveFileCnt);

        if (tempFileNameList.size() == moveFileCnt) {
            isSuccess = true;
        }

        return isSuccess;
    }

    /**
     * S3 임시 이미지 파일을 삭제한다.
     *
     * @param uploadImageType
     * @param tempFileName
     * @return
     * @throws Exception
     */
    public boolean deleteTempImage(String uploadImageType, String tempFileName) throws Exception {
        tempFileName = getStringService().replace(tempFileName, "..", "");

        return editorService.deleteS3TempImage(getTempUploadImagePath(uploadImageType), tempFileName);
    }

    /**********************************************************
     ############### private function  #########################
     ***********************************************************/

    /**
     * 업로드 이미지 레파지토리 경로를 얻는다.
     *
     * @param uploadImageType
     * @return
     * @throws Exception
     */
    private String getUploadImagePath(String uploadImageType) throws Exception {
        return getConfigService().getProperty("ncp_base.image." + uploadImageType + ".upload.path");
    }

    /**
     * [임시] 업로드 이미지 레파지토리 경로를 얻는다.
     *
     * @param uploadImageType
     * @return
     * @throws Exception
     */
    private String getTempUploadImagePath(String uploadImageType) throws Exception {

        String tempPath = getConfigService().getProperty("ncp_base.image." + uploadImageType + ".upload.temp.path") + getDateService().getStringCurrentToday() + "/";
        return tempPath;
    }

    /**
     * SMS/LMS 발송 처리.
     *
     * @param paramData [설명]
     * @throws Exception the exception
     * @since 2015. 5. 29
     */
    public void sendSysLsms(SysLsmsDTO paramData) throws Exception {
        sysLsmsService.sendSysLsms(paramData);
    }

    /**
     * SMS/LMS 발송 처리. (미접속자)
     *
     * @param mobileNo
     * @param msgKey
     * @param tempPwd
     * @throws Exception the exception
     * @since 2015. 5. 29
     */
    public void sendNotLoginSysLsms(String mobileNo, String msgKey, String tempPwd) throws Exception {
        sysLsmsService.sendNotLoginSysLsms(mobileNo, msgKey, tempPwd);
    }

    /**
     * SMS/LMS 자주 사용하는 문구 등록.
     *
     * @param systemPK  [설명]
     * @param paramData [설명]
     * @throws Exception the exception
     * @since 2015. 5. 29
     */
    public void saveSysLsmsTxt(SystemPK systemPK, List<SysLsmsDataDTO> paramData) throws Exception {
        sysLsmsService.saveSysLsmsTxt(paramData);
    }

    /**
     * 이메일 발송 처리.
     *
     * @param paramData [설명]
     * @throws Exception the exception
     * @since 2015. 5. 29
     */
    public void sendSysEmail(SysEmailDTO paramData) throws Exception {
        sysEmailService.sendSysEmail(paramData);
    }

    /**
     * 이메일 자주 사용하는 문구 등록.
     *
     * @param systemPK  [설명]
     * @param paramData [설명]
     * @throws Exception the exception
     * @since 2015. 5. 29
     */
    public void saveSysEmailTxt(SystemPK systemPK, List<SysEmailDataDTO> paramData) throws Exception {
        sysEmailService.saveSysEmailTxt(paramData);
    }
}
