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
 * @since       2015. 5. 11       
 */
package com.plgrim.ncp.biz.vendor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plgrim.ncp.base.abstracts.AbstractService;
import com.plgrim.ncp.biz.vendor.data.ShopSearchDTO;
import com.plgrim.ncp.biz.vendor.repository.ShopSearchRepository;
import com.plgrim.ncp.biz.vendor.result.SearchShopResult;
import com.plgrim.ncp.framework.page.PageParam;

/**
 * [시스템 매장]
 * 
 * <p>
 * 
 * <ul>
 *   <li> [기능1]
 *   <li> [기능2]
 * </ul>.
 *
 * @author sy59.gim
 * @since 2015. 5. 11
 */
@Service
public class ShopSearchService  extends AbstractService {



	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	/**
	 * 매장찾기 Repository
	 */
	@Autowired
	private ShopSearchRepository shopSearchRepository;

	/*
	 * ---------------------------------------------------------------------
	 * Constructors.
	 * ---------------------------------------------------------------------
	 */

	/*
	 * ---------------------------------------------------------------------
	 * public & interface method.
	 * ---------------------------------------------------------------------
	 */
	 
	/**
	 * My 매장찾기 브랜드명조회
	 *
	 * @param ShopSearchDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 6
	 */
	public List<SearchShopResult> getShopSrchBrndList(ShopSearchDTO dto) throws Exception {
		return shopSearchRepository.selectShopSrchBrndList(dto);
	}

	/**
	 * 매장찾기 조회
	 *
	 * @param ShopSearchDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 6
	 */
	public List<SearchShopResult> getShopSearchList(ShopSearchDTO dto) throws Exception {
		return shopSearchRepository.getShopSearchList(dto);
	}
	
	/**
	 * 구매가능 매장찾기 조회
	 *
	 * @param ShopSearchDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 6
	 */
	public List<SearchShopResult> getShopSearchForAvailableList(ShopSearchDTO dto) throws Exception {
		return shopSearchRepository.getShopSearchForAvailableList(dto);
	}
	
	/**
	 * 매장명 조회
	 *
	 * @param ShopSearchDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 6
	 */
	public SearchShopResult selectSearchShopInfo(ShopSearchDTO dto) throws Exception {
		return shopSearchRepository.selectSearchShopInfo(dto);
	}

	/**
	 * 모바일 매장찾기 조회
	 *
	 * @param ShopSearchDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 6
	 */
	public List<SearchShopResult> getMobileShopSearchList(ShopSearchDTO dto, PageParam pageParam) throws Exception {
		return shopSearchRepository.getMobileShopSearchList(dto, pageParam);
	}
	
	/**
	 * 매장찾기 건수
	 *
	 * @param ShopSearchDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 6
	 */
	public SearchShopResult getShopSearchCount(ShopSearchDTO dto) throws Exception {
		return shopSearchRepository.getShopSearchCount(dto);
	}
	
	/**
	 * 매장찾기 건수
	 *
	 * @param ShopSearchDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 6
	 */
	public SearchShopResult getShopSearchForAvailCount(ShopSearchDTO dto) throws Exception {
		return shopSearchRepository.getShopSearchForAvailCount(dto);
	}
	
	
	/**
	 * 매장찾기 Event조회
	 *
	 * @param ShopSearchDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 6
	 */
	public List<SearchShopResult> getShopSearchEvent(ShopSearchDTO dto) throws Exception {
		return shopSearchRepository.getShopSearchEvent(dto);
	}
	
	/**
	 * 가까운 매장 목록
	 *
	 * @param ShopSearchDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 6. 9
	 */
	public List<SearchShopResult> getNearShopSearchList(ShopSearchDTO dto) throws Exception {
		return shopSearchRepository.getNearShopSearchList(dto);
	}
	
	/**
	 * 가까운 매장 목록
	 *
	 * @param ShopSearchDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 6. 9
	 */
	public List<SearchShopResult> getNearShopSearchForAvailList(ShopSearchDTO dto) throws Exception {
		return shopSearchRepository.getNearShopSearchForAvailList(dto);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */

}
