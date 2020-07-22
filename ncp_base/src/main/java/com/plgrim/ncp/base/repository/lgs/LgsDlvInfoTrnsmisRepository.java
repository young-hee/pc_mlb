
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
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsDlvInfoTrnsmis;

/**
 * The Class LgsDlvInfoTrnsmisRepository.
 */
@Repository
public class LgsDlvInfoTrnsmisRepository extends AbstractRepository {
	

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
	 * 물류 배송 정보 전송 상세 조회.
	 *
	 * @param lgsDlvInfoTrnsmis the LgsDlvInfoTrnsmis
	 * @return the LgsDlvInfoTrnsmis
	 * @throws SQLException the SQL exception
	 */
	public LgsDlvInfoTrnsmis selectLgsDlvInfoTrnsmis(LgsDlvInfoTrnsmis lgsDlvInfoTrnsmis) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectLgsDlvInfoTrnsmis", lgsDlvInfoTrnsmis);
	}
	
	/**
	 * 물류 배송 정보 전송 등록.
	 *
	 * @param lgsDlvInfoTrnsmis the LgsDlvInfoTrnsmis
	 * @throws SQLException the SQL exception
	 */
	public void insertLgsDlvInfoTrnsmis(LgsDlvInfoTrnsmis lgsDlvInfoTrnsmis) {
		getSession1().insert("com.plgrim.ncp.base.insertLgsDlvInfoTrnsmis", lgsDlvInfoTrnsmis);
	}
	
	/**
	 * 물류 배송 정보 전송 수정.
	 *
	 * @param lgsDlvInfoTrnsmis the LgsDlvInfoTrnsmis
	 * @throws SQLException the SQL exception
	 */
	public int updateLgsDlvInfoTrnsmis(LgsDlvInfoTrnsmis lgsDlvInfoTrnsmis) {
		return getSession1().update("com.plgrim.ncp.base.updateLgsDlvInfoTrnsmis", lgsDlvInfoTrnsmis);
	}
	
	/**
	 * 물류 배송 정보 전송 삭제.
	 *
	 * @param lgsDlvInfoTrnsmis the LgsDlvInfoTrnsmis
	 * @return the LgsDlvInfoTrnsmis
	 * @throws SQLException the SQL exception
	 */
	public int deleteLgsDlvInfoTrnsmis(LgsDlvInfoTrnsmis lgsDlvInfoTrnsmis) {
		return getSession1().delete("com.plgrim.ncp.base.deleteLgsDlvInfoTrnsmis", lgsDlvInfoTrnsmis);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

