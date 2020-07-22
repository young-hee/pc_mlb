
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
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrLike;

/**
 * The Class MbrLikeRepository.
 */
@Repository
public class MbrLikeRepository extends AbstractRepository {
	

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
	 * 회원 좋아요 상세 조회.
	 *
	 * @param mbrLike the MbrLike
	 * @return the MbrLike
	 * @throws SQLException the SQL exception
	 */
	public MbrLike selectMbrLike(MbrLike mbrLike) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectMbrLike", mbrLike);
	}
	
	/**
	 * 회원 좋아요 등록.
	 *
	 * @param mbrLike the MbrLike
	 * @throws SQLException the SQL exception
	 */
	public void insertMbrLike(MbrLike mbrLike) {
		getSession1().insert("com.plgrim.ncp.base.insertMbrLike", mbrLike);
	}
	
	/**
	 * 회원 좋아요 수정.
	 *
	 * @param mbrLike the MbrLike
	 * @throws SQLException the SQL exception
	 */
	public int updateMbrLike(MbrLike mbrLike) {
		return getSession1().update("com.plgrim.ncp.base.updateMbrLike", mbrLike);
	}
	
	/**
	 * 회원 좋아요 삭제.
	 *
	 * @param mbrLike the MbrLike
	 * @return the MbrLike
	 * @throws SQLException the SQL exception
	 */
	public int deleteMbrLike(MbrLike mbrLike) {
		return getSession1().delete("com.plgrim.ncp.base.deleteMbrLike", mbrLike);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

