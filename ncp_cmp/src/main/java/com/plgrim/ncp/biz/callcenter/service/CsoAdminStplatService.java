/* Copyright (c) 2015 plgrim, Inc.
 * All right reserved.
 * http://plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 *
 * ------------------------------------------------------------------------
 * @author      sy59.gim
 * @since       2015. 6. 26       
 */
package com.plgrim.ncp.biz.callcenter.service;

import com.plgrim.ncp.base.entities.datasource1.sys.SysAdminStplatAgr;
import com.plgrim.ncp.base.entities.datasource1.sys.SysStplat;
import com.plgrim.ncp.base.repository.sys.SysAdminStplatAgrRepository;
import com.plgrim.ncp.base.repository.sys.SysStplatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plgrim.ncp.base.entities.datasource1.sys.SysAdminStplatAgr;
import com.plgrim.ncp.base.entities.datasource1.sys.SysStplat;
import com.plgrim.ncp.base.repository.sys.SysAdminStplatAgrRepository;
import com.plgrim.ncp.base.repository.sys.SysStplatRepository;

/**
 * [관리자 개인정보취급 동의].
 *
 * @author sy59.gim
 * @since 2015. 6. 26
 */
@Service
public class CsoAdminStplatService {

	/** 관리자 개인정보취급 동의 repository. */
	@Autowired
	SysAdminStplatAgrRepository sysAdminStplatAgrRepository;
	
	/** 시스템 약관 repository. */
	@Autowired
	SysStplatRepository sysStplatRepository;
	
	/**
	 * 관리자 개인정보 동의 이력 조회
	 *
	 * @param sasa [설명]
	 * @throws Exception the exception
	 * @since 2018. 7. 25
	 */
	public SysAdminStplatAgr selectSysAdminStplatAgr(SysAdminStplatAgr sasa) throws Exception {
		return sysAdminStplatAgrRepository.selectSysAdminStplatAgr(sasa);
	}
	
	/**
	 * 관리자 개인정보 동의 이력 등록
	 *
	 * @param sasa [설명]
	 * @throws Exception the exception
	 * @since 2015. 6. 26
	 */
	public void insertSysAdminStplatAgr(SysAdminStplatAgr sasa) throws Exception {
		sysAdminStplatAgrRepository.insertSysAdminStplatAgr(sasa);
	}
	
	/**
	 * 시스템 약관 조회
	 * 
	 * @param ss [설명]
	 * @return Sys stplat [설명]
	 * @throws Exception the exception
	 * @since 2015. 6. 26
	 */
	public SysStplat selectSysStplat(SysStplat ss) throws Exception {
		return sysStplatRepository.selectSysStplat(ss);
	}
}
