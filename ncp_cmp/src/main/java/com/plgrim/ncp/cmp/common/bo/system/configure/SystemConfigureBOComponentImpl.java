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
package com.plgrim.ncp.cmp.common.bo.system.configure;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.plgrim.ncp.cmp.common.bo.system.SystemConfigureBOComponent;
import com.plgrim.ncp.commons.result.SysConfigResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import com.plgrim.ncp.base.abstracts.AbstractComponent;
import com.plgrim.ncp.base.entities.datasource1.sys.SysDynmcConfig;
import com.plgrim.ncp.base.entities.datasource1.sys.SysDynmcConfigDetail;
import com.plgrim.ncp.commons.data.SysConfigDTO;
import com.plgrim.ncp.commons.grid.GridCommand;
import com.plgrim.ncp.commons.service.SysConfigService;
import com.plgrim.ncp.commons.util.BOSecurityUtil;
import com.plgrim.ncp.framework.commons.DateService;

@Transactional(value = "transactionManager")
@Component
public class SystemConfigureBOComponentImpl extends AbstractComponent implements SystemConfigureBOComponent {

	@Autowired
	SysConfigService sysConfigService;
	
	@Autowired
	private GridCommand gridCommand;
	
	/**
	 * 설정등록 처리
	 */
	public void addSysConfig(SysConfigDTO paramData) throws Exception {
		String loginId = BOSecurityUtil.getLoginId();
		SysDynmcConfig sysDynmcConfig = new SysDynmcConfig();
		sysDynmcConfig.setMallId(paramData.getSysDynmcConfig().getMallId());
		sysDynmcConfig.setConfigNm(paramData.getSysDynmcConfig().getConfigNm());
		sysDynmcConfig.setConfigBegDt(paramData.getSysDynmcConfig().getConfigBegDt());
		sysDynmcConfig.setConfigEndDt(paramData.getSysDynmcConfig().getConfigEndDt());
		sysDynmcConfig.setConfigSectCd(paramData.getSysDynmcConfig().getConfigSectCd());
		sysDynmcConfig.setUseYn(paramData.getSysDynmcConfig().getUseYn());
		sysDynmcConfig.setConfigDscr(paramData.getSysDynmcConfig().getConfigDscr());
		sysDynmcConfig.setRegtrId(loginId);
		sysDynmcConfig.setUdterId(loginId);
		//설정 등록 처리
		sysConfigService.insertSysDynmcConfig(sysDynmcConfig);
		
		if(paramData.getSysDynmcConfigDetailList() != null && paramData.getSysDynmcConfigDetailList().size() > 0) {
			for(int i =0 ; i < paramData.getSysDynmcConfigDetailList().size() ; i++){
				SysDynmcConfigDetail SysDynmcConfigDetail = paramData.getSysDynmcConfigDetailList().get(i);
				SysDynmcConfigDetail.setMallId(paramData.getSysDynmcConfig().getMallId());
				SysDynmcConfigDetail.setConfigNm(paramData.getSysDynmcConfig().getConfigNm());
				SysDynmcConfigDetail.setConfigBegDt(paramData.getSysDynmcConfig().getConfigBegDt());
				SysDynmcConfigDetail.setRegtrId(loginId);
				SysDynmcConfigDetail.setUdterId(loginId);
				//설정상세 등록 처리
				sysConfigService.insertSysDynmcConfigDetail(SysDynmcConfigDetail);
			}
		}
	}
	
	/**
	 * 설정값 일괄 등록/수정 처리
	 */
	public void updateSysDynmcConfigDetailList(List<SysConfigDTO> updateList) throws Exception {
		String loginId = BOSecurityUtil.getLoginId();
		if(updateList != null && updateList.size() > 0) {
			for(int i =0 ; i < updateList.size() ; i++){
				SysDynmcConfigDetail SysDynmcConfigDetail = updateList.get(i).getSysDynmcConfigDetail();
				SysDynmcConfigDetail.setRegtrId(loginId);
				SysDynmcConfigDetail.setUdterId(loginId);
				//설정상세 등록 처리
				sysConfigService.insertSysDynmcConfigDetail(SysDynmcConfigDetail);
			}
		}
	}
	
	/**
	 * 설정상세 삭제 처리
	 */
	public void deleteSysDynmcConfigDetail(SysDynmcConfigDetail paramData) throws Exception {
		sysConfigService.deleteSysDynmcConfigDetail(paramData);
	}
	
	/**
	 * 설정 수정 처리
	 */
	public void updateSysDynmcConfig(SysConfigDTO paramData) throws Exception {
		String loginId = BOSecurityUtil.getLoginId();
		String agoMallId = paramData.getAgoSysDynmcConfig().getMallId();
	    String agoConfigNm = paramData.getAgoSysDynmcConfig().getConfigNm();
	    String agoConfigBegDt = DateService.parseString(paramData.getAgoSysDynmcConfig().getConfigBegDt(), "yyyyMMdd");
	    paramData.getSysDynmcConfig().setConfigBegDt(DateService.getStartOfDate(paramData.getSysDynmcConfig().getConfigBegDt()));	//2017.01.01 00:00:000 
	    paramData.getSysDynmcConfig().setConfigEndDt(DateService.getEndOfDate(paramData.getSysDynmcConfig().getConfigEndDt()));	//2017.10.31 23:59:999 
	    paramData.getSysDynmcConfig().setUdterId(loginId);

	    //PK 값이 변경되어서 detail 테이블 값을 삭제하고 진행해야 하는 경우 구분값
	    boolean isPKChange = true;
	    //SYS_DYNMC_CONFIG 테이블 PK 가 변경이 없는 경우
	    if(agoMallId.equals(paramData.getSysDynmcConfig().getMallId()) && agoConfigNm.equals(paramData.getSysDynmcConfig().getConfigNm())
	    		&& agoConfigBegDt.equals(DateService.parseString(paramData.getSysDynmcConfig().getConfigBegDt(), "yyyyMMdd"))) {
	    	isPKChange = false;
	    }

    	if(isPKChange) {
    		//SYS_DYNMC_CONFIG 테이블 select insert
        	sysConfigService.updateAgoSysDynmcConfig(paramData);
        	
        	//SYS_DYNMC_CONFIG_DETAIL 테이블 update
        	sysConfigService.updateAgoSysDynmcConfigDetail(paramData);
        	
    		//이전 SYS_DYNMC_CONFIG 테이블 data 삭제
        	sysConfigService.deleteAgoSysDynmcConfig(paramData);
    	} else {
    		//SYS_DYNMC_CONFIG 테이블 update
        	sysConfigService.updateSysDynmcConfig(paramData);
    	}
    	
    	List<SysConfigDTO>  deleteList = gridCommand.getDeleteList(paramData.getGridList());
	    List<SysConfigDTO>  updateList = gridCommand.getUpdateList(paramData.getGridList());
	    List<SysConfigDTO>  createList = gridCommand.getCreateList(paramData.getGridList());
	    
    	if(deleteList != null && deleteList.size() > 0) {
			for(int i =0 ; i < deleteList.size() ; i++){
				SysDynmcConfigDetail SysDynmcConfigDetail = deleteList.get(i).getSysDynmcConfigDetail();
				SysDynmcConfigDetail.setMallId(paramData.getSysDynmcConfig().getMallId());
				SysDynmcConfigDetail.setConfigNm(paramData.getSysDynmcConfig().getConfigNm());
				SysDynmcConfigDetail.setConfigBegDt(paramData.getSysDynmcConfig().getConfigBegDt());
				//SYS_DYNMC_CONFIG_DETAIL 테이블 delete
				sysConfigService.deleteSysDynmcConfigDetail(SysDynmcConfigDetail);
			}
		}
    	if(updateList != null && updateList.size() > 0) {
			for(int i =0 ; i < updateList.size() ; i++){
				SysDynmcConfigDetail SysDynmcConfigDetail = updateList.get(i).getSysDynmcConfigDetail();
				SysDynmcConfigDetail.setMallId(paramData.getSysDynmcConfig().getMallId());
				SysDynmcConfigDetail.setConfigNm(paramData.getSysDynmcConfig().getConfigNm());
				SysDynmcConfigDetail.setConfigBegDt(paramData.getSysDynmcConfig().getConfigBegDt());
				SysDynmcConfigDetail.setRegtrId(loginId);
				SysDynmcConfigDetail.setUdterId(loginId);
				//SYS_DYNMC_CONFIG_DETAIL 테이블 update
				sysConfigService.insertSysDynmcConfigDetail(SysDynmcConfigDetail);
			}
		}
    	if(createList != null && createList.size() > 0) {
			for(int i =0 ; i < createList.size() ; i++){
				SysDynmcConfigDetail SysDynmcConfigDetail = createList.get(i).getSysDynmcConfigDetail();
				SysDynmcConfigDetail.setMallId(paramData.getSysDynmcConfig().getMallId());
				SysDynmcConfigDetail.setConfigNm(paramData.getSysDynmcConfig().getConfigNm());
				SysDynmcConfigDetail.setConfigBegDt(paramData.getSysDynmcConfig().getConfigBegDt());
				SysDynmcConfigDetail.setRegtrId(loginId);
				SysDynmcConfigDetail.setUdterId(loginId);
				//SYS_DYNMC_CONFIG_DETAIL 테이블 insert
				sysConfigService.insertSysDynmcConfigDetail(SysDynmcConfigDetail);
			}
		}
	}


	/**
	 * 설정조회 리스트
	 */
	public List<SysConfigResult> selectListSysConfig(SysConfigDTO paramData) throws Exception {
		return sysConfigService.selectListSysConfig(paramData);
	}

	/**
	 * 설정조회 리스트 총 갯수 조회
	 */
	public long selectListCountSysConfig(SysConfigDTO paramData) throws Exception {
		return sysConfigService.selectListCountSysConfig(paramData);
	}

	/**
	 * 설정조회
	 */
	public SysConfigResult selectSysConfig(SysConfigDTO paramData) throws Exception {
		return sysConfigService.selectSysConfig(paramData);
	}

	/**
	 * 설정조회 목록 엑셀 다운로드
	 */
	public List<Map<String, String>> selectListSysConfigExcel(SysConfigDTO paramData) throws Exception {

		List<Map<String, String>> configResultList = new ArrayList();

		List<SysConfigResult> resultList = sysConfigService.selectListSysConfig(paramData);

		if(resultList != null && resultList.size() > 0) {
			int excelNo = 1;
			for(int i =0 ; i < resultList.size() ; i++){
				SysConfigResult sysConfigResult = resultList.get(i);

				LinkedHashMap<String,String> dataMap = new LinkedHashMap<String,String>();

				dataMap.put("excelNo", String.valueOf(excelNo));
				dataMap.put("mallId", sysConfigResult.getSysDynmcConfigDetail().getMallId());
				dataMap.put("configNm",  sysConfigResult.getSysDynmcConfigDetail().getConfigNm());
				dataMap.put("configBegDt",  getDateService().parseDefaultFormat(sysConfigResult.getSysDynmcConfigDetail().getConfigBegDt()));
				dataMap.put("configEndDt",  getDateService().parseDefaultFormat(sysConfigResult.getSysDynmcConfig().getConfigEndDt()));
				dataMap.put("configDscr",  sysConfigResult.getSysDynmcConfig().getConfigDscr());
				dataMap.put("configKey",  sysConfigResult.getSysDynmcConfigDetail().getConfigKey());
				dataMap.put("configVal",  sysConfigResult.getSysDynmcConfigDetail().getConfigVal());
				dataMap.put("configSectCd",  sysConfigResult.getSysDynmcConfig().getConfigSectCd());
				dataMap.put("useYn",  "Y".equals(sysConfigResult.getSysDynmcConfigDetail().getUseYn())?"사용":"미사용"  );
				dataMap.put("regtrNm",  sysConfigResult.getSysDynmcConfigDetail().getRegtrId());
				dataMap.put("regDt",   getDateService().parseDefaultFormat(sysConfigResult.getSysDynmcConfigDetail().getRegDt()));
				dataMap.put("udterNm",  sysConfigResult.getSysDynmcConfigDetail().getUdterId() );
				dataMap.put("udtDt",   getDateService().parseDefaultFormat(sysConfigResult.getSysDynmcConfigDetail().getUdtDt()));

				configResultList.add(dataMap);
				excelNo++;
			}
		}
		return configResultList;
	}

	/**
	 * 설정상세 리스트 조회
	 */
	public List<SysConfigResult> selectSysConfigDetailList(SysConfigDTO paramData) throws Exception {
		return sysConfigService.selectSysConfigDetailList(paramData);
	}

	/**
	 * 서킷브래이커 스케줄 대상 설정조회 팝업 리스트
	 */
	public List<SysConfigResult> selectListSysConfigPop(SysConfigDTO paramData) throws Exception {
		return sysConfigService.selectListSysConfigPop(paramData);
	}

	/**
	 * 서킷브래이커 스케줄 대상 설정조회 팝업 리스트 총 갯수 조회
	 */
	public long selectListCountSysConfigPop(SysConfigDTO paramData) throws Exception {
		return sysConfigService.selectListCountSysConfigPop(paramData);
	}

}
