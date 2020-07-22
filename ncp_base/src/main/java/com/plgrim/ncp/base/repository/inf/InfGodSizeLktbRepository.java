
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
 * Generator(Generator)		2018-06-26                      
 */
package com.plgrim.ncp.base.repository.inf;

import java.sql.SQLException;
import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.datasource1.inf.InfGodSizeLktb;

/**
 * The Class InfGodSizeLktbRepository.
 */
@Repository
public class InfGodSizeLktbRepository extends AbstractRepository {
	

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
	 * 인터페이스 상품 사이즈 조견표 상세 조회.
	 *
	 * @param infGodSizeLktb the InfGodSizeLktb
	 * @return the InfGodSizeLktb
	 * @throws SQLException the SQL exception
	 */
	public InfGodSizeLktb selectInfGodSizeLktb(InfGodSizeLktb infGodSizeLktb) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectInfGodSizeLktb", infGodSizeLktb);
	}
	
	/**
	 * 인터페이스 상품 사이즈 조견표 등록.
	 *
	 * @param infGodSizeLktb the InfGodSizeLktb
	 * @throws SQLException the SQL exception
	 */
	public void insertInfGodSizeLktb(InfGodSizeLktb infGodSizeLktb) {
		getSession1().insert("com.plgrim.ncp.base.insertInfGodSizeLktb", infGodSizeLktb);
	}
	
	/**
	 * 인터페이스 상품 사이즈 조견표 수정.
	 *
	 * @param infGodSizeLktb the InfGodSizeLktb
	 * @throws SQLException the SQL exception
	 */
	public int updateInfGodSizeLktb(InfGodSizeLktb infGodSizeLktb) {
		return getSession1().update("com.plgrim.ncp.base.updateInfGodSizeLktb", infGodSizeLktb);
	}
	
	/**
	 * 인터페이스 상품 사이즈 조견표 삭제.
	 *
	 * @param infGodSizeLktb the InfGodSizeLktb
	 * @return the InfGodSizeLktb
	 * @throws SQLException the SQL exception
	 */
	public int deleteInfGodSizeLktb(InfGodSizeLktb infGodSizeLktb) {
		return getSession1().delete("com.plgrim.ncp.base.deleteInfGodSizeLktb", infGodSizeLktb);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

