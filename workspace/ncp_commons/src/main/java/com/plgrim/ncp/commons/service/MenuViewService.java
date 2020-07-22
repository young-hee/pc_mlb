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
package com.plgrim.ncp.commons.service;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plgrim.ncp.base.abstracts.AbstractService;
import com.plgrim.ncp.commons.repository.MenuViewRepository;
import com.plgrim.ncp.commons.result.MenuViewResult;

/**
 * @author Park
 *
 */
@Service
public class MenuViewService extends AbstractService {

	@Autowired
	MenuViewRepository menuViewRepository;
	
	/**
	 * 메뉴 리스트 조회
	 * @param boSiteId boSiteId 사이트 ID
	 * @param upperMenuSn 상위메뉴 ID
	 * @return List<MenuViewResult>
	 */
	public List<MenuViewResult> getMenuList(String boSiteId, BigInteger upperMenuSn) {
		return menuViewRepository.getMenuList(boSiteId, upperMenuSn);
	}
}
