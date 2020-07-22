
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
import com.plgrim.ncp.base.entities.datasource1.com.ComDmstcDlvCstPlcHist;

/**
 * The Class ComDmstcDlvCstPlcHistRepository.
 */
@Repository
public class ComDmstcDlvCstPlcHistRepository extends AbstractRepository {
	

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
	 * 업체 국내 배송비 정책 이력 상세 조회.
	 *
	 * @param comDmstcDlvCstPlcHist the ComDmstcDlvCstPlcHist
	 * @return the ComDmstcDlvCstPlcHist
	 * @throws SQLException the SQL exception
	 */
	public ComDmstcDlvCstPlcHist selectComDmstcDlvCstPlcHist(ComDmstcDlvCstPlcHist comDmstcDlvCstPlcHist) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectComDmstcDlvCstPlcHist", comDmstcDlvCstPlcHist);
	}
	
	/**
	 * 업체 국내 배송비 정책 이력 등록.
	 *
	 * @param comDmstcDlvCstPlcHist the ComDmstcDlvCstPlcHist
	 * @throws SQLException the SQL exception
	 */
	public void insertComDmstcDlvCstPlcHist(ComDmstcDlvCstPlcHist comDmstcDlvCstPlcHist) {
		getSession1().insert("com.plgrim.ncp.base.insertComDmstcDlvCstPlcHist", comDmstcDlvCstPlcHist);
	}
	
	/**
	 * 업체 국내 배송비 정책 이력 수정.
	 *
	 * @param comDmstcDlvCstPlcHist the ComDmstcDlvCstPlcHist
	 * @throws SQLException the SQL exception
	 */
	public int updateComDmstcDlvCstPlcHist(ComDmstcDlvCstPlcHist comDmstcDlvCstPlcHist) {
		return getSession1().update("com.plgrim.ncp.base.updateComDmstcDlvCstPlcHist", comDmstcDlvCstPlcHist);
	}
	
	/**
	 * 업체 국내 배송비 정책 이력 삭제.
	 *
	 * @param comDmstcDlvCstPlcHist the ComDmstcDlvCstPlcHist
	 * @return the ComDmstcDlvCstPlcHist
	 * @throws SQLException the SQL exception
	 */
	public int deleteComDmstcDlvCstPlcHist(ComDmstcDlvCstPlcHist comDmstcDlvCstPlcHist) {
		return getSession1().delete("com.plgrim.ncp.base.deleteComDmstcDlvCstPlcHist", comDmstcDlvCstPlcHist);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

