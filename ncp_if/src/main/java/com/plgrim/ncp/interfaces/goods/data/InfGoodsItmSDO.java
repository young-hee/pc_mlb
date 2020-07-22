package com.plgrim.ncp.interfaces.goods.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.plgrim.ncp.interfaces.abstracts.InterfaceSDO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
@JsonInclude(value = Include.NON_EMPTY)
public class InfGoodsItmSDO extends InterfaceSDO {
	private static final long serialVersionUID = -479027138524495676L;

	/**
	 * 순서 ㅁ. SEQ : 시퀀스
	 */
	private java.lang.Long seq;

	/**
	 * 브랜드 ID ㅁ. BRAND : 브랜드코드 >. 디스커버리 : X >. MLB : M >. MLB Kids : I
	 */
	@JsonProperty("BRAND")
	private String brndId;

	/**
	 * 시즌 코드 ㅁ. SEASON : 시즌 코드 ㅁ. ERP에서 연계 받는 코드
	 */
	@JsonProperty("SEASON")
	private String sesonCd;

	/**
	 * 디자인 그룹 번호
	 */
	@JsonProperty("ERP_CODE")
	private String dsgnGrpNo;

	/**
	 * ERP 상품 번호 ㅁ. ERP_CODE : 제품코드
	 */

	private String erpGodNo;

	/**
	 * 바코드 ㅁ. BARCODE : 바코드
	 */
	@JsonProperty("BARCODE")
	private String bcode;

	/**
	 * 상품 명 ㅁ. PROD_NAME : 제품명
	 */
	@JsonProperty("PROD_NAME")
	private String godNm;

	/**
	 * 상품 명 ㅁ. PROD_NAME_ENG : 영문 제품명
	 */
	@JsonProperty("PROD_NAME_ENG")
	private String godNmEng;
	
	/**
	 * 상품 명 ㅁ. PROD_NAME_CHI : 중문 제품명
	 */
	@JsonProperty("PROD_NAME_CHI")
	private String godNmChi;
	
	/**
	 * 정소가 ㅁ. TAG_PRICE : 판매가(택가)
	 */
	@JsonProperty("TAG_PRICE")
	private java.math.BigDecimal rtlPrc;

	/**
	 * 색상 코드 ㅁ. COLOR_CODE : 컬러코드
	 */
	@JsonProperty("COLOR_CODE")
	private String colorCd;

	/**
	 * 세탁 코드 ㅁ. WASH : 세탁방법코드
	 */
	@JsonProperty("WASH")
	private String lndrCd;

	/**
	 * 옵션 값 코드 ㅁ. SIZE : 사이즈
	 */
	@JsonProperty("SIZE")
	private String optValCd;

	/**
	 * 추천 성별 코드 ㅁ. SEX : 성별 >. 여성 : D >. 남성 : U >. 공용 : X
	 */
	@JsonProperty("SEX")
	private String recomendSexCd;

	/**
	 * 품목 코드 ㅁ. ITEM_CD : 품목코드 ㅁ. ERP과 연동하여 처리 된건은 2자리
	 */
	@JsonProperty("ITEM_CD")
	private String prdlstCd;

	/**
	 * 생산 구분 코드 ㅁ. ORDER_TYPE : 생산구분 >. 국내완사입 : C >. 국외완사입 : D >. 국내C.M.T : M >.
	 * 국외C.M.T : N >. 국내임가공 : P >. 국외임가공 : Q
	 */
	@JsonProperty("ORDER_TYPE")
	private String prdcSectCd;

	/**
	 * 상품 구분 코드 ㅁ. ACCOUNT_TYPE : 상제품구분 >. 상품 : 1 >. 제품 : 3 >. 저장품 : 5
	 */
	@JsonProperty("ACCOUNT_TYPE")
	private String godSectCd;

	/**
	 * 원산지 명 ㅁ. ORIGIN : 원산지 ㅁ. 원산지
	 */
	@JsonProperty("ORIGIN")
	private String orgPlNm;
	/* 영,중 원산지 추가 */
	private String engOrgPlNm;
	private String chiOrgPlNm;

	/**
	 * 제조 년월 ㅁ. LAUNCHDT : 제조년월
	 */
	@JsonProperty("LAUNCHDT")
	private String mnfcturYm;

	/**
	 * KC 인증 번호 ㅁ. KC_NUM : KC 인증번호
	 */
	@JsonProperty("KC_NUM")
	private String kcCrtfcNo;

	/**
	 * 관련 ERP 상품 번호 ㅁ. RELATION_CODE : 관련상품코드
	 */
	@JsonProperty("RELATION_CODE")
	private String relatErpGodNo;

	/**
	 * FIT 구분 코드 ㅁ. FIT_COATPANTS : FIT 구분
	 */
	@JsonProperty("FIT_COATPANTS")
	private String fitSectCd;

	/**
	 * FIT 상세 구분 코드 ㅁ. FIT_TYPE : FIT 상세코드
	 */
	@JsonProperty("FIT_TYPE")
	private String fitDetailSectCd;

	/**
	 * 수입사 명 ㅁ. IMPRCOM_NM : 수입사명
	 */
	@JsonProperty("IMPRCOM_NM")
	private String imprcomNm;

	/**
	 * 취급 주의 사항 설명 ㅁ. TRTMNT_ATND_MATTER : 취급시주의사항
	 */
	@JsonProperty("TRTMNT_ATND_MATTER")
	private String trtmntAtndMatterDscr;

	/**
	 * 실소가 유형 코드
	 * ㅁ. SALETRTYPE : 판매가유형	 
	 */
	@JsonProperty("SALETRTYPE")
	private String csmrPrcTpCd;	
	
	/**
	 * ERP SET 상품 디자인번호 : | 로 연결됨 
	 */
	@JsonProperty("SET")
	private String dsgnGrpSetCont;
	
	/**
	 * 등록자 ID 등록한 관리자 번호
	 */
	private String regtrId;

	/**
	 * 등록 일시
	 */
	private java.util.Date regDt;

	/**
	 * 수정자 ID 수정한 관리자 번호
	 */
	private String udterId;

	/**
	 * 수정 일시
	 */
	private java.util.Date udtDt;

}