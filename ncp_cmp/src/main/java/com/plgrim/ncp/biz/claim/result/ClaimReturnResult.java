package com.plgrim.ncp.biz.claim.result;

import com.plgrim.ncp.base.abstracts.AbstractResult;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 반품/교환 완료배치에서 대상쿼리 결과값 담는 Result
 * com.plgrim.ncp.batch.server.data.datasource1.claim.returnCmpl.ClaimReturn 와 동일
 *
 * @author js279.kim
 * @since 2016. 7. 15
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ClaimReturnResult extends AbstractResult {

	/**
	 * 클레임 번호
	 CL || YYYYMMDD || 7자리 Cycle Sequence
	 */
	private String clmNo;

	/**
	 * 클레임 유형 코드
	 ㅁ. 클레임 유형 : CLM_TP
	 >. 부분취소 : PART_CNCL
	 >. 전체취소 : ALL_CNCL
	 >. 일반교환 : GNRL_EXCHG
	 >. 맞교환 : DRT_EXCHG
	 >. 반품 : RTGOD
	 >. 옵션변경 : OPT_MOD
	 */
	private String clmTpCd;
	
	/**
	 * 환불금액
	 * clm.PAY_EXCHG_RT_CRNCY_SUM_AMT
	 */
	private java.math.BigDecimal repayAmt;

	/**
	 * 클레임 상태 코드
	 ㅁ. 클레임상태 : CLM_STAT
	 >. 신청 : REQST
	 >. 결제 대기 : PAY_WAIT
	 >. 접수 : RCEPT
	 >. 완료 : COMPT
	 >. 철회 : WTHDRAW
	 */
	private String clmStatCd;
	
	/**
	 * PG구분
	 */
	private String pgSectCd;
	
	/**
	 * pg 상점 id
	 */
	private String pgStoreId;
	
	/**
	 * 적립금 임시차감 전송 결과 코드
	 */
	private String erpTempErpInc;
	
	/**
	 * 주문유형코드
	 */
	private String ordTpCd;
	
	/**
	 * 몰ID  >.2015-11-23 티몰요건 관련 추가
	 */
	private String mallId;

	/**
	 * 주문 번호
	 OD || YYYYMMDD || 7자리 Cycle Sequence
	 */
	private String ordNo;
	
	/**
	 * 언어코드
	 */
	private String langCd;

	/**
	 * 결제 환율 통화 합계 금액
	 사이트의 기준 통화를 기준으로 기준 통화금액에 환율을 적용한 금액
	 */
	private java.math.BigDecimal payExchgRtCrncySumAmt;

	/**
	 * 결제 통화 결제 금액
	 */
	private java.math.BigDecimal payCrncyPayAmt;

	/**
	 * 구매 이력 증가 전송 : ORD_ERP_TRNSMIS.ERP_OR_ERP
	 */
	private String ordErpOrErpYn;

	/**
	 * ERP 재고 전송 구분 코드
	 */
	private String erpInvTrnsmisSectCd;

	/**
	 * 회원 번호
	 ㅁ. 회원 가입시 부여되는 고유한 관리 번호
	 >. MB || YYYYMMDD || 7자리 Cycle
	 */
	private String mbrNo;
}
