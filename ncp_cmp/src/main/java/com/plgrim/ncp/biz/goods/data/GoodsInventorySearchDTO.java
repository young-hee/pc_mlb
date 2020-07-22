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
 * Description	:	상품 재고 검색 DTO
 *
 * @Author 	:	neps
 * @Date   	:	2018. 7. 25.
 * @Version	:	
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Alias("ㅎoodsInventorySearchDTO")
public class GoodsInventorySearchDTO extends AbstractDTO {
    private static final long serialVersionUID = 3822218401060855922L;

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	/** 다중검색 상품번호 구분. */	
	private String godNosGbn;
	
    /** 상품번호. */
    private String godNo;
    
    /** 단품번호. */
    private String itmNo;
    
    /** ERP상품번호. */
    private String erpGodNo;
    
    /** 상품번호 멀티 */
    private String[] godNos;

    /** 브랜드ID 멀티. */
    private String[] brndIds;
    
    /** 브랜드ID. */
    private String brndId;
    
    /** 상품유형코드. */
    private String godTpCd;
    			
	/** 검색기간. */
	private String term;
	
	/** 검색기간 시작일. */
	private String startTermDt;
	
	/** 검색기간 시작시간 */
	private String startTermHh;
	
	/** 검색기간 시작분 */
	private String startTermMm;
	
	/** 검색기간 종료일. */
	private String endTermDt;
	
	/** 검색기간 종료시간 */
	private String endTermHh;
	
	/** 검색기간 종료분 */
	private String endTermMm;
	
	/** 매장명 */
	private String shopNm;
    
    /** 관리자 유형 코드 */
    private String adminTpCd;
    
    /** 창고여부 */
    private String wrhusYn;

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
