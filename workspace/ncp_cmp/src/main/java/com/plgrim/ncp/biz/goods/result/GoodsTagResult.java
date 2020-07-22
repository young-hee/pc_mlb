package com.plgrim.ncp.biz.goods.result;

import org.apache.ibatis.type.Alias;

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
 * Description	:	상품 태그 RESULT
 *
 * @Author 	:	neps
 * @Date   	:	2018. 5. 3.
 * @Version	:	
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Alias("goodsTagResult")
public class GoodsTagResult extends GoodsResult{	
	private static final long serialVersionUID = -7302886944375343439L;
	
    /** 상품 태그예약정보 엔티티 */
	private GodTagResveExtend godTagResveEx;
	
    /** 상품 태그예약정보 상품 엔티티 */
    private GodTagResveCnncExtend godTagResveCnncEx;
    
}
