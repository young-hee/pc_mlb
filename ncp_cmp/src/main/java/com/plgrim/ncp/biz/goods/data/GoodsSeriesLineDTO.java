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
 * Description	:	상품 시리즈/라인 DTO
 *
 * @Author 	:	neps
 * @Date   	:	2018. 5. 3.
 * @Version	:	
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Alias("goodsSeriesLineDTO")
public class GoodsSeriesLineDTO extends AbstractDTO {
    private static final long serialVersionUID = -518392264045239486L;

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
    /** 시리즈 라인구분. */
    private String gbn;
    
    /** 시리즈 라인 ID. */
    private String id;
    
    /** 시리즈 or 라인 이름 */
    private String name;
    
    /** 시리즈 라인 설명. */
    private String dscr;
    
    /** 라인_시리즈 아이디 */
    private String lineSrsId;
    
    /** 등록자, 수정자 */
    private String regTrId;
    
    /**
 	 * 
	 */
    private String userId;

    /** 브랜드 ID */
    private String endpBrndId;

    /** 사용여부 */
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
