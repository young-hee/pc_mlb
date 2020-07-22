package com.plgrim.ncp.commons.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.datasource1.god.GodAditDetailExtend;
import com.plgrim.ncp.commons.result.CodeViewResult;

/**
 * 공통 코드 View Repository
 * @author Park
 *
 */
@Repository
public class CodeViewRepository extends AbstractRepository{

	/**
	 * 공통코드리스트 조회
	 * @param codeGroup
	 * @return
	 */
	public List<CodeViewResult> getAllCodeList() {
		return getSession1().selectList("com.plgrim.ncp.commons.code.getAllCodeList");
	}

	public List<CodeViewResult> getAllRawCodeList() {
		return getSession1().selectList("com.plgrim.ncp.commons.code.getAllRawCodeList");
	}

	/**
	 * 공통코드리스트 조회
	 * @param codeGroup
	 * @return
	 */
	public List<CodeViewResult> getCodeList(String codeGroup) {
		return getSession1().selectList("com.plgrim.ncp.commons.code.getCodeList", codeGroup);
	}

	/**
	 * 다국어 공통코드리스트 조회
	 * @param upperCd 코드그룹
	 * @param langCd 언어
	 * @return List<CodeViewResult>
	 */
	public List<CodeViewResult> getCodeList(String upperCd, String langCd) {
		Map<String, String> param = new HashMap<String, String>();
		param.put("upperCd", upperCd);
		param.put("langCd", langCd);
		return getSession1().selectList("com.plgrim.ncp.commons.code.getLocalizedCodeList", param);
	}
	
	
	/**
	 * 공통코드 조회
	 * @param upperCd 코드그룹
	 * @param cd 코드
	 * @return CodeViewResult @see CodeViewResult
	 */
	public CodeViewResult getCode(String upperCd, String cd) {
		Map<String, String> param = new HashMap<String, String>();
		param.put("upperCd", upperCd);
		param.put("cd", cd);
		return getSession1().selectOne("com.plgrim.ncp.commons.code.getCode", param);
	}

	/**
	 * 다국어 공통코드 조회
	 * @param upperCd 코드그룹
	 * @param cd 코드
	 * @param langCd 언어코드
	 * @return CodeViewResult @see CodeViewResult
	 */
	public CodeViewResult getCode(String upperCd, String cd, String langCd) {
		Map<String, String> param = new HashMap<String, String>();
		param.put("upperCd", upperCd);
		param.put("cd", cd);
		param.put("langCd", langCd);
		return getSession1().selectOne("com.plgrim.ncp.commons.code.getLocalizedCode", param);
	}
	

	/**
	 * SR[22287] 상품상세 페이지 내 상품 추가정보 조회
	 * @param upperCd 코드그룹 추가정보 조회
	 * @param cd 코드
	 * @return CodeViewResult @see CodeViewResult
	 */
	@Cacheable("codeViewRepository.getAddInfoCodeList")
	public List<GodAditDetailExtend> getAddInfoCodeList(String upperCd, String langCd, String godNo) {
		Map<String, String> param = new HashMap<String, String>();
		param.put("upperCd", upperCd);
		param.put("langCd", langCd);
		param.put("godNo", godNo);
		return getSession1().selectList("com.plgrim.ncp.commons.code.getAddInfoCodeList", param);
	}

}