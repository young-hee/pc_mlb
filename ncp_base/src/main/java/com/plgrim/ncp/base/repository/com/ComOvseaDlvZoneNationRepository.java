
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
import com.plgrim.ncp.base.entities.datasource1.com.ComOvseaDlvZoneNation;

/**
 * The Class ComOvseaDlvZoneNationRepository.
 */
@Repository
public class ComOvseaDlvZoneNationRepository extends AbstractRepository {
	

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
	 * 업체 해외 배송 ZONE 국가 상세 조회.
	 *
	 * @param comOvseaDlvZoneNation the ComOvseaDlvZoneNation
	 * @return the ComOvseaDlvZoneNation
	 * @throws SQLException the SQL exception
	 */
	public ComOvseaDlvZoneNation selectComOvseaDlvZoneNation(ComOvseaDlvZoneNation comOvseaDlvZoneNation) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectComOvseaDlvZoneNation", comOvseaDlvZoneNation);
	}
	
	/**
	 * 업체 해외 배송 ZONE 국가 등록.
	 *
	 * @param comOvseaDlvZoneNation the ComOvseaDlvZoneNation
	 * @throws SQLException the SQL exception
	 */
	public void insertComOvseaDlvZoneNation(ComOvseaDlvZoneNation comOvseaDlvZoneNation) {
		getSession1().insert("com.plgrim.ncp.base.insertComOvseaDlvZoneNation", comOvseaDlvZoneNation);
	}
	
	/**
	 * 업체 해외 배송 ZONE 국가 수정.
	 *
	 * @param comOvseaDlvZoneNation the ComOvseaDlvZoneNation
	 * @throws SQLException the SQL exception
	 */
	public int updateComOvseaDlvZoneNation(ComOvseaDlvZoneNation comOvseaDlvZoneNation) {
		return getSession1().update("com.plgrim.ncp.base.updateComOvseaDlvZoneNation", comOvseaDlvZoneNation);
	}
	
	/**
	 * 업체 해외 배송 ZONE 국가 삭제.
	 *
	 * @param comOvseaDlvZoneNation the ComOvseaDlvZoneNation
	 * @return the ComOvseaDlvZoneNation
	 * @throws SQLException the SQL exception
	 */
	public int deleteComOvseaDlvZoneNation(ComOvseaDlvZoneNation comOvseaDlvZoneNation) {
		return getSession1().delete("com.plgrim.ncp.base.deleteComOvseaDlvZoneNation", comOvseaDlvZoneNation);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

