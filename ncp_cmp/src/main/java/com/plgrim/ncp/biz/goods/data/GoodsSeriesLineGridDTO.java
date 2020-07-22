package com.plgrim.ncp.biz.goods.data;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.type.Alias;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractDTO;

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
 * Description	:	상품 시리즈/라인 Grid DTO
 *
 * @Author 	:	neps
 * @Date   	:	2018. 5. 3.
 * @Version	:	
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Alias("goodsSeriesLineGridDTO")
public class GoodsSeriesLineGridDTO extends AbstractDTO {
    private static final long serialVersionUID = -518392264045239486L;

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
    /** 로그인 아이디. */
    String loginId;    

    private List<GoodsSeriesLineDTO> seriesList = new ArrayList<>();
    
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

    public void setLoginId(String loginId) {
    	this.loginId = loginId;
    	
    	if(!(seriesList.isEmpty())){
    		for(GoodsSeriesLineDTO goodsSeries : seriesList){
    			goodsSeries.setRegTrId(loginId);
    		}
    	}
    }
}
