
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
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrPchShop;

/**
 * The Class MbrPchShopRepository.
 */
@Repository
public class MbrPchShopRepository extends AbstractRepository {
	

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
	 * 회원 구매 매장 상세 조회.
	 *
	 * @param mbrPchShop the MbrPchShop
	 * @return the MbrPchShop
	 * @throws SQLException the SQL exception
	 */
	public MbrPchShop selectMbrPchShop(MbrPchShop mbrPchShop) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectMbrPchShop", mbrPchShop);
	}
	
	/**
	 * 회원 구매 매장 등록.
	 *
	 * @param mbrPchShop the MbrPchShop
	 * @throws SQLException the SQL exception
	 */
	public void insertMbrPchShop(MbrPchShop mbrPchShop) {
		getSession1().insert("com.plgrim.ncp.base.insertMbrPchShop", mbrPchShop);
	}
	
	/**
	 * 회원 구매 매장 수정.
	 *
	 * @param mbrPchShop the MbrPchShop
	 * @throws SQLException the SQL exception
	 */
	public int updateMbrPchShop(MbrPchShop mbrPchShop) {
		return getSession1().update("com.plgrim.ncp.base.updateMbrPchShop", mbrPchShop);
	}
	
	/**
	 * 회원 구매 매장 삭제.
	 *
	 * @param mbrPchShop the MbrPchShop
	 * @return the MbrPchShop
	 * @throws SQLException the SQL exception
	 */
	public int deleteMbrPchShop(MbrPchShop mbrPchShop) {
		return getSession1().delete("com.plgrim.ncp.base.deleteMbrPchShop", mbrPchShop);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

