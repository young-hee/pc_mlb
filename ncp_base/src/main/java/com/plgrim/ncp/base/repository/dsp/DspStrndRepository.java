
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
import com.plgrim.ncp.base.entities.datasource1.dsp.DspStrnd;

/**
 * The Class DspStrndRepository.
 */
@Repository
public class DspStrndRepository extends AbstractRepository {
	

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
	 * 전시 S-TREND 상세 조회.
	 *
	 * @param dspStrnd the DspStrnd
	 * @return the DspStrnd
	 * @throws SQLException the SQL exception
	 */
	public DspStrnd selectDspStrnd(DspStrnd dspStrnd) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectDspStrnd", dspStrnd);
	}
	
	/**
	 * 전시 S-TREND 등록.
	 *
	 * @param dspStrnd the DspStrnd
	 * @throws SQLException the SQL exception
	 */
	public void insertDspStrnd(DspStrnd dspStrnd) {
		getSession1().insert("com.plgrim.ncp.base.insertDspStrnd", dspStrnd);
	}
	
	/**
	 * 전시 S-TREND 수정.
	 *
	 * @param dspStrnd the DspStrnd
	 * @throws SQLException the SQL exception
	 */
	public int updateDspStrnd(DspStrnd dspStrnd) {
		return getSession1().update("com.plgrim.ncp.base.updateDspStrnd", dspStrnd);
	}
	
	/**
	 * 전시 S-TREND 삭제.
	 *
	 * @param dspStrnd the DspStrnd
	 * @return the DspStrnd
	 * @throws SQLException the SQL exception
	 */
	public int deleteDspStrnd(DspStrnd dspStrnd) {
		return getSession1().delete("com.plgrim.ncp.base.deleteDspStrnd", dspStrnd);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

