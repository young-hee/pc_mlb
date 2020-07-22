
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
import com.plgrim.ncp.base.entities.datasource1.dsp.DspCnrTpGrp;

/**
 * The Class DspCnrTpGrpRepository.
 */
@Repository
public class DspCnrTpGrpRepository extends AbstractRepository {
	

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
	 * 전시 코너 유형 그룹 상세 조회.
	 *
	 * @param dspCnrTpGrp the DspCnrTpGrp
	 * @return the DspCnrTpGrp
	 * @throws SQLException the SQL exception
	 */
	public DspCnrTpGrp selectDspCnrTpGrp(DspCnrTpGrp dspCnrTpGrp) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectDspCnrTpGrp", dspCnrTpGrp);
	}
	
	/**
	 * 전시 코너 유형 그룹 등록.
	 *
	 * @param dspCnrTpGrp the DspCnrTpGrp
	 * @throws SQLException the SQL exception
	 */
	public void insertDspCnrTpGrp(DspCnrTpGrp dspCnrTpGrp) {
		getSession1().insert("com.plgrim.ncp.base.insertDspCnrTpGrp", dspCnrTpGrp);
	}
	
	/**
	 * 전시 코너 유형 그룹 수정.
	 *
	 * @param dspCnrTpGrp the DspCnrTpGrp
	 * @throws SQLException the SQL exception
	 */
	public int updateDspCnrTpGrp(DspCnrTpGrp dspCnrTpGrp) {
		return getSession1().update("com.plgrim.ncp.base.updateDspCnrTpGrp", dspCnrTpGrp);
	}
	
	/**
	 * 전시 코너 유형 그룹 삭제.
	 *
	 * @param dspCnrTpGrp the DspCnrTpGrp
	 * @return the DspCnrTpGrp
	 * @throws SQLException the SQL exception
	 */
	public int deleteDspCnrTpGrp(DspCnrTpGrp dspCnrTpGrp) {
		return getSession1().delete("com.plgrim.ncp.base.deleteDspCnrTpGrp", dspCnrTpGrp);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

