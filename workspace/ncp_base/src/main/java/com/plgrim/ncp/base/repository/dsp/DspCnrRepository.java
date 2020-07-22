
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
package com.plgrim.ncp.base.repository.dsp;

import java.sql.SQLException;
import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspCnr;

/**
 * The Class DspCnrRepository.
 */
@Repository
public class DspCnrRepository extends AbstractRepository {
	

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
	 * 전시 코너 상세 조회.
	 *
	 * @param dspCnr the DspCnr
	 * @return the DspCnr
	 * @throws SQLException the SQL exception
	 */
	public DspCnr selectDspCnr(DspCnr dspCnr) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectDspCnr", dspCnr);
	}
	
	/**
	 * 전시 코너 등록.
	 *
	 * @param dspCnr the DspCnr
	 * @throws SQLException the SQL exception
	 */
	public void insertDspCnr(DspCnr dspCnr) {
		getSession1().insert("com.plgrim.ncp.base.insertDspCnr", dspCnr);
	}
	
	/**
	 * 전시 코너 수정.
	 *
	 * @param dspCnr the DspCnr
	 * @throws SQLException the SQL exception
	 */
	public int updateDspCnr(DspCnr dspCnr) {
		return getSession1().update("com.plgrim.ncp.base.updateDspCnr", dspCnr);
	}
	
	/**
	 * 전시 코너 삭제.
	 *
	 * @param dspCnr the DspCnr
	 * @return the DspCnr
	 * @throws SQLException the SQL exception
	 */
	public int deleteDspCnr(DspCnr dspCnr) {
		return getSession1().delete("com.plgrim.ncp.base.deleteDspCnr", dspCnr);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

