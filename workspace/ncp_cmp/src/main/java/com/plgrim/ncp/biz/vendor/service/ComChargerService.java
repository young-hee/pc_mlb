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
package com.plgrim.ncp.biz.vendor.service;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.plgrim.ncp.base.abstracts.AbstractService;
import com.plgrim.ncp.base.entities.datasource1.com.ComChrger;
import com.plgrim.ncp.biz.vendor.repository.ComChargerRepository;

/**
 * [업체 브랜드 담당자 서비스]
 * 
 * <p>
 * 
 * <ul>
 *   <li> [담당자 조회]
 *   <li> [담당자 수정]
 * </ul>.
 *
 * @author sy59.gim
 * @since 2015. 5. 8
 */
@Service
public class ComChargerService  extends AbstractService {

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	
	/**
	 * 업체 브랜드 담당자 Repository
	 */
	@Autowired
	private ComChargerRepository comChargerRepository;

	@Autowired
	@Qualifier("sessionTemplate1")
	SqlSession sqlSession1;	
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
		return comChargerRepository.selectCompanyCharger(comChrger);
	}
    
	/**
	 * 업체 담당자 저장.
	 *
	 * @param comChrger [설명]
	 * @return Long [설명]
	 * @since 2015. 3. 24
	 */
    public void mergeCompanyCharger(ComChrger comChrger, String loginId) {
    	comChrger.setRegtrId(loginId);
    	comChrger.setUdterId(loginId);
		comChargerRepository.mergeCompanyCharger(comChrger);
	}
	
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */

}
