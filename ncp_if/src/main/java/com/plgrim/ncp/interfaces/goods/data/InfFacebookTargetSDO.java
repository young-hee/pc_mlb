package com.plgrim.ncp.interfaces.goods.data;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.plgrim.ncp.base.abstracts.AbstractSDO;
import com.plgrim.ncp.interfaces.abstracts.InterfaceSDO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
@JsonInclude(value = Include.NON_EMPTY)
public class InfFacebookTargetSDO extends AbstractSDO {
	private static final long serialVersionUID = -3078213173831157161L;
	
	/*******************************************************************************
     * Request parameter
     *******************************************************************************/	
	
	private List<String> additional_image_urls;		// 추가 이미지 (list<string>)
	private String availability;				// 재고 상태 (enum {in stock, out of stock, preorder, available for order, discontinued, pending})
	private String age_group;					// 추천 연령 (enum {adult, all ages, infant, kids, newborn, teen, toddler})
	private String applinks;
	private String brand;						// string brand
	private String category;					// Max size: 250
	private String color;						// string
	private String condition;
	private String currency;					// string
	private String custom_label_0;				// string
	private String custom_label_1;				// string
	private String custom_label_2;				// string
	private String custom_label_3;				// string
	private String custom_label_4;				// string
	private String description;					// string
	private String gender;						// 추천 성별 (enum {female, male, unisex})
	private String gtin;						// string, 이건 머여..
	private String image_url;					// string, 1200x630px
	private Integer inventory;					// integer
	private String manufacturer_part_number;
	private String name;						// Name of the product item
	private String pattern;
	private String price;						// string
	private String product_type;				// string, delimited by commas 
	private String sale_price;					// string
	private String sale_price_end_date;			// string
	private String sale_price_start_date;		// string
	private String shipping;
	private String size;						// string
	private String url;							// URL of the product item
	
}
