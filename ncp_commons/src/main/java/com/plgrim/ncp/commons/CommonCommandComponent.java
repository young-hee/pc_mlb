package com.plgrim.ncp.commons;

import java.io.IOException;
import java.util.List;

import com.plgrim.ncp.commons.result.CKEditorUploadResult;
import org.springframework.web.multipart.MultipartFile;

import com.plgrim.ncp.commons.data.SysEmailDTO;
import com.plgrim.ncp.commons.data.SysEmailDataDTO;
import com.plgrim.ncp.commons.data.SysLsmsDTO;
import com.plgrim.ncp.commons.data.SysLsmsDataDTO;
import com.plgrim.ncp.framework.data.FileUploadResult;
import com.plgrim.ncp.framework.data.SystemPK;


public interface CommonCommandComponent {

	/**
	 * editor image upload  (기본 서버레파지토리에 저장)
	 * @param files
	 * @return
	 * @throws Exception
	 */
	public CKEditorUploadResult editorImageUpload(MultipartFile files) throws IOException;


	/**
	 * ckeditor Contents image upload  (S3 temp 이미지 클라우드 프론트에 해당하는 위치로 이동)
	 * @param files
	 * @return
	 * @throws Exception
	 */
	public String commitContentsImagesFromTemp(String editorContents, String imagesPathPrefix) throws IOException;



	/**
	 * 이미지 업로드  ( uploadImageType : properties 설정한 유형)
	 * @param uploadImageType
	 * @param files
	 * @return
	 * @throws Exception
	 */
	public FileUploadResult imageUpload( String uploadImageType, List<MultipartFile> files) throws Exception;
	
	/**
	 * 웹접근 upload 이미지 경로를 얻는다.
	 * @param uploadImageType
	 * @return
	 * @throws Exception
	 */
	public String getHttpImagePath( String uploadImageType  ) throws Exception;
	
	/**
	 *  임시 이미지 업로드 경로 ( uploadImageType : properties 설정한 유형)
	 * @param uploadImageType
	 * @param files
	 * @return
	 * @throws Exception
	 */
	public FileUploadResult tempImageUpload( String uploadImageType, List<MultipartFile> files) throws Exception;
	
	/**
	 * 임시 이미지 웹접근 upload 이미지 경로를 얻는다.
	 * @param uploadImageType
	 * @return
	 * @throws Exception
	 */
	public String getTempHttpImagePath( String uploadImageType  ) throws Exception;
	
	/**
	 * 임시 이미지파일을 운영 이미지경로로 이동시킨다.
	 * @param uploadImageType
	 * @param tempFileNameList
	 * @throws Exception
	 */
	public boolean moveTempImage ( String uploadImageType, List<String> tempFileNameList , String vendorFilePath)  throws Exception;
	
	
	/**
	 * 임시 이미지 파일을 삭제한다.
	 * @param uploadImageType
	 * @param tempFileName
	 * @return
	 * @throws Exception
	 */
	public boolean deleteTempImage ( String uploadImageType, String tempFileName )  throws Exception;
	
	/**
	 * SMS/LMS 발송 처리.
	 *
	 * @param systemPK [설명]
	 * @param paramData [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public void sendSysLsms( SysLsmsDTO paramData) throws Exception ;
	
	/**
	 * SMS/LMS 발송 처리. (미접속자)
	 *
	 * @param systemPK [설명]
	 * @param paramData [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public void sendNotLoginSysLsms(String mobileNo, String msgKey, String tempPwd) throws Exception;

	/**
	 * SMS/LMS 자주 사용하는 문구 등록.
	 *
	 * @param systemPK [설명]
	 * @param paramData [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public void saveSysLsmsTxt( SystemPK systemPK, List<SysLsmsDataDTO> paramData) throws Exception ;
	
	/**
	 * 이메일 발송 처리.
	 *
	 * @param systemPK [설명]
	 * @param paramData [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public void sendSysEmail( SysEmailDTO paramData) throws Exception ;
	
	/**
	 * 이메일 자주 사용하는 문구 등록.
	 *
	 * @param systemPK [설명]
	 * @param paramData [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public void saveSysEmailTxt( SystemPK systemPK, List<SysEmailDataDTO> paramData) throws Exception ;
}
