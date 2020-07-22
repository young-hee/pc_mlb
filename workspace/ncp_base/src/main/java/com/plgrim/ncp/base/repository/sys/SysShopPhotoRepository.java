
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
import com.plgrim.ncp.base.entities.datasource1.sys.SysShopPhoto;

/**
 * The Class SysShopPhotoRepository.
 */
@Repository
public class SysShopPhotoRepository extends AbstractRepository {
	

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
	 * 시스템 매장 사진 상세 조회.
	 *
	 * @param sysShopPhoto the SysShopPhoto
	 * @return the SysShopPhoto
	 * @throws SQLException the SQL exception
	 */
	public SysShopPhoto selectSysShopPhoto(SysShopPhoto sysShopPhoto) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectSysShopPhoto", sysShopPhoto);
	}
	
	/**
	 * 시스템 매장 사진 등록.
	 *
	 * @param sysShopPhoto the SysShopPhoto
	 * @throws SQLException the SQL exception
	 */
	public void insertSysShopPhoto(SysShopPhoto sysShopPhoto) {
		getSession1().insert("com.plgrim.ncp.base.insertSysShopPhoto", sysShopPhoto);
	}
	
	/**
	 * 시스템 매장 사진 수정.
	 *
	 * @param sysShopPhoto the SysShopPhoto
	 * @throws SQLException the SQL exception
	 */
	public int updateSysShopPhoto(SysShopPhoto sysShopPhoto) {
		return getSession1().update("com.plgrim.ncp.base.updateSysShopPhoto", sysShopPhoto);
	}
	
	/**
	 * 시스템 매장 사진 삭제.
	 *
	 * @param sysShopPhoto the SysShopPhoto
	 * @return the SysShopPhoto
	 * @throws SQLException the SQL exception
	 */
	public int deleteSysShopPhoto(SysShopPhoto sysShopPhoto) {
		return getSession1().delete("com.plgrim.ncp.base.deleteSysShopPhoto", sysShopPhoto);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

