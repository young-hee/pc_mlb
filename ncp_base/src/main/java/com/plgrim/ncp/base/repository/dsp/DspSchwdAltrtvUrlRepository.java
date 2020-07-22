
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
import com.plgrim.ncp.base.entities.datasource1.dsp.DspSchwdAltrtvUrl;

/**
 * The Class DspSchwdAltrtvUrlRepository.
 */
@Repository
public class DspSchwdAltrtvUrlRepository extends AbstractRepository {
	

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
	 * 전시 검색어 대체 URL 상세 조회.
	 *
	 * @param dspSchwdAltrtvUrl the DspSchwdAltrtvUrl
	 * @return the DspSchwdAltrtvUrl
	 * @throws SQLException the SQL exception
	 */
	public DspSchwdAltrtvUrl selectDspSchwdAltrtvUrl(DspSchwdAltrtvUrl dspSchwdAltrtvUrl) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectDspSchwdAltrtvUrl", dspSchwdAltrtvUrl);
	}
	
	/**
	 * 전시 검색어 대체 URL 등록.
	 *
	 * @param dspSchwdAltrtvUrl the DspSchwdAltrtvUrl
	 * @throws SQLException the SQL exception
	 */
	public void insertDspSchwdAltrtvUrl(DspSchwdAltrtvUrl dspSchwdAltrtvUrl) {
		getSession1().insert("com.plgrim.ncp.base.insertDspSchwdAltrtvUrl", dspSchwdAltrtvUrl);
	}
	
	/**
	 * 전시 검색어 대체 URL 수정.
	 *
	 * @param dspSchwdAltrtvUrl the DspSchwdAltrtvUrl
	 * @throws SQLException the SQL exception
	 */
	public int updateDspSchwdAltrtvUrl(DspSchwdAltrtvUrl dspSchwdAltrtvUrl) {
		return getSession1().update("com.plgrim.ncp.base.updateDspSchwdAltrtvUrl", dspSchwdAltrtvUrl);
	}
	
	/**
	 * 전시 검색어 대체 URL 삭제.
	 *
	 * @param dspSchwdAltrtvUrl the DspSchwdAltrtvUrl
	 * @return the DspSchwdAltrtvUrl
	 * @throws SQLException the SQL exception
	 */
	public int deleteDspSchwdAltrtvUrl(DspSchwdAltrtvUrl dspSchwdAltrtvUrl) {
		return getSession1().delete("com.plgrim.ncp.base.deleteDspSchwdAltrtvUrl", dspSchwdAltrtvUrl);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

