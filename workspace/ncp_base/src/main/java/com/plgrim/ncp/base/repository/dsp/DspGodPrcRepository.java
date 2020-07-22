
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
import com.plgrim.ncp.base.entities.datasource1.dsp.DspGodPrc;

/**
 * The Class DspGodPrcRepository.
 */
@Repository
public class DspGodPrcRepository extends AbstractRepository {
	

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
	 * 전시 상품 가격 상세 조회.
	 *
	 * @param dspGodPrc the DspGodPrc
	 * @return the DspGodPrc
	 * @throws SQLException the SQL exception
	 */
	public DspGodPrc selectDspGodPrc(DspGodPrc dspGodPrc) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectDspGodPrc", dspGodPrc);
	}
	
	/**
	 * 전시 상품 가격 등록.
	 *
	 * @param dspGodPrc the DspGodPrc
	 * @throws SQLException the SQL exception
	 */
	public void insertDspGodPrc(DspGodPrc dspGodPrc) {
		getSession1().insert("com.plgrim.ncp.base.insertDspGodPrc", dspGodPrc);
	}
	
	/**
	 * 전시 상품 가격 수정.
	 *
	 * @param dspGodPrc the DspGodPrc
	 * @throws SQLException the SQL exception
	 */
	public int updateDspGodPrc(DspGodPrc dspGodPrc) {
		return getSession1().update("com.plgrim.ncp.base.updateDspGodPrc", dspGodPrc);
	}
	
	/**
	 * 전시 상품 가격 삭제.
	 *
	 * @param dspGodPrc the DspGodPrc
	 * @return the DspGodPrc
	 * @throws SQLException the SQL exception
	 */
	public int deleteDspGodPrc(DspGodPrc dspGodPrc) {
		return getSession1().delete("com.plgrim.ncp.base.deleteDspGodPrc", dspGodPrc);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

