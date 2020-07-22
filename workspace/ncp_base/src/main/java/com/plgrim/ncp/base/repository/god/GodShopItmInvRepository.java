
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
import com.plgrim.ncp.base.entities.datasource1.god.GodShopItmInv;

/**
 * The Class GodShopItmInvRepository.
 */
@Repository
public class GodShopItmInvRepository extends AbstractRepository {
	

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
	 * 상품 매장 단품 재고 상세 조회.
	 *
	 * @param godShopItmInv the GodShopItmInv
	 * @return the GodShopItmInv
	 * @throws SQLException the SQL exception
	 */
	public GodShopItmInv selectGodShopItmInv(GodShopItmInv godShopItmInv) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectGodShopItmInv", godShopItmInv);
	}
	
	/**
	 * 상품 매장 단품 재고 등록.
	 *
	 * @param godShopItmInv the GodShopItmInv
	 * @throws SQLException the SQL exception
	 */
	public void insertGodShopItmInv(GodShopItmInv godShopItmInv) {
		getSession1().insert("com.plgrim.ncp.base.insertGodShopItmInv", godShopItmInv);
	}
	
	/**
	 * 상품 매장 단품 재고 수정.
	 *
	 * @param godShopItmInv the GodShopItmInv
	 * @throws SQLException the SQL exception
	 */
	public int updateGodShopItmInv(GodShopItmInv godShopItmInv) {
		return getSession1().update("com.plgrim.ncp.base.updateGodShopItmInv", godShopItmInv);
	}
	
	/**
	 * 상품 매장 단품 재고 삭제.
	 *
	 * @param godShopItmInv the GodShopItmInv
	 * @return the GodShopItmInv
	 * @throws SQLException the SQL exception
	 */
	public int deleteGodShopItmInv(GodShopItmInv godShopItmInv) {
		return getSession1().delete("com.plgrim.ncp.base.deleteGodShopItmInv", godShopItmInv);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

