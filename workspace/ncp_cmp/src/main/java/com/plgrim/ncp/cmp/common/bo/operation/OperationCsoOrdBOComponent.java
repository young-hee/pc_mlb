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
 * @since       2015. 6. 4       
 */
package com.plgrim.ncp.cmp.common.bo.operation;

import java.util.List;
import java.util.Map;

import com.plgrim.ncp.commons.data.CsoOrdAffDTO;
import com.plgrim.ncp.commons.result.CsoOrdAffResult;
import com.plgrim.ncp.framework.data.SystemPK;


public interface OperationCsoOrdBOComponent {

	/**
	 * 단체주문 / 제휴문의 목록 조회.
	 *
	 * @param systemPK [설명]
	 * @param paramData [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public List<CsoOrdAffResult> selectCsoOrdAffList( SystemPK systemPK, CsoOrdAffDTO paramData) throws Exception ;
	
	/**
	 * 단체주문 / 제휴문의 목록 조회 개수.
	 *
	 * @param systemPK [설명]
	 * @param paramData [설명]
	 * @return Long [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public long selectCsoOrdAffListCount( SystemPK systemPK, CsoOrdAffDTO paramData) throws Exception ;
	
	/**
	 * 단체주문 / 제휴문의 목록 조회 엑셀.
	 *
	 * @param systemPK [설명]
	 * @param paramData [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public List<Map<String, Object>> selectCsoOrdAffListExcel( SystemPK systemPK, CsoOrdAffDTO paramData) throws Exception ;
	
	/**
	 * 단체주문 / 제휴문의 상세 조회.
	 *
	 * @param systemPK [설명]
	 * @param paramData [설명]
	 * @return Sys cmmn noti result [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public CsoOrdAffResult selectCsoOrdAffDetail( SystemPK systemPK, CsoOrdAffDTO paramData) throws Exception ;

	/**
	 * 단체주문 / 제휴문의 답변 상태 수정.
	 *
	 * @param systemPK [설명]
	 * @param paramData [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public void updateCsoOrdAffStat( SystemPK systemPK, CsoOrdAffDTO paramData) throws Exception ;

	/**
	 * 단체주문 / 제휴문의 등록.
	 *
	 * @param systemPK [설명]
	 * @param paramData [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public void insertCsoOrdAff( SystemPK systemPK, CsoOrdAffDTO paramData) throws Exception ;


}