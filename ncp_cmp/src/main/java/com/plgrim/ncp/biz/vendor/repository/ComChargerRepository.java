/* Copyright (c) 2015 plgrim, Inc.
 * All right reserved.
 * http://plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 *
 * ------------------------------------------------------------------------
 * @author      sy59.gim
 * @since       2015. 5. 8       
 */
package com.plgrim.ncp.biz.vendor.repository;

import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.entities.datasource1.com.ComChrger;
import com.plgrim.ncp.base.repository.com.ComRepository;

/**
 * [업체 브랜드 담당자].
 *
 * @author sy59.gim
 * @since 2015. 5. 8
 */
@Repository
public class ComChargerRepository extends ComRepository  {

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
	 * 업체 담당자 조회.
	 *
	 * @param comChrger [설명]
	 * @return Long [설명]
	 * @since 2015. 3. 24
	 */
    public ComChrger selectCompanyCharger(ComChrger comChrger) {
		return getSession1().selectOne("com.plgrim.ncp.biz.com.chrger.selectCompanyCharger", comChrger);
	}
    
	/**
	 * 업체 담당자 저장.
	 *
	 * @param comChrger [설명]
	 * @return Long [설명]
	 * @since 2015. 3. 24
	 */
    public void mergeCompanyCharger(ComChrger comChrger) {
		getSession1().update("com.plgrim.ncp.biz.com.chrger.mergeCompanyCharger", comChrger);
	}

	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */

}
