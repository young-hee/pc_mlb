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
import com.fasterxml.jackson.databind.util.BeanUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plgrim.ncp.biz.callcenter.data.OutCallGridDTO;
import com.plgrim.ncp.biz.callcenter.repository.OutCallRepository;

/**
 * CodeViewService Implementation
 * @author Park
 *
 */
@Service
public class OutCallService {

	@Autowired
	private OutCallRepository outCallRepository;

	public void insertOutCall(List<OutCallGridDTO> createList) {
		
		Iterator<OutCallGridDTO> iterator = createList.iterator();
		while(iterator.hasNext()) {
			outCallRepository.insertOutCall(iterator.next());
		}
	}

	public void updateOutCall(List<OutCallGridDTO> updateList) {

		Iterator<OutCallGridDTO> iterator = updateList.iterator();
		while(iterator.hasNext()) {
			outCallRepository.updateOutCall(iterator.next());
		}

	}

	public void deleteOutCall(List<OutCallGridDTO> deleteList) {
		Iterator<OutCallGridDTO> iterator = deleteList.iterator();
		while(iterator.hasNext()) {
			outCallRepository.deleteOutCall(iterator.next());
		}
	}
	
	public String[] makePhoneNumber(String phoneNum) throws Exception{
		String[] totalArr = new String[4];
		totalArr[0] = "82";
		String[] tempNum = phoneNum.split("-");
		for(int i = 0 ; i < tempNum.length; i++){
			totalArr[i + 1] = tempNum[i];
		}
		return totalArr;
	}
}
