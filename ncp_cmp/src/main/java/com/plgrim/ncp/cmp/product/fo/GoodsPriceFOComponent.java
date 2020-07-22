package com.plgrim.ncp.cmp.product.fo;

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
 * Description	:	상품 가격 정보 Component
 *
 * @Author 	:	neps
 * @Date   	:	2018. 5. 3.
 * @Version	:	
 *
 */
public interface GoodsPriceFOComponent {
	
	/**
	 * 회원 유형별 가격 조회(PC)
	 * 
	 * @return
	 */
	public GoodsPriceSearchDTO getMemberTypeForPriceForPC();
	
	/**
	 * 회원 유형별 가격 조회
	 * 
	 * @return
	 */
	public String getMemberTypeForPrice();
	
	/**
	 * 회원 유형별 가격 조회(Mobile)
	 * 
	 * @return
	 */
	public String getMemberTypeForPriceForMobile();
}
