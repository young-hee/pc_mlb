package com.plgrim.ncp.interfaces.goods.data;

import lombok.Data;

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
 * Description	:	Naver EP Column	
 *
 * @Author 	:	neps
 * @Date   	:	2018. 7. 5.
 * @Version	:	
 *
 */
@Data
public class FacebookEpData {
	
	private String retailer_id;					// string, 판매자 상품 아이디
	private String godNo;						// string 상품번호
	private String add_img_url;					// 추가 이미지 (list<string>)
	private String availability;				// 재고 상태 (enum {in stock, out of stock, preorder, available for order, discontinued, pending})
	private String age_group;					// 추천 연령 (enum {adult, all ages, infant, kids, newborn, teen, toddler})
	private String brandId;						// string brand
	private String brand;						// string brand
	private String category;					// Max size: 250
	private String color;						// string
	private String currency;					// string
	private String description;					// string
	private String gender;						// 추천 성별 (enum {female, male, unisex})
	private String image_url;					// string, 1200x630px
	private String name;						// Name of the product item
	private String price;						// string
	private String product_type;				// string, delimited by commas 
	private String sale_price;					// string
	private String url;							// URL of the product item
	private String mallId;
	private String transGbn;
	
}
