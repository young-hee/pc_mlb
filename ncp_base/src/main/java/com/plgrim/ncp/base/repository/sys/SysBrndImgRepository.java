
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
package com.plgrim.ncp.base.repository.sys;

import java.sql.SQLException;
import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.datasource1.sys.SysBrndImg;

/**
 * The Class SysBrndImgRepository.
 */
@Repository
public class SysBrndImgRepository extends AbstractRepository {
	

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
	 * 시스템 브랜드 이미지 상세 조회.
	 *
	 * @param sysBrndImg the SysBrndImg
	 * @return the SysBrndImg
	 * @throws SQLException the SQL exception
	 */
	public SysBrndImg selectSysBrndImg(SysBrndImg sysBrndImg) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectSysBrndImg", sysBrndImg);
	}
	
	/**
	 * 시스템 브랜드 이미지 등록.
	 *
	 * @param sysBrndImg the SysBrndImg
	 * @throws SQLException the SQL exception
	 */
	public void insertSysBrndImg(SysBrndImg sysBrndImg) {
		getSession1().insert("com.plgrim.ncp.base.insertSysBrndImg", sysBrndImg);
	}
	
	/**
	 * 시스템 브랜드 이미지 수정.
	 *
	 * @param sysBrndImg the SysBrndImg
	 * @throws SQLException the SQL exception
	 */
	public int updateSysBrndImg(SysBrndImg sysBrndImg) {
		return getSession1().update("com.plgrim.ncp.base.updateSysBrndImg", sysBrndImg);
	}
	
	/**
	 * 시스템 브랜드 이미지 삭제.
	 *
	 * @param sysBrndImg the SysBrndImg
	 * @return the SysBrndImg
	 * @throws SQLException the SQL exception
	 */
	public int deleteSysBrndImg(SysBrndImg sysBrndImg) {
		return getSession1().delete("com.plgrim.ncp.base.deleteSysBrndImg", sysBrndImg);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

