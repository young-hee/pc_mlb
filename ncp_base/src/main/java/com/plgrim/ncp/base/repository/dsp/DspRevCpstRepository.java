
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
import com.plgrim.ncp.base.entities.datasource1.dsp.DspRevCpst;

/**
 * The Class DspRevCpstRepository.
 */
@Repository
public class DspRevCpstRepository extends AbstractRepository {
	

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
	 * 전시 개정 구성 상세 조회.
	 *
	 * @param dspRevCpst the DspRevCpst
	 * @return the DspRevCpst
	 * @throws SQLException the SQL exception
	 */
	public DspRevCpst selectDspRevCpst(DspRevCpst dspRevCpst) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectDspRevCpst", dspRevCpst);
	}
	
	/**
	 * 전시 개정 구성 등록.
	 *
	 * @param dspRevCpst the DspRevCpst
	 * @throws SQLException the SQL exception
	 */
	public void insertDspRevCpst(DspRevCpst dspRevCpst) {
		getSession1().insert("com.plgrim.ncp.base.insertDspRevCpst", dspRevCpst);
	}
	
	/**
	 * 전시 개정 구성 수정.
	 *
	 * @param dspRevCpst the DspRevCpst
	 * @throws SQLException the SQL exception
	 */
	public int updateDspRevCpst(DspRevCpst dspRevCpst) {
		return getSession1().update("com.plgrim.ncp.base.updateDspRevCpst", dspRevCpst);
	}
	
	/**
	 * 전시 개정 구성 삭제.
	 *
	 * @param dspRevCpst the DspRevCpst
	 * @return the DspRevCpst
	 * @throws SQLException the SQL exception
	 */
	public int deleteDspRevCpst(DspRevCpst dspRevCpst) {
		return getSession1().delete("com.plgrim.ncp.base.deleteDspRevCpst", dspRevCpst);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

