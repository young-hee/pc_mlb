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
package com.plgrim.ncp.commons.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.commons.result.MenuViewResult;

/**
 * 메뉴 Repository
 * @author Park
 *
 */
@Repository
public class MenuViewRepository extends AbstractRepository {

	/**
	 * 메뉴 리스트 조회
	 * @param boSiteId boSiteId 사이트 ID
	 * @param upperMenuSn 상위메뉴 ID
	 * @return List<MenuViewResult>
	 */
	public List<MenuViewResult> getMenuList(String boSiteId, BigInteger upperMenuSn) {
		MenuViewResult menuViewResult = new MenuViewResult();
		menuViewResult.setBoSiteId(boSiteId);
		menuViewResult.setUpperMenuSn(upperMenuSn);
		
		return getSession1().selectList("com.plgrim.ncp.commons.menu.getMenuList", menuViewResult);
		
		//return getTempTopMenuList(boSiteId,upperMenuSn );
	}
	
	/**
	 * 임시 상단 메뉴 조회
	 * @param boSiteId
	 * @param upperMenuSn
	 * @return
	 */
	public List<MenuViewResult> getTempTopMenuList(String boSiteId, BigInteger upperMenuSn) {
		MenuViewResult menuViewResult = new MenuViewResult();
		menuViewResult.setBoSiteId(boSiteId);
		menuViewResult.setUpperMenuSn(upperMenuSn);
		return getSession1().selectList("com.plgrim.ncp.commons.menu.getTempTopMenuList", menuViewResult);
	}
	
	
}
