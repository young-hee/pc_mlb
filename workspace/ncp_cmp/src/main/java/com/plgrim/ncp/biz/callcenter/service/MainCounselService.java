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

import com.plgrim.ncp.biz.callcenter.data.CsoCnsltClHistDTO;
import com.plgrim.ncp.biz.callcenter.repository.MainCounselRepository;
import com.plgrim.ncp.commons.util.BOSecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class MainCounselService {

	@Autowired
	MainCounselRepository mainCounselRepository;

	public void insertCtiCallInfo(CsoCnsltClHistDTO csoCnsltClHistDTO) {

		csoCnsltClHistDTO.setCnsltAdminId(BOSecurityUtil.getLoginId());
		csoCnsltClHistDTO.setRegtrId(BOSecurityUtil.getLoginId());
		csoCnsltClHistDTO.setUdterId(BOSecurityUtil.getLoginId());
		csoCnsltClHistDTO.setCstmrtelNationNo("82");

		mainCounselRepository.insertCtiCallInfo(csoCnsltClHistDTO);

	}
}
