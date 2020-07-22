
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
import com.plgrim.ncp.base.entities.datasource1.com.ComAffComInvExcpItm;

/**
 * The Class ComAffComInvExcpItmRepository.
 */
@Repository
public class ComAffComInvExcpItmRepository extends AbstractRepository {
	

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
	 * 업체 제휴 업체 재고 예외 단품 상세 조회.
	 *
	 * @param comAffComInvExcpItm the ComAffComInvExcpItm
	 * @return the ComAffComInvExcpItm
	 * @throws SQLException the SQL exception
	 */
	public ComAffComInvExcpItm selectComAffComInvExcpItm(ComAffComInvExcpItm comAffComInvExcpItm) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectComAffComInvExcpItm", comAffComInvExcpItm);
	}
	
	/**
	 * 업체 제휴 업체 재고 예외 단품 등록.
	 *
	 * @param comAffComInvExcpItm the ComAffComInvExcpItm
	 * @throws SQLException the SQL exception
	 */
	public void insertComAffComInvExcpItm(ComAffComInvExcpItm comAffComInvExcpItm) {
		getSession1().insert("com.plgrim.ncp.base.insertComAffComInvExcpItm", comAffComInvExcpItm);
	}
	
	/**
	 * 업체 제휴 업체 재고 예외 단품 수정.
	 *
	 * @param comAffComInvExcpItm the ComAffComInvExcpItm
	 * @throws SQLException the SQL exception
	 */
	public int updateComAffComInvExcpItm(ComAffComInvExcpItm comAffComInvExcpItm) {
		return getSession1().update("com.plgrim.ncp.base.updateComAffComInvExcpItm", comAffComInvExcpItm);
	}
	
	/**
	 * 업체 제휴 업체 재고 예외 단품 삭제.
	 *
	 * @param comAffComInvExcpItm the ComAffComInvExcpItm
	 * @return the ComAffComInvExcpItm
	 * @throws SQLException the SQL exception
	 */
	public int deleteComAffComInvExcpItm(ComAffComInvExcpItm comAffComInvExcpItm) {
		return getSession1().delete("com.plgrim.ncp.base.deleteComAffComInvExcpItm", comAffComInvExcpItm);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

