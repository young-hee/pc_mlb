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

import lombok.extern.slf4j.Slf4j;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


/**
 * ConfigProperties 기능 테스트
 * <p/>
 * <p/>
 *
 * @author tktaeki.kim
 * @since 2015. 2. 27
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:/META-INF/spring/base.all.xml"})
@ActiveProfiles("local")
@Slf4j
public class ConfigTest {

	/*
     * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */

    /* 시스템 설정*/
    static {
        System.setProperty("system.id", "ncp_base");
    }

    /* deploy 모드 로컬 설정 */
    String deployMode = "local";

    @Value("${ncp_base.oracle.driver}")
    String env;
	


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
    public void testConfig() throws Exception {
        // ncp_base
        log.info(env);
    }

	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */

}
