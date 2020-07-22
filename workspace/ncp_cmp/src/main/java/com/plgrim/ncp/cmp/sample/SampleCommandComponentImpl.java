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
package com.plgrim.ncp.cmp.sample;

import java.util.List;

import com.plgrim.ncp.framework.commons.LocaleService;
import com.plgrim.ncp.framework.exception.NotSupportedLanguageException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.plgrim.ncp.base.abstracts.AbstractComponent;
import com.plgrim.ncp.base.entities.datasource1.sample.Dept;
import com.plgrim.ncp.base.entities.datasource1.sample.Emp;
import com.plgrim.ncp.biz.example.dto.EmpSampleDTO;
import com.plgrim.ncp.biz.sample.adapter.SampleAdatper;
import com.plgrim.ncp.biz.sample.data.SampleDTO;
import com.plgrim.ncp.biz.sample.exception.SampeEmpExistException;
import com.plgrim.ncp.biz.sample.service.SampleService;
import com.plgrim.ncp.framework.data.SystemPK;

/**
 * 샘플 컴포넌트 구현체.
 *
 * @author tktaeki.kim
 * @since 2015. 2. 26
 */
@Slf4j
@Transactional(value = "transactionManager")
@Component
public class SampleCommandComponentImpl extends AbstractComponent implements
        SampleCommandComponent {

	/** 샘플 서비스. */
	@Autowired
	SampleService sampleService;

	@Autowired
	SampleAdatper adapter;

	@Override
	public void transaction2(SystemPK systemPk, Emp emp, Dept dept)
	        throws Exception {

		sampleService.createDept(systemPk, dept);
		sampleService.createEmp(systemPk, emp);
//		adapter.invoke();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.plgrim.ncp.biz.sample.SampleCommandComponent#addEmp(com.plgrim.ncp.
	 * biz.sample.layer1.dto.SampleDTO)
	 */
	@Override
	public void addEmp(SystemPK systemPk, SampleDTO dto) throws Exception {

//		// 신통합 몰이면
//		if (Mall.SF == systemPk.getMall()) {
//			//국문이면
//			if (Lang.KOR == systemPk.getLang()) {
//				//웹 이면
//				if (Channel.WEB == systemPk.getChannel()) {
//
//					// dto에서 emp 오브젝트를 가져 온다.
//					Emp emp = dto.getEmp();
//
//					// 기존 데이터가 존재할경우
//					if (sampleService.isEmpByEmpno(systemPk, emp.getEmpno())) {
//
//						throw new SampeEmpExistException("현재 가입된 사용하지 입니다.");
//
//					}
//					else {
//
//						// emp 등록
//						sampleService.createEmp(systemPk, emp);
//					}
//
//				}
//			}
//		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.plgrim.ncp.biz.sample.SampleComponent#addEmp(com.plgrim.ncp.base.entities
	 * .layer1.sample.Emp)
	 */
	@Override
	public void addEmp(SystemPK systemPk, Emp emp) throws Exception {
		// 기존 데이터가 존재할경우
		if (sampleService.isEmpByEmpno(systemPk, emp.getEmpno())) {
			throw new SampeEmpExistException("현재 가입된 사용하지 입니다.");
		}
		else {
			// emp 등록
			sampleService.createEmp(systemPk, emp);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.plgrim.ncp.biz.sample.SampleComponent#modify(com.plgrim.ncp.base.entities
	 * .layer1.sample.Emp)
	 */
	@Override
	public int modifyEmp(SystemPK systemPk, Emp emp) throws Exception {
		// TODO Auto-generated method stub
		return sampleService.modifyEmp(systemPk, emp);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.plgrim.ncp.biz.sample.SampleComponent#removeEmp(int)
	 */
	@Override
	public int removeEmp(SystemPK systemPk, int empno) throws Exception {
		return sampleService.removeEmp(systemPk, empno);
	}

	@Override
	public void transaction1(SystemPK systemPk, Emp emp) throws Exception {

		sampleService.createEmp(systemPk, emp);
		adapter.invoke();

	}

	/* (non-Javadoc)
	 * @see com.plgrim.ncp.cmp.sample.SampleCommandComponent#createFile(com.plgrim.ncp.biz.example.dto.EmpSampleDTO, java.util.List)
	 */
	@Override
    public void createFile(EmpSampleDTO dto, List<MultipartFile> files)
            throws Exception {
		sampleService.insertFile(dto, files);
	    
    }

	@Override
	public void exception(String directMessage , String[] exceptionParam) throws Exception {

		boolean status = true;
		if (status) {


			//메세지 직접 입력
//			NotSupportedLanguageException exception = new NotSupportedLanguageException(null);
//			exception.setDirectMessage(directMessage);
//			throw exception;

			//기본형
//			throw new NotSupportedLanguageException(null);


			//파라미터 전달 방식
			throw new NotSupportedLanguageException(exceptionParam);

		}
	}
}
