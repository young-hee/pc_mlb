
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
import com.plgrim.ncp.base.entities.datasource1.dsp.DspCtgrySrch;

/**
 * The Class DspCtgrySrchRepository.
 */
@Repository
public class DspCtgrySrchRepository extends AbstractRepository {
	

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
	 * 전시 카테고리 검색 상세 조회.
	 *
	 * @param dspCtgrySrch the DspCtgrySrch
	 * @return the DspCtgrySrch
	 * @throws SQLException the SQL exception
	 */
	public DspCtgrySrch selectDspCtgrySrch(DspCtgrySrch dspCtgrySrch) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectDspCtgrySrch", dspCtgrySrch);
	}
	
	/**
	 * 전시 카테고리 검색 등록.
	 *
	 * @param dspCtgrySrch the DspCtgrySrch
	 * @throws SQLException the SQL exception
	 */
	public void insertDspCtgrySrch(DspCtgrySrch dspCtgrySrch) {
		getSession1().insert("com.plgrim.ncp.base.insertDspCtgrySrch", dspCtgrySrch);
	}
	
	/**
	 * 전시 카테고리 검색 수정.
	 *
	 * @param dspCtgrySrch the DspCtgrySrch
	 * @throws SQLException the SQL exception
	 */
	public int updateDspCtgrySrch(DspCtgrySrch dspCtgrySrch) {
		return getSession1().update("com.plgrim.ncp.base.updateDspCtgrySrch", dspCtgrySrch);
	}
	
	/**
	 * 전시 카테고리 검색 삭제.
	 *
	 * @param dspCtgrySrch the DspCtgrySrch
	 * @return the DspCtgrySrch
	 * @throws SQLException the SQL exception
	 */
	public int deleteDspCtgrySrch(DspCtgrySrch dspCtgrySrch) {
		return getSession1().delete("com.plgrim.ncp.base.deleteDspCtgrySrch", dspCtgrySrch);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

