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
 * Description	:	상품 모델 검색 파라미터 DTO
 *
 * @Author 	:	neps
 * @Date   	:	2018. 5. 3.
 * @Version	:	
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Alias("goodsModelSearchDTO")
public class GoodsModelSearchDTO extends AbstractDTO{
    private static final long serialVersionUID = 5379859663185474609L;
    
    /*
     * ---------------------------------------------------------------------
     * Instance fields.
     * ---------------------------------------------------------------------
     */
    
    /**
     * 모델번호
     */
    private String modelNo;

    /**
     * 모델이름
     */
    private String modelNm;
    
    /**
     * 사업부 브랜드 코드
     */
    private String endpBrndId;
    
    /**
     * 성별코드
     */
    private String sexCd;
    
    /**
     * 모델 조회 구분
     */
    private String modelInqireSectCd;
    
    /**
     * 모델 조회 텍스트
     * no : 번호
     * name : 이름
     */    
    private String modelInqireText;
    
    /**
     * 등록 사이즈 (배열)
     */
    private String [] wearSizeCds;

    /**
     * 등록 사이즈
     */
    private String wearSizeCd;
    
    /**
     * 미전시 사이즈
     */
    private String nonDspSize;
    
    /**
     * 미사용 브랜드
     */
    private String nonEndpBrnd;    
    
    /**
     * 사용여부
     */
    private String useYn;

    /**
     * 전시여부
     */
    private String dspYn;
    
	/**
	 * 모델 이미지 순번	 
	 */
	private java.lang.Integer modelImgTurn;
	
	/**
	 * 상품번호	 
	 */
	private String godNo;
	
	/**
	 * 브랜드 코드	 
	 */
	private String brndId;

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
