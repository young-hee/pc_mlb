package com.plgrim.ncp.biz.goods.data;

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
 * Description	:	상품 검색 DTO
 *
 * @Author 	:	neps
 * @Date   	:	2018. 5. 3.
 * @Version	:	
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Alias("monolabsDTO")
public class MonolabsDTO extends AbstractDTO {
  
	/**
	 * 
	 */
	private static final long serialVersionUID = 9180764613322296830L;

	 
    /** ERP상품번호. */
    private String erpGodNo;
    
    private String key;
    
    private String token;
    
    private String language;
    
    
    
    private String skey;
    
    private String pw;
    
    private String api_key;
}
