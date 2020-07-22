
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
import com.plgrim.ncp.base.entities.datasource1.dsp.DspRecomendAltrtv;

/**
 * The Class DspRecomendAltrtvRepository.
 */
@Repository
public class DspRecomendAltrtvRepository extends AbstractRepository {
	

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
	 * 전시 추천 대체 상세 조회.
	 *
	 * @param dspRecomendAltrtv the DspRecomendAltrtv
	 * @return the DspRecomendAltrtv
	 * @throws SQLException the SQL exception
	 */
	public DspRecomendAltrtv selectDspRecomendAltrtv(DspRecomendAltrtv dspRecomendAltrtv) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectDspRecomendAltrtv", dspRecomendAltrtv);
	}
	
	/**
	 * 전시 추천 대체 등록.
	 *
	 * @param dspRecomendAltrtv the DspRecomendAltrtv
	 * @throws SQLException the SQL exception
	 */
	public void insertDspRecomendAltrtv(DspRecomendAltrtv dspRecomendAltrtv) {
		getSession1().insert("com.plgrim.ncp.base.insertDspRecomendAltrtv", dspRecomendAltrtv);
	}
	
	/**
	 * 전시 추천 대체 수정.
	 *
	 * @param dspRecomendAltrtv the DspRecomendAltrtv
	 * @throws SQLException the SQL exception
	 */
	public int updateDspRecomendAltrtv(DspRecomendAltrtv dspRecomendAltrtv) {
		return getSession1().update("com.plgrim.ncp.base.updateDspRecomendAltrtv", dspRecomendAltrtv);
	}
	
	/**
	 * 전시 추천 대체 삭제.
	 *
	 * @param dspRecomendAltrtv the DspRecomendAltrtv
	 * @return the DspRecomendAltrtv
	 * @throws SQLException the SQL exception
	 */
	public int deleteDspRecomendAltrtv(DspRecomendAltrtv dspRecomendAltrtv) {
		return getSession1().delete("com.plgrim.ncp.base.deleteDspRecomendAltrtv", dspRecomendAltrtv);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

