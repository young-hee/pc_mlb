
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
import com.plgrim.ncp.base.entities.datasource1.dsp.DspRev;

/**
 * The Class DspRevRepository.
 */
@Repository
public class DspRevRepository extends AbstractRepository {
	

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
	 * 전시 개정 상세 조회.
	 *
	 * @param dspRev the DspRev
	 * @return the DspRev
	 * @throws SQLException the SQL exception
	 */
	public DspRev selectDspRev(DspRev dspRev) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectDspRev", dspRev);
	}
	
	/**
	 * 전시 개정 등록.
	 *
	 * @param dspRev the DspRev
	 * @throws SQLException the SQL exception
	 */
	public void insertDspRev(DspRev dspRev) {
		getSession1().insert("com.plgrim.ncp.base.insertDspRev", dspRev);
	}
	
	/**
	 * 전시 개정 수정.
	 *
	 * @param dspRev the DspRev
	 * @throws SQLException the SQL exception
	 */
	public int updateDspRev(DspRev dspRev) {
		return getSession1().update("com.plgrim.ncp.base.updateDspRev", dspRev);
	}
	
	/**
	 * 전시 개정 삭제.
	 *
	 * @param dspRev the DspRev
	 * @return the DspRev
	 * @throws SQLException the SQL exception
	 */
	public int deleteDspRev(DspRev dspRev) {
		return getSession1().delete("com.plgrim.ncp.base.deleteDspRev", dspRev);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

