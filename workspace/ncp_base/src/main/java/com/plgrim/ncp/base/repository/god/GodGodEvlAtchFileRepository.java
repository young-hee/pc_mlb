
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
package com.plgrim.ncp.base.repository.god;

import java.sql.SQLException;
import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.datasource1.god.GodGodEvlAtchFile;

/**
 * The Class GodGodEvlAtchFileRepository.
 */
@Repository
public class GodGodEvlAtchFileRepository extends AbstractRepository {
	

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
	 * 상품 상품평 첨부 파일 상세 조회.
	 *
	 * @param godGodEvlAtchFile the GodGodEvlAtchFile
	 * @return the GodGodEvlAtchFile
	 * @throws SQLException the SQL exception
	 */
	public GodGodEvlAtchFile selectGodGodEvlAtchFile(GodGodEvlAtchFile godGodEvlAtchFile) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectGodGodEvlAtchFile", godGodEvlAtchFile);
	}
	
	/**
	 * 상품 상품평 첨부 파일 등록.
	 *
	 * @param godGodEvlAtchFile the GodGodEvlAtchFile
	 * @throws SQLException the SQL exception
	 */
	public void insertGodGodEvlAtchFile(GodGodEvlAtchFile godGodEvlAtchFile) {
		getSession1().insert("com.plgrim.ncp.base.insertGodGodEvlAtchFile", godGodEvlAtchFile);
	}
	
	/**
	 * 상품 상품평 첨부 파일 수정.
	 *
	 * @param godGodEvlAtchFile the GodGodEvlAtchFile
	 * @throws SQLException the SQL exception
	 */
	public int updateGodGodEvlAtchFile(GodGodEvlAtchFile godGodEvlAtchFile) {
		return getSession1().update("com.plgrim.ncp.base.updateGodGodEvlAtchFile", godGodEvlAtchFile);
	}
	
	/**
	 * 상품 상품평 첨부 파일 삭제.
	 *
	 * @param godGodEvlAtchFile the GodGodEvlAtchFile
	 * @return the GodGodEvlAtchFile
	 * @throws SQLException the SQL exception
	 */
	public int deleteGodGodEvlAtchFile(GodGodEvlAtchFile godGodEvlAtchFile) {
		return getSession1().delete("com.plgrim.ncp.base.deleteGodGodEvlAtchFile", godGodEvlAtchFile);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

