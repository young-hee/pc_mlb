/* Copyright (c) 2015 plgrim, Inc.
 * All right reserved.
 * http://plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 *
 * ------------------------------------------------------------------------
 * @author yoon.eun
 * @since 2016. 12. 01
 */
package com.plgrim.ncp.biz.zipcode.repository;

import java.sql.SQLException;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.datasource1.sys.SysFileUpload;
import com.plgrim.ncp.base.entities.datasource1.sys.SysPlc;
import com.plgrim.ncp.base.entities.datasource1.sys.SysPlcVal;
import com.plgrim.ncp.biz.zipcode.data.SysFileUploadExtend;

/**
 *
 * @author yoon.eun
 * @since 2016. 12. 01
 */
@Repository
public class SysZipcodeRepository extends AbstractRepository  {

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */

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
	 * @param sysPlc [설명]
	 * @return sysPlc [설명]
	 * @since 2016. 12 .01
	 */
    public SysPlc selectSysPlcPostNo(SysPlc sysPlc) {
		return getSession1().selectOne("com.plgrim.ncp.biz.sys.zipcode.selectSysPlcPostNo", sysPlc);
	}

	/**
	 * 시스템 정책 테이블 MERGE(우편번호 연동방식)
	 *
	 * @param sysPlc [설명]
	 * @return int [설명]
	 * @since 2016. 11. 30
	 */
    public int mergeSysPlcPostNoIntrlckMenmthd(SysPlc sysPlc) {
		return getSession1().update("com.plgrim.ncp.biz.sys.zipcode.mergeSysPlcPostNoIntrlckMenmthd", sysPlc);
	}

	/**
	 * 시스템 정책 값 테이블 조회(우편번호 연동방식)
	 * @param sysPlc [설명]
	 * @return sysPlc [설명]
	 * @since 2016. 12 .01
	 */
	public SysPlcVal selectSysPlcValPostNo(SysPlc sysPlc) {
		return getSession1().selectOne("com.plgrim.ncp.biz.sys.zipcode.selectSysPlcValPostNo", sysPlc);
	}

	/**
	 * 시스템 정책 테이블 MERGE(우편번호 연동방식)
	 *
	 * @param sysPlcVal [설명]
	 * @return int [설명]
	 * @since 2016. 11. 30
	 */
	public int mergeSysPlcValPostNo(SysPlcVal sysPlcVal) {
		return getSession1().update("com.plgrim.ncp.biz.sys.zipcode.mergeSysPlcValPostNo", sysPlcVal);
	}

	/**
	 * 시스템 파일 업로드 조회.
	 *
	 * @param sysFileUpload the SysFileUpload
	 * @return the SysFileUploadExtend
	 * @throws SQLException the SQL exception
	 */
	public SysFileUploadExtend selectSysFileUploadInfo(SysFileUpload sysFileUpload) throws Exception {
		SysFileUploadExtend result = getSession1().selectOne("com.plgrim.ncp.biz.sys.zipcode.selectSysFileUploadInfo", sysFileUpload);
		return result;
	}
	
	/**
	 * 시스템 파일 업로드 등록.
	 *
	 * @param sysFileUpload the SysFileUpload
	 * @throws SQLException the SQL exception
	 */
	public int insertSysFileUpload(SysFileUpload sysFileUpload) throws Exception {
		return getSession1().insert("com.plgrim.ncp.base.insertSysFileUpload", sysFileUpload);
	}
	
	/**
	 * 시스템 파일 업로드 수정.
	 *
	 * @param sysFileUpload the SysFileUpload
	 * @throws SQLException the SQL exception
	 */
	@Transactional(value="transactionManager", propagation=Propagation.REQUIRES_NEW)
	public int updateSysFileUpload(SysFileUpload sysFileUpload) throws Exception {
		return getSession1().update("com.plgrim.ncp.biz.sys.zipcode.updateSysFileUpload", sysFileUpload);
	}
	
	/**
	 * 시스템 우편번호 주소 테이블 RENAME
	 * 
	 * @param Map<String, String> tableInfo
	 * source
	 * target
	 * @throws Exception
	 */
	@Transactional(value="transactionManager", propagation=Propagation.REQUIRES_NEW)
    public void renameSysPostNoTable(Map<String, String> tableInfo) throws Exception {
        getSession1().update("com.plgrim.ncp.biz.sys.zipcode.renameSysPostNoTable", tableInfo);
    }
	
	/**
	 * 시스템 우편번호 주소 인덱스 RENAME
	 * 
	 * @param Map<String, String> indexInfo
	 * source
	 * target
	 * @throws Exception
	 */
	@Transactional(value="transactionManager", propagation=Propagation.REQUIRES_NEW)
    public void renameSysPostNoIndex(Map<String, String> indexInfo) throws Exception {
        getSession1().update("com.plgrim.ncp.biz.sys.zipcode.renameSysPostNoIndex", indexInfo);
    }
    
    /**
     * 시스템 우편번호 TABLE CHECK
     *
     * @return
     */
    public long checkSysPostNoTable(String tableName) throws Exception {
        return getSession1().selectOne("com.plgrim.ncp.biz.sys.zipcode.checkSysPostNoTable", tableName);
    }

    /**
     * 시스템 우편번호 INDEX CHECK
     *
     * @return
     */
    public long checkSysPostNoIndex(String indexName) throws Exception {
        return getSession1().selectOne("com.plgrim.ncp.biz.sys.zipcode.checkSysPostNoIndex", indexName);
    }
    
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */

}
