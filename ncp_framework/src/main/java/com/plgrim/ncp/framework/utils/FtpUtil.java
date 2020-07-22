package com.plgrim.ncp.framework.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FtpUtil {
	private static final String FTP_HOST = "ftp.photorank.me";
	private static final String FTP_ID = "mlbkorea";
	private static final String FTP_PW = "ohma9vooY5dee9ie";
	private static final int 	FTP_PORT = 21;

	private FTPClient ftpClient;
	private String host;

	public static void main(String[] args) {
		FtpUtil ftp = new FtpUtil();
		try {
			ftp.connect(FTP_HOST, FTP_PORT, FTP_ID, FTP_PW);
			ftp.cd("/feeds/MASTER");
			List<String> list = ftp.getFilesList();
			for(String s : list) {
				log.info(s);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ftp.disconnect();
		}
	}

	public FtpUtil() {
		ftpClient = new FTPClient();
	}

	public void cd(String path) throws IOException {
		ftpClient.cwd(path);
	}
	
	public boolean delete(String filePath, String fileName) throws Exception {
		boolean isDeleted = ftpClient.deleteFile(filePath + fileName);
		return isDeleted;
	}

	public boolean upload(String filePath, String fileName, InputStream is) throws Exception {
		ftpClient.setFileType(ftpClient.BINARY_FILE_TYPE, ftpClient.BINARY_FILE_TYPE);
		ftpClient.setFileTransferMode(ftpClient.BINARY_FILE_TYPE);
		ftpClient.enterLocalPassiveMode();
		boolean isUploaded = ftpClient.storeFile(filePath + fileName, is);
		return isUploaded;
	}

	public List<String> getFilesList() {
		List<String> list = new ArrayList<>();
		try {
			String[] s = ftpClient.listNames();
			if(s != null) {
				for(String f : s) {
					list.add(f);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public boolean connect(String host, int port, String userName, String password) {
		this.host = host;
		boolean isConnected = false;
		try {
			ftpClient.setControlEncoding("UTF-8");
			ftpClient.connect(host);
			int reply = ftpClient.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) { 
				ftpClient.disconnect(); 
				log.info(host + ", FTP server refused connection."); 
			} else { 
				log.info(host + ", Connect successful, " + ftpClient.getReplyString());
				ftpClient.setSoTimeout(60 * 1000);
				boolean isLogined = ftpClient.login(userName, password);
				if(isLogined) {
					isConnected = true;
					log.info(host + ", login success.");
				}else{
					log.info(host + ", login fail."); 
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return isConnected;
	}

	public void disconnect() {
		try {
			ftpClient.disconnect();
			log.info(host + ", disconnect."); 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
