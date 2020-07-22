
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
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrBnefAplTgt;

/**
 * The Class MbrBnefAplTgtRepository.
 */
@Repository
public class MbrBnefAplTgtRepository extends AbstractRepository {
	

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
	 * 회원 혜택 적용 대상 상세 조회.
	 *
	 * @param mbrBnefAplTgt the MbrBnefAplTgt
	 * @return the MbrBnefAplTgt
	 * @throws SQLException the SQL exception
	 */
	public MbrBnefAplTgt selectMbrBnefAplTgt(MbrBnefAplTgt mbrBnefAplTgt) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectMbrBnefAplTgt", mbrBnefAplTgt);
	}
	
	/**
	 * 회원 혜택 적용 대상 등록.
	 *
	 * @param mbrBnefAplTgt the MbrBnefAplTgt
	 * @throws SQLException the SQL exception
	 */
	public void insertMbrBnefAplTgt(MbrBnefAplTgt mbrBnefAplTgt) {
		getSession1().insert("com.plgrim.ncp.base.insertMbrBnefAplTgt", mbrBnefAplTgt);
	}
	
	/**
	 * 회원 혜택 적용 대상 수정.
	 *
	 * @param mbrBnefAplTgt the MbrBnefAplTgt
	 * @throws SQLException the SQL exception
	 */
	public int updateMbrBnefAplTgt(MbrBnefAplTgt mbrBnefAplTgt) {
		return getSession1().update("com.plgrim.ncp.base.updateMbrBnefAplTgt", mbrBnefAplTgt);
	}
	
	/**
	 * 회원 혜택 적용 대상 삭제.
	 *
	 * @param mbrBnefAplTgt the MbrBnefAplTgt
	 * @return the MbrBnefAplTgt
	 * @throws SQLException the SQL exception
	 */
	public int deleteMbrBnefAplTgt(MbrBnefAplTgt mbrBnefAplTgt) {
		return getSession1().delete("com.plgrim.ncp.base.deleteMbrBnefAplTgt", mbrBnefAplTgt);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

