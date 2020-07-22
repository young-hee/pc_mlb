package com.plgrim.ncp.biz.goods.data;

import org.apache.ibatis.type.Alias;

import com.plgrim.ncp.base.abstracts.AbstractDTO;

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
 * Description	:	상품 컨텐츠 검색 DTO
 *
 * @Author 	:	neps
 * @Date   	:	2018. 5. 3.
 * @Version	:	
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Alias("goodsContentSearchDTO")
public class GoodsContentSearchDTO extends AbstractDTO {
    private static final long serialVersionUID = 3822218401060855922L;

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */

    /**
     * 이벤트 전시 대상 유형코드
     */
    private String evtTgtTp;
    
    /**
     * 대상 회원 유형 코드
     */
    private String tgtMbrTp;
    
    /**
     * 대상 회원 속성 코드
     */
    private String tgtMbrAtrb;
    
    /**
     * 상품 번호
     */
    private String godNo;

    /**
     * 프로모션번호
     */
    private String prmNo;
    
    /**
     * 프로모션번호
     */
    private String[] prmNos;
    
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
