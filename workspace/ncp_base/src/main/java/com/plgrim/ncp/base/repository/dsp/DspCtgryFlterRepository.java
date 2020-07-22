
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
import com.plgrim.ncp.base.entities.datasource1.dsp.DspCtgryFlter;

/**
 * The Class DspCtgryFlterRepository.
 */
@Repository
public class DspCtgryFlterRepository extends AbstractRepository {
	

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
	 * 전시 카테고리 필터 상세 조회.
	 *
	 * @param dspCtgryFlter the DspCtgryFlter
	 * @return the DspCtgryFlter
	 * @throws SQLException the SQL exception
	 */
	public DspCtgryFlter selectDspCtgryFlter(DspCtgryFlter dspCtgryFlter) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectDspCtgryFlter", dspCtgryFlter);
	}
	
	/**
	 * 전시 카테고리 필터 등록.
	 *
	 * @param dspCtgryFlter the DspCtgryFlter
	 * @throws SQLException the SQL exception
	 */
	public void insertDspCtgryFlter(DspCtgryFlter dspCtgryFlter) {
		getSession1().insert("com.plgrim.ncp.base.insertDspCtgryFlter", dspCtgryFlter);
	}
	
	/**
	 * 전시 카테고리 필터 수정.
	 *
	 * @param dspCtgryFlter the DspCtgryFlter
	 * @throws SQLException the SQL exception
	 */
	public int updateDspCtgryFlter(DspCtgryFlter dspCtgryFlter) {
		return getSession1().update("com.plgrim.ncp.base.updateDspCtgryFlter", dspCtgryFlter);
	}
	
	/**
	 * 전시 카테고리 필터 삭제.
	 *
	 * @param dspCtgryFlter the DspCtgryFlter
	 * @return the DspCtgryFlter
	 * @throws SQLException the SQL exception
	 */
	public int deleteDspCtgryFlter(DspCtgryFlter dspCtgryFlter) {
		return getSession1().delete("com.plgrim.ncp.base.deleteDspCtgryFlter", dspCtgryFlter);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

