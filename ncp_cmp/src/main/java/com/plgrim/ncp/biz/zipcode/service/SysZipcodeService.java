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
 * @since       2015. 5. 8       
 */
package com.plgrim.ncp.biz.zipcode.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.plgrim.ncp.base.abstracts.AbstractService;
import com.plgrim.ncp.base.entities.datasource1.sys.SysFileUpload;
import com.plgrim.ncp.base.entities.datasource1.sys.SysPlc;
import com.plgrim.ncp.base.entities.datasource1.sys.SysPlcVal;
import com.plgrim.ncp.biz.zipcode.data.SysFileUploadExtend;
import com.plgrim.ncp.biz.zipcode.repository.SysZipcodeRepository;
import com.plgrim.ncp.commons.util.BODateUtil;
import com.plgrim.ncp.commons.util.CodeUtil;
import com.plgrim.ncp.framework.enums.DatabaseType;
import com.google.common.collect.Maps;

/**
 * 업체
 * 
 * <p>
 * 
 * <ul>
 *   <li> [기능1]
 *   <li> [기능2]
 * </ul>.
 *
 * @author sy59.gim
 * @since 2015. 3. 24
 */
@Service
public class SysZipcodeService extends AbstractService {

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	
	/**
	 * 업체 Repository
	 */
	@Autowired
	private SysZipcodeRepository sysZipcodeRepository;
	
	@Autowired
    @Qualifier("sessionTemplate1")
    private SqlSession sqlSession1;
	
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
	  * 시스템 정책 테이블 조회(우편번호 연동방식)
	  * @param sysPlc
	  * @throws Exception
	  */
	 public SysPlc selectSysPlcPostNo(SysPlc sysPlc) throws Exception{
		 return sysZipcodeRepository.selectSysPlcPostNo(sysPlc);
	 }

	 /**
	  * 시스템 정책 테이블 MERGE(우편번호 연동방식)
	  * @param sysPlc
	  * @throws Exception
	  */
	 public int mergeSysPlcPostNoIntrlckMenmthd(SysPlc sysPlc) throws Exception{
		 return sysZipcodeRepository.mergeSysPlcPostNoIntrlckMenmthd(sysPlc);
	 }

	/**
	 * 시스템 정책 값 테이블 조회(우편번호 연동방식)
	 * @param sysPlc
	 * @throws Exception
	 */
	public SysPlcVal selectSysPlcValPostNo(SysPlc sysPlc) throws Exception{
		return sysZipcodeRepository.selectSysPlcValPostNo(sysPlc);
	}

	/**
	 * 시스템 정책 값 테이블 MERGE(우편번호 연동방식)
	 * @param sysPlc
	 * @throws Exception
	 */
	public int mergeSysPlcValPostNo(SysPlcVal sysPlcVal) throws Exception{
		return sysZipcodeRepository.mergeSysPlcValPostNo(sysPlcVal);
	}

	/**
	 * 시스템 파일 업로드 이력 조회.
	 *
	 * @param sysFileUpload
	 * @return SysFileUploadExtend
	 * @throws Exception
	 */
	public SysFileUploadExtend selectSysFileUploadInfo(SysFileUpload sysFileUpload) throws Exception {
		SysFileUploadExtend result = sysZipcodeRepository.selectSysFileUploadInfo(sysFileUpload);
		
		String lang = "KOR";
		if(result != null && !"".equals(result.getFileUploadTpCd())) {
			result.setFileUploadTpNm(CodeUtil.getCode(lang, result.getFileUploadTpCd()).getCdNm());	
		}
		if(result != null && !"".equals(result.getProgrsStatCd())) {
			result.setProgrsStatNm(CodeUtil.getCode(lang, result.getProgrsStatCd()).getCdNm());	
		}
		
		return result;
	}
	
	/**
	 * 시스템 파일 업로드 등록.
	 *
	 * @param sysFileUpload
	 * @return sysFileUpload
	 * @throws Exception
	 */
	public SysFileUpload insertSysFileUpload(SysFileUpload sysFileUpload) throws Exception {
		String currentDate = BODateUtil.getCurrentDateString();
		
		Map<String, Object> conditions =  Maps.newHashMap();
		conditions.put("FILE_UPLOAD_DATE", currentDate);
		int fileUploadTurn = getIdGenService().generateDBOrder(sqlSession1, "SYS_FILE_UPLOAD", "FILE_UPLOAD_TURN", conditions, DatabaseType.ORACLE);
		
		sysFileUpload.setFileUploadDate(currentDate);
		sysFileUpload.setFileUploadTurn(fileUploadTurn);
		sysZipcodeRepository.insertSysFileUpload(sysFileUpload);
		
		return sysFileUpload;
	}
	
	/**
	 * 시스템 파일 업로드 수정.
	 *
	 * @param sysFileUpload
	 * @return int
	 * @throws Exception
	 */
	public int updateSysFileUpload(SysFileUpload sysFileUpload) throws Exception {
		return sysZipcodeRepository.updateSysFileUpload(sysFileUpload);
	}
	
	/**
	 * 테이블 RENAME
	 * 
	 * @return String result
	 * @throws Exception
	 */
	public String tableRename() throws Exception{
		String resultCode = "SUCCESS";
        
		List<Map<String, String>> list = new ArrayList<>();
		Map<String, String> tableInfo = null;
		
		try {
			// 테이블 명칭 세팅
			tableInfo = new HashMap<>();
			tableInfo.put("source", "SYS_POST_NO");
			tableInfo.put("target", "SYS_POST_NO_SWAP");
			list.add(tableInfo);
			
			tableInfo = new HashMap<>();
			tableInfo.put("source", "SYS_POST_NO_TMPR");
			tableInfo.put("target", "SYS_POST_NO");
			list.add(tableInfo);

			tableInfo = new HashMap<>();
			tableInfo.put("source", "SYS_POST_NO_SWAP");
			tableInfo.put("target", "SYS_POST_NO_TMPR");
			list.add(tableInfo);
			
			// list에 담은 테이블 명칭 갯수만큼 돌면서 명칭 변경
			for(int i=0; i<list.size(); i++) {
				sysZipcodeRepository.renameSysPostNoTable(list.get(i));
				checkTableRename(list.get(i));
			}
		}
		catch(Exception e) {
			resultCode = "FAIL";
		}

		return resultCode;
	}
	
	/**
	 * 테이블 RENAME 검증
	 * 검증 후 정상처리가 되지 않았으면 3번까지 재시도한다.
	 * 
	 * @param Map<String, String> tableInfo
	 * source
	 * target
	 * @throws Exception
	 */
	public void checkTableRename(Map<String, String> tableInfo) throws Exception{
		String targetName = tableInfo.get("target");
        if (!(sysZipcodeRepository.checkSysPostNoTable(targetName) > 0)) {
            for (int i = 0; i < 3; i++) {
               	sysZipcodeRepository.renameSysPostNoTable(tableInfo);

                if (sysZipcodeRepository.checkSysPostNoTable(targetName) > 0) {
                    break;
                }
            }
        }
	}
	
	/**
	 * 인덱스 RENAME
	 * 
	 * @return String result
	 * @throws Exception
	 */
	public String indexRename() throws Exception{
		String resultCode = "SUCCESS";
		
		List<String> indexNameList = new ArrayList<>();
		List<Map<String, String>> list = new ArrayList<>();
		Map<String, String> indexInfo = null;
		
		try {
			// 인덱스 명칭 세팅
			indexNameList.add("1");
			indexNameList.add("2");
			indexNameList.add("3");
			indexNameList.add("4");
			indexNameList.add("8");
			for(int i=0; i<indexNameList.size(); i++) {
				indexInfo = new HashMap<>();
				indexInfo.put("source", "IX" + indexNameList.get(i) + "_SYS_POST_NO");
				indexInfo.put("target", "IX" + indexNameList.get(i) + "_SYS_POST_NO_SWAP");
				list.add(indexInfo);
				indexInfo = new HashMap<>();
				indexInfo.put("source", "IX" + indexNameList.get(i) + "_SYS_POST_NO_TMPR");
				indexInfo.put("target", "IX" + indexNameList.get(i) + "_SYS_POST_NO");
				list.add(indexInfo);
				indexInfo = new HashMap<>();
				indexInfo.put("source", "IX" + indexNameList.get(i) + "_SYS_POST_NO_SWAP");
				indexInfo.put("target", "IX" + indexNameList.get(i) + "_SYS_POST_NO_TMPR");
				list.add(indexInfo);
			}
			
			// list에 담은 인덱스 명칭 갯수만큼 돌면서 명칭 변경
			for(int i=0; i<list.size(); i++) {
				sysZipcodeRepository.renameSysPostNoIndex(list.get(i));
				checkIndexRename(list.get(i));
			}
		}
		catch(Exception e) {
			resultCode = "FAIL";
		}
		
		return resultCode;
	}
	
	/**
	 * 인덱스 RENAME 검증
	 * 검증 후 정상처리가 되지 않았으면 3번까지 재시도한다.
	 * 
	 * @param Map<String, String> indexInfo
	 * source
	 * target
	 * @throws Exception
	 */
	public void checkIndexRename(Map<String, String> indexInfo) throws Exception{
		String targetName = indexInfo.get("target");
        long afterIndexChk = sysZipcodeRepository.checkSysPostNoIndex(targetName);
        if (!(afterIndexChk > 0)) {
            for (int i = 0; i < 3; i++) {
               	sysZipcodeRepository.renameSysPostNoIndex(indexInfo);

                if (sysZipcodeRepository.checkSysPostNoIndex(targetName) > 0) {
                    break;
                }
            }
        }
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */

}
