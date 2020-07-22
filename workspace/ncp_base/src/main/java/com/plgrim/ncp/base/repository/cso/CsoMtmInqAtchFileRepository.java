
/* Copyright (c) 2015 plgrim, Inc.
 * All right reserved.
 * http://www.plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 *
 * Revision History
 * Author              			Date                         	Description
 * ------------------   		--------------                  ------------------
 * Generator(Generator)		2018-05-23                      
 */
package com.plgrim.ncp.base.repository.cso;

import java.sql.SQLException;
import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.datasource1.cso.CsoMtmInqAtchFile;

/**
 * The Class CsoMtmInqAtchFileRepository.
 */
@Repository
public class CsoMtmInqAtchFileRepository extends AbstractRepository {
	

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
	 * 고객서비스 일대일 문의 첨부 파일 상세 조회.
	 *
	 * @param csoMtmInqAtchFile the CsoMtmInqAtchFile
	 * @return the CsoMtmInqAtchFile
	 * @throws SQLException the SQL exception
	 */
	public CsoMtmInqAtchFile selectCsoMtmInqAtchFile(CsoMtmInqAtchFile csoMtmInqAtchFile) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectCsoMtmInqAtchFile", csoMtmInqAtchFile);
	}
	
	/**
	 * 고객서비스 일대일 문의 첨부 파일 등록.
	 *
	 * @param csoMtmInqAtchFile the CsoMtmInqAtchFile
	 * @throws SQLException the SQL exception
	 */
	public void insertCsoMtmInqAtchFile(CsoMtmInqAtchFile csoMtmInqAtchFile) {
		getSession1().insert("com.plgrim.ncp.base.insertCsoMtmInqAtchFile", csoMtmInqAtchFile);
	}
	
	/**
	 * 고객서비스 일대일 문의 첨부 파일 수정.
	 *
	 * @param csoMtmInqAtchFile the CsoMtmInqAtchFile
	 * @throws SQLException the SQL exception
	 */
	public int updateCsoMtmInqAtchFile(CsoMtmInqAtchFile csoMtmInqAtchFile) {
		return getSession1().update("com.plgrim.ncp.base.updateCsoMtmInqAtchFile", csoMtmInqAtchFile);
	}
	
	/**
	 * 고객서비스 일대일 문의 첨부 파일 삭제.
	 *
	 * @param csoMtmInqAtchFile the CsoMtmInqAtchFile
	 * @return the CsoMtmInqAtchFile
	 * @throws SQLException the SQL exception
	 */
	public int deleteCsoMtmInqAtchFile(CsoMtmInqAtchFile csoMtmInqAtchFile) {
		return getSession1().delete("com.plgrim.ncp.base.deleteCsoMtmInqAtchFile", csoMtmInqAtchFile);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

