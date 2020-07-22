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
package com.plgrim.ncp.commons;

import java.util.List;
import java.util.Map;

import com.plgrim.ncp.commons.data.GodEvlDTO;
import com.plgrim.ncp.commons.result.GodEvlResult;
import com.plgrim.ncp.framework.data.SystemPK;


public interface GodEvlSelectComponent {

	/**
	 * 상품평 목록 조회.
	 *
	 * @param systemPK [설명]
	 * @param paramData [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public List<GodEvlResult> selectGodEvlList( SystemPK systemPK, GodEvlDTO paramData) throws Exception ;
	
	/**
	 * 상품평 목록 조회 개수.
	 *
	 * @param systemPK [설명]
	 * @param paramData [설명]
	 * @return Long [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public long selectGodEvlListCount( SystemPK systemPK, GodEvlDTO paramData) throws Exception ;
	
	/**
	 * 상품평 목록 조회 엑셀.
	 *
	 * @param systemPK [설명]
	 * @param paramData [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public List<Map<String, Object>> selectGodEvlListExcel( SystemPK systemPK, GodEvlDTO paramData) throws Exception ;
	
	/**
	 * 상품평 상세 조회.
	 *
	 * @param systemPK [설명]
	 * @param paramData [설명]
	 * @return Sys cmmn noti result [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public GodEvlResult selectGodEvlDetail( SystemPK systemPK, GodEvlDTO paramData) throws Exception ;
	
	/**
	 * 상품평관리 베스트 목록 조회 개수.
	 *
	 * @param systemPK [설명]
	 * @param paramData [설명]
	 * @return Long [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public long selectBestGodEvlListCount( SystemPK systemPK, GodEvlDTO paramData) throws Exception ;
}