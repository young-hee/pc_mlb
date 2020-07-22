
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
import com.plgrim.ncp.base.entities.datasource1.dsp.DspCtgryCnncGod;

/**
 * The Class DspCtgryCnncGodRepository.
 */
@Repository
public class DspCtgryCnncGodRepository extends AbstractRepository {
	

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
	 * 전시 카테고리 연결 상품 상세 조회.
	 *
	 * @param dspCtgryCnncGod the DspCtgryCnncGod
	 * @return the DspCtgryCnncGod
	 * @throws SQLException the SQL exception
	 */
	public DspCtgryCnncGod selectDspCtgryCnncGod(DspCtgryCnncGod dspCtgryCnncGod) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectDspCtgryCnncGod", dspCtgryCnncGod);
	}
	
	/**
	 * 전시 카테고리 연결 상품 등록.
	 *
	 * @param dspCtgryCnncGod the DspCtgryCnncGod
	 * @throws SQLException the SQL exception
	 */
	public void insertDspCtgryCnncGod(DspCtgryCnncGod dspCtgryCnncGod) {
		getSession1().insert("com.plgrim.ncp.base.insertDspCtgryCnncGod", dspCtgryCnncGod);
	}
	
	/**
	 * 전시 카테고리 연결 상품 수정.
	 *
	 * @param dspCtgryCnncGod the DspCtgryCnncGod
	 * @throws SQLException the SQL exception
	 */
	public int updateDspCtgryCnncGod(DspCtgryCnncGod dspCtgryCnncGod) {
		return getSession1().update("com.plgrim.ncp.base.updateDspCtgryCnncGod", dspCtgryCnncGod);
	}
	
	/**
	 * 전시 카테고리 연결 상품 삭제.
	 *
	 * @param dspCtgryCnncGod the DspCtgryCnncGod
	 * @return the DspCtgryCnncGod
	 * @throws SQLException the SQL exception
	 */
	public int deleteDspCtgryCnncGod(DspCtgryCnncGod dspCtgryCnncGod) {
		return getSession1().delete("com.plgrim.ncp.base.deleteDspCtgryCnncGod", dspCtgryCnncGod);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

