
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
 * Generator(Generator)		2019-02-01                      
 */
package com.plgrim.ncp.base.repository.dsp;

import java.sql.SQLException;
import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspPromtGrp;

/**
 * The Class DspPromtGrpRepository.
 */
@Repository
public class DspPromtGrpRepository extends AbstractRepository {
	

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
	 * 전시 기획전 그룹 상세 조회.
	 *
	 * @param dspPromtGrp the DspPromtGrp
	 * @return the DspPromtGrp
	 * @throws SQLException the SQL exception
	 */
	public DspPromtGrp selectDspPromtGrp(DspPromtGrp dspPromtGrp) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectDspPromtGrp", dspPromtGrp);
	}
	
	/**
	 * 전시 기획전 그룹 등록.
	 *
	 * @param dspPromtGrp the DspPromtGrp
	 * @throws SQLException the SQL exception
	 */
	public void insertDspPromtGrp(DspPromtGrp dspPromtGrp) {
		getSession1().insert("com.plgrim.ncp.base.insertDspPromtGrp", dspPromtGrp);
	}
	
	/**
	 * 전시 기획전 그룹 수정.
	 *
	 * @param dspPromtGrp the DspPromtGrp
	 * @throws SQLException the SQL exception
	 */
	public int updateDspPromtGrp(DspPromtGrp dspPromtGrp) {
		return getSession1().update("com.plgrim.ncp.base.updateDspPromtGrp", dspPromtGrp);
	}
	
	/**
	 * 전시 기획전 그룹 삭제.
	 *
	 * @param dspPromtGrp the DspPromtGrp
	 * @return the DspPromtGrp
	 * @throws SQLException the SQL exception
	 */
	public int deleteDspPromtGrp(DspPromtGrp dspPromtGrp) {
		return getSession1().delete("com.plgrim.ncp.base.deleteDspPromtGrp", dspPromtGrp);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

