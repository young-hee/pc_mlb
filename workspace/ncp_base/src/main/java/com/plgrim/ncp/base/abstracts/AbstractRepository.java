/* Copyright (c) 2015 plgrim, Inc.
 * All right reserved.
 * http://plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 *
 * Revision History
 * Author              Date                         Description
 * ------------------   --------------                  ------------------
 * beyondj2ee			2015.02.09        
 */
package com.plgrim.ncp.base.abstracts;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import lombok.Getter;
import lombok.Setter;

/**
 * 추상 Base 레파지토리.
 */
@Getter
@Setter
public abstract class AbstractRepository extends AbstractBean {

	/**
	 * PLGRIM 업무 DB
	 */
	@Autowired
	@Qualifier("sessionTemplate1")
	private SqlSession sqlSession1;

	/**
	 * 코드성 DB
	 */
	@Autowired(required = false)
	@Qualifier("sessionTemplate3")
	private SqlSession sqlSession3;

	/**
	 * Gets the base sql session.
	 *
	 * @return the base sql session
	 */
	protected SqlSession getSession1() {
		return this.sqlSession1;
	}

	/**
	 * Gets the base sql session.
	 *
	 * @return the base sql session
	 */
	protected SqlSession getSession3() {
		return this.sqlSession3;
	}
	

}
