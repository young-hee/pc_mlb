/* Copyright (c) 2015 plgrim, Inc.
 * All right reserved.
 * http://plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 *
 * ------------------------------------------------------------------------
 * @author      sy59.gim
 * @since       2015. 5. 11       
 */
package com.plgrim.ncp.biz.vendor.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import com.plgrim.ncp.base.abstracts.AbstractService;
import com.plgrim.ncp.base.entities.RepositoryHelper;
import com.plgrim.ncp.base.entities.datasource1.sys.SysBrnd;
import com.plgrim.ncp.base.entities.datasource1.sys.SysShop;
import com.plgrim.ncp.base.entities.datasource1.sys.SysShopBrnd;
import com.plgrim.ncp.base.entities.datasource1.sys.SysShopBrndFeeHist;
import com.plgrim.ncp.biz.vendor.data.VendorBrndSearchDTO;
import com.plgrim.ncp.biz.vendor.data.VendorSearchDTO;
import com.plgrim.ncp.biz.vendor.repository.SysShopBORepository;
import com.plgrim.ncp.biz.vendor.repository.VendorBrndRepository;
import com.plgrim.ncp.biz.vendor.result.SysShopResult;
import com.plgrim.ncp.biz.vendor.result.VendorBrndListResult;
import com.plgrim.ncp.commons.util.BOSecurityUtil;

/**
 * [시스템 매장]
 * 
 * <p>
 * 
 * <ul>
 *   <li> [기능1]
 *   <li> [기능2]
 * </ul>.
 *
 * @author sy59.gim
 * @since 2015. 5. 11
 */
@Service
public class SysShopService  extends AbstractService {



	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	/**
	 * 시스템 매장 Repository
	 */
	@Autowired
	private SysShopBORepository sysShopBORepository;

	/**
	 * 브랜드 매장관리 Repository
	 */
	@Autowired
	private VendorBrndRepository vendorBrndRepository;
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
	 * 매장 브랜드별 수수료율 목록 조회.
	 *
	 * @param sysShopBrnd [설명]
	 * @return List [설명]
	 * @throws Exception 
	 * @since 2015. 5. 11
	 */
    public Page<SysShopResult> selectShopBrandListForFee(VendorSearchDTO searchDTO) throws Exception {
    	RepositoryHelper.makePageEntityIndex(searchDTO, searchDTO.getPageParam());	// 페이지 설정
    	
    	// 목록 건수 조회
    	long totalRow = sysShopBORepository.selectShopBrandListCountForFee(searchDTO);
    	
    	// 목록 조회
    	List<SysShopResult> results = new ArrayList<SysShopResult>();
    	if(totalRow > 0){
    		results = sysShopBORepository.selectShopBrandListForFee(searchDTO);
    	}
    	
    	return new PageImpl<SysShopResult>(results, searchDTO.getPageParam().getPageable(), totalRow);
	}
    
	/**
	 * 매장 브랜드별 수수료율 엑셀 조회.
	 *
	 * @param sysShopBrnd [설명]
	 * @return List [설명]
	 * @throws Exception 
	 * @since 2015. 5. 11
	 */
    public List<Map<String, Object>> selectShopBrandExcelForFee(VendorSearchDTO searchDTO) throws Exception {
    	return sysShopBORepository.selectShopBrandExcelForFee(searchDTO);
	}
    
	/**
	 * 매장 브랜드별 수수료율 수정.
	 *
	 * @param sysShopBrnd [설명]
	 * @since 2015. 5. 11
	 */
    public void updateShopBrandForFee(SysShopBrnd sysShopBrnd, String loginId) {
    	sysShopBrnd.setUdterId(loginId);
    	sysShopBORepository.updateShopBrandForFee(sysShopBrnd);
	}
    
    /**
	 * /배송 매장 수수료 이력 저장
	 * @param updateList
	 * @return int
	 * @throws Exception
	 */
	public void updateShopBrandForFeeDlvHist(SysShopBrnd sysShopBrnd, String loginId) throws Exception{
		
		if(sysShopBrnd != null){
	    	
	    	// 배송매장 수수료 과거 이력 가져오기
			SysShopBrndFeeHist sysShopBrndOldParam = new SysShopBrndFeeHist();
    		SysShopResult oldData = new SysShopResult();
    		sysShopBrndOldParam.setFeeHistSectCd("DLV_SHOP_FEE");
    		sysShopBrndOldParam.setShopId(sysShopBrnd.getShopId());
    		sysShopBrndOldParam.setErpBrndId(sysShopBrnd.getErpBrndId());
			oldData = sysShopBORepository.selectFeeHistSectList(sysShopBrndOldParam);

			if(oldData == null){
				//이력이 없는 경우 이력 생성
				SysShopBrndFeeHist sysShopBrndParam = new SysShopBrndFeeHist();
				sysShopBrndParam.setShopId(sysShopBrnd.getShopId());
				sysShopBrndParam.setErpBrndId(sysShopBrnd.getErpBrndId());
				sysShopBrndParam.setFeeHistSectCd("DLV_SHOP_FEE");
				sysShopBrndParam.setModBfShopFeeSectCd(null);
				sysShopBrndParam.setModBfShopFeeAmt(null);
				sysShopBrndParam.setModBfShopFeeRt(null);
				sysShopBrndParam.setModAfShopFeeSectCd(sysShopBrnd.getDlvShopFeeSectCd());
				if("FIXAMT".equals(sysShopBrnd.getDlvShopFeeSectCd())){
					sysShopBrndParam.setModAfShopFeeAmt(sysShopBrnd.getDlvShopFeeAmt());
					sysShopBrndParam.setModAfShopFeeRt(null);
				}else if("FIXRT".equals(sysShopBrnd.getDlvShopFeeSectCd())){
					sysShopBrndParam.setModAfShopFeeAmt(null);
					sysShopBrndParam.setModAfShopFeeRt(sysShopBrnd.getDlvShopFeeRt());
				}
				sysShopBrndParam.setRegtrId(loginId);
				sysShopBrndParam.setUdterId(loginId);
				sysShopBORepository.insertAfFeeHistSectCd(sysShopBrndParam);
				
			}else{
				
				SysShopBrndFeeHist sysShopBrndParam = new SysShopBrndFeeHist();

				if(oldData.getSysShopBrndFeeHist() == null){
					
					// 이력은 있으나 변경이후 데이터가 없는경우 변경이후값 업데이트
					if("FIXAMT".equals(sysShopBrnd.getDlvShopFeeSectCd())){
						sysShopBrndParam.setUdterId(loginId);
						sysShopBrndParam.setShopId(sysShopBrnd.getShopId());
						sysShopBrndParam.setErpBrndId(sysShopBrnd.getErpBrndId());
						sysShopBrndParam.setFeeHistSectCd("DLV_SHOP_FEE");
						sysShopBrndParam.setModAfShopFeeSectCd("FIXAMT");
	    				sysShopBrndParam.setModAfShopFeeAmt(sysShopBrnd.getDlvShopFeeAmt());
	    				sysShopBrndParam.setModAfShopFeeRt(null);
						sysShopBORepository.updateInitFeeHistSectCd(sysShopBrndParam);
						
					}else if("FIXRT".equals(sysShopBrnd.getDlvShopFeeSectCd())){
						
						sysShopBrndParam.setUdterId(loginId);
						sysShopBrndParam.setShopId(sysShopBrnd.getShopId());
						sysShopBrndParam.setErpBrndId(sysShopBrnd.getErpBrndId());
						sysShopBrndParam.setFeeHistSectCd("DLV_SHOP_FEE");
						sysShopBrndParam.setModAfShopFeeSectCd("FIXRT");
	    				sysShopBrndParam.setModAfShopFeeAmt(null);
	    				sysShopBrndParam.setModAfShopFeeRt(sysShopBrnd.getDlvShopFeeRt());
						sysShopBORepository.updateInitFeeHistSectCd(sysShopBrndParam);
					}
					
				}else if(sysShopBrnd.getDlvShopFeeSectCd().equals(oldData.getSysShopBrndFeeHist().getModAfShopFeeSectCd())){
					//과거이력과 현재데이터가 정액인지 정률인지 구분
					//구분이 같다
					
					if("FIXAMT".equals(sysShopBrnd.getDlvShopFeeSectCd())){
						//정액인 경우
						
							if(sysShopBrnd.getDlvShopFeeAmt().equals(oldData.getSysShopBrndFeeHist().getModAfShopFeeAmt())){
								// 과거이력과 현재입력값이 같으면 패스. 
							}else{
								sysShopBrndParam.setUdterId(loginId);
								sysShopBrndParam.setShopId(sysShopBrnd.getShopId());
								sysShopBrndParam.setErpBrndId(sysShopBrnd.getErpBrndId());
								sysShopBrndParam.setFeeHistSectCd("DLV_SHOP_FEE");
								sysShopBORepository.updateFeeHistSectCd(sysShopBrndParam);
						           
								//정액 이력 생성
								sysShopBrndParam.setShopId(sysShopBrnd.getShopId());
								sysShopBrndParam.setErpBrndId(sysShopBrnd.getErpBrndId());
			    				sysShopBrndParam.setFeeHistSectCd("DLV_SHOP_FEE");
			    				sysShopBrndParam.setModBfShopFeeSectCd("FIXAMT");
			    				sysShopBrndParam.setModBfShopFeeAmt(oldData.getSysShopBrndFeeHist().getModAfShopFeeAmt());
			    				sysShopBrndParam.setModBfShopFeeRt(null);
			    				sysShopBrndParam.setModAfShopFeeSectCd("FIXAMT");
			    				sysShopBrndParam.setModAfShopFeeAmt(sysShopBrnd.getDlvShopFeeAmt());
			    				sysShopBrndParam.setModAfShopFeeRt(null);
			    				sysShopBrndParam.setRegtrId(loginId);
			    				sysShopBrndParam.setUdterId(loginId);
			    				sysShopBORepository.insertAfFeeHistSectCd(sysShopBrndParam);
							}
	
		    				
						}else if("FIXRT".equals(sysShopBrnd.getDlvShopFeeSectCd())){
							// 정률인경우
							
							if(sysShopBrnd.getDlvShopFeeRt().equals(oldData.getSysShopBrndFeeHist().getModAfShopFeeRt())){
								// 과거이력과 현재입력값이 같으면 패스.  
							}else{
	
								sysShopBrndParam.setUdterId(loginId);
								sysShopBrndParam.setShopId(sysShopBrnd.getShopId());
								sysShopBrndParam.setErpBrndId(sysShopBrnd.getErpBrndId());
								sysShopBrndParam.setFeeHistSectCd("DLV_SHOP_FEE");
								sysShopBORepository.updateFeeHistSectCd(sysShopBrndParam);
								
								//정류 이력 생성
								sysShopBrndParam.setShopId(sysShopBrnd.getShopId());
								sysShopBrndParam.setErpBrndId(sysShopBrnd.getErpBrndId());
			    				sysShopBrndParam.setFeeHistSectCd("DLV_SHOP_FEE");
			    				sysShopBrndParam.setModBfShopFeeSectCd("FIXRT");
			    				sysShopBrndParam.setModBfShopFeeAmt(null);
			    				sysShopBrndParam.setModBfShopFeeRt(oldData.getSysShopBrndFeeHist().getModAfShopFeeRt());
			    				sysShopBrndParam.setModAfShopFeeSectCd("FIXRT");
			    				sysShopBrndParam.setModAfShopFeeAmt(null);
			    				sysShopBrndParam.setModAfShopFeeRt(sysShopBrnd.getDlvShopFeeRt());
			    				sysShopBrndParam.setRegtrId(loginId);
			    				sysShopBrndParam.setUdterId(loginId);
			    				sysShopBORepository.insertAfFeeHistSectCd(sysShopBrndParam);
							}
						}
					
				}else{
					//구분이 다르다
					
					sysShopBrndParam.setUdterId(loginId);
					sysShopBrndParam.setShopId(sysShopBrnd.getShopId());
					sysShopBrndParam.setErpBrndId(sysShopBrnd.getErpBrndId());
					sysShopBrndParam.setFeeHistSectCd("DLV_SHOP_FEE");
					sysShopBORepository.updateFeeHistSectCd(sysShopBrndParam);
					
							if("FIXAMT".equals(sysShopBrnd.getDlvShopFeeSectCd())){
						           
								// 정류 에서 정액 으로 변경된 경우
								sysShopBrndParam.setShopId(sysShopBrnd.getShopId());
								sysShopBrndParam.setErpBrndId(sysShopBrnd.getErpBrndId());
			    				sysShopBrndParam.setFeeHistSectCd("DLV_SHOP_FEE");
			    				sysShopBrndParam.setModBfShopFeeSectCd("FIXRT");
			    				sysShopBrndParam.setModBfShopFeeAmt(null);
			    				sysShopBrndParam.setModBfShopFeeRt(oldData.getSysShopBrndFeeHist().getModAfShopFeeRt());
			    				sysShopBrndParam.setModAfShopFeeSectCd("FIXAMT");
			    				sysShopBrndParam.setModAfShopFeeAmt(sysShopBrnd.getDlvShopFeeAmt());
			    				sysShopBrndParam.setModAfShopFeeRt(null);
			    				sysShopBrndParam.setRegtrId(loginId);
			    				sysShopBrndParam.setUdterId(loginId);
			    				sysShopBORepository.insertAfFeeHistSectCd(sysShopBrndParam);
			    				
							}else if("FIXRT".equals(sysShopBrnd.getDlvShopFeeSectCd())){
								// 정액에서 정류료 변경된 경우
								sysShopBrndParam.setShopId(sysShopBrnd.getShopId());
								sysShopBrndParam.setErpBrndId(sysShopBrnd.getErpBrndId());
			    				sysShopBrndParam.setFeeHistSectCd("DLV_SHOP_FEE");
			    				sysShopBrndParam.setModBfShopFeeSectCd("FIXAMT");
			    				sysShopBrndParam.setModBfShopFeeAmt(oldData.getSysShopBrndFeeHist().getModAfShopFeeAmt());
			    				sysShopBrndParam.setModBfShopFeeRt(null);
			    				sysShopBrndParam.setModAfShopFeeSectCd("FIXRT");
			    				sysShopBrndParam.setModAfShopFeeAmt(null);
			    				sysShopBrndParam.setModAfShopFeeRt(sysShopBrnd.getDlvShopFeeRt());
			    				sysShopBrndParam.setRegtrId(loginId);
			    				sysShopBrndParam.setUdterId(loginId);
			    				sysShopBORepository.insertAfFeeHistSectCd(sysShopBrndParam);
								
							}
				}
				
			}
			
		}
	}
    
    
	/**
	 * /픽업 매장 수수료  히스토리 저장
	 * @param updateList
	 * @return int
	 * @throws Exception
	 */
	public void updateShopBrandForFeePkuHist(SysShopBrnd sysShopBrnd, String loginId) throws Exception{
		
		if(sysShopBrnd != null){
			
	    	// 픽업 매장 수수료 과거 이력 가져오기
			SysShopBrndFeeHist sysShopBrndOldParam = new SysShopBrndFeeHist();
    		SysShopResult oldData = new SysShopResult();
    		sysShopBrndOldParam.setFeeHistSectCd("PKUP_SHOP_FEE");
    		sysShopBrndOldParam.setShopId(sysShopBrnd.getShopId());
    		sysShopBrndOldParam.setErpBrndId(sysShopBrnd.getErpBrndId());
			oldData = sysShopBORepository.selectFeeHistSectList(sysShopBrndOldParam);

			
			if(oldData == null){
				//이력이 없는 경우 이력 생성
				SysShopBrndFeeHist sysShopBrndParam = new SysShopBrndFeeHist();
				sysShopBrndParam.setShopId(sysShopBrnd.getShopId());
				sysShopBrndParam.setErpBrndId(sysShopBrnd.getErpBrndId());
				sysShopBrndParam.setFeeHistSectCd("PKUP_SHOP_FEE");
				sysShopBrndParam.setModBfShopFeeSectCd(null);
				sysShopBrndParam.setModBfShopFeeAmt(null);
				sysShopBrndParam.setModBfShopFeeRt(null);
				sysShopBrndParam.setModAfShopFeeSectCd(sysShopBrnd.getPkupShopFeeSectCd());
				if("FIXAMT".equals(sysShopBrnd.getPkupShopFeeSectCd())){
					sysShopBrndParam.setModAfShopFeeAmt(sysShopBrnd.getPkupShopFeeAmt());
					sysShopBrndParam.setModAfShopFeeRt(null);
				}else if("FIXRT".equals(sysShopBrnd.getPkupShopFeeSectCd())){
					sysShopBrndParam.setModAfShopFeeAmt(null);
					sysShopBrndParam.setModAfShopFeeRt(sysShopBrnd.getPkupShopFeeRt());
				}
				sysShopBrndParam.setRegtrId(loginId);
				sysShopBrndParam.setUdterId(loginId);
				sysShopBORepository.insertAfFeeHistSectCd(sysShopBrndParam);
				
			}else{
				
				SysShopBrndFeeHist sysShopBrndParam = new SysShopBrndFeeHist();
				
				//과거데이터와 현제데이터가 정액인지 정률인지 구분
				
				if(oldData.getSysShopBrndFeeHist() == null){
					
					// 이력은 있으나 변경이후 데이터가 없는경우 변경이후값 업데이트
					if("FIXAMT".equals(sysShopBrnd.getPkupShopFeeSectCd())){
						sysShopBrndParam.setUdterId(loginId);
						sysShopBrndParam.setShopId(sysShopBrnd.getShopId());
						sysShopBrndParam.setErpBrndId(sysShopBrnd.getErpBrndId());
						sysShopBrndParam.setFeeHistSectCd("PKUP_SHOP_FEE");
						sysShopBrndParam.setModAfShopFeeSectCd("FIXAMT");
	    				sysShopBrndParam.setModAfShopFeeAmt(sysShopBrnd.getPkupShopFeeAmt());
	    				sysShopBrndParam.setModAfShopFeeRt(null);
						sysShopBORepository.updateInitFeeHistSectCd(sysShopBrndParam);
						
					}else if("FIXRT".equals(sysShopBrnd.getPkupShopFeeSectCd())){
						
						sysShopBrndParam.setUdterId(loginId);
						sysShopBrndParam.setShopId(sysShopBrnd.getShopId());
						sysShopBrndParam.setErpBrndId(sysShopBrnd.getErpBrndId());
						sysShopBrndParam.setFeeHistSectCd("PKUP_SHOP_FEE");
						sysShopBrndParam.setModAfShopFeeSectCd("FIXRT");
	    				sysShopBrndParam.setModAfShopFeeAmt(null);
	    				sysShopBrndParam.setModAfShopFeeRt(sysShopBrnd.getPkupShopFeeRt());
						sysShopBORepository.updateInitFeeHistSectCd(sysShopBrndParam);
					}
					
				}else if(sysShopBrnd.getPkupShopFeeSectCd().equals(oldData.getSysShopBrndFeeHist().getModAfShopFeeSectCd())){
					//구분이 같다
					
					if("FIXAMT".equals(sysShopBrnd.getPkupShopFeeSectCd())){
				        //정액인 경우  
						
						if(sysShopBrnd.getPkupShopFeeAmt().equals(oldData.getSysShopBrndFeeHist().getModAfShopFeeAmt())){
							// 과거이력과 현재입력값이 같으면 패스. 
						}else{
							sysShopBrndParam.setUdterId(loginId);
							sysShopBrndParam.setShopId(sysShopBrnd.getShopId());
							sysShopBrndParam.setErpBrndId(sysShopBrnd.getErpBrndId());
							sysShopBrndParam.setFeeHistSectCd("PKUP_SHOP_FEE");
							sysShopBORepository.updateFeeHistSectCd(sysShopBrndParam);
							
							//정액 이력 생성
							sysShopBrndParam.setShopId(sysShopBrnd.getShopId());
							sysShopBrndParam.setErpBrndId(sysShopBrnd.getErpBrndId());
		    				sysShopBrndParam.setFeeHistSectCd("PKUP_SHOP_FEE");
		    				sysShopBrndParam.setModBfShopFeeSectCd("FIXAMT");
		    				sysShopBrndParam.setModBfShopFeeAmt(oldData.getSysShopBrndFeeHist().getModAfShopFeeAmt());
		    				sysShopBrndParam.setModBfShopFeeRt(null);
		    				sysShopBrndParam.setModAfShopFeeSectCd("FIXAMT");
		    				sysShopBrndParam.setModAfShopFeeAmt(sysShopBrnd.getPkupShopFeeAmt());
		    				sysShopBrndParam.setModAfShopFeeRt(null);
		    				sysShopBrndParam.setRegtrId(loginId);
		    				sysShopBrndParam.setUdterId(loginId);
		    				sysShopBORepository.insertAfFeeHistSectCd(sysShopBrndParam);
						}
	    				
					}else if("FIXRT".equals(sysShopBrnd.getPkupShopFeeSectCd())){
						// 정률인경우
						
						if(sysShopBrnd.getPkupShopFeeRt().equals(oldData.getSysShopBrndFeeHist().getModAfShopFeeRt())){
							// 과거이력과 현재입력값이 같으면 패스.  
						}else{
							sysShopBrndParam.setUdterId(loginId);
							sysShopBrndParam.setShopId(sysShopBrnd.getShopId());
							sysShopBrndParam.setErpBrndId(sysShopBrnd.getErpBrndId());
							sysShopBrndParam.setFeeHistSectCd("PKUP_SHOP_FEE");
							sysShopBORepository.updateFeeHistSectCd(sysShopBrndParam);
							
							//정류 이력 생성
							sysShopBrndParam.setShopId(sysShopBrnd.getShopId());
							sysShopBrndParam.setErpBrndId(sysShopBrnd.getErpBrndId());
		    				sysShopBrndParam.setFeeHistSectCd("PKUP_SHOP_FEE");
		    				sysShopBrndParam.setModBfShopFeeSectCd("FIXRT");
		    				sysShopBrndParam.setModBfShopFeeAmt(null);
		    				sysShopBrndParam.setModBfShopFeeRt(oldData.getSysShopBrndFeeHist().getModAfShopFeeRt());
		    				sysShopBrndParam.setModAfShopFeeSectCd("FIXRT");
		    				sysShopBrndParam.setModAfShopFeeAmt(null);
		    				sysShopBrndParam.setModAfShopFeeRt(sysShopBrnd.getPkupShopFeeRt());
		    				sysShopBrndParam.setRegtrId(loginId);
		    				sysShopBrndParam.setUdterId(loginId);
		    				sysShopBORepository.insertAfFeeHistSectCd(sysShopBrndParam);
							
						}
						
						
					}
					
				}else{
					//다르다
					
					sysShopBrndParam.setUdterId(loginId);
					sysShopBrndParam.setShopId(sysShopBrnd.getShopId());
					sysShopBrndParam.setErpBrndId(sysShopBrnd.getErpBrndId());
					sysShopBrndParam.setFeeHistSectCd("PKUP_SHOP_FEE");
					sysShopBORepository.updateFeeHistSectCd(sysShopBrndParam);
					
					
							if("FIXAMT".equals(sysShopBrnd.getPkupShopFeeSectCd())){
						           
								//과거 데이터 : 정류 >  현재데이터 정액 데이터 인설트
								sysShopBrndParam.setShopId(sysShopBrnd.getShopId());
								sysShopBrndParam.setErpBrndId(sysShopBrnd.getErpBrndId());
			    				sysShopBrndParam.setFeeHistSectCd("PKUP_SHOP_FEE");
			    				sysShopBrndParam.setModBfShopFeeSectCd("FIXRT");
			    				sysShopBrndParam.setModBfShopFeeAmt(null);
			    				sysShopBrndParam.setModBfShopFeeRt(oldData.getSysShopBrndFeeHist().getModAfShopFeeRt());
			    				sysShopBrndParam.setModAfShopFeeSectCd("FIXAMT");
			    				sysShopBrndParam.setModAfShopFeeAmt(sysShopBrnd.getPkupShopFeeAmt());
			    				sysShopBrndParam.setModAfShopFeeRt(null);
			    				sysShopBrndParam.setRegtrId(loginId);
			    				sysShopBrndParam.setUdterId(loginId);
			    				sysShopBORepository.insertAfFeeHistSectCd(sysShopBrndParam);
			    				
							}else if("FIXRT".equals(sysShopBrnd.getPkupShopFeeSectCd())){
								//과거 데이터 : 정액 >  현재데이터 정류 데이터 인설트
								sysShopBrndParam.setShopId(sysShopBrnd.getShopId());
								sysShopBrndParam.setErpBrndId(sysShopBrnd.getErpBrndId());
			    				sysShopBrndParam.setFeeHistSectCd("PKUP_SHOP_FEE");
			    				sysShopBrndParam.setModBfShopFeeSectCd("FIXAMT");
			    				sysShopBrndParam.setModBfShopFeeAmt(oldData.getSysShopBrndFeeHist().getModAfShopFeeAmt());
			    				sysShopBrndParam.setModBfShopFeeRt(null);
			    				sysShopBrndParam.setModAfShopFeeSectCd("FIXRT");
			    				sysShopBrndParam.setModAfShopFeeAmt(null);
			    				sysShopBrndParam.setModAfShopFeeRt(sysShopBrnd.getPkupShopFeeRt());
			    				sysShopBrndParam.setRegtrId(loginId);
			    				sysShopBrndParam.setUdterId(loginId);
			    				sysShopBORepository.insertAfFeeHistSectCd(sysShopBrndParam);
								
							}
				}
				
			}
			
		}
	}
	
	 
		/**
		 * /픽업 매장 물류 수수료 히스토리 저장
		 * @param updateList
		 * @return int
		 * @throws Exception
		 */
		public void updateShopBrandForFeePkuLgsHist(SysShopBrnd sysShopBrnd, String loginId) throws Exception{
			
			if(sysShopBrnd != null){
				
		    	// 이전데이터 가져오기
				SysShopBrndFeeHist sysShopBrndOldParam = new SysShopBrndFeeHist();
	    		SysShopResult oldData = new SysShopResult();
	    		sysShopBrndOldParam.setFeeHistSectCd("PKUP_SHOP_LGS_FEE");
	    		sysShopBrndOldParam.setShopId(sysShopBrnd.getShopId());
	    		sysShopBrndOldParam.setErpBrndId(sysShopBrnd.getErpBrndId());
				oldData = sysShopBORepository.selectFeeHistSectList(sysShopBrndOldParam);
															
				
				
				if(oldData == null){
					//이력이 생성되어있지 않은경우 히스토리 데이터생성
					SysShopBrndFeeHist sysShopBrndParam = new SysShopBrndFeeHist();
					sysShopBrndParam.setShopId(sysShopBrnd.getShopId());
					sysShopBrndParam.setErpBrndId(sysShopBrnd.getErpBrndId());
					sysShopBrndParam.setFeeHistSectCd("PKUP_SHOP_LGS_FEE");
					sysShopBrndParam.setModBfShopFeeSectCd(null);
					sysShopBrndParam.setModBfShopFeeAmt(null);
					sysShopBrndParam.setModBfShopFeeRt(null);
					sysShopBrndParam.setModAfShopFeeSectCd(sysShopBrnd.getPkupShopLgsFeeSectCd());
					if("FIXAMT".equals(sysShopBrnd.getPkupShopLgsFeeSectCd())){
						sysShopBrndParam.setModAfShopFeeAmt(sysShopBrnd.getPkupShopLgsFeeAmt());
						sysShopBrndParam.setModAfShopFeeRt(null);
					}else if("FIXRT".equals(sysShopBrnd.getPkupShopLgsFeeSectCd())){
						sysShopBrndParam.setModAfShopFeeAmt(null);
						sysShopBrndParam.setModAfShopFeeRt(sysShopBrnd.getPkupShopLgsFeeRt());
					}
					sysShopBrndParam.setRegtrId(loginId);
					sysShopBrndParam.setUdterId(loginId);
					sysShopBORepository.insertAfFeeHistSectCd(sysShopBrndParam);
					
				}else{
					
					//기존데이터 날짜 업데이트
					SysShopBrndFeeHist sysShopBrndParam = new SysShopBrndFeeHist();
					
					
					if(oldData.getSysShopBrndFeeHist() == null){
						
						// AF 데이터가 없음
						if("FIXAMT".equals(sysShopBrnd.getPkupShopLgsFeeSectCd())){
							sysShopBrndParam.setUdterId(loginId);
							sysShopBrndParam.setShopId(sysShopBrnd.getShopId());
							sysShopBrndParam.setErpBrndId(sysShopBrnd.getErpBrndId());
							sysShopBrndParam.setFeeHistSectCd("PKUP_SHOP_LGS_FEE");
							sysShopBrndParam.setModAfShopFeeSectCd("FIXAMT");
		    				sysShopBrndParam.setModAfShopFeeAmt(sysShopBrnd.getPkupShopLgsFeeAmt());
		    				sysShopBrndParam.setModAfShopFeeRt(null);
							sysShopBORepository.updateInitFeeHistSectCd(sysShopBrndParam);
							
						}else if("FIXRT".equals(sysShopBrnd.getPkupShopLgsFeeSectCd())){
							
							sysShopBrndParam.setUdterId(loginId);
							sysShopBrndParam.setShopId(sysShopBrnd.getShopId());
							sysShopBrndParam.setErpBrndId(sysShopBrnd.getErpBrndId());
							sysShopBrndParam.setFeeHistSectCd("PKUP_SHOP_LGS_FEE");
							sysShopBrndParam.setModAfShopFeeSectCd("FIXRT");
		    				sysShopBrndParam.setModAfShopFeeAmt(null);
		    				sysShopBrndParam.setModAfShopFeeRt(sysShopBrnd.getPkupShopLgsFeeRt());;
							sysShopBORepository.updateInitFeeHistSectCd(sysShopBrndParam);
						}
						
					}else if(sysShopBrnd.getPkupShopLgsFeeSectCd().equals(oldData.getSysShopBrndFeeHist().getModAfShopFeeSectCd())){
						//과거데이터와 현제데이터가 정액인지 정률인지 구분
						//같다
						
						if("FIXAMT".equals(sysShopBrnd.getPkupShopLgsFeeSectCd())){
					           
							if(sysShopBrnd.getPkupShopLgsFeeAmt().equals(oldData.getSysShopBrndFeeHist().getModAfShopFeeAmt())){
								// 기존데이터가 같을경우 빠져나옴
							}else{
								sysShopBrndParam.setUdterId(loginId);
								sysShopBrndParam.setShopId(sysShopBrnd.getShopId());
								sysShopBrndParam.setErpBrndId(sysShopBrnd.getErpBrndId());
								sysShopBrndParam.setFeeHistSectCd("PKUP_SHOP_LGS_FEE");
								sysShopBORepository.updateFeeHistSectCd(sysShopBrndParam);
								
								//정액 데이터 인설트
								sysShopBrndParam.setShopId(sysShopBrnd.getShopId());
								sysShopBrndParam.setErpBrndId(sysShopBrnd.getErpBrndId());
			    				sysShopBrndParam.setFeeHistSectCd("PKUP_SHOP_LGS_FEE");
			    				sysShopBrndParam.setModBfShopFeeSectCd("FIXAMT");
			    				sysShopBrndParam.setModBfShopFeeAmt(oldData.getSysShopBrndFeeHist().getModAfShopFeeAmt());
			    				sysShopBrndParam.setModBfShopFeeRt(null);
			    				sysShopBrndParam.setModAfShopFeeSectCd("FIXAMT");
			    				sysShopBrndParam.setModAfShopFeeAmt(sysShopBrnd.getPkupShopLgsFeeAmt());
			    				sysShopBrndParam.setModAfShopFeeRt(null);
			    				sysShopBrndParam.setRegtrId(loginId);
			    				sysShopBrndParam.setUdterId(loginId);
			    				sysShopBORepository.insertAfFeeHistSectCd(sysShopBrndParam);
								
							}
							
		    				
						}else if("FIXRT".equals(sysShopBrnd.getPkupShopLgsFeeSectCd())){
							
							if(sysShopBrnd.getPkupShopLgsFeeRt().equals(oldData.getSysShopBrndFeeHist().getModAfShopFeeRt())){
								// 기존데이터가 같을경우 빠져나옴
							}else{
								
								sysShopBrndParam.setUdterId(loginId);
								sysShopBrndParam.setShopId(sysShopBrnd.getShopId());
								sysShopBrndParam.setErpBrndId(sysShopBrnd.getErpBrndId());
								sysShopBrndParam.setFeeHistSectCd("PKUP_SHOP_LGS_FEE");
								sysShopBORepository.updateFeeHistSectCd(sysShopBrndParam);
								
								//정류 데이터 인설트
								sysShopBrndParam.setShopId(sysShopBrnd.getShopId());
								sysShopBrndParam.setErpBrndId(sysShopBrnd.getErpBrndId());
			    				sysShopBrndParam.setFeeHistSectCd("PKUP_SHOP_LGS_FEE");
			    				sysShopBrndParam.setModBfShopFeeSectCd("FIXRT");
			    				sysShopBrndParam.setModBfShopFeeAmt(null);
			    				sysShopBrndParam.setModBfShopFeeRt(oldData.getSysShopBrndFeeHist().getModAfShopFeeRt());
			    				sysShopBrndParam.setModAfShopFeeSectCd("FIXRT");
			    				sysShopBrndParam.setModAfShopFeeAmt(null);
			    				sysShopBrndParam.setModAfShopFeeRt(sysShopBrnd.getPkupShopLgsFeeRt());
			    				sysShopBrndParam.setRegtrId(loginId);
			    				sysShopBrndParam.setUdterId(loginId);
			    				sysShopBORepository.insertAfFeeHistSectCd(sysShopBrndParam);
								
							}
							
						}
						
					}else{
						//다르다
						
						sysShopBrndParam.setUdterId(loginId);
						sysShopBrndParam.setShopId(sysShopBrnd.getShopId());
						sysShopBrndParam.setErpBrndId(sysShopBrnd.getErpBrndId());
						sysShopBrndParam.setFeeHistSectCd("PKUP_SHOP_LGS_FEE");
						sysShopBORepository.updateFeeHistSectCd(sysShopBrndParam);
						
						
								if("FIXAMT".equals(sysShopBrnd.getPkupShopLgsFeeSectCd())){
							           
									//과거 데이터 : 정류 >  현재데이터 정액 데이터 인설트
									sysShopBrndParam.setShopId(sysShopBrnd.getShopId());
									sysShopBrndParam.setErpBrndId(sysShopBrnd.getErpBrndId());
				    				sysShopBrndParam.setFeeHistSectCd("PKUP_SHOP_LGS_FEE");
				    				sysShopBrndParam.setModBfShopFeeSectCd("FIXRT");
				    				sysShopBrndParam.setModBfShopFeeAmt(null);
				    				sysShopBrndParam.setModBfShopFeeRt(oldData.getSysShopBrndFeeHist().getModAfShopFeeRt());
				    				sysShopBrndParam.setModAfShopFeeSectCd("FIXAMT");
				    				sysShopBrndParam.setModAfShopFeeAmt(sysShopBrnd.getPkupShopLgsFeeAmt());
				    				sysShopBrndParam.setModAfShopFeeRt(null);
				    				sysShopBrndParam.setRegtrId(loginId);
				    				sysShopBrndParam.setUdterId(loginId);
				    				sysShopBORepository.insertAfFeeHistSectCd(sysShopBrndParam);
				    				
								}else if("FIXRT".equals(sysShopBrnd.getPkupShopLgsFeeSectCd())){
									//과거 데이터 : 정액 >  현재데이터 정류 데이터 인설트
									sysShopBrndParam.setShopId(sysShopBrnd.getShopId());
									sysShopBrndParam.setErpBrndId(sysShopBrnd.getErpBrndId());
				    				sysShopBrndParam.setFeeHistSectCd("PKUP_SHOP_LGS_FEE");
				    				sysShopBrndParam.setModBfShopFeeSectCd("FIXAMT");
				    				sysShopBrndParam.setModBfShopFeeAmt(oldData.getSysShopBrndFeeHist().getModAfShopFeeAmt());
				    				sysShopBrndParam.setModBfShopFeeRt(null);
				    				sysShopBrndParam.setModAfShopFeeSectCd("FIXRT");
				    				sysShopBrndParam.setModAfShopFeeAmt(null);
				    				sysShopBrndParam.setModAfShopFeeRt(sysShopBrnd.getPkupShopLgsFeeRt());
				    				sysShopBrndParam.setRegtrId(loginId);
				    				sysShopBrndParam.setUdterId(loginId);
				    				sysShopBORepository.insertAfFeeHistSectCd(sysShopBrndParam);
									
								}
					}
					
				}
				
			}
		}
	
    /**
     * 매장조회
     * @param vendorBrndSearchDTO
     * @return
     */
    public List<VendorBrndListResult> selectSysShop(VendorBrndSearchDTO vendorBrndSearchDTO) {
		return sysShopBORepository.selectSysShop(vendorBrndSearchDTO.getSysShop());
	}
	
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */

}
