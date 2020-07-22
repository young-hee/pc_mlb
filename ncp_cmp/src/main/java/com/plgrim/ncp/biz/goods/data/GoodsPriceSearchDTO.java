package com.plgrim.ncp.biz.goods.data;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.apache.ibatis.type.Alias;

import com.plgrim.ncp.base.abstracts.AbstractDTO;

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
 * Description	:	상품 가격 검색 DTO
 *
 * @Author 	:	neps
 * @Date   	:	2018. 5. 3.
 * @Version	:	
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Alias("goodsPriceSearchDTO")
public class GoodsPriceSearchDTO extends AbstractDTO {
	private static final long serialVersionUID = -7343860760386138632L;

	/* B2E, SIGNL */
	private String spcPrmTp;

	/**
	 *가격 구분 코드
ㅁ. 가격 구분 : PRC_SECT
   >. 일반 : GNRL
   >. 임직원 : EMP
   >. B2E : B2E 키가 입력됨  --> 해당 상품만 입력됨 
	 */
	private String prcSectCd;

	private String lang;

	private String mallId;
	
	private String godNo;
}
