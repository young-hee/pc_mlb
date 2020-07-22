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
 * Description	:	공지사항 검색 DTO
 *
 * @Author 	:	neps
 * @Date   	:	2018. 5. 3.
 * @Version	:	
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Alias("goodsNoticeSearchDTO")
public class GoodsNoticeSearchDTO extends AbstractDTO {
    private static final long serialVersionUID = 3822218401060855922L;

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
    
    /** 검색기간 구분. */
	private String term;
	
    /** 검색기간 시작일. */
	private String startTermDt;
	
	/** 검색기간 종료일. */
	private String endTermDt;
	
	/** 공지사항 구분 */
	private String notiGbn;
	
	/** 전시여부. */
    private String dspYn;
    
    /** 브랜드ID. */
    private String brndId;

    private String[] brndIds;
    
    /** 상품ID. */
    private String godNo;
    
    /** 제목 */
    private String notiSj;
    
    /** 등록자 */
    private String regtrNm;
    
    /** 수정자 */
    private String udterNm;
    
    /** 공지순번 */
    private String godNotiSn;
    
    /** 다중검색 상품번호 구분. */	
	private String godNosGbn;
	
	/** 상품번호 멀티*/
    private String[] godNos;
    
    /** 페이지 번호. */
	private String pageNo;
	
	/** 언어 멀티*/
	private String[] langs;
	
	/** 공지구분코드 */
	private String godNotiSectCd;
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
