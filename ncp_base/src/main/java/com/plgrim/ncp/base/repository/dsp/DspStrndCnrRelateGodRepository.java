
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
import com.plgrim.ncp.base.entities.datasource1.dsp.DspStrndCnrRelateGod;

/**
 * The Class DspStrndCnrRelateGodRepository.
 */
@Repository
public class DspStrndCnrRelateGodRepository extends AbstractRepository {
	

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
	 * 전시 S-TREND 코너 연관 상품 상세 조회.
	 *
	 * @param dspStrndCnrRelateGod the DspStrndCnrRelateGod
	 * @return the DspStrndCnrRelateGod
	 * @throws SQLException the SQL exception
	 */
	public DspStrndCnrRelateGod selectDspStrndCnrRelateGod(DspStrndCnrRelateGod dspStrndCnrRelateGod) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectDspStrndCnrRelateGod", dspStrndCnrRelateGod);
	}
	
	/**
	 * 전시 S-TREND 코너 연관 상품 등록.
	 *
	 * @param dspStrndCnrRelateGod the DspStrndCnrRelateGod
	 * @throws SQLException the SQL exception
	 */
	public void insertDspStrndCnrRelateGod(DspStrndCnrRelateGod dspStrndCnrRelateGod) {
		getSession1().insert("com.plgrim.ncp.base.insertDspStrndCnrRelateGod", dspStrndCnrRelateGod);
	}
	
	/**
	 * 전시 S-TREND 코너 연관 상품 수정.
	 *
	 * @param dspStrndCnrRelateGod the DspStrndCnrRelateGod
	 * @throws SQLException the SQL exception
	 */
	public int updateDspStrndCnrRelateGod(DspStrndCnrRelateGod dspStrndCnrRelateGod) {
		return getSession1().update("com.plgrim.ncp.base.updateDspStrndCnrRelateGod", dspStrndCnrRelateGod);
	}
	
	/**
	 * 전시 S-TREND 코너 연관 상품 삭제.
	 *
	 * @param dspStrndCnrRelateGod the DspStrndCnrRelateGod
	 * @return the DspStrndCnrRelateGod
	 * @throws SQLException the SQL exception
	 */
	public int deleteDspStrndCnrRelateGod(DspStrndCnrRelateGod dspStrndCnrRelateGod) {
		return getSession1().delete("com.plgrim.ncp.base.deleteDspStrndCnrRelateGod", dspStrndCnrRelateGod);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

