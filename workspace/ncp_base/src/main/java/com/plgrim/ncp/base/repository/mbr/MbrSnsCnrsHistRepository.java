
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
package com.plgrim.ncp.base.repository.mbr;

import java.sql.SQLException;
import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrSnsCnrsHist;

/**
 * The Class MbrSnsCnrsHistRepository.
 */
@Repository
public class MbrSnsCnrsHistRepository extends AbstractRepository {
	

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
	 * 회원 SNS 공유 이력 상세 조회.
	 *
	 * @param mbrSnsCnrsHist the MbrSnsCnrsHist
	 * @return the MbrSnsCnrsHist
	 * @throws SQLException the SQL exception
	 */
	public MbrSnsCnrsHist selectMbrSnsCnrsHist(MbrSnsCnrsHist mbrSnsCnrsHist) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectMbrSnsCnrsHist", mbrSnsCnrsHist);
	}
	
	/**
	 * 회원 SNS 공유 이력 등록.
	 *
	 * @param mbrSnsCnrsHist the MbrSnsCnrsHist
	 * @throws SQLException the SQL exception
	 */
	public void insertMbrSnsCnrsHist(MbrSnsCnrsHist mbrSnsCnrsHist) {
		getSession1().insert("com.plgrim.ncp.base.insertMbrSnsCnrsHist", mbrSnsCnrsHist);
	}
	
	/**
	 * 회원 SNS 공유 이력 수정.
	 *
	 * @param mbrSnsCnrsHist the MbrSnsCnrsHist
	 * @throws SQLException the SQL exception
	 */
	public int updateMbrSnsCnrsHist(MbrSnsCnrsHist mbrSnsCnrsHist) {
		return getSession1().update("com.plgrim.ncp.base.updateMbrSnsCnrsHist", mbrSnsCnrsHist);
	}
	
	/**
	 * 회원 SNS 공유 이력 삭제.
	 *
	 * @param mbrSnsCnrsHist the MbrSnsCnrsHist
	 * @return the MbrSnsCnrsHist
	 * @throws SQLException the SQL exception
	 */
	public int deleteMbrSnsCnrsHist(MbrSnsCnrsHist mbrSnsCnrsHist) {
		return getSession1().delete("com.plgrim.ncp.base.deleteMbrSnsCnrsHist", mbrSnsCnrsHist);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

