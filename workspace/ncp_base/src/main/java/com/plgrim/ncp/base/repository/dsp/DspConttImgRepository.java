
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
import com.plgrim.ncp.base.entities.datasource1.dsp.DspConttImg;

/**
 * The Class DspConttImgRepository.
 */
@Repository
public class DspConttImgRepository extends AbstractRepository {
	

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
	 * 전시 컨텐츠 이미지 상세 조회.
	 *
	 * @param dspConttImg the DspConttImg
	 * @return the DspConttImg
	 * @throws SQLException the SQL exception
	 */
	public DspConttImg selectDspConttImg(DspConttImg dspConttImg) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectDspConttImg", dspConttImg);
	}
	
	/**
	 * 전시 컨텐츠 이미지 등록.
	 *
	 * @param dspConttImg the DspConttImg
	 * @throws SQLException the SQL exception
	 */
	public void insertDspConttImg(DspConttImg dspConttImg) {
		getSession1().insert("com.plgrim.ncp.base.insertDspConttImg", dspConttImg);
	}
	
	/**
	 * 전시 컨텐츠 이미지 수정.
	 *
	 * @param dspConttImg the DspConttImg
	 * @throws SQLException the SQL exception
	 */
	public int updateDspConttImg(DspConttImg dspConttImg) {
		return getSession1().update("com.plgrim.ncp.base.updateDspConttImg", dspConttImg);
	}
	
	/**
	 * 전시 컨텐츠 이미지 삭제.
	 *
	 * @param dspConttImg the DspConttImg
	 * @return the DspConttImg
	 * @throws SQLException the SQL exception
	 */
	public int deleteDspConttImg(DspConttImg dspConttImg) {
		return getSession1().delete("com.plgrim.ncp.base.deleteDspConttImg", dspConttImg);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

