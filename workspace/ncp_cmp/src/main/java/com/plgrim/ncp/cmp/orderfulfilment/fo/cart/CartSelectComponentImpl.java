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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.plgrim.ncp.base.abstracts.AbstractComponent;
import com.plgrim.ncp.base.entities.datasource1.bsk.Bsk;
import com.plgrim.ncp.base.entities.datasource1.bsk.BskGod;
import com.plgrim.ncp.base.entities.datasource1.bsk.BskGodExtend;
import com.plgrim.ncp.base.entities.datasource1.com.ComQdlvGudTxtExtend;
import com.plgrim.ncp.base.entities.datasource1.mbr.Mbr;
import com.plgrim.ncp.base.enums.BskEnum;
import com.plgrim.ncp.base.enums.MemberEnum;
import com.plgrim.ncp.biz.cart.data.CartOptChgSearchDTO;
import com.plgrim.ncp.biz.cart.data.CartSearchDTO;
import com.plgrim.ncp.biz.cart.data.GodItmQtySearchDTO;
import com.plgrim.ncp.biz.cart.exception.CartFailException;
import com.plgrim.ncp.biz.cart.result.CartCpstGodResult;
import com.plgrim.ncp.biz.cart.result.CartGodOptionResult;
import com.plgrim.ncp.biz.cart.result.CartGodPrmResult;
import com.plgrim.ncp.biz.cart.result.CartListResult;
import com.plgrim.ncp.biz.cart.result.CartOptChgGodResult;
import com.plgrim.ncp.biz.cart.result.CartOrderCheckResult;
import com.plgrim.ncp.biz.cart.result.CartResult;
import com.plgrim.ncp.biz.cart.result.GodInfoResult;
import com.plgrim.ncp.biz.cart.result.GodItmQtyResult;
import com.plgrim.ncp.biz.cart.service.CartSelectService;
import com.plgrim.ncp.biz.delivery.repository.DeliverySelectRepository;
import com.plgrim.ncp.biz.member.data.SecurityUserDetail;
import com.plgrim.ncp.biz.member.service.MemberBenefitSelectService;
import com.plgrim.ncp.biz.order.data.CouponSearchDTO;
import com.plgrim.ncp.biz.order.result.OrderCouponResult;
import com.plgrim.ncp.biz.order.service.OrderSelectService;
import com.plgrim.ncp.framework.commons.CollectionService;
import com.plgrim.ncp.framework.commons.ContextService;
import com.plgrim.ncp.framework.commons.StringService;
import com.plgrim.ncp.framework.data.SystemPK;
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
@Component
public class CartSelectComponentImpl extends AbstractComponent implements CartSelectComponent {

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	/** The cart select service. */
	@Autowired
	CartSelectService cartSelectService;
	@Autowired
	OrderSelectService orderSelectService;
	@Autowired
	DeliverySelectRepository deliverySelectRepository;
	/** The member service. */

	@Autowired
	MemberBenefitSelectService memberBenefitSelectService;

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
	 * @see com.plgrim.ncp.cmp.orderfulfilment.fo.cart.CartSelectComponent#selectCartList(com.plgrim.ncp.framework.data.SystemPK, com.plgrim.ncp.biz.cart.data.CartSearchDTO)
	 */
	@Override
	public CartListResult selectCartList(SystemPK systemPK,CartSearchDTO cartSearchDTO) throws Exception {
		
		log.info(CommonResponseCode.OD_00011_장바구니_조회_시작.toMessage());

		CartListResult result = new CartListResult();
		
		
		try {
			Mbr mbr; // 회원
			cartSearchDTO.setMallId(systemPK.getMall());
			cartSearchDTO.setDevice(systemPK.getDevice());
			cartSearchDTO.setLang(systemPK.getLang());
			cartSearchDTO.setCrncyCd(getCrncyCd(systemPK.getLang())); // 통화코드

			Bsk bsk = new Bsk();
			bsk.setBskTpCd(BskEnum.BskTp.BSK.toString());
			bsk.setMallId(systemPK.getMall());

			/*
			 * NCP 3차 추가
			 * 정책 > 언어별 장바구니 데이터 생성
			 * 카드마스터 조회 시 언어코드 추가
			 * since 2016. 01. 20
			 */
			bsk.setLangCd(systemPK.getLang());

			if(ContextService.hasRole()){ // 회원
				SecurityUserDetail userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();
				mbr = userDetail.getMbr();
				bsk.setMbrSectCd(BskEnum.MbrSect.MBR.toString());
				bsk.setMbrNo(mbr.getMbrNo());
			}
			else {
				mbr = new Mbr();
				mbr.setMbrTpCd(MemberEnum.MemberTpCd.NMBR.toString());
				bsk.setMbrSectCd(BskEnum.MbrSect.NMBR.toString());
				bsk.setSesionId(ContextService.getCurrentRequest().getSession().getId());
			}

			cartSearchDTO.setBsk(cartSelectService.selectBskInfo(bsk));
			cartSearchDTO.setMbr(mbr);

			int gnrlCnt = 0;
			int pkupCnt = 0;
			int overSeq = 0;
			int resvCnt = 0;	// 예약장바구니 기능 추가  : by cannon (2016.04.18)
			int quickCnt = 0;

			String dlvSectCd = cartSearchDTO.getDlvSectCd();
			List<BskGodExtend> bskInfo = cartSelectService.selectCartTpCount(cartSearchDTO);

			if(!CollectionService.isEmpty(bskInfo)){
				for(BskGodExtend bskGod : bskInfo){

					if(StringService.equalsIgnoreCase(BskEnum.DlvSect.PKUP_DLV.toString(), bskGod.getDlvSectCd())){
						pkupCnt = bskGod.getGodCnt();
					}else if(StringService.equalsIgnoreCase(BskEnum.DlvSect.OVSEA_DLV.toString(), bskGod.getDlvSectCd())){
						overSeq = bskGod.getGodCnt();
					}else if("RSV".equals(bskGod.getDlvSectCd())){	// 예약장바구니 기능 추가  : by cannon (2016.04.18)
						resvCnt = bskGod.getGodCnt();
					}else if("QDLV".equals(bskGod.getDlvSectCd())){
						quickCnt = bskGod.getGodCnt();
					}else{
						gnrlCnt = bskGod.getGodCnt();
					}

					if(StringService.isEmpty(dlvSectCd) && bskGod.getCartSeq() == 1){
						dlvSectCd = bskGod.getDlvSectCd();
						cartSearchDTO.setDlvSectCd(bskGod.getDlvSectCd());
						cartSearchDTO.setPkupShopSn(bskGod.getPkupShopSn());
						cartSearchDTO.setBrndId(bskGod.getBrndId());

						// 예약장바구니 기능 추가  : by cannon (2016.04.18)
						if("RSV".equals(dlvSectCd)){
							cartSearchDTO.setResveSaleGodYn("Y");
						}else{
							cartSearchDTO.setResveSaleGodYn("N");
						}
					}
				}

				result = cartSelectService.selectCartGodList(cartSearchDTO);
				result.setBskInfo(bskInfo);
			}

			if(StringService.isEmpty(dlvSectCd)){
				dlvSectCd = BskEnum.DlvSect.GNRL_DLV.toString();
			}

			result.setDlvSectCd(dlvSectCd);
			result.setPkupCartCnt(pkupCnt);
			result.setOverSeaCartCnt(overSeq);
			result.setGnrlCartCnt(gnrlCnt);
			result.setResvCartCnt(resvCnt);	// 예약장바구니 기능 추가  : by cannon (2016.04.18)
			result.setQuickCartCnt(quickCnt);

			log.info(CommonResponseCode.OD_00012_장바구니_조회_성공.toMessage());
		}
		catch (Exception e) {
			log.error(CommonResponseCode.OD_40032_장바구니_조회_실패.toMessage()+" exception : {}",e);
			throw e;
		}
		


		
		
		
		return result;
	}

	/* (non-Javadoc)
	 * @see com.plgrim.ncp.cmp.orderfulfilment.fo.cart.CartSelectComponent#isValidOrderGod(com.plgrim.ncp.framework.data.SystemPK, com.plgrim.ncp.biz.cart.data.CartSearchDTO)
	 */
	@Override
	public CartOrderCheckResult isValidOrderGod(SystemPK systemPK,CartSearchDTO cartSearchDTO) throws Exception {

		Mbr mbr; // 회원
		cartSearchDTO.setMallId(systemPK.getMall());
		cartSearchDTO.setDevice(systemPK.getDevice());
		cartSearchDTO.setLang(systemPK.getLang());
		cartSearchDTO.setCrncyCd(getCrncyCd(systemPK.getLang())); // 통화코드

		Bsk bsk = new Bsk();
		bsk.setBskTpCd(BskEnum.BskTp.BSK.toString());
		bsk.setMallId(systemPK.getMall());
		/*
		 * NCP 3차 추가
		 * 정책 > 언어별 장바구니 데이터 생성
		 * 카드마스터 조회 시 언어코드 추가
		 * since 2016. 01. 20
		 */
		bsk.setLangCd(systemPK.getLang());


		if(ContextService.hasRole()){ // 회원

			SecurityUserDetail userDetail = (SecurityUserDetail)ContextService.getCurrentUserDetail();
			mbr = userDetail.getMbr();
			bsk.setMbrSectCd(BskEnum.MbrSect.MBR.toString());
			bsk.setMbrNo(mbr.getMbrNo());

		}else {
			mbr = new Mbr();
			mbr.setMbrTpCd(MemberEnum.MemberTpCd.NMBR.toString());
			bsk.setMbrSectCd(BskEnum.MbrSect.NMBR.toString());
			bsk.setSesionId(ContextService.getCurrentRequest().getSession().getId());
		}

		
		cartSearchDTO.setBsk(cartSelectService.selectBskInfo(bsk));
		cartSearchDTO.setMbr(mbr);

		List<CartResult>  ordList = cartSelectService.selectCartGodValid(cartSearchDTO);

		CartOrderCheckResult cartOrderCheckResult = new CartOrderCheckResult();
		cartOrderCheckResult.setResultType("ORDER_ERR");
		boolean isSellYn = true;
		boolean isShopSaleYn = false;

		for(CartResult cart : ordList){

			if(StringService.equalsIgnoreCase(BskEnum.NO.toString(), cart.getSellYn())
			  || StringService.equalsIgnoreCase(BskEnum.NO.toString(), cart.getInvYn())
			  || StringService.equalsIgnoreCase(BskEnum.NO.toString(), cart.getAvailMinOrdCnt())
			  || StringService.equalsIgnoreCase(BskEnum.NO.toString(), cart.getAvailMaxOrdCnt())
			  || StringService.equalsIgnoreCase(BskEnum.NO.toString(), cart.getAditInvYn())
			){
				isSellYn = false;
				if(!isSellYn){
					break;
				}
			}
			
			if("Y".equals(cart.getShopDlvItm())){
				isShopSaleYn = true;
			}

			
		}
		
		
		if(isShopSaleYn&&"MBM".equals(systemPK.getMall())){
			if(ContextService.hasRole()){ // 회원
				String cstrno = deliverySelectRepository.getVipList();
				if(mbr!=null&&mbr.getErpCstmrNo()!=null&&cstrno.indexOf(mbr.getErpCstmrNo())!=-1){
					throw new CartFailException("vip");
				}
			}
		}

		boolean pkupShopChk = true;
		if(StringService.equalsIgnoreCase(BskEnum.DlvSect.PKUP_DLV.toString(), cartSearchDTO.getDlvSectCd())){
			pkupShopChk = cartSelectService.pkupShopYnCheck(cartSearchDTO);
		}

		if(!pkupShopChk){
			isSellYn = false;
			cartOrderCheckResult.setResultType("PKUP_SHOP");
		}
		if("Y".equals(cartSearchDTO.getEmpYn())){
			CouponSearchDTO couponSearchDTO = new CouponSearchDTO();
			couponSearchDTO.setMbr(cartSearchDTO.getMbr());
			couponSearchDTO.setBskGodList(cartSearchDTO.getBskGodList());
			couponSearchDTO.setMallId(cartSearchDTO.getMallId());
			couponSearchDTO.setLang(cartSearchDTO.getLang());
			couponSearchDTO.setDevice(cartSearchDTO.getDevice());
				
			if(StringService.isNotEmpty(mbr.getMbrNo())
					   && StringService.equalsIgnoreCase(mbr.getMbrAtrbCd(), MemberEnum.MemberAtrbCd.EMP.toString())
					   && "Y".equals(cartSearchDTO.getEmpYn())){
				couponSearchDTO.setEmpYn("Y");
			}
							
			
			
			List<OrderCouponResult> memberCouponList = orderSelectService.selectOrderCouponList(couponSearchDTO);
			if(memberCouponList.isEmpty()){
				cartOrderCheckResult.setCpnCnt(0);
			}else{
				cartOrderCheckResult.setCpnCnt(memberCouponList.size());
			}
		}

		
		
		cartOrderCheckResult.setResult(isSellYn);

		return cartOrderCheckResult;
    }

	/* (non-Javadoc)
	 * @see com.plgrim.ncp.cmp.orderfulfilment.fo.cart.CartSelectComponent#selectInvalidOrderGod(com.plgrim.ncp.framework.data.SystemPK, com.plgrim.ncp.biz.cart.data.CartSearchDTO)
	 */
	@Override
    public List<CartResult> selectInvalidOrderGod(SystemPK systemPK,CartSearchDTO cartSearchDTO) throws Exception {
		Mbr mbr; // 회원
		cartSearchDTO.setMallId(systemPK.getMall());
		cartSearchDTO.setDevice(systemPK.getDevice());
		cartSearchDTO.setLang(systemPK.getLang());
		cartSearchDTO.setCrncyCd(getCrncyCd(systemPK.getLang())); // 통화코드

		Bsk bsk = new Bsk();
		bsk.setBskTpCd(BskEnum.BskTp.BSK.toString());
		bsk.setMallId(systemPK.getMall());
		/*
		 * NCP 3차 추가
		 * 정책 > 언어별 장바구니 데이터 생성
		 * 카드마스터 조회 시 언어코드 추가
		 * since 2016. 01. 20
		 */
		bsk.setLangCd(systemPK.getLang());


		if(ContextService.hasRole()){ // 회원
			mbr = ((SecurityUserDetail) ContextService.getCurrentUserDetail()).getMbr();
			bsk.setMbrSectCd(BskEnum.MbrSect.MBR.toString());
			bsk.setMbrNo(mbr.getMbrNo());

		}else {
			mbr = new Mbr();
			mbr.setMbrTpCd(MemberEnum.MemberTpCd.NMBR.toString());
			bsk.setMbrSectCd(BskEnum.MbrSect.NMBR.toString());
			bsk.setSesionId(ContextService.getCurrentRequest().getSession().getId());
		}

		cartSearchDTO.setBsk(cartSelectService.selectBskInfo(bsk));
		cartSearchDTO.setMbr(mbr);

		List<CartResult> result = cartSelectService.selectInvalidCartGodList(cartSearchDTO);

		return result;
    }


	/* (non-Javadoc)
	 * @see com.plgrim.ncp.cmp.orderfulfilment.fo.cart.CartSelectComponent#selectGodOptionList(com.plgrim.ncp.framework.data.SystemPK, com.plgrim.ncp.base.entities.datasource1.bsk.BskGod)
	 */
	@Override
    public CartGodOptionResult selectGodOptionList(SystemPK systemPK,BskGod bskGod) throws Exception {

		bskGod.setMallId(systemPK.getMall());
		bskGod.setLang(systemPK.getLang());

		if(bskGod.getDlvSectCd().equalsIgnoreCase("QDLV")){
			return cartSelectService.selectGodOptionListForQdlv(bskGod);
		}else{
	    return cartSelectService.selectGodOptionList(bskGod);
    }

    }

	/* (non-Javadoc)
	 * @see com.plgrim.ncp.cmp.orderfulfilment.fo.cart.CartSelectComponent#selectPckageGodOptionList(com.plgrim.ncp.framework.data.SystemPK, com.plgrim.ncp.base.entities.datasource1.bsk.BskGod)
	 */
	public List<CartGodOptionResult> selectPckageGodOptionList(SystemPK systemPK,BskGod bskGod) throws Exception {

		bskGod.setMallId(systemPK.getMall());
		bskGod.setLang(systemPK.getLang());
		bskGod.setDevice(systemPK.getDevice());

		return cartSelectService.selectPckageGodOptionList(bskGod);
	}


	@Override
	public List<CartOptChgGodResult> selectPckageGodOptionListPickup(SystemPK systemPK, CartOptChgSearchDTO search) throws Exception {
		search.setMallId(systemPK.getMall());
		search.setLang(systemPK.getLang());
		search.setDevice(systemPK.getDevice());

		return cartSelectService.selectPckageGodOptionListPickup(search);
	}


	/* (non-Javadoc)
	 * @see com.plgrim.ncp.cmp.orderfulfilment.fo.cart.CartSelectComponent#selectInvAlrm(com.plgrim.ncp.framework.data.SystemPK, com.plgrim.ncp.base.entities.datasource1.bsk.BskGod)
	 */
	@Override
    public GodInfoResult selectInvAlrm(SystemPK systemPK, BskGod bskGod)throws Exception {

		bskGod.setLang(systemPK.getLang());
		GodInfoResult result  = cartSelectService.selectBskGodItm(bskGod);

		//재입고 알림신청 약관 조회
		result.setStplatList(cartSelectService.selectStplatList(BskEnum.ReWhsgNtcnReqst.toString()));

		if(ContextService.hasRole()){ // 회원
			SecurityUserDetail userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();
			Mbr mbr = userDetail.getMbr();

			result.setMobilNationNo(mbr.getMobilNationNo());
			result.setMobilAreaNo(mbr.getMobilAreaNo());
			result.setMobilTlofNo(mbr.getMobilTlofNo());
			result.setMobiltlofWthnNo(mbr.getMobilTlofWthnNo());

			result.setMobilNo(mbr.getMobilAreaNo()+""+mbr.getMobilTlofNo()+""+mbr.getMobilTlofWthnNo());

			//ncp 3차 ********************************************************************************************
			result.setMbrEmail(mbr.getMbrEmail());
			//ncp 3차 ********************************************************************************************

		}
	    return result;
    }

	/* (non-Javadoc)
	 * @see com.plgrim.ncp.cmp.orderfulfilment.fo.cart.CartSelectComponent#selectPkupShopInfo(com.plgrim.ncp.framework.data.SystemPK, com.plgrim.ncp.biz.cart.data.CartSearchDTO)
	 */
	@Override
    public List<CartResult> selectPkupShopInfo(SystemPK systemPK,CartSearchDTO cartSearchDTO) throws Exception {

		cartSearchDTO.setMallId(systemPK.getMall());
		cartSearchDTO.setDevice(systemPK.getDevice());
		cartSearchDTO.setLang(systemPK.getLang());
		cartSearchDTO.setCrncyCd(getCrncyCd(systemPK.getLang())); // 통화코드
		Bsk bsk = new Bsk();
		bsk.setBskNo(cartSearchDTO.getBskGodList().get(0).getBskNo());
		cartSearchDTO.setBsk(bsk);

	    return cartSelectService.selectPkupShopInfo(cartSearchDTO);
    }


	/* (non-Javadoc)
	 * @see com.plgrim.ncp.cmp.orderfulfilment.fo.cart.CartSelectComponent#selectMbrCartCnt(com.plgrim.ncp.framework.data.SystemPK)
	 */
	public int selectMbrCartCnt(SystemPK systemPK) throws Exception{

		CartSearchDTO dto = new CartSearchDTO();
		Bsk bsk = new Bsk();
		dto.setMallId(systemPK.getMall());

		/*
		 * NCP 3차 추가
		 * 정책 > 언어별 장바구니 데이터 생성
		 * 카드마스터 조회 시 언어코드 추가
		 * since 2016. 01. 20
		 */
		dto.setLang(systemPK.getLang());

		if(ContextService.hasRole()){ // 회원
			Mbr mbr = ((SecurityUserDetail) ContextService.getCurrentUserDetail()).getMbr();
			bsk.setMbrNo(mbr.getMbrNo());
			dto.setMbr(mbr);
		}else {
			bsk.setSesionId(ContextService.getCurrentRequest().getSession().getId());
		}

		dto.setBsk(bsk);
		return cartSelectService.selectMbrCartCnt(dto);
	}

	/* (non-Javadoc)
	 * @see com.plgrim.ncp.cmp.orderfulfilment.fo.cart.CartSelectComponent#selectMbrCartCnt(com.plgrim.ncp.framework.data.SystemPK)
	 */
    public CartGodPrmResult selectGodGft(String prmNo, String godNo)throws Exception {

		CartSearchDTO search = new CartSearchDTO();
		search.setImgSizeCd("90X120");
		search.setPrmNo(prmNo);
		search.setGodNo(godNo);
		return cartSelectService.selectGodGft(search);
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
	 * @param langCd [설명]
	 * @return String [설명]
	 * @throws Exception the exception
	 * @since 2015. 6. 19
	 */
	private String getCrncyCd(String langCd) throws Exception {

		String crncyCd = "KRW";
		if (!StringService.isEmpty(langCd)) {
			if ("CHI".equals(langCd)) {
				crncyCd = "CNY";
			}
			else if ("ENG".equals(langCd)) {
				crncyCd = "USD";
			}
			else if ("JPN".equals(langCd)) {
				crncyCd = "JPY";
			}
		}

		return crncyCd;
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
	public List<CartCpstGodResult> selectBskCpstGodCnnc(BskGod god) throws Exception {
		return cartSelectService.selectBskCpstGodCnnc(god);
	}

	@Override
	public List<GodItmQtyResult> selectGodItmQty(GodItmQtySearchDTO godItmQtySearchDTO) throws Exception {
		return cartSelectService.selectGodItmQty(godItmQtySearchDTO);
	}

	@Override
	public List<GodItmQtyResult> selectGodItmQtyForQdlv(GodItmQtySearchDTO godItmQtySearchDTO) throws Exception {
		return cartSelectService.selectGodItmQtyForQdlv(godItmQtySearchDTO);
	}

	@Override
	public ComQdlvGudTxtExtend selectComQdlvGudTxt(ComQdlvGudTxtExtend comQdlvGudTxtExtend) {
		return cartSelectService.selectComQdlvGudTxt(comQdlvGudTxtExtend);
	}

}
