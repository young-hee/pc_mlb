package com.plgrim.ncp.interfaces.crema.data;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.plgrim.ncp.base.abstracts.AbstractSDO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
@JsonIgnoreProperties({"brandId","categoryCodes","productOptions","subProductCodes"})
@JsonInclude(value=Include.NON_EMPTY)
public class CremaProductSDO extends AbstractSDO {

	private static final long serialVersionUID = 8352401981468776119L;

	private String brandId;

	@JsonProperty("access_token")
	private String accessToken;

	/** 상품 code. 상품의 고유 식별자입니다. 다른 상품과 중복되면 안됩니다. */
	private String code;

	/** 상품의 이름 */
	private String name;

	/** 상품의 URL */
	private String url;

	/** 상품의 원가(원 또는 locale 설정 별 소수점 화폐 단위 (e.g. $1.1 일 경우 1.1 입력)) */
	@JsonProperty("org_price")
	private String orgPrice;

	/** 상품의 판매가(원 또는 locale 설정 별 소수점 화폐 단위 (e.g. $1.1 일 경우 1.1 입력)) */
	@JsonProperty("final_price")
	private String finalPrice;

	private String categoryCodes;

	/** 상품의 카테고리 목록. 카테고리의 code의 array 형식으로 입력합니다. */
	@JsonProperty("category_codes")
	private String[] categoryCodeList;

	/** 상품의 상태. 진열중일 경우 1, 미진열일 경우 0을 입력합니다. */
	private String display;

	/** 상품 이미지 주소 */
	@JsonProperty("image_url")
	private String imageUrl;

	/** 상품의 재고량. 기본값은 1입니다. 품절 상품의 경우 0으로 입력합니다. */
	@JsonProperty("stock_count")
	private Integer stockCount;

	private String productOptions;
	/** 
	 * 상품 판매 옵션을 배열로 입력합니다. 핏 서비스를 이용하는 경우 필수로 입력해야 합니다.
	 * 예시(Content-Type) application/json: [{'name': 'size', 'values': ['S', 'M']}, {'name': 'color', 'values': ['black', 'white']}]
	 */
	@JsonProperty("product_options")
	private List<Map<String, Object>> productOptionList;

	/** 서브 브랜드 id. sub_brand_code를 입력한 경우 sub_brand_id는 입력하지 않아도 됩니다. */
	@JsonProperty("sub_brand_id")
	private Integer subBrandId;

	/** 서브 브랜드 코드. sub_brand_id를 입력한 경우 sub_brand_code는 입력하지 않아도 됩니다. */
	@JsonProperty("sub_brand_code")
	private String subBrandCode;

	/** 
	 * 세트상품의 id 목록. 부속 상품의 id 값들을 array 형식으로 입력합니다. 
	 * sub_product_codes를 입력한 경우 sub_product_ids는 입력하지 않아도 됩니다. 기존 세트 상품들을 제거하고 싶다면 빈 문자열을 입력하면 됩니다.
	 */
	@JsonProperty("sub_product_ids")
	private List<String> subProductIds;

	private String subProductCodes;

	/** 
	 * 트상품의 코드 목록. 부속 상품의 code 값들을 array 형식으로 입력합니다. sub_product_ids를 입력한 경우 sub_product_codes는 입력하지 않아도 됩니다. 
	 * 기존 세트 상품들을 제거하고 싶다면 빈 문자열을 입력하면 됩니다.
	 * */
	@JsonProperty("sub_product_codes")
	private String[] subProductCodeList;

	/** 상품 요약 설명. 타겟 개인화 위젯을 사용하는 경우에만 필수로 입력하면 됩니다.(최대 길이는 255자 입니다.) */
	@JsonProperty("simple_description")
	private String simpleDescription;

	/** 상품 등록일. 초단위까지만 유효. 타겟 서비스를 이용하는 경우 필수로 입력해야 합니다. */
	@JsonProperty("shop_builder_created_at")
	private String shopBuilderCreatedAt;

	/** 상품 수정일. 초단위까지만 유효. 타겟 서비스를 이용하는 경우 필수로 입력해야 합니다. */
	@JsonProperty("shop_builder_updated_at")
	private String shopBuilderUpdatedAt;

	/** 
	 * 상품 색상 목록을 배열로 입력합니다. 타겟 개인화 위젯을 사용하는 경우에만 필수로 입력하면 됩니다. 한 문자열은 한 개의 색상 (컬러칩)을 의미합니다. 
	 * 한 색상에 두 가지 이상의 색상을 보여줘야 할 경우 (멀티 칼라칩), '#aaaaaa, #bbbbbb' 같이 ,로 색상을 구분하면 됩니다. 
	 * 상품 색상은 '#'를 포함한 6자리 hex 코드로 입력해야 합니다. 
	 * 예시: ['#ffffff', '#aaaaaa, #bbbbbb']
	 */
	private List<String> colors;

	/** 상품 아이콘 코드 목록을 배열로 입력합니다. 
	 * 타겟 개인화 위젯을 사용하는 경우에만 필수로 입력하면 됩니다. 
	 * 상품 아이콘 표시기간은 시작날짜와 끝날짜를 모두 입력하거나 입력하지 않습니다. 
	 * 예시: [{'code': 'ps001'}, {'code': 'ps002', 'start_at': '2019-08-10T08:39:52.000+09:00', 'end_at': '2019-08-17T08:39:52.000+09:00'}]
	 */
	private String icons;

}