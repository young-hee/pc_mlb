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
package com.plgrim.ncp.biz.cart.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;
import com.plgrim.ncp.base.abstracts.AbstractService;
import com.plgrim.ncp.base.entities.datasource1.bsk.Bsk;
import com.plgrim.ncp.base.entities.datasource1.bsk.BskCpstGodCnnc;
import com.plgrim.ncp.base.entities.datasource1.bsk.BskGod;
import com.plgrim.ncp.base.entities.datasource1.bsk.BskGodExtend;
import com.plgrim.ncp.base.entities.datasource1.bsk.BskWishlst;
import com.plgrim.ncp.base.entities.datasource1.bsk.BskWishlstGod;
import com.plgrim.ncp.base.entities.datasource1.god.God;
import com.plgrim.ncp.base.entities.datasource1.god.GodReWhsgNtcn;
import com.plgrim.ncp.base.entities.datasource1.mbr.Mbr;
import com.plgrim.ncp.base.enums.BskEnum;
import com.plgrim.ncp.base.repository.bsk.BskWishlstRepository;
import com.plgrim.ncp.base.repository.god.GodRepository;
import com.plgrim.ncp.biz.cart.data.CartDTO;
import com.plgrim.ncp.biz.cart.data.CartGodDTO;
import com.plgrim.ncp.biz.cart.data.CartGodHistDTO;
import com.plgrim.ncp.biz.cart.data.CartGodOptionDTO;
import com.plgrim.ncp.biz.cart.data.CartSearchDTO;
import com.plgrim.ncp.biz.cart.data.GodReWhsgNtcnDTO;
import com.plgrim.ncp.biz.cart.data.WishListSearchDTO;
import com.plgrim.ncp.biz.cart.repository.CartCommandRepository;
import com.plgrim.ncp.biz.cart.repository.CartSelectRepository;
import com.plgrim.ncp.biz.cart.result.CartCpstGodResult;
import com.plgrim.ncp.biz.member.data.SecurityUserDetail;
import com.plgrim.ncp.biz.order.exception.OrderFailException;
import com.plgrim.ncp.framework.commons.CollectionService;
import com.plgrim.ncp.framework.commons.ContextService;
import com.plgrim.ncp.framework.commons.StringService;
import com.plgrim.ncp.framework.enums.DatabaseType;

import lombok.extern.slf4j.Slf4j;

/**
 * [장바구니 기능]
 * 
 * <p>
 * 
 * <ul>
 * <li>[기능1]
 * <li>[기능2]
 * </ul>
 * .
 *
 * @author mc009.park
 * @since 2015. 4. 2
 */
@Slf4j
@Service
public class CartCommandService extends AbstractService {

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	/** The cart command repository. */
	@Autowired
	CartCommandRepository cartCommandRepository;

	/** The cart select repository. */
	@Autowired
	CartSelectRepository cartSelectRepository;

	/** The bsk wishlst repository. */
	@Autowired
	BskWishlstRepository bskWishlstRepository;

	@Autowired
	GodRepository godRepository;
	
	@Autowired
	CartSelectService cartSelectService;

	/** The sql session1. */
	@Autowired
	@Qualifier("sessionTemplate1")
	SqlSession sqlSession1;
	/*
	 * ---------------------------------------------------------------------
	 * Constructors.
	 * ---------------------------------------------------------------------
	 */

	/*
	 * --------------------------------------------------------------------- public
	 * & interface method.
	 * ---------------------------------------------------------------------
	 */

	/**
	 * [장바구니 마스터 등록].
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param bsk
	 *            [설명]
	 * @throws Exception
	 *             the exception
	 * @since 2015. 4. 2
	 */
	public String insertCartMst(Bsk bsk) throws Exception {
		String bskNo = getIdGenService().generateDBNumber(sqlSession1, "SQ_BSK", "BK", DatabaseType.ORACLE);
		bsk.setBskNo(bskNo);
		cartCommandRepository.insertCartMst(bsk);
		return bskNo;
	}
	/*
	 * --------------------------------------------------------------------- private
	 * method. ---------------------------------------------------------------------
	 */

	/**
	 * [장바구니 상품등록].
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명]. 1.장바구니가 없을 경우 장바구니 생성 2.장바구니 상품등록 3.패키지형 상품의 경우 장바구니 구성 상품 연결 등록
	 * 
	 * @param cartDTO
	 *            [설명]
	 * @throws Exception
	 *             the exception
	 * @since 2015. 4. 2
	 */
	public BskGodExtend insertCart(CartDTO cartDTO) throws Exception {

		String bskNo = cartDTO.getBsk().getBskNo();
		BskGodExtend god = cartDTO.getGod();
		god.setBskNo(bskNo);
		
		//매장 픽업 매장 관련
		if("PKUP_DLV".equals(god.getDlvSectCd())) {
			log.debug("PKUP_DLV pkupShopSn : " + god.getPkupShopSn());
			god.setPkupShopSn(god.getPkupShopSn());
		}
		Integer newGodTurn = null;
		

		// 추천업체코드 - 한글처리 추가
		if (StringService.isNotEmpty(god.getRecomendComCd())) {
			// String recomendComCd = new
			// String(god.getRecomendComCd().getBytes("ISO-8859-1"),"utf-8");
			String recomendComCd = god.getRecomendComCd();
			if (recomendComCd.contains("$tp")) {
				recomendComCd = recomendComCd.substring(0, recomendComCd.lastIndexOf("$tp"));
			}
			god.setRecomendComCd(recomendComCd);
		}

		Map<String, Object> conditions = Maps.newHashMap();
		conditions.put("bsk_no", bskNo);

		String regtrId = cartDTO.getBsk().getRegtrId();
		String udterId = cartDTO.getBsk().getUdterId();

		// 패키지형 상품
		if (StringService.equalsIgnoreCase(BskEnum.YES.toString(), god.getPckageGodYn())) {

			if (!StringService.equalsIgnoreCase(BskEnum.BskTp.BSK.toString(), cartDTO.getBsk().getBskTpCd())) { // 즉시, 가격변동

				int masterGodTurn = this.getGodTurn(conditions);
				god.setGodTurn(masterGodTurn);

				// 장바구니 상품 등록(단품형 상품,패키지형 상품의 마스터)
				god.setRegtrId(regtrId);
				god.setUdterId(udterId);
				cartCommandRepository.insertCartGod(god);
				BskGod cpstGod = new BskGod();
				BskCpstGodCnnc bskCpstGodCnnc = new BskCpstGodCnnc();
				int i = 1;
				for (CartGodDTO godDTO : cartDTO.getCpstGodList()) {

					int godTurn = this.getGodTurn(conditions);

					cpstGod.setBskNo(bskNo); // 장바구니 번호
					cpstGod.setGodTurn(godTurn); // 상품순번
					cpstGod.setGodNo(godDTO.getGodNo());
					cpstGod.setItmNo(godDTO.getItmNo());

					if (StringService.equalsIgnoreCase(BskEnum.PckageGodTp.ADIT_CPST_GOD.toString(),
							godDTO.getPckageGodTpCd())) {
						cpstGod.setItmQty(godDTO.getItmQty() * godDTO.getCpstGodQty());
					} else {
						cpstGod.setItmQty(cartDTO.getGod().getItmQty() * godDTO.getCpstGodQty());
					}

					cpstGod.setPckageGodYn(god.getPckageGodYn());
					cpstGod.setDlvSectCd(god.getDlvSectCd());
					cpstGod.setPkupShopSn(god.getPkupShopSn());
					cpstGod.setRecomendComCd(god.getRecomendComCd()); // 추천업체코드
					cpstGod.setRegtrId(regtrId);
					cpstGod.setUdterId(udterId);

					// 구성 상품 연결 정보
					bskCpstGodCnnc.setBskNo(bskNo); // 장바구니 번호
					bskCpstGodCnnc.setGodTurn(masterGodTurn); // 상품순번
					bskCpstGodCnnc.setCpstGodTurn(godTurn); // 구성 상품 순번
					bskCpstGodCnnc.setPckageGodTpCd(godDTO.getPckageGodTpCd()); // 패키지형 상품 유형 코드
					if (godDTO.getCpstGodQty() == null) {
						bskCpstGodCnnc.setCpstGodQty(new Long(1)); // 구성 상품 수량
					} else {
						bskCpstGodCnnc.setCpstGodQty(godDTO.getCpstGodQty()); // 구성 상품 수량
					}

					bskCpstGodCnnc.setRegtrId(regtrId);
					bskCpstGodCnnc.setUdterId(udterId);

					if (godDTO.getSortSeq() < 1) {
						bskCpstGodCnnc.setSortSeq(i); // 정렬순서
					} else {
						bskCpstGodCnnc.setSortSeq(godDTO.getSortSeq()); // 정렬순서
					}

					// 장바구니 상품 등록
					cartCommandRepository.insertCartGod(cpstGod);
					// 장바구니 구성 상품 연결 등록
					cartCommandRepository.insertCartCpstGod(bskCpstGodCnnc);
					i++;
				}
			} else {
				cartDTO.setCpstCnt(cartDTO.getCpstGodList().size());

				List<BskGodExtend> bskgodList = cartSelectRepository.selectBskPckageInfo(cartDTO);

				if (CollectionService.isEmpty(bskgodList)) {
					int masterGodTurn = this.getGodTurn(conditions);
					
					newGodTurn = masterGodTurn;
					
					god.setGodTurn(masterGodTurn);
					god.setRegtrId(regtrId);
					god.setUdterId(udterId);
					// 장바구니 상품 등록(단품형 상품,패키지형 상품의 마스터)
					cartCommandRepository.insertCartGod(god);

					BskGod cpstGod = new BskGod();
					BskCpstGodCnnc bskCpstGodCnnc = new BskCpstGodCnnc();

					int i = 1;
					for (CartGodDTO godDTO : cartDTO.getCpstGodList()) {

						int godTurn = this.getGodTurn(conditions);

						cpstGod.setBskNo(bskNo); // 장바구니 번호
						cpstGod.setGodTurn(godTurn); // 상품순번
						cpstGod.setGodNo(godDTO.getGodNo());
						cpstGod.setItmNo(godDTO.getItmNo());
						cpstGod.setRegtrId(regtrId);
						cpstGod.setUdterId(udterId);

						if (StringService.equalsIgnoreCase(BskEnum.PckageGodTp.ADIT_CPST_GOD.toString(),
								godDTO.getPckageGodTpCd())) {
							cpstGod.setItmQty(godDTO.getItmQty() * godDTO.getCpstGodQty());
						} else {
							cpstGod.setItmQty(cartDTO.getGod().getItmQty() * godDTO.getCpstGodQty());
						}

						cpstGod.setPckageGodYn(god.getPckageGodYn());
						cpstGod.setDlvSectCd(god.getDlvSectCd());
						cpstGod.setPkupShopSn(god.getPkupShopSn());
						cpstGod.setRecomendComCd(god.getRecomendComCd()); // 추천업체코드

						// 구성 상품 연결 정보
						bskCpstGodCnnc.setBskNo(bskNo); // 장바구니 번호
						bskCpstGodCnnc.setGodTurn(masterGodTurn); // 상품순번
						bskCpstGodCnnc.setCpstGodTurn(godTurn); // 구성 상품 순번
						bskCpstGodCnnc.setPckageGodTpCd(godDTO.getPckageGodTpCd()); // 패키지형 상품 유형 코드
						if (godDTO.getCpstGodQty() == null) {
							bskCpstGodCnnc.setCpstGodQty(new Long(1)); // 구성 상품 수량
						} else {
							bskCpstGodCnnc.setCpstGodQty(godDTO.getCpstGodQty()); // 구성 상품 수량
						}

						bskCpstGodCnnc.setRegtrId(regtrId);
						bskCpstGodCnnc.setUdterId(udterId);

						if (godDTO.getSortSeq() < 1) {
							bskCpstGodCnnc.setSortSeq(i); // 정렬순서
						} else {
							bskCpstGodCnnc.setSortSeq(godDTO.getSortSeq()); // 정렬순서
						}

						// 장바구니 상품 등록
						cartCommandRepository.insertCartGod(cpstGod);
						// 장바구니 구성 상품 연결 등록
						cartCommandRepository.insertCartCpstGod(bskCpstGodCnnc);

						i++;
					}

					// 장바구니 데이터 적재 by cannon (2016.06.16) : 등록 후 수행
					insertCartBskGodHist("INPUT", god);

				} else {
					
					
					BskGod cpstGod = new BskGod();
					int masterGodTurn = bskgodList.get(0).getParentGodTurn();
					
					if(cartDTO.getSourceGodTurn()!=null&&!"".equals(cartDTO.getSourceGodTurn())
							&&masterGodTurn==Integer.valueOf(cartDTO.getSourceGodTurn()).intValue()){
						cartDTO.getGod().setGodTurn(new Integer(cartDTO.getSourceGodTurn()));
						
						List<CartCpstGodResult> resultList = cartSelectService.selectBskCpstGodCnnc(cartDTO.getGod());

						List<CartGodDTO> cpstGodList = new ArrayList<CartGodDTO>();

						for(CartCpstGodResult list : resultList){
							CartGodDTO innerGod = new CartGodDTO();
							innerGod.setGodTurn(list.getBskCpstGodCnnc().getGodTurn());
							innerGod.setCpstGodTurn(list.getBskCpstGodCnnc().getCpstGodTurn());
							innerGod.setGodNo(list.getGodNo());
							innerGod.setItmNo(list.getItmNo());
							innerGod.setPckageGodTpCd(list.getBskCpstGodCnnc().getPckageGodTpCd());
							innerGod.setCpstGodQty(list.getBskCpstGodCnnc().getCpstGodQty());
							innerGod.setSortSeq(list.getBskCpstGodCnnc().getSortSeq());
							cpstGodList.add(innerGod);
						}

						cartDTO.setCpstGodList(cpstGodList);
						
						this.updateGoodsQty(cartDTO, this.getMemberGuestId());
						//throw new OrderFailException("cart.js.txt.same.item",null);
						return god;
					}
					
					newGodTurn = masterGodTurn;
					
					cpstGod.setBskNo(bskNo);
					cpstGod.setGodTurn(masterGodTurn);
					cpstGod.setItmQty(god.getItmQty());
					cpstGod.setUdterId(udterId);
					// 마스터 상품 업데이트
					cartCommandRepository.updateItmQtyIncrease(cpstGod);
					for (CartGodDTO godDTO : cartDTO.getCpstGodList()) {
						for (BskGodExtend bskGodInfo : bskgodList) {

							if (StringService.equalsIgnoreCase(godDTO.getGodNo(), bskGodInfo.getGodNo())
									&& StringService.equalsIgnoreCase(godDTO.getItmNo(), bskGodInfo.getItmNo())
									&& StringService.equalsIgnoreCase(godDTO.getPckageGodTpCd(),
											bskGodInfo.getPckageGodTpCd())
									&& StringService.equalsIgnoreCase(masterGodTurn + "",
											bskGodInfo.getParentGodTurn() + "")) {
								cpstGod.setGodTurn(bskGodInfo.getGodTurn());
								cpstGod.setItmQty(cartDTO.getGod().getItmQty() * godDTO.getCpstGodQty());

								if (StringService.equalsIgnoreCase(BskEnum.PckageGodTp.ADIT_CPST_GOD.toString(),
										godDTO.getPckageGodTpCd())) {
									cpstGod.setItmQty(godDTO.getItmQty() * godDTO.getCpstGodQty());
								}

								cartCommandRepository.updateItmQtyIncrease(cpstGod);
							}
						}
					}
				}
			}
		} else {
			newGodTurn = cartSelectRepository.selectEqualCartGoodByNotPackageGodTurn(god);
			if(cartDTO.getSourceGodTurn()!=null&&!"".equals(cartDTO.getSourceGodTurn())
					&&newGodTurn!=null
					&&newGodTurn==Integer.valueOf(cartDTO.getSourceGodTurn()).intValue()){
				cartDTO.getGod().setGodTurn(new Integer(cartDTO.getSourceGodTurn()));
				this.updateGoodsQty(cartDTO, this.getMemberGuestId());
				//throw new OrderFailException("cart.js.txt.same.item",null);
				return god;
			}
			
			god.setRegtrId(regtrId);
			god.setUdterId(udterId);
			if (StringService.equalsIgnoreCase(BskEnum.BskTp.DIRT.toString(), cartDTO.getBsk().getBskTpCd())) {
				int masterGodTurn = this.getGodTurn(conditions);
				god.setGodTurn(masterGodTurn);
				cartCommandRepository.insertCartGod(god);
			} else {
				
				
				// 장바구니 데이터 적재 by cannon (2016.06.16)
				int itmCnt = 0;
				if (cartDTO.getBsk().getMbrNo() != null) {
					itmCnt = cartSelectRepository.selectCartGodItmCount(god);
				}
				god.setGodTurn(this.getGodTurn(conditions));

				//
				cartCommandRepository.insertMergeCartGod(god);

				// 장바구니 데이터 적재 by cannon (2016.06.16) : 등록 후 수행
				if (itmCnt == 0) {
					insertCartBskGodHist("INPUT", god);
				}
				
				

			}
		}
		log.debug("item change godTurn {} {}",newGodTurn,cartDTO.getSourceGodTurn());
		if((newGodTurn==null&&StringUtils.isNotEmpty(cartDTO.getSourceGodTurn()))
				||(newGodTurn!=null&&StringUtils.isNotEmpty(cartDTO.getSourceGodTurn())&&!newGodTurn.toString().equals(cartDTO.getSourceGodTurn()) )
				){
			log.debug("######################################################################################");
			log.debug("###################################삭제중복변경#########################################");
			log.debug("######################################################################################");
			god.setNewGodTurn(newGodTurn);
			

			CartSearchDTO delDTO  = new CartSearchDTO();
			List<BskGodExtend> bskgodExtendList = new ArrayList<BskGodExtend>(); 
			BskGodExtend delGod = cartDTO.getGod();
			delGod.setBskNo(cartDTO.getBsk().getBskNo());
			delGod.setGodTurn(new Integer(cartDTO.getSourceGodTurn()));
			delGod.setPckageGodYn(god.getPckageGodYn());
			if("Y".equals(god.getPckageGodYn())){
				delGod.setParentGodTurn(delGod.getGodTurn());
			}
			bskgodExtendList.add(delGod);
			delDTO.setBskgodExtendList(bskgodExtendList);
			
			if("Y".equals(god.getPckageGodYn())){
				//구성상품 있을시 변경
				BskCpstGodCnnc uptBskCncc = new BskCpstGodCnnc();
				uptBskCncc.setBskNo(bskNo);
				uptBskCncc.setCpstGodTurn(newGodTurn);
				uptBskCncc.setGodTurn(new Integer(cartDTO.getSourceGodTurn()));
				uptBskCncc.setPckageGodTpCd("ADIT_CPST_GOD");
				uptBskCncc.setUdterId(udterId);
				
				cartCommandRepository.updateCartCpstGod(uptBskCncc);
			}
			
			this.deleteCartGoods(delDTO);

		}
		
		return god;
	}

	/**
	 * [장바구니 상품 수량 변경].
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 * 
	 * @param bskGod
	 *            [장바구니 상품 Entity]
	 * @param superGodTurn
	 *            [패키지 마스터 상품의 상품순번]
	 * @param pckageGodTpCd
	 *            [패키지형 상품 유형코드]
	 * @throws Exception
	 *             the exception
	 * @since 2015. 4. 2
	 */
	public void updateGoodsQty(CartDTO cartDTO, String udterId) throws Exception {

		BskGod god = cartDTO.getGod();

		List<BskGod> bskGodList = new ArrayList<BskGod>();
		bskGodList.add(god);

		if (StringService.equalsIgnoreCase(BskEnum.YES.toString(), god.getPckageGodYn())) {

			for (CartGodDTO bskCpstGodCnnc : cartDTO.getCpstGodList()) {

				BskGod bskGodTmp = new BskGod();
				bskGodTmp.setBskNo(god.getBskNo());
				bskGodTmp.setGodTurn(bskCpstGodCnnc.getCpstGodTurn());
				bskGodTmp.setItmQty(god.getItmQty() * bskCpstGodCnnc.getCpstGodQty());

				bskGodList.add(bskGodTmp);
			}
		}

		for (BskGod bskGod : bskGodList) {
			bskGod.setUdterId(udterId);
			cartCommandRepository.updateBskGodQty(bskGod);
		}

	}

	/**
	 * [장바구니 상품삭제].
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 * 
	 * @param bskGodList
	 *            [장바구니 Entity]
	 * @throws Exception
	 *             the exception
	 * @since 2015. 4. 2
	 */
	public void deleteCartGoods(CartSearchDTO cartSearchDTO) throws Exception {

		BskGod god = new BskGod();
		for (BskGodExtend bskGod : cartSearchDTO.getBskgodExtendList()) {
			// 패키지형 상품의 경우 구성 상품 연결 삭제
			if (BskEnum.YES.toString().equals(bskGod.getPckageGodYn())) {
				// 장바구니 데이터 적재 by cannon (2016.06.16) : 삭제 전 수행
				insertCartBskGodHist("DELETE", bskGod);

				//
				god.setBskNo(bskGod.getBskNo());
				god.setGodTurn(bskGod.getGodTurn());
				// 패키지 전체 삭제
				if (bskGod.getGodTurn() == bskGod.getParentGodTurn()) {
					List<BskGod> bskGodList = cartSelectRepository.selectBskGodListByMastInfo(god);
					cartCommandRepository.deleteBskCpstByGodTurn(god);

					for (BskGod pckageGod : bskGodList) {
						cartCommandRepository.deleteBskGod(pckageGod);
					}
				} else { // 추가구성 삭제
					cartCommandRepository.deleteBskCpst(bskGod);
					cartCommandRepository.deleteBskGod(bskGod);
				}
			} else {
				// 장바구니 데이터 적재 by cannon (2016.06.16) : 삭제 전 수행
				insertCartBskGodHist("DELETE", bskGod);
				//
				cartCommandRepository.deleteBskGod(bskGod);
			}
		}
	}

	/**
	 * [주문상품 장바구니 상품삭제].
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 * 
	 * @param bskGodList
	 *            [장바구니 Entity]
	 * @throws Exception
	 *             the exception
	 * @since 2015. 4. 2
	 */
	public void deleteCartGoodsFromOrder(CartSearchDTO cartSearchDTO) throws Exception {

		BskGod god = new BskGod();
		for (BskGod bskGod : cartSearchDTO.getBskGodList()) {
			// 장바구니 데이터 적재 by cannon (2016.06.16) : 삭제 전 등록
			if (cartSearchDTO.getBsk() != null && "BSK".equals(cartSearchDTO.getBsk().getBskTpCd())) {
				this.insertCartBskGodHist("ORD", bskGod);
			}

			// if (BskEnum.YES.toString().equals(bskGod.getPckageGodYn())){
			god.setBskNo(bskGod.getBskNo());
			god.setGodTurn(bskGod.getGodTurn());
			List<BskGod> bskGodList = cartSelectRepository.selectBskGodListByMastInfo(god);
			// 마스터 정보로 구성상품 연결 삭제
			cartCommandRepository.deleteBskCpstByGodTurn(god);

			for (BskGod pckageGod : bskGodList) {
				// 구성 상품 삭제
				cartCommandRepository.deleteBskGod(pckageGod);
			}
			// }else{
			// cartCommandRepository.deleteBskGod(bskGod);
			// }
		}

		// 즉시 구매의 경우 장바구니 마스터 삭제
		if (cartSearchDTO.getBsk() != null && StringService.equalsIgnoreCase(BskEnum.BskTp.DIRT.toString(),
				StringService.nvl(cartSearchDTO.getBsk().getBskTpCd(), BskEnum.BskTp.BSK.toString()))) {
			cartCommandRepository.deleteBskByBskNo(cartSearchDTO.getBsk().getBskNo());
		}
	}

	/**
	 * [장바구니 병합].
	 * 
	 * <p/>
	 * 
	 * [비로그인시 넣었던 장바구니 상품을 회원 장바구니로 병합].
	 *
	 * @param bsk
	 *            [설명]
	 * @throws Exception
	 *             the exception
	 * @since 2015. 5. 6
	 */
	public void mergeCart(Bsk bsk) throws Exception {

		// 1. 병합 대상 카드 조회
		Bsk nmbrBsk = cartSelectRepository.selectBskInfoBySession(bsk);

		if (nmbrBsk != null) {
			// 2. member no 로 카트에 상품있는지 확인
			BskGod mrbBskGod = cartSelectRepository.selectCartByMbrNo(bsk);

			if (mrbBskGod == null) {
				cartCommandRepository.updateBskBySessionId(bsk);
				bsk.setBskNo(nmbrBsk.getBskNo());
				cartCommandRepository.updateBskGodBySessionId(bsk);
				cartCommandRepository.updateBskCpstGodBySessionId(bsk);
			} else {
				CartSearchDTO cartSearchDTO = new CartSearchDTO();
				bsk.setBskNo(mrbBskGod.getBskNo());
				cartSearchDTO.setTargetBsk(bsk);
				nmbrBsk.setRegtrId(bsk.getRegtrId());
				nmbrBsk.setUdterId(bsk.getUdterId());
				cartSearchDTO.setBsk(nmbrBsk);
				cartSearchDTO.setMaxGodTurn(mrbBskGod.getGodTurn());

				// 회원 장바구니와 중복된 상품 검색
				List<BskGodExtend> resultList = cartSelectRepository.selectBskMergeTarget(cartSearchDTO);

				// 중복된 상품 삭제
				if (!CollectionService.isEmpty(resultList)) {
					BskGod deleteGod = new BskGod();
					deleteGod.setBskNo(bsk.getBskNo());

					for (BskGodExtend bskgod : resultList) {
						deleteGod.setGodTurn(bskgod.getParentGodTurn());
						if (StringService.equalsIgnoreCase(BskEnum.YES.toString(), bskgod.getPckageGodYn())) {
							cartCommandRepository.deleteBskCpstByGodTurn(deleteGod);
							cartCommandRepository.deleteBskGod(deleteGod);
							deleteGod.setGodTurn(bskgod.getGodTurn());
							cartCommandRepository.deleteBskGod(deleteGod);
						} else {
							cartCommandRepository.deleteBskGod(deleteGod);
						}
					}
				}

				// 2.2 merge guest sessionId with loginId cart
				cartCommandRepository.mergeBskGod(cartSearchDTO);
				cartCommandRepository.mergeBskCpstGodCnnc(cartSearchDTO);

				// 2.3 delete session_id cart
				cartCommandRepository.deleteBskCpstBySessionId(nmbrBsk);
				cartCommandRepository.deleteBskGodBySessionId(nmbrBsk);
				cartCommandRepository.deleteBskBySessionId(nmbrBsk);
			}
		}
	}

	/**
	 * [주문대상 상품 병합].
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param pBsk
	 *            the bsk
	 * @param orderDTO
	 *            [설명]
	 * @return Cart search dto [설명]
	 * @throws Exception
	 *             the exception
	 * @since 2015. 5. 6
	 */
	public CartSearchDTO mergeCartOrder(Bsk pBsk, CartSearchDTO orderDTO) throws Exception {

		// member no 로 장바구니 조회
		Bsk bsk = cartSelectRepository.selectBskInfo(pBsk);

		if (bsk != null && StringService.isNotEmpty(bsk.getBskNo())) {
			orderDTO.setTargetBsk(bsk); // 삭제할 대상 장바구니
			orderDTO.getBsk().setBskNo(orderDTO.getBskGodList().get(0).getBskNo());

			List<BskGodExtend> bskGodList = cartSelectRepository.selectBskGodList(orderDTO);

			if (!CollectionService.isEmpty(bskGodList)) {
				BskGod deleteGod = new BskGod();
				deleteGod.setBskNo(bsk.getBskNo());

				for (BskGodExtend bskgod : bskGodList) {
					deleteGod.setGodTurn(bskgod.getParentGodTurn());
					if (StringService.equalsIgnoreCase(BskEnum.YES.toString(), bskgod.getPckageGodYn())) {
						cartCommandRepository.deleteBskCpstByGodTurn(deleteGod);
						cartCommandRepository.deleteBskGod(deleteGod);
						deleteGod.setGodTurn(bskgod.getGodTurn());
						cartCommandRepository.deleteBskGod(deleteGod);
					} else {
						cartCommandRepository.deleteBskGod(deleteGod);
					}
				}
			}

			BskGod bskGod = cartSelectRepository.selectCartByMbrNo(bsk);
			List<BskGod> order = new ArrayList<BskGod>();

			for (BskGod god : orderDTO.getBskGodList()) {
				BskGod orderGod = new BskGod();
				orderGod.setBskNo(bskGod.getBskNo());
				orderGod.setGodTurn(god.getGodTurn() + bskGod.getGodTurn());
				order.add(orderGod);
			}

			// 세션에서 넘어온 로그인 전 장바구니가 있다면 해당 장바구니에 담겨있는 상품들을 회원 장바구니로 복사한다.
			List<BskGodExtend> ssBskGodList = cartSelectRepository
					.selectBskGodListByBskNo(orderDTO.getBsk().getBskNo());
			for (BskGodExtend dto : ssBskGodList) {
				dto.setBskNo(bskGod.getBskNo());
				dto.setGodTurn(dto.getGodTurn() + bskGod.getGodTurn());
				dto.setRegtrId(pBsk.getMbrNo());
				dto.setUdterId(pBsk.getMbrNo());
				cartCommandRepository.insertCartGod(dto);
			}
			List<BskCpstGodCnnc> ssBskCpstGodList = cartSelectRepository
					.selectBskCpstGodListByBskNo(orderDTO.getBsk().getBskNo());
			List<BskCpstGodCnnc> mbrBskCpstGodList = cartSelectRepository
					.selectBskCpstGodListByBskNo(bskGod.getBskNo());
			int sortCnt = 0;
			for (BskCpstGodCnnc dto : mbrBskCpstGodList) {
				int dbSort = dto.getSortSeq();
				if (sortCnt < dbSort) {
					sortCnt = dbSort;
				}
			}
			BskCpstGodCnnc bskCpstGodCnnc = null;
			for (BskCpstGodCnnc dto : ssBskCpstGodList) {
				bskCpstGodCnnc = new BskCpstGodCnnc();
				// 구성 상품 연결 정보
				bskCpstGodCnnc.setBskNo(bskGod.getBskNo()); // 장바구니 번호
				bskCpstGodCnnc.setGodTurn(dto.getGodTurn() + bskGod.getGodTurn()); // 상품순번
				bskCpstGodCnnc.setCpstGodTurn(dto.getCpstGodTurn() + bskGod.getGodTurn()); // 구성 상품 순번
				bskCpstGodCnnc.setPckageGodTpCd(dto.getPckageGodTpCd()); // 패키지형 상품 유형 코드
				bskCpstGodCnnc.setCpstGodQty(dto.getCpstGodQty()); // 구성 상품 수량
				bskCpstGodCnnc.setRegtrId(pBsk.getMbrNo());
				bskCpstGodCnnc.setUdterId(pBsk.getMbrNo());

				bskCpstGodCnnc.setSortSeq(dto.getSortSeq() + sortCnt); // 정렬순서

				cartCommandRepository.insertCartCpstGod(bskCpstGodCnnc);
			}

			orderDTO.setBskGodList(order);
			orderDTO.getBsk().setBskNo(bskGod.getBskNo());
		}
		return orderDTO;
	}

	/**
	 * [메서드 설명].
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param bskGodList
	 *            [설명]
	 * @throws Exception
	 *             the exception
	 * @since 2015. 6. 19
	 */
	public void updateBskGodOption(CartSearchDTO cartSearchDTO) throws Exception {

		// 장바구니 데이터 적재 by cannon (2016.06.16) - 수정 전 등록
		if ("Y".equals(cartSearchDTO.getPckageGodYn())) {
			BskGod bskGod = new BskGod();
			bskGod.setBskNo(cartSearchDTO.getBskNo());
			bskGod.setGodTurn(cartSearchDTO.getParentGodTurn());
			this.insertCartBskGodHist("DELETE", bskGod);
		} else {
			this.insertCartBskGodHist("DELETE", cartSearchDTO.getBskGodList().get(0));
		}

		// 옵션 변경
		for (BskGod bskGod : cartSearchDTO.getBskGodList()) {
			cartCommandRepository.updateBskGodOption(bskGod);
		}

		// 장바구니 데이터 적재 by cannon (2016.06.16) - 수정 후 등록
		if ("Y".equals(cartSearchDTO.getPckageGodYn())) {
			BskGod bskGod = new BskGod();
			bskGod.setBskNo(cartSearchDTO.getBskNo());
			bskGod.setGodTurn(cartSearchDTO.getParentGodTurn());
			this.insertCartBskGodHist("INPUT", bskGod);
		} else {
			this.insertCartBskGodHist("INPUT", cartSearchDTO.getBskGodList().get(0));
		}

		// 같은 옵션의 중복상품 병합
		String pckageGodYn = cartSearchDTO.getPckageGodYn();
		long itmQty = 0;
		BskGod search = new BskGod();
		search.setDlvSectCd(cartSearchDTO.getBskGodList().get(0).getDlvSectCd());
		search.setPkupShopSn(cartSearchDTO.getBskGodList().get(0).getPkupShopSn());
		List<BskGodExtend> mergeTarget = null;

		if (StringService.equalsIgnoreCase("Y", pckageGodYn)) {
			search.setBskNo(cartSearchDTO.getBskNo());
			search.setGodTurn(cartSearchDTO.getParentGodTurn());
			mergeTarget = cartSelectRepository.selectPckageOptMergeTarget(search);
		} else {
			search.setBskNo(cartSearchDTO.getBskGodList().get(0).getBskNo());
			search.setGodTurn(cartSearchDTO.getBskGodList().get(0).getGodTurn());
			mergeTarget = cartSelectRepository.selectGnrlOptMergeTarget(search);
		}

		if (mergeTarget != null && mergeTarget.size() > 0) {
			itmQty = mergeTarget.get(0).getItmQty();
			CartSearchDTO delete = new CartSearchDTO();
			delete.setBskgodExtendList(mergeTarget);
			BskGodExtend data = new BskGodExtend();
			data.setBskNo(search.getBskNo());
			data.setParentGodTurn(search.getGodTurn());
			data.setItmQty(itmQty);

			cartCommandRepository.updateGodItmQty(data);

			if (StringService.equalsIgnoreCase("Y", pckageGodYn)) {
				for (BskGod bskGod : cartSearchDTO.getBskGodList()) {
					data.setGodTurn(bskGod.getGodTurn());
					cartCommandRepository.updateSetGodItmQty(data);
				}

				// 병합된 장바구니연결 상품 삭제
				cartCommandRepository.deleteBskGodCnncByOptChange(delete);
			}

			// 병합된 데이터 삭제
			cartCommandRepository.deleteBskGodByOptChange(delete);
			cartCommandRepository.deleteBskGodMasterByOptChange(delete);

		}
	}

	/**
	 * [메서드 설명].
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param bskGodList
	 *            [설명]
	 * @throws Exception
	 *             the exception
	 * @since 2015. 6. 19
	 */
	public void updateMbBskGodOption(CartSearchDTO cartSearchDTO) throws Exception {

		BskGod god = cartSearchDTO.getBskGodList().get(0);
		cartCommandRepository.updateMbBskGodOption(god);

		List<BskGodExtend> mergeTarget = cartSelectRepository.selectGnrlOptMergeTarget(god);

		if (mergeTarget != null && mergeTarget.size() > 0) {
			CartSearchDTO delete = new CartSearchDTO();
			delete.setBskgodExtendList(mergeTarget);
			BskGodExtend data = new BskGodExtend();
			data.setBskNo(god.getBskNo());
			data.setParentGodTurn(god.getGodTurn());
			data.setItmQty(mergeTarget.get(0).getItmQty());

			cartCommandRepository.updateGodItmQty(data);

			// 병합된 데이터 삭제
			cartCommandRepository.deleteBskGodByOptChange(delete);
		}

	}

	/**
	 * [메서드 설명].
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param cartDTO
	 *            [설명]
	 * @throws Exception
	 *             the exception
	 * @since 2015. 6. 19
	 */
	public void updateBskPckageGodOption(CartGodOptionDTO cartDTO) throws Exception {

		BskGod god = new BskGod();
		god.setBskNo(cartDTO.getBskNo());
		god.setGodTurn(cartDTO.getGodTurn());
		god.setItmQty(Long.valueOf(cartDTO.getItmQty()));
		god.setUdterId(cartDTO.getUdterId());
		cartCommandRepository.updateBskGodQty(god);

		for (BskGod bskGod : cartDTO.getBskGodList()) {
			// bskGod.setItmQty(cartDTO.getItmQty() * bskGod.getItmQty());
			bskGod.setItmQty(Long.valueOf(cartDTO.getItmQty()));
			bskGod.setUdterId(cartDTO.getUdterId());
			cartCommandRepository.updateMbBskGodOption(bskGod);
		}

		BskGod search = new BskGod();
		search.setBskNo(cartDTO.getBskNo());
		search.setGodTurn(cartDTO.getGodTurn());
		search.setDlvSectCd(cartDTO.getBskGodList().get(0).getDlvSectCd());
		search.setPkupShopSn(cartDTO.getBskGodList().get(0).getPkupShopSn());

		List<BskGodExtend> mergeTarget = cartSelectRepository.selectPckageOptMergeTarget(search);

		if (mergeTarget != null && mergeTarget.size() > 0) {
			CartSearchDTO delete = new CartSearchDTO();
			delete.setBskgodExtendList(mergeTarget);
			BskGodExtend data = new BskGodExtend();
			data.setBskNo(search.getBskNo());
			data.setParentGodTurn(search.getGodTurn());
			data.setItmQty(mergeTarget.get(0).getItmQty());

			cartCommandRepository.updateGodItmQty(data);

			for (BskGod bskGod : cartDTO.getBskGodList()) {
				data.setGodTurn(bskGod.getGodTurn());
				cartCommandRepository.updateSetGodItmQty(data);
			}

			// 병합된 장바구니연결 상품 삭제
			cartCommandRepository.deleteBskGodCnncByOptChange(delete);
			// 병합된 데이터 삭제
			cartCommandRepository.deleteBskGodByOptChange(delete);
			cartCommandRepository.deleteBskGodMasterByOptChange(delete);
		}
	}

	/**
	 * [즉시구매 장바구니 삭제].
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param bskNo
	 *            [설명]
	 * @throws Exception
	 *             the exception
	 * @since 2015. 5. 6
	 */
	public void deleteDirtCart(String bskNo) throws Exception {

		cartCommandRepository.deleteBskCpstGodByBskNo(bskNo);
		cartCommandRepository.deleteBskGodByBskNo(bskNo);
		cartCommandRepository.deleteBskByBskNo(bskNo);

	}

	/**
	 * [즉시구매 장바구니 삭제].
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param bskNo
	 *            [설명]
	 * @throws Exception
	 *             the exception
	 * @since 2015. 5. 6
	 */
	public void deletDirtBskbyMbrNo(String mbrNo) throws Exception {

		cartCommandRepository.deletDirtBskbyMbrNo(mbrNo);
	}

	/**
	 * [즉시구매 장바구니 수정].
	 * 
	 * <p/>
	 * 
	 * [비로그인 장바구니를 회원장바구니로 수정].
	 *
	 * @param bsk
	 *            [설명]
	 * @throws Exception
	 *             the exception
	 * @since 2015. 5. 6
	 */
	public void updateBskMaster(Bsk bsk) throws Exception {
		Bsk sBsk = new Bsk();
		sBsk.setBskTpCd(BskEnum.BskTp.DIRT.toString());
		sBsk.setMallId(bsk.getMallId());

		/*
		 * NCP 3차 쇼핑백 분기처리 since 2016.01.22
		 */
		sBsk.setLangCd(bsk.getLangCd());

		sBsk.setMbrNo(bsk.getMbrNo());
		sBsk.setSesionId(bsk.getSesionId());
		sBsk.setUdterId(bsk.getUdterId());
		cartCommandRepository.updateBskBySessionId(sBsk);
	}

	/**
	 * [메서드 설명].
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param cartSearchDTO
	 *            [설명]
	 * @throws Exception
	 *             the exception
	 * @since 2015. 6. 19
	 */
	public void updateCartDlv(CartSearchDTO cartSearchDTO) throws Exception {

		cartCommandRepository.updateCartDlv(cartSearchDTO);

		BskGod bskGod = new BskGod();
		bskGod.setBskNo(cartSearchDTO.getBsk().getBskNo());
		bskGod.setDlvSectCd("GNRL_DLV");
		this.mergeBskGod(bskGod);
	}

	/**
	 * [메서드 설명].
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param wish
	 *            [설명]
	 * @return Bsk wishlst [설명]
	 * @throws Exception
	 *             the exception
	 * @since 2015. 6. 19
	 */
	public BskWishlst insertWishList(BskWishlst wish) throws Exception {

		Long wishlstSn = getIdGenService().generateDBSequence(sqlSession1, "SQ_BSK_WISHLST", DatabaseType.ORACLE);
		wish.setWishlstSn(wishlstSn);

		bskWishlstRepository.insertBskWishlst(wish);

		return wish;
	}

	/**
	 * [메서드 설명].
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param wishList
	 *            [설명]
	 * @param bskGodList
	 *            [설명]
	 * @throws Exception
	 *             the exception
	 * @since 2015. 6. 19
	 */
	public int insertWishListGod(BskWishlst wishList, List<BskGod> bskGodList) throws Exception {

		WishListSearchDTO wishDTO = new WishListSearchDTO();
		wishDTO.setBskWishlst(wishList);

		List<BskWishlstGod> wishGodList = new ArrayList<BskWishlstGod>();
		List<String> godNo = new ArrayList<String>();

		for (BskGod god : bskGodList) {
			if (!godNo.contains(god.getGodNo())) {
				BskWishlstGod wishGod = new BskWishlstGod();
				wishGod.setWishlstSn(wishList.getWishlstSn());
				wishGod.setGodNo(god.getGodNo());
				wishGod.setItmNo(god.getItmNo());
				wishGod.setItmQty(god.getItmQty());
				if(StringUtils.isEmpty(god.getPckageGodYn())) {
					
					God v = new God();
					v.setGodNo(god.getGodNo());
					v = godRepository.selectGod(v);
                   if("SET_GOD".equals(v.getGodTpCd())) {
                	   wishGod.setPckageGodYn("Y");	
                   }else {
                	   wishGod.setPckageGodYn("N");	
                   }
				}else {
					 
					wishGod.setPckageGodYn(god.getPckageGodYn());	
				}
				wishGodList.add(wishGod);
				godNo.add(god.getGodNo());
			}
		}
		wishDTO.setBskWishlstGod(wishGodList);

		return cartCommandRepository.insertWishListGod(wishDTO);

	}

	/**
	 * [메서드 설명].
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param godReWhsgNtcnDTO
	 *            [설명]
	 * @throws Exception
	 *             the exception
	 * @since 2015. 6. 19
	 */
	public void insertInvAlrm(GodReWhsgNtcnDTO godReWhsgNtcnDTO) throws Exception {

		GodReWhsgNtcn rewhsg = new GodReWhsgNtcn();

		rewhsg.setSetGodNo(godReWhsgNtcnDTO.getSetGodNo());
		rewhsg.setMobilNationNo(godReWhsgNtcnDTO.getNationNo());
		rewhsg.setMobilAreaNo(godReWhsgNtcnDTO.getAreaNo());
		rewhsg.setMobilTlofNo(godReWhsgNtcnDTO.getTlofNo1());
		rewhsg.setMobilTlofWthnNo(godReWhsgNtcnDTO.getTlofNo2());
		rewhsg.setStplatIemAgrYn(BskEnum.YES.toString());
		rewhsg.setStplatCd(godReWhsgNtcnDTO.getStplatList().get(0).getStplatCd());
		rewhsg.setRegtrId(godReWhsgNtcnDTO.getRegtrId());
		rewhsg.setUdterId(godReWhsgNtcnDTO.getUdterId());

		// ncp 3차 ************************************************
		rewhsg.setNtcnSectCd(godReWhsgNtcnDTO.getNtcnSectCd());
		rewhsg.setMbrEmail(godReWhsgNtcnDTO.getMbrEmail());
		// ncp 3차 ************************************************

		if (StringService.isNotEmpty(godReWhsgNtcnDTO.getMbrNo())) {
			rewhsg.setMbrNo(godReWhsgNtcnDTO.getMbrNo());
		}

		for (BskGod god : godReWhsgNtcnDTO.getBskGodList()) {
			rewhsg.setGodNo(god.getGodNo());
			rewhsg.setItmNo(god.getItmNo());
			cartCommandRepository.insertInvAlrm(rewhsg);
		}
	}

	/**
	 * [메서드 설명].
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param cartSearchDTO
	 *            [설명]
	 * @throws Exception
	 *             the exception
	 * @since 2015. 6. 19
	 */
	public void updatePkupShop(CartSearchDTO cartSearchDTO) throws Exception {
		cartCommandRepository.updatePkupShop(cartSearchDTO);

		BskGod bskGod = new BskGod();
		bskGod.setBskNo(cartSearchDTO.getBskGodList().get(0).getBskNo());
		bskGod.setDlvSectCd("PKUP_DLV");
		bskGod.setPkupShopSn(cartSearchDTO.getShopId());
		this.mergeBskGod(bskGod);
	}

	public void deleteWishList(BskWishlstGod wishGod) throws Exception {
		cartCommandRepository.deleteWishList(wishGod);

	}

	/**
	 * [메서드 설명].
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param conditions
	 *            [설명]
	 * @return Int [설명]
	 * @throws Exception
	 *             the exception
	 * @since 2015. 6. 20
	 */
	private int getGodTurn(Map<String, Object> conditions) throws Exception {

		return getIdGenService().generateDBOrder(sqlSession1, "BSK_GOD", "GOD_TURN", conditions, DatabaseType.ORACLE);
	}

	/**
	 * [장바구니 상품 병합 및 삭제].
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param conditions
	 *            [설명]
	 * @return Int [설명]
	 * @throws Exception
	 *             the exception
	 * @since 2015. 6. 20
	 */
	private void mergeBskGod(BskGod bskGod) throws Exception {

		// 1. 병합대상 상품 수량 업데이트
		int cnt = cartCommandRepository.mergeBskGodItmQty(bskGod);
		// 2.병합된 상품 삭제
		if (cnt > 0) {
			List<BskGodExtend> deleteList = cartSelectRepository.selectMergedTarget(bskGod);

			BskGod god = new BskGod();
			god.setBskNo(bskGod.getBskNo());
			for (BskGodExtend delete : deleteList) {
				if (delete.getParentGodTurn() != delete.getGodTurn()) {
					god.setGodTurn(delete.getParentGodTurn());
					// 장바구니 구성 연결 삭제
					cartCommandRepository.deleteBskCpstByGodTurn(god);
					// 장바구니 상품 마스터 삭제
					god.setGodTurn(delete.getParentGodTurn());
					cartCommandRepository.deleteBskGod(god);
				}

				// 장바구니 상품 삭제
				god.setGodTurn(delete.getGodTurn());
				cartCommandRepository.deleteBskGod(god);
			}

		}

	}

	public void cleanBsk(Bsk bsk) throws Exception {

		List<Bsk> targetBsk = cartSelectRepository.selectCleanTargetBsk(bsk);

		if (targetBsk != null && targetBsk.size() > 1) {
			int cnt = 0;
			for (Bsk pBsk : targetBsk) {
				if (cnt > 0) {
					cartCommandRepository.deletDummyBsk(pBsk.getBskNo());
				}
				cnt++;
			}
		}
	}

	// 장바구니 데이터 적재 by cannon (2016.06.16)
	public void insertCartBskGodHist(String trnscTpCd, BskGod god) throws Exception {

		List<CartGodHistDTO> cartGodHistTgtList = cartSelectRepository.selectCartGodHistTgt(god);

		if (cartGodHistTgtList != null && !cartGodHistTgtList.isEmpty() && cartGodHistTgtList.get(0).getMbrNo() != null
				&& !cartGodHistTgtList.get(0).getMbrNo().isEmpty()) {

			Long upperBskGodHistSn = null;
			for (CartGodHistDTO cartGodHistDTO : cartGodHistTgtList) {
				if (upperBskGodHistSn != null)
					cartGodHistDTO.setUpperBskGodHistSn(upperBskGodHistSn);

				cartGodHistDTO.setTrnscTpCd(trnscTpCd);
				cartCommandRepository.insertCartBskGodHist(cartGodHistDTO);

				if (upperBskGodHistSn == null)
					upperBskGodHistSn = cartGodHistDTO.getBskGodHistSn();
			}
		}
	}
	
	private String getMemberGuestId() throws Exception {
		String id = "GUEST";
		// 회원
		if(ContextService.hasRole()){ // 회원
			Mbr mbr = ((SecurityUserDetail) ContextService.getCurrentUserDetail()).getMbr();
			id = mbr.getMbrNo();
		}


		return id;
	}

}
