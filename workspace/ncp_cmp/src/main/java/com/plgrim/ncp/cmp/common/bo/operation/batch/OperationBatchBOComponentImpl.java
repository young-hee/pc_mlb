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
 * @since       2015. 3. 27       
 */
package com.plgrim.ncp.cmp.common.bo.operation.batch;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.quartz.CronExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import com.google.common.io.Files;
import com.netflix.config.DynamicPropertyFactory;
import com.netflix.config.DynamicStringProperty;
import com.plgrim.ncp.base.abstracts.AbstractComponent;
import com.plgrim.ncp.base.entities.datasource1.sys.SysChckHist;
import com.plgrim.ncp.cmp.common.bo.operation.OperationBatchBOComponent;
import com.plgrim.ncp.commons.result.SysBatchResult;
import com.plgrim.ncp.commons.service.SysBatchGenerateService;
import com.plgrim.ncp.commons.service.SysBatchService;
import com.plgrim.ncp.framework.commons.DateService;
import com.plgrim.ncp.framework.commons.StringService;
import com.plgrim.ncp.framework.systems.SystemClock;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class OperationBatchBOComponentImpl extends AbstractComponent implements OperationBatchBOComponent {

	@Autowired
	SysBatchService sysBatchService;
	
	@Autowired
	SysBatchGenerateService sysBatchGenerateService;
	
	@Value("${elasticsearch.monitor.cfg.location}")
	Resource elasticSearchCfgFileResource;

	@Autowired
	SystemClock clock;
	
	long checkInterval = 1000L;	//파일 체크주기
	private long lastCheckTime = 0;	//파일 최종체크시간
	private File cfgElasticSearchFile;	//config 파일
	private long lastModifyTime = 0;	//파일 최종수정시간
	private List<String> startList = new ArrayList<String>();	//biz 시작로그 List
	private List<String> completeList = new ArrayList<String>();	//biz 성공로그 List
	private List<String> failList = new ArrayList<String>();	//biz 실패로그 List
	private List<String> messageList = new ArrayList<String>();	//biz 업무명(도메인 및 디바이스) List
	private List<String> agoMinuteList = new ArrayList<String>();	//검색주기(분) : 이전 몇분까지의 elasticsearch log를 검사할지 List
	private List<String> smsAlertTgtList = new ArrayList<String>();	//Aler SMS target group(SYS_ADMIN_SMS_SND_TGT) : SMS Alert targer group List
	private List<String> errorRatioList = new ArrayList<String>();	//Alert SMS 전송 기준 에러율(%) List
	private String seperator = "===";	//config 파일 구분자
	
	/**
	 * 배치 리스트 조회 후 SMS alarm 전송
	 */
	public List<SysBatchResult> callSysBatchAlarmSMS(String smsYn) throws Exception {
		List<SysBatchResult> rtnResult = new ArrayList<SysBatchResult>();
		
		try {
			SysBatchResult paramData = new SysBatchResult();
			paramData.setBatchStatCd("Running");
			List<SysBatchResult> result = sysBatchService.selectListSysBatch(paramData);
			SimpleDateFormat sdf = new  SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(result != null) {
				for(int i = 0 ; i < result.size() ; i++){
					SysBatchResult sysBatchResult = result.get(i);
					if(sysBatchResult != null 
							&& sysBatchResult.getBatchExecutTimeTerm() > 0 && sysBatchResult.getBatchExecutExpectTime() > 0
							&& (sysBatchResult.getBatchExecutTimeTerm() > sysBatchResult.getBatchExecutExpectTime())) {
						
						if("EXECUT".equals(sysBatchResult.getBatchExecutStatCd())) {	//배치가 실행 중인 경우
							// 1. [현재시간 기준 몇초 차이 > 배치 실행 예상 시간]
							// 2. 현재도 배치가 실행 중인 경우 (종료시간이 비정상적으로 긴 경우)
							log.info("callSysBatchAlarmSMS : {}", sysBatchResult.getBatchClassId() + " , " + sysBatchResult.getBatchExecutExpectTime() + " , " + sysBatchResult.getBatchExecutStatCd() + " , " + sdf.format(sysBatchResult.getBatchBegDt()));
							setBatchAlarmSMS(sysBatchResult, smsYn, rtnResult);
						} else {
							boolean success = false;
							String cronExpr = sysBatchResult.getBatchExecutCyclCont();
							success = CronExpression.isValidExpression(cronExpr);	//cron expression valid
							if(success) {
								Date agoDt = sysBatchResult.getBatchBegDt();	//배치 최종시작일시
								Date nowDt = new Date();	//현재시간
								CronExpression cronExpression = new CronExpression(cronExpr);
								Date nextDt = cronExpression.getNextValidTimeAfter(agoDt);
								int compare = nowDt.compareTo(nextDt);
								log.info("callSysBatchAlarmSMS : {}", sysBatchResult.getBatchClassId() + " , " + sysBatchResult.getBatchExecutExpectTime() + " , " + cronExpr + " , agoDt => " + sdf.format(agoDt) + " , nowDt => " + sdf.format(nowDt) + " , nextDt => " + sdf.format(nextDt) + " , compare => " + compare);
								
								if(compare > 0) {
									// 1. [현재시간 기준 몇초 차이 > 배치 실행 예상 시간]
									// 2. 현재시간 > 배치 최종시작일시 이후 cron expression 기준 바로 다음 실행예정일시 (정해진 시간에 배치가 실행하지 않는 경우)
									setBatchAlarmSMS(sysBatchResult, smsYn, rtnResult);
								}
							}
						}
					}
				}
			}
		} catch(Exception ex) {
			log.warn("callSysBatchAlarmSMS > Exception", ex);
		}
		
		return rtnResult;
	}
	
	/**
	 * IF 서버에 SMS 전송요청
	 */
	public void sendBatchAlarmSMS(String parameter) {
        sysBatchService.sendBatchAlarmSMS(parameter);
    }
	
	/**
	 * Elasticsearch 호출하여 쿼리 결과 return
	 */
	public List<SysBatchResult> getElasticsearchList(String fromDate, String toDate) {
		List<SysBatchResult> rtnResultList = new ArrayList<SysBatchResult>();
		
		try {
			cfgElasticSearchFile = elasticSearchCfgFileResource.getFile();
			log.debug(">>>>>getElasticsearchList ElasticSearch config location: {}", cfgElasticSearchFile.getAbsolutePath());
			this.checkElasticSearchConfig();
			String strFromDate = fromDate + ".000+09:00";
			String strToDate = toDate + ".000+09:00";
			DecimalFormat df = new DecimalFormat("0.##");
			
			for(int i=0; i<startList.size(); i++){
				String startCnt = sysBatchService.getElasticsearchCnt(startList.get(i), strFromDate, strToDate);
				String completeCnt = sysBatchService.getElasticsearchCnt(completeList.get(i), strFromDate, strToDate);
				String failCnt = sysBatchService.getElasticsearchCnt(failList.get(i), strFromDate, strToDate);
				double errorRatio = 0;
				if(Integer.parseInt(startCnt) > 0) {
					errorRatio = (double)(((double)Integer.parseInt(failCnt) / (double)Integer.parseInt(startCnt)) * 100);
				}
				
				SysBatchResult result = new SysBatchResult();
				String msg = "[" + messageList.get(i) + "] " + fromDate + "~" + toDate + ", 성공(" + completeCnt + ")/실패(" + failCnt 
						+ "), 현재실패율(" + df.format(errorRatio) + "%)/기준실패율(" + errorRatioList.get(i) + "%)";
				if(errorRatio >= Integer.parseInt(errorRatioList.get(i))) {	//기준실패율보다 현재실패율이 높다면 SMS 발송
					msg += " 확인요청";
					if(failCnt != null && !"0".equals(failCnt)) {	//실패건수가 있다면 세부 message 출력을 위해 재호출
						List<Map<String, Object>> rtnMapList = sysBatchService.getElasticsearchResultList(failList.get(i), strFromDate, strToDate);
						log.debug(">>>>>getElasticsearchList rtnMapList : {}", rtnMapList);
						if(rtnMapList != null && rtnMapList.size() > 0) {
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
						}
					}
				}
				result.setBatchDscr(msg);
				rtnResultList.add(result);
			}
		} catch(Exception ex) {
			log.warn(">>>>>getElasticsearchList Exception : {}", ex);
		}
		
		return rtnResultList;
	}
	
	/**
	 * Elasticsearch 호출하여 쿼리 결과 return(Batch)
	 */
	public List<SysBatchResult> getElasticsearchBatchList(String smsYn) {
		List<SysBatchResult> rtnResultList = new ArrayList<SysBatchResult>();
		
		try {
			cfgElasticSearchFile = elasticSearchCfgFileResource.getFile();
			log.debug(">>>>>getElasticsearchList ElasticSearch config location: {}", cfgElasticSearchFile.getAbsolutePath());
			this.checkElasticSearchConfig();
			String timeZone = ".000+09:00";
			SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:00");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			DecimalFormat df = new DecimalFormat("0.##");
			
			for(int i=0; i<startList.size(); i++){
				Calendar nowCal = Calendar.getInstance();
		    	Date nowDate = nowCal.getTime();
		    	int agoMinute = Integer.parseInt(agoMinuteList.get(i));
		    	nowCal.add(Calendar.MINUTE, (-1 * agoMinute));
		    	Date nowDate_agoMinute = nowCal.getTime();
		    	String strNowData = transFormat.format(nowDate) + timeZone;
				String strNowData_agoMinute = transFormat.format(nowDate_agoMinute)  + timeZone;
		    	
				String startCnt = sysBatchService.getElasticsearchCnt(startList.get(i), strNowData_agoMinute, strNowData);
				String completeCnt = sysBatchService.getElasticsearchCnt(completeList.get(i), strNowData_agoMinute, strNowData);
				String failCnt = sysBatchService.getElasticsearchCnt(failList.get(i), strNowData_agoMinute, strNowData);
				
				double errorRatio = 0;
				if(Integer.parseInt(startCnt) > 0) {
					errorRatio = (double)(((double)Integer.parseInt(failCnt) / (double)Integer.parseInt(startCnt)) * 100);
				}
				
				SysBatchResult result = new SysBatchResult();
				String msg = sdf.format(nowDate_agoMinute) + "~" + sdf.format(nowDate) + ", 성공(" + completeCnt + ")/실패(" + failCnt 
						+ "), 현재실패율(" + df.format(errorRatio) + "%)/기준실패율(" + errorRatioList.get(i) + "%)";
				if(errorRatio >= Integer.parseInt(errorRatioList.get(i))) {	//기준실패율보다 현재실패율이 높다면 SMS 발송
					msg += " 확인요청";
					if(failCnt != null && !"0".equals(failCnt)) {	//실패건수가 있다면 세부 message 출력을 위해 재호출
						List<Map<String, Object>> rtnMapList = sysBatchService.getElasticsearchResultList(failList.get(i), strNowData_agoMinute, strNowData);
						log.debug(">>>>>getElasticsearchBatchList rtnMapList : {}", rtnMapList);
						if(rtnMapList != null && rtnMapList.size() > 0) {
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
						}
					}
					
					if("Y".equals(smsYn)) {	//smsYn 파라매터가 Y인 경우에만 개발자에게 SMS 발송
						String parameter = "{'group':'" + smsAlertTgtList.get(i) + "','subject':'[" + messageList.get(i) + "]','message':'" + msg + "'}";
						log.debug(">>>>>getElasticsearchBatchList parameter : {}", parameter);
						this.sendBatchAlarmSMS(parameter);
					}
				}
				log.info(">>>>>getElasticsearchBatchList msg : {}",  msg);
				result.setBatchDscr(msg);
				rtnResultList.add(result);
			}
		} catch(Exception ex) {
			log.warn(">>>>>getElasticsearchList Exception : {}", ex);
		}
		
		return rtnResultList;
	}

	/**
	 * weekly 모니터링 쿼리 결과 return
	 */
	public List<SysChckHist> getWeeklyMonitor(String dayType, String smsYn) {
		return sysBatchGenerateService.getWeeklyMonitor(dayType, smsYn);
	}

	/**
	 * Elasticsearch 호출하여 쿼리 결과 return(기간조건)
	 */
	public List<SysBatchResult> getElasticsearchPeriodList(String smsYn, String period) {
		List<SysBatchResult> rtnResultList = new ArrayList<SysBatchResult>();
		
		try {
			DynamicStringProperty els_monitor_list = DynamicPropertyFactory.getInstance().getStringProperty("els_monitor_list", "");
			log.debug("els_monitor_list prop : {}", els_monitor_list.getValue());
			if(StringService.isEmpty(els_monitor_list.getValue())) {
				return rtnResultList;
			}
			
			String els_monitorList = els_monitor_list.getValue();
			String[] els_monitorArray = els_monitorList.split(",");
			for(int i=0; i<els_monitorArray.length; i++){
				DynamicStringProperty monitor_config = DynamicPropertyFactory.getInstance().getStringProperty(els_monitorArray[i] + "_monitor_config", "");
				log.debug("monitor_config prop : {}", monitor_config.getValue());
				if(!StringService.isEmpty(monitor_config.getValue())) {
					String elsConfig = monitor_config.getValue();
					String[] elsConfigArray = elsConfig.split(seperator);
					if(elsConfigArray != null && elsConfigArray.length == 4) {
						if(period.equals(elsConfigArray[2].replace("M", ""))) {	//설정 배치주기에 따른 쿼리만 검색
							SysBatchResult rtnResult = sysBatchService.callElasticsearchPeriod(smsYn, elsConfigArray);
							if(rtnResult != null) {
								rtnResultList.add(rtnResult);
							}
						}
					}
				}
			}
		} catch(Exception ex) {
			log.warn(">>>>>getElasticsearchPeriodList Exception : {}", ex);
		}
		
		return rtnResultList;
	}
	
	
	
	/**
	 * 이상있는 배치 후 SMS 문구 생성 후 alarm 전송
	 */
	private void setBatchAlarmSMS(SysBatchResult sysBatchResult, String smsYn, List<SysBatchResult> rtnResult) throws Exception {
		String msg = "[" + sysBatchResult.getBatchGrpNm() + "]" + sysBatchResult.getBatchNm() 
			+ " / " + sysBatchResult.getBatchClassId().replace("com.plgrim.ncp.batch.server.job.", "") 
			+ " / " + sysBatchResult.getBatchExecutCyclCont()
			+ " / " + sysBatchResult.getBatchExecutExpectTime()
			+ " / " + DateService.parseDefaultFormat(sysBatchResult.getBatchBegDt());
			if(sysBatchResult.getBatchEndDt() != null) {
				msg += "~" + DateService.parseDefaultFormat(sysBatchResult.getBatchEndDt());
			}
			msg +=  "(" + sysBatchResult.getBatchExecutStatCd() + ") 확인요청" ;
		String parameter = "{'group':'BATCH_MNTRNG_SMS','subject':'[PLGRIM 배치 모니터링]','message':'" + msg + "'}";
		
		if("Y".equals(smsYn)) {	//smsYn 파라매터가 Y인 경우에만 개발자에게 SMS 발송
			this.sendBatchAlarmSMS(parameter);
		}
		sysBatchResult.setBatchDscr(msg);
		rtnResult.add(sysBatchResult);
	}
	
	/**
	 * config 파일 변경 여부 확인
	 */
	private void checkElasticSearchConfig() {
		if (clock.isNotOver(lastCheckTime, checkInterval)) {
			return;
		}
		checkInterval = clock.currentTimeMillis();

		if ((cfgElasticSearchFile.lastModified() == lastModifyTime)) {
			return;
		}
		lastModifyTime = cfgElasticSearchFile.lastModified();
		 
		try {
			loadElasticSearchConfig();
		} catch (Exception e) {
			log.warn(">>>>>checkElasticSearchConfig Exception : {}",  e);
		}
	}
	
	/**
	 * config 파일 reload (synchronized)
	 */
	private void loadElasticSearchConfig() {
		synchronized (this) {
			try {
				this.reloadElasticSearchConfig();
			} catch (IOException e) {
				throw new IllegalArgumentException(e);
			}
		}
	}
	
	/**
	 * config 파일 reload 처리
	 */
	private void reloadElasticSearchConfig() throws IOException {
		List<String> lines = Files.readLines(cfgElasticSearchFile, Charset.defaultCharset());
		List<String> newStartList = new ArrayList<String>();
		List<String> newCompleteList = new ArrayList<String>();
		List<String> newFailList = new ArrayList<String>();
		List<String> newMessageList = new ArrayList<String>();
		List<String> newAgoMinuteList = new ArrayList<String>();
		List<String> newSmsAlertTgtList = new ArrayList<String>();
		List<String> newErrorRatioList = new ArrayList<String>();
		
		//시작로그===성공로그===실패로그===업무명(도메인 및 디바이스)===검색주기(분)===Aler SMS target group(SYS_ADMIN_SMS_SND_TGT)===Alert SMS 전송 기준 에러율(%)
		//AddOrder_Start && app:NCP===AddOrder_Complete && app:NCP===(AddOrder_OrderPGFail || AddOrder_OrderCompleteFail || AddOrder_Fail) && app:NCP===주문(PLGRIM PC)===30M===ORD_PLGRIM_ELS_MNTRNG_SMS===5%
		for (String cfgLine : lines) {
			try {
				if(StringUtils.isBlank(cfgLine) || "#".equals(cfgLine.substring(0, 1))) {	//주석처리
					continue;
				}
				String[] cfgLineArr = cfgLine.split(seperator);
				if(cfgLineArr != null && cfgLineArr.length == 7) {
					log.info(">>>>>reloadElasticSearchConfig : {}", cfgLine);
					newStartList.add(cfgLineArr[0]);
					newCompleteList.add(cfgLineArr[1]);
					newFailList.add(cfgLineArr[2]);
					newMessageList.add(cfgLineArr[3]);
					newAgoMinuteList.add(cfgLineArr[4].replace("M", ""));
					newSmsAlertTgtList.add(cfgLineArr[5]);
					newErrorRatioList.add(cfgLineArr[6].replace("%", ""));
				}
			} catch (Exception e) {
				log.warn(">>>>>reloadElasticSearchConfig Exception : {}",  e);
			}
		}
		startList = newStartList;
		completeList = newCompleteList;
		failList = newFailList;
		messageList = newMessageList;
		agoMinuteList = newAgoMinuteList;
		smsAlertTgtList = newSmsAlertTgtList;
		errorRatioList = newErrorRatioList;
	}
	
	/**
	 * Elasticsearch 호출하여 쿼리 속도결과 return
	 */
	public List<SysBatchResult> getElasticsearchSpeedList(String fromDate, String toDate) {
		List<SysBatchResult> rtnResultList = new ArrayList<SysBatchResult>();
		
		try {
			String timeZone = ".000+09:00";
			SimpleDateFormat stf = new SimpleDateFormat("yyyyMMddHH");
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:00:00");
			
			Date batchDtFrom = stf.parse(fromDate);
			Date batchDtTo = stf.parse(toDate);
			
			String strFromDate = df.format(batchDtFrom) + timeZone;
			String strToDate = df.format(batchDtTo)  + timeZone;

			
			List<Map<String, Object>> rtnMapList = sysBatchService.getElasticsearchSpeedList("app:'NCM' && _exists_:pReadyTime", strFromDate, strToDate);
			log.debug(">>>>>getElasticsearchSpeedList rtnMapList : {}", rtnMapList);
			
			if(rtnMapList != null && rtnMapList.size() > 0) {
				SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
				/*
				for (int j=0; j<rtnMapList.size(); j++) {
					String msg = "";
					SysBatchResult result = new SysBatchResult();
					SysBatchSpeed sysBatchSpeed = new SysBatchSpeed();
					Map<String, Object> rtnMap = rtnMapList.get(j);
					if(rtnMap != null) {
						if(rtnMap.get("timestamp") != null) {
							String timestamp = rtnMap.get("timestamp").toString().replace("\\", "").replace("\"", "").replace("[", "").replace("]", "");
							msg += timestamp;
							Date timestampDt = transFormat.parse(timestamp);
						    Calendar cal = Calendar.getInstance();
						    cal.setTime(timestampDt);
						    cal.add(Calendar.HOUR_OF_DAY, 9);
						    timestampDt = cal.getTime();
							sysBatchSpeed.setElsTimestamp(timestampDt);
						}
						if(rtnMap.get("requestId") != null) {
							String requestId = rtnMap.get("requestId").toString().replace("\\", "").replace("\"", "").replace("[", "").replace("]", "");
							msg += ", " + requestId;
							sysBatchSpeed.setRequestid(requestId);
						}
						if(rtnMap.get("sid") != null) {
							String sid = rtnMap.get("sid").toString().replace("\\", "").replace("\"", "").replace("[", "").replace("]", "");
							msg += ", " + sid;
							sysBatchSpeed.setSid(sid);
						}
						if(rtnMap.get("app") != null) {
							String app = rtnMap.get("app").toString().replace("\\", "").replace("\"", "").replace("[", "").replace("]", "");
							msg += ", " + app;
							sysBatchSpeed.setApp(app);
						}
						if(rtnMap.get("sysNo") != null) {
							String sysNo = rtnMap.get("sysNo").toString().replace("\\", "").replace("\"", "").replace("[", "").replace("]", "");
							msg += ", " + sysNo;
							sysBatchSpeed.setSysno(sysNo);
						}
						if(rtnMap.get("userAgent") != null) {
							String userAgent = rtnMap.get("userAgent").toString().replace("\\", "").replace("\"", "").replace("[", "").replace("]", "");
							msg += ", " + userAgent;
							sysBatchSpeed.setUseragent(userAgent);
						}
						if(rtnMap.get("remoteAddr") != null) {
							String remoteAddr = rtnMap.get("remoteAddr").toString().replace("\\", "").replace("\"", "").replace("[", "").replace("]", "");
							msg += ", " + remoteAddr;
							sysBatchSpeed.setRemoteaddr(remoteAddr);
						}
						if(rtnMap.get("referer") != null) {
							String referer = rtnMap.get("referer").toString().replace("\\", "").replace("\"", "").replace("[", "").replace("]", "");
							msg += ", " + referer;
							sysBatchSpeed.setReferer(referer);
						}
						if(rtnMap.get("pReadyTime") != null) {
							String pReadyTime = rtnMap.get("pReadyTime").toString().replace("\\", "").replace("\"", "").replace("[", "").replace("]", "");
							msg += ", " + pReadyTime;
							sysBatchSpeed.setPreadytime(Double.parseDouble(pReadyTime));
						}
						if(rtnMap.get("pLoadTime") != null) {
							String pLoadTime = rtnMap.get("pLoadTime").toString().replace("\\", "").replace("\"", "").replace("[", "").replace("]", "");
							msg += ", " + pLoadTime;
							sysBatchSpeed.setPloadtime(Double.parseDouble(pLoadTime));
						}
					}
					result.setBatchDscr(msg);
					rtnResultList.add(result);
					
					sysBatchService.mergeElasticsearchSpeed(sysBatchSpeed);
				}
				*/
			}
		} catch(Exception ex) {
			log.warn(">>>>>getElasticsearchList Exception : {}", ex);
		}
		
		return rtnResultList;
	}
	
}
