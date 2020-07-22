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

import java.util.List;

import com.plgrim.ncp.base.entities.datasource1.com.ComQdlvGudTxtExtend;
import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.datasource1.bsk.Bsk;
import com.plgrim.ncp.base.entities.datasource1.bsk.BskCpstGodCnnc;
import com.plgrim.ncp.base.entities.datasource1.bsk.BskGod;
import com.plgrim.ncp.base.entities.datasource1.bsk.BskGodExtend;
import com.plgrim.ncp.base.entities.datasource1.bsk.BskWishlst;
import com.plgrim.ncp.base.entities.datasource1.god.GodItmExtend;
import com.plgrim.ncp.base.entities.datasource1.sys.SysStplat;
import com.plgrim.ncp.biz.cart.data.CartDTO;
import com.plgrim.ncp.biz.cart.data.CartGodHistDTO;
import com.plgrim.ncp.biz.cart.data.CartOptChgSearchDTO;
import com.plgrim.ncp.biz.cart.data.CartSearchDTO;
import com.plgrim.ncp.biz.cart.data.GodItmQtySearchDTO;
import com.plgrim.ncp.biz.cart.result.CartBskNoForUpdateResult;
import com.plgrim.ncp.biz.cart.result.CartCpstGodResult;
import com.plgrim.ncp.biz.cart.result.CartGiftResult;
import com.plgrim.ncp.biz.cart.result.CartGodOptionResult;
import com.plgrim.ncp.biz.cart.result.CartGodPrmResult;
import com.plgrim.ncp.biz.cart.result.CartGodResult;
import com.plgrim.ncp.biz.cart.result.CartResult;
import com.plgrim.ncp.biz.cart.result.CartOptChgGodResult;
import com.plgrim.ncp.biz.cart.result.CartPrmResult;
import com.plgrim.ncp.biz.cart.result.GodItmQtyResult;

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

@Repository
public class CartSelectRepository extends AbstractRepository {

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
	 * @param bsk [설명]
	 * @return String [설명]
	 * @throws Exception the exception
	 * @since 2015. 6. 19
	 */
	public String getCartNo(Bsk bsk) throws Exception{
		return (String)getSession1().selectOne("com.plgrim.ncp.biz.cart.select.selectCartNo",bsk);
	}

	/**
	 * [메서드 설명].
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param bsk [설명]
	 * @return Bsk [설명]
	 * @throws Exception the exception
	 * @since 2015. 6. 19
	 */
	public Bsk selectBskInfo(Bsk bsk) throws Exception{
		return getSession1().selectOne("com.plgrim.ncp.biz.cart.select.selectBskInfo",bsk);
	}

	/**
	 * [메서드 설명].
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param bsk [설명]
	 * @return Bsk god [설명]
	 * @throws Exception the exception
	 * @since 2015. 6. 19
	 */
	public BskGod selectCartByMbrNo(Bsk bsk) throws Exception {
		return getSession1().selectOne("com.plgrim.ncp.biz.cart.select.selectCartByMbrNo", bsk);
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
	public int selectCartCount(String bskNo) throws Exception {
		return getSession1().selectOne("com.plgrim.ncp.biz.cart.select.selectCartCount", bskNo);
	}

	/**
	 * [메서드 설명].
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param bskGod [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 6. 19
	 */
	public List<CartCpstGodResult> selectBskCpstGodCnnc(BskGod bskGod) throws Exception {

		return getSession1().selectList("com.plgrim.ncp.biz.cart.select.selectBskCpstGodCnnc", bskGod);
	}
	/**
	 * 중복 주문 방지을 위해서 BSK 테이블에 bsk_no에 대하여 Lock 처리 - alan.
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param bskGod [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 11. 09
	 */
	public List<CartBskNoForUpdateResult> selectBskForUpdate(String mbrNo) throws Exception {

		return getSession1().selectList("com.plgrim.ncp.biz.cart.select.selectBskForUpdate", mbrNo);
	}
	/**
	 * [메서드 설명].
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param cartSearchDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 6. 19
	 */
	public List<CartGodResult> selectGnrlCartList(CartSearchDTO cartSearchDTO) throws Exception{

	    return getSession1().selectList("com.plgrim.ncp.biz.cart.select.selectGnrlCartList", cartSearchDTO);
    }

	/**
	 * [메서드 설명].
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param cartSearchDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 6. 19
	 */
	public List<CartGodResult> selectPkupCartList(CartSearchDTO cartSearchDTO) throws Exception{
		return getSession1().selectList("com.plgrim.ncp.biz.cart.select.selectPkupCartList", cartSearchDTO);
    }

	/**
	 * [메서드 설명].
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param cartSearchDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 6. 19
	 */
	public List<CartGodResult> selectBskGodOrder(CartSearchDTO cartSearchDTO) throws Exception{

	    return getSession1().selectList("com.plgrim.ncp.biz.cart.select.selectBskGodOrder", cartSearchDTO);
    }

	/**
	 * [메서드 설명].
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param cartSearchDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 6. 19
	 */
	public List<CartPrmResult> selectBskPrmAll(CartSearchDTO cartSearchDTO) throws Exception{

		return getSession1().selectList("com.plgrim.ncp.biz.cart.select.selectBskPrmAll", cartSearchDTO);
    }

	/**
	 * [메서드 설명].
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param cartSearchDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 6. 19
	 */
	public List<CartGodPrmResult> selectCartGodsGnlPrm(CartSearchDTO cartSearchDTO) throws Exception{

		return getSession1().selectList("com.plgrim.ncp.biz.cart.select.selectCartGodsGnlPrm", cartSearchDTO);
    }

	public CartGodPrmResult selectGodGiftSingle(CartSearchDTO cartSearchDTO) throws Exception{

		return getSession1().selectOne("com.plgrim.ncp.biz.cart.select.selectGodGift", cartSearchDTO);
    }

	/**
	 * [메서드 설명].
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param cartSearchDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 6. 19
	 */
	public List<CartGodPrmResult> selectCartGodsBundlePrm(CartSearchDTO cartSearchDTO) throws Exception{

		return getSession1().selectList("com.plgrim.ncp.biz.cart.select.selectCartGodsBundlePrm", cartSearchDTO);
    }

	/**
	 * [온라인 판매 상품 재고 조회].
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param god [설명]
	 * @return Int [설명]
	 * @throws Exception the exception
	 * @since 2015. 4. 24
	 */
	public int selectOnlineGodInvCount(BskGod god) throws Exception{

		return getSession1().selectOne("com.plgrim.ncp.biz.cart.select.selectOnlineGodInvCount", god);
	}

	/**
	 * [매장 판매 상품 재고 조회].
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param god [설명]
	 * @return Int [설명]
	 * @throws Exception the exception
	 * @since 2015. 4. 24
	 */
	public int selectShopGodInvCount(BskGod god) throws Exception{

		return getSession1().selectOne("com.plgrim.ncp.biz.cart.select.selectShopGodInvCount", god);
	}

	/**
	 * [장바구니 상품 즉시 할인 쿠폰 조회].
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param cartSearchDTO [설명]
	 * @return List [설명]
	 * @since 2015. 4. 23
	 */
	public List<CartGodPrmResult> selectImdtlApplcCpn(CartSearchDTO cartSearchDTO) throws Exception{

		return getSession1().selectList("com.plgrim.ncp.biz.cart.select.selectImdtlApplcCpn", cartSearchDTO);
    }


	/**
	 * [메서드 설명].
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param god [설명]
	 * @return Cart god option result [설명]
	 * @throws Exception the exception
	 * @since 2015. 6. 19
	 */
	public CartGodOptionResult selectGodOptionList(BskGod god) throws Exception{

		return getSession1().selectOne("com.plgrim.ncp.biz.cart.select.selectGodOptionList", god);
	}

	/**
	 * [메서드 설명].
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param god [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 6. 19
	 */
	public List<CartGodOptionResult> selectPckageGodOptionList(BskGod god) throws Exception{

		return getSession1().selectList("com.plgrim.ncp.biz.cart.select.selectPckageGodOptionList", god);
    }

	public List<GodItmQtyResult> selectPckageGodOptionListPickup(BskGod god) throws Exception{

		return getSession1().selectList("com.plgrim.ncp.biz.cart.select.selectPckageGodOptionListPickup", god);
    }

	/**
	 * [메서드 설명].
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param cartDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 6. 19
	 */
	public List<BskGodExtend> selectBskPckageInfo(CartDTO cartDTO) throws Exception{
		return getSession1().selectList("com.plgrim.ncp.biz.cart.select.selectBskPckageInfo", cartDTO);
    }

	/**
	 * [메서드 설명].
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param orderDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 6. 19
	 */
	public List<BskGodExtend> selectBskGodList(CartSearchDTO orderDTO) throws Exception{
		return getSession1().selectList("com.plgrim.ncp.biz.cart.select.selectBskGodList", orderDTO);
    }

	/**
	 * [메서드 설명].
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param cartSearchDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 6. 19
	 */
	public List<BskGodExtend> selectBskMergeTarget(CartSearchDTO cartSearchDTO) throws Exception{

		return getSession1().selectList("com.plgrim.ncp.biz.cart.select.selectBskMergeTarget", cartSearchDTO);
    }

	/**
	 * [메서드 설명].
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param bsk [설명]
	 * @return Bsk [설명]
	 * @throws Exception the exception
	 * @since 2015. 6. 19
	 */
	public Bsk selectBskInfoBySession(Bsk bsk) throws Exception{
		return getSession1().selectOne("com.plgrim.ncp.biz.cart.select.selectBskInfoBySession", bsk);
    }

	/**
	 * [메서드 설명].
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param cartSearchDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 6. 19
	 */
	public List<BskGodExtend> selectCartTypeCount(CartSearchDTO cartSearchDTO) throws Exception{
		return getSession1().selectList("com.plgrim.ncp.biz.cart.select.selectCartTypeCount", cartSearchDTO);
    }

	/**
	 * [메서드 설명].
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param cartSearchDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 6. 19
	 */
	public List<CartGodPrmResult> selectCrsPrm(CartSearchDTO cartSearchDTO) throws Exception{
		return getSession1().selectList("com.plgrim.ncp.biz.cart.select.selectCartCrsPrm", cartSearchDTO);
    }

	/**
	 * [메서드 설명].
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param cartSearchDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 6. 19
	 */
	public List<CartGodPrmResult> selectGodGift(CartSearchDTO cartSearchDTO) throws Exception{
		return getSession1().selectList("com.plgrim.ncp.biz.cart.select.selectGodGift", cartSearchDTO);
    }

	/**
	 * [메서드 설명].
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param cartSearchDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 6. 19
	 */
	public List<CartGiftResult> selectOrdGift(CartSearchDTO cartSearchDTO) throws Exception{
		return getSession1().selectList("com.plgrim.ncp.biz.cart.select.selectOrdGift", cartSearchDTO);
    }


	/**
	 * [메서드 설명].
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param god [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 6. 19
	 */
	public List<BskGod> selectBskGodListByMastInfo(BskGod god) throws Exception{
		return getSession1().selectList("com.plgrim.ncp.biz.cart.select.selectBskGodListByMastInfo", god);
    }

	/**
	 * [메서드 설명].
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param god [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 6. 19
	 */
	public List<BskGodExtend> selectBskGodItm(BskGod god) throws Exception{

	    return getSession1().selectList("com.plgrim.ncp.biz.cart.select.selectBskGodItm", god);
    }

	/**
	 * [메서드 설명].
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param stplatUseTpCd [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 6. 19
	 */
	public List<SysStplat> selectStplatList(String stplatUseTpCd) throws Exception{

		return getSession1().selectList("com.plgrim.ncp.biz.cart.select.selectStplatList", stplatUseTpCd);
	}

	/**
	 * [메서드 설명].
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param cartSearchDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 6. 19
	 */
	public List<CartGodResult> selectBskListByGods(CartSearchDTO cartSearchDTO) throws Exception{

		return getSession1().selectList("com.plgrim.ncp.biz.cart.select.selectBskListByGods", cartSearchDTO);
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
	 * @throws Exception the exception
	 * @since 2015. 6. 19
	 */
	public int selectMbrCartCnt(CartSearchDTO cartSearchDTO) throws Exception{
	    return getSession1().selectOne("com.plgrim.ncp.biz.cart.select.selectMbrCartCnt", cartSearchDTO);
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
	 * @throws Exception the exception
	 * @since 2015. 6. 19
	 */
	public BskWishlst selectBskWishlst(BskWishlst wish) throws Exception{
		return getSession1().selectOne("com.plgrim.ncp.biz.cart.select.selectBskWishlst", wish);
    }


	/**
	 * [예약 상품 재고 조회].
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param BskGod [설명]
	 * @return Int [설명]
	 * @throws Exception the exception
	 * @since 2015. 6. 19
	 */
	public int selectResvGodInvCount(BskGod god) throws Exception{
		return getSession1().selectOne("com.plgrim.ncp.biz.cart.select.selectResvGodInvCount", god);
    }

	public List<BskGodExtend> selectPckageOptMergeTarget(BskGod search) throws Exception{
		return getSession1().selectList("com.plgrim.ncp.biz.cart.select.selectPckageOptMergeTarget", search);
    }

	public List<BskGodExtend> selectGnrlOptMergeTarget(BskGod search) throws Exception{
		return getSession1().selectList("com.plgrim.ncp.biz.cart.select.selectGnrlOptMergeTarget", search);
    }

	public List<BskGodExtend> selectMergedTarget(BskGod bskGod) throws Exception{
		return getSession1().selectList("com.plgrim.ncp.biz.cart.select.selectMergedTarget", bskGod);
    }

	public List<Bsk> selectCleanTargetBsk(Bsk bsk) throws Exception{
		return getSession1().selectList("com.plgrim.ncp.biz.cart.select.selectCleanTargetBsk", bsk);
    }


	// 장바구니 데이터 적재 by cannon (2016.06.16)
	public int selectCartGodItmCount(BskGod god) throws Exception{
		return getSession1().selectOne("com.plgrim.ncp.biz.cart.select.selectCartGodItmCount", god);
    }

	// 장바구니 데이터 적재 by cannon (2016.06.16)
	public List<CartGodHistDTO> selectCartGodHistTgt(BskGod search) throws Exception{
		return getSession1().selectList("com.plgrim.ncp.biz.cart.select.selectCartGodHistTgt", search);
	}








	public List<BskGodExtend> selectBskGodListByBskNo(String bskNo) {
		return getSession1().selectList("com.plgrim.ncp.biz.cart.select.selectBskGodListByBskNo", bskNo);
	}

	public List<BskCpstGodCnnc> selectBskCpstGodListByBskNo(String bskNo) {
		return getSession1().selectList("com.plgrim.ncp.biz.cart.select.selectBskCpstGodListByBskNo", bskNo);
	}



	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */

	public List<GodItmQtyResult> selectGodItmQty(GodItmQtySearchDTO search){
		return getSession1().selectList("com.plgrim.ncp.biz.cart.select.selectGodItmQty", search);
	}

	public List<BskGodExtend> selectBskGodItmStatList(BskGod bskGod){
		return getSession1().selectList("com.plgrim.ncp.biz.cart.select.selectBskGodItmStatList", bskGod);
	}

	public List<CartOptChgGodResult> selectOptChgCpstList(CartOptChgSearchDTO search) {
		return getSession1().selectList("com.plgrim.ncp.biz.cart.select.selectOptChgCpstList", search);
	}

	public int selectShopGodInvPkupCount(BskGod god) {
		return getSession1().selectOne("com.plgrim.ncp.biz.cart.select.selectShopGodInvPkupCount", god);
	}
	

	public int selectPkupShopYn(CartSearchDTO cartSearchDTO) {
		return getSession1().selectOne("com.plgrim.ncp.biz.cart.select.selectPkupShopYn", cartSearchDTO);
	}

	/**
	 * 퀵배송 재고매트릭스
	 * @param godItmQtySearchDTO
	 * @return
	 */
	public List<GodItmQtyResult> selectGodItmQtyForQdlv(GodItmQtySearchDTO godItmQtySearchDTO) {
		return getSession1().selectList("com.plgrim.ncp.biz.cart.select.selectGodItmQtyForQdlv", godItmQtySearchDTO);
	}
	
	public int selectShopGodInvQdlvCount(BskGod god) {
		return getSession1().selectOne("com.plgrim.ncp.biz.cart.select.selectShopGodInvQdlvCount", god);
	}
	
	
	public CartGodOptionResult selectGodOptionListForQdlv(BskGod god){

		return getSession1().selectOne("com.plgrim.ncp.biz.cart.select.selectGodOptionListForQdlv", god);
	}

	public ComQdlvGudTxtExtend selectComQdlvGudTxt(ComQdlvGudTxtExtend comQdlvGudTxtExtend) {
		return getSession1().selectOne("com.plgrim.ncp.biz.cart.select.selectComQdlvGudTxt", comQdlvGudTxtExtend);
	}
	
	public Integer selectEqualCartGoodByNotPackageGodTurn(BskGod god){
		return getSession1().selectOne("com.plgrim.ncp.biz.cart.select.selectEqualCartGoodByNotPackageGodTurn", god);
	}
	
	public String selectResvGodYn(String godNo){
		return getSession1().selectOne("com.plgrim.ncp.biz.cart.select.selectResvGodYn", godNo);
	}
	
}
