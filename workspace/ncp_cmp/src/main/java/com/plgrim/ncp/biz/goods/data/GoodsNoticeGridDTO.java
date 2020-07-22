package com.plgrim.ncp.biz.goods.data;

import java.util.ArrayList;
import java.util.List;

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
 * Description	:	상품 공지사항 Grid DTO
 *
 * @Author 	:	neps
 * @Date   	:	2018. 5. 3.
 * @Version	:	
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Alias("goodsNoticeGridDTO")
public class GoodsNoticeGridDTO extends AbstractDTO {
    private static final long serialVersionUID = -518392264045239486L;

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */    
    private List<GoodsNoticeDTO> goodsNoticeGridList = new ArrayList<GoodsNoticeDTO>();
    
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
