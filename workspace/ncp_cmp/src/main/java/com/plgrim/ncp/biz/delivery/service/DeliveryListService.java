/* Copyright (c) 2015 plgrim, Inc.
 * All right reserved.
 * http://plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 *
 * ------------------------------------------------------------------------
 * @author      jwcale.kim
 * @since       2015. 8. 18       
 */
package com.plgrim.ncp.biz.delivery.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import com.plgrim.ncp.base.abstracts.AbstractService;
import com.plgrim.ncp.base.entities.RepositoryHelper;
import com.plgrim.ncp.base.entities.datasource1.com.Com;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsDlivyDrctGod;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsDlivyDrctGodHist;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsDlvRtrvlDrctHist;
import com.plgrim.ncp.base.entities.datasource1.sys.SysShopExtends;
import com.plgrim.ncp.biz.delivery.data.DeliveryAffDTO;
import com.plgrim.ncp.biz.delivery.data.DeliveryOrderGoodDTO;
import com.plgrim.ncp.biz.delivery.data.DeliverySearchDTO;
import com.plgrim.ncp.biz.delivery.repository.DeliveryCommandRepository;
import com.plgrim.ncp.biz.delivery.repository.DeliverySelectRepository;
import com.plgrim.ncp.biz.delivery.result.DeliveryArticleResult;
import com.plgrim.ncp.biz.delivery.result.DeliveryClaimGoodResult;
import com.plgrim.ncp.biz.delivery.result.DeliveryHistoryResult;
import com.plgrim.ncp.biz.delivery.result.DeliveryInvoiceResult;
import com.plgrim.ncp.biz.delivery.result.DeliveryOrderGoodResult;
import com.plgrim.ncp.biz.delivery.result.DeliveryRedisplayResult;
import com.plgrim.ncp.biz.vendor.data.VendorBrndSearchDTO;
import com.plgrim.ncp.biz.vendor.result.VendorBrndListResult;
import com.plgrim.ncp.commons.taglib.Functions;
import com.plgrim.ncp.commons.util.BOSecurityUtil;
import com.plgrim.ncp.framework.commons.StringService;
import com.plgrim.ncp.framework.data.SystemPK;

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
public class DeliveryListService extends AbstractService {

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	@Autowired
	DeliverySelectRepository deliverySelectRepository;

	@Autowired
	DeliveryCommandRepository deliveryCommandRepository;

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
	 * 자사상품 배송조회.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param systemPk [설명]
	 * @param deliverySearchDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 3. 30
	 */
	public List<DeliveryOrderGoodResult> getDeliveryList(SystemPK systemPk, DeliverySearchDTO deliverySearchDTO) throws Exception {
		List<String> ordNoList = new ArrayList<String>();
		List<String> wayBilNoList = new ArrayList<String>();
		List<String> searchNoList = new ArrayList<String>();
		List<String> brndIdNoList = new ArrayList<String>();
		
		//브랜드 다중검색
		if(StringService.isNotEmpty(deliverySearchDTO.getBrndIds())) {
			String[] brndIdArr = deliverySearchDTO.getBrndIds().split("\\,");
			for (String brndId : brndIdArr) {
				brndIdNoList.add(brndId.toUpperCase().trim());
            }
			deliverySearchDTO.setBrndIdList(brndIdNoList);
		}
		
		//주문번호 다중검색
		if(StringService.isNotEmpty(deliverySearchDTO.getOrdNos())) {
			String[] ordNoArr = deliverySearchDTO.getOrdNos().split("\\r?\\n");
			for (String ordNo : ordNoArr) {
				ordNoList.add(ordNo.toUpperCase().trim());
            }
			deliverySearchDTO.setOrdNoList(ordNoList);
		}
		
		//운송장번호 다중검색
		if(StringService.isNotEmpty(deliverySearchDTO.getWayBilNos())) {
			String[] wayBilNoArr = deliverySearchDTO.getWayBilNos().split("\\r?\\n");
			for (String wayBilNo : wayBilNoArr) {
				wayBilNoList.add(wayBilNo.toUpperCase().trim());
			}
			deliverySearchDTO.setWayBilNoList(wayBilNoList);
		}		
		
		//다중검색
		if(StringService.isNotEmpty(deliverySearchDTO.getSearchNos())) {
			String[] searchNoArr = deliverySearchDTO.getSearchNos().split("\\r?\\n");
			for (String searchNo : searchNoArr) {
				searchNoList.add(searchNo.toUpperCase().trim());
			}
			deliverySearchDTO.setSearchNoList(searchNoList);
		}
		
		return deliverySelectRepository.getDeliveryList(deliverySearchDTO);
	}
	
	/**
	 * 배송지연리스트 조회.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param systemPk [설명]
	 * @param deliverySearchDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 3. 30
	 */
	public List<DeliveryOrderGoodResult> getDelayDeliveryList(SystemPK systemPk, DeliverySearchDTO deliverySearchDTO) throws Exception {
		List<String> ordNoList = new ArrayList<String>();
		List<String> searchNoList = new ArrayList<String>();
		List<String> brndIdNoList = new ArrayList<String>();
		
		//브랜드 다중검색
		if(StringService.isNotEmpty(deliverySearchDTO.getBrndIds())) {
			String[] brndIdArr = deliverySearchDTO.getBrndIds().split("\\,");
			for (String brndId : brndIdArr) {
				brndIdNoList.add(brndId.toUpperCase().trim());
            }
			deliverySearchDTO.setBrndIdList(brndIdNoList);
		}
		
		//주문번호 다중검색
		if(StringService.isNotEmpty(deliverySearchDTO.getOrdNos())) {
			String[] ordNoArr = deliverySearchDTO.getOrdNos().split("\\r?\\n");
			for (String ordNo : ordNoArr) {
				ordNoList.add(ordNo.toUpperCase().trim());
            }
			deliverySearchDTO.setOrdNoList(ordNoList);
		}
			
		//다중검색
		if(StringService.isNotEmpty(deliverySearchDTO.getSearchNos())) {
			String[] searchNoArr = deliverySearchDTO.getSearchNos().split("\\r?\\n");
			for (String searchNo : searchNoArr) {
				searchNoList.add(searchNo.toUpperCase().trim());
			}
			deliverySearchDTO.setSearchNoList(searchNoList);
		}
		
		return deliverySelectRepository.getDelayDeliveryList(deliverySearchDTO);
	}
	
	/**
	 * 배송매장 변경이력 조회.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param systemPk [설명]
	 * @param deliverySearchDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 4. 2
	 */
	public List<DeliveryHistoryResult> getDeliveryShopHistoryList(SystemPK systemPk, DeliverySearchDTO deliverySearchDTO) throws Exception {
		return deliverySelectRepository.getDeliveryShopHistoryList(deliverySearchDTO);
	}

	/**
	 * 자사상품 회수리스트 조회.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param systemPk [설명]
	 * @param deliverySearchDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 4. 6
	 */
	public List<DeliveryClaimGoodResult> getRetrievalGoodsList(SystemPK systemPk, DeliverySearchDTO deliverySearchDTO) throws Exception {
		List<String> ordNoList = new ArrayList<String>();
		List<String> paramList = new ArrayList<String>();
		List<String> rtrvlDrctTpCdList = new ArrayList<String>();
		
		//주문번호 다중검색
		if(StringService.isNotEmpty(deliverySearchDTO.getOrdNos())) {
			String[] ordNoArr = deliverySearchDTO.getOrdNos().split("\n");
			for (String ordNo : ordNoArr) {
				ordNoList.add(ordNo.toUpperCase().trim());
            }
			deliverySearchDTO.setOrdNoList(ordNoList);
		}
		
		//송장번호/크레임번호 다중검색
		if(StringService.isNotEmpty(deliverySearchDTO.getParams())) {
			String[] paramArr = deliverySearchDTO.getParams().split("\n");
			for (String param : paramArr) {
				paramList.add(param.toUpperCase().trim());
			}
			deliverySearchDTO.setParamList(paramList);
		}
		
		//회수구분
		if(StringService.isNotEmpty(deliverySearchDTO.getRtrvlDrctTpCds())) {
			String[] rtrvlDrctTpCdArr = deliverySearchDTO.getRtrvlDrctTpCds().split("\\,");
			for (String rtrvlDrctTpCd : rtrvlDrctTpCdArr) {
				rtrvlDrctTpCdList.add(rtrvlDrctTpCd.toUpperCase().trim());
			}
			deliverySearchDTO.setRtrvlDrctTpCdList(rtrvlDrctTpCdList);
		}
		
		return deliverySelectRepository.getRetrievalGoodsList(deliverySearchDTO);
	}
	
	/**
	 * 상품출고배정 조회.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param systemPk [설명]
	 * @param deliverySearchDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 4. 6
	 */
	public List<DeliveryOrderGoodResult> getSelfAssignDeliveryList(SystemPK systemPk, DeliverySearchDTO deliverySearchDTO) throws Exception {
		List<String> paramList = new ArrayList<String>();
		List<String> brndIdList = new ArrayList<String>();
		
		//주문/ERP단품번호/온라인단품번호 다중검색
		if(StringService.isNotEmpty(deliverySearchDTO.getParams())) {
			String[] paramArr = deliverySearchDTO.getParams().split("\n");
			for (String param : paramArr) {
				paramList.add(param.toUpperCase().trim());
			}
			deliverySearchDTO.setParamList(paramList);
		}
		
		//브랜드ID 다중검색
		if(StringService.isNotEmpty(deliverySearchDTO.getBrndIds())) {
			String[] brndIdArr = deliverySearchDTO.getBrndIds().split(",");
			for (String brndId : brndIdArr) {
				brndIdList.add(brndId.toUpperCase().trim());
            }
			deliverySearchDTO.setBrndIdList(brndIdList);
		}
		
		return deliverySelectRepository.getSelfAssignDeliveryList(deliverySearchDTO);
	}
	
	
	/**
	 * 자사상품 배송조회.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param systemPk [설명]
	 * @param deliverySearchDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 3. 30
	 */
	public int getDeliveryListCount(SystemPK systemPk, DeliverySearchDTO deliverySearchDTO) throws Exception {
		List<String> ordNoList = new ArrayList<String>();
		List<String> wayBilNoList = new ArrayList<String>();
		List<String> searchNoList = new ArrayList<String>();
		List<String> brndIdNoList = new ArrayList<String>();
		
		//브랜드 다중검색
		if(StringService.isNotEmpty(deliverySearchDTO.getBrndIds())) {
			String[] brndIdArr = deliverySearchDTO.getBrndIds().split("\\,");
			for (String brndId : brndIdArr) {
				brndIdNoList.add(brndId.toUpperCase().trim());
            }
			deliverySearchDTO.setBrndIdList(brndIdNoList);
		}

		//주문번호 다중검색
		if(StringService.isNotEmpty(deliverySearchDTO.getOrdNos())) {
			String[] ordNoArr = deliverySearchDTO.getOrdNos().split("\\r?\\n");
			for (String ordNo : ordNoArr) {
				ordNoList.add(ordNo.toUpperCase().trim());
            }
			deliverySearchDTO.setOrdNoList(ordNoList);
		}
		
		//운송장번호 다중검색
		if(StringService.isNotEmpty(deliverySearchDTO.getWayBilNos())) {
			String[] wayBilNoArr = deliverySearchDTO.getWayBilNos().split("\\r?\\n");
			for (String wayBilNo : wayBilNoArr) {
				wayBilNoList.add(wayBilNo.toUpperCase().trim());
			}
			deliverySearchDTO.setWayBilNoList(wayBilNoList);
		}		
		
		//다중검색
		if(StringService.isNotEmpty(deliverySearchDTO.getSearchNos())) {
			String[] searchNoArr = deliverySearchDTO.getSearchNos().split("\\r?\\n");
			for (String searchNo : searchNoArr) {
				searchNoList.add(searchNo.toUpperCase().trim());
			}
			deliverySearchDTO.setSearchNoList(searchNoList);
		}
		
		return deliverySelectRepository.getDeliveryListCount(deliverySearchDTO);
	}	
	
	/**
	 * 자사상품 배송조회 excel.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param systemPk [설명]
	 * @param deliverySearchDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 3. 30
	 */
	public List<Map<String, Object>> getDeliveryListExcel(SystemPK systemPk, DeliverySearchDTO deliverySearchDTO) throws Exception {
		List<String> ordNoList = new ArrayList<String>();
		List<String> wayBilNoList = new ArrayList<String>();
		List<String> searchNoList = new ArrayList<String>();
		List<String> brndIdNoList = new ArrayList<String>();
		
		//브랜드 다중검색
		if(StringService.isNotEmpty(deliverySearchDTO.getBrndIds())) {
			String[] brndIdArr = deliverySearchDTO.getBrndIds().split("\\,");
			for (String brndId : brndIdArr) {
				brndIdNoList.add(brndId.toUpperCase().trim());
            }
			deliverySearchDTO.setBrndIdList(brndIdNoList);
		}

		//주문번호 다중검색
		if(StringService.isNotEmpty(deliverySearchDTO.getOrdNos())) {
			String[] ordNoArr = deliverySearchDTO.getOrdNos().split("\\r?\\n");
			for (String ordNo : ordNoArr) {
				ordNoList.add(ordNo.toUpperCase().trim());
            }
			deliverySearchDTO.setOrdNoList(ordNoList);
		}
		
		//운송장번호 다중검색
		if(StringService.isNotEmpty(deliverySearchDTO.getWayBilNos())) {
			String[] wayBilNoArr = deliverySearchDTO.getWayBilNos().split("\\r?\\n");
			for (String wayBilNo : wayBilNoArr) {
				wayBilNoList.add(wayBilNo.toUpperCase().trim());
			}
			deliverySearchDTO.setWayBilNoList(wayBilNoList);
		}		
		
		//다중검색
		if(StringService.isNotEmpty(deliverySearchDTO.getSearchNos())) {
			String[] searchNoArr = deliverySearchDTO.getSearchNos().split("\\r?\\n");
			for (String searchNo : searchNoArr) {
				searchNoList.add(searchNo.toUpperCase().trim());
			}
			deliverySearchDTO.setSearchNoList(searchNoList);
		}
		
		return deliverySelectRepository.getDeliveryListExcel(deliverySearchDTO);
	}	
	
	
	/**
	 * 자사상품 출고관리조회.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param systemPk [설명]
	 * @param deliverySearchDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 3. 30
	 */
	public Page<DeliveryOrderGoodResult> getDrctGoodsList(SystemPK systemPk,DeliverySearchDTO deliverySearchDTO) throws Exception {
		RepositoryHelper.makePageEntityIndex(deliverySearchDTO, deliverySearchDTO.getPageParam());	// 페이지 설정
		List<String> ordNoList = new ArrayList<String>();
		List<String> searchNoList = new ArrayList<String>();
		List<String> brndIdNoList = new ArrayList<String>();
		List<String> dlivyDrctGrpDgreList = new ArrayList<String>();
		List<String> assignTypeList = new ArrayList<String>();
		List<String> ordTpCdList = new ArrayList<String>();
		List<String> cstrnoList = new ArrayList<String>();
		
		//브랜드 다중검색
		if(StringService.isNotEmpty(deliverySearchDTO.getBrndIds())) {
			String[] brndIdArr = deliverySearchDTO.getBrndIds().split("\\,");
			for (String brndId : brndIdArr) {
				brndIdNoList.add(brndId.toUpperCase().trim());
			}
			deliverySearchDTO.setBrndIdList(brndIdNoList);
		}
		
		//주문번호 다중검색
		if(StringService.isNotEmpty(deliverySearchDTO.getOrdNos())) {
			String[] ordNoArr = deliverySearchDTO.getOrdNos().split("\\r?\\n");
			for (String ordNo : ordNoArr) {
				ordNoList.add(ordNo.toUpperCase().trim());
			}
			deliverySearchDTO.setOrdNoList(ordNoList);
		}
		
		//다중검색
		if(StringService.isNotEmpty(deliverySearchDTO.getSearchNos())) {
			String[] searchNoArr = deliverySearchDTO.getSearchNos().split("\\r?\\n");
			for (String searchNo : searchNoArr) {
				searchNoList.add(searchNo.toUpperCase().trim());
			}
			deliverySearchDTO.setSearchNoList(searchNoList);
		}
		
		//차수 다중검색
		if(StringService.isNotEmpty(deliverySearchDTO.getDlivyDrctGrpDgres())) {
			String[] dlivyDrctGrpDgreArr = deliverySearchDTO.getDlivyDrctGrpDgres().split("\\,");
			for (String dlivyDrctGrpDgre : dlivyDrctGrpDgreArr) {
				dlivyDrctGrpDgreList.add(dlivyDrctGrpDgre.toUpperCase().trim());
			}
			deliverySearchDTO.setDlivyDrctGrpDgreList(dlivyDrctGrpDgreList);
		}
		
		//배송유형
		if(StringService.isNotEmpty(deliverySearchDTO.getAssignType())) {
			String[] assignTypeArr = deliverySearchDTO.getAssignType().split("\\,");
			for (String assignType : assignTypeArr) {
				assignTypeList.add(assignType.toUpperCase().trim());
				
				if(assignType.toUpperCase().trim().equals("AUTO_ASGN")){ // 자동배정일 경우 강제배정 포함
					assignTypeList.add("ENFRC_ASGN"); // 강제배정
				}
			}
			deliverySearchDTO.setAssignTypeList(assignTypeList);
		}
		
		//주문유형
		if(StringService.isNotEmpty(deliverySearchDTO.getOrdTpCds())) {
			String[] ordTpCdArr = deliverySearchDTO.getOrdTpCds().split("\\,");
			for (String ordTpCd : ordTpCdArr) {
				ordTpCdList.add(ordTpCd.toUpperCase().trim());
			}
			deliverySearchDTO.setOrdTpCdList(ordTpCdList);
		}
 
		String cstrno = deliverySelectRepository.getVipList();
		
		if(StringService.isNotEmpty(cstrno)){
			String[] cstrnoArr = cstrno.split("\\|");
			for (String v : cstrnoArr) {
				cstrnoList.add(v.trim());
			}
			deliverySearchDTO.setCstrnoList(cstrnoList);
		}
		
		
		// 목록 건수 조회
		long totalRow = deliverySelectRepository.getDrctGoodsListCount(deliverySearchDTO);

		// 목록 조회
		List<DeliveryOrderGoodResult> results = new ArrayList<DeliveryOrderGoodResult>();
		if(totalRow > 0){
			results = deliverySelectRepository.getDrctGoodsList(deliverySearchDTO);
		}
		
		return new PageImpl<DeliveryOrderGoodResult>(results, deliverySearchDTO.getPageParam().getPageable(), totalRow);
	}	
	
	/**
	 * 자사상품 출고관리조회 excel.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param systemPk [설명]
	 * @param deliverySearchDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 3. 30
	 */
	public List<Map<String, Object>> getDrctGoodsListExcel(SystemPK systemPk, DeliverySearchDTO deliverySearchDTO) throws Exception {
		List<String> ordNoList = new ArrayList<String>();
		List<String> searchNoList = new ArrayList<String>();
		List<String> brndIdNoList = new ArrayList<String>();
		List<String> dlivyDrctGrpDgreList = new ArrayList<String>();
		List<String> assignTypeList = new ArrayList<String>();
		List<String> ordTpCdList = new ArrayList<String>();
		List<String> cstrnoList = new ArrayList<String>();
		
		//브랜드 다중검색
		if(StringService.isNotEmpty(deliverySearchDTO.getBrndIds())) {
			String[] brndIdArr = deliverySearchDTO.getBrndIds().split("\\,");
			for (String brndId : brndIdArr) {
				brndIdNoList.add(brndId.toUpperCase().trim());
            }
			deliverySearchDTO.setBrndIdList(brndIdNoList);
		}
		
		//주문번호 다중검색
		if(StringService.isNotEmpty(deliverySearchDTO.getOrdNos())) {
			String[] ordNoArr = deliverySearchDTO.getOrdNos().split("\\r?\\n");
			for (String ordNo : ordNoArr) {
				ordNoList.add(ordNo.toUpperCase().trim());
            }
			deliverySearchDTO.setOrdNoList(ordNoList);
		}
		
		//다중검색
		if(StringService.isNotEmpty(deliverySearchDTO.getSearchNos())) {
			String[] searchNoArr = deliverySearchDTO.getSearchNos().split("\\r?\\n");
			for (String searchNo : searchNoArr) {
				searchNoList.add(searchNo.toUpperCase().trim());
			}
			deliverySearchDTO.setSearchNoList(searchNoList);
		}
		
		//차수 다중검색
		if(StringService.isNotEmpty(deliverySearchDTO.getDlivyDrctGrpDgres())) {
			String[] dlivyDrctGrpDgreArr = deliverySearchDTO.getDlivyDrctGrpDgres().split("\\,");
			for (String dlivyDrctGrpDgre : dlivyDrctGrpDgreArr) {
				dlivyDrctGrpDgreList.add(dlivyDrctGrpDgre.toUpperCase().trim());
			}
			deliverySearchDTO.setDlivyDrctGrpDgreList(dlivyDrctGrpDgreList);
		}
		
		//배송유형
		if(StringService.isNotEmpty(deliverySearchDTO.getAssignType())) {
			String[] assignTypeArr = deliverySearchDTO.getAssignType().split("\\,");
			for (String assignType : assignTypeArr) {
				assignTypeList.add(assignType.toUpperCase().trim());
				
				if(assignType.toUpperCase().trim().equals("AUTO_ASGN")){ // 자동배정일 경우 강제배정 포함
					assignTypeList.add("ENFRC_ASGN"); // 강제배정
				}
			}
			deliverySearchDTO.setAssignTypeList(assignTypeList);
		}
		
		//주문유형
		if(StringService.isNotEmpty(deliverySearchDTO.getOrdTpCds())) {
			String[] ordTpCdArr = deliverySearchDTO.getOrdTpCds().split("\\,");
			for (String ordTpCd : ordTpCdArr) {
				ordTpCdList.add(ordTpCd.toUpperCase().trim());
			}
			deliverySearchDTO.setOrdTpCdList(ordTpCdList);
		}
		
		String cstrno = deliverySelectRepository.getVipList();
		
		if(StringService.isNotEmpty(cstrno)){
			String[] cstrnoArr = cstrno.split("\\|");
			for (String v : cstrnoArr) {
				cstrnoList.add(v.trim());
			}
			deliverySearchDTO.setCstrnoList(cstrnoList);
		}
		return deliverySelectRepository.getDrctGoodsListExcel(deliverySearchDTO);
	}		
	
	
	public List<Map<String, Object>>  getSampleData() throws Exception {
		return deliverySelectRepository.getSampleData();
	}
	
	
	/**
	 * 매장픽업 주문관리 조회.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param systemPk [설명]
	 * @param deliverySearchDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 3. 30
	 */
	public List<DeliveryOrderGoodResult> getStorePickupOrderList(SystemPK systemPk, DeliverySearchDTO deliverySearchDTO) throws Exception {
		List<String> searchNoList = new ArrayList<String>();
		List<String> brndIdNoList = new ArrayList<String>();
		
		//브랜드 다중검색
		if(StringService.isNotEmpty(deliverySearchDTO.getBrndIds())) {
			String[] brndIdArr = deliverySearchDTO.getBrndIds().split("\\,");
			for (String brndId : brndIdArr) {
				brndIdNoList.add(brndId.toUpperCase().trim());
            }
			deliverySearchDTO.setBrndIdList(brndIdNoList);
		}
		
		//다중검색
		if(StringService.isNotEmpty(deliverySearchDTO.getSearchNos())) {
			String[] searchNoArr = deliverySearchDTO.getSearchNos().split("\\r?\\n");
			for (String searchNo : searchNoArr) {
				searchNoList.add(searchNo.toUpperCase().trim());
			}
			deliverySearchDTO.setSearchNoList(searchNoList);
		}
		
		
		return deliverySelectRepository.getStorePickupOrderList(deliverySearchDTO);
	}	
	
	
	/**
	 * 매장픽업 주문관리 조회.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param systemPk [설명]
	 * @param deliverySearchDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 3. 30
	 */
	public int getStorePickupOrderListCount(SystemPK systemPk, DeliverySearchDTO deliverySearchDTO) throws Exception {
		List<String> ordNoList = new ArrayList<String>();
		List<String> searchNoList = new ArrayList<String>();
		List<String> brndIdNoList = new ArrayList<String>();
		
		//브랜드 다중검색
		if(StringService.isNotEmpty(deliverySearchDTO.getBrndIds())) {
			String[] brndIdArr = deliverySearchDTO.getBrndIds().split("\\,");
			for (String brndId : brndIdArr) {
				brndIdNoList.add(brndId.toUpperCase().trim());
            }
			deliverySearchDTO.setBrndIdList(brndIdNoList);
		}
		
		//주문번호 다중검색
		if(StringService.isNotEmpty(deliverySearchDTO.getOrdNos())) {
			String[] ordNoArr = deliverySearchDTO.getOrdNos().split("\\r?\\n");
			for (String ordNo : ordNoArr) {
				ordNoList.add(ordNo.toUpperCase().trim());
            }
			deliverySearchDTO.setOrdNoList(ordNoList);
		}
		
		//다중검색
		if(StringService.isNotEmpty(deliverySearchDTO.getSearchNos())) {
			String[] searchNoArr = deliverySearchDTO.getSearchNos().split("\\r?\\n");
			for (String searchNo : searchNoArr) {
				searchNoList.add(searchNo.toUpperCase().trim());
			}
			deliverySearchDTO.setSearchNoList(searchNoList);
		}
		
		return deliverySelectRepository.getStorePickupOrderListCount(deliverySearchDTO);
	}	
	
	
	/**
	 * 전체 주문상품 수량.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param systemPk [설명]
	 * @param deliverySearchDTO [설명]
	 * @return Int [설명]
	 * @throws Exception the exception
	 * @since 2015. 7. 25
	 */
	public int selectOrdGodCount(SystemPK systemPk, DeliverySearchDTO deliverySearchDTO) throws Exception {
		return deliverySelectRepository.selectOrdGodCount(deliverySearchDTO);
	}
	
	
	/**
	 * 매장픽업 주문관리 조회 excel.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param systemPk [설명]
	 * @param deliverySearchDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 3. 30
	 */
	public List<Map<String, Object>> getStorePickupOrderListExcel(SystemPK systemPk, DeliverySearchDTO deliverySearchDTO) throws Exception {
		List<String> ordNoList = new ArrayList<String>();
		List<String> searchNoList = new ArrayList<String>();
		List<String> brndIdNoList = new ArrayList<String>();
		
		//브랜드 다중검색
		if(StringService.isNotEmpty(deliverySearchDTO.getBrndIds())) {
			String[] brndIdArr = deliverySearchDTO.getBrndIds().split("\\,");
			for (String brndId : brndIdArr) {
				brndIdNoList.add(brndId.toUpperCase().trim());
            }
			deliverySearchDTO.setBrndIdList(brndIdNoList);
		}
		
		//주문번호 다중검색
		if(StringService.isNotEmpty(deliverySearchDTO.getOrdNos())) {
			String[] ordNoArr = deliverySearchDTO.getOrdNos().split("\\r?\\n");
			for (String ordNo : ordNoArr) {
				ordNoList.add(ordNo.toUpperCase().trim());
            }
			deliverySearchDTO.setOrdNoList(ordNoList);
		}
		
		//다중검색
		if(StringService.isNotEmpty(deliverySearchDTO.getSearchNos())) {
			String[] searchNoArr = deliverySearchDTO.getSearchNos().split("\\r?\\n");
			for (String searchNo : searchNoArr) {
				searchNoList.add(searchNo.toUpperCase().trim());
			}
			deliverySearchDTO.setSearchNoList(searchNoList);
		}
		
		
		return deliverySelectRepository.getStorePickupOrderListExcel(deliverySearchDTO);
	}	
	
	
	/**
	 * 배송지연 상품관리 조회 total count.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param systemPk [설명]
	 * @param deliverySearchDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 3. 30
	 */
	public int getDelayDeliveryListCount(SystemPK systemPk, DeliverySearchDTO deliverySearchDTO) throws Exception {
		List<String> ordNoList = new ArrayList<String>();
		List<String> searchNoList = new ArrayList<String>();
		List<String> brndIdNoList = new ArrayList<String>();
		
		//브랜드 다중검색
		if(StringService.isNotEmpty(deliverySearchDTO.getBrndIds())) {
			String[] brndIdArr = deliverySearchDTO.getBrndIds().split("\\,");
			for (String brndId : brndIdArr) {
				brndIdNoList.add(brndId.toUpperCase().trim());
            }
			deliverySearchDTO.setBrndIdList(brndIdNoList);
		}
		
		//주문번호 다중검색
		if(StringService.isNotEmpty(deliverySearchDTO.getOrdNos())) {
			String[] ordNoArr = deliverySearchDTO.getOrdNos().split("\\r?\\n");
			for (String ordNo : ordNoArr) {
				ordNoList.add(ordNo.toUpperCase().trim());
            }
			deliverySearchDTO.setOrdNoList(ordNoList);
		}
			
		//다중검색
		if(StringService.isNotEmpty(deliverySearchDTO.getSearchNos())) {
			String[] searchNoArr = deliverySearchDTO.getSearchNos().split("\\r?\\n");
			for (String searchNo : searchNoArr) {
				searchNoList.add(searchNo.toUpperCase().trim());
			}
			deliverySearchDTO.setSearchNoList(searchNoList);
		}
		
		return deliverySelectRepository.getDelayDeliveryListCount(deliverySearchDTO);
	}
	
	
	/**
	 * 배송지연 상품관리 조회 excel.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param systemPk [설명]
	 * @param deliverySearchDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 3. 30
	 */
	public List<Map<String, Object>> getDelayDeliveryListExcel(SystemPK systemPk, DeliverySearchDTO deliverySearchDTO) throws Exception {
		List<String> ordNoList = new ArrayList<String>();
		List<String> searchNoList = new ArrayList<String>();
		List<String> brndIdNoList = new ArrayList<String>();
		
		//브랜드 다중검색
		if(StringService.isNotEmpty(deliverySearchDTO.getBrndIds())) {
			String[] brndIdArr = deliverySearchDTO.getBrndIds().split("\\,");
			for (String brndId : brndIdArr) {
				brndIdNoList.add(brndId.toUpperCase().trim());
            }
			deliverySearchDTO.setBrndIdList(brndIdNoList);
		}
		
		//주문번호 다중검색
		if(StringService.isNotEmpty(deliverySearchDTO.getOrdNos())) {
			String[] ordNoArr = deliverySearchDTO.getOrdNos().split("\\r?\\n");
			for (String ordNo : ordNoArr) {
				ordNoList.add(ordNo.toUpperCase().trim());
            }
			deliverySearchDTO.setOrdNoList(ordNoList);
		}
			
		//다중검색
		if(StringService.isNotEmpty(deliverySearchDTO.getSearchNos())) {
			String[] searchNoArr = deliverySearchDTO.getSearchNos().split("\\r?\\n");
			for (String searchNo : searchNoArr) {
				searchNoList.add(searchNo.toUpperCase().trim());
			}
			deliverySearchDTO.setSearchNoList(searchNoList);
		}
		
		
		return deliverySelectRepository.getDelayDeliveryListExcel(deliverySearchDTO);
	}		
	
	
	/**
	 * 입점업체배송리스트 조회.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param systemPk [설명]
	 * @param deliverySearchDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 3. 30
	 */
	public List<DeliveryOrderGoodResult> getPartMallDeliveryList(SystemPK systemPk, DeliverySearchDTO deliverySearchDTO) throws Exception {
		List<String> ordNoList = new ArrayList<String>();
		List<String> searchNoList = new ArrayList<String>();
		List<String> drctTpCdList = new ArrayList<String>();
		
		//주문번호 다중검색
		if(StringService.isNotEmpty(deliverySearchDTO.getOrdNos())) {
			String[] ordNoArr = deliverySearchDTO.getOrdNos().split("\\r?\\n");
			for (String ordNo : ordNoArr) {
				ordNoList.add(ordNo.toUpperCase().trim());
            }
			deliverySearchDTO.setOrdNoList(ordNoList);
		}
		
		//다중검색
		if(StringService.isNotEmpty(deliverySearchDTO.getSearchNos())) {
			String[] searchNoArr = deliverySearchDTO.getSearchNos().split("\\r?\\n");
			for (String searchNo : searchNoArr) {
				searchNoList.add(searchNo.toUpperCase().trim());
			}
			deliverySearchDTO.setSearchNoList(searchNoList);
		}
		
		//주문유형
		if(StringService.isNotEmpty(deliverySearchDTO.getDrctTpCds())) {
			String[] drctTpCdArr = deliverySearchDTO.getDrctTpCds().split("\\,");
			for (String drctTpCd : drctTpCdArr) {
				drctTpCdList.add(drctTpCd.toUpperCase().trim());
			}
			deliverySearchDTO.setDrctTpCdList(drctTpCdList);
		}
		
		
		return deliverySelectRepository.getPartMallDeliveryList(deliverySearchDTO);
	}	
	
	
	/**
	 * 입점업체배송리스트 조회.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param systemPk [설명]
	 * @param deliverySearchDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 3. 30
	 */
	public int getPartMallDeliveryListCount(SystemPK systemPk, DeliverySearchDTO deliverySearchDTO) throws Exception {
		List<String> ordNoList = new ArrayList<String>();
		List<String> searchNoList = new ArrayList<String>();
		List<String> drctTpCdList = new ArrayList<String>();
		
		//주문번호 다중검색
		if(StringService.isNotEmpty(deliverySearchDTO.getOrdNos())) {
			String[] ordNoArr = deliverySearchDTO.getOrdNos().split("\\r?\\n");
			for (String ordNo : ordNoArr) {
				ordNoList.add(ordNo.toUpperCase().trim());
            }
			deliverySearchDTO.setOrdNoList(ordNoList);
		}
		
		//다중검색
		if(StringService.isNotEmpty(deliverySearchDTO.getSearchNos())) {
			String[] searchNoArr = deliverySearchDTO.getSearchNos().split("\\r?\\n");
			for (String searchNo : searchNoArr) {
				searchNoList.add(searchNo.toUpperCase().trim());
			}
			deliverySearchDTO.setSearchNoList(searchNoList);
		}
		
		//주문유형
		if(StringService.isNotEmpty(deliverySearchDTO.getDrctTpCds())) {
			String[] drctTpCdArr = deliverySearchDTO.getDrctTpCds().split("\\,");
			for (String drctTpCd : drctTpCdArr) {
				drctTpCdList.add(drctTpCd.toUpperCase().trim());
			}
			deliverySearchDTO.setDrctTpCdList(drctTpCdList);
		}
		
		return deliverySelectRepository.getPartMallDeliveryListCount(deliverySearchDTO);
	}	
	
	
	/**
	 * 입점업체배송리스트 조회 excel.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param systemPk [설명]
	 * @param deliverySearchDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 3. 30
	 */
	public List<Map<String, Object>> getPartMallDeliveryListExcel(SystemPK systemPk, DeliverySearchDTO deliverySearchDTO) throws Exception {
		List<String> ordNoList = new ArrayList<String>();
		List<String> searchNoList = new ArrayList<String>();
		List<String> drctTpCdList = new ArrayList<String>();
		
		//주문번호 다중검색
		if(StringService.isNotEmpty(deliverySearchDTO.getOrdNos())) {
			String[] ordNoArr = deliverySearchDTO.getOrdNos().split("\\r?\\n");
			for (String ordNo : ordNoArr) {
				ordNoList.add(ordNo.toUpperCase().trim());
            }
			deliverySearchDTO.setOrdNoList(ordNoList);
		}
		
		//다중검색
		if(StringService.isNotEmpty(deliverySearchDTO.getSearchNos())) {
			String[] searchNoArr = deliverySearchDTO.getSearchNos().split("\\r?\\n");
			for (String searchNo : searchNoArr) {
				searchNoList.add(searchNo.toUpperCase().trim());
			}
			deliverySearchDTO.setSearchNoList(searchNoList);
		}
		
		//주문유형
		if(StringService.isNotEmpty(deliverySearchDTO.getDrctTpCds())) {
			String[] drctTpCdArr = deliverySearchDTO.getDrctTpCds().split("\\,");
			for (String drctTpCd : drctTpCdArr) {
				drctTpCdList.add(drctTpCd.toUpperCase().trim());
			}
			deliverySearchDTO.setDrctTpCdList(drctTpCdList);
		}
		
		return deliverySelectRepository.getPartMallDeliveryListExcel(deliverySearchDTO);
	}	

	/**
	 * PO 입점업체배송리스트 조회 excel.
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param systemPk [설명]
	 * @param deliverySearchDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 3. 30
	 */
	public List<Map<String, Object>> getPoPartMallDeliveryListExcel(SystemPK systemPk, DeliverySearchDTO deliverySearchDTO) throws Exception {
		List<String> ordNoList = new ArrayList<String>();
		List<String> searchNoList = new ArrayList<String>();
		List<String> drctTpCdList = new ArrayList<String>();

		//주문번호 다중검색
		if(StringService.isNotEmpty(deliverySearchDTO.getOrdNos())) {
			String[] ordNoArr = deliverySearchDTO.getOrdNos().split("\\r?\\n");
			for (String ordNo : ordNoArr) {
				ordNoList.add(ordNo.toUpperCase().trim());
            }
			deliverySearchDTO.setOrdNoList(ordNoList);
		}

		//다중검색
		if(StringService.isNotEmpty(deliverySearchDTO.getSearchNos())) {
			String[] searchNoArr = deliverySearchDTO.getSearchNos().split("\\r?\\n");
			for (String searchNo : searchNoArr) {
				searchNoList.add(searchNo.toUpperCase().trim());
			}
			deliverySearchDTO.setSearchNoList(searchNoList);
		}

		//주문유형
		if(StringService.isNotEmpty(deliverySearchDTO.getDrctTpCds())) {
			String[] drctTpCdArr = deliverySearchDTO.getDrctTpCds().split("\\,");
			for (String drctTpCd : drctTpCdArr) {
				drctTpCdList.add(drctTpCd.toUpperCase().trim());
			}
			deliverySearchDTO.setDrctTpCdList(drctTpCdList);
		}

		return deliverySelectRepository.getPoPartMallDeliveryListExcel(deliverySearchDTO);
	}

	/**
	 * PO 입점업체 결품접수 조회 excel.
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param systemPk [설명]
	 * @param deliverySearchDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 3. 30
	 */
	public List<Map<String, Object>> getPoPartMallOrderGoodLackListExcel(SystemPK systemPk, DeliverySearchDTO deliverySearchDTO) throws Exception {
		List<String> ordNoList = new ArrayList<String>();
		List<String> searchNoList = new ArrayList<String>();
		List<String> drctTpCdList = new ArrayList<String>();

		//주문번호 다중검색
		if(StringService.isNotEmpty(deliverySearchDTO.getOrdNos())) {
			String[] ordNoArr = deliverySearchDTO.getOrdNos().split("\\r?\\n");
			for (String ordNo : ordNoArr) {
				ordNoList.add(ordNo.toUpperCase().trim());
            }
			deliverySearchDTO.setOrdNoList(ordNoList);
		}

		//다중검색
		if(StringService.isNotEmpty(deliverySearchDTO.getSearchNos())) {
			String[] searchNoArr = deliverySearchDTO.getSearchNos().split("\\r?\\n");
			for (String searchNo : searchNoArr) {
				searchNoList.add(searchNo.toUpperCase().trim());
			}
			deliverySearchDTO.setSearchNoList(searchNoList);
		}

		//주문유형
		if(StringService.isNotEmpty(deliverySearchDTO.getDrctTpCds())) {
			String[] drctTpCdArr = deliverySearchDTO.getDrctTpCds().split("\\,");
			for (String drctTpCd : drctTpCdArr) {
				drctTpCdList.add(drctTpCd.toUpperCase().trim());
			}
			deliverySearchDTO.setDrctTpCdList(drctTpCdList);
		}

		return deliverySelectRepository.getPoPartMallOrderGoodLackListExcel(deliverySearchDTO);
	}

	/**
	 * PO 입점업체 주문취소조회 excel.
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param systemPk [설명]
	 * @param deliverySearchDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 3. 30
	 */
	public List<Map<String, Object>> getPoPartMallOrderCancelListExcel(SystemPK systemPk, DeliverySearchDTO deliverySearchDTO) throws Exception {
		List<String> ordNoList = new ArrayList<String>();
		List<String> searchNoList = new ArrayList<String>();
		List<String> drctTpCdList = new ArrayList<String>();

		//주문번호 다중검색
		if(StringService.isNotEmpty(deliverySearchDTO.getOrdNos())) {
			String[] ordNoArr = deliverySearchDTO.getOrdNos().split("\\r?\\n");
			for (String ordNo : ordNoArr) {
				ordNoList.add(ordNo.toUpperCase().trim());
            }
			deliverySearchDTO.setOrdNoList(ordNoList);
		}

		//다중검색
		if(StringService.isNotEmpty(deliverySearchDTO.getSearchNos())) {
			String[] searchNoArr = deliverySearchDTO.getSearchNos().split("\\r?\\n");
			for (String searchNo : searchNoArr) {
				searchNoList.add(searchNo.toUpperCase().trim());
			}
			deliverySearchDTO.setSearchNoList(searchNoList);
		}

		//주문유형
		if(StringService.isNotEmpty(deliverySearchDTO.getDrctTpCds())) {
			String[] drctTpCdArr = deliverySearchDTO.getDrctTpCds().split("\\,");
			for (String drctTpCd : drctTpCdArr) {
				drctTpCdList.add(drctTpCd.toUpperCase().trim());
			}
			deliverySearchDTO.setDrctTpCdList(drctTpCdList);
		}

		return deliverySelectRepository.getPoPartMallOrderCancelListExcel(deliverySearchDTO);
	}

	/**
	 * 입점업체 결품접수 리스트 조회.
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param systemPk [설명]
	 * @param deliverySearchDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 3. 30
	 */
	public List<DeliveryOrderGoodResult> getPoPartMallOrderGoodLackList(SystemPK systemPk, DeliverySearchDTO deliverySearchDTO) throws Exception {
		List<String> ordNoList = new ArrayList<String>();
		List<String> searchNoList = new ArrayList<String>();
		List<String> drctTpCdList = new ArrayList<String>();

		//주문번호 다중검색
		if(StringService.isNotEmpty(deliverySearchDTO.getOrdNos())) {
			String[] ordNoArr = deliverySearchDTO.getOrdNos().split("\\r?\\n");
			for (String ordNo : ordNoArr) {
				ordNoList.add(ordNo.toUpperCase().trim());
            }
			deliverySearchDTO.setOrdNoList(ordNoList);
		}

		//다중검색
		if(StringService.isNotEmpty(deliverySearchDTO.getSearchNos())) {
			String[] searchNoArr = deliverySearchDTO.getSearchNos().split("\\r?\\n");
			for (String searchNo : searchNoArr) {
				searchNoList.add(searchNo.toUpperCase().trim());
			}
			deliverySearchDTO.setSearchNoList(searchNoList);
		}

		//주문유형
		if(StringService.isNotEmpty(deliverySearchDTO.getDrctTpCds())) {
			String[] drctTpCdArr = deliverySearchDTO.getDrctTpCds().split("\\,");
			for (String drctTpCd : drctTpCdArr) {
				drctTpCdList.add(drctTpCd.toUpperCase().trim());
			}
			deliverySearchDTO.setDrctTpCdList(drctTpCdList);
		}


		return deliverySelectRepository.getPoPartMallOrderGoodLackList(deliverySearchDTO);
	}


	/**
	 * 입점업체 결품접수 리스트 조회.
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param systemPk [설명]
	 * @param deliverySearchDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 3. 30
	 */
	public int getPoPartMallOrderGoodLackListCount(SystemPK systemPk, DeliverySearchDTO deliverySearchDTO) throws Exception {
		List<String> ordNoList = new ArrayList<String>();
		List<String> searchNoList = new ArrayList<String>();
		List<String> drctTpCdList = new ArrayList<String>();

		//주문번호 다중검색
		if(StringService.isNotEmpty(deliverySearchDTO.getOrdNos())) {
			String[] ordNoArr = deliverySearchDTO.getOrdNos().split("\\r?\\n");
			for (String ordNo : ordNoArr) {
				ordNoList.add(ordNo.toUpperCase().trim());
            }
			deliverySearchDTO.setOrdNoList(ordNoList);
		}

		//다중검색
		if(StringService.isNotEmpty(deliverySearchDTO.getSearchNos())) {
			String[] searchNoArr = deliverySearchDTO.getSearchNos().split("\\r?\\n");
			for (String searchNo : searchNoArr) {
				searchNoList.add(searchNo.toUpperCase().trim());
			}
			deliverySearchDTO.setSearchNoList(searchNoList);
		}

		//주문유형
		if(StringService.isNotEmpty(deliverySearchDTO.getDrctTpCds())) {
			String[] drctTpCdArr = deliverySearchDTO.getDrctTpCds().split("\\,");
			for (String drctTpCd : drctTpCdArr) {
				drctTpCdList.add(drctTpCd.toUpperCase().trim());
			}
			deliverySearchDTO.setDrctTpCdList(drctTpCdList);
		}

		return deliverySelectRepository.getPoPartMallOrderGoodLackListCount(deliverySearchDTO);
	}


	/**
	 * PO 입점업체 주문취소 목록 조회.
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param systemPk [설명]
	 * @param deliverySearchDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 3. 30
	 */
	public List<DeliveryOrderGoodResult> getPoPartMallOrderCancelList(SystemPK systemPk, DeliverySearchDTO deliverySearchDTO) throws Exception {
		List<String> ordNoList = new ArrayList<String>();
		List<String> searchNoList = new ArrayList<String>();
		List<String> drctTpCdList = new ArrayList<String>();

		//주문번호 다중검색
		if(StringService.isNotEmpty(deliverySearchDTO.getOrdNos())) {
			String[] ordNoArr = deliverySearchDTO.getOrdNos().split("\\r?\\n");
			for (String ordNo : ordNoArr) {
				ordNoList.add(ordNo.toUpperCase().trim());
            }
			deliverySearchDTO.setOrdNoList(ordNoList);
		}

		//다중검색
		if(StringService.isNotEmpty(deliverySearchDTO.getSearchNos())) {
			String[] searchNoArr = deliverySearchDTO.getSearchNos().split("\\r?\\n");
			for (String searchNo : searchNoArr) {
				searchNoList.add(searchNo.toUpperCase().trim());
			}
			deliverySearchDTO.setSearchNoList(searchNoList);
		}

		//주문유형
		if(StringService.isNotEmpty(deliverySearchDTO.getDrctTpCds())) {
			String[] drctTpCdArr = deliverySearchDTO.getDrctTpCds().split("\\,");
			for (String drctTpCd : drctTpCdArr) {
				drctTpCdList.add(drctTpCd.toUpperCase().trim());
			}
			deliverySearchDTO.setDrctTpCdList(drctTpCdList);
		}


		return deliverySelectRepository.getPoPartMallOrderCancelList(deliverySearchDTO);
	}


	/**
	 * PO 입점업체 주문취소 전체카운트 조회.
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param systemPk [설명]
	 * @param deliverySearchDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 3. 30
	 */
	public int getPoPartMallOrderCancelListCount(SystemPK systemPk, DeliverySearchDTO deliverySearchDTO) throws Exception {
		List<String> ordNoList = new ArrayList<String>();
		List<String> searchNoList = new ArrayList<String>();
		List<String> drctTpCdList = new ArrayList<String>();

		//주문번호 다중검색
		if(StringService.isNotEmpty(deliverySearchDTO.getOrdNos())) {
			String[] ordNoArr = deliverySearchDTO.getOrdNos().split("\\r?\\n");
			for (String ordNo : ordNoArr) {
				ordNoList.add(ordNo.toUpperCase().trim());
            }
			deliverySearchDTO.setOrdNoList(ordNoList);
		}

		//다중검색
		if(StringService.isNotEmpty(deliverySearchDTO.getSearchNos())) {
			String[] searchNoArr = deliverySearchDTO.getSearchNos().split("\\r?\\n");
			for (String searchNo : searchNoArr) {
				searchNoList.add(searchNo.toUpperCase().trim());
			}
			deliverySearchDTO.setSearchNoList(searchNoList);
		}

		//주문유형
		if(StringService.isNotEmpty(deliverySearchDTO.getDrctTpCds())) {
			String[] drctTpCdArr = deliverySearchDTO.getDrctTpCds().split("\\,");
			for (String drctTpCd : drctTpCdArr) {
				drctTpCdList.add(drctTpCd.toUpperCase().trim());
			}
			deliverySearchDTO.setDrctTpCdList(drctTpCdList);
		}

		return deliverySelectRepository.getPoPartMallOrderCancelListCount(deliverySearchDTO);
	}

	/**
	 * 판매제휴사 목록.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param systemPk [설명]
	 * @param deliverySearchDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 3. 30
	 */
	public List<Com> getCompanyList(SystemPK systemPk, DeliverySearchDTO deliverySearchDTO) throws Exception {
		return deliverySelectRepository.getCompanyList(deliverySearchDTO);
	}	

	
	/**
	 * 운송장상세.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param systemPk [설명]
	 * @param deliverySearchDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 3. 30
	 */
	public DeliveryInvoiceResult getInvoice(SystemPK systemPk, DeliverySearchDTO deliverySearchDTO) throws Exception {
		return deliverySelectRepository.getInvoice(deliverySearchDTO);
	}	
	
	
	/**
	 * 운송자 이력 목록.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param systemPk [설명]
	 * @param deliverySearchDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 3. 30
	 */
	public List<DeliveryInvoiceResult> getInvoiceHistoryList(SystemPK systemPk, DeliverySearchDTO deliverySearchDTO) throws Exception {
		return deliverySelectRepository.getInvoiceHistoryList(deliverySearchDTO);
	}
	
	
	/**
	 * 시리얼번호 중복체크.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 * @param systemPk [설명]
	 * @param deliverySearchDTO [설명]
	 * @return Int [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 28
	 */
	public int isUsedErpGodSnCnt(SystemPK systemPk, DeliverySearchDTO deliverySearchDTO) throws Exception {
		return deliverySelectRepository.isUsedErpGodSnCnt(deliverySearchDTO);
	}
	
	/**
	 * 복품출고 검수 리스트 조회.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param systemPk [설명]
	 * @param deliverySearchDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 3. 30
	 */
	public List<DeliveryOrderGoodResult> getGoodsInspectList(SystemPK systemPk, DeliverySearchDTO deliverySearchDTO) throws Exception {
		return deliverySelectRepository.getGoodsInspectList(deliverySearchDTO);
	}
	
	/**
	 * 상품회수처리 조회 조회.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param systemPk [설명]
	 * @param deliverySearchDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 3. 30
	 */
	public List<DeliveryClaimGoodResult> getCompleteRetrievalList(SystemPK systemPk, DeliverySearchDTO deliverySearchDTO) throws Exception {
		return deliverySelectRepository.getCompleteRetrievalList(deliverySearchDTO);
	}
	
	/**
	 * 자사상품 회수리스트 조회 row count.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param systemPk [설명]
	 * @param deliverySearchDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 4. 6
	 */
	public int getRetrievalGoodsListCount(SystemPK systemPk, DeliverySearchDTO deliverySearchDTO) throws Exception {
		List<String> ordNoList = new ArrayList<String>();
		List<String> paramList = new ArrayList<String>();
		List<String> rtrvlDrctTpCdList = new ArrayList<String>();
		
		//주문번호 다중검색
		if(StringService.isNotEmpty(deliverySearchDTO.getOrdNos())) {
			String[] ordNoArr = deliverySearchDTO.getOrdNos().split("\n");
			for (String ordNo : ordNoArr) {
				ordNoList.add(ordNo.toUpperCase().trim());
            }
			deliverySearchDTO.setOrdNoList(ordNoList);
		}
		
		//송장번호/크레임번호 다중검색
		if(StringService.isNotEmpty(deliverySearchDTO.getParams())) {
			String[] paramArr = deliverySearchDTO.getParams().split("\n");
			for (String param : paramArr) {
				paramList.add(param.toUpperCase().trim());
			}
			deliverySearchDTO.setParamList(paramList);
		}
		
		//회수구분
		if(StringService.isNotEmpty(deliverySearchDTO.getRtrvlDrctTpCds())) {
			String[] rtrvlDrctTpCdArr = deliverySearchDTO.getRtrvlDrctTpCds().split("\\,");
			for (String rtrvlDrctTpCd : rtrvlDrctTpCdArr) {
				rtrvlDrctTpCdList.add(rtrvlDrctTpCd.toUpperCase().trim());
			}
			deliverySearchDTO.setRtrvlDrctTpCdList(rtrvlDrctTpCdList);
		}
		
		return deliverySelectRepository.getRetrievalGoodsListCount(deliverySearchDTO);
	}
	
	
	
	
	/**
	 * 사은품 출고관리조회
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param systemPk [설명]
	 * @param deliverySearchDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 3. 30
	 */
	public List<DeliveryOrderGoodResult> getGiftDrctGoodsList(SystemPK systemPk, DeliverySearchDTO deliverySearchDTO) throws Exception {
		List<String> searchNoList = new ArrayList<String>();
		
		//다중검색
		if(StringService.isNotEmpty(deliverySearchDTO.getSearchNos())) {
			String[] searchNoArr = deliverySearchDTO.getSearchNos().split("\\r?\\n");
			for (String searchNo : searchNoArr) {
				searchNoList.add(searchNo.toUpperCase().trim());
			}
			deliverySearchDTO.setSearchNoList(searchNoList);
		}
		
		return deliverySelectRepository.getGiftDrctGoodsList(deliverySearchDTO);
	}
	
	
	/**
	 * 사은품 출고관리조회 rowcount
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param systemPk [설명]
	 * @param deliverySearchDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 3. 30
	 */
	public int getGiftDrctGoodsListCount(SystemPK systemPk, DeliverySearchDTO deliverySearchDTO) throws Exception {
		List<String> searchNoList = new ArrayList<String>();
		
		//다중검색
		if(StringService.isNotEmpty(deliverySearchDTO.getSearchNos())) {
			String[] searchNoArr = deliverySearchDTO.getSearchNos().split("\\r?\\n");
			for (String searchNo : searchNoArr) {
				searchNoList.add(searchNo.toUpperCase().trim());
			}
			deliverySearchDTO.setSearchNoList(searchNoList);
		}
		
		return deliverySelectRepository.getGiftDrctGoodsListCount(deliverySearchDTO);
	}	
	
	
	
	/**
	 * 사은품 출고관리조회 excel
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param systemPk [설명]
	 * @param deliverySearchDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 3. 30
	 */
	public  List<Map<String, Object>> getGiftDrctGoodsListExcel(SystemPK systemPk, DeliverySearchDTO deliverySearchDTO) throws Exception {
		List<String> searchNoList = new ArrayList<String>();
		
		//다중검색
		if(StringService.isNotEmpty(deliverySearchDTO.getSearchNos())) {
			String[] searchNoArr = deliverySearchDTO.getSearchNos().split("\\r?\\n");
			for (String searchNo : searchNoArr) {
				searchNoList.add(searchNo.toUpperCase().trim());
			}
			deliverySearchDTO.setSearchNoList(searchNoList);
		}
		
		return deliverySelectRepository.getGiftDrctGoodsListExcel(deliverySearchDTO);
	}
	
	
	/**
	 * 사은품 Picking List
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param systemPk [설명]
	 * @param deliverySearchDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 3. 30
	 */
	public  List<DeliveryOrderGoodResult> getGiftDrctGoodsPickingList(SystemPK systemPk, DeliverySearchDTO deliverySearchDTO) throws Exception {
		List<String> ordNoList = new ArrayList<String>();
		List<String> searchNoList = new ArrayList<String>();
		List<String> brndIdNoList = new ArrayList<String>();
		List<String> dlivyDrctGrpDgreList = new ArrayList<String>();
		List<String> assignTypeList = new ArrayList<String>();
		
		
		//브랜드 다중검색
		if(StringService.isNotEmpty(deliverySearchDTO.getBrndIds())) {
			String[] brndIdArr = deliverySearchDTO.getBrndIds().split("\\,");
			for (String brndId : brndIdArr) {
				brndIdNoList.add(brndId.toUpperCase().trim());
			}
			deliverySearchDTO.setBrndIdList(brndIdNoList);
		}
		
		//주문번호 다중검색
		if(StringService.isNotEmpty(deliverySearchDTO.getOrdNos())) {
			String[] ordNoArr = deliverySearchDTO.getOrdNos().split("\\r?\\n");
			for (String ordNo : ordNoArr) {
				ordNoList.add(ordNo.toUpperCase().trim());
			}
			deliverySearchDTO.setOrdNoList(ordNoList);
		}
		
		//다중검색
		if(StringService.isNotEmpty(deliverySearchDTO.getSearchNos())) {
			String[] searchNoArr = deliverySearchDTO.getSearchNos().split("\\r?\\n");
			for (String searchNo : searchNoArr) {
				searchNoList.add(searchNo.toUpperCase().trim());
			}
			deliverySearchDTO.setSearchNoList(searchNoList);
		}
		
		//차수 다중검색
		if(StringService.isNotEmpty(deliverySearchDTO.getDlivyDrctGrpDgres())) {
			String[] dlivyDrctGrpDgreArr = deliverySearchDTO.getDlivyDrctGrpDgres().split("\\,");
			for (String dlivyDrctGrpDgre : dlivyDrctGrpDgreArr) {
				dlivyDrctGrpDgreList.add(dlivyDrctGrpDgre.toUpperCase().trim());
			}
			deliverySearchDTO.setDlivyDrctGrpDgreList(dlivyDrctGrpDgreList);
		}
		
		//배송유형
		if(StringService.isNotEmpty(deliverySearchDTO.getAssignType())) {
			String[] assignTypeArr = deliverySearchDTO.getAssignType().split("\\,");
			for (String assignType : assignTypeArr) {
				assignTypeList.add(assignType.toUpperCase().trim());
				
				if(assignType.toUpperCase().trim().equals("AUTO_ASGN")){ // 자동배정일 경우 강제배정 포함
					assignTypeList.add("ENFRC_ASGN"); // 강제배정
				}
			}
			deliverySearchDTO.setAssignTypeList(assignTypeList);
		}
		
		return deliverySelectRepository.getGiftDrctGoodsPickingList(deliverySearchDTO);
	}	
	
	/**
	 * 자사상품 회수리스트 조회 excel.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param systemPk [설명]
	 * @param deliverySearchDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 4. 6
	 */
	public List<Map<String, Object>> getRetrievalGoodsListExcel(SystemPK systemPk, DeliverySearchDTO deliverySearchDTO) throws Exception {
		List<String> ordNoList = new ArrayList<String>();
		List<String> paramList = new ArrayList<String>();
		List<String> rtrvlDrctTpCdList = new ArrayList<String>();
		
		//주문번호 다중검색
		if(StringService.isNotEmpty(deliverySearchDTO.getOrdNos())) {
			String[] ordNoArr = deliverySearchDTO.getOrdNos().split("\n");
			for (String ordNo : ordNoArr) {
				ordNoList.add(ordNo.toUpperCase().trim());
            }
			deliverySearchDTO.setOrdNoList(ordNoList);
		}
		
		//송장번호/크레임번호 다중검색
		if(StringService.isNotEmpty(deliverySearchDTO.getParams())) {
			String[] paramArr = deliverySearchDTO.getParams().split("\n");
			for (String param : paramArr) {
				paramList.add(param.toUpperCase().trim());
			}
			deliverySearchDTO.setParamList(paramList);
		}
		
		//회수구분
		if(StringService.isNotEmpty(deliverySearchDTO.getRtrvlDrctTpCds())) {
			String[] rtrvlDrctTpCdArr = deliverySearchDTO.getRtrvlDrctTpCds().split("\\,");
			for (String rtrvlDrctTpCd : rtrvlDrctTpCdArr) {
				rtrvlDrctTpCdList.add(rtrvlDrctTpCd.toUpperCase().trim());
			}
			deliverySearchDTO.setRtrvlDrctTpCdList(rtrvlDrctTpCdList);
		}
		
		return deliverySelectRepository.getRetrievalGoodsListExcel(deliverySearchDTO);
	}	
	
	/**
	 * 상품출고배정 조회 rowcount.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param systemPk [설명]
	 * @param deliverySearchDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 3. 30
	 */
	public int getSelfAssignDeliveryListCount(SystemPK systemPk, DeliverySearchDTO deliverySearchDTO) throws Exception {
		List<String> paramList = new ArrayList<String>();
		List<String> brndIdList = new ArrayList<String>();
		
		//주문/상품번호 다중검색
		if(StringService.isNotEmpty(deliverySearchDTO.getParams())) {
			String[] paramArr = deliverySearchDTO.getParams().split("\n");
			for (String param : paramArr) {
				paramList.add(param.toUpperCase().trim());
			}
			deliverySearchDTO.setParamList(paramList);
		}
		
		//브랜드ID 다중검색
		if(StringService.isNotEmpty(deliverySearchDTO.getBrndIds())) {
			String[] brndIdArr = deliverySearchDTO.getBrndIds().split(",");
			for (String brndId : brndIdArr) {
				brndIdList.add(brndId.toUpperCase().trim());
            }
			deliverySearchDTO.setBrndIdList(brndIdList);
		}
		
		return deliverySelectRepository.getSelfAssignDeliveryListCount(deliverySearchDTO);
	}
	
	/**
	 * 브랜드 리스트 조회.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param systemPk [설명]
	 * @param dlvShopId [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 8. 18
	 */
	public List<DeliveryInvoiceResult> selectShopBrndList(SystemPK systemPk, DeliverySearchDTO deliverySearchDTO) throws Exception {
		return deliverySelectRepository.selectShopBrndList(deliverySearchDTO);
	}

	public List<DeliveryAffDTO> selectFrgnDeliveryList(String ordNo){
		return deliverySelectRepository.selectFrgnDeliveryList(ordNo);
	}

	/**
	 * 클레임 운송장상세.
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param systemPk [설명]
	 * @param deliverySearchDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 3. 30
	 */
	public DeliveryInvoiceResult getClmInvoice(SystemPK systemPk, DeliverySearchDTO deliverySearchDTO) throws Exception {
		return deliverySelectRepository.getClmInvoice(deliverySearchDTO);
	}


	/**
	 * 클레임 운송자 이력 목록.
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param systemPk [설명]
	 * @param deliverySearchDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 3. 30
	 */
	public List<DeliveryInvoiceResult> getClmInvoiceHistoryList(SystemPK systemPk, DeliverySearchDTO deliverySearchDTO) throws Exception {
		return deliverySelectRepository.getClmInvoiceHistoryList(deliverySearchDTO);
	}
	
	/**
     * 매장조회
     * @param vendorBrndSearchDTO
     * @return
     */
    public List<VendorBrndListResult> selectAssignDlvShop(VendorBrndSearchDTO vendorBrndSearchDTO) {
		return deliverySelectRepository.selectAssignDlvShop(vendorBrndSearchDTO.getSysShop());
	}
    
    
    /**
     * 상품 검수정보 조회.
     *
     * @param systemPk the system pk
     * @param deliverySearchDTO the delivery search dto
     * @return the list
     * @throws Exception the exception
     */
    public List<DeliveryOrderGoodResult> selectItemCheckList(SystemPK systemPk, DeliverySearchDTO deliverySearchDTO) throws Exception {
		return deliverySelectRepository.selectItemCheckList(deliverySearchDTO);
	}

    
    public List<LgsDlivyDrctGod> selectLgsDlivyDrctGod(String ordNo) throws Exception {
		return deliverySelectRepository.selectLgsDlivyDrctGod(ordNo);
	}

	/**
	 * 온라인 주문 반품 리스트 조회.
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param systemPk
	 * @param deliverySearchDTO
	 * @return List
	 * @throws Exception the exception
	 * @since 2016. 7. 27
	 */
	public List<DeliveryOrderGoodResult> getOfflineReturnList(SystemPK systemPk, DeliverySearchDTO deliverySearchDTO) throws Exception {
		String sysShopId = BOSecurityUtil.getShopId();
		deliverySearchDTO.setSysShopId(sysShopId);
		return deliverySelectRepository.getOfflineReturnList(deliverySearchDTO);
	}


	/**
	 * 시리얼번호 리스트
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param deliverySearchDTO
	 * @return List
	 * @throws Exception the exception
	 * @since 2016. 8. 3
	 */
	public List<String> getOrdTurnErpGodSnList(DeliverySearchDTO deliverySearchDTO) throws Exception {
		return deliverySelectRepository.getOrdTurnErpGodSnList(deliverySearchDTO);
	}

	/**
	 *  매장별 취급 브랜드 리스트
	 *
	 * @param String shopId
	 * @return List
	 * @throws Exception the exception
	 * @since 2016. 8. 12
	 */
	public List<SysShopExtends> getBrndList(String shopId) throws Exception {
		return deliverySelectRepository.getBrndList(shopId);
	}

	/**
	 * #50212 [개발]픽업 재진열 대상 알림(매장 Dashboard) 기능 추가
	 * 	- 픽업 재진열 리스트 조회
	 *
	 * @param dlvShopId 매장매장ID
	 * @return 픽업 재진열 리스트
	 * @throws Exception
	 */
	public List<DeliveryRedisplayResult> getDeliveryRedisplayList(String dlvShopId) throws Exception {
		return deliverySelectRepository.getDeliveryRedisplayList(dlvShopId);
	}

	/**
	 * [FO] 상품 배송조회.
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param systemPk [설명]
	 * @param deliverySearchDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2017. 9. 28
	 */
	public List<DeliveryOrderGoodResult> getDeliveryListFO(SystemPK systemPk, DeliverySearchDTO deliverySearchDTO) throws Exception {
		return deliverySelectRepository.getDeliveryListFO(deliverySearchDTO);
	}
	
}
