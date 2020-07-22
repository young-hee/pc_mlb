
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
import com.plgrim.ncp.base.entities.datasource1.dsp.DspCtgryDspAplBrnd;

/**
 * The Class DspCtgryDspAplBrndRepository.
 */
@Repository
public class DspCtgryDspAplBrndRepository extends AbstractRepository {
	

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
	 * 전시 카테고리 전시 적용 브랜드 상세 조회.
	 *
	 * @param dspCtgryDspAplBrnd the DspCtgryDspAplBrnd
	 * @return the DspCtgryDspAplBrnd
	 * @throws SQLException the SQL exception
	 */
	public DspCtgryDspAplBrnd selectDspCtgryDspAplBrnd(DspCtgryDspAplBrnd dspCtgryDspAplBrnd) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectDspCtgryDspAplBrnd", dspCtgryDspAplBrnd);
	}
	
	/**
	 * 전시 카테고리 전시 적용 브랜드 등록.
	 *
	 * @param dspCtgryDspAplBrnd the DspCtgryDspAplBrnd
	 * @throws SQLException the SQL exception
	 */
	public void insertDspCtgryDspAplBrnd(DspCtgryDspAplBrnd dspCtgryDspAplBrnd) {
		getSession1().insert("com.plgrim.ncp.base.insertDspCtgryDspAplBrnd", dspCtgryDspAplBrnd);
	}
	
	/**
	 * 전시 카테고리 전시 적용 브랜드 수정.
	 *
	 * @param dspCtgryDspAplBrnd the DspCtgryDspAplBrnd
	 * @throws SQLException the SQL exception
	 */
	public int updateDspCtgryDspAplBrnd(DspCtgryDspAplBrnd dspCtgryDspAplBrnd) {
		return getSession1().update("com.plgrim.ncp.base.updateDspCtgryDspAplBrnd", dspCtgryDspAplBrnd);
	}
	
	/**
	 * 전시 카테고리 전시 적용 브랜드 삭제.
	 *
	 * @param dspCtgryDspAplBrnd the DspCtgryDspAplBrnd
	 * @return the DspCtgryDspAplBrnd
	 * @throws SQLException the SQL exception
	 */
	public int deleteDspCtgryDspAplBrnd(DspCtgryDspAplBrnd dspCtgryDspAplBrnd) {
		return getSession1().delete("com.plgrim.ncp.base.deleteDspCtgryDspAplBrnd", dspCtgryDspAplBrnd);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

