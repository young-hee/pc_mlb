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
 * @since       2015. 3. 24       
 */
package com.plgrim.ncp.biz.sample.service;

import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.plgrim.ncp.base.abstracts.AbstractService;
import com.plgrim.ncp.base.entities.datasource1.sample.Dept;
import com.plgrim.ncp.base.entities.datasource1.sample.Emp;
import com.plgrim.ncp.biz.example.dto.EmpSampleDTO;
import com.plgrim.ncp.biz.sample.repository.EmpRepository;
import com.plgrim.ncp.framework.data.FileUploadInfo;
import com.plgrim.ncp.framework.data.FileUploadResult;
import com.plgrim.ncp.framework.data.SystemPK;
import com.plgrim.ncp.framework.exception.NotSupportedLanguageException;
import com.plgrim.ncp.framework.exception.NotSupportedUploadFileException;

/**
 * SampleService 구현체
 * 
 * <p>
 * 
 *
 * @author tktaeki.kim
 * @since 2015. 2. 27
 */
@Service
@Slf4j
public class SampleService extends AbstractService {

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */

	/** 사원 레파지토리. */
	@Autowired
	EmpRepository empRepository;

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
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.plgrim.ncp.biz.sample.layer1.service.SampleService#createEmp(com.plgrim
	 * .ncp.base.entities.layer1.sample.Emp)
	 */
	//@Transactional(value = "transactionManager", propagation = Propagation.REQUIRES_NEW)
	public void createEmp(SystemPK systemPk, Emp emp) throws Exception {
		if(emp.getEmpno() == 501){
			throw new RuntimeException();
		}else{
			empRepository.insertEmp(systemPk, emp);
		}
	    	
	}
	
	
	public void createDept(SystemPK systemPk, Dept dept) throws Exception {
		
		if(dept.getDeptNo() == 3){
			throw new RuntimeException();
		}else{
			empRepository.insertDept(systemPk, dept);
		}
		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.plgrim.ncp.biz.sample.layer1.service.SampleService#modifyEmp(com.plgrim
	 * .ncp.base.entities.layer1.sample.Emp)
	 */
	public int modifyEmp(SystemPK systemPk, Emp emp) throws Exception {
		return empRepository.updateEmp(systemPk, emp);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.plgrim.ncp.biz.sample.layer1.service.SampleService#removeEmp(int)
	 */
	public int removeEmp(SystemPK systemPk, int empno) throws Exception {
		return empRepository.deleteEmp(systemPk, empno);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.plgrim.ncp.biz.sample.layer1.service.SampleService#findEmpByEmpno(int)
	 */
	public Emp findEmpByEmpno(SystemPK systemPk, int empno) throws Exception {
		return empRepository.selectEmpByEmpno(systemPk, empno);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.plgrim.ncp.biz.sample.layer1.service.SampleService#isEmpByEmpno(int)
	 */
	public boolean isEmpByEmpno(SystemPK systemPk, int empno) throws Exception {

		Emp emp = findEmpByEmpno(systemPk, empno);

		if (emp == null) {
			return false;
		}

		return true;
	}

	/**
	 * 파일 업로드 예제
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dto [설명]
	 * @param files [설명]
	 * @throws Exception the exception
	 * @since 2015. 3. 24
	 */
	public void insertFile(EmpSampleDTO dto, List<MultipartFile> files)
	        throws Exception {

		log.info("- text parameter ----------");
		log.info("empno : " + dto.getEmpno());
		log.info("ename : " + dto.getEname());
	
		// 전체 업로드 확장자 체크를 한다.
		// excludeExtensions 포함된 데이터는 업로드 할 수 없다.
		String[] excludeExtensions = { "jsp", "php", "exe", "bat"};
		
		//확장자를 체크한다. 불가 확장자이며 NotSupportedUploadFileException 발생시킨다.
		getIoService().availableUploadExcludeExtension(files, excludeExtensions);
		
		/* 시스템 파일명을 자동으로 부여하여 업로드한다.
			FileUploadResult  fileUploadResult = getIoService().fileUploadAutoFileName(files, "c:/upload/", excludeExtensions);
		*/
		// 중복된 파일이 존재하면 오버라이드한다.
		FileUploadResult  fileUploadResult = getIoService().fileUploadOverWrite(files,  "c:/upload/", excludeExtensions);
		

	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */

}
