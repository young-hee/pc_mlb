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
package com.plgrim.ncp.biz.vendor.repository;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.datasource1.sys.SysShop;
import com.plgrim.ncp.base.entities.datasource1.sys.SysShopBrnd;
import com.plgrim.ncp.base.entities.datasource1.sys.SysShopBrndFeeHist;
import com.plgrim.ncp.biz.vendor.data.VendorSearchDTO;
import com.plgrim.ncp.biz.vendor.result.SysShopResult;
import com.plgrim.ncp.biz.vendor.result.VendorBrndListResult;

/**
 * [시스템 매장].
 *
 * @author sy59.gim
 * @since 2015. 5. 8
 */
@Repository
public class SysShopBORepository extends AbstractRepository  {

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
	 * 매장 브랜드별 수수료율 목록 건수 조회.
	 *
	 * @param sysShopBrnd [설명]
	 * @return Long [설명]
	 * @since 2015. 5. 11
	 */
    public long selectShopBrandListCountForFee(VendorSearchDTO searchDTO) {
		return getSession1().selectOne("com.plgrim.ncp.biz.sys.shop.selectShopBrandListCountForFee", searchDTO);
	}
	 
	/**
	 * 매장 브랜드별 수수료율 목록 조회.
	 *
	 * @param sysShopBrnd [설명]
	 * @return List [설명]
	 * @since 2015. 5. 11
	 */
    public List<SysShopResult> selectShopBrandListForFee(VendorSearchDTO searchDTO) {
		return getSession1().selectList("com.plgrim.ncp.biz.sys.shop.selectShopBrandListForFee", searchDTO);
	}
    
	/**
	 * 매장 브랜드별 수수료율 엑셀 조회.
	 *
	 * @param sysShopBrnd [설명]
	 * @return List [설명]
	 * @since 2015. 5. 11
	 */
    public List<Map<String, Object>> selectShopBrandExcelForFee(VendorSearchDTO searchDTO) {
		return getSession1().selectList("com.plgrim.ncp.biz.sys.shop.selectShopBrandExcelForFee", searchDTO);
	}
    
	/**
	 * 매장 브랜드별 수수료율 수정.
	 *
	 * @param sysShopBrnd [설명]
	 * @since 2015. 5. 11
	 */
    public void updateShopBrandForFee(SysShopBrnd sysShopBrnd) {
		getSession1().update("com.plgrim.ncp.biz.sys.shop.updateShopBrandForFee", sysShopBrnd);
	}
    
    /**
     * 매장조회
     * @param sysShop
     * @return
     */
    public List<VendorBrndListResult> selectSysShop(SysShop sysShop) {
		return getSession1().selectList("com.plgrim.ncp.biz.sys.shop.selectSysShop", sysShop);
	}
    
    /**
	 *  매장별 수수료율 히스토리 데이터 찾기
	 * @param sysShopBrnd
	 * @return
	 * @throws Exception
	 */
	public SysShopResult selectFeeHistSectList(SysShopBrndFeeHist sysShopBrnd){
		return getSession1().selectOne("com.plgrim.ncp.biz.sys.shop.selectFeeHistSectList", sysShopBrnd);
														
	}
	
	/**
	 * 매장별 수수료율 이력 생성
	 * @param insertFeeHistSectCd
	 * @return
	 * @throws Exception
	 */
	public void insertFeeHistSectCd(SysShopBrndFeeHist sysShopBrndFeeHist){
		getSession1().insert("com.plgrim.ncp.biz.sys.shop.insertFeeHistSectCd", sysShopBrndFeeHist);
	}
	
	/**
	 * 매장별 수수료율 업데이트후 이력 생성
	 * @param insertAfFeeHistSectCd
	 * @return
	 * @throws Exception
	 */
	public void insertAfFeeHistSectCd(SysShopBrndFeeHist sysShopBrndFeeHist){
		getSession1().insert("com.plgrim.ncp.biz.sys.shop.insertAfFeeHistSectCd", sysShopBrndFeeHist);
	}
	
	/**
	 * 매장별 수수료율 이력 수정
	 * @param updateFeeHistSectCd
	 * @return
	 * @throws Exception
	 */
	public void updateFeeHistSectCd(SysShopBrndFeeHist sysShopBrndFeeHist){
		getSession1().update("com.plgrim.ncp.biz.sys.shop.updateFeeHistSectCd", sysShopBrndFeeHist);
	}
	
	/**
	 * 매장별 수수료율  초기저장 이력 수정
	 * @param updateInitFeeHistSectCd
	 * @return
	 * @throws Exception
	 */
	public void updateInitFeeHistSectCd(SysShopBrndFeeHist sysShopBrndFeeHist){
		getSession1().update("com.plgrim.ncp.biz.sys.shop.updateInitFeeHistSectCd", sysShopBrndFeeHist);
	}
    
    

	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */

}
