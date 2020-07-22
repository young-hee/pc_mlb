
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
import com.plgrim.ncp.base.entities.datasource1.clm.ClmWrhsGodAplPrm;

/**
 * The Class ClmWrhsGodAplPrmRepository.
 */
@Repository
public class ClmWrhsGodAplPrmRepository extends AbstractRepository {
	

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
	 * 클레임 입고 상품 적용 프로모션 상세 조회.
	 *
	 * @param clmWrhsGodAplPrm the ClmWrhsGodAplPrm
	 * @return the ClmWrhsGodAplPrm
	 * @throws SQLException the SQL exception
	 */
	public ClmWrhsGodAplPrm selectClmWrhsGodAplPrm(ClmWrhsGodAplPrm clmWrhsGodAplPrm) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectClmWrhsGodAplPrm", clmWrhsGodAplPrm);
	}
	
	/**
	 * 클레임 입고 상품 적용 프로모션 등록.
	 *
	 * @param clmWrhsGodAplPrm the ClmWrhsGodAplPrm
	 * @throws SQLException the SQL exception
	 */
	public void insertClmWrhsGodAplPrm(ClmWrhsGodAplPrm clmWrhsGodAplPrm) {
		getSession1().insert("com.plgrim.ncp.base.insertClmWrhsGodAplPrm", clmWrhsGodAplPrm);
	}
	
	/**
	 * 클레임 입고 상품 적용 프로모션 수정.
	 *
	 * @param clmWrhsGodAplPrm the ClmWrhsGodAplPrm
	 * @throws SQLException the SQL exception
	 */
	public int updateClmWrhsGodAplPrm(ClmWrhsGodAplPrm clmWrhsGodAplPrm) {
		return getSession1().update("com.plgrim.ncp.base.updateClmWrhsGodAplPrm", clmWrhsGodAplPrm);
	}
	
	/**
	 * 클레임 입고 상품 적용 프로모션 삭제.
	 *
	 * @param clmWrhsGodAplPrm the ClmWrhsGodAplPrm
	 * @return the ClmWrhsGodAplPrm
	 * @throws SQLException the SQL exception
	 */
	public int deleteClmWrhsGodAplPrm(ClmWrhsGodAplPrm clmWrhsGodAplPrm) {
		return getSession1().delete("com.plgrim.ncp.base.deleteClmWrhsGodAplPrm", clmWrhsGodAplPrm);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

