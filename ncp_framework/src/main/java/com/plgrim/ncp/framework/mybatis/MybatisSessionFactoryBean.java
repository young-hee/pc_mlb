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
 * @since       2015. 4. 1       
 */
package com.plgrim.ncp.framework.mybatis;

import java.io.IOException;

import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;

/**
 * 
 * SqlSessionFactoryBean 상속 받아서 다이나믹하게 configuration을 설정 하도록 한다.
 * <p>
 * 
 *
 * @author tktaeki.kim
 * @since 2015. 4. 1
 */
public class MybatisSessionFactoryBean extends SqlSessionFactoryBean {

	@Override
	protected SqlSessionFactory buildSqlSessionFactory() throws IOException {

		SqlSessionFactory sessionFactory = super.buildSqlSessionFactory();
		Configuration configuration = sessionFactory.getConfiguration();

//		configuration.setMapUnderscoreToCamelCase(true);

		return sessionFactory;
	}

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

	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */

}
