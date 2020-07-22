
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
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrRecomendrRecomendeCnnc;

/**
 * The Class MbrRecomendrRecomendeCnncRepository.
 */
@Repository
public class MbrRecomendrRecomendeCnncRepository extends AbstractRepository {
	

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
	 * 회원 추천인 피추천인 연결 상세 조회.
	 *
	 * @param mbrRecomendrRecomendeCnnc the MbrRecomendrRecomendeCnnc
	 * @return the MbrRecomendrRecomendeCnnc
	 * @throws SQLException the SQL exception
	 */
	public MbrRecomendrRecomendeCnnc selectMbrRecomendrRecomendeCnnc(MbrRecomendrRecomendeCnnc mbrRecomendrRecomendeCnnc) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectMbrRecomendrRecomendeCnnc", mbrRecomendrRecomendeCnnc);
	}
	
	/**
	 * 회원 추천인 피추천인 연결 등록.
	 *
	 * @param mbrRecomendrRecomendeCnnc the MbrRecomendrRecomendeCnnc
	 * @throws SQLException the SQL exception
	 */
	public void insertMbrRecomendrRecomendeCnnc(MbrRecomendrRecomendeCnnc mbrRecomendrRecomendeCnnc) {
		getSession1().insert("com.plgrim.ncp.base.insertMbrRecomendrRecomendeCnnc", mbrRecomendrRecomendeCnnc);
	}
	
	/**
	 * 회원 추천인 피추천인 연결 수정.
	 *
	 * @param mbrRecomendrRecomendeCnnc the MbrRecomendrRecomendeCnnc
	 * @throws SQLException the SQL exception
	 */
	public int updateMbrRecomendrRecomendeCnnc(MbrRecomendrRecomendeCnnc mbrRecomendrRecomendeCnnc) {
		return getSession1().update("com.plgrim.ncp.base.updateMbrRecomendrRecomendeCnnc", mbrRecomendrRecomendeCnnc);
	}
	
	/**
	 * 회원 추천인 피추천인 연결 삭제.
	 *
	 * @param mbrRecomendrRecomendeCnnc the MbrRecomendrRecomendeCnnc
	 * @return the MbrRecomendrRecomendeCnnc
	 * @throws SQLException the SQL exception
	 */
	public int deleteMbrRecomendrRecomendeCnnc(MbrRecomendrRecomendeCnnc mbrRecomendrRecomendeCnnc) {
		return getSession1().delete("com.plgrim.ncp.base.deleteMbrRecomendrRecomendeCnnc", mbrRecomendrRecomendeCnnc);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

