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
 * Description	:	상품 시리즈/라인 검색 DTO
 *
 * @Author 	:	neps
 * @Date   	:	2018. 5. 3.
 * @Version	:	
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Alias("goodsSeriesLineSerachDTO")
public class GoodsSeriesLineSerachDTO extends AbstractDTO {
    private static final long serialVersionUID = 6191456068709124963L;

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
    /** 등록자 */
    private String regTrId;
    
    /**
 	 * 사용자 이름 
	 */
    private String userNm;
    
    /** 시작일자 */
    private String startTermDt;
    
    /** 종료일자 */
    private String endTermDt;
    
    /** 시리즈 이름 */
    private String srsNm;
    
    /** 라인 이름 */
    private String lineNm;
    
    /** 시리즈 라인구분. */
    private String gbn;
    
    /** 시리즈,라인 번호 멀티 */
    private String[] gridIds;

    /** 사업부 브랜드 Id*/
    private String endpBrndId;
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
