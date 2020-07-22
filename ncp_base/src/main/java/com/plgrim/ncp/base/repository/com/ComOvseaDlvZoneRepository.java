
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
import com.plgrim.ncp.base.entities.datasource1.com.ComOvseaDlvZone;

/**
 * The Class ComOvseaDlvZoneRepository.
 */
@Repository
public class ComOvseaDlvZoneRepository extends AbstractRepository {
	

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
	 * 업체 해외 배송 ZONE 상세 조회.
	 *
	 * @param comOvseaDlvZone the ComOvseaDlvZone
	 * @return the ComOvseaDlvZone
	 * @throws SQLException the SQL exception
	 */
	public ComOvseaDlvZone selectComOvseaDlvZone(ComOvseaDlvZone comOvseaDlvZone) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectComOvseaDlvZone", comOvseaDlvZone);
	}
	
	/**
	 * 업체 해외 배송 ZONE 등록.
	 *
	 * @param comOvseaDlvZone the ComOvseaDlvZone
	 * @throws SQLException the SQL exception
	 */
	public void insertComOvseaDlvZone(ComOvseaDlvZone comOvseaDlvZone) {
		getSession1().insert("com.plgrim.ncp.base.insertComOvseaDlvZone", comOvseaDlvZone);
	}
	
	/**
	 * 업체 해외 배송 ZONE 수정.
	 *
	 * @param comOvseaDlvZone the ComOvseaDlvZone
	 * @throws SQLException the SQL exception
	 */
	public int updateComOvseaDlvZone(ComOvseaDlvZone comOvseaDlvZone) {
		return getSession1().update("com.plgrim.ncp.base.updateComOvseaDlvZone", comOvseaDlvZone);
	}
	
	/**
	 * 업체 해외 배송 ZONE 삭제.
	 *
	 * @param comOvseaDlvZone the ComOvseaDlvZone
	 * @return the ComOvseaDlvZone
	 * @throws SQLException the SQL exception
	 */
	public int deleteComOvseaDlvZone(ComOvseaDlvZone comOvseaDlvZone) {
		return getSession1().delete("com.plgrim.ncp.base.deleteComOvseaDlvZone", comOvseaDlvZone);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

