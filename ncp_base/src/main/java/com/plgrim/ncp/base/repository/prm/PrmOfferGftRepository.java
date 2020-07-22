
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
package com.plgrim.ncp.base.repository.prm;

import java.sql.SQLException;
import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.datasource1.prm.PrmOfferGft;

/**
 * The Class PrmOfferGftRepository.
 */
@Repository
public class PrmOfferGftRepository extends AbstractRepository {
	

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
	 * 프로모션 제공 사은품 상세 조회.
	 *
	 * @param prmOfferGft the PrmOfferGft
	 * @return the PrmOfferGft
	 * @throws SQLException the SQL exception
	 */
	public PrmOfferGft selectPrmOfferGft(PrmOfferGft prmOfferGft) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectPrmOfferGft", prmOfferGft);
	}
	
	/**
	 * 프로모션 제공 사은품 등록.
	 *
	 * @param prmOfferGft the PrmOfferGft
	 * @throws SQLException the SQL exception
	 */
	public void insertPrmOfferGft(PrmOfferGft prmOfferGft) {
		getSession1().insert("com.plgrim.ncp.base.insertPrmOfferGft", prmOfferGft);
	}
	
	/**
	 * 프로모션 제공 사은품 수정.
	 *
	 * @param prmOfferGft the PrmOfferGft
	 * @throws SQLException the SQL exception
	 */
	public int updatePrmOfferGft(PrmOfferGft prmOfferGft) {
		return getSession1().update("com.plgrim.ncp.base.updatePrmOfferGft", prmOfferGft);
	}
	
	/**
	 * 프로모션 제공 사은품 삭제.
	 *
	 * @param prmOfferGft the PrmOfferGft
	 * @return the PrmOfferGft
	 * @throws SQLException the SQL exception
	 */
	public int deletePrmOfferGft(PrmOfferGft prmOfferGft) {
		return getSession1().delete("com.plgrim.ncp.base.deletePrmOfferGft", prmOfferGft);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

