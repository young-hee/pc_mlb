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
public class CremaOffOrderSDO extends AbstractSDO {

	private static final long serialVersionUID = 8352401981468776119L;

	private String mallId;

	@JsonProperty("ACCESS_TOKEN")
	private String accessToken;

	/** 주문 번호. 주문의 고유 식별자입니다. 다른 주문과 중복되면 안됩니다. */
	@JsonProperty("CODE")
	private String code;

	/** 주문 생성일. 초단위까지만 유효 */
	@JsonProperty("CREATED_AT")
	private String createdAt;

	/** 주문 실결제금액(쿠폰, 적립금을 제외한 결제 금액) */
	@JsonProperty("TOTAL_PRICE")
	private String totalPrice;

	/** 구매자 쇼핑몰 아이디(회원 주문이라면 값을 넣어줘야 합니다. 이 값이 없는 경우 비회원 주문으로 등록됩니다.) */
	@JsonProperty("USER_CODE")
	private String userCode;

	/** 구매자 이름 */
	@JsonProperty("USER_NAME")
	private String userName;

	/** 구매자 휴대폰 번호 */
	@JsonProperty("USER_PHONE")
	private String userPhone;

	/** 구매자 이메일 주소. Email 주소가 있으면 값을 넣어줘야 합니다. 이 값이 없는 경우 리뷰 이메일이 발송되지 않습니다. */
	@JsonProperty("USER_EMAIL")
	private String userEmail;

	/** 회원등급 id. 회원 등급이 있으면 값을 넣어줘야 합니다. 이 값이 없는 경우 리뷰 게시판에 회원등급이 노출되지 않습니다. */
	@JsonProperty("USER_GRADE_ID")
	private Integer userGradeId;

	/** 오프라인 매장 주문일 경우 매장명 */
	@JsonProperty("STORE_NAME")
	private String storeName;

	/** 주문 경로. PC에서 주문한 경우 pc, 모바일에서 주문한 경우 mobile을 입력합니다. */
	@JsonProperty("ORDER_DEVICE")
	private String orderDevice;

}