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
 * @since       2015. 5. 29       
 */
package com.plgrim.ncp.commons;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.plgrim.ncp.commons.util.CodeUtil;
import com.plgrim.ncp.framework.commons.StringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.plgrim.ncp.base.abstracts.AbstractComponent;
import com.plgrim.ncp.commons.data.GodEvlDTO;
import com.plgrim.ncp.commons.result.GodEvlResult;
import com.plgrim.ncp.commons.service.GodEvlService;
import com.plgrim.ncp.framework.data.SystemPK;

// @Slf4j
@Component
public class GodEvlSelectComponentImpl extends AbstractComponent implements GodEvlSelectComponent {

	@Autowired
	GodEvlService godEvlService; // 상품평관리 Service
	
	/**
	 * 상품평관리 목록 조회.
	 *
	 * @param systemPK [설명]
	 * @param paramData [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public List<GodEvlResult> selectGodEvlList( SystemPK systemPK, GodEvlDTO paramData) throws Exception {
		List<GodEvlResult> resultList = new ArrayList<GodEvlResult>();
		List<GodEvlResult> godEvlList = godEvlService.selectGodEvlList(paramData);
		if(!godEvlList.isEmpty()){
			for(GodEvlResult evl : godEvlList){
				if(StringService.isNotEmpty(evl.getGodGodEvl().getLangCd())){
					evl.getGodGodEvl().setLangCd(CodeUtil.getCode("KOR", evl.getGodGodEvl().getLangCd()).getCdNm());
				}

				if(StringService.isNotEmpty(evl.getMbrStatCd())){
					evl.setMbrStatCd(CodeUtil.getCode("KOR", evl.getMbrStatCd()).getCdNm());
				}

				resultList.add(evl);
			}
		}
		return resultList;
	}
	
	/**
	 * 상품평관리 목록 조회 개수.
	 *
	 * @param systemPK [설명]
	 * @param paramData [설명]
	 * @return Long [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public long selectGodEvlListCount( SystemPK systemPK, GodEvlDTO paramData) throws Exception {
		return godEvlService.selectGodEvlListCount(paramData); 
	}
	
	/**
	 * 상품평관리 목록 조회 엑셀.
	 *
	 * @param systemPK [설명]
	 * @param paramData [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public List<Map<String, Object>> selectGodEvlListExcel( SystemPK systemPK, GodEvlDTO paramData) throws Exception {
		return godEvlService.selectGodEvlListExcel(paramData); 
	}
	
	/**
	 * 상품평관리 상세 조회.
	 *
	 * @param systemPK [설명]
	 * @param paramData [설명]
	 * @return Sys cmmn noti result [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public GodEvlResult selectGodEvlDetail( SystemPK systemPK, GodEvlDTO paramData) throws Exception {
		return godEvlService.selectGodEvlDetail(paramData); 
	}
	
	/**
	 * 상품평관리 베스트 목록 조회 개수.
	 *
	 * @param systemPK [설명]
	 * @param paramData [설명]
	 * @return Long [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public long selectBestGodEvlListCount( SystemPK systemPK, GodEvlDTO paramData) throws Exception {
		return godEvlService.selectBestGodEvlListCount(paramData); 
	}
}
