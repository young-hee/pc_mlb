
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
package com.plgrim.ncp.base.repository.clm;

import java.sql.SQLException;
import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.datasource1.clm.ClmWrhsCpstGodCnnc;

/**
 * The Class ClmWrhsCpstGodCnncRepository.
 */
@Repository
public class ClmWrhsCpstGodCnncRepository extends AbstractRepository {
	

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
	 * 클레임 입고 구성 상품 연결 상세 조회.
	 *
	 * @param clmWrhsCpstGodCnnc the ClmWrhsCpstGodCnnc
	 * @return the ClmWrhsCpstGodCnnc
	 * @throws SQLException the SQL exception
	 */
	public ClmWrhsCpstGodCnnc selectClmWrhsCpstGodCnnc(ClmWrhsCpstGodCnnc clmWrhsCpstGodCnnc) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectClmWrhsCpstGodCnnc", clmWrhsCpstGodCnnc);
	}
	
	/**
	 * 클레임 입고 구성 상품 연결 등록.
	 *
	 * @param clmWrhsCpstGodCnnc the ClmWrhsCpstGodCnnc
	 * @throws SQLException the SQL exception
	 */
	public void insertClmWrhsCpstGodCnnc(ClmWrhsCpstGodCnnc clmWrhsCpstGodCnnc) {
		getSession1().insert("com.plgrim.ncp.base.insertClmWrhsCpstGodCnnc", clmWrhsCpstGodCnnc);
	}
	
	/**
	 * 클레임 입고 구성 상품 연결 수정.
	 *
	 * @param clmWrhsCpstGodCnnc the ClmWrhsCpstGodCnnc
	 * @throws SQLException the SQL exception
	 */
	public int updateClmWrhsCpstGodCnnc(ClmWrhsCpstGodCnnc clmWrhsCpstGodCnnc) {
		return getSession1().update("com.plgrim.ncp.base.updateClmWrhsCpstGodCnnc", clmWrhsCpstGodCnnc);
	}
	
	/**
	 * 클레임 입고 구성 상품 연결 삭제.
	 *
	 * @param clmWrhsCpstGodCnnc the ClmWrhsCpstGodCnnc
	 * @return the ClmWrhsCpstGodCnnc
	 * @throws SQLException the SQL exception
	 */
	public int deleteClmWrhsCpstGodCnnc(ClmWrhsCpstGodCnnc clmWrhsCpstGodCnnc) {
		return getSession1().delete("com.plgrim.ncp.base.deleteClmWrhsCpstGodCnnc", clmWrhsCpstGodCnnc);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

