
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
package com.plgrim.ncp.base.repository.com;

import java.sql.SQLException;
import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.datasource1.com.ComLangbyChrger;

/**
 * The Class ComLangbyChrgerRepository.
 */
@Repository
public class ComLangbyChrgerRepository extends AbstractRepository {
	

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
	 * 업체 언어별 담당자 상세 조회.
	 *
	 * @param comLangbyChrger the ComLangbyChrger
	 * @return the ComLangbyChrger
	 * @throws SQLException the SQL exception
	 */
	public ComLangbyChrger selectComLangbyChrger(ComLangbyChrger comLangbyChrger) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectComLangbyChrger", comLangbyChrger);
	}
	
	/**
	 * 업체 언어별 담당자 등록.
	 *
	 * @param comLangbyChrger the ComLangbyChrger
	 * @throws SQLException the SQL exception
	 */
	public void insertComLangbyChrger(ComLangbyChrger comLangbyChrger) {
		getSession1().insert("com.plgrim.ncp.base.insertComLangbyChrger", comLangbyChrger);
	}
	
	/**
	 * 업체 언어별 담당자 수정.
	 *
	 * @param comLangbyChrger the ComLangbyChrger
	 * @throws SQLException the SQL exception
	 */
	public int updateComLangbyChrger(ComLangbyChrger comLangbyChrger) {
		return getSession1().update("com.plgrim.ncp.base.updateComLangbyChrger", comLangbyChrger);
	}
	
	/**
	 * 업체 언어별 담당자 삭제.
	 *
	 * @param comLangbyChrger the ComLangbyChrger
	 * @return the ComLangbyChrger
	 * @throws SQLException the SQL exception
	 */
	public int deleteComLangbyChrger(ComLangbyChrger comLangbyChrger) {
		return getSession1().delete("com.plgrim.ncp.base.deleteComLangbyChrger", comLangbyChrger);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

