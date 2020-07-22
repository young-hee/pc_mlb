package com.plgrim.ncp.biz.goods.service;

import org.springframework.stereotype.Service;

import com.plgrim.ncp.base.entities.datasource1.dsp.DspGodPrc;

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
 * Description	:	상품 가격 Service
 *
 * @Author 	:	neps
 * @Date   	:	2018. 5. 3.
 * @Version	:	
 *
 */
@Service
public class GoodsPriceService extends GoodsService {
	
	/**
	 * 전시 가격 조회
	 * 
	 * @param goodsPriceSearchDTO
	 * @return
	 */
	public DspGodPrc getDspGodPrc(DspGodPrc dspGodPrcDTO) {
		DspGodPrc dspGodPrc = new DspGodPrc();		
		dspGodPrc = dspGodPrcRepository.selectDspGodPrc(dspGodPrcDTO);
		return dspGodPrc;
	}
}
