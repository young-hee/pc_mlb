package com.plgrim.ncp.biz.goods.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plgrim.ncp.base.entities.datasource1.dsp.DspCtgry;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspCtgryCnncGod;
import com.plgrim.ncp.base.entities.datasource1.god.GodDspCtgryExtend;
import com.plgrim.ncp.base.entities.datasource1.sys.SysBrndPrdlst;
import com.plgrim.ncp.base.enums.GoodsEnum;
import com.plgrim.ncp.base.repository.dsp.DspCtgryRepository;
import com.plgrim.ncp.biz.goods.repository.GoodsCategoryRepository;
import com.plgrim.ncp.biz.goods.result.GoodsInfoResult;

/** Copyright (c) 2018 plgrim, Inc.
 * All right reserved.
 * http://plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 *
 * ------------------------------------------------------------------------
 *
 * Description	:	상품 카테고리 Service
 *
 * @Author 	:	neps
 * @Date   	:	2018. 5. 3.
 * @Version	:	
 *
 */
@Service
public class GoodsCategoryService extends GoodsService{
	
	@Autowired
	private DspCtgryRepository dspCtgryRepository;
	
	@Autowired
	private GoodsCategoryRepository goodsCategoryRepository;

	/**
	 * 전시 카테고리 조회
	 * 
	 * @param dspCtgryNo
	 * @return
	 */
	public DspCtgry getDisplayCategory(String dspCtgryNo) {
		DspCtgry dspCtgry = new DspCtgry();
		dspCtgry.setDspCtgryNo(dspCtgryNo);
		return dspCtgryRepository.selectDspCtgry(dspCtgry);		
	}
	
	/**
	 * 시스템 브랜드 품목 상세 조회
	 * 
	 * @param brndId
	 * @param prdlstCd
	 * @return
	 */
	public SysBrndPrdlst getSystemBrandProductlist(String brndId, String prdlstCd) {
		SysBrndPrdlst sysBrndPrdlst = new SysBrndPrdlst(); 
		sysBrndPrdlst.setBrndId(brndId);
		sysBrndPrdlst.setPrdlstCd(prdlstCd);
		return sysBrndPrdlstRepository.selectSysBrndPrdlst(sysBrndPrdlst);
	}

	/**
	 * 전시카테고리 연결 상품 등록
	 * 
	 * @param dspCtgryCnncGod
	 * @return
	 */
	public GoodsInfoResult insertDisplayCategoryConnectionGoods(DspCtgryCnncGod dspCtgryCnncGod ) {
		GoodsInfoResult result = new GoodsInfoResult();
		try {
			dspCtgryCnncGodRepository.insertDspCtgryCnncGod(dspCtgryCnncGod);
			result.setRstCd(String.valueOf(GoodsEnum.SUCCESS_CODE));
		} catch (Exception e) {
			result.setRstCd(String.valueOf(GoodsEnum.FAIL_CODE));
			super.stackTrace(e);
			throw new RuntimeException("전시카테고리 연결 상품 등록 실패", e);
		}
		return result;
	}	

	/**
	 * 전시카테고리 연결 상품 수정
	 * 
	 * @param dspCtgryCnncGod
	 * @return
	 */
	public GoodsInfoResult updateDisplayCategoryConnectionGoods(DspCtgryCnncGod dspCtgryCnncGod) {
		GoodsInfoResult result = new GoodsInfoResult();
		try {
			dspCtgryCnncGodRepository.insertDspCtgryCnncGod(dspCtgryCnncGod);
			result.setRstCd(String.valueOf(GoodsEnum.SUCCESS_CODE));			
		} catch (Exception e) {
			result.setRstCd(String.valueOf(GoodsEnum.FAIL_CODE));
			super.stackTrace(e);
			throw new RuntimeException();			
		}
		return result;
	}	
	
	/**
	 * 전시 카테고리 연결 상품 삭제
	 * 
	 * @param godNo
	 */
	public void deleteDisplayCategoryConnectionGoods(String godNo) {
		goodsCategoryRepository.deleteDisplayCategoryConnectionGoods(godNo);		
	}
	
	/**
	 * 연결된 전시 카테고리 조회
	 * 
	 * @param godNo
	 * @return
	 */
	public List<GodDspCtgryExtend> getDisplayCategoryList(String godNo) {
		return goodsCategoryRepository.selectDisplayCategoryList(godNo);
	}
	
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */

}
