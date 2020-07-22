package com.plgrim.ncp.biz.goods.result;

import org.apache.ibatis.type.Alias;

import com.plgrim.ncp.base.abstracts.AbstractResult;

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
 * Description	:	상품 RESULT
 *
 * @Author 	:	neps
 * @Date   	:	2018. 5. 3.
 * @Version	:	
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Alias("goodsResult")
public class GoodsResult extends AbstractResult{
	private static final long serialVersionUID = -8748745725562557963L;

	/** 결과코드 */
	private String rstCd;
	
	/** 결과 메세지 */
	private String rstMsg;	
	
	/** 결과 카운트 */
	private int rstCnt;
	
	/** 요청 카운트 */
	private int reqCnt;
	
	/** ERP 상품번호 */
	private String erpGodNo;
	
	/** 온라인 상품번호 */
	private String godNo;
	
	/** 상품명 */
	private String godNm;
	
	/** 브랜드 아이디 */
	private String brndId;
	
	/** 브랜드명 */
	private String brndNm;
	
}
