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
 * @since       2015. 2. 27       
 */
package com.plgrim.ncp.base;

import javax.sql.DataSource;

import lombok.extern.slf4j.Slf4j;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.plgrim.ncp.framework.commons.DateService;
import com.plgrim.ncp.framework.commons.IdGenService;

/**
 * 스프링 컨텍스트 로드 테스트
 * 
 * <p>
 * 
 *
 * @author tktaeki.kim
 * @since 2015. 2. 27
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:/META-INF/spring/base.all.xml" })
@ActiveProfiles("local")
@Slf4j
public class ContextTest {

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */

	/* 시스템 설정 */
	static {
		System.setProperty("system.id", "ncp_base");
	}

	@Autowired
	@Qualifier("dataSource1")
	DataSource dataSource;

	@Autowired
	DateService dateService;
	
	@Autowired
	IdGenService idGenService;
	
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
	 * 스프링 컨테스트 테스트.
	 *
	 * @throws Exception the exception
	 * @since 2015. 2. 27
	 */
	@Test
	public void testLoadConfig() throws Exception {
		
//		String str = "#com.plgrim.ncp.framework.exception.NotSupportedException";

		String str = "java.lang.NullPointException";

	}

	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */

}
