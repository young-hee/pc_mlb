///* Copyright (c) 2015 plgrim, Inc.
// * All right reserved.
// * http://plgrim.com
// * This software is the confidential and proprietary information of plgrim
// * , Inc. You shall not disclose such Confidential Information and
// * shall use it only in accordance with the terms of the license agreement
// * you entered into with plgrim.
// *
// * Revision History
// * Author              Date                         Description
// * ------------------   --------------                  ------------------
// * beyondj2ee			2015.02.09                      
// */
//package com.plgrim.ncp.biz.sample.layer1.service;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertNotNull;
//import static org.junit.Assert.assertNull;
//import static org.junit.Assert.assertTrue;
//import lombok.extern.slf4j.Slf4j;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.transaction.TransactionConfiguration;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.plgrim.ncp.base.entities.datasource1.sample.Emp;
//import com.plgrim.ncp.biz.sample.service.SampleService;
//import com.plgrim.ncp.framework.data.SystemPK;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration({ "classpath:/META-INF/spring/base.all.xml" })
//@ActiveProfiles("local")
//@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
//@Transactional
//@Slf4j
//public class SampleServiceTest {
//
//	/*
//	 * ---------------------------------------------------------------------
//	 * Instance fields.
//	 * ---------------------------------------------------------------------
//	 */
//	static {
//		System.setProperty("system.id", "ncp_base");
//	}
//
//	/** The emp repository. */
//	@Autowired
//	SampleService sampleService;
//
//	/** The emp. */
//	private Emp emp;
//
//	/*
//	 * ---------------------------------------------------------------------
//	 * Constructors.
//	 * ---------------------------------------------------------------------
//	 */
//	/*
//	 * ---------------------------------------------------------------------
//	 * public & interface method.
//	 * ---------------------------------------------------------------------
//	 */
//	/**
//	 * Before.
//	 */
//	@Before
//	public void before() {
//
//		emp = new Emp();
//		emp.setEmpno(999);
//		emp.setEname("jackie");
//		emp.setJob("dev");
//		emp.setSal(10000);
//		emp.setDeptNo(10);
//	}
//
//
//	/**
//	 * 사원 정보 추가.
//	 *
//	 * @throws Exception the exception
//	 */
//	@Test
//	public void testCreateEmp() throws Exception {
//
//		sampleService.createEmp(new SystemPK(), emp);
//
//		// 값 비교
//		Emp compare = sampleService.findEmpByEmpno(new SystemPK(), emp.getEmpno());
//
//		assertNotNull(compare);
//		assertTrue(emp.getEmpno().intValue() == compare.getEmpno().intValue());
//
//	}
//
//	/**
//	 * 사원 수정 및 샂세 조회 테스트.
//	 *
//	 * @throws Exception the exception
//	 */
//	@Test
//	public void testModifyEmp() throws Exception {
//
//		sampleService.createEmp(new SystemPK(), this.emp);
//
//		this.emp.setEname("jackie2");
//		this.emp.setJob("dev2");
//		this.emp.setSal(20000);
//		this.emp.setDeptNo(20);
//
//		int updateCount = sampleService.modifyEmp(new SystemPK(), this.emp);
//		Emp compare = sampleService.findEmpByEmpno(new SystemPK(), emp.getEmpno());
//
//		assertTrue(updateCount >= 1);
//		assertNotNull(compare);
//		assertEquals(this.emp.getEname(), this.emp.getEname());
//
//	}
//
//	/**
//	 * 사원 삭제 및 샂세 조회 테스트.
//	 *
//	 * @throws Exception the exception
//	 */
//	@Test
//	public void testRemoveEmp() throws Exception {
//		sampleService.createEmp(new SystemPK(), this.emp);
//
//		int deleteCount = sampleService.removeEmp(new SystemPK(), this.emp.getEmpno());
//		Emp compare = sampleService.findEmpByEmpno(new SystemPK(), emp.getEmpno());
//
//		assertTrue(deleteCount >= 1);
//		assertNull(compare);
//	}
//
//	/*
//	 * ---------------------------------------------------------------------
//	 * private method.
//	 * ---------------------------------------------------------------------
//	 */
//}
