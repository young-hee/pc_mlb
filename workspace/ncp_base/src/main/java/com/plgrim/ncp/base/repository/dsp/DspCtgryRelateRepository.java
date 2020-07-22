
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
import com.plgrim.ncp.base.entities.datasource1.dsp.DspCtgryRelate;

/**
 * The Class DspCtgryRelateRepository.
 */
@Repository
public class DspCtgryRelateRepository extends AbstractRepository {
	

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
	 * 전시 카테고리 연관 상세 조회.
	 *
	 * @param dspCtgryRelate the DspCtgryRelate
	 * @return the DspCtgryRelate
	 * @throws SQLException the SQL exception
	 */
	public DspCtgryRelate selectDspCtgryRelate(DspCtgryRelate dspCtgryRelate) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectDspCtgryRelate", dspCtgryRelate);
	}
	
	/**
	 * 전시 카테고리 연관 등록.
	 *
	 * @param dspCtgryRelate the DspCtgryRelate
	 * @throws SQLException the SQL exception
	 */
	public void insertDspCtgryRelate(DspCtgryRelate dspCtgryRelate) {
		getSession1().insert("com.plgrim.ncp.base.insertDspCtgryRelate", dspCtgryRelate);
	}
	
	/**
	 * 전시 카테고리 연관 수정.
	 *
	 * @param dspCtgryRelate the DspCtgryRelate
	 * @throws SQLException the SQL exception
	 */
	public int updateDspCtgryRelate(DspCtgryRelate dspCtgryRelate) {
		return getSession1().update("com.plgrim.ncp.base.updateDspCtgryRelate", dspCtgryRelate);
	}
	
	/**
	 * 전시 카테고리 연관 삭제.
	 *
	 * @param dspCtgryRelate the DspCtgryRelate
	 * @return the DspCtgryRelate
	 * @throws SQLException the SQL exception
	 */
	public int deleteDspCtgryRelate(DspCtgryRelate dspCtgryRelate) {
		return getSession1().delete("com.plgrim.ncp.base.deleteDspCtgryRelate", dspCtgryRelate);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

