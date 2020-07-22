
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
import com.plgrim.ncp.base.entities.datasource1.dsp.DspBstGodEvlTmpr;

/**
 * The Class DspBstGodEvlTmprRepository.
 */
@Repository
public class DspBstGodEvlTmprRepository extends AbstractRepository {
	

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
	 * 전시 베스트 상품평_임시 상세 조회.
	 *
	 * @param dspBstGodEvlTmpr the DspBstGodEvlTmpr
	 * @return the DspBstGodEvlTmpr
	 * @throws SQLException the SQL exception
	 */
	public DspBstGodEvlTmpr selectDspBstGodEvlTmpr(DspBstGodEvlTmpr dspBstGodEvlTmpr) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectDspBstGodEvlTmpr", dspBstGodEvlTmpr);
	}
	
	/**
	 * 전시 베스트 상품평_임시 등록.
	 *
	 * @param dspBstGodEvlTmpr the DspBstGodEvlTmpr
	 * @throws SQLException the SQL exception
	 */
	public void insertDspBstGodEvlTmpr(DspBstGodEvlTmpr dspBstGodEvlTmpr) {
		getSession1().insert("com.plgrim.ncp.base.insertDspBstGodEvlTmpr", dspBstGodEvlTmpr);
	}
	
	/**
	 * 전시 베스트 상품평_임시 수정.
	 *
	 * @param dspBstGodEvlTmpr the DspBstGodEvlTmpr
	 * @throws SQLException the SQL exception
	 */
	public int updateDspBstGodEvlTmpr(DspBstGodEvlTmpr dspBstGodEvlTmpr) {
		return getSession1().update("com.plgrim.ncp.base.updateDspBstGodEvlTmpr", dspBstGodEvlTmpr);
	}
	
	/**
	 * 전시 베스트 상품평_임시 삭제.
	 *
	 * @param dspBstGodEvlTmpr the DspBstGodEvlTmpr
	 * @return the DspBstGodEvlTmpr
	 * @throws SQLException the SQL exception
	 */
	public int deleteDspBstGodEvlTmpr(DspBstGodEvlTmpr dspBstGodEvlTmpr) {
		return getSession1().delete("com.plgrim.ncp.base.deleteDspBstGodEvlTmpr", dspBstGodEvlTmpr);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

