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
package com.plgrim.ncp.biz.sample.repository;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.datasource1.sample.Dept;
import com.plgrim.ncp.base.entities.datasource1.sample.Emp;
import com.plgrim.ncp.framework.data.SystemPK;

/**
 * 사원 정보에 대한 DB 처리를 하는 Repository
 * 
 * <p>
 * 
 * <ul>
 * <li>DB 접속 체크 쿼리 사원 상세 조회 사원 등록 사원 수정 사원 삭제
 * </ul>.
 *
 * @author tktaeki.kim
 * @since 2015. 2. 27
 */
@Repository
@Slf4j
public class EmpRepository extends AbstractRepository {

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
	 * db 접속 유무 테스트 쿼리.
	 * 
	 * <p/>
	 * 
	 *
	 * @return Integer 리턴 상수
	 * @throws Exception the exception
	 * @since 2015. 2. 27
	 */
	public Integer selectEcho() throws Exception {
		
		Integer result = getSession1().selectOne(
		        "com.plgrim.ncp.biz.sample.echo");
		return result;
	}

	/**
	 * 사원번호를 기준으로 사원 상세 정보를 조회.
	 * 
	 * <p/>
	 *
	 * @param empno 사원번호
	 * @return Emp 사원 엔티티
	 * @throws Exception the exception
	 * @since 2015. 2. 27
	 */
	public Emp selectEmpByEmpno(SystemPK systemPk, int empno) throws Exception {
		
		Emp result = getSession1().selectOne(
		        "com.plgrim.ncp.biz.sample.selectEmpByEmpno", empno);

		return result;
	}

	/**
	 * 사원 정보 등록.
	 * 
	 * <p/>
	 * 
	 *
	 * @param emp 사원 엔티티
	 * @throws Exception the exception
	 * @since 2015. 2. 27
	 */
	public void insertEmp(SystemPK systemPk, Emp emp) throws Exception {
		getSession1().insert("com.plgrim.ncp.biz.sample.insertEmp", emp);
	}

	
	public void insertDept(SystemPK systemPk, Dept dept) throws Exception {
		getSession1().insert("com.plgrim.ncp.biz.sample.insertDept", dept);
	}
	
	
//	insertDept
	
	/**
	 * 사원 정보 수정.
	 * 
	 * <p/>
	 * 
	 * @param emp 사원 엔티티
	 * @return Int 수정된 row 수
	 * @throws Exception the exception
	 * @since 2015. 2. 27
	 */
	public int updateEmp(SystemPK systemPk, Emp emp) throws Exception {
		// 업데이트 후에 데이터를 다시 조회 한다.
		return getSession1().update("com.plgrim.ncp.biz.sample.updateEmp",
		        emp);
	}

	/**
	 * 사원 정보 삭제.
	 * 
	 * <p/>
	 * 
	 *
	 * @param empno 사원번호
	 * @return Int 수정된 row 수
	 * @throws Exception the exception
	 * @since 2015. 2. 27
	 */
	public int deleteEmp(SystemPK systemPk, int empno) throws Exception {
		// 업데이트 후에 데이터를 다시 조회 한다.
		return getSession1().delete("com.plgrim.ncp.biz.sample.deleteEmp",
		        empno);
	}

	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}
