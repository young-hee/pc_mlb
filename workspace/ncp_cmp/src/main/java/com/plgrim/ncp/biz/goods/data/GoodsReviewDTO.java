package com.plgrim.ncp.biz.goods.data;

import lombok.Data;
import lombok.EqualsAndHashCode;

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
 * Description	:	상품 리뷰 DTO
 *
 * @Author 	:	neps
 * @Date   	:	2018. 5. 3.
 * @Version	:	
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class GoodsReviewDTO extends AbstractDTO {
	/**
	 * 
	 */
    private static final long serialVersionUID = 7252281355795054058L;

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
    
    /** 정렬 flag. */
    private String sortFlag;
    
    /** 옵션명. */
    private String itmNm;
    
    /** 리뷰보기 flag. */
    private String reviewFlag;
    
    /** 디자인 그룹 번호. */
    private String dsgnGrpNo;
    
    /** 상품 번호. */
    private String godNo;
    
    /** 상품평 순번. */
    private int godEvlTurn;
    
    /*2자리 코드*/
    private String language;
    
    private String moreFlag;
    
    private String pageNo;

    private String godEvlExpsrSectCd;
    
    private String reviewDisplayType;

    private String mbrId;
    
    private String mbrNo; // 0525 ash
    
	/**  기획전상품평 포토상품평 우선여부  0525 ash */
    private String imgGodEvlPrioExpsrYn;
    
    private String mbrSizeClfcCd;
    
    private String langCd;
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
