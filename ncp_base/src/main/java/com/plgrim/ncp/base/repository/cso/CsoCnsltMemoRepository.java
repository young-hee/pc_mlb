
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
import com.plgrim.ncp.base.entities.datasource1.cso.CsoCnsltMemo;

/**
 * The Class CsoCnsltMemoRepository.
 */
@Repository
public class CsoCnsltMemoRepository extends AbstractRepository {
	

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
	 * 고객서비스 상담 메모 상세 조회.
	 *
	 * @param csoCnsltMemo the CsoCnsltMemo
	 * @return the CsoCnsltMemo
	 * @throws SQLException the SQL exception
	 */
	public CsoCnsltMemo selectCsoCnsltMemo(CsoCnsltMemo csoCnsltMemo) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectCsoCnsltMemo", csoCnsltMemo);
	}
	
	/**
	 * 고객서비스 상담 메모 등록.
	 *
	 * @param csoCnsltMemo the CsoCnsltMemo
	 * @throws SQLException the SQL exception
	 */
	public void insertCsoCnsltMemo(CsoCnsltMemo csoCnsltMemo) {
		getSession1().insert("com.plgrim.ncp.base.insertCsoCnsltMemo", csoCnsltMemo);
	}
	
	/**
	 * 고객서비스 상담 메모 수정.
	 *
	 * @param csoCnsltMemo the CsoCnsltMemo
	 * @throws SQLException the SQL exception
	 */
	public int updateCsoCnsltMemo(CsoCnsltMemo csoCnsltMemo) {
		return getSession1().update("com.plgrim.ncp.base.updateCsoCnsltMemo", csoCnsltMemo);
	}
	
	/**
	 * 고객서비스 상담 메모 삭제.
	 *
	 * @param csoCnsltMemo the CsoCnsltMemo
	 * @return the CsoCnsltMemo
	 * @throws SQLException the SQL exception
	 */
	public int deleteCsoCnsltMemo(CsoCnsltMemo csoCnsltMemo) {
		return getSession1().delete("com.plgrim.ncp.base.deleteCsoCnsltMemo", csoCnsltMemo);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

