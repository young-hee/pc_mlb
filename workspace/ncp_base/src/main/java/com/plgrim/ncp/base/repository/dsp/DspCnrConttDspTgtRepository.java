
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
import com.plgrim.ncp.base.entities.datasource1.dsp.DspCnrConttDspTgt;

/**
 * The Class DspCnrConttDspTgtRepository.
 */
@Repository
public class DspCnrConttDspTgtRepository extends AbstractRepository {
	

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
	 * 전시 코너 컨텐츠 전시 대상 상세 조회.
	 *
	 * @param dspCnrConttDspTgt the DspCnrConttDspTgt
	 * @return the DspCnrConttDspTgt
	 * @throws SQLException the SQL exception
	 */
	public DspCnrConttDspTgt selectDspCnrConttDspTgt(DspCnrConttDspTgt dspCnrConttDspTgt) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectDspCnrConttDspTgt", dspCnrConttDspTgt);
	}
	
	/**
	 * 전시 코너 컨텐츠 전시 대상 등록.
	 *
	 * @param dspCnrConttDspTgt the DspCnrConttDspTgt
	 * @throws SQLException the SQL exception
	 */
	public void insertDspCnrConttDspTgt(DspCnrConttDspTgt dspCnrConttDspTgt) {
		getSession1().insert("com.plgrim.ncp.base.insertDspCnrConttDspTgt", dspCnrConttDspTgt);
	}
	
	/**
	 * 전시 코너 컨텐츠 전시 대상 수정.
	 *
	 * @param dspCnrConttDspTgt the DspCnrConttDspTgt
	 * @throws SQLException the SQL exception
	 */
	public int updateDspCnrConttDspTgt(DspCnrConttDspTgt dspCnrConttDspTgt) {
		return getSession1().update("com.plgrim.ncp.base.updateDspCnrConttDspTgt", dspCnrConttDspTgt);
	}
	
	/**
	 * 전시 코너 컨텐츠 전시 대상 삭제.
	 *
	 * @param dspCnrConttDspTgt the DspCnrConttDspTgt
	 * @return the DspCnrConttDspTgt
	 * @throws SQLException the SQL exception
	 */
	public int deleteDspCnrConttDspTgt(DspCnrConttDspTgt dspCnrConttDspTgt) {
		return getSession1().delete("com.plgrim.ncp.base.deleteDspCnrConttDspTgt", dspCnrConttDspTgt);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

