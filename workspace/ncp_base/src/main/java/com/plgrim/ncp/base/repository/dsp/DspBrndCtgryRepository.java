
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
import com.plgrim.ncp.base.entities.datasource1.dsp.DspBrndCtgry;

/**
 * The Class DspBrndCtgryRepository.
 */
@Repository
public class DspBrndCtgryRepository extends AbstractRepository {
	

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
	 * 전시 브랜드 카테고리 상세 조회.
	 *
	 * @param dspBrndCtgry the DspBrndCtgry
	 * @return the DspBrndCtgry
	 * @throws SQLException the SQL exception
	 */
	public DspBrndCtgry selectDspBrndCtgry(DspBrndCtgry dspBrndCtgry) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectDspBrndCtgry", dspBrndCtgry);
	}
	
	/**
	 * 전시 브랜드 카테고리 등록.
	 *
	 * @param dspBrndCtgry the DspBrndCtgry
	 * @throws SQLException the SQL exception
	 */
	public void insertDspBrndCtgry(DspBrndCtgry dspBrndCtgry) {
		getSession1().insert("com.plgrim.ncp.base.insertDspBrndCtgry", dspBrndCtgry);
	}
	
	/**
	 * 전시 브랜드 카테고리 수정.
	 *
	 * @param dspBrndCtgry the DspBrndCtgry
	 * @throws SQLException the SQL exception
	 */
	public int updateDspBrndCtgry(DspBrndCtgry dspBrndCtgry) {
		return getSession1().update("com.plgrim.ncp.base.updateDspBrndCtgry", dspBrndCtgry);
	}
	
	/**
	 * 전시 브랜드 카테고리 삭제.
	 *
	 * @param dspBrndCtgry the DspBrndCtgry
	 * @return the DspBrndCtgry
	 * @throws SQLException the SQL exception
	 */
	public int deleteDspBrndCtgry(DspBrndCtgry dspBrndCtgry) {
		return getSession1().delete("com.plgrim.ncp.base.deleteDspBrndCtgry", dspBrndCtgry);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

