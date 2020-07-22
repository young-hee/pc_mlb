
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
 * Generator(Generator)		2018-06-05                      
 */
package com.plgrim.ncp.base.repository.dsp;

import java.sql.SQLException;
import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspCnrContt;

/**
 * The Class DspCnrConttRepository.
 */
@Repository
public class DspCnrConttRepository extends AbstractRepository {
	

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
	 * 전시 코너 컨텐츠 상세 조회.
	 *
	 * @param dspCnrContt the DspCnrContt
	 * @return the DspCnrContt
	 * @throws SQLException the SQL exception
	 */
	public DspCnrContt selectDspCnrContt(DspCnrContt dspCnrContt) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectDspCnrContt", dspCnrContt);
	}
	
	/**
	 * 전시 코너 컨텐츠 등록.
	 *
	 * @param dspCnrContt the DspCnrContt
	 * @throws SQLException the SQL exception
	 */
	public void insertDspCnrContt(DspCnrContt dspCnrContt) {
		getSession1().insert("com.plgrim.ncp.base.insertDspCnrContt", dspCnrContt);
	}
	
	/**
	 * 전시 코너 컨텐츠 수정.
	 *
	 * @param dspCnrContt the DspCnrContt
	 * @throws SQLException the SQL exception
	 */
	public int updateDspCnrContt(DspCnrContt dspCnrContt) {
		return getSession1().update("com.plgrim.ncp.base.updateDspCnrContt", dspCnrContt);
	}
	
	/**
	 * 전시 코너 컨텐츠 삭제.
	 *
	 * @param dspCnrContt the DspCnrContt
	 * @return the DspCnrContt
	 * @throws SQLException the SQL exception
	 */
	public int deleteDspCnrContt(DspCnrContt dspCnrContt) {
		return getSession1().delete("com.plgrim.ncp.base.deleteDspCnrContt", dspCnrContt);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

