
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
import com.plgrim.ncp.base.entities.datasource1.dsp.DspGodSaleIdex;

/**
 * The Class DspGodSaleIdexRepository.
 */
@Repository
public class DspGodSaleIdexRepository extends AbstractRepository {
	

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
	 * 전시 상품 판매 지수 상세 조회.
	 *
	 * @param dspGodSaleIdex the DspGodSaleIdex
	 * @return the DspGodSaleIdex
	 * @throws SQLException the SQL exception
	 */
	public DspGodSaleIdex selectDspGodSaleIdex(DspGodSaleIdex dspGodSaleIdex) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectDspGodSaleIdex", dspGodSaleIdex);
	}
	
	/**
	 * 전시 상품 판매 지수 등록.
	 *
	 * @param dspGodSaleIdex the DspGodSaleIdex
	 * @throws SQLException the SQL exception
	 */
	public void insertDspGodSaleIdex(DspGodSaleIdex dspGodSaleIdex) {
		getSession1().insert("com.plgrim.ncp.base.insertDspGodSaleIdex", dspGodSaleIdex);
	}
	
	/**
	 * 전시 상품 판매 지수 수정.
	 *
	 * @param dspGodSaleIdex the DspGodSaleIdex
	 * @throws SQLException the SQL exception
	 */
	public int updateDspGodSaleIdex(DspGodSaleIdex dspGodSaleIdex) {
		return getSession1().update("com.plgrim.ncp.base.updateDspGodSaleIdex", dspGodSaleIdex);
	}
	
	/**
	 * 전시 상품 판매 지수 삭제.
	 *
	 * @param dspGodSaleIdex the DspGodSaleIdex
	 * @return the DspGodSaleIdex
	 * @throws SQLException the SQL exception
	 */
	public int deleteDspGodSaleIdex(DspGodSaleIdex dspGodSaleIdex) {
		return getSession1().delete("com.plgrim.ncp.base.deleteDspGodSaleIdex", dspGodSaleIdex);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

