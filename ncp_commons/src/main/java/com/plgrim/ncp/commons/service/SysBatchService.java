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
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.plgrim.ncp.base.abstracts.AbstractService;
import com.plgrim.ncp.base.entities.datasource1.sys.SysChckHist;
import com.plgrim.ncp.commons.repository.SysCmmnBatchRepository;
import com.plgrim.ncp.commons.result.SysBatchResult;
import com.plgrim.ncp.framework.commons.StringService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SysBatchService extends AbstractService {

	// IF server url
	@Value("${ncp_base.interface.server.url}")
	String ifServerUrl;
	
	// security key monitoring SMS
	@Value("${ncp_if_server.authorization.security.key.sms}") 
	String smsSecurityKey;
	
	@Value("${elasticsearch.monitor.url}")
	String elasticSearchUrl;
	
	@Autowired
	SysCmmnBatchRepository sysBatchRepository;
	
	/**
	 * 배치 리스트 조회
	 */
	public List<SysBatchResult> selectListSysBatch(SysBatchResult paramData) throws Exception {
		return sysBatchRepository.selectListSysBatch(paramData);
	}
	
	/**
	 * 시스템 점검 이력 리스트 조회
	 */
	public List<SysChckHist> getSysChckHistList(SysChckHist sysChckHist) throws Exception {
		return sysBatchRepository.getSysChckHistList(sysChckHist);
	}
	
	/**
	 * Elasticsearch Url 호출하여 쿼리 total count 조회
	 */
	public String getElasticsearchCnt(String queryString, String fromDate, String toDate) {
		log.debug(">>>>>getElasticsearchCnt start : " + queryString + " , " + fromDate + " , " + toDate);

		PostMethod post = new PostMethod(elasticSearchUrl);
		HttpClient httpClient = new HttpClient();
		String rtnStr = "";

		String query = "{\n"
				+ "    \"query\": {\n"
				+ "        \"filtered\" : {\n"
				+ "            \"query\" : {\n"
				+ "                \"query_string\" : {\n"
				+ "                    \"query\" : \"" + queryString + "\"\n"
				+ "                }\n"
				+ "            },\n"
				+ "            \"filter\" : {\n"
				+ "                \"bool\" : {\n"
				+ "                    \"must\": ["
				+ "                    		{\n"
				+ "                    			\"range\": {\n"
				+ "                    				\"@timestamp\": {\n"
				+ "                    					\"gte\" : \"" + fromDate + "\",\n"
				+ "                    					\"lte\" : \"" + toDate + "\"\n"
				+ "                    				}\n"
				+ "                    			}\n"
				+ "                    		}\n"
				+ "                    ],\n"
				+ "                    \"must_not\": []\n"
				+ "                }\n"
				+ "            }\n"
				+ "        }\n"
				+ "    },\n"
				+ "    \"size\" : 0,"
				+ "    \"aggs\": {}"
				+ "}";
		try {
			RequestEntity entity = new StringRequestEntity(query, "application/json","UTF-8");
			post.setRequestEntity(entity);
			httpClient.executeMethod(post);

			int statusCode = post.getStatusCode();
			log.debug(">>>>>getElasticsearchCnt statusCode : {}", statusCode);
			
			if (statusCode == HttpStatus.SC_OK) {	//정상인 경우만 실행
				String strResult = post.getResponseBodyAsString();
				log.debug(">>>>>getElasticsearchCnt getResponseBodyAsString : {}", strResult);
				
				if(!StringService.isEmpty(strResult)) {
					JSONParser jsonParser = new JSONParser();
					JSONObject jsonObject = (JSONObject)jsonParser.parse(strResult);
					JSONObject hitsObj = (JSONObject)jsonObject.get("hits");
					rtnStr = hitsObj.get("total").toString();
					log.debug(">>>>>getElasticsearchCnt hits.total : {},{},{},{}", fromDate, toDate, queryString, rtnStr);
				}
			}
		} catch(Exception e) {
			log.warn(">>>>>getElasticsearchCnt Exception : {}", e);
		} finally {
			post.releaseConnection();
		}
		
		return rtnStr;
	}
	
	/**
	 * Elasticsearch Url 호출하여 쿼리 result 조회
	 */
	public List<Map<String, Object>> getElasticsearchResultList(String queryString, String fromDate, String toDate) {
		log.debug(">>>>>getElasticsearchResultList start : " + queryString + " , " + fromDate + " , " + toDate);

		List<Map<String, Object>> rtnMapList = new ArrayList<Map<String, Object>>();
		PostMethod post = new PostMethod(elasticSearchUrl);
		HttpClient httpClient = new HttpClient();

		String query = "{\n"
				+ "    \"fields\": [\"mbrNo\",\"message\",\"requestId\",\"sysNo\",\"level\",\"host\",\"requestUri\",\"mbrTpCd\",\"caller_class_name\",\"caller_method_name\",\"caller_file_name\",\"caller_line_number\"],\n"
				+ "    \"size\": 100,\n"
				+ "    \"query\": {\n"
				+ "        \"filtered\" : {\n"
				+ "            \"query\" : {\n"
				+ "                \"query_string\" : {\n"
				+ "                    \"query\" : \"" + queryString + "\"\n"
				+ "                }\n"
				+ "            },\n"
				+ "            \"filter\" : {\n"
				+ "                \"bool\" : {\n"
				+ "                    \"must\": ["
				+ "                    		{\n"
				+ "                    			\"range\": {\n"
				+ "                    				\"@timestamp\": {\n"
				+ "                    					\"gte\" : \"" + fromDate + "\",\n"
				+ "                    					\"lte\" : \"" + toDate + "\"\n"
				+ "                    				}\n"
				+ "                    			}\n"
				+ "                    		}\n"
				+ "                    ],\n"
				+ "                    \"must_not\": []\n"
				+ "                }\n"
				+ "            }\n"
				+ "        }\n"
				+ "    },\n"
				+ "    \"aggs\": {}"
				+ "}";
		try {
			RequestEntity entity = new StringRequestEntity(query, "application/json","UTF-8");
			post.setRequestEntity(entity);
			httpClient.executeMethod(post);

			int statusCode = post.getStatusCode();
			log.debug(">>>>>getElasticsearchResultList statusCode : {}", statusCode);
			
			if (statusCode == HttpStatus.SC_OK) {	//정상인 경우만 실행
				String strResult = post.getResponseBodyAsString();
				log.debug(">>>>>getElasticsearchResultList getResponseBodyAsString : {}", strResult);
				
				if(!StringService.isEmpty(strResult)) {
					JSONParser jsonParser = new JSONParser();
					JSONObject jsonObject = (JSONObject)jsonParser.parse(strResult);
					JSONObject hitsObj = (JSONObject)jsonObject.get("hits");
					String totalCnt = hitsObj.get("total").toString();
					log.debug(">>>>>getElasticsearchResultList hits.total : {},{},{},{}", fromDate, toDate, queryString, totalCnt);
					
					JSONArray subHitsArray = (JSONArray)hitsObj.get("hits");
					for (int i=0; i<subHitsArray.size(); i++) {
						Map<String, Object> map = new HashMap<String, Object>();
						map.put("totalCnt", totalCnt);
						JSONObject jso = (JSONObject)subHitsArray.get(i);
						JSONObject fieldsObj = (JSONObject)jso.get("fields");
						if(fieldsObj != null) {
							String message = "";
							String requestId = "";
							if(fieldsObj.get("message") != null) {
								message = fieldsObj.get("message").toString();
								map.put("message", message);
							}
							if(fieldsObj.get("requestId") != null) {
								requestId = fieldsObj.get("requestId").toString();
								map.put("requestId", requestId);
							}
							log.debug(">>>>>getElasticsearchResultList requestId, message, : {},{},{},{},{}", fromDate, toDate, queryString, requestId, message);
							rtnMapList.add(map);
						}
					}
				}
			}
		} catch(Exception e) {
			log.warn(">>>>>getElasticsearchResultList Exception : {}", e);
		} finally {
			post.releaseConnection();
		}
		
		return rtnMapList;
	}
	
	/**
	 * IF 서버에 SMS 전송요청
	 */
	public void sendBatchAlarmSMS(String parameter) {
        String url = ifServerUrl + "/smsmms/sendMonitoringSmsMms";	// SMS open api url 

        BufferedReader reader = null;
        InputStream in = null;
        log.info("sendBatchAlarmSMS Contents : " + parameter);
        
        try {
            String encodedParameter = URLEncoder.encode(parameter, "UTF-8");

            URL postUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) postUrl.openConnection();

            connection.setConnectTimeout(3000);
            connection.setDoOutput(true);
            connection.setInstanceFollowRedirects(false);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("tokenKey", smsSecurityKey);

            OutputStream os = connection.getOutputStream();
            
            os.write(encodedParameter.getBytes("UTF-8"));
            os.flush();

            in =  connection.getInputStream();

            reader = new BufferedReader(new InputStreamReader(in), 8192);
            StringBuilder builder = new StringBuilder();
            String line = null;

            while((line=reader.readLine())!=null) {
                builder.append(line).append("\n");
            }
            
            reader.close();
            in.close();
        } catch (Exception e) {
        	log.warn("", e);
        } finally {
        	try {
        		reader.close();
                in.close();
        	} catch (IOException e) {
        		log.warn("", e);
        	}
        }
    }
	
	/**
	 * Elasticsearch 호출하여 쿼리 결과 return(기간조건) - 단건 실행
	 */
	public SysBatchResult callElasticsearchPeriod(String smsYn, String[] configArray) {
		SysBatchResult rtnResult = new SysBatchResult();
		
		try {
			//##실패쿼리===업무명(도메인 및 디바이스)===검색시간(분)===Aler SMS target group(SYS_ADMIN_SMS_SND_TGT)##
			//(OD_40023 || OD_40015 || OD_40001 || OD_40002) && app:NCP===주문(PLGRIM PC)===5M===ORD_PLGRIM_ELS_MNTRNG_SMS
			if(configArray != null && configArray.length == 4) {
				String failQuery = configArray[0];
				String bizName = configArray[1];
				String period = configArray[2].replace("M", "");
				String smsTargetGroup = configArray[3];
				
				String timeZone = ".000+09:00";
				SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:00");
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
				
				Calendar nowCal = Calendar.getInstance();
		    	Date nowDate = nowCal.getTime();
		    	int agoMinute = Integer.parseInt(period);
		    	nowCal.add(Calendar.MINUTE, (-1 * agoMinute));
		    	Date nowDate_agoMinute = nowCal.getTime();
		    	String strNowData = transFormat.format(nowDate) + timeZone;
				String strNowData_agoMinute = transFormat.format(nowDate_agoMinute)  + timeZone;
				
				List<Map<String, Object>> rtnMapList = this.getElasticsearchResultList(failQuery, strNowData_agoMinute, strNowData);
				log.debug(">>>>>callElasticsearchPeriod rtnMapList : {}", rtnMapList);
				
				if(rtnMapList != null && rtnMapList.size() > 0) {	//실패건수가 있다면 세부 message 출력을 위해 재호출
					String msg = "[" + bizName + "] " + sdf.format(nowDate_agoMinute) + "~" + sdf.format(nowDate) + ", 실패(" + rtnMapList.size() + ") 확인요청";
					
					for (int j=0; j<rtnMapList.size(); j++) {
						Map<String, Object> rtnMap = rtnMapList.get(j);
						if(rtnMap != null) {
							if(rtnMap.get("requestId") != null) {
								msg += ", " + rtnMap.get("requestId").toString().replace("\\", "").replace("\"", "");
							}
							if(rtnMap.get("message") != null) {
								msg += "-" + rtnMap.get("message").toString().replace("\\", "").replace("\"", "");
							}
						}
					}
					
					log.info(">>>>>callElasticsearchPeriod msg : {}",  msg);
					rtnResult.setBatchDscr(msg);
					
					if("Y".equals(smsYn)) {	//smsYn 파라매터가 Y인 경우에만 개발자에게 SMS 발송
						String parameter = "{'group':'" + smsTargetGroup + "','subject':'[" + bizName + "]','message':'" + msg + "'}";
						log.debug(">>>>>callElasticsearchPeriod parameter : {}", parameter);
						this.sendBatchAlarmSMS(parameter);
					}
				}
			}
			
		} catch(Exception ex) {
			log.warn(">>>>>callElasticsearchPeriod Exception : {}", ex);
		}
		
		return rtnResult;
	}
	
	
	/**
	 * Elasticsearch Url 호출하여 쿼리 속도 result 조회
	 */
	public List<Map<String, Object>> getElasticsearchSpeedList(String queryString, String fromDate, String toDate) {
		log.debug(">>>>>getElasticsearchResultList start : " + queryString + " , " + fromDate + " , " + toDate);

		List<Map<String, Object>> rtnMapList = new ArrayList<Map<String, Object>>();
		PostMethod post = new PostMethod(elasticSearchUrl);
		HttpClient httpClient = new HttpClient();

		String query = "{\n"
				+ "    \"fields\": [\"@timestamp\",\"requestId\",\"sid\",\"app\",\"sysNo\",\"userAgent\",\"remoteAddr\",\"referer\",\"pLoadTime\",\"pReadyTime\"],\n"
				+ "    \"size\": 10000,\n"
				+ "    \"query\": {\n"
				+ "        \"filtered\" : {\n"
				+ "            \"query\" : {\n"
				+ "                \"query_string\" : {\n"
				+ "                    \"query\" : \"" + queryString + "\"\n"
				+ "                }\n"
				+ "            },\n"
				+ "            \"filter\" : {\n"
				+ "                \"bool\" : {\n"
				+ "                    \"must\": ["
				+ "                    		{\n"
				+ "                    			\"range\": {\n"
				+ "                    				\"@timestamp\": {\n"
				+ "                    					\"gte\" : \"" + fromDate + "\",\n"
				+ "                    					\"lte\" : \"" + toDate + "\"\n"
				+ "                    				}\n"
				+ "                    			}\n"
				+ "                    		}\n"
				+ "                    ],\n"
				+ "                    \"must_not\": []\n"
				+ "                }\n"
				+ "            }\n"
				+ "        }\n"
				+ "    },\n"
				+ "    \"aggs\": {}"
				+ "}";
		try {
			RequestEntity entity = new StringRequestEntity(query, "application/json","UTF-8");
			post.setRequestEntity(entity);
			httpClient.executeMethod(post);

			int statusCode = post.getStatusCode();
			log.debug(">>>>>getElasticsearchResultList statusCode : {}", statusCode);
			
			if (statusCode == HttpStatus.SC_OK) {	//정상인 경우만 실행
				String strResult = post.getResponseBodyAsString();
				log.debug(">>>>>getElasticsearchSpeedList getResponseBodyAsString : {}", strResult);
				
				if(!StringService.isEmpty(strResult)) {
					JSONParser jsonParser = new JSONParser();
					JSONObject jsonObject = (JSONObject)jsonParser.parse(strResult);
					JSONObject hitsObj = (JSONObject)jsonObject.get("hits");
					String totalCnt = hitsObj.get("total").toString();
					log.debug(">>>>>getElasticsearchSpeedList hits.total : {},{},{},{}", fromDate, toDate, queryString, totalCnt);
					
					JSONArray subHitsArray = (JSONArray)hitsObj.get("hits");
					for (int i=0; i<subHitsArray.size(); i++) {
						Map<String, Object> map = new HashMap<String, Object>();
						map.put("totalCnt", totalCnt);
						JSONObject jso = (JSONObject)subHitsArray.get(i);
						JSONObject fieldsObj = (JSONObject)jso.get("fields");
						if(fieldsObj != null) {
							String timestamp = "";
							String requestId = "";
							String sid = "";
							String app = "";
							String sysNo = "";
							String userAgent = "";
							String remoteAddr = "";
							String referer = "";
							String pReadyTime = "";
							String pLoadTime = "";
							if(fieldsObj.get("@timestamp") != null) {
								timestamp = fieldsObj.get("@timestamp").toString();
								map.put("timestamp", timestamp);
							}
							if(fieldsObj.get("requestId") != null) {
								requestId = fieldsObj.get("requestId").toString();
								map.put("requestId", requestId);
							}
							if(fieldsObj.get("sid") != null) {
								sid = fieldsObj.get("sid").toString();
								map.put("sid", sid);
							}
							if(fieldsObj.get("app") != null) {
								app = fieldsObj.get("app").toString();
								map.put("app", app);
							}
							if(fieldsObj.get("sysNo") != null) {
								sysNo = fieldsObj.get("sysNo").toString();
								map.put("sysNo", sysNo);
							}
							if(fieldsObj.get("userAgent") != null) {
								userAgent = fieldsObj.get("userAgent").toString();
								map.put("userAgent", userAgent);
							}
							if(fieldsObj.get("remoteAddr") != null) {
								remoteAddr = fieldsObj.get("remoteAddr").toString();
								map.put("remoteAddr", remoteAddr);
							}
							if(fieldsObj.get("referer") != null) {
								referer = fieldsObj.get("referer").toString();
								map.put("referer", referer);
							}
							if(fieldsObj.get("pReadyTime") != null) {
								pReadyTime = fieldsObj.get("pReadyTime").toString();
								map.put("pReadyTime", pReadyTime);
							}
							if(fieldsObj.get("pLoadTime") != null) {
								pLoadTime = fieldsObj.get("pLoadTime").toString();
								map.put("pLoadTime", pLoadTime);
							}
							log.debug(">>>>>getElasticsearchSpeedList requestId, message, : {},{},{},{},{}", fromDate, toDate, queryString, requestId);
							rtnMapList.add(map);
						}
					}
				}
			}
		} catch(Exception e) {
			log.warn(">>>>>getElasticsearchSpeedList Exception : {}", e);
		} finally {
			post.releaseConnection();
		}
		
		return rtnMapList;
	}
	
	/**
	 * merge 사이트 속도 측정
	 */
	/*
	public int mergeElasticsearchSpeed(SysBatchSpeed sysBatchSpeed) throws Exception {
		return sysBatchRepository.mergeElasticsearchSpeed(sysBatchSpeed);
	}
	*/
}
