
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
import com.plgrim.ncp.base.entities.datasource1.dsp.DspGodPrcLdadng;

/**
 * The Class DspGodPrcLdadngRepository.
 */
@Repository
public class DspGodPrcLdadngRepository extends AbstractRepository {
	

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
	 * 전시 상품 가격 적재 상세 조회.
	 *
	 * @param dspGodPrcLdadng the DspGodPrcLdadng
	 * @return the DspGodPrcLdadng
	 * @throws SQLException the SQL exception
	 */
	public DspGodPrcLdadng selectDspGodPrcLdadng(DspGodPrcLdadng dspGodPrcLdadng) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectDspGodPrcLdadng", dspGodPrcLdadng);
	}
	
	/**
	 * 전시 상품 가격 적재 등록.
	 *
	 * @param dspGodPrcLdadng the DspGodPrcLdadng
	 * @throws SQLException the SQL exception
	 */
	public void insertDspGodPrcLdadng(DspGodPrcLdadng dspGodPrcLdadng) {
		getSession1().insert("com.plgrim.ncp.base.insertDspGodPrcLdadng", dspGodPrcLdadng);
	}
	
	/**
	 * 전시 상품 가격 적재 수정.
	 *
	 * @param dspGodPrcLdadng the DspGodPrcLdadng
	 * @throws SQLException the SQL exception
	 */
	public int updateDspGodPrcLdadng(DspGodPrcLdadng dspGodPrcLdadng) {
		return getSession1().update("com.plgrim.ncp.base.updateDspGodPrcLdadng", dspGodPrcLdadng);
	}
	
	/**
	 * 전시 상품 가격 적재 삭제.
	 *
	 * @param dspGodPrcLdadng the DspGodPrcLdadng
	 * @return the DspGodPrcLdadng
	 * @throws SQLException the SQL exception
	 */
	public int deleteDspGodPrcLdadng(DspGodPrcLdadng dspGodPrcLdadng) {
		return getSession1().delete("com.plgrim.ncp.base.deleteDspGodPrcLdadng", dspGodPrcLdadng);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

