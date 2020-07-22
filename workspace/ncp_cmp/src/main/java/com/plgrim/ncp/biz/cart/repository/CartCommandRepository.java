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
package com.plgrim.ncp.biz.cart.repository;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.datasource1.bsk.Bsk;
import com.plgrim.ncp.base.entities.datasource1.bsk.BskCpstGodCnnc;
import com.plgrim.ncp.base.entities.datasource1.bsk.BskGod;
import com.plgrim.ncp.base.entities.datasource1.bsk.BskGodExtend;
import com.plgrim.ncp.base.entities.datasource1.bsk.BskWishlstGod;
import com.plgrim.ncp.base.entities.datasource1.god.GodReWhsgNtcn;
import com.plgrim.ncp.biz.cart.data.CartGodHistDTO;
import com.plgrim.ncp.biz.cart.data.CartSearchDTO;
import com.plgrim.ncp.biz.cart.data.WishListSearchDTO;

/**
 * [클래스 설명]
 * 장바구니 
 * <p>
 * 
 * <ul>
 * <li>[기능1]
 * <li>[기능2]
 * </ul>.
 *
 * @author syvictor.kim
 * @since 2015. 3. 23
 */

@Slf4j
@Repository
public class CartCommandRepository extends AbstractRepository {

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
	 * 장바구니 신규 등록
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param bsk [설명]
	 * @throws Exception the exception
	 * @since 2015. 4. 1
	 */
	public void insertCartMst(Bsk bsk) throws Exception{
		getSession1().insert("com.plgrim.ncp.biz.cart.command.insertBskMst",bsk);

    }

	/**
	 * [메서드 설명].
	 * 장바구니 상품 등록
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param bskGod [설명]
	 * @throws Exception the exception
	 * @since 2015. 4. 1
	 */
	public void insertCartGod(BskGod bskGod) throws Exception{
		getSession1().insert("com.plgrim.ncp.biz.cart.command.insertBskGod",bskGod);
		
    }

	/**
	 * [메서드 설명].
	 * 장바구니 구성상품 연결 등록
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 * 패키지형 상품만 등록한다.
	 * @param bskCpstGodCnnc [설명]
	 * @throws Exception the exception
	 * @since 2015. 4. 1
	 */
	public void insertCartCpstGod(BskCpstGodCnnc bskCpstGodCnnc) throws Exception{
		getSession1().insert("com.plgrim.ncp.biz.cart.command.insertCartCpstGod",bskCpstGodCnnc);
	    
    }

	/**
	 * [메서드 설명].
	 * 장바구니 상품 수량 변경
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param bskGod [설명]
	 * @return Int [설명]
	 * @throws Exception the exception
	 * @since 2015. 4. 1
	 */
	public int updateBskGodQty(BskGod bskGod) throws Exception {
		return getSession1().update("com.plgrim.ncp.biz.cart.command.updateBskGodQty", bskGod);
	}
	
	/**
	 * [메서드 설명].
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param bskGod [설명]
	 * @return Int [설명]
	 * @throws Exception the exception
	 * @since 2015. 6. 19
	 */
	public int deleteBskCpst(BskGod bskGod) throws Exception {
		return getSession1().delete("com.plgrim.ncp.biz.cart.command.deleteBskCpst", bskGod);
	}

	/**
	 * [메서드 설명].
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param bskGod [설명]
	 * @return Int [설명]
	 * @throws Exception the exception
	 * @since 2015. 6. 19
	 */
	public int deleteBskGod(BskGod bskGod) throws Exception {
		return getSession1().delete("com.plgrim.ncp.biz.cart.command.deleteBskGod", bskGod);
	}
	
	/**
	 * [메서드 설명].
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param bskNo [설명]
	 * @return Int [설명]
	 * @throws Exception the exception
	 * @since 2015. 6. 19
	 */
	public int deleteBskByBskNo(String bskNo) throws Exception {
		return getSession1().delete("com.plgrim.ncp.biz.cart.command.deleteBskByBskNo", bskNo);
	}
	
	/**
	 * [메서드 설명].
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param bskNo [설명]
	 * @return Int [설명]
	 * @throws Exception the exception
	 * @since 2015. 6. 19
	 */
	public int deleteBskGodByBskNo(String bskNo) throws Exception {
		return getSession1().delete("com.plgrim.ncp.biz.cart.command.deleteBskGodByBskNo", bskNo);
	}
	
	/**
	 * [메서드 설명].
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param bskNo [설명]
	 * @return Int [설명]
	 * @throws Exception the exception
	 * @since 2015. 6. 19
	 */
	public int deleteBskCpstGodByBskNo(String bskNo) throws Exception {
		return getSession1().delete("com.plgrim.ncp.biz.cart.command.deleteBskCpstGodByBskNo", bskNo);
	}
	
	/**
	 * [메서드 설명].
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param cartSearchDTO [설명]
	 * @return Int [설명]
	 * @since 2015. 6. 19
	 */
	public int mergeBskGod(CartSearchDTO cartSearchDTO) throws Exception{
		return getSession1().insert("com.plgrim.ncp.biz.cart.command.mergeBskGod", cartSearchDTO);
	}
	
	/**
	 * [메서드 설명].
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param cartSearchDTO [설명]
	 * @return Int [설명]
	 * @since 2015. 6. 19
	 */
	public int mergeBskCpstGodCnnc(CartSearchDTO cartSearchDTO) throws Exception{
		return getSession1().insert("com.plgrim.ncp.biz.cart.command.mergeBskCpstGodCnnc", cartSearchDTO);
	}
	
	/**
	 * [메서드 설명].
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param bsk [설명]
	 * @return Int [설명]
	 * @throws Exception the exception
	 * @since 2015. 6. 19
	 */
	public int deleteBskCpstBySessionId(Bsk bsk) throws Exception {
		return getSession1().delete("com.plgrim.ncp.biz.cart.command.deleteBskCpstBySessionId", bsk);
	}

	/**
	 * [메서드 설명].
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param bsk [설명]
	 * @return Int [설명]
	 * @throws Exception the exception
	 * @since 2015. 6. 19
	 */
	public int deleteBskGodBySessionId(Bsk bsk) throws Exception {
		return getSession1().delete("com.plgrim.ncp.biz.cart.command.deleteBskGodBySessionId", bsk);
	}
	
	/**
	 * [메서드 설명].
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param bsk [설명]
	 * @return Int [설명]
	 * @throws Exception the exception
	 * @since 2015. 6. 19
	 */
	public int deleteBskBySessionId(Bsk bsk) throws Exception {
		return getSession1().delete("com.plgrim.ncp.biz.cart.command.deleteBskBySessionId", bsk);
	}
	
	/**
	 * [메서드 설명].
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param bsk [설명]
	 * @return Int [설명]
	 * @throws Exception the exception
	 * @since 2015. 6. 19
	 */
	public int updateBskBySessionId(Bsk bsk) throws Exception {
		return getSession1().update("com.plgrim.ncp.biz.cart.command.updateBskBySessionId", bsk);
	}
	
	/**
	 * [메서드 설명].
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param bsk [설명]
	 * @return Int [설명]
	 * @throws Exception the exception
	 * @since 2015. 6. 19
	 */
	public int updateBskGodBySessionId(Bsk bsk) throws Exception {
		return getSession1().update("com.plgrim.ncp.biz.cart.command.updateBskGodBySessionId", bsk);
	}
	
	/**
	 * [메서드 설명].
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param bsk [설명]
	 * @return Int [설명]
	 * @throws Exception the exception
	 * @since 2015. 6. 19
	 */
	public int updateBskCpstGodBySessionId(Bsk bsk) throws Exception {
		return getSession1().update("com.plgrim.ncp.biz.cart.command.updateBskCpstGodBySessionId", bsk);
	}
	
	/**
	 * [메서드 설명].
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param bsk [설명]
	 * @return Int [설명]
	 * @throws Exception the exception
	 * @since 2015. 6. 19
	 */
	public int updateBskByMbrNo(Bsk bsk) throws Exception {
		return getSession1().update("com.plgrim.ncp.biz.cart.command.updateBskByMbrNo", bsk);
	}

	/**
	 * [메서드 설명].
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param bskGod [설명]
	 * @return Int [설명]
	 * @throws Exception the exception
	 * @since 2015. 6. 19
	 */
	public int updateBskGodOption(BskGod bskGod) throws Exception {
		
		return getSession1().update("com.plgrim.ncp.biz.cart.command.updateBskGodOption", bskGod);
    }
	
	/**
	 * [메서드 설명].
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param bskGod [설명]
	 * @return Int [설명]
	 * @throws Exception the exception
	 * @since 2015. 6. 19
	 */
	public int updateMbBskGodOption(BskGod bskGod) throws Exception {
		
		return getSession1().update("com.plgrim.ncp.biz.cart.command.updateMbBskGodOption", bskGod);
    }
	
	/**
	 * [메서드 설명].
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param god [설명]
	 * @throws Exception the exception
	 * @since 2015. 6. 19
	 */
	public void deleteBskCpstByGodTurn(BskGod god) throws Exception{
		
		getSession1().delete("com.plgrim.ncp.biz.cart.command.deleteBskCpstByGodTurn", god);
    }

	/**
	 * [메서드 설명].
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param god [설명]
	 * @throws Exception the exception
	 * @since 2015. 6. 19
	 */
	public void insertMergeCartGod(BskGod god) throws Exception{
		
		getSession1().update("com.plgrim.ncp.biz.cart.command.insertMergeCartGod", god);
    }

	/**
	 * [메서드 설명].
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param god [설명]
	 * @throws Exception the exception
	 * @since 2015. 6. 19
	 */
	public void updateItmQtyIncrease(BskGod god) throws Exception{
		getSession1().update("com.plgrim.ncp.biz.cart.command.updateItmQtyIncrease", god);
    }

	/**
	 * [메서드 설명].
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param cartSearchDTO [설명]
	 * @throws Exception the exception
	 * @since 2015. 6. 19
	 */
	public void updateCartDlv(CartSearchDTO cartSearchDTO) throws Exception{
		
		getSession1().update("com.plgrim.ncp.biz.cart.command.updateCartDlv", cartSearchDTO);
		
		getSession1().update("com.plgrim.ncp.biz.cart.command.updateCartDlvPckage", cartSearchDTO);
    }

	/**
	 * [메서드 설명].
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param wishDTO [설명]
	 * @throws Exception the exception
	 * @since 2015. 6. 19
	 */
	public int insertWishListGod(WishListSearchDTO wishDTO)  throws Exception{

		return getSession1().insert("com.plgrim.ncp.biz.cart.command.insertWishListGod", wishDTO);
    }

	/**
	 * [메서드 설명].
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param mbrNo [설명]
	 * @throws Exception the exception
	 * @since 2015. 6. 19
	 */
	public void deletDirtBskbyMbrNo(String mbrNo)  throws Exception{
		
		getSession1().delete("com.plgrim.ncp.biz.cart.command.deleteBskCpstGodByMbrNo", mbrNo);
		
		getSession1().delete("com.plgrim.ncp.biz.cart.command.deleteBskGodByMbrNo", mbrNo);
		
		getSession1().delete("com.plgrim.ncp.biz.cart.command.deleteBskByMbrNo", mbrNo);
	    
    }

	/**
	 * [메서드 설명].
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param rewhsg [설명]
	 * @throws Exception the exception
	 * @since 2015. 6. 19
	 */
	public void insertInvAlrm(GodReWhsgNtcn rewhsg) throws Exception{
		getSession1().insert("com.plgrim.ncp.biz.cart.command.insertInvAlrm", rewhsg);
    }

	/**
	 * [메서드 설명].
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param cartSearchDTO [설명]
	 * @throws Exception the exception
	 * @since 2015. 6. 19
	 */
	public void updatePkupShop(CartSearchDTO cartSearchDTO) throws Exception{
		getSession1().update("com.plgrim.ncp.biz.cart.command.updatePkupShop", cartSearchDTO);
    }

	public void deleteBskGodCnncByOptChange(CartSearchDTO delete) throws Exception{
		getSession1().update("com.plgrim.ncp.biz.cart.command.deleteBskGodCnncByOptChange", delete);
    }

	public void deleteBskGodByOptChange(CartSearchDTO delete) throws Exception{
		getSession1().update("com.plgrim.ncp.biz.cart.command.deleteBskGodByOptChange", delete);
	    
    }
	
	public void deleteBskGodMasterByOptChange(CartSearchDTO data) throws Exception{
		getSession1().delete("com.plgrim.ncp.biz.cart.command.deleteBskGodMasterByOptChange", data);
	    
    }

	public void updateGodItmQty(BskGodExtend data) throws Exception{
		getSession1().update("com.plgrim.ncp.biz.cart.command.updateGodItmQty", data);
    }

	public void updateSetGodItmQty(BskGodExtend data) throws Exception{
		getSession1().update("com.plgrim.ncp.biz.cart.command.updateSetGodItmQty", data);
    }

	public int mergeBskGodItmQty(BskGod bskGod) throws Exception{
		return getSession1().update("com.plgrim.ncp.biz.cart.command.mergeBskGodItmQty", bskGod);
    }

	public void deletDummyBsk(String bskNo)  throws Exception{
		
		getSession1().delete("com.plgrim.ncp.biz.cart.command.deleteBskCpstGodByBskNo", bskNo);
		
		getSession1().delete("com.plgrim.ncp.biz.cart.command.deleteBskGodByBskNo", bskNo);
		
		getSession1().delete("com.plgrim.ncp.biz.cart.command.deleteBskByBskNo", bskNo);
	    
    }

	public void deleteWishList(BskWishlstGod wishGod) throws Exception{
		getSession1().delete("com.plgrim.ncp.biz.cart.command.deleteWishList", wishGod);
    }
	
	// 장바구니 데이터 적재 by cannon (2016.06.16)
	public void insertCartBskGodHist(CartGodHistDTO cartGodHist) throws Exception{
		getSession1().insert("com.plgrim.ncp.biz.cart.command.insertCartBskGodHist", cartGodHist);
    }	


	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */


	public void updateCartCpstGod(BskCpstGodCnnc bskCpstGodCnnc) throws Exception{
		getSession1().insert("com.plgrim.ncp.biz.cart.command.updateCartCpstGod",bskCpstGodCnnc);
	    
    }

}
