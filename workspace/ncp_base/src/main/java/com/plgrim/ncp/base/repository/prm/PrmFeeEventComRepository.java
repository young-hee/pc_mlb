
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
import com.plgrim.ncp.base.entities.datasource1.prm.PrmFeeEventCom;

/**
 * The Class PrmFeeEventComRepository.
 */
@Repository
public class PrmFeeEventComRepository extends AbstractRepository {
	

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
	 * 프로모션 수수료 행사 업체 상세 조회.
	 *
	 * @param prmFeeEventCom the PrmFeeEventCom
	 * @return the PrmFeeEventCom
	 * @throws SQLException the SQL exception
	 */
	public PrmFeeEventCom selectPrmFeeEventCom(PrmFeeEventCom prmFeeEventCom) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectPrmFeeEventCom", prmFeeEventCom);
	}
	
	/**
	 * 프로모션 수수료 행사 업체 등록.
	 *
	 * @param prmFeeEventCom the PrmFeeEventCom
	 * @throws SQLException the SQL exception
	 */
	public void insertPrmFeeEventCom(PrmFeeEventCom prmFeeEventCom) {
		getSession1().insert("com.plgrim.ncp.base.insertPrmFeeEventCom", prmFeeEventCom);
	}
	
	/**
	 * 프로모션 수수료 행사 업체 수정.
	 *
	 * @param prmFeeEventCom the PrmFeeEventCom
	 * @throws SQLException the SQL exception
	 */
	public int updatePrmFeeEventCom(PrmFeeEventCom prmFeeEventCom) {
		return getSession1().update("com.plgrim.ncp.base.updatePrmFeeEventCom", prmFeeEventCom);
	}
	
	/**
	 * 프로모션 수수료 행사 업체 삭제.
	 *
	 * @param prmFeeEventCom the PrmFeeEventCom
	 * @return the PrmFeeEventCom
	 * @throws SQLException the SQL exception
	 */
	public int deletePrmFeeEventCom(PrmFeeEventCom prmFeeEventCom) {
		return getSession1().delete("com.plgrim.ncp.base.deletePrmFeeEventCom", prmFeeEventCom);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

