package com.plgrim.ncp.interfaces.shop.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.plgrim.ncp.interfaces.abstracts.InterfaceSDO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@JsonInclude(value = Include.NON_EMPTY)
public class ShopSDO extends InterfaceSDO{
	private static final long serialVersionUID = 5565094299434930152L;

	/**
	 * ㅁ. SHOP_ID : 매장 ID	 
	 */
	@JsonProperty("SHOP_ID")
	private String shopId;

	/**
	 * ㅁ. SHOP_NM : 매장명 	 
	 */
	@JsonProperty("SHOP_NM")
	private String shopNm;

	/**
	 * ㅁ. USE_YN : 사용 여부 
	 */
	@JsonProperty("USE_YN")
	private String useYn;

	/**
	 * ㅁ. RPRSTIV_NM : 대표자명 
	 */
	@JsonProperty("RPRSTIV_NM")
	private String rprstivNm;

	/**
	 * ㅁ. LTTD : 위도
	 */
	@JsonProperty("LTTD")
	private String lttd;

	/**
	 * ㅁ. LGTD : 경도
	 */
	@JsonProperty("LGTD")
	private String lgtd;

	/**
	 * ㅁ. POST_NO : 우편번호
	 */
	@JsonProperty("POST_NO")
	private String postNo;

	/**
	 * ㅁ. BASE_ADDR : 기본주소
	 */
	@JsonProperty("BASE_ADDR")
	private String baseAddr;

	/**
	 * ㅁ. DETAIL_ADDR : 상세주소
	 */
	@JsonProperty("DETAIL_ADDR")
	private String detailAddr;

	/**
	 * ㅁ. SHOP_TEL_NO : 매장 전화번호
	 */
	@JsonProperty("SHOP_TEL_NO")
	private String shopTelNo;

	/**
	 * ㅁ. RPRSTIV_MOBIL_NO : 대표자 휴대전화번호
	 */
	@JsonProperty("RPRSTIV_MOBIL_NO")
	private String rprstivMobilNo;

	/**
	 * ㅁ. ASGN_PRIO_RANK : 배정 우선 순위
	 */
	@JsonProperty("ASGN_PRIO_RANK")
	private String asgnPrioRank;

	/**
	 * ㅁ. BRND_ID : 브랜드 ID 리스트
	 */
	@JsonProperty("BRND_ID")
	private String brndId;
	
	/**
	 * ㅁ. DLV_SHOP_YN : 배송 매장 여부 
	 */
	@JsonProperty("DLV_SHOP_YN")
	private String dlvShopYn;	

	/**
	 * ㅁ. PKUP_SHOP_YN : 픽업 매장 여부 
	 */
	@JsonProperty("PKUP_SHOP_YN")
	private String pkupShopYn;		

	/**
	 * ㅁ. TYPE_EDFM : 
	 */
	@JsonProperty("TYPE_EDFM")
	private String typeEdfm;		

	/**
	 * ㅁ. UPDATE_USERID : 사용안함
	 */
	@JsonProperty("UPDATE_USERID")
	private String updateUserId;		

	/**
	 * ㅁ. UPDATE_DATE : 사용안함
	 */
	@JsonProperty("UPDATE_DATE")
	private String updateDate;		

	/**
	 * ㅁ. INPUT_USERID : 사용안함
	 */
	@JsonProperty("INPUT_USERID")
	private String inputUserId;		

	/**
	 * ㅁ. INPUT_DATE : 사용안함
	 */
	@JsonProperty("INPUT_DATE")
	private String inputDate;		

	/**
	 * ㅁ. OPEN_DATE : 사용안함
	 */
	@JsonProperty("OPEN_DATE")
	private String openDate;	
	
	/**
	 * 등록자 ID
	 * 등록한 관리자 번호	 
	 */
	private String regtrId;

	/**
	 * 등록 일시	 
	 */
	private java.util.Date regDt;

	/**
	 * 수정자 ID
	 * 수정한 관리자 번호	 
	 */
	private String udterId;

	/**
	 * 수정 일시	 
	 */
	private java.util.Date udtDt;	
		
}
