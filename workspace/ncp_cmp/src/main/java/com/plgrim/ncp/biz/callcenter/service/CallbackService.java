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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.plgrim.ncp.biz.callcenter.data.CallbackGridDTO;
import com.plgrim.ncp.biz.callcenter.data.CallbackParameter;
import com.plgrim.ncp.biz.callcenter.data.InfCallBackDTO;
import com.plgrim.ncp.biz.callcenter.repository.CallbackRepository;
import com.plgrim.ncp.commons.util.BOSecurityUtil;

/**
 * CodeViewService Implementation
 * @author Park
 *
 */
@Service
public class CallbackService {

	@Autowired
	private CallbackRepository callbackRepository;


	public void updateCallbackGrid(List<CallbackGridDTO> updateList) {
		Iterator<CallbackGridDTO> iterator = updateList.iterator();
		
		RestTemplate rest = new RestTemplate();
		CallbackParameter callbackParameter = new CallbackParameter();
		
		
		/*while(iterator.hasNext()) {
			callbackRepository.updateCallbackGrid(iterator.next());
		}*/
		for(int i= 0; i < updateList.size() ; i++){
			String url = "http://71.62.2.31/callback/update?callbackId=";
				   url += updateList.get(i).getClbcId()+"&callbackStatusCode="+updateList.get(i).getClbcPrcsStatCd();
			
			callbackParameter = rest.getForObject(url, CallbackParameter.class);
			
			if(callbackParameter.getResponseResultFlag()){
				updateList.get(i).setLoginId(BOSecurityUtil.getLoginId());
				if (updateList.get(i).getClbcPrcsStatCd().equals("110")) {
					updateList.get(i).setClbcPrcsStatNm("배포-미진행");
				} else if (updateList.get(i).getClbcPrcsStatCd().equals("131")) {
					updateList.get(i).setClbcPrcsStatNm("연결실패");
				} else if (updateList.get(i).getClbcPrcsStatCd().equals("132")) {
					updateList.get(i).setClbcPrcsStatNm("상담진행중");
				} else if (updateList.get(i).getClbcPrcsStatCd().equals("141")) {
					updateList.get(i).setClbcPrcsStatNm("완료-성공");
				} else if (updateList.get(i).getClbcPrcsStatCd().equals("142")) {
					updateList.get(i).setClbcPrcsStatNm("완료-실패");
				}

				callbackRepository.updateCallbackGrid(updateList.get(i));
			}
		}
	}

	public void insertCallback(InfCallBackDTO infCallBackDTO) throws Exception{
		callbackRepository.insertCallback(infCallBackDTO);
	}
}
