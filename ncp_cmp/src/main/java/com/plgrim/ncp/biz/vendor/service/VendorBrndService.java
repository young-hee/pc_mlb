/* Copyright (c) 2013 plgrim, Inc.
 * All right reserved.
 * http://plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 *
 * Revision History
 * Author              Date                         Description
 * ------------------   --------------                  ------------------
 *
 */
package com.plgrim.ncp.biz.vendor.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.plgrim.ncp.base.abstracts.AbstractService;
import com.plgrim.ncp.base.entities.RepositoryHelper;
import com.plgrim.ncp.base.entities.datasource1.sys.SysBrnd;
import com.plgrim.ncp.base.entities.datasource1.sys.SysBrndPrdlst;
import com.plgrim.ncp.base.entities.datasource1.sys.SysBrndPrdlstHist;
import com.plgrim.ncp.base.entities.datasource1.sys.SysShopBrnd;
import com.plgrim.ncp.base.entities.datasource1.sys.SysShopBrndFeeHist;
import com.plgrim.ncp.base.entities.datasource1.sys.SysShopEvt;
import com.plgrim.ncp.biz.brand.data.BrandResultDTO;
import com.plgrim.ncp.biz.vendor.data.ShopSearchDTO;
import com.plgrim.ncp.biz.vendor.data.VendorBrndRegDTO;
import com.plgrim.ncp.biz.vendor.data.VendorBrndSearchDTO;
import com.plgrim.ncp.biz.vendor.repository.VendorBrndRepository;
import com.plgrim.ncp.biz.vendor.result.VendorBrndEvtResult;
import com.plgrim.ncp.biz.vendor.result.VendorBrndListResult;
import com.plgrim.ncp.commons.util.BOSecurityUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Mark
 *
 */
@Service
@Slf4j
public class VendorBrndService extends AbstractService {
 
	/**
	 * 브랜드 매장관리 Repository
	 */
	@Autowired
	private VendorBrndRepository vendorBrndRepository;

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
	 * 브랜드 매장내역 조회 건수
	 * @param vendorBrndSearchDTO
	 * @return long
	 * @throws Exception
	 */
	public long selectVendorBrndListCount(VendorBrndSearchDTO vendorBrndSearchDTO) throws Exception {
		return vendorBrndRepository.selectVendorBrndListCount(vendorBrndSearchDTO);
	}


	/**
	 * 브랜드 매장내역 조회
	 * @param vendorBrndSearchDTO
	 * @return Page<VendorBrndListResult>
	 * @throws Exception
	 */
	public Page<VendorBrndListResult> selectVendorBrndList(VendorBrndSearchDTO vendorBrndSearchDTO) throws Exception {

		RepositoryHelper.makePageEntityIndex(vendorBrndSearchDTO, vendorBrndSearchDTO.getPageParam());	// 페이지 설정

		// 목록 건수 조회
		long totalRow = vendorBrndRepository.selectVendorBrndListCount(vendorBrndSearchDTO);

		// 목록 조회
		List<VendorBrndListResult> results = new ArrayList<VendorBrndListResult>();
		if(totalRow > 0){
			results = vendorBrndRepository.selectVendorBrndList(vendorBrndSearchDTO);
		}

		return new PageImpl<VendorBrndListResult>(results, vendorBrndSearchDTO.getPageParam().getPageable(), totalRow);
	}

	/**
	 * 브랜드 목록 조회(콤보박스)
	 * @return List<SysBrnd>
	 * @throws Exception
	 */
	public List<SysBrnd> selectSysBrndCombo() throws Exception {
		return vendorBrndRepository.selectSysBrndCombo();
	}

	/**
	 * 브랜드 매장내역 엑셀
	 * @param vendorBrndSearchDTO
	 * @return List<Map<String, Object>>
	 * @throws Exception
	 */
	public List<Map<String, Object>> selectVendorBrndListExcel(VendorBrndSearchDTO vendorBrndSearchDTO) throws Exception{
		return vendorBrndRepository.selectVendorBrndListExcel(vendorBrndSearchDTO);
	}

	/**
	 * 브랜드 매장내역 리스트 수정
	 * @param updateList
	 * @return int
	 * @throws Exception
	 */
	public void updateVendorShopBrndList(List<VendorBrndListResult> updateList) throws Exception{
		String loginId = BOSecurityUtil.getLoginId();

		try {
			if(updateList != null){
				for(VendorBrndListResult vendorBrndListResult: updateList) {
					vendorBrndListResult.getSysShopBrnd().setUdterId(loginId);
					vendorBrndListResult.getSysShopBrnd().setShopId(vendorBrndListResult.getSysShop().getShopId());
					vendorBrndListResult.getSysShopBrnd().setErpBrndId(vendorBrndListResult.getSysBrnd().getErpBrndId());

					SysShopBrnd sysShopBrnd=new SysShopBrnd();
					sysShopBrnd.setShopId(vendorBrndListResult.getSysShop().getShopId());
					sysShopBrnd.setErpBrndId(vendorBrndListResult.getSysBrnd().getErpBrndId());
					VendorBrndListResult vendorBrndListResultOld = new VendorBrndListResult();
					vendorBrndListResultOld = vendorBrndRepository.selectSysShopBrand(sysShopBrnd);

					// 브랜드 매장정보 중 배송매장여부, 픽업매장여부, 사용여부 수정 시 플래그 처리
					SysShopBrnd sysShopBrndOld = null;
					if (vendorBrndListResultOld != null) {	// 수정 전 현재 정보 null 체크
						sysShopBrndOld = vendorBrndListResultOld.getSysShopBrnd();
					}
					
					vendorBrndRepository.updateSysShopBrndList(vendorBrndListResult.getSysShopBrnd());

					/* START : 이력저장 시작 */
					/* 201712 퀵배송여부 추가됨에 따라 수수료 이력을 변경함. 단, 재고연동대상 체크를 위한 이력은 변경 안함(운영팀 요청사항) */
					/* 기존에 이력이 있는 경우 */
					if(null !=vendorBrndListResultOld){
						/* 배송매장여부가 이전과 틀릴 경우 */
						if(!(vendorBrndListResultOld.getSysShopBrnd().getDlvShopYn()).equals(vendorBrndListResult.getSysShopBrnd().getDlvShopYn())){
							/* 배송매장여부가 Y->N으로 변경할 경우 */
							if(vendorBrndListResult.getSysShopBrnd().getDlvShopYn().equals("N")){
								SysShopBrndFeeHist sysShopBrndFeeHist=new SysShopBrndFeeHist();
								sysShopBrndFeeHist.setShopId(vendorBrndListResult.getSysShop().getShopId());
								sysShopBrndFeeHist.setErpBrndId(vendorBrndListResult.getSysBrnd().getErpBrndId());
								sysShopBrndFeeHist.setFeeHistSectCd("DLV_SHOP_FEE");
								sysShopBrndFeeHist.setUdterId(loginId);
								vendorBrndRepository.updateSysShopBrndFeeHist(sysShopBrndFeeHist);
								/* 배송매장여부가 N->Y으로 변경할 경우 */
							}else{

								SysShopBrndFeeHist sysShopBrndFeeHist=new SysShopBrndFeeHist();
								sysShopBrndFeeHist.setShopId(vendorBrndListResult.getSysShop().getShopId());
								sysShopBrndFeeHist.setErpBrndId(vendorBrndListResult.getSysBrnd().getErpBrndId());
								sysShopBrndFeeHist.setFeeHistSectCd("DLV_SHOP_FEE");
								sysShopBrndFeeHist.setModBfShopFeeSectCd(vendorBrndListResultOld.getSysShopBrnd().getDlvShopFeeSectCd());
								sysShopBrndFeeHist.setModBfShopFeeAmt(vendorBrndListResultOld.getSysShopBrnd().getDlvShopFeeAmt());
								sysShopBrndFeeHist.setModBfShopFeeRt(vendorBrndListResultOld.getSysShopBrnd().getDlvShopFeeRt());
								sysShopBrndFeeHist.setUdterId(loginId);
								vendorBrndRepository.insertSysShopBrndFeeHist(sysShopBrndFeeHist);
							}
						}
						/* 픽업매장여부가 이전과 틀릴 경우 */
						if(!(vendorBrndListResultOld.getSysShopBrnd().getPkupShopYn()).equals(vendorBrndListResult.getSysShopBrnd().getPkupShopYn())){
							/* 픽업매장여부가 Y->N으로 변경할 경우 */
							if(vendorBrndListResult.getSysShopBrnd().getPkupShopYn().equals("N")){
								/* 픽업매장*/
								SysShopBrndFeeHist sysShopBrndFeeHist=new SysShopBrndFeeHist();
								sysShopBrndFeeHist.setShopId(vendorBrndListResult.getSysShop().getShopId());
								sysShopBrndFeeHist.setErpBrndId(vendorBrndListResult.getSysBrnd().getErpBrndId());
								sysShopBrndFeeHist.setUdterId(loginId);
								sysShopBrndFeeHist.setFeeHistSectCd("PKUP_SHOP_FEE");
								vendorBrndRepository.updateSysShopBrndFeeHist(sysShopBrndFeeHist);
								/* 픽업물류매장*/
								sysShopBrndFeeHist.setFeeHistSectCd("PKUP_SHOP_LGS_FEE");
								vendorBrndRepository.updateSysShopBrndFeeHist(sysShopBrndFeeHist);
								/* 픽업매장여부가 N->Y으로 변경할 경우 */
							}else{
								/* 픽업매장*/
								SysShopBrndFeeHist sysShopBrndFeeHist=new SysShopBrndFeeHist();
								sysShopBrndFeeHist.setShopId(vendorBrndListResult.getSysShop().getShopId());
								sysShopBrndFeeHist.setErpBrndId(vendorBrndListResult.getSysBrnd().getErpBrndId());
								sysShopBrndFeeHist.setUdterId(loginId);
								sysShopBrndFeeHist.setFeeHistSectCd("PKUP_SHOP_FEE");
								sysShopBrndFeeHist.setModBfShopFeeSectCd(vendorBrndListResultOld.getSysShopBrnd().getPkupShopFeeSectCd());
								sysShopBrndFeeHist.setModBfShopFeeAmt(vendorBrndListResultOld.getSysShopBrnd().getPkupShopFeeAmt());
								sysShopBrndFeeHist.setModBfShopFeeRt(vendorBrndListResultOld.getSysShopBrnd().getPkupShopFeeRt());
								vendorBrndRepository.insertSysShopBrndFeeHist(sysShopBrndFeeHist);

								/* 픽업물류매장*/
								/*
								if(vendorBrndListResult.getSysShopBrnd().getPkupShopLgsYn().equals("Y")){
									sysShopBrndFeeHist.setFeeHistSectCd("PKUP_SHOP_LGS_FEE");
									vendorBrndRepository.insertSysShopBrndFeeHist(sysShopBrndFeeHist);
								}
								*/
							}
						}
						/* 퀵배송매장여부가 이전과 다른 경우 */
						if( !StringUtils.equals(vendorBrndListResultOld.getSysShopBrnd().getQdlvShopYn(), vendorBrndListResult.getSysShopBrnd().getQdlvShopYn()) ) {
							/* 퀵배송매장여부를 Y->N으로 변경할 경우 */
							if( "N".equals(vendorBrndListResult.getSysShopBrnd().getQdlvShopYn()) ) {
								SysShopBrndFeeHist sysShopBrndFeeHist=new SysShopBrndFeeHist();
								sysShopBrndFeeHist.setShopId(vendorBrndListResult.getSysShop().getShopId());
								sysShopBrndFeeHist.setErpBrndId(vendorBrndListResult.getSysBrnd().getErpBrndId());
								sysShopBrndFeeHist.setFeeHistSectCd("QDLV_SHOP_FEE");
								sysShopBrndFeeHist.setUdterId(loginId);
								vendorBrndRepository.updateSysShopBrndFeeHist(sysShopBrndFeeHist);

							/* 퀵배송매장여부를 N->Y으로 변경할 경우 */
							}else{
								SysShopBrndFeeHist sysShopBrndFeeHist=new SysShopBrndFeeHist();
								sysShopBrndFeeHist.setShopId(vendorBrndListResult.getSysShop().getShopId());
								sysShopBrndFeeHist.setErpBrndId(vendorBrndListResult.getSysBrnd().getErpBrndId());
								sysShopBrndFeeHist.setFeeHistSectCd("QDLV_SHOP_FEE");
								sysShopBrndFeeHist.setModBfShopFeeSectCd(vendorBrndListResultOld.getSysShopBrnd().getDlvShopFeeSectCd()); /* 퀵배송 수수료는 배송매장 수수료를 씀 */
								sysShopBrndFeeHist.setModBfShopFeeAmt(vendorBrndListResultOld.getSysShopBrnd().getDlvShopFeeAmt()); /* 퀵배송 수수료는 배송매장 수수료를 씀 */
								sysShopBrndFeeHist.setModBfShopFeeRt(vendorBrndListResultOld.getSysShopBrnd().getDlvShopFeeRt()); /* 퀵배송 수수료는 배송매장 수수료를 씀 */
								sysShopBrndFeeHist.setUdterId(loginId);
								vendorBrndRepository.insertSysShopBrndFeeHist(sysShopBrndFeeHist);
							}
						}
						/* 사용여부가 이전과 틀릴 경우 */
						if(!(vendorBrndListResultOld.getSysShopBrnd().getUseYn()).equals(vendorBrndListResult.getSysShopBrnd().getUseYn())){
							/* 사용여부가 Y->N으로 변경할 경우 */
							if(vendorBrndListResult.getSysShopBrnd().getUseYn().equals("N")){
								SysShopBrndFeeHist sysShopBrndFeeHist=new SysShopBrndFeeHist();
								sysShopBrndFeeHist.setShopId(vendorBrndListResult.getSysShop().getShopId());
								sysShopBrndFeeHist.setErpBrndId(vendorBrndListResult.getSysBrnd().getErpBrndId());
								sysShopBrndFeeHist.setUdterId(loginId);
								sysShopBrndFeeHist.setFeeHistSectCd("ALL");
								vendorBrndRepository.updateSysShopBrndFeeHist(sysShopBrndFeeHist);
								/* 사용여부가 N->Y으로 변경할 경우 */
							}else{
								/* 배송매장사용여부가 변경되지 않았는데 Y인 경우에 이력저장함 */
								if((vendorBrndListResultOld.getSysShopBrnd().getDlvShopYn()).equals(vendorBrndListResult.getSysShopBrnd().getDlvShopYn())){
									if(vendorBrndListResult.getSysShopBrnd().getDlvShopYn().equals("Y")){
										SysShopBrndFeeHist sysShopBrndFeeHist=new SysShopBrndFeeHist();
										sysShopBrndFeeHist.setShopId(vendorBrndListResult.getSysShop().getShopId());
										sysShopBrndFeeHist.setErpBrndId(vendorBrndListResult.getSysBrnd().getErpBrndId());
										sysShopBrndFeeHist.setFeeHistSectCd("DLV_SHOP_FEE");
										sysShopBrndFeeHist.setModBfShopFeeSectCd(vendorBrndListResultOld.getSysShopBrnd().getDlvShopFeeSectCd());
										sysShopBrndFeeHist.setModBfShopFeeAmt(vendorBrndListResultOld.getSysShopBrnd().getDlvShopFeeAmt());
										sysShopBrndFeeHist.setModBfShopFeeRt(vendorBrndListResultOld.getSysShopBrnd().getDlvShopFeeRt());
										sysShopBrndFeeHist.setUdterId(loginId);
										vendorBrndRepository.insertSysShopBrndFeeHist(sysShopBrndFeeHist);

									}
								}
								/* 픽업매장사용여부가 변경되지 않았는데 Y인 경우에 이력저장함 */
								if((vendorBrndListResultOld.getSysShopBrnd().getPkupShopYn()).equals(vendorBrndListResult.getSysShopBrnd().getPkupShopYn())){
									if(vendorBrndListResult.getSysShopBrnd().getPkupShopYn().equals("Y")){
										SysShopBrndFeeHist sysShopBrndFeeHist=new SysShopBrndFeeHist();
										sysShopBrndFeeHist.setShopId(vendorBrndListResult.getSysShop().getShopId());
										sysShopBrndFeeHist.setErpBrndId(vendorBrndListResult.getSysBrnd().getErpBrndId());
										sysShopBrndFeeHist.setFeeHistSectCd("PKUP_SHOP_FEE");
										sysShopBrndFeeHist.setModBfShopFeeSectCd(vendorBrndListResultOld.getSysShopBrnd().getDlvShopFeeSectCd());
										sysShopBrndFeeHist.setModBfShopFeeAmt(vendorBrndListResultOld.getSysShopBrnd().getDlvShopFeeAmt());
										sysShopBrndFeeHist.setModBfShopFeeRt(vendorBrndListResultOld.getSysShopBrnd().getDlvShopFeeRt());
										sysShopBrndFeeHist.setUdterId(loginId);
										vendorBrndRepository.insertSysShopBrndFeeHist(sysShopBrndFeeHist);

										/* 픽업물류매장*/
										/*
										if(vendorBrndListResult.getSysShopBrnd().getPkupShopLgsYn().equals("Y")){
											sysShopBrndFeeHist.setFeeHistSectCd("PKUP_SHOP_LGS_FEE");
											vendorBrndRepository.insertSysShopBrndFeeHist(sysShopBrndFeeHist);
										}
										*/
									}
								}

								/* 퀵배송매장사용여부가 변경되지 않았고, 퀵배송매장사용여부가 Y인 경우에 이력 저장함 */
								if( StringUtils.equals(vendorBrndListResultOld.getSysShopBrnd().getQdlvShopYn(), vendorBrndListResult.getSysShopBrnd().getQdlvShopYn()) ) {
									if( "Y".equals(vendorBrndListResult.getSysShopBrnd().getQdlvShopYn()) ) {
										SysShopBrndFeeHist sysShopBrndFeeHist=new SysShopBrndFeeHist();
										sysShopBrndFeeHist.setShopId(vendorBrndListResult.getSysShop().getShopId());
										sysShopBrndFeeHist.setErpBrndId(vendorBrndListResult.getSysBrnd().getErpBrndId());
										sysShopBrndFeeHist.setFeeHistSectCd("QDLV_SHOP_FEE");
										sysShopBrndFeeHist.setModBfShopFeeSectCd(vendorBrndListResultOld.getSysShopBrnd().getDlvShopFeeSectCd()); /* 퀵배송 수수료는 배송매장 수수료를 씀 */
										sysShopBrndFeeHist.setModBfShopFeeAmt(vendorBrndListResultOld.getSysShopBrnd().getDlvShopFeeAmt()); /* 퀵배송 수수료는 배송매장 수수료를 씀 */
										sysShopBrndFeeHist.setModBfShopFeeRt(vendorBrndListResultOld.getSysShopBrnd().getDlvShopFeeRt()); /* 퀵배송 수수료는 배송매장 수수료를 씀 */
										sysShopBrndFeeHist.setUdterId(loginId);
										vendorBrndRepository.insertSysShopBrndFeeHist(sysShopBrndFeeHist);
									}
								}

							}
						}

						/* 기존에 이력이 없는 경우 */
					}else{

						SysShopBrndFeeHist sysShopBrndFeeHist=new SysShopBrndFeeHist();
						sysShopBrndFeeHist.setShopId(vendorBrndListResult.getSysShop().getShopId());
						sysShopBrndFeeHist.setErpBrndId(vendorBrndListResult.getSysBrnd().getErpBrndId());
						sysShopBrndFeeHist.setUdterId(loginId);

//						sysShopBrndFeeHist.setModBfShopFeeSectCd(vendorBrndListResultOld.getSysShopBrnd().getDlvShopFeeSectCd());
//						sysShopBrndFeeHist.setModBfShopFeeAmt(vendorBrndListResultOld.getSysShopBrnd().getDlvShopFeeAmt());
//						sysShopBrndFeeHist.setModBfShopFeeRt(vendorBrndListResultOld.getSysShopBrnd().getDlvShopFeeRt());
						if(vendorBrndListResult.getSysShopBrnd().getDlvShopYn().equals("Y")){
							sysShopBrndFeeHist.setFeeHistSectCd("DLV_SHOP_FEE");
							vendorBrndRepository.insertSysShopBrndFeeHist(sysShopBrndFeeHist);
						}

						if(vendorBrndListResult.getSysShopBrnd().getPkupShopYn().equals("Y")){
							sysShopBrndFeeHist.setFeeHistSectCd("PKUP_SHOP_FEE");
							vendorBrndRepository.insertSysShopBrndFeeHist(sysShopBrndFeeHist);
							
							/* 픽업물류매장*/
							/*
							if(vendorBrndListResult.getSysShopBrnd().getPkupShopLgsYn().equals("Y")){
								sysShopBrndFeeHist.setFeeHistSectCd("PKUP_SHOP_LGS_FEE");
								vendorBrndRepository.insertSysShopBrndFeeHist(sysShopBrndFeeHist);
							}
							*/
						}

						if( "Y".equals(vendorBrndListResult.getSysShopBrnd().getQdlvShopYn()) ) {
							sysShopBrndFeeHist.setFeeHistSectCd("QDLV_SHOP_FEE");
							vendorBrndRepository.insertSysShopBrndFeeHist(sysShopBrndFeeHist);
						}
					}
					/* END : 이력 저장 끝 */
					
					try {
						if (StringUtils.isNotEmpty(sysShopBrndOld.getDlvShopYn())
								&& StringUtils.isNotEmpty(sysShopBrndOld.getPkupShopYn())
								&& StringUtils.isNotEmpty(sysShopBrndOld.getUseYn())) {
							// 수정 전 현재 배송매장여부, 픽업매장여부, 사용여부 null 체크
							
							if (!StringUtils.equals(sysShopBrndOld.getDlvShopYn(), vendorBrndListResult.getSysShopBrnd().getDlvShopYn())
									|| !StringUtils.equals(sysShopBrndOld.getPkupShopYn(), vendorBrndListResult.getSysShopBrnd().getPkupShopYn())
									|| !StringUtils.equals(sysShopBrndOld.getUseYn(), vendorBrndListResult.getSysShopBrnd().getUseYn())) {
								// 수정 전 현재 값들과 수정 할 값들을 비교하여 하나라도 다르면 플래그 처리, 재고연동대상 처리를 위해
								
//								vendorBrndRepository.mergeSysShopBrndModifyFlag(vendorBrndListResult.getSysShopBrnd());
//								시스템 매장 브랜드 '배송매장여부', '픽업매장여부', '사용여부' 수정 시 플래그 처리 
							}
						}
						
					} catch (Exception e) {
				    	// 저장에 실패하면 오류로그만 남기고 처리하도록
						log.error(e.getMessage(), e);
					}
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 브랜드 매장 등록
	 * @param vendorBrndRegDTO
	 * @throws Exception
	 */
	public void insertSysShopBrnd(VendorBrndRegDTO vendorBrndRegDTO) throws Exception{
		vendorBrndRepository.insertSysShopBrnd(vendorBrndRegDTO.getSysShopBrnd());
		
		try {
			// 최초등록시에 변경 플래그 저장. 재고연동대상 처리를 위해
//			vendorBrndRepository.mergeSysShopBrndModifyFlag(vendorBrndRegDTO.getSysShopBrnd());
//			시스템 매장 브랜드 '배송매장여부', '픽업매장여부', '사용여부' 수정 시 플래그 처리 
		} catch (Exception e) {
	    	// 저장에 실패하면 오류로그만 남기고 처리하도록
			log.error(e.getMessage(), e);
		}
		
	}

	/**
	 * 브랜드 매장 기본정보 상세 수정(팝업)
	 * @param vendorBrndRegDTO
	 * @throws Exception
	 */
	public void updateSysShopBrnd(VendorBrndRegDTO vendorBrndRegDTO) throws Exception{
		String loginId = vendorBrndRegDTO.getSysShopBrnd().getUdterId();

		try {

			SysShopBrnd sysShopBrnd=new SysShopBrnd();
			sysShopBrnd.setShopId(vendorBrndRegDTO.getSysShopBrnd().getShopId());
			sysShopBrnd.setErpBrndId(vendorBrndRegDTO.getSysShopBrnd().getErpBrndId());
			VendorBrndListResult vendorBrndListResultOld = new VendorBrndListResult();
			vendorBrndListResultOld = vendorBrndRepository.selectSysShopBrand(sysShopBrnd);

			SysShopBrnd sysShopBrndOld = null;
			if (vendorBrndListResultOld != null) {	// 수정 전 현재 정보 null 체크
				sysShopBrndOld = vendorBrndListResultOld.getSysShopBrnd();
			}
			
			/* SYS_SHOP_BRND 저장*/
			vendorBrndRepository.updateSysShopBrnd(vendorBrndRegDTO.getSysShopBrnd());

			/* START : 이력저장 시작 */
			/* 201712 퀵배송여부 추가됨에 따라 수수료 이력을 변경함. 단, 재고연동대상 체크를 위한 이력은 변경 안함(운영팀 요청사항) */
			/* 기존에 이력이 있는 경우 */
			if(null !=vendorBrndListResultOld){
				/* 배송매장여부가 이전과 틀릴 경우 */
				if(!(vendorBrndListResultOld.getSysShopBrnd().getDlvShopYn()).equals(vendorBrndRegDTO.getSysShopBrnd().getDlvShopYn())){
					/* 배송매장여부가 Y->N으로 변경할 경우 */
					if(vendorBrndRegDTO.getSysShopBrnd().getDlvShopYn().equals("N")){
						SysShopBrndFeeHist sysShopBrndFeeHist=new SysShopBrndFeeHist();
						sysShopBrndFeeHist.setShopId(vendorBrndRegDTO.getSysShopBrnd().getShopId());
						sysShopBrndFeeHist.setErpBrndId(vendorBrndRegDTO.getSysShopBrnd().getErpBrndId());
						sysShopBrndFeeHist.setFeeHistSectCd("DLV_SHOP_FEE");
						sysShopBrndFeeHist.setUdterId(loginId);
						vendorBrndRepository.updateSysShopBrndFeeHist(sysShopBrndFeeHist);
						/* 배송매장여부가 N->Y으로 변경할 경우 */
					}else{

						SysShopBrndFeeHist sysShopBrndFeeHist=new SysShopBrndFeeHist();
						sysShopBrndFeeHist.setShopId(vendorBrndRegDTO.getSysShopBrnd().getShopId());
						sysShopBrndFeeHist.setErpBrndId(vendorBrndRegDTO.getSysShopBrnd().getErpBrndId());
						sysShopBrndFeeHist.setFeeHistSectCd("DLV_SHOP_FEE");
						sysShopBrndFeeHist.setModBfShopFeeSectCd(vendorBrndListResultOld.getSysShopBrnd().getDlvShopFeeSectCd());
						sysShopBrndFeeHist.setModBfShopFeeAmt(vendorBrndListResultOld.getSysShopBrnd().getDlvShopFeeAmt());
						sysShopBrndFeeHist.setModBfShopFeeRt(vendorBrndListResultOld.getSysShopBrnd().getDlvShopFeeRt());
						sysShopBrndFeeHist.setUdterId(loginId);
						vendorBrndRepository.insertSysShopBrndFeeHist(sysShopBrndFeeHist);
					}
				}
				/* 픽업매장여부가 이전과 틀릴 경우 */
				if(!(vendorBrndListResultOld.getSysShopBrnd().getPkupShopYn()).equals(vendorBrndRegDTO.getSysShopBrnd().getPkupShopYn())){
					/* 픽업매장여부가 Y->N으로 변경할 경우 */
					if(vendorBrndRegDTO.getSysShopBrnd().getPkupShopYn().equals("N")){
						/* 픽업매장*/
						SysShopBrndFeeHist sysShopBrndFeeHist=new SysShopBrndFeeHist();
						sysShopBrndFeeHist.setShopId(vendorBrndRegDTO.getSysShopBrnd().getShopId());
						sysShopBrndFeeHist.setErpBrndId(vendorBrndRegDTO.getSysShopBrnd().getErpBrndId());
						sysShopBrndFeeHist.setUdterId(loginId);
						sysShopBrndFeeHist.setFeeHistSectCd("PKUP_SHOP_FEE");
						vendorBrndRepository.updateSysShopBrndFeeHist(sysShopBrndFeeHist);
						/* 픽업물류매장*/
						sysShopBrndFeeHist.setFeeHistSectCd("PKUP_SHOP_LGS_FEE");
						vendorBrndRepository.updateSysShopBrndFeeHist(sysShopBrndFeeHist);
						/* 픽업매장여부가 N->Y으로 변경할 경우 */
					}else{
						/* 픽업매장*/
						SysShopBrndFeeHist sysShopBrndFeeHist=new SysShopBrndFeeHist();
						sysShopBrndFeeHist.setShopId(vendorBrndRegDTO.getSysShopBrnd().getShopId());
						sysShopBrndFeeHist.setErpBrndId(vendorBrndRegDTO.getSysShopBrnd().getErpBrndId());
						sysShopBrndFeeHist.setUdterId(loginId);
						sysShopBrndFeeHist.setFeeHistSectCd("PKUP_SHOP_FEE");
						sysShopBrndFeeHist.setModBfShopFeeSectCd(vendorBrndListResultOld.getSysShopBrnd().getPkupShopFeeSectCd());
						sysShopBrndFeeHist.setModBfShopFeeAmt(vendorBrndListResultOld.getSysShopBrnd().getPkupShopFeeAmt());
						sysShopBrndFeeHist.setModBfShopFeeRt(vendorBrndListResultOld.getSysShopBrnd().getPkupShopFeeRt());
						vendorBrndRepository.insertSysShopBrndFeeHist(sysShopBrndFeeHist);

						/* 픽업물류매장*/
						/*
						if(vendorBrndRegDTO.getSysShopBrnd().getPkupShopLgsYn().equals("Y")){
							sysShopBrndFeeHist.setFeeHistSectCd("PKUP_SHOP_LGS_FEE");
							vendorBrndRepository.insertSysShopBrndFeeHist(sysShopBrndFeeHist);
						}
						*/
					}
				}
				/* 퀵배송매장여부가 이전과 다른 경우 */
				if( !StringUtils.equals(vendorBrndListResultOld.getSysShopBrnd().getQdlvShopYn(), vendorBrndRegDTO.getSysShopBrnd().getQdlvShopYn()) ) {
					/* 퀵배송매장여부를 Y->N으로 변경할 경우 */
					if( "N".equals(vendorBrndRegDTO.getSysShopBrnd().getQdlvShopYn()) ) {
						SysShopBrndFeeHist sysShopBrndFeeHist=new SysShopBrndFeeHist();
						sysShopBrndFeeHist.setShopId(vendorBrndRegDTO.getSysShopBrnd().getShopId());
						sysShopBrndFeeHist.setErpBrndId(vendorBrndRegDTO.getSysShopBrnd().getErpBrndId());
						sysShopBrndFeeHist.setFeeHistSectCd("QDLV_SHOP_FEE");
						sysShopBrndFeeHist.setUdterId(loginId);
						vendorBrndRepository.updateSysShopBrndFeeHist(sysShopBrndFeeHist);

					/* 퀵배송매장여부를 N->Y으로 변경할 경우 */
					}else{
						SysShopBrndFeeHist sysShopBrndFeeHist=new SysShopBrndFeeHist();
						sysShopBrndFeeHist.setShopId(vendorBrndRegDTO.getSysShopBrnd().getShopId());
						sysShopBrndFeeHist.setErpBrndId(vendorBrndRegDTO.getSysShopBrnd().getErpBrndId());
						sysShopBrndFeeHist.setFeeHistSectCd("QDLV_SHOP_FEE");
						sysShopBrndFeeHist.setModBfShopFeeSectCd(vendorBrndListResultOld.getSysShopBrnd().getDlvShopFeeSectCd()); /* 퀵배송 수수료는 배송매장 수수료를 씀 */
						sysShopBrndFeeHist.setModBfShopFeeAmt(vendorBrndListResultOld.getSysShopBrnd().getDlvShopFeeAmt()); /* 퀵배송 수수료는 배송매장 수수료를 씀 */
						sysShopBrndFeeHist.setModBfShopFeeRt(vendorBrndListResultOld.getSysShopBrnd().getDlvShopFeeRt()); /* 퀵배송 수수료는 배송매장 수수료를 씀 */
						sysShopBrndFeeHist.setUdterId(loginId);
						vendorBrndRepository.insertSysShopBrndFeeHist(sysShopBrndFeeHist);
					}
				}
				/* 사용여부가 이전과 틀릴 경우 */
				if(!(vendorBrndListResultOld.getSysShopBrnd().getUseYn()).equals(vendorBrndRegDTO.getSysShopBrnd().getUseYn())){
					/* 사용여부가 Y->N으로 변경할 경우 */
					if(vendorBrndRegDTO.getSysShopBrnd().getUseYn().equals("N")){
						SysShopBrndFeeHist sysShopBrndFeeHist=new SysShopBrndFeeHist();
						sysShopBrndFeeHist.setShopId(vendorBrndRegDTO.getSysShopBrnd().getShopId());
						sysShopBrndFeeHist.setErpBrndId(vendorBrndRegDTO.getSysShopBrnd().getErpBrndId());
						sysShopBrndFeeHist.setUdterId(loginId);
						sysShopBrndFeeHist.setFeeHistSectCd("ALL");
						vendorBrndRepository.updateSysShopBrndFeeHist(sysShopBrndFeeHist);
						/* 사용여부가 N->Y으로 변경할 경우 */
					}else{
						/* 배송매장사용여부가 변경되지 않았는데 Y인 경우에 이력저장함 */
						if((vendorBrndListResultOld.getSysShopBrnd().getDlvShopYn()).equals(vendorBrndRegDTO.getSysShopBrnd().getDlvShopYn())){
							if(vendorBrndRegDTO.getSysShopBrnd().getDlvShopYn().equals("Y")){
								SysShopBrndFeeHist sysShopBrndFeeHist=new SysShopBrndFeeHist();
								sysShopBrndFeeHist.setShopId(vendorBrndRegDTO.getSysShopBrnd().getShopId());
								sysShopBrndFeeHist.setErpBrndId(vendorBrndRegDTO.getSysShopBrnd().getErpBrndId());
								sysShopBrndFeeHist.setFeeHistSectCd("DLV_SHOP_FEE");
								sysShopBrndFeeHist.setModBfShopFeeSectCd(vendorBrndListResultOld.getSysShopBrnd().getDlvShopFeeSectCd());
								sysShopBrndFeeHist.setModBfShopFeeAmt(vendorBrndListResultOld.getSysShopBrnd().getDlvShopFeeAmt());
								sysShopBrndFeeHist.setModBfShopFeeRt(vendorBrndListResultOld.getSysShopBrnd().getDlvShopFeeRt());
								sysShopBrndFeeHist.setUdterId(loginId);
								vendorBrndRepository.insertSysShopBrndFeeHist(sysShopBrndFeeHist);

							}
						}
						/* 픽업매장사용여부가 변경되지 않았는데 Y인 경우에 이력저장함 */
						if((vendorBrndListResultOld.getSysShopBrnd().getPkupShopYn()).equals(vendorBrndRegDTO.getSysShopBrnd().getPkupShopYn())){
							if(vendorBrndRegDTO.getSysShopBrnd().getPkupShopYn().equals("Y")){
								SysShopBrndFeeHist sysShopBrndFeeHist=new SysShopBrndFeeHist();
								sysShopBrndFeeHist.setShopId(vendorBrndRegDTO.getSysShopBrnd().getShopId());
								sysShopBrndFeeHist.setErpBrndId(vendorBrndRegDTO.getSysShopBrnd().getErpBrndId());
								sysShopBrndFeeHist.setFeeHistSectCd("PKUP_SHOP_FEE");
								sysShopBrndFeeHist.setModBfShopFeeSectCd(vendorBrndListResultOld.getSysShopBrnd().getDlvShopFeeSectCd());
								sysShopBrndFeeHist.setModBfShopFeeAmt(vendorBrndListResultOld.getSysShopBrnd().getDlvShopFeeAmt());
								sysShopBrndFeeHist.setModBfShopFeeRt(vendorBrndListResultOld.getSysShopBrnd().getDlvShopFeeRt());
								sysShopBrndFeeHist.setUdterId(loginId);
								vendorBrndRepository.insertSysShopBrndFeeHist(sysShopBrndFeeHist);

								/* 픽업물류매장*/
								/*
								if(vendorBrndRegDTO.getSysShopBrnd().getPkupShopLgsYn().equals("Y")){
									sysShopBrndFeeHist.setFeeHistSectCd("PKUP_SHOP_LGS_FEE");
									vendorBrndRepository.insertSysShopBrndFeeHist(sysShopBrndFeeHist);
								}
								*/
							}
						}
						/* 퀵배송매장사용여부가 변경되지 않았고, 퀵배송매장사용여부가 Y인 경우에 이력 저장함 */
						if( StringUtils.equals(vendorBrndListResultOld.getSysShopBrnd().getQdlvShopYn(), vendorBrndRegDTO.getSysShopBrnd().getQdlvShopYn()) ) {
							if( "Y".equals(vendorBrndRegDTO.getSysShopBrnd().getQdlvShopYn()) ) {
								SysShopBrndFeeHist sysShopBrndFeeHist=new SysShopBrndFeeHist();
								sysShopBrndFeeHist.setShopId(vendorBrndRegDTO.getSysShopBrnd().getShopId());
								sysShopBrndFeeHist.setErpBrndId(vendorBrndRegDTO.getSysShopBrnd().getErpBrndId());
								sysShopBrndFeeHist.setFeeHistSectCd("QDLV_SHOP_FEE");
								sysShopBrndFeeHist.setModBfShopFeeSectCd(vendorBrndListResultOld.getSysShopBrnd().getDlvShopFeeSectCd()); /* 퀵배송 수수료는 배송매장 수수료를 씀 */
								sysShopBrndFeeHist.setModBfShopFeeAmt(vendorBrndListResultOld.getSysShopBrnd().getDlvShopFeeAmt()); /* 퀵배송 수수료는 배송매장 수수료를 씀 */
								sysShopBrndFeeHist.setModBfShopFeeRt(vendorBrndListResultOld.getSysShopBrnd().getDlvShopFeeRt()); /* 퀵배송 수수료는 배송매장 수수료를 씀 */
								sysShopBrndFeeHist.setUdterId(loginId);
								vendorBrndRepository.insertSysShopBrndFeeHist(sysShopBrndFeeHist);
							}
						}

					}
				}

				/* 기존에 이력이 없는 경우 */
			}else{

				SysShopBrndFeeHist sysShopBrndFeeHist=new SysShopBrndFeeHist();
				sysShopBrndFeeHist.setShopId(vendorBrndRegDTO.getSysShopBrnd().getShopId());
				sysShopBrndFeeHist.setErpBrndId(vendorBrndRegDTO.getSysShopBrnd().getErpBrndId());
				sysShopBrndFeeHist.setUdterId(loginId);

				if(vendorBrndRegDTO.getSysShopBrnd().getDlvShopYn().equals("Y")){
					sysShopBrndFeeHist.setFeeHistSectCd("DLV_SHOP_FEE");
					vendorBrndRepository.insertSysShopBrndFeeHist(sysShopBrndFeeHist);
				}

				if(vendorBrndRegDTO.getSysShopBrnd().getPkupShopYn().equals("Y")){
					sysShopBrndFeeHist.setFeeHistSectCd("PKUP_SHOP_FEE");
					vendorBrndRepository.insertSysShopBrndFeeHist(sysShopBrndFeeHist);
					/* 픽업물류매장*/
					/*
					if(vendorBrndRegDTO.getSysShopBrnd().getPkupShopLgsYn().equals("Y")){
						sysShopBrndFeeHist.setFeeHistSectCd("PKUP_SHOP_LGS_FEE");
						vendorBrndRepository.insertSysShopBrndFeeHist(sysShopBrndFeeHist);
					}
					*/
				}

				if( "Y".equals(vendorBrndRegDTO.getSysShopBrnd().getQdlvShopYn()) ) {
					sysShopBrndFeeHist.setFeeHistSectCd("QDLV_SHOP_FEE");
					vendorBrndRepository.insertSysShopBrndFeeHist(sysShopBrndFeeHist);
				}
			}
			
			try {
				if (StringUtils.isNotEmpty(sysShopBrndOld.getDlvShopYn())
						&& StringUtils.isNotEmpty(sysShopBrndOld.getPkupShopYn())
						&& StringUtils.isNotEmpty(sysShopBrndOld.getUseYn())) {
					// 수정 전 현재 배송매장여부, 픽업매장여부, 사용여부 null 체크
					
					if (!StringUtils.equals(sysShopBrndOld.getDlvShopYn(), vendorBrndRegDTO.getSysShopBrnd().getDlvShopYn())
							|| !StringUtils.equals(sysShopBrndOld.getPkupShopYn(), vendorBrndRegDTO.getSysShopBrnd().getPkupShopYn())
							|| !StringUtils.equals(sysShopBrndOld.getUseYn(), vendorBrndRegDTO.getSysShopBrnd().getUseYn())) {
						// 수정 전 현재 값들과 수정 할 값들을 비교하여 하나라도 다르면 플래그 처리, 재고연동대상 처리를 위해
						
//						vendorBrndRepository.mergeSysShopBrndModifyFlag(vendorBrndRegDTO.getSysShopBrnd());
//						시스템 매장 브랜드 '배송매장여부', '픽업매장여부', '사용여부' 수정 시 플래그 처리 
						
					}
				}
				
			} catch (Exception e) {
		    	// 저장에 실패하면 오류로그만 남기고 처리하도록
				log.error(e.getMessage(), e);
			}
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 브랜드 매장 상세조회
	 * @param sysShopBrnd
	 * @return
	 * @throws Exception
	 */
	public VendorBrndListResult selectSysShopBrand(SysShopBrnd sysShopBrnd)throws Exception {
		VendorBrndListResult vendorBrndListResult = new VendorBrndListResult();
		vendorBrndListResult = vendorBrndRepository.selectSysShopBrand(sysShopBrnd);

		return vendorBrndListResult;
	}

	public List<VendorBrndEvtResult> selectBrndShopEvtList(ShopSearchDTO shopSearchDTO)throws Exception{
		return vendorBrndRepository.selectBrndShopEvtList(shopSearchDTO);
	}
	/**
	 * 브랜드 명 조회
	 * @param sysShopEvt
	 * @return
	 * @throws Exception
	 */
	public List<BrandResultDTO> selectSysBrndNm(List<String> brndIds)throws Exception{
		return vendorBrndRepository.selectSysBrndNm(brndIds);
	}

	/**
	 * 브랜드 매장 이벤트 단건 조회
	 * @param sysShopEvt
	 * @return
	 * @throws Exception
	 */
	public VendorBrndEvtResult selectOneSysShopEvt(SysShopEvt sysShopEvt)throws Exception{
		return vendorBrndRepository.selectOneSysShopEvt(sysShopEvt);
	}


	/**
	 * 브랜드 품목별 수선정보조회
	 * @param vendorBrndSearchDTO
	 * @return Page<VendorBrndListResult>
	 * @throws Exception
	 */
	public Page<VendorBrndListResult> getBrndPrdList(VendorBrndSearchDTO vendorBrndSearchDTO) throws Exception {

		RepositoryHelper.makePageEntityIndex(vendorBrndSearchDTO, vendorBrndSearchDTO.getPageParam());	// 페이지 설정

		// 목록 건수 조회
		long totalRow = vendorBrndRepository.selectVendorBrndPrdListCount(vendorBrndSearchDTO);

		// 목록 조회
		List<VendorBrndListResult> results = new ArrayList<VendorBrndListResult>();
		if(totalRow > 0){
			results = vendorBrndRepository.selectVendorBrndPrdList(vendorBrndSearchDTO);
		}

		return new PageImpl<VendorBrndListResult>(results, vendorBrndSearchDTO.getPageParam().getPageable(), totalRow);
	}


	/**
	 * 브랜드 품목별 수선정보 내역 조회
	 * @param vendorBrndSearchDTO
	 * @return Page<VendorBrndListResult>
	 * @throws Exception
	 */
	public Page<VendorBrndListResult> getBrndPrdHistList(VendorBrndSearchDTO vendorBrndSearchDTO) throws Exception {

		RepositoryHelper.makePageEntityIndex(vendorBrndSearchDTO, vendorBrndSearchDTO.getPageParam());	// 페이지 설정

		// 목록 건수 조회
		long totalRow = vendorBrndRepository.selectVendorBrndPrdListHistCount(vendorBrndSearchDTO);

		// 목록 조회
		List<VendorBrndListResult> results = new ArrayList<VendorBrndListResult>();
		if(totalRow > 0){
			results = vendorBrndRepository.selectVendorBrndPrdListHist(vendorBrndSearchDTO);
		}

		return new PageImpl<VendorBrndListResult>(results, vendorBrndSearchDTO.getPageParam().getPageable(), totalRow);
	}

	/**
	 * 상품수선정보 리스트 수정
	 * @param updateList
	 * @return int
	 * @throws Exception
	 */
	@Transactional(value = "transactionManager")
	public void updateBrndPrdList(List<VendorBrndListResult> updateList) throws Exception{
		String loginId = BOSecurityUtil.getLoginId();
		int uptCount = 0;

		try {
			if(updateList != null){
				for(VendorBrndListResult vendorBrndListResult: updateList) {
					SysBrndPrdlst tempSysBrndPrdlst = vendorBrndListResult.getSysBrndPrdlst();

					tempSysBrndPrdlst.setUdterId(loginId);
					tempSysBrndPrdlst.setBrndId(vendorBrndListResult.getSysBrnd().getBrndId());
					tempSysBrndPrdlst.setPrdlstCd(vendorBrndListResult.getSysPrdlstCd().getPrdlstCd());

					String shopRepairFeeSectCd = tempSysBrndPrdlst.getShopRepairFeeSectCd();
					String repairPsbYn = tempSysBrndPrdlst.getRepairPsbYn();

					List<SysBrndPrdlst> beforeList = vendorBrndRepository.selectSysBrndPrdUpdateLst(tempSysBrndPrdlst); //수정 이전(하위브랜드포함)

					//수수료 셋팅
					if(shopRepairFeeSectCd != null){
						if("FIXRT".equals(shopRepairFeeSectCd)){ //정률
							tempSysBrndPrdlst.setShopRepairFeeRt(vendorBrndListResult.getShopRepairFee());
						}else if("FIXAMT".equals(shopRepairFeeSectCd)){//정액
							tempSysBrndPrdlst.setShopRepairFeeAmt(vendorBrndListResult.getShopRepairFee());
						}
					}
					/* SYS_BRND_PRDLST 수정*/
					uptCount = vendorBrndRepository.updateSysBrndPrdlst(tempSysBrndPrdlst); //하위브랜드 포함 일괄업데이트

					if(uptCount > 0){
						SysBrndPrdlst paramSysBrndPrdlst = new SysBrndPrdlst();
						paramSysBrndPrdlst.setBrndId(tempSysBrndPrdlst.getBrndId());
						paramSysBrndPrdlst.setPrdlstCd(tempSysBrndPrdlst.getPrdlstCd());

						List<SysBrndPrdlst> afterList = vendorBrndRepository.selectSysBrndPrdUpdateLst(paramSysBrndPrdlst); //수정 이후(하위브랜드포함)

						for(SysBrndPrdlst sysBrndPrdlst: beforeList){
							//상품의 수선여부 일괄처리 (브랜드 품목별의 수선가능 여부가 Y => N 으로 변경될때만 상품마스터의 수선여부도 N으로 일괄처리.
							if("N".equals(tempSysBrndPrdlst.getRepairPsbYn()) && "Y".equals(sysBrndPrdlst.getRepairPsbYn())){ //이전의 데이터의 값이 Y이고 변경된 데이터의 값이 N일때
								sysBrndPrdlst.setUdterId(loginId);
								// TODO	상품재작업필요 : int godUptCnt = goodsInfoCommandRepository.updateFreeRepairPsbYnGod(sysBrndPrdlst); //쿼리에 수선여부값은 강제로 "N"으로 처리했고, 브랜드ID와 품목코드는 수정이전의 데이터와 수정이후의 데이터가 동일하므로 수정이전의 데이터를 인자로 보냄.

							}
						}

						//상품 수선정보 내역 등록 및 수정
						for(SysBrndPrdlst sysBrndPrdlst: afterList){

							SysBrndPrdlstHist paramSysBrndPrdlstHist = new SysBrndPrdlstHist();
							paramSysBrndPrdlstHist.setBrndId(sysBrndPrdlst.getBrndId());
							paramSysBrndPrdlstHist.setPrdlstCd(sysBrndPrdlst.getPrdlstCd());
							paramSysBrndPrdlstHist.setRepairPsbYn(repairPsbYn);
							paramSysBrndPrdlstHist.setShopRepairFeeSectCd(shopRepairFeeSectCd);

							if(shopRepairFeeSectCd != null){
								if("FIXRT".equals(shopRepairFeeSectCd)){ //정률
									paramSysBrndPrdlstHist.setShopRepairFeeRt(vendorBrndListResult.getShopRepairFee());
								}else if("FIXAMT".equals(shopRepairFeeSectCd)){//정액
									paramSysBrndPrdlstHist.setShopRepairFeeAmt(vendorBrndListResult.getShopRepairFee());
								}
							}

							paramSysBrndPrdlstHist.setRegtrId(loginId);
							paramSysBrndPrdlstHist.setUdterId(loginId);

							/*SYS_BRND_PRDLST_HIST 등록 및 수정*/
							vendorBrndRepository.updateSysBrndPrdlstHist(paramSysBrndPrdlstHist);
						}
					}
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}

	}


	/**
	 * 상품수선정보 리스트 등록
	 * @param param
	 * @return int
	 * @throws Exception
	 */
	public int insertBrndPrdList(SysBrndPrdlst param) throws Exception{

		String loginId = BOSecurityUtil.getLoginId();
		int rtn = 0;

		try {

			rtn = vendorBrndRepository.insertBrndPrdList(param);

		}catch (Exception e) {
			e.printStackTrace();
		}

		return rtn;

	}
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */

}
