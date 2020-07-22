package com.plgrim.ncp.cmp.product.bo;

import java.util.List;

import com.plgrim.ncp.base.entities.datasource1.god.GodStdCtgryExtend;
import com.plgrim.ncp.base.entities.datasource1.sys.SysPrdlstCd;
import com.plgrim.ncp.biz.goods.data.GoodsCategoryDTO;
import com.plgrim.ncp.framework.data.SystemPK;

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
 * Description	:	상품 표준카테고리 Component 
 *
 * @Author 	:	neps
 * @Date   	:	2018. 5. 3.
 * @Version	:	
 *
 */
public interface GoodsStandardCategoryBOComponent {
	
	/**
	 * 표준카테고리 목록 조회
	 * 
	 * @param systemPK
	 * @param categoryDTO
	 * @return
	 */
	public List<GodStdCtgryExtend> searchStandardCategoryList(SystemPK systemPK, GoodsCategoryDTO categoryDTO);
	
	/**
	 * 표준카테고리 상세 조회
	 * 
	 * @param systemPK
	 * @param categoryDTO
	 * @return
	 */
	public GodStdCtgryExtend getStandardCategoryDetail(SystemPK systemPK, GoodsCategoryDTO categoryDTO);

	/**
	 * 표준카테고리에 연결된 시스템 품목 목록 조회
	 * 
	 * @param systemPK
	 * @param stdCtgryNo
	 * @return
	 */
	public List<SysPrdlstCd> getStandardCategoryConnectedSystemCategoryList(SystemPK systemPK, String stdCtgryNo);
}
