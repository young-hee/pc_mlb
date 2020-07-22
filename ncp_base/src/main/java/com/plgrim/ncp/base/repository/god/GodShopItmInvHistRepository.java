
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
package com.plgrim.ncp.base.repository.god;

import java.sql.SQLException;
import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.datasource1.god.GodShopItmInvHist;

/**
 * The Class GodShopItmInvHistRepository.
 */
@Repository
public class GodShopItmInvHistRepository extends AbstractRepository {
	

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
	 * 상품 매장 단품 재고 이력 상세 조회.
	 *
	 * @param godShopItmInvHist the GodShopItmInvHist
	 * @return the GodShopItmInvHist
	 * @throws SQLException the SQL exception
	 */
	public GodShopItmInvHist selectGodShopItmInvHist(GodShopItmInvHist godShopItmInvHist) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectGodShopItmInvHist", godShopItmInvHist);
	}
	
	/**
	 * 상품 매장 단품 재고 이력 등록.
	 *
	 * @param godShopItmInvHist the GodShopItmInvHist
	 * @throws SQLException the SQL exception
	 */
	public void insertGodShopItmInvHist(GodShopItmInvHist godShopItmInvHist) {
		getSession1().insert("com.plgrim.ncp.base.insertGodShopItmInvHist", godShopItmInvHist);
	}
	
	/**
	 * 상품 매장 단품 재고 이력 수정.
	 *
	 * @param godShopItmInvHist the GodShopItmInvHist
	 * @throws SQLException the SQL exception
	 */
	public int updateGodShopItmInvHist(GodShopItmInvHist godShopItmInvHist) {
		return getSession1().update("com.plgrim.ncp.base.updateGodShopItmInvHist", godShopItmInvHist);
	}
	
	/**
	 * 상품 매장 단품 재고 이력 삭제.
	 *
	 * @param godShopItmInvHist the GodShopItmInvHist
	 * @return the GodShopItmInvHist
	 * @throws SQLException the SQL exception
	 */
	public int deleteGodShopItmInvHist(GodShopItmInvHist godShopItmInvHist) {
		return getSession1().delete("com.plgrim.ncp.base.deleteGodShopItmInvHist", godShopItmInvHist);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

