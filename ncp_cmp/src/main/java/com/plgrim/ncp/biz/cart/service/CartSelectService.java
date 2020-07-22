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
package com.plgrim.ncp.biz.cart.service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Function;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.Multimaps;
import com.plgrim.ncp.base.abstracts.AbstractService;
import com.plgrim.ncp.base.entities.datasource1.bsk.Bsk;
import com.plgrim.ncp.base.entities.datasource1.bsk.BskGod;
import com.plgrim.ncp.base.entities.datasource1.bsk.BskGodExtend;
import com.plgrim.ncp.base.entities.datasource1.bsk.BskWishlst;
import com.plgrim.ncp.base.entities.datasource1.com.ComDmstcDlvCstPlc;
import com.plgrim.ncp.base.entities.datasource1.com.ComOvseaDlvCstPlc;
import com.plgrim.ncp.base.entities.datasource1.com.ComQdlvGudTxtExtend;
import com.plgrim.ncp.base.entities.datasource1.god.God;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsDlvsp;
import com.plgrim.ncp.base.entities.datasource1.prm.Prm;
import com.plgrim.ncp.base.entities.datasource1.sys.SysStplat;
import com.plgrim.ncp.base.enums.BskEnum;
import com.plgrim.ncp.base.enums.MemberEnum.MemberAtrbCd;
import com.plgrim.ncp.base.enums.MemberEnum.MemberTpCd;
import com.plgrim.ncp.base.enums.PromotionEnum.PromotionOrderDiscountType;
import com.plgrim.ncp.biz.cart.data.CartOptChgSearchDTO;
import com.plgrim.ncp.biz.cart.data.CartSearchDTO;
import com.plgrim.ncp.biz.cart.data.GodItmQtySearchDTO;
import com.plgrim.ncp.biz.cart.repository.CartSelectRepository;
import com.plgrim.ncp.biz.cart.result.CartBskNoForUpdateResult;
import com.plgrim.ncp.biz.cart.result.CartCpstGodResult;
import com.plgrim.ncp.biz.cart.result.CartGiftResult;
import com.plgrim.ncp.biz.cart.result.CartGodOptionResult;
import com.plgrim.ncp.biz.cart.result.CartGodPrmResult;
import com.plgrim.ncp.biz.cart.result.CartGodResult;
import com.plgrim.ncp.biz.cart.result.CartListResult;
import com.plgrim.ncp.biz.cart.result.CartOptChgGodResult;
import com.plgrim.ncp.biz.cart.result.CartPrmResult;
import com.plgrim.ncp.biz.cart.result.CartResult;
import com.plgrim.ncp.biz.cart.result.CartSimpleListResult;
import com.plgrim.ncp.biz.cart.result.GodInfoResult;
import com.plgrim.ncp.biz.cart.result.GodItmQtyResult;
import com.plgrim.ncp.biz.delivery.repository.DeliverySelectRepository;
import com.plgrim.ncp.biz.order.data.LgsDlvspDTO;
import com.plgrim.ncp.framework.commons.CollectionService;
import com.plgrim.ncp.framework.commons.StringService;

import lombok.extern.slf4j.Slf4j;

/**
 * [클래스 설명]
 *
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
@Service
public class CartSelectService extends AbstractService {

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	/** The cart select repository. */
	@Autowired
	CartSelectRepository cartSelectRepository;
	
	@Autowired
	DeliverySelectRepository deliverySelectRepository;
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
	 * @return Bsk [설명]
	 * @throws Exception the exception
	 * @since 2015. 6. 19
	 */
	public Bsk selectBskInfo(Bsk bsk) throws Exception {
		return cartSelectRepository.selectBskInfo(bsk);
	}

	/**
	 * [메서드 설명].
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param cartSearchDTO [설명]
	 * @return Cart list result [설명]
	 * @throws Exception the exception
	 * @since 2015. 6. 19
	 */
	public CartListResult selectCartGodList(CartSearchDTO cartSearchDTO) throws Exception {

		CartListResult cart = new CartListResult();
		cart.setMbrTpCd(cartSearchDTO.getMbr().getMbrTpCd());
		// 카트 상품 리스트
		List<CartGodResult> cartGodResultList = null;

		// 프로모션 정보 리스트
		List<CartPrmResult> bundlePrmCnd = new ArrayList<CartPrmResult>();

		// 배송 정책 리스트
		List<ComDmstcDlvCstPlc> comDmstcDlvCstPlc = new ArrayList<ComDmstcDlvCstPlc>();

		/**
		 * ncp 3차
		 * 해외국내배송 정책 리스트
		 */
		List<ComDmstcDlvCstPlc> ovseaComDmstcDlvCstPlc = new ArrayList<ComDmstcDlvCstPlc>();
		//해외배송 정책 리스트
		List<ComOvseaDlvCstPlc> comOvseaDlvCstPlc = new ArrayList<ComOvseaDlvCstPlc>();


		// 접속한 장비가 PC가 아닐 경우 이미지 사이즈를 90X120으로 정의 ==> 255X337로 변경 (SR#16714)
		if(!StringService.equalsIgnoreCase("PC", StringService.nvl(cartSearchDTO.getDevice(),"PC"))){
			cartSearchDTO.setImgSizeCd("255X337");
		}

		boolean isOrder = false;

		// 검색 카트 상품정보에 리스트가 있을 경우 isOrder를 TRUE로 세팅
		if(!CollectionService.isEmpty(cartSearchDTO.getBskGodList())){
			isOrder = true;
		}


		//1.장바구니 상품 정보 조회

		// 카트 조회정보의 배송정보가 픽업인 경우
		if(StringService.equalsIgnoreCase(BskEnum.DlvSect.PKUP_DLV.toString(), cartSearchDTO.getDlvSectCd())){
			//픽업 상품 조회
			cartGodResultList = cartSelectRepository.selectPkupCartList(cartSearchDTO);
		}else{
			// 예약장바구니 기능 추가  : by cannon (2016.04.18)
			if("RSV".equals(cartSearchDTO.getDlvSectCd())){
				cartSearchDTO.setResveSaleGodYn("Y");
			}else{
				cartSearchDTO.setResveSaleGodYn(cartSearchDTO.getResveSaleGodYn());
			}

			//일반배송 상품 조회
			cartGodResultList = cartSelectRepository.selectGnrlCartList(cartSearchDTO);
		}

		if(!cartGodResultList.isEmpty()){
			//그룹별 장바구니
			List<List<CartResult>> cartList = new ArrayList<List<CartResult>>();
			//묶음 할인
			List<String> bundlePrm = new ArrayList<String>();
			//장바구니 상품
			List<BskGodExtend> bskGodList = new ArrayList<BskGodExtend>();

			/**
			 * 회원 속성 코드
			ㅁ. 회원 속성 : MBR_ATRB
		   >. 일반 회원 : GNRL_MBR
		   >. 플그림 : plgrim
		   >. 플그림 패션 : PLGRIM_FSHN
		   >. 그룹사 : GRPCO
		   >. 특판 회원(B2E) : SPCSALE_MBR
			 */
			String mbrAtrbCd = cartSearchDTO.getMbr().getMbrAtrbCd();

			boolean isEmpMember   = false;
			boolean isVipMember   = false;
			String vipList = deliverySelectRepository.getVipList();
			if(cartSearchDTO.getMbr()!=null&&cartSearchDTO.getMbr().getErpCstmrNo()!=null&&vipList.indexOf(cartSearchDTO.getMbr().getErpCstmrNo())!=-1){
				isVipMember = true;
			}
			
			

			//상품할인
			List<CartGodPrmResult> gnlPrmResultList = null;

			// 상품할인 Map
			ListMultimap<String, CartGodPrmResult> gnlPrmListMap = ArrayListMultimap.create();

			// 즉시할인쿠폰 Map
			ListMultimap<String, CartGodPrmResult> cpnPrmListMap = ArrayListMultimap.create();


			//묶음할인
			List<CartGodPrmResult> bndPrmResultList = null;
			//교차할인
			List<CartGodPrmResult> crsPrmResultList = null;
			//즉시할인쿠폰
			List<CartGodPrmResult> cpnPrmResultList = null;
			//상품사은품
			List<CartGodPrmResult> giftPrmResultList = null;
			//주문사은품
			List<CartGiftResult> ordGiftPrmResultList = null;
			//추가적립급
			List<CartGodPrmResult> aditPrmResultList = new ArrayList<CartGodPrmResult>();
			//배송비즉시할인쿠폰 : by cannon (2016.06.07)
			List<CartGodPrmResult> dlvFeeImdtlCpnResultList = null;

			//실소가
			BigDecimal csmrPrice = BigDecimal.ZERO;
			//상품유형별 가격
			String prcType = "GNRL";
			BskGod bskGod = null;
			God god = null;
			int dlvGrpSeq = 0;
			
			//i마일리지회원과 그룹사 회원 체크
			if(StringService.isNotEmpty(cartSearchDTO.getMbr().getMbrNo())
			   && StringService.equalsIgnoreCase(mbrAtrbCd, MemberAtrbCd.EMP.toString())
			   && "Y".equals(cartSearchDTO.getEmpYn())){
				isEmpMember = true;
			}
			//판매중이면서 상품권이 아닌경우
			for(CartGodResult godDetail : cartGodResultList){
				if(StringService.equalsIgnoreCase(BskEnum.YES.toString(), godDetail.getSellYn())
					&& !StringService.equalsIgnoreCase("GFCT", godDetail.getGod().getGodTpCd())
				  ){
					//대표상품 이거나 추가구성상품 인 경우 장바구니 상품 리스트에 추가
					if(godDetail.getBskGod().getGodTurn() == godDetail.getParentGodTurn()
					   || StringService.equalsIgnoreCase(BskEnum.PckageGodTp.ADIT_CPST_GOD.toString(), godDetail.getPckageGodTpCd())){
						BskGodExtend tmpGod = new BskGodExtend();
						tmpGod.setBskNo(godDetail.getBskGod().getBskNo());
						tmpGod.setGodTurn(godDetail.getBskGod().getGodTurn());
						tmpGod.setGodNo(godDetail.getBskGod().getGodNo());
						tmpGod.setItmQty(godDetail.getBskGod().getItmQty());
						tmpGod.setCartSeq(godDetail.getGrpSeq());
						bskGodList.add(tmpGod);
					}
				}
			}
			// 임직원 가격 지정
			if(isEmpMember){
				for(CartGodResult godDetail : cartGodResultList){
					godDetail.setPrice(godDetail.getGod().getRtlPrc());
					godDetail.setPrcType("EMP");
				}
			}
			// 장바구니에 상품이 존재하는 경우
			if(!CollectionService.isEmpty(bskGodList)){

				cartSearchDTO.setBskgodExtendList(bskGodList);

				//2.프로모션 정보 전체 조회
				List<CartPrmResult> cartPrmResultList = null;
				if(!isEmpMember){
					cartPrmResultList = cartSelectRepository.selectBskPrmAll(cartSearchDTO);
				}

				// 그룹시퀀스 리스트
				List<String> grpSeqList = new ArrayList<String>();
				// 주문 프로모션 제외 대상 상품 리스트
				List<String> gdNoList = new ArrayList<String>();

				// 브랜드 프로모션 번호 리스트
				List<String> prmNoBndList = new ArrayList<String>();
				// 브랜드 프로모션 상품번호 리스트
				List<String> godNoBndList = new ArrayList<String>();
				// 브랜드 프로모션 단품 수량 리스트
				List<String> itmQtyBndList = new ArrayList<String>();

				if(!CollectionService.isEmpty(cartPrmResultList)){
					//일반 프로모션 번호 리스트
					List<String> prmNoGnlList = new ArrayList<String>();
					//일반 상품번호 리스트
					List<String> godNoGnlList = new ArrayList<String>();
					//즉시할인 쿠폰 프로모션  번호 리스트
					List<String> prmNoCpnList = new ArrayList<String>();
				    //즉시할인 쿠폰 상품 리스트
					List<String> godNoCpnList = new ArrayList<String>();
					//상품 사은품 프로모션 번호 리스트
					List<String> prmNoGodGftList = new ArrayList<String>();
					//상품 사은품 번호 리스트
					List<String> godNoGodGftList = new ArrayList<String>();
					//주문 사은품 프로모션 번호 리스트
					List<String> prmNoOrdGftList = new ArrayList<String>();
					//주문 사은품 상품 번호 리스트
					List<String> godNoOrdGftList = new ArrayList<String>();
					//주문 사은품 상품수량 리스트
					List<String> ordGftQtyGodList  = new ArrayList<String>();
					//상품별무료배송비쿠폰 리스트, 배송비즉시할인쿠폰 : by cannon (2016.06.07)
					List<String> prmNoDlvFeeImdtlCpnList	= new ArrayList<String>();
					List<String> godNoDlvFeeImdtlCpnList	= new ArrayList<String>();

					for(CartPrmResult prmList : cartPrmResultList){
						if(StringService.equalsIgnoreCase(BskEnum.BskPrmType.GOD_DC.toString(),prmList.getPrmTpCd())){ //상품 할인
							prmNoGnlList.add(prmList.getPrmNo());
							godNoGnlList.add(prmList.getGodNo());
						}else if(StringService.equalsIgnoreCase(BskEnum.BskPrmType.ORD_DC.toString(),prmList.getPrmTpCd())){
							if(StringService.equalsIgnoreCase(BskEnum.BskPrmDtlType.BUNDLE_DC.toString(),prmList.getPrmDtlTpCd())){ //묶음할인

								grpSeqList.add(prmList.getGrpSeq()+"");
								prmNoBndList.add(prmList.getPrmNo());
								godNoBndList.add(prmList.getGodNo());
								itmQtyBndList.add(prmList.getItmQty().toString());
								if(bundlePrm.isEmpty() || !bundlePrm.contains(prmList.getPrmNo())){
									bundlePrm.add(prmList.getPrmNo());
									bundlePrmCnd.add(prmList); //묶음 할인 조건
								}
							}
						}else if(StringService.isNotEmpty(cartSearchDTO.getMbr().getMbrNo()) // 사은품 회원만 적용
								&&StringService.equalsIgnoreCase(BskEnum.BskPrmType.GFT.toString(),prmList.getPrmTpCd())){ //사은품
							if(StringService.equalsIgnoreCase(BskEnum.BskPrmDtlType.GOD_GFT.toString(),prmList.getPrmDtlTpCd())){ //상품 사은품
								prmNoGodGftList.add(prmList.getPrmNo());
								godNoGodGftList.add(prmList.getGodNo());
							}else{  //주문 사은품
								if(isOrder){
									prmNoOrdGftList.add(prmList.getPrmNo());
									godNoOrdGftList.add(prmList.getGodNo());
									ordGftQtyGodList.add(prmList.getItmQty().toString());
								}
							}
						}else if(StringService.equalsIgnoreCase(BskEnum.BskPrmType.CPN.toString(),prmList.getPrmTpCd())){ //즉시할인 쿠폰 , 배송비무료쿠폰
							if(StringService.equalsIgnoreCase(BskEnum.BskPrmDtlType.GOD_CPN.toString(), prmList.getPrmDtlTpCd())){	//즉시할인쿠폰, 배송비즉시할인쿠폰 : by cannon (2016.06.07)
								prmNoCpnList.add(prmList.getPrmNo());
								godNoCpnList.add(prmList.getGodNo());
							}else if(StringService.equalsIgnoreCase(BskEnum.BskPrmDtlType.DLV_CST_CPN.toString(), prmList.getPrmDtlTpCd())){	// 배송비즉시할인쿠폰 : by cannon (2016.06.07)
								// 퀵배송일 경우는 퀵배송비 쿠폰만, 일반배송일 경우는 일반배송 쿠폰만 노출되도록 분기 처리 by Henry 2017.12.13
								if(!"QDLV".equals(cartSearchDTO.getDlvSectCd())){
									prmNoDlvFeeImdtlCpnList.add(prmList.getPrmNo());
									godNoDlvFeeImdtlCpnList.add(prmList.getGodNo());
								}
							}else if(StringService.equalsIgnoreCase(BskEnum.BskPrmDtlType.QDLV_CST_CPN.toString(), prmList.getPrmDtlTpCd())){	// 배송비즉시할인쿠폰 : by cannon (2016.06.07)
								// 퀵배송일 경우는 퀵배송비 쿠폰만, 일반배송일 경우는 일반배송 쿠폰만 노출되도록 분기 처리 by Henry 2017.12.13
								if("QDLV".equals(cartSearchDTO.getDlvSectCd())){
									prmNoDlvFeeImdtlCpnList.add(prmList.getPrmNo());
									godNoDlvFeeImdtlCpnList.add(prmList.getGodNo());
								}

							}
						}else if(StringService.equalsIgnoreCase(BskEnum.BskPrmType.MBSH_PNT.toString(),prmList.getPrmTpCd())
                                || StringService.equalsIgnoreCase(BskEnum.BskPrmType.WEBPNT.toString(), prmList.getPrmTpCd())){
                            if( isOrder
                                    && cartSearchDTO.getMbr() != null
                                    && StringService.isNotEmpty(cartSearchDTO.getMbr().getMbrNo())
                                    && ( StringService.equalsIgnoreCase(MemberTpCd.UNITY_MBR.toString(),cartSearchDTO.getMbr().getMbrTpCd()) || StringService.equalsIgnoreCase(MemberTpCd.ONLINE_MBR.toString(),cartSearchDTO.getMbr().getMbrTpCd()) )
                                    && StringService.equalsIgnoreCase(BskEnum.BskPrmDtlType.ADIT_SAV.toString(), prmList.getPrmDtlTpCd())){ //포인트 추가 적립
                                CartGodPrmResult adit = new CartGodPrmResult();
                                Prm aditPrm = new Prm();
                                aditPrm.setPrmNo(prmList.getPrmNo());
                                aditPrm.setPrmTpCd(prmList.getPrmTpCd());  /*프로모션유형*/
                                aditPrm.setAccmlSectCd(prmList.getAccmlSectCd());
                                aditPrm.setAccmlAmt(prmList.getAccmlAmt());
                                aditPrm.setAccmlRt(prmList.getAccmlRt());
                                adit.setGodNo(prmList.getGodNo());
                                adit.setPrm(aditPrm);
                                aditPrmResultList.add(adit); /*유형별 추가적립이벤트 입력 (추후 유형별 별도 리스트에 적재)*/
                            }
						}
					}

					//상품할인 조회
					if(!CollectionService.isEmpty(prmNoGnlList) && !CollectionService.isEmpty(godNoGnlList)){
//						상품수가 많은 경우 REGEXP_SUBSTR 쿼리에서 에러 발생 (2016.03.15 by cannon)
//						cartSearchDTO.setPrmNo(StringService.convertListtoCSV(prmNoGnlList));
//						cartSearchDTO.setGodNo(StringService.convertListtoCSV(godNoGnlList));

						ArrayList<CartPrmResult> paramList = new ArrayList<CartPrmResult>();
						for(int i=0; i<prmNoGnlList.size(); i++){
							CartPrmResult param = new CartPrmResult();
							param.setPrmNo(prmNoGnlList.get(i));
							param.setGodNo(godNoGnlList.get(i));
							paramList.add(param);
						}

						cartSearchDTO.setCartPrmResultList(paramList);
						gnlPrmResultList = cartSelectRepository.selectCartGodsGnlPrm(cartSearchDTO);
					}

					//즉시할인쿠폰 조회
					if(StringService.isNotEmpty(cartSearchDTO.getMbr().getMbrNo())){
						if(!CollectionService.isEmpty(prmNoCpnList) && !CollectionService.isEmpty(godNoCpnList)){
//							상품수가 많은 경우 REGEXP_SUBSTR 쿼리에서 에러 발생 (2016.03.15 by cannon)
//							cartSearchDTO.setPrmNo(StringService.convertListtoCSV(prmNoCpnList));
//							cartSearchDTO.setGodNo(StringService.convertListtoCSV(godNoCpnList));

							ArrayList<CartPrmResult> paramList = new ArrayList<CartPrmResult>();
							for(int i=0; i<prmNoCpnList.size(); i++){
								CartPrmResult param = new CartPrmResult();
								param.setPrmNo(prmNoCpnList.get(i));
								param.setGodNo(godNoCpnList.get(i));
								paramList.add(param);
							}

							cartSearchDTO.setCartPrmResultList(paramList);
							cpnPrmResultList = cartSelectRepository.selectImdtlApplcCpn(cartSearchDTO);
						}
					}

					//상품 사은품 조회 - 픽업상품은 사은품 없음
					if(!CollectionService.isEmpty(prmNoGodGftList)
							&&!BskEnum.DlvSect.PKUP_DLV.toString().equals(cartSearchDTO.getDlvSectCd())
							&&!isEmpMember
							&&!isVipMember
							){
//						상품수가 많은 경우 REGEXP_SUBSTR 쿼리에서 에러 발생 (2016.03.15 by cannon)
//						cartSearchDTO.setPrmNo(StringService.convertListtoCSV(prmNoGodGftList));
//						cartSearchDTO.setGodNo(StringService.convertListtoCSV(godNoGodGftList));

						ArrayList<CartPrmResult> paramList = new ArrayList<CartPrmResult>();
						for(int i=0; i<prmNoGodGftList.size(); i++){
							CartPrmResult param = new CartPrmResult();
							param.setPrmNo(prmNoGodGftList.get(i));
							param.setGodNo(godNoGodGftList.get(i));
							paramList.add(param);
						}

						cartSearchDTO.setCartPrmResultList(paramList);
						giftPrmResultList =  cartSelectRepository.selectGodGift(cartSearchDTO);

					}

					//주문 사은품 조회 - 픽업상품은 사은품 없음
					if(isOrder && !CollectionService.isEmpty(prmNoOrdGftList) && !CollectionService.isEmpty(godNoOrdGftList)
							&&!BskEnum.DlvSect.PKUP_DLV.toString().equals(cartSearchDTO.getDlvSectCd())
							&&!isEmpMember
							&&!isVipMember
							){
//						상품수가 많은 경우 REGEXP_SUBSTR 쿼리에서 에러 발생 (2016.03.15 by cannon)
//						cartSearchDTO.setPrmNo(StringService.convertListtoCSV(prmNoOrdGftList));
//						cartSearchDTO.setGodNo(StringService.convertListtoCSV(godNoOrdGftList));
//						cartSearchDTO.setItmQty(StringService.convertListtoCSV(ordGftQtyGodList));

						ArrayList<CartPrmResult> paramList = new ArrayList<CartPrmResult>();
						for(int i=0; i<prmNoOrdGftList.size(); i++){
							CartPrmResult param = new CartPrmResult();
							param.setPrmNo(prmNoOrdGftList.get(i));
							param.setGodNo(godNoOrdGftList.get(i));
							param.setItmQty(Integer.valueOf(ordGftQtyGodList.get(i)));
							paramList.add(param);
						}

						cartSearchDTO.setCartPrmResultList(paramList);
						ordGiftPrmResultList = cartSelectRepository.selectOrdGift(cartSearchDTO);
					}

					//배송비즉시할인쿠폰 조회,  배송비즉시할인쿠폰 : by cannon (2016.06.07)
					if(StringService.isNotEmpty(cartSearchDTO.getMbr().getMbrNo())){
						if(!CollectionService.isEmpty(prmNoDlvFeeImdtlCpnList) && !CollectionService.isEmpty(godNoDlvFeeImdtlCpnList)){
							ArrayList<CartPrmResult> paramList = new ArrayList<CartPrmResult>();
							for(int i=0; i<prmNoDlvFeeImdtlCpnList.size(); i++){
								CartPrmResult param = new CartPrmResult();
								param.setPrmNo(prmNoDlvFeeImdtlCpnList.get(i));
								param.setGodNo(godNoDlvFeeImdtlCpnList.get(i));
								paramList.add(param);
							}

							cartSearchDTO.setCartPrmResultList(paramList);
							dlvFeeImdtlCpnResultList = cartSelectRepository.selectImdtlApplcCpn(cartSearchDTO);	//상품즉시할인조회활용
						}
						cart.setDlvFeeImdtlCpnResultList(dlvFeeImdtlCpnResultList);
					}
				}


				// 상품할인 Map
				if(!CollectionService.isEmpty(gnlPrmResultList)){
					gnlPrmListMap = Multimaps.index(
												gnlPrmResultList,
										        new Function<CartGodPrmResult, String>() {
										            @Override
										            public String apply(CartGodPrmResult prm) {
											            return prm.getGodNo();
										            }
										        }
											);
				}

				// 즉시할인쿠폰 Map
				if(!CollectionService.isEmpty(cpnPrmResultList)){
					cpnPrmListMap = Multimaps.index(
												cpnPrmResultList,
										        new Function<CartGodPrmResult, String>() {
										            @Override
										            public String apply(CartGodPrmResult prm) {
											            return prm.getGodNo();
										            }
										        }
											);
				}


				

				//묶음할인 조회
				if(!CollectionService.isEmpty(prmNoBndList)){
//					상품수가 많은 경우 REGEXP_SUBSTR 쿼리에서 에러 발생 (2016.03.15 by cannon)
//					cartSearchDTO.setGrpSeq(StringService.convertListtoCSV(grpSeqList));
//					cartSearchDTO.setPrmNo(StringService.convertListtoCSV(prmNoBndList));
//					cartSearchDTO.setGodNo(StringService.convertListtoCSV(godNoBndList));
//					cartSearchDTO.setItmQty(StringService.convertListtoCSV(itmQtyBndList));
//

					ArrayList<CartPrmResult> paramList = new ArrayList<CartPrmResult>();
					for(int i=0; i<prmNoBndList.size(); i++){
						CartPrmResult param = new CartPrmResult();
						param.setGrpSeq(Integer.parseInt(grpSeqList.get(i)));
						param.setPrmNo(prmNoBndList.get(i));
						param.setGodNo(godNoBndList.get(i));
						param.setItmQty(Integer.valueOf(itmQtyBndList.get(i)));
						paramList.add(param);
					}

					cartSearchDTO.setCartPrmResultList(paramList);

					//20160411 - 글로벌 묶음할인 제외(기획요건)
					if(true) {
						bndPrmResultList = cartSelectRepository.selectCartGodsBundlePrm(cartSearchDTO);
					}

				}

				//20160411 - 글로벌 교차할인 제외(기획요건)
				if(true) {
					// 교차 할인 조회
					crsPrmResultList = cartSelectRepository.selectCrsPrm(cartSearchDTO);
				}
			}

			List<CartResult> resultList = null;
			CartResult cartResult = null;
			int grpSeq = 0;
			// 장바구니 프로모션 Map
			HashMap<String,CartGodPrmResult> bskPrmMap = new HashMap<String,CartGodPrmResult>();
			CartGodPrmResult tmpDtl = null;
			for(CartGodResult godDetail : cartGodResultList){
				// 그룹 시퀀스가 다른 경우 장바구니 리스트에 들어갈 리스트 생성
				if(grpSeq != godDetail.getGrpSeq()){
					grpSeq = godDetail.getGrpSeq();
					resultList = new ArrayList<CartResult>();
					cartList.add(resultList);
				}

				// 대표상품이거나 추가구성 상품인 경우 프로모션 적용
				if(godDetail.getBskGod().getGodTurn() == godDetail.getParentGodTurn()
				  || StringService.equalsIgnoreCase(BskEnum.PckageGodTp.ADIT_CPST_GOD.toString(), godDetail.getPckageGodTpCd())
				 ){
					bskGod = godDetail.getBskGod();
					god = godDetail.getGod();
					csmrPrice = god.getCsmrPrc();
					prcType = StringService.isEmpty(godDetail.getPrcType()) ? "GNRL" : godDetail.getPrcType();
					cartResult = new CartResult();

					// 판매중인 상품만 프로모션 적용
					if(StringService.equalsIgnoreCase(BskEnum.YES.toString(), godDetail.getSellYn())){
						/* ===========================================================================================
						 * 프로모션 START
						 * ===========================================================================================*/
						/* ===========================================================================================
						 * 1.상품 할인
						 * ===========================================================================================*/

						//프로모션 중복 적용을 위한 임시 가격
						BigDecimal tmpPrice = csmrPrice;
						tmpPrice = this.setGodPromotion(cartResult, godDetail, prcType, gnlPrmListMap, cartSearchDTO.getCrncyCd(), csmrPrice, tmpPrice);

						//포인트 추가 적립
						if(isOrder
						   && aditPrmResultList.size() > 0){
							BigDecimal accmlAmt = BigDecimal.ZERO;
							BigDecimal tmpAmt = BigDecimal.ZERO;
							for(CartGodPrmResult prmDtl : aditPrmResultList){
								if(StringService.equalsIgnoreCase(prmDtl.getGodNo(), bskGod.getGodNo())){

									if(StringService.equalsIgnoreCase(BskEnum.BskDcSect.FIXRT.toString(), prmDtl.getPrm().getAccmlSectCd())){
										tmpAmt = tmpPrice.multiply(prmDtl.getPrm().getAccmlRt().multiply(new BigDecimal(0.01),MathContext.DECIMAL32));
									}else{
										tmpAmt = prmDtl.getPrm().getAccmlAmt();	//적립 금액	적립 유형 정액에 다른 적립 금액

									}

									if(accmlAmt.compareTo(tmpAmt) < 0){
										accmlAmt = tmpAmt;
//20160325
//										cartResult.setAditSavPrmNo(prmDtl.getPrm().getPrmNo());
//										cartResult.setAccmlSectCd(prmDtl.getPrm().getAccmlSectCd());
//										/*적립 금액 : 적립 유형 정액에 다른 적립 금액 */
//										cartResult.setAccmlAmt(prmDtl.getPrm().getAccmlAmt());
//										/*적립 율: 적립 유형 정률에 다른 적립 비율*/
//										cartResult.setAccmlRt(prmDtl.getPrm().getAccmlRt());
										if ( prmDtl.getPrm().getPrmTpCd().equals("MBSH_PNT")) {
											cartResult.setAditSavPrmNo(prmDtl.getPrm().getPrmNo());
											cartResult.setAccmlSectCd(prmDtl.getPrm().getAccmlSectCd());
										/*적립 금액 : 적립 유형 정액에 다른 적립 금액 */
											cartResult.setAccmlAmt(prmDtl.getPrm().getAccmlAmt());
										/*적립 율: 적립 유형 정률에 다른 적립 비율*/
											cartResult.setAccmlRt(prmDtl.getPrm().getAccmlRt());
										} else if (prmDtl.getPrm().getPrmTpCd().equals("WEBPNT")) {
											cartResult.setWebAditSavPrmNo(prmDtl.getPrm().getPrmNo());
											cartResult.setWebAccmlSectCd(prmDtl.getPrm().getAccmlSectCd());
											cartResult.setWebAccmlAmt(prmDtl.getPrm().getAccmlAmt());
											cartResult.setWebAccmlRt(prmDtl.getPrm().getAccmlRt());
										}
									}

								}
							}
						}

						//TO-BE 할인 프로모션 처리
						this.setDiscountPrm(	cartResult, 		bskGod,			bndPrmResultList,  	crsPrmResultList, 	cpnPrmResultList,
												bskPrmMap,  		tmpDtl,			godDetail,			god,				prcType,
												tmpPrice,			cartSearchDTO
								);

						/* ===========================================================================================
						 * 6.사은품
						 * ===========================================================================================*/
						//상품 사은품
						if("N".equals(cartSearchDTO.getVirtlDlvComptYn())){
							if(!CollectionService.isEmpty(giftPrmResultList)){
								for(CartGodPrmResult prmDtl : giftPrmResultList){
									if(StringService.equalsIgnoreCase(prmDtl.getGodNo(), bskGod.getGodNo())){
										cartResult.setGodGftPrmNo(prmDtl.getPrm().getPrmNo());
										cartResult.setGodGftList(prmDtl.getGiftList());
									}
								}
							}
						}

						// 주문 사은품
						if("N".equals(cartSearchDTO.getVirtlDlvComptYn()) && isOrder && !CollectionService.isEmpty(ordGiftPrmResultList)){
							for(CartGiftResult prmDtl : ordGiftPrmResultList){
								//금액 기준일 경우
								if(StringService.equalsIgnoreCase("AMT_STDR", prmDtl.getPrm().getOrdGftPchStdrCd())){
									for(BskGod bgd : prmDtl.getBskGod()){
										if(StringService.equalsIgnoreCase(bgd.getGodNo(), bskGod.getGodNo())){
											if(StringService.equalsIgnoreCase("EMP", prcType)){
												prmDtl.setOrdAmt(prmDtl.getOrdAmt().add(godDetail.getPrice().multiply(new BigDecimal(bskGod.getItmQty()))));
											}else{
												if(StringService.isEmpty(cartResult.getGodPrmNo())){
													prmDtl.setOrdAmt(prmDtl.getOrdAmt().add(god.getCsmrPrc().multiply(new BigDecimal(bskGod.getItmQty()))));
												}else{
													prmDtl.setOrdAmt(prmDtl.getOrdAmt().add(god.getCsmrPrc().subtract(cartResult.getGodPrmDcAmt()).multiply(new BigDecimal(bskGod.getItmQty()))));
												}
											}
											break;
										}
									}
								}
							}
						}

						/* ===========================================================================================
						 * 프로모션 END
						 * ===========================================================================================*/
					}

					
					this.checkInventory(bskGod, cartGodResultList, godDetail, cartResult, god, cartSearchDTO);
					

					//국내 배송 정책
					if( StringService.equalsIgnoreCase(BskEnum.YES.toString(), godDetail.getSellYn())
						&& (StringService.equalsIgnoreCase(BskEnum.DlvSect.GNRL_DLV.toString(), cartSearchDTO.getDlvSectCd())
							|| StringService.equalsIgnoreCase("RSV", cartSearchDTO.getDlvSectCd())// 예약장바구니 기능 추가  : by cannon (2016.04.18)
							|| StringService.equalsIgnoreCase("QDLV", cartSearchDTO.getDlvSectCd())) // quick
						&& bskGod.getGodTurn() == godDetail.getParentGodTurn()
						&& godDetail.getComDmstcDlvCstPlc() !=null){
						cartResult.setDlvCstLevySectCd(godDetail.getComDmstcDlvCstPlc().getDlvCstLevySectCd());
						cartResult.setDmstcDlvCst(godDetail.getComDmstcDlvCstPlc().getDmstcDlvCst());
						cartResult.setDmstcDlvCstExmStdrAmt(godDetail.getComDmstcDlvCstPlc().getDmstcDlvCstExmStdrAmt());

						cartResult.setQdlvCstLevySectCd(godDetail.getComDmstcDlvCstPlc().getQdlvCstLevySectCd());
						cartResult.setQdlvCstExmStdrAmt(godDetail.getComDmstcDlvCstPlc().getQdlvCstExmStdrAmt());
						cartResult.setQdlvCst(godDetail.getComDmstcDlvCstPlc().getQdlvCst());

						if(godDetail.getOvseaComDmstcDlvCstPlc()!=null){
							cartResult.setOvseaDmstcDlvCstLevySectCd(godDetail.getOvseaComDmstcDlvCstPlc().getDlvCstLevySectCd());
							cartResult.setOvseaDmstcDlvCst(godDetail.getOvseaComDmstcDlvCstPlc().getDmstcDlvCst());
							cartResult.setOvseaDmstcDlvCstExmStdrAmt(godDetail.getOvseaComDmstcDlvCstPlc().getDmstcDlvCstExmStdrAmt());
						}
						if(godDetail.getComOvseaDlvCstPlc()!=null){
							cartResult.setOvseadlvCstLevySectCd(godDetail.getComOvseaDlvCstPlc().getDlvCstLevySectCd());
							cartResult.setOvseaDlvCstExmStdrAmt(godDetail.getComOvseaDlvCstPlc().getOvseaDlvCstExmStdrAmt());
						}

						if(dlvGrpSeq != godDetail.getSubGrpSeq()){
							comDmstcDlvCstPlc.add(godDetail.getComDmstcDlvCstPlc());

							//ncp 3차 ********************************************************************************************
							ovseaComDmstcDlvCstPlc.add(godDetail.getOvseaComDmstcDlvCstPlc());
							comOvseaDlvCstPlc.add(godDetail.getComOvseaDlvCstPlc());
							//ncp 3차 ********************************************************************************************

							dlvGrpSeq = godDetail.getSubGrpSeq();
						}
					}

					resultList.add(cartResult);
				}
			}

			if(!bskPrmMap.isEmpty()){
				Iterator<String> iterator = bskPrmMap.keySet().iterator();
				List<CartGodPrmResult> bskPrmList = new ArrayList<CartGodPrmResult>();

				// 주문 할인 총 할인 금액 계산
				while(iterator.hasNext()){
					tmpDtl = bskPrmMap.get(iterator.next());
					if(tmpDtl.getOrdAmt().compareTo(BigDecimal.ZERO) > 0){
						if(StringService.equalsIgnoreCase("BUNDLE_DC", tmpDtl.getPrm().getPrmDtlTpCd())){
							if(StringService.isNotEmpty(tmpDtl.getPrm().getDcSectCd())){
								if(StringService.equalsIgnoreCase(BskEnum.BskDcSect.FIXAMT.toString(),tmpDtl.getPrm().getDcSectCd())){

									if(tmpDtl.getOrdAmt().compareTo(tmpDtl.getPrm().getDcAmt()) < 0){
										tmpDtl.setTotalDcAmt(tmpDtl.getPrm().getDcAmt().subtract(tmpDtl.getPrm().getDcAmt().subtract(tmpDtl.getOrdAmt())));
									}else{
										tmpDtl.setTotalDcAmt(tmpDtl.getPrm().getDcAmt());
									}
								}else if(StringService.equalsIgnoreCase(BskEnum.BskDcSect.FIXRT.toString(),tmpDtl.getPrm().getDcSectCd())){
									tmpDtl.setTotalDcAmt(this.down(tmpDtl.getOrdAmt().multiply(tmpDtl.getPrm().getDcRt().multiply(new BigDecimal(0.01)),MathContext.DECIMAL32),cartSearchDTO.getCrncyCd()));
								}

								tmpDtl.setGodItmQty(tmpDtl.getBundleQty());
								bskPrmList.add(tmpDtl);
							}
						}else{
							if(tmpDtl.getGodItmQty() > 0 && tmpDtl.getOrdAmt().compareTo(BigDecimal.ZERO) > 0){
								tmpDtl.setTotalDcAmt(this.getPrmDcAmt(tmpDtl.getPrm(),tmpDtl.getOrdAmt(),cartSearchDTO.getCrncyCd(),false));
								bskPrmList.add(tmpDtl);
							}
						}
					}
				}

				int totalImtQty = 0;
				BigDecimal tmpPrice = BigDecimal.ZERO;
				BigDecimal tmpBalancePrice = BigDecimal.ZERO;
				BigDecimal totalDcAmt = BigDecimal.ZERO;
				BigDecimal useDcAmt = BigDecimal.ZERO;
				BigDecimal realAmt = BigDecimal.ZERO;
				List<String> godNoList = null;
	            // 주문 할인 금액 분배 및 즉시할인 쿠폰 적용
				for(CartGodPrmResult prmDtl : bskPrmList){
					//#37164 [확인 및 수정요청] [긴급] 묶음할인 프로모션 할인율 오류 확인의 건
					//동일 GODNO의 상품이 들어올 경우 for 문이 배수로 더돌아 HashSet 사용하여 적용대상상품 리스트 중복제거/2017-02-22
					godNoList = new ArrayList<String>( new HashSet<String>(prmDtl.getGodNoList()));
					totalImtQty = prmDtl.getGodItmQty();
					totalDcAmt = prmDtl.getTotalDcAmt();
					useDcAmt = BigDecimal.ZERO;
					for(String godNo : godNoList){
						for(CartResult list : resultList){
							if(StringService.isNotEmpty(list.getBskPrmNo())
							   && list.getBskPrmNo().equals(prmDtl.getPrm().getPrmNo())
							   && list.getGrpSeq() == prmDtl.getGrpSeq()
							   && StringService.equalsIgnoreCase(godNo, list.getBskGod().getGodNo())){
								bskGod = list.getBskGod();
								realAmt = this.down((totalDcAmt.multiply(list.getPrice().subtract(StringService.isNotEmpty(list.getGodPrmNo()) ? list.getGodPrmDcAmt() : BigDecimal.ZERO))).divide(prmDtl.getOrdAmt(),MathContext.DECIMAL32), cartSearchDTO.getCrncyCd());
								useDcAmt = useDcAmt.add(realAmt.multiply(new BigDecimal(bskGod.getItmQty())));
								list.setBskPrmAplyDcAmt(realAmt);
								list.setBskPrmAplyBalancdDcAmt(realAmt);

								totalImtQty = totalImtQty - bskGod.getItmQty().intValue();

								if(totalImtQty == 0){
									if(totalDcAmt.compareTo(useDcAmt) > 0){
										list.setIsBalancdTarget("Y");
										list.setBskPrmAplyBalancdDcAmt(realAmt.add(totalDcAmt.subtract(useDcAmt)));
									}else if(totalDcAmt.compareTo(useDcAmt) < 0){
										list.setIsBalancdTarget("Y");
										list.setBskPrmAplyBalancdDcAmt(realAmt.subtract(useDcAmt.subtract(totalDcAmt)));
									}
								}

								tmpPrice = list.getPrice().subtract(StringService.isNotEmpty(list.getGodPrmNo()) ? list.getGodPrmDcAmt() : BigDecimal.ZERO).subtract(realAmt);
								tmpBalancePrice = list.getPrice().subtract(StringService.isNotEmpty(list.getGodPrmNo()) ? list.getGodPrmDcAmt() : BigDecimal.ZERO).subtract(list.getBskPrmAplyBalancdDcAmt());

								list.setCpnAplyDcAmt(BigDecimal.ZERO);
								list.setCpnAplyBalancedDcAmt(BigDecimal.ZERO);

								//쿠폰 적용 금액 재계산
								if(StringService.equalsIgnoreCase("GNRL", list.getPrcType())){
									if(!CollectionService.isEmpty(cpnPrmResultList)){
										BigDecimal cpnAplyDcAmt = BigDecimal.ZERO;

										for(CartGodPrmResult cpnDtl : cpnPrmResultList){
											if(StringService.equalsIgnoreCase(cpnDtl.getGodNo(), bskGod.getGodNo())){

												if(StringService.equalsIgnoreCase("IMPS", cpnDtl.getPrm().getGodDcDplctCd())
												   && StringService.isNotEmpty(list.getGodPrmNo())
												  ){
													 continue;
												  }

												if(tmpPrice.compareTo(BigDecimal.ZERO) > 0){
													cpnAplyDcAmt = this.getPrmDcAmt(cpnDtl.getPrm(),tmpPrice,cartSearchDTO.getCrncyCd(),true);
													if(list.getCpnAplyDcAmt().compareTo(cpnAplyDcAmt) < 0){
														list.setCpnNo(cpnDtl.getPrm().getPrmNo());
														list.setCpnNm(cpnDtl.getPrm().getPrmNm());
														list.setCpnType(cpnDtl.getPrm().getPrmDtlTpCd());
														list.setCpnMaxDcAmt(cpnDtl.getPrm().getMaxDcPsbAmt());
														list.setCpnDcSectCd(cpnDtl.getPrm().getDcSectCd());
														list.setCpnDcAmt(cpnDtl.getPrm().getDcAmt());
														list.setCpnDcRt(cpnDtl.getPrm().getDcRt());
														list.setGodDcDplctCd(cpnDtl.getPrm().getGodDcDplctCd());
														list.setCpnAplyDcAmt(cpnAplyDcAmt);
														list.setCpnAplyBalancedDcAmt(this.getPrmDcAmt(cpnDtl.getPrm(),tmpBalancePrice,cartSearchDTO.getCrncyCd(),true));
													}
												}else{
													break;
												}
											}
										}
									}
								}

							}

						}

					}
					if(PromotionOrderDiscountType.BUNDLE_DC.toString().equals(prmDtl.getPrm().getPrmDtlTpCd())){ // 묶음 할인만
						cart.setBskPrmGodNoList(godNoList);
					}	

				}
				
			}
			cart.setCartList(cartList);

			if(isOrder && !CollectionService.isEmpty(ordGiftPrmResultList)){

				List<CartGiftResult> ordGftList = new ArrayList<CartGiftResult>();

				for(CartGiftResult prmDtl : ordGiftPrmResultList){
					if(StringService.equalsIgnoreCase("AMT_STDR", prmDtl.getPrm().getOrdGftPchStdrCd())){
						if(prmDtl.getOrdAmt().compareTo(prmDtl.getPrm().getOrdGftAplCndAmt()) >= 0){
							ordGftList.add(prmDtl);
						}
					}else{
						ordGftList.add(prmDtl);
					}
				}

				if(ordGftList.size() > 0){
					cart.setOrdGiftList(ordGftList);
				}
			}

		}

		cart.setBundlePrmCnd(bundlePrmCnd);
		cart.setComDmstcDlvCstPlc(comDmstcDlvCstPlc);

		//ncp 3차 ********************************************************************************************
		cart.setOvseaComDmstcDlvCstPlc(ovseaComDmstcDlvCstPlc);
		cart.setComOvseaDlvCstPlc(comOvseaDlvCstPlc);
		//ncp 3차 ********************************************************************************************


		return cart;

	}

	/*
	 * ==============================================================================
	 * */
	
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
	public List<CartResult> selectCartGodValid(CartSearchDTO cartSearchDTO) throws Exception {

		List<CartResult> cartList = new ArrayList<CartResult>();
		List<CartGodResult> cartGodResultList = null;

		//17.7.26 이진형 추가 S
		GodItmQtySearchDTO giqsd = new GodItmQtySearchDTO();
		giqsd.setDlvSectCd(cartSearchDTO.getDlvSectCd());
		//17.7.26 이진형 추가 E

		// 접속한 장비가 PC가 아닐 경우 이미지 사이즈를 90X120으로 정의 ==> 255X337로 변경 (SR#16714)
		if(!StringService.equalsIgnoreCase("PC", StringService.nvl(cartSearchDTO.getDevice(),"PC"))){
			cartSearchDTO.setImgSizeCd("255X337");
		}

		
		//1.장바구니 상품 정보 조회
		cartGodResultList = getCartGodResultList(cartSearchDTO);
		/*if(StringService.equalsIgnoreCase(BskEnum.DlvSect.PKUP_DLV.toString(), cartSearchDTO.getDlvSectCd())){
			cartGodResultList = cartSelectRepository.selectPkupCartList(cartSearchDTO);
		}else{
			cartGodResultList = cartSelectRepository.selectGnrlCartList(cartSearchDTO);
		}*/

		if(!cartGodResultList.isEmpty()){
			List<BskGodExtend> bskGodList = new ArrayList<BskGodExtend>();
			List<CartGodPrmResult> gnlPrmResultList = null;
			List<CartGodPrmResult> cpnPrmResultList = null;
			String mbrAtrbCd = cartSearchDTO.getMbr().getMbrAtrbCd();
			boolean isEmpMember   = false;
			boolean isB2EMember   = false;
			boolean isSigleMember = false;

			if(StringService.isNotEmpty(cartSearchDTO.getEpUserid())){
				isSigleMember = true;
			}

			if(StringService.isNotEmpty(cartSearchDTO.getB2eCrtfcKey())){
				isB2EMember = true;
			}

			if(StringService.isNotEmpty(cartSearchDTO.getMbr().getMbrNo())
			   && StringService.equalsIgnoreCase(mbrAtrbCd, MemberAtrbCd.EMP.toString())
			   && "Y".equals(cartSearchDTO.getEmpYn())
			  ){
				isEmpMember = true;

			}

			BskGodExtend tmpGod = null;
			for(CartGodResult godDetail : cartGodResultList){
				//판매중 상품을 대상으로 프로모션 조회
				//추가 구성 상품도 프로모션 적용
				if(StringService.equalsIgnoreCase(BskEnum.YES.toString(), godDetail.getSellYn())
				   && !StringService.equalsIgnoreCase("GFCT", godDetail.getGod().getGodTpCd())){

					if(godDetail.getBskGod().getGodTurn() == godDetail.getParentGodTurn()
					   || StringService.equalsIgnoreCase(BskEnum.PckageGodTp.ADIT_CPST_GOD.toString(), godDetail.getPckageGodTpCd())){
						tmpGod = new BskGodExtend();
						tmpGod.setBskNo(godDetail.getBskGod().getBskNo());
						tmpGod.setGodTurn(godDetail.getBskGod().getGodTurn());
						tmpGod.setGodNo(godDetail.getBskGod().getGodNo());
						tmpGod.setItmQty(godDetail.getBskGod().getItmQty());
						tmpGod.setCartSeq(godDetail.getGrpSeq());
						bskGodList.add(tmpGod);
					}
				}
			}

			if(!CollectionService.isEmpty(bskGodList)){
				cartSearchDTO.setBskgodExtendList(bskGodList);

				//2.프로모션 정보 전체 조회
				List<CartPrmResult> cartPrmResultList = cartSelectRepository.selectBskPrmAll(cartSearchDTO);

				if(!CollectionService.isEmpty(cartPrmResultList)){
					List<String> prmNoGnlList = new ArrayList<String>();
					List<String> godNoGnlList = new ArrayList<String>();

					List<String> prmNoCpnList = new ArrayList<String>();
					List<String> godNoCpnList = new ArrayList<String>();

					for(CartPrmResult prmList : cartPrmResultList){
						if(StringService.equalsIgnoreCase(BskEnum.BskPrmType.GOD_DC.toString(),prmList.getPrmTpCd())){ //상품 할인
							prmNoGnlList.add(prmList.getPrmNo());
							godNoGnlList.add(prmList.getGodNo());
						}else if(StringService.equalsIgnoreCase(BskEnum.BskPrmType.CPN.toString(),prmList.getPrmTpCd())){ //즉시할인 쿠폰
							if(StringService.equalsIgnoreCase(BskEnum.BskPrmDtlType.GOD_CPN.toString(), prmList.getPrmDtlTpCd())){ // 배송비즉시할인쿠폰 : by cannon (2016.06.07)
								prmNoCpnList.add(prmList.getPrmNo());
								godNoCpnList.add(prmList.getGodNo());
							}
						}
					}

					//즉시할인쿠폰 조회
					if(StringService.isNotEmpty(cartSearchDTO.getMbr().getMbrNo())){
						if(!CollectionService.isEmpty(prmNoCpnList) && !CollectionService.isEmpty(godNoCpnList)){
//							상품수가 많은 경우 REGEXP_SUBSTR 쿼리에서 에러 발생 (2016.03.15 by cannon)
//							cartSearchDTO.setPrmNo(StringService.convertListtoCSV(prmNoCpnList));
//							cartSearchDTO.setGodNo(StringService.convertListtoCSV(godNoCpnList));

							ArrayList<CartPrmResult> paramList = new ArrayList<CartPrmResult>();
							for(int i=0; i<prmNoCpnList.size(); i++){
								CartPrmResult param = new CartPrmResult();
								param.setPrmNo(prmNoCpnList.get(i));
								param.setGodNo(godNoCpnList.get(i));
								paramList.add(param);
							}

							cartSearchDTO.setCartPrmResultList(paramList);
							cpnPrmResultList = cartSelectRepository.selectImdtlApplcCpn(cartSearchDTO);
						}
					}

					//상품할인 조회
					if(!CollectionService.isEmpty(prmNoGnlList) && !CollectionService.isEmpty(godNoGnlList)){
//						상품수가 많은 경우 REGEXP_SUBSTR 쿼리에서 에러 발생 (2016.03.15 by cannon)
//						cartSearchDTO.setPrmNo(StringService.convertListtoCSV(prmNoGnlList));
//						cartSearchDTO.setGodNo(StringService.convertListtoCSV(godNoGnlList));

						ArrayList<CartPrmResult> paramList = new ArrayList<CartPrmResult>();
						for(int i=0; i<prmNoGnlList.size(); i++){
							CartPrmResult param = new CartPrmResult();
							param.setPrmNo(prmNoGnlList.get(i));
							param.setGodNo(godNoGnlList.get(i));
							paramList.add(param);
						}

						cartSearchDTO.setCartPrmResultList(paramList);
						gnlPrmResultList = cartSelectRepository.selectCartGodsGnlPrm(cartSearchDTO);
					}
				}

				//B2E회원의 경우 B2E할인 적용된 상품은 장바구니 할인 불가하므로 묶음할인의 수량 및 교차할인의 그룹 조정 필요
				BigDecimal tmpPrice = BigDecimal.ZERO;
				BigDecimal dcAmt = BigDecimal.ZERO;
				String dcType = "";

				if(isB2EMember || isSigleMember || isEmpMember){
					BigDecimal clubAmt = BigDecimal.ZERO;
					BigDecimal b2eTmpPrice = BigDecimal.ZERO;
					BigDecimal singleTmpPrice = BigDecimal.ZERO;
					BigDecimal tmpEmpPrice = BigDecimal.ZERO;
					BigDecimal comparePrice = BigDecimal.ZERO;
					String comparePriceType = "GNRL";

					for(CartGodResult godDetail : cartGodResultList){
						if(!StringService.equalsIgnoreCase("GFCT", godDetail.getGod().getGodTpCd())){
							if(StringService.equalsIgnoreCase(BskEnum.YES.toString(), godDetail.getSellYn())
							   && (godDetail.getBskGod().getGodTurn() == godDetail.getParentGodTurn()
							   || StringService.equalsIgnoreCase(BskEnum.PckageGodTp.ADIT_CPST_GOD.toString(), godDetail.getPckageGodTpCd()))){

								tmpPrice = godDetail.getGod().getCsmrPrc();
								b2eTmpPrice = godDetail.getGod().getCsmrPrc();
								singleTmpPrice = godDetail.getGod().getCsmrPrc();
								tmpEmpPrice = godDetail.getGod().getRtlPrc();

								if(!CollectionService.isEmpty(gnlPrmResultList)){
									for(CartGodPrmResult prmDtl : gnlPrmResultList){
										if(StringService.equalsIgnoreCase(prmDtl.getGodNo(), godDetail.getBskGod().getGodNo())){

											dcType = prmDtl.getPrm().getPrmDtlTpCd();
											if(StringService.equalsIgnoreCase(BskEnum.BskPrmDtlType.B2E_SPSL.toString(),dcType)){
												dcAmt = this.getPrmDcAmt(prmDtl.getPrm(),godDetail.getGod().getCsmrPrc(),cartSearchDTO.getCrncyCd(),true);
												if((godDetail.getGod().getCsmrPrc().subtract(dcAmt)).compareTo(b2eTmpPrice) < 0){
													b2eTmpPrice = godDetail.getGod().getCsmrPrc().subtract(dcAmt);
												}
											}else if(StringService.equalsIgnoreCase(BskEnum.BskPrmDtlType.SIGNL_SPSL.toString(),dcType)){
												dcAmt = this.getPrmDcAmt(prmDtl.getPrm(),godDetail.getGod().getCsmrPrc(),cartSearchDTO.getCrncyCd(),true);
												if((godDetail.getGod().getCsmrPrc().subtract(dcAmt)).compareTo(singleTmpPrice) < 0){
													singleTmpPrice = godDetail.getGod().getCsmrPrc().subtract(dcAmt);
												}
											}else{
												dcAmt = this.getPrmDcAmt(prmDtl.getPrm(),tmpPrice,cartSearchDTO.getCrncyCd(),true);
												if((tmpPrice.subtract(dcAmt)).compareTo(tmpPrice) < 0){
													tmpPrice = tmpPrice.subtract(dcAmt);
												}
											}
										}
									}
								}

								if(StringService.isNotEmpty(cartSearchDTO.getMbr().getMbrNo())){
									if(!CollectionService.isEmpty(cpnPrmResultList)){
										BigDecimal cpnAplyDcAmt = BigDecimal.ZERO;
										BigDecimal tmpCpnAplyDcAmt = BigDecimal.ZERO;
										for(CartGodPrmResult prmDtl : cpnPrmResultList){
											if(StringService.equalsIgnoreCase(prmDtl.getGodNo(), godDetail.getBskGod().getGodNo())){

												if((StringService.equalsIgnoreCase("IMPS", prmDtl.getPrm().getGodDcDplctCd())
													&& tmpPrice.add(clubAmt).compareTo(godDetail.getGod().getCsmrPrc()) == 0)
													|| StringService.equalsIgnoreCase("PERM", prmDtl.getPrm().getGodDcDplctCd())
												  ){
													cpnAplyDcAmt = this.getPrmDcAmt(prmDtl.getPrm(),tmpPrice,cartSearchDTO.getCrncyCd(),true);
													if(tmpCpnAplyDcAmt.compareTo(cpnAplyDcAmt) < 0){
														tmpCpnAplyDcAmt = cpnAplyDcAmt;
													}
												  }
											}
										}
										tmpPrice = tmpPrice.subtract(tmpCpnAplyDcAmt);
									}
								}

								comparePrice = tmpPrice;
								comparePriceType = "GNRL";

								//싱글,B2E의 경우 임직원,i마일리지 불가
								if(b2eTmpPrice.compareTo(godDetail.getGod().getCsmrPrc()) < 0
								   || singleTmpPrice.compareTo(godDetail.getGod().getCsmrPrc()) < 0
								 ){
									if(isB2EMember && b2eTmpPrice.compareTo(godDetail.getGod().getCsmrPrc()) < 0){
										comparePriceType = "B2E";
										comparePrice = b2eTmpPrice;
									}

									if(isSigleMember && singleTmpPrice.compareTo(godDetail.getGod().getCsmrPrc()) < 0){
										comparePriceType = "SINGLE";
										comparePrice = singleTmpPrice;
									}
								}else{
									if(isEmpMember){
										comparePriceType = "EMP";
										comparePrice = tmpEmpPrice;
									}

								}

								godDetail.setPrcType(comparePriceType);
								if(StringService.equalsIgnoreCase("EMP", comparePriceType)){
									godDetail.setPrice(comparePrice);
								}else{
									godDetail.setPrice(godDetail.getGod().getCsmrPrc());
								}
							}
						}
					}
				}
			}

			//17.7.26 이진형 추가 S
			cartList = ordGodQtyValidCheck(cartGodResultList, giqsd, cartSearchDTO);
            //17.7.26 이진형 추가 E

			/*for(CartGodResult godDetail : cartGodResultList){
				if(godDetail.getBskGod().getGodTurn() == godDetail.getParentGodTurn()
				  || StringService.equalsIgnoreCase(BskEnum.PckageGodTp.ADIT_CPST_GOD.toString(), godDetail.getPckageGodTpCd())
				 ){
					CartResult cartResult = new CartResult();

					if(godDetail.getBskGod().getItmQty() < godDetail.getGod().getMinOrdQty()){
						cartResult.setAvailMinOrdCnt("N");
					}

					if(godDetail.getBskGod().getItmQty() > godDetail.getGod().getMaxOrdQty()){
						cartResult.setAvailMaxOrdCnt("N");
					}

					//패키지형 상품
					if(StringService.equalsIgnoreCase(BskEnum.YES.toString(), godDetail.getBskGod().getPckageGodYn())
					    && 	godDetail.getBskGod().getGodTurn() == godDetail.getParentGodTurn()
					 ){
						if(StringService.equalsIgnoreCase(BskEnum.YES.toString(), godDetail.getSellYn())){
							boolean isInvYn = true;
							for(CartGodResult godSub : cartGodResultList){
								if(godDetail.getBskGod().getGodTurn() == godSub.getParentGodTurn() && StringService.isNotEmpty(godSub.getPckageGodTpCd())){
									if(!StringService.equalsIgnoreCase(BskEnum.PckageGodTp.ADIT_CPST_GOD.toString(), godSub.getPckageGodTpCd())){

										if(StringService.equalsIgnoreCase(BskEnum.YES.toString(), godDetail.getSellYn())){
											if(StringService.equalsIgnoreCase(BskEnum.DlvSect.PKUP_DLV.toString(), godDetail.getBskGod().getDlvSectCd())){
												if(godSub.getBskGod().getItmQty() > godSub.getShopInvQty() + godSub.getCdcInvQty()){
													isInvYn = false;
												}else{
													if(godSub.getSumItmQty() > godSub.getShopInvQty() + godSub.getCdcInvQty()){
														isInvYn = false;
													}
												}
										    }else{
										    	if(godSub.getBskGod().getItmQty() > godSub.getRealInvQty()){
										    		isInvYn = false;
										    	}else{
										    		if(godSub.getSumItmQty() > godSub.getRealInvQty()){
										    			isInvYn = false;
										    		}
										    	}
										    }
										}
									}
								}
							}

							if(isInvYn){
								cartResult.setInvYn(BskEnum.YES.toString());
							}else{
								cartResult.setInvYn(BskEnum.NO.toString());
							}
						}else{
							cartResult.setInvYn(BskEnum.NO.toString());
						}
					}else{
						if(StringService.equalsIgnoreCase(BskEnum.YES.toString(), godDetail.getSellYn())){
							if(StringService.equalsIgnoreCase(BskEnum.DlvSect.PKUP_DLV.toString(), godDetail.getBskGod().getDlvSectCd())){
								if(godDetail.getBskGod().getItmQty() > godDetail.getShopInvQty() + godDetail.getCdcInvQty()){
									cartResult.setInvYn(BskEnum.NO.toString());
								}else{
									if(godDetail.getSumItmQty() > godDetail.getShopInvQty() + godDetail.getCdcInvQty()){
										cartResult.setInvYn(BskEnum.NO.toString());
									}else{
										cartResult.setInvYn(BskEnum.YES.toString());
									}
								}
						    }else{
						    	if(godDetail.getBskGod().getItmQty() > godDetail.getRealInvQty()){
						    		cartResult.setInvYn(BskEnum.NO.toString());
						    	}else{
						    		if(godDetail.getSumItmQty() > godDetail.getRealInvQty()){
						    			cartResult.setInvYn(BskEnum.NO.toString());
						    		}else{
						    			cartResult.setInvYn(BskEnum.YES.toString());
						    		}
						    	}
						    }
						}else{
							cartResult.setInvYn(BskEnum.NO.toString());
						}
					}

					cartResult.setBskGod(godDetail.getBskGod());
					cartResult.setGod(godDetail.getGod());
					cartResult.setPrice(godDetail.getPrice() == null ? godDetail.getGod().getCsmrPrc() : godDetail.getPrice());
					cartResult.setPrcType(StringService.isEmpty(godDetail.getPrcType()) ? "GNRL" : godDetail.getPrcType());


					//#34425 로 추가 2017-01-13
					cartResult.setOvseaDspStatCd(godDetail.getOvseaDspStatCd());
					cartResult.setTrsltStatCd(godDetail.getTrsltStatCd());
					cartResult.setOvseaDlvPsbYn(godDetail.getOvseaDlvPsbYn());

					cartList.add(cartResult);
				}
			}*/
		}
		return cartList;
	}

	//'17.09.04 픽업가능매장체크 이진형추가
    public boolean pkupShopYnCheck(CartSearchDTO cartSearchDTO) throws Exception {

    	boolean result = true;
    	//매장픽업인 경우 픽업가능여부를 체크한다.
		int pkupShopYnCnt = 1; //1값이 OK 1보다 큰 수가 되면 예외처리.
		if(StringService.equalsIgnoreCase(BskEnum.DlvSect.PKUP_DLV.toString(), cartSearchDTO.getDlvSectCd())){
			pkupShopYnCnt = cartSelectRepository.selectPkupShopYn(cartSearchDTO);
		}

		if(pkupShopYnCnt > 1){
			result = false;
		}

		return result;
    }

	//'17.07.26 이진형추가
	public List<CartResult> ordGodQtyValidCheck(List<CartGodResult> cartGodResultList, GodItmQtySearchDTO giqsd, CartSearchDTO cartSearchDTO) throws Exception {

		

		
		List<CartResult> cartList = new ArrayList<CartResult>();


		String itmNos = "";
		String shopId = "";

		//ITM_NO SET S
        for(int i=0 ; i < cartGodResultList.size(); i++){
        	CartGodResult tempGodDetail = cartGodResultList.get(i);
        	if(i==0){
        		shopId = tempGodDetail.getBskGod().getPkupShopSn();
        	}

        	if(i == cartGodResultList.size() - 1){
        		itmNos +=tempGodDetail.getBskGod().getItmNo();
        	}else{
        		itmNos +=tempGodDetail.getBskGod().getItmNo()+",";
        	}

        }

        giqsd.setShopId(shopId);
        if(StringService.isNotEmpty(itmNos)){
        	giqsd.setItmNoArr(itmNos.split("(\r\n)|,"));
        }
        //ITM번호SET E

        List<GodItmQtyResult> godItmQtyList =  cartSelectRepository.selectGodItmQty(giqsd);
        HashMap<String,GodItmQtyResult> tempQtyMap = new HashMap<String,GodItmQtyResult>(); //임시DATA

        for(GodItmQtyResult godItmQtyDetail : godItmQtyList){
        	tempQtyMap.put(godItmQtyDetail.getItmNo(), godItmQtyDetail);
		}

	log.debug("최초 수량 ===>" + tempQtyMap.toString());
	int tempIndex = 0;

		//editInvalidOrderGodPop.jsp용 추가변수 S
		boolean aditInvYn = true;
		boolean aditselYn = true;
		boolean availOrdCnt = true;
		//editInvalidOrderGodPop.jsp용 추가변수 E
        for(CartGodResult godDetail : cartGodResultList){
        	tempIndex++;
	log.debug(tempIndex + " : "+ godDetail.getBskGod().getItmNo());
        	long itmQty = godDetail.getBskGod().getItmQty(); //장바구니 선택수량

        	boolean isInvYn = true;
        	boolean isShopDlvYn = false;

			long MaxUsefulQty = 0; //가용재고수량

			long shopInvUsefulQty = 0; //픽업매장재고수량


			if(StringService.isEmpty(godDetail.getPckageGodTpCd())){ //세트나 구성상품은 작은 루프에서 처리 됨. 따라서 큰 루프에서는 세트나 구성상품은 제외.
				CartResult cartResult = new CartResult();

				//editInvalidOrderGodPop.jsp용 추가변수 S
				aditInvYn = true;
				aditselYn = true;
				availOrdCnt = true;
				//editInvalidOrderGodPop.jsp용 추가변수 E

				if(godDetail.getBskGod().getItmQty() < godDetail.getGod().getMinOrdQty()){
					cartResult.setAvailMinOrdCnt("N");
					availOrdCnt = false;
				}

				if(godDetail.getBskGod().getItmQty() > godDetail.getGod().getMaxOrdQty()){
					cartResult.setAvailMaxOrdCnt("N");
					availOrdCnt = false;
				}

				//패키지형 상품
				if(StringService.equalsIgnoreCase(BskEnum.YES.toString(), godDetail.getBskGod().getPckageGodYn())
				    && 	godDetail.getBskGod().getGodTurn() == godDetail.getParentGodTurn()
				 ){

					//********************************editInvalidOrderGodPop.jsp용 추가변수 S******************************
					List<CartGodResult> tempCartGodResultList = new ArrayList<CartGodResult>();
					//********************************editInvalidOrderGodPop.jsp용 추가변수 E******************************

					if(StringService.equalsIgnoreCase(BskEnum.YES.toString(), godDetail.getSellYn())){

						int pkgIdx = 0; //패키지 시작 인덱스

						//********************************editInvalidOrderGodPop.jsp용 S******************************
						List<String> tempItmNoList = new ArrayList<String>(); //세트상품의 대표상품을 제외한 필수 및 구성상품정보를 담기 위한 변수
						//********************************editInvalidOrderGodPop.jsp용 E******************************

						for(CartGodResult godSub : cartGodResultList){

							String tempItmNo = godSub.getBskGod().getItmNo();
							GodItmQtyResult tempQty = tempQtyMap.get(tempItmNo);

							if(godDetail.getBskGod().getGodTurn() == godSub.getParentGodTurn() && StringService.isNotEmpty(godSub.getPckageGodTpCd())){//세트 하위상품이면

								//********************************editInvalidOrderGodPop.jsp용 S******************************
								BskGodExtend cpstInvInfo = new BskGodExtend();

								tempCartGodResultList.add(godSub);

								//********************************editInvalidOrderGodPop.jsp용 E******************************
								if(StringService.equalsIgnoreCase(BskEnum.YES.toString(), godDetail.getSellYn())){
									if(!StringService.equalsIgnoreCase(BskEnum.PckageGodTp.ADIT_CPST_GOD.toString(), godSub.getPckageGodTpCd())){ //구성상품이 아니라면

										pkgIdx++;
										tempItmNoList.add(tempItmNo);

										if(StringService.equalsIgnoreCase(BskEnum.DlvSect.PKUP_DLV.toString(), godSub.getBskGod().getDlvSectCd())){ //패키지 매장픽업

				
											long shopInvQty = tempQty.getShopInvQty();

											if(pkgIdx == 1){ //가용수량 초기값 셋팅
				
												shopInvUsefulQty = shopInvQty;
											}else{ //재고지에 따른 가용수량 비교 셋팅
				
												if(shopInvUsefulQty > shopInvQty){
													shopInvUsefulQty = shopInvQty;
												}
											}

											log.debug(tempItmNo+":"+pkgIdx+":"+shopInvUsefulQty);

									    }else{ //패키지 일반배송

									    	long realInvQty = tempQty.getRealInvQty();

									    	//가용수량 초기값 셋팅
											if(pkgIdx == 1){
												MaxUsefulQty = realInvQty;
											}else{
												if(MaxUsefulQty > realInvQty){
													MaxUsefulQty = realInvQty;
												}
											}

									    }
									}else{ //구성상품이라면
										//********************************editInvalidOrderGodPop.jsp용 S******************************
										if(godDetail.getBskGod().getItmQty() < godDetail.getGod().getMinOrdQty()){
											cpstInvInfo.setAvailMinOrdCnt("N");
											availOrdCnt = false;
										}

										if(godDetail.getBskGod().getItmQty() > godDetail.getGod().getMaxOrdQty()){
											cpstInvInfo.setAvailMaxOrdCnt("N");
											availOrdCnt = false;
										}

										if(!StringService.equalsIgnoreCase(BskEnum.YES.toString(), godSub.getSellYn())){

											if(StringService.equalsIgnoreCase(BskEnum.BskGodItmSaleStat.SALE_PROGRS.toString(),godSub.getGod().getGodSaleSectCd())
													||StringService.equalsIgnoreCase(BskEnum.BskGodItmSaleStat.SALE_PROGRS_PKUP.toString(),godSub.getGod().getGodSaleSectCd())){
												godSub.getGod().setGodSaleSectCd(godSub.getGodItm().getItmStatCd());
											}

											aditselYn = false;
											aditInvYn = false;
										}
										//********************************editInvalidOrderGodPop.jsp용 E******************************
									}
								}else{ // !SellYn
									//********************************editInvalidOrderGodPop.jsp용 S******************************
									if(!StringService.equalsIgnoreCase(BskEnum.PckageGodTp.ADIT_CPST_GOD.toString(), godSub.getPckageGodTpCd())){

										if(StringService.equalsIgnoreCase(BskEnum.DlvSect.PKUP_DLV.toString(), godSub.getBskGod().getDlvSectCd())){ //픽업
											if(StringService.equalsIgnoreCase(BskEnum.BskGodSaleStat.SALE_PROGRS.toString(), godDetail.getGod().getGodSaleSectCd())
													|| StringService.equalsIgnoreCase(BskEnum.BskGodSaleStat.SALE_PROGRS_PKUP.toString(), godDetail.getGod().getGodSaleSectCd())){ //판매중인 상품
												 if(!StringService.equalsIgnoreCase(BskEnum.BskGodSaleStat.SALE_PROGRS.toString(), godSub.getGod().getGodSaleSectCd())
														 && !StringService.equalsIgnoreCase(BskEnum.BskGodSaleStat.SALE_PROGRS_PKUP.toString(), godSub.getGod().getGodSaleSectCd())){

													 godDetail.getGod().setGodSaleSectCd(godSub.getGod().getGodSaleSectCd());

												 }else if(!StringService.equalsIgnoreCase(BskEnum.BskGodItmSaleStat.SALE_PROGRS.toString(),godSub.getGodItm().getItmStatCd())
														 && !StringService.equalsIgnoreCase(BskEnum.BskGodItmSaleStat.SALE_PROGRS_PKUP.toString(),godSub.getGodItm().getItmStatCd())){

													 godDetail.getGod().setGodSaleSectCd(godSub.getGodItm().getItmStatCd());
												 }
											}
										}else{ //일반
											if(StringService.equalsIgnoreCase(BskEnum.BskGodSaleStat.SALE_PROGRS.toString(), godDetail.getGod().getGodSaleSectCd())){ //판매중인 상품
												 if(!StringService.equalsIgnoreCase(BskEnum.BskGodSaleStat.SALE_PROGRS.toString(), godSub.getGod().getGodSaleSectCd())){

													 godDetail.getGod().setGodSaleSectCd(godSub.getGod().getGodSaleSectCd());
												 }else if(!StringService.equalsIgnoreCase(BskEnum.BskGodItmSaleStat.SALE_PROGRS.toString(),godSub.getGodItm().getItmStatCd())){

													 godDetail.getGod().setGodSaleSectCd(godSub.getGodItm().getItmStatCd());
												 }
											}
										}

									}else{
										aditselYn = false;
										aditInvYn = false;
									}
									//********************************editInvalidOrderGodPop.jsp용 E******************************
								}
								//********************************editInvalidOrderGodPop.jsp용 S******************************
								if(StringService.equalsIgnoreCase(BskEnum.PckageGodTp.ADIT_CPST_GOD.toString(), godSub.getPckageGodTpCd())){
									cpstInvInfo.setSellYn(aditselYn ? "Y" : "N");
									cpstInvInfo.setInvYn(aditInvYn ? "Y" : "N");
								}

								cpstInvInfo.setParentGodTurn(godSub.getParentGodTurn());
								cpstInvInfo.setBskNo(godSub.getBskGod().getBskNo());
								cpstInvInfo.setGodTurn(godSub.getBskGod().getGodTurn());
								cpstInvInfo.setPckageGodTpCd(godSub.getPckageGodTpCd());
								cpstInvInfo.setBrndNm(godSub.getBrndNm());
								cpstInvInfo.setItmQty(godSub.getBskGod().getItmQty());
								cpstInvInfo.setItmNo(tempItmNo);

								cartResult.getBskCpstGodCnnc().add(godSub.getGod());
								cartResult.getBskCpstGodItmCnnc().add(godSub.getGodItm());
								cartResult.getBskCpstInvInfo().add(cpstInvInfo);
								//********************************editInvalidOrderGodPop.jsp용 E******************************

							} //PckageGod
						} //for

	log.debug(godDetail.getBskGod().getItmNo()+"|tempItmNoList"+tempItmNoList.toString());
						pkgIdx = 0; //패키지 인덱스 초기화
	log.debug("상품판매여부 플래그1 ===>" + isInvYn);
						if(StringService.equalsIgnoreCase(BskEnum.DlvSect.PKUP_DLV.toString(), godDetail.getBskGod().getDlvSectCd())){ //패키지 매장픽업

							boolean shopInvQtyYn = true; //픽업매장 기본상품 수량충족 YN
							boolean fc08QtyYn = true; //fc08 기본상품 수량충족 YN
							boolean fc01QtyYn = true; //fc01 기본상품 수량충족 YN
							boolean shopInvAdiQtyYn = true; //픽업매장 구성상품 수량충족 YN
							boolean fc08AdiQtyYn = true; //fc08 구성상품 수량충족 YN
							boolean fc01AdiQtyYn = true; //fc01 구성상품 수량충족 YN

							//추가구성상품 ITM단위 가용수량
							int shopInvAdiUsefulCnt = 0;
							int fc08AdiUseful = 0;
							int fc01AdiUseful = 0;

							//세트기본 상품수량 반영 O, 구성상품 수량 반영 X
							HashMap<String,GodItmQtyResult> shopInvQtyMap = new HashMap<String,GodItmQtyResult>(); //임시DATA1
				            HashMap<String,GodItmQtyResult> fc08QtyMap = new HashMap<String,GodItmQtyResult>(); //임시DATA1
				            HashMap<String,GodItmQtyResult> fc01QtyMap = new HashMap<String,GodItmQtyResult>(); //임시DATA1

				            //세트기본 상품수량 반영 O, 구성상품 수량 반영 O
							HashMap<String,GodItmQtyResult> tempShopInvQtyMap = new HashMap<String,GodItmQtyResult>(); //임시DATA2
							HashMap<String,GodItmQtyResult> tempFc08QtyMap = new HashMap<String,GodItmQtyResult>();    //임시DATA2
							HashMap<String,GodItmQtyResult> tempFc01QtyMap = new HashMap<String,GodItmQtyResult>();    //임시DATA2

							List<String> properAddItmList = new ArrayList<String>();
							List<String> shopInvAddItmList = new ArrayList<String>(); //임시DATA2
							List<String> fc08AddItmList = new ArrayList<String>();    //임시DATA2
							List<String> fc01AddItmList = new ArrayList<String>();    //임시DATA2



							/**세트단위 수량으로 가용재고 비교시작 */

							//재고분배 1순위 : 픽업매장재고
							if(shopInvUsefulQty >= itmQty){

								shopInvQtyMap = tempQtyMap; //세트기본 상품수량 반영 X, 구성상품수량 반영 X
	log.debug("세트 매장픽업필수 전 ===>" + shopInvQtyMap.toString());
								//세트 기본 상품의 수량 제외
								for(String tempItmNo : tempItmNoList){
									long shopInvQty = shopInvQtyMap.get(tempItmNo).getShopInvQty();
									shopInvQtyMap.get(tempItmNo).setShopInvQty(shopInvQty - itmQty);
	log.debug(tempItmNo+"|세트 매장픽업필수 " );
	log.debug("itmQty|세트 매장픽업필수 ===>" + itmQty);
								}
	log.debug("세트 매장픽업필수 후 ===>" + shopInvQtyMap.toString());
								tempShopInvQtyMap = shopInvQtyMap; //세트기본 상품수량 반영 O, 구성상품 수량 반영X

								int adiCnt = 0;
								int adiIndex = 0;
								//추가구성상품 수량 반영
								for(CartGodResult godSub : cartGodResultList){
									String adiItmNo = godSub.getBskGod().getItmNo();
									if(godDetail.getBskGod().getGodTurn() == godSub.getParentGodTurn() && StringService.isNotEmpty(godSub.getPckageGodTpCd())){
										if(StringService.equalsIgnoreCase(BskEnum.DlvSect.PKUP_DLV.toString(), godSub.getBskGod().getDlvSectCd())){ //패키지 매장픽업
											if(StringService.equalsIgnoreCase(BskEnum.PckageGodTp.ADIT_CPST_GOD.toString(), godSub.getPckageGodTpCd())){ //패키지 구성상품
												adiCnt++;
												long adiGodQty = tempShopInvQtyMap.get(adiItmNo).getShopInvQty();
												long adiItmQty = godSub.getBskGod().getItmQty();
												if(adiGodQty >= adiItmQty){
													tempShopInvQtyMap.get(adiItmNo).setShopInvQty(adiGodQty - adiItmQty);
													adiIndex++;
												}else{
													shopInvAddItmList.add(adiItmNo);
												}
											}
										}
									}
								}
	log.debug("세트 매장픽업추가 ===>" + tempShopInvQtyMap.toString());
								shopInvAdiUsefulCnt = adiIndex;

								if(adiCnt == adiIndex){ //구성상품의 모든 수량(itmNo기준)이 실제 가용재고량을 만족한다면
									tempQtyMap = tempShopInvQtyMap; //세트기본 상품수량 반영 O, 구성상품 수량 반영 O
	log.debug("세트 추가구성만족 ===>" +tempQtyMap.toString());
								}else{
	log.debug("세트 추가구성불만족 ===>" + tempQtyMap.toString());
									shopInvAdiQtyYn = false;
								}

							}else{
								shopInvQtyYn = false;
								shopInvAdiQtyYn = false;
							}
	log.debug("상품판매여부 플래그2 ===>" + isInvYn);
							
							

							
	log.debug("shopInvAdiQtyYn 추가구성 플래그 ===>" + shopInvAdiQtyYn);
	log.debug("fc08AdiQtyYn 추가구성 플래그 ===>" + fc08AdiQtyYn);
	log.debug("fc01AdiQtyYn 추가구성 플래그 ===>" + fc01AdiQtyYn);
							if(!shopInvQtyYn && !fc08QtyYn && !fc01QtyYn ){ //3재고지 세트기본상품 수량이 모두 부족시
								isInvYn = false;
							}

							if(!shopInvAdiQtyYn && !fc08AdiQtyYn && !fc01AdiQtyYn ){ //추가구성상품 3재고지 모두 수량부족인 경우 그 중 추가구성상품의 수량을 가장많이 충족하는 재고지를 찾아 배정한다.

								int tempAdiUsefulCnt = shopInvAdiUsefulCnt;
								properAddItmList = shopInvAddItmList;

								if(shopInvQtyYn && tempAdiUsefulCnt != 0){ //픽업매장이 필수수량을 만족하고 세트구성상품 ITM기준으로 1개 이상 있을 때
									tempQtyMap = tempShopInvQtyMap; //세트기본 상품수량 반영 O, 구성상품 수량 반영 O
								}

								if(tempAdiUsefulCnt < fc08AdiUseful ){

									tempAdiUsefulCnt = fc08AdiUseful;
									properAddItmList = fc08AddItmList;

									if(fc08QtyYn && tempAdiUsefulCnt != 0){ //픽업매장보다 추가구성상품의 재고량을 만족하는 수량(ITM기준)이 많은 경우
										tempQtyMap = tempFc08QtyMap; //세트기본 상품수량 반영 O, 구성상품 수량 반영 O
									}
								}
								if(tempAdiUsefulCnt < fc01AdiUseful){

									tempAdiUsefulCnt = fc01AdiUseful;
									properAddItmList = fc01AddItmList;

									if(fc01QtyYn && tempAdiUsefulCnt != 0){ //픽업매장과 FC08보다 추가구성상품의 재고량을 만족하는 수량(ITM기준)이 많은 경우
										tempQtyMap = tempFc01QtyMap; //세트기본 상품수량 반영 O, 구성상품 수량 반영 O
									}
								}

								//********************************editInvalidOrderGodPop.jsp용 추가변수 S******************************
								aditselYn = false;
								aditInvYn = false;
								//********************************editInvalidOrderGodPop.jsp용 추가변수 E******************************
	log.debug("properAddItmList"+properAddItmList.toString());
								for(BskGodExtend bge : cartResult.getBskCpstInvInfo()){
									log.debug("bge.getItmNo() ===>" + bge.getItmNo());

									if(properAddItmList.size() == 0
											&& StringService.equalsIgnoreCase(BskEnum.PckageGodTp.ADIT_CPST_GOD.toString(),bge.getPckageGodTpCd())){

										bge.setInvYn("N");
										log.debug("추가 구성 상품 수량 모두 불만족시 추가구성 플래그N ITM_NO ===>" + bge.getItmNo());
									}else{
										for(String itmNo : properAddItmList){
											if(StringService.equalsIgnoreCase(bge.getItmNo(),itmNo)
													&& StringService.equalsIgnoreCase(BskEnum.PckageGodTp.ADIT_CPST_GOD.toString(),bge.getPckageGodTpCd())){
												bge.setInvYn("N");
												log.debug("추가 구성 상품 수량 일부 불만족시 플래그N ITM_NO ===>" + itmNo);
											}
										}
									}
								}

							}

							log.debug("세트 최후 수량 ===>" + tempQtyMap.toString());


						} else { //팩키지 일반배송
							if(MaxUsefulQty >= itmQty){ //세트 기본상품의 수량을 충족한다면

								//세트 기본 상품의 수량 제외
								for(String tempItmNo : tempItmNoList){
									long realInvQty = tempQtyMap.get(tempItmNo).getRealInvQty();
									tempQtyMap.get(tempItmNo).setRealInvQty(realInvQty - itmQty);
								}

								int adiCnt = 0;
								int adiIndex = 0;

								//구성상품의 경우 판매가능여부에는 영향을 미치지 않고 해당ITM의 재고수량에만 반영한다.
								for(CartGodResult godSub : cartGodResultList){
									if(godDetail.getBskGod().getGodTurn() == godSub.getParentGodTurn() && StringService.isNotEmpty(godSub.getPckageGodTpCd())){
										if(!StringService.equalsIgnoreCase(BskEnum.DlvSect.PKUP_DLV.toString(), godSub.getBskGod().getDlvSectCd())){  //매장픽업X
											if(StringService.equalsIgnoreCase(BskEnum.PckageGodTp.ADIT_CPST_GOD.toString(), godSub.getPckageGodTpCd())){ //구성상품O

												adiCnt++;

												String adiItmNo = godSub.getBskGod().getItmNo();
												log.debug("세트 일반 수량 adiItmNo" + adiItmNo);
												long adiGodQty = tempQtyMap.get(adiItmNo).getRealInvQty();
												long adiItmQty = godSub.getBskGod().getItmQty();
												if(adiGodQty >= adiItmQty){
													tempQtyMap.get(adiItmNo).setRealInvQty(adiGodQty - adiItmQty);
													adiIndex++;
												}else{
													for(BskGodExtend bge : cartResult.getBskCpstInvInfo()){
														log.debug("세트 일반 수량 bge.getItmNo()" + bge.getItmNo());
														if(StringService.equalsIgnoreCase(bge.getItmNo(),adiItmNo)
																&& StringService.equalsIgnoreCase(BskEnum.PckageGodTp.ADIT_CPST_GOD.toString(), bge.getPckageGodTpCd())){ //추가구성상품의 수량을 만족하지 못 한경우
															bge.setInvYn("N");
															log.debug("세트 일반 수량 bge.getPckageGodTpCd()" + bge.getPckageGodTpCd());
														}
													}
												}
											}
										}
									}
								}

								if(adiCnt != adiIndex){ //구성상품의 모든 수량(itmNo기준)이 실제 가용재고량을 만족X
									aditInvYn = false;
								}

							}else{
								isInvYn = false;
								aditInvYn =false;
							}
						}

						if(isInvYn){
							cartResult.setInvYn(BskEnum.YES.toString());
						}else{
							cartResult.setInvYn(BskEnum.NO.toString());
						}

						if(aditInvYn){
							cartResult.setAditInvYn(BskEnum.YES.toString());
						}else{
							cartResult.setAditInvYn(BskEnum.NO.toString());
						}
					}else{
						cartResult.setInvYn(BskEnum.NO.toString());
						cartResult.setAditInvYn(BskEnum.NO.toString());
					}
				}else{ //일반상품
					if(StringService.equalsIgnoreCase(BskEnum.YES.toString(), godDetail.getSellYn())){

						String tempItmNo = godDetail.getBskGod().getItmNo();
						GodItmQtyResult tempQty = tempQtyMap.get(tempItmNo);
	log.debug("일반상품 최초 ===>" + tempQtyMap.toString());
						if(StringService.equalsIgnoreCase(BskEnum.DlvSect.PKUP_DLV.toString(), godDetail.getBskGod().getDlvSectCd())){ //일반상품 매장픽업
							long shopInvQty = tempQty.getShopInvQty();
							

							if(shopInvQty >= itmQty){
								tempQtyMap.get(tempItmNo).setShopInvQty(shopInvQty - itmQty);
							}else{
								isInvYn = false;
							}
	log.debug("일반상품 매장픽업 후 ===>" + tempQtyMap.toString());
					    }else{ //일반상품 일반배송
					    	long realInvQty = tempQty.getRealInvQty();
					    	long etcInvQty = tempQty.getEtcInvQty();
					    	if(realInvQty >= itmQty){
					    		tempQtyMap.get(tempItmNo).setRealInvQty(realInvQty - itmQty);
					    	}else if(etcInvQty >= itmQty){
					    		isShopDlvYn = true;
					    		tempQtyMap.get(tempItmNo).setEtcInvQty(etcInvQty - itmQty);
					    	}else{
					    		isInvYn = false;
					    	}
	log.debug("일반상품 일반배송 후 ===>" + tempQtyMap.toString());
					    }

						if(isInvYn){
							cartResult.setInvYn(BskEnum.YES.toString());
						}else{
							cartResult.setInvYn(BskEnum.NO.toString());
						}
						
						if(isShopDlvYn){
							cartResult.setShopDlvItm(BskEnum.YES.toString());
						}

					}else{
						cartResult.setInvYn(BskEnum.NO.toString());
						//********************************editInvalidOrderGodPop.jsp용 추가변수 S******************************
						if(StringService.equalsIgnoreCase(BskEnum.DlvSect.PKUP_DLV.toString(), godDetail.getBskGod().getDlvSectCd())){
							if(StringService.equalsIgnoreCase(BskEnum.BskGodItmSaleStat.SALE_PROGRS.toString(),godDetail.getGod().getGodSaleSectCd())
									|| StringService.equalsIgnoreCase(BskEnum.BskGodItmSaleStat.SALE_PROGRS_PKUP.toString(),godDetail.getGod().getGodSaleSectCd())){

								godDetail.getGod().setGodSaleSectCd(godDetail.getGodItm().getItmStatCd());
							}
						}else{
							if(StringService.equalsIgnoreCase(BskEnum.BskGodItmSaleStat.SALE_PROGRS.toString(),godDetail.getGod().getGodSaleSectCd())){
								godDetail.getGod().setGodSaleSectCd(godDetail.getGodItm().getItmStatCd());
							}
						}

						//********************************editInvalidOrderGodPop.jsp용 추가변수 E******************************
					}

				}
	log.debug("상품판매여부 플래그 ===>" + isInvYn);
	log.debug("상품수량 최후 ===>" + tempQtyMap.toString());
				cartResult.setBskGod(godDetail.getBskGod());
				cartResult.setGod(godDetail.getGod());
				cartResult.setPrice(godDetail.getPrice() == null ? godDetail.getGod().getCsmrPrc() : godDetail.getPrice());
				cartResult.setPrcType(StringService.isEmpty(godDetail.getPrcType()) ? "GNRL" : godDetail.getPrcType());


				//#34425 로 추가 2017-01-13
				cartResult.setOvseaDspStatCd(godDetail.getOvseaDspStatCd());
				cartResult.setTrsltStatCd(godDetail.getTrsltStatCd());
				cartResult.setOvseaDlvPsbYn(godDetail.getOvseaDlvPsbYn());

                //********************************editInvalidOrderGodPop.jsp용 추가변수 S******************************
				boolean ovaseaInvalidChk = true;
//				if(!"KOR".equalsIgnoreCase(cartSearchDTO.getLang())){
//					if(!StringService.equalsIgnoreCase("DSP_APRV",godDetail.getOvseaDspStatCd())//해외전시상태코드
//							||!StringService.equalsIgnoreCase("TRSLT_COMPT",godDetail.getTrsltStatCd())//상품번역상태코드
//							||!StringService.equalsIgnoreCase(BskEnum.YES.toString(),godDetail.getOvseaDlvPsbYn())//해외배송가능여부
//							){
//						ovaseaInvalidChk = false;
//						godDetail.setSellYn("N");//장바구니 리스트에서 sale closed 로 노출 위해 셋팅
//					}
//				}
				///////////////////////////

				//최상위세트
				if(StringService.equalsIgnoreCase(BskEnum.NO.toString(), godDetail.getSellYn())
					|| 	StringService.equalsIgnoreCase(BskEnum.NO.toString(), cartResult.getInvYn())
					|| !aditInvYn || !aditselYn || !availOrdCnt 
					){

					cartResult.setGodItm(godDetail.getGodItm());
					cartResult.setBrndNm(godDetail.getBrndNm());
					cartResult.setParentGodTurn(godDetail.getParentGodTurn());
					cartResult.setPckageGodTpCd(godDetail.getPckageGodTpCd());
					cartResult.setSellYn(godDetail.getSellYn());
				}
				//********************************editInvalidOrderGodPop.jsp용 추가변수 E******************************

				cartList.add(cartResult);
			}
		}

        for(CartResult car : cartList){
        	log.debug("최종 리턴 값 =>>>"+car.toString());
        }			

		

		return cartList;

	}

	
	
	
	
	
	//2017-11-24 퀵배송용 재고체크모듈 추가
	public List<CartResult> ordGodQtyValidCheckForQdlv(List<CartGodResult> cartGodResultList
			, GodItmQtySearchDTO giqsd
			, CartSearchDTO cartSearchDTO) 
			throws Exception {
		
		List<CartResult> cartList = new ArrayList<CartResult>();
		
		GodItmQtySearchDTO godItmQtySearchDTO = new GodItmQtySearchDTO();
		godItmQtySearchDTO.setDlvSectCd(BskEnum.DlvSect.QDLV.toString());				

		String[] itmArr = new String[cartGodResultList.size()];//재고메트릭스 조회위한 장바구니 내 모든 itm 배열
		int arrcnt = 0;
		//HashMap<String,Long> itmOrdQtyMap = new HashMap<String,Long>(); //itm 단위 구매수량
		
		for(CartGodResult godDTO : cartGodResultList){//itmNo 배열에 담는다
			itmArr[arrcnt] = godDTO.getBskGod().getItmNo();
			arrcnt++;
		}
		godItmQtySearchDTO.setItmNoArr(itmArr);
		
		//itm별 재고위치별 재고가져옴(재고메트릭스)
		List<GodItmQtyResult> itmqtyList = this.selectGodItmQtyForQdlv(godItmQtySearchDTO);
		
		//장바구니 상품만큼(추가구성있는셋트와 단품하나면 5개의 row 가 있다 / 셋트4 단품1)
		for(CartGodResult godDetail : cartGodResultList){
			
			//세트 껍대기랑 단품만 처리. 껍데기랑 단품은 PckageGodTpCd 가 비어있음
			if(StringService.isEmpty(godDetail.getPckageGodTpCd())){
				
				CartResult cartResult = new CartResult();//리턴되는 cartList 에 add 될 DTO
				
				List<CartGodResult> godInvChkDTOList = new ArrayList<CartGodResult>(); // 상품단위 배정을 위한 파라메터
				
				
				if(godDetail.getBskGod().getItmQty() < godDetail.getGod().getMinOrdQty()){
					cartResult.setAvailMinOrdCnt("N");
				}

				if(godDetail.getBskGod().getItmQty() > godDetail.getGod().getMaxOrdQty()){
					cartResult.setAvailMaxOrdCnt("N");
				}
				
				
				//godInvChkDTOList 값 셋팅
				if(StringService.equalsIgnoreCase(BskEnum.YES.toString(), godDetail.getBskGod().getPckageGodYn())//패키지형 상품
				    && 	godDetail.getBskGod().getGodTurn() == godDetail.getParentGodTurn()//껍대기일때
				    ){				
					for(CartGodResult godSub : cartGodResultList){//자식을 구해서 셋팅						
						if(godDetail.getBskGod().getGodTurn() == godSub.getParentGodTurn() && StringService.isNotEmpty(godSub.getPckageGodTpCd())){							
							//세트 구성상품(추가구성품 포함)
							godInvChkDTOList.add(godSub);
						}
					}					
				}else{//단품. 그냥셋팅				
					godInvChkDTOList.add(godDetail);
				}
				
				//상품하나단위로 배정처리(재고매트릭스는 누적) 파라메터로 던진 상품이 배정된 재고위치를 리턴
				String assignedShop = this.godAssignProcess(godDetail,godInvChkDTOList,itmqtyList,cartResult);
	
				//해당상품 배정된 위치에서 재고매트릭스에 재고 차감
				if(!"NONE".equalsIgnoreCase(assignedShop)){//배정되었다면
					for(GodItmQtyResult dto : itmqtyList){//재고매트릭스에서
						for(CartGodResult assignedCartGod: godInvChkDTOList){//배정된상품을
							if(dto.getItmNo().equals(assignedCartGod.getBskGod().getItmNo())){
								if(assignedShop.equalsIgnoreCase(dto.getChkShopId())){//배정된 재고위치에서									
									dto.setInvQty(dto.getInvQty()-assignedCartGod.getBskGod().getItmQty().intValue());//뺀다
								}
							}						
						}					
					}					
				}
				cartList.add(cartResult);
			}
		}
		
		
		
		return cartList;
	}	
	
	
	

	/**
	 * 상품하나 단위로 배정 후 배정된 재고위치 리턴하는 메소드
	 * @param godDetail >>> 세트일경우 부모상품, 단품일경우 자신
	 * @param godInvChkDTOList >>> 배정대상 상품(셋트면 구성품들)
	 * @param itmqtyList >>> 재고매트릭스
	 * @param godDetail >>> 배정 후 값 셋팅
	 * @return assignedShop >>> godInvChkDTOList 에 있는 상품이 배정된 재고위치
	 */
	private String godAssignProcess(CartGodResult godDetail, List<CartGodResult> godInvChkDTOList, List<GodItmQtyResult> itmqtyList, CartResult cartResult) {
		String assignedShop = "NONE";
		
		List<GodItmQtyResult> tempItmqtyList = new ArrayList<GodItmQtyResult>();
		tempItmqtyList.addAll(itmqtyList);
		
		//BeanUtils.copyProperties(tempItmqtyList, itmqtyList);
		
		

		HashMap<String,Long> itmOrdQtyMap = new HashMap<String,Long>(); //itm 단위 구매수량 (세트의 기본구성품 또는 단품)
		HashMap<String,Long> itmOrdAditQtyMap = new HashMap<String,Long>(); //itm 단위 추가구성품 구매수량(세트의 추가구성품)
		
		//itm 별 구매수량 
		for(CartGodResult cartGodSub : godInvChkDTOList){//맵에 기본구성품과 추가구성품을 분리해서 담는다.
			
			String subItmNo = cartGodSub.getBskGod().getItmNo();		
			Long itmOrdQty = cartGodSub.getBskGod().getItmQty();
			
			if("ADIT_CPST_GOD".equalsIgnoreCase(cartGodSub.getPckageGodTpCd())){
				//추가구성품 수량				
				itmOrdAditQtyMap.put(subItmNo, itmOrdQty);				
			}else{
				//기본구성품 수량
				itmOrdQtyMap.put(subItmNo, itmOrdQty);				
			}
		}
		
		//배정대상 상품의 재고위치(매장목록) 목록 추출
		List<String> shopInvList = new ArrayList<String>();//매장목록
		List<String> shopInvListTemp = new ArrayList<String>();//매장목록변경을 위한 임시객체
		HashMap<String,Boolean> isShopInvMap = new HashMap<String,Boolean>(); //매장단위 배정가능여부
		
		for(GodItmQtyResult dto : tempItmqtyList){//재고매트릭스는 장바구니 모든상품을 가지고있다
			for(CartGodResult cartGodSub : godInvChkDTOList){//배정할 상품만 체크하기 위해 for 수행
				String subItmNo = cartGodSub.getBskGod().getItmNo();
				if(subItmNo.equalsIgnoreCase(dto.getItmNo())){
					if(isShopInvMap.get(dto.getChkShopId())==null){						
						isShopInvMap.put(dto.getChkShopId(),true);						
					}
				}				
			}					
		}
										
		
		//세트 기본구성품 또는 단품 배정
		//재고메트릭스만큼 돌면서 해당 Itm 주문수량만큼 빼서 배정가능재고위치 셋팅
		//재고매트릭스는 퀵배송 우선순위인 FC08 FC01 퀵배송매장 순으로 가져옴
		for(GodItmQtyResult dto : tempItmqtyList){//재고매트릭스는 장바구니 모든상품을 가지고있다			
			for(CartGodResult cartGodSub : godInvChkDTOList){//배정할 상품만 체크하기 위해 for 수행
				String subItmNo = cartGodSub.getBskGod().getItmNo();
				if(subItmNo.equalsIgnoreCase(dto.getItmNo()) //해당itm
						&& !"ADIT_CPST_GOD".equalsIgnoreCase(cartGodSub.getPckageGodTpCd()) //추가구성품이 아닌거
						){					
					if(isShopInvMap.get(dto.getChkShopId())==true){//배정가능여부가 true 인 경우
						if(dto.getInvQty()<itmOrdQtyMap.get(dto.getItmNo())){//해당재고위치의 재고와 주문수량체크
							isShopInvMap.put(dto.getChkShopId(), false);//주문수량이 크면 해당재고위치는 배정불가
						}else{//배정가능하면
							//임시재고매트릭스에서 재고를뺀다.(셋트의 경우 추가구성품 재고체크를 위해)
							dto.setInvQty(dto.getInvQty()-itmOrdQtyMap.get(dto.getItmNo()).intValue());							
							if(!shopInvList.contains(dto.getChkShopId())){//기본 구성상품 또는 단품이 배정된 재고위치 저장
								shopInvList.add(dto.getChkShopId());	
							}
							
						}
					}					
				}
			}	
		}
		
		
		//배정성공여부 체크 shopInvList 에 본상품 배정가능한거만 남긴다.
		shopInvListTemp.addAll(shopInvList);
		for(String invShop : shopInvList){
			if(!isShopInvMap.get(invShop)){
				shopInvListTemp.remove(invShop);
			}else{
				if(assignedShop.equals("NONE")){//아직 배정안되었으면
					assignedShop = invShop;//처음 배정된 우선순위 높은 재고위치로 셋팅(단품 위해서)
				}
			}
		}
		shopInvList=shopInvListTemp;
		
		
		//세트 기본구성상품에대한 cartResult 데이터 셋팅 
		for(CartGodResult cartGodSub : godInvChkDTOList){
			
			
			if("Y".equalsIgnoreCase(godDetail.getBskGod().getPckageGodYn()) &&
					!"ADIT_CPST_GOD".equalsIgnoreCase(cartGodSub.getPckageGodTpCd())){ //세트이고 추가구성품이 아니면
				BskGodExtend cpstInvInfo = new BskGodExtend();			
				
				if(godDetail.getBskGod().getItmQty() < godDetail.getGod().getMinOrdQty()){
					cpstInvInfo.setAvailMinOrdCnt("N");
				}

				if(godDetail.getBskGod().getItmQty() > godDetail.getGod().getMaxOrdQty()){
					cpstInvInfo.setAvailMaxOrdCnt("N");
				}
				
				//cpstInvInfo.setSellYn(godDetail.getSellYn());
				//cpstInvInfo.setInvYn(shopInvList.size()!=0 ? "Y" : "N");
				
				
				cpstInvInfo.setParentGodTurn(cartGodSub.getParentGodTurn());
				cpstInvInfo.setBskNo(cartGodSub.getBskGod().getBskNo());
				cpstInvInfo.setGodTurn(cartGodSub.getBskGod().getGodTurn());
				cpstInvInfo.setPckageGodTpCd(cartGodSub.getPckageGodTpCd());
				cpstInvInfo.setBrndNm(cartGodSub.getBrndNm());
				cpstInvInfo.setItmQty(cartGodSub.getBskGod().getItmQty());
				cpstInvInfo.setItmNo(cartGodSub.getBskGod().getItmNo());

				cartResult.getBskCpstGodCnnc().add(cartGodSub.getGod());
				cartResult.getBskCpstGodItmCnnc().add(cartGodSub.getGodItm());
				cartResult.getBskCpstInvInfo().add(cpstInvInfo);
				cartResult.setBskGod(godDetail.getBskGod());
				
				cartResult.setGod(godDetail.getGod());
				cartResult.setPrice(godDetail.getPrice() == null ? godDetail.getGod().getCsmrPrc() : godDetail.getPrice());
				cartResult.setPrcType(StringService.isEmpty(godDetail.getPrcType()) ? "GNRL" : godDetail.getPrcType());
								
			}else if(!"Y".equalsIgnoreCase(godDetail.getBskGod().getPckageGodYn()) &&//셋트도 아니고
					!"ADIT_CPST_GOD".equalsIgnoreCase(cartGodSub.getPckageGodTpCd())){//추가구성품도 아니면
				//단품
				cartResult.setBskGod(godDetail.getBskGod());
				cartResult.setGod(godDetail.getGod());
				cartResult.setPrice(godDetail.getPrice() == null ? godDetail.getGod().getCsmrPrc() : godDetail.getPrice());
				cartResult.setPrcType(StringService.isEmpty(godDetail.getPrcType()) ? "GNRL" : godDetail.getPrcType());
			}
		}

		if("Y".equalsIgnoreCase(godDetail.getSellYn())){
			cartResult.setInvYn(shopInvList.size()!=0 ? BskEnum.YES.toString() : BskEnum.NO.toString());//기본구성품이나 단품이 배정이 되었다면 Y	
		}else{			
			cartResult.setInvYn(BskEnum.NO.toString());
			//********************************editInvalidOrderGodPop.jsp용 추가변수 S******************************
			if(StringService.equalsIgnoreCase(BskEnum.DlvSect.PKUP_DLV.toString(), godDetail.getBskGod().getDlvSectCd())){
				if(StringService.equalsIgnoreCase(BskEnum.BskGodItmSaleStat.SALE_PROGRS.toString(),godDetail.getGod().getGodSaleSectCd())
						|| StringService.equalsIgnoreCase(BskEnum.BskGodItmSaleStat.SALE_PROGRS_PKUP.toString(),godDetail.getGod().getGodSaleSectCd())){

					godDetail.getGod().setGodSaleSectCd(godDetail.getGodItm().getItmStatCd());
				}
			}else{
				if(StringService.equalsIgnoreCase(BskEnum.BskGodItmSaleStat.SALE_PROGRS.toString(),godDetail.getGod().getGodSaleSectCd())){
					godDetail.getGod().setGodSaleSectCd(godDetail.getGodItm().getItmStatCd());
				}
			}
		}
		
		

		
		//추가구성품 배정
		//재고메트릭스만큼 돌면서 해당 Itm 주문수량만큼 빼서 배정가능재고위치 셋팅
		//재고매트릭스는 퀵배송 우선순위인 FC08 FC01 퀵배송매장 순으로 가져옴
		boolean isAsignedForAdit = false;//추가구성품 배정여부
		boolean isExistForAdit = false;//추가구성품 존재여부
		for(GodItmQtyResult dto : tempItmqtyList){//재고매트릭스는 장바구니 모든상품을 가지고있다			
			for(CartGodResult cartGodSub : godInvChkDTOList){//배정할 상품만 체크하기 위해 for 수행
				String subItmNo = cartGodSub.getBskGod().getItmNo();
				if(!isAsignedForAdit && subItmNo.equalsIgnoreCase(dto.getItmNo()) //아직 배정이 안되었다면 / 배정 대상 상품만
						&& "ADIT_CPST_GOD".equalsIgnoreCase(cartGodSub.getPckageGodTpCd()) //추가구성품인거만
						){
					isExistForAdit = true;
					for(String shopInv : shopInvList){//기본 구성상품이 배정 가능한 위치에서만 배정
						if(!isAsignedForAdit && shopInv.equals(dto.getChkShopId())){//아직 배정이 안되었다면
							if(!isAsignedForAdit && dto.getInvQty()>=itmOrdAditQtyMap.get(dto.getItmNo())){//해당재고위치의 재고와 주문수량체크
								//break; 하나밖에 못빠져나옴
								isAsignedForAdit=true;//배정됨
								assignedShop = shopInv;
							}	
						}
					}														
				}
			}	
		}
		
				
		//세트 추가구성품에 대한 cartResult 데이터 셋팅 
		for(CartGodResult cartGodSub : godInvChkDTOList){			
			
			if("ADIT_CPST_GOD".equalsIgnoreCase(cartGodSub.getPckageGodTpCd())){
				BskGodExtend cpstInvInfo = new BskGodExtend();			
				
				if(godDetail.getBskGod().getItmQty() < godDetail.getGod().getMinOrdQty()){
					cpstInvInfo.setAvailMinOrdCnt("N");
				}

				if(godDetail.getBskGod().getItmQty() > godDetail.getGod().getMaxOrdQty()){
					cpstInvInfo.setAvailMaxOrdCnt("N");
				}
				
				cpstInvInfo.setSellYn(godDetail.getSellYn());
				cpstInvInfo.setInvYn(isAsignedForAdit ? BskEnum.YES.toString() : BskEnum.NO.toString());
				
				
				cpstInvInfo.setParentGodTurn(cartGodSub.getParentGodTurn());
				cpstInvInfo.setBskNo(cartGodSub.getBskGod().getBskNo());
				cpstInvInfo.setGodTurn(cartGodSub.getBskGod().getGodTurn());
				cpstInvInfo.setPckageGodTpCd(cartGodSub.getPckageGodTpCd());
				cpstInvInfo.setBrndNm(cartGodSub.getBrndNm());
				cpstInvInfo.setItmQty(cartGodSub.getBskGod().getItmQty());
				cpstInvInfo.setItmNo(cartGodSub.getBskGod().getItmNo());

				cartResult.getBskCpstGodCnnc().add(cartGodSub.getGod());
				cartResult.getBskCpstGodItmCnnc().add(cartGodSub.getGodItm());
				cartResult.getBskCpstInvInfo().add(cpstInvInfo);
				
				cartResult.setBskGod(godDetail.getBskGod());
				cartResult.setGod(godDetail.getGod());
				cartResult.setPrice(godDetail.getPrice() == null ? godDetail.getGod().getCsmrPrc() : godDetail.getPrice());
				cartResult.setPrcType(StringService.isEmpty(godDetail.getPrcType()) ? "GNRL" : godDetail.getPrcType());
								
			}
		}
		if("Y".equalsIgnoreCase(godDetail.getBskGod().getPckageGodYn()) && isExistForAdit){//세트이고 추가구성품이 있으면
			if("Y".equalsIgnoreCase(godDetail.getSellYn())){//판매중이면
				cartResult.setAditInvYn(isAsignedForAdit ? BskEnum.YES.toString() : BskEnum.NO.toString());	
			}else{
				cartResult.setAditInvYn(BskEnum.NO.toString());			
			}			
		}

		//최상위세트
		if(StringService.equalsIgnoreCase(BskEnum.NO.toString(), godDetail.getSellYn())
			|| 	StringService.equalsIgnoreCase(BskEnum.NO.toString(), cartResult.getInvYn())
			|| StringService.equalsIgnoreCase(assignedShop, "NONE")
			|| (isExistForAdit && !isAsignedForAdit)
			){
			//재고없을때 >> 품절 및 수량 부족 안내 팝업 노출위해 셋팅
			cartResult.setGodItm(godDetail.getGodItm());
			cartResult.setBrndNm(godDetail.getBrndNm());
			cartResult.setParentGodTurn(godDetail.getParentGodTurn());
			cartResult.setPckageGodTpCd(godDetail.getPckageGodTpCd());
			cartResult.setSellYn(godDetail.getSellYn());
		}
		
		
		cartResult.setSellYn(godDetail.getSellYn());
		
		return assignedShop;
	}

	// '17.07.26 이진형추가
	public List<CartGodResult> getCartGodResultList(CartSearchDTO cartSearchDTO) throws Exception {

		List<CartGodResult> cartGodResultList = null;

		if(StringService.equalsIgnoreCase(BskEnum.DlvSect.PKUP_DLV.toString(), cartSearchDTO.getDlvSectCd())){
			cartGodResultList = cartSelectRepository.selectPkupCartList(cartSearchDTO);
		}else{
			cartGodResultList = cartSelectRepository.selectGnrlCartList(cartSearchDTO);
		}

		return cartGodResultList;
	}

	//배송지 필수입력값 입력여부체크 '17.08.29 이진형추가
	public boolean emptyChkLgsDlvsp(List<LgsDlvspDTO> lgsDlvspDTOList) throws Exception{

		boolean bChk = true;

		//List<LgsDlvspDTO> lgsDlvspDTOList = orderDTO.getLgsDlvspDTOList();

		if(lgsDlvspDTOList == null ){
			bChk = false;
		}else{
			if(lgsDlvspDTOList.size() < 1){
				bChk = false;
			}
		}

		for(LgsDlvspDTO lgsDlvspDTO : lgsDlvspDTOList){

			LgsDlvsp lgsDlvsp = lgsDlvspDTO.getLgsDlvsp();

			if(StringService.equalsIgnoreCase(lgsDlvsp.getDlvSectCd(), BskEnum.DlvSect.PKUP_DLV.toString())){ //매장픽업일경우
				if(StringService.isEmpty(lgsDlvsp.getAddrseMobilAreaNo())
						|| StringService.isEmpty(lgsDlvsp.getAddrseMobilTlofNo())
						|| StringService.isEmpty(lgsDlvsp.getAddrseMobilTlofWthnNo()) ){

					bChk = false;

				}
			}else{ //매장픽업외 (사실상 일반배송)
				if(StringService.isEmpty(lgsDlvsp.getAddrseBaseAddr())
						|| StringService.isEmpty(lgsDlvsp.getAddrseDetailAddr())
						|| StringService.isEmpty(lgsDlvsp.getAddrseMobilAreaNo())
						|| StringService.isEmpty(lgsDlvsp.getAddrseMobilTlofNo())
						|| StringService.isEmpty(lgsDlvsp.getAddrseMobilTlofWthnNo()) ){

					bChk = false;

				}
			}
		}

		return bChk;

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
	public List<CartResult> selectInvalidCartGodList(CartSearchDTO cartSearchDTO) throws Exception {

		// 접속한 장비가 PC가 아닐 경우 이미지 사이즈를 90X120으로 정의 ==> 255X337로 변경 (SR#16714)
		if(!StringService.equalsIgnoreCase("PC", StringService.nvl(cartSearchDTO.getDevice(),"PC"))){
			cartSearchDTO.setImgSizeCd("255X337");
		}

		this.setEmpYn(cartSearchDTO);

		//17.7.26 이진형 추가 S
		GodItmQtySearchDTO giqsd = new GodItmQtySearchDTO();
		giqsd.setDlvSectCd(cartSearchDTO.getDlvSectCd());
		//17.7.26 이진형 추가 E

		
		List<CartGodResult> cartGodResultList = null;

		//1.장바구니 상품 정보 조회
		if(StringService.equalsIgnoreCase(BskEnum.DlvSect.PKUP_DLV.toString(), cartSearchDTO.getDlvSectCd())){
			cartGodResultList = cartSelectRepository.selectPkupCartList(cartSearchDTO);
		}else{
			cartGodResultList = cartSelectRepository.selectGnrlCartList(cartSearchDTO);
		}

		//17.7.26 이진형 추가 S
		List<CartResult> cartList = ordGodQtyValidCheck(cartGodResultList, giqsd, cartSearchDTO);
        //17.7.26 이진형 추가 E


		return cartList;
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
	public List<CartSimpleListResult> selectBskListByGods(CartSearchDTO cartSearchDTO) throws Exception{

		int bskCnt = 0;

		List<CartSimpleListResult> list = new ArrayList<CartSimpleListResult>();

		List<CartGodResult> cartGodResultList = cartSelectRepository.selectBskListByGods(cartSearchDTO);

		for(CartGodResult godDetail : cartGodResultList){
			if( godDetail.getBskGod().getGodTurn() == godDetail.getParentGodTurn()
			  || StringService.equalsIgnoreCase(BskEnum.PckageGodTp.ADIT_CPST_GOD.toString(), godDetail.getPckageGodTpCd())
			 ){
				CartSimpleListResult data = new CartSimpleListResult();


				if(StringService.equalsIgnoreCase(BskEnum.YES.toString(), godDetail.getBskGod().getPckageGodYn())
				   && godDetail.getBskGod().getGodTurn() == godDetail.getParentGodTurn()
				){
					List<BskGodExtend> cpstList = new ArrayList<BskGodExtend>();
					for(CartGodResult godSub : cartGodResultList){
						if(godDetail.getBskGod().getGodTurn() == godSub.getParentGodTurn()
						   && StringService.isNotEmpty(godSub.getPckageGodTpCd())
						   && !StringService.equalsIgnoreCase(BskEnum.PckageGodTp.ADIT_CPST_GOD.toString(), godSub.getPckageGodTpCd())
						){
							BskGodExtend cpstGod = new BskGodExtend();
							if(godSub.getGodItm() != null){
								cpstGod.setItmNm(godSub.getGodItm().getItmNm());
								cpstGod.setColorCd(godSub.getGod().getColorCd());
								cpstGod.setColorNm(godSub.getGod().getColorNm());
								cpstGod.setClorChipImgUrl(godSub.getGod().getClorChipImgUrl());
							}
							cpstGod.setPckageGodTpCd(godSub.getPckageGodTpCd());
							cpstList.add(cpstGod);
						}
					}
					data.setCpstGodList(cpstList);
				}

				BskGodExtend bskGodExtend = new BskGodExtend();
				bskGodExtend.setPckageGodYn(godDetail.getBskGod().getPckageGodYn());
				bskGodExtend.setPckageGodTpCd(StringService.isEmpty(godDetail.getPckageGodTpCd()) ? "MASTER" : godDetail.getPckageGodTpCd());
				bskGodExtend.setItmQty(godDetail.getBskGod().getItmQty());
				if(godDetail.getGodItm() != null){
					bskGodExtend.setItmNm(godDetail.getGodItm().getItmNm());
				}
				bskGodExtend.setDlvSectCd(godDetail.getBskGod().getDlvSectCd());
				bskGodExtend.setGodNo(godDetail.getBskGod().getGodNo());
				bskGodExtend.setGodNm(godDetail.getGod().getGodNm());
				bskGodExtend.setColorNm(godDetail.getGod().getColorNm());
				bskGodExtend.setBrndNm(godDetail.getBrndNm());
				bskGodExtend.setImgUrl(godDetail.getImgUrl());
				bskGodExtend.setPartmalSectCd(godDetail.getGod().getPartmalSectCd());
				bskGodExtend.setCsmrPrc(godDetail.getGod().getCsmrPrc());

				
				bskGodExtend.setErpGodNo(godDetail.getGod().getErpGodNo());

				data.setBskGod(bskGodExtend);
				list.add(data);

				if(godDetail.getBskGod().getGodTurn() == godDetail.getParentGodTurn()){
					bskCnt++;
				}
			}
		}


		if(list.size() > 0){
			list.get(0).setBskCnt(bskCnt);
		}

		return list;
	}


	/**
	 * [단품 재고 수량 조회].
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
	public int selectGodInvCount(BskGod god)throws Exception{
		/*
		 * 배송 구분 코드
		 *	ㅁ. 배송 구분 : DLV_SECT
		 *	   >. 일반 배송 : GNRL_DLV
		 *	   >. 픽업 배송 : PKUP_DLV
		 *	   >. 해외 배송 : OVSEA_DLV
		 */
		// 픽업 배송인 경우
		if(StringService.equalsIgnoreCase(BskEnum.DlvSect.PKUP_DLV.toString(), god.getDlvSectCd())){
			// 매장 판매 상품 재고 조회
			return cartSelectRepository.selectShopGodInvCount(god);
		}else if("QDLV".equals(god.getDlvSectCd())){// 퀵배송 추가
			// 퀵배송 상품 재고조회
			return cartSelectRepository.selectShopGodInvQdlvCount(god);
		}else if("RSV".equals(god.getDlvSectCd())){	// 예약장바구니 기능 추가  : by cannon (2016.04.18)
			// 예약 판매 상품 재고 조회
			return cartSelectRepository.selectResvGodInvCount(god);
		}else{
			// 온라인 판매 상품 재고 조회
			return cartSelectRepository.selectOnlineGodInvCount(god);
		}
	}

	/**
	 * [예약상품 재고 수량 조회].
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
	public int selectResvGodInvCount(BskGod god) throws Exception{
		return cartSelectRepository.selectResvGodInvCount(god);
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
	public List<CartCpstGodResult> selectBskCpstGodCnnc(BskGod god)throws Exception{

		return cartSelectRepository.selectBskCpstGodCnnc(god);
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
	public List<CartBskNoForUpdateResult> selectBskForUpdate(String mbrNo)throws Exception{

		return cartSelectRepository.selectBskForUpdate(mbrNo);
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

		return cartSelectRepository.selectGodOptionList(god);
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

		return cartSelectRepository.selectPckageGodOptionList(god);
	}

	public List<CartOptChgGodResult> selectPckageGodOptionListPickup(CartOptChgSearchDTO search) throws Exception{

		List<CartOptChgGodResult> result = new ArrayList<CartOptChgGodResult>();

		result = cartSelectRepository.selectOptChgCpstList(search);

		return result;
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
	public List<BskGodExtend> selectCartTpCount(CartSearchDTO cartSearchDTO) throws Exception{

		this.setEmpYn(cartSearchDTO);

		return cartSelectRepository.selectCartTypeCount(cartSearchDTO);
    }


	/**
	 * [메서드 설명].
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param wish [설명]
	 * @return Bsk wishlst [설명]
	 * @throws Exception the exception
	 * @since 2015. 6. 19
	 */
	public BskWishlst selectWishList(BskWishlst wish) throws Exception{

		return cartSelectRepository.selectBskWishlst(wish);
    }

	/**
	 * [메서드 설명].
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param bskGod [설명]
	 * @return God info result [설명]
	 * @throws Exception the exception
	 * @since 2015. 6. 19
	 */
	public GodInfoResult selectBskGodItm(BskGod bskGod) throws Exception{
		GodInfoResult result = new GodInfoResult();
		//상품 조회
		List<BskGodExtend> god = cartSelectRepository.selectBskGodItm(bskGod);

		result.setGod(god.get(0));

		if(god.size() > 1 && StringService.equalsIgnoreCase(BskEnum.YES.toString(), god.get(0).getPckageGodYn())){

			List<BskGodExtend> cpstList = new ArrayList<BskGodExtend>();
			for(BskGodExtend itm : god){
				if(itm.getGodTurn() != itm.getParentGodTurn()){
					cpstList.add(itm);
				}
			}

			result.setCpstGodList(cpstList);
		}
	    return result;
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

		return cartSelectRepository.selectStplatList(stplatUseTpCd);
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
	public List<CartResult> selectPkupShopInfo(CartSearchDTO cartSearchDTO) throws Exception{

		// 접속한 장비가 PC가 아닐 경우 이미지 사이즈를 90X120으로 정의 ==> 255X337로 변경 (SR#16714)
		if(!StringService.equalsIgnoreCase("PC", StringService.nvl(cartSearchDTO.getDevice(),"PC"))){
			cartSearchDTO.setImgSizeCd("255X337");
		}

		List<CartResult> cartList = new ArrayList<CartResult>();
		List<CartGodResult> cartGodResultList = cartSelectRepository.selectPkupCartList(cartSearchDTO);
		BskGod bskGod = null;
		God god = null;

		for(CartGodResult godDetail : cartGodResultList){
			if(godDetail.getBskGod().getGodTurn() == godDetail.getParentGodTurn()
			  || StringService.equalsIgnoreCase(BskEnum.PckageGodTp.ADIT_CPST_GOD.toString(), godDetail.getPckageGodTpCd())
			 ){
				bskGod = godDetail.getBskGod();
				CartResult cartResult = new CartResult();
				cartResult.setCrntDay(godDetail.getCrntDay());
				god = godDetail.getGod();
				//패키지형 상품
				if(StringService.equalsIgnoreCase(BskEnum.YES.toString(), bskGod.getPckageGodYn())
				    && 	bskGod.getGodTurn() == godDetail.getParentGodTurn()
				 ){
					boolean isInvYn = true;
					int pkupDay = 99999999;
					for(CartGodResult godSub : cartGodResultList){
						if(bskGod.getGodTurn() == godSub.getParentGodTurn() && StringService.isNotEmpty(godSub.getPckageGodTpCd())){
							if(!StringService.equalsIgnoreCase(BskEnum.PckageGodTp.ADIT_CPST_GOD.toString(), godSub.getPckageGodTpCd())){

								cartResult.getBskGodList().add(godSub.getBskGod());
								cartResult.getBskCpstGodCnnc().add(godSub.getGod());
								cartResult.getBskCpstGodItmCnnc().add(godSub.getGodItm());

								if(godSub.getBskGod().getItmQty() > godSub.getShopInvQty() + godSub.getCdcInvQty()){
									isInvYn = false;
								}else{
									if(StringService.isNotEmpty(godSub.getPkupDay())){
										if(pkupDay > Integer.parseInt(godSub.getPkupDay())){
											pkupDay = Integer.parseInt(godSub.getPkupDay());
											cartResult.setPkupDay(godSub.getPkupDay());
										}
									}
								}
							}
						}
					}

					if(isInvYn){
						cartResult.setInvYn(BskEnum.YES.toString());
					}else{
						cartResult.setInvYn(BskEnum.NO.toString());
					}
				}else{
					if(bskGod.getItmQty() > godDetail.getShopInvQty() + godDetail.getCdcInvQty()){
						cartResult.setInvYn(BskEnum.NO.toString());
					}else{
						cartResult.setInvYn(BskEnum.YES.toString());
						cartResult.setPkupDay(godDetail.getPkupDay());
					}
				}

				cartResult.setBskGod(bskGod);
				cartResult.setGod(god);
				cartResult.setSidoCd(godDetail.getSidoCd());
				cartResult.setGodItm(godDetail.getGodItm());
				cartResult.setBrndNm(godDetail.getBrndNm());
				cartResult.setShopNm(godDetail.getShopNm());
				cartResult.setRealInvQty(godDetail.getRealInvQty());
				cartResult.setHoffInvQty(godDetail.getHoffInvQty());
				cartResult.setEtcInvQty(godDetail.getEtcInvQty());
				
				cartResult.setRowSpanCount(godDetail.getRowSpanCount());
				cartResult.setHoldyYn(godDetail.getHoldyYn());
				cartResult.setShopAddr(godDetail.getShopAddr());
				cartResult.setShopEndHhmm(godDetail.getShopEndHhmm());
				cartResult.setShopBegHhmm(godDetail.getShopBegHhmm());
				cartResult.setParentGodTurn(godDetail.getParentGodTurn());
				cartResult.setSellYn(godDetail.getSellYn());
				cartResult.setCrntDay(godDetail.getCrntDay());
				cartList.add(cartResult);
			}
		}

	    return cartList;
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

		this.setEmpYn(cartSearchDTO);
		return cartSelectRepository.selectMbrCartCnt(cartSearchDTO);
    }


	public CartGodPrmResult selectGodGft(CartSearchDTO cartSearchDTO) throws Exception{

		ArrayList<CartPrmResult> paramList = new ArrayList<CartPrmResult>();
		CartPrmResult param = new CartPrmResult();
		param.setPrmNo(cartSearchDTO.getPrmNo());
		param.setGodNo(cartSearchDTO.getGodNo());
		paramList.add(param);

		cartSearchDTO.setCartPrmResultList(paramList);

		return cartSelectRepository.selectGodGiftSingle(cartSearchDTO);
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
	 * @param pAmt the amt
	 * @param crncy [설명]
	 * @return Big decimal [설명]
	 * @throws Exception the exception
	 * @since 2015. 6. 19
	 */
	private BigDecimal roundHalfUp(BigDecimal pAmt,String crncy) throws Exception{

		BigDecimal amt = pAmt;

		if(StringService.equalsIgnoreCase("KRW", crncy)){
			amt = pAmt.setScale(0,BigDecimal.ROUND_DOWN).divide(BigDecimal.TEN).setScale(0,BigDecimal.ROUND_HALF_UP).multiply(BigDecimal.TEN);
		}else{
			amt = pAmt.multiply(new BigDecimal(1000)).setScale(0,BigDecimal.ROUND_DOWN).divide(BigDecimal.TEN).setScale(0,BigDecimal.ROUND_HALF_UP).divide(new BigDecimal(100));
		}
		return amt;
	}

	/**
	 * [메서드 설명].
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param pAmt the amt
	 * @param crncy [설명]
	 * @return Big decimal [설명]
	 * @throws Exception the exception
	 * @since 2015. 6. 19
	 */
	private BigDecimal roundUp(BigDecimal pAmt,String crncy) throws Exception{

		BigDecimal amt = pAmt;

/*		if(StringService.equalsIgnoreCase("KRW", crncy)){*/
			//amt = pAmt.setScale(0,BigDecimal.ROUND_DOWN).divide(BigDecimal.TEN).setScale(0,BigDecimal.ROUND_UP).multiply(BigDecimal.TEN);
			final int movePoint = 1;
			amt  =  pAmt.movePointLeft(movePoint).setScale(0, RoundingMode.UP).movePointRight(movePoint);

/*		}else{
			amt = pAmt.multiply(new BigDecimal(1000)).setScale(0,BigDecimal.ROUND_DOWN).divide(BigDecimal.TEN).setScale(0,BigDecimal.ROUND_UP).divide(new BigDecimal(100));
		}*/

		return amt;
	}
	
	private BigDecimal down(BigDecimal pAmt,String crncy) throws Exception{

		BigDecimal amt = pAmt;

/*		if(StringService.equalsIgnoreCase("KRW", crncy)){*/
			//amt = pAmt.setScale(0,BigDecimal.ROUND_DOWN).divide(BigDecimal.TEN).setScale(0,BigDecimal.ROUND_UP).multiply(BigDecimal.TEN);
			final int movePoint = 1;
			amt  =  pAmt.movePointLeft(movePoint).setScale(0, RoundingMode.DOWN).movePointRight(movePoint);

/*		}else{
			amt = pAmt.multiply(new BigDecimal(1000)).setScale(0,BigDecimal.ROUND_DOWN).divide(BigDecimal.TEN).setScale(0,BigDecimal.ROUND_UP).divide(new BigDecimal(100));
		}*/

		return amt;
	}



	/**
	 * [메서드 설명].
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param prm [설명]
	 * @param price [설명]
	 * @param crncy [설명]
	 * @param aplyMaxAmt [설명]
	 * @return Big decimal [설명]
	 * @throws Exception the exception
	 * @since 2015. 6. 19
	 */
	private BigDecimal getPrmDcAmt(Prm prm , BigDecimal price,String crncy,boolean aplyMaxAmt)throws Exception{

		BigDecimal dcAmt = BigDecimal.ZERO;
		String dcSectCd = prm.getDcSectCd();

		if(StringService.equalsIgnoreCase(BskEnum.BskDcSect.FIXAMT.toString(), dcSectCd)){

			if(price.compareTo(prm.getDcAmt())< 0){
				dcAmt = prm.getDcAmt().subtract((prm.getDcAmt().subtract(price)));
			}else{
				dcAmt = prm.getDcAmt();
			}
		}else if(StringService.equalsIgnoreCase(BskEnum.BskDcSect.FIXRT.toString(), dcSectCd)){

			dcAmt = this.down(price.multiply(prm.getDcRt().multiply(new BigDecimal(0.01),MathContext.DECIMAL32)),crncy);

			if(aplyMaxAmt && prm.getMaxDcPsbAmt().compareTo(BigDecimal.ZERO) > 0){
				if(prm.getMaxDcPsbAmt().compareTo(BigDecimal.ZERO) > 0 && dcAmt.compareTo(prm.getMaxDcPsbAmt()) > 0){
					dcAmt= prm.getMaxDcPsbAmt();
				}
			}
		}else if(StringService.equalsIgnoreCase(BskEnum.BskDcSect.SALE_AMT.toString(), dcSectCd)){
			//균일가가 상품가격보다 클경우 프로모션 미적용
			if(price.compareTo(prm.getSaleUntPrc()) > 0){
				dcAmt = price.subtract(prm.getSaleUntPrc());
			}else{
				dcAmt = BigDecimal.ZERO;
			}
		}


		if(dcAmt.compareTo(price) > 0){
			dcAmt = price;
		}

		return dcAmt;
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
	private void setEmpYn(CartSearchDTO cartSearchDTO) throws Exception{
		if(cartSearchDTO.getMbr() != null && StringService.isNotEmpty(cartSearchDTO.getMbr().getMbrNo())){
			if(StringService.equalsIgnoreCase(cartSearchDTO.getMbr().getMbrAtrbCd(), MemberAtrbCd.EMP.toString())
			  ){
				//cartSearchDTO.setEmpYn(BskEnum.YES.toString());
			}
		}
	}

	public BigDecimal setGodPromotion(CartResult cartResult, CartGodResult godDetail, String prcType, ListMultimap<String, CartGodPrmResult> gnlPrmListMap, String crncy, BigDecimal csmrPrice, BigDecimal tmpPrice) throws Exception {
		if (gnlPrmListMap.isEmpty() || !StringService.equalsIn(prcType, "GNRL", "B2E", "SINGLE")) return tmpPrice;

		// 할인금액
		BigDecimal dcAmt = BigDecimal.ZERO;

		for(CartGodPrmResult prmDtl : gnlPrmListMap.get(godDetail.getBskGod().getGodNo())){

			if(!(StringService.equalsIgnoreCase("GNRL", prcType)
			   && StringService.equalsIgnoreCase(BskEnum.BskPrmDtlType.SUBD_GOD_DC.toString(), prmDtl.getPrm().getPrmDtlTpCd()))
			   &&
			   !(StringService.equalsIgnoreCase("B2E", prcType)
					  && StringService.equalsIgnoreCase(BskEnum.BskPrmDtlType.B2E_SPSL.toString(), prmDtl.getPrm().getPrmDtlTpCd()))
			   &&
			   !(StringService.equalsIgnoreCase("SINGLE", prcType)
					&& StringService.equalsIgnoreCase(BskEnum.BskPrmDtlType.SIGNL_SPSL.toString(), prmDtl.getPrm().getPrmDtlTpCd()))) {
				continue;
			}

			dcAmt = this.getPrmDcAmt(prmDtl.getPrm(),csmrPrice,crncy,true);
			if((csmrPrice.subtract(dcAmt)).compareTo(tmpPrice) < 0){
				tmpPrice = csmrPrice.subtract(dcAmt);
				cartResult.setGodPrmNo(prmDtl.getPrm().getPrmNo());
				cartResult.setGodPrmSectCd(prmDtl.getPrm().getDcSectCd());
				cartResult.setGodPrmAmt(prmDtl.getPrm().getDcAmt());
				cartResult.setGodPrmDcRt(prmDtl.getPrm().getDcRt());
				cartResult.setGodPrmSaleUntPrc(prmDtl.getPrm().getSaleUntPrc());
				cartResult.setGodPrmType(prmDtl.getPrm().getPrmDtlTpCd());
				cartResult.setGodPrmDcAmt(dcAmt);
			}
		}

		return tmpPrice;
	}

	public void setDiscountPrm(CartResult 		cartResult,
			BskGod 								bskGod,
			List<CartGodPrmResult> 				bndPrmResultList,
			List<CartGodPrmResult> 				crsPrmResultList,
			List<CartGodPrmResult> 				cpnPrmResultList,
			HashMap<String,CartGodPrmResult> 	bskPrmMap,
			CartGodPrmResult 					tmpDtl,
			CartGodResult 						godDetail,
			God 								god,
			String 								prcType,
			BigDecimal							tmpPrice,
			CartSearchDTO						cartSearchDTO
			)throws Exception
			{

				if(   !StringService.equalsIgnoreCase("B2E", prcType)
				   && !StringService.equalsIgnoreCase("SINGLE", prcType)){
					// 묶음 할인
					bundlePromoCheck(cartResult, bskGod, bndPrmResultList, bskPrmMap, tmpDtl, godDetail, god, prcType);
					// 교차 할인
					crossPromoCheck(cartResult, bskGod, crsPrmResultList, bskPrmMap, tmpDtl, godDetail, god, prcType);
				}

				// 즉시 할인 쿠폰
				if(StringService.equalsIgnoreCase("GNRL", prcType)){

						if(!CollectionService.isEmpty(cpnPrmResultList)){
							BigDecimal cpnAplyDcAmt = BigDecimal.ZERO;
							cartResult.setCpnAplyDcAmt(cpnAplyDcAmt);

							for(CartGodPrmResult prmDtl : cpnPrmResultList){

								if(StringService.equalsIgnoreCase(prmDtl.getGodNo(), bskGod.getGodNo())){

									if(StringService.equalsIgnoreCase("IMPS", prmDtl.getPrm().getGodDcDplctCd())
									   && StringService.isNotEmpty(cartResult.getGodPrmNo())
									  ){
										 continue;
									  }

									if(tmpPrice.compareTo(BigDecimal.ZERO) > 0){
										cpnAplyDcAmt = this.getPrmDcAmt(prmDtl.getPrm(),tmpPrice,cartSearchDTO.getCrncyCd(),true);
										if(cartResult.getCpnAplyDcAmt().compareTo(cpnAplyDcAmt) < 0){
											cartResult.setCpnNo(prmDtl.getPrm().getPrmNo());
											cartResult.setCpnNm(prmDtl.getPrm().getPrmNm());
											cartResult.setCpnType(prmDtl.getPrm().getPrmDtlTpCd());
											cartResult.setCpnMaxDcAmt(prmDtl.getPrm().getMaxDcPsbAmt());
											cartResult.setCpnDcSectCd(prmDtl.getPrm().getDcSectCd());
											cartResult.setCpnDcAmt(prmDtl.getPrm().getDcAmt());
											cartResult.setCpnDcRt(prmDtl.getPrm().getDcRt());
											cartResult.setGodDcDplctCd(prmDtl.getPrm().getGodDcDplctCd());
											cartResult.setCpnAplyDcAmt(cpnAplyDcAmt);
											cartResult.setCpnAplyBalancedDcAmt(cpnAplyDcAmt);
										}
									}
								}
							}
						}
				}

			}


	/**
	 *
	* <pre>
	* 묶음 할인 프로모션 체크
	* </pre>
	* @param cartResult
	* @param bskGod
	* @param bndPrmResultList
	* @param bskPrmMap
	* @param tmpDtl
	* @param godDetail
	* @param god
	* @param prcType
	* @throws Exception
	* @since 2015. 11. 17.
	 */
	public void bundlePromoCheck(	CartResult 							cartResult,
								BskGod 								bskGod,
								List<CartGodPrmResult> 				bndPrmResultList,
								HashMap<String,CartGodPrmResult> 	bskPrmMap,
								CartGodPrmResult 					tmpDtl,
								CartGodResult 						godDetail,
								God 								god,
								String 								prcType) throws Exception{
		/* 묶음 프로모션 리스트가 존재하면 현재 장바구니 상품내에 프로모션이 존재하는 지 확인 후
		 * 묶음 프로모션의 상품번호와 장바구니내의 상품번호가 같으면 체크 로직을 수행
		 * 각 상품 할인금액을 재계산
		 */
		if(!CollectionService.isEmpty(bndPrmResultList)){
			for(CartGodPrmResult prmDtl : bndPrmResultList){
				if( godDetail.getGrpSeq() == prmDtl.getGrpSeq()
					&& StringService.equalsIgnoreCase(prmDtl.getGodNo(), bskGod.getGodNo())){

					if(bskPrmMap.containsKey(prmDtl.getPrm().getPrmNo()+"_"+prmDtl.getGrpSeq())){
						tmpDtl = bskPrmMap.get(prmDtl.getPrm().getPrmNo()+"_"+prmDtl.getGrpSeq());
					}else{
						tmpDtl = prmDtl;
					}

					if(StringService.isNotEmpty(cartResult.getGodPrmNo())){
						tmpDtl.setOrdAmt(tmpDtl.getOrdAmt().add(god.getCsmrPrc().subtract(cartResult.getGodPrmDcAmt()).multiply(new BigDecimal(bskGod.getItmQty()))));
					}else{
						if(StringService.equalsIgnoreCase("EMP", prcType)){
							tmpDtl.setOrdAmt(tmpDtl.getOrdAmt().add(god.getEmpPrc().multiply(new BigDecimal(bskGod.getItmQty()))));
						}else{
							tmpDtl.setOrdAmt(tmpDtl.getOrdAmt().add(god.getCsmrPrc().multiply(new BigDecimal(bskGod.getItmQty()))));
						}

					}

					tmpDtl.getGodNoList().add(prmDtl.getGodNo());
					bskPrmMap.put(prmDtl.getPrm().getPrmNo()+"_"+prmDtl.getGrpSeq(),tmpDtl);

					cartResult.setBskPrmNo(prmDtl.getPrm().getPrmNo());
					cartResult.setBskPrmNm(prmDtl.getPrm().getPrmNm());
					cartResult.setBskPrmType(prmDtl.getPrm().getPrmDtlTpCd());
					cartResult.setOrdDcRelatePromtSn(prmDtl.getPrm().getOrdDcRelatePromtSn());
					cartResult.setRelateUrl(prmDtl.getPrm().getRelateUrl());
					cartResult.setBskPrmAplyDcAmt(BigDecimal.ZERO);
					cartResult.setBskPrmAplyBalancdDcAmt(BigDecimal.ZERO);
					break;
				}
			}
		}
	}

	/**
	 *
	* <pre>
	* 교차 할인 프로모션 체크
	* </pre>
	* @param cartResult
	* @param bskGod
	* @param crsPrmResultList
	* @param bskPrmMap
	* @param tmpDtl
	* @param godDetail
	* @param god
	* @param prcType
	* @throws Exception
	* @since 2015. 11. 17.
	 */
	public void crossPromoCheck(	CartResult 							cartResult,
			BskGod 								bskGod,
			List<CartGodPrmResult> 				crsPrmResultList,
			HashMap<String,CartGodPrmResult> 	bskPrmMap,
			CartGodPrmResult 					tmpDtl,
			CartGodResult 						godDetail,
			God 								god,
			String 								prcType) throws Exception{
			/* 교차 프로모션 리스트가 존재하면 현재 장바구니 상품내에 프로모션이 존재하는 지 확인 후
			 * 교차 프롬모션의 상품번호와 장바구니내의 상품번호가 같으면 체크 로직을 수행
			 * 각 상품 할인금액을 재계산
			 */

			if(!CollectionService.isEmpty(crsPrmResultList)){
				for(CartGodPrmResult prmDtl : crsPrmResultList){
					if(godDetail.getGrpSeq() == prmDtl.getGrpSeq()
					   && StringService.equalsIgnoreCase(prmDtl.getGodNo(), bskGod.getGodNo())){

						cartResult.setBskPrmNo(prmDtl.getPrm().getPrmNo());
						cartResult.setBskPrmNm(prmDtl.getPrm().getPrmNm());
						cartResult.setBskPrmType(prmDtl.getPrm().getPrmDtlTpCd());
						cartResult.setBskPrmSectCd(prmDtl.getPrm().getDcSectCd());
						cartResult.setBskPrmDcAmt(prmDtl.getPrm().getDcAmt());
						cartResult.setBskPrmDcRt(prmDtl.getPrm().getDcRt());
						cartResult.setCrsGodGrpCd(prmDtl.getCrsGodGrpCd());
						cartResult.setBskPrmAplyDcAmt(BigDecimal.ZERO);
						cartResult.setBskPrmAplyBalancdDcAmt(BigDecimal.ZERO);
						cartResult.setOrdDcRelatePromtSn(prmDtl.getPrm().getOrdDcRelatePromtSn());
						cartResult.setRelateUrl(prmDtl.getPrm().getRelateUrl());

						if(prmDtl.getGrp1Cnt() >=1 && prmDtl.getGrp2Cnt() >=1){

							if(bskPrmMap.containsKey(prmDtl.getPrm().getPrmNo()+"_"+prmDtl.getGrpSeq())){
								tmpDtl = bskPrmMap.get(prmDtl.getPrm().getPrmNo()+"_"+prmDtl.getGrpSeq());
							}else{
								tmpDtl = prmDtl;
							}

							if(StringService.isNotEmpty(cartResult.getGodPrmNo())){
								tmpDtl.setOrdAmt(tmpDtl.getOrdAmt().add(god.getCsmrPrc().subtract(cartResult.getGodPrmDcAmt()).multiply(new BigDecimal(bskGod.getItmQty()))));
							}else{
								if(StringService.equalsIgnoreCase("EMP", prcType)){
									tmpDtl.setOrdAmt(tmpDtl.getOrdAmt().add(god.getEmpPrc().multiply(new BigDecimal(bskGod.getItmQty()))));
								}else{
									tmpDtl.setOrdAmt(tmpDtl.getOrdAmt().add(god.getCsmrPrc().multiply(new BigDecimal(bskGod.getItmQty()))));
								}
							}
							tmpDtl.setGodItmQty(tmpDtl.getGodItmQty()+bskGod.getItmQty().intValue());
							tmpDtl.getGodNoList().add(prmDtl.getGodNo());
							bskPrmMap.put(prmDtl.getPrm().getPrmNo()+"_"+prmDtl.getGrpSeq(),tmpDtl);
						}
						break;
					}
				}
			}
	}

	/**
	 *
	* <pre>
	*재고 가능 여부 체크
	* </pre>
	* @since 2015. 10. 28.
	 */
	public void checkInventory(BskGod bskGod, List<CartGodResult> cartGodResultList, CartGodResult godDetail, CartResult cartResult, God god,CartSearchDTO cartSearchDTO)throws Exception{

		if("N".equals(cartSearchDTO.getVirtlDlvComptYn())){
			//패키지형 상품
			if(StringService.equalsIgnoreCase(BskEnum.YES.toString(), bskGod.getPckageGodYn())
			    && 	bskGod.getGodTurn() == godDetail.getParentGodTurn()
			 ){
	
				//재고가능여부
				boolean isInvYn = true;
				//추가상품 카운트
				int aditCnt = 0;
	
				//int pkupDay = 99999999;
				int pkupDay = 0;
	
	
				if(StringService.equalsIgnoreCase(BskEnum.DlvSect.PKUP_DLV.toString(), bskGod.getDlvSectCd())){//픽업세트 재고체크
					isInvYn = false;
	//				// 판매중인 상품인 경우
	//				if(StringService.equalsIgnoreCase(BskEnum.YES.toString(), godDetail.getSellYn())){
	//					List<CartGodDTO> cpstGodList = new ArrayList<CartGodDTO>();
	//
	//					GodItmQtySearchDTO godItmQtySearchDTO = new GodItmQtySearchDTO();
	//					godItmQtySearchDTO.setDlvSectCd(bskGod.getDlvSectCd());
	//					godItmQtySearchDTO.setShopId(bskGod.getPkupShopSn());
	//
	//
	//
	//					for(CartGodResult godSub : cartGodResultList){
	//						if(bskGod.getGodTurn() == godSub.getParentGodTurn() && StringService.isNotEmpty(godSub.getPckageGodTpCd())){
	//
	//							if(!StringService.equalsIgnoreCase(BskEnum.PckageGodTp.ADIT_CPST_GOD.toString(), godSub.getPckageGodTpCd())){
	//								//추가 구성 상품이 아닌경우
	//								cartResult.getBskGodList().add(godSub.getBskGod());
	//								cartResult.getBskCpstGodCnnc().add(godSub.getGod());
	//								cartResult.getBskCpstGodItmCnnc().add(godSub.getGodItm());
	//							}else{
	//								//추가구성품이면
	//								//추가 구성상품 카운트
	//								aditCnt++;
	//							}
	//
	//							//추가구성품도 포함
	//							CartGodDTO cartGodDTO = new CartGodDTO();
	//							cartGodDTO.setItmNo(godSub.getBskGod().getItmNo());
	//							cartGodDTO.setItmQty(godSub.getBskGod().getItmQty());
	//							cpstGodList.add(cartGodDTO);
	//
	//							if (StringService.isNotEmpty(godSub.getPkupDay()) && pkupDay < Integer.parseInt(godSub.getPkupDay())) {
	//								pkupDay = Integer.parseInt(godSub.getPkupDay());
	//								cartResult.setPkupDay(godSub.getPkupDay());
	//							}
	//
	//						}
	//					}
	//
	//
	//					String[] itmArr = new String[cpstGodList.size()];//재고메트릭스 조회위한 itm배열
	//					int arrcnt = 0;
	//					HashMap<String,Long> itmOrdQtyMap = new HashMap<String,Long>(); //itm 단위 구매수량
	//					for(CartGodDTO godDTO : cpstGodList){//itmNo 배열에 담는다
	//						itmArr[arrcnt] = godDTO.getItmNo();
	//						arrcnt++;
	//
	//						//구성품만큼 돌면서 itm 단위로 주문수량을 Map 에 묶어서 셋팅
	//						if(null!=itmOrdQtyMap.get(godDTO.getItmNo())){//이미있으면 누적
	//							itmOrdQtyMap.put(godDTO.getItmNo(), godDTO.getItmQty()+itmOrdQtyMap.get(godDTO.getItmNo()));
	//						}else{//없으면 map 에 add
	//							itmOrdQtyMap.put(godDTO.getItmNo(), godDTO.getItmQty());
	//						}
	//
	//					}
	//					godItmQtySearchDTO.setItmNoArr(itmArr);
	//					List<GodItmQtyResult> itmqtyList = this.selectGodItmQty(godItmQtySearchDTO);//itm별 재고가져옴(재고메트릭스)
	//
	//					boolean isShopInv = true;
	//					
	//
	//					//재고메트릭스만큼 돌면서 해당 Itm 주문수량만큼 빼서 배정가능재고위치 셋팅
	//					for(GodItmQtyResult dto : itmqtyList){
	//						if(isShopInv){
	//							if(dto.getShopInvQty()<itmOrdQtyMap.get(dto.getItmNo())){//매장재고보다 주문수량이 크면
	//								isShopInv = false; //매장재고 아님
	//							}
	//						}
	//					}
	//
	//					if(!isShopInv){//어디에도 배정하지 못하면
	//						//결품
	//						isInvYn = false;
	//
	//						for(CartGodResult godSub : cartGodResultList){
	//							if(bskGod.getGodTurn() == godSub.getParentGodTurn() && StringService.isNotEmpty(godSub.getPckageGodTpCd())){
	//								godSub.setInvYn(BskEnum.NO.toString());
	//							}
	//						}
	//					}
	//
	//					
	//					
	//					/*					case when inv_type = 'SHOP'
	//	                  then case when holdy_yn = 'N' and TO_DATE(TO_CHAR(SYSDATE,'YYYYMMDD')||shop_end_hhmm,'YYYYMMDDHH24MI') - 1/24 > SYSDATE
	//	                            then to_char(SYSDATE,'YYYYMMDD')
	//	                            else to_char(SYSDATE + 1,'YYYYMMDD')
	//	                        end
	//	                  else case when TO_DATE(TO_CHAR(SYSDATE,'YYYYMMDD')||'0900','YYYYMMDDHH24MI') > SYSDATE
	//	                            then to_char(SYSDATE + 1,'YYYYMMDD')
	//	                            else to_char(SYSDATE + 2,'YYYYMMDD')
	//	                       end
	//                 end*/
	//
	//					
	//					
	//					//셋트일경우 서버에서 재고위치를 배정하기때문에 픽업가능일자를 다시 구한다.
	//					String holdyYn = godDetail.getHoldyYn();
	//					String shopEndHhmm = godDetail.getShopEndHhmm(); //1800 이런 데이터가 온다
	//					Calendar nowCal = Calendar.getInstance();  // 현재 날짜/시간 등의 각종 정보 얻기
	//					//nowCal.add(nowCal.MONTH, 1);//Calendar의 월은 0부터 시작해서 1달을 더해준다. //12월은 11이고 +1을 하면 12가 아닌 1로 되어 주석처리
	//					Calendar chkCal = Calendar.getInstance();  // 현재 날짜/시간 등의 각종 정보 얻기
	//					//chkCal.add(nowCal.MONTH, 1);//Calendar의 월은 0부터 시작해서 1달을 더해준다.//12월은 11이고 +1을 하면 12가 아닌 1로 되어 주석처리
	//					 
	//					
	//					
	//					String sysdate = ""+nowCal.get(Calendar.YEAR)
	//							+nowCal.get(Calendar.MONTH)
	//							+nowCal.get(Calendar.DAY_OF_MONTH)
	//							+nowCal.get(Calendar.HOUR_OF_DAY)
	//							+nowCal.get(Calendar.MINUTE); 
	//					
	//					if(isShopInv){//센터 배정이면 픽업일이 당일이 아니다
	//											
	//						//매장에 배정이면 휴일아니고 매장영업종료시간 한시간 전이면 당일 그외에는 하루 + 된다
	//						
	//						if(null != shopEndHhmm && shopEndHhmm.length()==4 && null != holdyYn){
	//						
	//							//System.out.println(shopEndHhmm.substring(0,2)+"/"+shopEndHhmm.substring(2,4));
	//							
	//							chkCal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(shopEndHhmm.substring(0,2)));
	//							chkCal.set(Calendar.MINUTE, Integer.parseInt(shopEndHhmm.substring(2,4)));
	//							chkCal.add(Calendar.HOUR_OF_DAY, -1);
	//							if("N".equalsIgnoreCase(holdyYn) //매장휴일이 아니고
	//									&& chkCal.compareTo(nowCal)==1 //현재시간이 매장영업종료한시간전 보다 전이면
	//									){
	//								//당일
	//								String pkupDayStr = convertPkupDayFormat(nowCal); 
	//								
	//								pkupDay = Integer.parseInt(pkupDayStr);
	//								cartResult.setPkupDay(pkupDayStr);
	//							}else{
	//								//하루 + 
	//								nowCal.add(Calendar.DAY_OF_MONTH, 1);
	//								String pkupDayStr = convertPkupDayFormat(nowCal);
	//								pkupDay = Integer.parseInt(pkupDayStr);
	//								cartResult.setPkupDay(pkupDayStr);
	//							}							
	//						}
	//
	//					}
	//
	//
	//				}else{
	//					// 결품
	//					isInvYn = false;
	//
	//					for(CartGodResult godSub : cartGodResultList){
	//						//판매중 상품 체크
	//						if(StringService.equalsIgnoreCase(BskEnum.BskGodSaleStat.SALE_PROGRS.toString(), god.getGodSaleSectCd())
	//								|| StringService.equalsIgnoreCase(BskEnum.BskGodSaleStat.SALE_PROGRS_PKUP.toString(), god.getGodSaleSectCd())){
	//							 if(!StringService.equalsIgnoreCase(BskEnum.BskGodSaleStat.SALE_PROGRS.toString(), godSub.getGod().getGodSaleSectCd())
	//									 && !StringService.equalsIgnoreCase(BskEnum.BskGodSaleStat.SALE_PROGRS_PKUP.toString(), godSub.getGod().getGodSaleSectCd())){
	//								 god.setGodSaleSectCd(godSub.getGod().getGodSaleSectCd());
	//							 }else if(!StringService.equalsIgnoreCase(BskEnum.BskGodItmSaleStat.SALE_PROGRS.toString(),godSub.getGodItm().getItmStatCd())
	//									 && !StringService.equalsIgnoreCase(BskEnum.BskGodItmSaleStat.SALE_PROGRS_PKUP.toString(),godSub.getGodItm().getItmStatCd())){
	//								 god.setGodSaleSectCd(godSub.getGodItm().getItmStatCd());
	//							 }
	//						}
	//					}
	//
	//				}
	
	
				}else{
					//일반배송 세트 재고체크
					for(CartGodResult godSub : cartGodResultList){
						if(bskGod.getGodTurn() == godSub.getParentGodTurn() && StringService.isNotEmpty(godSub.getPckageGodTpCd())){
							//추가 구성 상품이 아닌경우
							if(!StringService.equalsIgnoreCase(BskEnum.PckageGodTp.ADIT_CPST_GOD.toString(), godSub.getPckageGodTpCd())){
								cartResult.getBskGodList().add(godSub.getBskGod());
								cartResult.getBskCpstGodCnnc().add(godSub.getGod());
								cartResult.getBskCpstGodItmCnnc().add(godSub.getGodItm());
								// 판매중인 상품인 경우
								if(StringService.equalsIgnoreCase(BskEnum.YES.toString(), godDetail.getSellYn())){
									if(godSub.getBskGod().getItmQty() <= godSub.getRealInvQty()){
										// 없음
									}else{
							    		isInvYn = false;
							    	}
	
								    
								//판매중인 상품이 아닌경우
								}else{
									// 결품
									isInvYn = false;
									//판매중 상품 체크
									if(StringService.equalsIgnoreCase(BskEnum.BskGodSaleStat.SALE_PROGRS.toString(), god.getGodSaleSectCd())){
										 if(!StringService.equalsIgnoreCase(BskEnum.BskGodSaleStat.SALE_PROGRS.toString(), godSub.getGod().getGodSaleSectCd())){
											 god.setGodSaleSectCd(godSub.getGod().getGodSaleSectCd());
										 }else if(!StringService.equalsIgnoreCase(BskEnum.BskGodItmSaleStat.SALE_PROGRS.toString(),godSub.getGodItm().getItmStatCd())){
											 god.setGodSaleSectCd(godSub.getGodItm().getItmStatCd());
										 }
									}
								}
							}else{
								//추가 구성상품 카운트
								aditCnt++;
							}
						}
					}
				}
	
	
	
	
				cartResult.setAditCnt(aditCnt);
	
				if(isInvYn){
					cartResult.setInvYn(BskEnum.YES.toString());
				}else{
					cartResult.setInvYn(BskEnum.NO.toString());
				}
			}else{//패키지형 아닌 단품인 경우
				
				if(StringService.equalsIgnoreCase(BskEnum.YES.toString(), godDetail.getSellYn())){
					if(StringService.equalsIgnoreCase(BskEnum.DlvSect.PKUP_DLV.toString(), bskGod.getDlvSectCd())){//픽업단품 재고체크
						if(!StringService.equalsIgnoreCase(BskEnum.PckageGodTp.ADIT_CPST_GOD.toString(), godDetail.getPckageGodTpCd())){
							int qtyChkInt = godDetail.getShopInvQty();
							
	
							if(bskGod.getItmQty() > qtyChkInt){
								cartResult.setInvYn(BskEnum.NO.toString());
							}else{
								cartResult.setInvYn(BskEnum.YES.toString());
								cartResult.setPkupDay(godDetail.getPkupDay());
							}
						}else{
							//추가구성품인 경우 세트제고체크하여 픽업날짜 이미 셋팅되어있으므로 동일하게 셋팅
							cartResult.setInvYn(BskEnum.YES.toString());
							int pkupDay = 0;
							for(CartGodResult godSub : cartGodResultList){
								if(godDetail.getParentGodTurn() == godSub.getParentGodTurn() && StringService.isNotEmpty(godSub.getPckageGodTpCd())){
									//if(bskGod.getGodTurn()!=godSub.getBskGod().getGodTurn()){
										//cartResult.setPkupDay(godSub.getPkupDay());
	
										if (StringService.isNotEmpty(godSub.getPkupDay()) && pkupDay < Integer.parseInt(godSub.getPkupDay())) {
											pkupDay = Integer.parseInt(godSub.getPkupDay());
											cartResult.setPkupDay(godSub.getPkupDay());
											if(null!=godSub.getInvYn()){//매장재고면 null 로 셋팅되어 건너뛴다
												cartResult.setInvYn(godSub.getInvYn());
											}
	
										}
									//}
	
	
								}
							}
						}
	
	
				    }else{ //일반배송 단품 재고체크 
				    	if(bskGod.getItmQty() <= godDetail.getRealInvQty()){
							// 없음
				    		cartResult.setInvYn(BskEnum.YES.toString());
						}else if(bskGod.getItmQty() <= godDetail.getEtcInvQty()){
							cartResult.setShopDlvItm("Y");
							cartResult.setInvYn(BskEnum.YES.toString());
							// 매장 수령
						}else{
							cartResult.setInvYn(BskEnum.NO.toString());
				    	}
				    }
	//				if(!"KOR".equalsIgnoreCase(lang)){//글로벌 에서 픽업판매중 상태이면 품절로 처리한다.
	//					if(StringService.equalsIgnoreCase(BskEnum.BskGodItmSaleStat.SALE_PROGRS_PKUP.toString(),god.getGodSaleSectCd())){
	//						cartResult.setInvYn(BskEnum.NO.toString());
	//					}
	//				}
	
				}else{
					cartResult.setInvYn(BskEnum.NO.toString());
					if(StringService.equalsIgnoreCase(BskEnum.BskGodItmSaleStat.SALE_PROGRS.toString(),god.getGodSaleSectCd())
							||StringService.equalsIgnoreCase(BskEnum.BskGodItmSaleStat.SALE_PROGRS_PKUP.toString(),god.getGodSaleSectCd())){
						god.setGodSaleSectCd(godDetail.getGodItm().getItmStatCd());
	//					if(!"KOR".equalsIgnoreCase(lang)){//글로벌 에서 픽업판매중 상태이면 품절로 처리한다.
	//						if(StringService.equalsIgnoreCase(BskEnum.BskGodItmSaleStat.SALE_PROGRS_PKUP.toString(),godDetail.getGodItm().getItmStatCd())){
	//							//cartResult.setInvYn(BskEnum.NO.toString());
	//							god.setGodSaleSectCd(BskEnum.BskGodItmSaleStat.SLDOUT.toString());
	//							//godDetail.setSellYn("Y");
	//						}
	//					}
					}
				}
			}
	
	
			//#34425 로 추가 2017-01-13
	//		if(!"KOR".equalsIgnoreCase(lang)){
	//			if(!StringService.equalsIgnoreCase("DSP_APRV",godDetail.getOvseaDspStatCd())//해외전시상태코드
	//					||!StringService.equalsIgnoreCase("TRSLT_COMPT",godDetail.getTrsltStatCd())//상품번역상태코드
	//					||!StringService.equalsIgnoreCase(BskEnum.YES.toString(),godDetail.getOvseaDlvPsbYn())//해외배송가능여부
	//					){
	//				godDetail.setSellYn("N");//장바구니 리스트에서 sale closed 로 노출 위해 셋팅
	//			}
	//		}
	
			if(StringService.equalsIgnoreCase(BskEnum.DlvSect.PKUP_DLV.toString(), bskGod.getDlvSectCd())){
				if(BskEnum.NO.toString().equalsIgnoreCase(godDetail.getPkupShopYn())){
					cartResult.setInvYn(BskEnum.NO.toString());
				}
			}
			
			cartResult.setSellYn(godDetail.getSellYn());
			cartResult.setRealInvQty(godDetail.getRealInvQty());
			cartResult.setHoffInvQty(godDetail.getHoffInvQty());
			cartResult.setEtcInvQty(godDetail.getEtcInvQty());
		}else{
			cartResult.setInvYn(BskEnum.YES.toString());
			cartResult.setSellYn(BskEnum.YES.toString());
			cartResult.setRealInvQty(99999999);
			cartResult.setHoffInvQty(0);
			cartResult.setEtcInvQty(0);
		}

		cartResult.setBskGod(bskGod);
		cartResult.setGod(god);
		cartResult.setGodItm(godDetail.getGodItm());
		cartResult.setBrndNm(godDetail.getBrndNm());
		cartResult.setParentGodTurn(godDetail.getParentGodTurn());
		cartResult.setGrpSeq(godDetail.getGrpSeq());
		cartResult.setSubGrpSeq(godDetail.getSubGrpSeq());
		cartResult.setSubGrpCnt(godDetail.getSubGrpCnt());
		cartResult.setDlvGrpRnum(godDetail.getDlvGrpRnum());
		cartResult.setDlvGrpCnt(godDetail.getDlvGrpCnt());
		cartResult.setPckageGodTpCd(godDetail.getPckageGodTpCd());
		cartResult.setShopNm(godDetail.getShopNm());
		cartResult.setRowSpanCount(godDetail.getRowSpanCount());
		
		
		
		cartResult.setHoldyYn(godDetail.getHoldyYn());
		cartResult.setShopAddr(godDetail.getShopAddr());
		cartResult.setShopEndHhmm(godDetail.getShopEndHhmm());
		cartResult.setShopBegHhmm(godDetail.getShopBegHhmm());
		cartResult.setShopId(godDetail.getShopId());
		cartResult.setShopTelAreaNo(godDetail.getShopTelAreaNo());
		cartResult.setShopTelTlofNo(godDetail.getShopTelTlofNo());
		cartResult.setShopTelTlofWthnNo(godDetail.getShopTelTlofWthnNo());
		cartResult.setPrice(godDetail.getPrice() == null ? god.getCsmrPrc() : godDetail.getPrice());
		cartResult.setPrcType(StringService.isEmpty(godDetail.getPrcType()) ? "GNRL" : godDetail.getPrcType());
		
		cartResult.setCrntDay(godDetail.getCrntDay());
		cartResult.setImgUrl(godDetail.getImgUrl());
		cartResult.setWishYn(godDetail.getWishYn());
		if(godDetail.getOrdGodExtend() != null) {
			cartResult.setOrdGodExtend(godDetail.getOrdGodExtend());
		}

	}

	private String convertPkupDayFormat(Calendar cal) throws Exception {
		String strYear = ""+cal.get(Calendar.YEAR);
		String strMonth = ""+(cal.get(Calendar.MONTH)+1); 
		String strDay = ""+cal.get(Calendar.DAY_OF_MONTH);
		
		if(strMonth.length()==1){//월이 한자리면 앞에 0을 붙인다
			strMonth = "0"+strMonth;
		}
		if(strDay.length()==1){//월이 한자리면 앞에 0을 붙인다
			strDay = "0"+strDay;
		}
		
		return strYear+strMonth+strDay;
	}
	
	public List<GodItmQtyResult> selectGodItmQty(GodItmQtySearchDTO godItmQtySearchDTO) {
		return cartSelectRepository.selectGodItmQty(godItmQtySearchDTO);
	}

	public int selectGodInvPkupCount(BskGod god) {
		return cartSelectRepository.selectShopGodInvPkupCount(god);
	}

	public List<BskGodExtend> selectBskGodItmStatList(BskGod bskGod){
		return cartSelectRepository.selectBskGodItmStatList(bskGod);
	}

	/**
	 * 퀵배송 재고매트릭스
	 * @param godItmQtySearchDTO
	 * @return
	 */
	public List<GodItmQtyResult> selectGodItmQtyForQdlv(GodItmQtySearchDTO godItmQtySearchDTO) {
		return cartSelectRepository.selectGodItmQtyForQdlv(godItmQtySearchDTO);
	}
	
	public int selectShopGodInvQdlvCount(BskGod god) {
		return cartSelectRepository.selectShopGodInvQdlvCount(god);
	}

	public CartGodOptionResult selectGodOptionListForQdlv(BskGod bskGod) {
		return cartSelectRepository.selectGodOptionListForQdlv(bskGod);
	}


	public ComQdlvGudTxtExtend selectComQdlvGudTxt(ComQdlvGudTxtExtend comQdlvGudTxtExtend) {
		return cartSelectRepository.selectComQdlvGudTxt(comQdlvGudTxtExtend);
	}
}
