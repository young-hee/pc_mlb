package com.plgrim.ncp.biz.goods.data;

import org.apache.ibatis.type.Alias;

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
 * Description	:	태그 검색 DTO
 *
 * @Author 	:	neps
 * @Date   	:	2018. 5. 3.
 * @Version	:	
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Alias("goodsTagSearchDTO")
public class GoodsTagSearchDTO extends GoodsSearchDTO {
	private static final long serialVersionUID = -3733735846535581053L;

    /** 태그명 */
    private String tagNm;
    
    /** 태그예약순번 */
    private String tagResveSn;
	
    /** 상품태그 예약시작일시 */
    private String resveBegDtStr;

    /** 상품태그 예약종료일시 */
    private String resveEndDtStr;

}
