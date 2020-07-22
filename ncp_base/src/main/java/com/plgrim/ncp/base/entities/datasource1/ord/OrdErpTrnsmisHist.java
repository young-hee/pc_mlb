/**
 * @author : Generator(Generator)
 * @date : 2018-05-23
 * @version : 1.0
 * @desc :  generated by gradle
 */

package com.plgrim.ncp.base.entities.datasource1.ord;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.apache.ibatis.type.Alias;

import com.plgrim.ncp.base.abstracts.AbstractEntity;

/**
 * 주문 ERP 전송 이력
 */
@Data
@EqualsAndHashCode(callSuper=false)
@Alias("ordErpTrnsmisHist")
public class OrdErpTrnsmisHist extends AbstractEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 주문 번호
	 * OD || YYYYMMDD || 7자리 Cycle Sequence	 
	 */
	private String ordNo;


	/**
	 * 주문 ERP 전송 유형 코드
	 * ㅁ. 주문 ERP 전송 유형 : ORD_ERP_TRNSMIS_TP
	 *    >. 통합 포인트 사용 임시 : UNITY_PNT_USE_TMPR
	 *    >. 통합 포인트 적립 임시 : UNITY_PNT_ACCML_TMPR
	 *    >. 구매 이력 증가 전송 : ERP_OR_ERP
	 *    >. 구매 이력 감소 전송 : ERP_RE_ERP
	 *    >. 리테일 전송 : RTL_TRNSMIS
	 *    >. 통합 포인트 사용 확정 : UNITY_PNT_USE_DCSN
	 *    >. 통합 포인트 적립 확정 : UNITY_PNT_ACCML_DCSN	 
	 */
	private String ordErpTrnsmisTpCd;


	/**
	 * 주문 상품 순번	 
	 */
	private java.lang.Integer ordGodTurn;


	/**
	 * ERP 전송 코드
	 * ㅁ. 주문 ERP전송유형별 상태코드
	 * 
	 *  - 통합 포인트 : UNITY_PNT > 사용금액 임시차감
	 * Y: RE 성공
	 * RH: RE HUB 전송
	 * F: RE 실패.
	 * R: OR 성공
	 * OH: OR 허브 전송
	 * E: OR 실패.
	 * X: 해당없음.
	 * N: 대상이지만 미전송
	 * 
	 *  - 통합 포인트 : UNITY_PNT_RTGOD > 적립금액 임시차감
	 * Y: RE 성공
	 * RH: RE HUB 전송
	 * F: RE 실패.
	 * R: OR 성공
	 * OH: OR 허브 전송
	 * E: OR 실패.
	 * X: 해당없음.
	 * N: 대상이지만 미전송
	 * 
	 *  - 구매 이력 증가 전송 : ERP_OR_ERP 
	 * N: 대상이지만 미전송.
	 * H: HUB 전송
	 * X: 해당없음
	 * E: 전송실패.
	 * Y: 전송 성공
	 * 
	 *  - 구매 이력 감소 전송 : ERP_RE_ERP
	 * N: 대상이지만 미전송.
	 * H: HUB 전송
	 * X: 해당없음
	 * E: 전송실패.
	 * Y: 전송 성공
	 * 
	 *  - 리테일 전송 : RTL_TRNSMIS (ERP 전체 > 반품/교환에서 사용)
	 * N: 대상이지만 미전송.
	 * H: HUB 전송
	 * X: 해당없음
	 * E: 전송실패.
	 * Y: 전송 성공	 
	 */
	private String erpTrnsmisCd;


	/**
	 * 수량 순번
	 * ㅁ. 주문별 상품 및 수량에 대한 순번
	 * ㅁ. 주문 번호에 유일성을 가짐	 
	 */
	private java.lang.Integer qtyTurn;


	/**
	 * ERP 전송 일시	 
	 */
	private java.util.Date erpTrnsmisDt;


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
