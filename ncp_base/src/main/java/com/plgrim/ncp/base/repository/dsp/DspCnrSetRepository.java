
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
import com.plgrim.ncp.base.entities.datasource1.dsp.DspCnrSet;

/**
 * The Class DspCnrSetRepository.
 */
@Repository
public class DspCnrSetRepository extends AbstractRepository {
	

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
	 * 전시 코너 세트 상세 조회.
	 *
	 * @param dspCnrSet the DspCnrSet
	 * @return the DspCnrSet
	 * @throws SQLException the SQL exception
	 */
	public DspCnrSet selectDspCnrSet(DspCnrSet dspCnrSet) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectDspCnrSet", dspCnrSet);
	}
	
	/**
	 * 전시 코너 세트 등록.
	 *
	 * @param dspCnrSet the DspCnrSet
	 * @throws SQLException the SQL exception
	 */
	public void insertDspCnrSet(DspCnrSet dspCnrSet) {
		getSession1().insert("com.plgrim.ncp.base.insertDspCnrSet", dspCnrSet);
	}
	
	/**
	 * 전시 코너 세트 수정.
	 *
	 * @param dspCnrSet the DspCnrSet
	 * @throws SQLException the SQL exception
	 */
	public int updateDspCnrSet(DspCnrSet dspCnrSet) {
		return getSession1().update("com.plgrim.ncp.base.updateDspCnrSet", dspCnrSet);
	}
	
	/**
	 * 전시 코너 세트 삭제.
	 *
	 * @param dspCnrSet the DspCnrSet
	 * @return the DspCnrSet
	 * @throws SQLException the SQL exception
	 */
	public int deleteDspCnrSet(DspCnrSet dspCnrSet) {
		return getSession1().delete("com.plgrim.ncp.base.deleteDspCnrSet", dspCnrSet);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

