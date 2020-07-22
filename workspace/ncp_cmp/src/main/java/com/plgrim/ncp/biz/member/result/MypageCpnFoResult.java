package com.plgrim.ncp.biz.member.result;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractResult;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrIssuCpn;

@Data
@EqualsAndHashCode(callSuper=false)
public class MypageCpnFoResult extends AbstractResult{

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */

	/**
	 *
	 */
	private static final long serialVersionUID = 462614658710562596L;


	/**회원 쿠폰 발급 Entity	 */
	MbrIssuCpn mbrIssuCpn;


	private String dcNm;		  //할인명

	private String cpnNm;		  //쿠폰제목

	private String validDay;	  //유효기간(일)

	private String validHour;	  //유효기간(시간)

	private String godNm;		  //상품명

	private String godFullNm;     //상품명

	private String godNo;		  //상품명

	private String prmNm;         // 프로모션 명

	private String prmNo;         // 프로모션 번호

	private String prmDscr;       // 프로모션 내용

	private String cpnTpCd;       // 쿠폰 유형 명

	private String cpnStatCd;     // 쿠폰 상태 명

	private String validDate;     // 쿠폰 유효기간

	private String validTime;     // 쿠폰 남은시간

	private String validBegDate;  // 쿠폰 시간 일자

	private String validEndDate;  // 쿠폰 종료 일자

	private String cpnUseDt;      // 쿠폰 사용 일자

	private String dcSectNm;      // 할인유형

	private String ordInfo;       // 주문정보

	private int ordCnt;       	  // 주문건수

	private String ordNo;    	  // 주문번호

	private String aplGodSecCd;   // 쿠폰 상품 Type

	private String imgUrl; 		  // 이미지경로

	private String salePrc; 	  // 가격

	private String lastSalePrc;   // 쿠폰가격

	private String brndId;        // Brand Id

	private String brndNm;        // Brand NM

	private String dspCtgryNm;    // 카테고리명

	private String godBrndNm;     // 상품 브랜드

	private String cpnBrndNm;     // 쿠폰 브랜드명

	private String aplGodSectCd;  // 쿠폰적용 구분 코드

	private String mbrCpnNo;	  // 쿠폰일련번호

	private String dcSectCd;	  // 쿠폰활인구분코드

	private String dcRtAmt;       // 쿠폰활인비율,가격

	private String dcRtAmtEx;       // 쿠폰활인비율,가격 환율 적용(금액인 경우만 환율 적용 처리)

	private String dcCpnPrc;       // 쿠폰활인가격

	private String cpnMaxDcPsbAmt;       // 쿠폰 최대 할인금액

	private String cpnMaxDcPsbAmtEx;       // 쿠폰 최대 할인금액 환율 적용

	private String cpnUseMinPchAmt;      // 쿠폰 사용 최소 구매금액

	private String cpnUseMinPchAmtEx;      // 쿠폰 사용 최소 구매금액 환율 적용

	private String datebyUsePsbBegHour;  // 쿠폰 유효시작시간

	private String datebyUsePsbEndHour;  // 쿠폰 유효종료시간

	private String cpnUsePsbDowsmon;     //쿠폰 유효요일

	//[#31806][16.12.29] 멤버십 제도개선(cpnKndCd, cpnCrtfcCd, useOffMall, mallId 추가)
	private String cpnKndCd;    //쿠폰종류(온라인,온오프라인)
	private String cpnCrtfcCd;  //쿠폰 인증 코드
	private String useOffMall;  //오프 사용 매장
	private String mallId;     	//mall id

	// #41113 나의 쿠폰 페이지에서 온오프라인 쿠폰 티켓번호 노출 170411 - 쿠폰사용가능기간 여부 조회
	private String usePsbPeriodYn;

	// 무료반품쿠폰
	private String sesonCd;


	/*
	 * ---------------------------------------------------------------------
	 * Constructors.
	 * ---------------------------------------------------------------------
	 */

	/*
	 * ---------------------------------------------------------------------
	 * public & interface method.
	 * ---------------------------------------------------------------------
	 */

	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */

}
