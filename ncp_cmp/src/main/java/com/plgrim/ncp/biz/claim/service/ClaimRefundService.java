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

import com.plgrim.ncp.base.abstracts.AbstractService;
import com.plgrim.ncp.biz.claim.data.ClaimListSearchDTO;
import com.plgrim.ncp.biz.claim.data.ClaimRefundListSearchDTO;
import com.plgrim.ncp.biz.claim.repository.ClaimDelayRepository;
import com.plgrim.ncp.biz.claim.repository.ClaimRefundRepository;
import com.plgrim.ncp.biz.claim.result.ClaimListResult;
import com.plgrim.ncp.biz.claim.result.ClaimRefundListResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * CodeViewService Implementation
 * @author Park
 *
 */
@Slf4j
@Service
public class ClaimRefundService extends AbstractService {

	@Autowired
	ClaimRefundRepository claimRefundRepository;

	public List<ClaimRefundListResult> selectClaimRefundList(ClaimRefundListSearchDTO claimRefundListSearchDTO) {
		return claimRefundRepository.selectClaimRefundList(claimRefundListSearchDTO);
	}

	public long selectClaimRefundListTotal(ClaimRefundListSearchDTO claimRefundListSearchDTO) {
		return claimRefundRepository.selectClaimRefundListTotal(claimRefundListSearchDTO);
	}


	/***************************************************************************************************
	 * Khan
	 * *************************************************************************************************/

	

}
