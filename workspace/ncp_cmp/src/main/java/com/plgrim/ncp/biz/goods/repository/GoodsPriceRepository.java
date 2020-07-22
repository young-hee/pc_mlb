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
 * @since       2015. 8. 19
 */
package com.plgrim.ncp.biz.goods.repository;

import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspGodPrc;
import com.plgrim.ncp.biz.goods.data.GoodsPriceSearchDTO;

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
 * Description	:	상품 가격 Repository
 *
 * @Author 	:	neps
 * @Date   	:	2018. 5. 3.
 * @Version	:	
 *
 */
@Repository
public class GoodsPriceRepository extends AbstractRepository {
 
	/**
	 * 전시 가격 조회
	 * 
	 * @param goodsPriceSearchDTO
	 * @return
	 */
	public DspGodPrc selectDspGodPrc(GoodsPriceSearchDTO goodsPriceSearchDTO) {
		return getSession1().selectOne("com.plgrim.ncp.biz.goods.price.selectDspGodPrc", goodsPriceSearchDTO);
	}
    
}
