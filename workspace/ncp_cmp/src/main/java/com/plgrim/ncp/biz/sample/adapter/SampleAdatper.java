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
 * @since       2015. 3. 9       
 */
package com.plgrim.ncp.biz.sample.adapter;

import org.springframework.stereotype.Service;

import com.plgrim.ncp.framework.exception.NotSupportedException;

/**
 * 어탭터 테스트
 * 
 * <p>
 * 
 *
 * @author tktaeki.kim
 * @since 2015. 3. 9
 */
@Service
public class SampleAdatper {

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
	 * 일부러 exception을 발생 시킨다.
	 * 
	 * <p/>
	 * 
	 * @throws Exception the exception
	 * @since 2015. 3. 9
	 */
	public void invoke() throws Exception {
		throw new NotSupportedException(null);
	}
	
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */

}
