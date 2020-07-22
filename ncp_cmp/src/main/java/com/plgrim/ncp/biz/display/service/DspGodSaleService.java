/* Copyright (c) 2015 plgrim, Inc.
 * All right reserved.
 * http://plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 *
 * ------------------------------------------------------------------------
 * @author      daelim.im
 * @since       2016. 10. 27      
 */
package com.plgrim.ncp.biz.display.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plgrim.ncp.base.abstracts.AbstractService;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspGodSaleIdex;
import com.plgrim.ncp.base.entities.datasource1.god.God;
import com.plgrim.ncp.base.repository.dsp.DspGodSaleIdexRepository;
import com.plgrim.ncp.biz.goods.data.GoodsDTO;

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
 * @author daelim.im
 * @since 2016. 10. 27
 */
@Service
public class DspGodSaleService extends AbstractService{

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	@Autowired
	DspGodSaleIdexRepository dspGodSaleIdexRepository;
	

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
	 * @param GoodsDTO goodsDTO
	 * @throws Exception
	 * @since 2016. 10. 27
	 */
	public void insertDspGodSaleIdex(String godNo) throws Exception {			
		
		long newGodIdex = 11000;
		long newGodRecentIdex = 11000;
		
		/*START 2016-11-18 옥민재,김정석 요구사항 추가..*/
		long newGodSortSeq = 10001;	/* 신상품 정렬 순서 */	
		/*END*/
		
		DspGodSaleIdex dspGodSaleIdex = new DspGodSaleIdex();
		dspGodSaleIdex.setGodNo(godNo);
		//dspGodSaleIdex.setNewGodIdex(newGodIdex);
		//dspGodSaleIdex.setNewGodRecentIdex(newGodRecentIdex);
		//dspGodSaleIdex.setNewGodSortSeq(newGodSortSeq);
		
		// 기존에 데이터가 없을 경우, 등록 있으면 수정
		if (dspGodSaleIdexRepository.selectDspGodSaleIdex(dspGodSaleIdex) == null) {
			dspGodSaleIdexRepository.insertDspGodSaleIdex(dspGodSaleIdex);
		} else {
			dspGodSaleIdexRepository.updateDspGodSaleIdex(dspGodSaleIdex);
		}
    }

}
