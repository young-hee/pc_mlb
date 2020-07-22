/* Copyright (c) 2017 plgrim, Inc.
 * All right reserved.
 * http://plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 *
 * ------------------------------------------------------------------------
 * @author      siga77.lee
 * @since       2017. 10. 27       
 */
package com.plgrim.ncp.biz.vendor.repository;

import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.entities.datasource1.com.ComDmstcDlvCstPlc;
import com.plgrim.ncp.base.entities.datasource1.com.ComOvseaDlvCstPlcLangHis;
import com.plgrim.ncp.base.repository.com.ComRepository;


/**
 * [클래스 설명]
 * 
 * <p>
 * 
 * <ul>
 *   <li> [기능1]
 *   <li> [기능2]
 * </ul>.
 *
 * @author siga77.lee
 * @since 2017. 10. 27
 */
/**
 * @author user
 *
 */
@Repository
public class VendorOvseaDlvCstPlcRepository extends ComRepository  {

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
	 * 글로벌 해외배송비 정책 수정.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param comDmstcDlvCstPlc [설명]
	 * @since 2017. 10. 26
	 */
	public int updateComOvseaDlvCstPlcEng(ComDmstcDlvCstPlc comDmstcDlvCstPlc) {
		return getSession1().update("com.plgrim.ncp.biz.vendor.updateComOvseaDlvCstPlcEng", comDmstcDlvCstPlc);
	}

	/**
	 * 중문 해외배송비 정책 수정.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param comDmstcDlvCstPlc [설명]
	 * @since 2017. 10. 26
	 */
	public int updateComOvseaDlvCstPlcChi(ComDmstcDlvCstPlc comDmstcDlvCstPlc) {
		return getSession1().update("com.plgrim.ncp.biz.vendor.updateComOvseaDlvCstPlcChi", comDmstcDlvCstPlc);
	}
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */

	/**
	 * 해외배송비 정책 이력 등록.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param comOvseaDlvCstPlcLangHis [설명]
	 * @since 2017. 10. 31
	 */
	public int insertComOvseaDlvCstPlcLangHist(ComOvseaDlvCstPlcLangHis comOvseaDlvCstPlcLangHis) {
		return getSession1().insert("com.plgrim.ncp.base.insertComOvseaDlvCstPlcLangHis", comOvseaDlvCstPlcLangHis);
	}
}
