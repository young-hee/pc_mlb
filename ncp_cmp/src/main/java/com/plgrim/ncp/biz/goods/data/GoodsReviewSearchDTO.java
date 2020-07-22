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
 * Description	:	상품 리뷰 검색 DTO
 *
 * @Author 	:	neps
 * @Date   	:	2018. 5. 3.
 * @Version	:	
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Alias("goodsReviewSearchDTO")
public class GoodsReviewSearchDTO extends AbstractDTO {
    private static final long serialVersionUID = 3822218401060855922L;

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
    
    /** 상품번호. */
    private String godNo;

    /** ERP상품번호. */
    private String erpGodNo;    
    
    /** 디자인 그룹번호 */
    private String dsgnGrpNo;
    
    /** 회원번호 */
    private String mbrNo;

    /** 회원ID */
    private String mbrId;
    
    /** 몰구분 */
    private String mallTpCd;
    
	/** 포토 리뷰 여부 */
	private String photoReviewYn;
	
	/** 상품평 순번 */
	private java.lang.Integer godEvlTurn;
	
	private String bstGodEvlYn;

    /** 정렬 flag. */
    private String sortFlag;   
    
    
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
