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
package com.plgrim.ncp.cmp.common.bo.history.access;

import com.plgrim.ncp.base.abstracts.AbstractComponent;
import com.plgrim.ncp.cmp.common.bo.history.HistoryAccessBOComponent;
import com.plgrim.ncp.commons.data.FormSysAdmlogDTO;
import com.plgrim.ncp.commons.result.AdmLogResult;
import com.plgrim.ncp.commons.service.SysAdmlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

// @Slf4j
@Component
@Transactional(value = "transactionManager")
public class HistoryAccessBOComponentImpl extends AbstractComponent implements HistoryAccessBOComponent {

	
	@Autowired
    SysAdmlogService service;
	
	
	/**
	 * 운영자로그인로그 목록조회
	 * @param paramData
	 * @return
	 * @throws Exception
	 */
	public List<AdmLogResult> selectListLoginLog(FormSysAdmlogDTO paramData) throws Exception {
		return service.selectListLoginLog(paramData);
	}
	
	/**
	 * 운영자로그인로그 목록 엑셀다운로드
	 * @param paramData
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, String>> selectListLoginLogExcel( FormSysAdmlogDTO paramData) throws Exception{
		
		List<Map<String, String>> resultList = new ArrayList();
		
		Iterator<AdmLogResult> iterator = service.selectListLoginLog(paramData).iterator();
		
		int excelNo = 1;
		while(iterator.hasNext()) {
			AdmLogResult result = iterator.next();
			
			LinkedHashMap<String,String> dataMap = new LinkedHashMap<String,String>();
			
			dataMap.put("excelNo", String.valueOf(excelNo));
			dataMap.put("boSiteIds", result.getSysAdminLoginLog().getBoSiteId() );
			dataMap.put("adminId", result.getSysAdmin().getAdminId() );
			dataMap.put("adminNm", result.getSysAdmin().getAdminNm() );
			dataMap.put("conectIp", result.getSysAdminLoginLog().getConectIp() );
			dataMap.put("conectDt", getDateService().parseString( result.getSysAdminLoginLog().getConectDt() , "yyyy-MM-dd") );
			dataMap.put("comNm", result.getComNm() );
			dataMap.put("email", result.getSysAdmin().getEmail() );
		
			resultList.add(dataMap);
			excelNo++;
		}
		
		return resultList;
		
	}

	/**
	 * 운영자로그인로그 총로그수
	 * @param paramData
	 * @return
	 * @throws Exception
	 */
	public long selectCountLoginLog( FormSysAdmlogDTO paramData) throws Exception {
		return service.selectCountLoginLog(paramData);
	}
	
	/**
	 * 운영자메뉴내역 로그조회
	 * @param paramData
	 * @return
	 * @throws Exception
	 */
	public List<AdmLogResult> selectListMenuLog(FormSysAdmlogDTO paramData) throws Exception {
		return service.selectListMenuLog(paramData);
	}
	
	/**
	 * 운영자메뉴내역 총로그수
	 * @param paramData
	 * @return
	 * @throws Exception
	 */
	public long selectCountMenuLog( FormSysAdmlogDTO paramData) throws Exception {
		return service.selectCountMenuLog(paramData);
	}
	
	/**
	 * 운영자메뉴->File이용 내역
	 * @param paramData
	 * @return
	 * @throws Exception
	 */
	public List<AdmLogResult> selectListFileLog(FormSysAdmlogDTO paramData) throws Exception {
		return service.selectListFileLog(paramData);
	}
	
	/**
	 * 운영자 메뉴(FILE) 이용내역 엑셀다운로드
	 * @param paramData
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, String>> selectListFileLogExcel( FormSysAdmlogDTO paramData) throws Exception{
		List<Map<String, String>> resultList = new ArrayList();
		
		Iterator<AdmLogResult> iterator = service.selectListFileLog(paramData).iterator();
		
		int excelNo = 1;
		while(iterator.hasNext()) {
			AdmLogResult result = iterator.next();
			
			LinkedHashMap<String,String> dataMap = new LinkedHashMap<String,String>();
			
			dataMap.put("excelNo", String.valueOf(excelNo));
			dataMap.put("adminId", result.getSysAdmin().getAdminId() );
			dataMap.put("adminNm", result.getSysAdmin().getAdminNm() );
			dataMap.put("conectIp", result.getSysAdminUseLog().getConectIp() );
			dataMap.put("conectDt", getDateService().parseString( result.getSysAdminUseLog().getConectDt() , "yyyy-MM-dd") );
			dataMap.put("menuNm", result.getSysMenu().getMenuNm() );
		
			resultList.add(dataMap);
			excelNo++;
		}
		
		return resultList;
	}
	
}
