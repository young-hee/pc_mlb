
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
import com.plgrim.ncp.base.entities.datasource1.dsp.DspBstGodEvl;

/**
 * The Class DspBstGodEvlRepository.
 */
@Repository
public class DspBstGodEvlRepository extends AbstractRepository {
	

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
	 * 전시 베스트 상품평 상세 조회.
	 *
	 * @param dspBstGodEvl the DspBstGodEvl
	 * @return the DspBstGodEvl
	 * @throws SQLException the SQL exception
	 */
	public DspBstGodEvl selectDspBstGodEvl(DspBstGodEvl dspBstGodEvl) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectDspBstGodEvl", dspBstGodEvl);
	}
	
	/**
	 * 전시 베스트 상품평 등록.
	 *
	 * @param dspBstGodEvl the DspBstGodEvl
	 * @throws SQLException the SQL exception
	 */
	public void insertDspBstGodEvl(DspBstGodEvl dspBstGodEvl) {
		getSession1().insert("com.plgrim.ncp.base.insertDspBstGodEvl", dspBstGodEvl);
	}
	
	/**
	 * 전시 베스트 상품평 수정.
	 *
	 * @param dspBstGodEvl the DspBstGodEvl
	 * @throws SQLException the SQL exception
	 */
	public int updateDspBstGodEvl(DspBstGodEvl dspBstGodEvl) {
		return getSession1().update("com.plgrim.ncp.base.updateDspBstGodEvl", dspBstGodEvl);
	}
	
	/**
	 * 전시 베스트 상품평 삭제.
	 *
	 * @param dspBstGodEvl the DspBstGodEvl
	 * @return the DspBstGodEvl
	 * @throws SQLException the SQL exception
	 */
	public int deleteDspBstGodEvl(DspBstGodEvl dspBstGodEvl) {
		return getSession1().delete("com.plgrim.ncp.base.deleteDspBstGodEvl", dspBstGodEvl);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

