package com.plgrim.ncp.biz.goods.service;

import java.sql.Timestamp;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Service;

import com.plgrim.ncp.base.entities.datasource1.god.GodLangbyGodNm;
import com.plgrim.ncp.base.entities.datasource1.god.GodLangbyGodNmHist;
import com.plgrim.ncp.base.enums.GoodsEnum;
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
 * Description	:	상품 History Service
 *
 * @Author 	:	neps
 * @Date   	:	2018. 5. 18.
 * @Version	:	
 *
 */
@Service
public class GoodsHistoryService extends GoodsService{

	/**
	 * 언어별 상품명 이력 등록
	 * 
	 * @param godLangbyGodNm
	 * @return
	 */
	public GoodsInfoResult insertGoodsLangByGoodsNameHistory(GodLangbyGodNm godLangbyGodNm) {
		GoodsInfoResult result = new GoodsInfoResult();
		try {
			GodLangbyGodNmHist godNmHist = new GodLangbyGodNmHist();
			GodLangbyGodNm nGodLangbyGodNm = godLangbyGodNmRepository.selectGodLangbyGodNm(godLangbyGodNm);
			
			BeanUtils.copyProperties(nGodLangbyGodNm, godNmHist);
			
			Timestamp histDt = new Timestamp(System.currentTimeMillis()); 			
			godNmHist.setHistDt(String.valueOf(histDt));
			
			godLangbyGodNmHistRepository.insertGodLangbyGodNmHist(godNmHist);
		} catch (Exception e) {
			super.stackTrace(e);
		}
		result.setRstCd(String.valueOf(GoodsEnum.SUCCESS_CODE));
		return result;
	}
}
