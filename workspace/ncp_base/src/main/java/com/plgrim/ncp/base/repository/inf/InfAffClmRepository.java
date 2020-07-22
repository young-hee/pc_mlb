
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
package com.plgrim.ncp.base.repository.inf;

import java.sql.SQLException;
import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.datasource1.inf.InfAffClm;

/**
 * The Class InfAffClmRepository.
 */
@Repository
public class InfAffClmRepository extends AbstractRepository {
	

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
	 * 인터페이스 제휴 클레임 상세 조회.
	 *
	 * @param infAffClm the InfAffClm
	 * @return the InfAffClm
	 * @throws SQLException the SQL exception
	 */
	public InfAffClm selectInfAffClm(InfAffClm infAffClm) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectInfAffClm", infAffClm);
	}
	
	/**
	 * 인터페이스 제휴 클레임 등록.
	 *
	 * @param infAffClm the InfAffClm
	 * @throws SQLException the SQL exception
	 */
	public void insertInfAffClm(InfAffClm infAffClm) {
		getSession1().insert("com.plgrim.ncp.base.insertInfAffClm", infAffClm);
	}
	
	/**
	 * 인터페이스 제휴 클레임 수정.
	 *
	 * @param infAffClm the InfAffClm
	 * @throws SQLException the SQL exception
	 */
	public int updateInfAffClm(InfAffClm infAffClm) {
		return getSession1().update("com.plgrim.ncp.base.updateInfAffClm", infAffClm);
	}
	
	/**
	 * 인터페이스 제휴 클레임 삭제.
	 *
	 * @param infAffClm the InfAffClm
	 * @return the InfAffClm
	 * @throws SQLException the SQL exception
	 */
	public int deleteInfAffClm(InfAffClm infAffClm) {
		return getSession1().delete("com.plgrim.ncp.base.deleteInfAffClm", infAffClm);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

