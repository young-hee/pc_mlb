
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
import com.plgrim.ncp.base.entities.datasource1.dsp.DspCtgryDspApl;

/**
 * The Class DspCtgryDspAplRepository.
 */
@Repository
public class DspCtgryDspAplRepository extends AbstractRepository {
	

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
	 * 전시 카테고리 전시 적용 상세 조회.
	 *
	 * @param dspCtgryDspApl the DspCtgryDspApl
	 * @return the DspCtgryDspApl
	 * @throws SQLException the SQL exception
	 */
	public DspCtgryDspApl selectDspCtgryDspApl(DspCtgryDspApl dspCtgryDspApl) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectDspCtgryDspApl", dspCtgryDspApl);
	}
	
	/**
	 * 전시 카테고리 전시 적용 등록.
	 *
	 * @param dspCtgryDspApl the DspCtgryDspApl
	 * @throws SQLException the SQL exception
	 */
	public void insertDspCtgryDspApl(DspCtgryDspApl dspCtgryDspApl) {
		getSession1().insert("com.plgrim.ncp.base.insertDspCtgryDspApl", dspCtgryDspApl);
	}
	
	/**
	 * 전시 카테고리 전시 적용 수정.
	 *
	 * @param dspCtgryDspApl the DspCtgryDspApl
	 * @throws SQLException the SQL exception
	 */
	public int updateDspCtgryDspApl(DspCtgryDspApl dspCtgryDspApl) {
		return getSession1().update("com.plgrim.ncp.base.updateDspCtgryDspApl", dspCtgryDspApl);
	}
	
	/**
	 * 전시 카테고리 전시 적용 삭제.
	 *
	 * @param dspCtgryDspApl the DspCtgryDspApl
	 * @return the DspCtgryDspApl
	 * @throws SQLException the SQL exception
	 */
	public int deleteDspCtgryDspApl(DspCtgryDspApl dspCtgryDspApl) {
		return getSession1().delete("com.plgrim.ncp.base.deleteDspCtgryDspApl", dspCtgryDspApl);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

