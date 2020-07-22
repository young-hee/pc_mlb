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

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.NoHttpResponseException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.plgrim.ncp.base.abstracts.AbstractService;
import com.plgrim.ncp.base.entities.datasource1.sys.SysChckHist;
import com.plgrim.ncp.commons.repository.SysCmmnBatchRepository;
import com.netflix.config.DynamicPropertyFactory;
import com.netflix.config.DynamicStringProperty;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SysBatchGenerateService extends AbstractService {

	@Value("${ncp.image.url}")
	String imageSvcPath;
	
	@Autowired
	SysBatchService sysBatchService;
	
	@Autowired
	SysCmmnBatchRepository sysBatchRepository;
	
	/**
	 * weekly 모니터링 쿼리 결과 return
	 */
	public List<SysChckHist> getWeeklyMonitor(String dayType, String smsYn) {
		List<SysChckHist> rtnResultList = null;
		
		try {
			DynamicStringProperty weekly_source_url = DynamicPropertyFactory.getInstance().getStringProperty("weekly_source_url", "");
			log.debug(">>>>>getWeeklyMonitor weekly_source_url : {}", weekly_source_url.getValue());
			DynamicStringProperty weekly_result_path = DynamicPropertyFactory.getInstance().getStringProperty("weekly_result_path", "");
			log.debug(">>>>>getWeeklyMonitor weekly_result_path : {}", weekly_result_path.getValue());
			
			if(StringUtils.isNotEmpty(weekly_source_url.getValue()) || StringUtils.isNotEmpty(weekly_result_path.getValue())){
				SysChckHist sysChckHist = new SysChckHist();
				//sysChckHist.setDayType(dayType);
				// 시스템 점검 이력 리스트 조회
				rtnResultList = sysBatchRepository.getSysChckHistList(sysChckHist);
				
				// 시스템 점검 이력 리스트 조회 (DW) 포함

				if("Y".equals(smsYn)) {	//smsYn 파라매터가 Y인 경우에만 html generator and 개발자에게 SMS 발송
					String sourceUrl = weekly_source_url.getValue();
					String backupFileDir = weekly_result_path.getValue();
					String requestCharSet = "UTF-8";
					String responseCharSet = "UTF-8";
					//Html Contents
					String htmlContents = getHtmlContents(sourceUrl, requestCharSet);
					//에러가 없는 경우
					if(StringUtils.isNotEmpty(htmlContents)){
						String backupFilePath = backupFileDir + "weekly_" + getFormatCurrDateTime("YYYYMMDDhhmm") + ".html";

						//대상경로 체크(없는 경우 생성)
						fileValidate(backupFilePath);

						//NAS 서버 대상경로로 저장
						boolean success = writeToFile(htmlContents, backupFilePath, requestCharSet, responseCharSet);
						
						if(success) {
							backupFilePath = backupFilePath.replace("/IMG1/images", imageSvcPath);
							log.info(">>>>>getWeeklyMonitor backupFilePath save success : {}", backupFilePath);
							String msg = "[ " + backupFilePath + " ] 확인요청";
							String parameter = "{'group':'WEEKLY_MNTRNG_SMS','subject':'[PLGRIM 모니터링]','message':'" + msg + "'}";
							log.info(">>>>>getWeeklyMonitor parameter : {}", parameter);
							sysBatchService.sendBatchAlarmSMS(parameter);
						}
					}
				}
			}
		} catch(Exception ex) {
			log.warn(">>>>>getWeeklyMonitor Exception : {}", ex);
		}
		
		return rtnResultList;
	}

	/**
	 * html contents 다운로드
	 */
	private String getHtmlContents(String sourceUrl, String requestCharSet) throws Exception {
		String responseBody = "";

		HttpClient client = new HttpClient(new MultiThreadedHttpConnectionManager());
		client.getHttpConnectionManager().getParams().setConnectionTimeout(3000);

		GetMethod method = new GetMethod(sourceUrl);
		method.setRequestHeader("Content-type", "text/xml; charset=" + requestCharSet);

		HttpMethodRetryHandler myretryhandler = new HttpMethodRetryHandler(){
			public boolean retryMethod(final HttpMethod method, final IOException exception, int executionCount){
				if (executionCount >= 1){
		            // Do not retry if over max retry count
		            return false;
		        }
		        if (exception instanceof NoHttpResponseException){
		            // Retry if the server dropped connection on us
		            return true;
		        }
		        if (!method.isRequestSent()){
		            // Retry if the request has not been sent fully or
		            // if it's OK to retry methods that have been sent
		            return true;
		        }
		        // otherwise do not retry
		        return false;
		    }
		};

		method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, myretryhandler);

		int statusCode = client.executeMethod(method);

		String responseCharSet = "utf-8";
		StringBuilder buffer = new StringBuilder();

		if (statusCode == HttpStatus.SC_OK){
			responseCharSet = method.getResponseCharSet();
            BufferedReader reader = new BufferedReader(new InputStreamReader(method.getResponseBodyAsStream(), responseCharSet));

            String line = null;
            while( (line = reader.readLine()) != null ){
                buffer.append(line+"\n");
            }

            responseBody = buffer.toString();
		}

		return responseBody;
	}
	
	/**
	 * 파일 validate
	 */
    private void fileValidate(String filePath) throws Exception{

		String fileDir = "";

		if(filePath.indexOf(".") > -1){
			fileDir = filePath.substring(0, filePath.lastIndexOf("/"));
		}

		File tempDir = new File(fileDir);
		File tempFile = new File(filePath);

		if(!tempDir.exists()){
			tempDir.mkdirs();
		}

		if(!tempFile.exists()){
			tempFile.createNewFile();
		}
	}
    
    /**
	 * 파일 생성
	 */
	private boolean writeToFile(String inStr, String filePath, String charSet, String resCharSet){
		FileOutputStream fos = null;
		BufferedWriter out = null;
		boolean bRet = true;

		try{
			fos = new FileOutputStream(filePath);
            out = new BufferedWriter( new OutputStreamWriter( fos, charSet ));
            out.write( new String(inStr.getBytes(resCharSet), charSet));
		}catch (FileNotFoundException e){
			bRet = false;
		}catch (IOException e){
			bRet = false;
		}finally{
			try{
				out.close();
			}catch (Exception e){
			}
		}

		return bRet;
	}
    
    /**
	 * 현재시간을 원하는 형식으로 변환
	 */
    private static String getFormatCurrDateTime(String format) {
		GregorianCalendar cal = new GregorianCalendar();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;
		int day = cal.get(Calendar.DATE);
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		int minute = cal.get(Calendar.MINUTE);
		int sec = cal.get(Calendar.SECOND);
		int msec = cal.get(Calendar.MILLISECOND);
		int am_pm = cal.get(Calendar.AM_PM);
		format = replaceStringAll(format, "YYYY", toLen(year, 4));
		format = replaceStringAll(format, "MM", toLen2(month));
		format = replaceStringAll(format, "DD", toLen2(day));
		format = replaceStringAll(format, "hh", toLen2(hour));
		format = replaceStringAll(format, "mm", toLen2(minute));
		format = replaceStringAll(format, "ss", toLen2(sec));
		format = replaceStringAll(format, "ms", toLen2(msec));
		format = replaceStringAll(format, "AM", am_pm == 1 ? "오후" : "오전");

		return format.toString();
	}
	
	/**
	 * 문자열의 변환(전체문자열에는 영향없음)
	 */
	private static String replaceStringAll(String in, String find, String replace) {
		if (in == null) {
			return "";
		}
		String source = in;
		String keyStr = find;
		String toStr = replace;
		int startIndex = 0;
		int curIndex = 0;
		StringBuffer result = new StringBuffer();
		while ((curIndex = source.indexOf(keyStr, startIndex)) >= 0) {
			result.append(source.substring(startIndex, curIndex)).append(toStr);
			startIndex = curIndex + keyStr.length();
		}
		if (startIndex <= source.length()) {
			result.append(source.substring(startIndex, source.length()));
		}

		return result.toString();
	}
	
	/**
	 * length크기에 맞추어 0을 붙여 반환
	 */
	private static String toLen(int nums, int length) {
		String num = String.valueOf(nums).toString();
		int space = length - num.length();
		int i = 0;
		String buf = "";
		for (i = 0; i < space; i++) {
			buf += "0";
		}
		num = buf + num;

		return num;
	}
	
	
	/**
	 * 2->02
	 */
	private static String toLen2(int nums) {
		String num = String.valueOf(nums).toString();
		if (num.length() == 1)
			num = "0" + num;
		return num;
	}
}
