package com.plgrim.ncp.interfaces.crema.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.plgrim.ncp.base.abstracts.AbstractSDO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
@JsonInclude(value=Include.NON_EMPTY)
@JsonIgnoreProperties({"mallId"})
public class CremaCartItemSDO extends AbstractSDO {

	private static final long serialVersionUID = 8352401981468776119L;

	private String mallId;

	@JsonProperty("access_token")
	private String accessToken;

	/** 회원의 쇼핑몰 아이디 */
	@JsonProperty("user_code")
	private String userCode;

	/** 장바구니가 속한 상품의 크리마 서비스측 id(쇼핑몰 서비스측 ID 아님) product_code를 입력한 경우 product_id는 입력하지 않아도 됩니다. */
	@JsonProperty("product_id")
	private String productId;

	/** 장바구니가 속한 상품의 쇼핑몰 서비스측 고유 식별자. product_id를 입력한 경우 product_code는 입력하지 않아도 됩니다. */
	@JsonProperty("product_code")
	private String productCode;

	/** 쇼핑몰에서 사용되고 있는 장바구니 항목의 고유 ID */
	private String code;

	/** 장바구니에 추가된 시간. 초단위까지만 유효 */
	@JsonProperty("added_to_cart_at")
	private String addedToCartAt;

}