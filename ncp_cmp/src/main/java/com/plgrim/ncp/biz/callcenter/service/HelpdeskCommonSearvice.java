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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.plgrim.ncp.base.entities.datasource1.sys.SysShopExtends;
import com.plgrim.ncp.biz.callcenter.data.SendMailDTO;
import com.plgrim.ncp.biz.callcenter.repository.HelpdeskCommonRepository;
import com.plgrim.ncp.biz.helpdesk.result.HelpdeskEmailResult;
import com.plgrim.ncp.biz.vendor.data.ShopSearchDTO;
import com.plgrim.ncp.framework.page.PageParam;

/**
 * CodeViewService Implementation
 * @author Park
 *
 */


@Service
public class HelpdeskCommonSearvice {
	
	
	@Autowired
	HelpdeskCommonRepository helpdeskCommonRepository;

	public Page<SysShopExtends> getHelpdeskShopList(ShopSearchDTO shopSearchDTO , PageParam pageParam) throws Exception {
		return helpdeskCommonRepository.getHelpdeskShopList(shopSearchDTO, pageParam);
	}
	
	/**	1:1문의 메일 템플릿 입력정보 */
	public HelpdeskEmailResult getSendEmailTemplateInfo(SendMailDTO sendMailDTO) throws Exception{
		
		return helpdeskCommonRepository.getSendEmailTemplateInfo(sendMailDTO);
	}
	/**	단체제휴 메일 템플릿 입력정보 */
	public HelpdeskEmailResult getSendAffOrdEmailTemplateInfo(SendMailDTO sendMailDTO) throws Exception{
		
		return helpdeskCommonRepository.getSendAffOrdEmailTemplateInfo(sendMailDTO);
	}
}
