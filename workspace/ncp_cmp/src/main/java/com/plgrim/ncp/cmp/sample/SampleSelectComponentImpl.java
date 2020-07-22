/* Copyright (c) 2015 plgrim, Inc.
 * All right reserved.
 * http://plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 *
 * ------------------------------------------------------------------------
 * @author      tktaeki.kim
 * @since       2015. 2. 26       
 */
package com.plgrim.ncp.cmp.sample;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.plgrim.ncp.base.abstracts.AbstractComponent;
import com.plgrim.ncp.base.entities.datasource1.sample.Emp;
import com.plgrim.ncp.biz.sample.service.SampleService;
import com.plgrim.ncp.framework.data.SystemPK;

/**
 *  샘플 컴포넌트 구현체.
 *
 * @author tktaeki.kim
 * @since 2015. 2. 26
 */
@Component
@Slf4j
public class SampleSelectComponentImpl extends AbstractComponent implements
        SampleSelectComponent {

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */

	@Autowired
	SampleService sampleService;

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.plgrim.ncp.biz.sample.SampleComponent#getEmpDetail(int)
	 */
	@Override
	public Emp getEmpDetail(SystemPK systemPk, int empno) throws Exception {
		return sampleService.findEmpByEmpno(systemPk , empno);
	}

	@Override
    public Page<Emp> getEmpList(SystemPK systemPk) throws Exception {
	    // TODO Auto-generated method stub
	    return null;
    }
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}
