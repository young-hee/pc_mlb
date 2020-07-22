package com.plgrim.ncp.framework.commons;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
//import net.sf.jazzlib.ZipEntry;
//import net.sf.jazzlib.ZipInputStream;
//import net.sf.jazzlib.ZipOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ZipService {

//	private static final byte[] buf = new byte[1024];

	/**
	* Comment  : 생성될 ZIP파일의 경로에 디렉토리가 없을경우 에러  발생
	*/
	public static void createZipFile(String targetPath, String zipPath) {
	    createZipFile(targetPath, zipPath, false);
	}
	
	/**
	 * Comment  : zip 파일을 생성.
	 */
	public static void createZipFile(String targetPath, String zipPath, boolean isDirCre) {
		try {
			File fTargetPath = new File(targetPath);
			File[] files = null;
			 
			if(fTargetPath.isDirectory()){
			     files = fTargetPath.listFiles();
			}else{
			     files = new File[1];
			     files[0] = fTargetPath;
			}
			 
			File path = new File(zipPath);
			File dir = null;
			dir = new File(path.getParent());
			if(isDirCre){
				dir.mkdirs();
			}
			 
			ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(path));
			 
			// zip 파일 압축
			makeZipFile(files, zipOut, "");
 
			// stream을 닫음으로서 zip 파일 생성
			zipOut.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	     
	}
	 
	/**
	 * Comment  : # 일부 파일들을 배열로 설정하여 zip 파일 생성
	 * ex) String[] arrZip = new String[]{"C:\\aaa.txt", "C:\\bbb.txt", "C:\\ccc.txt"}
	 *     ZipUtils.createZipFile(arrZip, "C:\\test.zip");
	 */
	public static void createZipFile(String[] targetFiles, String zipPath) {
		createZipFile(targetFiles, zipPath, false);
	}	     
	
	/**
	 * Comment  : # 일부 파일들을 배열로 설정하여 zip 파일 생성 (디렉토리 생성여부 선택)
	 */
	public static void createZipFile(String[] targetFiles, String zipPath, boolean isDirCre) {
		try {
			File[] files = new File[targetFiles.length];
			
			for(int i = 0; i < files.length; i++){
				files[i] = new File(targetFiles[i]);
			}
      
			File path = new File(zipPath);
			File dir = null;
			dir = new File(path.getParent());
			
			if(isDirCre){
				// 디렉토리가 없을경우 생성
				dir.mkdirs();
			}
   
			// zip 파일의 outputStream
			ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(path));
   
			// zip 파일 압축
			makeZipFile(files, zipOut, "");
   
			// stream을 닫음으로서 zip 파일 생성
			zipOut.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	   
	/**
	* Comment  : zip 파일의 압축을 해제
	*/
	public static void unZipFile(String targetZip, String completeDir) {
	    unZipFile(targetZip, completeDir, false);
	}  	    
	
	/**
	 * Comment  : 
	 */
	public static void unZipFile(String targetZip, String completeDir, boolean isDirCre) {
		ZipInputStream in = null;
		BufferedOutputStream  out = null;
		
		try{
		long startTime  = System.currentTimeMillis();
		
			File fCompleteDir = null;
			fCompleteDir = new File(completeDir);
			if(isDirCre){
				// 디렉토리가 없을경우 생성
				fCompleteDir.mkdirs();
			}
 
			// zip 파일의 input stream을 읽어들임
			in = new ZipInputStream(new FileInputStream(targetZip));
			ZipEntry entry = null;
 
			// input stream내의 압축된 파일들을 하나씩 읽어들임.
			while((entry = in.getNextEntry()) != null){
				if(log.isInfoEnabled()){
					log.info("entry : " + entry);
				}
				
				// zip 파일의 구조와 동일하게 가기위해 로컬의 디렉토리 구조를 만듬.
				String entryName = entry.getName();
				if(entry.getName().lastIndexOf("/") > 0){
					String mkDirNm = entryName.substring(0, entryName.lastIndexOf("/"));
 
					if(log.isInfoEnabled()){
						log.info("mkDirNm : " + mkDirNm);
					}
     
					new File(completeDir + mkDirNm).mkdirs();
				}
				
				// 해제할 각각 파일의 output stream을 생성
				out = new BufferedOutputStream(new FileOutputStream(completeDir + entry.getName())); 
				
				int bytes_read;
				byte[] buf = new byte[1024];
				while((bytes_read = in.read(buf)) != -1){
					if(log.isInfoEnabled()){
						//log.info("bytes_read : " + bytes_read);
					}	                     
					out.write(buf, 0, bytes_read);
				}
				// 하나의 파일이 압축해제됨
				out.close();
			}

		long endTime = System.currentTimeMillis();
		
		
		log.info("########## [START] unZipFile Info ###########################");
		log.info("- unZipFile 시작시간=" + startTime + " ms");
		log.info("- unZipFile 종료시간=" + endTime + " ms");
		log.info("- unZipFile 시간=" +  ((endTime - startTime) / 1000.0) + " ms");
		log.info("########## [END] unZipFile Info #############################");
		
		}catch(Exception e){
			throw new RuntimeException(e);
		}finally{
			// 모든 파일의 압축이 해제되면 input stream을 닫는다.
			IOUtils.closeQuietly(in);
			IOUtils.closeQuietly(out);
		}
	}	        
	
	// byte 배열을 받아서 압축된 byte배열을 리턴
	public static byte[] compressToZip(byte[] src) {
		byte[] retSrc = null;
		ByteArrayOutputStream baos = null;
     
		try{
			// zip 파일의 output Stream
			ByteArrayInputStream bais = new ByteArrayInputStream(src);
			baos = new ByteArrayOutputStream();
			ZipOutputStream zos = new ZipOutputStream(baos) ;
 
			zos.putNextEntry(new ZipEntry("temp.tmp"));
 
			int bytes_read = 0;
			byte[] buf = new byte[1024];
			// 전달받은 src를 압축하여 파일에 씀
			
			while((bytes_read = bais.read(buf)) != -1){
				zos.write(buf, 0, bytes_read);
			}
			bais.close();
			zos.close();
 
			// 스트림을 닫은후 byte배열을 얻어옴
			return baos.toByteArray();
		}catch(Exception e){
			throw new RuntimeException(e);
		}finally{
			IOUtils.closeQuietly(baos);
		}
     
	}	
	
	// 압축된 byte 배열을 받아서 zipPath위치에 zip 파일을 생성한다.
	private static void makeZipFile(byte[] src, String zipPath) {
		FileOutputStream fos = null;
		ByteArrayInputStream bais = null;
     
		try{
			fos = new FileOutputStream(zipPath);
			bais = new ByteArrayInputStream(src);
         
			int bytes_read = 0;
			byte[] buf = new byte[1024];
			while((bytes_read = bais.read(buf)) != -1){
				fos.write(buf, 0, bytes_read);
			}
         
		}catch(Exception e){
			throw new RuntimeException(e);
		}finally{
			IOUtils.closeQuietly(fos);
			IOUtils.closeQuietly(bais);
		}
	}	         
	
	// 압축된 byte 배열의 압축을 해제하여 byte배열로 리턴
	public static byte[] unZip(byte[] src) {
		ByteArrayOutputStream baos = null;
		ZipInputStream zis = null;
		int bytes_read = 0;
     
		try{
			zis = new ZipInputStream(new ByteArrayInputStream(src));
			baos = new ByteArrayOutputStream();
         
			zis.getNextEntry(); // entry는 하나밖에 없음을 보장
			byte[] buf = new byte[1024];
			while((bytes_read = zis.read(buf)) != -1){
				baos.write(buf, 0, bytes_read);
			}
         
			return baos.toByteArray();
		}catch(Exception e){
			throw new RuntimeException(e);
		}finally{
			IOUtils.closeQuietly(baos);
			IOUtils.closeQuietly(zis);
		}
     
	}
	
	// 문자열을 압축하여 byte배열로 리턴(UTF-8)
	public static byte[] compressToZip(String src) {
		try {
			return compressToZip(src.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}
	     
	// byte배열을 압축하여 zip 파일로 생성
	public static void srcToZipFile(byte[] src, String zipPath) {
		byte[] retSrc = null;
		// 압축
		retSrc = compressToZip(src);
	         
		// 파일로 만듬
		makeZipFile(retSrc, zipPath);
	}
	     
	// byte 배열을 압축하여 zip 파일로 생성
	public static void srcToZipFile(String src, String zipPath) {
		
		try {
			byte[] retSrc = null;
			     
			// 압축
			retSrc = compressToZip(src.getBytes("UTF-8"));
			     
			// 파일로 만듬
			makeZipFile(retSrc, zipPath);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}
	     
	     
	public static void makeZipFile(File[] files, ZipOutputStream zipOut, String targetDir) {
		try {
			for(int i = 0; i < files.length; i++){
				File compPath = new File(files[i].getPath());

				if(compPath.isDirectory()){
					File[] subFiles = compPath.listFiles();
					makeZipFile(subFiles, zipOut, targetDir+compPath.getName()+"/");
					continue;
				}

				FileInputStream in = new FileInputStream(compPath);
				zipOut.putNextEntry(new ZipEntry(targetDir+"/"+files[i].getName()));

				int data;

				byte[] buf = new byte[1024];
				while((data = in.read(buf)) > 0){
					zipOut.write(buf, 0, data);    
				}
				zipOut.closeEntry();
				in.close();
			}
		}  catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	                  
}
