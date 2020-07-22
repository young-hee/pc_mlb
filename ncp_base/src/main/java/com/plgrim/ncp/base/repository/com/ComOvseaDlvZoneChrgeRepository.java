
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
import com.plgrim.ncp.base.entities.datasource1.com.ComOvseaDlvZoneChrge;

/**
 * The Class ComOvseaDlvZoneChrgeRepository.
 */
@Repository
public class ComOvseaDlvZoneChrgeRepository extends AbstractRepository {
	

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
	 * 업체 해외 배송 ZONE 요금 상세 조회.
	 *
	 * @param comOvseaDlvZoneChrge the ComOvseaDlvZoneChrge
	 * @return the ComOvseaDlvZoneChrge
	 * @throws SQLException the SQL exception
	 */
	public ComOvseaDlvZoneChrge selectComOvseaDlvZoneChrge(ComOvseaDlvZoneChrge comOvseaDlvZoneChrge) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectComOvseaDlvZoneChrge", comOvseaDlvZoneChrge);
	}
	
	/**
	 * 업체 해외 배송 ZONE 요금 등록.
	 *
	 * @param comOvseaDlvZoneChrge the ComOvseaDlvZoneChrge
	 * @throws SQLException the SQL exception
	 */
	public void insertComOvseaDlvZoneChrge(ComOvseaDlvZoneChrge comOvseaDlvZoneChrge) {
		getSession1().insert("com.plgrim.ncp.base.insertComOvseaDlvZoneChrge", comOvseaDlvZoneChrge);
	}
	
	/**
	 * 업체 해외 배송 ZONE 요금 수정.
	 *
	 * @param comOvseaDlvZoneChrge the ComOvseaDlvZoneChrge
	 * @throws SQLException the SQL exception
	 */
	public int updateComOvseaDlvZoneChrge(ComOvseaDlvZoneChrge comOvseaDlvZoneChrge) {
		return getSession1().update("com.plgrim.ncp.base.updateComOvseaDlvZoneChrge", comOvseaDlvZoneChrge);
	}
	
	/**
	 * 업체 해외 배송 ZONE 요금 삭제.
	 *
	 * @param comOvseaDlvZoneChrge the ComOvseaDlvZoneChrge
	 * @return the ComOvseaDlvZoneChrge
	 * @throws SQLException the SQL exception
	 */
	public int deleteComOvseaDlvZoneChrge(ComOvseaDlvZoneChrge comOvseaDlvZoneChrge) {
		return getSession1().delete("com.plgrim.ncp.base.deleteComOvseaDlvZoneChrge", comOvseaDlvZoneChrge);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

