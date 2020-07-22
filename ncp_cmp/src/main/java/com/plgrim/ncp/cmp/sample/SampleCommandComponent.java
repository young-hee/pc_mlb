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
package com.plgrim.ncp.cmp.sample;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.plgrim.ncp.base.entities.datasource1.sample.Dept;
import com.plgrim.ncp.base.entities.datasource1.sample.Emp;
import com.plgrim.ncp.biz.example.dto.EmpSampleDTO;
import com.plgrim.ncp.biz.sample.data.SampleDTO;
import com.plgrim.ncp.framework.data.SystemPK;



/**
 * 샘플 예제를 위한 커맨드 컴포넌트
 * 
 * <p>
 * 
 * <ul>
 *   <li> 사원 정보 등록
 *   <li> 사원 정보 수정
 *   <li> 사원 정보 삭제
 * </ul>.
 *
 * @author tktaeki.kim
 * @since 2015. 2. 26
 */
public interface SampleCommandComponent {
	
	
	/**
	 * [메서드 설명].
	 * 
	 * <p/>
	 * 
	 * <strong>선수조건</strong>
	 * <p/>
	 * [선수조건 작성] <br/>
	 * <p/>
	 * <strong>입력 파라미터 정의</strong>
	 * <p/>
	 * 
	 * 예)
	 * <p/>
	 * dto.emp.empno 사원번호 - 필수<br/>
	 * dto.emp.ename 사원명 - 필수<br/>
	 * dto.emp.confirmYm 사원구분 - 필수 - Y/N<br/>
	 * dtp.emp.deptno 부서번호 - 선택<br/>
	 *
	 * @param dto [설명]
	 * @throws Exception the exception
	 * @since 2015. 3. 3
	 */
	void addEmp (SystemPK systemPk, SampleDTO dto) throws Exception;
	
	
	/**
	 * 사원 정보를 DB에 저장 한다.
	 * 
	 * <p/>
	 * 
	 * <strong>선수조건</strong>
	 * <p/>
	 * 
	 * 반드시 사원구분 코드가 'Y'값 이어야 한다.
	 * 
	 * <p/>
	 * <strong>입력 파라미터 정의</strong>
	 * <p/>
	 * 
	 * 예)
	 * <p/>
	 * emp.empno 사원번호 - 필수<br/>
	 * emp.ename 사원명 - 필수<br/>
	 * emp.confirmYm 사원구분 - 필수 - Y/N<br/>
	 * emp.deptno 부서번호 - 선택<br/>
	 *
	 * @param emp 사원 엔티티
	 * @throws Exception the exception
	 * @since 2015. 2. 26
	 */
	void addEmp (SystemPK systemPk, Emp emp) throws Exception;
	
	
	
	
	/**
	 * 사원 정보를 수정 한다.
	 * 
	 * <p/>
	 * 
	 * <strong>선수조건</strong>
	 * <p/>
	 * 반드시 사원번호(empno)가 존재 해야 한다. <br/>
	 * <p/>
	 * <strong>입력 파라미터 정의</strong>
	 * <p/>
	 * 
	 * 예)
	 * <p/>
	 * emp.empno 사원번호 - 필수<br/>
	 * emp.ename 사원명 - 필수<br/>
	 * emp.confirmYm 사원구분 - 필수 - Y/N<br/>
	 * emp.deptno 부서번호 - 선택<br/>
	 * 
	 * <p/>
	 * <strong>출력 파라미터 정의</strong>
	 *  <p/>
	 * 예)
	 * int 처리결과수 - 필수
	 * <p/>
	 *
	 * @param emp 사원 엔티티
	 * @return the int
	 * @throws Exception the exception
	 * @since 2015. 2. 26
	 */
	int modifyEmp(SystemPK systemPk, Emp emp)  throws Exception;
	
	
	
	/**
	 * 사원 정보를 삭제 한다.
	 * 
	 * <p/>
	 * 
	 * <strong>선수조건</strong>
	 * <p/>
	 * 반드시 사원번호(empno)가 존재 해야 한다. <br/>
	 * <p/>
	 * <strong>입력 파라미터 정의</strong>
	 * <p/>
	 * 
	 * 예)
	 * <p/>
	 * empno 사원번호 - 필수<br/>
	 * 
	 * <p/>
	 * <strong>출력 파라미터 정의</strong>
	 * <p/>
	 * 예)
	 * int 처리결과수 - 필수
	 * <p/>
	 *
	 * @param empno 사원번호
	 * @return 처리결과수
	 * @throws Exception the exception
	 * @since 2015. 2. 26
	 */
	int removeEmp(SystemPK systemPk, int empno) throws Exception;
	
	
	/**
	 * [메서드 설명].
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param emp [설명]
	 * @throws Exception the exception
	 * @since 2015. 3. 9
	 */
	void transaction1(SystemPK systemPk, Emp emp) throws Exception;
	
	void transaction2(SystemPK systemPk, Emp emp, Dept dept) throws Exception;
	
	void createFile(EmpSampleDTO dto, List<MultipartFile> files) throws Exception;

	/*Exception 테스트를 위한 메서드*/
	void exception(String directMessage, String[] exceptionParam) throws Exception;
}
