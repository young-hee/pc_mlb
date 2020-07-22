/**
 * @author : Generator(Generator)
 * @date : 2018-05-23
 * @version : 1.0
 * @desc :  generated by gradle
 */

package com.plgrim.ncp.base.entities.datasource1.evt;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.apache.ibatis.type.Alias;

import com.plgrim.ncp.base.abstracts.AbstractEntity;

/**
 * 이벤트
 */
@Data
@EqualsAndHashCode(callSuper=false)
@Alias("evt")
public class Evt extends AbstractEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 이벤트 번호
	 * ㅁ. EV || YYYYMMDD || 7자리 Cycle Sequence	 
	 */
	private String evtNo;


	/**
	 * 상위 이벤트 번호
	 * ㅁ. EV || YYYYMMDD || 7자리 Cycle Sequence	 
	 */
	private String upperEvtNo;


	/**
	 * 대표 하위 구분 코드
	 * ㅁ. 대표 하위 구분 : RPRST_LWPRT_SECT
	 *    >. 하위 이벤트 : LWPRT_EVT
	 *    >. 대표 이벤트 : RPRST_EVT	 
	 */
	private String rprstLwprtSectCd;


	/**
	 * 정렬 순서
	 * 우선 순위	 
	 */
	private java.lang.Integer sortSeq;


	/**
	 * 이벤트 상품 정렬 기준 코드	 
	 */
	private String evtGodSortStdrCd;


	/**
	 * 이벤트 명
	 * 해당 이벤트 명(몰 노출)	 
	 */
	private String evtNm;


	/**
	 * 이벤트 설명
	 * 이벤트에 대한 자세한 설명	 
	 */
	private String evtDscr;


	/**
	 * 이벤트 유형 코드
	 * ㅁ. 이벤트 유형 : EVT_TP
	 *    >. 전체추첨 : ALL_DRWT
	 *    >. 전체당첨 : ALL_PRIZE
	 *    >. 구매추첨 : PCH_DRWT
	 *    >. 구매당첨 : PCH_PRIZE
	 *    >. 타임쿠폰 : TM_CPN
	 *    >. SNS댓글 : SNS_REPLY
	 *    >. SNS 공유 : SNS_CNRS
	 *    >. 출석체크 : ATEND_CHK
	 *    >. 스탬프 : STMP	 
	 */
	private String evtTpCd;


	/**
	 * 이벤트 상태 코드
	 * ㅁ. 이벤트 상태 : EVT_STAT
	 *    >. 승인대기 : APRV_WAIT
	 *    >. 승인 : APRV
	 *    >. 취소 : CNCL	 
	 */
	private String evtStatCd;


	/**
	 * 이벤트 취소 설명	 
	 */
	private String evtCnclDscr;


	/**
	 * 이벤트 시작 일시
	 * 이벤트가 시작되는 시점	 
	 */
	private java.util.Date evtBegDt;


	/**
	 * 이벤트 종료 일시
	 * 이벤트가 종료되는 시점	 
	 */
	private java.util.Date evtEndDt;


	/**
	 * 전시 여부
	 * 전시여부	 
	 */
	private String dspYn;


	/**
	 * GNB 상단 배너 노출 여부	 
	 */
	private String gnbUpendBannerExpsrYn;


	/**
	 * PC 모바일 대표 이미지 개별 사용 여부
	 * ㅁ. Y인 경우 PC와 모바일 이미지를 개별적으로 사용
	 *    >. 이벤트 이미지.디바이스 구분 코드의 PC와 모바일 사용
	 * 
	 * ㅁ. N인 경우 PC와 모바일 이미지를 함께 사용
	 *    >. 이벤트 이미지.디바이스 구분 코드의 PC+모바일 만 사용	 
	 */
	private String pcMobileRprstImgIndUseYn;


	/**
	 * PC 모바일 배경 이미지 개별 사용 여부
	 * ㅁ. Y인 경우 PC와 모바일 이미지를 개별적으로 사용
	 *    >. 이벤트 이미지.디바이스 구분 코드의 PC와 모바일 사용
	 * 
	 * ㅁ. N인 경우 PC와 모바일 이미지를 함께 사용
	 *    >. 이벤트 이미지.디바이스 구분 코드의 PC+모바일 만 사용	 
	 */
	private String pcMobileBcrnImgIndUseYn;


	/**
	 * 이벤트 상세 유형 코드
	 * ㅁ. 이벤트 상세 유형 : EVT_DETAIL_TP
	 *    >. URL : URL
	 *    >. HTML : HTML	 
	 */
	private String evtDetailTpCd;


	/**
	 * PC URL	 
	 */
	private String pcUrl;


	/**
	 * 모바일 URL	 
	 */
	private String mobileUrl;


	/**
	 * PC HTML	 
	 */
	private String pcHtml;


	/**
	 * 모바일 HTML	 
	 */
	private String mobileHtml;


	/**
	 * 응모 시작 일시
	 * 이벤트에 응모 가능한 시작일
	 * 예를 들어 8seconds의 경우
	 * 8일 8시 8분 8초에 시작되는 이벤트를 
	 * 생성할 수도 있다	 
	 */
	private java.util.Date applcnBegDt;


	/**
	 * 응모 종료 일시
	 * 이벤트에 응모가능한 종료일	 
	 */
	private java.util.Date applcnEndDt;


	/**
	 * 일자별 응모 시작 시각	 
	 */
	private String datebyApplcnBegHour;


	/**
	 * 일자별 응모 종료 시각	 
	 */
	private String datebyApplcnEndHour;


	/**
	 * 추첨 일자
	 * 추첨이벤트의 경우 
	 * 응모자를 대상으로
	 * 당첨인원을 추첨 공표하는 일자	 
	 */
	private String drwtDate;


	/**
	 * 당첨 발표 일자
	 * 추첨이벤트의 경우 
	 * 응모자를 대상으로
	 * 당첨인원을 추첨 공표하는 일자	 
	 */
	private String prizePresnatnDate;


	/**
	 * 경품 발송 일자
	 * 추첨이벤트의 경우 
	 * 응모자를 대상으로
	 * 당첨인원을 추첨 공표하는 일자	 
	 */
	private String freeGiftSndDate;


	/**
	 * 응모 횟수 구분 코드
	 * ㅁ 응모에 참여할 수 있는 단위를 정한다
	 * 
	 * ㅁ. 응모 횟수 구분 : APPLCN_COUNT_SECT
	 *    >. 이벤트 기준 1회 : EVT_STDR_1BT
	 *    >. 1일 1회 : 1DAY_1BT
	 *    >. 무제한 : UNLMIT	 
	 */
	private String applcnCountSectCd;


	/**
	 * 구매 금액 기준 코드
	 * ㅁ. 구매 금액 기준 : PCH_AMT_STDR
	 *    >. 주문 건당 : ORD_PER
	 *    >. 기간 내 누적 : PD_IN_ACMTL	 
	 */
	private String pchAmtStdrCd;


	/**
	 * 참여 시작 구매 금액	 
	 */
	private java.math.BigDecimal partcptnBegPchAmt;


	/**
	 * 참여 종료 구매 금액	 
	 */
	private java.math.BigDecimal partcptnEndPchAmt;


	/**
	 * 구매 기간 시작 일시
	 * 이벤트가 시작되는 시점	 
	 */
	private java.util.Date pchPdBegDt;


	/**
	 * 구매 기간 종료 일시
	 * 이벤트가 종료되는 시점	 
	 */
	private java.util.Date pchPdEndDt;


	/**
	 * 응모 방법 코드
	 * ㅁ. 응모 방법 : APPLCN_MTHD
	 *    >. 수동 : PASSIV
	 *    >. 자동 : AUTO	 
	 */
	private String applcnMthdCd;


	/**
	 * 이벤트 구분자 유형 코드
	 * ㅁ. 기획전 구분자 유형 : PROMT_SPRPR_TP
	 *    >. 텍스트 : TEXT
	 *    >. 이미지 : IMG	 
	 */
	private String evtSprtrTpCd;


	/**
	 * 임직원 제외 여부
	 * ㅁ. Y인 경우 임직원 제외	 
	 */
	private String empExclsYn;


	/**
	 * 당첨 이력 코드
	 * ㅁ. 당첨 이력 : PRIZE_HIST
	 *    >. 당첨이력 무관 : PRIZE_HIST_UNLMIT
	 *    >. 최근 3개월 당첨자 제외 : PRIZE_HIST_3
	 *    >. 최근 6개월 당첨자 제외 : PRIZE_HIST_6
	 *    >. 최근 12개월 당첨자 제외 : PRIZE_HIST_12	 
	 */
	private String prizeHistCd;


	/**
	 * 구매 조건 코드
	 * ㅁ. 구매 조건 : PCH_CND
	 *    >. 구매 금액 무관 : PCH_AMT_UNLMIT
	 *    >. 구매 금액 순 : PCH_AMT_SEQ	 
	 */
	private String pchCndCd;


	/**
	 * 등록자 ID	 
	 */
	private String regtrId;


	/**
	 * 등록 일시	 
	 */
	private java.util.Date regDt;


	/**
	 * 수정자 ID	 
	 */
	private String udterId;


	/**
	 * 수정 일시	 
	 */
	private java.util.Date udtDt;
	
	private String spcifyUrlDspYn;
	
}
