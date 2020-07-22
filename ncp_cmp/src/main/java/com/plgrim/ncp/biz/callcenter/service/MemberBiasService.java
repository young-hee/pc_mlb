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
package com.plgrim.ncp.biz.callcenter.service;

import java.util.Iterator;
import java.util.List;

import com.plgrim.ncp.commons.util.BOSecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plgrim.ncp.base.enums.MemberBiasEnum;
import com.plgrim.ncp.biz.callcenter.data.MemberBiasDTO;
import com.plgrim.ncp.biz.callcenter.data.MemberBiasGridDTO;
import com.plgrim.ncp.biz.callcenter.repository.MemberBiasRepository;

/**
 * CodeViewService Implementation
 * @author Park
 *
 */
@Service
public class MemberBiasService {

	@Autowired
	private MemberBiasRepository memberBiasRepository;

	public void insertMemberBias(MemberBiasDTO memberBiasDTO) throws Exception{
		
		memberBiasDTO.setMemoRegtrId(BOSecurityUtil.getLoginId());
		memberBiasDTO.setMemoTpCd(MemberBiasEnum.memoTp.CSTMR_INCLN.toString());
		memberBiasDTO.setRegtrId(BOSecurityUtil.getLoginId());
		memberBiasDTO.setUdterId(BOSecurityUtil.getLoginId());
		memberBiasRepository.insertMemberBias(memberBiasDTO);
		
		
	}
	
	public void updateMemberBias(List<MemberBiasGridDTO> updateList) {
		Iterator<MemberBiasGridDTO> iterator = updateList.iterator();
		while(iterator.hasNext()) {
			//memberBiasRepository.updateOutCall(iterator.next());
			memberBiasRepository.updateMemberBias(iterator.next());
			
		}

	}
	
	
}
