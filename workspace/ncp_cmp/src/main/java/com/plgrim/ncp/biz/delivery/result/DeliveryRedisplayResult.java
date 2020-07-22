package com.plgrim.ncp.biz.delivery.result;

import com.plgrim.ncp.base.abstracts.AbstractResult;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class DeliveryRedisplayResult extends AbstractResult {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 주문 번호
	 OD || YYYYMMDD || 7자리 Cycle Sequence
	 */
	private String ordNo;

	/**
	 * 주문 상품 순번
	 */
	private java.lang.Integer ordGodTurn;

	/**
	 * 클레임 번호
	 CL || YYYYMMDD || 7자리 Cycle Sequence
	 */
	private String clmNo;

	/**
	 * 국내 운송장 번호
	 ㅁ. SQ_WAYBIL_NO 를 이용하여 채번
	 >. 50301200000 부터 시작

	 ㅁ. ASIS SEQ_DELI_CPMWBLNUM
	 >. Min Value : 50300841000
	 >. Max Value : 50301840999
	 >. Next Value : 50301095870 (2015-08-17)
	 */
	private String dmstcWaybilNo;

	/**
	 * ERP 상품 번호
	 */
	private String erpGodNo;

	/**
	 * 단품 명
	 */
	private String itmNm;

	/**
	 * 출고지시 취소 수량
	 */
	private java.lang.Long dlivyDrctCnclQty;

	/**
	 * 출고지시 취소 일시
	 */
	private java.util.Date dlivyDrctCnclDt;

	/**
	 * #50212 [개발]픽업 재진열 대상 알림(매장 Dashboard) 기능 추가
	 *
	 * 픽업 재진열 코드
	 ㅁ. 픽업 재진열 : PKUP_REDISP
	 >. 재진열 비대상 : REDISP_UTGT
	 >. 재진열 대상 : REDISP_TGT
	 >. 재진열 완료 : REDISP_COMPT
	 */
	private String pkupRedispCd;

}
