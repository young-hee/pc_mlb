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

import java.util.List;

import com.plgrim.ncp.base.entities.datasource1.bsk.BskGod;
import com.plgrim.ncp.base.entities.datasource1.com.ComQdlvGudTxtExtend;
import com.plgrim.ncp.biz.cart.data.CartOptChgSearchDTO;
import com.plgrim.ncp.biz.cart.data.CartSearchDTO;
import com.plgrim.ncp.biz.cart.data.GodItmQtySearchDTO;
import com.plgrim.ncp.biz.cart.result.CartCpstGodResult;
import com.plgrim.ncp.biz.cart.result.CartGodOptionResult;
import com.plgrim.ncp.biz.cart.result.CartGodPrmResult;
import com.plgrim.ncp.biz.cart.result.CartListResult;
import com.plgrim.ncp.biz.cart.result.CartOptChgGodResult;
import com.plgrim.ncp.biz.cart.result.CartOrderCheckResult;
import com.plgrim.ncp.biz.cart.result.CartResult;
import com.plgrim.ncp.biz.cart.result.GodInfoResult;
import com.plgrim.ncp.biz.cart.result.GodItmQtyResult;
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
public interface CartSelectComponent {

	/**
	 * [장바구니 조회].
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param systemPK [설명]
	 * @param cartSearchDTO [설명]
	 * @return Cart list result [설명]
	 * @throws Exception the exception
	 * @since 2015. 6. 19
	 */
	CartListResult selectCartList(SystemPK systemPK,CartSearchDTO cartSearchDTO) throws Exception;
	
	
	/**
	 * [상품 옵션 조회].
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param systemPK [설명]
	 * @param bskGod [설명]
	 * @return Cart god option result [설명]
	 * @throws Exception the exception
	 * @since 2015. 6. 19
	 */
	CartGodOptionResult selectGodOptionList(SystemPK systemPK,BskGod bskGod) throws Exception;
	
	/**
	 * [패키지형 상품 옵션 조회].
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param systemPK [설명]
	 * @param god [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 6. 19
	 */
	List<CartGodOptionResult> selectPckageGodOptionList(SystemPK systemPK,BskGod god) throws Exception;

	/**
	 * 픽업 패키지형 상품 옵션 조회
	 * @param systemPK
	 * @param bskGod
	 * @return
	 * @throws Exception
	 */
	List<CartOptChgGodResult> selectPckageGodOptionListPickup(SystemPK systemPK, CartOptChgSearchDTO search) throws Exception;
	
	
	
	
	/**
	 * 주문상품 유효성 검증
	 *
	 * @param systemPK [설명]
	 * @param cartSearchDTO [설명]
	 * @return the cart order check result
	 * @throws Exception the exception
	 */
	CartOrderCheckResult isValidOrderGod(SystemPK systemPK,CartSearchDTO cartSearchDTO) throws Exception;

	/**
	 * 장바구니 상품 정보 조회
	 * 
	 * @param systemPK
	 * @param cartSearchDTO
	 * @return
	 * @throws Exception
	 */
	List<CartResult> selectInvalidOrderGod(SystemPK systemPK,CartSearchDTO cartSearchDTO) throws Exception;


	/**
	 * 재고알림 정보 조회
	 *
	 * @param systemPK [설명]
	 * @param bskGod [설명]
	 * @return God info result [설명]
	 * @throws Exception the exception
	 * @since 2015. 6. 19
	 */
	GodInfoResult  selectInvAlrm(SystemPK systemPK, BskGod bskGod) throws Exception;


	/**
	 * 픽업상품 리스트 조회
	 *
	 * @param systemPK [설명]
	 * @param cartSearchDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 6. 19
	 */
	List<CartResult> selectPkupShopInfo(SystemPK systemPK,CartSearchDTO cartSearchDTO) throws Exception;
	
	/**
	 * 회원 장바구니 수량 조회
	 *
	 * @param systemPK [설명]
	 * @return Int [설명]
	 * @throws Exception the exception
	 * @since 2015. 6. 19
	 */
	int selectMbrCartCnt(SystemPK systemPK) throws Exception;
	
	/**
	 * [상품 사은품 조회].
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param systemPK [설명]
	 * @return Int [설명]
	 * @throws Exception the exception
	 * @since 2015. 6. 19
	 */
	CartGodPrmResult selectGodGft(String prmNo, String godNo) throws Exception;

	/**
	 * 카트구성상품정보조회on카트수량갱신
	 * 
	 * @param god
	 * @return
	 * @throws Exception
	 */
	List<CartCpstGodResult> selectBskCpstGodCnnc(BskGod god) throws Exception;

	/**
	 * 단품재고 수량조회
	 * 
	 * @param godItmQtySearchDTO
	 * @return
	 * @throws Exception
	 */
	List<GodItmQtyResult> selectGodItmQty(GodItmQtySearchDTO godItmQtySearchDTO) throws Exception;
	
	/**
	 * 퀵배송 단품재고 수량조회
	 * 
	 * @param godItmQtySearchDTO
	 * @return
	 * @throws Exception
	 */
	List<GodItmQtyResult> selectGodItmQtyForQdlv(GodItmQtySearchDTO godItmQtySearchDTO) throws Exception;

	/**
	 * 퀵배송 가능 시간 안내문구 조회
	 * 
	 * @param comQdlvGudTxtExtend
	 * @return
	 */
    ComQdlvGudTxtExtend selectComQdlvGudTxt(ComQdlvGudTxtExtend comQdlvGudTxtExtend);
}