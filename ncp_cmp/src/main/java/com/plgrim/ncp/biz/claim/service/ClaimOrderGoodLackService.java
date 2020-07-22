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
package com.plgrim.ncp.biz.claim.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plgrim.ncp.base.abstracts.AbstractService;
import com.plgrim.ncp.biz.claim.data.ClaimListSearchDTO;
import com.plgrim.ncp.biz.claim.repository.ClaimOrderGoodLackRepository;
import com.plgrim.ncp.biz.claim.result.ClaimListResult;
import com.plgrim.ncp.biz.claim.result.DeliveryOrderGoodResult;
import com.plgrim.ncp.commons.taglib.Functions;
import com.plgrim.ncp.commons.util.BOSecurityUtil;
import com.plgrim.ncp.framework.commons.StringService;

/**
 * CodeViewService Implementation
 * @author Park
 *
 */
@Slf4j
@Service
public class ClaimOrderGoodLackService extends AbstractService {

	@Autowired
	ClaimOrderGoodLackRepository claimOrderGoodLackRepository;

	public List<DeliveryOrderGoodResult> selectOrderGoodLackList(ClaimListSearchDTO claimListSearchDTO) {
		List<String> searchNoList = new ArrayList<String>();
		
		//다중검색
		if(StringService.isNotEmpty(claimListSearchDTO.getSearchNos())) {
			String[] searchNoArr = claimListSearchDTO.getSearchNos().split("\\r?\\n");
			for (String searchNo : searchNoArr) {
				searchNoList.add(searchNo.toUpperCase().trim());
			}
			claimListSearchDTO.setSearchNoList(searchNoList);
		}
		
		return claimOrderGoodLackRepository.selectOrderGoodLackList(claimListSearchDTO);
	}
	
	public List<ClaimListResult> selectOrderGoodLackListForExcel(ClaimListSearchDTO claimListSearchDTO) {
		List<String> searchNoList = new ArrayList<String>();
		
		//다중검색
		if(StringService.isNotEmpty(claimListSearchDTO.getSearchNos())) {
			String[] searchNoArr = claimListSearchDTO.getSearchNos().split("\\r?\\n");
			for (String searchNo : searchNoArr) {
				searchNoList.add(searchNo.toUpperCase().trim());
			}
			claimListSearchDTO.setSearchNoList(searchNoList);
		}
		
		return claimOrderGoodLackRepository.selectOrderGoodLackListForExcel(claimListSearchDTO);
	}
	

	public long selectOrderGoodLackListTotal(ClaimListSearchDTO claimListSearchDTO) {
		List<String> searchNoList = new ArrayList<String>();
		
		//다중검색
		if(StringService.isNotEmpty(claimListSearchDTO.getSearchNos())) {
			String[] searchNoArr = claimListSearchDTO.getSearchNos().split("\\r?\\n");
			for (String searchNo : searchNoArr) {
				searchNoList.add(searchNo.toUpperCase().trim());
			}
			claimListSearchDTO.setSearchNoList(searchNoList);
		}
		
		return claimOrderGoodLackRepository.selectOrderGoodLackListTotal(claimListSearchDTO);
	}


	public long mainOrderGoodLackCount() throws Exception {

		HashMap<String, String>  map = Functions.beForeMonth();
		
		ClaimListSearchDTO claimListSearchDTO = new ClaimListSearchDTO();
		claimListSearchDTO.setClmDtStart(map.get("S"));
		claimListSearchDTO.setClmDtEnd(map.get("E"));
		if( BOSecurityUtil.getAdminTpCd().equals("AFF_AGNC")){
			
			claimListSearchDTO.setAffVrscComId(BOSecurityUtil.getComId());
		}
		
		claimListSearchDTO.setBrndIdArr(BOSecurityUtil.getStringListFromAuthBrndList());
		return claimOrderGoodLackRepository.selectOrderGoodLackListTotal(claimListSearchDTO);
	}
	
	
	/***************************************************************************************************
	 * Khan
	 * *************************************************************************************************/

	

}
