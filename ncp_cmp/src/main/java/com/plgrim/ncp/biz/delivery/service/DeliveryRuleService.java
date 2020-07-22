/* Copyright (c) 2015 plgrim, Inc.
 * All right reserved.
 * http://plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 *
 * ------------------------------------------------------------------------
 * @author      th86.yang
 * @since       2015. 8. 29       
 */
package com.plgrim.ncp.biz.delivery.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.google.common.base.Function;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimaps;
import com.plgrim.ncp.base.abstracts.AbstractService;
import com.plgrim.ncp.base.entities.datasource1.clm.ClmWrhsGodExtend;
import com.plgrim.ncp.base.entities.datasource1.com.ComDmstcDlvCstPlcExtend;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsDlivyDrctGod;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsDlv;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsDlvFoExtend;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsDlvspExtend;
import com.plgrim.ncp.base.entities.datasource1.ord.Ord;
import com.plgrim.ncp.base.entities.datasource1.ord.OrdGod;
import com.plgrim.ncp.base.entities.datasource1.ord.OrdGodExtends;
import com.plgrim.ncp.base.enums.OrderEnum;
import com.plgrim.ncp.base.repository.ord.OrdRepository;
import com.plgrim.ncp.biz.claim.data.ClmDlvOrdGodInfoDTO;
import com.plgrim.ncp.biz.delivery.data.DeliveryRuleByGoodDTO;
import com.plgrim.ncp.biz.delivery.repository.DeliveryCommandRepository;
import com.plgrim.ncp.biz.delivery.repository.DeliverySelectRepository;
import com.plgrim.ncp.biz.delivery.result.DeliveryRuleByGoodResult;
import com.plgrim.ncp.biz.order.repository.OrderSelectRepository;
import com.plgrim.ncp.framework.commons.StringService;
import com.plgrim.ncp.interfaces.util.InterfaceApiCommon;

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
 * @author 
 * @since 2015. 4. 13
 */
@Slf4j
@Service
public class DeliveryRuleService extends AbstractService {

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	@Autowired
	DeliveryCommandRepository deliveryCommandRepository;

	@Autowired
	DeliverySelectRepository deliverySelectRepository;

	@Autowired
	OrderSelectRepository orderSelectRepository;

	@Autowired
	OrdRepository ordRepository;
	
	@Autowired
	InterfaceApiCommon interfaceApiCommon;
	

	@Autowired
	@Qualifier("sessionTemplate1")
	SqlSession sqlSession1;

	/*
	 * ---------------------------------------------------------------------
	 * Constructors.
	 * ---------------------------------------------------------------------
	 */

	
	
	
	/**
	 * 상품리스트를 전달받아 배송 정책을 리턴하도록 처리
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dto [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 8. 2
	 */
	public	List<DeliveryRuleByGoodResult>  selectDeliveryRuleByGood(DeliveryRuleByGoodDTO deliveryRuleByGoodDTO) throws Exception {
		return deliverySelectRepository.selectDeliveryRuleByGood(deliveryRuleByGoodDTO);
	}
	
	/**
	 * 상품리스트를 전달받아 국내 배송정책번호를 키로 하는 배송정책 MapList을 리턴한다.
	 * @param deliveryRuleByGoodDTO
	 * @return
	 * @throws Exception
	 */
	public Map<Long, DeliveryRuleByGoodResult> selectDeliveryRuleMapByGoodNo(DeliveryRuleByGoodDTO deliveryRuleByGoodDTO) throws Exception {
		
		List<DeliveryRuleByGoodResult> deliveryRulByGoodResultList = deliverySelectRepository.selectDeliveryRuleByGood(deliveryRuleByGoodDTO);
		
		Map<Long, DeliveryRuleByGoodResult> deliveryRuleMap = null; 
				
		if (deliveryRulByGoodResultList != null && deliveryRulByGoodResultList.size() > 0 ) {
			deliveryRuleMap = Maps.uniqueIndex(
					deliveryRulByGoodResultList, 
					new Function<DeliveryRuleByGoodResult, Long>() {
			            @Override
			            public Long apply(DeliveryRuleByGoodResult deliveryRulByGoodResult) {
			                return deliveryRulByGoodResult.getComDmstcDlvCstPlc().getDmstcDlvCstPlcSn();
			            }
			        }
				);
		}
		
		return deliveryRuleMap;
	}
	
	/**
	 * 주문 상품에 해당하는 물류배송 정보 생성 - 주문 배송비 (현재는 취소에서만 사용)
	 * 
	 * @param ordGodList
	 * @return List<LgsDlv>
	 * @throws Exception
	 */
	public List<LgsDlvFoExtend> getOrdGodLgsDlv(List<OrdGodExtends> ordGodList, List<LgsDlvFoExtend> lgsDlvList, boolean isOrdFlag,List<ClmWrhsGodExtend> reqClmWrhsGodListList) throws Exception {
		List<LgsDlvFoExtend> retLgsDlvList = new ArrayList<LgsDlvFoExtend>();		
		
		// 상품번호별 배송정책 리스트 조회 조건 셋팅
		Set<String> godList = new HashSet<String>();
		for (OrdGod ordGod : ordGodList) {
			godList.add(ordGod.getGodNo());
		}
		
		// 배송정책 일련번호 기준으로 Map 생성
		Map<Long, ComDmstcDlvCstPlcExtend> deliveryRuleMap = this.getDeliveryRuleMapByGodNoList(new ArrayList<String>(godList), false);
		
		// 배송 수거지별로 주문상품 ListMultiMap 생성
		ListMultimap<Integer, OrdGodExtends> ordGodDlvListMap = Multimaps.index(
			ordGodList, 
			new Function<OrdGodExtends, Integer>() {
	            @Override
	            public Integer apply(OrdGodExtends ordGod) {
	                return ordGod.getDlvPcupspTurn();
	            }
	        }
		);
		
		// 배송정보 Map
		ListMultimap<String, LgsDlvFoExtend> lgsDlvListMap = ArrayListMultimap.create();
		if (lgsDlvList != null && lgsDlvList.size() > 0) {
			// 배송정책 일련번호 기준으로 Map 생성
			lgsDlvListMap = Multimaps.index(
				lgsDlvList, 
				new Function<LgsDlvFoExtend, String>() {
					@Override
					public String apply(LgsDlvFoExtend lgsDlv) {
		                return lgsDlv.getDlvPcupspTurn() + "_" + lgsDlv.getDmstcDlvCstPlcSn().intValue();
		            }
				}
			);
		}
		
		// 배송 수거지 + 배송 정책 별로 주문 상품의 배송비 계산
		// 배송 수거지 별
		for (Integer dlvPcupspTurn : ordGodDlvListMap.keySet()) {
			
			// 배송 정책 별
		    boolean cpnFlag=false;
		    
			for (Long dmstcDlvCstPlcSn : deliveryRuleMap.keySet()) {
				// 배송비 정보를 설정할 [배송물류 Entity]
				LgsDlvFoExtend lgsDlv = null;
				
				// 배송정책 일련번호에 해당하는 배송정책 정보를 가져온다 
				ComDmstcDlvCstPlcExtend comDmstcDlvCstPlc = deliveryRuleMap.get(dmstcDlvCstPlcSn);
				
				// 배송정책 번호별 주문금액 합계
				double sumSaleAmt = 0;
				// 국내 배송비 면제 기준 금액
				double dmstcDlvCstExmStdrAmt = 0;
				// 국내 배송비
				double dmstcDlvCst = 0;
				// 원 실 배송비
				double oriRealityDlvCst = 0;
				// 취소배송비
				double befCnclDlvCst = 0;
				// 무료배송비쿠폰;
				double dlvCstCpnDcAmt = 0;
				
				// 주문상품을 배송수거지 기준으로 돌며 같은 배송비 정책 기준에 해당하는  
				for (OrdGodExtends ordGod : ordGodDlvListMap.get(dlvPcupspTurn)) {
					// 국내 배송비 정책 일련번호가 같은 경우
					if (dmstcDlvCstPlcSn.equals(ordGod.getDmstcDlvCstPlcSn())) {
						
						// 물류배송 정보가 없는 경우에 배송순번까지 체크하여 가져온다. 배송 순번이 가장 큰것에 넣는다. 
						for (LgsDlvFoExtend lgsDlvFoExtend : lgsDlvListMap.get(dlvPcupspTurn + "_" + dmstcDlvCstPlcSn.intValue())) {
							if (ordGod.isClmApply() && lgsDlvFoExtend.getDlvTurn() == ordGod.getDlvTurn()) {
								lgsDlv = lgsDlvFoExtend;
							}
							
							if (lgsDlvFoExtend.getRealityDlvCst().doubleValue() > 0) {
								oriRealityDlvCst = lgsDlvFoExtend.getRealityDlvCst().doubleValue();
							}
							
							if (lgsDlvFoExtend.getBefCnclDlvCst().doubleValue() > 0) { //전체취소인경우
								befCnclDlvCst = lgsDlvFoExtend.getBefCnclDlvCst().doubleValue();
							}
							//무료배송비쿠폰금액확인
							if (lgsDlvFoExtend.getDlvCstCpnDcAmt().doubleValue() > 0) {
								dlvCstCpnDcAmt = lgsDlvFoExtend.getDlvCstCpnDcAmt().doubleValue();
							}
						}

						// 주문인 경우
						if (isOrdFlag) {
							// 배송정책 번호별 주문금액 합계 설정 : 판매 금액
							sumSaleAmt += ordGod.getSaleAmt().doubleValue();
						}
						// 취소인 경우
						else {
							// 배송정책 번호별 주문금액 합계 설정
							sumSaleAmt += ( ordGod.getRtlPrc().doubleValue() * ordGod.getOrdQty().doubleValue() ) 
													- ((
															( ordGod.getWebDcAmt().doubleValue() 
																	+ ordGod.getEmpDcAmt().doubleValue() 
																	+ ordGod.getGodDcAmt().doubleValue() 
																	+ ordGod.getBundleDcAmt().doubleValue() 
																	+ ordGod.getCrsDcAmt().doubleValue() 
																	+ ordGod.getGodCpnDcAmt().doubleValue() 
															) / ordGod.getOriOrdQty().doubleValue()
														) * ordGod.getOrdQty().doubleValue());		
						}
						
						// 국내 배송비 면제 기준 금액 설정
						dmstcDlvCstExmStdrAmt = comDmstcDlvCstPlc.getDmstcDlvCstExmStdrAmt().doubleValue();
						// 국내 배송비 설정
						dmstcDlvCst = comDmstcDlvCstPlc.getDmstcDlvCst().doubleValue();
					
					}
				}
				
				// 클레임 적용용 물류 배송이 생성되지 않은 경우 
				if (lgsDlv == null) {
					// 기존 물류 배송 정보가 있으면 첫번째 데이터 입력 
					if (lgsDlvListMap.containsKey(dlvPcupspTurn + "_" + dmstcDlvCstPlcSn.intValue())) {
						lgsDlv = lgsDlvListMap.get(dlvPcupspTurn + "_" + dmstcDlvCstPlcSn.intValue()).get(0);
					}
					// 기존 물류 배송 정보가 없는 경우 빈 값 생성 (주문에서 사용하기 위함. 현재는 사용 안함)
					else {
						lgsDlv = new LgsDlvFoExtend();

						// 배송 수거지 순번
						lgsDlv.setDlvPcupspTurn(dlvPcupspTurn);
						// 국내 배송비 정책 일련번호
						lgsDlv.setDmstcDlvCstPlcSn(dmstcDlvCstPlcSn);
					}
				}
				
				// 주문 번호
				lgsDlv.setOrdNo(ordGodDlvListMap.get(dlvPcupspTurn).get(0).getOrdNo());
				
				// 배송 업체 코드
				lgsDlv.setDlvComCd(comDmstcDlvCstPlc.getDlvComCd());
				
				// 원 실 배송비
				lgsDlv.setOriRealityDlvCst(new BigDecimal(oriRealityDlvCst));
				
				//클레임 마스터사유 추출
				List<ClmDlvOrdGodInfoDTO> dlvspClmOrdGod = new ArrayList<ClmDlvOrdGodInfoDTO>();
				ClmDlvOrdGodInfoDTO clmDlvOrdGodInfoDTO = null;
	    		for(ClmWrhsGodExtend clmGod : reqClmWrhsGodListList){
	    			clmDlvOrdGodInfoDTO = new ClmDlvOrdGodInfoDTO();
	    			clmDlvOrdGodInfoDTO.setClmResnCd(clmGod.getClmResnCd());	    			
	    			dlvspClmOrdGod.add(clmDlvOrdGodInfoDTO);
	    		}		    			    	
	    		
				if (sumSaleAmt > 0) {
					// 주문금액이 배송비 면제기준을 넘으면 무료(0), 넘지 않으면 배송비 적용, 매장픽업일 경우도 무료처리.
					if (sumSaleAmt >= dmstcDlvCstExmStdrAmt || "SHOP_PKUP".equals(lgsDlv.getDlvMnCd())) {
						lgsDlv.setCnclDlvCst(new BigDecimal(0));
						lgsDlv.setRealityDlvCst(new BigDecimal(0));
					} else {
						//배송비 차감
						//1.해당배송지에 이전 차감 금액이 있는지확인. 있다면 지나간다.
						//2.해당 배송지에 남아있는 상품이 있어야함.
						//3.정책이 깨지는지 확인. 깨지면 배송비 차감
						
						List<OrdGod> remainOrdGodAplPrmList = orderSelectRepository.selectRemainOrdGodAplPrmList(lgsDlv.getOrdNo(),dlvPcupspTurn);
						//해당주문에 걸려있는 주문상품적용 프로모션에 상품배송비쿠폰의 잔여 주문수량의sum 을 구한다.
						int cpnCnt = 0;
						for(OrdGod dto : remainOrdGodAplPrmList){
							cpnCnt += dto.getOrdQty().intValue();
							for(ClmWrhsGodExtend clmGod : reqClmWrhsGodListList){
								 
								if(clmGod.getOrdGodTurn() == dto.getOrdGodTurn()){
									cpnCnt -= clmGod.getClmQty();
								}									 																													 
							}	
						}
					     
						if(cpnCnt==0){ cpnFlag=true;}   //상품배송비쿠폰 적용수량이 없는경우
											    
				 	    if(cpnFlag) {
				 	    	if( dlvCstCpnDcAmt > 0) { //무료배송비쿠폰을 사용한경우
				 	    		lgsDlv.setCnclDlvCst(new BigDecimal(0));
				 	    		lgsDlv.setRealityDlvCst(new BigDecimal(0));	
				 	    	} else {
				 	    		lgsDlv.setCnclDlvCst(new BigDecimal(0));
				 	    		lgsDlv.setRealityDlvCst(new BigDecimal(dmstcDlvCst));
				 	    	}
					    } else {
						   lgsDlv.setCnclDlvCst(new BigDecimal(0));
						   lgsDlv.setRealityDlvCst(new BigDecimal(0));	
				  	    }   
					}
				// 전체 취소인 경우
				} else {
					
					
					// 실 주문 배송비가 기존에 있었던 경우 취소 배송비에 넣어준다.
					
					if(oriRealityDlvCst > 0 && (oriRealityDlvCst > befCnclDlvCst)) { //K2 이전취소금액이 없는경우만 이전배송비를 넣어준다.
						 lgsDlv.setCnclDlvCst(new BigDecimal(oriRealityDlvCst));
					}
					else {
						 lgsDlv.setCnclDlvCst(new BigDecimal(0));
				   	}
			    	
				}
				
				if (lgsDlvListMap.containsKey(dlvPcupspTurn + "_" + dmstcDlvCstPlcSn.intValue())) {
					retLgsDlvList.add(lgsDlv);
				} else {
					if (sumSaleAmt > 0) {
						retLgsDlvList.add(lgsDlv);
					}
				}
			}
		}
		
		return retLgsDlvList;
	}
	
	/**
	 * 취소 클레임 물류 배송 조회 
	 * 
	 * 1. 클레임 대상 원 주문 상품 목록 조회 조건 셋팅
	 * 2. 취소 대상 원 주문 상품 목록 조회
	 * 3. 원 주문에 해당하는 기존 클레임(부분취소) 조회
	 * 4. 클레임 대상 원 주문 상품 목록이 없는 경우 종료
	 * 5. 상품순번 기준으로 원 주문상품 ListMultiMap 생성
	 * 6. 상품순번 기준으로 원 주문에 해당하는 기존 클레임 ListMultiMap 생성
	 * 7. 상품순번 기준으로 요청받은 클레임 상품 Map 생성
	 * 8. 원 주문 - 부분취소 상품 리스트 재설정
	 * 9. 원 주문의 물류 배송정보 조회
	 * 10. 부분취소가 적용된 주문을 기준으로 배송정책 정보 새로 설정
	 * 
	 * @param reqClmWrhsGodListList
	 * @return List<LgsDlvFoExtend>
	 * @throws Exception
	 */
	public List<LgsDlvFoExtend> getCnclClmLgsDlvList(List<ClmWrhsGodExtend> reqClmWrhsGodListList) throws Exception {
		
		List<LgsDlvFoExtend> clmLgsDlvList = new ArrayList<LgsDlvFoExtend>();
		
		// 1. 클레임 대상 원 주문 상품 목록 조회 조건 셋팅
		DeliveryRuleByGoodDTO deliveryRuleByGoodDTO = new DeliveryRuleByGoodDTO();
		deliveryRuleByGoodDTO.setOrdNo(reqClmWrhsGodListList.get(0).getOrdNo());
		
		// 원 주문 상품만 조회 : "출고 상품 연결"이 연결된 주문 상품은 교환 상품으로 제외
		deliveryRuleByGoodDTO.setNotExistsGodCnncTpCd("DLIVY_GOD_CNNC");
		
		// 2. 취소 대상 원 주문 상품 목록 조회
		List<OrdGodExtends> oriOrdGodListByClmList = deliverySelectRepository.selectOriOrdGodListByClm(deliveryRuleByGoodDTO);
		
		// 3. 원 주문에 해당하는 기존 클레임(부분취소) 조회
		deliveryRuleByGoodDTO.setClmTpCd("PART_CNCL");	// 부분취소
		List<ClmWrhsGodExtend> oriClmWrhsGodList = deliverySelectRepository.selectClmWrhsGodByOrdNo(deliveryRuleByGoodDTO);
		
		// 4. 클레임 대상 원 주문 상품 목록이 없는 경우 종료
		if (oriOrdGodListByClmList == null || oriOrdGodListByClmList.size() == 0 ) {
			return clmLgsDlvList;
		}
		
		// 5. 상품순번 기준으로 원 주문상품 ListMultiMap 생성
		Map<Integer, OrdGodExtends> oriOrdGodMap = Maps.uniqueIndex(
			oriOrdGodListByClmList, 
			new Function<OrdGodExtends, Integer>() {
	            @Override
	            public Integer apply(OrdGodExtends ordGod) {
	                return ordGod.getOrdGodTurn();
	            }
	        }
		);
		
		// 6. 상품순번 기준으로 원 주문에 해당하는 기존 클레임 ListMultiMap 생성
		ListMultimap<Integer, ClmWrhsGodExtend> oriClmOrdGodTurnListMap = ArrayListMultimap.create(); 
		
		if (oriClmWrhsGodList != null && oriClmWrhsGodList.size() > 0) {
			oriClmOrdGodTurnListMap = Multimaps.index(
				oriClmWrhsGodList, 
				new Function<ClmWrhsGodExtend, Integer>() {
		            @Override
		            public Integer apply(ClmWrhsGodExtend ordGod) {
		                return ordGod.getOrdGodTurn();
		            }
		        }
			);
		}
		
		// 7. 상품순번 기준으로 요청받은 클레임 상품 Map 생성
		Map<Integer, ClmWrhsGodExtend> reqClmGodMap = Maps.uniqueIndex(
			reqClmWrhsGodListList, 
			new Function<ClmWrhsGodExtend, Integer>() {
				@Override
				public Integer apply(ClmWrhsGodExtend ordClmGodCnnc) {
	                return ordClmGodCnnc.getOrdGodTurn();
	            }
			}
		);
		
		// 8. 원 주문 - 부분취소 상품 리스트 재설정 
		List<OrdGodExtends> newOrdGodList = new ArrayList<OrdGodExtends>();
		
		for (Integer ordGodTurn : oriOrdGodMap.keySet()) {
			
			// 세트나 패키지인경우엔 skip
			if (StringService.equalsIn(oriOrdGodMap.get(ordGodTurn).getGodTpCd(), "SET_GOD", "PCKAGE_GOD")) continue;
			
			// 원 주문수량 - 기존 부분취소 적용 
			if (oriClmOrdGodTurnListMap.containsKey(ordGodTurn)) {
				for (ClmWrhsGodExtend clmWrhsGod : oriClmOrdGodTurnListMap.get(ordGodTurn)) {
					oriOrdGodMap.get(ordGodTurn).setOrdQty( oriOrdGodMap.get(ordGodTurn).getOrdQty() - clmWrhsGod.getClmQty());
				}
			}
			
			if (reqClmGodMap.containsKey(ordGodTurn)) {
				// 요청받은 부분취소 수량을 원 주문 수량에서 차감
				oriOrdGodMap.get(ordGodTurn).setOrdQty( oriOrdGodMap.get(ordGodTurn).getOrdQty() - reqClmGodMap.get(ordGodTurn).getClmQty() );
				oriOrdGodMap.get(ordGodTurn).setClmApply(true);
			}
				
			newOrdGodList.add(oriOrdGodMap.get(ordGodTurn));
		}
		
		// 9. 원 주문의 물류 배송정보 조회
		List<LgsDlvFoExtend> ordLgsDlvList = deliverySelectRepository.selectLgsDlvByOrdNo(deliveryRuleByGoodDTO);
		
		// 10. 부분취소가 적용된 주문을 기준으로 배송정책 정보 새로 설정 
		clmLgsDlvList = this.getOrdGodLgsDlv(newOrdGodList, ordLgsDlvList, false,reqClmWrhsGodListList);
		
		return clmLgsDlvList;
	}
	
	/**
	 * 반품 클레임 물류 배송 조회 
	 * 
	 * 1-1. 호출구분, 주문유형, 로그인유형을 기준으로 무료 배송 여부 확인
	 * 1-2. 전체 반품여부 체크
	 * 
	 * <반품 수거지 별로 Loop>
	 * 
	 * 2. 배송수단 코드가 "택배" 인 경우 이면 반품배송비 0원 처리
	 * 3. 반품 요청 상품 목록 Map
	 * 
	 * 4. 반품 진행을 할 원 주문 데이터 조회
	 * 4-1. 반품 요청을 한 주문 상품이 교환을 통해 변경된 상품이 아니면 해당 주문 상품 리스트 조회 (반품 요청 주문 상품 순번 = 원 주문 상품 순번)
	 * 4-2. 반품 요청을 한 주문 상품이 교환을 통해 변경된 상품이면 최초의 원 주문 상품 순번을 조회한다. (반품 요청 주문 상품 순번 <> 원 주문 상품 순번)
	 * 4-3. 4-2가 존재하면 (4-1) + (4-2) 을 합쳐 원 주문 데이터를 만든다. 
	 * 4-4. 교환의 원 주문 상품을 최조 주문 상품 리스트에 추가한다.
	 * 
	 * 5. 배송지 + 배송 정책 번호를 기준으로 원 주문 상품 전체 조회
	 * 5-1. 반품 요청 원 주문 상품을 배송정책 번호를 기준으로 ListMap 생성
	 * 
	 * 6. 주문 + 배송정책 번호에 해당하는 물류 배송 금액 합계를 조회하여 배송정책번호 별로 Map 생성
	 * 7. 상품번호별 배송정책 리스트 조회후 배송정책 일련번호 기준으로 Map 생성
	 * 
	 * <<배송정책 일련번호 별로 Loop>>
	 * 8. 판매자 귀책사유 체크
	 * 
	 * 9. 고객 귀책 사유
	 * 9-1. 원 주문 배송비가 무료인 경우 : 반품지를 하나로 합칠 수 있기 때문에 배송지와는 상관없이 배송정책 번호 기준으로만 체크한다
	 * 9-1-1. 원 주문 상품 리스트에 연결된 부분취소가 있는 경우 부분취소로 인해 주문 배송비를 부과했는지 확인
	 * 9-1-2. 9-1-1 에서 배송비를 부과한 이력이 없는 경우
	 * 		  	 원 주문 상품에 연결된 고객 귀책 사유로 반품으로 인한 배송비가 있는 경우 실제 배송비가 반품 배송비보다 크면 주문 배송비를 지급 한 것으로 판단
	 * 9-1-3. 9-1-2 에서 배송비를 부과한 이력이 없는 경우
	 *          원 주문 상품에 연결된 교환 상품이 있고 해당 교환 상품이 고객 귀책으로 반품으로 접수되어 배송비가 있는 경우 실제 배송비가 반품 배송비보다 크면 주문 배송비를 지급 한 것으로 판단
	 * 
	 * 9-1-4. 기존에 주문 배송비를 지급 한 이력이 없다면
	 * 9-1-4-1. 원 주문 - (부분 취소 + 기존 반품 + 금번 반품) 으로 총 주문 금액 계산
	 * 9-1-4-2. 9-1-4-1의 총 주문 금액이 무료 배송 기준을 충족하면 반품 배송비만 부과
	 * 9-1-4-3. 9-1-4-1의 총 주문 금액이 무료 배송 기준을 충족하지 않으면 주문 배송비 + 반품 배송비 부과
	 * 9-1-5. 기존에 주문 배송비를 지급 한 이력이 있으면 반품 배송비만 부과
	 * 
	 * 9-2. 원 주문 배송비가 유료인 경우
	 * 9-2-1. 반품 배송비만 부과 
	 * 
	 * 10. 판매자 귀책 사유
	 * 10-1. 전체 반품인 경우 주문 배송비가 존재하고 전체 반품인 경우 최초 주문 배송비를 환불 해 준다.
	 * 
	 * @param reqLgsDlvspExtendList
	 * 	@param callTp
	 * @param ordTp
	 * @param adminTpCd
	 * @return List<LgsDlvspExtend>
	 * @throws Exception
	 */
	public List<LgsDlvspExtend> getRtClmLgsDlvList(List<LgsDlvspExtend> reqLgsDlvspExtendList, String callTp, String ordTp, String adminTpCd) throws Exception {
		
		// 1-1. 호출구분, 주문유형, 로그인유형을 기준으로 무료 배송 여부 확인
		if (isFreeDlvAmt(reqLgsDlvspExtendList, callTp, ordTp, adminTpCd, null, null)) {
			return reqLgsDlvspExtendList;
		}
		
		// 1-2. 전체 반품여부 체크
		boolean isAllRt = isAllRtClm(reqLgsDlvspExtendList);
		boolean iscancel = false;
		
		List<LgsDlvspExtend> retLgsDlvspExtendList = new ArrayList<LgsDlvspExtend>();
		
		// <반품 수거지 별로 Loop>
		for (LgsDlvspExtend reqLgsDlvsp : reqLgsDlvspExtendList) {

			// 반품 물류 배송 List
			List<LgsDlvFoExtend> clmLgsDlvList = new ArrayList<LgsDlvFoExtend>();
			// 3. 반품 요청 상품 목록 Map
			Map<Integer, ClmWrhsGodExtend> reqClmWrhsGodMap =  Maps.newHashMap();
			
			// 4. 반품 진행을 할 원 주문 데이터 조회
			// 클레임 대상 원 주문 상품 목록 조회 조건 셋팅 
			DeliveryRuleByGoodDTO deliveryRuleByGoodDTO = new DeliveryRuleByGoodDTO();
			// 반품 요청 상품 순번
			List<Integer> ordGodTurnList = new ArrayList<Integer>();
			
			if (CollectionUtils.isEmpty(reqLgsDlvsp.getClmWrhsGodList())) continue;
			
			for (ClmWrhsGodExtend reqClmWrhsGod : reqLgsDlvsp.getClmWrhsGodList()) {
				// 세트나 패키지인경우엔 skip			
				if (StringService.equalsIn(reqClmWrhsGod.getPckageGodTpCd(), "SET_GOD", "PCKAGE_GOD")) continue;
				
				// 주문번호
				if (deliveryRuleByGoodDTO.getOrdNo() == null) {
					deliveryRuleByGoodDTO.setOrdNo(reqClmWrhsGod.getOrdNo());
				}
				
				ordGodTurnList.add(reqClmWrhsGod.getOrdGodTurn());
				
				// 반품 요청 상품 순번을 Key로 객체 저장
				reqClmWrhsGodMap.put(reqClmWrhsGod.getOrdGodTurn(), reqClmWrhsGod);
			}
		
			if (ordGodTurnList.size() > 0) {
				deliveryRuleByGoodDTO.setOrdGodTurnList(ordGodTurnList);
			}
			// 교환으로 출고된 상품("출고 상품 연결")은 제외 
			deliveryRuleByGoodDTO.setNotExistsGodCnncTpCd("DLIVY_GOD_CNNC");
			
			// 4-1. 반품 요청을 한 주문 상품이 교환을 통해 변경된 상품이 아니면 해당 주문 상품 리스트 조회 (반품 요청 주문 상품 순번 = 원 주문 상품 순번)
			List<OrdGodExtends> reqRtOrdGodList = deliverySelectRepository.selectOriOrdGodListByClm(deliveryRuleByGoodDTO);
			
			// 원 주문 상품 순번 - 반품 요청 상품 순번 연결 Map
			Map<Integer, Integer> oriToReqRtOrdGodTurnMap = new HashMap<Integer, Integer>();
			
			// 반품 요청 주문 상품 순번 = 원 주문 상품 순번
			for (OrdGodExtends reqRtOrdGod : reqRtOrdGodList) {
				oriToReqRtOrdGodTurnMap.put(reqRtOrdGod.getOrdGodTurn(), reqRtOrdGod.getOrdGodTurn());
				
				ordGodTurnList.remove(reqRtOrdGod.getOrdGodTurn());
			}
			
			int exrtsize=ordGodTurnList.size();//K2 교환후 반품접수시 문제건
	    	if (ordGodTurnList != null && ordGodTurnList.size() > 0) {
				// 교환 상품의 경우 원주문 상품 조회 
				List<Integer> exchgOriOrdGodTurnList = new ArrayList<Integer>();
				
				// 4-2. 반품 요청을 한 주문 상품이 교환을 통해 변경된 상품이면 최초의 원 주문 상품 순번을 조회한다. (반품 요청 주문 상품 순번 <> 원 주문 상품 순번)
				for (Integer reqRtOrdGodTurn : ordGodTurnList) {
					// 반품 요청 상품 순번
					deliveryRuleByGoodDTO.setOrdGodTurn(reqRtOrdGodTurn);
					// 교환 상품의 경우 원주문 상품 조회
					ClmWrhsGodExtend exchgOriOrdGod = deliverySelectRepository.selectExchgOriOrdGod(deliveryRuleByGoodDTO);
					// 조회할 원 주문 상품 순번 연결
					//exchgOriOrdGodTurnList.add(exchgOriOrdGod.getOriOrdGodTurn());
					 exchgOriOrdGodTurnList.add(exchgOriOrdGod.getOrdGodTurn());                   //교환후반품접수시 배송비 이상으로 상단원주문 넣는것을 교환출고순번으로변경
					// 원 주문 상품 순번에 반품 요청 주문 상품  연결
					 oriToReqRtOrdGodTurnMap.put(exchgOriOrdGod.getOrdGodTurn(), reqRtOrdGodTurn); //교환후반품접수시 배송비 이상으로 상단원주문 넣는것을 교환출고순번으로변경
				}
				deliveryRuleByGoodDTO.setOrdGodTurnList(exchgOriOrdGodTurnList);
				
				// 4-3. 4-2가 존재하면 (4-1) + (4-2) 을 합쳐 원 주문 데이터를 만든다.
				// 클레임 제약 조건 제거
				deliveryRuleByGoodDTO.setNotExistsGodCnncTpCd(null);
				List<OrdGodExtends> exchgOriOrdGodListByClm = deliverySelectRepository.selectOriOrdGodListByClm(deliveryRuleByGoodDTO);
				
				// 4-4. 교환의 원 주문 상품을 최조 주문 상품 리스트에 추가한다.
				if (exchgOriOrdGodListByClm != null && exchgOriOrdGodListByClm.size() > 0) {
					for (OrdGodExtends exchgOriOrdGod : exchgOriOrdGodListByClm) {
						// 교환에 대한 원 주문의 주문 상품 순번이 이미 있는 경우엔 skip : 이런일은 발생할까?
						/*
						for (OrdGodExtends oriOrdGod : oriOrdGodListByClm) {
							if (oriOrdGod.getOrdGodTurn().equals(exchgOriOrdGod.getOrdGodTurn())) {
								continue loops;
							}
						}
						*/
						
						// 반품 요청 주문 상품 리스트에 추가 
						reqRtOrdGodList.add(exchgOriOrdGod);
					}
				}
			         			
				
			}
			
			// 5. 배송지 + 배송 정책 번호를 기준으로 원 주문 상품 전체 조회
			ordGodTurnList.clear();
			for (OrdGodExtends reqRtOrdGod : reqRtOrdGodList) {
				ordGodTurnList.add(reqRtOrdGod.getOrdGodTurn());
			}
			deliveryRuleByGoodDTO.setSelectReqRtByLgsDlv(true);
			deliveryRuleByGoodDTO.setOrdGodTurnList(ordGodTurnList);
			List<OrdGodExtends> oriOrdGodListByClm= new ArrayList<OrdGodExtends>();
			List<OrdGodExtends> oriOrdGodListByClmAfter= new ArrayList<OrdGodExtends>();
			ListMultimap<Long, OrdGodExtends> oriOrdGodDvlListMapAfter = null;
			
			if (exrtsize > 0) { //교환상품 반품시존재하는경우
				oriOrdGodListByClm = deliverySelectRepository.selectOriOrdGodListByClmEx(deliveryRuleByGoodDTO); // 교환상품 반품시 원주문 배송비정책 변경 쿼리
				oriOrdGodListByClmAfter = deliverySelectRepository.selectOriOrdGodListByClmAfter(deliveryRuleByGoodDTO); // 교환상품 반품시 원주문 배송비정책 전체
				
				oriOrdGodDvlListMapAfter = Multimaps.index(
					oriOrdGodListByClmAfter, 
					new Function<OrdGodExtends, Long>() {
						@Override
						public Long apply(OrdGodExtends ordGod) {
			                return ordGod.getDmstcDlvCstPlcSn();
			            }
					}
				);
			}
		    else{
			    oriOrdGodListByClm = deliverySelectRepository.selectOriOrdGodListByClm(deliveryRuleByGoodDTO);
		    }
			
			// 5-1. 반품 요청 원 주문 상품을 배송정책 번호를 기준으로 ListMap 생성
			ListMultimap<Long, OrdGodExtends> oriOrdGodDvlListMap = Multimaps.index(
				oriOrdGodListByClm, 
				new Function<OrdGodExtends, Long>() {
					@Override
					public Long apply(OrdGodExtends ordGod) {
		                return ordGod.getDmstcDlvCstPlcSn();
		            }
				}
			);
			if(oriOrdGodDvlListMapAfter==null){
				// 5-1. 반품 요청 원 주문 상품을 배송정책 번호를 기준으로 ListMap 생성
				oriOrdGodDvlListMapAfter = Multimaps.index(
						oriOrdGodListByClm, 
					new Function<OrdGodExtends, Long>() {
						@Override
						public Long apply(OrdGodExtends ordGod) {
			                return ordGod.getDmstcDlvCstPlcSn();
			            }
					}
				);
			}

			
			
			
			// 원 주문 배송 수거지 순번	
			Set<Integer> dlvPcupspTurnSet = new HashSet<Integer>();
			for (OrdGodExtends ordGodExtends : oriOrdGodListByClm) {
				dlvPcupspTurnSet.add(ordGodExtends.getDlvPcupspTurn());
			}
			deliveryRuleByGoodDTO.setDlvPcupspTurnList(new ArrayList<Integer>(dlvPcupspTurnSet));
			
			// 6. 주문 + 배송정책 번호에 해당하는 물류 배송 금액 합계를 조회하여 배송정책번호 별로 Map 생성
			List<LgsDlv> oriOrdLgsDlvCstList = deliverySelectRepository.selectLgsDlvCstGroupByDmstcDlvCstPlcSn(deliveryRuleByGoodDTO);
			Map<Long, LgsDlv> oriOrdLgsDlvCstMap =  Maps.newHashMap();
			if (oriOrdLgsDlvCstList != null && oriOrdLgsDlvCstList.size() > 0) {
				// 배송정책 일련번호 기준으로 Map 생성
				oriOrdLgsDlvCstMap = Maps.uniqueIndex(
					oriOrdLgsDlvCstList, 
					new Function<LgsDlv, Long>() {
						@Override
						public Long apply(LgsDlv lgsDlv) {
			                return lgsDlv.getDmstcDlvCstPlcSn();
			            }
					}
				);
			}
			
			// 7. 상품번호별 배송정책 리스트 조회후 배송정책 일련번호 기준으로 Map 생성
			Set<String> godList = new HashSet<String>();
			for (OrdGod ordGod : oriOrdGodListByClm) {
				godList.add(ordGod.getGodNo());
			}
			// 배송정책 일련번호 기준으로 Map 생성
			Map<Long, ComDmstcDlvCstPlcExtend> deliveryRuleMap = this.getDeliveryRuleMapByGodNoList(new ArrayList<String>(godList), false);			
			
			// <배송정책 일련번호 별로 Loop>
			for(Long dmstcDlvCstPlcSn : oriOrdGodDvlListMap.keySet()) {
				LgsDlv oriOrdLgsDlv = oriOrdLgsDlvCstMap.get(dmstcDlvCstPlcSn);
				
				// 배송정책 일련번호에 해당하는 배송정책 정보를 가져온다 
				ComDmstcDlvCstPlcExtend comDmstcDlvCstPlc = deliveryRuleMap.get(dmstcDlvCstPlcSn);
				
				// 배송비 정보를 설정할 [배송물류 Entity]
				LgsDlvFoExtend lgsDlv = new LgsDlvFoExtend();
				
				// 주문 번호
				lgsDlv.setOrdNo(deliveryRuleByGoodDTO.getOrdNo());
				
				// 국내 배송비 정책 일련번호
				lgsDlv.setDmstcDlvCstPlcSn(dmstcDlvCstPlcSn);				
				
				// 배송비 부담 주체 코드 : 배송정책 일련번호 기준으로 조회
				lgsDlv.setDlvCstBndMbdCd(oriOrdGodDvlListMap.get(dmstcDlvCstPlcSn).get(0).getPartmalSectCd().equals("MCOM") ? "MCOM" : "COM");
				
				// 통화 코드
				lgsDlv.setCrncyCd("KRW");
				
				// 업체 명
				lgsDlv.setComNm(comDmstcDlvCstPlc.getComNm());
				
				// 배송 업체 코드
				lgsDlv.setDlvComCd(comDmstcDlvCstPlc.getDlvComCd());
				
				/*
				 *  8. 판매자 귀책사유 체크
				 *  하나의 배송정책번호(업체) 내에 판매자귀책이 하나라도 있으면 해당 배송비 정책은 판매자 귀책으로 한다. 
				 */
				boolean isSlrImpt = false;
				ordGodTurnList.clear();
				
				for (OrdGodExtends oriOrdGod : oriOrdGodDvlListMap.get(dmstcDlvCstPlcSn)) {
					// 조회에 사용할 원 주문 상품 순번 List 생성
					ordGodTurnList.add(oriOrdGod.getOrdGodTurn());
					
					//K2 2016.02.29 원 주문 상품이 반품 요청과 매핑이 걸려있지 않는 경우엔 skip
					if (!reqClmWrhsGodMap.containsKey(oriOrdGod.getOrdGodTurn())) {
						continue;
					}  
					
					Integer reqRtOrdGodTurn = oriToReqRtOrdGodTurnMap.get(oriOrdGod.getOrdGodTurn());
					
					/*
					 * ㅁ. 클레임 사유 : CLM_RSN
					   >. 고객변심취소 : CSTMR_CHGMIND_CNCL
					   >. 사이즈교환 : SIZE_EXCHG
					   >. 품절취소 : SLDOUT_CNCL
					   >. 배송지연취소 : DLV_DELAY_CNCL
					   >. 이중주문취소 : DPLC_ORD_CNCL
					   >. 시스템오류취소 : SYS_ERR_CNCL
					   >. 누락배송 : OVSITE_DLV
					   >. 택배분실 : HDRY_LOST
					   >. 옵션변경 : OPT_CHG
					   >. 상품불량 : GOD_BADN
					   >. 상품파손 : GOD_DMG
					   >. 상품 오염 : GOD_STAN
					   >. 기타 상품정보상이 : ETC_GOD_INFO_GAP
					   >. 사이즈 정보 상이 : SIZE_INFO_DFFRNC
					   >. 색상 정보 상이 : COLOR_INFO_DFFRNC
					   >. 오배송 : WN_DLV
					   >. 기타 : ETC
					 */
					// 고객 귀책사유(단순변심, 옵션변경, 수량변경)가 아닌 경우 -> 판매자 귀책 사유  
					if (!StringService.equalsIn(reqClmWrhsGodMap.get(reqRtOrdGodTurn).getClmResnCd(), "CSTMR_CHGMIND_CNCL", "SIZE_EXCHG", "DPLC_ORD_CNCL", "OPT_CHG", "CSTMR_CHGMIND")) {
						isSlrImpt = true;
						break;
					}
				}
				deliveryRuleByGoodDTO.setOrdGodTurnList(ordGodTurnList);
				
				// 9. 고객 귀책 사유
				if (!isSlrImpt) {

					double dmstcDlvCstExmStdrAmt=0;
					double dmstcDlvCst=0;

					// 국내 배송비 면제 기준 금액 
					dmstcDlvCstExmStdrAmt = comDmstcDlvCstPlc.getDmstcDlvCstExmStdrAmt().doubleValue();
						
					// 국내 배송비 설정
					dmstcDlvCst = comDmstcDlvCstPlc.getDmstcDlvCst().doubleValue();
					
					// 구매자 귀책 반품 배송비	
					double buyerImptRtgodDlvCst = comDmstcDlvCstPlc.getBuyerImptRtgodDlvCst().doubleValue();
					
					// 9-1. 원 주문 배송비가 무료인 경우 : 반품지를 하나로 합칠 수 있기 때문에 배송지와는 상관없이 배송정책 번호 기준으로만 체크한다
					if (oriOrdLgsDlv.getRealityDlvCst().doubleValue() == 0) {

						String beforePay = "N";

						// 자사인 경우에 원 주문 배송비 체크
						if ("MCOM".equals(lgsDlv.getDlvCstBndMbdCd()) == false) {
							beforePay = "Y";
						}
						else {
							
							// 9-1-1. 원 주문 상품 리스트에 연결된 부분취소가 있는 경우 부분취소로 인해 주문
							// 배송비를 부과했는지 확인

							int cpnCnt = 0;
							boolean cpnFlag = false;

							List<OrdGod> remainOrdGodAplPrmList = orderSelectRepository.selectRemainOrdGodAplPrmList(lgsDlv.getOrdNo(),oriOrdLgsDlv.getDlvPcupspTurn());
							// 해당주문에 걸려있는 주문상품적용 프로모션에 상품배송비쿠폰의 잔여 주문수량의sum 을
							// 구한다.
							for (OrdGod dto : remainOrdGodAplPrmList) {
								cpnCnt += dto.getOrdQty().intValue();
								for (ClmWrhsGodExtend reqClmWrhsGod : reqLgsDlvsp.getClmWrhsGodList()) {
									if (reqClmWrhsGod.getOrdGodTurn() == dto.getOrdGodTurn()) {
										cpnCnt -= reqClmWrhsGod.getClmQty();
									}
								}
							}
							if (cpnCnt == 0) {
								cpnFlag = true;
							}

							if (cpnFlag) {// 상품별 배송비 상품이전부 반품되면
								beforePay = deliverySelectRepository.beforePayOrdDlvAmtByPartCnclYN(deliveryRuleByGoodDTO);
							}
							else {
								beforePay = "Y";
							}

							Ord ord = new Ord();
							ord.setOrdNo(lgsDlv.getOrdNo());
							
							ord = ordRepository.selectOrd(ord);
							
							if (OrderEnum.ORD_TP_CD_SHOP_PKUP_ORD.toString().equals(ord.getOrdTpCd())) {	// 매장수령주문의 경우 초도배송비가 존재하지 않으므로 부과한 것으로 가정
								beforePay = "Y";
							}
						}
						
						if ("N".equals(beforePay)) {
							// 9-1-2. 9-1-1 에서 배송비를 부과한 이력이 없는 경우 
							// 		  	 원 주문 상품에 연결된 고객 귀책 사유로 반품으로 인한 배송비가 있는 경우 반품 배송비가 정책배송비보다 크면 주문 배송비를 지급 한 것으로 판단
							beforePay = deliverySelectRepository.beforePayOrdDlvAmtByRtGodYN(deliveryRuleByGoodDTO);
							
							if ("N".equals(beforePay)) {
								// 9-1-3. 9-1-2 에서 배송비를 부과한 이력이 없는 경우
								//          원 주문 상품에 연결된 교환 상품이 있고 해당 교환 상품이 고객 귀책으로 반품으로 접수되어 배송비가 있는 경우 반품 배송비가 정책배송비보다 크면 주문 배송비를 지급 한 것으로 판단
								beforePay = deliverySelectRepository.beforePayOrdDlvAmtByExchgToRtGodYN(deliveryRuleByGoodDTO);
							} 
						}
						
						// 9-1-4. 기존에 주문 배송비를 지급 한 이력이 없다면
						if ("N".equals(beforePay)) {
							 double sumSaleAmt=remainSaleAmt(reqClmWrhsGodMap, deliveryRuleByGoodDTO, oriToReqRtOrdGodTurnMap, oriOrdGodDvlListMapAfter, dmstcDlvCstPlcSn,
							         lgsDlv, dmstcDlvCstExmStdrAmt, dmstcDlvCst, buyerImptRtgodDlvCst);
							 
							// 반품 배송 정보 설정
							// 9-1-4-2. 9-1-4-1의 총 주문 금액이 무료 배송 기준을 충족하면 반품 배송비만 부과 
							if (sumSaleAmt > dmstcDlvCstExmStdrAmt) {
								// 기준 통화 금액
								lgsDlv.setStdrCrncyAmt(new BigDecimal(buyerImptRtgodDlvCst));
								// 결제 환율 통화 금액
								lgsDlv.setPayExchgRtCrncyAmt(new BigDecimal(buyerImptRtgodDlvCst));
								// 실제 배송비
								lgsDlv.setRealityDlvCst(new BigDecimal(buyerImptRtgodDlvCst));
								// 정책 배송비	 
								lgsDlv.setPlcDlvCst(new BigDecimal(buyerImptRtgodDlvCst));
								// 반품 배송비
								lgsDlv.setRtgodDlvCst(new BigDecimal(buyerImptRtgodDlvCst));
							} 
							
							/*
							 * 9-1-4-3. 9-1-4-1의 총 주문 금액이 무료 배송 기준을 충족하지 않으면 주문 배송비 + 반품 배송비 부과
							 * => 정책배송비엔 반품배송비만 입력
							 * => 반품배송비엔 주문배송비 + 반품 배송비 입력
							 */
							else {
								// 기준 통화 금액
								lgsDlv.setStdrCrncyAmt(new BigDecimal(dmstcDlvCst + buyerImptRtgodDlvCst));
								// 결제 환율 통화 금액
								lgsDlv.setPayExchgRtCrncyAmt(new BigDecimal(dmstcDlvCst + buyerImptRtgodDlvCst));
								// 실제 배송비
								lgsDlv.setRealityDlvCst(new BigDecimal(dmstcDlvCst + buyerImptRtgodDlvCst));
								// 정책 배송비	 
								lgsDlv.setPlcDlvCst(new BigDecimal(buyerImptRtgodDlvCst));
								// 반품 배송비
								lgsDlv.setRtgodDlvCst(new BigDecimal(dmstcDlvCst +buyerImptRtgodDlvCst));
							}
							
						}
						// 9-1-5. 기존에 주문 배송비를 지급 한 이력이 있으면 반품 배송비만 부과
						else {
							// 기준 통화 금액
							lgsDlv.setStdrCrncyAmt(new BigDecimal(buyerImptRtgodDlvCst));
							// 결제 환율 통화 금액
							lgsDlv.setPayExchgRtCrncyAmt(new BigDecimal(buyerImptRtgodDlvCst));
							// 실제 배송비
							lgsDlv.setRealityDlvCst(new BigDecimal(buyerImptRtgodDlvCst));
							// 정책 배송비	 
							lgsDlv.setPlcDlvCst(new BigDecimal(buyerImptRtgodDlvCst));
							// 반품 배송비
							lgsDlv.setRtgodDlvCst(new BigDecimal(buyerImptRtgodDlvCst));
						}		
					}
					// 9-2. 원 주문 배송비가 유료인 경우
					else {
						// 9-2-1. 반품 배송비만 부과 
						// 기준 통화 금액
						lgsDlv.setStdrCrncyAmt(new BigDecimal(buyerImptRtgodDlvCst));
						// 결제 환율 통화 금액
						lgsDlv.setPayExchgRtCrncyAmt(new BigDecimal(buyerImptRtgodDlvCst));
						// 실제 배송비
						lgsDlv.setRealityDlvCst(new BigDecimal(buyerImptRtgodDlvCst));
						// 정책 배송비	 
						lgsDlv.setPlcDlvCst(new BigDecimal(buyerImptRtgodDlvCst));
						// 반품 배송비
						lgsDlv.setRtgodDlvCst(new BigDecimal(buyerImptRtgodDlvCst));
					}
					
				}
				// 10. 판매자 귀책 사유
				else {
					// 기준 통화 금액
					lgsDlv.setStdrCrncyAmt(comDmstcDlvCstPlc.getSlrImptRtgodDlvCst());
					// 결제 환율 통화 금액
					lgsDlv.setPayExchgRtCrncyAmt(comDmstcDlvCstPlc.getSlrImptRtgodDlvCst());
					// 실제 배송비
					lgsDlv.setRealityDlvCst(comDmstcDlvCstPlc.getSlrImptRtgodDlvCst());
					// 정책 배송비	 
					lgsDlv.setPlcDlvCst(comDmstcDlvCstPlc.getSlrImptRtgodDlvCst());
					// 반품 배송비
					lgsDlv.setRtgodDlvCst(comDmstcDlvCstPlc.getSlrImptRtgodDlvCst());
					// 취소 배송비
					lgsDlv.setCnclDlvCst(new BigDecimal(0));
					// 교환 배송비	 
					lgsDlv.setExchgDlvCst(new BigDecimal(0));
					
					//실제반품하고 남은금액
                    double reaminSaleAmt =remainSaleAmt(reqClmWrhsGodMap, deliveryRuleByGoodDTO, oriToReqRtOrdGodTurnMap, oriOrdGodDvlListMapAfter, dmstcDlvCstPlcSn, lgsDlv, 0, 0, 0);
                    
					// 전체 반품인 경우
					//if (isAllRt) {
                    if (reaminSaleAmt ==0) {
						/*
						 * 10-1. 전체 반품인 경우 주문 배송비가 존재하고 전체 반품인 경우 최초 주문 배송비를 환불 해 준다.
						 * 
						 * 1. 주문 배송비 체크 
						 * 		최조 주문시 배송비 발생
						 *		무료 배송비 주문 후 주문 취소로 인해 배송비 발생
						 * 		무료 배송비 주문 후 부분 취소(고객 귀책 사유)로 주문 배송비 발생
						 * 
						 * 2. 주문 배송비가 존재하는 경우 
						 * 		현재 반품 처리된 상품 수량 체크
						 * 		=> 물류 출고지시 상품.출고지시 수량 - 출고지시 취소 수량 (배송 상태 코드 = 출고완료 : DLIVY_COMPT, 배송완료 : DLV_COMPT)
						 * 			- 물류 회수지시 상품<키:출고지시 상품 번호>.회수지시 수량-회수지시 철회 수량(회수 상태 코드 = 입고 완료 : WRHS_COMPT, 회수 완료 : RTRVL_COMPT)
						 * 
						 * 4. 전체 반품여부 체크 
						 * 		남은 수량 - 금번 접수된 상품 수량 전체가 0인경우 
						 * 		=> 주문 배송비 환불 처리
						 */
	
						// 주문 배송비 발생여부 체크
						String beforePay = "Y";
						double ordDlvAmt = oriOrdLgsDlv.getRealityDlvCst().doubleValue();
						
						if (ordDlvAmt == 0) {
							// 무료 배송비 주문 후 주문 취소로 인해 배송비 발생
							ordDlvAmt = oriOrdLgsDlv.getCnclDlvCst().doubleValue();
	
							if (ordDlvAmt == 0) {
								//  원 주문 상품에 연결된 고객 귀책 사유로 반품으로 인한 배송비가 있는 경우 실제 배송비가 반품 배송비보다 크면 주문 배송비를 지급 한 것으로 판단
								beforePay = deliverySelectRepository.beforePayOrdDlvAmtByRtGodYN(deliveryRuleByGoodDTO);
								
								if ("N".equals(beforePay)) {
									// 반품으로 주문 배송비를 환불한 이력이 없는 경우
									// 		원 주문 상품에 연결된 교환 상품이 있고 해당 교환 상품이 고객 귀책으로 반품으로 접수되어 배송비가 있는 경우 실제 배송비가 반품 배송비보다 크면 주문 배송비를 지급 한 것으로 판단
									beforePay = deliverySelectRepository.beforePayOrdDlvAmtByExchgToRtGodYN(deliveryRuleByGoodDTO);
								} 
							}						
						}
						
						// 주문배송비가 존재하고 기존에 주문 배송비를 환불 한 이력이 없다면 최초 주문 배송비 환불
						if (ordDlvAmt > 0 && "Y".equals(beforePay)) {
							// 취소 배송비
							lgsDlv.setCnclDlvCst(new BigDecimal(ordDlvAmt));
							iscancel=true;
						} 
					}
				}
				
				
				if ("HDRY".equals(reqLgsDlvsp.getDlvMnCd())) {
				     for (ClmWrhsGodExtend reqClmWrhsGod : reqLgsDlvsp.getClmWrhsGodList()) {
						if (StringService.equalsIn(reqClmWrhsGod.getPckageGodTpCd(), "SET_GOD", "PCKAGE_GOD")) continue;
			  
						if(isSlrImpt==true && isAllRt==true && iscancel ==true ) { 
							//판매자귀책 이며 전체반품이며 환불금액이 없는경우
						  	lgsDlv = getFreeLgsDlvCancel(lgsDlv.getCnclDlvCst(),reqClmWrhsGod.getOrdNo(), reqClmWrhsGod.getDmstcDlvCstPlcSn(), reqClmWrhsGod.getPartmalSectCd(), deliveryRuleMap.get(reqClmWrhsGod.getDmstcDlvCstPlcSn()).getComNm(), deliveryRuleMap.get(reqClmWrhsGod.getDmstcDlvCstPlcSn()).getDlvComCd());
						}
						else{
						
		    				lgsDlv = getFreeLgsDlv(reqClmWrhsGod.getOrdNo(), reqClmWrhsGod.getDmstcDlvCstPlcSn(), reqClmWrhsGod.getPartmalSectCd(), deliveryRuleMap.get(reqClmWrhsGod.getDmstcDlvCstPlcSn()).getComNm(), deliveryRuleMap.get(reqClmWrhsGod.getDmstcDlvCstPlcSn()).getDlvComCd());
						}
					}
			    }
				
				
				clmLgsDlvList.add(lgsDlv);
			}
			
			reqLgsDlvsp.setLgsDlvList(clmLgsDlvList);
			
			retLgsDlvspExtendList.add(reqLgsDlvsp);
		}
		
		return retLgsDlvspExtendList;
	}
	
	/**
	 * 교환 배송 정보 조회
	 * 
	 * @param reqLgsDlvspExtendList
	 * @param callTp
	 * @param ordTp
	 * @param adminTpCd
	 * @param clmTpCd
	 * @return List<LgsDlvspExtend>
	 * @throws Exception
	 */
	public List<LgsDlvspExtend> getExchgClmLgsDlvList(List<LgsDlvspExtend> reqLgsDlvspExtendList, String callTp, String ordTp, String adminTpCd, String clmTpCd, String role) throws Exception {
					
		// 호출구분, 주문유형, 로그인유형, 클레임 유형을 기준으로 무료 배송 여부 확인
		if (isFreeDlvAmt(reqLgsDlvspExtendList, callTp, ordTp, adminTpCd, clmTpCd, role)) {
			return reqLgsDlvspExtendList;
		}
		
		List<LgsDlvspExtend> retLgsDlvspExtendList = new ArrayList<LgsDlvspExtend>();
		
		// 상품번호별 배송정책 리스트 조회
		Set<String> godList = new HashSet<String>();
		for (LgsDlvspExtend reqLgsDlvsp : reqLgsDlvspExtendList) {
			
			if (CollectionUtils.isEmpty(reqLgsDlvsp.getClmWrhsGodList())) continue;
			
			for (ClmWrhsGodExtend reqClmWrhsGod : reqLgsDlvsp.getClmWrhsGodList()) {
				// 세트나 패키지인경우엔 skip			
				if (StringService.equalsIn(reqClmWrhsGod.getPckageGodTpCd(), "SET_GOD", "PCKAGE_GOD")) continue;
				
				godList.add(reqClmWrhsGod.getGodNo());
			}
		}

		// 배송정책 일련번호 기준으로 Map 생성
		Map<Long, ComDmstcDlvCstPlcExtend> deliveryRuleMap = null;
		deliveryRuleMap = this.getDeliveryRuleMapByGodNoList(new ArrayList<String>(godList), false);
		
		// 교환 수거지 별로 Loop
		for (LgsDlvspExtend reqLgsDlvsp : reqLgsDlvspExtendList) {
				
			// 국내 배송비 정책 일련번호 기준으로 하나씩 생성
			Set<Long> dmstcDlvCstPlcSnSet = new HashSet<Long>();
			
			// 교환 물류 배송 List
			List<LgsDlvFoExtend> clmLgsDlvList = new ArrayList<LgsDlvFoExtend>();
			
			// 수거정보에만 금액 적용
			if ("CLM_PCUPSP".equals(reqLgsDlvsp.getDlvPcupspSectCd())) {
				if (CollectionUtils.isEmpty(reqLgsDlvsp.getClmWrhsGodList())) continue;
				
				for (ClmWrhsGodExtend reqClmWrhsGod : reqLgsDlvsp.getClmWrhsGodList()) {
					// 세트나 패키지인경우엔 skip			
					if (StringService.equalsIn(reqClmWrhsGod.getPckageGodTpCd(), "SET_GOD", "PCKAGE_GOD")) continue;
					
					// 국내 배송비 정책 일련번호 기준으로 하나씩 생성
					if (dmstcDlvCstPlcSnSet.contains(reqClmWrhsGod.getDmstcDlvCstPlcSn())) continue;
					
					/*
					 *  판매자 귀책사유 체크
					 *  하나의 배송지 내에 판매자귀책이 하나라도 있으면 해당 배송비 정책은 판매자 귀책으로 한다. 
					 */
					boolean isSlrImpt = false;
					
					for (ClmWrhsGodExtend chkReqClmWrhsGod : reqLgsDlvsp.getClmWrhsGodList()) {
						/*
						 * ㅁ. 클레임 사유 : CLM_RSN
						   >. 고객변심취소 : CSTMR_CHGMIND_CNCL
						   >. 사이즈교환 : SIZE_EXCHG
						   >. 품절취소 : SLDOUT_CNCL
						   >. 배송지연취소 : DLV_DELAY_CNCL
						   >. 이중주문취소 : DPLC_ORD_CNCL
						   >. 시스템오류취소 : SYS_ERR_CNCL
						   >. 누락배송 : OVSITE_DLV
						   >. 택배분실 : HDRY_LOST
						   >. 옵션변경 : OPT_CHG
						   >. 상품불량 : GOD_BADN
						   >. 상품파손 : GOD_DMG
						   >. 상품 오염 : GOD_STAN
						   >. 기타 상품정보상이 : ETC_GOD_INFO_GAP
						   >. 사이즈 정보 상이 : SIZE_INFO_DFFRNC
						   >. 색상 정보 상이 : COLOR_INFO_DFFRNC
						   >. 오배송 : WN_DLV
						   >. 기타 : ETC
						 */
						// 고객 귀책 사유가 아닌 경우 -> 판매자 귀책 사유  
						if (reqClmWrhsGod.getDmstcDlvCstPlcSn().equals(chkReqClmWrhsGod.getDmstcDlvCstPlcSn()) 
								&& !StringService.equalsIn(chkReqClmWrhsGod.getClmResnCd(), "CSTMR_CHGMIND_CNCL", "SIZE_EXCHG", "DPLC_ORD_CNCL", "OPT_CHG", "CSTMR_CHGMIND" ,"COLOR_INFO_DFFRNC")) {
							isSlrImpt = true;
							break;
						}
					}
				

					dmstcDlvCstPlcSnSet.add(reqClmWrhsGod.getDmstcDlvCstPlcSn());

					// 배송정책 일련번호에 해당하는 배송정책 정보를 가져온다
					ComDmstcDlvCstPlcExtend comDmstcDlvCstPlc = deliveryRuleMap.get(reqClmWrhsGod.getDmstcDlvCstPlcSn());

					// 배송비 정보를 설정할 [배송물류 Entity]
					LgsDlvFoExtend lgsDlv = new LgsDlvFoExtend();
					// 주문 번호
					lgsDlv.setOrdNo(reqClmWrhsGod.getOrdNo());
					// 국내 배송비 정책 일련번호
					lgsDlv.setDmstcDlvCstPlcSn(reqClmWrhsGod.getDmstcDlvCstPlcSn());
					// 배송비 부담 주체 코드
					lgsDlv.setDlvCstBndMbdCd(reqClmWrhsGod.getPartmalSectCd().equals("MCOM") ? "MCOM" : "COM");
					// 통화 코드
					lgsDlv.setCrncyCd("KRW");
					// 업체 명
					lgsDlv.setComNm(comDmstcDlvCstPlc.getComNm());
					// 배송 업체 코드
					lgsDlv.setDlvComCd(comDmstcDlvCstPlc.getDlvComCd());

					// 고객 귀책 사유 
					if (!isSlrImpt) {
						/**
						 * 구매자 귀책 교환 배송비
						 */
						// 기준 통화 금액
						lgsDlv.setStdrCrncyAmt(comDmstcDlvCstPlc.getBuyerImptExchgDlvCst());
						// 결제 환율 통화 금액
						lgsDlv.setPayExchgRtCrncyAmt(comDmstcDlvCstPlc.getBuyerImptExchgDlvCst());
						// 실제 배송비
						lgsDlv.setRealityDlvCst(comDmstcDlvCstPlc.getBuyerImptExchgDlvCst());
						// 정책 배송비
						lgsDlv.setPlcDlvCst(comDmstcDlvCstPlc.getBuyerImptExchgDlvCst());
						// 교환 배송비
						lgsDlv.setExchgDlvCst(comDmstcDlvCstPlc.getBuyerImptExchgDlvCst());

					}
					// 판매자 귀책 사유
					else {
						/**
						 * 판매자 귀책 교환 배송비
						 */
						// 기준 통화 금액
						lgsDlv.setStdrCrncyAmt(comDmstcDlvCstPlc.getSlrImptExchgDlvCst());
						// 결제 환율 통화 금액
						lgsDlv.setPayExchgRtCrncyAmt(comDmstcDlvCstPlc.getSlrImptExchgDlvCst());
						// 실제 배송비
						lgsDlv.setRealityDlvCst(comDmstcDlvCstPlc.getSlrImptExchgDlvCst());
						// 정책 배송비
						lgsDlv.setPlcDlvCst(comDmstcDlvCstPlc.getSlrImptExchgDlvCst());
						// 교환 배송비
						lgsDlv.setExchgDlvCst(comDmstcDlvCstPlc.getSlrImptExchgDlvCst());
					}
					// 취소 배송비
					lgsDlv.setCnclDlvCst(new BigDecimal(0));
					// 반품 배송비
					lgsDlv.setRtgodDlvCst(new BigDecimal(0));

					clmLgsDlvList.add(lgsDlv);
				}

			} else {
				
				if (CollectionUtils.isEmpty(reqLgsDlvsp.getClmWrhsGodList())) continue;
				
				for (ClmWrhsGodExtend reqClmWrhsGod : reqLgsDlvsp.getClmWrhsGodList()) {
					// 국내 배송비 정책 일련번호 기준으로 하나씩 생성
					if (dmstcDlvCstPlcSnSet.contains(reqClmWrhsGod.getDmstcDlvCstPlcSn())) continue;
					
					dmstcDlvCstPlcSnSet.add(reqClmWrhsGod.getDmstcDlvCstPlcSn());
					
					// 무료 배송비 정보
					LgsDlvFoExtend lgsDlv = getFreeLgsDlv(reqClmWrhsGod.getOrdNo(), reqClmWrhsGod.getDmstcDlvCstPlcSn(), reqClmWrhsGod.getPartmalSectCd(), deliveryRuleMap.get(reqClmWrhsGod.getDmstcDlvCstPlcSn()).getComNm(), deliveryRuleMap.get(reqClmWrhsGod.getDmstcDlvCstPlcSn()).getDlvComCd());
					
					clmLgsDlvList.add(lgsDlv);
				}
			}
			
			reqLgsDlvsp.setLgsDlvList(clmLgsDlvList);
			
			retLgsDlvspExtendList.add(reqLgsDlvsp);
		}
		
		return retLgsDlvspExtendList;
	}
	
	/**
	 * 호출구분, 주문유형, 로그인유형을 기준으로 무료 배송 여부 확인
	 * 
	 * @param reqLgsDlvspExtendList
	 * @param callTp
	 * @param ordTp
	 * @param adminTpCd
	 * @param clmTpCd
	 * @return boolean
	 * @throws Exception 
	 */
	private boolean isFreeDlvAmt(List<LgsDlvspExtend> reqLgsDlvspExtendList, String callTp, String ordTp, String adminTpCd, String clmTpCd, String role) throws Exception {
		boolean isFree = false;
		
		/* 
		 * callTp 호출구분 매장반품 : DLV >> 매장픽업 주문 관리 화면에서 반품접수 매장반품이면 반품배송비 0원 처리		
		 * adminTpCd 로그인 유형 SHOP_MASTER 인 경우 이면 반품배송비 0원 처리
		 */
		// REPAIR : 무료수선서비스 매장방문일 경우 추가
		if ("DLV".equals(callTp) || "SHOP_MASTER".equals(adminTpCd)) {
			isFree = true;
			
			// 상품번호별 배송정책 리스트 조회
			Set<String> godList = new HashSet<String>();
			for (LgsDlvspExtend reqLgsDlvsp : reqLgsDlvspExtendList) {
				if (CollectionUtils.isEmpty(reqLgsDlvsp.getClmWrhsGodList())) continue;
				
				for (ClmWrhsGodExtend reqClmWrhsGod : reqLgsDlvsp.getClmWrhsGodList()) {
					// 세트나 패키지인경우엔 skip			
					if (StringService.equalsIn(reqClmWrhsGod.getPckageGodTpCd(), "SET_GOD", "PCKAGE_GOD")) continue;
					
					godList.add(reqClmWrhsGod.getGodNo());
				}
			}
			// 배송정책 일련번호 기준으로 Map 생성
			Map<Long, ComDmstcDlvCstPlcExtend> deliveryRuleMap = this.getDeliveryRuleMapByGodNoList(new ArrayList<String>(godList), false);
			
			// 교환 수거지 별로 Loop
			for (LgsDlvspExtend reqLgsDlvsp : reqLgsDlvspExtendList) {
				// 교환 물류 배송 List
				List<LgsDlvFoExtend> clmLgsDlvList = new ArrayList<LgsDlvFoExtend>();
				
				if (CollectionUtils.isEmpty(reqLgsDlvsp.getClmWrhsGodList())) continue;
				
				loops : for (ClmWrhsGodExtend reqClmWrhsGod : reqLgsDlvsp.getClmWrhsGodList()) {
					
					for(LgsDlvFoExtend lgsDlv : clmLgsDlvList) {
						if (reqClmWrhsGod.getDmstcDlvCstPlcSn().equals(lgsDlv.getDmstcDlvCstPlcSn())) {
							continue loops;
						}
					}
					
					// 무료 배송비 정보
					LgsDlvFoExtend lgsDlv = getFreeLgsDlv(reqClmWrhsGod.getOrdNo(), reqClmWrhsGod.getDmstcDlvCstPlcSn(), reqClmWrhsGod.getPartmalSectCd(), deliveryRuleMap.get(reqClmWrhsGod.getDmstcDlvCstPlcSn()).getComNm(), deliveryRuleMap.get(reqClmWrhsGod.getDmstcDlvCstPlcSn()).getDlvComCd());
					
					clmLgsDlvList.add(lgsDlv);
				}
				
				reqLgsDlvsp.setLgsDlvList(clmLgsDlvList);
			}
		}
		
		return isFree;
	}
	
	/**
	 * 무료 배송비 정보 개별로 설정
	 * 
	 * @param ordNo
	 * @param dmstcDlvCstPlcSn
	 * @param partmalSectCd
	 * @return LgsDlvFoExtend
	 */
	private LgsDlvFoExtend getFreeLgsDlv(String ordNo, Long dmstcDlvCstPlcSn, String partmalSectCd, String comNm, String dlvComCd) {
		// [배송물류 Entity]
		LgsDlvFoExtend lgsDlv = new LgsDlvFoExtend();
		// 주문 번호
		lgsDlv.setOrdNo(ordNo);
		// 국내 배송비 정책 일련번호
		lgsDlv.setDmstcDlvCstPlcSn(dmstcDlvCstPlcSn);
		// 배송비 부담 주체 코드
		lgsDlv.setDlvCstBndMbdCd(partmalSectCd.equals("MCOM") ? "MCOM" : "COM");
		// 통화 코드
		lgsDlv.setCrncyCd("KRW");
		// 업체 명
		lgsDlv.setComNm(comNm);
		// 배송 업체 코드
		lgsDlv.setDlvComCd(dlvComCd);
		// 기준 통화 금액
		lgsDlv.setStdrCrncyAmt(new BigDecimal(0));
		// 결제 환율 통화 금액
		lgsDlv.setPayExchgRtCrncyAmt(new BigDecimal(0));
		// 실제 배송비
		lgsDlv.setRealityDlvCst(new BigDecimal(0));
		// 정책 배송비	 
		lgsDlv.setPlcDlvCst(new BigDecimal(0));
		// 교환 배송비
		lgsDlv.setExchgDlvCst(new BigDecimal(0));
		// 취소 배송비
		lgsDlv.setCnclDlvCst(new BigDecimal(0));
		// 반품 배송비
		lgsDlv.setRtgodDlvCst(new BigDecimal(0));
		
		return lgsDlv;
	}
	

	private LgsDlvFoExtend getFreeLgsDlvCancel(BigDecimal dlv, String ordNo, Long dmstcDlvCstPlcSn, String partmalSectCd, String comNm, String dlvComCd) {
		// [배송물류 Entity]
		LgsDlvFoExtend lgsDlv = new LgsDlvFoExtend();
		// 주문 번호
		lgsDlv.setOrdNo(ordNo);
		// 국내 배송비 정책 일련번호
		lgsDlv.setDmstcDlvCstPlcSn(dmstcDlvCstPlcSn);
		// 배송비 부담 주체 코드
		lgsDlv.setDlvCstBndMbdCd(partmalSectCd.equals("MCOM") ? "MCOM" : "COM");
		// 통화 코드
		lgsDlv.setCrncyCd("KRW");
		// 업체 명
		lgsDlv.setComNm(comNm);
		// 배송 업체 코드
		lgsDlv.setDlvComCd(dlvComCd);
		// 기준 통화 금액
		lgsDlv.setStdrCrncyAmt(new BigDecimal(0));
		// 결제 환율 통화 금액
		lgsDlv.setPayExchgRtCrncyAmt(new BigDecimal(0));
		// 실제 배송비
		lgsDlv.setRealityDlvCst(new BigDecimal(0));
		// 정책 배송비	 
		lgsDlv.setPlcDlvCst(new BigDecimal(0));
		// 교환 배송비
		lgsDlv.setExchgDlvCst(new BigDecimal(0));
		// 취소 배송비
		lgsDlv.setCnclDlvCst(dlv);
		// 반품 배송비
		lgsDlv.setRtgodDlvCst(new BigDecimal(0));
			
		return lgsDlv;
	}
	
	/**
	 * 상품번호별 배송정책 리스트 조회
	 * 
	 * @param godList
	 * @return Map<Long, ComDmstcDlvCstPlcExtend>
	 * @throws Exception
	 */
	private Map<Long, ComDmstcDlvCstPlcExtend> getDeliveryRuleMapByGodNoList(List<String> godList, boolean isUseOrd) throws Exception {
		// 상품번호별 배송정책 리스트 조회
		DeliveryRuleByGoodDTO deliveryRuleByGoodDTO = new DeliveryRuleByGoodDTO();
		deliveryRuleByGoodDTO.setGodList((godList));
		deliveryRuleByGoodDTO.setUseOrd(isUseOrd);
		
		List<ComDmstcDlvCstPlcExtend> deliveryRuleByGodNoList = deliverySelectRepository.selectDeliveryRuleByGodNoList(deliveryRuleByGoodDTO);

		// 상품별 배송정책 정보가 없는 경우 종료
		if (deliveryRuleByGodNoList == null || deliveryRuleByGodNoList.size() == 0 ) {
			throw new Exception();
		}

		// 배송정책 일련번호 기준으로 Map 생성
		Map<Long, ComDmstcDlvCstPlcExtend> deliveryRuleMap = Maps.uniqueIndex(
			deliveryRuleByGodNoList,
			new Function<ComDmstcDlvCstPlcExtend, Long>() {
				@Override
				public Long apply(ComDmstcDlvCstPlcExtend comDmstcDlvCstPlc) {
	                return comDmstcDlvCstPlc.getDmstcDlvCstPlcSn();
	            }
			}
		);

		return deliveryRuleMap;
	}

	/**
	 * 수선일 경우 자사 기본 배송정책 조회
	 *
	 * @param godList
	 * @return Map<Long, ComDmstcDlvCstPlcExtend>
	 * @throws Exception
	 */
	public Map<Long, ComDmstcDlvCstPlcExtend> getDeliveryRuleMapForRepair (String clmTpCd) throws Exception {
		// 상품번호별 배송정책 리스트 조회
		DeliveryRuleByGoodDTO deliveryRuleByGoodDTO = new DeliveryRuleByGoodDTO();
		deliveryRuleByGoodDTO.setClmTpCd(clmTpCd);

		List<ComDmstcDlvCstPlcExtend> deliveryRuleByGodNoList = deliverySelectRepository.selectDeliveryRuleByGodNoList(deliveryRuleByGoodDTO);
		
		// 상품별 배송정책 정보가 없는 경우 종료
		if (deliveryRuleByGodNoList == null || deliveryRuleByGodNoList.size() == 0 ) {
			throw new Exception();
		}
		
		// 배송정책 일련번호 기준으로 Map 생성
		Map<Long, ComDmstcDlvCstPlcExtend> deliveryRuleMap = Maps.uniqueIndex(
			deliveryRuleByGodNoList, 
			new Function<ComDmstcDlvCstPlcExtend, Long>() {
				@Override
				public Long apply(ComDmstcDlvCstPlcExtend comDmstcDlvCstPlc) {
	                return comDmstcDlvCstPlc.getDmstcDlvCstPlcSn();
	            }
			}
		);
		
		return deliveryRuleMap;
	}
	
	/**
	 * 전체 반품여부 체크
	 * 
	 * @param reqLgsDlvspExtendList
	 * @return
	 * @throws Exception
	 */
	private boolean isAllRtClm(List<LgsDlvspExtend> reqLgsDlvspExtendList) throws Exception {
		boolean isAllRt = false;
		
		if (CollectionUtils.isEmpty(reqLgsDlvspExtendList)) return isAllRt;
		if (CollectionUtils.isEmpty(reqLgsDlvspExtendList.get(0).getClmWrhsGodList())) return isAllRt;
		
		DeliveryRuleByGoodDTO deliveryRuleByGoodDTO = new DeliveryRuleByGoodDTO();
		deliveryRuleByGoodDTO.setOrdNo(reqLgsDlvspExtendList.get(0).getClmWrhsGodList().get(0).getOrdNo());
		
		List<LgsDlivyDrctGod> availlableRtOrdQtyList = deliverySelectRepository.selectAvaillableRtOrdQtyList(deliveryRuleByGoodDTO);
		
		if (CollectionUtils.isEmpty(availlableRtOrdQtyList)) throw new Exception("반품 가능한 수량이 없습니다.");

		// 전체 반품여부 체크
		isAllRt = true;
		loop1 : for (LgsDlvspExtend reqLgsDlvsp : reqLgsDlvspExtendList) {
			loop2 : for (ClmWrhsGodExtend reqClmWrhsGod : reqLgsDlvsp.getClmWrhsGodList()) {
				// 세트나 패키지인경우엔 skip			
				if (StringService.equalsIn(reqClmWrhsGod.getPckageGodTpCd(), "SET_GOD", "PCKAGE_GOD")) continue loop2;
				
				for (LgsDlivyDrctGod lgsDlivyDrctGod : availlableRtOrdQtyList) {
					
					if (reqClmWrhsGod.getOrdGodTurn() == lgsDlivyDrctGod.getOrdGodTurn()) {
						
						if (reqClmWrhsGod.getClmQty().doubleValue() != lgsDlivyDrctGod.getDlivyDrctQty().doubleValue()) {
							isAllRt = false;
							break loop1;
						}
						
						continue loop2;
					}
				}
			}
		}
		
		return isAllRt;
	}
	
	private double remainSaleAmt(Map<Integer, ClmWrhsGodExtend> reqClmWrhsGodMap, DeliveryRuleByGoodDTO deliveryRuleByGoodDTO,
	        Map<Integer, Integer> oriToReqRtOrdGodTurnMap, ListMultimap<Long, OrdGodExtends> oriOrdGodDvlListMap, Long dmstcDlvCstPlcSn,
	        LgsDlvFoExtend lgsDlv, double dmstcDlvCstExmStdrAmt, double dmstcDlvCst, double buyerImptRtgodDlvCst) throws Exception {
		// 9-1-4-1. 원 주문 - (부분 취소 + 기존 반품 + 금번 반품) 으로 총 주문 금액 계산
		// 1) 원 주문에 해당하는 부분취소 내역 조회
		deliveryRuleByGoodDTO.setSelectExchgToRt(false);
		deliveryRuleByGoodDTO.setClmTpCd("PART_CNCL");	// 부분취소 : PART_CNCL
		List<ClmWrhsGodExtend> cnclWrhsGodList = deliverySelectRepository.selectClmWrhsGodByOrdNo(deliveryRuleByGoodDTO);
		
		ListMultimap<Integer, ClmWrhsGodExtend> cnclWrhsGodListMap =  ArrayListMultimap.create();
		if (cnclWrhsGodList != null && cnclWrhsGodList.size() > 0) {
			// 주문 상품 순번 기준으로 ListMap 생성 - 하나의 주문 상품 순번에 여러건의 주문을 했을 경우 여러번 부분 취소를 할 수 있다.
			cnclWrhsGodListMap = Multimaps.index(
				cnclWrhsGodList, 
				new Function<ClmWrhsGodExtend, Integer>() {
					@Override
					public Integer apply(ClmWrhsGodExtend ordGod) {
		                return ordGod.getOrdGodTurn();
		            }
				}
			);
		}
		
		// 2) 원 주문에 해당하는 기존 반품 내역 조회					
		deliveryRuleByGoodDTO.setClmTpCd("RTGOD");	// 반품 : RTGOD
		List<ClmWrhsGodExtend> rtWrhsGodList = deliverySelectRepository.selectClmWrhsGodByOrdNo(deliveryRuleByGoodDTO);
		
		ListMultimap<Integer, ClmWrhsGodExtend> rtWrhsGodListMap =  ArrayListMultimap.create();
		if (rtWrhsGodList != null && rtWrhsGodList.size() > 0) {
			// 주문 상품 순번 기준으로 ListMap 생성 - 하나의 주문 상품 순번에 여러건의 주문을 했을 경우 여러번 부분 반품을 할 수 있다.
			rtWrhsGodListMap = Multimaps.index(
				rtWrhsGodList, 
				new Function<ClmWrhsGodExtend, Integer>() {
					@Override
					public Integer apply(ClmWrhsGodExtend ordGod) {
		                return ordGod.getOrdGodTurn();
		            }
				}
			);
		}					
		
		// 3) 원 주문의 교환후 반품 내역 조회
		deliveryRuleByGoodDTO.setSelectExchgToRt(true);
		List<ClmWrhsGodExtend> exchgToRtWrhsGodList = deliverySelectRepository.selectClmWrhsGodByOrdNo(deliveryRuleByGoodDTO);
		
		ListMultimap<Integer, ClmWrhsGodExtend> exchgToRtWrhsGodListMap =  ArrayListMultimap.create();
		if (exchgToRtWrhsGodList != null && exchgToRtWrhsGodList.size() > 0) {
			// 원 주문 상품 순번 연결
			for (ClmWrhsGodExtend exchgToRtWrhsGod : exchgToRtWrhsGodList) {
				deliveryRuleByGoodDTO.setOrdGodTurn( exchgToRtWrhsGod.getOrdGodTurn() );
				ClmWrhsGodExtend  exchgOriOrdGod = deliverySelectRepository.selectExchgOriOrdGod(deliveryRuleByGoodDTO);
				exchgToRtWrhsGod.setOriOrdGodTurn(exchgOriOrdGod.getOriOrdGodTurn());
			}
			
			// 원 주문 상품 순번 기준으로 ListMap 생성 - 하나의 주문 상품 순번에 여러건의 주문을 했을 경우 여러번 부분 교환을 할 수 있다.
			exchgToRtWrhsGodListMap = Multimaps.index(
				exchgToRtWrhsGodList, 
				new Function<ClmWrhsGodExtend, Integer>() {
					@Override
					public Integer apply(ClmWrhsGodExtend ordGod) {
		                return ordGod.getOriOrdGodTurn();
		            }
				}
			);
		}			
		
		// 배송정책 번호별 주문금액 합계
		double sumSaleAmt = 0;
		
		// 4) 배송정책에 해당하는 주문 상품 총 금액 - 클레임 금액 계산
		for (OrdGodExtends oriOrdGod : oriOrdGodDvlListMap.get(dmstcDlvCstPlcSn)) {
			// 원 상품 순번으로 반품 요청 상품 순번 조회
			Integer reqRtOrdGodTurn = oriToReqRtOrdGodTurnMap.get(oriOrdGod.getOrdGodTurn());
			
			// 원 주문 수량
			double oriOrdQty = oriOrdGod.getOrdQty();
			
			// 취소 수량
			double cnclOrdQty = 0;
			if (cnclWrhsGodListMap.containsKey(oriOrdGod.getOrdGodTurn())) {
				for (ClmWrhsGodExtend clmWrhsGodExtend : cnclWrhsGodListMap.get(oriOrdGod.getOrdGodTurn())) {
					cnclOrdQty += clmWrhsGodExtend.getClmQty();
				}
			}
			
			// 기존 반품 수량
			double rtOrdQty = 0;
			if (rtWrhsGodListMap.containsKey(oriOrdGod.getOrdGodTurn())) {
				for (ClmWrhsGodExtend clmWrhsGodExtend : rtWrhsGodListMap.get(oriOrdGod.getOrdGodTurn())) {
					rtOrdQty += clmWrhsGodExtend.getClmQty();
				}
			}
			
			// 교환 후 반품 수량
			double exchgToRtOrdQty = 0;
			if (exchgToRtWrhsGodListMap.containsKey(oriOrdGod.getOrdGodTurn())) {
				for (ClmWrhsGodExtend clmWrhsGodExtend : exchgToRtWrhsGodListMap.get(oriOrdGod.getOrdGodTurn())) {
					exchgToRtOrdQty += clmWrhsGodExtend.getClmQty();
				}
			}
			
			// 금번 반품 수량
			double clmQty = 0;
			
			// 반품 요청상품에 존재하지 않는 경우는 기존 주문 상품. => 배송지, 배송 정책 별 전체 주문 금액 sum을 위해 필요
			if (reqClmWrhsGodMap.containsKey(reqRtOrdGodTurn)) {
				clmQty = reqClmWrhsGodMap.get(reqRtOrdGodTurn).getClmQty();
			}
			
			// 원 주문 - (부분 취소 + 기존 반품 + 금번 반품) 이 무료 배송 기준을 충족하면 반품 배송비만 부과
			double newOrdQty = oriOrdQty - cnclOrdQty - rtOrdQty - exchgToRtOrdQty - clmQty;

			sumSaleAmt += ((oriOrdGod.getSaleAmt().doubleValue() 
							- oriOrdGod.getGodDcAmt().doubleValue()
							- oriOrdGod.getBundleDcAmt().doubleValue()
							- oriOrdGod.getCrsDcAmt().doubleValue()
							- oriOrdGod.getGodCpnDcAmt().doubleValue()
							) / oriOrdQty) * newOrdQty;
		}

	     return  sumSaleAmt;
	}
}