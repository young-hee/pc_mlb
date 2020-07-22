
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
import com.plgrim.ncp.base.entities.datasource1.dsp.DspCtgry;

/**
 * The Class DspCtgryRepository.
 */
@Repository
public class DspCtgryRepository extends AbstractRepository {
	

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
	 * 전시 카테고리 상세 조회.
	 *
	 * @param dspCtgry the DspCtgry
	 * @return the DspCtgry
	 * @throws SQLException the SQL exception
	 */
	public DspCtgry selectDspCtgry(DspCtgry dspCtgry) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectDspCtgry", dspCtgry);
	}
	
	/**
	 * 전시 카테고리 등록.
	 *
	 * @param dspCtgry the DspCtgry
	 * @throws SQLException the SQL exception
	 */
	public void insertDspCtgry(DspCtgry dspCtgry) {
		getSession1().insert("com.plgrim.ncp.base.insertDspCtgry", dspCtgry);
	}
	
	/**
	 * 전시 카테고리 수정.
	 *
	 * @param dspCtgry the DspCtgry
	 * @throws SQLException the SQL exception
	 */
	public int updateDspCtgry(DspCtgry dspCtgry) {
		return getSession1().update("com.plgrim.ncp.base.updateDspCtgry", dspCtgry);
	}
	
	/**
	 * 전시 카테고리 삭제.
	 *
	 * @param dspCtgry the DspCtgry
	 * @return the DspCtgry
	 * @throws SQLException the SQL exception
	 */
	public int deleteDspCtgry(DspCtgry dspCtgry) {
		return getSession1().delete("com.plgrim.ncp.base.deleteDspCtgry", dspCtgry);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

