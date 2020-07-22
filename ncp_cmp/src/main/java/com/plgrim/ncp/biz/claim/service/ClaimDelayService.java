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
import com.plgrim.ncp.base.entities.datasource1.clm.Clm;
import com.plgrim.ncp.base.entities.datasource1.clm.ClmWrhsGod;
import com.plgrim.ncp.base.entities.datasource1.clm.ClmWrhsGodExtend;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsDlvspExtend;
import com.plgrim.ncp.base.entities.datasource1.ord.OrdClmGodCnnc;
import com.plgrim.ncp.base.enums.GoodsEnum.GoodsType;
import com.plgrim.ncp.biz.claim.data.*;
import com.plgrim.ncp.biz.claim.repository.*;
import com.plgrim.ncp.biz.claim.result.ClaimListResult;
import com.plgrim.ncp.biz.claim.result.ClmDetailListResult;
import com.plgrim.ncp.framework.commons.StringService;
import com.plgrim.ncp.framework.enums.DatabaseType;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * CodeViewService Implementation
 * @author Park
 *
 */
@Slf4j
@Service
public class ClaimDelayService extends AbstractService {

	@Autowired
	ClaimDelayRepository claimDelayRepository;

	public List<ClaimListResult> selectClaimDelayList(ClaimListSearchDTO claimListSearchDTO) {
		return claimDelayRepository.selectClaimDelayList(claimListSearchDTO);
	}

	public long selectClaimDelayListTotal(ClaimListSearchDTO claimListSearchDTO) {
		return claimDelayRepository.selectClaimDelayListTotal(claimListSearchDTO);
	}


	/***************************************************************************************************
	 * Khan
	 * *************************************************************************************************/

	

}
