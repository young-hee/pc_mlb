
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
import com.plgrim.ncp.base.entities.datasource1.dsp.DspStrndDspTgt;

/**
 * The Class DspStrndDspTgtRepository.
 */
@Repository
public class DspStrndDspTgtRepository extends AbstractRepository {
	

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
	 * 전시 S-TREND 전시 대상 상세 조회.
	 *
	 * @param dspStrndDspTgt the DspStrndDspTgt
	 * @return the DspStrndDspTgt
	 * @throws SQLException the SQL exception
	 */
	public DspStrndDspTgt selectDspStrndDspTgt(DspStrndDspTgt dspStrndDspTgt) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectDspStrndDspTgt", dspStrndDspTgt);
	}
	
	/**
	 * 전시 S-TREND 전시 대상 등록.
	 *
	 * @param dspStrndDspTgt the DspStrndDspTgt
	 * @throws SQLException the SQL exception
	 */
	public void insertDspStrndDspTgt(DspStrndDspTgt dspStrndDspTgt) {
		getSession1().insert("com.plgrim.ncp.base.insertDspStrndDspTgt", dspStrndDspTgt);
	}
	
	/**
	 * 전시 S-TREND 전시 대상 수정.
	 *
	 * @param dspStrndDspTgt the DspStrndDspTgt
	 * @throws SQLException the SQL exception
	 */
	public int updateDspStrndDspTgt(DspStrndDspTgt dspStrndDspTgt) {
		return getSession1().update("com.plgrim.ncp.base.updateDspStrndDspTgt", dspStrndDspTgt);
	}
	
	/**
	 * 전시 S-TREND 전시 대상 삭제.
	 *
	 * @param dspStrndDspTgt the DspStrndDspTgt
	 * @return the DspStrndDspTgt
	 * @throws SQLException the SQL exception
	 */
	public int deleteDspStrndDspTgt(DspStrndDspTgt dspStrndDspTgt) {
		return getSession1().delete("com.plgrim.ncp.base.deleteDspStrndDspTgt", dspStrndDspTgt);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

