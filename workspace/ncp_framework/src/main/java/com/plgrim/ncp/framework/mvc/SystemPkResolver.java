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
 * @since       2015. 3. 23       
 */
package com.plgrim.ncp.framework.mvc;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import com.plgrim.ncp.framework.data.SystemPK;

/**
 * WEB-INF 밑에 리소스를 검색 후 리스트 형태로 리턴하는 팩토리 빈
 * 
 * <p>.
 *
 * @author tktaeki.kim
 * @since 2015. 3. 4
 */
@Slf4j
@Data
public class SystemPkResolver {

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	
	/* mall 아이디 */
	String mailId;
	
	/* 채널   */
	String channel;
	
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
	 * SystemPK를 환경변수를 읽어서 자동으로 생성 한다.
	 * 
	 * <p/>
	 * 
	 * @return System pk 자동 생성된 SystemPK
	 * @throws Exception the exception
	 * @since 2015. 3. 23
	 */
	public SystemPK getSystemPK() throws Exception {
		
		
		return null;
	}

	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */

	/**
	 * The main method.
	 *
	 * @param vars the arguments
	 * @throws Exception the exception
	 */
	public static void main(String[] vars) throws Exception {

	}

}
