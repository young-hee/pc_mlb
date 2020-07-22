/* Copyright (c) 2013 plgrim, Inc.
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
 *                       
 */
package com.plgrim.ncp.commons.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plgrim.ncp.base.abstracts.AbstractService;
import com.plgrim.ncp.base.entities.datasource1.god.GodAditDetailExtend;
import com.plgrim.ncp.commons.repository.CodeViewRepository;
import com.plgrim.ncp.commons.result.CodeViewResult;

/**
 * CodeViewService Implementation
 * @author Park
 *
 */
@Service
public class CodeViewService extends AbstractService {

	/**
	 * 공통코드 조회 Repository
	 */
	@Autowired
	private CodeViewRepository codeViewRepository;
	
	/**
	 * 공통코드 조회
	 * @param codeGroup
	 * @return List<CodeViewResult> @see CodeViewResult
	 */
	public List<CodeViewResult> getCodeList(String codeGroup) {
		return codeViewRepository.getCodeList(codeGroup);
	}

	/**
	 * 언어별 공통코드 조회
	 * @param codeGroup
	 * @return List<CodeViewResult> @see CodeViewResult
	 */
	public List<CodeViewResult> getCodeList(String codeGroup, String lang) {
		return codeViewRepository.getCodeList(codeGroup, lang);
	}
	
	/**
	 * 공통코드 조회
	 * @param upperCd 코드그룹
	 * @param cd 코드
	 * @return CodeViewResult @see CodeViewResult
	 */
	public CodeViewResult getCode(String upperCd, String cd) {
		return codeViewRepository.getCode(upperCd, cd);
	}
	
	/**
	 * 다국어 공통코드 조회
	 * @param upperCd 코드그룹
	 * @param cd 코드
	 * @param langCd 언어코드
	 * @return CodeViewResult @see CodeViewResult
	 */
	public CodeViewResult getCode(String upperCd, String cd, String langCd) {
		return codeViewRepository.getCode(upperCd, cd, langCd);
	}
	
	/**
	 * SR[22287] 상품상세 페이지 내 상품 추가정보 조회
	 * @param upperCd 코드그룹 추가정보 조회
	 * @param cd 코드
	 * @return CodeViewResult @see CodeViewResult
	 */
	public List<GodAditDetailExtend> getAddInfoCodeList(String upperCd, String langCd, String godNo) {
		return codeViewRepository.getAddInfoCodeList(upperCd, langCd, godNo);
	}

}
