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

import com.plgrim.ncp.base.abstracts.AbstractService;
import com.plgrim.ncp.base.entities.datasource1.cso.CsoFaqAtchmnfl;
import com.plgrim.ncp.biz.callcenter.data.FaqDTO;
import com.plgrim.ncp.biz.callcenter.data.FaqGridDTO;
import com.plgrim.ncp.biz.callcenter.repository.AlarmRepository;
import com.plgrim.ncp.biz.callcenter.repository.FaqRepository;
import com.plgrim.ncp.commons.util.BOSecurityUtil;
import com.plgrim.ncp.framework.commons.IOService;
import com.plgrim.ncp.framework.data.FileUploadInfo;
import com.plgrim.ncp.framework.data.FileUploadResult;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

/**
 * CodeViewService Implementation
 * @author Park
 *
 */
@Service
@Slf4j
public class AlarmService extends AbstractService {

	@Autowired
	private AlarmRepository alarmRepository;

	public void updateConfirmInPromiseCall(Map param) throws Exception{
		alarmRepository.updateConfirmInPromiseCall(param);
	}


	public void updateConfirmInTransfer(Map param) {
		alarmRepository.updateConfirmInTransfer(param);
	}
}
