
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
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrPsnlInfoUsef;

/**
 * The Class MbrPsnlInfoUsefRepository.
 */
@Repository
public class MbrPsnlInfoUsefRepository extends AbstractRepository {
	

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
	 * 회원 개인정보 이용 상세 조회.
	 *
	 * @param mbrPsnlInfoUsef the MbrPsnlInfoUsef
	 * @return the MbrPsnlInfoUsef
	 * @throws SQLException the SQL exception
	 */
	public MbrPsnlInfoUsef selectMbrPsnlInfoUsef(MbrPsnlInfoUsef mbrPsnlInfoUsef) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectMbrPsnlInfoUsef", mbrPsnlInfoUsef);
	}
	
	/**
	 * 회원 개인정보 이용 등록.
	 *
	 * @param mbrPsnlInfoUsef the MbrPsnlInfoUsef
	 * @throws SQLException the SQL exception
	 */
	public void insertMbrPsnlInfoUsef(MbrPsnlInfoUsef mbrPsnlInfoUsef) {
		getSession1().insert("com.plgrim.ncp.base.insertMbrPsnlInfoUsef", mbrPsnlInfoUsef);
	}
	
	/**
	 * 회원 개인정보 이용 수정.
	 *
	 * @param mbrPsnlInfoUsef the MbrPsnlInfoUsef
	 * @throws SQLException the SQL exception
	 */
	public int updateMbrPsnlInfoUsef(MbrPsnlInfoUsef mbrPsnlInfoUsef) {
		return getSession1().update("com.plgrim.ncp.base.updateMbrPsnlInfoUsef", mbrPsnlInfoUsef);
	}
	
	/**
	 * 회원 개인정보 이용 삭제.
	 *
	 * @param mbrPsnlInfoUsef the MbrPsnlInfoUsef
	 * @return the MbrPsnlInfoUsef
	 * @throws SQLException the SQL exception
	 */
	public int deleteMbrPsnlInfoUsef(MbrPsnlInfoUsef mbrPsnlInfoUsef) {
		return getSession1().delete("com.plgrim.ncp.base.deleteMbrPsnlInfoUsef", mbrPsnlInfoUsef);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

