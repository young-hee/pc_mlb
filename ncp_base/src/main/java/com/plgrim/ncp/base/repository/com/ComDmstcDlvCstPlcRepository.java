
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
 * Generator(Generator)		2018-05-29                      
 */
package com.plgrim.ncp.base.repository.com;

import java.sql.SQLException;
import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.datasource1.com.ComDmstcDlvCstPlc;

/**
 * The Class ComDmstcDlvCstPlcRepository.
 */
@Repository
public class ComDmstcDlvCstPlcRepository extends AbstractRepository {
	

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
	 * 업체 국내 배송비 정책 상세 조회.
	 *
	 * @param comDmstcDlvCstPlc the ComDmstcDlvCstPlc
	 * @return the ComDmstcDlvCstPlc
	 * @throws SQLException the SQL exception
	 */
	public ComDmstcDlvCstPlc selectComDmstcDlvCstPlc(ComDmstcDlvCstPlc comDmstcDlvCstPlc) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectComDmstcDlvCstPlc", comDmstcDlvCstPlc);
	}
	
	/**
	 * 업체 국내 배송비 정책 등록.
	 *
	 * @param comDmstcDlvCstPlc the ComDmstcDlvCstPlc
	 * @throws SQLException the SQL exception
	 */
	public void insertComDmstcDlvCstPlc(ComDmstcDlvCstPlc comDmstcDlvCstPlc) {
		getSession1().insert("com.plgrim.ncp.base.insertComDmstcDlvCstPlc", comDmstcDlvCstPlc);
	}
	
	/**
	 * 업체 국내 배송비 정책 수정.
	 *
	 * @param comDmstcDlvCstPlc the ComDmstcDlvCstPlc
	 * @throws SQLException the SQL exception
	 */
	public int updateComDmstcDlvCstPlc(ComDmstcDlvCstPlc comDmstcDlvCstPlc) {
		return getSession1().update("com.plgrim.ncp.base.updateComDmstcDlvCstPlc", comDmstcDlvCstPlc);
	}
	
	/**
	 * 업체 국내 배송비 정책 삭제.
	 *
	 * @param comDmstcDlvCstPlc the ComDmstcDlvCstPlc
	 * @return the ComDmstcDlvCstPlc
	 * @throws SQLException the SQL exception
	 */
	public int deleteComDmstcDlvCstPlc(ComDmstcDlvCstPlc comDmstcDlvCstPlc) {
		return getSession1().delete("com.plgrim.ncp.base.deleteComDmstcDlvCstPlc", comDmstcDlvCstPlc);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

