/* Copyright (c) 2015 plgrim, Inc.
 * All right reserved.
 * http://plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 *
 * ------------------------------------------------------------------------
 * @author      shhenry.choi
 * @since       2015. 3. 17
 */
package com.plgrim.ncp.biz.claim.data;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractDTO;

/**
 * 클레임 DTO.
 *
 * @author shhenry.choi
 * @since 2015. 3. 18
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ClaimDTO extends AbstractDTO {

    /**
     *
     */
    private static final long serialVersionUID = -1371693748618703028L;

/*****************************************************************************************************************************************************************
* Clm [클레임]
*****************************************************************************************************************************************************************/

    /**
     * 클레임 번호
     * CL || YYYYMMDD || 7자리 Cycle Sequence
     */
    private String clmNo;
    /**
     * 주문 번호
     * OD || YYYYMMDD || 7자리 Cycle Sequence
     */
    private String ordNo;
    /**
     * 클레임 상태 코드
    ㅁ. 클레임상태 : CLM_STAT
    >. 전체 취소 신청 : ALL_CNCL_REQST
    >. 전체 취소 접수 : ALL_CNCL_RCEPT
    >. 전체 취소 완료 : ALL_CNCL_COMPT
    >. 부분 취소 신청 : PART_CNCL_REQST
    >. 부분 취소 접수 : PART_CNCL_RCEPT
    >. 부분 취소 완료 : PART_CNCL_COMPT
    >. 반품 신청 : RTGOD_REQST
    >. 반품 접수 : RTGOD_RCEPT
    >. 반품 회수 지시 : RTGOD_RTRVL_DRCT
    >. 반품 회수 완료 : RTGOD_RTRVL_COMPT
    >. 교환 신청 : EXCHG_REQST
    >. 교환 접수 : EXCHG_RCEPT
    >. 교환 회수 지시 : EXCHG_RTRVL_DRCT
    >. 교환 회수 완료 : EXCHG_RTRVL_COMPT
    >. 교환 출고 지시 : EXCHG_DLIVY_DRCT
    >. 교환 배송중 : EXCHG_DLV_PROGRS
    >. 교환 배송 완료 : EXCHG_DLV_COMPT
    >. 맞교환 신청 : DRT_EXCHG_REQST
    >. 맞교환 접수 : DRT_EXCHG_RCEPT
    >. 맞교환 출고/회수 지시 : DRT_EXCHG_RTRVL_DLIVY_DRCT
    >. 맞교환 배송중 : DRT_EXCHG_DLV_PROGRS
    >. 맞교환 완료 : DRT_EXCHG_DLV_COMPT
    >. 클레임 취소 : CLM_CNCL
    >. 결제 대기 : PAY_WAIT
     */
    private String clmStatCd;
    /**
     * 클레임 유형 코드
    ㅁ. 클레임 유형 : CLM_TP
    >. 부분취소 : PART_CNCL
    >. 전체취소 : ALL_CNCL
    >. 일반교환 : GNRL_EXCHG
    >. 맞교환 : DRT_EXCHG
    >. 반품 : RTGOD
     */
    private String clmTpCd;
    /**
     * 클레임 일시
     */
    private java.util.Date clmDt;
    /**
     * 클레임 사유 코드
	ㅁ. 클레임 사유 : CLM_RSN
	   >. 고객변심취소 : CSTMR_CHGMIND_CNCL
	   >. 사이즈교환 : SIZE_EXCHG
	   >. 품절취소 : SLDOUT_CNCL
	   >. 배송지연취소 : DLV_DELAY_CNCL
	   >. 이중주문취소 : DPLC_ORD_CNCL
	   >. 시스템오류취소 : SYS_ERR_CNCL
	   >. 누락배송 : OVSITE_DLV
	   >. 택배분실 : HDRY_LOST
	   >. 옵션변경 : OPT_CHG
	   >. 상품불량 : GOD_BADN
	   >. 상품파손 : GOD_DMG
	   >. 상품 오염 : GOD_STAN
	   >. 기타 상품정보상이 : ETC_GOD_INFO_GAP
	   >. 사이즈 정보 상이 : SIZE_INFO_DFFRNC
	   >. 색상 정보 상이 : COLOR_INFO_DFFRNC
	   >. 오배송 : WN_DLV
	   >. 기타 : ETC
     */
    private String clmResnCd;
    /**
     * 클레임 사유 내용
     */
    private String clmResnCont;
    /**
     * 접수 관리자 ID
     */
    private String rceptAdminId;
    /**
     * 접수 일시
     */
    private java.util.Date rceptDt;
    /**
     * 완료 일시
     */
    private java.util.Date comptDt;
    /**
     * 통화 코드
    ISO 4217을 준수
    사용 코드 : CRNCY    통화
    KRW    대한민국 원
    USD    미국 달러
    CNY     런민비 (위안)
    JPY    일본 엔
     */
    private String crncyCd;
    /**
     * 기준 통화 합계 금액
    사이트의 기준 통화의 기준이 되는 금액
     */
    private java.math.BigDecimal stdrCrncySumAmt;
    /**
     * 결제 환율 통화 합계 금액
    사이트의 기준 통화를 기준으로 기준 통화금액에 환율을 적용한 금액
     */
    private java.math.BigDecimal payExchgRtCrncySumAmt;
    /**
     * 정소가 합계 금액
     */
    private java.math.BigDecimal rtlPrcSumAmt;
    /**
     * 실소가 합계 금액
     */
    private java.math.BigDecimal csmrPrcSumAmt;
    /**
     * 판매가 합계 금액
     */
    private java.math.BigDecimal salePrcSumAmt;
    /**
     * 서비스 합계 금액
     */
    private java.math.BigDecimal svcSumAmt;
    /**
     * 배송비 합계 금액
     */
    private java.math.BigDecimal dlvCstSumAmt;
    /**
     * 결제 합계 금액
     */
    private java.math.BigDecimal paySumAmt;
    /**
     * 상품 할인 합계 금액
     */
    private java.math.BigDecimal godDcSumAmt;
    /**
     * 임직원 할인 합계 금액
     */
    private java.math.BigDecimal empDcSumAmt;
    /**
     * 상품 쿠폰 할인 합계 금액
     */
    private java.math.BigDecimal godCpnDcSumAmt;
    /**
     * 장바구니 쿠폰 할인 합계 금액
     */
    private java.math.BigDecimal bskCpnDcSumAmt;
    /**
     * 배송비 쿠폰 할인 합계 금액
     */
    private java.math.BigDecimal dlvCstCpnDcSumAmt;
    /**
     * 통합 포인트 사용 합계 금액
     */
    private java.math.BigDecimal unityPntUseSumAmt;
    /**
     * 이벤트 포인트 사용 합계 금액
     */
    private java.math.BigDecimal evtPntUseSumAmt;
    /**
     * 웹포인트 사용 합계 금액
    적립은 되고 있으나 사용 할 수 없는부분
     */
    private java.math.BigDecimal webpntUseSumAmt;
    /**
     * 상품 요약
     */
    private String godSumry;
    /**
     * 판매 제휴사 주문 번호
     */
    private String saleAffComOrdNo;
    /**
     * 판매 제휴사 ID
     */
    private String saleAffComId;
    /**
     * 광고 제휴사 ID
     */
    private String advrtsAffComId;
    /**
     * 영수증 신청 처리 코드
    사용 코드 : RCPTFR_REQST_STAT    영수증신청상태
    NO_RCPTFR    영수증없음
    TAX_BILL_PUBLI_RCEPT    세금계산서(재)발행접수
    TAX_BILL_PUBLI_RCEPT_CNCL    세금계산서(재)발행접수취소
    TAX_BILL_PUBLI_COMPT    세금계산서(재)발행완료
    TAX_BILL_PUBLI_CNCL    세금계산서발행취소
    CASH_RCPTFR_PUBLI_RCEPT    현금영수증(재)발행접수
    CASH_RCPTFR_PUBLI_RCEPT_CNCL    현금영수증(재)발행접수취소
    CASH_RCPTFR_PUBLI_COMPT    현금영수증(재)발행완료
    CASH_RCPTFR_PUBLI_CNCL    현금영수증발행취소
     */
    private String rcptfrReqstPrcsCd;
    /**
     * 영수증 승인 번호
     */
    private String rcptfrAprvNo;
    /**
     * RET 전송 여부
     */
    private String retTrnsmisYn;
    /**
     * RET 전송 일시
     */
    private java.util.Date retTrnsmisDt;
    /**
     * 등록자 ID
    등록한 관리자 번호
     */
    private String regtrId;
    /**
     * 등록 일시
     */
    private java.util.Date regDt;
    /**
     * 수정자 ID
    수정한 관리자 번호
     */
    private String udterId;
    /**
     * 수정 일시
     */
    private java.util.Date udtDt;


/*****************************************************************************************************************************************************************
* CsoCnslMemo [고객서비스 상담 메모]
*****************************************************************************************************************************************************************/

    /**
     * 메모 일련번호
    ㅁ. 메모 등록을 관리하는 일련번호
    >. SEQUENCE를 이용하여 자동 발번 하도록 한다.
    >. 실직 식별자 로써 관리 함
     */
    private java.lang.Long memoSn;
    /**
     * 메모 등록자 ID
     */
    private String memoRegtrId;
    /**
     * 메모 유형 코드
    ㅁ. 메모가 작성된 관계 업무에 대한 연결 여부를 표현 한다.
	ㅁ. 메모 유형 : MEMO_TP
    >. 주문 : ORD
    >. 클레임 : CLM
    >. 회원 : MBR
     */
    private String memoTpCd;
    /**
     * 고객 유형 코드
    ㅁ. 고객 유형 : CSTMR_TP
    >. 악성 민원 : MLGN_CVPL
     */
    private String cstmrTpCd;
    /**
     * 노출 여부
    ㅁ. Y 인 경우, 고객상담>회원정보 출력 영역 고객성향에 출력됨
    ㅁ. N인 경우 고객 성향 관리에서만 관리 됨
     */
    private String expsrYn;
    /**
     * 메모 내용
    ㅁ. 상담자가 대상 건에 대한 업무적인 내용을 기입하는 항목으로 정의 함.
    ㅁ. 상담자의 메모 내역을 검색 할 경우 메모 내용에 대한 검색을 단어를 기준으로 검색이 가능 하도록 Domain Index (CTX-CAT)등을 고려 한다.
     */
    private String memoCont;

    /**
     * 회원 번호
    ㅁ. 회원 가입시 부여되는 고유한 관리 번호
    >. MB || YYYYMMDD || 7자리 Cycle
     */
    private String mbrNo;






}
