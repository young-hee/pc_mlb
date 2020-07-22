
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
import com.plgrim.ncp.base.entities.datasource1.dsp.DspPromtImg;

/**
 * The Class DspPromtImgRepository.
 */
@Repository
public class DspPromtImgRepository extends AbstractRepository {
	

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
	 * 전시 기획전 이미지 상세 조회.
	 *
	 * @param dspPromtImg the DspPromtImg
	 * @return the DspPromtImg
	 * @throws SQLException the SQL exception
	 */
	public DspPromtImg selectDspPromtImg(DspPromtImg dspPromtImg) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectDspPromtImg", dspPromtImg);
	}
	
	/**
	 * 전시 기획전 이미지 등록.
	 *
	 * @param dspPromtImg the DspPromtImg
	 * @throws SQLException the SQL exception
	 */
	public void insertDspPromtImg(DspPromtImg dspPromtImg) {
		getSession1().insert("com.plgrim.ncp.base.insertDspPromtImg", dspPromtImg);
	}
	
	/**
	 * 전시 기획전 이미지 수정.
	 *
	 * @param dspPromtImg the DspPromtImg
	 * @throws SQLException the SQL exception
	 */
	public int updateDspPromtImg(DspPromtImg dspPromtImg) {
		return getSession1().update("com.plgrim.ncp.base.updateDspPromtImg", dspPromtImg);
	}
	
	/**
	 * 전시 기획전 이미지 삭제.
	 *
	 * @param dspPromtImg the DspPromtImg
	 * @return the DspPromtImg
	 * @throws SQLException the SQL exception
	 */
	public int deleteDspPromtImg(DspPromtImg dspPromtImg) {
		return getSession1().delete("com.plgrim.ncp.base.deleteDspPromtImg", dspPromtImg);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

