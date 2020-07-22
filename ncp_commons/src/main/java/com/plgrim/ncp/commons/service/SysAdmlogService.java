/* Copyright (c) 2015 plgrim, Inc.
 * All right reserved.
 * http://plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 *
 * ------------------------------------------------------------------------
 * @author      jwcale.kim
 * @since       2015. 5. 22       
 */
package com.plgrim.ncp.commons.service;

import com.plgrim.ncp.base.abstracts.AbstractService;
import com.plgrim.ncp.base.entities.datasource1.sys.SysAdminLoginLog;
import com.plgrim.ncp.base.entities.datasource1.sys.SysAdminUseLog;
import com.plgrim.ncp.commons.data.FormSysAdmlogDTO;
import com.plgrim.ncp.commons.repository.SysAdmlogRepository;
import com.plgrim.ncp.commons.result.AdmLogResult;
import com.plgrim.ncp.interfaces.util.AdapterHeader;
import com.plgrim.ncp.interfaces.util.InterfaceApiCommon;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * EditorService Implementation
 * @author Tam
 *
 */
@Slf4j
@Service
public class SysAdmlogService extends AbstractService {

	
	@Autowired
    SysAdmlogRepository repository;

	@Autowired
	InterfaceApiCommon interfaceApiCommon;

	/**
	 * 운영자로그인로그 목록조회
	 * @param paramData
	 * @return
	 * @throws Exception
	 */
	public List<AdmLogResult> selectListLoginLog( FormSysAdmlogDTO paramData) throws Exception {
		return repository.selectListLoginLog(paramData);
	}
	
	/**
	 * 운영자로그인로그 총로그수
	 * @param paramData
	 * @return
	 * @throws Exception
	 */
	public long selectCountLoginLog( FormSysAdmlogDTO paramData) throws Exception {
		return repository.selectCountLoginLog(paramData);
	}
	
	/**
	 * 운영자메뉴내역 로그조회
	 * @param paramData
	 * @return
	 * @throws Exception
	 */
	public List<AdmLogResult> selectListMenuLog( FormSysAdmlogDTO paramData) throws Exception {
		return repository.selectListMenuLog(paramData);
	}
	
	/**
	 * 운영자메뉴내역 총로그수
	 * @param paramData
	 * @return
	 * @throws Exception
	 */
	public long selectCountMenuLog( FormSysAdmlogDTO paramData) throws Exception {
		return repository.selectCountMenuLog(paramData);
	}
	
	/**
	 * 운영자메뉴->File이용 내역
	 * @param paramData
	 * @return
	 * @throws Exception
	 */
	public List<AdmLogResult> selectListFileLog( FormSysAdmlogDTO paramData) throws Exception {
		return repository.selectListFileLog(paramData);
	}
	
	/**
	 * selectCountFileLog
	 * @param paramData
	 * @return
	 * @throws Exception
	 */
	public long selectCountFileLog( FormSysAdmlogDTO paramData) throws Exception {
		return repository.selectCountFileLog(paramData);
	}
	
	/**
	 * 운영자 로그인로그 등록
	 * @param paramData
	 * @throws Exception
	 */
	public void insertLoginLog ( SysAdminLoginLog paramData) throws Exception {
		repository.insertLoginLog(paramData);
	}
	
	/**
	 *  운영자 메뉴사용로그 등록
	 * @param paramData
	 * @throws Exception
	 */
	public void insertMenuUseLog ( SysAdminUseLog paramData) throws Exception {
		repository.insertMenuUseLog(paramData);
	}



//	/**
//	 * 회원 휴머스온 알림톡/SMS LIST 조회
//	 * @return
//	 * @
//	 */
//	public NoticeListResult selectAlimTalkListForMember(NoticeSDO noticeSDO) {
//
//		AdapterHeader adapterHeader = this.setAdapterHeader();
//		NoticeListResult resultList = new NoticeListResult();
//		try {
//			resultList = noticeAdapter.selectAlimTalkListForMember(noticeSDO, adapterHeader);
//		} catch(Exception e){
//			log.info("selectAlimTalkListForMember Call Exception {}", e.getMessage());
//		}
//
//		return resultList;
//	}
//
//	/**
//	 * 회원 휴머스온 알림톡/SMS LIST 엑셀 조회 (회원관리>전체회원정보>발송내역)
//	 * @return
//	 * @
//	 */
//	public List<Map<String, Object>> selectAlimTalkExcelListForMember(NoticeSDO noticeSDO) {
//
//		AdapterHeader adapterHeader = this.setAdapterHeader();
//		List<Map<String, Object>> result = new ArrayList<>();
//		try {
//			result = noticeAdapter.selectAlimTalkExcelListForMember(noticeSDO, adapterHeader);
//		} catch(Exception e){
//			log.info("selectAlimTalkExcelListForMember Call Exception {}", e.getMessage());
//		}
//
//		return result;
//	}
//
//	/**
//	 * 회원 휴머스온 알림톡/SMS LIST 엑셀 조회 (운영관리>메세지로그조회)
//	 * @return
//	 * @
//	 */
//	public List<Map<String, Object>> selectAlimTalkExcelListForMsgLog(NoticeSDO noticeSDO) {
//
//		AdapterHeader adapterHeader = this.setAdapterHeader();
//		List<Map<String, Object>> result = new ArrayList<>();
//		try {
//			result = noticeAdapter.selectAlimTalkExcelListForMsgLog(noticeSDO, adapterHeader);
//		} catch(Exception e){
//			log.info("selectAlimTalkExcelListForMsgLog Call Exception {}", e.getMessage());
//		}
//
//		return result;
//	}


	/**
	 * AdapterHeader 값 설정
	 * @return
	 */
	private AdapterHeader setAdapterHeader(){
		AdapterHeader adapterHeader = new AdapterHeader();
		adapterHeader.setRequestId(this.interfaceApiCommon.getRequestId());
		adapterHeader.setMallId("DXM");
		adapterHeader.setLangCd("KOR");
		adapterHeader.setDvcCd("PC");

		return adapterHeader;
	}
}
