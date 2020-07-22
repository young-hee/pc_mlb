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

import java.util.Locale;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.plgrim.ncp.framework.commons.IdGenService;
import com.plgrim.ncp.framework.enums.DatabaseType;
import com.google.common.collect.Maps;

/**
 * IdGenService 테스트 케이스
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
public class IdGenServiceTest {

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
	 * 3가지 유형의 아이디 생성 테스트를 한다.
	 *
	 * @throws Exception the exception
	 * @since 2015. 2. 27
	 */
	@Test
	public void testGenerate() throws Exception {

		log.info(""
		        + idGenService.generateUUID());
		
		log.info(""
		        + idGenService.generateDBSequence(sqlSession1, "AA_SEQ",
		                DatabaseType.ORACLE));
		log.info(""
		        + idGenService.generateDBNumber(sqlSession1, "AA_SEQ", "IT",
		                DatabaseType.ORACLE));
		
		
		Map<String, Object> conditions = Maps.newHashMap();
		conditions.put("pk1", 1);
		conditions.put("pk2", "0121");
		
		log.info(""
		        + idGenService.generateDBOrder(sqlSession1, "aa_seq_table", "pk3",
		        		conditions, DatabaseType.ORACLE));
	}

	
//	@Test
	public void locale() throws Exception {
		
		Locale kor = new Locale("ko", "KR");
		Locale us = new Locale("en", "US");
		Locale cn = Locale.CHINESE;
		
		log.info(kor.getISO3Language());
		log.info(us.getISO3Language());
		log.info(cn.getISO3Language());
		
//		RequestContextUtils.getLocale(request);
		
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */

}
