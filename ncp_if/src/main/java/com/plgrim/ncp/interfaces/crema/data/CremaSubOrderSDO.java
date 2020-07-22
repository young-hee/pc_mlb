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
@JsonIgnoreProperties({"mallId", "productOptions"})
public class CremaSubOrderSDO extends AbstractSDO {

	private static final long serialVersionUID = 8352401981468776119L;

	private String mallId;

	@JsonProperty("access_token")
	private String accessToken;

	/** 주문 번호. 주문의 고유 식별자입니다. /v1/orders 에 사용하는 code와 같은 값입니다. */
	@JsonProperty("order_code")
	private String orderCode;

	/** 주문상품의 code. 주문상품의 고유 식별자입니다. 같은 주문 내 다른 상품과 중복되면 안 됩니다. */
	private String code;

	/** 주문상품에 속한 상품의 크리마 서비스측 id(쇼핑몰 서비스측 id 아님), product_code를 입력한 경우 product_id는 입력하지 않아도 됩니다. */
	@JsonProperty("product_id")
	private Integer productId;

	/** 주문상품에 속한 상품의 쇼핑몰 서비스측 고유 식별자, product_id를 입력한 경우 product_code는 입력하지 않아도 됩니다. */
	@JsonProperty("product_code")
	private String productCode;

	/** 주문상품의 가격. 단일 상품 구매 가격인 아닌, '상품가격 X 구매수량' 입니다. 구매취소/부분취소/할인과 무관합니다. */
	private Float price;

	/** 주문상품 구매 수량 */
	@JsonProperty("product_count")
	private Integer productCount;

	private String productOptions;

	/** 주문상품 구매 옵션. 여러개의 product_options[]에 <key>:<value> 포맷으로 추가. (이 페이지 하단의 Example을 참조) 핏·리뷰 서비스를 이용하는 경우 필수로 입력해야 합니다. */
	@JsonProperty("product_options")
	private String[] productOptionList;

	/** 주문상품의 상태. 사용 가능한 값은 주문상태 페이지를 참조해주세요. */
	private String status;

	/** 배송 시작일. 배송 시작하면 값을 넣어줘야 합니다. 이 값이 없는 주문에 대해서는 리뷰 문자와 이메일이 발송되지 않습니다. */
	@JsonProperty("delivery_started_at")
	private String deliveryStartedAt;

	/** 배송 완료일 */
	@JsonProperty("delivered_at")
	private String deliveredAt;

	/** 송장번호 */
	@JsonProperty("invoice")
	private String invoice;

	/** 배송업체. 사용 가능한 값은 택배사 페이지를 참조해주세요. */
	@JsonProperty("delivery_service")
	private String deliveryService;

}