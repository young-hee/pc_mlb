package com.plgrim.ncp.biz.delivery.result;

import com.plgrim.ncp.base.abstracts.AbstractResult;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class DeliveryArticleResult extends AbstractResult {

    private static final long serialVersionUID = 4535518680341599969L;

	private String endpBrndNm;     // 사업자 브랜드명
	private String brandBox1;      // 브랜드박스(소)
	private String brandBox2;      // 브랜드박스(중)
	private String brandBox3;      // 브랜드박스(대)
	private String shoppingBag1;   // 쇼핑백(소)
	private String shoppingBag2;   // 쇼핑백(중)
	private String shoppingBag3;   // 쇼핑백(대)
	private String shoppingBag4;   // 쇼핑백(특대)

}
