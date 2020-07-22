
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
import com.plgrim.ncp.base.entities.datasource1.com.ComDmstcDlvCstPlcExcp;

/**
 * The Class ComDmstcDlvCstPlcExcpRepository.
 */
@Repository
public class ComDmstcDlvCstPlcExcpRepository extends AbstractRepository {
	

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
	 * 업체 국내 배송비 정책 예외 상세 조회.
	 *
	 * @param comDmstcDlvCstPlcExcp the ComDmstcDlvCstPlcExcp
	 * @return the ComDmstcDlvCstPlcExcp
	 * @throws SQLException the SQL exception
	 */
	public ComDmstcDlvCstPlcExcp selectComDmstcDlvCstPlcExcp(ComDmstcDlvCstPlcExcp comDmstcDlvCstPlcExcp) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectComDmstcDlvCstPlcExcp", comDmstcDlvCstPlcExcp);
	}
	
	/**
	 * 업체 국내 배송비 정책 예외 등록.
	 *
	 * @param comDmstcDlvCstPlcExcp the ComDmstcDlvCstPlcExcp
	 * @throws SQLException the SQL exception
	 */
	public void insertComDmstcDlvCstPlcExcp(ComDmstcDlvCstPlcExcp comDmstcDlvCstPlcExcp) {
		getSession1().insert("com.plgrim.ncp.base.insertComDmstcDlvCstPlcExcp", comDmstcDlvCstPlcExcp);
	}
	
	/**
	 * 업체 국내 배송비 정책 예외 수정.
	 *
	 * @param comDmstcDlvCstPlcExcp the ComDmstcDlvCstPlcExcp
	 * @throws SQLException the SQL exception
	 */
	public int updateComDmstcDlvCstPlcExcp(ComDmstcDlvCstPlcExcp comDmstcDlvCstPlcExcp) {
		return getSession1().update("com.plgrim.ncp.base.updateComDmstcDlvCstPlcExcp", comDmstcDlvCstPlcExcp);
	}
	
	/**
	 * 업체 국내 배송비 정책 예외 삭제.
	 *
	 * @param comDmstcDlvCstPlcExcp the ComDmstcDlvCstPlcExcp
	 * @return the ComDmstcDlvCstPlcExcp
	 * @throws SQLException the SQL exception
	 */
	public int deleteComDmstcDlvCstPlcExcp(ComDmstcDlvCstPlcExcp comDmstcDlvCstPlcExcp) {
		return getSession1().delete("com.plgrim.ncp.base.deleteComDmstcDlvCstPlcExcp", comDmstcDlvCstPlcExcp);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

