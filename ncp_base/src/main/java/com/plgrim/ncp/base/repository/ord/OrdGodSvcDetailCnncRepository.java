
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
package com.plgrim.ncp.base.repository.ord;

import java.sql.SQLException;
import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.datasource1.ord.OrdGodSvcDetailCnnc;

/**
 * The Class OrdGodSvcDetailCnncRepository.
 */
@Repository
public class OrdGodSvcDetailCnncRepository extends AbstractRepository {
	

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
	 * 주문 상품 서비스 상세 연결 상세 조회.
	 *
	 * @param ordGodSvcDetailCnnc the OrdGodSvcDetailCnnc
	 * @return the OrdGodSvcDetailCnnc
	 * @throws SQLException the SQL exception
	 */
	public OrdGodSvcDetailCnnc selectOrdGodSvcDetailCnnc(OrdGodSvcDetailCnnc ordGodSvcDetailCnnc) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectOrdGodSvcDetailCnnc", ordGodSvcDetailCnnc);
	}
	
	/**
	 * 주문 상품 서비스 상세 연결 등록.
	 *
	 * @param ordGodSvcDetailCnnc the OrdGodSvcDetailCnnc
	 * @throws SQLException the SQL exception
	 */
	public void insertOrdGodSvcDetailCnnc(OrdGodSvcDetailCnnc ordGodSvcDetailCnnc) {
		getSession1().insert("com.plgrim.ncp.base.insertOrdGodSvcDetailCnnc", ordGodSvcDetailCnnc);
	}
	
	/**
	 * 주문 상품 서비스 상세 연결 수정.
	 *
	 * @param ordGodSvcDetailCnnc the OrdGodSvcDetailCnnc
	 * @throws SQLException the SQL exception
	 */
	public int updateOrdGodSvcDetailCnnc(OrdGodSvcDetailCnnc ordGodSvcDetailCnnc) {
		return getSession1().update("com.plgrim.ncp.base.updateOrdGodSvcDetailCnnc", ordGodSvcDetailCnnc);
	}
	
	/**
	 * 주문 상품 서비스 상세 연결 삭제.
	 *
	 * @param ordGodSvcDetailCnnc the OrdGodSvcDetailCnnc
	 * @return the OrdGodSvcDetailCnnc
	 * @throws SQLException the SQL exception
	 */
	public int deleteOrdGodSvcDetailCnnc(OrdGodSvcDetailCnnc ordGodSvcDetailCnnc) {
		return getSession1().delete("com.plgrim.ncp.base.deleteOrdGodSvcDetailCnnc", ordGodSvcDetailCnnc);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

