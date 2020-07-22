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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.plgrim.ncp.base.abstracts.AbstractComponent;
import com.plgrim.ncp.base.entities.datasource1.bsk.Bsk;
import com.plgrim.ncp.base.entities.datasource1.bsk.BskGod;
import com.plgrim.ncp.base.entities.datasource1.bsk.BskGodExtend;
import com.plgrim.ncp.base.entities.datasource1.bsk.BskWishlst;
import com.plgrim.ncp.base.entities.datasource1.bsk.BskWishlstGod;
import com.plgrim.ncp.base.entities.datasource1.mbr.Mbr;
import com.plgrim.ncp.base.enums.BskEnum;
import com.plgrim.ncp.base.enums.BskEnum.BskTp;
import com.plgrim.ncp.base.enums.BskEnum.MbrSect;
import com.plgrim.ncp.base.enums.SessionEnum;
import com.plgrim.ncp.biz.cart.data.CartDTO;
import com.plgrim.ncp.biz.cart.data.CartGodDTO;
import com.plgrim.ncp.biz.cart.data.CartGodOptionDTO;
import com.plgrim.ncp.biz.cart.data.CartSearchDTO;
import com.plgrim.ncp.biz.cart.data.GodItmQtySearchDTO;
import com.plgrim.ncp.biz.cart.data.GodReWhsgNtcnDTO;
import com.plgrim.ncp.biz.cart.data.ReOrderCartDTO;
import com.plgrim.ncp.biz.cart.exception.CartFailException;
import com.plgrim.ncp.biz.cart.repository.CartSelectRepository;
import com.plgrim.ncp.biz.cart.result.CartCpstGodResult;
import com.plgrim.ncp.biz.cart.result.CartSimpleListResult;
import com.plgrim.ncp.biz.cart.result.GodItmQtyResult;
import com.plgrim.ncp.biz.cart.service.CartCommandService;
import com.plgrim.ncp.biz.cart.service.CartSelectService;
import com.plgrim.ncp.biz.member.data.MypageFoDTO;
import com.plgrim.ncp.biz.member.data.SecurityUserDetail;
import com.plgrim.ncp.biz.member.result.MypageWishFoResult;
import com.plgrim.ncp.biz.member.service.MemberActivitySelectService;
import com.plgrim.ncp.biz.order.repository.OrderSelectRepository;
import com.plgrim.ncp.commons.data.order.OrdGodReOrderDTO;
import com.plgrim.ncp.framework.commons.CollectionService;
import com.plgrim.ncp.framework.commons.ContextService;
import com.plgrim.ncp.framework.commons.StringService;
import com.plgrim.ncp.framework.data.SystemPK;
import com.plgrim.ncp.framework.exception.NotSupportedException;
import com.plgrim.ncp.framework.responsecode.common.CommonResponseCode;

import lombok.extern.slf4j.Slf4j;

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
@Slf4j
@Transactional(value = "transactionManager")
@Component
public class CartCommandComponentImpl extends AbstractComponent implements CartCommandComponent {

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	/** The cart command service. */
	@Autowired
	CartCommandService cartCommandService;

	/** The cart select service. */
	@Autowired
	CartSelectService cartSelectService;
	
	@Autowired
	MemberActivitySelectService  memberActivitySelectService;
	
	@Autowired
	CartSelectRepository cartSelectRepository;
	
	@Autowired
	OrderSelectRepository orderSelectRepository;
	
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
	/* (non-Javadoc)
	 * @see com.plgrim.ncp.cmp.orderfulfilment.fo.cart.CartCommandComponent#addCart(com.plgrim.ncp.framework.data.SystemPK, com.plgrim.ncp.biz.cart.data.CartDTO)
	 */
	@Override
	public Map addCart(SystemPK pk, CartDTO cartDTO) throws Exception {

	
		log.info(CommonResponseCode.OD_00013_장바구니_등록_시작.toMessage());
		//int invStatus = 0;
		HashMap<String,Integer> result = null;
		try {
			//1.상품 재고 체크
			if(!BskTp.REORDER.toString().equals(cartDTO.getBsk().getBskTpCd())){ // 가격변경 주문은 재고 체크 안함
				result = this.checkGodsInv(cartDTO);
				// 재고수량이 없는 경우 종료
				if(result.get("rtn") < 1){
					return result;
				}
			}
			

			
			Bsk bsk = cartDTO.getBsk();
			bsk.setMallId(pk.getMall());

			/*
			 * NCP 3차 추가
			 * 정책 > 언어별 장바구니 데이터 생성
			 * 카드마스터 조회 시 언어코드 추가
			 * 해외인 경우 장바구니 상품 배송수단코드를 구분하기 위해
			 * since 2016. 01. 20
			 */
			bsk.setLangCd(pk.getLang());
			cartDTO.setLang(pk.getLang());

			if(StringService.isEmpty(bsk.getBskTpCd())){
				bsk.setBskTpCd(BskEnum.BskTp.BSK.toString());
			}

			// 회원정보 입력
			if(ContextService.hasRole()){ // 회원
				SecurityUserDetail userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();
				Mbr mbr = userDetail.getMbr();
				bsk.setMbrSectCd(MbrSect.MBR.toString());
				bsk.setMbrNo(mbr.getMbrNo());
				bsk.setRegtrId(mbr.getMbrNo());
				bsk.setUdterId(mbr.getMbrNo());
			}
			else {
				Mbr mbr = new Mbr();
				mbr.setMbrTpCd(MbrSect.NMBR.toString());
				bsk.setMbrSectCd(MbrSect.NMBR.toString());
				bsk.setSesionId(ContextService.getCurrentRequest().getSession().getId());
				bsk.setRegtrId("GUEST");
				bsk.setUdterId("GUEST");
			}

			// 장바구니 정보 조회
			Bsk sBsk = cartSelectService.selectBskInfo(bsk);

			if(sBsk == null){
				//신규 장바구니 생성
				bsk.setBskNo(cartCommandService.insertCartMst(bsk));
			}else{
				if(StringService.equalsIgnoreCase(BskEnum.BskTp.DIRT.toString(), bsk.getBskTpCd())
						||StringService.equalsIgnoreCase(BskEnum.BskTp.REORDER.toString(), bsk.getBskTpCd())){
					cartCommandService.deleteDirtCart(sBsk.getBskNo());
					//신규 장바구니 생성
					bsk.setBskNo(cartCommandService.insertCartMst(bsk));
				}else{
					bsk.setBskNo(sBsk.getBskNo());
				}
			}

			cartDTO.setBsk(bsk);

			//3.장바구니 상품 등록
			BskGodExtend god = cartCommandService.insertCart(cartDTO);
			
			if(StringService.equalsIgnoreCase(BskEnum.BskTp.BSK.toString(), bsk.getBskTpCd())){
				if("Y".equals(cartDTO.getResveSaleGodYn())){
					ContextService.getCurrentRequest().getSession().setAttribute("SESSION_KEY_DEFAULT_CART", "RSV");
				}else{
					ContextService.getCurrentRequest().getSession().setAttribute("SESSION_KEY_DEFAULT_CART", cartDTO.getGod().getDlvSectCd());
				}
				
			}
			
			CartSearchDTO dto = new CartSearchDTO();

			if(StringService.equalsIgnoreCase(BskEnum.BskTp.DIRT.toString(), bsk.getBskTpCd())){
				dto.setDlvSectCd(cartDTO.getGod().getDlvSectCd());
				dto.setMallId(pk.getMall());
				dto.setDevice(pk.getDevice());
				dto.setLang(pk.getLang());
				dto.setBsk(bsk);
				List<BskGod> list = new ArrayList<BskGod>();
				list.add(god);
				dto.setBskGodList(list);
				dto.setNaverPayYn(cartDTO.getNaverPayYn());
				if("Y".equals(cartDTO.getResveSaleGodYn())){
					dto.setDlvSectCd("RSV");
				}
				ContextService.getCurrentRequest().getSession().setAttribute("SESSION_KEY_CART_GOODS", dto);
			}
			if(god.getGodTurn()!=null){
				result.put("newGodTurn", god.getNewGodTurn());
			}
			log.info(CommonResponseCode.OD_00014_장바구니_등록_성공.toMessage());
		}
		catch (Exception e) {
			log.error(CommonResponseCode.OD_40033_장바구니_등록_실패.toMessage()+" exception : {}",e);
			throw e;
		}
		
		
		return result;
	}

	/* (non-Javadoc)
	 * @see com.plgrim.ncp.cmp.orderfulfilment.fo.cart.CartCommandComponent#getMiniCartList(com.plgrim.ncp.framework.data.SystemPK, com.plgrim.ncp.biz.cart.data.CartDTO)
	 */
	public List<CartSimpleListResult> getMiniCartList(SystemPK pk, CartDTO cartDTO) throws Exception{

			Bsk bsk = cartDTO.getBsk();
			//2.장바구니 조회
			if(StringService.isEmpty(bsk.getBskTpCd())){
				bsk.setBskTpCd(BskEnum.BskTp.BSK.toString());
			}

			bsk.setMallId(pk.getMall());

			/*
			 * NCP 3차 추가
			 * 정책 > 언어별 장바구니 데이터 생성
			 * 카드마스터 조회 시 언어코드 추가
			 * 해외인 경우 장바구니 상품 배송수단코드를 구분하기 위해
			 * since 2016. 01. 20
			 */
			bsk.setLangCd(pk.getLang());
			cartDTO.setLang(pk.getLang());

			Mbr mbr = null;
			if(ContextService.hasRole()){ // 회원

				SecurityUserDetail userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();
				mbr = userDetail.getMbr();
				bsk.setMbrSectCd(MbrSect.MBR.toString());
				bsk.setMbrNo(mbr.getMbrNo());
			}
			else {
				mbr = new Mbr();
				mbr.setMbrTpCd(MbrSect.NMBR.toString());
				String sessionId = ContextService.getCurrentRequest().getSession().getId();
				bsk.setMbrSectCd(MbrSect.NMBR.toString());
				bsk.setSesionId(sessionId);
			}


			Bsk sBsk = cartSelectService.selectBskInfo(bsk);

			if(sBsk == null){
				return null;
			}

			bsk.setBskNo(sBsk.getBskNo());
			cartDTO.setBsk(bsk);

			CartSearchDTO dto = new CartSearchDTO();
			dto.setMbr(mbr);
			dto.setMallId(pk.getMall());
			dto.setLang(pk.getLang());
			dto.setDevice(pk.getDevice());
			dto.setBsk(bsk);
			return cartSelectService.selectBskListByGods(dto);

	}


	
	public HashMap<String,Integer> updateGoodsQty(SystemPK pk,CartDTO cartDTO) throws Exception {

		log.info(CommonResponseCode.OD_00015_장바구니_수량변경_시작.toMessage());
		log.debug("cartDTO reserve {}", cartDTO.getResveSaleGodYn() );
		
		HashMap<String,Integer> result = null;
		
		try {
			if(StringService.equalsIgnoreCase(BskEnum.YES.toString(), cartDTO.getGod().getPckageGodYn())
					   && CollectionService.isEmpty(cartDTO.getCpstGodList())
					){
						List<CartCpstGodResult> resultList = cartSelectService.selectBskCpstGodCnnc(cartDTO.getGod());

						List<CartGodDTO> cpstGodList = new ArrayList<CartGodDTO>();

						for(CartCpstGodResult list : resultList){
							CartGodDTO god = new CartGodDTO();
							god.setGodTurn(list.getBskCpstGodCnnc().getGodTurn());
							god.setCpstGodTurn(list.getBskCpstGodCnnc().getCpstGodTurn());
							god.setGodNo(list.getGodNo());
							god.setItmNo(list.getItmNo());
							god.setPckageGodTpCd(list.getBskCpstGodCnnc().getPckageGodTpCd());
							god.setCpstGodQty(list.getBskCpstGodCnnc().getCpstGodQty());
							god.setSortSeq(list.getBskCpstGodCnnc().getSortSeq());
							cpstGodList.add(god);
						}

						cartDTO.setCpstGodList(cpstGodList);
					}

					//상품 재고 체크
					result = this.checkGodsInv(cartDTO);

					if(result.get("rtn") < 1){
						return result;
					}

					//2 상품수량 변경
					 cartCommandService.updateGoodsQty(cartDTO, this.getMemberGuestId());
					 log.info(CommonResponseCode.OD_00016_장바구니_수량변경_성공.toMessage());
		}
		catch (Exception e) {
			log.error(CommonResponseCode.OD_40034_장바구니_수량변경_실패.toMessage()+" exception : {}", e);
			throw e;
		}

		return result;
		
	}
	

	/* (non-Javadoc)
	 * @see com.plgrim.ncp.cmp.orderfulfilment.fo.cart.CartCommandComponent#deleteCartGoods(com.plgrim.ncp.framework.data.SystemPK, com.plgrim.ncp.biz.cart.data.CartSearchDTO)
	 */
	public void deleteCartGoods(SystemPK systemPK,CartSearchDTO cartSearchDTO) throws Exception {

		cartCommandService.deleteCartGoods(cartSearchDTO);
	}

	/* (non-Javadoc)
	 * @see com.plgrim.ncp.cmp.orderfulfilment.fo.cart.CartCommandComponent#moveToWishList(com.plgrim.ncp.framework.data.SystemPK, com.plgrim.ncp.biz.cart.data.CartSearchDTO)
	 */
	public int moveToWishList(SystemPK pk,CartSearchDTO cartSearchDTO) throws Exception {

		Mbr mbr = ((SecurityUserDetail) ContextService.getCurrentUserDetail()).getMbr();
 
		cartSearchDTO.getBsk().setMbrNo(mbr.getMbrNo());
		cartSearchDTO.getBsk().setMallId(pk.getMall());

		BskWishlst wish = new BskWishlst();
		wish.setMbrNo(mbr.getMbrNo());
		wish.setMallId(pk.getMall());
		wish.setLangCd(pk.getLang());
		wish.setRegtrId(mbr.getMbrNo());
		wish.setUdterId(mbr.getMbrNo());
		BskWishlst wishList = cartSelectService.selectWishList(wish);

		if(wishList == null){
			wish = cartCommandService.insertWishList(wish);
		}else{
			wish.setWishlstSn(wishList.getWishlstSn());
		}
		cartSearchDTO.setWishlstSn(wish.getWishlstSn());
		int count = cartCommandService.insertWishListGod(wish,cartSearchDTO.getBskGodList());
		MypageFoDTO dto = new MypageFoDTO();
		dto.setMbrNo(mbr.getMbrNo());
		dto.setGodNo(cartSearchDTO.getGodNo());
		dto.setWishlstSn(String.valueOf(wish.getWishlstSn()));
		MypageWishFoResult mypageWishFoResult = memberActivitySelectService.selectGodWishList(dto);	
		cartSearchDTO.setGodTurn(mypageWishFoResult.getGodTurn());
 
		return count;

	}

	/* (non-Javadoc)
	 * @see com.plgrim.ncp.cmp.orderfulfilment.fo.cart.CartCommandComponent#mergeCart(com.plgrim.ncp.framework.data.SystemPK)
	 */
	public void mergeCart(SystemPK systemPK) throws Exception {

		Mbr mbr = ((SecurityUserDetail) ContextService.getCurrentUserDetail()).getMbr();
		String prvSessionId = (String) ContextService.getCurrentRequest().getSession().getAttribute(SessionEnum.SESSION_ID.toString());

		if(log.isInfoEnabled()){
			log.info("##################### Cart Merge################################");
			log.info("Previous Session Id " + prvSessionId);
			log.info("Current Session Id " + ContextService.getCurrentRequest().getSession().getId());
			log.info("################################################################");
		}

		Bsk bsk = new Bsk();
		bsk.setMbrNo(mbr.getMbrNo());
		bsk.setSesionId(prvSessionId);
		bsk.setMallId(systemPK.getMall());

//		/*
//		 * NCP 3차 추가
//		 * 정책 > 언어별 장바구니 데이터 생성
//		 * 카드마스터 조회 시 언어코드 추가
// 	     * since 2016. 01. 20
//		 */
		bsk.setLangCd(systemPK.getLang());

		bsk.setBskTpCd(BskEnum.BskTp.BSK.toString());
		bsk.setRegtrId(mbr.getMbrNo());
		bsk.setUdterId(mbr.getMbrNo());

		cartCommandService.cleanBsk(bsk);

		boolean mergeChked = false;

		if(ContextService.getCurrentRequest().getSession().getAttribute("SESSION_KEY_CART_GOODS") != null){
			CartSearchDTO cartSearchDTO = (CartSearchDTO) ContextService.getCurrentRequest().getSession().getAttribute("SESSION_KEY_CART_GOODS");

			if(!StringService.equalsIgnoreCase(BskEnum.BskTp.DIRT.toString(), cartSearchDTO.getBsk().getBskTpCd())){
				if(StringService.equalsIgnoreCase(systemPK.getMall(), cartSearchDTO.getMallId())){
					cartSearchDTO.setBsk(bsk);
					CartSearchDTO orderDTO = cartCommandService.mergeCartOrder(bsk,cartSearchDTO);
					ContextService.getCurrentRequest().getSession().setAttribute("SESSION_KEY_CART_GOODS", orderDTO);
					mergeChked = true;
				}else{
					ContextService.getCurrentRequest().getSession().removeAttribute("SESSION_KEY_CART_GOODS");
				}
			}else{
				cartCommandService.deletDirtBskbyMbrNo(mbr.getMbrNo());
				cartCommandService.updateBskMaster(bsk);
			}
		}

		if(mergeChked==false){
			cartCommandService.mergeCart(bsk);
		}

	}

	/* (non-Javadoc)
	 * @see com.plgrim.ncp.cmp.orderfulfilment.fo.cart.CartCommandComponent#updateBskGodOption(com.plgrim.ncp.framework.data.SystemPK, java.util.List)
	 */
	@Override
    public boolean updateBskGodOption(SystemPK systemPK, CartSearchDTO cartSearchDTO) throws Exception {

		
		log.info(CommonResponseCode.OD_00017_장바구니_옵션변경_시작.toMessage());
		
		if(cartSearchDTO ==  null || CollectionService.isEmpty(cartSearchDTO.getBskGodList())){
			log.error(CommonResponseCode.OD_40035_장바구니_옵션변경_실패.toMessage()+" / cartSearchDTO 가 비어있음");
			throw new Exception("");
		}


		String udterId = this.getMemberGuestId();
		for(BskGod bskGod : cartSearchDTO.getBskGodList()){
			int invCnt = cartSelectService.selectGodInvCount(bskGod);

			if(invCnt < bskGod.getItmQty()){
				log.error(CommonResponseCode.OD_40035_장바구니_옵션변경_실패.toMessage()+" / 재고보다 많은 수량으로 변경 시도");
				return false;
			}

			bskGod.setUdterId(udterId);
		}

		
		cartCommandService.updateBskGodOption(cartSearchDTO);
		
		
		log.info(CommonResponseCode.OD_00018_장바구니_옵션변경_성공.toMessage());

		return true;
    }


	/* (non-Javadoc)
	 * @see com.plgrim.ncp.cmp.orderfulfilment.fo.cart.CartCommandComponent#updateBskPckageGodOption(com.plgrim.ncp.framework.data.SystemPK, com.plgrim.ncp.biz.cart.data.CartGodOptionDTO)
	 */
	public boolean updateBskPckageGodOption(SystemPK systemPK,CartGodOptionDTO cartDTO) throws Exception {

		log.info(CommonResponseCode.OD_00017_장바구니_옵션변경_시작.toMessage());
		
		if(CollectionService.isEmpty(cartDTO.getBskGodList())){
			log.error(CommonResponseCode.OD_40035_장바구니_옵션변경_실패.toMessage()+" / cartSearchDTO 가 비어있음");
			throw new Exception("");
		}

		int itmQty = cartDTO.getItmQty();

		for(BskGod bskGod : cartDTO.getBskGodList()){

			int invCnt = cartSelectService.selectGodInvCount(bskGod);

			if(invCnt < (itmQty)){
				log.error(CommonResponseCode.OD_40035_장바구니_옵션변경_실패.toMessage()+" / 재고보다 많은 수량으로 변경 시도");
				return false;
			}
		}

		cartDTO.setUdterId(this.getMemberGuestId());
		cartCommandService.updateBskPckageGodOption(cartDTO);
		
		log.info(CommonResponseCode.OD_00018_장바구니_옵션변경_성공.toMessage());

		return true;
    }

	/* (non-Javadoc)
	 * @see com.plgrim.ncp.cmp.orderfulfilment.fo.cart.CartCommandComponent#updateCartDlv(com.plgrim.ncp.framework.data.SystemPK, com.plgrim.ncp.biz.cart.data.CartSearchDTO)
	 */
	@Override
    public boolean updateCartDlv(SystemPK systemPK,CartSearchDTO cartSearchDTO) throws Exception {

		boolean res = true;

		if(cartSearchDTO == null || CollectionService.isEmpty(cartSearchDTO.getBskGodList())){
			throw new Exception("");
		}


		BskGod pBskGod = cartSearchDTO.getBskGodList().get(0);
		List<BskGodExtend> bskGodItmStatList = cartSelectService.selectBskGodItmStatList(pBskGod);

		for(BskGodExtend bskGodExtend : bskGodItmStatList){
			if(StringService.equalsIgnoreCase(BskEnum.BskGodItmSaleStat.SALE_PROGRS_PKUP.toString(), bskGodExtend.getItmStatCd())){
				res = false;
				break;
			}
		}

		if(res){
			cartSearchDTO.setMallId(systemPK.getMall());
			cartSearchDTO.getBsk().setBskNo(cartSearchDTO.getBskGodList().get(0).getBskNo());
			cartSearchDTO.setUdterId(this.getMemberGuestId());
			cartCommandService.updateCartDlv(cartSearchDTO);
		}

		return res;
    }

	/* (non-Javadoc)
	 * @see com.plgrim.ncp.cmp.orderfulfilment.fo.cart.CartCommandComponent#insertInvAlrm(com.plgrim.ncp.framework.data.SystemPK, com.plgrim.ncp.biz.cart.data.GodReWhsgNtcnDTO)
	 */
	@Override
    public void insertInvAlrm(SystemPK systemPK,GodReWhsgNtcnDTO godReWhsgNtcnDTO) throws Exception {

		//ncp 3차 ************************************************
		if(true){

			if(godReWhsgNtcnDTO == null
					|| CollectionService.isEmpty(godReWhsgNtcnDTO.getBskGodList())
					|| CollectionService.isEmpty(godReWhsgNtcnDTO.getStplatList())
					|| StringService.isEmpty(godReWhsgNtcnDTO.getAreaNo())
					|| StringService.isEmpty(godReWhsgNtcnDTO.getTlofNo1())
					|| StringService.isEmpty(godReWhsgNtcnDTO.getTlofNo2())
					){
				throw new Exception("");
			}

			godReWhsgNtcnDTO.setNtcnSectCd("MOBIL_NTCN");	//휴대전화 알림
		} else {

			if(godReWhsgNtcnDTO == null
					|| CollectionService.isEmpty(godReWhsgNtcnDTO.getBskGodList())
					|| CollectionService.isEmpty(godReWhsgNtcnDTO.getStplatList())
					|| StringService.isEmpty(godReWhsgNtcnDTO.getMbrEmail())
					){
				throw new Exception("");
			}

			godReWhsgNtcnDTO.setNtcnSectCd("EMAIL_NTCN");	//이메일 알림
		}
		//ncp 3차 ************************************************

		if(ContextService.hasRole()){ // 회원
			Mbr mbr= ((SecurityUserDetail) ContextService.getCurrentUserDetail()).getMbr();
			godReWhsgNtcnDTO.setMbrNo(mbr.getMbrNo());
			godReWhsgNtcnDTO.setRegtrId(mbr.getMbrNo());
			godReWhsgNtcnDTO.setUdterId(mbr.getMbrNo());
		}else{
			godReWhsgNtcnDTO.setRegtrId("GUEST");
			godReWhsgNtcnDTO.setUdterId("GUEST");
		}

		cartCommandService.insertInvAlrm(godReWhsgNtcnDTO);

    }


	/* (non-Javadoc)
	 * @see com.plgrim.ncp.cmp.orderfulfilment.fo.cart.CartCommandComponent#updatePkupShop(com.plgrim.ncp.biz.cart.data.CartSearchDTO)
	 */
	@Override
    public void updatePkupShop(CartSearchDTO cartSearchDTO) throws Exception {

		cartSearchDTO.setUdterId(this.getMemberGuestId());
		cartCommandService.updatePkupShop(cartSearchDTO);

    }

	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
	/**
	 * [메서드 설명].
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @return String [설명]
	 * @throws Exception the exception
	 * @since 2015. 6. 19
	 */
	private String getMemberGuestId() throws Exception {
		String id = "GUEST";
		// 회원
		if(ContextService.hasRole()){ // 회원
			Mbr mbr = ((SecurityUserDetail) ContextService.getCurrentUserDetail()).getMbr();
			id = mbr.getMbrNo();
		}


		return id;
	}


	

	
	
	/**
	 * [상품 재고 체크].
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param cartDTO [설명]
	 * @throws Exception the exception
	 * @since 2015. 6. 19
	 */
	private HashMap<String,Integer> checkGodsInv(CartDTO cartDTO)throws Exception{

		int invCnt = 0;
		int rtn = 1;
		List<String> godsList = null;
		// 예약 판매 상품 여부
		String resveSaleGodYn = cartSelectRepository.selectResvGodYn(cartDTO.getGod().getGodNo());
		// 패키지형 상품 여부
		boolean isPckage = StringService.equalsIgnoreCase(BskEnum.YES.toString(), cartDTO.getGod().getPckageGodYn());
		String dlvSectCd = cartDTO.getGod().getDlvSectCd();



		if("PKUP_DLV".equalsIgnoreCase(dlvSectCd)){//픽업
			if(isPckage){//패키지형
				GodItmQtySearchDTO godItmQtySearchDTO = new GodItmQtySearchDTO();
				godItmQtySearchDTO.setDlvSectCd(dlvSectCd);
				godItmQtySearchDTO.setShopId(cartDTO.getGod().getPkupShopSn());

				String[] itmArr = new String[cartDTO.getCpstGodList().size()];//재고메트릭스 조회위한 itm배열
				int arrcnt = 0;
				HashMap<String,Long> itmOrdQtyMap = new HashMap<String,Long>(); //itm 단위 구매수량
				for(CartGodDTO godDTO : cartDTO.getCpstGodList()){//itmNo 배열에 담는다
					itmArr[arrcnt] = godDTO.getItmNo();
					arrcnt++;

					Long itmOrdQty = 0L;

					if("ADIT_CPST_GOD".equalsIgnoreCase(godDTO.getPckageGodTpCd())){
						//추가구성품 수량
						itmOrdQty = godDTO.getItmQty();
					}else{
						//기본구성품 수량
						//itmOrdQty = godDTO.getCpstGodQty();
						itmOrdQty = cartDTO.getGod().getItmQty();
					}

					//구성품만큼 돌면서 itm 단위로 주문수량을 Map 에 묶어서 셋팅
					if(null!=itmOrdQtyMap.get(godDTO.getItmNo())){//이미있으면 누적
						itmOrdQtyMap.put(godDTO.getItmNo(), itmOrdQty+itmOrdQtyMap.get(godDTO.getItmNo()));
					}else{//없으면 map 에 add
						itmOrdQtyMap.put(godDTO.getItmNo(), itmOrdQty);
					}

				}
				godItmQtySearchDTO.setItmNoArr(itmArr);
				List<GodItmQtyResult> itmqtyList = cartSelectService.selectGodItmQty(godItmQtySearchDTO);//itm별 재고가져옴(재고메트릭스)

				boolean isShopInv = true;
				boolean isFc08Inv = true;
				boolean isFc01Inv = true;
				
				Long shopInvCnt = 0L;
				Long fc08InvCnt = 0L;
				Long fc01InvCnt = 0L;
				boolean isFirst = true;
				
				
				//재고메트릭스만큼 돌면서 해당 Itm 주문수량만큼 빼서 배정가능재고위치 셋팅
				for(GodItmQtyResult dto : itmqtyList){
					if(isShopInv){
						if(dto.getShopInvQty()<itmOrdQtyMap.get(dto.getItmNo())){//매장재고보다 주문수량이 크면
							isShopInv = false; //매장재고 아님
						}
						if(isFirst || dto.getShopInvQty() < shopInvCnt){ //재고위치별로 아이템에대한 min 값을 구한다.
							shopInvCnt = dto.getShopInvQty();
						}
					}
					
/*					if(dto.getShopInvQty()==0&&dto.getFc08Qty()==0&&dto.getFc01Qty()==0){
						//모두 재고가 없다
						rtn = -1;
					}*/
					
					isFirst = false;
				}
				invCnt = shopInvCnt.intValue();
				if(shopInvCnt<fc08InvCnt){
					invCnt = fc08InvCnt.intValue();					
				}
				if(invCnt<fc01InvCnt.intValue()){
					invCnt=fc01InvCnt.intValue();
				}
				 
				
				if(rtn!=-1&&!isShopInv&&!isFc08Inv&&!isFc01Inv){//어디에도 배정하지 못하면
					rtn = 0;
				}


			}else{//단품
				invCnt = cartSelectService.selectGodInvPkupCount(cartDTO.getGod());
				// 재고수량이 0인 경우 주문 불가
				if(invCnt == 0){
					rtn = -1;
				}else{
					// 재고수량이 주문수량보다 작은 경우 주문 불가
					if(invCnt < cartDTO.getGod().getItmQty()){
						rtn = 0;
					}
				}
			}
		}else{//픽업이 아니면 기존로직
			//패키지형 상품의 구성상품 재고 체크
			if(isPckage){
				godsList = new ArrayList<String>();
				BskGod god = new BskGod();
				HashMap<String,Long> itmMap = new HashMap<String,Long>();

				// 구성상품 리스트가 없는 경우 Exception 발생
				if(cartDTO.getCpstGodList() == null || CollectionService.isEmpty(cartDTO.getCpstGodList())){
					throw new NotSupportedException(null);
				}

				// 구성상품 Loop
				for(CartGodDTO godDTO : cartDTO.getCpstGodList()){
					// 구성상품의 상품번호가 null이 아니고 단품번호가 null이 아닌 경우
					if(StringService.isNotEmpty(godDTO.getGodNo()) && StringService.isNotEmpty(godDTO.getItmNo())){
						// 상품 리스트에 상품번호 추가
						godsList.add(godDTO.getGodNo());
						// 장바구니 상품 설정
						// 상품번호
						god.setGodNo(godDTO.getGodNo());
						// 단품번호
						god.setItmNo(godDTO.getItmNo());
						// 배송 구분 코드
						god.setDlvSectCd(cartDTO.getGod().getDlvSectCd());
						// 픽업 매장 일련번호
						god.setPkupShopSn(cartDTO.getGod().getPkupShopSn());

						//예약 상품 인 경우
						if("Y".equals(resveSaleGodYn)){
							// 예약상품 재고 수량 조회
							invCnt = cartSelectService.selectResvGodInvCount(god);
						}else{
							// 단품 재고 수량 조회
							invCnt = cartSelectService.selectGodInvCount(god);
						}

						// 장바구니 상품 주문 수량과 재고 수량 비교
						if(rtn >= 0){
							if(invCnt == 0){
								rtn = -1;
							}else{
								/*
								 * 패키지형상품 유형 코드
								 *	ㅁ. 패키지형상품 유형 : PCKAGE_GOD_TP
								 *	   >. 세트상품 : SET_GOD
								 *	   >. 패키지 상품 : PCKAGE_GOD
								 *	   >. 추가 구성 상품 : ADIT_CPST_GOD
								 */
								// 추가 구성 상품인 경우
								if(StringService.equalsIgnoreCase(BskEnum.PckageGodTp.ADIT_CPST_GOD.toString(), godDTO.getPckageGodTpCd())){
									// 장바구니의 주문 상품 수량 구성상품 단품 번호 별로 sum
									if(itmMap.containsKey(godDTO.getItmNo())){
										itmMap.put(godDTO.getItmNo(), itmMap.get(godDTO.getItmNo()) + godDTO.getItmQty());
									}else{
										itmMap.put(godDTO.getItmNo(), godDTO.getItmQty());
									}
								}else{
									// TODO 추가 구성상품이 아닌 경우 장바구니의 상품 주문 수량 sum????
									if(itmMap.containsKey(godDTO.getItmNo())){
										itmMap.put(godDTO.getItmNo(), itmMap.get(godDTO.getItmNo())+ cartDTO.getGod().getItmQty());
									}else{
										itmMap.put(godDTO.getItmNo(), cartDTO.getGod().getItmQty());
									}
								}

								// 재고수량이 주문 수량보다 적은 경우 주문 불가
								if(invCnt < itmMap.get(godDTO.getItmNo())){
									rtn = 0;
								}
							}
						}
					}

				}
			// 패키지형 상품이 아닌 경우 구성상품 재고 체크
			}else{
				//예약 상품 인 경우
				if("Y".equals(resveSaleGodYn)){
					// 예약상품 재고 수량 조회
					invCnt = cartSelectService.selectResvGodInvCount(cartDTO.getGod());
				}else{
					// 단품 재고 수량 조회
					invCnt = cartSelectService.selectGodInvCount(cartDTO.getGod());
				}

				// 재고수량이 0인 경우 주문 불가
				if(invCnt == 0){
					rtn = -1;
				}else{
					// 재고수량이 주문수량보다 작은 경우 주문 불가
					if(invCnt < cartDTO.getGod().getItmQty()){
						rtn = 0;
					}
				}
			}
		}



		// 재고수량이 0인 경우 상품 판매상태 변경
		if(rtn < 0){
			// TODO	상품재작업필요 : goodsInfoCommandService.updateGodStatus(cartDTO.getGod().getGodNo(),isPckage,godsList);
		}
		HashMap<String,Integer> result = new HashMap<String,Integer>(); //임시DATA
		result.put("rtn", rtn);
		result.put("invCnt", invCnt);
		
		return result;
	}
	
	

	@Override
    public void deleteWishList(SystemPK pk, CartSearchDTO cartSearchDTO) throws Exception {

		Mbr mbr = ((SecurityUserDetail) ContextService.getCurrentUserDetail()).getMbr();

		cartSearchDTO.getBsk().setMbrNo(mbr.getMbrNo());
		cartSearchDTO.getBsk().setMallId(pk.getMall());

		BskWishlst wish = new BskWishlst();
		wish.setMbrNo(mbr.getMbrNo());
		wish.setMallId(pk.getMall());
		wish.setLangCd(pk.getLang());
		BskWishlst wishList = cartSelectService.selectWishList(wish);

		if(wishList != null){

			BskWishlstGod wishGod = new BskWishlstGod();
			wishGod.setWishlstSn(wishList.getWishlstSn());
			wishGod.setGodNo(cartSearchDTO.getBskGodList().get(0).getGodNo());
			cartCommandService.deleteWishList(wishGod);
		}

    }
	

    @Override
    public void addReOrderCartList(SystemPK pk, ReOrderCartDTO reOrderCartDTO){
    	try{
    		
    		List<OrdGodReOrderDTO> reOrderDTOList = orderSelectRepository.selectReOrderGodList(reOrderCartDTO);
    		List<CartDTO> cartList = new ArrayList();
    		
    		for(OrdGodReOrderDTO reOrdDTO:reOrderDTOList){
    			if(reOrdDTO.getPckageGodTpCd()==null){
	    			CartDTO cartDTO = new CartDTO();
	    			BskGodExtend god = new BskGodExtend();
	    			god.setGodNo(reOrdDTO.getGodNo());
	    			god.setItmNo(reOrdDTO.getItmNo());
	    			god.setItmQty(reOrdDTO.getOrdQty());
	    			if("SET_GOD".equals(reOrdDTO.getGodTpCd())){
	    				god.setPckageGodYn("Y");
	    				
	    				List<CartGodDTO> setGodList = new ArrayList();
	    				for(OrdGodReOrderDTO setOrdDTO:reOrderDTOList){
		    				if(reOrdDTO.getPckageGodTpCd()!=null&&reOrdDTO.getOrdGodTurn().equals(setOrdDTO.getParentOrdGodTurn())){
		    					CartGodDTO cartGodDTO = new CartGodDTO();
		    					cartGodDTO.setGodNo(setOrdDTO.getGodNo());
		    					cartGodDTO.setItmNo(setOrdDTO.getItmNo());
		    					cartGodDTO.setPckageGodTpCd(setOrdDTO.getPckageGodTpCd());
		    					cartGodDTO.setCpstGodQty(1L);
		    					cartGodDTO.setItmQty(setOrdDTO.getOrdQty());
		    					setGodList.add(cartGodDTO);
		    				}
		    			}	
	    				
	    				cartDTO.setCpstGodList(setGodList);
	    			}else{
	    				god.setPckageGodYn("N");
	    			}
	    			god.setDlvSectCd("GNRL_DLV");
	    			
	    			cartDTO.setGod(god);
	    			
	    			cartList.add(cartDTO);
    			}
    		}
    		reOrderCartDTO.setCartList(cartList);
    		
    		
			Bsk bsk = new Bsk();
			// 회원정보 입력
			if(ContextService.hasRole()){ // 회원
				SecurityUserDetail userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();
				Mbr mbr = userDetail.getMbr();
				bsk.setMbrSectCd(MbrSect.MBR.toString());
				bsk.setMbrNo(mbr.getMbrNo());
				bsk.setRegtrId(mbr.getMbrNo());
				bsk.setUdterId(mbr.getMbrNo());
			}
			else {
				Mbr mbr = new Mbr();
				mbr.setMbrTpCd(MbrSect.NMBR.toString());
				bsk.setMbrSectCd(MbrSect.NMBR.toString());
				bsk.setSesionId(ContextService.getCurrentRequest().getSession().getId());
				bsk.setRegtrId("GUEST");
				bsk.setUdterId("GUEST");
			}
			bsk.setBskTpCd(BskEnum.BskTp.REORDER.toString());
			bsk.setMallId(pk.getMall());
			bsk.setLangCd(pk.getLang());
			
			Bsk sBsk = cartSelectService.selectBskInfo(bsk);
	
			if(sBsk == null){
				//신규 장바구니 생성
				bsk.setBskNo(cartCommandService.insertCartMst(bsk));
			}else{
				cartCommandService.deleteDirtCart(sBsk.getBskNo());
				//신규 장바구니 생성
				bsk.setBskNo(cartCommandService.insertCartMst(bsk));
			}
			
			List<BskGod> list = new ArrayList<BskGod>();
			for(CartDTO cartDTO : reOrderCartDTO.getCartList()){
				cartDTO.setBsk(bsk);
				//3.장바구니 상품 등록
				BskGodExtend god = cartCommandService.insertCart(cartDTO);
				list.add(god);

			}
			CartSearchDTO dto = new CartSearchDTO();
			
			dto.setDlvSectCd(BskEnum.DlvSect.GNRL_DLV.toString());
			dto.setMallId(pk.getMall());
			dto.setDevice(pk.getDevice());
			dto.setLang(pk.getLang());
			dto.setBsk(bsk);
			dto.setBskGodList(list);
			dto.setVirtlDlvComptYn("Y");
			dto.setVirtlDlvOrdNo(reOrderCartDTO.getOrdNo());
			dto.setVirtlDlvClmNo(reOrderCartDTO.getClmNo());
			
			ContextService.getCurrentRequest().getSession().setAttribute("SESSION_KEY_CART_GOODS", dto);
			log.info(CommonResponseCode.OD_00014_장바구니_등록_성공.toMessage());
    	}catch(Exception e){
    		log.error(e.getMessage(),e);
    		throw new CartFailException(e.getMessage());
    	}
		
		
    }

}
