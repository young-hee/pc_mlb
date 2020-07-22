
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
import com.plgrim.ncp.base.entities.datasource1.dsp.DspCnrTmplatInfoCnnc;

/**
 * The Class DspCnrTmplatInfoCnncRepository.
 */
@Repository
public class DspCnrTmplatInfoCnncRepository extends AbstractRepository {
	

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
	 * 전시 코너 템플릿 정보 연결 상세 조회.
	 *
	 * @param dspCnrTmplatInfoCnnc the DspCnrTmplatInfoCnnc
	 * @return the DspCnrTmplatInfoCnnc
	 * @throws SQLException the SQL exception
	 */
	public DspCnrTmplatInfoCnnc selectDspCnrTmplatInfoCnnc(DspCnrTmplatInfoCnnc dspCnrTmplatInfoCnnc) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectDspCnrTmplatInfoCnnc", dspCnrTmplatInfoCnnc);
	}
	
	/**
	 * 전시 코너 템플릿 정보 연결 등록.
	 *
	 * @param dspCnrTmplatInfoCnnc the DspCnrTmplatInfoCnnc
	 * @throws SQLException the SQL exception
	 */
	public void insertDspCnrTmplatInfoCnnc(DspCnrTmplatInfoCnnc dspCnrTmplatInfoCnnc) {
		getSession1().insert("com.plgrim.ncp.base.insertDspCnrTmplatInfoCnnc", dspCnrTmplatInfoCnnc);
	}
	
	/**
	 * 전시 코너 템플릿 정보 연결 수정.
	 *
	 * @param dspCnrTmplatInfoCnnc the DspCnrTmplatInfoCnnc
	 * @throws SQLException the SQL exception
	 */
	public int updateDspCnrTmplatInfoCnnc(DspCnrTmplatInfoCnnc dspCnrTmplatInfoCnnc) {
		return getSession1().update("com.plgrim.ncp.base.updateDspCnrTmplatInfoCnnc", dspCnrTmplatInfoCnnc);
	}
	
	/**
	 * 전시 코너 템플릿 정보 연결 삭제.
	 *
	 * @param dspCnrTmplatInfoCnnc the DspCnrTmplatInfoCnnc
	 * @return the DspCnrTmplatInfoCnnc
	 * @throws SQLException the SQL exception
	 */
	public int deleteDspCnrTmplatInfoCnnc(DspCnrTmplatInfoCnnc dspCnrTmplatInfoCnnc) {
		return getSession1().delete("com.plgrim.ncp.base.deleteDspCnrTmplatInfoCnnc", dspCnrTmplatInfoCnnc);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

