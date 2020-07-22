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

import org.springframework.data.domain.Page;

import com.plgrim.ncp.base.entities.datasource1.sample.Emp;
import com.plgrim.ncp.framework.data.SystemPK;



/**
 * 샘플 예제를 위한 조회 컴포넌트
 * 
 * <p>
 * 
 * <ul>
 *   <li> 사원 상세 정보 조회
 *   <li> 사원 목록 조회
 * </ul>.
 *
 * @author tktaeki.kim
 * @since 2015. 2. 26
 */
public interface SampleSelectComponent {

	
	
	/**
	 * 사원 상세 정보를 조회 한다.
	 * 
	 * <p/>
	 * 
	 * <strong>선수조건</strong>
	 * <p/>
	 * 반드시 사원번호(empno)가 존재 해야 한다.<br/>
	 * <p/>
	 * <strong>입력 파라미터 정의</strong>
	 * <p/>
	 * 
	 * 예)
	 * <p/>
	 * empno 사원번호 - 필수<br/>
	 * 
	 * <strong>출력 파라미터 정의</strong>
	 * <p/>
	 * 
	 * emp.empno 사원번호 - 필수<br/>
	 * emp.ename 사원명 - 필수<br/>
	 * emp.sal 급여 - 선택
	 *
	 * @param empno 사원번호
	 * @return 사원 엔티티
	 * @throws Exception the exception
	 * @since 2015. 2. 26
	 */
	Emp getEmpDetail(SystemPK systemPk, int empno) throws Exception;
	
	/**
	 * 사원 기본 목록을 조회 한다.
	 * 
	 * <p/>
	 * 
	 * <strong>선수조건</strong>
	 * <p/>
	 * 해당 사항 없음 <br/>
	 * <p/>
	 * <strong>입력 파라미터 정의</strong>
	 * <p/>
	 * 
	 * 예)
	 * <p/>
	 * 없음<br/>
	 * <p/>
	 * <strong>출력 파라미터 정의</strong>
	 * <p/>
	 * Emp 엔티티 리스트<br/>
	 * 
	 * emp.empno 사원번호 - 필수<br/>
	 * emp.ename 사원명 - 필수<br/>
	 * emp.sal 급여 - 선택
	 *
	 * @return Emp 페이지 리스트
	 * @throws Exception the exception
	 * @since 2015. 2. 26
	 */
	Page<Emp> getEmpList(SystemPK systemPk) throws Exception;
	
	

}
