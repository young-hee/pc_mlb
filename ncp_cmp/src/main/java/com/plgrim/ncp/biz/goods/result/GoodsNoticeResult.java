package com.plgrim.ncp.biz.goods.result;

import java.util.List;

import org.apache.ibatis.type.Alias;

import com.plgrim.ncp.base.entities.datasource1.god.GodNoti;
import com.plgrim.ncp.base.entities.datasource1.god.GodNotiBrndCnnc;
import com.plgrim.ncp.base.entities.datasource1.god.GodNotiExtend;
import com.plgrim.ncp.base.entities.datasource1.god.GodNotiGodCnnc;

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
 * Description	:	상품 공지사항 RESULT
 *
 * @Author 	:	neps
 * @Date   	:	2018. 5. 3.
 * @Version	:	
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Alias("goodsNoticeResult")
public class GoodsNoticeResult extends GoodsResult{
    private static final long serialVersionUID = -2760735906564861790L;
	
	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */    
    /** 공지사항 정보 엔티티 */
    private GodNoti godNoti;
    
    /** 공지사항 확장 정보 엔티티 */
    private GodNotiExtend godNotiEx;
    
    /** 공지사항 상품 정보 엔티티 */
    private GodNotiGodCnnc godNotiGodCnnc;
    
    /** 공지사항 브랜드 정보 엔티티 */
    private GodNotiBrndCnnc godNotiBrndCnnc;
    
    /** 공지사항 확장 정보 엔티티 리스트 */
    private List<GodNotiExtend> godNotiExList;
  
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
