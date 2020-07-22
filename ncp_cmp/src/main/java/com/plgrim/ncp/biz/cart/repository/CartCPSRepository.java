/* Copyright (c) 2015 plgrim, Inc.
 * All right reserved.
 * http://plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 *
 * ------------------------------------------------------------------------
 * @author      mc009.park
 * @since       2015. 6. 20       
 */
package com.plgrim.ncp.biz.cart.repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.datasource1.bsk.Bsk;
import com.plgrim.ncp.base.entities.datasource1.sys.SysShop;
import com.plgrim.ncp.biz.cart.data.ShopSearchDTO;
import com.plgrim.ncp.biz.cart.result.SidoCdResult;
import com.plgrim.ncp.biz.goods.result.GoodsSearchFoResult;
import org.springframework.stereotype.Repository;

import java.util.List;



/**
 * [클래스 설명]
 *
 * <p>
 *
 * <ul>
 *   <li> [기능1]
 *   <li> [기능2]
 * </ul>.
 *
 * @author mc009.park
 * @since 2015. 6. 20
 */
@Repository
public class CartCPSRepository extends AbstractRepository {

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
	 * [메서드 설명].
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param bskNo [설명]
	 * @return Bsk [설명]
	 * @throws Exception the exception
	 * @since 2015. 6. 19
	 */
	public Bsk selectBskInfoByBskNo(String bskNo) throws Exception{

		return getSession1().selectOne("com.plgrim.ncp.biz.cart.cps.selectBskInfoByBskNo",bskNo);

	}

	/**
	 * [메서드 설명].
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param shopSearchDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 6. 19
	 */
	public List<SysShop> selectSidoShopList(ShopSearchDTO shopSearchDTO) throws Exception{
		return getSession1().selectList("com.plgrim.ncp.biz.cart.cps.selectSidoShopList",shopSearchDTO);
	}

	public List<SysShop> selectSidoShopListCart(ShopSearchDTO shopSearchDTO) throws Exception{
		return getSession1().selectList("com.plgrim.ncp.biz.cart.cps.selectSidoShopListCart",shopSearchDTO);
	}


	/**
	 * [메서드 설명].
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param shopSearchDTO [설명]
	 * @return Sys shop brnd [설명]
	 * @throws Exception the exception
	 * @since 2015. 6. 19
	 */
	public GoodsSearchFoResult selectShopInfo(ShopSearchDTO shopSearchDTO) throws Exception{
		return getSession1().selectOne("com.plgrim.ncp.biz.cart.cps.selectShopInfo",shopSearchDTO);
	}

	 /* ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */




}
