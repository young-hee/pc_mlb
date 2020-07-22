
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
import com.plgrim.ncp.base.entities.datasource1.dsp.DspGodPrmThrgh;

/**
 * The Class DspGodPrmThrghRepository.
 */
@Repository
public class DspGodPrmThrghRepository extends AbstractRepository {
	

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
	 * 전시 상품 프로모션 경유 상세 조회.
	 *
	 * @param dspGodPrmThrgh the DspGodPrmThrgh
	 * @return the DspGodPrmThrgh
	 * @throws SQLException the SQL exception
	 */
	public DspGodPrmThrgh selectDspGodPrmThrgh(DspGodPrmThrgh dspGodPrmThrgh) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectDspGodPrmThrgh", dspGodPrmThrgh);
	}
	
	/**
	 * 전시 상품 프로모션 경유 등록.
	 *
	 * @param dspGodPrmThrgh the DspGodPrmThrgh
	 * @throws SQLException the SQL exception
	 */
	public void insertDspGodPrmThrgh(DspGodPrmThrgh dspGodPrmThrgh) {
		getSession1().insert("com.plgrim.ncp.base.insertDspGodPrmThrgh", dspGodPrmThrgh);
	}
	
	/**
	 * 전시 상품 프로모션 경유 수정.
	 *
	 * @param dspGodPrmThrgh the DspGodPrmThrgh
	 * @throws SQLException the SQL exception
	 */
	public int updateDspGodPrmThrgh(DspGodPrmThrgh dspGodPrmThrgh) {
		return getSession1().update("com.plgrim.ncp.base.updateDspGodPrmThrgh", dspGodPrmThrgh);
	}
	
	/**
	 * 전시 상품 프로모션 경유 삭제.
	 *
	 * @param dspGodPrmThrgh the DspGodPrmThrgh
	 * @return the DspGodPrmThrgh
	 * @throws SQLException the SQL exception
	 */
	public int deleteDspGodPrmThrgh(DspGodPrmThrgh dspGodPrmThrgh) {
		return getSession1().delete("com.plgrim.ncp.base.deleteDspGodPrmThrgh", dspGodPrmThrgh);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

