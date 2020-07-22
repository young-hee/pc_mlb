
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
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrBnefDetail;

/**
 * The Class MbrBnefDetailRepository.
 */
@Repository
public class MbrBnefDetailRepository extends AbstractRepository {
	

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
	 * 회원 혜택 상세 상세 조회.
	 *
	 * @param mbrBnefDetail the MbrBnefDetail
	 * @return the MbrBnefDetail
	 * @throws SQLException the SQL exception
	 */
	public MbrBnefDetail selectMbrBnefDetail(MbrBnefDetail mbrBnefDetail) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectMbrBnefDetail", mbrBnefDetail);
	}
	
	/**
	 * 회원 혜택 상세 등록.
	 *
	 * @param mbrBnefDetail the MbrBnefDetail
	 * @throws SQLException the SQL exception
	 */
	public void insertMbrBnefDetail(MbrBnefDetail mbrBnefDetail) {
		getSession1().insert("com.plgrim.ncp.base.insertMbrBnefDetail", mbrBnefDetail);
	}
	
	/**
	 * 회원 혜택 상세 수정.
	 *
	 * @param mbrBnefDetail the MbrBnefDetail
	 * @throws SQLException the SQL exception
	 */
	public int updateMbrBnefDetail(MbrBnefDetail mbrBnefDetail) {
		return getSession1().update("com.plgrim.ncp.base.updateMbrBnefDetail", mbrBnefDetail);
	}
	
	/**
	 * 회원 혜택 상세 삭제.
	 *
	 * @param mbrBnefDetail the MbrBnefDetail
	 * @return the MbrBnefDetail
	 * @throws SQLException the SQL exception
	 */
	public int deleteMbrBnefDetail(MbrBnefDetail mbrBnefDetail) {
		return getSession1().delete("com.plgrim.ncp.base.deleteMbrBnefDetail", mbrBnefDetail);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

