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
 * Description	:	상품 Excel DTO
 *
 * @Author 	:	neps
 * @Date   	:	2018. 5. 3.
 * @Version	:	
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Alias("goodsDsgnGrpExcelDTO")
public class GoodsDsgnGrpExcelDTO extends GoodsValidDTO{
 
	private String  dsgnGrpNo;
	
	private String  dsgnGrpCnncNo;
	
	private String  grpCnncNo;
	
	private String  brndId;
	 
}
