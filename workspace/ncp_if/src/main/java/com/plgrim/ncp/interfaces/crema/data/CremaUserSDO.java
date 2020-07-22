package com.plgrim.ncp.interfaces.crema.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.plgrim.ncp.base.abstracts.AbstractSDO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
@JsonInclude(value=Include.NON_EMPTY)
public class CremaUserSDO extends AbstractSDO {

	private static final long serialVersionUID = 8352401981468776119L;

	private String mallId;

	@JsonProperty("access_token")
	private String accessToken;

	/** 회원의 쇼핑몰 아이디. 다른 회원와 중복되면 안됩니다. */
	@JsonProperty("user_id")
	private String userId;

	/** 이름 */
	@JsonProperty("user_name")
	private String userName;

	/** 가입일. 초단위까지만 유효. 타겟 서비스를 이용하는 경우 필수로 입력해야 합니다. */
	@JsonProperty("created_at")
	private String createdAt;

	/** 휴대폰 번호. 예시: 010-1234-5678, 01012345678 */
	@JsonProperty("user_phone")
	private String userPhone;

	/** SMS 문자 수신 여부. 수신 허용 시 1, 수신 거부 시 0 */
	@JsonProperty("allow_sms")
	private Integer allowSms	;

	/** 
	 * email 주소. 예시: sample@sample.com
	 * Email 주소가 있으면 값을 넣어줘야 합니다. 이 값이 없는 경우 리뷰 이메일이 발송되지 않습니다. 
	 */
	@JsonProperty("user_email")
	private String userEmail;

	/** email 수신 여부. 수신 허용 시 1, 수신 거부 시 0 */
	@JsonProperty("allow_email")
	private Integer allowEmail;

	/** 회원등급 ID. 회원 등급이 있으면 값을 넣어줘야 합니다. 이 값이 없는 경우 리뷰 게시판에 회원등급이 노출되지 않습니다. */
	@JsonProperty("user_grade_id")
	private Integer userGradeId;

	/** 마지막 로그인 날짜. 초단위까지만 유효 */
	@JsonProperty("last_logged_in_at")
	private String lastLoggedInAt;

	/** 활성회원 여부. 활성회원이면 1, 휴면/삭제회원이면 0 */
	private Integer active;

	/** 생년월일. YYYY-mm-dd */
	@JsonProperty("birth_date")
	private String birthDate;

	@JsonProperty("user_status")
	private String userStatus;

}