
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
 * Generator(Generator)		2018-06-07                      
 */
package com.plgrim.ncp.base.repository.dsp;

import java.sql.SQLException;
import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspUseGrp;

/**
 * The Class DspUseGrpRepository.
 */
@Repository
public class DspUseGrpRepository extends AbstractRepository {
	

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
	 * 전시 사용 그룹 상세 조회.
	 *
	 * @param dspUseGrp the DspUseGrp
	 * @return the DspUseGrp
	 * @throws SQLException the SQL exception
	 */
	public DspUseGrp selectDspUseGrp(DspUseGrp dspUseGrp) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectDspUseGrp", dspUseGrp);
	}
	
	/**
	 * 전시 사용 그룹 등록.
	 *
	 * @param dspUseGrp the DspUseGrp
	 * @throws SQLException the SQL exception
	 */
	public void insertDspUseGrp(DspUseGrp dspUseGrp) {
		getSession1().insert("com.plgrim.ncp.base.insertDspUseGrp", dspUseGrp);
	}
	
	/**
	 * 전시 사용 그룹 수정.
	 *
	 * @param dspUseGrp the DspUseGrp
	 * @throws SQLException the SQL exception
	 */
	public int updateDspUseGrp(DspUseGrp dspUseGrp) {
		return getSession1().update("com.plgrim.ncp.base.updateDspUseGrp", dspUseGrp);
	}
	
	/**
	 * 전시 사용 그룹 삭제.
	 *
	 * @param dspUseGrp the DspUseGrp
	 * @return the DspUseGrp
	 * @throws SQLException the SQL exception
	 */
	public int deleteDspUseGrp(DspUseGrp dspUseGrp) {
		return getSession1().delete("com.plgrim.ncp.base.deleteDspUseGrp", dspUseGrp);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

