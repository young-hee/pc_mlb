package com.plgrim.ncp.biz.goods.result;

import java.util.List;

import org.apache.ibatis.type.Alias;

import com.plgrim.ncp.base.entities.datasource1.god.GodModel;
import com.plgrim.ncp.base.entities.datasource1.god.GodModelImg;
import com.plgrim.ncp.base.entities.datasource1.god.GodModelImgCnnc;
import com.plgrim.ncp.base.entities.datasource1.god.GodModelImgExtend;
import com.plgrim.ncp.base.entities.datasource1.god.GodModelSize;

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
 * Description	:	상품 모델 RESULT
 *
 * @Author 	:	neps
 * @Date   	:	2018. 5. 3.
 * @Version	:	
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Alias("goodsModelResult")
public class GoodsModelResult extends GoodsResult{
    private static final long serialVersionUID = 5550306474993538196L;

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
    private String godNo;
    
    private GodModel godModel;
    
    private GodModelImgCnnc godModelImgCnnc;
    
    private GodModelImg godModelImg;
    
    private List<GodModelSize> godModelSizeList;
    
    private List<GodModelImg> godModelImgList;
    
    private List<GodModelImgExtend> godModelImgExList;
    
    private String useBrnds;
    
    private String noUseBrnds;
    
    private int nonDspSizeCnt;
    
    private int totalRow;

    private String sexNm;
    
    private String modelHeight;

    private String modelWeight;
    
    private String modelWaist;
    
    
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
