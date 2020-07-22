/* Copyright (c) 2015 plgrim, Inc.
 * All right reserved.
 * http://plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 *
 * ------------------------------------------------------------------------
 * @author      jwcale.kim
 * @since       2015. 7. 13       
 */
package com.plgrim.ncp.commons.repository;

import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.datasource1.cso.CsoOrgztOrdAffInq;
import com.plgrim.ncp.base.entities.datasource1.cso.CsoOrgztOrdAffInqAns;
import com.plgrim.ncp.commons.data.CsoOrdAffDTO;
import com.plgrim.ncp.commons.result.CsoOrdAffResult;

/**
 * 단체주문 / 제휴문의관리 Repository
 * @author ed
 *
 */
@Slf4j
@Repository
public class CsoOrdAffRepository extends AbstractRepository {
	
	/**
	 * 단체주문 / 제휴문의 목록 조회.
	 *
	 * @param paramData [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public List<CsoOrdAffResult> selectCsoOrdAffList( CsoOrdAffDTO paramData) throws Exception {
		return getSession1().selectList("com.plgrim.ncp.commons.csoordaff.selectCsoOrdAffList", paramData);
	}
	
	/**
	 * 단체주문 / 제휴문의 목록 조회 개수.
	 *
	 * @param paramData [설명]
	 * @return Long [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public long selectCsoOrdAffListCount( CsoOrdAffDTO paramData) throws Exception {
		return getSession1().selectOne("com.plgrim.ncp.commons.csoordaff.selectCsoOrdAffListCount", paramData);
    }
	
	/**
	 * 단체주문 / 제휴문의 목록 조회 엑셀.
	 *
	 * @param paramData [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public List<Map<String, Object>> selectCsoOrdAffListExcel( CsoOrdAffDTO paramData) throws Exception {
		return getSession1().selectList("com.plgrim.ncp.commons.csoordaff.selectCsoOrdAffListExcel", paramData);
	}
	
	/**
	 * 단체주문 / 제휴문의 상세 조회.
	 *
	 * @param paramData [설명]
	 * @return Sys cmmn noti result
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public CsoOrdAffResult selectCsoOrdAffDetail( CsoOrdAffDTO paramData) throws Exception {
		return getSession1().selectOne("com.plgrim.ncp.commons.csoordaff.selectCsoOrdAffDetail", paramData);
	}
	
	/**
	 * 단체주문 / 제휴문의 답변 등록.
	 *
	 * @param paramData [설명]
	 * @return int
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public int insertCsoOrdAffAns(CsoOrgztOrdAffInqAns paramData) throws Exception {
		return getSession1().insert("com.plgrim.ncp.commons.csoordaff.insertCsoOrdAffAns", paramData);
	}
	
	/**
	 * 단체주문 / 제휴문의 답변 상태 수정.
	 *
	 * @param paramData [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public int updateCsoOrdAffStat(CsoOrgztOrdAffInq paramData) {
		return getSession1().update("com.plgrim.ncp.commons.csoordaff.updateCsoOrdAffStat", paramData);
    }
}





