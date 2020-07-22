/* Copyright (c) 2015 plgrim, Inc.
 * All right reserved.
 * http://plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 *
 * ------------------------------------------------------------------------
 * @author      csh
 * @since       2015. 5. 11       
 */
package com.plgrim.ncp.biz.vendor.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.RepositoryHelper;
import com.plgrim.ncp.biz.vendor.data.ShopSearchDTO;
import com.plgrim.ncp.biz.vendor.result.SearchShopResult;
import com.plgrim.ncp.framework.page.PageParam;

/**
 * [매장찾기].
 *
 * @author csh
 * @since 2015. 5. 8
 */
@Repository
public class ShopSearchRepository extends AbstractRepository  {

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */

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
	 * 매장찾기 브랜드명조회
	 *
	 * @param ShopSearchDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 6
	 */
    public List<SearchShopResult> selectShopSrchBrndList(ShopSearchDTO dto) {
		return getSession1().selectList("com.plgrim.ncp.biz.sys.shop.search.selectShopSrchBrndList", dto);
	}
    
    /**
	 * 매장찾기 조회
	 *
	 * @param ShopSearchDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 6
	 */
    public List<SearchShopResult> getShopSearchList(ShopSearchDTO dto) {
		return getSession1().selectList("com.plgrim.ncp.biz.sys.shop.search.selectShopSearchList", dto);
	}
    
    /**
	 * 구매가능 매장찾기 조회
	 *
	 * @param ShopSearchDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 6
	 */
    public List<SearchShopResult> getShopSearchForAvailableList(ShopSearchDTO dto) {
		return getSession1().selectList("com.plgrim.ncp.biz.sys.shop.search.selectShopSearchForAvailableList", dto);
	}
    
    /**
     * 매장찾기 조회
     *
     * @param ShopSearchDTO [설명]
     * @return List [설명]
     * @throws Exception the exception
     * @since 2015. 5. 6
     */
    public SearchShopResult selectSearchShopInfo(ShopSearchDTO dto) {
    	return getSession1().selectOne("com.plgrim.ncp.biz.sys.shop.search.selectSearchShopInfo", dto);
    }
    
    /**
	 * 모바일 매장찾기 조회
	 *
	 * @param ShopSearchDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 6
	 */
    public List<SearchShopResult> getMobileShopSearchList(ShopSearchDTO dto, PageParam pageParam) {
    	RepositoryHelper.makePageEntityIndex(dto, pageParam);
		return getSession1().selectList("com.plgrim.ncp.biz.sys.shop.search.selectShopSearchList", dto);
	}
    
    
    /**
	 * 매장찾기 건수
	 *
	 * @param ShopSearchDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 6
	 */
    public SearchShopResult getShopSearchCount(ShopSearchDTO dto) {
		return getSession1().selectOne("com.plgrim.ncp.biz.sys.shop.search.selectShopSearchCount", dto);
	}
    
    /**
	 * 매장찾기 건수
	 *
	 * @param ShopSearchDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 6
	 */
    public SearchShopResult getShopSearchForAvailCount(ShopSearchDTO dto) {
		return getSession1().selectOne("com.plgrim.ncp.biz.sys.shop.search.selectShopSearchForAvailCount", dto);
	}
    
    /**
	 * 매장찾기 이벤트 조회
	 *
	 * @param ShopSearchDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 6
	 */
    public List<SearchShopResult> getShopSearchEvent(ShopSearchDTO dto) {
		return getSession1().selectList("com.plgrim.ncp.biz.sys.shop.search.selectShopSearchEvent", dto);
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
		RepositoryHelper.makePageEntityIndex(dto, dto.getPageParam());
		return getSession1().selectList("com.plgrim.ncp.biz.sys.shop.search.selectNearShopSearchList", dto);
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
		RepositoryHelper.makePageEntityIndex(dto, dto.getPageParam());
		return getSession1().selectList("com.plgrim.ncp.biz.sys.shop.search.selectNearShopSearchForAvailList", dto);
	}

	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */

}
