/* Copyright (c) 2015 plgrim, Inc.
 * All right reserved.
 * http://plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 *
 * ------------------------------------------------------------------------
 * @author      tktaeki.kim
 * @since       2015. 4. 9       
 */
package com.plgrim.ncp.framework.commons;

import com.plgrim.ncp.framework.cloud.CloudFileSystem;
import com.plgrim.ncp.framework.cloud.aws.S3FileSystem;
import com.plgrim.ncp.framework.data.FileUploadInfo;
import com.plgrim.ncp.framework.data.FileUploadResult;
import com.plgrim.ncp.framework.exception.NotSupportedUploadFileException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.PosixFilePermission;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * File & IO 관련 유틸리티 서비스
 * <p/>
 * <p/>
 * .
 *
 * @author tktaeki.kim
 * @since 2015. 3. 3
 */
@Slf4j
public class IOService implements ApplicationContextAware {
    private static String bucketName;

    private static String fileSystemTempPath;
    
    /**
     * @see org.springframework.context.ApplicationContextAware#setApplicationContext(org.springframework.context.ApplicationContext)
     */
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		IOService.bucketName = applicationContext.getEnvironment().getProperty("ncp_base.cloud.bucketName");
		IOService.fileSystemTempPath = applicationContext.getEnvironment().getProperty("ncp_base.spring.mvc.upload.temp");
	}

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */

	/**
	 * UTF-8 인코딩.
	 */
	static final String FILE_DEFAULT_ENCODING = "UTF-8";

	/**
	 * 한글 인코딩.
	 */
	static final String FILE_KO_ENCODING = "MS949";

	/**
	 * The constant EXTENSION_SEPARATOR.
	 */
	static final char EXTENSION_SEPARATOR = '.';

	/**
	 * The constant UNIX_SEPARATOR.
	 */
	static final char UNIX_SEPARATOR = '/';

	/**
	 * The constant WINDOWS_SEPARATOR.
	 */
	static final char WINDOWS_SEPARATOR = '\\';

	/*
	 * ---------------------------------------------------------------------
	 * Constructors.
	 * ---------------------------------------------------------------------
	 */
	/*
	 * ---------------------------------------------------------------------
	 * public & interface method.
	 * ---------------------------------------------------------------------
	 */

	/**
	 * S3 buckket Name 을 가져온다.
	 * @return
	 */
	public static String getBucketName(){
		return bucketName;
	}


	/**
	 * File System 용 Temp Path 를 가져온다.
	 *
	 * @return
	 */
	public static String getFileSystemTempPath() {
		return fileSystemTempPath;
	}

	/**
	 * 디렉토리 및 하위 디렉토리의 파일을 전체 삭제한다.
	 * <p/>
	 * <p/>
	 * <p/>
	 * 
	 * <pre>
	 * FileUtil.deleteDirectory(new File(&quot;&quot;));
	 * </pre>
	 *
	 * @param file
	 *            삭제 디렉토리
	 * @since 2015. 3. 3
	 */
	public static void deleteAllDirectory(final File file) {
		if (file.isDirectory()) {
			File[] files = file.listFiles();

			for (File nextFile : files) {
				deleteAllDirectory(nextFile);
			}
		}

		file.delete();
	}

	/**
	 * 파일 확장자를 리턴 한다.
	 * <p/>
	 * <p/>
	 * <p/>
	 * getIoService().getExtension("foo.txt") --> "txt" <br/>
	 * getIoService().getExtension("a/b/c.jpg") --> "jpg"<br/>
	 * getIoService().getExtension("a/b.txt/c") --> ""<br/>
	 * getIoService().getExtension("a/b/c") --> ""<br/>
	 *
	 * @param filename
	 *            파일명
	 * @return String 확장자 @ the exception
	 * @since 2015. 3. 3
	 */
	public static String getExtension(String filename) {
		return FilenameUtils.getExtension(filename);

	}

	/**
	 * 파일 확장자를 삭제 한다.
	 * <p/>
	 * <p/>
	 * <p/>
	 * getIoService().removeExtension("foo.txt") --> "foo"<br/>
	 * getIoService().removeExtension("a/b/c.jpg") --> "a/b/c"<br/>
	 * getIoService().removeExtension("a/b/c") --> "a/b/c"<br/>
	 * getIoService().removeExtension("a.b/c") --> "a.b/c"<br/>
	 *
	 * @param filename
	 *            [설명]
	 * @return String [설명] @ the exception
	 * @since 2015. 3. 3
	 */
	public static String removeExtension(String filename) {
		return FilenameUtils.removeExtension(filename);
	}

	/**
	 * 확장자 유무 체크를 한다.
	 * <p/>
	 * <p/>
	 * getIoService().isExtension("a.txt", "txt") = "true"
	 *
	 * @param filename
	 *            파일 명
	 * @param extension
	 *            체크 확장자
	 * @return true:존재 할 경우, false:존재하지 않을 경우 @ the exception
	 * @since 2015. 3. 3
	 */
	public static boolean isExtension(String filename, String extension) {
		return FilenameUtils.isExtension(filename, extension);
	}

	/**
	 * 확장자 유무 체크를 한다.
	 * <p/>
	 * <p/>
	 * <p/>
	 * getIoService().isExtension("a.txt", {"txt","jpg"}) = "true"
	 *
	 * @param filename
	 *            파일 명
	 * @param extension
	 *            체크 확장자 배열
	 * @return true:존재 할 경우, false:존재하지 않을 경우 @ the exception
	 * @since 2015. 3. 3
	 */
	public static boolean isExtension(String filename, String[] extension) {
		return FilenameUtils.isExtension(filename, extension);
	}

	/**
	 * 확장자를 포함한 파일명을 리턴 한다.
	 * <p/>
	 * <p/>
	 * <p/>
	 * getIoService().getName("a/b/c.txt") = "c.txt"
	 * getIoService().getName("a/b/c") = "c" getIoService().getName("a/b/c/") =
	 * ""
	 *
	 * @param fileName
	 *            전체 full 경로 파일 명
	 * @return String 파일 명 @ the exception
	 * @since 2015. 3. 3
	 */
	public static String getName(String fileName) {
		return FilenameUtils.getName(fileName);
	}

	/**
	 * 확장자를 제외한 파일명을 린턴 한다.
	 * <p/>
	 * <p/>
	 * <p/>
	 * getIoService().getName("a/b/c.txt") = "c" getIoService().getName("a.txt")
	 * = "a" getIoService().getName("a/b/c/") = ""
	 *
	 * @param fileName
	 *            전체 full 경로 파일 명
	 * @return String 파일 명 @ the exception
	 * @since 2015. 3. 3
	 */
	public static String getBaseName(String fileName) {
		return FilenameUtils.getBaseName(fileName);
	}
	/**
	 * 파일을 지정된 디렉토리에 옮긴다. 만약 디렉토리가 존재 하지 않을 경우 디렉토리를 생성 한다.
	 * <p/>
	 * <p/>
	 * <p/>
	 * getIoService().moveFile(new File("c:/ESRep3.log"), new File("c:/temp2"));
	 *
	 * @param srcFile
	 *            소스 파일
	 * @param destFile
	 *            복사할 디렉토리 @ the exception
	 * @since 2015. 3. 3
	 */
	public static void moveFile(File srcFile, File destFile) {
		try {
			File path = destFile.getParentFile();
			log.debug("Path : {}, {}", path.getPath(), path.exists());
			if (!path.exists()) {
				path.setExecutable(true, false);
				try {
					log.debug("MKDIR : {}", path.mkdirs());
				} catch (Exception e) {
					log.warn(e.getMessage());
				}
			}
			FileUtils.moveFileToDirectory(srcFile, destFile, true);
			destFile.setReadable(true, false);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static void moveFileToFile(File srcFile, File destFile) {
		try {
			File path = destFile.getParentFile();
			log.debug("Path : {}, {}", path.getPath(), path.exists());
			if (!path.exists()) {
				try {
					log.debug("MKDIR : {}", path.mkdirs());
				} catch (Exception e) {
					log.warn(e.getMessage());
				}
				// setPermission(path);
			}

			FileUtils.moveFile(srcFile, destFile);
			setPermission(destFile);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static void setPermission(File destFile) {
		String os = System.getProperty("os.name").toLowerCase();

		if (os.indexOf("win") < 0) {
			log.info("[os=" + os + "] PosixFilePermission setPermission");

			Set<PosixFilePermission> perms = new HashSet<PosixFilePermission>();
			// add owners permission
			perms.add(PosixFilePermission.OWNER_READ);
			perms.add(PosixFilePermission.OWNER_WRITE);
			perms.add(PosixFilePermission.OWNER_EXECUTE);
			// add group permissions
			perms.add(PosixFilePermission.GROUP_READ);
			perms.add(PosixFilePermission.GROUP_WRITE);
			perms.add(PosixFilePermission.GROUP_EXECUTE);
			// add others permissions
			perms.add(PosixFilePermission.OTHERS_READ);
			perms.add(PosixFilePermission.OTHERS_WRITE);
			perms.add(PosixFilePermission.OTHERS_EXECUTE);

			Path parent = Paths.get(destFile.getPath());
			while (parent != null) {
				try {
					Files.setPosixFilePermissions(parent, perms);
					parent = parent.getParent();
				} catch (Exception fe) {
					log.warn(fe.getMessage());
					break;
				}
			}
		}
	}

	/**
	 * 파일을 복사 한다.
	 * <p/>
	 * <p/>
	 * <p/>
	 * getIoService().copyFile(new File("c:/ESRep3.log"), new
	 * File("c:/ESRep4.log"));
	 *
	 * @param source
	 *            소스 파일
	 * @param dest
	 *            복사될 파일 @ the exception
	 * @since 2015. 3. 3
	 */
	public static void copyFile(File source, File dest) {
		try {
			File path = dest.getParentFile();
			log.debug("Path : {}, {}", path.getPath(), path.exists());
			if (!path.exists()) {
				try {
					log.debug("MKDIR : {}", path.mkdirs());
				} catch (Exception e) {
					log.warn(e.getMessage());
				}
			}
			FileUtils.copyFile(source, dest);
			setPermission(dest);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

//	/**
//	 * 업로드 파일을 지정된 위치에 저장 한다.
//	 * <p/>
//	 * <p/>
//	 *
//	 * @param multipartFile
//	 *            [설명]
//	 * @param path
//	 *            [설명]
//	 * @param fileName
//	 *            [설명] @ the exception
//	 * @since 2015. 3. 24
//	 */
//	public static String saveUploadFile(MultipartFile multipartFile, String path, String fileName) {
//
//		try {
//			File pFile = new File(path);
//			boolean available = true;
//
//			// 디렉토리가 존재 하지 않을 경우 생성
//			if (!pFile.exists()) {
//
//				log.info("before.pFile.canExecute()===>" + pFile.canExecute());
//
//				pFile.setExecutable(true, false);
//				pFile.setReadable(true, false);
//				pFile.mkdirs();
//				// 폴더 생성시 권한 777 부여
//				setPermission(pFile);
//
//				log.info("after.pFile.getPath()===>" + pFile.getPath());
//				log.info("after.pFile.canExecute()===>" + pFile.canExecute());
//			}
//
//			String uploadFile = trimToPath(path) + fileName;
//			File targetFile = new File(uploadFile);
//			log.info("before.targetFile.canExecute()===>" + targetFile.canExecute());
//
//			multipartFile.transferTo(targetFile);
//			targetFile.setReadable(true, false);
//			// 파일 777 권한 부여
//			setPermission(targetFile);
//
//			log.info("after.trimToPath(path) + fileName===>" + uploadFile);
//			log.info("after.targetFile.canExecute()===>" + targetFile.canExecute());
//
//			return uploadFile;
//		} catch (Exception e) {
//			throw new RuntimeException(e);
//		}
//	}
	
	/**
	 * multipartFile 파일을 cloudFileSystem을 통해 저장한다.
	 * 
	 * <p/>
	 *
	 * @param multipartFile spring multipart file
	 * @param path 저장할 path
	 * @param fileName 저장할 filename
	 * @return 저장된 path+filename return
	 * @since 2015. 3. 24
	 */
	public static String saveUploadFile(MultipartFile multipartFile, String path, String fileName) {
		if (bucketName == null) {
			throw new IllegalStateException("bucketName is null due to didn't called setApplicationContext().");
		}
		try {
			String trimedPath = trimToPath(path);
			String cloudPath = bucketName+":"+trimedPath;
			S3FileSystem s3FileSystem = new S3FileSystem();
			long fileSize = multipartFile.getSize();
			s3FileSystem.store(multipartFile.getInputStream(), fileSize, cloudPath, fileName, CloudFileSystem.Permission.PUBLIC);

			String url = cloudPath+fileName;
			log.info("saveUploadFile: url[{}], size[{}]", url, fileSize);

			return trimedPath+fileName;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}


	/**
	 * 디렉토리 마지막에 구분자가 없을 경우 구분자를 추가해 준다..
	 * <p/>
	 * c:/aaa/bbb => c:/aaa/bbb/ c:/aaa/bbb/ => c:/aaa/bbb/
	 * <p/>
	 *
	 * @param path
	 *            [설명]
	 * @return String [설명] @ the exception
	 * @since 2015. 3. 24
	 */
	public static String trimToPath(String path) {

		String convertPath = path;

		// 맨 위체 구분자가 없을 경우 추가 한다.
		if (!(StringUtils.endsWith(path, "" + UNIX_SEPARATOR) || StringUtils.endsWith(path, "" + WINDOWS_SEPARATOR))) {
			convertPath += "" + UNIX_SEPARATOR;
		}

		return convertPath;
	}

	/**
	 * String [] excludeExtensions에 하나라도 포함이 되면 NotSupportedUploadFileException
	 * 발생.
	 * <p/>
	 * <p/>
	 * white list=====================
	 *
	 * @param files
	 *            [설명]
	 * @param excludeExtensions
	 *            [설명] @ the exception
	 * @since 2015. 3. 24
	 */
	public static void availableUploadExcludeExtension(List<MultipartFile> files, String[] excludeExtensions) {

		boolean available = false;

		for (MultipartFile file : files) {

			String extension = getExtension(file.getOriginalFilename());

			available = true;

			for (String excludeExtension : excludeExtensions) {
				if (excludeExtension.equalsIgnoreCase(extension)) {
					available = false;
					break;
				}
			}

			if (!available) {
				break;
			}
		}

		// 부접한한 확장자면 NotSupportedUploadFileException를 리턴 한다.
		if (!available) {
			throw new NotSupportedUploadFileException(null);
		}

	}

	/**
	 * 허용가능 확장자를 체크하는
	 *
	 * @param files
	 *            파일들
	 * @param includeExtensions
	 *            허용 확장자 문자열 @
	 */
	public static void availableUploadIncludeExtension(List<MultipartFile> files, String[] includeExtensions) {

		boolean available = false;

		for (MultipartFile file : files) {

			String extension = getExtension(file.getOriginalFilename());

			for (String includeExtension : includeExtensions) {

				if (includeExtension.equalsIgnoreCase(extension)) {
					available = true;
				}
			}
			if (available) {
				break;
			}
		}

		// 부접한한 확장자면 NotSupportedUploadFileException를 리턴 한다.
		if (!available) {
			throw new NotSupportedUploadFileException(null);
		}

	}

	/**
	 * 파일업로드 중복 파일명을 무시하고 오버라이트 업로드한다.
	 *
	 * @param files
	 * @param savePath
	 * @param includeExtensions
	 * @return @
	 */
	public static FileUploadResult fileUploadOverWrite(List<MultipartFile> files, String savePath, String[] excludeExtensions) {
		return fileUpload(false, files, savePath, excludeExtensions, null);
	}

	/**
	 * 파일업로드 유일한 파일명을 생성하여 업로드한다.
	 *
	 * @param files
	 * @param savePath
	 * @param includeExtensions
	 * @return @
	 */
	public static FileUploadResult fileUploadAutoFileName(List<MultipartFile> files, String savePath, String[] excludeExtensions) {
		return fileUpload(true, files, savePath, excludeExtensions, null);
	}

	/**
	 * 파일업로드 save Filename 명으로 저장한다.
	 * 
	 * @param files
	 * @param savePath
	 * @param excludeExtensions
	 * @param saveFileName
	 * @return @
	 */
	public static FileUploadResult fileUploadOverWrite(List<MultipartFile> files, String savePath, String[] excludeExtensions,
			String saveFileName) {
		return fileUpload(false, files, savePath, excludeExtensions, saveFileName);
	}

	/**
	 * File Upload 자동으로 파일명을 생성하여 저장한다.
	 *
	 * @param autoFileName
	 *            파일업로드 자동부여 여부 : true(자동) , false (오리지널파일명)
	 * @param files
	 *            List<MultipartFile>
	 * @param savePath
	 *            레파지토리 경로
	 * @param includeExtensions
	 *            업로드 불가 확장자
	 * @return 저장된 파일정보 List @
	 */
	private static FileUploadResult fileUpload(boolean isAutoFileName, List<MultipartFile> files, String savePath,
			String[] excludeExtensions, String saveFileName) {

		// 확장자 체크
		availableUploadExcludeExtension(files, excludeExtensions);

		// system 제외 확장자 로직 추가해야함.

		FileUploadResult fileuploadResult = new FileUploadResult();

		List<FileUploadInfo> uploadFileList = new ArrayList<FileUploadInfo>();

		for (MultipartFile file : files) {

			String extension = getExtension(file.getOriginalFilename());

			long fileSize = file.getSize();
			String fileName = file.getOriginalFilename();
			String orgFileName = extractFileName(file.getOriginalFilename());

			if (fileSize > 0 && StringService.isNotEmpty(fileName)) {
				log.info("= file avaiable !!!");

				if (isAutoFileName) {
					fileName = getUniqueFileName() + "." + extension;
				} else if (saveFileName != null) {
					fileName = saveFileName + "." + extension;
				}

				saveUploadFile(file, savePath, fileName);

				FileUploadInfo fileUploadInfo = new FileUploadInfo();
				fileUploadInfo.setFileName(fileName);
				fileUploadInfo.setFilePath(savePath);
				fileUploadInfo.setFileSize(fileSize);
				fileUploadInfo.setOrgFileName(orgFileName);
				fileUploadInfo.setExtension(extension);

				uploadFileList.add(fileUploadInfo);

			}

			fileuploadResult.setFileCnt(uploadFileList.size());
			fileuploadResult.setRows(uploadFileList);

			if (log.isInfoEnabled()) {

				if (fileuploadResult.getFileCnt() > 0) {

					List<FileUploadInfo> rows = fileuploadResult.getRows();

					for (FileUploadInfo fileInfo : rows) {
						log.info("########## [START] Upload File Info #############################");
						log.info("- 사용자 파일명=" + fileInfo.getOrgFileName());
						log.info("- 파일 확장자=" + fileInfo.getExtension());
						log.info("- 파일사이즈=" + fileInfo.getFileSize());
						log.info("-파일 저장 경로 =" + fileInfo.getFilePath());
						log.info("-저장 파일명=" + fileInfo.getFileName());
						log.info("########## [END] Upload File Info #############################");

					}
				}
			}
		}
		return fileuploadResult;
	}

	/**
	 * 파일 삭제
	 * 
	 * @param filePath
	 * @param fileName
	 * @ @author ys.tam
	 * @since 2015. 4. 29
	 */
	public static void deleteFile(String filePath, String fileName) {

		File file = new File(filePath, fileName);

		if (!file.isDirectory()) {
			log.info(" file delete =>" + file.getAbsolutePath());
			FileUtils.deleteQuietly(file);
		}
	}

	/**
	 * 파입 업로드를 위한 파일 이름 생성
	 *
	 * @return
	 */
	private static String getUniqueFileName() {
		return String.valueOf(System.nanoTime());
	}

	/**
	 * 파일명를 추출한다. ( ex) kkk.pdf return kkk )
	 *
	 * @param fileName
	 * @return
	 */
	private static String extractFileName(String fileName) {

		// 나중에 lastIndex사용

		String temp[] = fileName.split("[.]");
		if (temp.length != 0)
			return temp[0];
		return "";
	}

}
