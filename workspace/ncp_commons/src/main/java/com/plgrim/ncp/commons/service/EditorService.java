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
package com.plgrim.ncp.commons.service;

import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.plgrim.ncp.base.abstracts.AbstractService;
import com.plgrim.ncp.commons.result.CKEditorUploadResult;
import com.plgrim.ncp.framework.cloud.CloudFileSystem;
import com.plgrim.ncp.framework.cloud.aws.S3FileSystem;
import com.plgrim.ncp.framework.commons.IOService;
import com.plgrim.ncp.framework.data.FileUploadResult;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * EditorService Implementation
 *
 * @author Tam
 */
@Slf4j
@Service
public class EditorService extends AbstractService {

    private static final Pattern PTN_UNCOMMITED_IMAGE = Pattern.compile("(<img[^>]+src\\s*\\=\\s*['\"])((\\S+)\\/)(pec\\/temp\\/)(.+?)(\\#uncommited)(['\"])", Pattern.CASE_INSENSITIVE);
    private static final Pattern PTN_UNCOMMITED_ORG_FILENAME = Pattern.compile("^[0-9]+?\\.(.+?)$");
    private static long commitUploadCount = 0;

    private static final String UNIQUE_PREFIX = UUID.randomUUID().toString();
    private static long tempUploadCount = 0;
    private static final int FILE_SIZE_KB = 1024;

    @Value("${ncp_base.editor.image.upload.size}")
    private long imageUploadSize;

    @Value("${ncp_web_bo.cloud.bucket.url}")
    private String backOfficeBucketURL;

    @Value("${ncp_web_bo.cloud.bucket.pec.temp.folder.path}")
    private String backOfficeBucketTempFolderPath;

    @Value("${ncp_web_bo.cdn.DXM.url}")
    private String dxmCloudFrontURL;

    @Value("${ncp_web_bo.cdn.MBM.url}")
    private String mlbCloudFrontURL;

    @Value("${ncp_web_bo.cdn.MLB_KIDS.url}")
    private String mlbKidsCloudFrontURL;

    @Value("${ncp_web_bo.cdn.SAM.url}")
    private String samCloudFrontURL;

    @Value("${ncp_base.editor.image.upload.path}")
    String editorUploadPath;

    @Value("${ncp_base.editor.image.upload.inludeExtensions}")
    String editorUploadInludeExtensions;

    @Value("${ncp_base.upload.excludeExtensions}")
    String editorUploadExcludeExtensions;

    @Value("${ncp_base.upload.image.inludeExtensions}")
    String imageUploadExcludeExtensions;

    @Autowired
    private CloudFileSystem cloudFileSystem;


    /**
     * 이미지 업로드
     *
     * @param uploadPath
     * @param files
     * @return
     * @throws Exception
     */
    public FileUploadResult imageUpload(String uploadPath, List<MultipartFile> files) throws Exception {

        //허용가능한 확장자인지 체크한다.
        getIoService().availableUploadIncludeExtension(files, getStringService().split(imageUploadExcludeExtensions, ","));

        return getIoService().fileUploadAutoFileName(files, uploadPath, getStringService().split(editorUploadExcludeExtensions, ","));

    }

    /**
     * 임시이미지 이동
     *
     * @param tempImageFile
     * @param moveImageFile
     * @return
     * @throws Exception
     */
    public void moveTempImage(File tempImageFile, File moveImageFile) throws Exception {

        // FileUtils.moveFile(tempImageFile, moveImageFile);

        getIoService().moveFile(tempImageFile, moveImageFile);
    }

    /**
     * 임시이미지 이동 (CloudFileSystem.move)
     *
     * @param tempImageFile
     * @param moveImageFile
     * @return
     * @throws Exception
     */
    public void moveTempImage(String tempImageFile, String moveImageFile) throws Exception {
        String bucketName = IOService.getBucketName();
        String sourcePath = bucketName + ":" + tempImageFile;
        String targetPath = bucketName + ":" + moveImageFile;

        try {
            cloudFileSystem.move(sourcePath, targetPath, CloudFileSystem.Permission.PUBLIC);
        } catch (AmazonS3Exception e) {
            if (e.getMessage().indexOf("The specified key does not exist") == -1) { // s3 resource 404 ignore
                throw e;
            }
        }

    }

    /**
     * 파일 삭제
     *
     * @param filePath
     * @param fileName
     * @throws Exception
     */
    public void deleteTempImage(String filePath, String fileName) throws Exception {

        getIoService().deleteFile(filePath, fileName);
    }

    /**
     * 파일 삭제
     *
     * @param filePath
     * @param fileName
     * @throws Exception
     */
    public boolean deleteS3TempImage(String filePath, String fileName) throws Exception {
        String sourceFile = IOService.getBucketName() + ":" + filePath + fileName;
        boolean isSuccess = true;

        try {
            cloudFileSystem.delete(sourceFile);
        } catch (AmazonS3Exception e) {
            if (e.getMessage().indexOf("The specified key does not exist") == -1) { // s3 resource 404 ignore
                throw e;
            }
            isSuccess = false;
        }

        return isSuccess;
    }


    /**
     * CKEditor에서 업로드 하는 삽입 이미지를 임시 등록합니다.
     * limitSize 기본 설정 값
     *
     * @param uploadFile
     * @return
     * @throws IOException
     */
    public CKEditorUploadResult uploadTempImageToS3(MultipartFile uploadFile) throws IOException {
        return this.uploadTempImageToS3(uploadFile, imageUploadSize);
    }

    /**
     * CKEditor에서 업로드 하는 삽입 이미지를 임시 등록합니다.
     *
     * @return CKEditor에 돌려줄 결과 데이터
     * @throws IOException IO 오류 발생시
     */
    public CKEditorUploadResult uploadTempImageToS3(MultipartFile uploadFile, long limitSize) throws IOException {
        CKEditorUploadResult rv = new CKEditorUploadResult();
        StringBuilder sb = new StringBuilder();
        if (uploadFile.getSize() > limitSize) {
            double limitMbs;
            double limitKbs;
            DecimalFormat dcf;

            limitKbs = limitSize / this.FILE_SIZE_KB;
            limitMbs = limitKbs / this.FILE_SIZE_KB;
            dcf = new DecimalFormat("###,##0.##");
            sb.append("file is too large to upload. (limit ");
            if (limitMbs >= 1) {
                sb.append(dcf.format(limitMbs)).append("MB)");
            } else if (limitKbs >= 1) {
                sb.append(dcf.format(limitKbs)).append("KB)");
            } else {
                sb.append(dcf.format(limitSize)).append("bytes)");
            }
            rv.setUploadError(sb.toString());
            return rv;
        }

        if (!uploadFile.getContentType().startsWith("image/")) {
            rv.setUploadError("Uploaded file is not a valid image.");
            return rv;
        }

        long tempUploadIndex;
        synchronized (EditorService.class) {
            tempUploadIndex = tempUploadCount++;
        }


        sb.append(backOfficeBucketTempFolderPath);
        sb.append(getDateService().getStringCurrentToday());
        sb.append('/').append(UNIQUE_PREFIX).append('/');

        String folderPath = sb.toString();
        String fileName = tempUploadIndex + "." + uploadFile.getOriginalFilename();

        cloudFileSystem.store(uploadFile.getInputStream(), uploadFile.getSize(), IOService.getBucketName() + ":" + folderPath, fileName, CloudFileSystem.Permission.PUBLIC);

        fileName = encodeTextReplace(fileName);

        String url = backOfficeBucketURL + folderPath + fileName;

        rv = new CKEditorUploadResult();
        rv.setUploaded(1);
        rv.setFileName(fileName);
        rv.setUrl(url + "#uncommited");

        return rv;
    }

    /**
     * CKEditor에 의해 작성된 컨텐츠에 삽입되어 있는 임시 이미지들을 정식 서비스 이미지로 등록하고, 해당 링크를 수정 반영하여 리턴합니다.
     *
     * @param editorContents   CKEditor에 의해 작성된 컨텐츠
     * @param imagesPathPrefix 정식 등록 이미지의 path prefix
     * @return 정식 이미지 path로 변경된 컨텐츠
     */
    public String commitContentsImagesFromTemp(String editorContents, String imagesPathPrefix) throws IOException {
        return this.commitContentsImagesFromTemp(editorContents, imagesPathPrefix, false);
    }

    /**
     * CKEditor에 의해 작성된 컨텐츠에 삽입되어 있는 임시 이미지들을 정식 서비스 이미지로 등록하고, 해당 링크를 수정 반영하여 리턴합니다.
     *
     * @param editorContents   CKEditor에 의해 작성된 컨텐츠
     * @param imagesPathPrefix 정식 등록 이미지의 path prefix
     * @param pass 업로드 시 현재 날짜 folder 생성 여부
     * @return 정식 이미지 path로 변경된 컨텐츠
     */
    public String commitContentsImagesFromTemp(String editorContents, String imagesPathPrefix, boolean pass) throws IOException {
        Matcher mch;
        StringBuffer sb;

        sb = new StringBuffer();
        mch = PTN_UNCOMMITED_IMAGE.matcher(editorContents);
        while (mch.find()) {
            Matcher filenameMch;
            int sp;
            String tempResourcePath;
            String tempResourceFileName;
            StringBuilder commitResourcePathBuilder;
            String commitResourcePath;
            long tempUploadIndex;
            StringBuilder replaceBuilder;

            synchronized (S3FileSystem.class) {
                tempUploadIndex = commitUploadCount++;
            }

            tempResourcePath = IOService.getBucketName() + ":" + mch.group(4) + decodeTextReplace(mch.group(5));
            sp = tempResourcePath.lastIndexOf('/');
            if (sp == -1) {
                tempResourceFileName = tempResourcePath;
            } else {
                tempResourceFileName = tempResourcePath.substring(sp + 1);
            }

            filenameMch = PTN_UNCOMMITED_ORG_FILENAME.matcher(tempResourceFileName);
            if (filenameMch.find()) {
                tempResourceFileName = filenameMch.group(1);
            }

            commitResourcePathBuilder = new StringBuilder();
            commitResourcePathBuilder.append(imagesPathPrefix);
            if (!imagesPathPrefix.endsWith("/")) {
                commitResourcePathBuilder.append('/');
            }

            if(!pass){
            	commitResourcePathBuilder.append(getDateService().getStringCurrentToday());
            	commitResourcePathBuilder.append("/");
            }

            commitResourcePathBuilder.append(tempUploadIndex);
            commitResourcePathBuilder.append('.').append(tempResourceFileName);
            commitResourcePath = commitResourcePathBuilder.toString();

            try {
                cloudFileSystem.move(tempResourcePath, commitResourcePath, CloudFileSystem.Permission.PUBLIC);
            } catch (AmazonS3Exception e) {
                if (e.getMessage().indexOf("The specified key does not exist") == -1) { // s3 resource 404 ignore
                    throw e;
                }
            }

            replaceBuilder = new StringBuilder();
            replaceBuilder.append(mch.group(1));
            replaceBuilder.append(getMallCloudFrontURL(commitResourcePath));
            String encodePath = encodeTextReplace(commitResourcePath.substring(commitResourcePath.indexOf("/images"))).replaceAll("%2F", "/");
            replaceBuilder.append(encodePath);
            replaceBuilder.append(mch.group(7));

            mch.appendReplacement(sb, replaceBuilder.toString());
        }
        mch.appendTail(sb);

        return sb.toString();
    }
    
    private String getMallCloudFrontURL(String path) {
        String result = "";

        if (path.indexOf("/discovery-expedition/") > -1) {
            result = this.dxmCloudFrontURL;
        } else if (path.indexOf("/mlb-korea/") > -1) {
            result = this.mlbCloudFrontURL;
        } else if (path.indexOf("/mlb-kids/") > -1) {
            result = this.mlbKidsCloudFrontURL;
        } else if (path.indexOf("/stretch-angels/") > -1) {
            result = this.samCloudFrontURL;
        }

        return result;
    }



    /**
     * 특수 문자가 존재 할 경우 URLEncode
     * URL 상 %가 있을 경우 %25로 재인코딩이 되므로 replace
     * @param text
     * @return
     */
    private String encodeTextReplace(String text) throws UnsupportedEncodingException {
        Pattern pattern = Pattern.compile("[!#$%&'()+]");
        if (!pattern.matcher(text).find()) {
            return text;
        }

        return URLEncoder.encode(text, "UTF-8");
    }

    /**
     * 특수문자(%) 디코딩
     * @param text
     * @return
     */
    private String decodeTextReplace(String text) throws UnsupportedEncodingException {
        Pattern pattern = Pattern.compile("[!#$%&'()+]");
        if (!pattern.matcher(text).find()) {
            return text;
        }

        return URLDecoder.decode(text, "UTF-8");
    }

}
