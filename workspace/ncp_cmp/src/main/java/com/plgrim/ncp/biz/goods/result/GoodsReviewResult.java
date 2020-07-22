package com.plgrim.ncp.biz.goods.result;

import java.util.List;

import org.apache.ibatis.type.Alias;
import org.springframework.data.domain.Page;

import com.plgrim.ncp.base.entities.datasource1.god.GodGodEvl;
import com.plgrim.ncp.base.entities.datasource1.god.GodGodEvlAtchFile;
import com.plgrim.ncp.base.entities.datasource1.god.GodGodEvlExtend;

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
 * Description	:	상품 리뷰 RESULT
 *
 * @Author 	:	neps
 * @Date   	:	2018. 5. 3.
 * @Version	:	
 *
 */
@Data
@EqualsAndHashCode(callSuper=false)
@Alias("goodsReviewResult")
public class GoodsReviewResult extends GoodsResult {

	private static final long serialVersionUID = 3559926488934861590L;

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */

	/** 상품 상품평 엔티티. */
	private GodGodEvl godGodEvl;
    
	/** 상품 상품평 확장 엔티티. */
	private GodGodEvlExtend godGodEvlEx;
	
	/** 상품명. */
	private String godNm;

	/** 상품 상품평 확장 목록. */
	private Page<GodGodEvlExtend> godGodEvlExList;
	
    /** 상품 상품평 첨부 파일 리스트. */
    private List<GodGodEvlAtchFile> godGodEvlAtchFileList;
    
	/** 상품 상품평 평점 리스트. */
	private GodGodEvlExtend reviewAverageScore;
    
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
