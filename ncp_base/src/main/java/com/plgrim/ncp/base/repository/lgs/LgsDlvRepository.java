
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
package com.plgrim.ncp.base.repository.lgs;

import java.sql.SQLException;
import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsDlv;

/**
 * The Class LgsDlvRepository.
 */
@Repository
public class LgsDlvRepository extends AbstractRepository {
	

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
	 * 물류 배송 상세 조회.
	 *
	 * @param lgsDlv the LgsDlv
	 * @return the LgsDlv
	 * @throws SQLException the SQL exception
	 */
	public LgsDlv selectLgsDlv(LgsDlv lgsDlv) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectLgsDlv", lgsDlv);
	}
	
	/**
	 * 물류 배송 등록.
	 *
	 * @param lgsDlv the LgsDlv
	 * @throws SQLException the SQL exception
	 */
	public void insertLgsDlv(LgsDlv lgsDlv) {
		getSession1().insert("com.plgrim.ncp.base.insertLgsDlv", lgsDlv);
	}
	
	/**
	 * 물류 배송 수정.
	 *
	 * @param lgsDlv the LgsDlv
	 * @throws SQLException the SQL exception
	 */
	public int updateLgsDlv(LgsDlv lgsDlv) {
		return getSession1().update("com.plgrim.ncp.base.updateLgsDlv", lgsDlv);
	}
	
	/**
	 * 물류 배송 삭제.
	 *
	 * @param lgsDlv the LgsDlv
	 * @return the LgsDlv
	 * @throws SQLException the SQL exception
	 */
	public int deleteLgsDlv(LgsDlv lgsDlv) {
		return getSession1().delete("com.plgrim.ncp.base.deleteLgsDlv", lgsDlv);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

