package com.plgrim.ncp.biz.goods.data;

import org.apache.ibatis.type.Alias;

import com.plgrim.ncp.base.abstracts.AbstractDTO;
import com.plgrim.ncp.base.entities.datasource1.cso.CsoCnsltMemo;
import com.plgrim.ncp.base.entities.datasource1.god.GodNoti;
import com.plgrim.ncp.base.entities.datasource1.god.GodNotiBrndCnnc;
import com.plgrim.ncp.base.entities.datasource1.god.GodNotiGodCnnc;
import com.plgrim.ncp.base.entities.datasource1.god.GodTagResveCnncExtend;
import com.plgrim.ncp.base.entities.datasource1.god.GodTagResveExtend;

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
 * Description	:	컨텐츠 DTO
 *
 * @Author 	:	neps
 * @Date   	:	2018. 5. 3.
 * @Version	:	
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Alias("goodsContentDTO")
public class GoodsContentDTO extends AbstractDTO{
	private static final long serialVersionUID = -7672057354863775990L;
	
	/** 상품 태그예약정보 엔티티 */
    private GodTagResveExtend godTagResveEx;

    /** 상품 태그예약 연결 상품정보 엔티티 */
    private GodTagResveCnncExtend godTagResveCnncEx;
    
    /**	공지사항정보 엔티티 */
    private GodNoti godNoti;
    
    /**	공지사항 브랜드 연결정보 엔티티 */
    private GodNotiBrndCnnc godNotiBrndCnnc;
    
    /**	공지사항 상품 연결정보 엔티티 */
    private GodNotiGodCnnc godNotiGodCnnc;
    
    /** 상품 메모 */
    private CsoCnsltMemo csoCnsltMemo;
    
}
