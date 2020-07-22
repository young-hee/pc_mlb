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
 * @since       2015. 6. 19
 */
package com.plgrim.ncp.cmp.orderfulfilment.fo.cart;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.plgrim.ncp.biz.cart.data.CartDTO;
import com.plgrim.ncp.biz.cart.data.CartGodOptionDTO;
import com.plgrim.ncp.biz.cart.data.CartSearchDTO;
import com.plgrim.ncp.biz.cart.data.GodReWhsgNtcnDTO;
import com.plgrim.ncp.biz.cart.data.ReOrderCartDTO;
import com.plgrim.ncp.biz.cart.result.CartSimpleListResult;
import com.plgrim.ncp.framework.data.SystemPK;

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
 * @since 2015. 6. 19
 */
public interface CartCommandComponent {


	/**
	 * [장바구니 추가].
	 *
	 * <p/>
	 * @param pk [설명]
	 * @param cartDTO [설명]
	 * @return the list< cart simple list result>
	 * @throws Exception the exception
	 * @since 2015. 6. 19
	 */
	Map addCart(SystemPK pk, CartDTO cartDTO) throws Exception;


	
	/**
	 * [상품 수량 변경].
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param pk [설명]
	 * @param cartDTO [설명]
	 * @throws Exception the exception
	 * @since 2015. 6. 19
	 */
	HashMap<String,Integer> updateGoodsQty(SystemPK pk,CartDTO cartDTO) throws Exception;

	/**
	 * [장바구니 상품 삭제].
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param systemPK [설명]
	 * @param cartSearchDTO [설명]
	 * @throws Exception the exception
	 * @since 2015. 6. 19
	 */
	void deleteCartGoods(SystemPK systemPK,CartSearchDTO cartSearchDTO) throws Exception;

	/**
	 * [위시리스트 등록].
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param pk [설명]
	 * @param cartSearchDTO [설명]
	 * @throws Exception the exception
	 * @since 2015. 6. 19
	 */
	 int moveToWishList(SystemPK pk,CartSearchDTO cartSearchDTO) throws Exception;

	/**
	 * [장바구니 병합].
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param systemPK [설명]
	 * @throws Exception the exception
	 * @since 2015. 6. 19
	 */
	void mergeCart(SystemPK systemPK) throws Exception;

	/**
	 * [상품 옵션 변경].
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param systemPK [설명]
	 * @param cartSearchDTO [설명]
	 * @throws Exception the exception
	 * @since 2015. 6. 19
	 */
	boolean updateBskGodOption(SystemPK systemPK, CartSearchDTO cartSearchDTO) throws Exception;

	/**
	 * [상품 배송 유형 변경].
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param systemPK [설명]
	 * @param cartSearchDTO [설명]
	 * @throws Exception the exception
	 * @since 2015. 6. 19
	 */
	boolean updateCartDlv(SystemPK systemPK,CartSearchDTO cartSearchDTO) throws Exception;

	/**
	 * [재입고 알림 요청 등록].
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param systemPK [설명]
	 * @param godReWhsgNtcnDTO [설명]
	 * @throws Exception the exception
	 * @since 2015. 6. 19
	 */
	void insertInvAlrm(SystemPK systemPK,GodReWhsgNtcnDTO godReWhsgNtcnDTO) throws Exception;

	/**
	 * [픽업 매장 변경 ].
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param cartSearchDTO [설명]
	 * @throws Exception the exception
	 * @since 2015. 6. 19
	 */
	 void updatePkupShop(CartSearchDTO cartSearchDTO) throws Exception;

	/**
	 * [패키지형 상품 옵션 변경].
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param systemPK [설명]
	 * @param cartDTO [설명]
	 * @throws Exception the exception
	 * @since 2015. 6. 19
	 */
	boolean updateBskPckageGodOption(SystemPK systemPK,CartGodOptionDTO cartDTO) throws Exception;
	/**
	 * [ GNB 영역의 미니 카트 목록 조회 ]
	 * @param pk
	 * @param cartDTO
	 * @return
	 * @throws Exception
	 */
	public List<CartSimpleListResult> getMiniCartList(SystemPK pk, CartDTO cartDTO) throws Exception;

	/**
	 * 위시리스트 삭제
	 * 
	 * @param systemPK
	 * @param cartSearchDTO
	 * @throws Exception
	 */
	void deleteWishList(SystemPK systemPK, CartSearchDTO cartSearchDTO) throws Exception;
	
	
	public void addReOrderCartList(SystemPK pk, ReOrderCartDTO reOrderCartDTO);

}
