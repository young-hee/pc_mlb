package com.plgrim.ncp.biz.goods.result;

import java.util.List;

import org.apache.ibatis.type.Alias;

import com.plgrim.ncp.base.entities.datasource1.god.God;
import com.plgrim.ncp.base.entities.datasource1.god.GodCpstGodCnncExtend;
import com.plgrim.ncp.base.entities.datasource1.god.GodExtend;
import com.plgrim.ncp.base.entities.datasource1.god.GodImg;
import com.plgrim.ncp.base.entities.datasource1.god.GodItmExtend;

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
 * Description	:	상품 옵션 RESULT
 *
 * @Author 	:	neps
 * @Date   	:	2018. 6. 4.
 * @Version	:	
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Alias("goodsOptionResult ")
public class GoodsOptionResult extends GoodsResult{
	private static final long serialVersionUID = -4916161291578706881L;

	/**
	 * 상품 정보
	 */
	private God god;
	
	/**
	 * 단품 목록
	 */
	private List<GodItmExtend> itemList;
	
	/**
	 * 이미지 - THUMNAIL
	 */
	private GodImg iamge;
	
	/**
	 * 이미지 목록 - DETAIL
	 */
	private List<GodImg> imageList;
	
	/**
	 * 색상 목록
	 */
	private List<GodExtend> designColorList;
	
	/**
	 * 구성품 목록
	 */
	private List<GodCpstGodCnncExtend> cpstList;

}
