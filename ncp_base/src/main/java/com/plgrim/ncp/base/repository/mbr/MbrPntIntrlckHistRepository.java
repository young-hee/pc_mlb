
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
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrPntIntrlckHist;

/**
 * The Class MbrPntIntrlckHistRepository.
 */
@Repository
public class MbrPntIntrlckHistRepository extends AbstractRepository {
	

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
	 * 회원 포인트 연동 이력 상세 조회.
	 *
	 * @param mbrPntIntrlckHist the MbrPntIntrlckHist
	 * @return the MbrPntIntrlckHist
	 * @throws SQLException the SQL exception
	 */
	public MbrPntIntrlckHist selectMbrPntIntrlckHist(MbrPntIntrlckHist mbrPntIntrlckHist) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectMbrPntIntrlckHist", mbrPntIntrlckHist);
	}
	
	/**
	 * 회원 포인트 연동 이력 등록.
	 *
	 * @param mbrPntIntrlckHist the MbrPntIntrlckHist
	 * @throws SQLException the SQL exception
	 */
	public void insertMbrPntIntrlckHist(MbrPntIntrlckHist mbrPntIntrlckHist) {
		getSession1().insert("com.plgrim.ncp.base.insertMbrPntIntrlckHist", mbrPntIntrlckHist);
	}
	
	/**
	 * 회원 포인트 연동 이력 수정.
	 *
	 * @param mbrPntIntrlckHist the MbrPntIntrlckHist
	 * @throws SQLException the SQL exception
	 */
	public int updateMbrPntIntrlckHist(MbrPntIntrlckHist mbrPntIntrlckHist) {
		return getSession1().update("com.plgrim.ncp.base.updateMbrPntIntrlckHist", mbrPntIntrlckHist);
	}
	
	/**
	 * 회원 포인트 연동 이력 삭제.
	 *
	 * @param mbrPntIntrlckHist the MbrPntIntrlckHist
	 * @return the MbrPntIntrlckHist
	 * @throws SQLException the SQL exception
	 */
	public int deleteMbrPntIntrlckHist(MbrPntIntrlckHist mbrPntIntrlckHist) {
		return getSession1().delete("com.plgrim.ncp.base.deleteMbrPntIntrlckHist", mbrPntIntrlckHist);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

