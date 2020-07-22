
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
import com.plgrim.ncp.base.entities.datasource1.com.ComAffComDcExcpGod;

/**
 * The Class ComAffComDcExcpGodRepository.
 */
@Repository
public class ComAffComDcExcpGodRepository extends AbstractRepository {
	

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
	 * 업체 제휴 업체 할인 예외 상품 상세 조회.
	 *
	 * @param comAffComDcExcpGod the ComAffComDcExcpGod
	 * @return the ComAffComDcExcpGod
	 * @throws SQLException the SQL exception
	 */
	public ComAffComDcExcpGod selectComAffComDcExcpGod(ComAffComDcExcpGod comAffComDcExcpGod) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectComAffComDcExcpGod", comAffComDcExcpGod);
	}
	
	/**
	 * 업체 제휴 업체 할인 예외 상품 등록.
	 *
	 * @param comAffComDcExcpGod the ComAffComDcExcpGod
	 * @throws SQLException the SQL exception
	 */
	public void insertComAffComDcExcpGod(ComAffComDcExcpGod comAffComDcExcpGod) {
		getSession1().insert("com.plgrim.ncp.base.insertComAffComDcExcpGod", comAffComDcExcpGod);
	}
	
	/**
	 * 업체 제휴 업체 할인 예외 상품 수정.
	 *
	 * @param comAffComDcExcpGod the ComAffComDcExcpGod
	 * @throws SQLException the SQL exception
	 */
	public int updateComAffComDcExcpGod(ComAffComDcExcpGod comAffComDcExcpGod) {
		return getSession1().update("com.plgrim.ncp.base.updateComAffComDcExcpGod", comAffComDcExcpGod);
	}
	
	/**
	 * 업체 제휴 업체 할인 예외 상품 삭제.
	 *
	 * @param comAffComDcExcpGod the ComAffComDcExcpGod
	 * @return the ComAffComDcExcpGod
	 * @throws SQLException the SQL exception
	 */
	public int deleteComAffComDcExcpGod(ComAffComDcExcpGod comAffComDcExcpGod) {
		return getSession1().delete("com.plgrim.ncp.base.deleteComAffComDcExcpGod", comAffComDcExcpGod);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

