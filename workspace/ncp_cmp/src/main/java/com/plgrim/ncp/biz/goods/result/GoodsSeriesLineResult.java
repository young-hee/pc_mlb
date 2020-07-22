package com.plgrim.ncp.biz.goods.result;

import org.apache.ibatis.type.Alias;

import lombok.Data;
import lombok.EqualsAndHashCode;

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
 * Description	:	상품 시리즈/라인 RESULT
 *
 * @Author 	:	neps
 * @Date   	:	2018. 5. 3.
 * @Version	:	
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Alias("goodsSeriesLineResult")
public class GoodsSeriesLineResult extends GoodsResult{
    private static final long serialVersionUID = -2760735906564861790L;
	
	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
    /** 시리즈 라인구분. */
    private String gbn;
    
    /** 시리즈 or 라인 아이디 */
    private String id;
    
    /** 라인_시리즈 아이디 */
    private String lineSrsId;
    
    /** 시리즈 or 라인 이름 */
    private String name;
    
    /** 시리즈 or 라인 설명 */
    private String dscr;
    
    /** 등록자 */
    private String regTrId;
    private String regTrNm;
    
    /** 등록일자 */
    private String regDt;

    /** 사업부 브랜드 ID */
    private String endpBrndId;

    /** 사업부 브랜드 명 */
    private String endpBrndNm;

    /** 사용 여부 */
    private String useYn;

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

	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */

}
