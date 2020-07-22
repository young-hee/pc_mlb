/* Copyright (c) 2013 plgrim, Inc.
 * All right reserved.
 * http://plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 *
 * Revision History
 * Author              Date                         Description
 * ------------------   --------------                  ------------------
 *                       
 */
package com.plgrim.ncp.commons.taglib;

import com.plgrim.ncp.commons.repository.GoodsImageRepository;

/**
 * JSP Page Option Custom Tag Lib
 * @author Park
 */

public class UrlGoodImageTag extends UrlTag {
	
	String goodNo;
	String size;
	String imgTurn;
	GoodsImageRepository goodsImageRepository;
	/**
	 * UID
	 */
    private static final long serialVersionUID = 6909370419303809488L;


	@Override
    String getDefaultRootURI(){

		try {
			GoodsImageRepository goodsImageRepository = (GoodsImageRepository) getRequestContext().getWebApplicationContext().getBean(GoodsImageRepository.class);
	        this.path = goodsImageRepository.selectRprstGodImagePath(goodNo,size,imgTurn);
        }
        catch (Exception e) {
        	this.path = "";
        }
		//this.path = "/15/01/02/"+size+'/'+goodNo+".jpg";
	    return CodeBean.urlImage;
    }
	
	public void setGoodNo(String goodNo) {
		this.goodNo = goodNo;
	}

	public void setSize(String size) {
		this.size = size;
	}
	
	public void setImgTurn(String imgTurn){
		this.imgTurn = imgTurn;
	}

}
