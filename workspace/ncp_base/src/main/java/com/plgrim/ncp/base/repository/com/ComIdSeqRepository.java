
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
package com.plgrim.ncp.base.repository.com;

import java.sql.SQLException;
import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.datasource1.com.ComIdSeq;

/**
 * The Class ComIdSeqRepository.
 */
@Repository
public class ComIdSeqRepository extends AbstractRepository {
	

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
	 * 업체 ID 순서 상세 조회.
	 *
	 * @param comIdSeq the ComIdSeq
	 * @return the ComIdSeq
	 * @throws SQLException the SQL exception
	 */
	public ComIdSeq selectComIdSeq(ComIdSeq comIdSeq) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectComIdSeq", comIdSeq);
	}
	
	/**
	 * 업체 ID 순서 등록.
	 *
	 * @param comIdSeq the ComIdSeq
	 * @throws SQLException the SQL exception
	 */
	public void insertComIdSeq(ComIdSeq comIdSeq) {
		getSession1().insert("com.plgrim.ncp.base.insertComIdSeq", comIdSeq);
	}
	
	/**
	 * 업체 ID 순서 수정.
	 *
	 * @param comIdSeq the ComIdSeq
	 * @throws SQLException the SQL exception
	 */
	public int updateComIdSeq(ComIdSeq comIdSeq) {
		return getSession1().update("com.plgrim.ncp.base.updateComIdSeq", comIdSeq);
	}
	
	/**
	 * 업체 ID 순서 삭제.
	 *
	 * @param comIdSeq the ComIdSeq
	 * @return the ComIdSeq
	 * @throws SQLException the SQL exception
	 */
	public int deleteComIdSeq(ComIdSeq comIdSeq) {
		return getSession1().delete("com.plgrim.ncp.base.deleteComIdSeq", comIdSeq);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

